package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Vector;

import Model.Client_Model;
import View.BXH_GUI;
import View.Block_GUI;
import View.ClientStart_GUI;
import View.Client_GUI;
import View.Login_GUI;

public class client extends Thread{
	Client_Model client_Model;
	Client_GUI client_GUI;
	Block_GUI block_GUI;
	Login_GUI login_GUI;
	BXH_GUI bxh_GUI;
	ClientStart_GUI clientStart_GUI;
	boolean Login = false;
	public client() throws UnknownHostException, IOException {
		clientStart_GUI = new ClientStart_GUI();
		login_GUI = new Login_GUI();
		login_GUI.setVisible(false);
		block_GUI = new Block_GUI();
		block_GUI.setVisible(false);
		client_GUI = new Client_GUI(3, 1, 1, 1);
		client_GUI.setVisible(false);
		
		bxh_GUI = new BXH_GUI();
		bxh_GUI.setVisible(false);
		clientStart_GUI.btstart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					String IPv4 = clientStart_GUI.tf_IPv4.getText();
					int port = Integer.parseInt(clientStart_GUI.port.getText());
					client_Model = new Client_Model(IPv4, port);
					start();
					login_GUI.setVisible(true);
					clientStart_GUI.dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//DataSQL_Model login_Model = new DataSQL_Model();
		login_GUI.login_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = login_GUI.username_login.getText();
				String password = login_GUI.password_login.getText();
				client_Model.login(userName, password);
			}
		});
		
		login_GUI.register_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = login_GUI.username_register.getText();
				String password = login_GUI.password_register1.getText();
				String fullname = login_GUI.name_register.getText();
				String age = login_GUI.age_register.getText();
				client_Model.register(userName, password, fullname, age);
			}
		});
	}
	
	public void addAction() {
		for (int i = 0; i < client_Model.getN(); i++)
			for (int j = 0; j < client_Model.getN(); j++) {
				client_GUI.board[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						client_Model.click(e.getActionCommand());
					}
				});
			}
		client_GUI.exit_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Hello");
				client_GUI.setVisible(false);
				
			}
		});
		
		client_GUI.listUser_bt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					client_Model.Send("BXH");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void run() {
		
		while(true) {
			if (Login) {
				if (client_Model.getNewGame()) {
					client_GUI = client_GUI.NewGame(client_Model.getN(), client_Model.getX(), client_Model.getY(), client_Model.getColor());
					client_GUI.setTitle(client_Model.getUserName(), client_Model.getScore());
					client_Model.setNewGame(false);
					addAction();
					
					block_GUI.setVisible(false);
					client_GUI.setVisible(true);				
				}
				if(client_Model.getBlock()) {
					block_GUI.setVisible(true);
					client_GUI.setVisible(false);
					client_Model.setBlock(false);
				}
				
				if (client_Model.getBXH()) {
					Vector data = client_Model.ArraylistToVector();
					System.out.println(data);
					bxh_GUI.setData(data);
					bxh_GUI.setVisible(true);
					client_Model.setBXH(false);
				}
			} else {
				if (client_Model.getLogin()) {
					Login = true;
					login_GUI.setVisible(false);
				}
				if (client_Model.getLoginFailer()) {
					login_GUI.notification("Incorrect account or password!");
					client_Model.setLoginFailer(false);
				}
				
				if (client_Model.getRegister() == 2) {
					login_GUI.notification("Register failer!");
					client_Model.setRegister(0);
				}
				
				if (client_Model.getRegister() == 1) {
					login_GUI.notification("Register success!");
					login_GUI.viewLogin();
					login_GUI.setTextLogin();
					client_Model.setRegister(0);
				}
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		new client();
	}
}

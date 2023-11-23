package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import models.ServerModel;
import views.ServerGUI;

public class Server {
	private ServerModel serverModel = null;
	
	public Server(){
		ServerGUI server_GUI = new ServerGUI();
		server_GUI.IP_lb.setText("IPv4: " + getIPv4());
		server_GUI.start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!server_GUI.isStatusOn()) {
					try {
						String text = server_GUI.port_tf.getText();
						int port = Integer.parseInt(text);
						serverModel = new ServerModel(port);
						server_GUI.setOn();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("New Game");
					serverModel.restartGame();
				}
			}
		});
	}
	public String getIPv4() {
		InetAddress iAddress;
		String currentIp = "localhost";
		try {
			iAddress = InetAddress.getLocalHost();
			currentIp = iAddress.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return currentIp;
	}
	
	public static void main(String[] args) {
		new Server();
	}
}

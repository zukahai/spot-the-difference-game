package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import models.ServerModel;
import views.ServerGUI;

public class server {
	public server(){
		ServerGUI server_GUI = new ServerGUI();
		server_GUI.IP_lb.setText("IPv4: " + getIPv4());
		server_GUI.start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String text = server_GUI.port_tf.getText();
					int port = Integer.parseInt(text);
					new ServerModel(port);
					server_GUI.setOn();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentIp;
	}
	
	public static void main(String[] args) {
		new server();
	}
}

package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ServerModel extends Thread {
	ServerSocket serverSocket;
	ArrayList<Socket> arrayListSocket = new ArrayList<Socket>();
	ArrayList<WorkThread> arrayListWorkThread = new ArrayList<WorkThread>();
	JTextArea data = new JTextArea("3 1 1 1");
	public ServerModel(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server starting.....");
		this.start();
	}
	
	public void send(String data, Socket s) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
		dataOutputStream.writeUTF(data);
		dataOutputStream.flush();
	}

	public void restartGame() {
		for (Socket s : arrayListSocket) {
			try {
				String newData = "3 1 1 1";
				data.setText(newData);
				send("NewGame " + newData, s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void run() {
		while(true) {
			try {
				Socket socket = this.serverSocket.accept();
				arrayListSocket.add(socket);
				send("NewGame " + data.getText(), socket);
				WorkThread workThread = new WorkThread(socket, data);
				arrayListWorkThread.add(workThread);
				for (WorkThread w : arrayListWorkThread)
					w.setArrayListSocket(arrayListSocket);;
				System.out.println(socket);
				workThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		new ServerModel(3333);
	}

}

class WorkThread extends Thread{
	DataSQLModel dataSQL_Model = new DataSQLModel();
	JTextArea data;
	public int n, x, y, color;
	Socket socket;
	ArrayList<Socket> arrayListSocket;
	private int NumberOfImage = 54;
	
	public WorkThread(Socket socket, JTextArea data) {
		this.socket = socket;
		this.data = data;
	}
	
	public void setArrayListSocket(ArrayList<Socket> arrayListSocket) {
		this.arrayListSocket = arrayListSocket;
	}
	
	public void Send(String data, Socket s) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
		dataOutputStream.writeUTF(data);
		dataOutputStream.flush();
	}
	public void SendObj(ArrayList<User> arrayListUser, Socket s) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
		objectOutputStream.writeObject(arrayListUser);
		objectOutputStream.flush();
	}
	
	public String randomData() {
		n = (int) (Math.random() * 100000) % 5 + 3;
		x = (int) (Math.random() * 100000) % n;
		y = (int) (Math.random() * 100000) % n;
		color = (int)(Math.random()*100000) % NumberOfImage + 1;
		return n + " " + x + " " + y + " " + color;
	}
	
	public boolean check(String st, String result) {
		String s[] = st.split(" ");
		String ans = s[1] + " " + s[2];
		s = result.split(" ");
		String ans2 = s[1] + " " + s[2];
		System.out.println("[" + ans + " " + ans2 + "]");
		return ans.equals(ans2);
	}
	
	public void run() {
		try {
			DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
			while(true) {
				String st = dataInputStream.readUTF();
				String key[] = st.split(" ");
				System.out.println("Client: " + st);
				String result = data.getText();

				if (key[0].equals("Click")) {
					if (check(st, result)) {
						System.out.println("h");
						String UserName = key[3];
						dataSQL_Model.increasePoint(UserName);
						result = randomData();
						data.setText(result);
						Send("+1", this.socket);
						for (Socket s : arrayListSocket) {
							Send("NewGame " + result, s);
						}
					}else {
						Send("Block", this.socket);
					}
				} else if (key[0].equals("Login")) {
					try {
						String userName = key[1];
						String password = key[2];
						if (dataSQL_Model.Login(userName, password)) {
							int Score = dataSQL_Model.getScore(userName);
							Send("Login Accept " + userName + " " + Score, socket);
						} else {
							Send("Login Fail", socket);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (key[0].equals("Register")) {
					String userName = key[1];
					String password = key[2];
					String fullname = key[3];
					int age = Integer.parseInt(key[4]);
					if (dataSQL_Model.Insert(userName, password, fullname, age)) {
						Send("Register Accept", socket);
					} else {
						Send("Register Fail", socket);
					}
				} else if(key[0].equals("BXH")) {
					Send("BXHserver", socket);
					SendObj(dataSQL_Model.getListUser(), socket);
				}
						
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
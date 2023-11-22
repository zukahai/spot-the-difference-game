 package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

public class Client_Model extends Thread{
	Socket socket;
	User user;
	ArrayList<User> arrayListUser = new ArrayList<User>();
	public String UserName = "";
	public int Score;
	public int n, x, y, color;
	public boolean NewGame = false;
	public boolean block = false;
	public boolean login = false;
	public boolean loginFailer = false;
	public int register = 0;
	public boolean BXH = false;
	public Client_Model(String IPv4, int port) throws UnknownHostException, IOException {
		socket = new Socket(IPv4, port);
		this.start();
	}
	
	public void setData(int n, int x, int y, int color) {
		this.n = n;
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void Send(String data) throws IOException {
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		dataOutputStream.writeUTF(data);
		dataOutputStream.flush();
	}
	
	public void login(String userName, String password) {
		try {
			Send("Login " + userName + " " + password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void register(String userName, String password, String fullname, String age) {
		try {
			Send("Register " + userName + " " + password + " " + fullname + " " + age);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void click(String x_y) {
		try {
			Send("Click "  + x_y + " " + UserName);
			Music music = new Music();
			music.sound();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void BXH(String BXH) {
		try {
			Send("BXH");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				String data = dataInputStream.readUTF();
				
				System.out.println("@@ " + data);
				String key[] = data.split(" ");
				if (key[0].equals("NewGame")) {
					n = Integer.parseInt(key[1]);
					x = Integer.parseInt(key[2]);
					y = Integer.parseInt(key[3]);
					color = Integer.parseInt(key[4]);
					NewGame = true;
				}else if(key[0].equals("+1")) {
					Score = Score+1;
				}else if(key[0].equals("Block")) {
					block = true;
				}
				else if (key[0].equals("Login")) {
					if (key[1].equals("Accept")) {
						login = true;
						UserName = key[2];
						Score = Integer.parseInt(key[3]);
					} else {
						loginFailer = true;
					}
				} else if (key[0].equals("Register")) {
					if (key[1].equals("Accept")) {
						register = 1;
					} else {
						register = 2;
					}
				} else if(key[0].equals("BXHserver")) {
					try {
						ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
						arrayListUser = (ArrayList<User>)objectInputStream.readObject();
						BXH = true;
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
				System.out.println("Server: " + NewGame);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Vector ArraylistToVector() {
		Vector vD = new Vector<>();
		for (User user : arrayListUser) {
			System.out.println(user);
			Vector vtemp = new Vector<>();
			vtemp.add(user.getUsername());
			vtemp.add(user.getFullname());
			vtemp.add(user.getAge());
			vtemp.add(user.getScore());
			vD.add(vtemp);
		}
		return vD;
	}

	public boolean getBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public boolean getLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public boolean getLoginFailer() {
		return loginFailer;
	}

	public void setLoginFailer(boolean loginFailer) {
		this.loginFailer = loginFailer;
	}

	public int getRegister() {
		return register;
	}

	public void setRegister(int register) {
		this.register = register;
	}
	
	public boolean getNewGame() {
		return NewGame;
	}

	public void setNewGame(boolean newGame) {
		NewGame = newGame;
	}
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getColor() {
		return color;
	}

	public void setColor(int Color) {
		this.color = Color;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		Score = score;
	}

	public boolean getBXH() {
		return BXH;
	}

	public void setBXH(boolean bXH) {
		BXH = bXH;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client_Model c = new Client_Model("localhost", 3333);
	}
}

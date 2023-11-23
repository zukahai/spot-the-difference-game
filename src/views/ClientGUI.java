package views;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClientGUI extends JFrame{
	public JButton board[][] = new JButton[100][100];
	public JButton listUser_bt;
	public JButton exit_bt;
	public Panel p = new Panel();
	Container cont;
	JFrame client;
	public int size;
	public int n = 4;
	public ClientGUI(int n, int x, int y, int color) {
		this.n = n;
		size = Math.min(this.getWidthScreen(), this.getHeightScreen()) - 50;
		client = new JFrame("Client");
		cont = new Container();
		cont = client.getContentPane();
		Panel p = new Panel();
		p.setLayout(new GridLayout(n, n));
		
		for(int i = 0; i<n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = new JButton();
				board[i][j].setActionCommand(i + " " + j);
				board[i][j].setIcon(getIcon(color, 1, (size) / n));
				board[i][j].setBorder(null);
				p.add(board[i][j]);
			}
		}
		board[x][y].setIcon(getIcon(color, 2, (size) / n));
		
		cont.add(p);
		
		Panel p1 = new Panel();
		p1.setLayout(new FlowLayout());
		listUser_bt = new JButton("BXH");
		listUser_bt.setBackground(new Color(127, 255, 212));
		exit_bt = new JButton("Exit");
		exit_bt.setBackground(new Color(127, 255, 212));
		p1.add(exit_bt);
		p1.add(listUser_bt);
		p1.setLayout(new GridLayout(1,2));
		cont.add(p1, "North");
		
		client.setSize(size, size + 55);
		client.setVisible(true);
		client.setLocationRelativeTo(null);
		client.setResizable(false);
	}
	
	private Icon getIcon(int index, int index2, int size) {
		int width = size, height = size;
		Image image = new ImageIcon(getClass().getResource("/images/image" + index + "_" + index2 + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
	
	public void notification(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public ClientGUI NewGame(int n, int x, int y, int color) {
		client.dispose();
		return new ClientGUI(n, x, y, color);
	}
	
	public void setTitle(String title, int Score) {
		client.setTitle("Client: " + title + " | Score: " + Score);
	}
	public void setVisible(boolean Visible) {
		client.setVisible(Visible);
	}
	public void Dispose() {
		client.dispose();
	}

	public int getWidthScreen() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
		return screenWidth;
	}

	public int getHeightScreen() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenHeight = (int) screenSize.getHeight();
		return screenHeight;
	}
	public static void main(String[] args) {
		ClientGUI client_GUI = new  ClientGUI(5, 0, 0,1);
		client_GUI.getWidthScreen();
	}
}

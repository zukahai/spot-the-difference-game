package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Color;

public class ClientStartGUI extends JFrame {

	private JPanel contentPane;
	public JTextField tf_IPv4;
	public JTextField port;
	public JButton btstart ;
	public Timer slide_timer;
	public int Nslide = 4;
	public int indexSlide = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientStartGUI frame = new ClientStartGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientStartGUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 0, 414, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbIPv4 = new JLabel("IPv4: ");
		lbIPv4.setBounds(10, 11, 48, 24);
		lbIPv4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lbIPv4);
		
		tf_IPv4 = new JTextField("localhost");
		tf_IPv4.setBackground(new Color(248, 248, 255));
		tf_IPv4.setBounds(46, 13, 125, 22);
		panel.add(tf_IPv4);
		tf_IPv4.setColumns(10);
		
		JLabel lbPort = new JLabel("Port: ");
		lbPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPort.setBounds(181, 12, 48, 22);
		panel.add(lbPort);
		
		port = new JTextField("3333");
		port.setBackground(new Color(248, 248, 255));
		port.setBounds(218, 13, 92, 24);
		panel.add(port);
		port.setColumns(10);
		
		btstart = new JButton("Start");
		btstart.setBackground(new Color(127, 255, 212));
		btstart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btstart.setBounds(320, 11, 84, 28);
		panel.add(btstart);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(10, 57, 414, 234);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton slide = new JButton("New button");
		slide.setBounds(10, 0, 404, 223);
		slide.setIcon(getIcon("slide1", slide.getWidth() + 100, slide.getHeight()));
		
		panel_1.add(slide);
		setLocationRelativeTo(null);
		setVisible(true);
		
		slide_timer = new Timer(1500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexSlide ++;
				if (indexSlide > Nslide)
					indexSlide = 1;
				slide.setIcon(getIcon("slide" + indexSlide , slide.getWidth() + 100, slide.getHeight()));
			}
		});
		slide_timer.start();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	private Icon getIcon(String name, int width, int height) {
		Image image = new ImageIcon(getClass().getResource("/images/" + name + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
}

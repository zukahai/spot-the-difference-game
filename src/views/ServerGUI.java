package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class ServerGUI extends JFrame {

	private JPanel contentPane;
	public JTextField port_tf;
	public JButton start;
	public JLabel IP_lb;
	public JLabel status;
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
					ServerGUI frame = new ServerGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(10, 0, 687, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		IP_lb = new JLabel("IPv4:");
		IP_lb.setBackground(new Color(224, 255, 255));
		IP_lb.setFont(new Font("Times New Roman", Font.BOLD, 18));
		IP_lb.setBounds(10, 12, 298, 37);
		panel.add(IP_lb);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(283, 22, 97, 14);
		panel.add(lblNewLabel_1);
		
		port_tf = new JTextField("3333");
		port_tf.setBackground(new Color(224, 255, 255));
		port_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		port_tf.setBounds(352, 13, 107, 32);
		panel.add(port_tf);
		port_tf.setColumns(10);
		
		start = new JButton("Start");
		start.setBackground(new Color(127, 255, 212));
		start.setBounds(578, 11, 97, 37);
		panel.add(start);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 665, 2);
		panel.add(separator);
		
		status = new JLabel("OFF");
		status.setForeground(new Color(255, 0, 0));
		status.setFont(new Font("Tahoma", Font.BOLD, 21));
		status.setBounds(488, 16, 97, 25);
		panel.add(status);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 70, 687, 373);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton slide = new JButton();
		slide.setBackground(new Color(224, 255, 255));
		slide.setBounds(0, 0, 687, 373);
		slide.setIcon(getIcon("slide3", slide.getWidth(), slide.getHeight()));
		
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
				slide.setIcon(getIcon("slide" + indexSlide , slide.getWidth(), slide.getHeight()));
			}
		});
		slide_timer.start();
	}
	
	public void setOn() {
		status.setText("ON");
		status.setForeground(Color.GREEN);
	}
	
	private Icon getIcon(String name, int width, int height) {
		Image image = new ImageIcon(getClass().getResource("/images/" + name + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
}

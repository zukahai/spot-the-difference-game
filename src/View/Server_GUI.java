package View;

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

public class Server_GUI extends JFrame {

	private JPanel contentPane;
	public JTextField port_tf;
	public JButton start;
	public JLabel IP_lb;
	public Timer slide_timer;
	public int Nslide = 3;
	public int indexSlide = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server_GUI frame = new Server_GUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Server_GUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 723, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 687, 59);
		contentPane.add(panel);
		panel.setLayout(null);
		
		IP_lb = new JLabel("IPv4:");
		IP_lb.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		IP_lb.setBounds(10, 11, 298, 37);
		panel.add(IP_lb);
		
		JLabel lblNewLabel_1 = new JLabel("PORT");
		lblNewLabel_1.setBounds(308, 22, 46, 14);
		panel.add(lblNewLabel_1);
		
		port_tf = new JTextField("3333");
		port_tf.setBounds(352, 13, 188, 32);
		panel.add(port_tf);
		port_tf.setColumns(10);
		
		start = new JButton("Start");
		start.setBounds(588, 18, 89, 23);
		panel.add(start);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 665, 2);
		panel.add(separator);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 70, 687, 373);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton slide = new JButton();
		slide.setBounds(0, 0, 687, 373);
		slide.setIcon(getIcon("slide1", slide.getWidth(), slide.getHeight()));
		
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
	
	private Icon getIcon(String name, int width, int height) {
		Image image = new ImageIcon(getClass().getResource("/image/" + name + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
}

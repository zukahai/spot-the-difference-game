package View;

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

public class ClientStart_GUI extends JFrame {

	private JPanel contentPane;
	public JTextField tf_IPv4;
	public JTextField port;
	public JButton btstart ;
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
					ClientStart_GUI frame = new ClientStart_GUI();
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
	public ClientStart_GUI() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 414, 46);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbIPv4 = new JLabel("IPv4: ");
		lbIPv4.setBounds(10, 11, 48, 24);
		lbIPv4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lbIPv4);
		
		tf_IPv4 = new JTextField("localhost");
		tf_IPv4.setBounds(46, 13, 144, 22);
		panel.add(tf_IPv4);
		tf_IPv4.setColumns(10);
		
		JLabel lbPort = new JLabel("Port: ");
		lbPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPort.setBounds(200, 12, 48, 22);
		panel.add(lbPort);
		
		port = new JTextField("3333");
		port.setBounds(239, 13, 92, 24);
		panel.add(port);
		port.setColumns(10);
		
		btstart = new JButton("Start");
		btstart.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btstart.setBounds(341, 11, 63, 24);
		panel.add(btstart);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 57, 414, 193);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton slide = new JButton("New button");
		slide.setBounds(10, 5, 394, 188);
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

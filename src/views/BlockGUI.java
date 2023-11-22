package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class BlockGUI extends JFrame {
	public Timer slide_timer;
	public int Nslide = 3;
	public int indexSlide = 1;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlockGUI frame = new BlockGUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BlockGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 504);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setForeground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(10, 81, 687, 373);
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setText("You are blocked :))");
		btnNewButton.setIcon(getIcon("slide1", btnNewButton.getWidth(), btnNewButton.getHeight()));
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("You have chosen incorrectly.");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Cambria Math", Font.BOLD, 20));
		lblNewLabel.setBounds(203, 11, 652, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblPleaseWaitFor = new JLabel("Please wait for someone else to choose correctly to continue the game");
		lblPleaseWaitFor.setForeground(new Color(0, 100, 0));
		lblPleaseWaitFor.setFont(new Font("Cambria Math", Font.BOLD, 20));
		lblPleaseWaitFor.setBounds(32, 46, 652, 24);
		contentPane.add(lblPleaseWaitFor);
		// setVisible(true);

		slide_timer = new Timer(1500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexSlide ++;
				if (indexSlide > Nslide)
					indexSlide = 1;
				btnNewButton.setIcon(getIcon("slide" + indexSlide , btnNewButton.getWidth(), btnNewButton.getHeight()));
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

package View;

import java.awt.BorderLayout;
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

public class Block_GUI extends JFrame {
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
					Block_GUI frame = new Block_GUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Block_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBounds(81, 36, 289, 214);
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setText("You are blocked :))");
		btnNewButton.setIcon(getIcon("slide1", btnNewButton.getWidth(), btnNewButton.getHeight()));
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("You are block ");
		lblNewLabel.setFont(new Font("Cambria Math", Font.PLAIN, 20));
		lblNewLabel.setBounds(137, 11, 194, 24);
		contentPane.add(lblNewLabel);
		setVisible(true);

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
		
	}
	private Icon getIcon(String name, int width, int height) {
		Image image = new ImageIcon(getClass().getResource("/image/" + name + ".png")).getImage();
		Icon icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
		return icon;
	}
	

}

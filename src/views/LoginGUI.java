package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame {

	public JPanel contentPane;
	public JPanel login_panel;
	public JPanel register_panel;
	public JTextField username_login;
	public JPasswordField password_login;
	public JTextField username_register;
	public JPasswordField password_register1;
	public JPasswordField password_register2;
	public JTextField name_register;
	public JTextField age_register;
	public JButton login_button;
	public JButton register_button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		// setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 401);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		login_panel = new JPanel();
		login_panel.setBackground(new Color(224, 255, 255));
		login_panel.setBounds(10, 11, 414, 342);
		contentPane.add(login_panel);
		login_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("T\u00E0i kho\u1EA3n");
		lblNewLabel.setBounds(44, 114, 79, 14);
		login_panel.add(lblNewLabel);
		
		username_login = new JTextField();
		username_login.setBounds(107, 106, 267, 30);
		login_panel.add(username_login);
		username_login.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(44, 200, 79, 14);
		login_panel.add(lblMtKhu);
		
		password_login = new JPasswordField();
		password_login.setBounds(107, 192, 267, 30);
		login_panel.add(password_login);
		
		JLabel lblNewLabel_1 = new JLabel("B\u1EA1n ch\u01B0a c\u00F3 t\u00E0i kho\u1EA3n?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_1.setBounds(44, 263, 199, 14);
		login_panel.add(lblNewLabel_1);
		
		login_button = new JButton("\u0110\u0103ng nh\u1EADp");
		login_button.setBackground(new Color(127, 255, 212));
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		login_button.setBounds(151, 293, 110, 23);
		login_panel.add(login_button);
		
		JLabel lblNewLabel_2 = new JLabel("\u0110\u0103ng nh\u1EADp", SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 11, 394, 30);
		login_panel.add(lblNewLabel_2);
		
		JLabel register_lb = new JLabel("\u0110\u0103ng k\u00FD");
		
		register_panel = new JPanel();
		register_panel.setBackground(new Color(224, 255, 255));
		register_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewRegister();
			}
		});
		register_lb.setForeground(Color.RED);
		register_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
		register_lb.setBounds(307, 263, 67, 14);
		login_panel.add(register_lb);
		
		register_panel.setVisible(false);
		register_panel.setLayout(null);
		register_panel.setBounds(10, 11, 414, 342);
		contentPane.add(register_panel);
		
		JLabel lblNewLabel_3 = new JLabel("T\u00E0i kho\u1EA3n");
		lblNewLabel_3.setBounds(44, 52, 79, 14);
		register_panel.add(lblNewLabel_3);
		
		username_register = new JTextField();
		username_register.setColumns(10);
		username_register.setBounds(107, 44, 267, 30);
		register_panel.add(username_register);
		
		JLabel lblMtKhu_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu_1.setBounds(44, 93, 79, 14);
		register_panel.add(lblMtKhu_1);
		
		password_register1 = new JPasswordField();
		password_register1.setBounds(107, 85, 267, 30);
		register_panel.add(password_register1);
		
		JLabel lblNewLabel_1_1 = new JLabel("B\u1EA1n \u0111\u00E3 c\u00F3 t\u00E0i kho\u1EA3n?");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel_1_1.setBounds(44, 275, 199, 14);
		register_panel.add(lblNewLabel_1_1);
		
		register_button = new JButton("\u0110\u0103ng k\u00FD");
		register_button.setBackground(new Color(127, 255, 212));
		register_button.setBounds(157, 300, 110, 23);
		register_panel.add(register_button);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u0110\u0103ng k\u00FD t\u00E0i kho\u1EA3n", SwingConstants.CENTER);
		lblNewLabel_2_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(10, 11, 394, 30);
		register_panel.add(lblNewLabel_2_1);
		
		JLabel lblMtKhu_1_1 = new JLabel("Nh\u1EADp l\u1EA1i");
		lblMtKhu_1_1.setBounds(44, 135, 79, 14);
		register_panel.add(lblMtKhu_1_1);
		
		password_register2 = new JPasswordField();
		password_register2.setBounds(107, 127, 267, 30);
		register_panel.add(password_register2);
		
		JLabel login_lb = new JLabel("\u0110\u0103ng nh\u1EADp");
		login_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewLogin();
			}
		});
		login_lb.setForeground(Color.RED);
		login_lb.setFont(new Font("Tahoma", Font.BOLD, 12));
		login_lb.setBounds(301, 274, 80, 14);
		register_panel.add(login_lb);
		
		JLabel lblNewLabel_3_1 = new JLabel("Họ tên");
		lblNewLabel_3_1.setBounds(44, 180, 79, 14);
		register_panel.add(lblNewLabel_3_1);
		
		name_register = new JTextField();
		name_register.setColumns(10);
		name_register.setBounds(107, 172, 267, 30);
		register_panel.add(name_register);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Tuổi");
		lblNewLabel_3_1_1.setBounds(44, 221, 79, 14);
		register_panel.add(lblNewLabel_3_1_1);
		
		age_register = new JTextField();
		age_register.setColumns(10);
		age_register.setBounds(107, 213, 267, 30);
		register_panel.add(age_register);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void viewRegister() {
		login_panel.setVisible(false);
		register_panel.setVisible(true);
	}
	
	public void viewLogin() {
		login_panel.setVisible(true);
		register_panel.setVisible(false);
	}

	public void setTextLogin() {
		username_login.setText(username_register.getText());
		password_login.setText(password_register1.getText());
	}
	
	public void notification(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}

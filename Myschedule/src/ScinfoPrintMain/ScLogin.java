package ScinfoPrintMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ScinfoDAO.ScInfoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ScLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textPassword;
	
	ScInfoDAO md = new ScInfoDAO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScLogin frame = new ScLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ScLogin() throws ClassNotFoundException, SQLException {
		
		super("MySchedule");
		ScInfoDAO md = new ScInfoDAO();
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("로그인");
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLogin.setBounds(130, 25, 80, 40);
		contentPane.add(lblLogin);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.setBounds(272, 73, 80, 50);
		contentPane.add(btnJoin);
		
		JButton btnLogin = new JButton("로그인");
		
		ScInfoDAO md1 = new ScInfoDAO();
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mem_id = textId.getText();
				String mem_pw = textPassword.getText();
				
			
				try {
					if( md1.Correct_Id( textId.getText() , textPassword.getText() ) ) {
						JOptionPane.showMessageDialog( ScLogin.this, "로그인 성공");
					
						// 메인으로 이동
					
						
						new Main().setVisible(true);
						setVisible(false);
						

						
						
						
					} else {
						JOptionPane.showMessageDialog( ScLogin.this, "로그인 실패 아디 비번 확인");
						textId.setText("");
						textPassword.setText("");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(136, 185, 100, 50);
		contentPane.add(btnLogin);
		
		textId = new JTextField();
		textId.setBounds(130, 77, 130, 40);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(130, 127, 130, 40);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(50, 70, 70, 40);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(50, 120, 70, 40);
		contentPane.add(lblPassword);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();	
				setVisible(false); 
				try {
					new ScMember().setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
	}
}

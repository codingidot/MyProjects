package ScinfoPrintMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ScinfoDAO.ScInfoDAO;

import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScMember extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textPw;
	JLabel lblCheck;

	ScInfoDAO md = new ScInfoDAO();
	
	
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 * 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScMember frame = new ScMember();
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
	public ScMember() throws ClassNotFoundException, SQLException {
		
		ScInfoDAO md = new ScInfoDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textId = new JTextField();
		textId.setBounds(130, 77, 130, 40);
		contentPane.add(textId);
		textId.setColumns(10);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 1, 1);
		contentPane.add(desktopPane);
		
		textPw = new JTextField();
		textPw.setBounds(130, 127, 130, 40);
		contentPane.add(textPw);
		textPw.setColumns(10);
		
		JButton btnCheck = new JButton("중복 확인");
		
		ScInfoDAO md1 = new ScInfoDAO();
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				
			
			//	String mem_id = textId.getText();
				
				try {
					if(md1.check_Id(textId.getText())) {
						JOptionPane.showConfirmDialog(ScMember.this, "ID중복");
						textId.setText("");
					}else {
						JOptionPane.showMessageDialog(ScMember.this, "사용가능");
					}
					
					
					
				}catch(SQLException e1){
					e1.printStackTrace();
					
				}
			
				
			
			}
		});
			
		btnCheck.setBounds(272, 73, 80, 50);
		contentPane.add(btnCheck);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(50, 70, 70, 40);
		contentPane.add(lblID);
		
		JLabel lblPw = new JLabel("Password");
		lblPw.setBounds(50, 120, 70, 40);
		contentPane.add(lblPw);
		
		JButton btnJoin = new JButton("회원 가입");
		btnJoin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
					if(md1.check_Id(textId.getText())) {
						JOptionPane.showConfirmDialog(ScMember.this, "ID중복","CHECK",JOptionPane.DEFAULT_OPTION);
						textId.setText("");
					}else {
						JOptionPane.showMessageDialog(ScMember.this, "사용가능","CHECK",JOptionPane.DEFAULT_OPTION);
					}
					
					
					
				}catch(SQLException e1){
					e1.printStackTrace();
					
				}
			
				
			
			}
				
			
		});
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String mem_id = textId.getText();
				String mem_pw = textPw.getText();
				
				int result = JOptionPane.showConfirmDialog(null, mem_id + " , " + mem_pw + "\n" + "확인하세요", "확인하세요",JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "다시 입력하세요");
				} else if (result == JOptionPane.YES_OPTION) {
					
					boolean b1 = md1.insert_member(mem_id, mem_pw);
						if(b1)
							System.out.println("insert ok");
						else 
							System.out.println("insert error");
					
					JOptionPane.showMessageDialog(null, "회원가입 완료");
					
					try {
						new ScLogin().setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
					
				
				}
			}
		});
		btnJoin.setBounds(136, 185, 100, 50);
		contentPane.add(btnJoin);
		
		JLabel lblJoin = new JLabel("회원 가입");
		lblJoin.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblJoin.setBounds(130, 25, 80, 40);
		contentPane.add(lblJoin);
		
		lblCheck = new JLabel("");
		lblCheck.setForeground(Color.RED);
		lblCheck.setBounds(282, 122, 80, 50);
		contentPane.add(lblCheck);
	}
}
	

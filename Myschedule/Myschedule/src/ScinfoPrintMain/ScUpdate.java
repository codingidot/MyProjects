package ScinfoPrintMain;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ScinfoDAO.ScInfoDAO;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JSpinner;
import javax.swing.JSeparator;
import java.awt.Font;

public class ScUpdate extends JFrame {

	
	/**
	 * 
	 */
	
	/**
	 * 
	 */
	protected static final int id = 0;
	protected static final String date = null;
	protected static final int finish_time = 0;
	protected static final String contents = null;
	protected static final int sc_id = 0;
	private JPanel contentPane;
	private JTextField textField_date;
	private JTextField textField_H;
	private JTextField textField_M;
	private JTextField textField_H2;
	private JTextField textField_M2;
	private JTextField textField_Con;
	protected int start_time;
	private JTextField textField;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScUpdate frame = new ScUpdate();
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
	public ScUpdate() throws ClassNotFoundException, SQLException {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("\uC77C\uC815 \uC218\uC815\uD558\uAE30");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 32));
		lblTitle.setBounds(56, 55, 420, 48);
		contentPane.add(lblTitle);
		
		JLabel lblstart = new JLabel("시작 시간");
		lblstart.setFont(new Font("굴림", Font.BOLD, 17));
		lblstart.setBounds(56, 271, 88, 48);
		contentPane.add(lblstart);
		
		JLabel lblfinish = new JLabel("끝나는 시간");
		lblfinish.setFont(new Font("굴림", Font.BOLD, 17));
		lblfinish.setBounds(56, 388, 116, 38);
		contentPane.add(lblfinish);
		
		JLabel lblcon = new JLabel("일정 내용 입력");
		lblcon.setFont(new Font("굴림", Font.BOLD, 17));
		lblcon.setBounds(55, 467, 183, 72);
		contentPane.add(lblcon);
		
		JLabel lbldate = new JLabel("날짜 입력");
		lbldate.setFont(new Font("굴림", Font.BOLD, 17));
		lbldate.setBounds(56, 197, 116, 38);
		contentPane.add(lbldate);
		
		JLabel lblEx1 = new JLabel("ex) 2019/03/05");
		lblEx1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx1.setBounds(51, 229, 153, 46);
		contentPane.add(lblEx1);
		
		JLabel lblEx2 = new JLabel("ex) 13시 59분");
		lblEx2.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx2.setBounds(56, 318, 116, 33);
		contentPane.add(lblEx2);
		
		JLabel lblEx3 = new JLabel("ex) 18\uC2DC 00\uBD84");
		lblEx3.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx3.setBounds(56, 424, 116, 33);
		contentPane.add(lblEx3);
		
		textField_date = new JTextField();
		textField_date.setBounds(203, 212, 310, 48);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
		
		textField_H = new JTextField();
		textField_H.setBounds(203, 302, 114, 56);
		contentPane.add(textField_H);
		textField_H.setColumns(10);
		
		JLabel lblH = new JLabel("시");
		lblH.setFont(new Font("굴림", Font.BOLD, 30));
		lblH.setBounds(329, 318, 50, 48);
		contentPane.add(lblH);
		
		textField_M = new JTextField();
		textField_M.setBounds(391, 302, 108, 56);
		contentPane.add(textField_M);
		textField_M.setColumns(10);
		
		JLabel lblM = new JLabel("분");
		lblM.setFont(new Font("굴림", Font.BOLD, 30));
		lblM.setBounds(511, 318, 50, 48);
		contentPane.add(lblM);
		
		textField_H2 = new JTextField();
		textField_H2.setBounds(203, 388, 114, 48);
		contentPane.add(textField_H2);
		textField_H2.setColumns(10);
		
		JLabel lblH2 = new JLabel("시");
		lblH2.setFont(new Font("굴림", Font.BOLD, 30));
		lblH2.setBounds(329, 398, 50, 38);
		contentPane.add(lblH2);
		
		textField_M2 = new JTextField();
		textField_M2.setBounds(391, 388, 108, 48);
		contentPane.add(textField_M2);
		textField_M2.setColumns(10);
		
		JLabel lblM2 = new JLabel("분");
		lblM2.setFont(new Font("굴림", Font.BOLD, 30));
		lblM2.setBounds(511, 398, 50, 38);
		contentPane.add(lblM2);
		
		textField_Con = new JTextField();
		textField_Con.setBounds(203, 497, 337, 168);
		contentPane.add(textField_Con);
		textField_Con.setColumns(10);

		System.out.println();
		JButton btnRg = new JButton("\uC218\uC815\uD558\uAE30");
		
		
		btnRg.setBounds(552, 625, 92, 40);
		contentPane.add(btnRg);
		 
		
		ScInfoDAO proDAO = new ScInfoDAO();
		btnRg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int sc_id = Integer.parseInt(textField.getText());
				String sc_date = textField_date.getText();
				int start_time = Integer.parseInt(textField_H.getText())*100+Integer.parseInt(textField_M.getText());
				int finish_time = Integer.parseInt(textField_H2.getText())*100+Integer.parseInt(textField_M2.getText());
				String sc_contents = textField_Con.getText();
				
				boolean	b1 = ScInfoDAO.update(sc_id,sc_date,start_time,finish_time,sc_contents);
				
				if(b1) 
					System.out.println("update ok");
				else
					System.out.println("update error");
	
			}
		});
		
		JButton btnMain = new JButton("\uB2EB\uAE30");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnMain.setBounds(656, 625, 92, 40);
		contentPane.add(btnMain);
		
		JButton btnReset = new JButton("\uB9AC\uC14B");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				textField_date.setText("");
				textField_H.setText("");
				textField_M.setText("");
			    textField_H2.setText("");
				textField_M2.setText("");
				textField_Con.setText("");	
			
			}
		});
		btnReset.setBounds(760, 626, 96, 38);
		contentPane.add(btnReset);
		
		textField = new JTextField();
		textField.setBounds(203, 133, 310, 56);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("\uC218\uC815\uD560 ID");
		lblId.setFont(new Font("굴림", Font.BOLD, 17));
		lblId.setBounds(56, 133, 96, 38);
		contentPane.add(lblId);
	}
}
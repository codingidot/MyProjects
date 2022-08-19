package ScinfoPrintMain;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ScinfoDAO.ScInfoDAO;


public class ScInsert extends JFrame {

	protected static String date = null;
	protected static int finish_time = 0;
	protected static String contents = null;
	protected static int sc_seq = 0;
	private JPanel contentPane;
	private JTextField textField_date;
	private JTextField textField_H;
	private JTextField textField_M;
	private JTextField textField_H2;
	private JTextField textField_M2;
	private JTextField textField_Con;
	public static int start_time;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScInsert frame = new ScInsert();
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
public ScInsert() throws ClassNotFoundException, SQLException {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("일정을 입력하세요");
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
		lbldate.setBounds(56, 171, 116, 48);
		contentPane.add(lbldate);
		
		JLabel lblEx1 = new JLabel("ex) 2022/03/05");
		lblEx1.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx1.setBounds(56, 208, 153, 53);
		contentPane.add(lblEx1);
		
		JLabel lblEx2 = new JLabel("ex) 13시 59분");
		lblEx2.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx2.setBounds(56, 318, 116, 33);
		contentPane.add(lblEx2);
		
		JLabel lblEx3 = new JLabel("ex) 18시 48분");
		lblEx3.setFont(new Font("굴림", Font.PLAIN, 17));
		lblEx3.setBounds(56, 424, 116, 33);
		contentPane.add(lblEx3);
		
		textField_date = new JTextField();
		textField_date.setBounds(203, 212, 310, 48);
		contentPane.add(textField_date);
		textField_date.setColumns(10);
		
		textField_H = new JTextField();
		textField_H.setBounds(203, 318, 114, 40);
		contentPane.add(textField_H);
		textField_H.setColumns(10);
		
		JLabel lblH = new JLabel("시");
		lblH.setFont(new Font("굴림", Font.BOLD, 30));
		lblH.setBounds(329, 318, 50, 48);
		contentPane.add(lblH);
		
		textField_M = new JTextField();
		textField_M.setBounds(391, 325, 108, 33);
		contentPane.add(textField_M);
		textField_M.setColumns(10);
		
		JLabel lblM = new JLabel("분");
		lblM.setFont(new Font("굴림", Font.BOLD, 30));
		lblM.setBounds(532, 318, 50, 48);
		contentPane.add(lblM);
		
		textField_H2 = new JTextField();
		textField_H2.setBounds(203, 398, 114, 38);
		contentPane.add(textField_H2);
		textField_H2.setColumns(10);
		
		JLabel lblH2 = new JLabel("시");
		lblH2.setFont(new Font("굴림", Font.BOLD, 30));
		lblH2.setBounds(329, 398, 50, 38);
		contentPane.add(lblH2);
		
		textField_M2 = new JTextField();
		textField_M2.setBounds(391, 398, 108, 38);
		contentPane.add(textField_M2);
		textField_M2.setColumns(10);
		
		JLabel lblM2 = new JLabel("분");
		lblM2.setFont(new Font("굴림", Font.BOLD, 30));
		lblM2.setBounds(532, 398, 50, 38);
		contentPane.add(lblM2);
		
		textField_Con = new JTextField();
		textField_Con.setBounds(203, 497, 387, 168);
		contentPane.add(textField_Con);
		textField_Con.setColumns(10);

		System.out.println();
		JButton btnRg = new JButton("\uC77C\uC815 \uB4F1\uB85D");
		
		ScInfoDAO sc_dao = new ScInfoDAO();
		
	
		
		btnRg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int number = 28; 
				String numberToString = String.format("%04d", number);
				System.out.println(numberToString);

			
				
				String date = textField_date.getText();
				int stime=Integer.parseInt(textField_H.getText());
				if(stime==0){
				stime=99;
                    }
				int ftime=Integer.parseInt(textField_H2.getText());
				if(ftime==0){
				ftime=99;
                    }
				int start_time =stime*100+Integer.parseInt(textField_M.getText());
				int finish_time = ftime*100+Integer.parseInt(textField_M2.getText());
				String contents = textField_Con.getText();
				String st_time = String.format("%04d", start_time);
				int fi_time = Integer.parseInt(String.format("%04d", finish_time));
				
				int st_time2 = Integer.parseInt(st_time);
				
			
				boolean b1 = ScInfoDAO.insert(sc_seq, date,st_time2,fi_time,contents);
				if(b1) 
					System.out.println("insert ok");
				else
					System.out.println("insert error");
				

			}
		});
		
		btnRg.setBounds(602, 625, 123, 40);
		contentPane.add(btnRg);
		 
		btnRg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "일정등록이 완료되었습니다.");
			}
		});
		
		JButton btnMain = new JButton("\uBA54\uC778\uD654\uBA74\uC73C\uB85C");
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		btnMain.setBounds(737, 625, 108, 40);
		contentPane.add(btnMain);
		
		JButton btnReset = new JButton("\uB9AC\uC14B");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_date.setText("");
				textField_H.setText("");
				textField_M.setText("");
			    textField_H2.setText("");
				textField_M2.setText("");
				textField_Con.setText("");	
			
			}
		});
		btnReset.setBounds(674, 577, 96, 38);
		contentPane.add(btnReset);
	}
}

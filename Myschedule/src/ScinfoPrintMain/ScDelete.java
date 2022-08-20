package ScinfoPrintMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ScinfoVO.ScInfoVO;
import ScinfoDAO.ScInfoDAO;

public class ScDelete extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScDelete frame = new ScDelete();
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
	public ScDelete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(36, 157, 82, 36);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBox.setToolTipText("");
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(36, 97, 82, 36);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"22", "23", "24", "25"}));
		comboBox_1.setToolTipText("");
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(36, 214, 82, 36);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_2.setToolTipText("");
		contentPane.add(comboBox_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 95, 295, 224);
		contentPane.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.setBounds(36, 275, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year=Integer.parseInt(comboBox_1.getSelectedItem().toString());
				int month=Integer.parseInt(comboBox.getSelectedItem().toString());
				int day=Integer.parseInt(comboBox_2.getSelectedItem().toString());
				String contents="";
			
				try {
					ScInfoDAO tid=new ScInfoDAO();
					for(ScInfoVO tiv : tid.schedule(year, month, day)) {
						String stime=String.valueOf(tiv.getStart_time());
						if(stime.length()==3) {
							if(stime.substring(0)=="0") {
								stime=stime.substring(0,2)+"0"+stime.substring(2);
							}else {
							stime="0"+stime;}
						} else if(Integer.parseInt(stime)>=9900) {
							stime="00"+stime.substring(2,stime.length());
						}
						String ftime=String.valueOf(tiv.getFinish_time());
						if(ftime.length()==3) {
							if(ftime.substring(0)=="0") {
								ftime=ftime.substring(0,2)+"0"+ftime.substring(2);
							}else if(ftime.substring(2)=="0") {
							ftime="0"+stime;}
						} else if(Integer.parseInt(ftime)>=9900) {
							ftime="00"+ftime.substring(2,ftime.length());
						}
						
						contents+="아이디: "+tiv.getSc_id()+"\n"+"날짜: "+tiv.getSc_date().substring(0,10)+"\n"+"시작시간: "+stime.substring(0,2)+"시 "+stime.substring(2,stime.length())+"분"+"\n"
								+ "끝나는시간: "+ftime.substring(0,2)+"시 "+ftime.substring(2,ftime.length())+"분"+"\n"+ "내용: "+tiv.getSc_contents()
								+"\n\n";
					}	
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
				textArea.setText(contents);
				
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\uC77C\uC815 \uC0AD\uC81C");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 30));
		lblNewLabel.setBounds(215, 10, 135, 55);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uB144");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(130, 97, 41, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC6D4");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(130, 157, 41, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\uC77C");
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(130, 216, 41, 30);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_1 = new JButton("\uB2EB\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(413, 368, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC0AD\uC81C\uD560 \uC77C\uC815 ID\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		lblNewLabel_2.setBounds(36, 320, 223, 44);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(36, 368, 116, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("\uC0AD\uC81C\uD558\uAE30");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(textField.getText());
				try {
					ScInfoDAO tidao=new ScInfoDAO();
					if(tidao.delete_schedule(id)==1) {
						textField.setText("삭제 되었습니다");
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(179, 368, 97, 23);
		contentPane.add(btnNewButton_2);
		
		
		
	}

}

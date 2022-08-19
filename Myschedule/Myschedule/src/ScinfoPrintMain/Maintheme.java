package ScinfoPrintMain;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Maintheme extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maintheme frame = new Maintheme();
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
	public Maintheme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 960);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Schejule!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("돋움", Font.BOLD, 30));
		lblNewLabel.setBounds(220, 40, 209, 70);
		contentPane.add(lblNewLabel);
		
		JButton sc_insert_button = new JButton("\uC77C\uC815\uC785\uB825");
		sc_insert_button.setFont(new Font("돋움", Font.PLAIN, 12));
		sc_insert_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//new insert 실행
		
			try {
				new ScInsert().setVisible(true);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		sc_insert_button.setBounds(220, 210, 200, 50);
		contentPane.add(sc_insert_button);
		
		JButton sc_select_button = new JButton("\uC77C\uC815\uAC80\uC0C9");
		sc_select_button.setFont(new Font("돋움", Font.PLAIN, 12));
		sc_select_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//new select 실행
			new ScSelect().setVisible(true);
			
			}
		});
		
		sc_select_button.setBounds(220, 290, 200, 50);
		contentPane.add(sc_select_button);
		
		JButton sc_update_button = new JButton("\uC77C\uC815\uC218\uC815");
		sc_update_button.setFont(new Font("돋움", Font.PLAIN, 12));
		sc_update_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//new update 실행
				try {
					new ScUpdate().setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sc_update_button.setBounds(220, 370, 200, 50);
		contentPane.add(sc_update_button);
		
		JButton sc_delete_button = new JButton("\uC77C\uC815\uC0AD\uC81C");
		sc_delete_button.setFont(new Font("돋움", Font.PLAIN, 12));
		sc_delete_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//new delete 실행
			new ScDelete().setVisible(true);
			}
		});
		sc_delete_button.setBounds(220, 450, 200, 50);
		contentPane.add(sc_delete_button);
		
		
		
		
		
		JButton sc_close_button = new JButton("\uC885\uB8CC\uD558\uAE30");
		sc_close_button.setFont(new Font("돋움", Font.PLAIN, 12));
		sc_close_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//클릭시 종료되는 버튼
			System.exit(0);
				
			}
		});
		sc_close_button.setBounds(220, 530, 200, 50);
		contentPane.add(sc_close_button);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Maintheme.class.getResource("/krim/agenda-g57bb3cd8d_640.png")));
		lblNewLabel_1.setBounds(12, 140, 602, 670);
		contentPane.add(lblNewLabel_1);
	}
}

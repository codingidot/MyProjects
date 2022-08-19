package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import data.DTO;
import font.setfont;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class client extends JFrame {

	//	:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::클라이언트 전역 선언:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  
	// 네트워크 객체 준비 =================================================
	static Socket socket;														// 소켓
	static ObjectInputStream ois;											// 객체 수신
	static ObjectOutputStream oos;										// 객체 발신
	
	
	// 클라 변수 준비 =====================================================
	static String id;																// 클라이언트 ID
	static String ip = "127.0.0.1";											// 접속 아이피
	static String code;															//	Task 코드 임시 저장
	static int roomNumber;													//	방 번호 저장
	static int playerNum;														//	플레이어가 몇p 인지 저장 (출력할 땐 +1 // 예) 1p 저장: 0, 출력: 0+1)
	static int rotation_sw;													//	출발점 돌았는지 체크용
	static int mooindo=0;													//	무인도 잔여 카운트
	
	
	// UI 준비 ========================================================
	static JPanel contentPane, p_login, p_signup, p_playroom, p_lobby, 
			p_lobby_room1, p_lobby_room2, p_lobby_room3, p_lobby_room4, p_lobby_room5, 
			p_lobby_room6, p_lobby_room7, p_lobby_room8, p_lobby_room9, p_lobby_room10,
			p_sidebar_user1, p_sidebar_user2, p_sidebar_user3, p_sidebar_user4,
			p_finish_p1,	p_finish_p2,	p_finish_p3,	p_finish_p4, p_finish;
	
	static JButton btn_pr_start, btn_pr_dice;
	
	static JTextField tf_login_id, tf_signup_id;
	
	static JPasswordField pf_login_pw, pf_signup_pw, pf_signup_pwre;
	
	static JLabel lbl_login_title, lbl_sidebar_title ,lbl_sidebar_location, 
			lbl_pr_title, lbl_pr_wait, lbl_pr_dice1, lbl_pr_dice2, lbl_pr_yourturn, lbl_pr_msg, lbl_pr_donate, lbl_pr_mooindo,
			lbl_room1_stats, lbl_room2_stats, lbl_room3_stats, lbl_room4_stats, lbl_room5_stats, 
			lbl_room6_stats, lbl_room7_stats, lbl_room8_stats, lbl_room9_stats, lbl_room10_stats,
			lbl_room1_title, lbl_room2_title, lbl_room3_title, lbl_room4_title, lbl_room5_title,
			lbl_room6_title, lbl_room7_title, lbl_room8_title, lbl_room9_title, lbl_room10_title,
			lbl_room1_ICON_1p, lbl_room2_ICON_1p, lbl_room3_ICON_1p, lbl_room4_ICON_1p, lbl_room5_ICON_1p,
			lbl_room6_ICON_1p, lbl_room7_ICON_1p, lbl_room8_ICON_1p, lbl_room9_ICON_1p, lbl_room10_ICON_1p,
			lbl_room1_ICON_2p, lbl_room2_ICON_2p, lbl_room3_ICON_2p, lbl_room4_ICON_2p, lbl_room5_ICON_2p,
			lbl_room6_ICON_2p, lbl_room7_ICON_2p, lbl_room8_ICON_2p, lbl_room9_ICON_2p, lbl_room10_ICON_2p,
			lbl_room1_ICON_3p, lbl_room2_ICON_3p, lbl_room3_ICON_3p, lbl_room4_ICON_3p, lbl_room5_ICON_3p,
			lbl_room6_ICON_3p, lbl_room7_ICON_3p, lbl_room8_ICON_3p, lbl_room9_ICON_3p, lbl_room10_ICON_3p,
			lbl_room1_ICON_4p, lbl_room2_ICON_4p, lbl_room3_ICON_4p, lbl_room4_ICON_4p, lbl_room5_ICON_4p,
			lbl_room6_ICON_4p, lbl_room7_ICON_4p, lbl_room8_ICON_4p, lbl_room9_ICON_4p, lbl_room10_ICON_4p,
			lbl_sidebar_picon1, lbl_sidebar_picon2, lbl_sidebar_picon3, lbl_sidebar_picon4,
			lbl_sidebar_pname1, lbl_sidebar_pname2, lbl_sidebar_pname3, lbl_sidebar_pname4,	
			lbl_sidebar_pmoney1, lbl_sidebar_pmoney2, lbl_sidebar_pmoney3, lbl_sidebar_pmoney4,
	
			lbl_pr_local0_user1, lbl_pr_local0_user2, lbl_pr_local0_user3, lbl_pr_local0_user4,
			lbl_pr_local1_user1, lbl_pr_local1_user2, lbl_pr_local1_user3, lbl_pr_local1_user4,
			lbl_pr_local2_user1, lbl_pr_local2_user2, lbl_pr_local2_user3, lbl_pr_local2_user4,
			lbl_pr_local3_user1, lbl_pr_local3_user2, lbl_pr_local3_user3, lbl_pr_local3_user4,
			lbl_pr_local4_user1, lbl_pr_local4_user2, lbl_pr_local4_user3, lbl_pr_local4_user4,
			lbl_pr_local5_user1, lbl_pr_local5_user2, lbl_pr_local5_user3, lbl_pr_local5_user4,
			lbl_pr_local6_user1, lbl_pr_local6_user2, lbl_pr_local6_user3, lbl_pr_local6_user4,
			lbl_pr_local7_user1, lbl_pr_local7_user2, lbl_pr_local7_user3, lbl_pr_local7_user4,
			lbl_pr_local8_user1, lbl_pr_local8_user2, lbl_pr_local8_user3, lbl_pr_local8_user4,
			lbl_pr_local9_user1, lbl_pr_local9_user2, lbl_pr_local9_user3, lbl_pr_local9_user4,
			lbl_pr_local10_user1, lbl_pr_local10_user2, lbl_pr_local10_user3, lbl_pr_local10_user4,
			lbl_pr_local11_user1, lbl_pr_local11_user2, lbl_pr_local11_user3, lbl_pr_local11_user4,
			lbl_pr_local12_user1, lbl_pr_local12_user2, lbl_pr_local12_user3, lbl_pr_local12_user4,
			lbl_pr_local13_user1, lbl_pr_local13_user2, lbl_pr_local13_user3, lbl_pr_local13_user4,
			lbl_pr_local14_user1, lbl_pr_local14_user2, lbl_pr_local14_user3, lbl_pr_local14_user4,
			lbl_pr_local15_user1, lbl_pr_local15_user2, lbl_pr_local15_user3, lbl_pr_local15_user4,
			lbl_pr_local16_user1, lbl_pr_local16_user2, lbl_pr_local16_user3, lbl_pr_local16_user4,
			lbl_pr_local17_user1, lbl_pr_local17_user2, lbl_pr_local17_user3, lbl_pr_local17_user4,
			lbl_pr_local18_user1, lbl_pr_local18_user2, lbl_pr_local18_user3, lbl_pr_local18_user4,
			lbl_pr_local19_user1, lbl_pr_local19_user2, lbl_pr_local19_user3, lbl_pr_local19_user4,
			lbl_pr_local20_user1, lbl_pr_local20_user2, lbl_pr_local20_user3, lbl_pr_local20_user4,
			lbl_pr_local21_user1, lbl_pr_local21_user2, lbl_pr_local21_user3, lbl_pr_local21_user4,
			lbl_pr_local22_user1, lbl_pr_local22_user2, lbl_pr_local22_user3, lbl_pr_local22_user4,
			lbl_pr_local23_user1, lbl_pr_local23_user2, lbl_pr_local23_user3, lbl_pr_local23_user4,
			lbl_pr_local24_user1, lbl_pr_local24_user2, lbl_pr_local24_user3, lbl_pr_local24_user4,
			lbl_pr_local25_user1, lbl_pr_local25_user2, lbl_pr_local25_user3, lbl_pr_local25_user4,
			lbl_pr_local26_user1, lbl_pr_local26_user2, lbl_pr_local26_user3, lbl_pr_local26_user4,
			lbl_pr_local27_user1, lbl_pr_local27_user2, lbl_pr_local27_user3, lbl_pr_local27_user4,
			lbl_pr_local28_user1, lbl_pr_local28_user2, lbl_pr_local28_user3, lbl_pr_local28_user4,
			lbl_pr_local29_user1, lbl_pr_local29_user2, lbl_pr_local29_user3, lbl_pr_local29_user4,
			lbl_pr_local30_user1, lbl_pr_local30_user2, lbl_pr_local30_user3, lbl_pr_local30_user4,
			lbl_pr_local31_user1, lbl_pr_local31_user2, lbl_pr_local31_user3, lbl_pr_local31_user4,
			lbl_sidebar_next1, lbl_sidebar_next2, lbl_sidebar_next3, lbl_sidebar_next4,
			
			lbl_pr_local1_build, lbl_pr_local2_build, lbl_pr_local3_build, lbl_pr_local5_build,
			lbl_pr_local6_build, lbl_pr_local7_build, lbl_pr_local9_build, lbl_pr_local10_build,
			lbl_pr_local11_build, lbl_pr_local13_build, lbl_pr_local14_build, lbl_pr_local15_build,
			lbl_pr_local17_build, lbl_pr_local18_build, lbl_pr_local19_build, lbl_pr_local21_build,
			lbl_pr_local22_build, lbl_pr_local23_build, lbl_pr_local24_build, lbl_pr_local25_build,
			lbl_pr_local26_build, lbl_pr_local27_build, lbl_pr_local29_build, lbl_pr_local30_build, lbl_pr_local31_build,
			lbl_finish_p2_money,	lbl_finish_bar2,	lbl_finish_p3, lbl_finish_p3_money,	lbl_finish_bar3, lbl_finish_p4, lbl_finish_p4_money, lbl_finish_bar4, lbl_pr_bg_end,
			lbl_finish_p1, lbl_finish_p1_money, lbl_finish_bar1, lbl_finish_p2, 
			lbl_finish_win1, lbl_finish_win2, lbl_finish_win3, lbl_finish_win4;
			
			;
	
	// 객체 준비 ==================================================================================
	static DTO dto = new DTO();													// DTO (데이터 전송 객체)
	static setfont setfont = new setfont();				// 전호형			// TTF 폰트 사용 기능							// https://github.com/Hx2DEV/marble/issues/34
	static c_T0000 T0000 = new c_T0000();				// 이상원			// 로그인 기능 										// https://github.com/Hx2DEV/marble/issues/26
	static c_T0003 T0003 = new c_T0003();				// 민지홍			// 방 목록 갱신 기능								// https://github.com/Hx2DEV/marble/issues/3
	static c_T0004 T0004 = new c_T0004();				// 조동현			// 방 생성 기능										// https://github.com/Hx2DEV/marble/issues/6
	static c_T0018 T0018 = new c_T0018(); 			// 이상원			// 회원가입 기능 									// https://github.com/Hx2DEV/marble/issues/27
	static c_T0006 T0006 = new c_T0006();				// 조동현			// 방 참가 기능										// https://github.com/Hx2DEV/marble/issues/22
	static c_T0009	T0009 = new c_T0009();				// 민지홍, 전호형	// 게임 시작 기능									// https://github.com/Hx2DEV/marble/issues/7
	static c_T0010 T0010 = new c_T0010();				// 전호형			// 턴 분배 기능 추가								// https://github.com/Hx2DEV/marble/issues/9
	static c_T0012 T0012 = new c_T0012();				// 양은지			// 통행료 지불 기능 추가						// https://github.com/Hx2DEV/marble/issues/19
	static c_T0013 T0013 = new c_T0013();				// 전호형			// [T0013] 게임 승/패 처리 기능 추가		// https://github.com/Hx2DEV/marble/issues/20
	static c_T0015 T0015 = new c_T0015();				// 조동현			// 주사위 굴리기 기능 추가 					// https://github.com/Hx2DEV/marble/issues/2
	static c_T0017 T0017 = new c_T0017();				// 양은지			// 통행료 지불 기능 추가						// https://github.com/Hx2DEV/marble/issues/24
	static c_T0020 T0020 = new c_T0020();				// 양은지			// 황금열쇠 기능 추가							// https://github.com/Hx2DEV/marble/issues/28
	static c_T0021 T0021 = new c_T0021();				// 양은지			// 좌표에 따른 TASK 동작 기능 추가			// https://github.com/Hx2DEV/marble/issues/29
	static c_T0023 T0023 = new c_T0023();				// 조동현			// 수령처에서 적립된 금액 수령 기능 추가	// https://github.com/Hx2DEV/marble/issues/31
	static c_T0024 T0024 = new c_T0024();				// 이인호			// 무인도 기능 추가								// https://github.com/Hx2DEV/marble/issues/32
	static c_T0008_1 T0008_1 = new c_T0008_1();	// 양은지			// 지역 구매 기능 추가1 						// https://github.com/Hx2DEV/marble/issues/8
	static c_T0008_2 T0008_2 = new c_T0008_2();	// 양은지			// 지역 구매 기능 추가2							// https://github.com/Hx2DEV/marble/issues/8
	
	
	// 동적 변수 사용 준비 ==============================================
	static HashMap<Integer, JLabel> HMroom_stats = new HashMap<>();
	static HashMap<Integer, JLabel> HMroom_title = new HashMap<>();
	static HashMap<Integer, JLabel> HMroom_ICON = new HashMap<>();
	static HashMap<Integer, JPanel> HMlobby_room = new HashMap<>();
	static HashMap<Integer, JPanel> HMsidebar_user = new HashMap<>();
	static HashMap<Integer, JLabel> HMsidebar_p = new HashMap<>();
	static HashMap<Integer, JLabel> HMp_icon = new HashMap<>();
	static HashMap<Integer, JLabel> HMp_localicon = new HashMap<>();	
	static HashMap<Integer, JPanel> HMfinish	=	new HashMap<>();
	static HashMap<Integer, JLabel> HMfinish_money	=	new HashMap<>();
	private JTextField tf_ipsetting;
	private JLabel lbl_login_setting;
	private JButton btn_ipsetting_save;

	
	

	// 메인 시작 ===================================================
	public static void main(String[] args) {
		//conn_receive();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client frame = new client();
					frame.setVisible(true);						
					guiHMput();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// JFrame 시작 ==========================================================
	public client() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		p_signup = new JPanel();
		p_signup.setVisible(false);
		
		p_playroom = new JPanel();
		p_playroom.setVisible(false);
		
		p_lobby = new JPanel();
		p_lobby.setVisible(false);
		
		p_login = new JPanel();
		p_login.setBackground(Color.WHITE);
		p_login.setBounds(0, 0, 768, 733);
		contentPane.add(p_login);
		p_login.setLayout(null);
		
		lbl_login_title = new JLabel("");
		lbl_login_title.setBounds(271, 106, 226, 226);		
		lbl_login_title.setIcon(new ImageIcon(client.class.getResource("/img/bg_title.png")));
		p_login.add(lbl_login_title);
		
		JPanel p_login_id = new JPanel();
		p_login_id.setBorder(new LineBorder(Color.GRAY));
		p_login_id.setBackground(Color.WHITE);
		p_login_id.setBounds(271, 342, 226, 42);
		p_login.add(p_login_id);
		p_login_id.setLayout(null);
		
		JLabel lbl_login_UI_id = new JLabel("ID");
		lbl_login_UI_id.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_login_UI_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login_UI_id.setBounds(10, 10, 31, 20);
		p_login_id.add(lbl_login_UI_id);
		
		tf_login_id = new JTextField();
		tf_login_id.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tf_login_id.setOpaque(false);
		tf_login_id.setBorder(null);
		tf_login_id.setBounds(66, 4, 150, 34);
		p_login_id.add(tf_login_id);
		tf_login_id.setColumns(10);
		
		JPanel p_login_pw = new JPanel();
		p_login_pw.setLayout(null);
		p_login_pw.setBorder(new LineBorder(Color.GRAY));
		p_login_pw.setBackground(Color.WHITE);
		p_login_pw.setBounds(271, 394, 226, 42);
		p_login.add(p_login_pw);
		
		JLabel lbl_login_UI_pw = new JLabel("PW");
		lbl_login_UI_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login_UI_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_login_UI_pw.setBounds(10, 10, 31, 20);
		p_login_pw.add(lbl_login_UI_pw);
		
		pf_login_pw = new JPasswordField();
		pf_login_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pf_login_pw.setOpaque(false);
		pf_login_pw.setBorder(null);
		pf_login_pw.setBounds(66, 4, 150, 34);
		p_login_pw.add(pf_login_pw);
		
		JButton btn_login_login = new JButton("로그인");
		btn_login_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_login_login) {			// 테스트용  더미
					if(tf_login_id.getText().equals("")) {		
						JOptionPane.showMessageDialog(null, "아이디 입력하세요.");
					}else {
						conn_receive();
						dto.setId(tf_login_id.getText());
						dto.setPw(pf_login_pw.getText());
						
						// [T0000] 로그인 기능 추가
						// https://github.com/Hx2DEV/marble/issues/26
						// 작업자 이상원
						// 작업자 전호형
						T0000.c_T0000_send(dto, oos);		// 로그인 기능 시작
					}
				}
			}
		});
		btn_login_login.setForeground(new Color(255, 255, 255));
		btn_login_login.setBackground(new Color(65, 105, 225));
		btn_login_login.setFont(setfont.fontSetting(14, "regular"));
		btn_login_login.setBounds(271, 446, 226, 42);
		p_login.add(btn_login_login);
		
		JButton btn_login_signup = new JButton("회원가입");
		btn_login_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_login_signup) {
					conn_receive();
					p_allHidden();
					p_signup.setVisible(true);
					tf_login_id.setText("");
					pf_login_pw.setText("");
				}
			}
		});
		btn_login_signup.setForeground(Color.GRAY);
		btn_login_signup.setBorder(null);
		btn_login_signup.setFont(setfont.fontSetting(14, "regular"));
		btn_login_signup.setBackground(Color.WHITE);
		btn_login_signup.setBounds(271, 498, 226, 42);
		p_login.add(btn_login_signup);
		
		JButton btn_setting = new JButton("");
		btn_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_setting) {
					btn_ipsetting_save.setVisible(true);
					lbl_login_setting.setVisible(true);
					tf_ipsetting.setVisible(true);
					tf_ipsetting.setText(ip);
				}
			}
		});
		btn_setting.setOpaque(false);
		btn_setting.setBackground(Color.WHITE);
		btn_setting.setBorder(null);
		btn_setting.setBorderPainted(false);
		btn_setting.setBounds(734, 699, 24, 24);
		btn_setting.setIcon(new ImageIcon(client.class.getResource("/img/gear.png")));
		p_login.add(btn_setting);
		
		tf_ipsetting = new JTextField();
		tf_ipsetting.setVisible(false);
		tf_ipsetting.setBounds(539, 699, 114, 24);
		tf_ipsetting.setFont(setfont.fontSetting(12, "regular"));
		p_login.add(tf_ipsetting);
		tf_ipsetting.setColumns(10);
		
		lbl_login_setting = new JLabel("서버IP");
		lbl_login_setting.setVisible(false);
		lbl_login_setting.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_login_setting.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_login_setting.setFont(setfont.fontSetting(12, "regular"));
		lbl_login_setting.setBounds(479, 699, 50, 24);
		p_login.add(lbl_login_setting);
		
		btn_ipsetting_save = new JButton("저장");
		btn_ipsetting_save.setBackground(Color.WHITE);
		btn_ipsetting_save.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_ipsetting_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_ipsetting_save) {
					btn_ipsetting_save.setVisible(false);
					lbl_login_setting.setVisible(false);
					tf_ipsetting.setVisible(false);
					ip = tf_ipsetting.getText();
				}
			}
		});
		btn_ipsetting_save.setVisible(false);
		btn_ipsetting_save.setFont(setfont.fontSetting(12, "regular"));
		btn_ipsetting_save.setBounds(663, 699, 61, 24);
		p_login.add(btn_ipsetting_save);
		
		
		p_lobby.setBackground(new Color(255, 255, 255));
		p_lobby.setBounds(0, 0, 768, 733);
		contentPane.add(p_lobby);
		p_lobby.setLayout(null);
		
		p_lobby_room1 = new JPanel();
		p_lobby_room1.addMouseListener(new MouseAdapter() {
			// [T0006] 방 참가 기능 추가
			// https://github.com/Hx2DEV/marble/issues/22
			// 작업자 조동현
			// 수정자 전호형
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==p_lobby_room1) {		
					if (lbl_room1_stats.getText()=="가득참") {
						JOptionPane.showMessageDialog(null, "참여할 자리가 없습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						dto.setRoomNumber("1");			// 요청된 방번호 저장
						dto.setId(id);							// DTO에 아이디 저장
						T0006.c_T0006_send(dto, oos);	// 방 참가 기능 시작
					}
					
		
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getSource()==p_lobby_room1) {
					if (lbl_room1_stats.getText()=="가득참") {
						JOptionPane.showMessageDialog(null, "참여할 자리가 없습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						dto.setRoomNumber("1");			// 요청된 방번호 저장
						dto.setId(id);							// DTO에 아이디 저장
						T0006.c_T0006_send(dto, oos);	// 방 참가 기능 시작
					}
				}
			}
		});
		p_lobby_room1.setVisible(false);
		p_lobby_room1.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room1.setBounds(26, 67, 711, 60);
		p_lobby.add(p_lobby_room1);
		p_lobby_room1.setLayout(null);
		
		lbl_room1_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room1_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room1_title.setBounds(180, 8, 300, 44);
		p_lobby_room1.add(lbl_room1_title);
		
		JLabel lbl_room1_number = new JLabel("1");
		lbl_room1_number.setBorder(null);
		lbl_room1_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room1_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room1_number.setBounds(10, 8, 44, 44);
		p_lobby_room1.add(lbl_room1_number);
		
		lbl_room1_ICON_1p = new JLabel("");
		lbl_room1_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room1_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room1.add(lbl_room1_ICON_1p);
		
		lbl_room1_ICON_2p = new JLabel("");
		lbl_room1_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room1_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room1.add(lbl_room1_ICON_2p);
		
		lbl_room1_ICON_3p = new JLabel("");
		lbl_room1_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room1_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room1.add(lbl_room1_ICON_3p);
		
		lbl_room1_ICON_4p = new JLabel("");
		lbl_room1_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room1_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room1.add(lbl_room1_ICON_4p);
		
		lbl_room1_stats = new JLabel("대기중");
		lbl_room1_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room1_stats.setOpaque(true);
		lbl_room1_stats.setBackground(new Color(72, 209, 204));
		lbl_room1_stats.setForeground(new Color(255, 255, 255));
		lbl_room1_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room1_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room1_stats.setBounds(64, 8, 90, 42);
		p_lobby_room1.add(lbl_room1_stats);
		
		p_lobby_room2 = new JPanel();
		p_lobby_room2.setVisible(false);
		p_lobby_room2.setLayout(null);
		p_lobby_room2.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room2.setBounds(26, 126, 711, 60);
		p_lobby.add(p_lobby_room2);
		
		lbl_room2_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room2_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room2_title.setBounds(180, 8, 300, 44);
		p_lobby_room2.add(lbl_room2_title);
		
		JLabel lbl_room2_number = new JLabel("2");
		lbl_room2_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room2_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room2_number.setBorder(null);
		lbl_room2_number.setBounds(10, 8, 44, 44);
		p_lobby_room2.add(lbl_room2_number);
		
		lbl_room2_ICON_1p = new JLabel("");
		lbl_room2_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room2_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room2.add(lbl_room2_ICON_1p);
		
		lbl_room2_ICON_2p = new JLabel("");
		lbl_room2_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room2_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room2.add(lbl_room2_ICON_2p);
		
		lbl_room2_ICON_3p = new JLabel("");
		lbl_room2_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room2_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room2.add(lbl_room2_ICON_3p);
		
		lbl_room2_ICON_4p = new JLabel("");
		lbl_room2_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room2_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room2.add(lbl_room2_ICON_4p);
		
		lbl_room2_stats = new JLabel("대기중");
		lbl_room2_stats.setOpaque(true);
		lbl_room2_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room2_stats.setForeground(Color.WHITE);
		lbl_room2_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room2_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room2_stats.setBackground(new Color(72, 209, 204));
		lbl_room2_stats.setBounds(64, 8, 90, 42);
		p_lobby_room2.add(lbl_room2_stats);
		
		p_lobby_room3 = new JPanel();
		p_lobby_room3.setVisible(false);
		p_lobby_room3.setLayout(null);
		p_lobby_room3.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room3.setBounds(26, 185, 711, 60);
		p_lobby.add(p_lobby_room3);
		
		lbl_room3_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room3_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room3_title.setBounds(180, 8, 300, 44);
		p_lobby_room3.add(lbl_room3_title);
		
		JLabel lbl_room3_number = new JLabel("3");
		lbl_room3_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room3_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room3_number.setBorder(null);
		lbl_room3_number.setBounds(10, 8, 44, 44);
		p_lobby_room3.add(lbl_room3_number);
		
		lbl_room3_ICON_1p = new JLabel("");
		lbl_room3_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room3_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room3.add(lbl_room3_ICON_1p);
		
		lbl_room3_ICON_2p = new JLabel("");
		lbl_room3_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room3_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room3.add(lbl_room3_ICON_2p);
		
		lbl_room3_ICON_3p = new JLabel("");
		lbl_room3_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room3_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room3.add(lbl_room3_ICON_3p);
		
		lbl_room3_ICON_4p = new JLabel("");
		lbl_room3_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room3_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room3.add(lbl_room3_ICON_4p);
		
		lbl_room3_stats = new JLabel("대기중");
		lbl_room3_stats.setOpaque(true);
		lbl_room3_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room3_stats.setForeground(Color.WHITE);
		lbl_room3_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room3_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room3_stats.setBackground(new Color(72, 209, 204));
		lbl_room3_stats.setBounds(64, 8, 90, 42);
		p_lobby_room3.add(lbl_room3_stats);
		
		p_lobby_room4 = new JPanel();
		p_lobby_room4.setVisible(false);
		p_lobby_room4.setLayout(null);
		p_lobby_room4.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room4.setBounds(26, 244, 711, 60);
		p_lobby.add(p_lobby_room4);
		
		lbl_room4_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room4_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room4_title.setBounds(180, 8, 300, 44);
		p_lobby_room4.add(lbl_room4_title);
		
		JLabel lbl_room4_number = new JLabel("4");
		lbl_room4_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room4_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room4_number.setBorder(null);
		lbl_room4_number.setBounds(10, 8, 44, 44);
		p_lobby_room4.add(lbl_room4_number);
		
		lbl_room4_ICON_1p = new JLabel("");
		lbl_room4_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room4_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room4.add(lbl_room4_ICON_1p);
		
		lbl_room4_ICON_2p = new JLabel("");
		lbl_room4_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room4_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room4.add(lbl_room4_ICON_2p);
		
		lbl_room4_ICON_3p = new JLabel("");
		lbl_room4_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room4_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room4.add(lbl_room4_ICON_3p);
		
		lbl_room4_ICON_4p = new JLabel("");
		lbl_room4_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room4_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room4.add(lbl_room4_ICON_4p);
		
		lbl_room4_stats = new JLabel("대기중");
		lbl_room4_stats.setOpaque(true);
		lbl_room4_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room4_stats.setForeground(Color.WHITE);
		lbl_room4_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room4_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room4_stats.setBackground(new Color(72, 209, 204));
		lbl_room4_stats.setBounds(64, 8, 90, 42);
		p_lobby_room4.add(lbl_room4_stats);
		
		p_lobby_room5 = new JPanel();
		p_lobby_room5.setVisible(false);
		p_lobby_room5.setLayout(null);
		p_lobby_room5.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room5.setBounds(26, 303, 711, 60);
		p_lobby.add(p_lobby_room5);
		
		lbl_room5_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room5_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room5_title.setBounds(180, 8, 300, 44);
		p_lobby_room5.add(lbl_room5_title);
		
		JLabel lbl_room5_number = new JLabel("5");
		lbl_room5_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room5_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room5_number.setBorder(null);
		lbl_room5_number.setBounds(10, 8, 44, 44);
		p_lobby_room5.add(lbl_room5_number);
		
		lbl_room5_ICON_1p = new JLabel("");
		lbl_room5_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room5_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room5.add(lbl_room5_ICON_1p);
		
		lbl_room5_ICON_2p = new JLabel("");
		lbl_room5_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room5_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room5.add(lbl_room5_ICON_2p);
		
		lbl_room5_ICON_3p = new JLabel("");
		lbl_room5_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room5_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room5.add(lbl_room5_ICON_3p);
		
		lbl_room5_ICON_4p = new JLabel("");
		lbl_room5_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room5_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room5.add(lbl_room5_ICON_4p);
		
		lbl_room5_stats = new JLabel("대기중");
		lbl_room5_stats.setOpaque(true);
		lbl_room5_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room5_stats.setForeground(Color.WHITE);
		lbl_room5_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room5_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room5_stats.setBackground(new Color(72, 209, 204));
		lbl_room5_stats.setBounds(64, 8, 90, 42);
		p_lobby_room5.add(lbl_room5_stats);
		
		p_lobby_room6 = new JPanel();
		p_lobby_room6.setVisible(false);
		p_lobby_room6.setLayout(null);
		p_lobby_room6.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room6.setBounds(26, 362, 711, 60);
		p_lobby.add(p_lobby_room6);
		
		lbl_room6_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room6_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room6_title.setBounds(180, 8, 300, 44);
		p_lobby_room6.add(lbl_room6_title);
		
		JLabel lbl_room6_number = new JLabel("6");
		lbl_room6_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room6_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room6_number.setBorder(null);
		lbl_room6_number.setBounds(10, 8, 44, 44);
		p_lobby_room6.add(lbl_room6_number);
		
		lbl_room6_ICON_1p = new JLabel("");
		lbl_room6_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room6_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room6.add(lbl_room6_ICON_1p);
		
		lbl_room6_ICON_2p = new JLabel("");
		lbl_room6_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room6_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room6.add(lbl_room6_ICON_2p);
		
		lbl_room6_ICON_3p = new JLabel("");
		lbl_room6_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room6_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room6.add(lbl_room6_ICON_3p);
		
		lbl_room6_ICON_4p = new JLabel("");
		lbl_room6_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room6_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room6.add(lbl_room6_ICON_4p);
		
		lbl_room6_stats = new JLabel("대기중");
		lbl_room6_stats.setOpaque(true);
		lbl_room6_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room6_stats.setForeground(Color.WHITE);
		lbl_room6_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room6_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room6_stats.setBackground(new Color(72, 209, 204));
		lbl_room6_stats.setBounds(64, 8, 90, 42);
		p_lobby_room6.add(lbl_room6_stats);
		
		p_lobby_room7 = new JPanel();
		p_lobby_room7.setVisible(false);
		p_lobby_room7.setLayout(null);
		p_lobby_room7.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room7.setBounds(26, 421, 711, 60);
		p_lobby.add(p_lobby_room7);
		
		lbl_room7_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room7_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room7_title.setBounds(180, 8, 300, 44);
		p_lobby_room7.add(lbl_room7_title);
		
		JLabel lbl_room7_number = new JLabel("7");
		lbl_room7_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room7_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room7_number.setBorder(null);
		lbl_room7_number.setBounds(10, 8, 44, 44);
		p_lobby_room7.add(lbl_room7_number);
		
		lbl_room7_ICON_1p = new JLabel("");
		lbl_room7_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room7_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room7.add(lbl_room7_ICON_1p);
		
		lbl_room7_ICON_2p = new JLabel("");
		lbl_room7_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room7_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room7.add(lbl_room7_ICON_2p);
		
		lbl_room7_ICON_3p = new JLabel("");
		lbl_room7_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room7_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room7.add(lbl_room7_ICON_3p);
		
		lbl_room7_ICON_4p = new JLabel("");
		lbl_room7_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room7_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room7.add(lbl_room7_ICON_4p);
		
		lbl_room7_stats = new JLabel("대기중");
		lbl_room7_stats.setOpaque(true);
		lbl_room7_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room7_stats.setForeground(Color.WHITE);
		lbl_room7_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room7_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room7_stats.setBackground(new Color(72, 209, 204));
		lbl_room7_stats.setBounds(64, 8, 90, 42);
		p_lobby_room7.add(lbl_room7_stats);
		
		p_lobby_room8 = new JPanel();
		p_lobby_room8.setVisible(false);
		p_lobby_room8.setLayout(null);
		p_lobby_room8.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room8.setBounds(26, 480, 711, 60);
		p_lobby.add(p_lobby_room8);
		
		lbl_room8_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room8_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room8_title.setBounds(180, 8, 300, 44);
		p_lobby_room8.add(lbl_room8_title);
		
		JLabel lbl_room8_number = new JLabel("8");
		lbl_room8_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room8_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room8_number.setBorder(null);
		lbl_room8_number.setBounds(10, 8, 44, 44);
		p_lobby_room8.add(lbl_room8_number);
		
		lbl_room8_ICON_1p = new JLabel("");
		lbl_room8_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room8_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room8.add(lbl_room8_ICON_1p);
		
		lbl_room8_ICON_2p = new JLabel("");
		lbl_room8_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room8_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room8.add(lbl_room8_ICON_2p);
		
		lbl_room8_ICON_3p = new JLabel("");
		lbl_room8_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room8_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room8.add(lbl_room8_ICON_3p);
		
		lbl_room8_ICON_4p = new JLabel("");
		lbl_room8_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room8_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room8.add(lbl_room8_ICON_4p);
		
		lbl_room8_stats = new JLabel("대기중");
		lbl_room8_stats.setOpaque(true);
		lbl_room8_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room8_stats.setForeground(Color.WHITE);
		lbl_room8_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room8_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room8_stats.setBackground(new Color(72, 209, 204));
		lbl_room8_stats.setBounds(64, 8, 90, 42);
		p_lobby_room8.add(lbl_room8_stats);
		
		p_lobby_room9 = new JPanel();
		p_lobby_room9.setVisible(false);
		p_lobby_room9.setLayout(null);
		p_lobby_room9.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room9.setBounds(26, 539, 711, 60);
		p_lobby.add(p_lobby_room9);
		
		lbl_room9_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room9_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room9_title.setBounds(180, 8, 300, 44);
		p_lobby_room9.add(lbl_room9_title);
		
		JLabel lbl_room9_number = new JLabel("9");
		lbl_room9_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room9_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room9_number.setBorder(null);
		lbl_room9_number.setBounds(10, 8, 44, 44);
		p_lobby_room9.add(lbl_room9_number);
		
		lbl_room9_ICON_1p = new JLabel("");
		lbl_room9_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room9_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room9.add(lbl_room9_ICON_1p);
		
		lbl_room9_ICON_2p = new JLabel("");
		lbl_room9_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room9_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room9.add(lbl_room9_ICON_2p);
		
		lbl_room9_ICON_3p = new JLabel("");
		lbl_room9_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room9_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room9.add(lbl_room9_ICON_3p);
		
		lbl_room9_ICON_4p = new JLabel("");
		lbl_room9_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room9_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room9.add(lbl_room9_ICON_4p);
		
		lbl_room9_stats = new JLabel("대기중");
		lbl_room9_stats.setOpaque(true);
		lbl_room9_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room9_stats.setForeground(Color.WHITE);
		lbl_room9_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room9_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room9_stats.setBackground(new Color(72, 209, 204));
		lbl_room9_stats.setBounds(64, 8, 90, 42);
		p_lobby_room9.add(lbl_room9_stats);
		
		p_lobby_room10 = new JPanel();
		p_lobby_room10.setVisible(false);
		p_lobby_room10.setLayout(null);
		p_lobby_room10.setBorder(new LineBorder(Color.GRAY));
		p_lobby_room10.setBounds(26, 598, 711, 60);
		p_lobby.add(p_lobby_room10);
		
		lbl_room10_title = new JLabel("방 제목이 이 곳에 출력 됩니다.");
		lbl_room10_title.setFont(setfont.fontSetting(16, "regular"));
		lbl_room10_title.setBounds(180, 8, 300, 44);
		p_lobby_room10.add(lbl_room10_title);
		
		JLabel lbl_room10_number = new JLabel("10");
		lbl_room10_number.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room10_number.setFont(setfont.fontSetting(16, "bold"));
		lbl_room10_number.setBorder(null);
		lbl_room10_number.setBounds(10, 8, 44, 44);
		p_lobby_room10.add(lbl_room10_number);
		
		lbl_room10_ICON_1p = new JLabel("");
		lbl_room10_ICON_1p.setBounds(490, 8, 44, 44);
		lbl_room10_ICON_1p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room10.add(lbl_room10_ICON_1p);
		
		lbl_room10_ICON_2p = new JLabel("");
		lbl_room10_ICON_2p.setBounds(544, 8, 44, 44);
		lbl_room10_ICON_2p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room10.add(lbl_room10_ICON_2p);
		
		lbl_room10_ICON_3p = new JLabel("");
		lbl_room10_ICON_3p.setBounds(598, 8, 44, 44);
		lbl_room10_ICON_3p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room10.add(lbl_room10_ICON_3p);
		
		lbl_room10_ICON_4p = new JLabel("");
		lbl_room10_ICON_4p.setBounds(652, 8, 44, 44);
		lbl_room10_ICON_4p.setIcon(new ImageIcon(client.class.getResource("/img/icon_unready.png")));
		p_lobby_room10.add(lbl_room10_ICON_4p);
		
		lbl_room10_stats = new JLabel("대기중");
		lbl_room10_stats.setOpaque(true);
		lbl_room10_stats.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_room10_stats.setForeground(Color.WHITE);
		lbl_room10_stats.setFont(setfont.fontSetting(16, "ebold"));
		lbl_room10_stats.setBorder(new LineBorder(Color.GRAY));
		lbl_room10_stats.setBackground(new Color(72, 209, 204));
		lbl_room10_stats.setBounds(64, 8, 90, 42);
		p_lobby_room10.add(lbl_room10_stats);
		
		JButton btn_lobby_refresh = new JButton("목록 새로고침");
		btn_lobby_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_lobby_refresh) {
					T0003.c_T0003_send(dto, oos);	// 방 목록 새로고침 기능 시작
				}
			}
		});
		btn_lobby_refresh.setFont(setfont.fontSetting(12, "regular"));
		btn_lobby_refresh.setBackground(Color.WHITE);
		btn_lobby_refresh.setBounds(409, 668, 159, 48);
		p_lobby.add(btn_lobby_refresh);
		
		JButton btn_lobby_makeroom = new JButton("방 만들기");
		btn_lobby_makeroom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_lobby_makeroom) {
					T0004.c_T0004_send(dto, oos, contentPane); // 방 생성 기능 시작	
				}
			}
		});
		btn_lobby_makeroom.setForeground(Color.WHITE);
		btn_lobby_makeroom.setFont(setfont.fontSetting(12, "bold"));
		btn_lobby_makeroom.setBackground(new Color(65, 105, 225));
		btn_lobby_makeroom.setBounds(578, 668, 159, 48);
		p_lobby.add(btn_lobby_makeroom);
		
		JLabel lbl_lobby_UI_line = new JLabel("");
		lbl_lobby_UI_line.setBackground(Color.DARK_GRAY);
		lbl_lobby_UI_line.setOpaque(true);
		lbl_lobby_UI_line.setBounds(26, 55, 711, 1);
		p_lobby.add(lbl_lobby_UI_line);
		
		JLabel lbl_lobby_UI_title = new JLabel("게임 로비");
		lbl_lobby_UI_title.setFont(setfont.fontSetting(24, "ebold"));
		lbl_lobby_UI_title.setBounds(28, 20, 305, 28);
		p_lobby.add(lbl_lobby_UI_title);
		
		JLabel lbl_lobby_bg = new JLabel("");
		lbl_lobby_bg.setBackground(Color.GRAY);
		lbl_lobby_bg.setBounds(26, 67, 711, 591);
		p_lobby.add(lbl_lobby_bg);
		p_playroom.setBackground(new Color(245, 245, 245));
		p_playroom.setBounds(0, 0, 768, 733);
		contentPane.add(p_playroom);
		p_playroom.setLayout(null);
		
		btn_pr_start = new JButton("게임 시작");
		btn_pr_start.setVisible(false);
		btn_pr_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_pr_start) {
					// [T0009] 게임 시작 기능 추가
					// https://github.com/Hx2DEV/marble/issues/7
					// 작업자 민지홍, 전호형
					T0009.c_T0009_send(dto, oos);
				}
			}
		});
		
		btn_pr_dice = new JButton("주사위 던지기");
		btn_pr_dice.setVisible(false);
		btn_pr_dice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_pr_dice) {
					// [T0015] 주사위 굴리기 기능 추가
					// https://github.com/Hx2DEV/marble/issues/2
					// 작업자 조동현
					// 수정자 전호형
					
					if(mooindo == 0) {	// 무인도가 아니라면
						lbl_pr_mooindo.setVisible(false);
						T0015.c_T0015_send(dto, oos, id, roomNumber);
						lbl_pr_msg.setText("");					// 인게임 메세지 초기화
						btn_pr_dice.setVisible(false);			// 주사위 굴리기 버튼 숨기기
						lbl_pr_yourturn.setVisible(true);		// 다른 사람의 턴 이미지 보이기
					} else {					// 무인도라면
						if(mooindo==1) {
							System.out.println("무인도에서 다음 턴에 탈출합니다.");
						}else {
							lbl_pr_msg.setText("무인도 탈출까지 "+(mooindo-1)+" 턴 남았습니다.");
						}
						mooindo = mooindo -1;
						dto.setId(id);
						lbl_pr_wait.setVisible(true);
						btn_pr_dice.setVisible(false);			// 주사위 굴리기 버튼 숨기기
						lbl_pr_yourturn.setVisible(false);		// 다른 사람의 턴 이미지 보이기
						T0010.c_T0010_send(dto, oos, roomNumber);			// 턴 넘기기
					}
				}
			}
		});
		
		lbl_pr_wait = new JLabel("턴 대기중 입니다...");
		lbl_pr_wait.setOpaque(true);
		lbl_pr_wait.setForeground(Color.LIGHT_GRAY);
		lbl_pr_wait.setVisible(false);
		
		lbl_pr_dice2 = new JLabel("");
		lbl_pr_dice2.setVisible(false);
		
		lbl_pr_yourturn = new JLabel("");
		lbl_pr_yourturn.setBackground(Color.ORANGE);
		lbl_pr_yourturn.setOpaque(true);
		lbl_pr_yourturn.setVisible(false);
		
		lbl_pr_mooindo = new JLabel("");
		lbl_pr_mooindo.setVisible(false);
		
		p_finish = new JPanel();
		p_finish.setVisible(false);
		p_finish.setBorder(new LineBorder(Color.GRAY));
		p_finish.setBounds(234, 216, 300, 300);
		p_playroom.add(p_finish);
		p_finish.setLayout(null);
		
		lbl_finish_win1 = new JLabel("");
		lbl_finish_win1.setVisible(false);
		lbl_finish_win1.setBounds(20, 101, 24, 24);
		lbl_finish_win1.setIcon(new ImageIcon(client.class.getResource("/img/win.png")));
		p_finish.add(lbl_finish_win1);
		
		lbl_finish_win3 = new JLabel("");
		lbl_finish_win3.setVisible(false);
		lbl_finish_win3.setBounds(20, 195, 24, 24);
		lbl_finish_win3.setIcon(new ImageIcon(client.class.getResource("/img/win.png")));
		p_finish.add(lbl_finish_win3);
		
		lbl_finish_win4 = new JLabel("");
		lbl_finish_win4.setVisible(false);
		lbl_finish_win4.setBounds(20, 242, 24, 24);
		lbl_finish_win4.setIcon(new ImageIcon(client.class.getResource("/img/win.png")));
		p_finish.add(lbl_finish_win4);
		
		lbl_finish_win2 = new JLabel("");
		lbl_finish_win2.setVisible(false);
		lbl_finish_win2.setBounds(20, 148, 24, 24);
		lbl_finish_win2.setIcon(new ImageIcon(client.class.getResource("/img/win.png")));
		p_finish.add(lbl_finish_win2);
		
		JLabel lbl_pr_finish_title = new JLabel("게임 결과");
		lbl_pr_finish_title.setForeground(Color.WHITE);
		lbl_pr_finish_title.setOpaque(true);
		lbl_pr_finish_title.setBackground(new Color(30, 144, 255));
		lbl_pr_finish_title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_finish_title.setFont(setfont.fontSetting(16, "ebold"));
		lbl_pr_finish_title.setBounds(1, 14, 298, 55);
		p_finish.add(lbl_pr_finish_title);
		
		p_finish_p1 = new JPanel();
		p_finish_p1.setVisible(false);
		p_finish_p1.setBorder(new LineBorder(Color.GRAY));
		p_finish_p1.setBackground(Color.WHITE);
		p_finish_p1.setBounds(34, 89, 232, 48);
		p_finish.add(p_finish_p1);
		p_finish_p1.setLayout(null);
		
		lbl_finish_p1 = new JLabel("");
		lbl_finish_p1.setBounds(24, 10, 110, 28);
		lbl_finish_p1.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p1.add(lbl_finish_p1);
		
		lbl_finish_p1_money = new JLabel("");
		lbl_finish_p1_money.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_finish_p1_money.setBounds(158, 10, 64, 28);
		lbl_finish_p1_money.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p1.add(lbl_finish_p1_money);
		
		lbl_finish_bar1 = new JLabel("");
		lbl_finish_bar1.setOpaque(true);
		lbl_finish_bar1.setBackground(Color.GRAY);
		lbl_finish_bar1.setBounds(147, 16, 1, 15);
		p_finish_p1.add(lbl_finish_bar1);
		
		p_finish_p2 = new JPanel();
		p_finish_p2.setVisible(false);
		p_finish_p2.setLayout(null);
		p_finish_p2.setBorder(new LineBorder(Color.GRAY));
		p_finish_p2.setBackground(Color.WHITE);
		p_finish_p2.setBounds(34, 136, 232, 48);
		p_finish.add(p_finish_p2);
		
		lbl_finish_p2 = new JLabel("");
		lbl_finish_p2.setBounds(24, 10, 110, 28);
		lbl_finish_p2.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p2.add(lbl_finish_p2);
		
		lbl_finish_p2_money = new JLabel("");
		lbl_finish_p2_money.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_finish_p2_money.setBounds(158, 10, 64, 28);
		lbl_finish_p2_money.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p2.add(lbl_finish_p2_money);
		
		lbl_finish_bar2 = new JLabel("");
		lbl_finish_bar2.setOpaque(true);
		lbl_finish_bar2.setBackground(Color.GRAY);
		lbl_finish_bar2.setBounds(147, 16, 1, 15);
		p_finish_p2.add(lbl_finish_bar2);
		
		p_finish_p3 = new JPanel();
		p_finish_p3.setVisible(false);
		p_finish_p3.setLayout(null);
		p_finish_p3.setBorder(new LineBorder(Color.GRAY));
		p_finish_p3.setBackground(Color.WHITE);
		p_finish_p3.setBounds(34, 183, 232, 48);
		p_finish.add(p_finish_p3);
		
		lbl_finish_p3 = new JLabel("");
		lbl_finish_p3.setBounds(24, 10, 110, 28);
		lbl_finish_p3.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p3.add(lbl_finish_p3);
		
		lbl_finish_p3_money = new JLabel("");
		lbl_finish_p3_money.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_finish_p3_money.setBounds(158, 10, 64, 28);
		lbl_finish_p3_money.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p3.add(lbl_finish_p3_money);
		
		lbl_finish_bar3 = new JLabel("");
		lbl_finish_bar3.setOpaque(true);
		lbl_finish_bar3.setBackground(Color.GRAY);
		lbl_finish_bar3.setBounds(147, 16, 1, 15);
		p_finish_p3.add(lbl_finish_bar3);
		
		p_finish_p4 = new JPanel();
		p_finish_p4.setVisible(false);
		p_finish_p4.setLayout(null);
		p_finish_p4.setBorder(new LineBorder(Color.GRAY));
		p_finish_p4.setBackground(Color.WHITE);
		p_finish_p4.setBounds(34, 229, 232, 48);
		p_finish.add(p_finish_p4);
		
		lbl_finish_p4 = new JLabel("");
		lbl_finish_p4.setBounds(24, 10, 110, 28);
		lbl_finish_p4.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p4.add(lbl_finish_p4);
		
		lbl_finish_p4_money = new JLabel("");
		lbl_finish_p4_money.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_finish_p4_money.setBounds(158, 10, 64, 28);
		lbl_finish_p4_money.setFont(setfont.fontSetting(12, "bold"));
		p_finish_p4.add(lbl_finish_p4_money);
		
		lbl_finish_bar4 = new JLabel("");
		lbl_finish_bar4.setOpaque(true);
		lbl_finish_bar4.setBackground(Color.GRAY);
		lbl_finish_bar4.setBounds(147, 16, 1, 15);
		p_finish_p4.add(lbl_finish_bar4);
		
		lbl_pr_bg_end = new JLabel("");
		lbl_pr_bg_end.setVisible(false);
		lbl_pr_bg_end.setBounds(0, 0, 768, 733);
		lbl_pr_bg_end.setIcon(new ImageIcon(client.class.getResource("/img/bg_finish.png")));
		p_playroom.add(lbl_pr_bg_end);
		
		lbl_pr_mooindo.setBounds(309, 142, 158, 122);
		lbl_pr_mooindo.setIcon(new ImageIcon(client.class.getResource("/img/mooindo.png")));
		p_playroom.add(lbl_pr_mooindo);
		
		lbl_pr_msg = new JLabel("");
		lbl_pr_msg.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_msg.setForeground(new Color(255, 99, 71));
		lbl_pr_msg.setBackground(Color.WHITE);
		lbl_pr_msg.setFont(setfont.fontSetting(14, "bold"));
		lbl_pr_msg.setBounds(187, 537, 392, 52);
		p_playroom.add(lbl_pr_msg);
		
		lbl_pr_yourturn.setForeground(Color.DARK_GRAY);
		lbl_pr_yourturn.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_yourturn.setBounds(308, 479, 159, 48);
		lbl_pr_yourturn.setText("당신의 턴 진행중 입니다");
		lbl_pr_yourturn.setFont(setfont.fontSetting(14, "regular"));
		p_playroom.add(lbl_pr_yourturn);
		
				
		lbl_pr_dice2.setBounds(403, 304, 128, 128);
		p_playroom.add(lbl_pr_dice2);
		
		lbl_pr_dice1 = new JLabel("");
		lbl_pr_dice1.setVisible(false);
		lbl_pr_dice1.setBounds(235, 304, 128, 128);
		p_playroom.add(lbl_pr_dice1);
		lbl_pr_wait.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_wait.setFont(setfont.fontSetting(14, "regular"));
		lbl_pr_wait.setBackground(Color.GRAY);
		lbl_pr_wait.setBounds(308, 479, 159, 48);
		p_playroom.add(lbl_pr_wait);
		
		
		btn_pr_dice.setBounds(308, 479, 159, 48);
		btn_pr_dice.setForeground(new Color(255, 255, 255));
		btn_pr_dice.setBackground(new Color(65, 105, 225));
		btn_pr_dice.setFont(setfont.fontSetting(14, "regular"));
		p_playroom.add(btn_pr_dice);
		
		
		btn_pr_start.setForeground(Color.WHITE);
		btn_pr_start.setFont(setfont.fontSetting(14, "regular"));
		btn_pr_start.setBackground(Color.ORANGE);
		btn_pr_start.setBounds(308, 478, 159, 48);
		p_playroom.add(btn_pr_start);
		
		JPanel p_pr_local1 = new JPanel();
		p_pr_local1.setBorder(new LineBorder(Color.GRAY));
		p_pr_local1.setBackground(new Color(179, 200, 70));
		p_pr_local1.setBounds(530, 611, 60, 90);
		p_playroom.add(p_pr_local1);
		p_pr_local1.setLayout(null);
		
		lbl_pr_local1_build = new JLabel("");
		lbl_pr_local1_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local1_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local1_build.setOpaque(true);
		lbl_pr_local1_build.setBounds(0, 0, 60, 20);
		p_pr_local1.add(lbl_pr_local1_build);
		
		lbl_pr_local1_user1 = new JLabel("");
		lbl_pr_local1_user1.setVisible(false);
		lbl_pr_local1_user1.setBorder(null);
		lbl_pr_local1_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local1_user1.setBounds(7, 25, 20, 20);
		p_pr_local1.add(lbl_pr_local1_user1);
		
		lbl_pr_local1_user2 = new JLabel("");
		lbl_pr_local1_user2.setVisible(false);
		lbl_pr_local1_user2.setBorder(null);
		lbl_pr_local1_user2.setBackground(new Color(0, 0, 255));
		lbl_pr_local1_user2.setBounds(33, 25, 20, 20);
		p_pr_local1.add(lbl_pr_local1_user2);
		
		lbl_pr_local1_user3 = new JLabel("");
		lbl_pr_local1_user3.setVisible(false);
		lbl_pr_local1_user3.setBorder(null);
		lbl_pr_local1_user3.setBackground(Color.ORANGE);
		lbl_pr_local1_user3.setBounds(7, 65, 20, 20);
		p_pr_local1.add(lbl_pr_local1_user3);
		
		lbl_pr_local1_user4 = new JLabel("");
		lbl_pr_local1_user4.setVisible(false);
		lbl_pr_local1_user4.setBorder(null);
		lbl_pr_local1_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local1_user4.setBounds(33, 65, 20, 20);
		p_pr_local1.add(lbl_pr_local1_user4);
		
		JLabel lbl_pr_local1_name = new JLabel("");
		lbl_pr_local1_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local1_name.setForeground(Color.WHITE);
		lbl_pr_local1_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local1_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local1.png")));
		lbl_pr_local1_name.setBounds(1, 20, 58, 69);
		p_pr_local1.add(lbl_pr_local1_name);
		
		JPanel p_pr_local9 = new JPanel();
		p_pr_local9.setBorder(new LineBorder(Color.GRAY));
		p_pr_local9.setBackground(new Color(93, 202, 210));
		p_pr_local9.setBounds(47, 512, 90, 60);
		p_playroom.add(p_pr_local9);
		p_pr_local9.setLayout(null);
		
		lbl_pr_local9_build = new JLabel("");
		lbl_pr_local9_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local9_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local9_build.setOpaque(true);
		lbl_pr_local9_build.setBounds(70, 0, 20, 60);
		p_pr_local9.add(lbl_pr_local9_build);
		
		lbl_pr_local9_user1 = new JLabel("");
		lbl_pr_local9_user1.setBorder(null);
		lbl_pr_local9_user1.setVisible(false);
		lbl_pr_local9_user1.setBackground(Color.RED);
		lbl_pr_local9_user1.setBounds(5, 7, 20, 20);
		p_pr_local9.add(lbl_pr_local9_user1);
		
		lbl_pr_local9_user3 = new JLabel("");
		lbl_pr_local9_user3.setBorder(null);
		lbl_pr_local9_user3.setVisible(false);
		lbl_pr_local9_user3.setBackground(Color.ORANGE);
		lbl_pr_local9_user3.setBounds(5, 33, 20, 20);
		p_pr_local9.add(lbl_pr_local9_user3);
		
		lbl_pr_local9_user2 = new JLabel("");
		lbl_pr_local9_user2.setBorder(null);
		lbl_pr_local9_user2.setVisible(false);
		lbl_pr_local9_user2.setBackground(Color.BLUE);
		lbl_pr_local9_user2.setBounds(44, 7, 20, 20);
		p_pr_local9.add(lbl_pr_local9_user2);
		
		lbl_pr_local9_user4 = new JLabel("");
		lbl_pr_local9_user4.setBorder(null);
		lbl_pr_local9_user4.setVisible(false);
		lbl_pr_local9_user4.setBackground(Color.GREEN);
		lbl_pr_local9_user4.setBounds(44, 33, 20, 20);
		p_pr_local9.add(lbl_pr_local9_user4);
		
		JLabel lbl_pr_local9_name = new JLabel("");
		lbl_pr_local9_name.setBounds(1, 1, 69, 58);
		lbl_pr_local9_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local9.png")));
		p_pr_local9.add(lbl_pr_local9_name);
		
		JPanel p_pr_local8 = new JPanel();
		p_pr_local8.setBorder(new LineBorder(Color.GRAY));
		p_pr_local8.setBounds(47, 571, 130, 130);
		p_playroom.add(p_pr_local8);
		p_pr_local8.setLayout(null);
		
		lbl_pr_local8_user1 = new JLabel("");
		lbl_pr_local8_user1.setBorder(null);
		lbl_pr_local8_user1.setVisible(false);
		lbl_pr_local8_user1.setBackground(Color.RED);
		lbl_pr_local8_user1.setBounds(30, 30, 20, 20);
		p_pr_local8.add(lbl_pr_local8_user1);
		
		lbl_pr_local8_user2 = new JLabel("");
		lbl_pr_local8_user2.setBorder(null);
		lbl_pr_local8_user2.setVisible(false);
		lbl_pr_local8_user2.setBackground(Color.BLUE);
		lbl_pr_local8_user2.setBounds(80, 30, 20, 20);
		p_pr_local8.add(lbl_pr_local8_user2);
		
		lbl_pr_local8_user4 = new JLabel("");
		lbl_pr_local8_user4.setBorder(null);
		lbl_pr_local8_user4.setVisible(false);
		lbl_pr_local8_user4.setBackground(Color.GREEN);
		lbl_pr_local8_user4.setBounds(80, 80, 20, 20);
		p_pr_local8.add(lbl_pr_local8_user4);
		
		lbl_pr_local8_user3 = new JLabel("");
		lbl_pr_local8_user3.setBorder(null);
		lbl_pr_local8_user3.setVisible(false);
		lbl_pr_local8_user3.setBackground(Color.ORANGE);
		lbl_pr_local8_user3.setBounds(30, 80, 20, 20);
		p_pr_local8.add(lbl_pr_local8_user3);
		
		JLabel lbl_pr_local8_name = new JLabel("무인도");
		lbl_pr_local8_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local8_name.setFont(setfont.fontSetting(14, "bold"));
		lbl_pr_local8_name.setBounds(0, 0, 130, 130);
		p_pr_local8.add(lbl_pr_local8_name);
		
		JPanel p_pr_local0 = new JPanel();
		p_pr_local0.setBorder(new LineBorder(Color.GRAY));
		p_pr_local0.setBounds(589, 571, 130, 130);
		p_playroom.add(p_pr_local0);
		p_pr_local0.setLayout(null);
		
		lbl_pr_local0_user4 = new JLabel("");
		lbl_pr_local0_user4.setBorder(null);
		lbl_pr_local0_user4.setVisible(false);
		lbl_pr_local0_user4.setBackground(Color.GREEN);
		lbl_pr_local0_user4.setBounds(80, 80, 20, 20);
		p_pr_local0.add(lbl_pr_local0_user4);
		
		lbl_pr_local0_user3 = new JLabel("");
		lbl_pr_local0_user3.setBorder(null);
		lbl_pr_local0_user3.setVisible(false);
		lbl_pr_local0_user3.setBackground(Color.ORANGE);
		lbl_pr_local0_user3.setBounds(30, 80, 20, 20);
		p_pr_local0.add(lbl_pr_local0_user3);
		
		lbl_pr_local0_user2 = new JLabel("");
		lbl_pr_local0_user2.setBorder(null);
		lbl_pr_local0_user2.setVisible(false);
		lbl_pr_local0_user2.setBackground(Color.BLUE);
		lbl_pr_local0_user2.setBounds(80, 30, 20, 20);
		p_pr_local0.add(lbl_pr_local0_user2);
		
		lbl_pr_local0_user1 = new JLabel("");
		lbl_pr_local0_user1.setBorder(null);
		lbl_pr_local0_user1.setVisible(false);
		lbl_pr_local0_user1.setBackground(Color.RED);
		lbl_pr_local0_user1.setBounds(30, 30, 20, 20);
		p_pr_local0.add(lbl_pr_local0_user1);
		
		JLabel lbl_pr_local0_name = new JLabel("출발");
		lbl_pr_local0_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local0_name.setFont(setfont.fontSetting(14, "bold"));
		lbl_pr_local0_name.setBounds(0, 0, 130, 130);
		p_pr_local0.add(lbl_pr_local0_name);
		
		JPanel p_pr_local16 = new JPanel();
		p_pr_local16.setBorder(new LineBorder(Color.GRAY));
		p_pr_local16.setBounds(47, 29, 130, 130);
		p_playroom.add(p_pr_local16);
		p_pr_local16.setLayout(null);
		
		lbl_pr_local16_user1 = new JLabel("");
		lbl_pr_local16_user1.setBorder(null);
		lbl_pr_local16_user1.setVisible(false);
		lbl_pr_local16_user1.setBackground(Color.RED);
		lbl_pr_local16_user1.setBounds(30, 30, 20, 20);
		p_pr_local16.add(lbl_pr_local16_user1);
		
		lbl_pr_local16_user2 = new JLabel("");
		lbl_pr_local16_user2.setBorder(null);
		lbl_pr_local16_user2.setVisible(false);
		lbl_pr_local16_user2.setBackground(Color.BLUE);
		lbl_pr_local16_user2.setBounds(80, 30, 20, 20);
		p_pr_local16.add(lbl_pr_local16_user2);
		
		lbl_pr_local16_user4 = new JLabel("");
		lbl_pr_local16_user4.setBorder(null);
		lbl_pr_local16_user4.setVisible(false);
		lbl_pr_local16_user4.setBackground(Color.GREEN);
		lbl_pr_local16_user4.setBounds(80, 80, 20, 20);
		p_pr_local16.add(lbl_pr_local16_user4);
		
		lbl_pr_local16_user3 = new JLabel("");
		lbl_pr_local16_user3.setBorder(null);
		lbl_pr_local16_user3.setVisible(false);
		lbl_pr_local16_user3.setBackground(Color.ORANGE);
		lbl_pr_local16_user3.setBounds(30, 80, 20, 20);
		p_pr_local16.add(lbl_pr_local16_user3);
		
		JLabel lbl_pr_local16_name = new JLabel("기부함");
		lbl_pr_local16_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local16_name.setFont(setfont.fontSetting(14, "bold"));
		lbl_pr_local16_name.setBounds(0, 0, 130, 130);
		p_pr_local16.add(lbl_pr_local16_name);
		
		lbl_pr_donate = new JLabel("");
		lbl_pr_donate.setVisible(false);
		lbl_pr_donate.setForeground(new Color(255, 99, 71));
		lbl_pr_donate.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_donate.setFont(setfont.fontSetting(12, "bold"));
		lbl_pr_donate.setBounds(10, 80, 110, 28);
		p_pr_local16.add(lbl_pr_donate);
		
		JPanel p_pr_local24 = new JPanel();
		p_pr_local24.setBorder(new LineBorder(Color.GRAY));
		p_pr_local24.setBounds(589, 29, 130, 130);
		p_playroom.add(p_pr_local24);
		p_pr_local24.setLayout(null);
		
		lbl_pr_local24_user1 = new JLabel("");
		lbl_pr_local24_user1.setBorder(null);
		lbl_pr_local24_user1.setVisible(false);
		lbl_pr_local24_user1.setBackground(Color.RED);
		lbl_pr_local24_user1.setBounds(30, 30, 20, 20);
		p_pr_local24.add(lbl_pr_local24_user1);
		
		lbl_pr_local24_user2 = new JLabel("");
		lbl_pr_local24_user2.setBorder(null);
		lbl_pr_local24_user2.setVisible(false);
		lbl_pr_local24_user2.setBackground(Color.BLUE);
		lbl_pr_local24_user2.setBounds(80, 30, 20, 20);
		p_pr_local24.add(lbl_pr_local24_user2);
		
		lbl_pr_local24_user4 = new JLabel("");
		lbl_pr_local24_user4.setBorder(null);
		lbl_pr_local24_user4.setVisible(false);
		lbl_pr_local24_user4.setBackground(Color.GREEN);
		lbl_pr_local24_user4.setBounds(80, 80, 20, 20);
		p_pr_local24.add(lbl_pr_local24_user4);
		
		lbl_pr_local24_user3 = new JLabel("");
		lbl_pr_local24_user3.setBorder(null);
		lbl_pr_local24_user3.setVisible(false);
		lbl_pr_local24_user3.setBackground(Color.ORANGE);
		lbl_pr_local24_user3.setBounds(30, 80, 20, 20);
		p_pr_local24.add(lbl_pr_local24_user3);
		
		JLabel lbl_pr_local24_name = new JLabel("준비중");
		lbl_pr_local24_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local24_name.setFont(setfont.fontSetting(14, "bold"));
		lbl_pr_local24_name.setBounds(0, 0, 130, 130);
		p_pr_local24.add(lbl_pr_local24_name);
		
		JPanel p_pr_local2 = new JPanel();
		p_pr_local2.setLayout(null);
		p_pr_local2.setBorder(new LineBorder(Color.GRAY));
		p_pr_local2.setBackground(new Color(245, 245, 245));
		p_pr_local2.setBounds(471, 611, 60, 90);
		p_playroom.add(p_pr_local2);
		
		lbl_pr_local2_user1 = new JLabel("");
		lbl_pr_local2_user1.setVisible(false);
		lbl_pr_local2_user1.setBorder(null);
		lbl_pr_local2_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local2_user1.setBounds(7, 25, 20, 20);
		p_pr_local2.add(lbl_pr_local2_user1);
		
		lbl_pr_local2_user2 = new JLabel("");
		lbl_pr_local2_user2.setVisible(false);
		lbl_pr_local2_user2.setBorder(null);
		lbl_pr_local2_user2.setBackground(Color.BLUE);
		lbl_pr_local2_user2.setBounds(33, 25, 20, 20);
		p_pr_local2.add(lbl_pr_local2_user2);
		
		lbl_pr_local2_user3 = new JLabel("");
		lbl_pr_local2_user3.setVisible(false);
		lbl_pr_local2_user3.setBorder(null);
		lbl_pr_local2_user3.setBackground(Color.ORANGE);
		lbl_pr_local2_user3.setBounds(7, 65, 20, 20);
		p_pr_local2.add(lbl_pr_local2_user3);
		
		lbl_pr_local2_user4 = new JLabel("");
		lbl_pr_local2_user4.setVisible(false);
		lbl_pr_local2_user4.setBorder(null);
		lbl_pr_local2_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local2_user4.setBounds(33, 65, 20, 20);
		p_pr_local2.add(lbl_pr_local2_user4);
		
		lbl_pr_local2_build = new JLabel("");
		lbl_pr_local2_build.setOpaque(true);
		lbl_pr_local2_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local2_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local2_build.setBounds(0, 0, 60, 20);
		p_pr_local2.add(lbl_pr_local2_build);
		
		JLabel lbl_pr_local2_name = new JLabel("");
		lbl_pr_local2_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local2_name.setForeground(Color.WHITE);
		lbl_pr_local2_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local2_name.setBounds(1, 20, 58, 69);
		lbl_pr_local2_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local2.png")));
		p_pr_local2.add(lbl_pr_local2_name);
		
		JPanel p_pr_local3 = new JPanel();
		p_pr_local3.setLayout(null);
		p_pr_local3.setBorder(new LineBorder(Color.GRAY));
		p_pr_local3.setBackground(new Color(179, 200, 70));
		p_pr_local3.setBounds(412, 611, 60, 90);
		p_playroom.add(p_pr_local3);
		
		lbl_pr_local3_build = new JLabel("");
		lbl_pr_local3_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local3_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local3_build.setOpaque(true);
		lbl_pr_local3_build.setBounds(0, 0, 60, 20);
		p_pr_local3.add(lbl_pr_local3_build);
		
		lbl_pr_local3_user1 = new JLabel("");
		lbl_pr_local3_user1.setVisible(false);
		lbl_pr_local3_user1.setBorder(null);
		lbl_pr_local3_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local3_user1.setBounds(7, 25, 20, 20);
		p_pr_local3.add(lbl_pr_local3_user1);
		
		lbl_pr_local3_user2 = new JLabel("");
		lbl_pr_local3_user2.setVisible(false);
		lbl_pr_local3_user2.setBorder(null);
		lbl_pr_local3_user2.setBackground(Color.BLUE);
		lbl_pr_local3_user2.setBounds(33, 25, 20, 20);
		p_pr_local3.add(lbl_pr_local3_user2);
		
		lbl_pr_local3_user3 = new JLabel("");
		lbl_pr_local3_user3.setVisible(false);
		lbl_pr_local3_user3.setBorder(null);
		lbl_pr_local3_user3.setBackground(Color.ORANGE);
		lbl_pr_local3_user3.setBounds(7, 65, 20, 20);
		p_pr_local3.add(lbl_pr_local3_user3);
		
		lbl_pr_local3_user4 = new JLabel("");
		lbl_pr_local3_user4.setVisible(false);
		lbl_pr_local3_user4.setBorder(null);
		lbl_pr_local3_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local3_user4.setBounds(33, 65, 20, 20);
		p_pr_local3.add(lbl_pr_local3_user4);
		
		JLabel lbl_pr_local3_name = new JLabel("");
		lbl_pr_local3_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local3_name.setForeground(Color.WHITE);
		lbl_pr_local3_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local3_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local3.png")));
		lbl_pr_local3_name.setBounds(1, 20, 58, 69);
		p_pr_local3.add(lbl_pr_local3_name);
		
		JPanel p_pr_local4 = new JPanel();
		p_pr_local4.setLayout(null);
		p_pr_local4.setBorder(new LineBorder(Color.GRAY));
		p_pr_local4.setBackground(new Color(231, 231, 231));
		p_pr_local4.setBounds(353, 611, 60, 90);
		p_playroom.add(p_pr_local4);
		
		lbl_pr_local4_user1 = new JLabel("");
		lbl_pr_local4_user1.setVisible(false);
		lbl_pr_local4_user1.setBorder(null);
		lbl_pr_local4_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local4_user1.setBounds(7, 25, 20, 20);
		p_pr_local4.add(lbl_pr_local4_user1);
		
		lbl_pr_local4_user2 = new JLabel("");
		lbl_pr_local4_user2.setVisible(false);
		lbl_pr_local4_user2.setBorder(null);
		lbl_pr_local4_user2.setBackground(Color.BLUE);
		lbl_pr_local4_user2.setBounds(33, 25, 20, 20);
		p_pr_local4.add(lbl_pr_local4_user2);
		
		lbl_pr_local4_user3 = new JLabel("");
		lbl_pr_local4_user3.setVisible(false);
		lbl_pr_local4_user3.setBorder(null);
		lbl_pr_local4_user3.setBackground(Color.ORANGE);
		lbl_pr_local4_user3.setBounds(7, 65, 20, 20);
		p_pr_local4.add(lbl_pr_local4_user3);
		
		lbl_pr_local4_user4 = new JLabel("");
		lbl_pr_local4_user4.setVisible(false);
		lbl_pr_local4_user4.setBorder(null);
		lbl_pr_local4_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local4_user4.setBounds(33, 65, 20, 20);
		p_pr_local4.add(lbl_pr_local4_user4);
		
		JLabel lbl_pr_local4_name = new JLabel("");
		lbl_pr_local4_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local4_name.setForeground(Color.DARK_GRAY);
		lbl_pr_local4_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local4_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_card.png")));
		lbl_pr_local4_name.setBounds(1, 20, 58, 69);
		p_pr_local4.add(lbl_pr_local4_name);
		
		JPanel p_pr_local5 = new JPanel();
		p_pr_local5.setLayout(null);
		p_pr_local5.setBorder(new LineBorder(Color.GRAY));
		p_pr_local5.setBackground(new Color(97, 177, 66));
		
		p_pr_local5.setBounds(294, 611, 60, 90);
		p_playroom.add(p_pr_local5);
		
		lbl_pr_local5_build = new JLabel("");
		lbl_pr_local5_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local5_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local5_build.setOpaque(true);
		lbl_pr_local5_build.setBounds(0, 0, 60, 20);
		p_pr_local5.add(lbl_pr_local5_build);
		
		lbl_pr_local5_user1 = new JLabel("");
		lbl_pr_local5_user1.setVisible(false);
		lbl_pr_local5_user1.setBorder(null);
		lbl_pr_local5_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local5_user1.setBounds(7, 25, 20, 20);
		p_pr_local5.add(lbl_pr_local5_user1);
		
		lbl_pr_local5_user2 = new JLabel("");
		lbl_pr_local5_user2.setVisible(false);
		lbl_pr_local5_user2.setBorder(null);
		lbl_pr_local5_user2.setBackground(Color.BLUE);
		lbl_pr_local5_user2.setBounds(33, 25, 20, 20);
		p_pr_local5.add(lbl_pr_local5_user2);
		
		lbl_pr_local5_user3 = new JLabel("");
		lbl_pr_local5_user3.setVisible(false);
		lbl_pr_local5_user3.setBorder(null);
		lbl_pr_local5_user3.setBackground(Color.ORANGE);
		lbl_pr_local5_user3.setBounds(7, 65, 20, 20);
		p_pr_local5.add(lbl_pr_local5_user3);
		
		lbl_pr_local5_user4 = new JLabel("");
		lbl_pr_local5_user4.setVisible(false);
		lbl_pr_local5_user4.setBorder(null);
		lbl_pr_local5_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local5_user4.setBounds(33, 65, 20, 20);
		p_pr_local5.add(lbl_pr_local5_user4);
		
		JLabel lbl_pr_local5_name = new JLabel("");
		lbl_pr_local5_name.setBounds(1, 20, 58, 69);
		lbl_pr_local5_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local5.png")));
		p_pr_local5.add(lbl_pr_local5_name);
		
		JPanel p_pr_local6 = new JPanel();
		p_pr_local6.setLayout(null);
		p_pr_local6.setBorder(new LineBorder(Color.GRAY));
		p_pr_local6.setBackground(new Color(97, 177, 66));
		p_pr_local6.setBounds(235, 611, 60, 90);
		p_playroom.add(p_pr_local6);
		
		lbl_pr_local6_build = new JLabel("");
		lbl_pr_local6_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local6_build.setOpaque(true);
		lbl_pr_local6_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local6_build.setBounds(0, 0, 60, 20);
		p_pr_local6.add(lbl_pr_local6_build);
		
		lbl_pr_local6_user1 = new JLabel("");
		lbl_pr_local6_user1.setVisible(false);
		lbl_pr_local6_user1.setBorder(null);
		lbl_pr_local6_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local6_user1.setBounds(7, 25, 20, 20);
		p_pr_local6.add(lbl_pr_local6_user1);
		
		lbl_pr_local6_user2 = new JLabel("");
		lbl_pr_local6_user2.setVisible(false);
		lbl_pr_local6_user2.setBorder(null);
		lbl_pr_local6_user2.setBackground(Color.BLUE);
		lbl_pr_local6_user2.setBounds(33, 25, 20, 20);
		p_pr_local6.add(lbl_pr_local6_user2);
		
		lbl_pr_local6_user3 = new JLabel("");
		lbl_pr_local6_user3.setVisible(false);
		lbl_pr_local6_user3.setBorder(null);
		lbl_pr_local6_user3.setBackground(Color.ORANGE);
		lbl_pr_local6_user3.setBounds(7, 65, 20, 20);
		p_pr_local6.add(lbl_pr_local6_user3);
		
		lbl_pr_local6_user4 = new JLabel("");
		lbl_pr_local6_user4.setVisible(false);
		lbl_pr_local6_user4.setBorder(null);
		lbl_pr_local6_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local6_user4.setBounds(33, 65, 20, 20);
		p_pr_local6.add(lbl_pr_local6_user4);
		
		JLabel lbl_pr_local6_name = new JLabel("");
		lbl_pr_local6_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local6_name.setForeground(Color.WHITE);
		lbl_pr_local6_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local6_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local6.png")));
		lbl_pr_local6_name.setBounds(1, 20, 58, 69);
		p_pr_local6.add(lbl_pr_local6_name);
		
		JPanel p_pr_local7 = new JPanel();
		p_pr_local7.setLayout(null);
		p_pr_local7.setBorder(new LineBorder(Color.GRAY));
		p_pr_local7.setBackground(new Color(97, 177, 66));
		p_pr_local7.setBounds(176, 611, 60, 90);
		p_playroom.add(p_pr_local7);
		
		lbl_pr_local7_build = new JLabel("");
		lbl_pr_local7_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local7_build.setOpaque(true);
		lbl_pr_local7_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local7_build.setBounds(0, 0, 60, 20);
		p_pr_local7.add(lbl_pr_local7_build);
		
		lbl_pr_local7_user1 = new JLabel("");
		lbl_pr_local7_user1.setVisible(false);
		lbl_pr_local7_user1.setBorder(null);
		lbl_pr_local7_user1.setBackground(new Color(220, 20, 60));
		lbl_pr_local7_user1.setBounds(7, 25, 20, 20);
		p_pr_local7.add(lbl_pr_local7_user1);
		
		lbl_pr_local7_user2 = new JLabel("");
		lbl_pr_local7_user2.setVisible(false);
		lbl_pr_local7_user2.setBorder(null);
		lbl_pr_local7_user2.setBackground(Color.BLUE);
		lbl_pr_local7_user2.setBounds(33, 25, 20, 20);
		p_pr_local7.add(lbl_pr_local7_user2);
		
		lbl_pr_local7_user3 = new JLabel("");
		lbl_pr_local7_user3.setVisible(false);
		lbl_pr_local7_user3.setBorder(null);
		lbl_pr_local7_user3.setBackground(Color.ORANGE);
		lbl_pr_local7_user3.setBounds(7, 65, 20, 20);
		p_pr_local7.add(lbl_pr_local7_user3);
		
		lbl_pr_local7_user4 = new JLabel("");
		lbl_pr_local7_user4.setVisible(false);
		lbl_pr_local7_user4.setBorder(null);
		lbl_pr_local7_user4.setBackground(new Color(0, 255, 127));
		lbl_pr_local7_user4.setBounds(33, 65, 20, 20);
		p_pr_local7.add(lbl_pr_local7_user4);
		
		JLabel lbl_pr_local7_name = new JLabel("");
		lbl_pr_local7_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local7_name.setForeground(Color.WHITE);
		lbl_pr_local7_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local7_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local7.png")));
		lbl_pr_local7_name.setBounds(1, 20, 58, 69);
		p_pr_local7.add(lbl_pr_local7_name);
		
		JPanel p_pr_local10 = new JPanel();
		p_pr_local10.setLayout(null);
		p_pr_local10.setBorder(new LineBorder(Color.GRAY));
		p_pr_local10.setBackground(new Color(93, 202, 210));
		p_pr_local10.setBounds(47, 453, 90, 60);
		p_playroom.add(p_pr_local10);
		
		lbl_pr_local10_build = new JLabel("");
		lbl_pr_local10_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local10_build.setOpaque(true);
		lbl_pr_local10_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local10_build.setBounds(70, 0, 20, 60);
		p_pr_local10.add(lbl_pr_local10_build);
		
		lbl_pr_local10_user1 = new JLabel("");
		lbl_pr_local10_user1.setBorder(null);
		lbl_pr_local10_user1.setVisible(false);
		lbl_pr_local10_user1.setBackground(Color.RED);
		lbl_pr_local10_user1.setBounds(5, 7, 20, 20);
		p_pr_local10.add(lbl_pr_local10_user1);
		
		lbl_pr_local10_user3 = new JLabel("");
		lbl_pr_local10_user3.setBorder(null);
		lbl_pr_local10_user3.setVisible(false);
		lbl_pr_local10_user3.setBackground(Color.ORANGE);
		lbl_pr_local10_user3.setBounds(5, 33, 20, 20);
		p_pr_local10.add(lbl_pr_local10_user3);
		
		lbl_pr_local10_user2 = new JLabel("");
		lbl_pr_local10_user2.setBorder(null);
		lbl_pr_local10_user2.setVisible(false);
		lbl_pr_local10_user2.setBackground(Color.BLUE);
		lbl_pr_local10_user2.setBounds(44, 7, 20, 20);
		p_pr_local10.add(lbl_pr_local10_user2);
		
		lbl_pr_local10_user4 = new JLabel("");
		lbl_pr_local10_user4.setBorder(null);
		lbl_pr_local10_user4.setVisible(false);
		lbl_pr_local10_user4.setBackground(Color.GREEN);
		lbl_pr_local10_user4.setBounds(44, 33, 20, 20);
		p_pr_local10.add(lbl_pr_local10_user4);
		
		JLabel lbl_pr_local10_name = new JLabel("");
		lbl_pr_local10_name.setBounds(1, 1, 69, 58);
		lbl_pr_local10_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local10.png")));
		p_pr_local10.add(lbl_pr_local10_name);
		
		JPanel p_pr_local11 = new JPanel();
		p_pr_local11.setLayout(null);
		p_pr_local11.setBorder(new LineBorder(Color.GRAY));
		p_pr_local11.setBackground(new Color(93, 202, 210));
		p_pr_local11.setBounds(47, 394, 90, 60);
		p_playroom.add(p_pr_local11);
		
		lbl_pr_local11_build = new JLabel("");
		lbl_pr_local11_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local11_build.setOpaque(true);
		lbl_pr_local11_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local11_build.setBounds(70, 0, 20, 60);
		p_pr_local11.add(lbl_pr_local11_build);
		
		lbl_pr_local11_user1 = new JLabel("");
		lbl_pr_local11_user1.setBorder(null);
		lbl_pr_local11_user1.setVisible(false);
		lbl_pr_local11_user1.setBackground(Color.RED);
		lbl_pr_local11_user1.setBounds(5, 7, 20, 20);
		p_pr_local11.add(lbl_pr_local11_user1);
		
		lbl_pr_local11_user3 = new JLabel("");
		lbl_pr_local11_user3.setBorder(null);
		lbl_pr_local11_user3.setVisible(false);
		lbl_pr_local11_user3.setBackground(Color.ORANGE);
		lbl_pr_local11_user3.setBounds(5, 33, 20, 20);
		p_pr_local11.add(lbl_pr_local11_user3);
		
		lbl_pr_local11_user2 = new JLabel("");
		lbl_pr_local11_user2.setBorder(null);
		lbl_pr_local11_user2.setVisible(false);
		lbl_pr_local11_user2.setBackground(Color.BLUE);
		lbl_pr_local11_user2.setBounds(44, 7, 20, 20);
		p_pr_local11.add(lbl_pr_local11_user2);
		
		lbl_pr_local11_user4 = new JLabel("");
		lbl_pr_local11_user4.setBorder(null);
		lbl_pr_local11_user4.setVisible(false);
		lbl_pr_local11_user4.setBackground(Color.GREEN);
		lbl_pr_local11_user4.setBounds(44, 33, 20, 20);
		p_pr_local11.add(lbl_pr_local11_user4);
		
		JLabel lbl_pr_local11_name = new JLabel("");
		lbl_pr_local11_name.setBounds(1, 1, 69, 58);
		lbl_pr_local11_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local11.png")));
		p_pr_local11.add(lbl_pr_local11_name);
		
		JPanel p_pr_local13 = new JPanel();
		p_pr_local13.setLayout(null);
		p_pr_local13.setBorder(new LineBorder(Color.GRAY));
		p_pr_local13.setBackground(new Color(117, 157, 225));
		p_pr_local13.setBounds(47, 276, 90, 60);
		p_playroom.add(p_pr_local13);
		
		lbl_pr_local13_build = new JLabel("");
		lbl_pr_local13_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local13_build.setOpaque(true);
		lbl_pr_local13_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local13_build.setBounds(70, 0, 20, 60);
		p_pr_local13.add(lbl_pr_local13_build);
		
		lbl_pr_local13_user1 = new JLabel("");
		lbl_pr_local13_user1.setBorder(null);
		lbl_pr_local13_user1.setVisible(false);
		lbl_pr_local13_user1.setBackground(Color.RED);
		lbl_pr_local13_user1.setBounds(5, 7, 20, 20);
		p_pr_local13.add(lbl_pr_local13_user1);
		
		lbl_pr_local13_user3 = new JLabel("");
		lbl_pr_local13_user3.setBorder(null);
		lbl_pr_local13_user3.setVisible(false);
		lbl_pr_local13_user3.setBackground(Color.ORANGE);
		lbl_pr_local13_user3.setBounds(5, 33, 20, 20);
		p_pr_local13.add(lbl_pr_local13_user3);
		
		lbl_pr_local13_user2 = new JLabel("");
		lbl_pr_local13_user2.setBorder(null);
		lbl_pr_local13_user2.setVisible(false);
		lbl_pr_local13_user2.setBackground(Color.BLUE);
		lbl_pr_local13_user2.setBounds(44, 7, 20, 20);
		p_pr_local13.add(lbl_pr_local13_user2);
		
		lbl_pr_local13_user4 = new JLabel("");
		lbl_pr_local13_user4.setBorder(null);
		lbl_pr_local13_user4.setVisible(false);
		lbl_pr_local13_user4.setBackground(Color.GREEN);
		lbl_pr_local13_user4.setBounds(44, 33, 20, 20);
		p_pr_local13.add(lbl_pr_local13_user4);
		
		JLabel lbl_pr_local13_name = new JLabel("");
		lbl_pr_local13_name.setBounds(1, 1, 69, 58);
		lbl_pr_local13_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local13.png")));
		p_pr_local13.add(lbl_pr_local13_name);
		
		JPanel p_pr_local14 = new JPanel();
		p_pr_local14.setBackground(new Color(117, 157, 225));
		p_pr_local14.setBorder(new LineBorder(Color.GRAY));
		p_pr_local14.setBounds(47, 217, 90, 60);
		p_playroom.add(p_pr_local14);
		p_pr_local14.setLayout(null);
		
		lbl_pr_local14_build = new JLabel("");
		lbl_pr_local14_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local14_build.setOpaque(true);
		lbl_pr_local14_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local14_build.setBounds(70, 0, 20, 60);
		p_pr_local14.add(lbl_pr_local14_build);
		
		lbl_pr_local14_user1 = new JLabel("");
		lbl_pr_local14_user1.setBorder(null);
		lbl_pr_local14_user1.setVisible(false);
		lbl_pr_local14_user1.setBackground(Color.RED);
		lbl_pr_local14_user1.setBounds(5, 7, 20, 20);
		p_pr_local14.add(lbl_pr_local14_user1);
		
		lbl_pr_local14_user2 = new JLabel("");
		lbl_pr_local14_user2.setBorder(null);
		lbl_pr_local14_user2.setVisible(false);
		lbl_pr_local14_user2.setBackground(Color.BLUE);
		lbl_pr_local14_user2.setBounds(44, 7, 20, 20);
		p_pr_local14.add(lbl_pr_local14_user2);
		
		lbl_pr_local14_user3 = new JLabel("");
		lbl_pr_local14_user3.setBorder(null);
		lbl_pr_local14_user3.setVisible(false);
		lbl_pr_local14_user3.setBackground(Color.ORANGE);
		lbl_pr_local14_user3.setBounds(5, 33, 20, 20);
		p_pr_local14.add(lbl_pr_local14_user3);
		
		lbl_pr_local14_user4 = new JLabel("");
		lbl_pr_local14_user4.setBorder(null);
		lbl_pr_local14_user4.setVisible(false);
		lbl_pr_local14_user4.setBackground(Color.GREEN);
		lbl_pr_local14_user4.setBounds(44, 33, 20, 20);
		p_pr_local14.add(lbl_pr_local14_user4);
		
		JLabel lbl_pr_local14_name = new JLabel("");
		lbl_pr_local14_name.setBounds(1, 1, 69, 58);
		lbl_pr_local14_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local14.png")));
		p_pr_local14.add(lbl_pr_local14_name);
		
		JPanel p_pr_local15 = new JPanel();
		p_pr_local15.setLayout(null);
		p_pr_local15.setBorder(new LineBorder(Color.GRAY));
		p_pr_local15.setBackground(new Color(117, 157, 225));
		p_pr_local15.setBounds(47, 158, 90, 60);
		p_playroom.add(p_pr_local15);
		
		lbl_pr_local15_build = new JLabel("");
		lbl_pr_local15_build.setBackground(new Color(245, 245, 245));
		lbl_pr_local15_build.setOpaque(true);
		lbl_pr_local15_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local15_build.setBounds(70, 0, 20, 60);
		p_pr_local15.add(lbl_pr_local15_build);
		
		lbl_pr_local15_user1 = new JLabel("");
		lbl_pr_local15_user1.setBorder(null);
		lbl_pr_local15_user1.setVisible(false);
		lbl_pr_local15_user1.setBackground(Color.RED);
		lbl_pr_local15_user1.setBounds(5, 7, 20, 20);
		p_pr_local15.add(lbl_pr_local15_user1);
		
		lbl_pr_local15_user2 = new JLabel("");
		lbl_pr_local15_user2.setBorder(null);
		lbl_pr_local15_user2.setVisible(false);
		lbl_pr_local15_user2.setBackground(Color.BLUE);
		lbl_pr_local15_user2.setBounds(44, 7, 20, 20);
		p_pr_local15.add(lbl_pr_local15_user2);
		
		lbl_pr_local15_user3 = new JLabel("");
		lbl_pr_local15_user3.setBorder(null);
		lbl_pr_local15_user3.setVisible(false);
		lbl_pr_local15_user3.setBackground(Color.ORANGE);
		lbl_pr_local15_user3.setBounds(5, 33, 20, 20);
		p_pr_local15.add(lbl_pr_local15_user3);
		
		lbl_pr_local15_user4 = new JLabel("");
		lbl_pr_local15_user4.setBorder(null);
		lbl_pr_local15_user4.setVisible(false);
		lbl_pr_local15_user4.setBackground(Color.GREEN);
		lbl_pr_local15_user4.setBounds(44, 33, 20, 20);
		p_pr_local15.add(lbl_pr_local15_user4);
		
		JLabel lbl_pr_local15_name = new JLabel("");
		lbl_pr_local15_name.setBounds(1, 1, 69, 58);
		lbl_pr_local15_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local15.png")));
		p_pr_local15.add(lbl_pr_local15_name);
		
		JPanel p_pr_local25 = new JPanel();
		p_pr_local25.setLayout(null);
		p_pr_local25.setBorder(new LineBorder(Color.GRAY));
		p_pr_local25.setBackground(new Color(255, 172, 137));
		p_pr_local25.setBounds(629, 158, 90, 60);
		p_playroom.add(p_pr_local25);
		
		lbl_pr_local25_build = new JLabel("");
		lbl_pr_local25_build.setOpaque(true);
		lbl_pr_local25_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local25_build.setBounds(0, 0, 20, 60);
		p_pr_local25.add(lbl_pr_local25_build);
		
		lbl_pr_local25_user1 = new JLabel("");
		lbl_pr_local25_user1.setBorder(null);
		lbl_pr_local25_user1.setVisible(false);
		lbl_pr_local25_user1.setBackground(Color.RED);
		lbl_pr_local25_user1.setBounds(25, 7, 20, 20);
		p_pr_local25.add(lbl_pr_local25_user1);
		
		lbl_pr_local25_user2 = new JLabel("");
		lbl_pr_local25_user2.setBorder(null);
		lbl_pr_local25_user2.setVisible(false);
		lbl_pr_local25_user2.setBackground(Color.BLUE);
		lbl_pr_local25_user2.setBounds(64, 7, 20, 20);
		p_pr_local25.add(lbl_pr_local25_user2);
		
		lbl_pr_local25_user3 = new JLabel("");
		lbl_pr_local25_user3.setBorder(null);
		lbl_pr_local25_user3.setVisible(false);
		lbl_pr_local25_user3.setBackground(Color.ORANGE);
		lbl_pr_local25_user3.setBounds(25, 33, 20, 20);
		p_pr_local25.add(lbl_pr_local25_user3);
		
		lbl_pr_local25_user4 = new JLabel("");
		lbl_pr_local25_user4.setBorder(null);
		lbl_pr_local25_user4.setVisible(false);
		lbl_pr_local25_user4.setBackground(Color.GREEN);
		lbl_pr_local25_user4.setBounds(64, 33, 20, 20);
		p_pr_local25.add(lbl_pr_local25_user4);
		
		JLabel lbl_pr_local25_name = new JLabel("");
		lbl_pr_local25_name.setBounds(20, 1, 69, 58);
		lbl_pr_local25_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local25.png")));
		p_pr_local25.add(lbl_pr_local25_name);
		
		JPanel p_pr_local31 = new JPanel();
		p_pr_local31.setLayout(null);
		p_pr_local31.setBorder(new LineBorder(Color.GRAY));
		p_pr_local31.setBackground(new Color(253, 131, 120));
		p_pr_local31.setBounds(629, 512, 90, 60);
		p_playroom.add(p_pr_local31);
		
		lbl_pr_local31_build = new JLabel("");
		lbl_pr_local31_build.setOpaque(true);
		lbl_pr_local31_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local31_build.setBounds(0, 0, 20, 60);
		p_pr_local31.add(lbl_pr_local31_build);
		
		lbl_pr_local31_user1 = new JLabel("");
		lbl_pr_local31_user1.setBorder(null);
		lbl_pr_local31_user1.setVisible(false);
		lbl_pr_local31_user1.setBackground(Color.RED);
		lbl_pr_local31_user1.setBounds(25, 7, 20, 20);
		p_pr_local31.add(lbl_pr_local31_user1);
		
		lbl_pr_local31_user2 = new JLabel("");
		lbl_pr_local31_user2.setBorder(null);
		lbl_pr_local31_user2.setVisible(false);
		lbl_pr_local31_user2.setBackground(Color.BLUE);
		lbl_pr_local31_user2.setBounds(64, 7, 20, 20);
		p_pr_local31.add(lbl_pr_local31_user2);
		
		lbl_pr_local31_user3 = new JLabel("");
		lbl_pr_local31_user3.setBorder(null);
		lbl_pr_local31_user3.setVisible(false);
		lbl_pr_local31_user3.setBackground(Color.ORANGE);
		lbl_pr_local31_user3.setBounds(25, 33, 20, 20);
		p_pr_local31.add(lbl_pr_local31_user3);
		
		lbl_pr_local31_user4 = new JLabel("");
		lbl_pr_local31_user4.setBorder(null);
		lbl_pr_local31_user4.setVisible(false);
		lbl_pr_local31_user4.setBackground(Color.GREEN);
		lbl_pr_local31_user4.setBounds(64, 33, 20, 20);
		p_pr_local31.add(lbl_pr_local31_user4);
		
		JLabel lbl_pr_local31_name = new JLabel("");
		lbl_pr_local31_name.setBounds(20, 1, 69, 58);
		lbl_pr_local31_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local31.png")));
		p_pr_local31.add(lbl_pr_local31_name);
		
		JPanel p_pr_local30 = new JPanel();
		p_pr_local30.setLayout(null);
		p_pr_local30.setBorder(new LineBorder(Color.GRAY));
		p_pr_local30.setBackground(new Color(245, 245, 245));
		p_pr_local30.setBounds(629, 453, 90, 60);
		p_playroom.add(p_pr_local30);
		
		lbl_pr_local30_user1 = new JLabel("");
		lbl_pr_local30_user1.setBorder(null);
		lbl_pr_local30_user1.setVisible(false);
		lbl_pr_local30_user1.setBackground(Color.RED);
		lbl_pr_local30_user1.setBounds(25, 7, 20, 20);
		p_pr_local30.add(lbl_pr_local30_user1);
		
		lbl_pr_local30_user2 = new JLabel("");
		lbl_pr_local30_user2.setBorder(null);
		lbl_pr_local30_user2.setVisible(false);
		lbl_pr_local30_user2.setBackground(Color.BLUE);
		lbl_pr_local30_user2.setBounds(64, 7, 20, 20);
		p_pr_local30.add(lbl_pr_local30_user2);
		
		lbl_pr_local30_user3 = new JLabel("");
		lbl_pr_local30_user3.setBorder(null);
		lbl_pr_local30_user3.setVisible(false);
		lbl_pr_local30_user3.setBackground(Color.ORANGE);
		lbl_pr_local30_user3.setBounds(25, 33, 20, 20);
		p_pr_local30.add(lbl_pr_local30_user3);
		
		lbl_pr_local30_user4 = new JLabel("");
		lbl_pr_local30_user4.setBorder(null);
		lbl_pr_local30_user4.setVisible(false);
		lbl_pr_local30_user4.setBackground(Color.GREEN);
		lbl_pr_local30_user4.setBounds(64, 33, 20, 20);
		p_pr_local30.add(lbl_pr_local30_user4);
		
		JLabel lbl_pr_local30_name = new JLabel("");
		lbl_pr_local30_name.setBounds(20, 1, 69, 58);
		lbl_pr_local30_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local30.png")));
		p_pr_local30.add(lbl_pr_local30_name);
		
		lbl_pr_local30_build = new JLabel("");
		lbl_pr_local30_build.setOpaque(true);
		lbl_pr_local30_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local30_build.setBounds(0, 0, 20, 60);
		p_pr_local30.add(lbl_pr_local30_build);
		
		JPanel p_pr_local29 = new JPanel();
		p_pr_local29.setLayout(null);
		p_pr_local29.setBorder(new LineBorder(Color.GRAY));
		p_pr_local29.setBackground(new Color(253, 131, 120));
		p_pr_local29.setBounds(629, 394, 90, 60);
		p_playroom.add(p_pr_local29);
		
		lbl_pr_local29_build = new JLabel("");
		lbl_pr_local29_build.setOpaque(true);
		lbl_pr_local29_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local29_build.setBounds(0, 0, 20, 60);
		p_pr_local29.add(lbl_pr_local29_build);
		
		lbl_pr_local29_user1 = new JLabel("");
		lbl_pr_local29_user1.setBorder(null);
		lbl_pr_local29_user1.setVisible(false);
		lbl_pr_local29_user1.setBackground(Color.RED);
		lbl_pr_local29_user1.setBounds(25, 7, 20, 20);
		p_pr_local29.add(lbl_pr_local29_user1);
		
		lbl_pr_local29_user2 = new JLabel("");
		lbl_pr_local29_user2.setBorder(null);
		lbl_pr_local29_user2.setVisible(false);
		lbl_pr_local29_user2.setBackground(Color.BLUE);
		lbl_pr_local29_user2.setBounds(64, 7, 20, 20);
		p_pr_local29.add(lbl_pr_local29_user2);
		
		lbl_pr_local29_user3 = new JLabel("");
		lbl_pr_local29_user3.setBorder(null);
		lbl_pr_local29_user3.setVisible(false);
		lbl_pr_local29_user3.setBackground(Color.ORANGE);
		lbl_pr_local29_user3.setBounds(25, 33, 20, 20);
		p_pr_local29.add(lbl_pr_local29_user3);
		
		lbl_pr_local29_user4 = new JLabel("");
		lbl_pr_local29_user4.setBorder(null);
		lbl_pr_local29_user4.setVisible(false);
		lbl_pr_local29_user4.setBackground(Color.GREEN);
		lbl_pr_local29_user4.setBounds(64, 33, 20, 20);
		p_pr_local29.add(lbl_pr_local29_user4);
		
		JLabel lbl_pr_local29_name = new JLabel("");
		lbl_pr_local29_name.setBounds(20, 1, 69, 58);
		lbl_pr_local29_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local29.png")));
		p_pr_local29.add(lbl_pr_local29_name);
		
		JPanel p_pr_local26 = new JPanel();
		p_pr_local26.setLayout(null);
		p_pr_local26.setBorder(new LineBorder(Color.GRAY));
		p_pr_local26.setBackground(new Color(255, 172, 137));
		p_pr_local26.setBounds(629, 217, 90, 60);
		p_playroom.add(p_pr_local26);
		
		lbl_pr_local26_build = new JLabel("");
		lbl_pr_local26_build.setOpaque(true);
		lbl_pr_local26_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local26_build.setBounds(0, 0, 20, 60);
		p_pr_local26.add(lbl_pr_local26_build);
		
		lbl_pr_local26_user1 = new JLabel("");
		lbl_pr_local26_user1.setBorder(null);
		lbl_pr_local26_user1.setVisible(false);
		lbl_pr_local26_user1.setBackground(Color.RED);
		lbl_pr_local26_user1.setBounds(25, 7, 20, 20);
		p_pr_local26.add(lbl_pr_local26_user1);
		
		lbl_pr_local26_user2 = new JLabel("");
		lbl_pr_local26_user2.setBorder(null);
		lbl_pr_local26_user2.setVisible(false);
		lbl_pr_local26_user2.setBackground(Color.BLUE);
		lbl_pr_local26_user2.setBounds(64, 7, 20, 20);
		p_pr_local26.add(lbl_pr_local26_user2);
		
		lbl_pr_local26_user3 = new JLabel("");
		lbl_pr_local26_user3.setBorder(null);
		lbl_pr_local26_user3.setVisible(false);
		lbl_pr_local26_user3.setBackground(Color.ORANGE);
		lbl_pr_local26_user3.setBounds(25, 33, 20, 20);
		p_pr_local26.add(lbl_pr_local26_user3);
		
		lbl_pr_local26_user4 = new JLabel("");
		lbl_pr_local26_user4.setBorder(null);
		lbl_pr_local26_user4.setVisible(false);
		lbl_pr_local26_user4.setBackground(Color.GREEN);
		lbl_pr_local26_user4.setBounds(64, 33, 20, 20);
		p_pr_local26.add(lbl_pr_local26_user4);
		
		JLabel lbl_pr_local26_name = new JLabel("");
		lbl_pr_local26_name.setBounds(20, 1, 69, 58);
		lbl_pr_local26_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local26.png")));
		p_pr_local26.add(lbl_pr_local26_name);
		
		JPanel p_pr_local28 = new JPanel();
		p_pr_local28.setLayout(null);
		p_pr_local28.setBorder(new LineBorder(Color.GRAY));
		p_pr_local28.setBackground(new Color(231, 231, 231));
		p_pr_local28.setBounds(629, 335, 90, 60);
		p_playroom.add(p_pr_local28);
		
		lbl_pr_local28_user1 = new JLabel("");
		lbl_pr_local28_user1.setBorder(null);
		lbl_pr_local28_user1.setVisible(false);
		lbl_pr_local28_user1.setBackground(Color.RED);
		lbl_pr_local28_user1.setBounds(25, 7, 20, 20);
		p_pr_local28.add(lbl_pr_local28_user1);
		
		lbl_pr_local28_user2 = new JLabel("");
		lbl_pr_local28_user2.setBorder(null);
		lbl_pr_local28_user2.setVisible(false);
		lbl_pr_local28_user2.setBackground(Color.BLUE);
		lbl_pr_local28_user2.setBounds(64, 7, 20, 20);
		p_pr_local28.add(lbl_pr_local28_user2);
		
		lbl_pr_local28_user3 = new JLabel("");
		lbl_pr_local28_user3.setBorder(null);
		lbl_pr_local28_user3.setVisible(false);
		lbl_pr_local28_user3.setBackground(Color.ORANGE);
		lbl_pr_local28_user3.setBounds(25, 33, 20, 20);
		p_pr_local28.add(lbl_pr_local28_user3);
		
		lbl_pr_local28_user4 = new JLabel("");
		lbl_pr_local28_user4.setBorder(null);
		lbl_pr_local28_user4.setVisible(false);
		lbl_pr_local28_user4.setBackground(Color.GREEN);
		lbl_pr_local28_user4.setBounds(64, 33, 20, 20);
		p_pr_local28.add(lbl_pr_local28_user4);
		
		JLabel lbl_pr_local28_name = new JLabel("");
		lbl_pr_local28_name.setBounds(20, 1, 69, 58);
		lbl_pr_local28_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_card_rr.png")));
		p_pr_local28.add(lbl_pr_local28_name);
		
		JPanel p_pr_local27 = new JPanel();
		p_pr_local27.setLayout(null);
		p_pr_local27.setBorder(new LineBorder(Color.GRAY));
		p_pr_local27.setBackground(new Color(255, 172, 137));
		p_pr_local27.setBounds(629, 276, 90, 60);
		p_playroom.add(p_pr_local27);
		
		lbl_pr_local27_build = new JLabel("");
		lbl_pr_local27_build.setOpaque(true);
		lbl_pr_local27_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local27_build.setBounds(0, 0, 20, 60);
		p_pr_local27.add(lbl_pr_local27_build);
		
		lbl_pr_local27_user1 = new JLabel("");
		lbl_pr_local27_user1.setBorder(null);
		lbl_pr_local27_user1.setVisible(false);
		lbl_pr_local27_user1.setBackground(Color.RED);
		lbl_pr_local27_user1.setBounds(25, 7, 20, 20);
		p_pr_local27.add(lbl_pr_local27_user1);
		
		lbl_pr_local27_user2 = new JLabel("");
		lbl_pr_local27_user2.setBorder(null);
		lbl_pr_local27_user2.setVisible(false);
		lbl_pr_local27_user2.setBackground(Color.BLUE);
		lbl_pr_local27_user2.setBounds(64, 7, 20, 20);
		p_pr_local27.add(lbl_pr_local27_user2);
		
		lbl_pr_local27_user3 = new JLabel("");
		lbl_pr_local27_user3.setBorder(null);
		lbl_pr_local27_user3.setVisible(false);
		lbl_pr_local27_user3.setBackground(Color.ORANGE);
		lbl_pr_local27_user3.setBounds(25, 33, 20, 20);
		p_pr_local27.add(lbl_pr_local27_user3);
		
		lbl_pr_local27_user4 = new JLabel("");
		lbl_pr_local27_user4.setBorder(null);
		lbl_pr_local27_user4.setVisible(false);
		lbl_pr_local27_user4.setBackground(Color.GREEN);
		lbl_pr_local27_user4.setBounds(64, 33, 20, 20);
		p_pr_local27.add(lbl_pr_local27_user4);
		
		JLabel lbl_pr_local27_name = new JLabel("");
		lbl_pr_local27_name.setBounds(20, 1, 69, 58);
		lbl_pr_local27_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local27.png")));
		p_pr_local27.add(lbl_pr_local27_name);
		
		JPanel p_pr_local17 = new JPanel();
		p_pr_local17.setLayout(null);
		p_pr_local17.setBorder(new LineBorder(Color.GRAY));
		p_pr_local17.setBackground(new Color(253, 153, 172));
		p_pr_local17.setBounds(176, 29, 60, 90);
		p_playroom.add(p_pr_local17);
		
		lbl_pr_local17_build = new JLabel("");
		lbl_pr_local17_build.setOpaque(true);
		lbl_pr_local17_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local17_build.setBounds(0, 70, 60, 20);
		p_pr_local17.add(lbl_pr_local17_build);
		
		lbl_pr_local17_user2 = new JLabel("");
		lbl_pr_local17_user2.setBorder(null);
		lbl_pr_local17_user2.setVisible(false);
		lbl_pr_local17_user2.setBackground(Color.BLUE);
		lbl_pr_local17_user2.setBounds(33, 5, 20, 20);
		p_pr_local17.add(lbl_pr_local17_user2);
		
		lbl_pr_local17_user1 = new JLabel("");
		lbl_pr_local17_user1.setBorder(null);
		lbl_pr_local17_user1.setVisible(false);
		lbl_pr_local17_user1.setBackground(Color.RED);
		lbl_pr_local17_user1.setBounds(7, 5, 20, 20);
		p_pr_local17.add(lbl_pr_local17_user1);
		
		lbl_pr_local17_user4 = new JLabel("");
		lbl_pr_local17_user4.setBorder(null);
		lbl_pr_local17_user4.setVisible(false);
		lbl_pr_local17_user4.setBackground(Color.GREEN);
		lbl_pr_local17_user4.setBounds(33, 45, 20, 20);
		p_pr_local17.add(lbl_pr_local17_user4);
		
		lbl_pr_local17_user3 = new JLabel("");
		lbl_pr_local17_user3.setBorder(null);
		lbl_pr_local17_user3.setVisible(false);
		lbl_pr_local17_user3.setBackground(Color.ORANGE);
		lbl_pr_local17_user3.setBounds(7, 45, 20, 20);
		p_pr_local17.add(lbl_pr_local17_user3);
		
		JLabel lbl_pr_local17_name = new JLabel("");
		lbl_pr_local17_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local17_name.setForeground(Color.WHITE);
		lbl_pr_local17_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local17_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local17.png")));
		lbl_pr_local17_name.setBounds(1, 1, 58, 69);
		p_pr_local17.add(lbl_pr_local17_name);
		
		JPanel p_pr_local18 = new JPanel();
		p_pr_local18.setBorder(new LineBorder(Color.GRAY));
		p_pr_local18.setBounds(235, 29, 60, 90);
		p_pr_local18.setBackground(new Color(253, 153, 172));
		p_playroom.add(p_pr_local18);
		p_pr_local18.setLayout(null);
		
		lbl_pr_local18_build = new JLabel("");
		lbl_pr_local18_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local18_build.setOpaque(true);
		lbl_pr_local18_build.setBounds(0, 70, 60, 20);
		p_pr_local18.add(lbl_pr_local18_build);
		
		lbl_pr_local18_user1 = new JLabel("");
		lbl_pr_local18_user1.setBorder(null);
		lbl_pr_local18_user1.setVisible(false);
		lbl_pr_local18_user1.setBackground(Color.RED);
		lbl_pr_local18_user1.setBounds(7, 5, 20, 20);
		p_pr_local18.add(lbl_pr_local18_user1);
		
		lbl_pr_local18_user2 = new JLabel("");
		lbl_pr_local18_user2.setBorder(null);
		lbl_pr_local18_user2.setVisible(false);
		lbl_pr_local18_user2.setBackground(Color.BLUE);
		lbl_pr_local18_user2.setBounds(33, 5, 20, 20);
		p_pr_local18.add(lbl_pr_local18_user2);
		
		lbl_pr_local18_user3 = new JLabel("");
		lbl_pr_local18_user3.setBorder(null);
		lbl_pr_local18_user3.setVisible(false);
		lbl_pr_local18_user3.setBackground(Color.ORANGE);
		lbl_pr_local18_user3.setBounds(7, 45, 20, 20);
		p_pr_local18.add(lbl_pr_local18_user3);
		
		lbl_pr_local18_user4 = new JLabel("");
		lbl_pr_local18_user4.setBorder(null);
		lbl_pr_local18_user4.setVisible(false);
		lbl_pr_local18_user4.setBackground(Color.GREEN);
		lbl_pr_local18_user4.setBounds(33, 45, 20, 20);
		p_pr_local18.add(lbl_pr_local18_user4);
		
		JLabel lbl_pr_local18_name = new JLabel("");
		lbl_pr_local18_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local18_name.setForeground(Color.WHITE);
		lbl_pr_local18_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local18_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local18.png")));
		lbl_pr_local18_name.setBounds(1, 1, 58, 69);
		p_pr_local18.add(lbl_pr_local18_name);
		
		JPanel p_pr_local19 = new JPanel();
		p_pr_local19.setLayout(null);
		p_pr_local19.setBorder(new LineBorder(Color.GRAY));
		p_pr_local19.setBackground(new Color(253, 153, 172));
		p_pr_local19.setBounds(294, 29, 60, 90);
		p_playroom.add(p_pr_local19);
		
		lbl_pr_local19_build = new JLabel("");
		lbl_pr_local19_build.setOpaque(true);
		lbl_pr_local19_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local19_build.setBounds(0, 70, 60, 20);
		p_pr_local19.add(lbl_pr_local19_build);
		
		lbl_pr_local19_user1 = new JLabel("");
		lbl_pr_local19_user1.setBorder(null);
		lbl_pr_local19_user1.setVisible(false);
		lbl_pr_local19_user1.setBackground(Color.RED);
		lbl_pr_local19_user1.setBounds(7, 5, 20, 20);
		p_pr_local19.add(lbl_pr_local19_user1);
		
		lbl_pr_local19_user2 = new JLabel("");
		lbl_pr_local19_user2.setBorder(null);
		lbl_pr_local19_user2.setVisible(false);
		lbl_pr_local19_user2.setBackground(Color.BLUE);
		lbl_pr_local19_user2.setBounds(33, 5, 20, 20);
		p_pr_local19.add(lbl_pr_local19_user2);
		
		lbl_pr_local19_user3 = new JLabel("");
		lbl_pr_local19_user3.setBorder(null);
		lbl_pr_local19_user3.setVisible(false);
		lbl_pr_local19_user3.setBackground(Color.ORANGE);
		lbl_pr_local19_user3.setBounds(7, 45, 20, 20);
		p_pr_local19.add(lbl_pr_local19_user3);
		
		lbl_pr_local19_user4 = new JLabel("");
		lbl_pr_local19_user4.setBorder(null);
		lbl_pr_local19_user4.setVisible(false);
		lbl_pr_local19_user4.setBackground(Color.GREEN);
		lbl_pr_local19_user4.setBounds(33, 45, 20, 20);
		p_pr_local19.add(lbl_pr_local19_user4);
		
		JLabel lbl_pr_local19_name = new JLabel("");
		lbl_pr_local19_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local19_name.setForeground(Color.WHITE);
		lbl_pr_local19_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local19_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local19.png")));
		lbl_pr_local19_name.setBounds(1, 1, 58, 69);
		p_pr_local19.add(lbl_pr_local19_name);
		
		JPanel p_pr_local20 = new JPanel();
		p_pr_local20.setLayout(null);
		p_pr_local20.setBorder(new LineBorder(Color.GRAY));
		p_pr_local20.setBackground(new Color(231, 231, 231));
		p_pr_local20.setBounds(353, 29, 60, 90);
		p_playroom.add(p_pr_local20);
		
		lbl_pr_local20_user1 = new JLabel("");
		lbl_pr_local20_user1.setBorder(null);
		lbl_pr_local20_user1.setVisible(false);
		lbl_pr_local20_user1.setBackground(Color.RED);
		lbl_pr_local20_user1.setBounds(7, 5, 20, 20);
		p_pr_local20.add(lbl_pr_local20_user1);
		
		lbl_pr_local20_user2 = new JLabel("");
		lbl_pr_local20_user2.setBorder(null);
		lbl_pr_local20_user2.setVisible(false);
		lbl_pr_local20_user2.setBackground(Color.BLUE);
		lbl_pr_local20_user2.setBounds(33, 5, 20, 20);
		p_pr_local20.add(lbl_pr_local20_user2);
		
		lbl_pr_local20_user3 = new JLabel("");
		lbl_pr_local20_user3.setBorder(null);
		lbl_pr_local20_user3.setVisible(false);
		lbl_pr_local20_user3.setBackground(Color.ORANGE);
		lbl_pr_local20_user3.setBounds(7, 45, 20, 20);
		p_pr_local20.add(lbl_pr_local20_user3);
		
		lbl_pr_local20_user4 = new JLabel("");
		lbl_pr_local20_user4.setBorder(null);
		lbl_pr_local20_user4.setVisible(false);
		lbl_pr_local20_user4.setBackground(Color.GREEN);
		lbl_pr_local20_user4.setBounds(33, 45, 20, 20);
		p_pr_local20.add(lbl_pr_local20_user4);
		
		JLabel lbl_pr_local20_name = new JLabel("");
		lbl_pr_local20_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local20_name.setForeground(Color.DARK_GRAY);
		lbl_pr_local20_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local20_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_card.png")));
		lbl_pr_local20_name.setBounds(1, 15, 58, 69);
		p_pr_local20.add(lbl_pr_local20_name);
		
		JPanel p_pr_local23 = new JPanel();
		p_pr_local23.setLayout(null);
		p_pr_local23.setBorder(new LineBorder(Color.GRAY));
		p_pr_local23.setBackground(new Color(187, 153, 224));
		p_pr_local23.setBounds(530, 29, 60, 90);
		p_playroom.add(p_pr_local23);
		
		lbl_pr_local23_build = new JLabel("");
		lbl_pr_local23_build.setOpaque(true);
		lbl_pr_local23_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local23_build.setBounds(0, 70, 60, 20);
		p_pr_local23.add(lbl_pr_local23_build);
		
		lbl_pr_local23_user1 = new JLabel("");
		lbl_pr_local23_user1.setBorder(null);
		lbl_pr_local23_user1.setVisible(false);
		lbl_pr_local23_user1.setBackground(Color.RED);
		lbl_pr_local23_user1.setBounds(7, 5, 20, 20);
		p_pr_local23.add(lbl_pr_local23_user1);
		
		lbl_pr_local23_user2 = new JLabel("");
		lbl_pr_local23_user2.setBorder(null);
		lbl_pr_local23_user2.setVisible(false);
		lbl_pr_local23_user2.setBackground(Color.BLUE);
		lbl_pr_local23_user2.setBounds(33, 5, 20, 20);
		p_pr_local23.add(lbl_pr_local23_user2);
		
		lbl_pr_local23_user3 = new JLabel("");
		lbl_pr_local23_user3.setBorder(null);
		lbl_pr_local23_user3.setVisible(false);
		lbl_pr_local23_user3.setBackground(Color.ORANGE);
		lbl_pr_local23_user3.setBounds(7, 45, 20, 20);
		p_pr_local23.add(lbl_pr_local23_user3);
		
		lbl_pr_local23_user4 = new JLabel("");
		lbl_pr_local23_user4.setBorder(null);
		lbl_pr_local23_user4.setVisible(false);
		lbl_pr_local23_user4.setBackground(Color.GREEN);
		lbl_pr_local23_user4.setBounds(33, 45, 20, 20);
		p_pr_local23.add(lbl_pr_local23_user4);
		
		JLabel lbl_pr_local23_name = new JLabel("");
		lbl_pr_local23_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local23_name.setForeground(Color.WHITE);
		lbl_pr_local23_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local23_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local23.png")));
		lbl_pr_local23_name.setBounds(1, 1, 58, 69);
		p_pr_local23.add(lbl_pr_local23_name);
		
		JPanel p_pr_local22 = new JPanel();
		p_pr_local22.setLayout(null);
		p_pr_local22.setBorder(new LineBorder(Color.GRAY));
		p_pr_local22.setBackground(new Color(187, 153, 224));
		p_pr_local22.setBounds(471, 29, 60, 90);
		p_playroom.add(p_pr_local22);
		
		lbl_pr_local22_build = new JLabel("");
		lbl_pr_local22_build.setOpaque(true);
		lbl_pr_local22_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local22_build.setBounds(0, 70, 60, 20);
		p_pr_local22.add(lbl_pr_local22_build);
		
		lbl_pr_local22_user1 = new JLabel("");
		lbl_pr_local22_user1.setBorder(null);
		lbl_pr_local22_user1.setVisible(false);
		lbl_pr_local22_user1.setBackground(Color.RED);
		lbl_pr_local22_user1.setBounds(7, 5, 20, 20);
		p_pr_local22.add(lbl_pr_local22_user1);
		
		lbl_pr_local22_user2 = new JLabel("");
		lbl_pr_local22_user2.setBorder(null);
		lbl_pr_local22_user2.setVisible(false);
		lbl_pr_local22_user2.setBackground(Color.BLUE);
		lbl_pr_local22_user2.setBounds(33, 5, 20, 20);
		p_pr_local22.add(lbl_pr_local22_user2);
		
		lbl_pr_local22_user3 = new JLabel("");
		lbl_pr_local22_user3.setBorder(null);
		lbl_pr_local22_user3.setVisible(false);
		lbl_pr_local22_user3.setBackground(Color.ORANGE);
		lbl_pr_local22_user3.setBounds(7, 45, 20, 20);
		p_pr_local22.add(lbl_pr_local22_user3);
		
		lbl_pr_local22_user4 = new JLabel("");
		lbl_pr_local22_user4.setBorder(null);
		lbl_pr_local22_user4.setVisible(false);
		lbl_pr_local22_user4.setBackground(Color.GREEN);
		lbl_pr_local22_user4.setBounds(33, 45, 20, 20);
		p_pr_local22.add(lbl_pr_local22_user4);
		
		JLabel lbl_pr_local22_name = new JLabel("");
		lbl_pr_local22_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local22_name.setForeground(Color.WHITE);
		lbl_pr_local22_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local22_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local22.png")));
		lbl_pr_local22_name.setBounds(1, 1, 58, 69);
		p_pr_local22.add(lbl_pr_local22_name);
		
		JPanel p_pr_local21 = new JPanel();
		p_pr_local21.setLayout(null);
		p_pr_local21.setBorder(new LineBorder(Color.GRAY));
		p_pr_local21.setBackground(new Color(187, 153, 224));
		p_pr_local21.setBounds(412, 29, 60, 90);
		p_playroom.add(p_pr_local21);
		
		lbl_pr_local21_build = new JLabel("");
		lbl_pr_local21_build.setOpaque(true);
		lbl_pr_local21_build.setBorder(new LineBorder(Color.GRAY));
		lbl_pr_local21_build.setBounds(0, 70, 60, 20);
		p_pr_local21.add(lbl_pr_local21_build);
		
		lbl_pr_local21_user1 = new JLabel("");
		lbl_pr_local21_user1.setBorder(null);
		lbl_pr_local21_user1.setVisible(false);
		lbl_pr_local21_user1.setBackground(Color.RED);
		lbl_pr_local21_user1.setBounds(7, 5, 20, 20);
		p_pr_local21.add(lbl_pr_local21_user1);
		
		lbl_pr_local21_user2 = new JLabel("");
		lbl_pr_local21_user2.setBorder(null);
		lbl_pr_local21_user2.setVisible(false);
		lbl_pr_local21_user2.setBackground(Color.BLUE);
		lbl_pr_local21_user2.setBounds(33, 5, 20, 20);
		p_pr_local21.add(lbl_pr_local21_user2);
		
		lbl_pr_local21_user3 = new JLabel("");
		lbl_pr_local21_user3.setBorder(null);
		lbl_pr_local21_user3.setVisible(false);
		lbl_pr_local21_user3.setBackground(Color.ORANGE);
		lbl_pr_local21_user3.setBounds(7, 45, 20, 20);
		p_pr_local21.add(lbl_pr_local21_user3);
		
		lbl_pr_local21_user4 = new JLabel("");
		lbl_pr_local21_user4.setBorder(null);
		lbl_pr_local21_user4.setVisible(false);
		lbl_pr_local21_user4.setBackground(Color.GREEN);
		lbl_pr_local21_user4.setBounds(33, 45, 20, 20);
		p_pr_local21.add(lbl_pr_local21_user4);
		
		JLabel lbl_pr_local21_name = new JLabel("");
		lbl_pr_local21_name.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_pr_local21_name.setForeground(Color.WHITE);
		lbl_pr_local21_name.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbl_pr_local21_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_local21.png")));
		lbl_pr_local21_name.setBounds(1, 1, 58, 69);
		p_pr_local21.add(lbl_pr_local21_name);
		
		JPanel p_pr_local12 = new JPanel();
		p_pr_local12.setLayout(null);
		p_pr_local12.setBorder(new LineBorder(Color.GRAY));
		p_pr_local12.setBackground(new Color(231, 231, 231));
		p_pr_local12.setBounds(47, 335, 90, 60);
		p_playroom.add(p_pr_local12);
		
		lbl_pr_local12_user1 = new JLabel("");
		lbl_pr_local12_user1.setBorder(null);
		lbl_pr_local12_user1.setVisible(false);
		
		lbl_pr_local12_user4 = new JLabel("");
		lbl_pr_local12_user4.setBorder(null);
		lbl_pr_local12_user4.setVisible(false);
		lbl_pr_local12_user4.setBackground(new Color(0, 255, 0));
		lbl_pr_local12_user4.setBounds(44, 33, 20, 20);
		p_pr_local12.add(lbl_pr_local12_user4);
		lbl_pr_local12_user1.setBackground(Color.RED);
		lbl_pr_local12_user1.setBounds(5, 7, 20, 20);
		p_pr_local12.add(lbl_pr_local12_user1);
		
		lbl_pr_local12_user3 = new JLabel("");
		lbl_pr_local12_user3.setBorder(null);
		lbl_pr_local12_user3.setVisible(false);
		lbl_pr_local12_user3.setBackground(Color.ORANGE);
		lbl_pr_local12_user3.setBounds(5, 33, 20, 20);
		p_pr_local12.add(lbl_pr_local12_user3);
		
		lbl_pr_local12_user2 = new JLabel("");
		lbl_pr_local12_user2.setBorder(null);
		lbl_pr_local12_user2.setVisible(false);
		lbl_pr_local12_user2.setBackground(Color.BLUE);
		lbl_pr_local12_user2.setBounds(44, 7, 20, 20);
		p_pr_local12.add(lbl_pr_local12_user2);
		
		JLabel lbl_pr_local12_name = new JLabel("");
		lbl_pr_local12_name.setBounds(1, 1, 69, 58);
		lbl_pr_local12_name.setIcon(new ImageIcon(client.class.getResource("/img/bg_card_r.png")));
		p_pr_local12.add(lbl_pr_local12_name);
		
		lbl_pr_title = new JLabel("");
		lbl_pr_title.setBounds(275, 255, 226, 226);
		lbl_pr_title.setIcon(new ImageIcon(client.class.getResource("/img/bg_title.png")));
		p_playroom.add(lbl_pr_title);
		
		JLabel lbl_pr_bg = new JLabel("");
		lbl_pr_bg.setIcon(new ImageIcon(client.class.getResource("/img/bg_line2.png")));
		lbl_pr_bg.setBounds(37, 21, 689, 689);
		
		p_playroom.add(lbl_pr_bg);
		p_signup.setBackground(new Color(255, 255, 255));
		p_signup.setBounds(0, 0, 768, 733);
		contentPane.add(p_signup);
		p_signup.setLayout(null);
		
		JLabel lbl_login_title_1 = new JLabel("");
		lbl_login_title_1.setBounds(277, 131, 214, 214);
		lbl_login_title_1.setIcon(new ImageIcon(client.class.getResource("/img/signup_title.png")));
		p_signup.add(lbl_login_title_1);
		
		JPanel p_signup_id = new JPanel();
		p_signup_id.setLayout(null);
		p_signup_id.setBorder(new LineBorder(Color.GRAY));
		p_signup_id.setBackground(Color.WHITE);
		p_signup_id.setBounds(271, 357, 226, 42);
		p_signup.add(p_signup_id);
		
		JLabel lbl_signup_UI_id = new JLabel("ID");
		lbl_signup_UI_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_signup_UI_id.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_signup_UI_id.setBounds(10, 10, 46, 20);
		p_signup_id.add(lbl_signup_UI_id);
		
		tf_signup_id = new JTextField();
		tf_signup_id.setOpaque(false);
		tf_signup_id.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		tf_signup_id.setColumns(10);
		tf_signup_id.setBorder(null);
		tf_signup_id.setBounds(66, 4, 150, 34);
		p_signup_id.add(tf_signup_id);
		
		JPanel p_signup_pw = new JPanel();
		p_signup_pw.setLayout(null);
		p_signup_pw.setBorder(new LineBorder(Color.GRAY));
		p_signup_pw.setBackground(Color.WHITE);
		p_signup_pw.setBounds(271, 409, 226, 42);
		p_signup.add(p_signup_pw);
		
		JLabel lbl_signup_UI_pw = new JLabel("PW");
		lbl_signup_UI_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_signup_UI_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_signup_UI_pw.setBounds(10, 10, 46, 20);
		p_signup_pw.add(lbl_signup_UI_pw);
		
		pf_signup_pw = new JPasswordField();
		pf_signup_pw.setOpaque(false);
		pf_signup_pw.setBorder(null);
		pf_signup_pw.setBounds(66, 4, 150, 34);
		p_signup_pw.add(pf_signup_pw);
		
		JButton btn_signup_signup = new JButton("가입완료");
		btn_signup_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_signup_signup) {
					dto.setId(tf_signup_id.getText());					// 텍스트 필드 입력 값 DTO에 저장
					dto.setPw(pf_signup_pw.getText());				// 패스워드 필드 입력 값 DTO에 저장
					dto.setNote(pf_signup_pwre.getText());		// 패스워드 필드 입력 값 DTO에 저장
					
					// [T0018] 회원가입 기능 추가
					// https://github.com/Hx2DEV/marble/issues/27
					// 작업자 이상원 
					// 수정자 전호형
					T0018.c_T0018_send(dto, oos);
				}
			}
		});
		btn_signup_signup.setActionCommand("가입완료");
		btn_signup_signup.setForeground(Color.WHITE);
		btn_signup_signup.setFont(setfont.fontSetting(14, "regular"));
		btn_signup_signup.setBackground(new Color(65, 105, 225));
		btn_signup_signup.setBounds(271, 513, 226, 42);
		p_signup.add(btn_signup_signup);
		
		JButton btn_signup_back = new JButton("돌아가기");
		btn_signup_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btn_signup_back) {
					p_allHidden();				// 모든 panel 숨기기
					p_login.setVisible(true);	// 로그인 panel 보이기
				}
			}
		});
		btn_signup_back.setForeground(Color.GRAY);
		btn_signup_back.setFont(setfont.fontSetting(14, "regular"));
		btn_signup_back.setBorder(null);
		btn_signup_back.setBackground(Color.WHITE);
		btn_signup_back.setBounds(271, 565, 226, 42);
		p_signup.add(btn_signup_back);
		
		JPanel p_signup_pwre = new JPanel();
		p_signup_pwre.setLayout(null);
		p_signup_pwre.setBorder(new LineBorder(Color.GRAY));
		p_signup_pwre.setBackground(Color.WHITE);
		p_signup_pwre.setBounds(271, 461, 226, 42);
		p_signup.add(p_signup_pwre);
		
		JLabel lbl_signup_UI_pwre = new JLabel("PW(RE)");
		lbl_signup_UI_pwre.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_signup_UI_pwre.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		lbl_signup_UI_pwre.setBounds(10, 10, 46, 20);
		p_signup_pwre.add(lbl_signup_UI_pwre);
		
		pf_signup_pwre = new JPasswordField();
		pf_signup_pwre.setOpaque(false);
		pf_signup_pwre.setBorder(null);
		pf_signup_pwre.setBounds(66, 4, 150, 34);
		p_signup_pwre.add(pf_signup_pwre);
		
		JPanel p_sidebar = new JPanel();
		p_sidebar.setBounds(767, 0, 245, 733);
		contentPane.add(p_sidebar);
		p_sidebar.setLayout(null);
		
		lbl_sidebar_location = new JLabel("");
		lbl_sidebar_location.setFont(setfont.fontSetting(16, "bold"));
		lbl_sidebar_location.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_sidebar_location.setBounds(10, 25, 59, 30);
		p_sidebar.add(lbl_sidebar_location);
		
		lbl_sidebar_title = new JLabel("");
		lbl_sidebar_title.setFont(setfont.fontSetting(14, "regular"));
		lbl_sidebar_title.setBounds(10, 51, 180, 30);
		p_sidebar.add(lbl_sidebar_title);
		
		p_sidebar_user1 = new JPanel();
		p_sidebar_user1.setVisible(false);
		p_sidebar_user1.setBorder(new LineBorder(Color.GRAY));
		p_sidebar_user1.setBackground(Color.WHITE);
		p_sidebar_user1.setBounds(10, 91, 225, 36);
		p_sidebar.add(p_sidebar_user1);
		p_sidebar_user1.setLayout(null);
		
		lbl_sidebar_picon1 = new JLabel("");
		lbl_sidebar_picon1.setBounds(44, 5, 26, 26);
		lbl_sidebar_picon1.setIcon(new ImageIcon(client.class.getResource("/img/icon_1pready_s.png")));
		p_sidebar_user1.add(lbl_sidebar_picon1);
		
		lbl_sidebar_pname1 = new JLabel("1P 아이디");
		lbl_sidebar_pname1.setFont(setfont.fontSetting(12, "light"));
		lbl_sidebar_pname1.setBounds(75, 3, 75, 30);
		p_sidebar_user1.add(lbl_sidebar_pname1);
		
		lbl_sidebar_pmoney1 = new JLabel("");
		lbl_sidebar_pmoney1.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_sidebar_pmoney1.setFont(setfont.fontSetting(12, "regular"));
		lbl_sidebar_pmoney1.setBounds(154, 3, 60, 30);
		p_sidebar_user1.add(lbl_sidebar_pmoney1);
		
		JLabel lbl_sidebar_bar = new JLabel("");
		lbl_sidebar_bar.setOpaque(true);
		lbl_sidebar_bar.setBackground(Color.GRAY);
		lbl_sidebar_bar.setBounds(151, 10, 1, 15);
		p_sidebar_user1.add(lbl_sidebar_bar);
		
		lbl_sidebar_next1 = new JLabel("");
		lbl_sidebar_next1.setVisible(false);
		lbl_sidebar_next1.setBounds(10, 6, 24, 24);
		lbl_sidebar_next1.setIcon(new ImageIcon(client.class.getResource("/img/next.png")));
		p_sidebar_user1.add(lbl_sidebar_next1);
		
		p_sidebar_user2 = new JPanel();
		p_sidebar_user2.setVisible(false);
		p_sidebar_user2.setLayout(null);
		p_sidebar_user2.setBorder(new LineBorder(Color.GRAY));
		p_sidebar_user2.setBackground(Color.WHITE);
		p_sidebar_user2.setBounds(10, 125, 225, 36);
		p_sidebar.add(p_sidebar_user2);
		
		lbl_sidebar_picon2 = new JLabel("");
		lbl_sidebar_picon2.setBounds(44, 5, 26, 26);
		lbl_sidebar_picon2.setIcon(new ImageIcon(client.class.getResource("/img/icon_2pready_s.png")));
		p_sidebar_user2.add(lbl_sidebar_picon2);
		
		lbl_sidebar_pname2 = new JLabel("2P 아이디");
		lbl_sidebar_pname2.setFont(setfont.fontSetting(12, "light"));
		lbl_sidebar_pname2.setBounds(75, 3, 75, 30);
		p_sidebar_user2.add(lbl_sidebar_pname2);
		
		lbl_sidebar_pmoney2 = new JLabel("");
		lbl_sidebar_pmoney2.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_sidebar_pmoney2.setFont(setfont.fontSetting(12, "regular"));
		lbl_sidebar_pmoney2.setBounds(154, 3, 60, 30);
		p_sidebar_user2.add(lbl_sidebar_pmoney2);
		
		JLabel lbl_sidebar_bar_1 = new JLabel("");
		lbl_sidebar_bar_1.setOpaque(true);
		lbl_sidebar_bar_1.setBackground(Color.GRAY);
		lbl_sidebar_bar_1.setBounds(151, 10, 1, 15);
		p_sidebar_user2.add(lbl_sidebar_bar_1);
		
		lbl_sidebar_next2 = new JLabel("");
		lbl_sidebar_next2.setVisible(false);
		lbl_sidebar_next2.setBounds(10, 6, 24, 24);
		lbl_sidebar_next2.setIcon(new ImageIcon(client.class.getResource("/img/next.png")));
		p_sidebar_user2.add(lbl_sidebar_next2);
		
		p_sidebar_user3 = new JPanel();
		p_sidebar_user3.setVisible(false);
		p_sidebar_user3.setLayout(null);
		p_sidebar_user3.setBorder(new LineBorder(Color.GRAY));
		p_sidebar_user3.setBackground(Color.WHITE);
		p_sidebar_user3.setBounds(10, 160, 225, 36);
		p_sidebar.add(p_sidebar_user3);
		
		lbl_sidebar_picon3 = new JLabel("");
		lbl_sidebar_picon3.setBounds(44, 5, 26, 26);
		lbl_sidebar_picon3.setIcon(new ImageIcon(client.class.getResource("/img/icon_3pready_s.png")));
		p_sidebar_user3.add(lbl_sidebar_picon3);
		
		lbl_sidebar_pname3 = new JLabel("3P 아이디");
		lbl_sidebar_pname3.setFont(setfont.fontSetting(12, "light"));
		lbl_sidebar_pname3.setBounds(75, 3, 75, 30);
		p_sidebar_user3.add(lbl_sidebar_pname3);
		
		lbl_sidebar_pmoney3 = new JLabel("");
		lbl_sidebar_pmoney3.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_sidebar_pmoney3.setFont(setfont.fontSetting(12, "regular"));
		lbl_sidebar_pmoney3.setBounds(154, 3, 60, 30);
		p_sidebar_user3.add(lbl_sidebar_pmoney3);
		
		JLabel lbl_sidebar_bar_2 = new JLabel("");
		lbl_sidebar_bar_2.setOpaque(true);
		lbl_sidebar_bar_2.setBackground(Color.GRAY);
		lbl_sidebar_bar_2.setBounds(151, 10, 1, 15);
		p_sidebar_user3.add(lbl_sidebar_bar_2);
		
		lbl_sidebar_next3 = new JLabel("");
		lbl_sidebar_next3.setVisible(false);
		lbl_sidebar_next3.setBounds(10, 6, 24, 24);
		lbl_sidebar_next3.setIcon(new ImageIcon(client.class.getResource("/img/next.png")));
		p_sidebar_user3.add(lbl_sidebar_next3);
		
		p_sidebar_user4 = new JPanel();
		p_sidebar_user4.setVisible(false);
		p_sidebar_user4.setLayout(null);
		p_sidebar_user4.setBorder(new LineBorder(Color.GRAY));
		p_sidebar_user4.setBackground(Color.WHITE);
		p_sidebar_user4.setBounds(10, 195, 225, 36);
		p_sidebar.add(p_sidebar_user4);
		
		lbl_sidebar_picon4 = new JLabel("");
		lbl_sidebar_picon4.setBounds(44, 5, 26, 26);
		lbl_sidebar_picon4.setIcon(new ImageIcon(client.class.getResource("/img/icon_4pready_s.png")));
		p_sidebar_user4.add(lbl_sidebar_picon4);
		
		lbl_sidebar_pname4 = new JLabel("4P 아이디");
		lbl_sidebar_pname4.setFont(setfont.fontSetting(12, "light"));
		lbl_sidebar_pname4.setBounds(75, 3, 75, 30);
		p_sidebar_user4.add(lbl_sidebar_pname4);
		
		lbl_sidebar_pmoney4 = new JLabel("");
		lbl_sidebar_pmoney4.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_sidebar_pmoney4.setFont(setfont.fontSetting(12, "regular"));
		lbl_sidebar_pmoney4.setBounds(154, 3, 60, 30);
		p_sidebar_user4.add(lbl_sidebar_pmoney4);
		
		JLabel lbl_sidebar_bar_3 = new JLabel("");
		lbl_sidebar_bar_3.setOpaque(true);
		lbl_sidebar_bar_3.setBackground(Color.GRAY);
		lbl_sidebar_bar_3.setBounds(151, 10, 1, 15);
		p_sidebar_user4.add(lbl_sidebar_bar_3);
		
		lbl_sidebar_next4 = new JLabel("");
		lbl_sidebar_next4.setVisible(false);
		lbl_sidebar_next4.setBounds(10, 6, 24, 24);
		lbl_sidebar_next4.setIcon(new ImageIcon(client.class.getResource("/img/next.png")));
		p_sidebar_user4.add(lbl_sidebar_next4);
	}

	private static void conn_receive() {		// 서버 접속 및 데이터 수신
		// 서버로 부터 데이터 수신 시작 ============================================================================
		try {
			socket = new Socket(ip, 9999);																	// 소켓 접속 정보 저장
			oos = new ObjectOutputStream(socket.getOutputStream());											//	객체 발신체에 소켓 정보 저장
			ois = new ObjectInputStream(socket.getInputStream());												//	객체 수신체에 소켓 정보 저장			
			
			System.out.println("[서버 연결]" + socket.getRemoteSocketAddress().toString()); 				// 서버 접속 정보 출력
			ExecutorService receiver = Executors.newCachedThreadPool(); 										// 메시지 수신 스레드 풀
			receiver.execute(() -> {		 																					// 메세지 수신 스레드 시작=============
				try {
					while (true) {
						dto = (DTO)ois.readObject();																		// 메세지 수신 블로킹 (수신 오는 즉시 아래 코드 진행)
						code = dto.getCode();																				// 수신된 코드 확인
						System.out.println("▒▒▒ 서버로 부터 리시브 성공 ▒▒▒"+dto.getCode());
						switch(code) {																							// 수신된 코드에 따른 동작 시작==========
						case "T0000" :
							// [T0000] 로그인 기능 추가
							// https://github.com/Hx2DEV/marble/issues/26
							// 작업자 이상원
							// 작업자 전호형			
							switch(dto.getIntnote()) {
								case 1:
									dto.setId(tf_login_id.getText());						// dto에 ID 저장
									id = tf_login_id.getText();							// 클라이언트 전역에 ID저장
									T0003.c_T0003_send(dto, oos);					// 방 목록 갱신 기능
									p_allHidden();
									p_lobby.setVisible(true);
									System.out.println("[클라] [T0000] 로그인 기능 완료");
									break;
								case 2:	JOptionPane.showMessageDialog(contentPane, "아이디, 패스워드를 입력해주세요.");			break;
								case 3:	JOptionPane.showMessageDialog(contentPane, "로그인 에러 발생 다시 시도해 주세요.");	break;
								case 4:	JOptionPane.showMessageDialog(contentPane, "아이디, 패스워드를 확인해 주세요.");		break;
							}							
							break;	
						
							
						case "T0003" :
							// [T0003] 방 목록 갱신 기능 추가
							// https://github.com/Hx2DEV/marble/issues/3
							// 작업자 민지홍
							// 수정자 전호형
							T0003.c_T0003_recv(dto, HMroom_stats, HMroom_title, HMroom_ICON, HMlobby_room);
							break;
						
						
						case "T0004" :
							// [T0004] 방 생성 기능 추가
							// https://github.com/Hx2DEV/marble/issues/6
							// 작업자 조동현
							// 수정자 전호형
							roomNumber = T0004.c_T0004_recv(dto, contentPane, lbl_sidebar_title, lbl_sidebar_location, HMsidebar_p, HMsidebar_user);
							if(dto.getIntnote()!=0) {		// 방 생성에 성공했다면
								//roomNumber = Integer.parseInt(dto.getRoomNumber());		// 클라에 방번호 저장 
								p_allHidden();																// 모든 패널 숨기기
								p_playroom.setVisible(true);											// 게임방 보이기
								btn_pr_start.setVisible(true);
								playerNum=0;																// 플레이어 번호 저장
							}
							break;
							
							
						case "T0006" :
							// [T0006] 방 참가 기능 추가
							// https://github.com/Hx2DEV/marble/issues/22
							// 작업자 조동현
							// 수정자 전호형
							roomNumber = T0006.c_T0006_recv(dto, contentPane, HMsidebar_p, HMsidebar_user, lbl_sidebar_title, lbl_sidebar_location);		
							if(dto.getIntnote()!=0) {		// 방 참가에 성공했다면
								p_allHidden();									// 패널 다 숨기기
								p_playroom.setVisible(true);				// 게임방 노출
							}
							break;
								
						case "T0008_1" :
							// [T0008] 지역 구매 기능 추가 
							// https://github.com/Hx2DEV/marble/issues/8
							// 양은지
							// 전호형
							T0008_1.c_T0008_1_recv(dto, p_playroom, oos, id, rotation_sw, HMsidebar_user, lbl_pr_msg);
							break;
							
						case "T0008_2" :
							// [T0008] 지역 구매 기능 추가 
							// https://github.com/Hx2DEV/marble/issues/8
							// 양은지
							// 전호형
							T0008_2.c_T0008_2_recv(dto, p_playroom, lbl_pr_msg, id, HMp_localicon, HMsidebar_p, roomNumber, oos, rotation_sw);
							break;
							
							
						case "T0009" :
							// [T0009] 게임 시작 기능 추가
							// https://github.com/Hx2DEV/marble/issues/7
							 // 작업자 민지홍, 전호형
							dto.setId(id);
							T0009.c_T0009_recv(dto, HMsidebar_p, btn_pr_start, HMp_icon, contentPane, lbl_pr_title, oos);
							break;
							
						case "T0010" :
							// [T0010] 턴 분배 기능 추가
							// https://github.com/Hx2DEV/marble/issues/9
							// 작업자 전호형
							T0010.c_T0010_recv(dto, btn_pr_dice, HMsidebar_user, id, lbl_pr_wait, HMsidebar_p);
							break;
						
						case "T0012" :
							// [T0012] 통행료 지불 기능 추가
							// https://github.com/Hx2DEV/marble/issues/19
							// 작업자 양은지
							// 수정자 전호형
							T0012.c_t0012_recv(dto, HMsidebar_p, lbl_pr_msg, id, rotation_sw, oos, roomNumber);
							break;
										
						case "T0013" :
							// [T0013] 게임 승/패 처리 기능 추가
							// https://github.com/Hx2DEV/marble/issues/20
							// 작업자 전호형
							T0013.c_T0013_recv(dto, btn_pr_dice, HMfinish_money, HMfinish, p_finish, lbl_pr_bg_end);
							break;
								
						case "T0015" :
							// [T0015] 주사위 굴리기 기능 추가
							// https://github.com/Hx2DEV/marble/issues/2
							// 작업자 조동현
							// 수정자 전호형
							dto = T0015.c_t0015_recv(dto, HMp_icon, lbl_pr_dice1, lbl_pr_dice2, oos, id);
							lbl_pr_yourturn.setVisible(false);
							lbl_pr_wait.setVisible(true);
							if ( dto.getIntnote() == 10 ) {	// 한바퀴 돌았다면
								rotation_sw = 1;				// 로테이션 스위치 1
								dto.setIntnote(1);				// 체크 요소로 사용한 더미 값 초기화
							}
							
							// [T0021] 좌표에따른 TASK 동작 기능 추가
							// https://github.com/Hx2DEV/marble/issues/29
							// 작업자 양은지
							// 수정자 전호형
							if(dto.getId().equals(id)) {
								rotation_sw = T0021.c_T0021_send(dto, oos, roomNumber, rotation_sw);
							}
							break;
							
							
						case "T0017" :
							// [T0017] 출발점 통과시 월급 지급 기능 추가
							// https://github.com/Hx2DEV/marble/issues/24
							// 작업자 양은지
							// 수정자 전호형
							T0017.c_T0017_recv(dto, oos, HMsidebar_p, lbl_pr_msg, id, rotation_sw);
							dto.setIntnote(1);
							rotation_sw=0;
							break;
							
							
						case "T0018" :
							// [T0018] 회원가입 기능 추가
							// https://github.com/Hx2DEV/marble/issues/27
							// 작업자 이상원 
							// 수정자 전호형
							T0018.c_T0018_recv(dto, oos, p_login, p_signup);
							p_allHidden();
							p_login.setVisible(true);
							break;
							
							
						case "T0020" :
							// [T0020] 황금열쇠 기능 추가
							// https://github.com/Hx2DEV/marble/issues/28
							// 작업자 양은지
							// 수정자 전호형
							T0020.c_T0020_recv(dto, oos, id, lbl_pr_msg, lbl_pr_donate, HMsidebar_p, rotation_sw, roomNumber);
							break;
							
							
						case "T0023" :
							// [T0023] 수령처에서 적립된 금액 수령 기능 추가
							// https://github.com/Hx2DEV/marble/issues/31
							// 작업자 조동현
							// 수정자 전호형
							T0023.c_T0023_recv(dto, oos, id, roomNumber, HMsidebar_p, lbl_pr_msg, rotation_sw, lbl_pr_donate);
							break;
						
							
						case "T0024" :
							// [T0024] 무인도 기능 추가
							// https://github.com/Hx2DEV/marble/issues/32
							// 작업자 이인호
							// 수정자 전호형
							mooindo = 3;
							T0024.c_T0024_recv(dto, oos , roomNumber, lbl_pr_mooindo, lbl_pr_msg, rotation_sw);
							break;
						}																											// 수신된 코드에 따른 동작 종료=========
					}
				}catch (Throwable e) {
					e.printStackTrace();
				}
			});																														// 메세지 수신 스레드 종료=============
			//while(true) {	}																									// Main 종료에 따른 소켓 닫힘 방지용 무한반복
		}catch (Exception e) {		// 접속 실패 시 접속 종료
			JOptionPane.showMessageDialog(contentPane, "접속에 실패하였습니다. 다시 실행해주세요.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);
			e.printStackTrace();
			//dispose();
			System.exit(0);
		}
		// 서버로 부터 데이터 수신 종료 =================================================
	}
	
	
	
	private static void p_allHidden() {		// contentPane all hideen
		p_login.setVisible(false);
		p_signup.setVisible(false);
		p_lobby.setVisible(false);
		p_playroom.setVisible(false);
	}
	
	private static void guiHMput() {		// HashMap Put. 동적변수 사용
		HMroom_stats.put(1, lbl_room1_stats); HMroom_stats.put(2, lbl_room2_stats); HMroom_stats.put(3, lbl_room3_stats); HMroom_stats.put(4, lbl_room4_stats); HMroom_stats.put(5, lbl_room5_stats);
		HMroom_stats.put(6, lbl_room6_stats); HMroom_stats.put(7, lbl_room7_stats); HMroom_stats.put(8, lbl_room8_stats); HMroom_stats.put(9, lbl_room9_stats); HMroom_stats.put(10, lbl_room10_stats);

		HMroom_title.put(1, lbl_room1_title); HMroom_title.put(2, lbl_room2_title); HMroom_title.put(3, lbl_room3_title); HMroom_title.put(4, lbl_room4_title); HMroom_title.put(5, lbl_room5_title); 
		HMroom_title.put(6, lbl_room6_title); HMroom_title.put(7, lbl_room7_title); HMroom_title.put(8, lbl_room8_title); HMroom_title.put(9, lbl_room9_title); HMroom_title.put(10, lbl_room10_title); 
			
		HMroom_ICON.put(11, lbl_room1_ICON_1p); HMroom_ICON.put(21, lbl_room2_ICON_1p); HMroom_ICON.put(31, lbl_room3_ICON_1p); HMroom_ICON.put(41, lbl_room4_ICON_1p); HMroom_ICON.put(51, lbl_room5_ICON_1p); 
		HMroom_ICON.put(61, lbl_room6_ICON_1p); HMroom_ICON.put(71, lbl_room7_ICON_1p); HMroom_ICON.put(81, lbl_room8_ICON_1p); HMroom_ICON.put(91, lbl_room9_ICON_1p); HMroom_ICON.put(101, lbl_room10_ICON_1p); 
		HMroom_ICON.put(12, lbl_room1_ICON_2p); HMroom_ICON.put(22, lbl_room2_ICON_2p); HMroom_ICON.put(32, lbl_room3_ICON_2p); HMroom_ICON.put(42, lbl_room4_ICON_2p); HMroom_ICON.put(52, lbl_room5_ICON_2p); 
		HMroom_ICON.put(62, lbl_room6_ICON_2p); HMroom_ICON.put(72, lbl_room7_ICON_2p); HMroom_ICON.put(82, lbl_room8_ICON_2p); HMroom_ICON.put(92, lbl_room9_ICON_2p); HMroom_ICON.put(102, lbl_room10_ICON_2p);
		HMroom_ICON.put(13, lbl_room1_ICON_3p); HMroom_ICON.put(23, lbl_room2_ICON_3p); HMroom_ICON.put(33, lbl_room3_ICON_3p); HMroom_ICON.put(43, lbl_room4_ICON_3p); HMroom_ICON.put(53, lbl_room5_ICON_3p); 
		HMroom_ICON.put(63, lbl_room6_ICON_3p); HMroom_ICON.put(73, lbl_room7_ICON_3p); HMroom_ICON.put(83, lbl_room8_ICON_3p); HMroom_ICON.put(93, lbl_room9_ICON_3p); HMroom_ICON.put(103, lbl_room10_ICON_3p);
		HMroom_ICON.put(14, lbl_room1_ICON_4p); HMroom_ICON.put(24, lbl_room2_ICON_4p); HMroom_ICON.put(34, lbl_room3_ICON_4p); HMroom_ICON.put(44, lbl_room4_ICON_4p); HMroom_ICON.put(54, lbl_room5_ICON_4p); 
		HMroom_ICON.put(64, lbl_room6_ICON_4p); HMroom_ICON.put(74, lbl_room7_ICON_4p); HMroom_ICON.put(84, lbl_room8_ICON_4p); HMroom_ICON.put(94, lbl_room9_ICON_4p); HMroom_ICON.put(104, lbl_room10_ICON_4p);
		
		HMlobby_room.put(1, p_lobby_room1); HMlobby_room.put(2, p_lobby_room2); HMlobby_room.put(3, p_lobby_room3); HMlobby_room.put(4, p_lobby_room4); HMlobby_room.put(5, p_lobby_room5); 
		HMlobby_room.put(6, p_lobby_room6); HMlobby_room.put(7, p_lobby_room7); HMlobby_room.put(8, p_lobby_room8); HMlobby_room.put(9, p_lobby_room9); HMlobby_room.put(10, p_lobby_room10);
		
		HMsidebar_user.put(1, p_sidebar_user1); HMsidebar_user.put(2, p_sidebar_user2); HMsidebar_user.put(3, p_sidebar_user3); HMsidebar_user.put(4, p_sidebar_user4);
		
		HMsidebar_p.put(1, lbl_sidebar_picon1); HMsidebar_p.put(2, lbl_sidebar_picon2); HMsidebar_p.put(3, lbl_sidebar_picon3); HMsidebar_p.put(4, lbl_sidebar_picon4);
		HMsidebar_p.put(11, lbl_sidebar_pname1); HMsidebar_p.put(12, lbl_sidebar_pname2); HMsidebar_p.put(13, lbl_sidebar_pname3); HMsidebar_p.put(14, lbl_sidebar_pname4);
		HMsidebar_p.put(21, lbl_sidebar_pmoney1); HMsidebar_p.put(22, lbl_sidebar_pmoney2); HMsidebar_p.put(23, lbl_sidebar_pmoney3); HMsidebar_p.put(24, lbl_sidebar_pmoney4);
		HMsidebar_p.put(31, lbl_sidebar_next1); HMsidebar_p.put(32, lbl_sidebar_next2); HMsidebar_p.put(33, lbl_sidebar_next3); HMsidebar_p.put(34, lbl_sidebar_next4);
		
		
		HMp_icon.put(100, lbl_pr_local0_user1); HMp_icon.put(200, lbl_pr_local0_user2); HMp_icon.put(300, lbl_pr_local0_user3); HMp_icon.put(400, lbl_pr_local0_user4);
		HMp_icon.put(101, lbl_pr_local1_user1); HMp_icon.put(201, lbl_pr_local1_user2); HMp_icon.put(301, lbl_pr_local1_user3); HMp_icon.put(401, lbl_pr_local1_user4);
		HMp_icon.put(102, lbl_pr_local2_user1); HMp_icon.put(202, lbl_pr_local2_user2); HMp_icon.put(302, lbl_pr_local2_user3); HMp_icon.put(402, lbl_pr_local2_user4);
		HMp_icon.put(103, lbl_pr_local3_user1); HMp_icon.put(203, lbl_pr_local3_user2); HMp_icon.put(303, lbl_pr_local3_user3); HMp_icon.put(403, lbl_pr_local3_user4);
		HMp_icon.put(104, lbl_pr_local4_user1); HMp_icon.put(204, lbl_pr_local4_user2); HMp_icon.put(304, lbl_pr_local4_user3); HMp_icon.put(404, lbl_pr_local4_user4);
		HMp_icon.put(105, lbl_pr_local5_user1); HMp_icon.put(205, lbl_pr_local5_user2); HMp_icon.put(305, lbl_pr_local5_user3); HMp_icon.put(405, lbl_pr_local5_user4);
		HMp_icon.put(106, lbl_pr_local6_user1); HMp_icon.put(206, lbl_pr_local6_user2); HMp_icon.put(306, lbl_pr_local6_user3); HMp_icon.put(406, lbl_pr_local6_user4);
		HMp_icon.put(107, lbl_pr_local7_user1); HMp_icon.put(207, lbl_pr_local7_user2); HMp_icon.put(307, lbl_pr_local7_user3); HMp_icon.put(407, lbl_pr_local7_user4);
		HMp_icon.put(108, lbl_pr_local8_user1); HMp_icon.put(208, lbl_pr_local8_user2); HMp_icon.put(308, lbl_pr_local8_user3); HMp_icon.put(408, lbl_pr_local8_user4);
		HMp_icon.put(109, lbl_pr_local9_user1); HMp_icon.put(209, lbl_pr_local9_user2); HMp_icon.put(309, lbl_pr_local9_user3); HMp_icon.put(409, lbl_pr_local9_user4);
		HMp_icon.put(110, lbl_pr_local10_user1); HMp_icon.put(210, lbl_pr_local10_user2); HMp_icon.put(310, lbl_pr_local10_user3); HMp_icon.put(410, lbl_pr_local10_user4);
		HMp_icon.put(111, lbl_pr_local11_user1); HMp_icon.put(211, lbl_pr_local11_user2); HMp_icon.put(311, lbl_pr_local11_user3); HMp_icon.put(411, lbl_pr_local11_user4);
		HMp_icon.put(112, lbl_pr_local12_user1); HMp_icon.put(212, lbl_pr_local12_user2); HMp_icon.put(312, lbl_pr_local12_user3); HMp_icon.put(412, lbl_pr_local12_user4);
		HMp_icon.put(113, lbl_pr_local13_user1); HMp_icon.put(213, lbl_pr_local13_user2); HMp_icon.put(313, lbl_pr_local13_user3); HMp_icon.put(413, lbl_pr_local13_user4);
		HMp_icon.put(114, lbl_pr_local14_user1); HMp_icon.put(214, lbl_pr_local14_user2); HMp_icon.put(314, lbl_pr_local14_user3); HMp_icon.put(414, lbl_pr_local14_user4);
		HMp_icon.put(115, lbl_pr_local15_user1); HMp_icon.put(215, lbl_pr_local15_user2); HMp_icon.put(315, lbl_pr_local15_user3); HMp_icon.put(415, lbl_pr_local15_user4);
		HMp_icon.put(116, lbl_pr_local16_user1); HMp_icon.put(216, lbl_pr_local16_user2); HMp_icon.put(316, lbl_pr_local16_user3); HMp_icon.put(416, lbl_pr_local16_user4);
		HMp_icon.put(117, lbl_pr_local17_user1); HMp_icon.put(217, lbl_pr_local17_user2); HMp_icon.put(317, lbl_pr_local17_user3); HMp_icon.put(417, lbl_pr_local17_user4);
		HMp_icon.put(118, lbl_pr_local18_user1); HMp_icon.put(218, lbl_pr_local18_user2); HMp_icon.put(318, lbl_pr_local18_user3); HMp_icon.put(418, lbl_pr_local18_user4);
		HMp_icon.put(119, lbl_pr_local19_user1); HMp_icon.put(219, lbl_pr_local19_user2); HMp_icon.put(319, lbl_pr_local19_user3); HMp_icon.put(419, lbl_pr_local19_user4);
		HMp_icon.put(120, lbl_pr_local20_user1); HMp_icon.put(220, lbl_pr_local20_user2); HMp_icon.put(320, lbl_pr_local20_user3); HMp_icon.put(420, lbl_pr_local20_user4);
		HMp_icon.put(121, lbl_pr_local21_user1); HMp_icon.put(221, lbl_pr_local21_user2); HMp_icon.put(321, lbl_pr_local21_user3); HMp_icon.put(421, lbl_pr_local21_user4);
		HMp_icon.put(122, lbl_pr_local22_user1); HMp_icon.put(222, lbl_pr_local22_user2); HMp_icon.put(322, lbl_pr_local22_user3); HMp_icon.put(422, lbl_pr_local22_user4);
		HMp_icon.put(123, lbl_pr_local23_user1); HMp_icon.put(223, lbl_pr_local23_user2); HMp_icon.put(323, lbl_pr_local23_user3); HMp_icon.put(423, lbl_pr_local23_user4);
		HMp_icon.put(124, lbl_pr_local24_user1); HMp_icon.put(224, lbl_pr_local24_user2); HMp_icon.put(324, lbl_pr_local24_user3); HMp_icon.put(424, lbl_pr_local24_user4);
		HMp_icon.put(125, lbl_pr_local25_user1); HMp_icon.put(225, lbl_pr_local25_user2); HMp_icon.put(325, lbl_pr_local25_user3); HMp_icon.put(425, lbl_pr_local25_user4);
		HMp_icon.put(126, lbl_pr_local26_user1); HMp_icon.put(226, lbl_pr_local26_user2); HMp_icon.put(326, lbl_pr_local26_user3); HMp_icon.put(426, lbl_pr_local26_user4);
		HMp_icon.put(127, lbl_pr_local27_user1); HMp_icon.put(227, lbl_pr_local27_user2); HMp_icon.put(327, lbl_pr_local27_user3); HMp_icon.put(427, lbl_pr_local27_user4);
		HMp_icon.put(128, lbl_pr_local28_user1); HMp_icon.put(228, lbl_pr_local28_user2); HMp_icon.put(328, lbl_pr_local28_user3); HMp_icon.put(428, lbl_pr_local28_user4);
		HMp_icon.put(129, lbl_pr_local29_user1); HMp_icon.put(229, lbl_pr_local29_user2); HMp_icon.put(329, lbl_pr_local29_user3); HMp_icon.put(429, lbl_pr_local29_user4);
		HMp_icon.put(130, lbl_pr_local30_user1); HMp_icon.put(230, lbl_pr_local30_user2); HMp_icon.put(330, lbl_pr_local30_user3); HMp_icon.put(430, lbl_pr_local30_user4);
		HMp_icon.put(131, lbl_pr_local31_user1); HMp_icon.put(231, lbl_pr_local31_user2); HMp_icon.put(331, lbl_pr_local31_user3); HMp_icon.put(431, lbl_pr_local31_user4);

		HMp_localicon.put(1, lbl_pr_local1_build); 			HMp_localicon.put(2, lbl_pr_local2_build);			HMp_localicon.put(3, lbl_pr_local3_build);
		HMp_localicon.put(5, lbl_pr_local5_build);			HMp_localicon.put(6, lbl_pr_local6_build);			HMp_localicon.put(7, lbl_pr_local7_build); 
		HMp_localicon.put(9, lbl_pr_local9_build); 			HMp_localicon.put(10, lbl_pr_local10_build);		HMp_localicon.put(11, lbl_pr_local11_build); 
		HMp_localicon.put(13, lbl_pr_local13_build);		HMp_localicon.put(14, lbl_pr_local14_build);		HMp_localicon.put(15, lbl_pr_local15_build); 
		HMp_localicon.put(17, lbl_pr_local17_build);		HMp_localicon.put(18, lbl_pr_local18_build);		HMp_localicon.put(19, lbl_pr_local19_build); 
		HMp_localicon.put(21, lbl_pr_local21_build);		HMp_localicon.put(22, lbl_pr_local22_build);		HMp_localicon.put(23, lbl_pr_local23_build); 
		HMp_localicon.put(25, lbl_pr_local25_build);		HMp_localicon.put(26, lbl_pr_local26_build);		HMp_localicon.put(27, lbl_pr_local27_build); 
		HMp_localicon.put(29, lbl_pr_local29_build);		HMp_localicon.put(30, lbl_pr_local30_build);		HMp_localicon.put(31, lbl_pr_local31_build);
		
		HMfinish.put(1, p_finish_p1); HMfinish.put(2, p_finish_p2); HMfinish.put(3, p_finish_p3); HMfinish.put(4, p_finish_p4);
		
		HMfinish_money.put(1, lbl_finish_p1); HMfinish_money.put(2, lbl_finish_p2); HMfinish_money.put(3, lbl_finish_p3); HMfinish_money.put(4, lbl_finish_p4);
		HMfinish_money.put(11, lbl_finish_p1_money); HMfinish_money.put(12, lbl_finish_p2_money); HMfinish_money.put(13, lbl_finish_p3_money); HMfinish_money.put(14, lbl_finish_p4_money);
		HMfinish_money.put(21, lbl_finish_win1); HMfinish_money.put(22, lbl_finish_win2); HMfinish_money.put(23, lbl_finish_win3); HMfinish_money.put(24, lbl_finish_win4);

		HashMap<Integer, String> malicon = new HashMap<>();
		malicon.put(1, "/img/icon_mal_p1.png");
		malicon.put(2, "/img/icon_mal_p2.png");
		malicon.put(3, "/img/icon_mal_p3.png");
		malicon.put(4, "/img/icon_mal_p4.png");
		
		for(int i = 0; i < 32 ; i++) {
			for (int j = 1; j < 5; j++) {
				HMp_icon.get((j*100)+i).setIcon(new ImageIcon(client.class.getResource(malicon.get(j))));
			}
		}
	}
}

// [T0004] 방 생성 기능 추가
// https://github.com/Hx2DEV/marble/issues/6
// 작업자 조동현
// 수정자 전호형


package client;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.DTO;

public class c_T0004 {
	
	public int c_T0004_recv(DTO DTO, JPanel contentPane, JLabel lbl_sidebar_title, JLabel lbl_sidebar_location, HashMap<Integer, JLabel> hMsidebar_p, HashMap<Integer, JPanel> hMsidebar_user)  {
		
		// 룸인포 String -> 배열[]화 시작
		String tmp = DTO.getRoomname();
		String[] roomname = tmp.split("/");
		
		// 게임인포 String -> 배열[][][] 화 시작
		String[] tmp_gameinfo = DTO.getGameinfo().split("/");
		String[][][] gi = new String[10][7][4];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gi[i][j][k] = tmp_gameinfo[cnt];
					cnt ++;
				}
			}
		}
		
		int RN	= Integer.parseInt(DTO.getRoomNumber())-1;			// 방번호 임시 저장
		
		HashMap<Integer, String> HMicon_s = new HashMap<>();		// 동적 변수 생성
		HMicon_s.put(1, "/img/icon_1pready_s.png");
		HMicon_s.put(2, "/img/icon_2pready_s.png");
		HMicon_s.put(3, "/img/icon_3pready_s.png");
		HMicon_s.put(4, "/img/icon_4pready_s.png");
		
		if(DTO.getIntnote()==0) {	// 방이 모두 차서 생성 못했다면
			JOptionPane.showMessageDialog(contentPane, "더 이상 방을 만들 수 없습니다.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);	
		} else {							// 방 생성에 성공했다면
			
			hMsidebar_user.get(1).setVisible(true);
			hMsidebar_user.get(2).setVisible(true);
			hMsidebar_user.get(3).setVisible(true);
			hMsidebar_user.get(4).setVisible(true);
			
			lbl_sidebar_location.setText(String.valueOf(RN+1)+" 번방");
			lbl_sidebar_title.setText(roomname[RN]);
			
			for (int i = 0; i < 4; i++) {
				if(!gi[RN][0][i].equals("null")) {		// i 번째 플레이어가 있다면 
					hMsidebar_p.get(i+1).setIcon(new ImageIcon(client.class.getResource(HMicon_s.get(i+1))));				//	i번째 플레이어 아이콘 변경
					hMsidebar_p.get(i+11).setText(gi[RN][0][i]);																			//	플레이어 아이디 변경

				} else {												// i 번째 플레이어가 없다면
					hMsidebar_p.get(i+1).setIcon(new ImageIcon(client.class.getResource("/img/icon_unready_s.png")));	//	i번째 플레이어 아이콘 변경
					hMsidebar_p.get(i+11).setText("");																			//	플레이어 아이디 변경
				}
			}
			
			
			System.out.println("[클라] [T0004] "+(RN)+"번째 방 생성 성공");
		}
		System.out.println("[클라] [T0004] 방 생성 기능 완료");
		return RN;
	};
	
	public void c_T0004_send(DTO DTO, ObjectOutputStream oos, JPanel contentPane ) {
		try {
			System.out.println("[클라] [T0004] 방 생성 기능 시작");
			
			String rname ="";
			int random = (int)(Math.random() * 3) + 1;
			switch (random) {
				case 1:  rname = "K부동산 게임 우리 마블";
				case 2: rname = "[!!특보!!] 땅 투기 현장";
					break;
				case 3: rname = "플젝 개어렵고 힘들다";
					break;
			}
			
			String title = JOptionPane.showInputDialog(contentPane, "방 이름을 입력해주세요.", rname);
		
			if(title==null || title.equals("")) {																					// 입력을 취소하거나 값이 없다면.
				JOptionPane.showMessageDialog(contentPane, "방 생성이 취소되었습니다.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);							// 취소 메세지와, 진행 중지
				System.out.println("[클라] [T0004] 방 생성 기능 취소됨");
			}else if (title!=null){																									// 입력 값이 있다면.
				DTO.setNote(title);																									// 입력 값 toss에 저장
				DTO.setCode("T0004");																							// 코드 값 toss에 저장
		        oos.writeObject(DTO);	 																							// toss 발송
		        oos.flush(); 																											// OOS 비우기
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

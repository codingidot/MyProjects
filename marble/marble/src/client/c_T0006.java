// [T0006] 방 참가 기능 추가
// https://github.com/Hx2DEV/marble/issues/22
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

public class c_T0006 {
	
	public int c_T0006_recv(DTO DTO, JPanel contentPane, HashMap<Integer, JLabel> hMsidebar_p, HashMap<Integer, JPanel> hMsidebar_user, JLabel lbl_sidebar_title, JLabel lbl_sidebar_location) {
	
		int RN	= Integer.parseInt(DTO.getRoomNumber())-1;			// 방번호 임시 저장

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
			
		HashMap<Integer, String> HMicon_s = new HashMap<>();		// 동적 변수 생성
		HMicon_s.put(1, "/img/icon_1pready_s.png");
		HMicon_s.put(2, "/img/icon_2pready_s.png");
		HMicon_s.put(3, "/img/icon_3pready_s.png");
		HMicon_s.put(4, "/img/icon_4pready_s.png");
		
		// 데이터 처리 시작
		if(DTO.getIntnote()==0) {	// 방 참가 실패한 경우
			JOptionPane.showMessageDialog(null, "참여할 자리가 없습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE, null);
			
		} else {							// 방 참가 성공한 경우
			
			hMsidebar_user.get(1).setVisible(true);
			hMsidebar_user.get(2).setVisible(true);
			hMsidebar_user.get(3).setVisible(true);
			hMsidebar_user.get(4).setVisible(true);
			
			
			lbl_sidebar_location.setText((RN+1)+" 번방");								// 사이드바 방 정보 변경
			lbl_sidebar_title.setText(DTO.getNote());										// 사이드바 방 정보 변경
			
			for (int i = 0; i < 4; i++) {
				
				if(!gi[RN][0][i].equals("null")) {		// i 번째 플레이어가 있다면 
					hMsidebar_p.get(i+1).setIcon(new ImageIcon(client.class.getResource(HMicon_s.get(i+1))));				//	i번째 플레이어 아이콘 변경
					hMsidebar_p.get(i+11).setText(gi[RN][0][i]);																			//	플레이어 아이디 변경

				} else {												// i 번째 플레이어가 없다면
					hMsidebar_p.get(i+1).setIcon(new ImageIcon(client.class.getResource("/img/icon_unready_s.png")));	//	i번째 플레이어 아이콘 변경
					hMsidebar_p.get(i+11).setText("");																			//	플레이어 아이디 변경
				}
			}			
		}
		System.out.println("[클라] [T0006] 방 참가 기능 완료");
		return RN;
	}

	public void c_T0006_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0006] 방 참가 기능 시작");
				
			DTO.setCode("T0006");		// 코드 저장
			oos.writeObject(DTO);	 	// DTO 발송
			oos.flush(); 						// OOS 비우기						
       
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

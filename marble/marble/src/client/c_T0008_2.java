// [T0008] 지역 구매 기능 추가 
// https://github.com/Hx2DEV/marble/issues/8
// 양은지
// 전호형

package client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DTO;

public class c_T0008_2 {
	
	c_T0010 T0010 = new c_T0010();
	c_T0017 T0017 = new c_T0017();

	public void c_T0008_2_recv(DTO DTO, JPanel p_playroom, JLabel lbl_pr_msg, String id, HashMap<Integer, JLabel> HMp_localicon, HashMap<Integer, JLabel> HMsidebar_p, int roomNumber, ObjectOutputStream oos, int rotation_sw) {
		
		// 지역 이름 셋팅
		String[] localname = {"","방콕","독도","베이징","","타이페이","두바이","카이로","","발리","도쿄","시드니","","퀘백","하와이","상파울로","",
									"프라하","푸켓","베를린","","모스크바","제네바","로마","","타히티","런던","파리","","뉴욕","부산","서울",""};
		
		int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 저장
		int nowpositon = DTO.getAfterPosition();					// 도달한 장소 정보 저장
		
		// place String -> 배열[][][] 화 시작
		String[] tmp_place = DTO.getPlace().split("/");
		String[][][] place = new String[10][3][32];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 32; k++) {
					place[i][j][k] = tmp_place[cnt];
					cnt ++;
				}
			}
		}
		
		// 게임인포 String -> 배열[][][] 화 시작
		String[] tmp_gameinfo = DTO.getGameinfo().split("/");
		String[][][] gi = new String[10][7][4];
		int cnt2 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gi[i][j][k] = tmp_gameinfo[cnt2];
					cnt2 ++;
				}
			}
		}
		
		
		// 데이터 처리 시작 ====================================
		// 지도 색상칠하기 시작
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 4; j++) {
				if(i%4 != 0) {
					if(!place[RN][0][i].equals("null")) {
						if(place[RN][0][i].equals(gi[RN][0][j])) {
							System.out.println("아이는? "+i);
							switch(j) {
							case 0: HMp_localicon.get(i).setBackground(new Color(218, 0, 57)); break;
							case 1: HMp_localicon.get(i).setBackground(new Color(0, 126, 255)); break;
							case 2: HMp_localicon.get(i).setBackground(new Color(124, 198, 35)); break;
							case 3: HMp_localicon.get(i).setBackground(new Color(203, 205, 62)); break;
							}
							break;
						}
					}
				}
			}
		}
		// 게임머니 갱신 시작
		for (int i = 0; i < 4; i++) {
			if(!gi[RN][0][i].equals("null")){
				HMsidebar_p.get(i+21).setText(gi[RN][2][i]+" 만원");
			}
		}
		
		if (id.equals(DTO.getId())){	// 유저 아이디가 요청했던 ID와 일치한다면	
			
			if(DTO.getNote().equals("구매성공")) {	// 구매에 성공했다면
				
				//JOptionPane.showMessageDialog(p_playroom, "지역 구매에 성공하였습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE);
				lbl_pr_msg.setText(localname[nowpositon]+" 지역을 구매하셨습니다.");
				lbl_pr_msg.setVisible(true);
				
			} else {
				
				//JOptionPane.showMessageDialog(p_playroom, "보유 금액이 부족합니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE);
				lbl_pr_msg.setText("보유 금액이 부족하여 구매에 실패하였습니다.");
				lbl_pr_msg.setVisible(true);
				
			}
						
		} else {		// 대기중인 플레이어 라면
			
			if(DTO.getNote().equals("구매성공")) {
				lbl_pr_msg.setText(DTO.getId()+"님께서 "+localname[nowpositon]+" 지역을 구매하셨습니다.");
				lbl_pr_msg.setVisible(true);
			}
			
		}
		System.out.println("[클라] [T0008] 지역 구매 기능2 완료 ");	
		
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!로테이션스위치는 "+rotation_sw);
		if(id.equals(DTO.getId())) {
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
			} else {
				T0010.c_T0010_send(DTO, oos, roomNumber);		// 턴 분배 기능 시작
			}
		}
	}
	

	public void c_T0008_2_send(DTO DTO, ObjectOutputStream oos ) {
		
		System.out.println("[클라] [T0008] 지역 구매 기능2 시작 ");
		DTO.setCode("T0008_2");										// 코드 저장
		try {
			oos.writeObject(DTO);										// 코드 발송
			oos.flush();														// OOS 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

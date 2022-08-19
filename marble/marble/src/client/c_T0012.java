// [T0012] 통행료 지불 기능 추가
// https://github.com/Hx2DEV/marble/issues/19
// 작업자 양은지
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DTO;

public class c_T0012 {
	
	c_T0010 T0010 = new c_T0010();
	c_T0013 T0013 = new c_T0013();
	c_T0017 T0017 = new c_T0017();
	
	public void c_t0012_recv(DTO DTO, HashMap<Integer, JLabel> HMsidebar_p, JLabel lbl_pr_msg, String id, int rotation_sw, ObjectOutputStream oos, int roomNumber) {
		
		// 지역 정보 String -> 배열[][][] 화 시작
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
			
		int RN = roomNumber; 													// 요청된 방정보 가져오기
		int nowpositon = DTO.getAfterPosition();							// 요청된 좌표 정보 가져오기
		String owner = place[RN][0][nowpositon];							// 땅 주인 id
		int price = Integer.parseInt(place[RN][2][nowpositon])	;		// 통행료
		
		
		// 게임머니 갱신 시작
		for (int i = 0; i < 4; i++) {
			if(!gi[RN][0][i].equals("null")){
				HMsidebar_p.get(i+21).setText(gi[RN][2][i]+" 만원");
			}
		}

		if (DTO.getIntnote() == 9999)	{		// 돈이 부족해서 파산 코드가 왔다면
			if(id.equals(DTO.getId())) {
				T0013.c_T0013_send(DTO, oos);		// [T0013] 게임 승/패 처리 기능 
				System.out.println("[클라] [T0012] 통행료 지불 기능 완료 ");
			}
		} else {

			if (!DTO.getId().equals(owner)) {		// 이동한 유저와 주인이 같지 않다면
			lbl_pr_msg.setText(DTO.getId()+"님이 "+owner+"님께 통행료 "+price+"만원 지불하였습니다.");
			lbl_pr_msg.setVisible(true);
			}
			
			if(id.equals(DTO.getId())) {		
				if (rotation_sw == 1) {
					T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				} else {
					T0010.c_T0010_send(DTO, oos, roomNumber);		// 턴 분배 기능 시작
				}
			}
			System.out.println("[클라] [T0012] 통행료 지불 기능 완료 ");
		}

	}

	public void c_T0012_send(DTO DTO, ObjectOutputStream oos, int roomNumber) {
		System.out.println("[T0012] 통행료 지불 기능 시작");
		
		DTO.setCode("T0012");														// 코드저장
		DTO.setRoomNumber(String.valueOf(roomNumber));				// 룸번호 저장 (+1)
		
		try {
			oos.writeObject(DTO);													// DTO 발송
			oos.flush();																	// OOS 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

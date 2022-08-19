// [T0023] 수령처에서 적립된 금액 수령 기능 추가
// https://github.com/Hx2DEV/marble/issues/31
// 작업자 조동현
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;

import data.DTO;

public class c_T0023 {
	
	c_T0010 T0010 = new c_T0010();
	c_T0017 T0017 = new c_T0017();
	
	public void c_T0023_recv(DTO DTO, ObjectOutputStream oos, String id, int roomNumber, HashMap<Integer, JLabel> HMsidebar_p, JLabel lbl_pr_msg, int rotation_sw, JLabel lbl_pr_donate) {
		
		String call_id = DTO.getId();														// 요청자 ID
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int tmp_donate = DTO.getTmp_donate();										// 획득한 금액 
		
		// 기부금 String -> 배열[]화 시작
		String tmp = DTO.getDonate();
		String[] donate = tmp.split("/");
		
		
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
	
		// 데이터 처리 시작 =========================================
		
		// 게임머니 갱신 시작
		for (int i = 0; i < 4; i++) {
			if(!gi[RN][0][i].equals("null")){
				HMsidebar_p.get(i+21).setText(gi[RN][2][i]+" 만원");
			}
		}
		
		if( call_id.equals(id) ) { 																						// 요청자와 클라이언트가 일치하면
			
			if (tmp_donate == 0) {																					// 전달 받은 돈이 없다면
				lbl_pr_msg.setText("모여진 돈이 없습니다.");													// 메세지 출력
			} else {																										// 전달 받은 돈이 있다면
				lbl_pr_msg.setText(tmp_donate+"만원을 기부 받았습니다!");								// 메세지 출력
			}
			
		} else { 		
																															// 요청자와 클라이언트가 일치하지 않으면
			if (tmp_donate == 0) {																					// 전달 받은 돈이 없다면
			} else {																										// 전달 받은 돈이 있다면
				lbl_pr_msg.setText(call_id+"님께서 "+tmp_donate+"만원을 기부 받았습니다!");		// 메세지 출력
			}
		}
		
		lbl_pr_donate.setText("");																					// 기부처 메세지 초기화
		
		
		System.out.println("[클라] [T0023] 수령처에서 적립된 금액 수령 기능 완료");
		
		// 요청한 아이디와 클라이언트 아이디가 일치하는 유저가 다음 작업 요청
		if(id.equals(DTO.getId())) {
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); 							// 출발점 통과시 월급 지급 기능 
			} else {
				T0010.c_T0010_send(DTO, oos, roomNumber);		// 턴 분배 기능 시작
			}
		}
	}

	public void c_T0023_send(DTO DTO, ObjectOutputStream oos, int roomNumber) {
		try {
			System.out.println("[클라] [T0023] 수령처에서 적립된 금액 수령 기능 시작");
			DTO.setCode("T0023");
			oos.writeObject(DTO);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

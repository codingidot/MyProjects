// [T0020] 황금열쇠 기능 추가
// https://github.com/Hx2DEV/marble/issues/28
// 작업자 양은지
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;

import data.DTO;

public class c_T0020 {
	
	c_T0010 T0010 = new c_T0010();
	c_T0013 T0013 = new c_T0013();
	c_T0017 T0017 = new c_T0017();
	
	public void c_T0020_recv(DTO DTO, ObjectOutputStream oos, String id, JLabel lbl_pr_msg, JLabel lbl_pr_donate, HashMap<Integer, JLabel> HMsidebar_p, int rotation_sw, int roomNumber) {
		
		String call_id = DTO.getId();														// 요청자 ID
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int gold_result = DTO.getGoldkey();												// 황금열쇠 결과
		
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

		if (DTO.getIntnote() == 9999)	{		// 돈이 부족해서 파산 코드가 왔다면
			T0013.c_T0013_send(DTO, oos);		// [T0013] 게임 승/패 처리 기능 
			System.out.println("[클라] [T0020] 황금열쇠 기능 완료");
			
		} else { // 잔고가 있다면
			if (gold_result > 0) { 	// 열쇠로 돈을 얻었다면
				if (call_id.equals(id)) {		// 요청한 사람과 보는 사람이 같다면
					lbl_pr_msg.setText("대박! 황금열쇠로 "+gold_result+"만원을 획득하셨습니다!");
					lbl_pr_msg.setVisible(true);
				} else {
					lbl_pr_msg.setText("대박! "+call_id+"님께서 황금열쇠로 "+gold_result+"만원을 획득하셨습니다");
					lbl_pr_msg.setVisible(true);
				}
				
			} else {	// 열쇠로 돈을 잃었다면
				if (call_id.equals(id)) {		// 요청한 사람과 보는 사람이 같다면
					lbl_pr_msg.setText("감사합니다! "+gold_result*-1+"만원을 기부하셨습니다!");

				} else {
					lbl_pr_msg.setText(call_id + "님께서 "+gold_result*-1+"만원을 기부하셨습니다!");
				}
				lbl_pr_msg.setVisible(true);
				lbl_pr_donate.setVisible(true);
				lbl_pr_donate.setText("[ 적립금 "+donate[RN]+"만원 ]");
			}
					
			System.out.println("[클라] [T0020] 황금열쇠 기능 완료");
			
			// 요청자와 일치하는 클라이언트만 서버로 요청보내기
			if(id.equals(DTO.getId())) {
				if (rotation_sw == 1) {
					T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				} else {
					T0010.c_T0010_send(DTO, oos, roomNumber);		// 턴 분배 기능 시작
				}
			}
		}
	}

	
	
	
	public void c_T0020_send(DTO DTO, ObjectOutputStream oos, int roomNumber) {
		try {
			System.out.println("[클라] [T0020] 황금열쇠 기능 시작");
			DTO.setCode("T0020");
			oos.writeObject(DTO);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



}

// [T0017] 출발점 통과시 월급 지급 기능 추가
// https://github.com/Hx2DEV/marble/issues/24
// 작업자 양은지
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;

import data.DTO;

public class c_T0017 {
	
	c_T0010 T0010 = new c_T0010();
	
	public void c_T0017_recv(DTO DTO, ObjectOutputStream oos, HashMap<Integer, JLabel> HMsidebar_p, JLabel lbl_pr_msg, String id, int rotation_sw) {
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int playerNum = 0;																	// 플레이어가 몇 P 인지	
		
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
		
		// 게임머니 최신화
		for (int i = 0; i < 4; i++) {
			if(!gi[RN][0][i].equals("null")){
				HMsidebar_p.get(i+21).setText(gi[RN][2][i]+" 만원");		// 게임머니 재설정
			}
		}
		
		if (id.equals(DTO.getId())){
			lbl_pr_msg.setText("출발지를 통과하였습니다. [게임머니 +20 만원]");
			T0010.c_T0010_send(DTO, oos, RN);		// 턴 분배 기능 시작
		}
		System.out.println("[클라] [T0017] 출발점 통과시 월급 지급 기능 완료 ");
	}
		
	
		
	public void c_T0017_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0017] 출발점 통과시 월급 지급 기능 시작 ");
			DTO.setCode("T0017");											// 코드 저장
			oos.writeObject(DTO);										// 코드 발송
			oos.flush();														// OOS 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

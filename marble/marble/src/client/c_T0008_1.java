// [T0008] 지역 구매 기능 추가 
// https://github.com/Hx2DEV/marble/issues/8
// 양은지
// 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.DTO;

public class c_T0008_1 {
	c_T0008_2 T0008_2 = new c_T0008_2();
	c_T0010 T0010 = new c_T0010();
	c_T0012 T0012 = new c_T0012();
	c_T0017 T0017 = new c_T0017();
	
	public void c_T0008_1_recv (DTO DTO, JPanel p_playroom, ObjectOutputStream oos, String id, int rotation_sw, HashMap<Integer, JPanel> HMsidebar_user, JLabel lbl_pr_msg) {
		int roomNumber = Integer.parseInt(DTO.getRoomNumber());	// 방번호 임시저장 
				
		// 요청한 계정과 플레이어가 일치한다면
		if(id.equals(DTO.getId())) {
			
			// 도달한 땅에 주인이 없는 경우
			if(DTO.getNote().equals("주인없음")){
				
				int result = JOptionPane.showConfirmDialog(p_playroom, "해당 지역을 구매 할까요?", "WOORI MARBLE", JOptionPane.YES_NO_OPTION);
				
//				if( result == JOptionPane.CLOSED_OPTION) {		// 입력 창을 닫았다면
//					JOptionPane.showMessageDialog(p_playroom, "지역 구매가 취소되었습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE);
//					//T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
//					if (rotation_sw == 1) {
//						System.out.println("내아이디는 " +id + "요청된 아이디는" +DTO.getId());
//						T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
//					} else{
//						T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
//					}
//				}
				
				if( result == JOptionPane.YES_OPTION) {			// 구매 시
					T0008_2.c_T0008_2_send(DTO, oos);
					
				} else {														// 취소 시
					JOptionPane.showMessageDialog(p_playroom, "지역 구매가 취소되었습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE);
					
					if (rotation_sw == 1) {
						T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
					} else{
						T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
					}
				}
			} else { // 주인이 있다면
				
				// [T0012] 통행료 지불 기능 추가
				// https://github.com/Hx2DEV/marble/issues/19
				// 작업자 양은지
				// 수정자 전호형
				T0012.c_T0012_send(DTO, oos, roomNumber);	
			}
			System.out.println("[클라] [T0008] 지역 구매 기능1 완료 ");
		}
	}
	
	public void c_T0008_1_send(DTO DTO, ObjectOutputStream oos ) {
		
		System.out.println("[클라] [T0008] 지역 구매 기능1 시작 ");
		DTO.setCode("T0008_1");											// 코드 저장
		try {
			oos.writeObject(DTO);										// 코드 발송
			oos.flush();														// OOS 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}

// [T0024] 무인도 기능 추가
// https://github.com/Hx2DEV/marble/issues/32
// 작업자 이인호
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;

import data.DTO;

public class c_T0024 {
	
	c_T0010 T0010 = new c_T0010();
	c_T0017 T0017 = new c_T0017();

	public void c_T0024_recv(DTO DTO, ObjectOutputStream oos, int roomNumber, JLabel lbl_pr_mooindo, JLabel lbl_pr_msg, int rotation_sw) {
		lbl_pr_mooindo.setVisible(true);
		lbl_pr_msg.setText("무인도에 빠졌습니다!");
		System.out.println("[클라] [T0024] 무인도 기능 완료");
		
		if (rotation_sw == 1) {
			T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
		} else{
			T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
		}
	}
	
	public void c_T0024_send(DTO DTO, ObjectOutputStream oos, int roomNumber) {
		try {
			System.out.println("[클라] [T0024] 무인도 기능 시작");
			DTO.setCode("T0024");
			oos.writeObject(DTO);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}

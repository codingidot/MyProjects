// [T0018] 회원가입 기능 추가
// https://github.com/Hx2DEV/marble/issues/27
// 작업자 이상원 
// 수정자 전호형

package client;

import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.DTO;

public class c_T0018 {
	
	public void c_T0018_recv(DTO DTO, ObjectOutputStream oos, JPanel p_login, JPanel p_signup) {	
		switch (DTO.getIntnote()) {		// 처리결과가
		case 0:	// 가입성공
			JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다.", "WOORI MARBLE", JOptionPane.INFORMATION_MESSAGE, null);
			p_login.setVisible(true);
			p_signup.setVisible(false);
			break;
		case 1:	// ID PW 불일치
			JOptionPane.showMessageDialog(null, "PW, PW(re)가 일치하지 않습니다.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);
			break;
		case 2:	// 누락 항목 있음
			JOptionPane.showMessageDialog(null, "ID, PW, PW(re) 모두 작성해주세요.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);
			break;
		case 3:	// 아이디 중복
			JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다.", "WOORI MARBLE", JOptionPane.ERROR_MESSAGE, null);
			break;
		}
	}
	
	public void c_T0018_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[T0018] 회원가입 기능 시작");
	        DTO.setCode("T0018");		//객체에 코드 저장
	        oos.writeObject(DTO);	 	//DTO 전송
	        oos.flush(); 						//OOS 비우기
	        System.out.println("[T0018] 회원가입 기능 완료");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

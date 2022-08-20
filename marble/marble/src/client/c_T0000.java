// [T0000] 로그인 기능 추가
// https://github.com/Hx2DEV/marble/issues/26
// 작업자 이상원
// 수정자 전호형

package client;

import java.io.ObjectOutputStream;
import data.DTO;

public class c_T0000 {
	
	public void c_T0000_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0000] 로그인 기능 시작");
			DTO.setCode("T0000");		//객체에 코드 저장
	        oos.writeObject(DTO);	 	//toss 발송
	        oos.flush(); 						//oos 비우기
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

// [T0018] 회원가입 기능 추가
// https://github.com/Hx2DEV/marble/issues/27
// 작업자 이상원 
// 수정자 전호형

package server;

import java.io.ObjectOutputStream;

import data.DAO_Signup;
import data.DTO;

// 클라이언트 클래스
public class s_T0018{

	public void s_T0018_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[T0018] 회원가입 기능 시작");
			DAO_Signup DS = new DAO_Signup(DTO);
			DTO = DS.signup(DTO);															// 회원가입 시도
			oos.writeObject(DTO);															// TOSS 객체 전송
	        oos.flush();																			// OOS 비우기
	        System.out.println("[서버] [T0018] 회원가입 기능 완료");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
// [T0000] 로그인 기능 추가
// https://github.com/Hx2DEV/marble/issues/26
// 작업자 이상원
// 작업자 전호형

package server;

import java.io.ObjectOutputStream;
import data.DAO_Login;
import data.DTO;

// 클라이언트 클래스
public class s_T0000{
	public DTO s_T0000_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[서버] [t0000] 로그인 기능 시작");
			DAO_Login DL = new DAO_Login(DTO);									// DB 연결 시도
			DTO.setIntnote(DL.login(DTO.getId(), DTO.getPw()));					// 로그인을 시도하고, 시도 결과를 DTO Intnote에 저장
			oos.writeObject(DTO);															// DTO 객체 내용 전송
	        oos.flush();																			// OOS 비움
	        System.out.println("[서버] [t0000] 로그인 기능 완료");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DTO;
	};
}
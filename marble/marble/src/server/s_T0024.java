// [T0024] 무인도 기능 추가
// https://github.com/Hx2DEV/marble/issues/32
// 작업자 이인호
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import data.DTO;

public class s_T0024 {

	public void s_T0024_send(DTO DTO, ObjectOutputStream oos) {
		// 클라에서 처리합니다.
		try {
			System.out.println("[서버] [T0024] 무인도 기능 시작");
			oos.writeObject(DTO);
			oos.flush();
			
			System.out.println("[서버] [T0024] 무인도 기능 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

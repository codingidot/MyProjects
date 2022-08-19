// [T0008] 지역 구매 기능 추가 
// https://github.com/Hx2DEV/marble/issues/8
// 양은지
// 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import data.DTO;

public class s_T0008_1
{
	public DTO s_T0008_1_send(DTO DTO, ObjectOutputStream oos, String[][][] place) {
		System.out.println("[서버] [T0008] 지역 구매 기능1 시작");
		DTO.setCode("T0008_1");
		int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 가져오기
		int nowpositon = DTO.getAfterPosition();
		
		if(place[RN][0][nowpositon] == null) {		// 만약 해당 좌표가 null 이면
			place[RN][0][nowpositon] = "null";		// "null"로 초기화
		}
		
		if(place[RN][0][nowpositon].equals("null")) {	// 만약 해당 좌표의 주인이 없다면
			DTO.setNote("주인없음");
			
		} else {
			DTO.setNote("주인있음");
		}
		

        // 플레이스[][][] -> String 변환
        String tmp_place ="";
        for (int i = 0; i < 10; i++) {
        	for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 32; k++) {
					tmp_place = tmp_place + place[i][j][k]+"/";
				}
			}
		}
        DTO.setPlace(tmp_place);		

		try {
			oos.writeObject(DTO);				// DTO 발송하기
			oos.flush();								// OOS 비우기
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[서버] [T0008] 지역 구매 기능1 완료");
		return DTO;
	
	}
}

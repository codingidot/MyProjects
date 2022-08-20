// [T0003] 방 목록 갱신 기능 추가
// https://github.com/Hx2DEV/marble/issues/3
// 작업자 민지홍
// 수정자 전호형

package server;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0003 {

	public DTO s_T0003_send(DTO DTO, ObjectOutputStream oos, String[] roomname, String[][][] gameinfo) {
		
		try {
			System.out.println("[서버] [T0003] 방 목록 갱신 시작!");
	        DTO.setCode("T0003");															// 클라이언트가 전송된 코드 분류를 알 수 있도록 객체에 코드명 저장
	        
	        
	        // 방정보[] -> String 변환
	        String tmp_roomname = "";
	        for (int i = 0; i < 10; i++) {
	        	tmp_roomname = tmp_roomname + roomname[i]+"/";
			}
	        
	        // 게임인포[][][] -> String 변환
	        String tmp_gameinfo ="";
	        for (int i = 0; i < 10; i++) {
	        	for (int j = 0; j < 7; j++) {
					for (int k = 0; k < 4; k++) {
						tmp_gameinfo = tmp_gameinfo + gameinfo[i][j][k]+"/";
					}
				}
			}
	        
	        DTO.setRoomname(tmp_roomname);	        
	        DTO.setGameinfo(tmp_gameinfo);												// 서버의 정보를 DTO에 동기화
			oos.writeObject(DTO);																// DTO 객체 내용 전송
	        oos.flush();																				// OOS 비움
	        System.out.println("[서버] [T0003] 방 목록 갱신 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DTO;
	}
}
	
// [T0004] 방 생성 기능 추가
// https://github.com/Hx2DEV/marble/issues/6
// 작업자 조동현
// 수정자 전호형

package server;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0004 {

	public DTO s_T0004_send(DTO DTO, ObjectOutputStream oos, String[] roomname, String[][][] gameinfo)  {
		
			DTO.setCode("T0004");
			

			for (int i = 0; i < 10; i++) {
				if(roomname[i].equals("null") ) {														// i번에 방 제목이 없다면
					roomname[i] = DTO.getNote();													// 방 제목 저장
					gameinfo[i][0][0] = DTO.getId();													// 아이디 저장
					DTO.setRoomNumber(String.valueOf(i+1));									// 생성한 방 번호 임시 저장
					System.out.println("[서버] [T0004] "+(i+1)+"번 방 생성 성공");
					break;
				}else if (i == 10) {
					DTO.setIntnote(0);
					System.out.println("[서버] [T0004] 방이 모두 존재해서 생성 실패");
				}
			}
			
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
	        DTO.setGameinfo(tmp_gameinfo);
	        
		try {
			oos.writeObject(DTO);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
        System.out.println("[서버] [T0004] 방 생성 완료");
		return DTO;
	}
	
}

// [T0006] 방 참가 기능 추가
// https://github.com/Hx2DEV/marble/issues/22
// 작업자 조동현
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0006 {
	
	public DTO s_T0006_send(DTO DTO,HashMap<String, ObjectOutputStream> userOOSMap, String[] roomname, String[][][] gameinfo) {
		System.out.println("[서버] [T0006] 방 참가 기능 시작");								
		DTO.setCode("T0006");
		
		int RN		= Integer.parseInt(DTO.getRoomNumber())-1;										// 요청된 방번호 임시저장		
		String ID 	= DTO.getId();																					// 요청된 아이디 임시저장
		
		if (!gameinfo[RN][0][0].equals("null") &&	!gameinfo[RN][0][1].equals("null") && 
			!gameinfo[RN][0][2].equals("null") && !gameinfo[RN][0][3].equals("null") ) {				// 방에 빈자리가 없다면
				DTO.setIntnote(0);																					// 방 참가 실패 알림용 임시 저장
			
		} else {	// 방에 빈자리가 있다면
			
			for( int i = 0; i < 4; i++ ) {
				if( gameinfo[RN][0][i].equals("null") ) {															// i번째 유저가 없다면
					gameinfo[RN][0][i] = ID;																			// 그자리에 아이디 저장
					DTO.setPlayerNum(i);																				//	몇번째 플레이어인지 정보 전달
					System.out.println(DTO.getRoomNumber()+"번 방 "+(i+1)+"자리에 참가자 ID 추가 완료");
					break;
				}
			}
		}

		
		// DTO 전송 보낼 준비 시작================================
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
		DTO.setNote(roomname[RN]);
		
		try {
			// 대상자에게 발송 시작
				for (int i = 0; i < 4; i++) {
					String id = gameinfo[RN][0][i];
					if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
						userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						System.out.println("[서버] [T0009] "+id+"에게 신호 전송");
					}
				}
			System.out.println("[서버] [T0006] 방 참가 기능 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DTO;
		}
	
}

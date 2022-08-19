// [T0010] 턴 분배 기능 추가
// https://github.com/Hx2DEV/marble/issues/9
// 작업자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0010 {
	public DTO s_T0010_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[] roomturn, String[] roomplayturn) {
		
		System.out.println("[서버] [T0010] 턴 분배 기능 시작");
		
		DTO.setCode("T0010"); 													// DTO에 작업 코드 저장
		int RN = Integer.parseInt(DTO.getRoomNumber())-1;			// 방번호 임시 저장
		String ID = "" ;																// 아이디 임시 저장
		int membercount =0;													// 방 참여자 수
		

		for (int i = 0; i < 4; i++) {			// 방 참여자 수 카운트
			if(gameinfo[RN][0][i]==null) {
				gameinfo[RN][0][i] ="null";
			}
			if(!gameinfo[RN][0][i].equals("null")) {				// i번째 유저가  null 이아니면
				membercount = membercount+1;				// 멤버 카운트에 +1
			}
		}
		
		if (roomturn[RN] == null)  {
			roomturn[RN] = "1";		
			} 		// 룸 턴이 null 이면 초기화
		
		if (roomplayturn[RN] == null) {
			roomplayturn[RN] = "1";		
			}	// 룸플레이어 턴이 null 이면 초기화
		
		
		int RPT	= Integer.parseInt(roomplayturn[RN]);
		int RT		= Integer.parseInt(roomturn[RN]);
		

		
		System.out.println("이번 턴은 " + RPT);
		if (RPT > membercount) { 	
			RPT = 1;														// 넘겨받은 PT 값이 유저 수를 초과하면 다시 첫번째인 1로 변경			
		}														
		roomplayturn[RN] = String.valueOf(RPT);				// 수정한 값을 roomplayturn 다시 저장
		roomturn[RN] = String.valueOf(RT);						// 수정한 값을 roomturn 다시 저장
		
		
		// 룸플레이어 턴 -> String 변환
        String tmp_roomplayturn = "";
        for (int i = 0; i < 10; i++) {
        	tmp_roomplayturn = tmp_roomplayturn + roomplayturn[i]+"/";
		}
		DTO.setRoomplayturn(tmp_roomplayturn);	// String으로 변환된 데이터를 DTO에 저장
		
		// 방 턴[] -> String 변환
        String tmp_roomturn = "";
        for (int i = 0; i < 10; i++) {
        	tmp_roomturn = tmp_roomturn + roomturn[i]+"/";
		}
        DTO.setRoomturn(tmp_roomturn);				// String으로 변환된 데이터를 DTO에 저장
        
        
		// 대상자에게 메세지 발송
		try {
			for (int i = 0; i < 4; i++) {
				String id = gameinfo[RN][0][i];
				if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
					userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
					userOOSMap.get(id).flush();
					System.out.println("[서버] [T0010] "+id+"에게 신호 전송");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		RT = RT + 1;
		RPT = RPT + 1;
		roomplayturn[RN] = String.valueOf(RPT);				// 수정한 값을 roomplayturn 다시 저장
		roomturn[RN] = String.valueOf(RT);						// 수정한 값을 roomturn 다시 저장
		

		// 룸플레이어 턴 -> String 변환
        tmp_roomplayturn = "";
        for (int i = 0; i < 10; i++) {
        	tmp_roomplayturn = tmp_roomplayturn + roomplayturn[i]+"/";
		}
		DTO.setRoomplayturn(tmp_roomplayturn);	// String으로 변환된 데이터를 DTO에 저장
		// 방 턴[] -> String 변환
        tmp_roomturn = "";
        for (int i = 0; i < 10; i++) {
        	tmp_roomturn = tmp_roomturn + roomturn[i]+"/";
		}
        DTO.setRoomturn(tmp_roomturn);				// String으로 변환된 데이터를 DTO에 저장
		
		
		System.out.println("[서버] [T0010] 턴 분배 기능 완료");
		return DTO;
	}
}

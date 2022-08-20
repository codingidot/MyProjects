// [T0017] 출발점 통과시 월급 지급 기능 추가
// https://github.com/Hx2DEV/marble/issues/24
// 작업자 양은지
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0017 {

	public DTO s_T0017_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[][] rotation) {
		System.out.println("[서버] [T0017] 출발점 통과시 월급 지급 기능 시작");
		
		String ID = DTO.getId();																// 아이디 얻기
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int playerNum = 0;																	// 플레이어가 몇 P 인지	
		
		// 아이디로 플레이어 넘버 구하기
		for (int i = 0; i < 4; i++) {
			if (gameinfo[RN][0][i].equals(ID)) {		
				playerNum = i;
			} 
		}
		int tmp = Integer.parseInt(gameinfo[RN][2][playerNum]) + 20;				// 변경 후 게임머니 임시 저장

		gameinfo[RN][2][playerNum] = String.valueOf(tmp);							// 게임머니 + 100 지급
		
		if (rotation[RN][playerNum]==null) {
			rotation[RN][playerNum] = "0" ;													// 로테이션 초기화   
		}
		if (rotation[RN][playerNum].equals("null")){
			rotation[RN][playerNum] = "0" ;													// 로테이션 초기화
		}
		
		rotation[RN][playerNum] = 
				String.valueOf(	Integer.parseInt(rotation[RN][playerNum])+1)	;		// 바퀴 수 +1 추가
		
		
        // 게임인포[][][] -> String 변환
        String tmp_gameinfo ="";
        for (int i = 0; i < 10; i++) {
        	for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					tmp_gameinfo = tmp_gameinfo + gameinfo[i][j][k]+"/";
				}
			}
        }
        
        
        // 로테이션[][] -> String 변환
        String tmp_roation = "" ;
        for(int i=0 ; i<10; i++) {
                for (int j = 0; j < 4; j++) {
                        tmp_roation +=  (rotation[i][j]+"/");
                }
        }
        
        DTO.setGameinfo(tmp_gameinfo);				// 작업한 게임 정보 저장
        DTO.setRotation(tmp_roation);					// 작업한 정보 저장
		
        // 대상자에게 메세지 발송
		try {
			for (int i = 0; i < 4; i++) {
				String id = gameinfo[RN][0][i];
				if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
					userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
					userOOSMap.get(id).flush();
					System.out.println("[서버] [T0017] "+id+"에게 신호 전송");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DTO;
	}
}

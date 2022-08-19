// [T0023] 수령처에서 적립된 금액 수령 기능 추가
// https://github.com/Hx2DEV/marble/issues/31
// 작업자 조동현
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import data.DTO;

public class s_T0023 {

	public DTO s_T0023_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[] donate) {
		System.out.println("[서버] [T0023] 수령처에서 적립된 금액 수령 기능 시작");
	
		String id = DTO.getId();																// 요청자 ID
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int playerNum = 0;																	// 플레이어 넘버 구할것 준비
		
		if ( donate[RN] == null ) {	donate[RN]="0";	}								// 기부금이 없다면 초기화
		if ( donate[RN].equals("null")) {	donate[RN]="0";	}						// 기부금이 없다면 초기화
		DTO.setTmp_donate(Integer.parseInt(donate[RN]));							// DTO에 기부금액 저장
		
		// 아이디로 플레이어 넘버 구하기
		for (int i = 0; i < 4; i++) {
			if (gameinfo[RN][0][i].equals(id)) {		
				playerNum = i;
			} 
		}

		// 데이터 처리 시작 ==================================================
		// ↓ 요청된 유저의 보유금액에 기부금 더하기
		gameinfo[RN][2][playerNum] = String.valueOf(Integer.parseInt(gameinfo[RN][2][playerNum]) + Integer.parseInt(donate[RN])); 
		
		donate[RN] = "0";  // 기부금 초기화 하기
				

		
		// 처리된 데이터 String 변환 및 DTO에 저장 =================================
		// 기부함[] -> String 변환
        String tmp_donate = "";
        for (int i = 0; i < 10; i++) {
        	tmp_donate = tmp_donate + donate[i]+"/";
		}
        DTO.setDonate(tmp_donate);						// 작업한 donate 저장
		
        // 게임인포[][][] -> String 변환
        String tmp_gameinfo ="";
        for (int i = 0; i < 10; i++) {
        	for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					tmp_gameinfo = tmp_gameinfo + gameinfo[i][j][k]+"/";
				}
			}
        }
        DTO.setGameinfo(tmp_gameinfo);				// 작업한 게임 정보 저장

        
        // 대상자에게 DTO 전송 ==============================================
		try {
			for (int i = 0; i < 4; i++) {
				String tmp_id = gameinfo[RN][0][i];
				if (!tmp_id.equals("null")) {										// i 번째 아이디가 null이 아니면
					userOOSMap.get(tmp_id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
					userOOSMap.get(tmp_id).flush();
					System.out.println("[서버] [T0020] "+id+"에게 신호 전송");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("[서버] [T0023] 수령처에서 적립된 금액 수령 기능 완료");
		return DTO;
	}
}

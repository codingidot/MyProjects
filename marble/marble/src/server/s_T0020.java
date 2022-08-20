// [T0020] 황금열쇠 기능 추가
// https://github.com/Hx2DEV/marble/issues/28
// 작업자 양은지
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0020 {

	public DTO s_T0020_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[] donate) {
		
		String id = DTO.getId();																// 요청자 ID
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int random = (int)(Math.random() * 10) + 1;									// 무작위 값		
		int playerNum = 0;																	// 플레이어 넘버 구할것 준비
		// 아이디로 플레이어 넘버 구하기
		for (int i = 0; i < 4; i++) {
			if (gameinfo[RN][0][i].equals(id)) {		
				playerNum = i;
			} 
		}
		if (donate[RN]==null) {					// donate 초기화
			donate[RN]="0";
		}
		
		if (donate[RN].equals("null")){			// donate 초기화
			donate[RN]="0";
		}
		
		
		
		switch (random) {
		case 1 : case 2: case 3: case 4: case 5:	// 겜머니 얻기
			gameinfo[RN][2][playerNum] =  String.valueOf(Integer.parseInt(gameinfo[RN][2][playerNum])  + (random * 3)); 	// 케이스 결과의 3의 배수로 돈획득
			DTO.setGoldkey(random * 3);																												// 골드키 확인용
			break;
			
		case 6 : case 7: case 8: case 9: case 10:	// 겜머니 기부금으로 넣기 
			
			int after_money = Integer.parseInt(gameinfo[RN][2][playerNum])  - ((random-5) * 3);
			if( after_money < 0 ) { // 잔고가 부족한 경우
				gameinfo[RN][2][playerNum] = "0";				// 플레이어 머니 0으로 변경
				DTO.setIntnote(9999);								// 게임 종료 코드
			} else {
				gameinfo[RN][2][playerNum] =  String.valueOf(Integer.parseInt(gameinfo[RN][2][playerNum])  - ((random-5) * 3)); 	// 케이스 결과의 3의 배수로 돈차감
				donate[RN] = String.valueOf(Integer.parseInt(donate[RN]) + ((random-5) * 3));
				DTO.setGoldkey((random-5) * -3); // 골드키 확인용				
			}
			break;
		}
		
		// 데이터 DTO 넣기위해 변환
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
        
        // 대상자에게 발송
		try {
			for (int i = 0; i < 4; i++) {
				String tmp_id = gameinfo[RN][0][i];
				if (!tmp_id.equals("null")) {										// i 번째 아이디가 null이 아니면
					userOOSMap.get(tmp_id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
					userOOSMap.get(tmp_id).flush();
					System.out.println("[서버] [T0020] "+tmp_id+"에게 신호 전송");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        return DTO;
	}
}

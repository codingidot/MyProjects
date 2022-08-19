// [T0012] 통행료 지불 기능 추가
// https://github.com/Hx2DEV/marble/issues/19
// 작업자 양은지
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0012 {

	public DTO s_T0012_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[][][] place) {
		
		int nowpositon = DTO.getAfterPosition();					// 요청된 좌표 정보 가져오기
		int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 가져오기
		String id = DTO.getId();
		int playerNum = 0;												// 요청된 캐릭터가 몇p인지.
		String owner = place[RN][0][nowpositon];					// 땅 주인 id
		int owner_playerNum = 0;												// 땅 주인이 몇 p인지.
		

		// 아이디로 플레이어 넘버 구하기
		for (int i = 0; i < 4; i++) {
			if(gameinfo[RN][0][i]==null) {
				gameinfo[RN][0][i] = "";
			}
			
			if (gameinfo[RN][0][i].equals(id)) {		
				playerNum = i;
			}
		}
		
		// 땅주인 아이디로 플레이어 넘버 구하기
		for (int i = 0; i < 4; i++) {
			if(gameinfo[RN][0][i]==null) {
				gameinfo[RN][0][i] = "";
			}
			
			if (gameinfo[RN][0][i].equals(owner)) {	
				owner_playerNum = i;
			}
		}
			
		int price = Integer.parseInt(place[RN][2][nowpositon])	;		// 통행료
		
		if(!id.equals(place[RN][0][nowpositon])) {
			// 데이터 처리 시작==================
			if( Integer.parseInt(gameinfo[RN][2][playerNum]) >= price) {		// 유저 머니가 통행료 보다 많거나 같으면
				gameinfo[RN][2][playerNum] = String.valueOf(Integer.parseInt(gameinfo[RN][2][playerNum]) - price);	// 땅에 걸린 유저에게서 머니 차감
				gameinfo[RN][2][owner_playerNum] =  String.valueOf(Integer.parseInt(gameinfo[RN][2][owner_playerNum]) + price);	// 땅주인에게 머니 추가
			} else	{	// 통행료 보다 돈이 적다면
				System.out.println("★★★★★★★★★★★파산★★★★★★★★★★★★");
				gameinfo[RN][2][playerNum] = "0";				// 플레이어 머니 0으로 변경
				DTO.setIntnote(9999);								// 게임 종료 코드
			}
		}
		
		// 처리된 데이터 DTO에 전송하기 위해 String 변환
        // 게임인포[][][] -> String 변환
        String tmp_gameinfo ="";
        for (int i = 0; i < 10; i++) {
        	for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					tmp_gameinfo = tmp_gameinfo + gameinfo[i][j][k]+"/";
				}
			}
		}
        DTO.setGameinfo(tmp_gameinfo);
        DTO.setCode("T0012");
        
		try {
			for (int i = 0; i < 4; i++) {
				String id1 = gameinfo[RN][0][i];
				if (!id1.equals("null")) {											// i 번째 아이디가 null이 아니면
					if (id1!=null) {
						userOOSMap.get(id1).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						userOOSMap.get(id1).flush();
						System.out.println("[서버] [T0012] "+id1+"에게 신호 전송");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DTO;
	}
}

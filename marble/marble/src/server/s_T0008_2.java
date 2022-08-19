// [T0008] 지역 구매 기능 추가 
// https://github.com/Hx2DEV/marble/issues/8
// 양은지
// 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0008_2 {
	
	public DTO s_T0008_2_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[][][] place) {
		System.out.println("[서버] [T0008] 지역 구매 기능2 시작");
		
		int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 가져오기
		int nowposition = DTO.getAfterPosition();					// 요청된 자리 저장
		String id = DTO.getId();											// 아이디 저장
		int PN = 0;															// 요청 아이디가 몇 p 인지 저장
		
		for (int i = 0; i < 4; i++) {			
			if (gameinfo[RN][0][i].equals(id)) {											// 몇번째 플레이어인지 구하기
				PN = i;
			}
		}
		
		int playermoney = Integer.parseInt(gameinfo[RN][2][PN]);					// 요청된 플레이어 머니
		int localprice	= Integer.parseInt(place[RN][1][nowposition]);			// 지역 가격
				
		if( localprice <= playermoney ) {													// 지역가격 보다 요청한 유저의 돈이 많거나 같다면
			playermoney = playermoney - localprice;									// 지역 구매에 필요한 가격만큼 차감
			place[RN][0][nowposition] = id;												// 구매자 이름 등록
			DTO.setNote("구매성공");
		} else {
			DTO.setNote("구매실패");
		}
		
		// 처리된 데이터 정리======================
		gameinfo[RN][2][PN] = String.valueOf(playermoney);						// 요청된 플레이어 머니 저장
		
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
        
        
        // 모든 유저에게 DTO 발송
		try {
			for (int i = 0; i < 4; i++) {
				String id1 = gameinfo[RN][0][i];
				if (!id1.equals("null")) {											// i 번째 아이디가 null이 아니면
					if (id1!=null) {
						userOOSMap.get(id1).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						userOOSMap.get(id1).flush();
						System.out.println("[서버] [T0008_2] "+id+"에게 신호 전송");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[서버] [T0008] 지역 구매 기능2 완료");
		return DTO;
	}
}

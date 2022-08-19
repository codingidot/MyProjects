// [T0009] 게임 시작 기능 추가
// https://github.com/Hx2DEV/marble/issues/7
// 작업자 민지홍, 전호형

package server;

import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

// 클라이언트 클래스
public class s_T0009{

	public DTO s_T0009_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[][][] place) {
		try {
			System.out.println("[서버] [T0009] 게임 시작 기능 시작");
			
			DTO.setPlayerturn(1);														// 1순위 플레이어가 먼저 시작할 수 있도록 플레이어턴 저장
			
			int RN = Integer.parseInt(DTO.getRoomNumber())-1;			// 방번호 임시저장 
			int cnt = 0;																	// 방 참가자 수 카운트 (임시 저장)
	
			
			for (int i = 0; i < 4; i++) {
				if(!gameinfo[RN][0][i].equals("null")){		// 자리마다 사람이 있다면
					cnt = cnt + 1;								// cnt에 +1
				}
			} 
			
			if(cnt > 1) {		// 방 인원이 1명 이상이라면,
				DTO.setIntnote(1);
				
				// [T0007] 지역세팅 기능 추가
				// https://github.com/Hx2DEV/marble/issues/5
				// 작업자 이인호
				// 수정자 전호형
				s_T0007 T0007 = new s_T0007();
				place = T0007.s_T0007_send(DTO, place);							// place 처리 결과 저장
				// ====================================================
				
				// [T0011] 턴 순서 지정 기능 추가
				// https://github.com/Hx2DEV/marble/issues/21
				// 작성자 이인호
				// 수정자 전호형
				s_T0011 T0011 = new s_T0011(); 	// 주사위 던지는 순서 구하기
				HashMap <Integer, int[]> HM = new HashMap<>();
				HM = T0011.s_T0011_send(gameinfo, RN);

				int[] dice = HM.get(1);
				int[] rank = HM.get(2);
				// =========================================
				
				
				for (int i = 0; i < 4; i++) {			
					if (!gameinfo[RN][0][i].equals("null")) {
						gameinfo[RN][1][i] = String.valueOf(rank[i]);	// 플레이 순서
						gameinfo[RN][2][i] = "50";							// 게임머니
						gameinfo[RN][3][i] = "0";							// 시작위치
						gameinfo[RN][4][i] = "0";							// 무인도 플레이어 턴
						gameinfo[RN][5][i] = String.valueOf(dice[i]);	// 플레이 순서 주사위 값	
					}
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
		        
		        
				
		        // 대상자에게 발송 시작
				for (int i = 0; i < 4; i++) {
					String id = gameinfo[RN][0][i];
					if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
						userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						userOOSMap.get(id).flush();
						
						System.out.println("[서버] [T0009] "+id+"에게 신호 전송");
					}
				}
			} else {
				System.out.println("[서버] [T0009] 1명 밖에 없어서 진행 불가");
				DTO.setIntnote(0);
				
		        // 대상자에게 발송 시작
				for (int i = 0; i < 4; i++) {
					String id = gameinfo[RN][0][i];
					if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
						userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						userOOSMap.get(id).flush();
						System.out.println("[서버] [T0009] "+id+"에게 신호 전송");
					}
				}
				
			}
	        System.out.println("[서버] [T0009] 게임 시작 기능 완료");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DTO;
	};
	
	public void s_T0009() {};
	
}
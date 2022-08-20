package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0013 {

	public void s_T0013_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[][][] place) {
		System.out.println("[서버] [T0013] 게임 /승/패 처리 기능 시작");
		//유저들 재산 계산
		
		// static String [][][] place = new String [10][3][32];		 	// 방번호 // 소유자, 구매가격, 통행료
		// static String [][][] gameinfo = new String [10][7][4]; 	// 방번호 // 아이디, 턴순서, 게임머니, 플레이위치
		int p1 = 0; 
		int p2 = 0;
		int p3 = 0;
		int p4 = 0;
		
		String callid =  DTO.getId();
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호

		// gameinfo 유저 널 값 초기화
		for (int i = 0; i < 4; i++) {
			if(gameinfo[RN][0][i]==null) {
				gameinfo[RN][0][i]="null";
			}
		}
		
		// gameinfo 겜머니 널 값 초기화
		for (int i = 0; i < 4; i++) {
			if(gameinfo[RN][2][i]==null) {
				gameinfo[RN][2][i]="0";
			}
		}
		
		// place 유저 널 값 초기화
		for (int i = 0; i < 32; i++) {
			if(place[RN][0][i]==null) {
				place[RN][0][i]="null";
			}
			
			if(place[RN][0][i].equals("null")) {
				place[RN][0][i]="";
			}
		}
		
		// place 지역 널 값 초기화
		for (int i = 0; i < 32; i++) {
			if(place[RN][1][i]==null) {
				place[RN][1][i]="0";
			}
			if(place[RN][1][i].equals("null")) {
				place[RN][1][i]="0";
			}
		}
		
		

		if( !gameinfo[RN][0][0].equals("null")) {
			p1 = Integer.parseInt(gameinfo[RN][2][0]);								// 1p 머니 임시저장 
		}
		if( !gameinfo[RN][0][1].equals("null")) {
			p2 = Integer.parseInt(gameinfo[RN][2][1]);								// 1p 머니 임시저장 
		}
		if( !gameinfo[RN][0][2].equals("null")) {
			p3 = Integer.parseInt(gameinfo[RN][2][2]);								// 1p 머니 임시저장 
		}
		if( !gameinfo[RN][0][3].equals("null")) {
			p4 = Integer.parseInt(gameinfo[RN][2][3]);								// 1p 머니 임시저장 
		}

		
		// 게임머니 초기화
		for (int i = 0; i < 4; i++) {		
			gameinfo[RN][2][i] = "0";
		}
		
		
		// 지역 가격을 유저별 자산에 포함
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 32; j++) {
				if (gameinfo[RN][0][i].equals(place[RN][0][j])){	 // j지역의 소유자 i라면 
					gameinfo[RN][2][i] = 						// 유저 돈 + 지역 가격  
						String.valueOf(		Integer.parseInt(gameinfo[RN][2][i]) +	Integer.parseInt(place[RN][1][j]));
				}
			}
		}
		
		
		
		// 전 재산 합산
		gameinfo[RN][2][0] = String.valueOf(Integer.parseInt(gameinfo[RN][2][0]) + p1);	
		gameinfo[RN][2][1] = String.valueOf(Integer.parseInt(gameinfo[RN][2][1]) + p2);
		gameinfo[RN][2][2] = String.valueOf(Integer.parseInt(gameinfo[RN][2][2]) + p3);
		gameinfo[RN][2][3] = String.valueOf(Integer.parseInt(gameinfo[RN][2][3]) + p4);
		
		
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
					System.out.println("[서버] [T0013] "+tmp_id+"에게 신호 전송");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		System.out.println("[서버] [T0013] 게임 /승/패 처리 기능 완료");
	}
}

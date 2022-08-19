// [T0015] 주사위 굴리기 기능 추가
// https://github.com/Hx2DEV/marble/issues/2
// 작업자 조동현
// 수정자 전호형

package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import data.DTO;

public class s_T0015 {
	//주사위 굴려서 이동
		public DTO s_T0015_send(DTO DTO, HashMap<String, ObjectOutputStream> userOOSMap, String[][][] gameinfo, String[] roomplayturn) {
			System.out.println("[서버] [T0015] 주사위 굴리기 기능 시작");
			
			int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 가져오기
			String ID = DTO.getId();											// 요청된 유저 아이디 가져오기
			int playerNum = 0;												// 요청된 플레이어가 몇p인지 가져오기 (초기화)
			
			// 아이디로 플레이어 넘버 구하기
			for (int i = 0; i < 4; i++) {
				if (gameinfo[RN][0][i].equals(ID)) {		
					playerNum = i;
				}
			}
			
			int dice1=(int)(Math.ceil(Math.random()*6));	// 1번 주사위 
			DTO.setdice1(dice1);									// DTO에 저장
			int dice2=(int)(Math.ceil(Math.random()*6));	// 2번 주사위
			DTO.setdice2(dice2);									// DTO에 저장
			
			int dice = dice1 + dice2; //주사위 던진값
			
			if ( gameinfo[RN][3][playerNum] == null || gameinfo[RN][3][playerNum].equals("null") ) { // 플레이어 좌표가 null이면 0으로 초기화
				gameinfo[RN][3][playerNum] = "0";
			}
			
			int before_position = Integer.parseInt(gameinfo[RN][3][playerNum]); 			// 이동 전 좌표
			int after_position = Integer.parseInt(gameinfo[RN][3][playerNum])+dice;		// 이동 후 좌표
					
			if(after_position >= 32) {
				after_position = after_position - 32; //현재위치 32보다 크면 32빼줌
			}
			
			gameinfo[RN][3][playerNum] = String.valueOf(after_position);
			
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
			
	        // 룸플레이어 턴 -> String 변환
	        String tmp_roomplayturn = "";
	        for (int i = 0; i < 10; i++) {
	        	tmp_roomplayturn = tmp_roomplayturn + roomplayturn[i]+"/";
			}
			DTO.setRoomplayturn(tmp_roomplayturn);	// String으로 변환된 데이터를 DTO에 저장
	        DTO.setAfter_Position(after_position);			// 작업한 이전 좌표 저장
	        DTO.setBefore_position(before_position);		// 작업한 이후 좌표 저장
			
	        DTO.setCode("T0015");
			try {
				for (int i = 0; i < 4; i++) {
					String id = gameinfo[RN][0][i];
					if (!id.equals("null")) {										// i 번째 아이디가 null이 아니면
						userOOSMap.get(id).writeObject(DTO);			// 아이디를 키값으로 OOS를 꺼내 발송.
						userOOSMap.get(id).flush();
						System.out.println("[서버] [T0015] "+id+"에게 신호 전송");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return DTO;
		}
}

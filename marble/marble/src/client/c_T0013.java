// [T0013] 게임 승/패 처리 기능 추가
// https://github.com/Hx2DEV/marble/issues/20
// 작업자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DTO;

public class c_T0013 {
	
	public void c_T0013_recv(DTO DTO, JButton btn_pr_player_money, HashMap<Integer, JLabel> HMfinish_money, HashMap<Integer, JPanel> HMfinish, JPanel p_finish, JLabel lbl_pr_bg_end) {
		
		int RN = Integer.parseInt(DTO.getRoomNumber()); 							// 방번호
		int [] player_money = new int[4];

		// 게임인포 String -> 배열[][][] 화 시작
		String[] tmp_gameinfo = DTO.getGameinfo().split("/");
		String[][][] gi = new String[10][7][4];
		int cnt2 = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gi[i][j][k] = tmp_gameinfo[cnt2];
					cnt2 ++;
				}
			}
		}
		

		// 데이터 처리===============================================
		btn_pr_player_money.setVisible(false);										// 주사위 던지기 버튼 숨기기
		p_finish.setVisible(true);															// 결과 패널 출력
		lbl_pr_bg_end.setVisible(true);													// 배경 검정 처리
		
		for (int i = 0; i < 4; i++) {
			// 유저 이름이 null 이면 초기화
			if (gi[RN][0][i]==null) {
				gi[RN][0][i]="null";
			}
			if ( gi[RN][2][i].equals("null")) {
				gi[RN][2][i] = "0";
			}
			
			if (!gi[RN][0][i].equals("null")) {	// i번째 유저가 null 이 아니면
				HMfinish.get(i+1).setVisible(true);						// 결과 패널 보이기
				HMfinish_money.get(i+1).setText(gi[RN][0][i]);		// i번째유저 아이디 보이기
				HMfinish_money.get(i+11).setText(gi[RN][2][i] + " 만원");		// i번째유저 게임머니 보이기
			}
		}
		
		
		// 겜머니 숫자로 저장
		for (int i = 0; i < 4; i++) {
			player_money[i] =	Integer.parseInt(gi[RN][2][i]);
		}	
		
		
		
	     int[] rank = {1,1,1,1};
         
	                       
	        for(int i=0; i < 4; i++){
	            rank[i] = 1; //1등으로 초기화
	           
	            for (int j = 0; j < 4; j++) { 		//기준데이터와 나머지데이터비교                             
	                if(player_money[i] < player_money[j]){   		//기준데이터가 나머지데이터라 비교했을때 적으면 rank[i] 카운트
	                    rank[i]++; //COUNT                 
	                }              
	            }          
	        }      
	       
		
	
        
        // 뽑은 랭킹에서 1등 배경 색 바꾸기
        for (int i = 0; i < 4; i++) {
        	System.out.println(i+"번째 유저는 몇등?" + rank[i]);
        	if(rank[i]==1) {
        		HMfinish_money.get(i+21).setVisible(true);
        	}
		}
		
		System.out.println("[클라] [T0013] 게임 승/패 처리 기능 완료");
	}
		
		
		
		

	public void c_T0013_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0013] 게임 승/패 처리 기능 시작");
			DTO.setCode("T0013");
			oos.writeObject(DTO);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

}

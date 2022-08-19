// [T0011] 턴 순서 지정 기능 추가
// https://github.com/Hx2DEV/marble/issues/21
// 작성자 이인호
// 수정자 전호형

package server;

import java.util.HashMap;

public class s_T0011 {
	
	public HashMap<Integer, int[]> s_T0011_send(String[][][] gameinfo, int RN) {
		
		int[] dice = new int[4];
		int[] rank = {1,1,1,1,1};
		
		
		for(int i=0; i< 4; i++) {// 1~6까지 주사위 굴림
			if(!gameinfo[RN][0][i].equals("null")) {					// i번째 유저가 자리에 있다면
				dice[i] = (int)(Math.random() * 6) + 1;				// 무작위 수 생성
				for(int j=0; j < i; j++) {									// 주사위 숫자 중복제거 시작
					if(dice[i]==dice[j]) {
						i--;														// 중복이 있다면 다시 주사위 굴림
					}
				}
			}else {
				dice[i] = 0;
			}
		}
                
        for(int i = 0; i < 4 ; i++){
        	if(dice[i]!=0) {
	            rank[i] = 1; 										//1등으로 초기화
	            for (int j = 0; j < dice.length; j++) { 		//기준데이터와 나머지데이터비교                             
	                if(dice[i]<dice[j]){   						//기준데이터가 나머지데이터라 비교했을때 적으면 rank[i] 카운트
	                    rank[i]++;   
	                }              
	            }      
        	}else {
        		rank[i] = 0;
        	}
        }      
        
        for (int i = 0; i < rank.length; i++) {
        	System.out.println("랭크"+i+"는"+rank[i]);
		}
        
        
        HashMap <Integer, int[]> HM = new HashMap<>();
		
        HM.put(1, dice);
        HM.put(2, rank);
        
		return HM;
	}
	
}

// [T0007] 지역세팅 기능 추가
// https://github.com/Hx2DEV/marble/issues/5
// 작업자 이인호
// 수정자 전호형

package server;


import data.DTO;

public class s_T0007 {

	public String[][][] s_T0007_send(DTO DTO, String[][][] place) {
		System.out.println("[서버] [T0007] 지역 세팅 기능 시작");
		
		int RN = Integer.parseInt(DTO.getRoomNumber())-1;
		for(int i = 0; i < 32; i++) {								//구입료, 통행료 세팅
			if( (i % 4) != 0) {
				place[RN][1][i]=String.valueOf(i);				//구입료 세팅
				place[RN][2][i]=String.valueOf(i*2);			//통행료 세팅
			}
			else
			{
				place[RN][1][i]="0";// CARD지역은 NULL 이 아님	
			}
		}
		System.out.println("[서버] [T0007] 지역 세팅 기능 완료");
		return place;
	}
	
}

// [T0021] 좌표에따른 TASK 동작 기능 추가
// https://github.com/Hx2DEV/marble/issues/29
// 작업자 양은지
// 수정자 전호형


package client;

import java.io.ObjectOutputStream;

import data.DTO;


public class c_T0021 {

	
	static c_T0008_1 T0008_1 = new c_T0008_1(); 	// 지역구매 
	static c_T0010 T0010 = new c_T0010();				// 전호형	
	static c_T0017 T0017 = new c_T0017(); 			// 월급
//	static c_T0019 T0019 = new c_T0019(); 			// 세계여행
	static c_T0020 T0020 = new c_T0020(); 			// 황금열쇠
	static c_T0023 T0023 = new c_T0023(); 			// 수령처
	static c_T0024 T0024 = new c_T0024(); 			// 무인도
	
	public int c_T0021_send(DTO DTO, ObjectOutputStream oos, int roomNumber, int rotation_sw) {
		int after_position = DTO.getAfterPosition();
		
		switch(after_position) {
		
		case 0: // 출발점 좌표
			
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				rotation_sw = 0;
			} else{
				T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
			}
			break;
			
			
		case 1: case 2: case 3: case 5: case 6: case 7: case 9: case 10: case 11: case 13: case 14: case 15:
		case 17: case 18: case 19: case 21: case 22: case 23: case 25: case 26: case 27: case 29: case 30: case 31: 
			T0008_1.c_T0008_1_send(DTO, oos); // 지역구매 신호 보내기		
			break;
			
		
		case 4: case 12: case 20: case 28:	 // 황금열쇠
			T0020.c_T0020_send(DTO, oos, roomNumber);	// 황금열쇠 기능 시작
			break;
			
			
		case 8: // 무인도 좌표 		
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				rotation_sw = 0;
			} else{
				T0024.c_T0024_send(DTO, oos, roomNumber);	// 무인도 기능 추가
			}
			break;
			
			
		case 16: // 기부수령처 좌표		
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				rotation_sw = 0;
			} else{
				T0023.c_T0023_send(DTO, oos, roomNumber);	// 수령처에서 적립된 금액 수령 기능
			}
			
			break;
			
		
		case 24: // 세계여행 죄표
			if (rotation_sw == 1) {
				T0017.c_T0017_send(DTO, oos); // 출발점 통과시 월급 지급 기능 추가
				rotation_sw = 0;
			} else{
				T0010.c_T0010_send(DTO, oos, roomNumber);	// 턴 분배 기능 시작
			}
			break;
				
			
		}
		return rotation_sw;
	}
}

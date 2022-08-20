// [T0015] 주사위 굴리기 기능 추가
// https://github.com/Hx2DEV/marble/issues/2
// 작업자 조동현
// 수정자 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import data.DTO;

public class c_T0015 {
		
		c_T0017 T0017 = new c_T0017();		// 월급 기능
	
		public DTO c_t0015_recv(DTO DTO, HashMap<Integer, JLabel> HMp_icon, JLabel lbl_pr_dice1, JLabel lbl_pr_dice2, ObjectOutputStream oos, String id2) {
			String id = DTO.getId();
			int RN = Integer.parseInt(DTO.getRoomNumber()); 		// 요청된 방정보 가져오기
			int playerNum = 0;
			int sw = 0;
			
			// 게임인포 String -> 배열[][][] 화 시작
			String[] tmp_gameinfo = DTO.getGameinfo().split("/");
			String[][][] gi = new String[10][7][4];
			int cnt = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 7; j++) {
					for (int k = 0; k < 4; k++) {
						gi[i][j][k] = tmp_gameinfo[cnt];
						cnt ++;
					}
				}
			}
			
			// 아이디로 플레이어 넘버 구하기
			for (int i = 0; i < 4; i++) {
				if (gi[RN][0][i].equals(id)) {		
					playerNum = i;
				}
			}
			
			int dice1 = DTO.getdice1();										// 주사위 1값
			int dice2 = DTO.getdice2();										// 주사위 2값
			int dice = dice1 + dice2; 										// 주사위 값 합
			int before_position = DTO.getBefore_position(); 			// 이동 전 좌표
			int after_position = DTO.getAfterPosition();				// 이동 후 좌표
			int pure_after_position = before_position + dice;		// 주사위 수를 순수하게 더한 좌표 (출발지 돌아도 그냥 더한 값)
					
			// 동적 변수 생성
			HashMap <Integer, String> diceimg = new HashMap<>();
			diceimg.put(1, "/img/dice1.png");
			diceimg.put(2, "/img/dice2.png");
			diceimg.put(3, "/img/dice3.png");
			diceimg.put(4, "/img/dice4.png");
			diceimg.put(5, "/img/dice5.png");
			diceimg.put(6, "/img/dice6.png");
			diceimg.put(21 ,"/img/dice1/dice1.png");
			diceimg.put(22 ,"/img/dice1/dice2.png");
			diceimg.put(23 ,"/img/dice1/dice3.png");
			diceimg.put(24 ,"/img/dice1/dice4.png");
			diceimg.put(25 ,"/img/dice1/dice5.png");
			diceimg.put(26 ,"/img/dice1/dice6.png");
			diceimg.put(11 ,"/img/dice2/dice1.png");
			diceimg.put(12 ,"/img/dice2/dice2.png");
			diceimg.put(13 ,"/img/dice2/dice3.png");
			diceimg.put(14 ,"/img/dice2/dice4.png");
			diceimg.put(15 ,"/img/dice2/dice5.png");
			diceimg.put(16 ,"/img/dice2/dice6.png");
			
			
			
			// 데이터 처리 시작
			lbl_pr_dice1.setVisible(true);
			lbl_pr_dice2.setVisible(true);
			// 주사위 흔들기 시작
			for (int i = 0; i < 3; i++) {							
				for (int j = 1; j < 7; j++) {
					lbl_pr_dice1.setIcon(new ImageIcon(client.class.getResource(diceimg.get(j+20))));
					lbl_pr_dice2.setIcon(new ImageIcon(client.class.getResource(diceimg.get(j+10))));
					try { Thread.sleep(100);	} catch (InterruptedException e) {	e.printStackTrace();}
				}
				
			}
			
			// 얻은 주사위 값 보여주기
			lbl_pr_dice1.setIcon(new ImageIcon(client.class.getResource(diceimg.get(dice1))));
			lbl_pr_dice2.setIcon(new ImageIcon(client.class.getResource(diceimg.get(dice2))));		
			try { Thread.sleep(1000);	} catch (InterruptedException e) {	e.printStackTrace();}
			
			System.out.println("[클라] [T0015] 이동 전 좌표: "+before_position+" / 이동 후 좌표"+after_position);
			if( before_position > after_position ) {		// 출발지를 통과한 경우

				for (int i = before_position; i < pure_after_position+1 ; i++) {
					if (i<32) {		// 현 위치가 출발지 이전이면
						HMp_icon.get(((playerNum+1)*100)+i).setVisible(true);
						try { Thread.sleep(300);	} catch (InterruptedException e) {	e.printStackTrace();}
					
						if ( i != after_position) {
							HMp_icon.get(((playerNum+1)*100)+i).setVisible(false);
						}
						
					} else {																										// 현 위치가 출발지를 밟거나 지났다면
						if (i-32 == 0) {
							DTO.setIntnote(10);																				// 1바퀴 돌았을때를 표시할 임시 코드 지정
						}
						
						HMp_icon.get(((playerNum+1)*100)+(i-32)).setVisible(true);								// 출발지 부터 다시 좌표찍기
						try { Thread.sleep(300);	} catch (InterruptedException e) {	e.printStackTrace();}
					
						if ( i != pure_after_position) {
							HMp_icon.get(((playerNum+1)*100)+i-32).setVisible(false);
						}
					}
				}
							

			} else {
				for (int i = before_position; i < after_position+1 ; i++) {
					
					HMp_icon.get(((playerNum+1)*100)+i).setVisible(true);
					try { Thread.sleep(300);	} catch (InterruptedException e) {	e.printStackTrace();}
					
					if ( i != after_position) {
						HMp_icon.get(((playerNum+1)*100)+i).setVisible(false);
					}
				}
			}
			
			System.out.println("[클라] [T0015] 주사위 굴리기 기능 완료");
			return DTO;
		}
	
		
	public void c_T0015_send(DTO DTO, ObjectOutputStream oos, String id, int roomNumber ) {
		try {
			System.out.println("[클라] [T0015] 주사위 굴리기 기능 시작");
			DTO.setId(id);														// 아이디 저장
			DTO.setRoomNumber(String.valueOf(roomNumber));	// DTO 에 방번호 저장
			DTO.setCode("T0015");											// 코드 저장
			oos.writeObject(DTO);											// 코드 발송
			oos.flush();															// OOS 비우기
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
}

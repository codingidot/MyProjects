// [T0010] 턴 분배 기능 추가
// https://github.com/Hx2DEV/marble/issues/9
// 작업자 전호형

package client;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DTO;

public class c_T0010 {
	
	public void c_T0010_recv(DTO DTO, JButton btn_pr_dice, HashMap<Integer, JPanel> HMsidebar_user, String id, JLabel lbl_pr_wait, HashMap<Integer, JLabel> HMsidebar_p) {	
		System.out.println("[클라] [T0010] 턴 분배 기능 시작");
		
		// 게임인포 String -> 배열[][][] 화 시작
		String[] tmp_gameinfo = DTO.getGameinfo().split("/");
		String[][][] gameinfo = new String[10][7][4];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				for (int k = 0; k < 4; k++) {
					gameinfo[i][j][k] = tmp_gameinfo[cnt];
					cnt ++;
				}
			}
		}
		
		// 룸플레이턴 String -> 배열[]화 시작
		String tmp = DTO.getRoomplayturn();
		String[] PTarray = tmp.split("/");
		
		int RN = Integer.parseInt(DTO.getRoomNumber())-1;			// 방번호 임시 저장
		int PT =	Integer.parseInt(PTarray[RN]);								// 몇 번째 플레이어에게 던질 기회를 줄 지 설정
		int Pwhere = 0;															// 몇 번째에 플레이어가 존재하는지 (준비)
		int membercount = 0;													// 멤버수 저장 (준비)
		int nextPT = PT+1 ;														// 다음턴 번호 (준비)
		
		
		// 멤버수 확인
		for (int i = 0; i < 4; i++) {			
			if(gameinfo[RN][0][i]==null) {							// 값이 없다면 데이터 초기화
				gameinfo[RN][0][i] ="null";
			}
			if(!gameinfo[RN][0][i].equals("null")) {				// i번째 유저가  null 이아니면
				membercount = membercount+1;				// 멤버 카운트에 +1
			}
		}	
				
		if(PT+1 > membercount) {									// 멤버수 보다 다음 플레이턴의 수가 크면 1로 초기화
			nextPT = 1;
		}
		
		
		for (int i = 0; i < 4; i++) {
			if( gameinfo[RN][0][i].equals(id) ) {
				Pwhere = i;
			}
		}
		
		for (int i = 0; i < 4; i++) {	// 다음턴 유저 커서 노출
			if(Integer.parseInt(gameinfo[RN][1][i]) == nextPT) {			
				HMsidebar_p.get(31).setVisible(false);
				HMsidebar_p.get(32).setVisible(false);
				HMsidebar_p.get(33).setVisible(false);
				HMsidebar_p.get(34).setVisible(false);
				HMsidebar_p.get(i+31).setVisible(true);
				break;	
			}
		}

		for (int i = 0; i < 4; i++) { // 현재턴 유저 색상 변경
			if(Integer.parseInt(gameinfo[RN][1][i]) == PT) {
				HMsidebar_user.get(1).setBackground(Color.WHITE);
				HMsidebar_user.get(2).setBackground(Color.WHITE);
				HMsidebar_user.get(3).setBackground(Color.WHITE);
				HMsidebar_user.get(4).setBackground(Color.WHITE);			
				HMsidebar_user.get(i+1).setBackground(new Color(255, 165, 0));	// 패널 배경 색상 변경
				break;	
			}
		}

		
		if(Integer.parseInt(gameinfo[RN][1][Pwhere]) == PT ) {		// 게임 내 던질 차례 번호와, 유저의 던질차례가 일치하면
			lbl_pr_wait.setVisible(false);										// 턴 대기중 이미지 숨기기
			btn_pr_dice.setVisible(true);  									// 주사위 던지기 버튼 노출
			DTO.setPlayerturn(DTO.getPlayerturn()+1);					// 다음턴 유저가 던질 수 있도록 순서 변경
		} else {
			lbl_pr_wait.setVisible(true);										// 턴 대기중 이미지 보이기
		}
		System.out.println("[클라] [T0010] 턴 분배 기능 완료");
	}

	public void c_T0010_send(DTO DTO, ObjectOutputStream oos, int roomNumber) {
		System.out.println("[클라] [T0010] 턴 분배 기능 시작");
		DTO.setCode("T0010");
		DTO.setRoomNumber(String.valueOf(roomNumber)+1);	// 룸넘버 DTO에 저장
		
		try {
			oos.writeObject(DTO);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

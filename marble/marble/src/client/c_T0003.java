// [T0003] 로비에서 방 목록 갱신 기능 추가
// https://github.com/Hx2DEV/marble/issues/3
// 작업자 민지홍
// 수정자 전호형

package client;

import java.awt.Color;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.DTO;

public class c_T0003 {
	
	public void c_T0003_recv(	DTO DTO, 
										HashMap<Integer, JLabel> HMroom_stats, HashMap<Integer, JLabel> HMroom_title,
										HashMap<Integer, JLabel> HMroom_ICON, HashMap<Integer, JPanel> HMlobby_room) {
	
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

		String tmp_roominfo = DTO.getRoomname();
		String[] roomname = tmp_roominfo.split("/");

		HashMap<Integer, String> HMicon = new HashMap<>();	
		HMicon.put(1, "/img/icon_1pready.png");
		HMicon.put(2, "/img/icon_2pready.png");
		HMicon.put(3, "/img/icon_3pready.png");
		HMicon.put(4, "/img/icon_4pready.png");
		
		
		// 데이터 처리 시작
		for (int i = 1; i < 11; i++) {
			if(gi[i-1][0][0].equals("null") && gi[i-1][0][1].equals("null") && gi[i-1][0][2].equals("null") && gi[i-1][0][3].equals("null")) { // i번 방의 사용자가 없다면
				HMlobby_room.get(i).setVisible(false);
			}else {
				for (int j = 0; j < 4; j++) {
					if(!gi[i-1][0][j].equals("null")) {//i번째 방 j번째 유저가 존재한다면
						HMroom_ICON.get((i*10)+(j+1)).setIcon(new ImageIcon(client.class.getResource(HMicon.get(j+1))));		// 아이콘 변경
						HMroom_title.get(i).setText(roomname[i-1]);																				// i번 방 이름 변경
						HMlobby_room.get(i).setVisible(true);																						// i번 방 패널 변경
						HMroom_stats.get(i).setText("대기중");																						// i번 방 상태창 변경
						HMroom_stats.get(i).setBackground(new Color(72, 209, 204));														// i번 방 상태창 변경
					}
				}
				
				if(!gi[i-1][0][0].equals("null") && !gi[i-1][0][1].equals("null") && !gi[i-1][0][2].equals("null") && !gi[i-1][0][3].equals("null")) {	// i번 방에 사용자가 가득찼다면
					HMroom_stats.get(i).setText("가득참");																							// i번 방 상태창 변경
					HMroom_stats.get(i).setBackground(Color.DARK_GRAY);																	// i번 방 상태창 변경
				}
				
			}	
		}
		System.out.println("[클라] [T0003] 방 목록 갱신 완료");
	}
	

	public void c_T0003_send(DTO dto, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0003] 방 목록 갱신 시작");
	        dto.setCode("T0003");			//객체에 코드 저장
	        oos.writeObject(dto);	 			//DTO 발송
	        oos.flush(); 							//OOS 비우기
		} catch (Exception e1) {
			e1.printStackTrace();	
		}
	}
}
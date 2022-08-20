// [T0009] 게임 시작 기능 추가
// https://github.com/Hx2DEV/marble/issues/7
// 작업자 민지홍, 전호형

package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.DTO;

public class c_T0009 {
	
	public void c_T0009_recv(DTO DTO, HashMap<Integer, JLabel> HMsidebar_p, JButton btn_pr_start, HashMap<Integer, JLabel> HMp_icon, JPanel contentPane, JLabel lbl_pr_title, ObjectOutputStream oos) {
		System.out.println("[클라] [T0009] 리시브 시작");
		
		if(DTO.getIntnote()==0) {
			JOptionPane.showMessageDialog(contentPane, "플레이어가 2명 이상일 때 시작 가능합니다.", "WOORI MARBLE", JOptionPane.WARNING_MESSAGE, null);
		}else {
			
			int RN = Integer.parseInt(DTO.getRoomNumber())-1;	// 방 번호
					
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
			btn_pr_start.setVisible(false);				// 시작 버튼 안보이게
			lbl_pr_title.setVisible(false);					// 가운데 로고 안보이게
				
			
			// 게임머니 최신화
			for (int i = 0; i < 4; i++) {
				if(!gi[RN][0][i].equals("null")){
					HMsidebar_p.get(i+21).setText(gi[RN][2][i]+" 만원");
					HMp_icon.get((i+1)*100).setVisible(true);
				}
			}
				
			
			if(gi[RN][0][0].equals(DTO.getId())) {				//방장이면
				try {
					DTO.setCode("T0010");
					oos.writeObject(DTO);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("[클라] [T0009] 게임 시작 기능 완료");
	}
	
	public void c_T0009_send(DTO DTO, ObjectOutputStream oos) {
		try {
			System.out.println("[클라] [T0009] 게임 시작 기능 시작");
			DTO.setCode("T0009");		//객체에 코드 저장
	        oos.writeObject(DTO);	 	//toss 발송
	        oos.flush(); 						//oos 비우기
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

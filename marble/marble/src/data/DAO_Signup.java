package data;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DAO_Signup {

	private Connection con;
	int rowcnt;
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String Cryptogram;
	SHA256 sha256 = new SHA256();
	
	public DAO_Signup(DTO DTO){
		con = new conn(DTO).getConnection(); 	
	}
	
	public DTO signup(DTO DTO)  {	
		// PASSWORD 암호화 시작 =========================
		try {
			Cryptogram = sha256.encrypt(DTO.getPw());
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("DAO_Login; login 암호화 에러");
			e1.printStackTrace();
		}
		// PASSWORD 암호화 완료 =========================
		
		
		String sql = "SELECT * FROM MARBLE_MEMBER WHERE member_id = ?";
				
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, DTO.getId());
			rowcnt = ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
    	}finally {
    		//try { rs.close(); } catch (Exception e) { /* ignored */ }
		    //try { ps.close(); } catch (Exception e) { /* ignored */ }
		    //try { con.close(); } catch (Exception e) { /* ignored */ }
    	}	
		if(rowcnt == 0) { // 아이디가 존재하지 않을경우
			if(DTO.getId().equals("")||DTO.getPw().equals("")||DTO.getNote().equals("")) {		//공란이 있다면 getNote = pwre 
				System.out.println("누락 항목 있음");
				//JOptionPane.showMessageDialog(null, "ID, PW, PW(re) 모두 작성해주세요.", "BLACKJAC", JOptionPane.ERROR_MESSAGE, null);
				DTO.setIntnote(2);
				return DTO;
			}else {																									// 공란이 없다면
				if(DTO.getPw().equals(DTO.getNote())){													// PW와 PWRE가 일치한다면
					sql = "INSERT INTO marble_member VALUES (?, ?, 0, 0)";						// 가입 진행

					try {
						ps=con.prepareStatement(sql);
						ps.setString(1, DTO.getId());
						ps.setString(2, Cryptogram);
						rowcnt = ps.executeUpdate();
					} catch(SQLException e){
						e.printStackTrace();
						return DTO;
					}finally {
//						try { rs.close(); } catch (Exception e) { /* ignored */ }
//					    try { ps.close(); } catch (Exception e) { /* ignored */ }
//					    try { con.close(); } catch (Exception e) { /* ignored */ }
					}
					System.out.println("가입 완료");
					//JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다.");
					DTO.setIntnote(0);
					return DTO;
				}else {
					//JOptionPane.showMessageDialog(null, "PW, PW(re)가 일치하지 않습니다.", "BLACKJAC", JOptionPane.ERROR_MESSAGE, null);
					System.out.println("IDPW 불일치");
					DTO.setIntnote(1);
					return DTO;
				}
			}
		}else {
			//아이디가 존재
			System.out.println("아이디 중복");
			//JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다.");
			DTO.setIntnote(3);
			return DTO;
		}
	}
}


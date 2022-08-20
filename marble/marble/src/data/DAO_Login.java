package data;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Login {
		
		private Connection con;													// 접속 객체 미리 준비
		int rowcnt;																	// DB 조회 결과 라인 수 출력
		PreparedStatement ps = null;											// SQL 쿼리 실행 객체
		ResultSet rs = null;														// 실행 결과 저장 객체
		
		String Cryptogram;														// 비밀번호를 암호화한 데이터 값 저장 변수
		SHA256 sha256 = new SHA256();									// 비밀번호 암호화(SHA256) 객체 준비
		
		public DAO_Login(DTO DTO) {
			con = new conn(DTO).getConnection();						// conn.java 에서 준비한 접속 객체를 활용하여 DB 접속 시도
		}

		public int login(String id, String pw)  {	

		// PASSWORD 암호화 시작 =========================
		try {
			Cryptogram = sha256.encrypt(pw);
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("DAO_Login; login 암호화 에러");
			e1.printStackTrace();
		}
		// PASSWORD 암호화 완료 =========================
		
		if(id.equals("") || pw.equals("")) {// ID or PW 공란 에러출력
			System.out.println("아이디, 패스워드 모두 입력해주세요.");
			return 2;
		}		
		
		// 로그인 시작 =====================================
		String sql = "SELECT * FROM MARBLE_MEMBER WHERE member_id = ? AND member_pw = ?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, Cryptogram);
			rowcnt = ps.executeUpdate(); // ID/PW 일치 체크
		} catch(SQLException e){
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!Login_DAO.login' ID/PW 에러");
			return 3;
    	}finally {
    		try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { ps.close(); } catch (Exception e) { /* ignored */ }
    	}	
		// 로그인 종료 =====================================
		
		
		if(rowcnt==1){
			System.out.println("Login_DAO.login; 로그인성공");
			return 1;
		}else{
			System.out.println("아이디, 패스워드를 확인해 주세요.");
			return 4;
		}		
	}
}

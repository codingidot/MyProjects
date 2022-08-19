package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class conn {
	
	private Connection con;																				// 18 번 라인에서 사용할 접속 객체 미리 준비
	
	public Connection getConnection() {															// 접속이 필요한 곳에서 해당 메소드 출력시 아래의 준비한 con 리턴									
		return con;
	}
	
	public conn(DTO DTO){																				// con 준비
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");										//오라클 드라이버 로드(oracle8.jar) 
			String address = "jdbc:oracle:thin:@shared00.iptime.org:32779/XEPDB1";		// 아랫줄 con 인자로 넣을 IP주소+포트+SIDorSNAME 값 준비
			con=DriverManager.getConnection(address,"MARBLE","1");						// 위에서 준비한 DB의 address, 아이디, 암호를 인자로 준비
		} catch (Exception e) {
			System.out.println("conn.conn; DB 접속 에러");
			e.printStackTrace();
		}
		System.out.println("conn.conn; DB접속 성공");
	}		
} // Conn class-end



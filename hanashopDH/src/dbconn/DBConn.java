package dbconn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

	
	private Connection con;
	
	public Connection getConnection() {
		return con;
	}
	
	public DBConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@shared00.iptime.org:32779/XEPDB1","3JO","hana");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

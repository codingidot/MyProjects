package ScinfoDBConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//src-마오-class - 페키지명은 telinfoDBConn, class명은 TelInfoDBConn
//main x
public class ScInfoDBConn { //TelInfoDBConn.java
private Connection con; //공통 접속객체 를 위하여 (전역변수)

public Connection getConnection() { //메소드3형식 // getter
return con; //접속객체 return
}

//TelInfoDBConn를 new해서 객체를 만드는 순간에 여기를 제일 먼저옴
public ScInfoDBConn() // 디폴트생성자로 개발자가 만듣것
throws ClassNotFoundException, SQLException {

Class.forName("oracle.jdbc.driver.OracleDriver");
//오라클 드라이버 에러를 방지하기위해
//프로젝트명 - 마오 - biuld path....-add external jar - ojdbc8.jar
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
}//
}//TelInfoDBConn클래스 -end

package ScinfoDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import ScinfoDBConn.ScInfoDBConn;
import ScinfoVO.ScInfoVO;


public class ScInfoDAO {//TelInfoDAO.java --dao:메소드의 집합

	//이제까지는 insert.java, select.java, update.java, delete.java 로 사용
		//Connection 4개, PreparedStatement 4개, ResultSet ..개씩 있어야함	
		// ====> 공통인 위의 것을 전역으로 사용해서 편리하게 효율적으로 사용
		private boolean idCheck = false;
		private static Connection con;
		//결과적으로 con에는 DBConn 접속객체가 들어간다
		//con은 telinfoDBConn.TelInfoDBConn 에 있음
		
		static PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		public ScInfoDAO() // 생성자
				throws ClassNotFoundException, SQLException	{
			con = new ScInfoDBConn().getConnection(); 
			//드라이버 loadign, 실제 hr로 접속을 하여 생긴 con을 가지고 옴
			//접속객체 get -> con
			
		}
		
		//아이디값 삽입 생성자
		public boolean insert_member(String mem_id, String mem_pw) {
			String sql = "insert into member_table values(?,?)";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				pstmt.setString(2, mem_pw);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("insert");
				System.out.println(e.getMessage());
				return false;

			}
			return true;
		}
		
		//아이디값 중복확인 생성자
		public boolean check_Id (String id) throws SQLException {
			
			Connection con = null;
			String sql = "SELECT mem_id FROM member_table";
			PreparedStatement pstmt = this.con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getString("mem_id").equals(id)) {
					idCheck = true;
				}
			}
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con != null) con.close();
			
			return idCheck;
			
			
			
		}
		
		//로그인 시도 생성자
		public boolean Correct_Id (String id, String pw) throws SQLException {
			String sql = "SELECT mem_id, mem_pw FROM member_table";
			PreparedStatement pstmt = this.con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				if(rs.getString("mem_id").equals(id)) {
					if(rs.getString("mem_pw").equals(pw)) {
						idCheck = true;
					}
				}
			}
			
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( con != null ) con.close();
			
			return idCheck;
			
			
		}
	
	
		 //CRUD 를 여기에 작성  
		//전체 출력을 위해 메소드 정의문 작성  - R
									//getAllInfo() 전체를 대표하는 메소드
		public ArrayList<ScInfoVO> schedule(int year, int month, int day) throws SQLException{
			ArrayList<ScInfoVO> tiarray = new ArrayList<ScInfoVO>();//generic
			String sql=null;
			if(month==0 && day==0) {
				sql="select * from myschedule "
						+" where substr(sc_date,1,2)=?"
						+" order by sc_id";
			} else if(month!=0 && day==0) {
				sql="select * from myschedule "
						+ " where substr(sc_date,1,2)=? "
						+ " and substr(sc_date,4,2)=?  "
						+ " order by sc_id";
			} else if(month!=0 && day!=0 ) {
				sql="select * from myschedule"
						+ " where substr(sc_date,1,2)=?"
						+ " and substr(sc_date,4,2)=? "
						+ " and substr(sc_date,7,2)=? "
						+ " order by sc_id";

			}

			pstmt = con.prepareStatement(sql);
			if(month==0 && day==0) {
				pstmt.setInt(1, year);
			}  else if(month!=0 && day==0) {
				pstmt.setInt(1, year);
				pstmt.setInt(2, month);
			}  else if(month!=0 && day!=0 ) {
				pstmt.setInt(1, year);
				pstmt.setInt(2, month);
				pstmt.setInt(3, day);
			}
		
			rs = pstmt.executeQuery();
			while(rs.next()) {
			int id = rs.getInt("sc_id");
			String date = rs.getString("sc_date");
			int start = rs.getInt("start_time");
			int finish = rs.getInt("finish_time");
			String contents=rs.getString("sc_contents");

			ScInfoVO tiv=new ScInfoVO(id, date, start,finish, contents);
			//VO객체에 넣기
			tiarray.add(tiv); //VO객체 ->collection에 넣기
		}//while-end
			return tiarray;  //메소드 3형식
	}//schedule()-end
		
		// 자료입력 메소드		
		public static boolean insert 
				(int sc_id, String sc_date, int start_time, int finish_time, String sc_contents) {
			String sql = "insert into myschedule values(sc_seq.nextval,to_date(?),?,?,?)";
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, sc_date);
					pstmt.setInt(2,start_time);
					pstmt.setInt(3, finish_time);
					pstmt.setString(4, sc_contents);
					pstmt.executeUpdate();
				}catch(SQLException e) {
					System.out.println("insert Exception");
					System.out.println(e.getMessage());
					return false;
				}
				return true;
		}
		
		public int  delete_schedule(int id) throws SQLException{
			String sql="DELETE myschedule WHERE SC_ID=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result=pstmt.executeUpdate();
			return result;
			
				
			}
		
	
		
		public ArrayList<ScInfoVO> schedule1(int year, int month, int day) throws SQLException{
			ArrayList<ScInfoVO> tiarray = new ArrayList<ScInfoVO>();//generic
			String sql=null;
		
			while(rs.next()) {
			int id = rs.getInt("sc_id");
			String date = rs.getString("sc_date");
			int start = rs.getInt("start_time");
			int finish = rs.getInt("finish_time");
			String contents=rs.getString("sc_contents");

			ScInfoVO tiv=new ScInfoVO(id, date, start, finish, contents);
			//VO객체에 넣기
			tiarray.add(tiv); //VO객체 ->collection에 넣기
		}//while-end
			return tiarray;  //메소드 3형식
	}//schedule()-end
			
		
		//업데이트 부분
		public static boolean update(int sc_id,String sc_date,int start_time, int finish_time,String sc_contents){
			String sql = "update myschedule set "
											+ "sc_id= ?,"
											+ "sc_date= ?,"
											+ "start_time= ?,"
											+ "finish_time= ?,"
											+ "sc_contents= ?"
											+ "where sc_id = ?";
								
			try {				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, sc_id);	
				pstmt.setString(2, sc_date);
				pstmt.setInt(3, start_time);
				pstmt.setInt(4, finish_time);
				pstmt.setString(5, sc_contents);
				pstmt.setInt(6, sc_id);				
				pstmt.executeUpdate();
	
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("update Exception");
				System.out.println(e.getMessage());
				return false;
		
			}
			return true;
			
}
		
		
		
		
		
		
		
		
	
	
	
}
package qanswer.qanswerDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import member.memberVO.MemberVO;
import notice.noticeVO.noticeVO;
import qanswer.qanswerVO.qanswerVO;
import review.reviewVO.ReviewVO;

public class qanswerDAO {

	   private Connection con;
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   
	   public qanswerDAO() throws ClassNotFoundException, SQLException {
	      con = new DBConn().getConnection();
	   }
	   
	   public void pstmtClose() throws SQLException {
	      if(ps != null) {
	         ps.close();
	      }
	   }
	   
	   public void getAllInfoClose() throws SQLException {
	      if(rs != null) {
	         rs.close();
	      }
	      if(ps != null) {
	         ps.close();
	      }
	      if(con != null) {
	         con.close();
	      }
	   }

	   
	   
	   public boolean isTheredelivery() throws SQLException {
	         
		      String sql="SELECT  * FROM  qanswer";
		      
		      ps=con.prepareStatement(sql);
		      rs=ps.executeQuery();
		      System.out.println("isTheredelivery 실행");
		      
		      if(rs.next()) {
		         return true;
		      }
		      
		      return false;
		   }
	   
	   
	public ArrayList<qanswerVO> qanswer() throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<qanswerVO> tiarray = new ArrayList<qanswerVO>();
		String sql = "select * from Qanswer order by q_no";
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("while 안에");
			int no = rs.getInt("q_no");
			String id = rs.getString("q_id");
			String title = rs.getString("q_title");
			String content = rs.getString("q_content");
			String answer = rs.getString("q_answer");
			
			System.out.println(no+id+title);
		
			qanswerVO tiv = new qanswerVO(no,id,title,content,answer);
			
			tiarray.add(tiv);
		}
		
		return tiarray;
	}

	public qanswerVO qanswer(int no8) throws SQLException {
		String sql = "select * from qanswer where q_no = ?";
		qanswerVO tiv=null;
		ps = con.prepareStatement(sql);
		ps.setInt(1, no8);
		rs = ps.executeQuery();
		while(rs.next()) {
			int num = rs.getInt("q_no");
			String id = rs.getString("q_id");
			String title = rs.getString("q_title");	
			String content = rs.getString("q_content");
			String answer = rs.getString("q_answer");
	
			tiv = new qanswerVO(num,id,title,content,answer);
			
			

			System.out.println(num+id+title+content);

		}
	
		return tiv;

	}

	public boolean qanswerwrite(int no11,String id1, String title1, String content1) throws SQLException {
		
		
		 String sql="INSERT INTO qanswer (q_no,q_id,q_title,q_content) values(?,?,?,?)";
	 System.out.println(id1+"\t"+title1+"\t"+content1);
		ps = con.prepareStatement(sql);
		
		ps.setInt(1, no11);
		ps.setString(2, id1);
		ps.setString(3, title1);
		ps.setString(4, content1);
		
	
		System.out.println("executeUpdate 시작");
		if(ps.executeUpdate()!=0) {
				ps.close();
				System.out.println("성공");
			return true;
		}else {
			System.out.println("실패");
			return false;
		
		}
	}
	
	
	   public int get_no() throws SQLException {
		   System.out.println("q_no  메소드 시작");
		   String sql="";
		   int q_no=0;
		   sql="select * from qanswer";
		   ps=con.prepareStatement(sql);
		   rs=ps.executeQuery();
		   
		   while(rs.next()) {
			   if(rs.getInt("q_no")>q_no) {
				   q_no=rs.getInt("q_no");
			   }
		   }
		   return q_no+1;
	   }

	public boolean qanswerwrite(String no12, String answer) throws SQLException {
		
		System.out.println(no12+answer);
		
		
		 String sql="update qanswer set  q_answer=? where q_no=?";

		ps = con.prepareStatement(sql);
		ps.setString(1, answer);
		ps.setString(2, no12);
		ps.executeUpdate();
		
		return true;
		
	}

	   
	}

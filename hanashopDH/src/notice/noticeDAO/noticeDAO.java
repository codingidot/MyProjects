package notice.noticeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import member.memberVO.MemberVO;
import notice.noticeVO.noticeVO;
import review.reviewVO.ReviewVO;

public class noticeDAO {
   
   private Connection con;
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   public noticeDAO() throws ClassNotFoundException, SQLException {
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


   public ArrayList<noticeVO> notice(int start, int end) throws SQLException{
		ArrayList<noticeVO> tiarray = new ArrayList<noticeVO>();
		String sql = "select * from (select rownum rn,tt.* from (select * from notice order by n_no ) tt) where rn >="+start+" and rn <="+end;
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("n_no");
			String title = rs.getString("n_title");
			String content = rs.getString("n_content");
		
			noticeVO tiv = new noticeVO(id,title,content);
			
			tiarray.add(tiv);
		}
		
		return tiarray;
	}

   public noticeVO notice(int no6) throws SQLException {
	   
		   
		      String sql = "SELECT * FROM notice WHERE n_no=?";
		      noticeVO tiv=null;
		      
		      ps = con.prepareStatement(sql);
		      ps.setInt(1, no6);
		      rs = ps.executeQuery();
		      
		     
		      while(rs.next()) {
		         int n_no = rs.getInt("n_no");
		         String n_title = rs.getString("n_title");
		         String n_content = rs.getString("n_content");
		       
		        tiv = new noticeVO(n_no,n_title,n_content);
		        
		        System.out.println(n_no+n_title+n_content);
		
		      }
		      return tiv;
		   }
   
   public int get_no() throws SQLException {
	   System.out.println("n_no  메소드 시작");
	   String sql="";
	   int n_no=0;
	   sql="select * from notice";
	   ps=con.prepareStatement(sql);
	   rs=ps.executeQuery();
	   
	   while(rs.next()) {
		   if(rs.getInt("n_no")>n_no) {
			   n_no=rs.getInt("n_no");
		   }
	   }
	   return n_no+1;
   }
   
   
   public void insert_notice(int no,String title,String content) throws SQLException {
	   System.out.println("공지사항 등록 메소드 시작");
	   String sql="";
	   sql="insert into notice values(?,?,?)";
	   ps=con.prepareStatement(sql);
	   ps.setInt(1, no);
	   ps.setString(2, title);
	   ps.setString(3, content);
	   System.out.println("공지사항 등록 sql문 실행");
	   ps.executeUpdate();
	   
	   System.out.println("성공");
	   
	   
	   
   }
   
   public boolean isThereNotice() throws SQLException {
       
	      String sql="SELECT  * FROM notice";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isThereBasket 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }

	public boolean noticedelete(int no) throws SQLException {
		 String sql="delete from notice where n_no=?";
		 System.out.println(no);
		 
			ps = con.prepareStatement(sql);			
			
		
			ps.setInt(1,no);
			 ps.executeUpdate();
			 
			 
			return true;
	}

	 
	   public boolean updatenotice(String title,String content, int no) throws SQLException {
		
		   String sql="update notice set n_title=?, n_content=? where n_no=?";
			 System.out.println(title+"\t"+ content+"\t" + no);
			 
				ps = con.prepareStatement(sql);			
				
				ps.setString(1, title);
				ps.setString(2, content);
				ps.setInt(3,no);
				 ps.executeUpdate();
				 
				 
				return true;
	}
	   
	   
	   
	   public int total_count() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM notice";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	   
}
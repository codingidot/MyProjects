package likey.likeyDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import likey.likeyVO.LikeyVO;
import product.productVO.ProductVO;

public class LikeyDAO {
	   private Connection con;
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   
	   public LikeyDAO() throws ClassNotFoundException, SQLException {
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
	   
	   
	   
	   //여기서부터 메소드 시작
	   
	   
	   

	   //큰수 구하기
	   public int get_no() throws SQLException {
		   System.out.println("get_no  메소드 시작");
		   String sql="";
		   int l_no=0;
		   sql="select * from likey";
		   ps=con.prepareStatement(sql);
		   rs=ps.executeQuery();
		   
		   while(rs.next()) {
			   if(rs.getInt("l_no")>l_no) {
				   l_no=rs.getInt("l_no");
			   }
		   }
		   return l_no+1;
	   }
	   
	   
	   
	   
	   //해당리뷰에 좋아요 눌렀는지 확인하는 method
	   /*
	   public int qnaCheck(int pno) throws SQLException{
		   System.out.println("qnaCheck method 실행");
		   int count = 0;
		   String sql = "SELECT * FROM qna WHERE q_pno = ?";
		   ps = con.prepareStatement(sql);
		   ps.setInt(1, pno);
		   rs = ps.executeQuery();
		   if(rs.next()) {
			   count = 1; // 제품에 해당하는 qna가 있다면
		   }
		   return count;
	   }
	    */
	   
	   
	   public String checkLike(int pno, String id, int rno) throws SQLException{
		   System.out.println("checkLikeDAO도착");
		   String check = "0";
		   
		   String sql = "SELECT * FROM likey WHERE l_pno=? AND l_id=? AND l_rno=?";
		   ps = con.prepareStatement(sql);
		   ps.setInt(1, pno);
		   ps.setString(2,id);
		   ps.setInt(3, rno);
		   
		   rs=ps.executeQuery(); 
		   
		   //sql문 결과값이 있으면 if문 실행 ??
		   if(rs.next()) {
			   check = "1";
		   }
		   // 값이 하나라도 있으면 1을 리턴
		   return check;
		   
	   }
	   
	   // checkLike에 등록되어 있지않으면 좋아요 카운트 됨과 동시에 정보저장
	   public int regist(int no, int pno, String id, int rno) throws SQLException{
		   System.out.println("registDAO도착");
		   
		   String sql = "INSERT INTO likey VALUES(?,?,?,?)";
		   
		   ps = con.prepareStatement(sql);
		   ps.setInt(1, no);
		   ps.setInt(2, pno);
		   ps.setString(3, id);
		   ps.setInt(4, rno);
		   
		   //1을 반환하게 될거임
		   return ps.executeUpdate();
	   }
	   
}

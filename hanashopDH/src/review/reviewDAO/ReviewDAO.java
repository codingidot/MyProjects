package review.reviewDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import review.reviewVO.ReviewVO;

public class ReviewDAO {
   
   private Connection con;
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   public ReviewDAO() throws ClassNotFoundException, SQLException {
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
   
   // 아아디에 따른 리뷰목록 보기
   public ArrayList<ReviewVO> review(String id4, int start, int end) throws SQLException{
      ArrayList<ReviewVO> tiarray = new ArrayList<ReviewVO>();
      String sql="select * from (select rownum rn,tt.* from (select * from review where r_id='"+id4+"') tt) where rn >="+start+ " and rn <="+end;
      
      ps = con.prepareStatement(sql);
     
      rs = ps.executeQuery();
      while(rs.next()) {
         int num = rs.getInt("r_no");
         String id = rs.getString("r_id");
         int pno = rs.getInt("r_pno");
         String title = rs.getString("r_title");   
         String content = rs.getString("r_content");
         int star = rs.getInt("r_star");
         int like = rs.getInt("r_like");
         int dislike = rs.getInt("r_dislike");
         String image = rs.getString("r_image");
         
         ReviewVO tiv = new ReviewVO(num,id,pno,title,content,star, like, dislike , image);
      
         
         
         tiarray.add(tiv);

         System.out.println(num+id+pno+title+content+star);
         
         
         
      }
      return tiarray;
      
   
   }
   
   
   // 제품번호에 따른 리뷰보기
   public ReviewVO review2(int no) throws SQLException{
      
      String sql = "select * from review where r_no = ?";
      ReviewVO tiv=null;
      
      ps = con.prepareStatement(sql);
      ps.setInt(1, no);
      rs = ps.executeQuery();
      while(rs.next()) {
         int num = rs.getInt("r_no");
         String id = rs.getString("r_id");
         int pno = rs.getInt("r_pno");
         String title = rs.getString("r_title");   
         String content = rs.getString("r_content");
         int star = rs.getInt("r_star");
         int like = rs.getInt("r_like");
         int dislike = rs.getInt("r_dislike");
         String image = rs.getString("r_image");
         
        tiv = new ReviewVO(num,id,pno,title,content,star, like, dislike , image);
      
         
         


         System.out.println(num+id+pno+title+content+star);
         
         
         
      }
      return tiv;
      
   
   }
   
	// 해당 qno에 해당하는 리뷰가 있는지 없는지 여부확인
	public int reviewCheck(int pno) throws SQLException{
		System.out.println("reviewcheck 메소드 실행");
		int count = 0;
		String sql = "select * FROM review WHERE r_pno = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, pno);
		rs = ps.executeQuery();
		if(rs.next()) {
			count = 1; //리뷰가 있다면
		}
		
		return count; //리뷰가 없다면
	}
	
	
	
	
	
	
   
   
   public boolean updatereview(String title,String content, int no) throws SQLException {
	
	   String sql="update review set r_title=?, r_content=? where r_no=?";
		 System.out.println(title+"\t"+ content+"\t" + no);
		 
			ps = con.prepareStatement(sql);			
			
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3,no);
			 ps.executeUpdate();
			 
			 
			return true;
}

	public boolean reviewdelete(int no) throws SQLException {
		
		 String sql="delete from review where r_no=?";
		 System.out.println(no);
		 
			ps = con.prepareStatement(sql);			
			
		
			ps.setInt(1,no);
			 ps.executeUpdate();
			 
			 
			return true;
	}
	
	//해당 제품에 대한 list를 출력하되, 상위 10개만 노출되게 하는 method
		public ArrayList<ReviewVO> getList(int start, int p_no) throws SQLException{
//			String sql = "select * from board order by seq desc";
			
			String sql = "select * from (select rownum rn,tt.* from (select * from review order by r_no desc) tt) where rn >= ? and rn <= ? and r_pno=?";
			System.out.println("getList 메소드");

			ArrayList<ReviewVO> list = null;
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, start + 1);
			ps.setInt(2, start + 5);
			ps.setInt(3, p_no);
			rs = ps.executeQuery();
			System.out.println("rs값 : " + rs);
			list = this.makeList(rs);
			
			return list;
		}
		
		
	
	
	

		//해당 제품에 대한 list를 출력하되, 상위 10개만 노출되게 하는 method 좋아요순
		public ArrayList<ReviewVO> getList2(int start, int pno) throws SQLException{
//			String sql = "select * from board order by seq desc";
			
			String sql = "select * from (select rownum rn,tt.* from (select * from review order by r_like desc) tt) where rn >= ? and rn <= ? and r_pno=?";
			System.out.println("getList 메소드");

			ArrayList<ReviewVO> list = null;
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, start + 1);
			ps.setInt(2, start + 5);
			ps.setInt(3, pno);
			rs = ps.executeQuery();
			System.out.println("rs값 : " + rs);
			list = this.makeList(rs);
			
			return list;
		}
	
	
	
		// 리스트를 만드는 method
		private ArrayList<ReviewVO> makeList(ResultSet rs) throws SQLException {
			ArrayList<ReviewVO> list = new ArrayList<ReviewVO>();

			System.out.println("makelist 메소드");

				while (rs.next()) {
					int r_no = rs.getInt("r_no");
					String r_id = rs.getString("r_id");
					int r_pno = rs.getInt("r_pno");
					String r_title = rs.getString("r_title");
					String r_content = rs.getString("r_content");
					int r_star = rs.getInt("r_star");
					int r_like = rs.getInt("r_like");
					int r_dislike = rs.getInt("r_dislike");
					String r_image = rs.getString("r_image");
					
					ReviewVO rvo = new ReviewVO(r_no, r_id, r_pno, r_title, r_content, r_star, r_like, r_dislike , r_image);
					list.add(rvo);
				}
			

			return list;

		}
	
		// 리뷰 갯수보는 METHOD
		public int getTotal(int p_no) throws SQLException {
			String sql = "select count (*) from review WHERE r_pno=?";
//			String sql = "select count(*) from review";
			System.out.println("getTotal 메소드");

			int su = 0;
			
				ps = con.prepareStatement(sql);
				ps.setInt(1, p_no);
				rs = ps.executeQuery();
				if(rs.next()) {
					su = rs.getInt("count(*)");
				}
			

			System.out.println("rs값 : " + rs);
			System.out.println("su값 : " + su);
			return su;
		}
	
	
		//리뷰 등록하는 method
		public int reviewWrite(String title, String id, int pno ,int star, String content, String image ) throws SQLException {
			String sql = "INSERT INTO REVIEW VALUES(review_seq.NEXTVAL,?,?,?,?,?,0,0,?)";
			System.out.println("reviewWrite메소드");
			
			
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, pno);
			ps.setString(3, title);
			ps.setString(4, content);
			ps.setInt(5, star);
			ps.setString(6, image);
			
			//1을 반환할것임
			return ps.executeUpdate();
			
		}
		
		
		// 리뷰 추천버튼
		public int reviewUpbutton(int r_no) throws SQLException{
			System.out.println("dao도착");
			String sql = "UPDATE Review SET r_like = r_like+1 WHERE r_no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_no);
			return ps.executeUpdate();
			
		}	
		
		
		// 리뷰 비추천버튼
		public int reviewDownbutton(int r_no) throws SQLException{
			System.out.println("dao도착");
			String sql = "UPDATE Review SET r_dislike = r_dislike+1 WHERE r_no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_no);
			
			return ps.executeUpdate();
		}
		
		//리뷰 삭제하기
		public int reviewDelete(int r_no) throws SQLException {
			System.out.println("dao도착");
			String sql = "DELETE FROM REVIEW WHERE r_no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, r_no);
			
			return ps.executeUpdate();
		}
		
		//리뷰 수정
		public int reviewUpdate(String title, int star, String content, String image, int r_no) throws SQLException {
			String sql = "UPDATE REVIEW SET r_title=?, r_content=?, r_star=?, r_image=? where r_no=?";
			System.out.println("reviewWrite메소드");
			
			
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, star);
			ps.setString(4, image);
			ps.setInt(5, r_no);
			
			//1을 반환할것임
			return ps.executeUpdate();
			
		}
	
	 public boolean isThereReviewlist(String id1) throws SQLException {
         
	      String sql="SELECT  * FROM review WHERE r_ID='"+id1+"'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isThereReviewlist 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }

	 public int total_count(String id) throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM review where r_id='"+id+"'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }

	 public void delete_product4(String [] delete_list1) throws SQLException {
         //상품 목록에서 지우기
         
         String num="";
         for(int i=0; i<delete_list1.length;i++) {
            if(i%2!=0) {
            num+=","+delete_list1[i];
            }else {
               num+=delete_list1[i];
            }
         }
         System.out.println("삭제할 상품 번호: "+num);
         
         String sql="delete from review where r_pno in("+num+")";
         
         ps=con.prepareStatement(sql);
         if(ps.executeUpdate()!=0) {
            num="";
            System.out.println("review 상품 삭제 성공");
            
         }else {
               
         System.out.println("review 상품 삭제 실패했거나 review 테이블에 해당 review가 없음");
         }   
         
      }

}
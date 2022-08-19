package wishlist.wishlistDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import wishlist.wishlistVO.wishlistVO;

public class wishlistDAO {
   
   
      private Connection con;
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      public wishlistDAO() throws ClassNotFoundException, SQLException {
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

   public ArrayList<wishlistVO> getAllInfo(String id1, int start, int end) throws SQLException {
      //아이디에 해당하는 wishlist 모든 값 출력
      ArrayList<wishlistVO> barray=new ArrayList<wishlistVO>();
      String sql="select * from (select rownum rn,tt.* from (select * from wishlist where w_id='"+id1+"') tt) where rn >="+start+ " and rn <="+end;	
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		System.out.println("getAllInfo 실행");
         System.out.println(sql);
      
         while(rs.next()) {
            int key=0;
            String id=rs.getString("w_id");
            String name=rs.getString("w_pname");
            int price=rs.getInt("w_price");
            int count=rs.getInt("w_count");
            String size=rs.getString("w_size");
            int no=rs.getInt("w_no");
   
            wishlistVO bvo=new wishlistVO(no,id, name, price, count,size);
            
            if(name==null) {
               name="";
            }
            if(size==null) {
               size="";
            }
            
            for(wishlistVO bvo1:barray) {
               if(bvo1.getW_pname().equals(name)&&size.equals(bvo1.getW_size())) {
                  
                     key=1;
                  bvo1.setW_count(bvo1.getW_count()+count);                  
               }
            }
            
            if(key!=1) {
               barray.add(bvo);
               System.out.println("ArrayList에 추가");
            }
         }
         
         return barray;
   }   
   

   public void delete_wishlist(String [] delete_list) throws SQLException {
         
         ArrayList<String> arlist=new ArrayList<String>();
         String[] split;
         String sql="";
         for(int i=0; i<delete_list.length;i++) {
            split=delete_list[i].split(",");
            System.out.println("삭제할 아이디 상품명 사이즈 "+split[0]+"\t"+split[1]+"\t"+split[2]+"\t");
            sql="delete from wishlist where w_id='"+split[0]+"' and w_pname='"+split[1]+"' and  w_size='"+split[2]+"'";
            arlist.add(sql);
         }
         
         for(String sql1:arlist) {
         ps=con.prepareStatement(sql1);
         ps.executeUpdate();
         ps.close();
         }
         
         arlist=null;
         sql="";
         //delete_list=null;
         
         
      }
   
   
   public void basketsend_wishlist(String [] delete_list) throws SQLException {
      
      ArrayList<String> arlist=new ArrayList<String>();
      String[] split;
      String sql="";
      for(int i=0; i<delete_list.length;i++) {
         split=delete_list[i].split(",");
         System.out.println("보낼 아이디 상품명 사이즈 "+split[0]+"\t"+split[1]+"\t"+split[2]+"\t");
         sql="insert into basket (b_id,b_name,b_size,b_count,b_price,b_no) values('"+split[0]+"','"+split[1]+"','"+split[2]+"',"+split[3]+","+split[4]+","+split[5]+")";
         arlist.add(sql);
         System.out.println(sql);
      }
      
      for(String sql1:arlist) {
      ps=con.prepareStatement(sql1);
      ps.executeUpdate();
      ps.close();
      }
      
      arlist=null;
      sql="";
      //delete_list=null;
      
      
   }
   
   
   public boolean isThereWishlist(String id1) throws SQLException {
       
	      String sql="SELECT  * FROM wishlist WHERE W_ID='"+id1+"'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isThereWishlist 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }
   
   
   public boolean insert_wish(String id, String name, int price, int count, String size, int no) throws SQLException {
		System.out.println("insert 메소드 시작");
		String sql="INSERT INTO WISHLIST VALUES(?,?,?,?,?,?)";
		
		ps=con.prepareStatement(sql);
		System.out.println("insert sql실행");
		
		  ps.setString(1, id); 
		  ps.setString(2, name); 
		  ps.setInt(3, price);
		  ps.setInt(4, count); 
		  ps.setString(5, size);
		  ps.setInt(6, no);
		 
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
   
   
   public void delete_wish(String [] delete_list) throws SQLException {
	  //아이디,상품명,사이즈,개수,상품번호
		ArrayList<String> arlist=new ArrayList<String>();
		String[] split;
		String sql="";
		for(int i=0; i<delete_list.length;i++) {
			split=delete_list[i].split(",");
			System.out.println("삭제할 아이디 상품명 사이즈 "+split[0]+"\t"+split[1]+"\t"+split[2]+"\t");
			sql="delete from wishlist where w_id='"+split[0]+"' and w_pname='"+split[1]+"' and  w_size='"+split[2]+"'";
			arlist.add(sql);
		}
		
		for(String sql1:arlist) {
		ps=con.prepareStatement(sql1);
		ps.executeUpdate();
		System.out.println("위시리스트 삭제완료");
		ps.close();
		}
		
		arlist=null;
		sql="";
		//delete_list=null;
		
		
	}
   
   public int total_count(String id1) throws SQLException {
	   int total=0;
	   String sql = "SELECT * FROM wishlist where w_id='"+id1+"'";
	      
 	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      
	  while(rs.next()) {
		  total+=1;
	  }
	  
	  return total;
   }
   
   }
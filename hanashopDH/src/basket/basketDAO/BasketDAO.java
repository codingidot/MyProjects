package basket.basketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basket.basketVO.BasketVO;
import dbconn.DBConn;

public class BasketDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BasketDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
		System.out.println("DAO 생성자");
	}
	
	public ArrayList<BasketVO> getAllInfo(String id1,int start,int end) throws SQLException{
		ArrayList< BasketVO> barray=new ArrayList<BasketVO>();
		//String sql="SELECT  * FROM BASKET WHERE B_ID='"+id1+"'";
		String sql="select * from (select rownum rn,tt.* from (select * from basket where b_id='"+id1+"') tt) where rn >="+start+ " and rn <="+end;	
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println("getAllInfo 실행");
			while(rs.next()) {
				int key=0;
				String id=rs.getString("b_id");
				String name=rs.getString("b_name");
				int price=rs.getInt("b_price");
				int count=rs.getInt("b_count");
				String size=rs.getString("b_size");
				int no=rs.getInt("b_no");
	
				BasketVO bvo=new BasketVO(id, name, price, count,size,no);
				
				if(name==null) {
					name="";
				}
				if(size==null) {
					size="";
				}
				
				for(BasketVO bvo1:barray) {
					if(bvo1.getB_name().equals(name)&&size.equals(bvo1.getB_size())) {
						
							key=1;
						bvo1.setB_count(bvo1.getB_count()+count);						
					}
				}
				
				if(key!=1) {
				   barray.add(bvo);
				   System.out.println("ArrayList에 추가");
				}
			}
			
			return barray;
			
	}
	
	public boolean insert_basket(String id, String name, int price, int count, String size, int no) throws SQLException {
		System.out.println("insert 메소드 시작");
		String sql="INSERT INTO BASKET VALUES(?,?,?,?,?,?)";
		
		pstmt=con.prepareStatement(sql);
		System.out.println("insert sql실행");
		
		  pstmt.setString(1, id); 
		  pstmt.setString(2, name); 
		  pstmt.setInt(3, price);
		  pstmt.setInt(4, count); 
		  pstmt.setString(5, size);
		  pstmt.setInt(6, no);
		 
		System.out.println("executeUpdate 시작");
		if(pstmt.executeUpdate()!=0) {
				pstmt.close();
				System.out.println("성공");
			return true;
		}else {
			System.out.println("실패");
			return false;
		
		}
	
	}
	
	
	public void delete_basket(String [] delete_list) throws SQLException {
		
		ArrayList<String> arlist=new ArrayList<String>();
		String[] split;
		String sql="";
		for(int i=0; i<delete_list.length;i++) {
			split=delete_list[i].split(",");
			System.out.println("삭제할 아이디 상품명 사이즈 "+split[0]+"\t"+split[1]+"\t"+split[2]+"\t");
			sql="delete from basket where b_id='"+split[0]+"' and b_name='"+split[1]+"' and  b_size='"+split[2]+"'";
			arlist.add(sql);
		}
		
		for(String sql1:arlist) {
		pstmt=con.prepareStatement(sql1);
		pstmt.executeUpdate();
		pstmt.close();
		}
		
		arlist=null;
		sql="";
		//delete_list=null;
		
		
	}
	
	
	public void delete_product3(String [] delete_list1) throws SQLException {
		//basket에서 해당 p_no 목록에서 지우기
		
		String num="";
		for(int i=0; i<delete_list1.length;i++) {
			if(i%2!=0) {
			num+=","+delete_list1[i];
			}else {
				num+=delete_list1[i];
			}
		}
		System.out.println("삭제할 상품 번호: "+num);
		
		String sql="delete from basket where b_no in("+num+")";
		
		pstmt=con.prepareStatement(sql);
		if(pstmt.executeUpdate()!=0) {
			num="";
			System.out.println("basket 상품 삭제 성공");
			
		}else {
				
		System.out.println("basket 상품 삭제 실패했거나 review 테이블에 해당 basket이 없음");
		}
		
	}
	
	   public boolean isThereBasket(String id1) throws SQLException {
	         
		      String sql="SELECT  * FROM BASKET WHERE B_ID='"+id1+"'";
		      
		      pstmt=con.prepareStatement(sql);
		      rs=pstmt.executeQuery();
		      System.out.println("isThereBasket 실행");
		      
		      if(rs.next()) {
		         return true;
		      }
		      
		      return false;
		   }
	
	
	
	
	
	public void pstmtClose() throws SQLException {
		if(pstmt != null) {
			pstmt.close();
		}
	}
	
	public void getAllInfoClose() throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(con != null) {
			con.close();
		}
	}
	
	 public int total_count(String id1) throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM basket where b_id='"+id1+"'";
		      
	 	  pstmt = con.prepareStatement(sql);
		      rs = pstmt.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }
}

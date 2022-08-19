package orderlist.orderlistDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basket.basketVO.BasketVO;
import dbconn.DBConn;
import orderlist.orderlistVO.OrderlistVO;

public class OrderlistDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public OrderlistDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
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
		
	
	 public boolean isThereOrderlist(String id1) throws SQLException {
         
	      String sql="SELECT  * FROM orderlist WHERE o_ID='"+id1+"'";
	      
	      pstmt=con.prepareStatement(sql);
	      rs=pstmt.executeQuery();
	      System.out.println("isThereOrderlist 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }
	

	public ArrayList<OrderlistVO> orderinfo(String id3,int start,int end) throws SQLException{
		ArrayList<OrderlistVO> tiarray = new ArrayList<OrderlistVO>();
		String sql="select * from (select rownum rn,tt.* from (select * from orderlist where o_id='"+id3+"') tt) where rn >="+start+ " and rn <="+end;
		System.out.println(sql);
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
	
		while(rs.next()) {
			
			int key=0;
			String id = rs.getString("o_id");
			String name = rs.getString("o_name");
			int count = rs.getInt("o_count");
			String size = rs.getString("o_size");
			int price = rs.getInt("o_price");
		
			
			OrderlistVO tiv = new OrderlistVO(id,name,count,size,price);
		
			System.out.println(id+name+count);
			
			/*
			 * for(OrderlistVO tiv1:tiarray) {
			 * if(tiv1.getO_name().equals(name)&&size.equals(tiv1.getO_size())) {
			 * 
			 * key=1; tiv1.setO_count(tiv1.getO_count()+count);
			 * tiv1.setO_price(tiv.getO_price()+price); } }
			 */
			
			if(key!=1) {
			   tiarray.add(tiv);
			   System.out.println("ArrayList에 추가");
			}
			
		}
		return tiarray;
		
	
	}
	
	
	public int total_count(String id) throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM orderlist where o_id='"+id+"'";
		      
	 	  pstmt = con.prepareStatement(sql);
		      rs = pstmt.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }

	public void pay(String [] pay_list, int point) throws SQLException {
		//결제시 orderlist에 추가되고 basket에서는 삭제
		String[] split;
		String sql2="";
		
		for(int i=0; i< pay_list.length;i++) {
			split=pay_list[i].split(",");				//id, 상품명, 사이즈, 개수, 가격 순서로 들어옴      
			//결제되어 orderlist에 추가					    //id, 상품명, 개수, 사이즈, 가격 순으로 넣어야함
			System.out.println("결제할 아이디 상품명 사이즈 개수 가격 p_no  "+split[0]+"\t"+split[1]+"\t"+split[2]+"\t"+split[3]+"\t"+split[4]+"\t"+split[5]);
			
			// 결제 금액
			//split[4]*split[3] //가격 * 개수
			int price1=Integer.parseInt(split[4]); //가격
			int count1=Integer.parseInt(split[3]); //개수
			int totPrice=price1*count1-point; //총 가격
			if(i>=1) {
				totPrice=price1*count1;
			}
			System.out.println("결제되는 금액 :"+totPrice);
			//orderlist에 추가
			sql2="insert into orderlist values ('"+split[0]+"','"+split[1]+"','"+split[3]+"','"+split[2]+"','"+totPrice+"')";
			System.out.println(sql2);
			pstmt=con.prepareStatement(sql2);
			//pstmt.addBatch();
			pstmt.executeUpdate();
			System.out.println("결제시 orderlist에 추가됨");
			//장바구니에서 삭제
			sql2="delete from basket where b_no="+split[5];
			System.out.println(sql2);
			pstmt=con.prepareStatement(sql2);
			//pstmt.addBatch();
			pstmt.executeUpdate();
			System.out.println("결제시 장바구니 삭제됨");
			}
		pstmt.close();
		split=null;
		sql2="";
		
		}
		
	public void insertOrderlist2(String date, String address, int price, String id) throws SQLException {
		//결제시 orderlist2 테이블에 추가
		String sql2="insert into orderlist2 values ('"+date+"','"+address+"',"+price+",'"+id+"')";
		System.out.println(sql2);
		
		pstmt=con.prepareStatement(sql2);
		pstmt.executeUpdate();
		System.out.println("결제시 orderlist2에 추가됨");
	}
}






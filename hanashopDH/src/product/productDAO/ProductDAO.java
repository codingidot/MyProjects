package product.productDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import product.productVO.ProductVO;

public class ProductDAO {
   
   private Connection con;
   PreparedStatement ps = null;
   ResultSet rs = null;
   
   public ProductDAO() throws ClassNotFoundException, SQLException {
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
   
   
   
   // 여기서부터 필요한 기능 작성
   // 전체 목록 불러오기
   public ArrayList<ProductVO> showAll() throws SQLException {
	   System.out.println("showAllDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM product order by p_no desc";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
      
   }
   
   
   
   public int total_count() throws SQLException {
	   int total=0;
	   String sql = "SELECT * FROM product";
	      
 	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      
	  while(rs.next()) {
		  total+=1;
	  }
	  
	  return total;
   }
   
   
   // 상의탭 누를시 검색결과 출력하는 dao
   public ArrayList<ProductVO> showTop() throws SQLException {
	   System.out.println("showTopDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM product WHERE p_category='상의'";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
}   
   // 하의탭 누를시 검색결과 출력하는 dao
   public ArrayList<ProductVO> showBottom() throws SQLException {
	   System.out.println("showBottomDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM product WHERE p_category='하의'";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
   }
   
   // 베스트탭 누를시 검색결과 출력하는 dao
   public ArrayList<ProductVO> showBest() throws SQLException {
	   System.out.println("showBestDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM product ORDER BY p_count DESC";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
   }
   
   
   // 원피스탭 누를시 검색결과 출력하는 dao
   public ArrayList<ProductVO> showOnepiece() throws SQLException {
	   System.out.println("showOnepiece도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM product WHERE p_category='원피스'";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
   }
   
// 상품이미지 누를시 상세페이지 연결하는 dao
   public ProductVO showDetail(int pno) throws SQLException {
	   ProductVO vo = null;
	   String sql = "SELECT * FROM product WHERE p_no = ?";
	   
	   ps = con.prepareStatement(sql);
	   ps.setInt(1, pno);
	   rs = ps.executeQuery();
	   
	   if(rs.next()) {
		   int p_no = rs.getInt(1);
		   String p_name = rs.getString(2);
		   int p_price = rs.getInt(3);
		   String p_category = rs.getString(4);
		   String p_category2 = rs.getString(5);
		   int p_stockS = rs.getInt(6);
		   int p_stockM = rs.getInt(7);
		   int p_stockL = rs.getInt(8);
		   int p_count = rs.getInt(9);
		   String p_image = rs.getString(10);
		   vo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	   }else {
		   vo = null;
	   }
	   return vo;
   }
   
   
// 카테고리에서 아이템 검색시 출력하는 method
   public ArrayList<ProductVO> SearchItem(String item) throws SQLException {
	   System.out.println("SearchItemDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      System.out.println(item);
//      SELECT * FROM product WHERE p_name like '%원피스%'
      String sql = "SELECT * FROM product WHERE p_name LIKE '%" + item + "%'";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      
	      System.out.println(sql);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
   }
   
   
   
   
   
   public void updateGo(String p_no,String p_name, String p_price, String p_stockS, String p_stockM, String p_stockL, String cat1, String cat2, String fileName) throws SQLException {
		
		String sql="update product set p_name='"+p_name+"', p_price="+p_price+", p_stockS="+p_stockS+", p_stockM="+p_stockM+", p_stockL="+p_stockL+", p_category='"+cat1+"', p_category2='"+cat2+"', p_image='"+fileName+"' where p_no="+p_no;
		System.out.println(sql);
		ps=con.prepareStatement(sql);
		ps.executeUpdate();
		System.out.println("product 테이블 수정 완료");
}

   public ArrayList<ProductVO> showBest20() throws SQLException {
	   System.out.println("showBestDAO도착");
      ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
      String sql = "SELECT * FROM (SELECT * FROM product ORDER BY p_count DESC) WHERE rownum <=10 ";
      
    	  ps = con.prepareStatement(sql);
	      rs = ps.executeQuery();
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
      return parray;
   
   }

// 헤더에서 상품탭 누를시 검색결과 출력하는 dao
public ArrayList<ProductVO> category(String category) throws SQLException {
	   System.out.println("dao도착, 값 넘어왔는지 확인" + category);
   ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
   String sql = "SELECT * FROM product WHERE p_category="+category;


 	  ps = con.prepareStatement(sql);
		  System.out.println(sql);
		  
		  
	      rs = ps.executeQuery();
	      System.out.println(rs);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
   return parray;
   

}   
  
// 헤더에서 상품탭눌렀을떄 best5 뽑기
public ArrayList<ProductVO> categoryBest5(String category) throws SQLException {
	   System.out.println("dao도착, 값 넘어왔는지 확인" + category);
   ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
   String sql = "SELECT * FROM (SELECT * FROM product WHERE p_category="+category+" ORDER BY P_count DESC)WHERE ROWNUM<=5";


 	  ps = con.prepareStatement(sql);
		  System.out.println(sql);
		  
		  
	      rs = ps.executeQuery();
	      System.out.println(rs);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
   return parray;
   

}   


// 헤더에서 상품탭눌렀을떄 best5 뽑기
public ArrayList<ProductVO> searchBest5(String item) throws SQLException {
	   System.out.println("dao도착, 값 넘어왔는지 확인" + item);
   ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
   String sql = "SELECT * FROM (SELECT * FROM product WHERE p_name LIKE '%"+item+"%' ORDER BY P_count DESC) WHERE ROWNUM<=5";


 	  ps = con.prepareStatement(sql);
		  System.out.println(sql);
		  
		  
	      rs = ps.executeQuery();
	      System.out.println(rs);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
   return parray;
   

}   





// 카테고리페이지에서 정렬
public ArrayList<ProductVO> Sortlist(String category, String orderby) throws SQLException {
	   System.out.println("SortlsitDAO, 값 넘어왔는지 확인" + category);
   ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
   String sql = "SELECT * FROM product WHERE p_category="+category+"ORDER BY " + orderby;


 	  ps = con.prepareStatement(sql);
		  System.out.println(sql);
		  
		  
	      rs = ps.executeQuery();
	      System.out.println(rs);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
   return parray;
   

}      
   
// 카테고리페이지에서 정렬(검색해서 이페이지들어왔으면 다른값으로 줘야함)
public ArrayList<ProductVO> Sortlist2(String item, String orderby) throws SQLException {
	   System.out.println("Sortlist2DAO 도착");
	   System.out.println("값 제대로 넘어왔는지 확인 item : " + item);
	   System.out.println("값 제대로 넘어왔는지 확인 orderby : " + orderby);
   ArrayList<ProductVO> parray = new ArrayList<ProductVO>();
   String sql = "SELECT * FROM product WHERE p_name LIKE '%"+item+"%' ORDER BY " + orderby;


 	  ps = con.prepareStatement(sql);
		  System.out.println(sql);
		  
		  
	      rs = ps.executeQuery();
	      System.out.println(rs);
	      while(rs.next()) {
	    	  int p_no = rs.getInt("p_no");
	    	  String p_name = rs.getString("p_name");
	    	  int p_price = rs.getInt("p_price");
	    	  String p_category = rs.getString("p_category");
	    	  String p_category2 = rs.getString("p_category2");
	    	  int p_stockS = rs.getInt("p_stockS");
	    	  int p_stockM = rs.getInt("p_stockM");
	    	  int p_stockL = rs.getInt("p_stockL");
	    	  int p_count = rs.getInt("p_count");
	    	  String p_image = rs.getString("p_image");
	    	  
	    	  
	    	 
	    	  ProductVO pvo = new ProductVO(p_no, p_name, p_price, p_category, p_category2, p_stockS, p_stockM, p_stockL, p_count, p_image);
	    	  
	    	  parray.add(pvo);
	      }
	      System.out.println("dao에서 정보 리턴");
   return parray;
   

}   


  
public int getProductNum() throws SQLException {
		//p_no에서 가장 높은숫자 가져오기
		System.out.println("마지막 상품번호 불러오기 메서드 실행");
		String sql="SELECT  * FROM PRODUCT";
		ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		int num=0;
		while(rs.next()) {
			int num2=rs.getInt("p_no");
			if(num2>num) {
				num=num2;
			}
		}
		return num;
		
	}
	
	
	public ArrayList<ProductVO> getAllInfo(int start,int end) throws SQLException{
		//상품 테이블 출력
		ArrayList< ProductVO> parray=new ArrayList<ProductVO>();
		String sql="select * from (select rownum rn,tt.* from (select * from product order by p_no ) tt) where rn >="+start+" and rn <="+end;
		System.out.println(sql);	
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			System.out.println("getAllInfo 메소드 실행");
			while(rs.next()) {
				
				int no=rs.getInt("p_no");
				String name=rs.getString("p_name");
				int price=rs.getInt("p_price");
				String category1=rs.getString("p_category");
				String category2=rs.getString("p_category2");
				int stockS=rs.getInt("p_stockS");
				int stockM=rs.getInt("p_stockM");
				int stockL=rs.getInt("p_stockL");
				int count=rs.getInt("p_count");
				String image=rs.getString("p_image");
				System.out.println(no+"\t"+name+"\t"+price+"\t"+category1+"\t"+category2+"\t 받아왔음(from database)");
				if(category1==null) {
					category1="";
				}
				if(category2==null) {
					category2="";
				}
				if(image==null) {
					image="";
				}
				if(name==null) {
					name="";
				}
				
				ProductVO pvo=new ProductVO(no,name, price, category1, category2, stockS,stockM,stockL,count ,image);
				parray.add(pvo);
				System.out.println("ArrayList에 추가");
			}
			System.out.println("ArrayList 반환");
			return parray;
			
			
	}
	
	
	
	public boolean delete_product1(String [] delete_list1) throws SQLException {
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
		
		String sql="delete from product where p_no in("+num+")";
		
		ps=con.prepareStatement(sql);
		if(ps.executeUpdate()!=0) {
			num="";
			System.out.println("상품 삭제 성공");
			return true;
		}
				
		System.out.println("상품 삭제 실패");
		return false;
		
	}
	
	
	
	public boolean insert_product(int p_no, String p_name, int p_price, String p_category, String p_category2, int p_stockS, int p_stockM, int p_stockL,int p_count ,String p_image) throws SQLException {
	//상품등록
	System.out.println("product insert 메소드 시작");
	String sql="INSERT INTO PRODUCT VALUES(?,?,?,?,?,?,?,?,?,?)";
	
	ps=con.prepareStatement(sql);
	System.out.println("SQL ?안에 넣는다");
	
	  ps.setInt(1, p_no); 
	  ps.setString(2, p_name); 
	  ps.setInt(3, p_price);
	  ps.setString(4, p_category); 
	  ps.setString(5, p_category2);
	  ps.setInt(6,p_stockS);
	  ps.setInt(7,p_stockM);
	  ps.setInt(8,p_stockL);
	  ps.setInt(9, p_count);
	  ps.setString(10,p_image);
	  
	System.out.println("executeUpdate 실행한다");
	if(ps.executeUpdate()!=0) {
		System.out.println("성공");
		return true;
	}else {
		System.out.println("실패");
		return false;
	
	}	
	}
	
	
	public ArrayList<ProductVO> MoneyAllInfo() throws SQLException{
		//상품 테이블 출력
		ArrayList< ProductVO> parray=new ArrayList<ProductVO>();
		String sql="SELECT  * FROM PRODUCT ORDER BY P_NO";
			
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("getAllInfo 메소드 실행");
			while(rs.next()) {
				int key=0;
				int no=rs.getInt("p_no");
				String name=rs.getString("p_name");
				int price=rs.getInt("p_price");
				String category1=rs.getString("p_category");
				String category2=rs.getString("p_category2");
				int stockS=rs.getInt("p_stockS");
				int stockM=rs.getInt("p_stockM");
				int stockL=rs.getInt("p_stockL");
				int count=rs.getInt("p_count");
				String image=rs.getString("p_image");
				System.out.println(no+"\t"+name+"\t"+price+"\t"+category1+"\t"+category2+"\t 받아왔음(from database)");
				if(category1==null) {
					category1="";
				}
				if(category2==null) {
					category2="";
				}
				if(image==null) {
					image="";
				}
				if(name==null) {
					name="";
				}
				
				ProductVO pvo=new ProductVO(no,name, price, category1, category2, stockS,stockM,stockL,count ,image);
				
				for(ProductVO pvo1:parray) {
					if(pvo1.getP_category().equals(category1)&&pvo1.getP_category2().equals(category2)) {
						
							key=1;
						pvo1.setP_count(pvo1.getP_count()+count);						
					}
				}
				
				if(key!=1) {
				   parray.add(pvo);
				   System.out.println("ArrayList에 추가");
				}
				
			}
			System.out.println("ArrayList 반환");
			return parray;
			
			
	}
	
	
	public String check_stock(String [] pay_list) throws SQLException {
		String[] split;
		String stockSize="";
		int key=0;
		String result="";
		for(int i=0; i< pay_list.length;i++) {
			System.out.println("check_stock메소드  "+pay_list[i]);
			split=pay_list[i].split(",");
			
			if(split[2].equals("S")) {
				stockSize="p_stockS";
			}else if(split[2].equals("M")) {
				stockSize="p_stockM";
			}else if(split[2].equals("L")) {
				stockSize="p_stockL";
			}
			
			
			
			String sql2="select * from product where p_no='"+split[5]+"'";
			System.out.println(sql2);
			System.out.println("product 테이블 조회");
			ps=con.prepareStatement(sql2);
			rs=ps.executeQuery();
			
			while(rs.next()) {
			
				int stock=rs.getInt(stockSize);
				if(stock<0) {
					stock=0;
				}
				int buyCount=Integer.parseInt(split[3]);
				if(stock-buyCount<0) {
					key=1;
					System.out.println("재고부족");
					System.out.println("현재 있는 사이즈>>"+split[2]+"  재고>>"+stock);
					System.out.println("사려고하는 개수>>"+buyCount);
					String needMore=String.valueOf(buyCount-stock);
					result+=split[1]+" 상품의 "+split[2]+" 사이즈가 "+needMore+"개 부족합니다 \n";
				}
			}	
			
		}
			if(key==1) {
				System.out.println("check_stock에서 부족한 경우 출력되는것>>>>"+result);
				return result;
			}
			
			return "enough";
	}
	
	
	
	public void pay2(String [] pay_list) throws SQLException {
		//product 테이블 재고변경
		String[] split;
		
		
		for(int i=0; i< pay_list.length;i++) {
			split=pay_list[i].split(",");
			//${sessionScope.id },${vo1.b_name},${vo1.b_size },${vo1.b_count },${vo1.b_price },${vo1.b_no }
			//id, 상품명, 사이즈, 개수, 가격 p_no순서로 들어옴      
			//id, 상품명, 개수, 사이즈, 가격 no순으로 넣어야함
			String stockSize="";
			int tcount=0;
			if(split[2].equals("S")) {
				stockSize="p_stockS";
			}else if(split[2].equals("M")) {
				stockSize="p_stockM";
			}else if(split[2].equals("L")) {
				stockSize="p_stockL";
			}
			System.out.println("stockSize: "+stockSize);
			System.out.println("p_no: "+split[5]);
			String sql2="select * from product where p_no='"+split[5]+"'";
			System.out.println(sql2);
			System.out.println("product 테이블 조회");
			ps=con.prepareStatement(sql2);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int stockCount=rs.getInt(stockSize); //구매한 상품 사이즈의 재고
				int stockCount2=stockCount-Integer.parseInt(split[3]);//재고에서 구매한 개수 뺀것
				int count=rs.getInt("p_count"); //product 테이블에서 총 상품 구매수
				int count2=Integer.parseInt(split[3]);//구매수
				tcount=count+count2;//총 상품 구매수에 구매수를 더한것
				System.out.println("변경전 count :"+count+"   변경된 count :"+tcount+"  변경해야할 재고:"+stockSize);
				
				sql2="update product set "+stockSize+"="+stockCount2+", p_count="+tcount+" where p_no="+split[5];
				System.out.println(sql2);
				ps=con.prepareStatement(sql2);
				ps.executeUpdate();
				System.out.println("product 테이블 수정 완료");
				
				
			}
			
	    }
	}	
	
	
	   
	   
	   
	  
		
		
		
		
		
		
		
		
		
		
   
   

}
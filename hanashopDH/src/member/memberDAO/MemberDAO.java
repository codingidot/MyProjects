package member.memberDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import member.memberVO.MemberVO;



public class MemberDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDAO() throws ClassNotFoundException, SQLException {
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

	public boolean update_all(String name, String id, String pw, String tel, String d,
			String email, String address) throws SQLException {
		// TODO Auto-generated method stub
		
		 String sql="update member set  u_id=?, u_password=?, u_name=?, u_tel=?,u_d=TO_DATE(?,'YYYY-MM-DD'), u_address=?, u_email=? where u_id=?";
	 System.out.println(id+"\t"+ pw+"\t"+name+"\t"+tel+"\t"+email+"\t"+d+"\t"+address+"\t" );
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, pw);
		pstmt.setString(3, name);
		pstmt.setString(4, tel);
		pstmt.setString(5, d);
		pstmt.setString(6,address);
		pstmt.setString(7, email);
		pstmt.setString(8, id);
		pstmt.executeUpdate();
		
	
		return true;
	}

	public ArrayList<MemberVO> getAllInfo() throws SQLException{
		ArrayList<MemberVO> tiarray = new ArrayList<MemberVO>();
		String sql = "SELECT * FROM member ORDER BY u_id";
		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			String id = rs.getString("u_id");
			String pw = rs.getString("u_password");
			String name = rs.getString("u_name");
			String tel = rs.getString("u_tel");
			String address = rs.getString("u_address");
			String email = rs.getString("u_email");
			Date d = rs.getDate("u_d");
			int point = rs.getInt("u_point");
		
			
			MemberVO tiv = new MemberVO(id,pw, name, tel, address, email, d, point);
			
			tiarray.add(tiv);
		}
		
		return tiarray;
	}
			
	public MemberVO getInfo(String id1) throws SQLException {
		MemberVO tv = null;
		String sql = "select * from member where u_id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id1);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			String id = rs.getString("u_id");
			String pw = rs.getString("u_password");
			String name = rs.getString("u_name");
			String tel = rs.getString("u_tel");
			String address = rs.getString("u_address");
			String email = rs.getString("u_email");
			Date d = rs.getDate("u_d");
			int point = rs.getInt("u_point");
		
			System.out.println(id+name);
			tv = new MemberVO(id,pw,name,tel,address,email,d,point);
			
		}else {
			tv = null;
		}
		return tv;
	}

	public boolean withdraw(String idid) throws SQLException {
		
		String sql = "DELETE member WHERE u_id = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, idid);
		pstmt.executeUpdate();
		
		return true;
		
	}
	
	public ArrayList<MemberVO> getAllInfo2(int start,int end) throws SQLException{
		ArrayList< MemberVO> marray=new ArrayList<MemberVO>();
		String sql="select * from (select rownum rn,tt.* from (select * from member order by u_id ) tt) where rn >="+start+" and rn <="+end;
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			System.out.println("MEMBER getAllInfo 실행");
			while(rs.next()) {
				
				String id=rs.getString("u_id");
				String password=rs.getString("u_password");
				String name=rs.getString("u_name");
				String tel=rs.getString("u_tel");
				String address=rs.getString("u_address");
				String email=rs.getString("u_email");
				Date d=rs.getDate("u_d");
				int point=rs.getInt("u_point");		
				
				System.out.println(id+"\t"+password+"\t"+name+"\t"+tel+"\t"+address+"\t"+email+"\t"+d+"\t"+point);
				MemberVO mvo=new MemberVO(id,password,name,tel,address,email,d,point);
				marray.add(mvo);
				
			
			}
			
			return marray;
			
	}
	
	public void delete_ID(String [] delete_member) throws SQLException {
		String sql="";
		for(String id:delete_member) {
			
			sql="delete from member where u_id='"+id+"'";
			
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
			System.out.println(id+"  삭제");
			
		}
	}
	
	
	// 로그인버튼 눌렀을때 동작하는 method
	public MemberVO checkLogin(String u_id1) throws SQLException{
//	public boolean checkLogin(String u_id, String u_pw) throws SQLException{
		System.out.println("checkLoginDAO 도착");

//		String sql = "select * from member where u_id=? and u_password=?";
		String sql = "select * from member where u_id=?";
		System.out.println("sql문 실행여부확인");

		System.out.println("dao에서 넘어왔는지 확인 " + u_id1);
//		System.out.println("dao에서 넘어왔는지 확인 " + u_pw);
		

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, u_id1);
//		pstmt.setString(2, u_pw);
		rs = pstmt.executeQuery();
		
		
		MemberVO mvo= null;
		
		
		
		System.out.println(sql);
//		System.out.println(rs.getString(sql));
		
		while(rs.next()) {
			System.out.println("while문 실행여부 확인"); // <= 실행되면 주석처리할것
			String u_id = rs.getString("u_id");
			String u_password = rs.getString("u_password");
			String u_name = rs.getString("u_name");
			String u_tel = rs.getString("u_tel");
			String u_address = rs.getString("u_address");
			String u_email = rs.getString("u_email");
			Date u_d = rs.getDate("u_d");
			int u_point = rs.getInt("u_point");
			
			System.out.println(u_id + u_password);
			mvo = new MemberVO(u_id,u_password,u_name,u_tel,u_address,u_email,u_d,u_point);
			
		}
		return mvo;
		
	}

	public boolean insertmember(String name, String id, String pw, String tel, String d, String email, String address) throws SQLException
	{	
		String sql="INSERT INTO member VALUES(?,?,?,?,?,?,?,?)";
		 System.out.println(id+"\t"+ pw+"\t"+name+"\t"+tel+"\t"+email+"\t"+d+"\t"+address+"\t" );
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, address);
			pstmt.setString(6,email);
			pstmt.setString(7, d);
			pstmt.setInt(8, 0);
			
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
	
	public int selectIdCheck(String userId) throws SQLException {
		
		int result;
		System.out.println(userId);
		String sql= "SELECT u_id FROM MEMBER WHERE u_id=?";
		
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs=pstmt.executeQuery();
			
			if(rs.next()){	
				result = 0;
			}else{
				result = 1;
			}

			System.out.println("아이디 중복체크결과 : "+result);
			
			return result;
	}
	
	
	
	// 로그인버튼 눌렀을때 동작하는 method
	public String goLogin(String u_id1,String u_pw) throws SQLException{
//		public boolean checkLogin(String u_id, String u_pw) throws SQLException{
			System.out.println("goLoginDAO 도착");

//			String sql = "select * from member where u_id=? and u_password=?";
			String sql = "select * from member where u_id=?";
			System.out.println("sql문 실행여부확인");

			System.out.println("dao에서 넘어왔는지 확인 " + u_id1);
			

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, u_id1);
			rs = pstmt.executeQuery();
			
			
			
			
			
			System.out.println(sql);
			if(!rs.next()) {
				System.out.println("일치하는 아이디 없음");
				return "1";
			}else {
					rs = pstmt.executeQuery();
					while(rs.next()) {
						System.out.println("while문 실행여부 확인"); // <= 실행되면 주석처리할것
						String u_password = rs.getString("u_password");
						if(!u_pw.equals(u_password)) {
							System.out.println("비밀번호 불일치");
						return "2";
						}
					
				
					}
			}
					System.out.println("로그인 성공");
					return "3";
		}
	
	
	//멤버테이블 회원수를 불러오는 메소드
	public int total_count() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM MEMBER";
		      
	 	  pstmt = con.prepareStatement(sql);
		      rs = pstmt.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }
	
	//결제 하기전에 해당 아이디의 포인트를 불러오는 메소드
	public int get_point(String id) throws SQLException {
		String sql="";
		int point=0;
		sql="select * from member where u_id=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		
		System.out.println(sql);
		
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println("데이터베이스 포인트 가져옴");
			 point=rs.getInt("u_point");
		}
		
		return point;
	}//get_point 메소드 끝

	
public void update_point(String id, int point) throws SQLException {
	//포인트 사용시 포인트 0으로 됨
	String sql="";
	System.out.println("update_point 메소드 시작   id="+id);
	sql="update member set u_point=? where u_id=?";
	pstmt=con.prepareStatement(sql);
	pstmt.setInt(1, point);
	pstmt.setString(2, id);
	pstmt.executeUpdate();
	System.out.println(sql);
	System.out.println("point 업데이트 완료");
}


public void pay3(String [] pay_list, String id2) throws SQLException {
	//member 테이블에 포인트 적립
	String[] split;
	String sql3="";
	for(int i=0; i< pay_list.length;i++) {
		split=pay_list[i].split(",");
		//${sessionScope.id },${vo1.b_name},${vo1.b_size },${vo1.b_count },${vo1.b_price },${vo1.b_no }
		//id, 상품명, 사이즈, 개수, 가격 p_no순서로 들어옴
		
		sql3="select * from member where u_id='"+id2+"'";
		pstmt=con.prepareStatement(sql3);
		rs=pstmt.executeQuery();
		System.out.println("member 테이블 검색");
		while(rs.next()) {
			int point=rs.getInt("u_point"); //포인트 적립 전 포인트
			System.out.println("적립 전 포인트 "+point);
			int newPoint=point+((Integer.parseInt(split[4])*Integer.parseInt(split[3]))/100);//적립 후 포인트
			System.out.println("적립 후 포인트 "+newPoint);
			sql3="update member set u_point="+newPoint+" where u_id='"+id2+"'";
			System.out.println(sql3);
			pstmt=con.prepareStatement(sql3);
			pstmt.executeUpdate();
			System.out.println("포인트 적립 완료");
		}
	}
}


}
	
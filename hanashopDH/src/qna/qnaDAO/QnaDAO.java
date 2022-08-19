package qna.qnaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconn.DBConn;
import qna.qnaVO.QnaVO;

public class QnaDAO {
	
	   private Connection con;
	   PreparedStatement ps = null;
	   ResultSet rs = null;
	   
	   public QnaDAO() throws ClassNotFoundException, SQLException {
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
	   
	   
	   /*
		public ArrayList<QnaVO> qanswer() throws SQLException {
			
			System.out.println("dao 왔니?");
			
			ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();
			String sql = "select * from Qna order by q_no";
			
			
			System.out.println(sql);
			
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println("while 안에");
				int no = rs.getInt("q_no");
				String id = rs.getString("q_id");
				int pno = rs.getInt("q_pno");
				String title = rs.getString("q_title");
				String content = rs.getString("q_content");
				Date date = rs.getDate("q_date");
				int hit = rs.getInt("q_hit");
				int parentno = rs.getInt("q_parentno");
				String pw = rs.getString("q_pw");
				String image = rs.getString("q_iamge");
				String state = rs.getString("q_state");
				
				System.out.println(no+id+title);
			
				QnaVO tiv = new QnaVO(no,id,pno,title,content,date,hit,parentno,pw,image,state);
				
				tiarray.add(tiv);
			}
			
			return tiarray;
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
		
		*/
		
		

	   //큰수 구하기
	   public int get_no() throws SQLException {
		   System.out.println("q_no  메소드 시작");
		   String sql="";
		   int q_no=0;
		   sql="select * from qna";
		   ps=con.prepareStatement(sql);
		   rs=ps.executeQuery();
		   
		   while(rs.next()) {
			   if(rs.getInt("q_no")>q_no) {
				   q_no=rs.getInt("q_no");
			   }
		   }
		   return q_no+1;
	   }
	   
	   
	   
	   
	   
	   
	   //해당 상품에 대한 qna있는지 없는지 여부 확인하는 method
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
	   
	   
	 //해당 제품에 대한 list를 출력하되, 상위 10개만 노출되게 하는 method
	 		public ArrayList<QnaVO> getList(int start, int p_no) throws SQLException{
//	 			String sql = "select * from board order by seq desc";
	 			
	 			String sql = "SELECT * FROM (SELECT ROWNUM RN,TT.*FROM(SELECT * FROM qna WHERE q_pno=? ORDER BY q_parentno DESC, q_no ASC) TT) WHERE RN>=? and RN <= ? and q_pno=?";
	 			System.out.println("getList 메소드");

	 			ArrayList<QnaVO> list = null;
	 			
	 			ps = con.prepareStatement(sql);
	 			ps.setInt(1, p_no);
	 			ps.setInt(2, start + 1);
	 			ps.setInt(3, start + 10);
	 			ps.setInt(4, p_no);
	 			rs = ps.executeQuery();
	 			System.out.println("rs값 : " + rs);
	 			list = this.makeList(rs);
	 			
	 			return list;
	 		}
	   
	 		// 리스트를 만드는 method
			private ArrayList<QnaVO> makeList(ResultSet rs) throws SQLException {
				ArrayList<QnaVO> list = new ArrayList<QnaVO>();

				System.out.println("makelist 메소드");

					while (rs.next()) {
						int q_no = rs.getInt("q_no");
						String q_id;
						if(rs.getString("q_id").equals("admin")) {
							q_id = rs.getString("q_id");
						}else {
							q_id = rs.getString("q_id").substring(0,2)+"****님";
						}
						int q_pno = rs.getInt("q_pno");
						String q_title = rs.getString("q_title");
						String q_content = rs.getString("q_content");
						Date q_date = rs.getDate("q_DATE");
						int q_hit = rs.getInt("q_hit");
						int q_parentno = rs.getInt("q_parentno");
						String q_pw = rs.getString("q_pw");
						String q_image = rs.getString("q_image");
						String q_state = rs.getString("q_state");
						String q_category = rs.getString("q_category");
						
						QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
						list.add(qvo);
					}
				

				return list;

			}
		
			// 문의 갯수보는 METHOD
			public int getTotal(int q_pno) throws SQLException {
				System.out.println(q_pno);
				String sql = "select count (*) from qna WHERE q_pno=?";
//				String sql = "select count(*) from review";
				System.out.println("getTotal 메소드");

				int su = 0;
				
					ps = con.prepareStatement(sql);
					ps.setInt(1, q_pno);
					rs = ps.executeQuery();
					if(rs.next()) {
						su = rs.getInt("count(*)");
					}
				

				System.out.println("rs값 : " + rs);
				System.out.println("su값 : " + su);
				return su;
			}
			
		
		
			
			//문의 등록하는 method
			public int qnaWrite(int no, String id, int pno, String title,String content, String pw, String filename, String category) throws SQLException {
				String sql = "INSERT INTO QNA VALUES(?,?,?,?,?,sysdate,0,?,?,?,'답변대기',?)";
				System.out.println("qnaWrite");
				
				
				ps = con.prepareStatement(sql);
				ps.setInt(1, no);
				ps.setString(2, id);
				ps.setInt(3, pno);
				ps.setString(4, title);
				ps.setString(5, content);
				ps.setInt(6, no);
				ps.setString(7, pw);
				ps.setString(8, filename);
				ps.setString(9, category);
				
				//1을 반환하게 될거임
				return ps.executeUpdate();
				
			}
		   
		   
		   
		   
		   
		   
		   
		// 상품에대한 후기 리스트를 뽑는 메소드
		public ArrayList<QnaVO> Productqnalist(int pno) throws SQLException {
			System.out.println("ProductqnaDAO 도착");
			
			ArrayList<QnaVO> qarray = new ArrayList<QnaVO>();
			
			System.out.println("pno : " + pno);
			
			String sql = "SELECT * FROM qna WHERE q_pno=? ORDER BY q_title DESC";
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, pno);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				int q_no = rs.getInt("q_no");
				String q_id = rs.getString("q_id");
				int q_pno = rs.getInt("q_pno");
				String q_title = rs.getString("q_title");	
				String q_content = rs.getString("q_content");
				Date q_date = rs.getDate("q_date");
				int q_hit = rs.getInt("q_hit");
				int q_parentno = rs.getInt("q_parentno");
				String q_pw = rs.getString("q_pw");
				String q_image = rs.getString("q_image");
				String q_state = rs.getString("q_state");
				String q_category = rs.getString("q_category");
				
				QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
				
				qarray.add(qvo);
			}
			System.out.println("dao에서 리턴");
			return qarray;
		}
		
		
	

	
	
		// 리뷰내용보는 메소드
		public QnaVO ProductQNAView(int qno) throws SQLException {
			System.out.println("ProductqnaDAOview 도착");
			
			QnaVO vo = null;
			String sql = "SELECT * FROM qna WHERE q_no= "+qno;
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				int q_no = rs.getInt(1);
				String q_id = rs.getString(2);
				int q_pno = rs.getInt(3);
				String q_title = rs.getString(4);	
				String q_content = rs.getString(5);
				Date q_date = rs.getDate(6);
				int q_hit = rs.getInt(7);
				int q_parentno = rs.getInt(8);
				String q_pw = rs.getString(9);
				String q_image = rs.getString(10);
				String q_state = rs.getString(11);
				String q_category = rs.getString(12);
				
				vo = new QnaVO(q_no ,q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			}else {
				vo = null;
			}
			return vo;
		}
		
		

		

		// 조회수 증가하는 메소드
		public void Uphit(int qno) throws SQLException {
			System.out.println("조회수 증가 dao 실행");
			String sql = "Update qna SET q_hit=q_hit+1 WHERE q_no= "+qno;
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
			
		}
	
		// 답변 달았을때 사용하는 메소드
		public int qnareaction(int no, String id, int pno, String title,String content, int parentno, String pw, String category) throws SQLException {
			String sql = "INSERT INTO QNA VALUES(?,?,?,?,?,sysdate,0,?,?,'','해당없음',?)";
			System.out.println("qnareaction메소드 시작");
			
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			ps.setInt(3, pno);
			ps.setString(4, title);
			ps.setString(5, content);
			ps.setInt(6, parentno);
			ps.setString(7, pw);
			ps.setString(8, category);
			
			//1을 반환하게 될거임
			return ps.executeUpdate();
			
		}
		
		
		
		
		
		
		
	
	// 답변 달았을때 사용하는 메소드
	public int qnareaction2(int no, String id, int pno, String title,String content, int parentno, String pw) throws SQLException {
		String sql = "INSERT INTO  QNA (q_no,q_id,q_pno,q_title,q_content,q_parentno,q_pw,q_date) VALUES(?,'admin',?,?,?,?,?,sysdate)";
		System.out.println("qnareaction메소드 시작");
		System.out.println(no+id+pno+title+content+parentno+pw);
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.setInt(2, pno);
		ps.setString(3, title);
		ps.setString(4, content);
		ps.setInt(5, parentno);
		ps.setString(6, pw);
		
		//1을 반환하게 될거임
		return ps.executeUpdate();
		
	}
	
	//답변이 달리면 기존글의 state를 "답변대기"에서 "처리완료"로 수정
	public int changeState(int parentno) throws SQLException{
//		UPDATE [테이블] SET [열] = '변경할값' WHERE [조건]
		System.out.println("changeStateDAO 실행");
		String sql = "UPDATE QNA SET q_state ='처리완료' WHERE q_parentno = q_no AND q_parentno = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, parentno);
		
		return ps.executeUpdate();
	}
	
	// 답변달렸을떄 기존의 질문내용을 받아서 보여주는 method
		public String getParentContent(int parentno) throws SQLException{
			System.out.println("parentno : " + parentno);
			System.out.println("getParentContentDAO 실행");
			
			String sql = "SELECT q_content FROM (SELECT * FROM qna WHERE q_parentno = ? ORDER BY q_no ASC) WHERE ROWNUM <= 1";
			
			String content ="";
			ps = con.prepareStatement(sql);
			ps.setInt(1, parentno);

			rs=ps.executeQuery();
			
			if(rs.next()) {
				content= rs.getString("q_content");
				
				
			}
			System.out.println("content : " + content);
			return content;
			
		}		
	
	
	
	public String getParentContent2(int parentno) throws SQLException{
		System.out.println("parentno : " + parentno);
		System.out.println("getParentContentDAO 실행");
		
		String sql = "SELECT q_content FROM (SELECT * FROM qna WHERE q_parentno = ? ORDER BY q_no ASC) WHERE q_id='admin'";
		
		String content ="";
		ps = con.prepareStatement(sql);
		ps.setInt(1, parentno);

		rs=ps.executeQuery();
		
		if(rs.next()) {
			content= rs.getString("q_content");
			
			
		}
		System.out.println("content : " + content);
		return content;
		
	}

	// 답변달렸을떄 기존의 질문내용을 받아서 보여주는 method
	public String getChildContent(int parentno) throws SQLException{
		System.out.println("parentno : " + parentno);
		System.out.println("getParentContentDAO 실행");
		
		String sql = "SELECT q_content FROM (SELECT * FROM qna WHERE q_parentno = ? ORDER BY q_no DESC) WHERE ROWNUM <= 1";
		
		String content ="";
		ps = con.prepareStatement(sql);
		ps.setInt(1, parentno);

		rs=ps.executeQuery();
		
		if(rs.next()) {
			content= rs.getString("q_content");
			
			
		}
		System.out.println("content : " + content);
		return content;
		
	}	
	
	
	
	// 비밀번호가맞으면 리뷰내용보는 메소드
		public String qnapwcheck(String pw) throws SQLException {
			System.out.println("qnacheckpw 도착");
			System.out.println(pw);
			
			String sql = "SELECT q_pw FROM qna WHERE q_pw= ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			rs=ps.executeQuery();
			while(rs.next()) {
				return "1"; // 비밀번호가 일치할시
			}
			
			return null; //비밀번호가 불일치할시
		}
		
		//답변인지 질문인지 확인하는 메소드
		public int type(int qno, int parentno) throws SQLException{
			int type = 0;
			System.out.println("typeDAO 실행");
			String sql = "SELECT * FROM qna WHERE ? = ? and ROWNUM <= 1";
			ps = con.prepareStatement(sql);
			ps.setInt(1, qno);
			ps.setInt(2, parentno);

			//1을 반환하게 될거임
			type = ps.executeUpdate();
			
			
			return type;
			//즉, q_no와 parentno가 같다면(질문글이라면) 1을 return
			//q_no와 parentno가 다르다면(답변글이라면) 0을 return
		}
		
		// 리뷰 삭제했을떄 사용하는 메소드
		public int qnaDelete(int qparentno) throws SQLException {
			System.out.println("qnaDeleteDAO 실행");
			String sql = "Delete FROM qna WHERE q_parentno = ?";

			
				ps = con.prepareStatement(sql);
				ps.setInt(1, qparentno);
				
				
				// 정상적으로 실행되었다면 1을 리턴하게 될거임
				return ps.executeUpdate();
			
		}
		
		// 리뷰수정Form으로 이동하는 메소드
		public QnaVO QnaUpdateForm(int qno) throws SQLException {
			System.out.println("ProductqnaDAOview 도착");
			
			QnaVO vo = null;
			String sql = "SELECT * FROM qna WHERE q_no= "+qno;
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()) {
				int q_no = rs.getInt(1);
				String q_id = rs.getString(2);
				int q_pno = rs.getInt(3);
				String q_title = rs.getString(4);	
				String q_content = rs.getString(5);
				q_content = q_content.replace("<br>", "\r\n");
				Date q_date = rs.getDate(6);
				int q_hit = rs.getInt(7);
				int q_parentno = rs.getInt(8);
				String q_pw = rs.getString(9);
				String q_image = rs.getString(10);
				String q_state = rs.getString(11);
				String q_category = rs.getString(12);
				
				vo = new QnaVO(q_no ,q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			}else {
				vo = null;
			}
			return vo;
		}

		//문의내용 수정하는 method
		public int qnaUpdate(int no, String title,String content, String pw, String filename, String category) throws SQLException {
			String sql = "UPDATE QNA SET q_title= ?, q_content= ?, q_pw= ?, q_image=?, q_category = ? WHERE q_no = ?";
			System.out.println("qnaUpdateDAO");
			
			
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setString(3, pw);
			ps.setString(4, filename);
			ps.setString(5, category);
			ps.setInt(6, no);
			
			//1을 반환하게 될거임		
			return ps.executeUpdate();
			
		}
		

	public ArrayList<QnaVO> qnacom(int start, int end) throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();

		String sql ="select * from (select rownum rn,tt.* from (select * from qna where q_no=q_parentno and q_category='상품문의') tt) where rn >="+start+" and rn <="+end;
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int q_no = rs.getInt("q_no");
			String q_id = rs.getString("q_id");
			int q_pno = rs.getInt("q_pno");
			String q_title = rs.getString("q_title");	
			String q_content = rs.getString("q_content");
			Date q_date = rs.getDate("q_date");
			int q_hit = rs.getInt("q_hit");
			int q_parentno = rs.getInt("q_parentno");
			String q_pw = rs.getString("q_pw");
			String q_image = rs.getString("q_image");
			String q_state = rs.getString("q_state");
			String q_category = rs.getString("q_category");
			 
			QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			
			tiarray.add(qvo);
		}
		System.out.println("dao에서 리턴");
		return tiarray;
	}
	
	public ArrayList<QnaVO> qnacom2(int start, int end) throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum rn,tt.* from (select * from qna where q_no=q_parentno and q_category='배송문의') tt) where rn >="+start+" and rn <="+end;
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int q_no = rs.getInt("q_no");
			String q_id = rs.getString("q_id");
			int q_pno = rs.getInt("q_pno");
			String q_title = rs.getString("q_title");	
			String q_content = rs.getString("q_content");
			Date q_date = rs.getDate("q_date");
			int q_hit = rs.getInt("q_hit");
			int q_parentno = rs.getInt("q_parentno");
			String q_pw = rs.getString("q_pw");
			String q_image = rs.getString("q_image");
			String q_state = rs.getString("q_state");
			String q_category = rs.getString("q_category");
			 
			QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			
			tiarray.add(qvo);
		}
		System.out.println("dao에서 리턴");
		return tiarray;
	}
	
	public ArrayList<QnaVO> qnacom3(int start, int end) throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum rn,tt.* from (select * from qna where q_no=q_parentno and q_category='입고요청') tt) where rn >="+start+" and rn <="+end;
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int q_no = rs.getInt("q_no");
			String q_id = rs.getString("q_id");
			int q_pno = rs.getInt("q_pno");
			String q_title = rs.getString("q_title");	
			String q_content = rs.getString("q_content");
			Date q_date = rs.getDate("q_date");
			int q_hit = rs.getInt("q_hit");
			int q_parentno = rs.getInt("q_parentno");
			String q_pw = rs.getString("q_pw");
			String q_image = rs.getString("q_image");
			String q_state = rs.getString("q_state");
			String q_category = rs.getString("q_category");
			 
			QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			
			tiarray.add(qvo);
		}
		System.out.println("dao에서 리턴");
		return tiarray;
	}
	
	public ArrayList<QnaVO> qnacom4(int start, int end) throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum rn,tt.* from (select * from qna where q_no=q_parentno and q_category='환불관련') tt) where rn >="+start+" and rn <="+end;
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int q_no = rs.getInt("q_no");
			String q_id = rs.getString("q_id");
			int q_pno = rs.getInt("q_pno");
			String q_title = rs.getString("q_title");	
			String q_content = rs.getString("q_content");
			Date q_date = rs.getDate("q_date");
			int q_hit = rs.getInt("q_hit");
			int q_parentno = rs.getInt("q_parentno");
			String q_pw = rs.getString("q_pw");
			String q_image = rs.getString("q_image");
			String q_state = rs.getString("q_state");
			String q_category = rs.getString("q_category");
			 
			QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			
			tiarray.add(qvo);
		}
		System.out.println("dao에서 리턴");
		return tiarray;
	}
	
	public ArrayList<QnaVO> qnacom5(int start, int end) throws SQLException {
		
		System.out.println("dao 왔니?");
		
		ArrayList<QnaVO> tiarray = new ArrayList<QnaVO>();
		String sql = "select * from (select rownum rn,tt.* from (select * from qna where q_no=q_parentno and q_category='기타문의') tt) where rn >="+start+" and rn <="+end;
		
		
		System.out.println(sql);
		
		
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			int q_no = rs.getInt("q_no");
			String q_id = rs.getString("q_id");
			int q_pno = rs.getInt("q_pno");
			String q_title = rs.getString("q_title");	
			String q_content = rs.getString("q_content");
			Date q_date = rs.getDate("q_date");
			int q_hit = rs.getInt("q_hit");
			int q_parentno = rs.getInt("q_parentno");
			String q_pw = rs.getString("q_pw");
			String q_image = rs.getString("q_image");
			String q_state = rs.getString("q_state");
			String q_category = rs.getString("q_category");
			 
			QnaVO qvo = new QnaVO(q_no, q_id, q_pno, q_title, q_content, q_date, q_hit, q_parentno, q_pw, q_image, q_state, q_category);
			
			tiarray.add(qvo);
		}
		System.out.println("dao에서 리턴");
		return tiarray;
	}
	
	
	public boolean isqna() throws SQLException {
        
	      String sql="SELECT  * FROM qna WHERE q_category='상품문의'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isqna 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }
	
	public boolean isqna2() throws SQLException {
        
	      String sql="SELECT  * FROM qna WHERE q_category='배송문의'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isqna 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }

	
	public boolean isqna3() throws SQLException {
        
	      String sql="SELECT  * FROM qna WHERE q_category='입고요청'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isqna 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }

	
	public boolean isqna4() throws SQLException {
        
	      String sql="SELECT  * FROM qna WHERE q_category='환불관련'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isqna 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }

	
	public boolean isqna5() throws SQLException {
        
	      String sql="SELECT  * FROM qna WHERE q_category='기타문의'";
	      
	      ps=con.prepareStatement(sql);
	      rs=ps.executeQuery();
	      System.out.println("isqna 실행");
	      
	      if(rs.next()) {
	         return true;
	      }
	      
	      return false;
	   }


	public int qnaWrite2(int no, String id, String title, String content, String fileName, String category) throws SQLException {
		
		String sql = "INSERT INTO QNA (q_no,q_id,q_title,q_content,q_parentno,q_date,q_image,q_category) VALUES(?,?,?,?,?,sysdate,?,?)";
		System.out.println("qnaWrite");
		
		
		ps = con.prepareStatement(sql);
		ps.setInt(1, no);
		ps.setString(2, id);
		ps.setString(3, title);
		ps.setString(4, content);
		ps.setInt(5, no);
		ps.setString(6, fileName);
		ps.setString(7, category);
//		ps.setString(7, category);

		
		//1을 반환하게 될거임
		return ps.executeUpdate();
		
	
	}
	
	public void delete_product2(String [] delete_list1) throws SQLException {
	      //qna에서 해당 q_pno 목록에서 지우기
	      
	      String num="";
	      for(int i=0; i<delete_list1.length;i++) {
	         if(i%2!=0) {
	         num+=","+delete_list1[i];
	         }else {
	            num+=delete_list1[i];
	         }
	      }
	      System.out.println("삭제할 상품 번호: "+num);
	      
	      String sql="delete from qna where q_pno in("+num+")";
	      
	      ps=con.prepareStatement(sql);
	      if(ps.executeUpdate()!=0) {
	         num="";
	         System.out.println("qna 상품 삭제 성공");
	         
	      }else {
	            
	      System.out.println("qna 상품 삭제 실패했거나 qna 테이블에 해당 qna가 없음");
	      
	      }
	   }
	
	   
	   public int total_count1() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM qna where q_category='상품문의'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	
	   public int total_count2() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM qna where q_category='배송문의'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	   public int total_count3() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM qna where q_category='환불관련'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	   public int total_count4() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM qna where q_category='입고요청'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	   public int total_count5() throws SQLException {
		   int total=0;
		   String sql = "SELECT * FROM qna where q_category='기타문의'";
		      
	 	  ps = con.prepareStatement(sql);
		      rs = ps.executeQuery();
		      
		  while(rs.next()) {
			  total+=1;
		  }
		  
		  return total;
	   }   
	
}
   
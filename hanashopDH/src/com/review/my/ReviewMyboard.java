package com.review.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class ReviewMyboard implements ReviewImpl{

	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("내 작성글 페이지 서버 도착");
		
		String currentpage=request.getParameter("currentpage");
		if(currentpage==null) {
			currentpage="1";
		}
		System.out.println("currentpage>>"+currentpage);
		
		String currentgroup=request.getParameter("currentgroup");
		
		if(currentgroup==null) {
			currentgroup="1";
		}
		System.out.println("currentgroup>>"+currentgroup);
		
		String sstart=request.getParameter("start");
		System.out.println("시작페이지  "+sstart);
		if(sstart==null) {
			sstart="1";
		}
		int start=Integer.parseInt(sstart);
		System.out.println("숫자 start>>"+start);
		
		String send=request.getParameter("end");
		System.out.println("마지막  "+send);
		if(send==null) {
			send="5";
		}
		int end=Integer.parseInt(send);
		System.out.println("숫자 end>>"+end);
		
		
		
		ArrayList<ReviewVO> arrVO2 = new ArrayList<ReviewVO>();
		String id4 =(String)session.getAttribute("id");
		ReviewDAO tidao3 = null;
		String inReview="";
		ArrayList<ReviewVO> mv2 = null;
		System.out.println(id4);
	try { tidao3 = new ReviewDAO();
	
			mv2  = tidao3.review(id4, start, end);
			arrVO2.addAll(mv2);
			request.setAttribute("my1", arrVO2);
			
			int total=tidao3.total_count(id4);
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
			
			if(!tidao3.isThereReviewlist(id4)) {
				inReview="no";
	

	}
			
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("currentgroup", currentgroup);
} catch ( ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}
request.setAttribute("inReview", inReview);



}

}

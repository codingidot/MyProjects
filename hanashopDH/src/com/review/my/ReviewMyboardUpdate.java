package com.review.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import review.reviewDAO.ReviewDAO;

public class ReviewMyboardUpdate implements ReviewImpl {

	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
		
		System.out.println("ReviewMyboardUpdatae 서버 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	
		
		
	
		/*
		 * String sid = request.getParameter("sid"); session.setAttribute("sid",sid);
		 */
		String title = request.getParameter("r_title");
		String content = request.getParameter("r_content");
		int no = Integer.parseInt(request.getParameter("no"));

		//int point = Integer.parseInt(request.getParameter("point"));
		System.out.println(title+"\t"+content+"\t"+no);
		ReviewDAO dao;
		try {
			dao = new ReviewDAO();
			dao.updatereview(title,content,no);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

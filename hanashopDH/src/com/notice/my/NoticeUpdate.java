package com.notice.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.noticeDAO.noticeDAO;
import review.reviewDAO.ReviewDAO;

public class NoticeUpdate implements NoticeImpl{

	@Override
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession(true);
		
		System.out.println("Notice Update 서버 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
	
	
		String title = request.getParameter("n_title");
		String content = request.getParameter("n_content");
		int no = Integer.parseInt(request.getParameter("no"));

		//int point = Integer.parseInt(request.getParameter("point"));
		System.out.println(title+"\t"+content+"\t"+no);
		noticeDAO dao;
		try {
			dao = new noticeDAO();
			dao.updatenotice(title,content,no);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

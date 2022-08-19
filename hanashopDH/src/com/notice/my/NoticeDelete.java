package com.notice.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.noticeDAO.noticeDAO;

public class NoticeDelete implements NoticeImpl{

	@Override
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession(true);
		
		System.out.println("ReviewMyboardUpdatae 서버 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));

		
		System.out.println(no);
		noticeDAO dao;
		try {
			dao = new noticeDAO();
			dao.noticedelete(no);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

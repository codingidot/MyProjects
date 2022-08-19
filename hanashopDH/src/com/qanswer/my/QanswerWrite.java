package com.qanswer.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qanswer.qanswerDAO.qanswerDAO;

public class QanswerWrite implements qanswerImpl{

	@Override
	public void qanswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session=request.getSession(true);
		
		System.out.println("QanswerWrite 서버 도착");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		String id11 = request.getParameter("id");
		String title1 = request.getParameter("title");
		String content1 = request.getParameter("content");
		
		System.out.println( id11+"\t"+title1+"\t"+content1+"\t");
		qanswerDAO dao11;
		try {
			dao11 = new qanswerDAO();
			int no11=dao11.get_no();
			dao11.qanswerwrite(no11,id11,title1,content1);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

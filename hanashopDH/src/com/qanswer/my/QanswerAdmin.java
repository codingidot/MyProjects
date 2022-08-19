package com.qanswer.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qanswer.qanswerDAO.qanswerDAO;

public class QanswerAdmin implements qanswerImpl{

	@Override
	public void qanswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		System.out.println("QanswerAdmin 서버 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String answer = request.getParameter("q_answer");
		String no12 = request.getParameter("q_no");
		
		System.out.println(answer+ no12);
		qanswerDAO dao12;
		try {
			dao12 = new qanswerDAO();
			dao12.qanswerwrite(no12,answer);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

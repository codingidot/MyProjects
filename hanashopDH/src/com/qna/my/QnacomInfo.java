package com.qna.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class QnacomInfo implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("QancomInfo 서버 도착");
		
		
	    String parentno1 = request.getParameter("qparentno");
		System.out.println("parentno1 : " + parentno1);
		  
		 int parentno = Integer.parseInt(parentno1); 
		 System.out.println("parentno : " + parentno);
		 
		
		int no8 = Integer.parseInt(request.getParameter("qno"));
		QnaDAO tidao8 = null;
		QnaVO vo8 = null;
		System.out.println(no8);
		
		try {
			tidao8 = new QnaDAO();
			
			vo8 = tidao8.ProductQNAView(no8);
			String content = tidao8.getParentContent2(parentno);
			System.out.println(vo8.getQ_id()+"찍히니");
			request.setAttribute("vo", vo8);
			request.setAttribute("content", content);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

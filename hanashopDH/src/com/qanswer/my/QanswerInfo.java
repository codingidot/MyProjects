package com.qanswer.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qanswer.qanswerDAO.qanswerDAO;
import qanswer.qanswerVO.qanswerVO;

public class QanswerInfo implements qanswerImpl{

	@Override
	public void qanswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("QanswerInfo 서버 도착");
		
		int no8 = Integer.parseInt(request.getParameter("no"));
		qanswerDAO tidao8 = null;
		qanswerVO vo8 = null;
		System.out.println(no8);
		
		try {
			tidao8 = new qanswerDAO();
			
			vo8 = tidao8.qanswer(no8);
			System.out.println(vo8.getQ_id()+"찍히니");
			request.setAttribute("vo8", vo8);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

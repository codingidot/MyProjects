package com.qna.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class QnaWarehouse implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		System.out.println("Qanswer 서버 도착");
		
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
		
		
		
		ArrayList<QnaVO> arrVO7 = new ArrayList<QnaVO>();
		QnaDAO tidao7 = null;
		String isqna="";
		ArrayList<QnaVO> mv7 = null;

	
		try {tidao7 = new QnaDAO();
		
		mv7  = tidao7.qnacom3(start,end);
		arrVO7.addAll(mv7);
		request.setAttribute("vo", arrVO7);
		
		int total=tidao7.total_count3();
		String stotal=String.valueOf(total);
		System.out.println("total은?????"+total);
		request.setAttribute("stotal", stotal);
		
		if(!tidao7.isqna3()) {
			isqna="no";
         }
		
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("currentgroup", currentgroup);
		} catch ( ClassNotFoundException | SQLException e1) {
		e1.printStackTrace();
		}
		request.setAttribute("isqna", isqna);
		
		
		
	}
		
}
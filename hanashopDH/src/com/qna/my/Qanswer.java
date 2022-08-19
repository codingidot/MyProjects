package com.qna.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class Qanswer implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*
		
		System.out.println("Qanswer 서버 도착");
		
		ArrayList<QnaVO> arrVO7 = new ArrayList<QnaVO>();
		QnaDAO tidao7 = null;
		ArrayList<QnaVO> mv7 = null;

	
		try {tidao7 = new QnaDAO();
		
		mv7  = tidao7.qanswer();
		arrVO7.addAll(mv7);
		request.setAttribute("my1", arrVO7);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/

	}
}

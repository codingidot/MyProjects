package com.qanswer.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qanswer.qanswerDAO.qanswerDAO;
import qanswer.qanswerVO.qanswerVO;

public class Qanswer implements qanswerImpl{

	@Override
	public void qanswer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("Qanswer 서버 도착");
		
		ArrayList<qanswerVO> arrVO7 = new ArrayList<qanswerVO>();
		qanswerDAO tidao7 = null;
		String isdelivery="";
		ArrayList<qanswerVO> mv7 = null;

	
		try {tidao7 = new qanswerDAO();
		
		mv7  = tidao7.qanswer();
		arrVO7.addAll(mv7);
		request.setAttribute("my1", arrVO7);
		
		
		if(!tidao7.isTheredelivery()) {
			 isdelivery="no";

		
		}
		} catch ( ClassNotFoundException | SQLException e1) {
		e1.printStackTrace();
		}
		request.setAttribute("isdelivery", isdelivery);
		
		
		
	}
		
}
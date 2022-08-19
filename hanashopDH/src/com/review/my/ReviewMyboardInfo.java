package com.review.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class ReviewMyboardInfo implements ReviewImpl{

	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("내 작성글 상세 페이지");
	
		int no = Integer.parseInt(request.getParameter("no"));
		ReviewDAO tidao4 = null;
		ReviewVO vo4 = null;
		System.out.println(no);
		
		try { 
			tidao4 = new ReviewDAO();
			
			vo4 = tidao4.review2(no);
			System.out.println(vo4.getR_id()+"찍히니");
			request.setAttribute("vo4", vo4);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

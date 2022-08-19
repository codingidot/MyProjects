package com.review.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.reviewDAO.ReviewDAO;

public class ReviewDelete implements ReviewImpl {
	
	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("서비스 도착");
		int r_no = Integer.parseInt(request.getParameter("no"));
		System.out.println(r_no);
		
		int deletecheck;
		
		
		
		ReviewDAO rdao = new ReviewDAO();
		
		deletecheck = rdao.reviewDelete(r_no);
		
		request.setAttribute("deletecheck", deletecheck);
		
		
	}
}
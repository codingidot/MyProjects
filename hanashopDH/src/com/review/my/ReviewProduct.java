package com.review.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class ReviewProduct implements ReviewImpl {
	
	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("서비스 도착");
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println(p_no);
		
		int start = 0;
		if(request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		ReviewDAO rdao = null;
		rdao = new ReviewDAO();
		
		ArrayList<ReviewVO> list = null; 
		list = rdao.getList(start, p_no);
		
		int count;
		count = rdao.reviewCheck(p_no);
		System.out.println("count : " + count);
		
		int total;
		total = rdao.getTotal(p_no);
		
		
		int nowPage = start/5 + 1;
		int totalPage = total %5 == 0 ? total /5 : total /5 + 1;
		
		
		System.out.println("count : " + count);
		
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("start", start);
		request.setAttribute("list",list);
		request.setAttribute("count", count);
		
	}
}

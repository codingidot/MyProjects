package com.review.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class ReviewSortlike implements ReviewImpl{

	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("리뷰 추천순 서비스 도착");
		
		String pno1 = request.getParameter("p_no");
		System.out.println("pno1 : " + pno1);
		
		int pno = Integer.parseInt(pno1);
		System.out.println("pno : " + pno);
		
		
		
		
		
		
		int start = 0;
		if(request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		ReviewDAO rdao = null;
		rdao = new ReviewDAO();

		int count;
		count = rdao.reviewCheck(pno);
		System.out.println("count : " + count);
		
		////////////////////////////////////////////////////////////////
		ArrayList<ReviewVO> list = null; 
		list = rdao.getList2(start, pno);
		
		int total;
		total = rdao.getTotal(pno);
		
		
		int nowPage = start/5 + 1;
		int totalPage = total %5 == 0 ? total /5 : total /5 + 1;
		
		
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("start", start);
		request.setAttribute("list",list);
		request.setAttribute("count", count);
		
		
	}
}

package com.qna.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;
import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class Qnalist implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		System.out.println("QanProduct 서비스 왔음");
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		System.out.println("파라미터 넘어왔는지 확인 : " + pno);
		
		QnaDAO qdao = null;
		qdao = new QnaDAO();
		
		ArrayList<QnaVO> list = null;
		list = qdao.Productqnalist(pno);
		System.out.println("정보담았음");
		request.setAttribute("list", list);
		*/
		
		System.out.println("qnalist서비스 도착");
		int pno = Integer.parseInt(request.getParameter("pno"));
		System.out.println("pno: " +pno);
		
		int start = 0;
		if(request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		QnaDAO qdao = null;
		qdao = new QnaDAO();
		
		ArrayList<QnaVO> list = null; 
		list = qdao.getList(start, pno);
		
		int total;
		total = qdao.getTotal(pno);
		
		
		int count = qdao.qnaCheck(pno);
		
		int nowPage = start/10 + 1;
		int totalPage = total %10 == 0 ? total /10 : total /10 + 1;
		
		
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("start", start);
		request.setAttribute("list",list);
		System.out.println(count);
		request.setAttribute("count", count);
		request.setAttribute("total", total);
		
	}
		

}

package com.review.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import likey.likeyDAO.LikeyDAO;
import review.reviewDAO.ReviewDAO;
import review.reviewVO.ReviewVO;

public class ReviewDownbutton implements ReviewImpl {
	
	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		System.out.println("서비스 도착");
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		System.out.println("r_no : " + r_no);
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);

		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("p_no : " + p_no);
		
		LikeyDAO ldao2 = null;
		ldao2 = new LikeyDAO();
		int no = ldao2.get_no();
		System.out.println("get_no 메소드 종료");
		
		int check = 0;
		
		
		String upcheck;
		int upcheck2;
		String state2 = "0";
		
		//만약 아이디가 null값이면 method 실행x
		if(id != "") {
			System.out.println("if문 실행여부 확인");
			LikeyDAO ldao = new LikeyDAO();
			upcheck = ldao.checkLike(p_no, id, r_no);
			System.out.println("upcheck : " + upcheck);
			// 이미 좋아요 누른 상태라면 upcheck ==> 1
			// 좋아요 누른 상태가 아니면 upcehck ==> 0
			state2 = "1";
			
			if(upcheck != "1") {
				state2 = "2";
				// 좋아요 누른 상태가 아니면(upcheck = 0이면)
				// 등록하고 좋아요 숫자 누르는 method 실행
				upcheck2 = ldao.regist(no, p_no, id, r_no);
				
				ReviewDAO rdao = new ReviewDAO();
				
				check = rdao.reviewDownbutton(r_no);
			}
		}
		
		System.out.println("state2 : " + state2);
//		request.setAttribute("check", check);
		request.setAttribute("state2", state2);
		
		
	}
}

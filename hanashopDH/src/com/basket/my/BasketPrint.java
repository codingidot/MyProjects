package com.basket.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.basketDAO.BasketDAO;
import basket.basketVO.BasketVO;
import member.memberDAO.MemberDAO;
import product.productVO.ProductVO;

public class BasketPrint implements BasketImpl{

	@Override
	public void basket(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
		String send=request.getParameter("end");
		System.out.println("마지막  "+send);
		if(send==null) {
			send="5";
		}
		int end=Integer.parseInt(send);
		
	
		
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("currentgroup", currentgroup);
		
		String inBasket="";
		System.out.println("장바구니 목록보기 서버 도착");
		HttpSession session=request.getSession(true);
		BasketDAO DAO2;
		MemberDAO MDAO2;
		int point2=0;
		try {ArrayList<BasketVO> barray1=null;
			DAO2 = new BasketDAO();
			MDAO2=new MemberDAO();
			
			String id1=(String)session.getAttribute("id");
			System.out.println("id는 "+id1);
			session.setAttribute("id", id1);
			int total=DAO2.total_count(id1);
		String stotal=String.valueOf(total);
		System.out.println("total은?????"+total);
		request.setAttribute("stotal", stotal);
			barray1=DAO2.getAllInfo(id1,start,end);
			if(!DAO2.isThereBasket(id1)) {
	            inBasket="no";
	         }
			request.setAttribute("list", barray1);
			request.setAttribute("inBasket", inBasket);
			 point2=MDAO2.get_point(id1);
			request.setAttribute("point", point2);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

package com.basket.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.basketDAO.BasketDAO;

public class BasketEnroll implements BasketImpl{

	@Override
	public void basket(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session=request.getSession(true);
		String size7=request.getParameter("size");
		String scount=request.getParameter("count");
		int count7=Integer.parseInt(scount);
		String sprice=request.getParameter("price");
		int price7=Integer.parseInt(sprice);
		String id7=(String)session.getAttribute("id");
		String name7=request.getParameter("name");
		String sno7=request.getParameter("no");
		int no7=Integer.parseInt(sno7);
		
		
		System.out.println("아이디 찍혀라  "+id7);
		
		   session.setAttribute("id", id7);
		System.out.println("session으로 아이디 찍혀라  "+(String)session.getAttribute("id"));   
		String id8=(String)session.getAttribute("id");
		try {
			BasketDAO DAO=new BasketDAO();
			
			if(DAO.insert_basket(id8, name7, price7, count7, size7, no7)==true) {
				
				System.out.println("등록성공");
			}else {
				System.out.println("등록실패");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

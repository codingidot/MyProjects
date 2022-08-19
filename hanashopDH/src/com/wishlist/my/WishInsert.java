package com.wishlist.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wishlist.wishlistDAO.wishlistDAO;

public class WishInsert implements WishlistImpl {

	@Override
	public void Wishlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
		String size7=request.getParameter("size");
		System.out.println("wishlist insert 사이즈 >>"+size7);
		String scount=request.getParameter("count");
		System.out.println("wishlist scount 사이즈 >>"+scount);
		int count7=Integer.parseInt(scount);
		String sprice=request.getParameter("price");
		System.out.println("wishlist sprice 사이즈 >>"+sprice);
		int price7=Integer.parseInt(sprice);
		String id7=(String)session.getAttribute("id");
		String name7=request.getParameter("name");
		System.out.println("wishlist name 사이즈 >>"+name7);
		String sno7=request.getParameter("no");
		System.out.println("wishlist sno 사이즈 >>"+sno7);
		int no7=Integer.parseInt(sno7);
		
		
		System.out.println("아이디 찍혀라  "+id7);
		
		   session.setAttribute("id", id7);
		System.out.println("session으로 아이디 찍혀라  "+(String)session.getAttribute("id"));   
		
		wishlistDAO wdao=new wishlistDAO();
		wdao.insert_wish(id7, name7, price7, count7, size7, no7);
	}

}

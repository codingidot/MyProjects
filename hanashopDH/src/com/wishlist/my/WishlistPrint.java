package com.wishlist.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wishlist.wishlistDAO.wishlistDAO;
import wishlist.wishlistVO.wishlistVO;

public class WishlistPrint implements WishlistImpl{

	@Override
	public void Wishlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		 
		
		
		HttpSession session=request.getSession(true);
	      
	      System.out.println("wishlist 출력 wishlistPrint.java ");
	      String inWishlist="";
	      ArrayList<wishlistVO> arrVO2 = new ArrayList<wishlistVO>();
	      String id4 =(String)session.getAttribute("id");
	      System.out.println("id는 "+id4);
			session.setAttribute("id", id4);
			
	      wishlistDAO tidao3 = null;
	      ArrayList<wishlistVO> mv2 = null;
	      System.out.println(id4);
	   try { tidao3 = new wishlistDAO();
	   		int total=tidao3.total_count(id4);
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
	         mv2  = tidao3.getAllInfo(id4,start,end);
	         arrVO2.addAll(mv2);
	         if(!tidao3.isThereWishlist(id4)) {
		           inWishlist = "no";
		         }
	         request.setAttribute("inWishlist", inWishlist);
	         request.setAttribute("list", arrVO2);
	      } catch ( ClassNotFoundException | SQLException e1) {
	         e1.printStackTrace();
	      }

	}

}

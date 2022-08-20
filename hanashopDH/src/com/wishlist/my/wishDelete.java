package com.wishlist.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wishlist.wishlistDAO.wishlistDAO;

public class wishDelete implements WishlistImpl{

	@Override
	public void Wishlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session=request.getSession(true);
		
			
		//위시리스트 목록에서 삭제
	
		System.out.println("위시리스트 삭제 서버 도착");
		String [] delete_list=request.getParameterValues("deleteList");
		for(String aa:delete_list) {
		System.out.println("삭제 해야할 아이디, 상품명, 사이즈 "+aa);
		}
			wishlistDAO wdao=new wishlistDAO();
			wdao.delete_wish(delete_list);
			System.out.println("삭제성공");
	}

}

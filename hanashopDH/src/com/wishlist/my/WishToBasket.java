package com.wishlist.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basket.basketDAO.BasketDAO;
import wishlist.wishlistDAO.wishlistDAO;

public class WishToBasket implements WishlistImpl {

	@Override
	public void Wishlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		wishlistDAO wdao=new wishlistDAO();
		System.out.println("위시리스트에서 장바구니 서버 도착");
		String [] delete_list=request.getParameterValues("deleteList");
		
		//위시리스트에서 삭제
		wdao.delete_wish(delete_list);
		
		
		//바스켓에 추가
		BasketDAO DAO=new BasketDAO();
		for(String aa:delete_list) {
		System.out.println("이동 해야할 아이디, 상품명, 사이즈 "+aa);
		String[] split=aa.split(",");
		
		
		// 아이디,상품명,사이즈,개수,가격,상품번호
		if(DAO.insert_basket(split[0], split[1],Integer.parseInt(split[4]) , Integer.parseInt(split[3]), split[2], Integer.parseInt(split[5]))==true) {
								//id7, name7, price7, count7, size7, no7
			System.out.println("등록성공");
		}else {
			System.out.println("등록실패");
		}
		
		
						
		
		}
	}

}

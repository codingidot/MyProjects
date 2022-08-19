package com.basket.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.basketDAO.BasketDAO;
import member.memberDAO.MemberDAO;
import orderlist.orderlistDAO.OrderlistDAO;
import product.productDAO.ProductDAO;

public class BasketDelete implements BasketImpl{

	@Override
	public void basket(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String option=request.getParameter("option12");
		System.out.println("삭제와 결제와 구매중 "+option );
		HttpSession session=request.getSession(true);
		
			
		//장바구니 목록에서 삭제
	
		System.out.println("장바구니 삭제 서버 도착");
		String [] delete_list=request.getParameterValues("deleteList");
		for(String aa:delete_list) {
		System.out.println("삭제 해야할 아이디, 상품명, 사이즈 "+aa);
		}
			try {
			BasketDAO DAO1=new BasketDAO();
			DAO1.delete_basket(delete_list);
				
				System.out.println("삭제성공");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			
	}
}



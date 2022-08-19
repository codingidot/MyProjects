package com.basket.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import basket.basketDAO.BasketDAO;
import basket.basketVO.BasketVO;
import member.memberDAO.MemberDAO;

public class BasketWithdraw implements BasketImpl{

	@Override
	public void basket(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
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
			barray1=DAO2.getAllInfo(id1);
			request.setAttribute("list", barray1);
			
			 point2=MDAO2.get_point(id1);
			request.setAttribute("point", point2);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

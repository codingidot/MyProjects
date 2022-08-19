package com.product.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductShowAll implements ProductImpl {
	@Override //parent에서 선언하고 child에서 구현
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShowAll 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ProductDAO pdao = new ProductDAO();
		
		ArrayList<ProductVO> list = null;
		list = pdao.showAll();
		System.out.println("정보 담았음");
		
		
		ArrayList<ProductVO> list2 = null;
		list2 = pdao.showBest20();
		System.out.println("정보 담았음");

		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		
		
		//DONE
	}
}

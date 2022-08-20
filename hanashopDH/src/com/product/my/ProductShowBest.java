package com.product.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductShowBest implements ProductImpl{
	
	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShowBest 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ProductDAO pdao = null;
		
		pdao = new ProductDAO();
		
		int count = 2;
		ArrayList<ProductVO> list = null;
		list = pdao.showBest();
		System.out.println("정보 담았음");
		System.out.println(count);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
	}
}

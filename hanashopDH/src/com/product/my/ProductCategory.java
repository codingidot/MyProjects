package com.product.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductCategory implements ProductImpl{
	
	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShowBest 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String category = request.getParameter("category");
		System.out.println(category);
		
		ProductDAO pdao = new ProductDAO();
		

		int count = 1; 
		
		ArrayList<ProductVO> list = null;
		list = pdao.category(category);
		System.out.println("정보 담았음");
		
		ArrayList<ProductVO> list2 = null;
		list2 = pdao.categoryBest5(category);

		request.setAttribute("list", list); // 카테고리 검색결과 담는 list
		request.setAttribute("category", category); // 어떤 카테고리를 했는지 다음페이지에서 사용하기 때문에 넘겨줘야 함
		request.setAttribute("list2", list2); // 카테고리내 best5 담는 list
		request.setAttribute("count", count); // 여기서 제대로 처리가 되어서 넘어갔다면 1
		
	}
}

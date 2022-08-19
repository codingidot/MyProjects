package com.product.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductSort implements ProductImpl{
	
	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Toplist 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String category = request.getParameter("category");
		System.out.println("category : " + category);
		
		String orderby = request.getParameter("orderby");
		System.out.println("orderby : " + orderby);
		
		String item = request.getParameter("item");
		System.out.println("item : " + item);
		
		orderby = orderby.replaceAll("\'","");

		ProductDAO pdao = new ProductDAO();
		

		int count = 1;
		ArrayList<ProductVO> list = null;
		ArrayList<ProductVO> list2 = null;
		// 검색했을때 정렬하는건 다른 다른식으로 작동해야함
		// 그냥 헤더에서 상품탭 누르면 item값을 안받아왔으므로 null값
		if(item.length() >= 2) {
			System.out.println("if문 들어왔음");
			list = pdao.Sortlist2(item, orderby);
			list2 = pdao.searchBest5(item);
		}else {
			System.out.println("else문 들어왔음");
			list = pdao.Sortlist(category, orderby);
			list2 = pdao.categoryBest5(category);
		}

		System.out.println("count : " + count);
		
		
		System.out.println("정보 담았음");
		request.setAttribute("list", list); // 정렬해주고 값 담는 list
		request.setAttribute("list2", list2); // 해당 list에서 인기순 top5 출력
		request.setAttribute("count", count); // 여기서 잘 지나갔으면 1
		

	}
}

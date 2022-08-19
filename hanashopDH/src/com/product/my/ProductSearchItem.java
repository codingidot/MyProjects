package com.product.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductSearchItem implements ProductImpl {
	@Override //parent에서 선언하고 child에서 구현
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SearchItem 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String item = request.getParameter("item");
		System.out.println(item);
		ProductDAO pdao = null;
		
		pdao = new ProductDAO();
		
		ArrayList<ProductVO> list = null;
		list = pdao.SearchItem(item);
		System.out.println("정보 담았음");


		ArrayList<ProductVO> list2 = null;
		ArrayList<ProductVO> list3 = null;
		
		
		int count = 0;
		if(list.size() != 0) {
			System.out.println("검색결과 있음");
			list2 = pdao.searchBest5(item);
			count = 1;
		}else {
			System.out.println("검색결과 없음");
			System.out.println("제품이 없다면 실행");
			list3 = pdao.showBest20();
		}

		request.setAttribute("count", count); // 검색결과가 있다면1 , 없다면 0
		request.setAttribute("list", list); // 검색결과 담는 list
		request.setAttribute("list2", list2); // 검색결과 있을때 그 검색결과 내용중에서 best5담는 list
		request.setAttribute("list3", list3); // 검색결과 없을때 대신 인기아이템 보여주는 list
	}
}

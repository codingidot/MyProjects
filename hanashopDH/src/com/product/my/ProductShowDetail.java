package com.product.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductShowDetail implements ProductImpl {
	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("showDetail 서비스 도착");
		
		
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		
		
		ProductVO vo = new ProductDAO().showDetail(p_no);
		
		request.setAttribute("sp_no", p_no);
		request.setAttribute("vo", vo);
		
	}
}

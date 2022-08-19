package com.product.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class ProductPrint implements ProductImpl{

	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ProductDAO pdao=new ProductDAO();	
			String currentpage=request.getParameter("currentpage");
			if(currentpage==null) {
				currentpage="1";
			}
			System.out.println("currentpage>>"+currentpage);
			
			String currentgroup=request.getParameter("currentgroup");
			
			if(currentgroup==null) {
				currentgroup="1";
			}
			System.out.println("currentgroup>>"+currentgroup);
			
			String sstart=request.getParameter("start");
			System.out.println("시작페이지  "+sstart);
			if(sstart==null) {
				sstart="1";
			}
			int start=Integer.parseInt(sstart);
			String send=request.getParameter("end");
			System.out.println("마지막  "+send);
			if(send==null) {
				send="5";
			}
			int end=Integer.parseInt(send);
			ArrayList<ProductVO> parray=pdao.getAllInfo(start,end);
		
			int total=pdao.total_count();
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
			request.setAttribute("sangpum", parray);
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("currentgroup", currentgroup);
			System.out.println("request.setAttribute으로 저장");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}

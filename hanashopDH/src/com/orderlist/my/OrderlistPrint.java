package com.orderlist.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.productDAO.ProductDAO;
import product.productVO.ProductVO;

public class OrderlistPrint implements OrderlistImpl {

	@Override
	public void Orderlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArrayList<ProductVO> plist=new ArrayList<ProductVO>();
			ProductDAO pdao=new ProductDAO();
			plist=pdao.MoneyAllInfo();
			for(ProductVO pvo:plist) {
				System.out.println(pvo.getP_category()+"\t"+pvo.getP_category2()+"\t"+pvo.getP_count()+"\t"+pvo.getP_count()*pvo.getP_price());
			}
			request.setAttribute("plist2", plist);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

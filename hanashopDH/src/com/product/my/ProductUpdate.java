package com.product.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class ProductUpdate implements ProductImpl{

	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String p_no=request.getParameter("p_no");	
		String p_name=request.getParameter("p_name");
		String p_price=request.getParameter("p_price");
		String p_stockS=request.getParameter("p_stockS");
		String p_stockM=request.getParameter("p_stockM");
		String p_stockL=request.getParameter("p_stockL");
		String cat1=request.getParameter("cat1"); //카테고리1
	      String cat2=request.getParameter("cat2"); //카테고리2
	     
		System.out.println("수정할 p_no는 "+p_no+"수정할 p_name는 "+p_name+"수정할 p_rice는 "+p_price
				+" 수정할 카테고리1"+cat1+" 수정할 카테고리2"+cat2+" 수정할 p_stockS는 "+p_stockS+"수정할 p_stockM는 "+p_stockM+"수정할 p_stockL는 "+p_stockL);
		
	      
	      
		request.setAttribute("p_no", p_no);
		request.setAttribute("p_name", p_name);
		request.setAttribute("p_price", p_price);
		request.setAttribute("p_stockS", p_stockS);
		request.setAttribute("p_stockM", p_stockM);
		request.setAttribute("p_stockL", p_stockL);
		request.setAttribute(p_name, p_stockL);
		request.setAttribute("cat1", cat1);
		request.setAttribute("cat2", cat2);
		
		
		
		
		 
	      
		
	}

}

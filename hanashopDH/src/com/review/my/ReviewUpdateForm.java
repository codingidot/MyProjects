package com.review.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewUpdateForm implements ReviewImpl{

	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		
		
		String r_content = request.getParameter("r_content");
		r_content = r_content.replaceAll("<br>", "\r\n");
		System.out.println("replace 한 content : " + r_content);
		
		request.setAttribute("r_content", r_content);
	}
	
}

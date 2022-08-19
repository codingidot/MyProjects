package com.basket.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BasketImpl {
		
	public void basket(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}

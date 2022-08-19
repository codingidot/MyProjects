package com.pay.my;
import java.sql.SQLException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

public interface PayImpl {
	
	

		public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception;
		
	
}

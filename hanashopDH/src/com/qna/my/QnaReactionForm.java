package com.qna.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaReactionForm implements QnaImpl {

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String content = request.getParameter("content");
		System.out.println("content : " + content);
		content = content.replaceAll("<br>", "\r\n");
		System.out.println("replace한 content : " + content);
		
		
		request.setAttribute("content", content);
		
	}

}

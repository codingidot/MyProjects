package com.qna.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class qnaUpdateForm implements QnaImpl {

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String qno1 = request.getParameter("qno");
		System.out.println("qno1 : " + qno1);
		
		int qno = Integer.parseInt(qno1);
		System.out.println("qno : " + qno);
		
		String content = request.getParameter("content");
		System.out.println("content : " + content);
	
		content = content.replaceAll("<br>", "\r\n");
		System.out.println("replace한 content : " + content);
		
		
		QnaVO vo = new QnaDAO().QnaUpdateForm(qno);
		
		
		
		request.setAttribute("vo", vo);
		
	}

}

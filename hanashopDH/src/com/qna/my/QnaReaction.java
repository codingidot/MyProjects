package com.qna.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class QnaReaction implements QnaImpl {
	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("서비스도착");
		
		String id = request.getParameter("id");
		System.out.println("id :" +id);
		
		String pno1 = request.getParameter("pno");
		System.out.println("pno1 :" +pno1);
		
		int pno = Integer.parseInt(pno1);
		System.out.println("pno :" +pno);
		
		String title = request.getParameter("title");
		System.out.println("title :" +title);

		String content = request.getParameter("content");
		System.out.println("content :" +content);
		
		String parentno1 = request.getParameter("parentno");
		System.out.println("parentno1 :" +parentno1);
		
		int parentno = Integer.parseInt(parentno1);
		System.out.println("parentno :" +parentno);
		
		String pw = request.getParameter("pw");
		System.out.println("pw :" +pw);
		
		String category = request.getParameter("category");
		System.out.println("category : " + category);
		
		content = content.replaceAll("\r\n", "<br>");
		System.out.println("replace한 content : " + content);
//		content = content.replaceAll("<br>", "\r\n");
//		System.out.println("replace한 content : " + content);
		
		QnaDAO qdao = new QnaDAO();
		
		int no = qdao.get_no();
		
		
		
		int reactioncheck = qdao.qnareaction(no, id,pno,title,content,parentno,pw,category);
		
		int changeState = qdao.changeState(parentno);
		System.out.println(changeState);
		System.out.println("reactioncheck : " + reactioncheck);
		
		request.setAttribute("reactioncheck", reactioncheck);
//		request.setAttribute("vo2", vo2);
		
	}
}
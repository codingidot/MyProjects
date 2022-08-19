package com.qna.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;

public class QnaAdmin implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println("서비스도착");
		
		String id = request.getParameter("q_id");
		System.out.println("id :" +id);
		
		String pno1 = request.getParameter("q_pno");
		System.out.println("pno1 :" +pno1);
		
		int pno = Integer.parseInt(pno1);
		System.out.println("pno :" +pno);
		
		String title = request.getParameter("q_title");
		System.out.println("title :" +title);

		String answer = request.getParameter("q_answer");
		System.out.println("content :" +answer);
		
		String parentno1 = request.getParameter("qparentno");
		System.out.println("parentno1 : " + parentno1);
			  
		 int parentno = Integer.parseInt(parentno1); 
		 System.out.println("parentno : " + parentno);
			
		
		String pw = request.getParameter("q_pw");
		System.out.println("pw :" +pw);
		
		
		QnaDAO qdao = new QnaDAO();
		
		int no = qdao.get_no();
		
		//답변글엔 질문내용도 같이 보여야함
		
		
//		ArrayList<QnaVO> vo2 = null;
//		vo2 = qdao.getParentContent(parentno);
		
		
		int reactioncheck = qdao.qnareaction2(no,id,pno,title,answer,parentno,pw);
		
		int changeState = qdao.changeState(parentno);
		System.out.println(changeState);
		System.out.println("reactioncheck : " + reactioncheck);
		
		request.setAttribute("reactioncheck", reactioncheck);
//		request.setAttribute("vo2", vo2);
		
	}
}
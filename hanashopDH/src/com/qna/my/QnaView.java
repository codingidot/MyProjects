package com.qna.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;
import qna.qnaVO.QnaVO;

public class QnaView implements QnaImpl {
	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("서비스도착");
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		System.out.println("qno : " + qno);
		
		String pw = request.getParameter("pw");
		System.out.println("pw : " + pw);
		
		String parentno1 = request.getParameter("parentno");
		System.out.println("parentno1 : " + parentno1);
		
		int parentno = Integer.parseInt(parentno1);
		System.out.println("parentno : " + parentno);
		
		

		QnaVO vo = new QnaDAO().ProductQNAView(qno);
		
		QnaDAO qdao = new QnaDAO();
		
		String pwcheck = qdao.qnapwcheck(pw);
		
		
		System.out.println("pwcheck : " + pwcheck);
		
		
		
//		ArrayList<QnaVO> vo2 = null;
//		vo2 = qdao.getParentContent(parentno);
		String content = qdao.getParentContent(parentno);
		String content2 = qdao.getChildContent(parentno);
		
		int type = qdao.type(qno, parentno);
		System.out.println("type : " + type +" ===> 1이면 원글, 0이면 답변글");
		request.setAttribute("pwcheck", pwcheck);

		
		request.setAttribute("sp_no", qno);
		request.setAttribute("vo", vo);
		request.setAttribute("pwcheck", pwcheck);
		request.setAttribute("content", content);
		request.setAttribute("content2", content2);
		request.setAttribute("type", type);
		
		new QnaDAO().Uphit(qno);
		
		
	}

}

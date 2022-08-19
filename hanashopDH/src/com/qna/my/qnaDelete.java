package com.qna.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.qnaDAO.QnaDAO;

public class qnaDelete implements QnaImpl{

	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	
		
		System.out.println("qnaDelete 서비스 도착");

//		<a href="qnadelete.do?qparentno=${vo.q_parentno}&action='delete'" onclick="del();">리뷰삭제</a>
		
		String qparentno1 = request.getParameter("qparentno");
		System.out.println("qno1 = " + qparentno1);
		
		int qparentno = Integer.parseInt(qparentno1);
		System.out.println("qno = " + qparentno);
		
		
		QnaDAO qdao = new QnaDAO();
		int delete = qdao.qnaDelete(qparentno);
		
		System.out.println("delete = " + delete + "==> 1이면 삭제성공");
		
		int deletecheck = 0;
		if(delete == 1) {
			deletecheck = 1;
		}
		request.setAttribute("deletecheck", deletecheck);
		
		
	}

}

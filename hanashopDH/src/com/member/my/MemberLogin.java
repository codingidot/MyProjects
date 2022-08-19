package com.member.my;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import member.memberVO.MemberVO;


public class MemberLogin implements MemberImpl{
	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
		// TODO Auto-generated method stub
		
		System.out.println("MemberLogin 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String u_id1 = request.getParameter("u_id1");
		String u_pw = request.getParameter("u_pw");
		
		//값이 제대로 넘어왔는지 확인(콘솔창)
		System.out.println("아이디 넘어왔음 : " + u_id1);
		System.out.println("비밀번호 넘어왔음 : " + u_pw);


		MemberDAO mdao=new MemberDAO();
		String result = mdao.goLogin(u_id1, u_pw);
		System.out.println("result >>>>>>>>>>>> "+result);
		if(result.equals("3")) {
			System.out.println("result가 3과 일치함");
			session.setAttribute("id",u_id1 );
		}
		request.setAttribute("result", result);
		
	}
	
	


}

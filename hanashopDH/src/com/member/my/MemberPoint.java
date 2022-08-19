package com.member.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;

public class MemberPoint implements MemberImpl {
	//멤버 테이블에서 해당 id 포인트 가져오기
	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
		String id23=(String)session.getAttribute("id");
		String size23=request.getParameter("size");
		String scount23=request.getParameter("count");
		String sprice23=request.getParameter("price");
		String name23=request.getParameter("name");
		String sno23=request.getParameter("no");
		String key23=request.getParameter("key");
		request.setAttribute("size", size23);
		request.setAttribute("count", scount23);
		request.setAttribute("price", sprice23);
		request.setAttribute("name", name23);
		request.setAttribute("no", sno23);
		request.setAttribute("key", key23);
		
		try {
			MemberDAO mdao23=new MemberDAO();
			
			int point=mdao23.get_point(id23);
			System.out.println("현재 포인트: "+point);
			request.setAttribute("point", point);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

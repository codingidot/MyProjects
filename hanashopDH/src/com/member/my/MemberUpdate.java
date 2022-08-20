package com.member.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;

public class MemberUpdate implements MemberImpl{

	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		System.out.println("MemberUpdate 서버 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		/*
		 * String sid = request.getParameter("sid"); session.setAttribute("sid",sid);
		 */
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String d = request.getParameter("date");
		//String d = request.getParameter("d");
		String address = request.getParameter("address");
		//int point = Integer.parseInt(request.getParameter("point"));
		System.out.println(id+"\t"+ pw+"\t"+name+"\t"+tel+"\t"+email+"\t"+d+"\t"+address+"\t");
		MemberDAO dao;
		try {
			dao = new MemberDAO();
			dao.update_all(name,id,pw,tel,d,email,address);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

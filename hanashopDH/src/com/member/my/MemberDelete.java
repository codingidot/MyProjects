package com.member.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.memberDAO.MemberDAO;

public class MemberDelete implements MemberImpl{

	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String [] delete_member=request.getParameterValues("delMemberList");
		try {
			MemberDAO mdao1=new MemberDAO();
			mdao1.delete_ID(delete_member);
			System.out.println("아이디 삭제 끝");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

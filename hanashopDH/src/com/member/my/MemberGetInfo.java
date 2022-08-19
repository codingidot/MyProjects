package com.member.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import member.memberVO.MemberVO;

public class MemberGetInfo implements MemberImpl{

	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(true);
		
		System.out.println("MemberGetInfo 서버 도착");
		
		ArrayList<MemberVO> arrVO = new ArrayList<MemberVO>();
		String id2 = request.getParameter("id");
		MemberDAO tidao1 = null;
		MemberVO mv = null;
	try { tidao1 = new MemberDAO();
			mv  = tidao1.getInfo(id2);
			arrVO.add(mv);
			request.setAttribute("my", arrVO);
		} catch ( ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

}

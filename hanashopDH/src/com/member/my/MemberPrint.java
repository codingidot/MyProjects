package com.member.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.memberDAO.MemberDAO;
import member.memberVO.MemberVO;

public class MemberPrint implements MemberImpl{

	@Override
	public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArrayList<MemberVO> mlist=new ArrayList<MemberVO>();
			
			MemberDAO mdao=new MemberDAO();
			String currentpage=request.getParameter("currentpage");
			if(currentpage==null) {
				currentpage="1";
			}
			System.out.println("currentpage>>"+currentpage);
			
			String currentgroup=request.getParameter("currentgroup");
			
			if(currentgroup==null) {
				currentgroup="1";
			}
			System.out.println("currentgroup>>"+currentgroup);
			
			String sstart=request.getParameter("start");
			System.out.println("시작페이지  "+sstart);
			if(sstart==null) {
				sstart="1";
			}
			int start=Integer.parseInt(sstart);
			String send=request.getParameter("end");
			System.out.println("마지막  "+send);
			if(send==null) {
				send="5";
			}
			int end=Integer.parseInt(send);
			mlist=mdao.getAllInfo2(start,end);
			
			int total=mdao.total_count();
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("currentgroup", currentgroup);
			System.out.println("request.setAttribute으로 저장");
			request.setAttribute("mlist", mlist);
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

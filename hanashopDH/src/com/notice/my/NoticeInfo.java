package com.notice.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.noticeDAO.noticeDAO;
import notice.noticeVO.noticeVO;

public class NoticeInfo implements NoticeImpl{

	@Override
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("NoticeInfo 서버 도착");
		
		int no6 = Integer.parseInt(request.getParameter("no"));
		noticeDAO tidao6 = null;
		noticeVO vo6 = null;
		System.out.println(no6);
		
		try {
			tidao6 = new noticeDAO();
			
			vo6 = tidao6.notice(no6);
			
			request.setAttribute("vo", vo6);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

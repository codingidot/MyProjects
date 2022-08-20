package com.notice.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.noticeDAO.noticeDAO;

public class NoticeEnroll implements NoticeImpl {

	@Override
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
			try {
				noticeDAO nDAO=new noticeDAO();
				int no2=nDAO.get_no();
				nDAO.insert_notice(no2,title ,content );
				System.out.println("공지사항 등록 완료");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
	}

}

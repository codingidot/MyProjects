package com.notice.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.noticeDAO.noticeDAO;
import notice.noticeVO.noticeVO;

public class Notice implements NoticeImpl{

	@Override
	public void notice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(true);
		
		System.out.println("Notice 서버 도착");
		
		

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
		System.out.println("숫자 start>>"+start);
		
		String send=request.getParameter("end");
		System.out.println("마지막  "+send);
		if(send==null) {
			send="5";
		}
		int end=Integer.parseInt(send);
		System.out.println("숫자 end>>"+end);
		
		
		
		String inNotice="";
		ArrayList<noticeVO> arrVO5 = new ArrayList<noticeVO>();
		noticeDAO tidao5 = null;
		ArrayList<noticeVO> mv5 = null;

	try { tidao5 = new noticeDAO();
	
			mv5  = tidao5.notice(start,end);
			arrVO5.addAll(mv5);
			request.setAttribute("my1", arrVO5);
			int total=tidao5.total_count();
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
			
			if(!tidao5.isThereNotice()) {
				inNotice="no";
	

	}
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("currentgroup", currentgroup);
} catch ( ClassNotFoundException | SQLException e1) {
	e1.printStackTrace();
}
request.setAttribute("inNotice", inNotice);



}

}

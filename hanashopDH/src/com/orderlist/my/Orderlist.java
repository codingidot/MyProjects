package com.orderlist.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import orderlist.orderlistDAO.OrderlistDAO;
import orderlist.orderlistVO.OrderlistVO;

public class Orderlist implements OrderlistImpl{

	@Override
	public void Orderlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		HttpSession session=request.getSession(true);
		
		System.out.println("Orderlist 서버 도착");
		
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
		
		
	try { 
		
		ArrayList<OrderlistVO> arrVO1 = new ArrayList<OrderlistVO>();
		String id3 = (String)session.getAttribute("id");
		String inOrderlist="";
		OrderlistDAO tidao2 =new OrderlistDAO();
		ArrayList<OrderlistVO> mv1  = tidao2.orderinfo(id3,start,end);
			arrVO1.addAll(mv1);
			request.setAttribute("my1", arrVO1);
			
			int total=tidao2.total_count(id3);
			String stotal=String.valueOf(total);
			System.out.println("total은?????"+total);
			request.setAttribute("stotal", stotal);
			if(!tidao2.isThereOrderlist(id3)) {
			            inOrderlist="no";
			            System.out.println(inOrderlist);

			}
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("currentgroup", currentgroup);
			request.setAttribute("inOrderlist",inOrderlist);
			System.out.println("request.setAttribute으로 저장");
			//request.setAttribute("inOrderlist", inOrderlist);
		} catch ( SQLException e1) {
			e1.printStackTrace();
		}
		

	
	
	}
	
}

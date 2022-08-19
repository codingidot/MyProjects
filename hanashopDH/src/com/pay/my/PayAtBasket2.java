package com.pay.my;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import orderlist.orderlistDAO.OrderlistDAO;
import product.productDAO.ProductDAO;

public class PayAtBasket2 implements PayImpl {
	// 장바구니에서 구매하기
	@Override
	public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
		String point12=request.getParameter("usepoint"); //사용하는 포인트값
		request.setAttribute("usepoint", point12);//(int형으로 넣어줘야함)
		int ipoint12=Integer.parseInt(point12);//사용하는 포인트값(int)---dao에 들어갈 인자
		String nowpoint12=request.getParameter("nowpoint");// 현재 가지고 있는 포인트---dao에 들어갈 인자
		request.setAttribute("nowpoint", nowpoint12);
		int inowpoint12=Integer.parseInt(nowpoint12);
		int resultPoint=inowpoint12-ipoint12;//현재 가지고 있는 포인트에서 사용하는 포인트 값 빼준 값---dao에 들어갈 인자
		request.setAttribute("resultPoint", resultPoint);//(int형으로 넣어줘야함)
		String id12=(String)session.getAttribute("id");//사용자 아이디 받음---dao에 들어갈 인자
		request.setAttribute("id", id12);//사용자 아이디 request에 저장
		System.out.println("서버로 온 포인트 :" + point12);
		String[] arr_paydo=request.getParameterValues("paylist");//장바구니에서  받아온 checkbox 선택한 값들
		request.setAttribute("paylist", arr_paydo);
		String[] arr_paydo2=request.getParameterValues("paylist2");
		String totPrice= request.getParameter("totPrice");
		int itotPrice=Integer.parseInt(totPrice);
		request.setAttribute("totPrice", totPrice);
		String address12=request.getParameter("roadFullAddr");//배송지---dao에 들어갈 인자
		request.setAttribute("roadFullAddr",address12 );
		String payDate=request.getParameter("paydate");//구매날짜---dao에 들어갈 인자
		request.setAttribute("payDate", payDate);
		String realpay=request.getParameter("realpay");
		request.setAttribute("realpay", realpay);
		String name=request.getParameter("name");
		request.setAttribute("name", name);
		request.setAttribute("basket","basket");
		ArrayList<String> paylist12=new ArrayList<String>();
		String key=(String)request.getParameter("key");
		//paysuccess.jsp에서 결제가 완료되면 여기서 key값을 받아 여기서 정보처리여부 결정
		System.out.println("key값>>"+key);
		
		if(key==null) {//null에러를 위한것
			key="";
		}
		for (String aa : arr_paydo) {
			System.out.println("1111결제해야할 아이디, 상품명, 사이즈,개수,가격, p_no >>>>>>>" + aa);
			paylist12.add(aa);
		}
		if(key.equals("ok")) {
		for (String aa : arr_paydo2) {
			System.out.println("2222결제해야할 아이디, 상품명, 사이즈,개수,가격, p_no >>>>>>>" + aa);
			paylist12.add(aa);
		}
		}
		request.setAttribute("paylist", paylist12);
		

		
		
		
		  MemberDAO MDAO45; 
		  OrderlistDAO DAO45; 
		  ProductDAO PDAO45; 
		  
			  MDAO45 = new MemberDAO(); 
			  DAO45=new OrderlistDAO(); 
			  PDAO45=new ProductDAO();
			  
			 
			  String checkStock="";
			  
			  if(!key.equals("ok")) {
			  checkStock=PDAO45.check_stock(arr_paydo);
			  request.setAttribute("checkStock", checkStock); 
			  System.out.println("check_stock값>>>"+checkStock);
			  }
			  if(key.equals("ok")) {//paysuccess.jsp에서 결제 성공후 오는거니 재고는 enough으로 설정해줌
					checkStock="enough";
					request.setAttribute("checkStock", checkStock); 
					String nopay="yes";
					System.out.println("nopay>>"+nopay);
					request.setAttribute("nopay", nopay);
				}
			  if(!checkStock.equals("enough")) {
				 System.out.println("재고부족!!!");
				
			  }else {
			if(key.equals("ok")) {//ok를 받아왔으면 paysuccess.jsp에서 결제가 완료되고 submit되어 ok가 온것이다.
			  if(ipoint12!=0) {
					
					DAO45.pay(arr_paydo2, ipoint12);//결제시 orderlist에 추가되고 basket에서는 삭제
					MDAO45.update_point(id12, resultPoint); //포인트를 사용했으니 사용한 만큼 빼줌
					PDAO45.pay2(arr_paydo2);	//product 테이블 재고변경
					DAO45.insertOrderlist2(payDate, address12, itotPrice, id12);
				}else {
					DAO45.pay(arr_paydo2, ipoint12);//결제시 orderlist에 추가되고 basket에서는 삭제
					MDAO45.pay3(arr_paydo2, id12);//포인트적립
					PDAO45.pay2(arr_paydo2);	//product 테이블 재고변경
					DAO45.insertOrderlist2(payDate, address12, itotPrice, id12);
				}
			  System.out.println("결제성공");
			} 
				
			  }
	}

}
package com.pay.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import orderlist.orderlistDAO.OrderlistDAO;
import product.productDAO.ProductDAO;

public class PayAtDetail implements PayImpl{

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
		System.out.println("서버로 온 포인트 :"+point12);
		String size12=request.getParameter("size");//구매하는 사이즈---dao에 들어갈 인자
		request.setAttribute("size", size12);
		String scount12=request.getParameter("count");//구매하는 개수---dao에 들어갈 인자
		request.setAttribute("count", scount12);
		String sprice12=request.getParameter("price");//구매하는 상품 하나의 가격---dao에 들어갈 인자
		request.setAttribute("price", sprice12);
		String name12=request.getParameter("name"); //상품명---dao에 들어갈 인자
		request.setAttribute("name", name12);
		String sno12=request.getParameter("no"); //product테이블에서 상품번호---dao에 들어갈 인자
		request.setAttribute("no", sno12);
		String address12=request.getParameter("roadFullAddr");//배송지---dao에 들어갈 인자
		request.setAttribute("roadFullAddr",address12 );
		String payDate=request.getParameter("paydate");//구매날짜---dao에 들어갈 인자
		request.setAttribute("payDate", payDate);
		String buy=id12+","+name12+","+size12+","+scount12+","+sprice12+","+sno12;//문자열로 넣어서 dao에서 정보를 분리해 사용할거임
		//id, 상품명, 사이즈, 개수, 가격 p_no순서로 들어옴  
		String pay_list[]= {buy};//메소드에 넣어줄 인자형태인 배열로 넣어줌---dao에 들어갈 인자
		request.setAttribute("pay_list", pay_list);	
		
		String key=(String)request.getParameter("key");
		//paysuccess.jsp에서 결제가 완료되면 여기서 key값을 받아 여기서 정보처리여부 결정
		System.out.println("key값>>"+key);
		
		if(key==null) {//null에러를 위한것
			key="";
		}
		
		try {
			OrderlistDAO DAO1=new OrderlistDAO();
			ProductDAO PDAO1=new ProductDAO();
			MemberDAO MDAO1=new MemberDAO();
			
			String checkStock=PDAO1.check_stock(pay_list);// 재고가 충분한지 확인
			request.setAttribute("checkStock", checkStock);//재고 충분한지 확인한 값을 paysuccess로 전달
			if(key.equals("ok")) {//paysuccess.jsp에서 결제 성공후 오는거니 재고는 enough으로 설정해줌
				checkStock="enough";
				String nopay="yes";
				request.setAttribute("nopay", nopay);
			}
			
			String realpay=request.getParameter("realpay");//실제로 지불해야하는 값
			int price12=Integer.parseInt(realpay);//---dao에 들어갈 인자
			request.setAttribute("realpay", realpay);//결제 금액 전송
			
			if(!checkStock.equals("enough")) {//재고가 부족할 경우 데이터베이스 처리를 안함
				System.out.println("재고부족");
			}else {
				
				if(key.equals("ok")) {//ok를 받아왔으면 paysuccess.jsp에서 결제가 완료되고 submit되어 ok가 온것이다.
				if(ipoint12!=0) {//사용할 포인트가 0이 아닐 경우 포인트 적립은 하지않고 사용하는 포인트만큼 차감 
				
					DAO1.pay(pay_list, ipoint12);//결제시 orderlist에 추가되고 basket에서는 삭제
					MDAO1.update_point(id12, resultPoint); //포인트를 사용했으니 사용한 만큼 빼줌
					PDAO1.pay2(pay_list);	//product 테이블 재고변경
					DAO1.insertOrderlist2(payDate, address12, price12, id12);//orderlist2테이블에 구매날짜,주소,지불가격,아이디를 넣어줌
				}else {//사용하는 포인트가 0일 경우 포인트 적립
				DAO1.pay(pay_list, ipoint12);//결제시 orderlist에 추가되고 basket에서는 삭제
				MDAO1.pay3(pay_list, id12);//포인트적립
				PDAO1.pay2(pay_list);	//product 테이블 재고변경
				DAO1.insertOrderlist2(payDate, address12, price12, id12);//orderlist2테이블에 구매날짜,주소,지불가격,아이디를 넣어줌
				}
		
			System.out.println("결제성공");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
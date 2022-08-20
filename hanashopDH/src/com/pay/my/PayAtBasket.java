package com.pay.my;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.memberDAO.MemberDAO;
import orderlist.orderlistDAO.OrderlistDAO;
import product.productDAO.ProductDAO;

public class PayAtBasket implements PayImpl {
	//장바구니에서 구매하기
	@Override
	public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(true);
				String id9=(String)session.getAttribute("id");
				String [] pay_list11=request.getParameterValues("deleteList");
				ArrayList<String> arr_payList=new ArrayList<String>(); 
				ArrayList<String> arr_paydo=new ArrayList<String>();
				MemberDAO mdao23=new MemberDAO();
				
				int point=mdao23.get_point(id9);
				System.out.println("현재 포인트: "+point);
				
				int total=0;
				
				String[] split;
				String ask=request.getParameter("askpoint");
				
				for(String aa:pay_list11) {
					System.out.println("paypayList.do--결제해야할 아이디, 상품명, 사이즈,개수,가격, b_no >>>>>>>"+aa);
					split=aa.split(",");
					
					int icount=Integer.parseInt(split[3]);
					int iprice=Integer.parseInt(split[4]);
					total+=icount*iprice;
					String totPrice=String.valueOf(icount*iprice);
					arr_payList.add("상품명 :"+split[1]+"  \t"+"사이즈 :"+split[2]+"  \t"+"개수 :"+split[3]+"  \t"+"가격 :"+totPrice);
					System.out.println("상품명 :"+split[1]+"\t"+"사이즈 :"+split[2]+" \t"+"개수 :"+split[3]+"\t"+"가격 :"+totPrice);
					arr_paydo.add(aa);
				}
				
				request.setAttribute("arr_payList", arr_payList);
				request.setAttribute("arr_paydo", arr_paydo);
				request.setAttribute("total",total );
				request.setAttribute("point", point);
				
				
				/*
				 * System.out.println("ask는-->>"+ask); if(ask==null) { ask=""; }
				 * 
				 * for(String aa:pay_list11) {
				 * System.out.println("paypayList.do--결제해야할 아이디, 상품명, 사이즈,개수,가격, b_no >>>>>>>"
				 * +aa); pay_arr.add(aa); }
				 * 
				 * MemberDAO MDAO45; OrderlistDAO DAO45; ProductDAO PDAO45; try { MDAO45 = new
				 * MemberDAO(); DAO45=new OrderlistDAO(); PDAO45=new ProductDAO();
				 * 
				 * int point45=0; if(ask.equals("yes")) {
				 * 
				 * point45=MDAO45.get_point(id9); DAO45.pay(pay_list11, point45);
				 * MDAO45.update_point(id9); PDAO45.pay2(pay_list11); }else {
				 * DAO45.pay(pay_list11, point45); MDAO45.pay3(pay_list11, id9);
				 * PDAO45.pay2(pay_list11); } } catch (ClassNotFoundException | SQLException e)
				 * { e.printStackTrace(); }
				 */
	}
	


}

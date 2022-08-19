package com.product.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import basket.basketDAO.BasketDAO;
import product.productDAO.ProductDAO;
import qna.qnaDAO.QnaDAO;
import review.reviewDAO.ReviewDAO;

public class ProductDelete implements ProductImpl{

   @Override
   public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String [] delete_list1=request.getParameterValues("deleteList1");
      for(String aa:delete_list1) {
      System.out.println("삭제 해야할 상품번호  "+aa);
      }
         try {
         ProductDAO dao3=new ProductDAO();
         if(dao3.delete_product1(delete_list1)==true) {
            
            System.out.println("product 테이블에서 삭제성공");
         }else {
            System.out.println("product 테이블에서 삭제실패");
         }
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
         
         QnaDAO qdao=new QnaDAO();
         qdao.delete_product2(delete_list1);// 상품 삭제시 qna에서 삭제
         BasketDAO bdao=new BasketDAO();
         bdao.delete_product3(delete_list1);// 상품 삭제시 basket에서 삭제
         ReviewDAO rdao=new ReviewDAO();
         rdao.delete_product4(delete_list1);// 상품 삭제시 review에서 삭제
   }

}
package com.member.my;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.memberDAO.MemberDAO;

public class MemberIdCheck implements MemberImpl{

   @Override
   public void member(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      
      System.out.println("memberidcheck 왔니?");
      
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      
      
      
   
      String userId = request.getParameter("id");
      System.out.println(userId);
      MemberDAO dao;
      
      
         dao = new MemberDAO();   
         int result= dao.selectIdCheck(userId);
         String result1="";
         if(result==0) {
            result1="이미 사용중인 아이디 입니다";
         }else {
            result1="사용 가능한 아이디 입니다";
         }
         
      
      request.setAttribute("result", result); 
      request.setAttribute("userid", userId);   
      request.setAttribute("result1",result1);
   }
}
package com.product.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import product.productDAO.ProductDAO;

public class ProductEnroll implements ProductImpl{

   @Override
   public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String name4=request.getParameter("nameEnroll"); //상품명
      System.out.println(name4);
      int price4=Integer.parseInt(request.getParameter("priceEnroll")); //가격
      System.out.println(price4);
      int stockS=Integer.parseInt(request.getParameter("stockS"));//재고
      System.out.println(stockS);
      int stockM=Integer.parseInt(request.getParameter("stockM"));//재고
      System.out.println(stockM);
      int stockL=Integer.parseInt(request.getParameter("stockL"));//재고
      System.out.println(stockL);
      String cat1=request.getParameter("cat1"); //카테고리1
      String cat2=request.getParameter("cat2"); //카테고리2
      int count4=0;
      Part filePart=request.getPart("image"); //Part 클래스로 변수 선언할 수 있음
      
      if(cat1.equals("상의")) {
         if(cat2.equals("1")) {
            cat2="반팔";
         }else {
            cat2="긴팔";
         }
      }
      
      if(cat1.equals("하의")) {
         if(cat2.equals("1")) {
            cat2="반바지";
         }else {
            cat2="긴바지";
         }
      }
      
      if(cat1.equals("원피스")) {
         if(cat2.equals("1")) {
            cat2="미니원피스";
         }else {
            cat2="롱원피스";
         }
      }
      
      
      String fileName=filePart.getSubmittedFileName();//파일이름
      if(fileName=="" || fileName==null) {
    	  fileName="NoFile";
      }
      System.out.println("파일이름: "+fileName);
      String realPath=request.getServletContext().getRealPath("/image"); //인자는 root부터의 경로,  //절대경로임
      System.out.println("realPath경로" +realPath);
      
      String filePath=realPath+File.separator+fileName;      
      // 파일경로에서 디렉토리 사이에 /를 넣을지 \를 넣을지 환경에 따라 다르므로 자바에서 제공하는 File.separator사용
      //fis.read(); //1바이트씩 읽어서 int로 반환해준다. 읽을게 없으면 -1반환
      
      
      InputStream fis=filePart.getInputStream();
      FileOutputStream fos=new FileOutputStream(filePath);
      
      
      byte[] buf=new byte[1024];
      int size5=0;
      while((size5=fis.read(buf))!=-1) {
         System.out.println("파일크기: "+size5);
            fos.write(buf, 0, size5);
      }
      fos.flush();
      fos.close();
      fis.close();
      
         try {
            ProductDAO pdao=new ProductDAO();
            int pno=pdao.getProductNum()+1;
            pdao.insert_product(pno,name4, price4  ,cat1, cat2, stockS, stockM, stockL ,count4 ,fileName);
         System.out.println("상품등록 성공");
         } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
         
         }
   }

}
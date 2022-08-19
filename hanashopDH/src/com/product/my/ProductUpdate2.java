package com.product.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import product.productDAO.ProductDAO;

public class ProductUpdate2 implements ProductImpl {

	@Override
	public void product(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String p_no=request.getParameter("no");	
		String p_name=request.getParameter("name");
		String p_price=request.getParameter("price");
		String p_stockS=request.getParameter("stockS");
		String p_stockM=request.getParameter("stockM");
		String p_stockL=request.getParameter("stockL");
		String cat1=request.getParameter("cat1"); //카테고리1
	      String cat2=request.getParameter("cat2"); //카테고리2
		
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
	       Part filePart=request.getPart("image"); //Part 클래스로 변수 선언할 수 있음
			System.out.println("수정할 p_no는 "+p_no+"수정할 p_name는 "+p_name+"수정할 p_rice는 "+p_price
					+" 수정할 카테고리1"+cat1+" 수정할 카테고리2"+cat2+" 수정할 p_stockS는 "+p_stockS+"수정할 p_stockM는 "+p_stockM+"수정할 p_stockL는 "+p_stockL);
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
	      System.out.println("수정할 p_no는 "+p_no+"수정할 p_name는 "+p_name+"수정할 p_rice는 "+p_price
					+" 수정할 카테고리1"+cat1+" 수정할 카테고리2"+cat2+" 수정할 p_stockS는 "+p_stockS+"수정할 p_stockM는 "+p_stockM+"수정할 p_stockL는 "+p_stockL+" 파일이름은 "+fileName );
			
		
		ProductDAO pdao123=new ProductDAO();
		pdao123.updateGo(p_no, p_name, p_price, p_stockS, p_stockM, p_stockL, cat1, cat2, fileName);
		
	}

}

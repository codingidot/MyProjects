package com.review.my;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import review.reviewDAO.ReviewDAO;


public class ReviewUpdate implements ReviewImpl {
	
	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		System.out.println("ReviewUpdate 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		int star = Integer.parseInt(request.getParameter("star"));
		String content = request.getParameter("content");
		content = content.replaceAll("\r\n", "<br>");
		System.out.println("Replace한 content : " + content);
		Part filePart = request.getPart("image");
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		
		String fileName = filePart.getSubmittedFileName();// 파일이름
		System.out.println("파일이름 제대로 왔는지 확인" + fileName);
		
		if(fileName != "") {
		String realPath = request.getServletContext().getRealPath("/image");
		System.out.println("test line 38");
		
		String filePath = realPath+File.separator + fileName;
		// 파일경로사에서 디렉토리 사이에 /를 넣을지 \를 넣을지 환경에 따라 다르로 file.separator
		System.out.println("test line 42");
		
		InputStream fis = filePart.getInputStream();
		FileOutputStream fos = new FileOutputStream(filePath);
		System.out.println("test line 46");
		
		byte[] buf = new byte[1024];
		
		int size = 0;
		while((size = fis.read(buf))!=-1) {
			fos.write(buf,0,size);
		}
		
		fos.flush();
		fos.close();
		fis.close();
		}

		
//		int check = 0;
		
		ReviewDAO rdao = null;
		rdao = new ReviewDAO();
		
		int writecheck = rdao.reviewUpdate(title,star, content, fileName, rno);
		
		request.setAttribute("writecheck", writecheck);
	}
	

}

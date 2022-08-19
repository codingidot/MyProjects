package com.review.my;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import review.reviewDAO.ReviewDAO;


public class ReviewWrite implements ReviewImpl {
	
	@Override
	public void review(HttpServletRequest request, HttpServletResponse response) throws Exception { 
		System.out.println("ReviewWrite 서비스 도착");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		System.out.println("pno : " + pno);
		
		int star = Integer.parseInt(request.getParameter("star"));
		System.out.println("star : " + star);
		
		String content = request.getParameter("content");
		System.out.println("content : " + content);
		content = content.replaceAll("\r\n", "<br>");
		System.out.println("replace한 content : " + content);
		
		Part filePart = request.getPart("image");
		
		
		String fileName = filePart.getSubmittedFileName();// 파일이름
		System.out.println("파일이름 제대로 왔는지 확인" + fileName);
		
		if(fileName != "") {
		String realPath = request.getServletContext().getRealPath("/image");
		
		String filePath = realPath+File.separator + fileName;
		// 파일경로사에서 디렉토리 사이에 /를 넣을지 \를 넣을지 환경에 따라 다르로 file.separator
		
		InputStream fis = filePart.getInputStream();
		FileOutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		
		int size = 0;
		while((size = fis.read(buf))!=-1) {
			fos.write(buf,0,size);
		}
		
		fos.flush();
		fos.close();
		fis.close();
		}

		System.out.println(title + "," + id + "," + pno + "," +star + "," + content + "," + fileName);
		
		
		ReviewDAO rdao = new ReviewDAO();
		
		int writecheck = rdao.reviewWrite(title, id, pno,star, content, fileName);
		
		request.setAttribute("writecheck", writecheck);
	}
	

}

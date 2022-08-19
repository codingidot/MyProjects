package com.qna.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import qna.qnaDAO.QnaDAO;

public class QnaWrite2 implements QnaImpl{


	@Override
	public void qna(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		System.out.println("id :" +id);
		
		String title = request.getParameter("title");
		System.out.println("title :" +title);
		
		String content = request.getParameter("content");
		System.out.println("content :" +content);

		String category = request.getParameter("category");
		System.out.println("category : " + category);
		
		
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
		
		
		
		QnaDAO qdao = null;
		qdao = new QnaDAO();
		
		int no = qdao.get_no();
		
		
		int writecheck = qdao.qnaWrite2(no, id,title,content,fileName,category);
		System.out.println("writecheck : " + writecheck);
		
		request.setAttribute("writecheck", writecheck);
		
		
		
		
	}
	
	

}

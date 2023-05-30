package com.exercise.go;

import java.io.UnsupportedEncodingException;

public class FindEncoding {

	/* 인코딩 테스트
	public static void main(String[] args) throws UnsupportedEncodingException {
		String originalStr = "테스트";
		System.out.println(originalStr);

		String[] charSet = {"utf-8", "euc-kr", "MS949", "iso-8859-1", "x-windows-949"};
		for(int i = 0; i<charSet.length; i++){
			for(int j = 0; j<charSet.length; j++){
				try{ 
					System.out.println("[" + charSet[i] + "," + charSet[j] + "]" + new String(originalStr.getBytes(charSet[i]), charSet[j]));
				} catch (UnsupportedEncodingException e){
					e.printStackTrace();
				}
			}
		}
	}
*/
}

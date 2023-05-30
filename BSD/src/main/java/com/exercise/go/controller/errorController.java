package com.exercise.go.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController implements ErrorController {
	
	@GetMapping("/error")
	public String redirectRoot() {
		// SPA�� url�� ���ٽ� 404������ ����. ������ ���� ����� �����̵Ǿ� tempates ���� �ִ� � ȭ������ return �Ұ��� 
		// src/main/resources�� �ִ� templates ������ index.html�� �־����
		return "index.html";
	}

}

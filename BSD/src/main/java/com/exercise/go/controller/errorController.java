package com.exercise.go.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errorController implements ErrorController {
	
	@GetMapping("/error")
	public String redirectRoot() {
		// SPA는 url로 접근시 404에러가 난다. 에러가 나면 여기로 매핑이되어 tempates 폴에 있는 어떤 화면으로 return 할건지 
		// src/main/resources에 있는 templates 폴더에 index.html을 넣어줘라
		return "index.html";
	}

}

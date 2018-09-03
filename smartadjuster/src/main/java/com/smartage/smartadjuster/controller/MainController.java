package com.smartage.smartadjuster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/greet")
	public String greetUser() {
		return "hello there";
	}
	
	
	
}

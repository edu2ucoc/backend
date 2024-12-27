package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatbotController {
	@GetMapping("/chatbot")
	public String home() {
		return "chatbot";
	}
}

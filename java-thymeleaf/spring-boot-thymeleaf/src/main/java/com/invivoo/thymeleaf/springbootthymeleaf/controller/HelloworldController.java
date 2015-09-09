package com.invivoo.thymeleaf.springbootthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloworldController {

	@RequestMapping
	public String getHelloWorldPage(Model model) {
		model.addAttribute("name", "Daniel Lavoie");
		
		return "hello-world";
	}
}

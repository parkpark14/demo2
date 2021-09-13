package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping({"/test"})
	public @ResponseBody String test() {
		return "Test";
	}
	
	@GetMapping({"/index","","/"})
	public String index() {
		return "index";
	}

}

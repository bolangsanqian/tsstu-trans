package com.tsstu.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/layout/left")
	public String left() {
		return "layout/left";
	}
	
	@RequestMapping("/layout/main")
	public String main() {
		return "layout/main";
	}
	
}

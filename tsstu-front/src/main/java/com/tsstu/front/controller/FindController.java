package com.tsstu.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/find")
public class FindController extends BaseController {
	
	@RequestMapping(value={"", "index"}, method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("find/find_index");
		mv.addObject("currentMenu", "find");
		return mv;
	}
}

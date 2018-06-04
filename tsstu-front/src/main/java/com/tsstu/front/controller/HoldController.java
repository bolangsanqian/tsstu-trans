package com.tsstu.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hold")
public class HoldController extends BaseController {
	
	@RequestMapping(value={"", "/index"}, method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("order/cycle_order_index");
		mv.addObject("currentMenu", "hold");
		return mv;
	}
	
	@RequestMapping(value={"", "/detail"}, method=RequestMethod.GET)
	public ModelAndView detail(Integer id){
		ModelAndView mv = new ModelAndView("public/detail");
		return mv;
	}
}

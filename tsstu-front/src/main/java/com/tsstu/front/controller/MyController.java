package com.tsstu.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MyController extends BaseController {
	
	@RequestMapping(value={"", "index"}, method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("my/my_index");
		mv.addObject("currentMenu", "my");
		return mv;
	}
	
	@RequestMapping(value="/index2", method=RequestMethod.GET)
	public String index2(){
		return "my/my_index2";
	}
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public String info(){
		return "my/my_info";
	}
	
	@RequestMapping(value="/contract_bank", method=RequestMethod.GET)
	public String toContractBank(){
		return "my/my_contract_bank";
	}
	
	@RequestMapping(value="/edit_password", method=RequestMethod.GET)
	public String toEditPassword(){
		return "my/my_edit_password";
	}
	
	@RequestMapping(value="/find_password", method=RequestMethod.GET)
	public String toFindPassword(){
		return "my/my_find_password";
	}
	
	@RequestMapping(value="/fund_flow", method=RequestMethod.GET)
	public String tofundFlow(){
		return "my/my_fund_flow";
	}
}

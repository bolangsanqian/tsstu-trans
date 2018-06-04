package com.tsstu.front.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants;
import com.tsstu.front.model.CycleProduct;
import com.tsstu.front.service.CycleProductService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private CycleProductService cycleProductService;
	
	@RequestMapping(value={"","/index"}, method=RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("currentMenu", "index");
		
		// 产品列表
		List<CycleProduct> list = cycleProductService.getList("status", Constants.STATUS_ENABLE);
		mv.addObject("list", list);
		
		return mv;
	}
}

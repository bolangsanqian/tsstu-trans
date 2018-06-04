package com.tsstu.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.tsstu.console.core.datasource.DBContextHolder;
import com.tsstu.console.core.datasource.Source;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.model.Demo;
import com.tsstu.console.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

	@Autowired
	public DemoService demoService;
	
	@RequestMapping("/index")
	public String index() {
		DBContextHolder.switchDataSource(Source.master);
		List<Demo> list = demoService.getList();
		logger.info("list: {}", JSON.toJSONString(list));
		DBContextHolder.switchDataSource(Source.slave);
		List<Demo> list2 = demoService.getList(new Demo());
		logger.info("list2: {}", JSON.toJSONString(list2));
		return "demo/demo_index";
	}
	
	@RequestMapping("/page")
	public String page(Pager<Demo> pager) {
		super.initPageData(pager);
		List<Demo> list = demoService.getList(pager);
		logger.info("list: {}", JSON.toJSONString(list));
		return "demo/demo_index";
	}
	
	@RequestMapping("/export")
	public String export(Pager<Demo> pager) {
		super.initPageData(pager);
		List<Demo> list = demoService.getExportList(pager);
		logger.info("export_list: {}", JSON.toJSONString(list));
		return "demo/demo_index";
	}
	
	@ResponseBody
	@RequestMapping("/ajax")
	public Result ajax() {
		return success();
	}
	
	@RequestMapping("/table")
	public String table() {
		return "demo/demo_table";
	}
	
	@RequestMapping("/table_tag")
	public ModelAndView table_tag(Pager<Demo> pager) {
		ModelAndView mv = new ModelAndView("demo/demo_table_tag");
		super.initPageData(pager);
		demoService.getList(pager, new Demo());
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	@RequestMapping("/form")
	public String form() {
		return "demo/demo_form";
	}
	
	@RequestMapping("/form_win")
	public String form_win() {
		return "demo/demo_form_win";
	}
	
	@RequestMapping("/form_win2")
	public String form_win2() {
		return "demo/demo_form_win2";
	}
	
	@RequestMapping("/form_win3")
	public String form_win3() {
		return "demo/demo_form_win3";
	}
	
	@RequestMapping("/date")
	public String date() {
		return "demo/demo_date";
	}
}

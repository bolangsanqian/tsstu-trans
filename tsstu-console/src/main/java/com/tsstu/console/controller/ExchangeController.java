package com.tsstu.console.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.console.core.entity.Result;
import com.tsstu.console.model.Member;
import com.tsstu.console.service.MemberService;

@Controller
@RequestMapping("/exchange")
public class ExchangeController extends BaseController {

	@Autowired
	public MemberService memberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("exchange/exchange_index");
		Member model = memberService.getExchange();
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(Member member) {
		int effectRow = memberService.update(member);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
}

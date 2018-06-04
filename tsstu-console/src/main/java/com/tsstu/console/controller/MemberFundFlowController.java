package com.tsstu.console.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.MemberFundFlow;
import com.tsstu.console.service.MemberFundFlowService;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/member_fund_flow")
public class MemberFundFlowController extends BaseController {

	@Autowired
	public MemberFundFlowService memberFundFlowService;
	
	@Autowired
	public MemberService memberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<MemberFundFlow> pager) {
		ModelAndView mv = new ModelAndView("member_fund_flow/member_fund_flow_index");
		super.initPageData(pager);
		
		//数据权限控制
		super.dataPermission(pager, mv);
		
		memberFundFlowService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("member_fund_flow/member_fund_flow_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(MemberFundFlow model) {
		int effectRow = memberFundFlowService.add(model);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
	
	/**
	 * 修改页面
	 * @return
	 */
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView toEdit(Integer id) {
		ModelAndView mv = new ModelAndView("member_fund_flow/member_fund_flow_edit");
		MemberFundFlow model = memberFundFlowService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(MemberFundFlow member_fund_flow) {
		int effectRow = memberFundFlowService.update(member_fund_flow);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
	
	/**
	 * 删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="del")
	public Result del(Integer id) {
		int effectRow = memberFundFlowService.delete(id);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="delBatch")
	public Result del_batch(Integer[] ids) {
		int effectRow = memberFundFlowService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<MemberFundFlow> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<MemberFundFlow> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("member_id", "会员编号");
		headInfoList.put("flow_no", "流水号");
		headInfoList.put("trans_no", "交易单号");
		headInfoList.put("amount", "金额");
		headInfoList.put("change_type", "类型");
		headInfoList.put("frozen_money", "占用资金");
		headInfoList.put("trans_time", "交易时间");

		//数据列表
		List<Map<String, Object>> dataList = MapUtils.bean2MapList(list, null);
		
		//数据字典
		Map<String, Map<String, Object>> dictMaps = new HashMap<String, Map<String, Object>>();
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("headInfoList", headInfoList);
		dataMap.put("dataList", dataList);
		dataMap.put("dictMaps", dictMaps);
		POIExcelView erv = new POIExcelView();				
		ModelAndView mv = new ModelAndView(erv, dataMap);
		mv.addObject("fileName", "会员资金流水");
		return mv;
    }
	
}

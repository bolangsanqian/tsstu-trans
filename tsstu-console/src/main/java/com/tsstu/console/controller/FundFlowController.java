package com.tsstu.console.controller;

import java.util.ArrayList;
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

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.FundFlow;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.FundFlowService;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/fund_flow")
public class FundFlowController extends BaseController {

	@Autowired
	public FundFlowService fundFlowService;
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	public AgentMemberService agentMemberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<FundFlow> pager) {
		ModelAndView mv = new ModelAndView("fund_flow/fund_flow_index");
		super.initPageData(pager);

		//数据权限控制
		super.dataPermission(pager, mv);
		
		fundFlowService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 详情页面
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		FundFlow model = fundFlowService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("资金流水id", model.getId()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("昵称", model.getNick_name()));
		detailList.add(new Detail("流水号", model.getFlow_no()));
		detailList.add(new Detail("交易单号", model.getTrans_no()));
		detailList.add(new Detail("金额", model.getAmount()));
		detailList.add(new Detail("交易类型", model.getChange_type(), DictConstants.D_CUST_FUND_FLOW_TYPE));
		detailList.add(new Detail("可用余额", model.getBalance()));
		detailList.add(new Detail("占用资金", model.getFrozen_money()));
		detailList.add(new Detail("交易时间", model.getTrans_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("备注", model.getRemark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("fund_flow/fund_flow_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(FundFlow model) {
		int effectRow = fundFlowService.add(model);
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
		ModelAndView mv = new ModelAndView("fund_flow/fund_flow_edit");
		FundFlow model = fundFlowService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(FundFlow fund_flow) {
		int effectRow = fundFlowService.update(fund_flow);
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
		int effectRow = fundFlowService.delete(id);
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
		int effectRow = fundFlowService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<FundFlow> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<FundFlow> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("customer_id", "顾客id");
		headInfoList.put("flow_no", "流水号");
		headInfoList.put("trans_no", "交易单号");
		headInfoList.put("amount", "金额");
		headInfoList.put("change_type", "交易类型");
		headInfoList.put("balance", "可用余额");
		headInfoList.put("frozen_money", "占用资金");
		headInfoList.put("trans_time", "交易时间");
		headInfoList.put("member_id", "所属会员ID");

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
		mv.addObject("fileName", "资金流水");
		return mv;
    }
	
}

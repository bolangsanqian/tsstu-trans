package com.tsstu.console.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.tsstu.console.model.FundChange;
import com.tsstu.console.service.FundChangeService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/fund_change")
public class FundChangeController extends BaseController {

	@Autowired
	public FundChangeService fundChangeService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<FundChange> pager) {
		ModelAndView mv = new ModelAndView("fund_change/fund_change_index");
		super.initPageData(pager);
		fundChangeService.getList(pager);
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
		FundChange model = fundChangeService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("加减币调整id", model.getId()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("客户昵称", model.getNick_name()));
		detailList.add(new Detail("流水号", model.getFlow_no()));
		detailList.add(new Detail("变动类型", model.getChange_type(), DictConstants.D_FUND_CHANGE_TYPE));
		detailList.add(new Detail("变动金额", model.getAmount()));
		detailList.add(new Detail("申请人", model.getApply_id()));
		detailList.add(new Detail("申请原因", model.getApply_reason()));
		detailList.add(new Detail("申请时间", model.getApply_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审核人", model.getReview_id()));
		detailList.add(new Detail("审核时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_FUND_CHANGE_STATUS));
		detailList.add(new Detail("审核备注", model.getReview_remark()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属微会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("fund_change/fund_change_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(FundChange model) {
		if (null == model.getMobile() || "".equals(model.getMobile())) {
			return error("请输入手机号码！");
    	}
		model.setApply_id(super.getLoginUserId());
		model.setApply_time(new Date());
		int effectRow = fundChangeService.add(model);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
	
	/**
	 * 审批页面
	 * @return
	 */
	@RequestMapping(value="/review", method=RequestMethod.GET)
	public ModelAndView toReview(String ids) {
		ModelAndView mv = new ModelAndView("fund_change/fund_change_review");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 审批通过
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/review_pass", method=RequestMethod.POST)
	public Result reviewPass(String ids, String review_remark) {
		boolean bo = fundChangeService.reviewPass(ids, super.getLoginUserId(), review_remark);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 审批驳回
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/review_reject", method=RequestMethod.POST)
	public Result reviewReject(String ids, String review_remark) {
		boolean bo = fundChangeService.reviewReject(ids, super.getLoginUserId(), review_remark);
		if (bo) {
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
		ModelAndView mv = new ModelAndView("fund_change/fund_change_edit");
		FundChange model = fundChangeService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(FundChange fund_change) {
		int effectRow = fundChangeService.update(fund_change);
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
		int effectRow = fundChangeService.delete(id);
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
		int effectRow = fundChangeService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<FundChange> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<FundChange> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("customer_id", "客户表_客户id");
		headInfoList.put("flow_no", "加减币流水号");
		headInfoList.put("change_type", "变动类型");
		headInfoList.put("amount", "变动金额");
		headInfoList.put("apply_id", "申请人");
		headInfoList.put("apply_reason", "申请原因");
		headInfoList.put("apply_time", "申请时间");
		headInfoList.put("review_id", "审核人");
		headInfoList.put("review_time", "审核时间");
		headInfoList.put("status", "状态");
		headInfoList.put("review_remark", "审核备注");
		headInfoList.put("agent_member_id", "代理会员");

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
		mv.addObject("fileName", "加减币管理");
		return mv;
    }
	
}

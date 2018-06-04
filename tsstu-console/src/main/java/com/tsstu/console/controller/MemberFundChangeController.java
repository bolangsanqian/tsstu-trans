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
import com.tsstu.console.model.MemberFundChange;
import com.tsstu.console.service.MemberFundChangeService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/member_fund_change")
public class MemberFundChangeController extends BaseController {

	@Autowired
	public MemberFundChangeService memberFundChangeService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<MemberFundChange> pager) {
		ModelAndView mv = new ModelAndView("member_fund_change/member_fund_change_index");
		super.initPageData(pager);
		memberFundChangeService.getList(pager);
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
		MemberFundChange model = memberFundChangeService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("会员资金调整id", model.getId()));
		detailList.add(new Detail("加减币流水号", model.getFlow_no()));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("变动类型", model.getChange_type(), DictConstants.D_MEMBER_FUND_CHANGE_TYPE));
		detailList.add(new Detail("变动金额", model.getAmount()));
		detailList.add(new Detail("申请人", model.getApply_id()));
		detailList.add(new Detail("审批原因", model.getApply_reason()));
		detailList.add(new Detail("申请时间", model.getApply_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审核人", model.getReview_id()));
		detailList.add(new Detail("审核时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_FUND_CHANGE_STATUS));
		detailList.add(new Detail("审核备注", model.getReview_remark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("member_fund_change/member_fund_change_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(MemberFundChange model) {
		model.setApply_id(super.getLoginUserId());
		model.setApply_time(new Date());
		int effectRow = memberFundChangeService.add(model);
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
		ModelAndView mv = new ModelAndView("member_fund_change/member_fund_change_edit");
		MemberFundChange model = memberFundChangeService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(MemberFundChange member_fund_change) {
		int effectRow = memberFundChangeService.update(member_fund_change);
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
		int effectRow = memberFundChangeService.delete(id);
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
		int effectRow = memberFundChangeService.deleteBatch(ids);
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
		ModelAndView mv = new ModelAndView("member_fund_change/member_fund_change_review");
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
		boolean bo = memberFundChangeService.reviewPass(ids, super.getLoginUserId(), review_remark);
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
		boolean bo = memberFundChangeService.reviewReject(ids, super.getLoginUserId(), review_remark);
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<MemberFundChange> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<MemberFundChange> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "加减币流水号");
		headInfoList.put("member_id", "所属会员");
		headInfoList.put("change_type", "变动类型");
		headInfoList.put("amount", "变动金额");
		headInfoList.put("apply_id", "申请人");
		headInfoList.put("apply_reason", "审批原因");
		headInfoList.put("apply_time", "申请时间");
		headInfoList.put("status", "状态");

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
		mv.addObject("fileName", "加减币管理(会员)");
		return mv;
    }
	
}

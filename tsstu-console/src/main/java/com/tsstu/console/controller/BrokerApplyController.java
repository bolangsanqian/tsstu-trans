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
import com.tsstu.common.constants.Constants.REVIEW_STATUS;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.BrokerApply;
import com.tsstu.console.service.BrokerApplyService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/broker_apply")
public class BrokerApplyController extends BaseController {

	@Autowired
	public BrokerApplyService brokerApplyService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<BrokerApply> pager) {
		ModelAndView mv = new ModelAndView("broker_apply/broker_apply_index");
		super.initPageData(pager);
		
		//数据权限控制
		super.dataPermission(pager, mv);
				
		brokerApplyService.getList(pager);
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
		BrokerApply model = brokerApplyService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("经纪人申请id", model.getId()));
		detailList.add(new Detail("客户id", model.getCustomer_id()));
		detailList.add(new Detail("用户名", model.getUsername()));
		detailList.add(new Detail("密码", model.getPassword()));
		detailList.add(new Detail("申请时间", model.getApply_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_COMMON_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("审批人", model.getReview_by_name()));
		detailList.add(new Detail("审批时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审批状态 ", model.getReview_status(), DictConstants.D_REVIEW_STATUS));
		detailList.add(new Detail("审批备注", model.getReview_remark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("broker_apply/broker_apply_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(BrokerApply model) {
		int effectRow = brokerApplyService.add(model);
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
		ModelAndView mv = new ModelAndView("broker_apply/broker_apply_edit");
		BrokerApply model = brokerApplyService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(BrokerApply model) {
		int effectRow = brokerApplyService.update(model);
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
		BrokerApply apply = brokerApplyService.getOne(id);
		if (apply.getReview_status().intValue() != REVIEW_STATUS.REVIEW_REJECT) {
			return error("非驳回状态的申请，删除失败");
		}
		int effectRow = brokerApplyService.delete(id);
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
		for(Integer id : ids) {
			BrokerApply apply = brokerApplyService.getOne(id);
			if (apply.getReview_status().intValue() != REVIEW_STATUS.REVIEW_REJECT) {
				return error("有非驳回状态的申请，删除失败");
			}
		}
		int effectRow = brokerApplyService.deleteBatch(ids);
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
		ModelAndView mv = new ModelAndView("broker_apply/broker_apply_review");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 审批
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/review", method=RequestMethod.POST)
	public Result review(String ids, Integer review_status, String review_remark) {
		if (review_status != REVIEW_STATUS.REVIEW_PASS && review_status != REVIEW_STATUS.REVIEW_REJECT) { //非法状态
			return error("审批失败！");
		}
		boolean bo = false;
		if (review_status == REVIEW_STATUS.REVIEW_PASS) {
			bo = brokerApplyService.reviewPass(ids, super.getLoginUserId(), review_remark);
		} else {
			bo = brokerApplyService.reviewReject(ids, super.getLoginUserId(), review_remark);
		}
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<BrokerApply> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<BrokerApply> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("username", "用户名");
		headInfoList.put("apply_time", "申请时间");
		headInfoList.put("operation_member_id", "运营中心");
		headInfoList.put("member_id", "所属会员");
		headInfoList.put("agent_member_id", "代理会员");
		headInfoList.put("review_by", "审核人");
		headInfoList.put("review_time", "审核时间");

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
		mv.addObject("fileName", "经纪人申请");
		return mv;
    }
	
}

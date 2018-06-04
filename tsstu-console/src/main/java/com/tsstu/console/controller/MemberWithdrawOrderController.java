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
import com.tsstu.console.model.MemberWithdrawOrder;
import com.tsstu.console.service.MemberWithdrawOrderService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/member_withdraw_order")
public class MemberWithdrawOrderController extends BaseController {

	@Autowired
	public MemberWithdrawOrderService memberWithdrawOrderService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<MemberWithdrawOrder> pager) {
		ModelAndView mv = new ModelAndView("member_withdraw_order/member_withdraw_order_index");
		super.initPageData(pager);
		memberWithdrawOrderService.getList(pager);
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
		MemberWithdrawOrder model = memberWithdrawOrderService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("提现订单id", model.getId()));
		detailList.add(new Detail("提现流水号", model.getFlow_no()));
		detailList.add(new Detail("会员编号", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("提现金额", model.getAmount()));
		detailList.add(new Detail("手续费", model.getFee()));
		detailList.add(new Detail("提现银行", model.getBank_no(), DictConstants.DICT_CONTRACT_BANK));
		detailList.add(new Detail("省份", model.getProvince_code()));
		detailList.add(new Detail("城市", model.getCity_code()));
		detailList.add(new Detail("银行支行", model.getBank_branch()));
		detailList.add(new Detail("银行卡号", model.getBankcard_no()));
		detailList.add(new Detail("姓名", model.getReal_name()));
		detailList.add(new Detail("身份证", model.getIdentity()));
		detailList.add(new Detail("申请时间", model.getApply_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("提现状态", model.getStatus(), DictConstants.D_WITHDRAW_STATUS));
		detailList.add(new Detail("审批标识", model.getReview_sign()));
		detailList.add(new Detail("审批人", model.getReview_id()));
		detailList.add(new Detail("审批时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审批备注", model.getReview_remark()));
		detailList.add(new Detail("审批状态", model.getReview_status(), DictConstants.D_WITHDRAW_REVIEW_STATUS));
		detailList.add(new Detail("付款方式", model.getPay_method(), DictConstants.D_MEMBER_WITHDRAW_PAY_METHOD));
		detailList.add(new Detail("第三方单号", model.getThird_flow_no()));
		detailList.add(new Detail("付款备注", model.getPay_remark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("member_withdraw_order/member_withdraw_order_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(MemberWithdrawOrder model) {
		int effectRow = memberWithdrawOrderService.add(model);
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
		ModelAndView mv = new ModelAndView("member_withdraw_order/member_withdraw_order_edit");
		MemberWithdrawOrder model = memberWithdrawOrderService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(MemberWithdrawOrder member_withdraw_order) {
		int effectRow = memberWithdrawOrderService.update(member_withdraw_order);
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
		int effectRow = memberWithdrawOrderService.delete(id);
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
		int effectRow = memberWithdrawOrderService.deleteBatch(ids);
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
		ModelAndView mv = new ModelAndView("member_withdraw_order/member_withdraw_order_review");
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
			return error("订单非待审批状态！");
		}
		boolean bo = false;
		if (review_status == REVIEW_STATUS.REVIEW_PASS) {
			bo = memberWithdrawOrderService.reviewPass(ids, super.getLoginUserId(), review_remark);
		} else {
			bo = memberWithdrawOrderService.reviewReject(ids, super.getLoginUserId(), review_remark);
		}
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 付款页面
	 * @return
	 */
	@RequestMapping(value="/pay", method=RequestMethod.GET)
	public ModelAndView toPay(String ids) {
		ModelAndView mv = new ModelAndView("member_withdraw_order/member_withdraw_order_pay");
		mv.addObject("ids", ids);
		return mv;
	}
	
	/**
	 * 付款
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/pay", method=RequestMethod.POST)
	public Result pay(String ids, Integer status, String pay_remark, Integer pay_method) {
		if (status != REVIEW_STATUS.REVIEW_PASS && status != REVIEW_STATUS.REVIEW_REJECT) { //非法状态
			return error("订单非待审批状态！");
		}
		boolean bo = false;
		if (status == REVIEW_STATUS.REVIEW_PASS) {
			bo = memberWithdrawOrderService.payPass(ids, super.getLoginUserId(), pay_remark, pay_method);
		} else {
			bo = memberWithdrawOrderService.payReject(ids, super.getLoginUserId(), pay_remark);
		}
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<MemberWithdrawOrder> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<MemberWithdrawOrder> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "提现流水号");
		headInfoList.put("member_id", "会员编号");
		headInfoList.put("amount", "提现金额");
		headInfoList.put("fee", "手续费");
		headInfoList.put("bankcard_no", "银行卡号");
		headInfoList.put("real_name", "姓名");
		headInfoList.put("identity", "身份证");
		headInfoList.put("apply_time", "申请时间");
		headInfoList.put("status", "提现状态");
		headInfoList.put("review_sign", "审批标识（0：非审批单，1：审批单）");
		headInfoList.put("review_status", "审批状态");
		headInfoList.put("pay_method", "付款方式");
		headInfoList.put("pay_remark", "付款备注");

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
		mv.addObject("fileName", "会员提现订单");
		return mv;
    }
	
}

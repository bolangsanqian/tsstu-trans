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
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.WithdrawOrder;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.WithdrawOrderService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/withdraw_order")
public class WithdrawOrderController extends BaseController {

	@Autowired
	public WithdrawOrderService withdrawOrderService;
	
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
	public ModelAndView index(Pager<WithdrawOrder> pager) {
		ModelAndView mv = new ModelAndView("withdraw_order/withdraw_order_index");
		super.initPageData(pager);
		withdrawOrderService.getList(pager);
		mv.addObject("pageInfo", pager);
		
		//运营中心
		List<Member> operationList = memberService.getEnableOperationList();
		mv.addObject("operationList", operationList);
		//会员列表
		List<Member>  memberList = memberService.getEnableCommonMemberList();
		mv.addObject("memberList", memberList);
		//代理会员列表
		List<AgentMember>  agentMemberList = agentMemberService.getEnableAgentMemberList();
		mv.addObject("agentMemberList", agentMemberList);
		return mv;
	}
	
	/**
	 * 详情页面
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		WithdrawOrder model = withdrawOrderService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("提现订单id", model.getId()));
		detailList.add(new Detail("提现流水号", model.getFlow_no()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("昵称", model.getNick_name()));
		detailList.add(new Detail("提现金额", model.getAmount()));
		detailList.add(new Detail("手续费", model.getFee()));
		detailList.add(new Detail("申请时间", model.getApply_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("提现状态", model.getStatus(), DictConstants.D_WITHDRAW_STATUS));
		detailList.add(new Detail("审批标识", model.getReview_sign()));
		detailList.add(new Detail("审批人", model.getReview_id()));
		detailList.add(new Detail("审批时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审批备注", model.getReview_remark()));
		detailList.add(new Detail("审批状态", model.getReview_status(), DictConstants.D_WITHDRAW_REVIEW_STATUS));
		detailList.add(new Detail("审批自动到账", model.getAuto_arrival()));
		detailList.add(new Detail("提现方式", model.getWithdraw_method(), DictConstants.D_WITHDRAW_METHOD));
		detailList.add(new Detail("第三方单号", model.getThird_flow_no()));
		detailList.add(new Detail("付款人", model.getPay_id()));
		detailList.add(new Detail("付款时间", model.getPay_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("付款备注", model.getPay_remark()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属微会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("提现openid", model.getWithdraw_openid()));
		detailList.add(new Detail("公众号应用ID", model.getWx_appid()));
		detailList.add(new Detail("微信支付商户号", model.getWxpay_mch_id()));
		detailList.add(new Detail("微信支付密钥", model.getWxpay_api_key()));
		detailList.add(new Detail("微信支付证书路径", model.getWxpay_cert_path()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("withdraw_order/withdraw_order_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(WithdrawOrder model) {
		int effectRow = withdrawOrderService.add(model);
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
		ModelAndView mv = new ModelAndView("withdraw_order/withdraw_order_edit");
		WithdrawOrder model = withdrawOrderService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(WithdrawOrder withdraw_order) {
		int effectRow = withdrawOrderService.update(withdraw_order);
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
		int effectRow = withdrawOrderService.delete(id);
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
		int effectRow = withdrawOrderService.deleteBatch(ids);
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
		ModelAndView mv = new ModelAndView("withdraw_order/withdraw_order_review");
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
			bo = withdrawOrderService.reviewPass(ids, super.getLoginUserId(), review_remark);
		} else {
			bo = withdrawOrderService.reviewReject(ids, super.getLoginUserId(), review_remark);
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
		ModelAndView mv = new ModelAndView("withdraw_order/withdraw_order_pay");
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
			bo = withdrawOrderService.payPass(ids, super.getLoginUserId(), pay_remark, pay_method);
		} else {
			bo = withdrawOrderService.payReject(ids, super.getLoginUserId(), pay_remark);
		}
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<WithdrawOrder> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<WithdrawOrder> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "提现流水号");
		headInfoList.put("amount", "提现金额");
		headInfoList.put("fee", "手续费");
		headInfoList.put("apply_time", "申请时间");
		headInfoList.put("review_sign", "审批标识（0：不需要审批，1：需要审批）");
		headInfoList.put("review_id", "审批人");
		headInfoList.put("review_time", "审批时间");
		headInfoList.put("review_remark", "审批备注");
		headInfoList.put("review_status", "审批状态（0：未审批，1：已审批，-1：已驳回）");
		headInfoList.put("withdraw_method", "提现方式(0：微信提现，1：网银提现，2：融宝提现)");
		headInfoList.put("third_flow_no", "第三方提现订单号");
		headInfoList.put("fail_reason", "失败原因");
		headInfoList.put("pay_remark", "付款审批备注");
		headInfoList.put("withdraw_openid", "提现openid");
		headInfoList.put("wx_appid", "公众号应用ID");
		headInfoList.put("wxpay_mch_id", "微信支付商户号");
		headInfoList.put("wxpay_api_key", "微信支付密钥");
		headInfoList.put("wxpay_cert_path", "微信支付证书路径");
		headInfoList.put("remark", "备注");

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
		mv.addObject("fileName", "提现订单");
		return mv;
    }
	
}

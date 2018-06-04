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
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.RechargeOrder;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.RechargeOrderService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/recharge_order")
public class RechargeOrderController extends BaseController {

	@Autowired
	public RechargeOrderService rechargeOrderService;
	
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
	public ModelAndView index(Pager<RechargeOrder> pager) {
		ModelAndView mv = new ModelAndView("recharge_order/recharge_order_index");
		super.initPageData(pager);
		rechargeOrderService.getList(pager);
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
		RechargeOrder model = rechargeOrderService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("充值订单id", model.getId()));
		detailList.add(new Detail("充值流水号", model.getFlow_no()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("昵称", model.getNick_name()));
		detailList.add(new Detail("充值金额", model.getAmount()));
		detailList.add(new Detail("手续费", model.getFee()));
		detailList.add(new Detail("支付方式", model.getPayment_method(), DictConstants.D_CUST_PAYMENT_METHOD));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_RECHARGE_STATUS));
		detailList.add(new Detail("充值时间", model.getRecharge_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("支付单号", model.getPay_sn()));
		detailList.add(new Detail("支付回调时间", model.getCallback_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("备注", model.getRemark()));
		detailList.add(new Detail("支付IP地址", model.getPay_ip()));
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
		ModelAndView mv = new ModelAndView("recharge_order/recharge_order_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(RechargeOrder model) {
		int effectRow = rechargeOrderService.add(model);
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
		ModelAndView mv = new ModelAndView("recharge_order/recharge_order_edit");
		RechargeOrder model = rechargeOrderService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(RechargeOrder recharge_order) {
		int effectRow = rechargeOrderService.update(recharge_order);
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
		int effectRow = rechargeOrderService.delete(id);
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
		int effectRow = rechargeOrderService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<RechargeOrder> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<RechargeOrder> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "充值流水号");
		headInfoList.put("amount", "充值金额");
		headInfoList.put("fee", "手续费");
		headInfoList.put("payment_method", "支付方式");
		headInfoList.put("status", "状态");
		headInfoList.put("recharge_time", "充值时间");
		headInfoList.put("remark", "备注");
		headInfoList.put("pay_ip", "支付IP地址");
		headInfoList.put("member_id", "所属微会员");
		headInfoList.put("agent_member_id", "代理会员");
		headInfoList.put("recharge_timestamp", "充值时间戳");

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
		mv.addObject("fileName", "充值订单");
		return mv;
    }
	
}

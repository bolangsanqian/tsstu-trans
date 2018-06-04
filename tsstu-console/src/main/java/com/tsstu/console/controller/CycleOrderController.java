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
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.CycleOrder;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.CycleOrderService;
import com.tsstu.console.service.CycleProductService;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/cycle_order")
public class CycleOrderController extends BaseController {

	@Autowired
	public CycleOrderService cycleOrderService;
	
	@Autowired
	public CycleProductService cycleProductService;
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	public AgentMemberService agentMemberService;
	
	/**
	 * 持仓分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/open_index")
	public ModelAndView open_index(Pager<CycleOrder> pager) {
		ModelAndView mv = new ModelAndView("cycle_order/cycle_order_open_index");
		super.initPageData(pager);
		
		//数据权限控制
		super.dataPermission(pager, mv);
		
		//追加查询条件
		pager.putData("status", 0); //只查询持仓订单
		
		cycleOrderService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 平仓分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/close_index")
	public ModelAndView close_index(Pager<CycleOrder> pager) {
		ModelAndView mv = new ModelAndView("cycle_order/cycle_order_close_index");
		super.initPageData(pager);
		
		//追加查询条件
		pager.putData("status", 1); //只查询平仓订单
		
		//数据权限控制
		super.dataPermission(pager, mv);

		cycleOrderService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 持仓详情页面
	 * @return
	 */
	@RequestMapping(value="/open_detail", method=RequestMethod.GET)
	public ModelAndView open_detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		CycleOrder model = cycleOrderService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("订单id", model.getId()));
		detailList.add(new Detail("订单编号", model.getFlow_no()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("昵称", model.getNick_name()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("商品代码", model.getProduct_code()));
		detailList.add(new Detail("商品名称", model.getProduct_name()));
		detailList.add(new Detail("建仓价格", model.getOpen_price()));
		detailList.add(new Detail("做单方向", model.getDirection(), DictConstants.D_TRADING_DIRECTION));
		detailList.add(new Detail("金额", model.getAmount()));
		detailList.add(new Detail("手续费", model.getFee()));
		detailList.add(new Detail("持单时间", model.getHold_time()));
		detailList.add(new Detail("时间单位", model.getTime_unit(), DictConstants.D_HOLD_TIME_UNIT));
		detailList.add(new Detail("潜在收益比例", model.getProfit() + "%"));
		detailList.add(new Detail("订单状态", model.getStatus(), DictConstants.D_ORDER_STATUS));
		detailList.add(new Detail("建仓时间", model.getOpen_time(), DetailDataTypeConstants.DATE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 平仓详情页面
	 * @return
	 */
	@RequestMapping(value="/close_detail", method=RequestMethod.GET)
	public ModelAndView close_detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		CycleOrder model = cycleOrderService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("订单id", model.getId()));
		detailList.add(new Detail("订单编号", model.getFlow_no()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("昵称", model.getNick_name()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("建仓价格", model.getOpen_price()));
		detailList.add(new Detail("平仓价格", model.getClose_price()));
		detailList.add(new Detail("做单方向", model.getDirection(), DictConstants.D_TRADING_DIRECTION));
		detailList.add(new Detail("金额", model.getAmount()));
		detailList.add(new Detail("盈亏金额", model.getYk_amount()));
		detailList.add(new Detail("手续费", model.getFee()));
		detailList.add(new Detail("持单时间", model.getHold_time()));
		detailList.add(new Detail("时间单位", model.getTime_unit(), DictConstants.D_HOLD_TIME_UNIT));
		detailList.add(new Detail("潜在收益比例", model.getProfit() + "%"));
		detailList.add(new Detail("订单状态", model.getStatus(), DictConstants.D_ORDER_STATUS));
		detailList.add(new Detail("建仓时间", model.getOpen_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("平仓时间", model.getClose_time(), DetailDataTypeConstants.DATE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 持仓导出
	 * @param pager
	 * @return
	 */
	@RequestMapping(value="/open_exportExcel")
	public ModelAndView open_exportExcel(Pager<CycleOrder> pager){
		pager.putData("pageSize", 10000000);
		this.open_index(pager);
		List<CycleOrder> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "订单编号");
		headInfoList.put("cycle_goods_id", "简单商品id");
		headInfoList.put("customer_id", "客户id");
		headInfoList.put("goods_name", "商品名称");
		headInfoList.put("open_price", "建仓价格");
		headInfoList.put("close_price", "平仓价格");
		headInfoList.put("direction", "做单方向");
		headInfoList.put("amount", "金额");
		headInfoList.put("yk_amount", "盈亏金额");
		headInfoList.put("fee", "手续费");
		headInfoList.put("hold_time", "持单时间");
		headInfoList.put("time_unit", "时间单位");
		headInfoList.put("open_time", "建仓时间");
		headInfoList.put("close_time", "平仓时间");
		headInfoList.put("member_id", "所属会员ID");
		headInfoList.put("operation_member_id", "运营中心");
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
		mv.addObject("fileName", "周期订单");
		return mv;
    }
	
	/**
	 * 平仓导出
	 * @param pager
	 * @return
	 */
	@RequestMapping(value="/close_exportExcel")
	public ModelAndView close_exportExcel(Pager<CycleOrder> pager){
		pager.putData("pageSize", 10000000);
		this.close_index(pager);
		List<CycleOrder> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("flow_no", "订单编号");
		headInfoList.put("cycle_goods_id", "简单商品id");
		headInfoList.put("customer_id", "客户id");
		headInfoList.put("goods_name", "商品名称");
		headInfoList.put("open_price", "建仓价格");
		headInfoList.put("close_price", "平仓价格");
		headInfoList.put("direction", "做单方向");
		headInfoList.put("amount", "金额");
		headInfoList.put("yk_amount", "盈亏金额");
		headInfoList.put("fee", "手续费");
		headInfoList.put("hold_time", "持单时间");
		headInfoList.put("time_unit", "时间单位");
		headInfoList.put("open_time", "建仓时间");
		headInfoList.put("close_time", "平仓时间");
		headInfoList.put("member_id", "所属会员ID");
		headInfoList.put("operation_member_id", "运营中心");
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
		mv.addObject("fileName", "周期订单");
		return mv;
    }
	
}

package com.tsstu.front.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.ORDER_STATUS;
import com.tsstu.common.model.Quotation;
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.front.cache.QuotationCache;
import com.tsstu.front.core.entity.Result;
import com.tsstu.front.model.Customer;
import com.tsstu.front.model.CycleOrder;
import com.tsstu.front.model.CycleProduct;
import com.tsstu.front.model.HoldTime;
import com.tsstu.front.model.ProductCategory;
import com.tsstu.front.service.CustomerService;
import com.tsstu.front.service.CycleOrderService;
import com.tsstu.front.service.CycleProductService;
import com.tsstu.front.service.HoldTimeService;
import com.tsstu.front.service.ProductCategoryService;

@Controller
@RequestMapping("/trade")
public class TradeController extends BaseController {

	@Autowired
	protected QuotationCache quotationCache;
	
	@Autowired
	private CycleProductService cycleProductService;
	
	@Autowired
	private HoldTimeService holdTimeService;
	
	@Autowired
	private CycleOrderService cycleOrderService;
	
	@Autowired
	private CustomerService CustomerService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@RequestMapping(value={"","/index"}, method=RequestMethod.GET)
	public ModelAndView index(String code){
		ModelAndView mv = new ModelAndView("trade/trade_index");
		
		Quotation quotation = (Quotation)quotationCache.get("realtime_" + code);
		mv.addObject("quotation", quotation);
		
		// 产品
		CycleProduct product = cycleProductService.getOne("code", code);
		mv.addObject("product", product);
		
		
		String now_history = "history_" + code + "_1440_now";
		QuotationHistory history = (QuotationHistory)quotationCache.get(now_history);
		if (null != history) {
			quotation.setOpen(history.getOpen());
		}
		return mv;
	}
	
	/**
	 * 交易中
	 * @param order_id 订单id
	 * @return
	 */
	@RequestMapping(value="trading_dialog", method=RequestMethod.POST)
	public ModelAndView tradingDialog(Integer order_id){
		ModelAndView mv = new ModelAndView("trade/trade_trading_dialog");
		CycleOrder order = cycleOrderService.getOne(order_id);
		long miao = order.getClose_timestamp() - order.getOpen_timestamp();
		mv.addObject("order", order);
		mv.addObject("miao", miao);
		return mv;
	}
	
	/**
	 * 订单确认
	 * @return
	 */
	@RequestMapping(value="confirm_dialog", method=RequestMethod.POST)
	public ModelAndView confirmDialog(Integer product_id, Integer direction){
		ModelAndView mv = new ModelAndView("trade/trade_confirm_dialog");
		
		//商品信息
		CycleProduct product = cycleProductService.getOne(product_id);
		mv.addObject("product", product);
		
		//方向
		mv.addObject("direction", direction);
		
		//持仓时间列表
		List<HoldTime> holdTimeList = holdTimeService.getList("cycle_product_id", product_id);
		mv.addObject("holdTimeList", holdTimeList);
		
		//默认金额列表
		HoldTime holdTime = holdTimeList.get(0);
		String [] defaultAmountList = holdTime.getAmount_list().split(",");
		mv.addObject("defaultAmountList", defaultAmountList);
		return mv;
	}
	
	/**
	 * 持仓列表
	 * @return
	 */
	@RequestMapping(value="hold_dialog", method=RequestMethod.POST)
	public ModelAndView confirmDialog(){
		ModelAndView mv = new ModelAndView("trade/trade_hold_dialog");
		List<CycleOrder> list = cycleOrderService.getList("status", ORDER_STATUS.HOLD);
		mv.addObject("list", list);
		return mv;
	}
	
	/**
	 * 交易
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="trade", method=RequestMethod.POST)
	public Result trade(Integer product_id, Integer hold_time_id, BigDecimal amount, Integer direction){
		Customer customer = CustomerService.getOne(1000000);
		CycleProduct cycleProduct = cycleProductService.getOne(product_id);
		ProductCategory productCategory = productCategoryService.getOne(cycleProduct.getProduct_category_id());
		HoldTime holdTime = holdTimeService.getOne(hold_time_id);
		Result result = cycleOrderService.tradeCheck(customer, productCategory, cycleProduct, holdTime, amount, direction);
		if (result.isOK()) {
			CycleOrder order = new CycleOrder();
			boolean bo = cycleOrderService.trade(customer, order, productCategory, cycleProduct, holdTime, amount, direction);
			if (bo) {
				return success(order);
			}
		}
		return error();
	}
	
	
}

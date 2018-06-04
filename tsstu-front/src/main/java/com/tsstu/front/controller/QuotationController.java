package com.tsstu.front.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsstu.common.model.Quotation;
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.front.cache.QuotationCache;
import com.tsstu.front.core.entity.Result;
import com.tsstu.front.service.QuotationService;

@Controller
@RequestMapping("/quotation")
public class QuotationController extends BaseController {

	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	protected QuotationCache quotationCache;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("history")
	public Result history(String code, String minute, String type, Integer limit) {
		if (limit > 100) {
			limit = 100;
		}
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("minute", minute);
		map.put("limit", limit);
		List<QuotationHistory> historyList = quotationHistoryService.getList(map);
		*/
		
		String key = "history_" + code + "_" + minute;
		List<QuotationHistory> historyList = (List<QuotationHistory>)quotationCache.get(key);
		if (null == historyList) {
			historyList =  new ArrayList<QuotationHistory>();
		}
		limit = historyList.size() > limit ? limit : historyList.size();
		return success(historyList.subList(historyList.size() - limit, historyList.size()));
	}
	
	@ResponseBody
	@RequestMapping("realtime")
	public Result realtime_quotation(String codes, @RequestParam(value="minute", defaultValue="0")Integer minute) {
		List<Object> result = new ArrayList<Object>();
		List<Quotation> quotation_list = new ArrayList<Quotation>();
		QuotationHistory quotation_history_now = new QuotationHistory();
		if (null != codes) {
			String [] code_arr = codes.split(",");
			for (String code : code_arr) {
				code = code.toUpperCase();
				Quotation quotation = (Quotation)quotationCache.get("realtime_" + code);
				if (null != quotation) {
					quotation_list.add(quotation);
				}
			}
			result.add(quotation_list);
			
			if (code_arr.length == 1 && minute > 0) {
				String key = "history_" + code_arr[0] + "_" + minute + "_now";
				quotation_history_now = (QuotationHistory)quotationCache.get(key);
				result.add(quotation_history_now);
			}
		}
		return success(result);
	}
}

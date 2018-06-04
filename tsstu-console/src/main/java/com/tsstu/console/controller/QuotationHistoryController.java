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
import com.tsstu.common.model.QuotationHistory;
import com.tsstu.common.util.MapUtils;
import com.tsstu.console.cache.QuotationCache;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.service.QuotationHistoryService;
import com.tsstu.console.service.QuotationService;

@Controller
@RequestMapping("/quotation_history")
public class QuotationHistoryController extends BaseController {

	@Autowired
	public QuotationHistoryService quotationHistoryService;
	
	@Autowired
	public QuotationService quotationService;
	
	@Autowired
	public QuotationCache quotationCache;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<QuotationHistory> pager) {
		ModelAndView mv = new ModelAndView("quotation_history/quotation_history_index");
		super.initPageData(pager);
		quotationHistoryService.getList(pager);
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
		QuotationHistory model = quotationHistoryService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("行情id", model.getId()));
		detailList.add(new Detail("开盘价", model.getOpen()));
		detailList.add(new Detail("最低价", model.getLow()));
		detailList.add(new Detail("最高价", model.getHigh()));
		detailList.add(new Detail("收盘价", model.getClose()));
		detailList.add(new Detail("时间戳", model.getCtm()));
		detailList.add(new Detail("开盘时间", model.getFirstdt(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("收盘时间", model.getLastdt(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("创建时间", model.getCt(), DetailDataTypeConstants.DATE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("quotation_history/quotation_history_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(QuotationHistory model) {
		int effectRow = quotationHistoryService.add(model);
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
		ModelAndView mv = new ModelAndView("quotation_history/quotation_history_edit");
		QuotationHistory model = quotationHistoryService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(QuotationHistory model) {
		int effectRow = quotationHistoryService.update(model);
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
		int effectRow = quotationHistoryService.delete(id);
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
		int effectRow = quotationHistoryService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 初始化k线
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="init")
	public Result init(Integer[] ids) {
		boolean bo = quotationService.initToCache();
		if (bo) {
			return success();
		} 
		return error();
	}
	
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<QuotationHistory> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<QuotationHistory> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("open", "开盘价");
		headInfoList.put("low", "最低价");
		headInfoList.put("high", "最高价");
		headInfoList.put("close", "收盘价");
		headInfoList.put("ctm", "时间戳");
		headInfoList.put("firstdt", "开盘时间");
		headInfoList.put("lastdt", "收盘时间");
		headInfoList.put("ct", "创建时间");

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
		mv.addObject("fileName", "历史行情");
		return mv;
    }
	
}

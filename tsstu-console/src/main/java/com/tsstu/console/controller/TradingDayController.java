package com.tsstu.console.controller;

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

import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.model.TradingDay;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.TradingDayService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/trading_day")
public class TradingDayController extends BaseController {

	@Autowired
	public TradingDayService tradingDayService;
	
	@Autowired
	public ProductCategoryService productCategoryService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<TradingDay> pager) {
		ModelAndView mv = new ModelAndView("trading_day/trading_day_index");
		super.initPageData(pager);
		tradingDayService.getList(pager);
		mv.addObject("pageInfo", pager);
		
		//商品分类
		List<ProductCategory> categoryList = productCategoryService.getEnableList();
		mv.addObject("categoryList", categoryList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("trading_day/trading_day_edit");
		//商品分类
		List<ProductCategory> categoryList = productCategoryService.getEnableList();
		mv.addObject("categoryList", categoryList);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(TradingDay model) {
		int effectRow = tradingDayService.add(model);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
	
	/**
	 * 修改页面
	 * @return
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public ModelAndView toEdit(Integer id) {
		ModelAndView mv = new ModelAndView("trading_day/trading_day_edit");
		TradingDay model = tradingDayService.getOne(id);
		mv.addObject("model", model);
		//商品分类
		List<ProductCategory> categoryList = productCategoryService.getEnableList();
		mv.addObject("categoryList", categoryList);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(TradingDay trading_day) {
		int effectRow = tradingDayService.update(trading_day);
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
		int effectRow = tradingDayService.delete(id);
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
		int effectRow = tradingDayService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 重置交易日
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="reset")
	public Result reset(Integer product_category_id) {
		boolean bo = tradingDayService.resetByCategoryId(product_category_id);
		if (bo) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<TradingDay> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<TradingDay> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("product_categoray_id", "产品分类");
		headInfoList.put("week", "星期");
		headInfoList.put("opening_time", "开盘时间");
		headInfoList.put("closing_time", "收盘时间");
		headInfoList.put("closing_day", "收盘日期");

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
		mv.addObject("fileName", "交易日管理");
		return mv;
    }
	
}

package com.tsstu.console.controller;

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

import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.CycleProduct;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.service.CycleProductService;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/cycle_product")
public class CycleProductController extends BaseController {

	@Autowired
	public CycleProductService cycleProductService;
	
	@Autowired
	public ProductCategoryService productCategoryService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<CycleProduct> pager) {
		ModelAndView mv = new ModelAndView("cycle_product/cycle_product_index");
		super.initPageData(pager);
		cycleProductService.getList(pager);
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
		ModelAndView mv = new ModelAndView("cycle_product/cycle_product_edit");
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
	public Result add(CycleProduct model) {
		model.setCreate_time(new Date());
		int effectRow = cycleProductService.add(model);
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
	public ModelAndView to_edit(Integer id) {
		ModelAndView mv = new ModelAndView("cycle_product/cycle_product_edit");
		CycleProduct model = cycleProductService.getOne(id);
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
	public Result edit(CycleProduct cycle_product) {
		int effectRow = cycleProductService.update(cycle_product);
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
		int effectRow = cycleProductService.delete(id);
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
		int effectRow = cycleProductService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<CycleProduct> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<CycleProduct> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("code", "产品代码");
		headInfoList.put("name", "产品名称");
		headInfoList.put("unit", "单位");
		headInfoList.put("product_category_id", "产品分类");
		headInfoList.put("max_hold_amount", "最大持仓金额");
		headInfoList.put("min_create_amount", "最小建仓金额");
		headInfoList.put("fee_formula", "手续费公式");
		headInfoList.put("status", "状态");
		headInfoList.put("sort", "排序");
		headInfoList.put("create_time", "创建时间");

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
		mv.addObject("fileName", "产品管理(时限模式)");
		return mv;
    }
	
}

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

import com.tsstu.common.constants.Constants;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.ProductCategory;
import com.tsstu.console.model.QuotationDatasource;
import com.tsstu.console.service.ProductCategoryService;
import com.tsstu.console.service.QuotationDatasourceService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/product_category")
public class ProductCategoryController extends BaseController {

	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private QuotationDatasourceService quotationDatasourceService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<ProductCategory> pager) {
		ModelAndView mv = new ModelAndView("product_category/product_category_index");
		super.initPageData(pager);
		productCategoryService.getList(pager);
		mv.addObject("pageInfo", pager);
		
		// 数据源
		List<QuotationDatasource> dsList = quotationDatasourceService.getList("status", Constants.STATUS_ENABLE);
		mv.addObject("dsList", dsList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("product_category/product_category_edit");
		// 数据源
		List<QuotationDatasource> dsList = quotationDatasourceService.getList("status", Constants.STATUS_ENABLE);
		mv.addObject("dsList", dsList);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(ProductCategory model) {
		model.setCreate_time(new Date());
		int effectRow = productCategoryService.add(model);
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
		ModelAndView mv = new ModelAndView("product_category/product_category_edit");
		ProductCategory model = productCategoryService.getOne(id);
		mv.addObject("model", model);
		// 数据源
		List<QuotationDatasource> dsList = quotationDatasourceService.getList("status", Constants.STATUS_ENABLE);
		mv.addObject("dsList", dsList);
		return mv;
	}
	
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(ProductCategory product_category) {
		int effectRow = productCategoryService.update(product_category);
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
		int effectRow = productCategoryService.delete(id);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<ProductCategory> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<ProductCategory> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("code", "产品代码");
		headInfoList.put("name", "分类名称");
		headInfoList.put("status", "状态");
		headInfoList.put("is_foreign_product", "外汇产品");
		headInfoList.put("rate_type", "汇率类型");
		headInfoList.put("rate", "汇率");
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
		mv.addObject("fileName", "产品分类");
		return mv;
    }
	
}

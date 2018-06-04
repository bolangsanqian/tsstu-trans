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

import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.HoldTime;
import com.tsstu.console.service.HoldTimeService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/hold_time")
public class HoldTimeController extends BaseController {

	@Autowired
	public HoldTimeService holdTimeService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<HoldTime> pager) {
		ModelAndView mv = new ModelAndView("hold_time/hold_time_index");
		super.initPageData(pager);
		holdTimeService.getList(pager);
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
		HoldTime model = holdTimeService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("持单时间id", model.getId()));
		detailList.add(new Detail("简单商品id", model.getCycle_product_id()));
		detailList.add(new Detail("持单时间", model.getHold_time()));
		detailList.add(new Detail("时间单位", model.getTime_unit()));
		detailList.add(new Detail("潜在收益比例", model.getProfit()));
		detailList.add(new Detail("时间段列表", model.getTime_list()));
		detailList.add(new Detail("金额列表", model.getAmount_list()));
		detailList.add(new Detail("排序", model.getSort()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd(Integer cycle_product_id) {
		ModelAndView mv = new ModelAndView("hold_time/hold_time_edit");
		mv.addObject("cycle_product_id", cycle_product_id);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(HoldTime model) {
		int effectRow = holdTimeService.add(model);
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
		ModelAndView mv = new ModelAndView("hold_time/hold_time_edit");
		HoldTime model = holdTimeService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(HoldTime hold_time) {
		int effectRow = holdTimeService.update(hold_time);
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
		int effectRow = holdTimeService.delete(id);
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
		int effectRow = holdTimeService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<HoldTime> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<HoldTime> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("time_unit", "时间单位（M：分钟，H：小时）");
		headInfoList.put("time_list", "时间段列表(09:00-10:00,11:00-12:00)");
		headInfoList.put("amount_list", "金额列表(100,200,300,400)");

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
		mv.addObject("fileName", "持单时间");
		return mv;
    }
	
}

package com.tsstu.console.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.CoderAttribute;
import com.tsstu.console.service.CoderAttributeService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/coder_attribute")
public class CoderAttributeController extends BaseController {

	@Autowired
	public CoderAttributeService coderAttributeService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<CoderAttribute> pager) {
		ModelAndView mv = new ModelAndView("coder_attribute/coder_attribute_index");
		super.initPageData(pager);
		coderAttributeService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd(Integer coder_id) {
		ModelAndView mv = new ModelAndView("coder_attribute/coder_attribute_edit");
		CoderAttribute attr = new CoderAttribute();
		attr.setIs_key(0);
		attr.setIs_search(1);
		attr.setData_length(0);
		attr.setIs_sort(0);
		attr.setIs_show(1);
		attr.setJava_type("String");
		mv.addObject("coder_id", coder_id);
		mv.addObject("model", attr);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(CoderAttribute coderAttribute) {
		coderAttribute.setData_type(this.switchDataType(coderAttribute.getJava_type()));
		coderAttribute.setCreate_time(new Date());
		int effectRow = coderAttributeService.add(coderAttribute);
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
		ModelAndView mv = new ModelAndView("coder_attribute/coder_attribute_edit");
		CoderAttribute model = coderAttributeService.getOne(id);
    	mv.addObject("model", model);
		return mv;
	}
	
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(CoderAttribute coderAttribute) {
		coderAttribute.setData_type(this.switchDataType(coderAttribute.getJava_type()));
		int effectRow = coderAttributeService.update(coderAttribute);
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
		int effectRow = coderAttributeService.delete(id);
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
		int effectRow = coderAttributeService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	private String switchDataType(String javaType) {
		String dataType = javaType;
		if ("Integer".equals(javaType)) {
			dataType = "INTEGER";
		} else if ("String".equals(javaType)) {
			dataType = "VARCHAR";
		} else if ("Float".equals(javaType) || "BigDecimal".equals(javaType)) {
			dataType = "NUMERIC";
		} else if ("Date".equals(javaType)) {
			dataType = "TIMESTAMP";
		}
		return dataType;
	}
	
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值  
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<CoderAttribute> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<CoderAttribute> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("id", "编号");
		headInfoList.put("username", "用户名");
		headInfoList.put("mobile", "手机号码");
		headInfoList.put("email", "邮箱");
		headInfoList.put("sex", "性别");
		headInfoList.put("birthday", "生日");
		headInfoList.put("register_time", "注册日期");
		
		//数据列表
		List<Map<String, Object>> dataList = MapUtils.bean2MapList(list, null);
		
		//数据字典
		Map<String, Map<String, Object>> dictMaps = new HashMap<String, Map<String, Object>>();
		Map<String, Object> sex_dicMap = new HashMap<String, Object>();
		sex_dicMap.put("0", "男");
		sex_dicMap.put("1", "女");
		dictMaps.put("sex", sex_dicMap);
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("headInfoList", headInfoList);
		dataMap.put("dataList", dataList);
		dataMap.put("dictMaps", dictMaps);
		POIExcelView erv = new POIExcelView();				
		ModelAndView mv = new ModelAndView(erv, dataMap);
		mv.addObject("fileName", "用户信息");
		return mv;
    }
	
}

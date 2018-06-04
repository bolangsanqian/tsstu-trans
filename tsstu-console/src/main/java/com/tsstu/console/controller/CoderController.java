package com.tsstu.console.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.util.DelAllFile;
import com.tsstu.common.util.FileZipUtil;
import com.tsstu.common.util.MapUtils;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Coder;
import com.tsstu.console.model.CoderAttribute;
import com.tsstu.console.model.DictionaryItem;
import com.tsstu.console.service.CoderAttributeService;
import com.tsstu.console.service.CoderService;
import com.tsstu.console.service.DictionaryItemService;
import com.tsstu.console.util.FileDownload;
import com.tsstu.console.util.FreemarkerUtils;
import com.tsstu.console.util.PathUtil;

@Controller
@RequestMapping("/coder")
public class CoderController extends BaseController {

	@Autowired
	private CoderService coderService;
	
	@Autowired
	private CoderAttributeService coderAttributeService;
	
	@Autowired
	private DictionaryItemService dictionaryItemService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Coder> pager) {
		ModelAndView mv = new ModelAndView("coder/coder_index");
		super.initPageData(pager);
		coderService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("coder/coder_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(Coder coder) {
		coder.setCreate_time(new Date());
		int effectRow = coderService.add(coder);
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
		ModelAndView mv = new ModelAndView("coder/coder_edit");
		Coder model = coderService.getOne(id);
    	mv.addObject("model", model);
		return mv;
	}
	
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(Coder coder) {
		int effectRow = coderService.update(coder);
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
		int effectRow = coderService.delete(id);
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
		int effectRow = coderService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="generateCode")
	public void generateCode(HttpServletResponse response, String idstr) throws Exception {
		String outFileDir = "temp/code/";
		String templateDir = "template/code/";
		DelAllFile.delFolder(PathUtil.getClasspath() + outFileDir);
		String[] ids = idstr.split(",");
		for (String id : ids) {
			Coder coder = coderService.getOne(Integer.valueOf(id));
			List<CoderAttribute> attributeList = coderAttributeService.getList("coder_id", id);
			if (null != attributeList && attributeList.size() > 0) {
				//文件名
				String table_name = coder.getTable_name();
				String objectName = coder.getClass_name();
				String objectName2 = table_name.substring(table_name.indexOf("_") + 1).toLowerCase();
				String modelName =  objectName + ".java";
				String controllerName = objectName + "Controller.java";
				String serviceName = objectName + "Service.java";
				String serviceImplName = objectName + "ServiceImpl.java";
				String mapperName = objectName + "Mapper.java";
				String mapperXmlName = objectName + "Mapper.xml";
				String indexJspName = objectName2 + "_index.jsp";
				String editJspName = objectName2 + "_edit.jsp";
				String permissionSqlName = table_name + ".sql";
				
				boolean hasDate = false;
				boolean hasCreateTime = false;
				boolean hasCreateBy = false;
				boolean hasBigDecimal = false;
				int stringFieldCount = 0;
				int inputFieldCount = 0;
				Map<String,Object> root = new HashMap<String,Object>();
				for (CoderAttribute attr : attributeList) {
					if ("Date".equals(attr.getJava_type())) { //是否有日期类型
						hasDate = true;
					}
					if ("BigDecimal".equals(attr.getJava_type())) { //是否有金额类型
						hasBigDecimal = true;
					}
					if ("String".equals(attr.getJava_type())) {
						stringFieldCount += 1;
					}
					if (attr.getIs_input().intValue() == 1) {
						inputFieldCount += 1;
					}
					if (null != attr.getDict_code() && !"".equals(attr.getDict_code())) {
						List<DictionaryItem> dictItemList = dictionaryItemService.getList("dict_code", attr.getDict_code());
						attr.setDictItemList(dictItemList);
					} else {
						attr.setDict_code("");
					}
					
					if (attr.getName().equals("create_time")) {
						hasCreateTime = true;
					}
					
					if (attr.getName().equals("create_by")) {
						hasCreateBy = true;
					}
					
				}
				root.put("stringFieldCount", stringFieldCount);
				root.put("inputFieldCount", inputFieldCount);
				root.put("nowDate", new Date());
				root.put("hasDate", hasDate);
				root.put("hasBigDecimal", hasBigDecimal);
				root.put("hasCreateTime", hasCreateTime);
				root.put("hasCreateBy", hasCreateBy);
				root.put("table_name", table_name);
				root.put("objectName", objectName);
				root.put("objectName2", objectName2);
				root.put("title", coder.getName());
				root.put("author", this.getLoginUsername());
				root.put("module", coder);
				root.put("fieldList", attributeList);
				
				//生成model
				FreemarkerUtils.printFile("model.template", root, modelName, outFileDir + "/model/", templateDir);
				
				//生成controller
				FreemarkerUtils.printFile("controller.template", root, controllerName, outFileDir + "/controller/", templateDir);
					
				//生成service
				FreemarkerUtils.printFile("service.template", root, serviceName, outFileDir + "/service/", templateDir);
				FreemarkerUtils.printFile("serviceImpl.template", root, serviceImplName, outFileDir + "/service/impl/", templateDir);
					
				//生成mapper
				FreemarkerUtils.printFile("mapper.template", root, mapperName, outFileDir + "/dao/", templateDir);
				FreemarkerUtils.printFile("mapper_xml.template", root, mapperXmlName, outFileDir + "/dao/", templateDir);
					
				//生成jsp
				FreemarkerUtils.printFile("index_jsp.template", root, indexJspName, outFileDir + "/views/" + objectName2.toLowerCase() + "/", templateDir);
				FreemarkerUtils.printFile("edit_jsp.template", root, editJspName, outFileDir + "/views/" + objectName2.toLowerCase() + "/", templateDir);
				
				//权限SQL
				FreemarkerUtils.printFile("permission_sql.template", root, permissionSqlName, outFileDir + "/sql/", templateDir);
			}
		}
		//生成的全部代码压缩成zip文件
		boolean bo = FileZipUtil.zip(PathUtil.getClasspath() + outFileDir, PathUtil.getClasspath() + "temp/code.zip");
		if(bo){
			FileDownload.fileDownload(response, PathUtil.getClasspath() + "temp/code.zip", "code.zip");
		}
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Coder> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Coder> list = pager.getResult();
		
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
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("headInfoList", headInfoList);
		dataMap.put("dataList", dataList);
		dataMap.put("dictMaps", dictMaps);
		POIExcelView erv = new POIExcelView();				
		ModelAndView mv = new ModelAndView(erv, dataMap);
		mv.addObject("fileName", "用户信息");
		return mv;
    }
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值  
	}
	
}

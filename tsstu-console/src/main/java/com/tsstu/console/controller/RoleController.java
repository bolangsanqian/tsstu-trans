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

import com.alibaba.fastjson.JSON;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Permission;
import com.tsstu.console.model.Role;
import com.tsstu.console.service.PermissionService;
import com.tsstu.console.service.RoleService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	public RoleService roleService;
	
	@Autowired
	public PermissionService permissionService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Role> pager) {
		ModelAndView mv = new ModelAndView("role/role_index");
		super.initPageData(pager);
		roleService.getList(pager);
		mv.addObject("pageInfo", pager);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("role/role_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(Role role) {
		int effectRow = roleService.add(role);
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
		ModelAndView mv = new ModelAndView("role/role_edit");
		Role model = roleService.getOne(id);
    	mv.addObject("model", model);
		return mv;
	}
	
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(Role role) {
		int effectRow = roleService.update(role);
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
	@RequestMapping(value="/del")
	public Result del(Integer id) {
		int effectRow = roleService.delete(id);
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
	@RequestMapping(value="/delBatch")
	public Result del_batch(Integer[] ids) {
		int effectRow = roleService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 修改权限页面
	 * @return
	 */
	@RequestMapping(value="/editPermission", method=RequestMethod.GET)
	public ModelAndView toEditPermission(Integer id) {
		ModelAndView mv = new ModelAndView("role/role_right");
		List<Permission> permissionList =permissionService.buildPermissionByRoleId(id);
    	mv.addObject("permissionList", JSON.toJSONString(permissionList));
    	mv.addObject("roleId", id);
		return mv;
	}
	
	/**
	 * 修改权限页面
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editPermission", method=RequestMethod.POST)
	public Result editPermission(Integer[] ids, Integer roleId) {
		boolean bo = permissionService.updateRolePermission(ids, roleId);
		if (bo) {
			return success();
		}
		return error();
	}
	
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Role> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Role> list = pager.getResult();
		
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
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值  
	}
	
}

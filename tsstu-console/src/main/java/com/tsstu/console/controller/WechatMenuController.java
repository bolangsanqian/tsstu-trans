package com.tsstu.console.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.WechatMenu;
import com.tsstu.console.service.WechatMenuService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/wechat_menu")
public class WechatMenuController extends BaseController {

	@Autowired
	public WechatMenuService wechatMenuService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<WechatMenu> pager,
			@RequestParam(value="id", defaultValue="0") String id,
    		@RequestParam(value="pid", defaultValue="0") String pid) {
		ModelAndView mv = new ModelAndView("wechat_menu/wechat_menu_index");
		super.initPageData(pager);
		
		pager.putData("id", id);
		pager.putData("pid",pid);
		wechatMenuService.getList(pager);
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
		WechatMenu model = wechatMenuService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("菜单id", model.getId()));
		detailList.add(new Detail("父菜单id", model.getPid()));
		detailList.add(new Detail("名称", model.getName()));
		detailList.add(new Detail("菜单类型", model.getMenu_type(), DictConstants.D_WXMENU_TYPE));
		detailList.add(new Detail("关键字", model.getKeyword()));
		detailList.add(new Detail("跳转地址", model.getUrl()));
		detailList.add(new Detail("排序", model.getSort()));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_ENABLE_DISABLE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd(Integer pid) {
		ModelAndView mv = new ModelAndView("wechat_menu/wechat_menu_edit");
		WechatMenu model = new WechatMenu();
		model.setMenu_type(0);
		mv.addObject("model", model);
		mv.addObject("pid", pid);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(WechatMenu model) {
		model.setCreate_time(new Date());
		if (model.getMenu_type().intValue() == 0) {
			model.setUrl("无");
		} else {
			model.setKeyword("无");
		}
		int effectRow = wechatMenuService.add(model);
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
		ModelAndView mv = new ModelAndView("wechat_menu/wechat_menu_edit");
		WechatMenu model = wechatMenuService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(WechatMenu model) {
		if (model.getMenu_type().intValue() == 0) {
			model.setUrl("无");
		} else {
			model.setKeyword("无");
		}
		int effectRow = wechatMenuService.update(model);
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
		int effectRow = wechatMenuService.delete(id);
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
		int effectRow = wechatMenuService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<WechatMenu> pager){
		pager.putData("pageSize", 10000000);
		//this.index(pager);
		List<WechatMenu> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("name", "名称");
		headInfoList.put("menu_type", "菜单类型");
		headInfoList.put("keyword", "关键字");
		headInfoList.put("url", "跳转地址");
		headInfoList.put("status", "状态");
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
		mv.addObject("fileName", "菜单管理(微信)");
		return mv;
    }
	
}

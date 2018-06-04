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
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.WechatMsg;
import com.tsstu.console.service.WechatMsgService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/wechat_msg")
public class WechatMsgController extends BaseController {

	@Autowired
	public WechatMsgService wechatMsgService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<WechatMsg> pager) {
		ModelAndView mv = new ModelAndView("wechat_msg/wechat_msg_index");
		super.initPageData(pager);
		wechatMsgService.getList(pager);
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
		WechatMsg model = wechatMsgService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("消息id", model.getId()));
		detailList.add(new Detail("关键字", model.getKeyword()));
		detailList.add(new Detail("匹配方式", model.getMatch_type(), DictConstants.D_WXMSG_MATCH_TYPE));
		detailList.add(new Detail("消息类型", model.getMsg_type(), DictConstants.D_WXMSG_TYPE));
		detailList.add(new Detail("内容", model.getContent()));
		detailList.add(new Detail("状态(0：禁用，1：启用)", model.getStatus(), DictConstants.D_ENABLE_DISABLE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("备注", model.getRemark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("wechat_msg/wechat_msg_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(WechatMsg model) {
		int effectRow = wechatMsgService.add(model);
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
		ModelAndView mv = new ModelAndView("wechat_msg/wechat_msg_edit");
		WechatMsg model = wechatMsgService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(WechatMsg wechat_msg) {
		int effectRow = wechatMsgService.update(wechat_msg);
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
		int effectRow = wechatMsgService.delete(id);
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
		int effectRow = wechatMsgService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<WechatMsg> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<WechatMsg> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("keyword", "关键字");
		headInfoList.put("match_type", "匹配方式");
		headInfoList.put("msg_type", "消息类型");
		headInfoList.put("content", "内容");
		headInfoList.put("status", "状态(0：禁用，1：启用)");
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
		mv.addObject("fileName", "消息管理");
		return mv;
    }
	
}

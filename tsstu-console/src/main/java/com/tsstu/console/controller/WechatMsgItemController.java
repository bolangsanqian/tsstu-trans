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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.WechatMsgItem;
import com.tsstu.console.service.WechatMsgItemService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/wechat_msg_item")
public class WechatMsgItemController extends BaseController {

	@Autowired
	public WechatMsgItemService wechatMsgItemService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<WechatMsgItem> pager) {
		ModelAndView mv = new ModelAndView("wechat_msg_item/wechat_msg_item_index");
		super.initPageData(pager);
		wechatMsgItemService.getList(pager);
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
		WechatMsgItem model = wechatMsgItemService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("子项id", model.getId()));
		detailList.add(new Detail("消息id", model.getWechat_msg_id()));
		detailList.add(new Detail("标题", model.getTitle()));
		detailList.add(new Detail("描述", model.getDescription()));
		detailList.add(new Detail("图片地址", model.getImage_url()));
		detailList.add(new Detail("跳转地址", model.getUrl()));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_ENABLE_DISABLE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("备注", model.getRemark()));
		detailList.add(new Detail("排序", model.getSort()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd(Integer wechat_msg_id) {
		ModelAndView mv = new ModelAndView("wechat_msg_item/wechat_msg_item_edit");
		mv.addObject("wechat_msg_id", wechat_msg_id);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(WechatMsgItem model) {
		model.setCreate_time(new Date());
		int effectRow = wechatMsgItemService.add(model);
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
		ModelAndView mv = new ModelAndView("wechat_msg_item/wechat_msg_item_edit");
		WechatMsgItem model = wechatMsgItemService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(WechatMsgItem wechat_msg_item) {
		int effectRow = wechatMsgItemService.update(wechat_msg_item);
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
		int effectRow = wechatMsgItemService.delete(id);
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
		int effectRow = wechatMsgItemService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<WechatMsgItem> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<WechatMsgItem> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("title", "标题");
		headInfoList.put("description", "描述");
		headInfoList.put("image_url", "图片地址");
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
		mv.addObject("fileName", "消息子项(微信)");
		return mv;
    }
	
}

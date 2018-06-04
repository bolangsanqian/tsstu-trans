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

import com.tsstu.common.constants.Constants;
import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Broker;
import com.tsstu.console.service.BrokerService;
import com.tsstu.console.service.UserService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/broker")
public class BrokerController extends BaseController {

	@Autowired
	public BrokerService brokerService;
	
	@Autowired
	public UserService userService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Broker> pager) {
		ModelAndView mv = new ModelAndView("broker/broker_index");
		super.initPageData(pager);
		brokerService.getList(pager);
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
		Broker model = brokerService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("经纪人id", model.getId()));
		detailList.add(new Detail("用户id", model.getUser_id()));
		detailList.add(new Detail("客户id", model.getCustomer_id()));
		detailList.add(new Detail("邀请码", model.getInvite_code()));
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
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("broker/broker_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(Broker model) {
		model.setCreate_time(new Date());
		int effectRow = brokerService.add(model);
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
		ModelAndView mv = new ModelAndView("broker/broker_edit");
		Broker model = brokerService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(Integer id, String password, String repassword) {
		if (!password.equals(repassword)) {
			return error("两次密码输入不一致！");
		}
		
		Broker broker = brokerService.getOne(id);
		if (null == broker) {
			return error("经纪人不存在！");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", userService.md5password(password));
		map.put("id", broker.getUser_id());
		int effectRow = userService.update(map);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit_status", method=RequestMethod.POST)
	public Result updateStatus(Integer id, Integer status) {
		if (Constants.STATUS_ENABLE != status.intValue() && Constants.STATUS_DISABLE != status.intValue()) {
			return error("非法操作！");
		}
		int effectRow = brokerService.update("id", id, "status", status);
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
		int effectRow = brokerService.delete(id);
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
		int effectRow = brokerService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Broker> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Broker> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("user_id", "用户id");
		headInfoList.put("customer_id", "客户id");
		headInfoList.put("invite_code", "邀请码");
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
		mv.addObject("fileName", "经纪人");
		return mv;
    }
	
}

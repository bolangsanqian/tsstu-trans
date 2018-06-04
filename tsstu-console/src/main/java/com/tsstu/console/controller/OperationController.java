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
import com.tsstu.common.constants.Constants.MemberTypeConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Member;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/operation")
public class OperationController extends BaseController {

	@Autowired
	public MemberService memberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Member> pager) {
		ModelAndView mv = new ModelAndView("operation/operation_index");
		super.initPageData(pager);
		
		// 添加查询条件
		pager.putData("member_type", MemberTypeConstants.OPERATION); //只查询普通会员
		memberService.getList(pager);
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
		Member model = memberService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("会员编号", model.getId()));
		detailList.add(new Detail("名称", model.getName()));
		detailList.add(new Detail("公司名字", model.getCompany_name()));
		detailList.add(new Detail("公司领导", model.getCompany_leader()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("证件类型", model.getIdentity_type(), DictConstants.MEMBER_IDENTITY_TYPE));
		detailList.add(new Detail("证件号", model.getIdentity()));
		detailList.add(new Detail("余额", model.getBalance()));
		detailList.add(new Detail("冻结资金", model.getFrozen_money()));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_ENABLE_DISABLE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("微信二维码地址", model.getWx_qrcode_url(), DetailDataTypeConstants.IMAGE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("operation/operation_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(Member model) {
		model.setCreate_time(new Date());
		model.setMember_type(MemberTypeConstants.OPERATION);
		int effectRow = memberService.add(model);
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
		ModelAndView mv = new ModelAndView("operation/operation_edit");
		Member model = memberService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(Member member) {
		int effectRow = memberService.update(member);
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
		int effectRow = memberService.delete(id);
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
		int effectRow = memberService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Member> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Member> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("id", "运营中心id");
		headInfoList.put("name", "名称");
		headInfoList.put("company_name", "公司名字");
		headInfoList.put("company_leader", "公司领导");
		headInfoList.put("mobile", "手机号码");
		headInfoList.put("identity_type", "证件类型");
		headInfoList.put("identity", "证件号");
		headInfoList.put("balance", "余额");
		headInfoList.put("frozen_money", "冻结资金");
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
		mv.addObject("fileName", "运营中心");
		return mv;
    }
	
}

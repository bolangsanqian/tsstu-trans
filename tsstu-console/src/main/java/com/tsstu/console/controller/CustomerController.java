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
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Customer;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.CustomerService;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Autowired
	public CustomerService customerService;
	
	@Autowired
	public MemberService memberService;
	
	@Autowired
	public AgentMemberService agentMemberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Customer> pager) {
		ModelAndView mv = new ModelAndView("customer/customer_index");
		super.initPageData(pager);
		
		//数据权限控制
		super.dataPermission(pager, mv);
		
		customerService.getList(pager);
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
		Customer model = customerService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("客户id", model.getId()));
		detailList.add(new Detail("客户编号", model.getCust_no()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("可用余额", model.getBalance()));
		detailList.add(new Detail("冻结资金", model.getFrozen_money()));
		detailList.add(new Detail("客户昵称", model.getNick_name()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_MEMBER));
		detailList.add(new Detail("代理会员", model.getAgent_member_id(), DictConstants.DICT_AGENT_MEMBER));
		detailList.add(new Detail("客户状态", model.getStatus(), DictConstants.D_CUSTOMER_STATUS));
		detailList.add(new Detail("注册日期", model.getRegister_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("注册ip", model.getRegister_ip()));
		detailList.add(new Detail("最后登录时间", model.getLast_login_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("最后登录ip", model.getLast_login_ip()));
		detailList.add(new Detail("分享码", model.getShare_code()));
		detailList.add(new Detail("是否为经纪人", model.getIs_borker(), DictConstants.D_TRUE_FALSE));
		detailList.add(new Detail("所属经纪人", model.getBroker_id()));
		detailList.add(new Detail("是否已激活", model.getActive_status(), DictConstants.D_CUSTOMER_ACTIVE_STATUS));
		detailList.add(new Detail("是否容许充值", model.getRecharge_status(), DictConstants.D_ALLOW_STATUS));
		detailList.add(new Detail("是否容许提现", model.getWithdraw_status(), DictConstants.D_ALLOW_STATUS));
		detailList.add(new Detail("是否容许交易", model.getTrade_status(), DictConstants.D_ALLOW_STATUS));
		detailList.add(new Detail("是否容许登录", model.getLogin_status(), DictConstants.D_ALLOW_STATUS));
		detailList.add(new Detail("性别", model.getSex(), DictConstants.D_SEX));
		detailList.add(new Detail("邮箱", model.getEmail()));
		detailList.add(new Detail("生日", model.getBirthday(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("头像地址", model.getHead_url(), DetailDataTypeConstants.IMAGE));
		detailList.add(new Detail("微信openid", model.getWx_openid()));
		detailList.add(new Detail("层级编号", model.getH_number()));
		detailList.add(new Detail("层级等级", model.getH_level()));
		if (model.getStatus().intValue() == 2) {
			detailList.add(new Detail("销户前手机号码", model.getOrg_mobile()));
			detailList.add(new Detail("销户前微信openid", model.getOrg_wx_openid()));
		}
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("customer/customer_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="add", method=RequestMethod.POST)
	public Result add(Customer model) {
		int effectRow = customerService.add(model);
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
		ModelAndView mv = new ModelAndView("customer/customer_edit");
		Customer model = customerService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public Result edit(Customer customer) {
		int effectRow = customerService.update(customer);
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
		int effectRow = customerService.delete(id);
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
		int effectRow = customerService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 启用账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="enable_account", method=RequestMethod.POST)
	public Result enableAccount(Integer id) {
		boolean bo = customerService.enableAccount(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁用账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="disable_account", method=RequestMethod.POST)
	public Result disableAccount(Integer id) {
		boolean bo = customerService.disableAccount(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 销户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="cancel_account", method=RequestMethod.POST)
	public Result cancelAccount(Integer id) {
		boolean bo = customerService.cancelAccount(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 容许充值
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="allow_recharge", method=RequestMethod.POST)
	public Result allowRecharge (Integer id) {
		boolean bo =customerService.allowRecharge(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁止充值
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="stop_recharge", method=RequestMethod.POST)
	public Result stopRecharge(Integer id) {
		boolean bo = customerService.stopRecharge(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 容许提现
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="allow_withdraw", method=RequestMethod.POST)
	public Result allowWithdraw(Integer id) {
		boolean bo = customerService.allowWithdraw(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁止提现
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="stop_withdraw", method=RequestMethod.POST)
	public Result stopWithdraw(Integer id) {
		boolean bo = customerService.stopWithdraw(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 容许交易
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="allow_trade", method=RequestMethod.POST)
	public Result allowTrade(Integer id) {
		boolean bo = customerService.allowTrade(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁止交易
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="stop_trade", method=RequestMethod.POST)
	public Result stopTrade(Integer id) {
		boolean bo = customerService.stopTrade(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 容许登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="allow_login", method=RequestMethod.POST)
	public Result allowLogin(Integer id) {
		boolean bo = customerService.allowLogin(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁止登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="stop_login", method=RequestMethod.POST)
	public Result stopLogin(Integer id) {
		boolean bo = customerService.stopLogin(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	/**
	 * 禁止登录
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="unbind_wx", method=RequestMethod.POST)
	public Result unbindWechat(Integer id) {
		boolean bo = customerService.unbindWechat(id);
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Customer> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Customer> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("cust_no", "客户编号");
		headInfoList.put("mobile", "手机号码");
		headInfoList.put("balance", "余额");
		headInfoList.put("frozen_money", "冻结资金");
		headInfoList.put("nick_name", "昵称");
		headInfoList.put("wx_openid", "微信openid");
		headInfoList.put("head_url", "头像地址");
		headInfoList.put("is_borker", "是否为经纪人");
		headInfoList.put("agency_member_id", "运营中心id");
		headInfoList.put("member_id", "所属会员id");
		headInfoList.put("agent_member_id", "代理会员id");
		headInfoList.put("status", "状态");
		headInfoList.put("allow_recharge", "是否容许充值");
		headInfoList.put("allow_withdraw", "是否容许提现");
		headInfoList.put("allow_login", "是否容许登录");
		headInfoList.put("allow_trade", "是否容许交易");
		headInfoList.put("register_time", "注册日期");
		headInfoList.put("register_ip", "注册ip地址");
		headInfoList.put("last_login_time", "最后登录时间");
		headInfoList.put("org_mobile", "销户前手机号码");
		headInfoList.put("share_code", "分享码");

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
		mv.addObject("fileName", "客户管理");
		return mv;
    }
	
}

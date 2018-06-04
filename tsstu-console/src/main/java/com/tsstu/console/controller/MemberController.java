package com.tsstu.console.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.common.constants.Constants.FileConstants;
import com.tsstu.common.constants.Constants.MemberTypeConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.DictMember;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Member;
import com.tsstu.console.model.User;
import com.tsstu.console.service.MemberService;
import com.tsstu.console.service.UserService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	public MemberService memberService;
	
	@Autowired
	public UserService userService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Member> pager) {
		ModelAndView mv = new ModelAndView("member/member_index");
		super.initPageData(pager);
		
		// 添加查询条件
		pager.putData("member_type", MemberTypeConstants.COMMON_MEMBER); //只查询普通会员
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
		detailList.add(new Detail("是否为默认会员", model.getIs_default(), DictConstants.D_TRUE_FALSE));
		detailList.add(new Detail("邀请码", model.getInvite_code()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("客户激活金额", model.getCustomer_active_amount()));
		detailList.add(new Detail("交易所佣金", model.getExchange_commission()));
		detailList.add(new Detail("运营中心佣金", model.getOperation_commission()));
		detailList.add(new Detail("会员佣金", model.getMember_commission()));
		detailList.add(new Detail("经纪人直接佣金", model.getDirect_commission()));
		detailList.add(new Detail("经济人间接佣金", model.getIndirect_commission()));
		detailList.add(new Detail("经纪人直接分红", model.getDirect_b_commission()));
		detailList.add(new Detail("经纪人间接分红", model.getIndirect_b_commission()));
		detailList.add(new Detail("微信二维码地址", model.getWx_qrcode_url(), DetailDataTypeConstants.IMAGE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 会员列表
	 * @param operation_member_id
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getMemberList")
	public List<DictMember> getMemberList(@RequestParam(name="operation_member_id", defaultValue="0")Integer operation_member_id, HttpServletRequest request) {
		List<DictMember> dictList = new ArrayList<DictMember>();
		List<DictMember> list = (List<DictMember>)request.getServletContext().getAttribute(DictConstants.DICT_COMMON_MEMBER);
		if (null != list) {
			for (DictMember dict : list) {
				if (operation_member_id == 0 || dict.getOperation_member_id().intValue() == operation_member_id.intValue()) {
					dictList.add(dict);
				}
			}
		}
		return dictList;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("member/member_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(Member model) {
		model.setCreate_time(new Date());
		model.setMember_type(MemberTypeConstants.COMMON_MEMBER);
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
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView toEdit(Integer id) {
		ModelAndView mv = new ModelAndView("member/member_edit");
		Member model = memberService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
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
	@RequestMapping(value="/del")
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
	@RequestMapping(value="/delBatch")
	public Result del_batch(Integer[] ids) {
		int effectRow = memberService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 公众号二维码上传页面
	 * @return
	 */
	@RequestMapping(value="/upload_wx_qrcode", method=RequestMethod.GET)
	public ModelAndView to_qrcode_upload(Integer id) {
		ModelAndView mv = new ModelAndView("member/member_qrcode_upload");
		Member member = memberService.getOne(id);
		mv.addObject("model", member);
		return mv;
	}
	
	/**
     * 公众号二维码上传
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upload_wx_qrcode",method=RequestMethod.POST)
    public Result qrcode_upload(Integer id, MultipartFile wx_qrcode_url) {
		//先保存Banner图片
    	Member m = new Member();
    	m.setId(id);
		if(null != wx_qrcode_url) {
			String fileName = wx_qrcode_url.getOriginalFilename().toLowerCase();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			if( !(fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) ){
				return error("二维码图片格式不对！");
			}
			String savePath = getRequest().getServletContext().getRealPath(FileConstants.WX_QRCODE_DIR);
			String savedFileName = System.currentTimeMillis()+"."+fileType;
			String fullPath = savePath +File.separator+ savedFileName;
			try {
				BufferedImage image = ImageIO.read(wx_qrcode_url.getInputStream());
				File saveFile = new File(savePath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				ImageIO.write( image, fileType , new File (fullPath) );
			} catch (IOException e) {
				throw new ValidateException("保存会员二维码图片失败，请稍后重试",e);
			}
			m.setWx_qrcode_url(FileConstants.WX_QRCODE_DIR + savedFileName);
		} else {
			return error("二维码图片为空");
		}
    	int effectRow = memberService.update(m);
    	if (effectRow > 0) {
    		return success();
    	}
    	return error("操作失败");
    }
    
    /**
	 * 删除公众号二维码
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/del_wx_qrcode", method=RequestMethod.GET)
	public Result del_wx_qrcode(Integer id) {
		int effectRow = memberService.delWxQrcode(id);
		if (effectRow > 0) {
			return success();
		}
		return error();
	}
    
    /**
	 * 账号页面
	 * @return
	 */
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public ModelAndView toAccount(Integer id, Integer user_type) {
		ModelAndView mv = new ModelAndView("member/member_account");
		Member member = memberService.getOne(id);
		User user = memberService.getAccount(id);
		mv.addObject("model", user);
		mv.addObject("member_id", member.getId());
		mv.addObject("mobile", member.getMobile());
		mv.addObject("user_type", user_type);
		return mv;
	}
	
	/**
	 * 创建、修改账号
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public Result account(User user, Integer member_id) {
		int effectRow = 0;
		Member member = memberService.getOne(member_id);
		if (member.getUser_id() > 0) {
			effectRow = memberService.updateAccount(user, member_id);
		} else {
			effectRow = memberService.createAccount(user, member_id, super.getLoginUserId());
		}
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
		headInfoList.put("id", "会员编号");
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
		headInfoList.put("is_default", "是否为默认会员");
		headInfoList.put("invite_code", "会员邀请码");
		headInfoList.put("operation_member_id", "运营中心id");
		headInfoList.put("exchange_commission", "交易所佣金");
		headInfoList.put("operation_commission", "运营中心佣金");
		headInfoList.put("member_commission", "会员佣金");
		headInfoList.put("direct_commission", "经纪人直接佣金");
		headInfoList.put("indirect_commission", "经济人间接佣金");
		headInfoList.put("direct_b_commission", "经纪人直接分红");
		headInfoList.put("indirect_b_commission", "经纪人间接分红");

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
		mv.addObject("fileName", "会员管理");
		return mv;
    }
	
}

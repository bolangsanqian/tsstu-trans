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
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.DictMember;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.AgentMember;
import com.tsstu.console.model.Member;
import com.tsstu.console.service.AgentMemberService;
import com.tsstu.console.service.MemberService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/agent_member")
public class AgentMemberController extends BaseController {

	@Autowired
	public AgentMemberService agentMemberService;
	
	@Autowired
	public MemberService memberService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<AgentMember> pager) {
		ModelAndView mv = new ModelAndView("agent_member/agent_member_index");
		super.initPageData(pager);
		agentMemberService.getList(pager);
		mv.addObject("pageInfo", pager);
		
		//运营中心列表
		List<Member> operationList = memberService.getEnableOperationList();
		mv.addObject("operationList", operationList);
		//会员列表
		List<Member>  memberList = memberService.getEnableCommonMemberList();
		mv.addObject("memberList", memberList);
		return mv;
	}
	
	/**
	 * 详情页面
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		AgentMember model = agentMemberService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("编号", model.getId()));
		detailList.add(new Detail("代理名称", model.getName()));
		detailList.add(new Detail("运营中心", model.getOperation_member_id(), DictConstants.DICT_OPERATION));
		detailList.add(new Detail("所属会员", model.getMember_id(), DictConstants.DICT_COMMON_MEMBER));
		detailList.add(new Detail("公司名称", model.getCompany_name()));
		detailList.add(new Detail("负责人", model.getCompany_leader()));
		detailList.add(new Detail("手机号码", model.getMobile()));
		detailList.add(new Detail("证件类型", model.getIdentity_type(), DictConstants.MEMBER_IDENTITY_TYPE));
		detailList.add(new Detail("证件号", model.getIdentity()));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_ENABLE_DISABLE));
		detailList.add(new Detail("提成", model.getPsition_percent()));
		detailList.add(new Detail("邀请码", model.getInvite_code()));
		detailList.add(new Detail("公众号二维码", model.getWx_qrcode_url(), DetailDataTypeConstants.IMAGE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 代理会员字典
	 * @param member_id
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("getAgentMemberList")
	public List<DictMember> getAgentMemberList(
			@RequestParam(name="member_id", defaultValue="0")Integer member_id, 
			@RequestParam(name="operation_member_id", defaultValue="0")Integer operation_member_id, HttpServletRequest request) {
		List<DictMember> dictList = new ArrayList<DictMember>();
		List<DictMember> list = (List<DictMember>)request.getServletContext().getAttribute(DictConstants.DICT_AGENT_MEMBER);
		if (null != list) {
			for (DictMember dict : list) {
				if ((operation_member_id == 0 && member_id == 0)) {
					dictList.add(dict);
				} else if ((operation_member_id == 0 && member_id != 0) && dict.getMember_id().intValue() == member_id.intValue()) {
					dictList.add(dict);
				} else if ((operation_member_id != 0 && member_id == 0) && dict.getOperation_member_id().intValue() == operation_member_id.intValue()) {
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
		ModelAndView mv = new ModelAndView("agent_member/agent_member_edit");
		//运营中心列表
		List<Member> operationList = memberService.getEnableOperationList();
		mv.addObject("operationList", operationList);
		//会员列表
		List<Member>  memberList = memberService.getEnableCommonMemberList();
		mv.addObject("memberList", memberList);
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(AgentMember model) {
		model.setCreate_time(new Date());
		int effectRow = agentMemberService.add(model);
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
		ModelAndView mv = new ModelAndView("agent_member/agent_member_edit");
		AgentMember model = agentMemberService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(AgentMember agent_member) {
		int effectRow = agentMemberService.update(agent_member);
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
		int effectRow = agentMemberService.delete(id);
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
		int effectRow = agentMemberService.deleteBatch(ids);
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
		ModelAndView mv = new ModelAndView("agent_member/agent_member_qrcode_upload");
		AgentMember agentMember = agentMemberService.getOne(id);
		mv.addObject("model", agentMember);
		return mv;
	}
	
	/**
     * 公众号二维码上传
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upload_wx_qrcode",method=RequestMethod.POST)
    public Result qrcode_upload(Integer id, MultipartFile wx_qrcode_url) {
    	AgentMember m = new AgentMember();
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
    	int effectRow = agentMemberService.updateQrcodeUrl(id, m.getWx_qrcode_url());
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
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<AgentMember> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<AgentMember> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("member_id", "会员编号");
		headInfoList.put("name", "代理会员名称");
		headInfoList.put("company_name", "公司名称");
		headInfoList.put("company_leader", "公司负责人");
		headInfoList.put("mobile", "手机号码");
		headInfoList.put("identity", "证件编号");
		headInfoList.put("psition_percent", "分红比例");
		headInfoList.put("status", "状态");
		headInfoList.put("invite_code", "邀请码");
		headInfoList.put("create_time", "创建时间");
		headInfoList.put("remark", "备注");

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
		mv.addObject("fileName", "代理会员");
		return mv;
    }
	
}

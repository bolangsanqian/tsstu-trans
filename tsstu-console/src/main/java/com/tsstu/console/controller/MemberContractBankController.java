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

import com.tsstu.common.constants.Constants.D_CONTRACT_BANK_STATUS;
import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.MemberContractBank;
import com.tsstu.console.model.Region;
import com.tsstu.console.service.MemberContractBankService;
import com.tsstu.console.service.RegionService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/member_contract_bank")
public class MemberContractBankController extends BaseController {

	@Autowired
	public MemberContractBankService memberContractBankService;
	
	@Autowired
	public RegionService regionService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<MemberContractBank> pager) {
		ModelAndView mv = new ModelAndView("member_contract_bank/member_contract_bank_index");
		super.initPageData(pager);
		memberContractBankService.getList(pager);
		mv.addObject("pageInfo", pager);
		
		//省
		List<Region> provPegionList = regionService.getList("parent_code", "0");
		mv.addObject("provRegionList", provPegionList);
		return mv;
	}
	
	/**
	 * 详情页面
	 * @return
	 */
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView detail(Integer id) {
		ModelAndView mv = new ModelAndView("public/detail");
		MemberContractBank model = memberContractBankService.getOne(id);
		
		Region prov_region = regionService.getOne("code", model.getProvince_code());
		Region city_region = regionService.getOne("code", model.getCity_code());
		
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("签约银行id", model.getId()));
		detailList.add(new Detail("会员编号", model.getMember_id()));
		detailList.add(new Detail("真实姓名", model.getReal_name()));
		detailList.add(new Detail("身份证", model.getIdentity()));
		detailList.add(new Detail("银行编号", model.getBank_no(), DictConstants.DICT_CONTRACT_BANK));
		detailList.add(new Detail("银行卡号", model.getBankcard_no()));
		detailList.add(new Detail("省份", prov_region.getName()));
		detailList.add(new Detail("城市", city_region.getName()));
		detailList.add(new Detail("银行支行", model.getBank_branch()));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_CONTRACT_BANK_STATUS));
		detailList.add(new Detail("创建时间", model.getCreate_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("最后更新时间", model.getLast_update_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审批人", model.getReview_id()));
		detailList.add(new Detail("审批时间", model.getReview_time(), DetailDataTypeConstants.DATE));
		detailList.add(new Detail("审批备注", model.getReview_remark()));
		mv.addObject("detailList", detailList);
		return mv;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView toAdd() {
		ModelAndView mv = new ModelAndView("member_contract_bank/member_contract_bank_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(MemberContractBank model) {
		int effectRow = memberContractBankService.add(model);
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
		ModelAndView mv = new ModelAndView("member_contract_bank/member_contract_bank_edit");
		MemberContractBank model = memberContractBankService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(MemberContractBank member_contract_bank) {
		int effectRow = memberContractBankService.update(member_contract_bank);
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
		int effectRow = memberContractBankService.delete(id);
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
		int effectRow = memberContractBankService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	/**
	 * 审批页面
	 * @return
	 */
	@RequestMapping(value="/review", method=RequestMethod.GET)
	public ModelAndView toReview(Integer id) {
		ModelAndView mv = new ModelAndView("member_contract_bank/member_contract_bank_review");
		mv.addObject("id", id);
		return mv;
	}
	
	/**
	 * 审批通过
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/review", method=RequestMethod.POST)
	public Result reviewPass(Integer id, Integer review_status, String review_remark) {
		if (review_status != D_CONTRACT_BANK_STATUS.YTG && review_status != D_CONTRACT_BANK_STATUS.YBH) { //非法状态
			return error("签约银行状态异常");
		}
		boolean bo = false;
		if (review_status == D_CONTRACT_BANK_STATUS.YTG) {
			bo = memberContractBankService.reviewPass(id, super.getLoginUserId(), review_remark);
		} else {
			bo = memberContractBankService.reviewReject(id, super.getLoginUserId(), review_remark);
		}
		if (bo) {
			return success();
		}
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<MemberContractBank> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<MemberContractBank> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("real_name", "真实姓名");
		headInfoList.put("identity", "身份证");
		headInfoList.put("bank_no", "银行编号");
		headInfoList.put("bankcard_no", "银行卡号");
		headInfoList.put("bank_branch", "银行支行");
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
		mv.addObject("fileName", "签约管理(会员)");
		return mv;
    }
	
}

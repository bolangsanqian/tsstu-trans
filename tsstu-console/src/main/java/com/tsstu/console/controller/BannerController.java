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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tsstu.common.constants.Constants.DetailDataTypeConstants;
import com.tsstu.common.constants.Constants.DictConstants;
import com.tsstu.common.constants.Constants.FileConstants;
import com.tsstu.console.core.entity.Detail;
import com.tsstu.console.core.entity.Result;
import com.tsstu.console.core.exception.ValidateException;
import com.tsstu.console.core.page.Pager;
import com.tsstu.console.core.view.POIExcelView;
import com.tsstu.console.model.Banner;
import com.tsstu.console.service.BannerService;
import com.tsstu.common.util.MapUtils;

@Controller
@RequestMapping("/banner")
public class BannerController extends BaseController {

	@Autowired
	public BannerService bannerService;
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(Pager<Banner> pager) {
		ModelAndView mv = new ModelAndView("banner/banner_index");
		super.initPageData(pager);
		bannerService.getList(pager);
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
		Banner model = bannerService.getOne(id);
		List<Detail> detailList = new ArrayList<Detail>();
		detailList.add(new Detail("首页banner图id", model.getId()));
		detailList.add(new Detail("标题", model.getTitle()));
		detailList.add(new Detail("图片地址", model.getImage_url()));
		detailList.add(new Detail("跳转地址", model.getJump_url()));
		detailList.add(new Detail("交易模式", model.getTrade_mode(), DictConstants.D_TRADE_MODE));
		detailList.add(new Detail("状态", model.getStatus(), DictConstants.D_TRUE_FALSE));
		detailList.add(new Detail("排序", model.getSort()));
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
		ModelAndView mv = new ModelAndView("banner/banner_edit");
		return mv;
	}
	
	/**
	 * 添加
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Result add(Banner model, MultipartFile img) {
		//先保存Banner图片
		if(null != img) {
			String fileName = img.getOriginalFilename().toLowerCase();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			if( !(fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) ){
				return error("图片格式不正确！");
			}
			String savePath = getRequest().getServletContext().getRealPath(FileConstants.BANNER_DIR);
			String savedFileName = System.currentTimeMillis()+"."+fileType;
			String fullPath = savePath +File.separator+ savedFileName;
			try {
				BufferedImage image = ImageIO.read(img.getInputStream());
				File saveFile = new File(savePath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				ImageIO.write( image, fileType , new File (fullPath) );
			} catch (IOException e) {
				throw new ValidateException("保存图片失败，请稍后重试",e);
			}
			model.setImage_url(FileConstants.BANNER_DIR + savedFileName);
		} else {
			return error("图片为空");
		}
		model.setCreate_time(new Date());
		int effectRow = bannerService.add(model);
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
		ModelAndView mv = new ModelAndView("banner/banner_edit");
		Banner model = bannerService.getOne(id);
		mv.addObject("model", model);
		return mv;
	}
	
	/**
	 * 修改
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public Result edit(Banner banner, MultipartFile img) {
		//先保存Banner图片
		if(null != img) {
			String fileName = img.getOriginalFilename().toLowerCase();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			if( !(fileName.endsWith(".jpg") || fileName.endsWith(".gif") || fileName.endsWith(".png")) ){
				return error("图片格式不正确！");
			}
			String savePath = getRequest().getServletContext().getRealPath(FileConstants.BANNER_DIR);
			String savedFileName = System.currentTimeMillis()+"."+fileType;
			String fullPath = savePath +File.separator+ savedFileName;
			try {
				BufferedImage image = ImageIO.read(img.getInputStream());
				File saveFile = new File(savePath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				ImageIO.write( image, fileType , new File (fullPath) );
			} catch (IOException e) {
				throw new ValidateException("保存图片失败，请稍后重试",e);
			}
			banner.setImage_url(FileConstants.BANNER_DIR + savedFileName);
		} else {
			return error("图片为空");
		}
		int effectRow = bannerService.update(banner);
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
		int effectRow = bannerService.delete(id);
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
		int effectRow = bannerService.deleteBatch(ids);
		if (effectRow > 0) {
			return success();
		} 
		return error();
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Pager<Banner> pager){
		pager.putData("pageSize", 10000000);
		this.index(pager);
		List<Banner> list = pager.getResult();
		
		//标题(列名|列宽|单元格式)
		LinkedHashMap<String, String> headInfoList = new LinkedHashMap<String, String>();
		headInfoList.put("title", "标题");
		headInfoList.put("image_url", "图片地址");
		headInfoList.put("jump_url", "跳转地址");
		headInfoList.put("trade_mode", "交易模式");
		headInfoList.put("status", "状态");
		headInfoList.put("sort", "排序");
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
		mv.addObject("fileName", "轮播图片");
		return mv;
    }
	
}

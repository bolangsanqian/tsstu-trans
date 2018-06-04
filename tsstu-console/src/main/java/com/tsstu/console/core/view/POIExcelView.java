package com.tsstu.console.core.view;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tsstu.common.util.DateUtil;
import com.tsstu.console.core.exception.SystemException;
import com.tsstu.console.util.POIUtil;

@SuppressWarnings("all")
public class POIExcelView extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Object fileName = model.get("fileName");
		if (null == fileName) {
			fileName = DateUtil.format(new Date(), "yyyyMMddhhmmss");
		} else {
			fileName = fileName + "_" + DateUtil.format(new Date(), "yyyyMMddhhmmss");
		}
		this.compatibleFileName(request, response, fileName.toString());
		LinkedHashMap<String, String> headInfoList = (LinkedHashMap<String, String>)model.get("headInfoList");
		List<Map<String, Object>> dataList = (List<Map<String, Object>>)model.get("dataList");
		Map<String, Map<String, Object>> dictMaps = (HashMap<String, Map<String, Object>>)model.get("dictMaps");
		POIUtil.exportExcel2FilePath((HSSFWorkbook)workbook, fileName.toString(), "", headInfoList, dataList, dictMaps);
		
	}
	
	// 判断浏览器类型，firefox浏览器做特殊处理，否则下载文件名乱码
	public void compatibleFileName(HttpServletRequest request, HttpServletResponse response, String excelname) {
		try {
			String agent = request.getHeader("USER-AGENT").toLowerCase();
			response.setContentType("application/vnd.ms-excel");
			String fileName = excelname;
			String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			if (agent.contains("firefox")) {
				response.setCharacterEncoding("utf-8");
				response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
			} else {
				response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
			}
		} catch (Exception e) {
			throw new SystemException();
		}
	}
	
	/**  
     * 设置下载文件中文件的名称  
     *   
     * @param filename  
     * @param request  
     * @return  
     */    
    public String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
          newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
          return MimeUtility.encodeText(filename, "UTF-8", "B");    
      
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    }   

	

}

package com.tsstu.console.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tsstu.common.util.DateUtil;
import com.tsstu.common.constants.Constants.ExportConstants;  
  
/** 
 * poi 导出excel 工具类 
 */  
public class POIUtil {  
  
    /** 
     * 1.创建 workbook 
     * @return 
     */  
    public HSSFWorkbook getHSSFWorkbook(){  
        return new HSSFWorkbook();  
    }  
  
    /** 
     * 2.创建 sheet 
     * @param hssfWorkbook 
     * @param sheetName sheet 名称 
     * @return 
     */  
    public HSSFSheet getHSSFSheet(HSSFWorkbook hssfWorkbook, String sheetName){  
        return hssfWorkbook.createSheet(sheetName);  
    }  
  
    /** 
     * 3.写入表头信息 
     * @param hssfWorkbook 
     * @param hssfSheet 
     * @param headInfoList List<Map<String, Object>> 
     *              key: title         列标题 
     *                   columnWidth   列宽 
     *                   dataKey       列对应的 dataList item key 
     */  
    public void writeHeader(HSSFWorkbook hssfWorkbook,HSSFSheet hssfSheet ,LinkedHashMap<String, String> headInfoList){  
        HSSFCellStyle cs = hssfWorkbook.createCellStyle();  
        HSSFFont font = hssfWorkbook.createFont();  
        font.setFontHeightInPoints((short)12);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        cs.setFont(font);  
        cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
  
        HSSFRow r = hssfSheet.createRow(0);  
        r.setHeight((short) 380);  
        HSSFCell c = null;  
        String headInfo = null;  
        //处理excel表头  
        Set<String> keys = headInfoList.keySet();
        int i = 0;
        for (String dataKey : keys) {
        	 headInfo = headInfoList.get(dataKey);  
             String [] headInfoArr = headInfo.split("\\|");
             String title = headInfoArr[0];
             int width = 30;
             if (headInfoArr.length > 1 && null != headInfoArr[1] && !"".equals(headInfoArr[1])) {
             	width = Integer.valueOf(headInfoArr[1]);
             }
             hssfSheet.setColumnWidth(i, (short)((width * 8) / ((double) 1 / 20)));  
             c = r.createCell(i);  
             c.setCellValue(title);  
             c.setCellStyle(cs);  
             i ++;
        }
    }  
  
    /** 
     * 4.写入内容部分 
     * @param hssfWorkbook 
     * @param hssfSheet 
     * @param startIndex 从1开始，多次调用需要加上前一次的dataList.size() 
     * @param headInfoList List<Map<String, Object>> 
     *              key: title         列标题 
     *                   columnWidth   列宽 
     *                   dataKey       列对应的 dataList item key 
     * @param dataList 
     */  
    public void writeContent(HSSFWorkbook hssfWorkbook,HSSFSheet hssfSheet ,int startIndex,  
    		LinkedHashMap<String, String> headInfoList, List<Map<String, Object>> dataList, Map<String, Map<String, Object>> dictMaps){  
        String headInfo = null;  
        HSSFRow r = null;  
        HSSFCell c = null;  
        //处理数据  
        Map<String, Object> dataItem = null;  
        Object v = null;
        Object f = null;
        for (int i=0, rownum = startIndex, len = (startIndex + dataList.size()); rownum < len; i++,rownum++){  
            r = hssfSheet.createRow(rownum);  
            r.setHeightInPoints(16);  
            dataItem = dataList.get(i);  
            int j = 0;
            Set<String> keys = headInfoList.keySet();
            for (String dataKey : keys) {
                 c = r.createCell(j);
                 v = dataItem.get(dataKey);
                 
                 //单元格格式处理，默认为字符串
                 HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
                 HSSFDataFormat format = hssfWorkbook.createDataFormat();
                 headInfo = headInfoList.get(dataKey);
                 String [] headInfoArr = headInfo.split("\\|");
                 f = "@";
                 if (headInfoArr.length > 2) {
                	f =  headInfoArr[2];
                 } 
                 cellStyle.setDataFormat(format.getFormat(f.toString()));
                 c.setCellStyle(cellStyle);
                 
                 //数据字典
                 if (null != dictMaps && dictMaps.size() > 0) {
 					Map<String, Object> dicMap = dictMaps.get(dataKey);
 					if (null != dicMap) {
 						v = dicMap.get(v + "");
 					}
 				}
                 
                 if (v instanceof String) {  
                     c.setCellValue((String)v);  
                 }else if (v instanceof Boolean) {  
                     c.setCellValue((Boolean)v);  
                 }else if (v instanceof Date) {
                 	if ("@".equals(f.toString())) {
                 		f = ExportConstants.DEFAUT_DATE_FORMAT;
                 	}
                 	v = DateUtil.format((Date)v, f.toString().toLowerCase());
                     c.setCellValue(v.toString()); 
                 }else if (v instanceof Calendar) {  
                     c.setCellValue((Calendar)v);  
                 }else if (v instanceof Double) {  
                     c.setCellValue((Double)v);  
                 }else if (v instanceof Integer  
                         || v instanceof Long  
                         || v instanceof Short  
                         || v instanceof Float) {  
                     c.setCellValue(Double.parseDouble(v.toString()));  
                 }else if (v instanceof HSSFRichTextString) {  
                     c.setCellValue((HSSFRichTextString)v);  
                 }else {  
                     c.setCellValue(v.toString());  
                 }  
                 j ++;
			}
        }  
    }  
  
    public void write2FilePath(HSSFWorkbook hssfWorkbook, String filePath) throws IOException{  
        FileOutputStream fileOut = null;  
        try{  
            fileOut = new FileOutputStream(filePath);  
            hssfWorkbook.write(fileOut);  
        }finally{  
            if(fileOut != null){  
                fileOut.close();  
            }  
        }  
    }  
  
  
    /** 
     * 导出excel 
     * code example: 
         List<Map<String, Object>> headInfoList = new ArrayList<Map<String,Object>>(); 
         Map<String, Object> itemMap = new HashMap<String, Object>(); 
         itemMap.put("title", "序号1"); 
         itemMap.put("columnWidth", 25); 
         itemMap.put("dataKey", "XH1"); 
         headInfoList.add(itemMap); 
 
         itemMap = new HashMap<String, Object>(); 
         itemMap.put("title", "序号2"); 
         itemMap.put("columnWidth", 50); 
         itemMap.put("dataKey", "XH2"); 
         headInfoList.add(itemMap); 
 
         itemMap = new HashMap<String, Object>(); 
         itemMap.put("title", "序号3"); 
         itemMap.put("columnWidth", 25); 
         itemMap.put("dataKey", "XH3"); 
         headInfoList.add(itemMap); 
 
         List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>(); 
         Map<String, Object> dataItem = null; 
         for(int i=0; i < 100; i++){ 
         dataItem = new HashMap<String, Object>(); 
         dataItem.put("XH1", "data" + i); 
         dataItem.put("XH2", 88888888f); 
         dataItem.put("XH3", "脉兜V5.."); 
         dataList.add(dataItem); 
         } 
         POIUtil.exportExcel2FilePath("test sheet 1","F:\\temp\\customer2.xls", headInfoList, dataList); 
 
     * @param sheetName   sheet名称 
     * @param filePath   文件存储路径， 如：f:/a.xls 
     * @param headInfoList List<Map<String, Object>> 
     *                           key: title         列标题 
     *                                columnWidth   列宽 
     *                                dataKey       列对应的 dataList item key 
     * @param dataList  List<Map<String, Object>> 导出的数据 
     * @throws java.io.IOException 
     * 
     */  
    public static void exportExcel2FilePath(String sheetName, String filePath,  
    							   LinkedHashMap<String, String> headInfoList,  
                                   List<Map<String, Object>> dataList) throws IOException {  
        POIUtil poiUtil = new POIUtil();  
        HSSFWorkbook hssfWorkbook = poiUtil.getHSSFWorkbook();  
        POIUtil.exportExcel2FilePath(hssfWorkbook, sheetName, filePath, headInfoList, dataList, null);
    }  
    
    
	public static void exportExcel2FilePath(HSSFWorkbook hssfWorkbook, String sheetName, String filePath, LinkedHashMap<String, String> headInfoList,
			List<Map<String, Object>> dataList, Map<String, Map<String, Object>> dictMaps) throws IOException {
		POIUtil poiUtil = new POIUtil();
		// 2.创建 Sheet
		HSSFSheet hssfSheet = poiUtil.getHSSFSheet(hssfWorkbook, sheetName);
		// 3.写入 head
		poiUtil.writeHeader(hssfWorkbook, hssfSheet, headInfoList);
		// 4.写入内容
		poiUtil.writeContent(hssfWorkbook, hssfSheet, 1, headInfoList, dataList, dictMaps);
	}
    
  
}  
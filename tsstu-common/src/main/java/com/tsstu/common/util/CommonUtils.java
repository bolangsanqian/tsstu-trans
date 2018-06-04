package com.tsstu.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonUtils {

	/**
	 * 判断字符串是否为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (null == str || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符串对象是否为null或空字符串
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(Object str) {
		if (null == str || "".equals(str.toString())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否为null或大小为0
	 * @param objs
	 * @return
	 */
	public static boolean isNullOrEmpty(Object[] objs) {
		if (null == objs || objs.length <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断List集合是否为null或大小为0
	 * @param objs
	 * @return
	 */
	public static boolean isNullOrEmpty(List<?> list) {
		if (null == list || list.size() <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断数组是否为null或大小为0
	 * @param objs
	 * @return
	 */
	public static boolean isNullOrEmpty(int[] objs) {
		if (null == objs || objs.length <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断Integer是否null或0
	 * @param str
	 * @return
	 */
	public static boolean isNullOrZero(Integer num) {
		if (null == num || num.intValue() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 生成唯一订单编号
	 * @return
	 */
	public static synchronized String generateOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
}

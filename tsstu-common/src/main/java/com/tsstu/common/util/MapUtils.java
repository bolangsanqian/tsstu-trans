package com.tsstu.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map工具类
 *
 * @author jqlin
 */
public class MapUtils {

	/**
	 * 从map集合中获取属性值
	 * 
	 * @param <E>
	 * @param map
	 *            map集合
	 * @param key
	 *            键对
	 * @param defaultValue
	 *            默认值
	 * @return
	 * @author jiqinlin
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static <E> E get(Map map, Object key, E defaultValue) {
		Object o = map.get(key);
		if (o == null)
			return defaultValue;
		return (E) o;
	}

	/**
	 * Map集合对象转化成 JavaBean集合对象
	 * 
	 * @param javaBean
	 *            JavaBean实例对象
	 * @param mapList
	 *            Map数据集对象
	 * @return
	 * @author jqlin
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <T> List<T> map2Bean(T javaBean, List<Map> mapList) {
		if (mapList == null || mapList.isEmpty()) {
			return null;
		}
		List<T> objectList = new ArrayList<T>();

		T object = null;
		for (Map map : mapList) {
			if (map != null) {
				object = map2Bean(javaBean, map);
				objectList.add(object);
			}
		}

		return objectList;

	}

	/**
	 * Map对象转化成 JavaBean对象
	 * 
	 * @param javaBean
	 *            JavaBean实例对象
	 * @param map
	 *            Map对象
	 * @return
	 * @author jqlin
	 */
	@SuppressWarnings("all")
	public static <T> T map2Bean(T javaBean, Map map) {
		try {
			// 获取javaBean属性
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
			// 创建 JavaBean 对象
			Object obj = javaBean.getClass().newInstance();

			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				String propertyName = null; // javaBean属性名
				Object propertyValue = null; // javaBean属性值
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (map.containsKey(propertyName)) {
						propertyValue = map.get(propertyName);
						pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
					}
				}
				return (T) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * JavaBean对象转化成Map对象
	 * 
	 * @param javaBean
	 * @return
	 * @author jqlin
	 */
	public static Map<String, Object> bean2Map(Object javaBean, Map<String, Map<String, Object>> dicMaps) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 获取javaBean属性
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				String propertyName = null; // javaBean属性名
				Object propertyValue = null; // javaBean属性值
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (!propertyName.equals("class")) {
						Method readMethod = pd.getReadMethod();
						propertyValue = readMethod.invoke(javaBean, new Object[0]);
						if (propertyValue instanceof Date) {
							//propertyValue = DateUtils.format((Date)propertyValue);
						}
						if (null != dicMaps && dicMaps.size() > 0) {
							Map<String, Object> dicMap = dicMaps.get(propertyName);
							if (null != dicMap) {
								propertyValue = dicMap.get(propertyValue + "");
							}
						}
						map.put(propertyName, propertyValue);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	
	public static List<Map<String, Object>> bean2MapList(@SuppressWarnings("rawtypes") List list, Map<String, Map<String, Object>> dicMaps) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (Object object : list) {
			Map<String, Object> map = MapUtils.bean2Map(object, dicMaps);
			listMap.add(map);
		}
		return listMap;
	}

}

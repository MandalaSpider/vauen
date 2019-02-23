package com.vauen.yann.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import net.sf.json.JSONObject;

/**
* @ClassName: ReflectUtils  
* @Description: TODO(反射工具类)  
* @author 作者：Mike  
* @date 2019年1月31日
 */
public class ReflectUtils {

	/**
	* @Title: objectToMap  
	* @Description: TODO(反射---将object转换为TreeMap<String, Object>)  
	* @author 作者：Mike 
	* @date 2019年1月31日 
	* @param @param obj
	* @param @return
	* @param @throws IllegalAccessException    参数  
	* @return TreeMap<String,Object>    返回类型  
	* @throws
	 */
	public static TreeMap<String, Object> objectToMap(Object obj) throws IllegalAccessException {
		TreeMap<String, Object> map = new TreeMap<>();
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);
			map.put(fieldName, value);
		}
		return map;
	}
	/**
	* @Title: objectToJson  
	* @Description: TODO(反射---object转换为JSONObject)  
	* @author 作者：Mike 
	* @date 2019年1月31日 
	* @param @param obj
	* @param @return
	* @param @throws IllegalAccessException    参数  
	* @return JSONObject    返回类型  
	* @throws
	 */
	public static JSONObject objectToJson(Object obj) throws IllegalAccessException {
		JSONObject result = new JSONObject();
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value = field.get(obj);
			result.put(fieldName, value);
		}
		return result;
	}
	/**
	* @Title: uppercase  
	* @Description: TODO(转换为大写)  
	* @author 作者：Mike 
	* @date 2019年1月31日 
	* @param @param str
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String uppercase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
	/**
	* @Title: getValueForMethod  
	* @Description: TODO(反射获取get方法)  
	* @author 作者：Mike 
	* @date 2019年1月31日 
	* @param @param entity
	* @param @param property
	* @param @return
	* @param @throws NoSuchMethodException
	* @param @throws SecurityException
	* @param @throws IllegalAccessException
	* @param @throws IllegalArgumentException
	* @param @throws InvocationTargetException    参数  
	* @return Object    返回类型  
	* @throws
	 */
	public static Object getValueForMethod(Object entity, String property) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object obj = null;
		if (null != entity) {
			if (StringUtils.isNotEmpty(property)) {

				String[] propertys = StringUtils.split(property, ".");
				for (String pro : propertys) {
					if (null != entity) {
						Method method = entity.getClass().getDeclaredMethod("get" + StringUtils.capitalize(pro));
						obj = method.invoke(entity);
					} else {
						obj = "";
					}
				}
			}
		}
		return obj;
	}
}

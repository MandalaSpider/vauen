package com.vauen.yann.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;


/**
* @ClassName: ObjectUtils  
* @Description: TODO(工具类)  
* @author 作者：Mike  
* @date 2019年2月18日
 */
public class ObjectUtils {
	
	public static void main(String[] args) {
		System.out.println(orderNoGeneration());
		System.out.println(getSalt(10));
		System.out.println(generateCode());
		System.out.println(unicodeToString("\\ud83d\\ude00\\ud83d\\ude00\\u597d\\u7684"));
	}
	
	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",  
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z" };  
	/**
	* @Title: generateShortUuid  
	* @Description: TODO(生成8位不重复的邀请码)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String generateInvitecode() {  
	    StringBuffer shortBuffer = new StringBuffer();  
	    String uuid = UUID.randomUUID().toString().replace("-", "");  
	    for (int i = 0; i < 8; i++) {  
	        String str = uuid.substring(i * 4, i * 4 + 4);  
	        int x = Integer.parseInt(str, 16);  
	        shortBuffer.append(chars[x % 0x3E]);  
	    }  
	    return shortBuffer.toString();  
	  
	}

	/**
	* @Title: orderNoGeneration  
	* @Description: TODO(生成订单号---yyyyMMddHHmmssSS)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public synchronized static String orderNoGeneration() {
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
		final AtomicInteger count = new AtomicInteger(0);
		LocalDateTime localDateTime = LocalDateTime.now();
		if (count.getAndIncrement() == 10000) {
			count.set(0);
		}
		String orderNo=localDateTime.format(FORMATTER) + String.format("%04d", count.get());
		return orderNo;
	}
	/**
	* @Title: getUUID  
	* @Description: TODO(生成UUID)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	/**
	* @Title: paramSort  
	* @Description: TODO(将map对象参数排序)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param params
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String paramSort(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}
		return prestr;
	}
	/**
	* @Title: isAjaxRequest  
	* @Description: TODO(判断是否为Ajax请求)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param request
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	* @Title: isAjaxRequest  
	* @Description: TODO(判断是否为Ajax请求)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param request
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isAjaxRequest(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestType = httpRequest.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	* @Title: getSalt  
	* @Description: TODO(生成盐值)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param length
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String getSalt(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	* @Title: generateCode  
	* @Description: TODO(生成验证码)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String generateCode() {
		return new Integer((int) ((Math.random() * 9 + 1) * 100000)).toString();
	}
	/**
	* @Title: unicode2String  
	* @Description: TODO(unicode 转字符串)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param unicode
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String unicodeToString(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			string.append((char) data);
		}
		return string.toString();
	}
	/**
	* @Title: isEmpty  
	* @Description: TODO(判断对象是否为空)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param obj
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null || obj.equals("undefined")) {
			return true;
		} else if (obj instanceof String && (obj.equals(""))) {
			return true;
		} else if (obj instanceof Collection && ((Collection<?>) obj).isEmpty() && ((Collection<?>) obj).size() == 0) {
			return true;
		} else if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) {
			return true;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		return false;
	}
	/**
	* @Title: getSize  
	* @Description: TODO(获取对象大小)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param obj
	* @param @return    参数  
	* @return int    返回类型  
	* @throws
	 */
	public static int getSize(Object obj) {
		int size = 0;
		if (obj instanceof Collection) {
			size = ((Collection<?>) obj).size();
		} else if (obj instanceof Map) {
			size = ((Map<?, ?>) obj).size();
		} else if (obj instanceof Object[]) {
			size = ((Object[]) obj).length;
		}
		return size;
	}
	/**
	* @Title: isNullOrUndefined  
	* @Description: TODO(检验对象是否为undefined 或者null)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param obj
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isNullOrUndefined(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj instanceof String && (obj.equals("undefined"))) {
			return true;
		} else {
			return false;
		}
	}
	/**
	* @Title: isChinese  
	* @Description: TODO(校验对象是否是中文)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param object
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public static Boolean isChinese(Object object) {
		Boolean tag = true;
		if (ObjectUtils.isNotEmpty(object)) {
			char[] ch = object.toString().toCharArray();
			for (char c : ch) {
				if (!check(c)) {
					tag = false;
				}
			}
		} else {
			tag = false;
		}
		return tag;
	}
	/**
	* @Title: isNotEmpty  
	* @Description: TODO(判断对象是否不为空)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param obj
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	public static boolean check(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
	/**
	* @Title: isEmail  
	* @Description: TODO(判断是否是邮箱)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param email
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isEmail(String email) {
		Boolean tag = false;
		if (isNotEmpty(email)) {
			String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern p = Pattern.compile(str);
			Matcher m = p.matcher(email);

			tag = m.matches();
		}
		return tag;
	}
	/**
	* @Title: URLDecoder  
	* @Description: TODO(URL---解码)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param str
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String URLDecoder(String str) {
		try {
			if (ObjectUtils.isNotEmpty(str)) {
				str = URLDecoder.decode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	* @Title: URLEncode  
	* @Description: TODO(URL---编码)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param str
	* @param @return    参数  
	* @return String    返回类型  
	* @throws
	 */
	public static String URLEncode(String str) {
		try {
			if (ObjectUtils.isNotEmpty(str)) {
				str = URLEncoder.encode(str, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	 /**
	 * @Title: gtzero  
	 * @Description: TODO(判断BigDecimal是否大于0)  
	 * @author 作者：Mike 
	 * @date 2019年2月18日 
	 * @param @param v
	 * @param @return    参数  
	 * @return Boolean    返回类型  
	 * @throws
	  */
	public static Boolean gtzero(BigDecimal v) {
		Boolean tag = false;
		if (ObjectUtils.isNotEmpty(v)) {
			if (v.doubleValue() > 0) {
				tag = true;
			}
		}
		return tag;
	}
	/**
	* @Title: contains  
	* @Description: TODO(判断某个字符串是否存在于数组中)  
	* @author 作者：Mike 
	* @date 2019年2月18日 
	* @param @param stringArray
	* @param @param source
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean contains(String[] stringArray, String source) {
		List<String> tempList = Arrays.asList(stringArray);
		if (tempList.contains(source)) {
			return true;
		} else {
			return false;
		}
	}
	
}

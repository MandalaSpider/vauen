package com.vauen.yann.third.ocr.discern;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.springframework.web.multipart.MultipartFile;

import com.vauen.yann.common.result.ResultJsonUtil;
import com.vauen.yann.utils.ObjectUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StreamUtil {

	/**
	* @Title: dataCheck  
	* @Description: TODO(校验图片是否为空)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param file
	* @param @param type
	* @param @return
	* @param @throws Exception    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean dataCheck(MultipartFile file, String type) throws Exception {
		boolean flag = true;
		if (file.equals("") || file.getSize() <= 0) {
			flag = false;
		}
		if (ObjectUtils.isEmpty(type)) {
			flag = false;
		}
		return flag;
	}
	/**
	* @Title: inputStreamToFile  
	* @Description: TODO(读取文件)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param ins
	* @param @param f
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws
	 */
	public static void inputStreamToFile(InputStream ins, File f) throws Exception {
		try {
			OutputStream os = new FileOutputStream(f);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: TODO --- 根据类型1身份证 2银行卡和返回结果集 将string转换为json格式返回
	 * @author 作者：Mike
	 * @date 2018年10月24日
	 */
	public static ResultJsonUtil strToJson(String type, String result) {
		ResultJsonUtil j = new ResultJsonUtil();
		JSONObject jsonObject = JSONObject.fromObject(result);
		if (jsonObject.containsKey("error_message")) {
			return j.setError("识别失败,请稍后重试");
		}
		JSONArray array = new JSONArray();
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (Integer.parseInt(type) == 1) {
			if (jsonObject.containsKey("cards")) {
				array = jsonObject.getJSONArray("cards");
				if (ObjectUtils.isNotEmpty(array)) {
					if (array.size() > 0) {
						map = jsonToMap(array);
						j.setMsg("身份证识别成功");
					} else {
						j.setError("身份证识别失败");
					}
				} else {
					j.setError("身份证识别失败---请确认您的证件");
				}
			}
		}
		if(Integer.parseInt(type) == 2){
			if (jsonObject.containsKey("bank_cards")) {
				array = jsonObject.getJSONArray("bank_cards");
				if (ObjectUtils.isNotEmpty(array)) {
					if (array.size() > 0) {
						map = jsonToMap(array);
						j.setMsg("银行卡识别成功");
					} else {
						j.setError("银行卡识别失败");
					}
				} else {
					j.setError("银行卡识别失败---请确认您的卡片");
				}
			}
		}
		j.setData(map);
		return j;
	}
	
	/**
	 * @Description: TODO --- json数组转map
	 * @author 作者：Mike
	 * @date 2018年10月24日
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> jsonToMap(JSONArray array) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (array.size() > 0) {
			JSONObject obj = JSONObject.fromObject(array.get(0));
			Iterator<String> iterator = obj.keys();
			String key = null;
			String value = null;
			while (iterator.hasNext()) {
				key = iterator.next();
				value = obj.getString(key);
				map.put(key, value);
			}
		}
		return map;
	}
	
	
}

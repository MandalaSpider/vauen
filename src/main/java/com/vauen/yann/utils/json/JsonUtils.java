package com.vauen.yann.utils.json;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	// 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
    * @Title: objectToJson  
    * @Description: TODO(将对象转换成json字符串。)  
    * @author 作者：Mike 
    * @date 2019年2月22日 
    * @param @param data
    * @param @return    参数  
    * @return String    返回类型  
    * @throws
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
    * @Title: jsonToPojo  
    * @Description: TODO(将json结果集转化为对象)  
    * @author 作者：Mike 
    * @date 2019年2月22日 
    * @param @param jsonData
    * @param @param beanType
    * @param @return    参数  
    * @return T    返回类型  
    * @throws
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    /**
    * @Title: jsonToList  
    * @Description: TODO(将json数据转换成pojo对象list)  
    * @author 作者：Mike 
    * @date 2019年2月22日 
    * @param @param jsonData
    * @param @param beanType
    * @param @return    参数  
    * @return List<T>    返回类型  
    * @throws
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
}

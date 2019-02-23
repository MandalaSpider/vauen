package com.vauen.yann.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.vauen.yann.model.JSONResult;

@ControllerAdvice
public class UnifiedExceptionHandler {

	public static final String IMOOC_ERROR_VIEW = "error";

	/**
	* @Title: errorHandler  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param reqest
	* @param @param response
	* @param @param e
	* @param @return
	* @param @throws Exception    参数  
	* @return Object    返回类型  
	* @throws
	 */
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
		e.printStackTrace();
		if (isAjax(reqest)) {
			return JSONResult.errorException(e.getMessage());
		} else {
			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", e);
			mav.addObject("url", reqest.getRequestURL());
			mav.setViewName(IMOOC_ERROR_VIEW);
			return mav;
		}
	}
	/**
	* @Title: isAjax  
	* @Description: TODO(判断是否是ajax请求)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param httpRequest
	* @param @return    参数  
	* @return boolean    返回类型  
	* @throws
	 */
	public static boolean isAjax(HttpServletRequest httpRequest) {
		return (httpRequest.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
	}
}

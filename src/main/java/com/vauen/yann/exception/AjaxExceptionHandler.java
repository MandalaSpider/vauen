package com.vauen.yann.exception;

import javax.servlet.http.HttpServletRequest;
import com.vauen.yann.model.JSONResult;

public class AjaxExceptionHandler {

//	@ExceptionHandler(value = Exception.class)
	public JSONResult defaultErrorHandler(HttpServletRequest req, 
			Exception e) throws Exception {
		e.printStackTrace();
		return JSONResult.errorException(e.getMessage());
	}
}

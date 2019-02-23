package com.vauen.yann.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.vauen.yann.common.result.ResultJsonUtil;
import com.vauen.yann.model.TSUserInfo;
import com.vauen.yann.service.ITSUserInfoService;

//@RestController		// @RestController = @Controller + @ResponseBody
@Controller
@RequestMapping("/api/user")
public class UserInfoController {

	@Autowired
	private ITSUserInfoService userInfoService;
	
	/**
	* @Title: userRegister  
	* @Description: TODO(用户注册---http://localhost:8080/api/user/userRegister)  
	* @author 作者：Mike 
	* @date 2019年2月22日 
	* @param @param userInfo
	* @param @return
	* @param @throws Exception    参数  
	* @return ResultJsonUtil    返回类型  
	* @throws
	 */
	@RequestMapping("/userRegister")
	@ResponseBody
	public ResultJsonUtil userRegister(TSUserInfo userInfo) throws Exception {
		ResultJsonUtil result = new ResultJsonUtil();
		try {
			result = userInfoService.userRegister(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("网络异常,请联系管理员");
		}
		return result;
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public ResultJsonUtil getUserInfo(String mobile) throws Exception {
		ResultJsonUtil result = new ResultJsonUtil();
		try {
			result = userInfoService.getUserInfo(mobile);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("网络异常,请联系管理员");
		}
		return result;
	}
}

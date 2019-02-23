package com.vauen.yann.service;

import com.vauen.yann.common.result.ResultJsonUtil;
import com.vauen.yann.model.TSUserInfo;

public interface ITSUserInfoService {

	/**用户注册*/
	ResultJsonUtil userRegister(TSUserInfo userInfo);
	/**获取用户信息*/
	ResultJsonUtil getUserInfo(String mobile);

}

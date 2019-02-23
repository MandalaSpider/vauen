package com.vauen.yann.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.vauen.yann.common.result.ResultJsonUtil;
import com.vauen.yann.mapper.TSUserInfoMapper;
import com.vauen.yann.model.TSUserInfo;
import com.vauen.yann.service.ITSUserInfoService;
import com.vauen.yann.utils.ObjectUtils;
import com.vauen.yann.utils.date.DateUtil;
import com.vauen.yann.utils.secure.PassWordUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class TSUserInfoServiceImpl implements ITSUserInfoService {

	@Autowired
	private TSUserInfoMapper userInfoMapper;

	/**
	 * 用户注册
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultJsonUtil userRegister(TSUserInfo userInfo){
		ResultJsonUtil result = new ResultJsonUtil();
		if (ObjectUtils.isEmpty(userInfo)) {
			result.setError("注册信息为空");
		}
		String uuid = ObjectUtils.getUUID();
		userInfo.setId(uuid);
		userInfo.setToken(uuid);
		userInfo.setInvitecode(ObjectUtils.generateInvitecode());//邀请码
		userInfo.setPassword(PassWordUtil.encrypt(userInfo.getPassword()));
		userInfo.setCreatetime(DateUtil.getDateTime());
		int i = userInfoMapper.insert(userInfo);
		if (i == 1) {
			result.setMsg("恭喜您已注册成功！您的注册账号为：" + userInfo.getUsername() + "。请牢记您的账号");
		} else {
			result.setError("注册异常,请联系管理者---18300665808");
		}
		result.setData(userInfo);
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultJsonUtil getUserInfo(String mobile) {
		ResultJsonUtil result = new ResultJsonUtil();
		if (ObjectUtils.isEmpty(mobile)) {
			result.setError("手机号不能为空");
		}
		Example example = new Example(TSUserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo(mobile);
        example.and(criteria);
		List<TSUserInfo> list = userInfoMapper.selectByExample(mobile);
		if (list.size() >= 1) {
			result.setData(list.get(0));
		}else {
			result.setError("该手机尚未注册");
		}
		return result;
	}
	
}

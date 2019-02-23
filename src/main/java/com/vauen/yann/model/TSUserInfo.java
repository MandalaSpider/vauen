package com.vauen.yann.model;

import javax.persistence.Basic;
import javax.persistence.Table;
import com.vauen.yann.utils.secure.MD5Util;
import com.vauen.yann.utils.secure.PassWordUtil;

@Table(name = "t_s_userinfo")
public class TSUserInfo {
	/**
	 * 用户ID
	 */
	private String id;

	/**
	 * 用户token
	 */
	private String token;

	/**
	 * 用户邀请码
	 */
	private String invitecode;

	/**
	 * 角色等级
	 */
	private String rolegrade;

	/**
	 * 真实姓名
	 */
	private String realname;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 用户登陆账号
	 */
	private String username;

	/**
	 * 用户登陆密码
	 */
	private String password;

	/**
	 * 密码盐值
	 */
	private String salt = MD5Util.MD5(PassWordUtil.PASSWORD_SALT).substring(10, 26);

	/**
	 * 登陆手机
	 */
	private String mobile;

	/**
	 * 登陆方式
	 */
	private String logintype;

	/**
	 * 是否实名
	 */
	private Integer isrealname;

	/**
	 * 微信openid
	 */
	private String wechatopenid;

	/**
	 * 微信唯一标示
	 */
	private String wechatunionid;

	/**
	 * 微信绑定手机
	 */
	private String wechatmobile;

	/**
	 * 用户头像
	 */
	private String userphoto;

	/**
	 * 省
	 */
	private String province;

	/**
	 * 市
	 */
	private String city;

	/**
	 * 区县
	 */
	private String district;

	/**
	 * 是否启用：1 启用；0 禁用
	 */
	private Integer isenable;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别：1 男；2 女
	 */
	private Integer sex;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 最后登录时间
	 */
	private String lastlogintime;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建时间
	 */
	private String createtime;

	/**
	 * 更新时间
	 */
	private String updatetime;

	/**
	 * 用户ID
	 * 
	 * @return id 用户ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 用户ID
	 * 
	 * @param id 用户ID
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 用户token
	 * 
	 * @return token 用户token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 用户token
	 * 
	 * @param token 用户token
	 */
	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	/**
	 * 用户邀请码
	 * 
	 * @return invitecode 用户邀请码
	 */
	public String getInvitecode() {
		return invitecode;
	}

	/**
	 * 用户邀请码
	 * 
	 * @param invitecode 用户邀请码
	 */
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode == null ? null : invitecode.trim();
	}

	/**
	 * 角色等级
	 * 
	 * @return rolegrade 角色等级
	 */
	public String getRolegrade() {
		return rolegrade;
	}

	/**
	 * 角色等级
	 * 
	 * @param rolegrade 角色等级
	 */
	public void setRolegrade(String rolegrade) {
		this.rolegrade = rolegrade == null ? null : rolegrade.trim();
	}

	/**
	 * 真实姓名
	 * 
	 * @return realname 真实姓名
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * 真实姓名
	 * 
	 * @param realname 真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	/**
	 * 昵称
	 * 
	 * @return nickname 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称
	 * 
	 * @param nickname 昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	/**
	 * 用户登陆账号
	 * 
	 * @return username 用户登陆账号
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 用户登陆账号
	 * 
	 * @param username 用户登陆账号
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * 用户登陆密码
	 * 
	 * @return password 用户登陆密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 用户登陆密码
	 * 
	 * @param password 用户登陆密码
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 密码盐值
	 * 
	 * @return salt 密码盐值
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 密码盐值
	 * 
	 * @param salt 密码盐值
	 */
	public void setSalt(String salt) {
		this.salt = salt == null ? null : salt.trim();
	}

	/**
	 * 登陆手机
	 * 
	 * @return mobile 登陆手机
	 */
	@Basic
	public String getMobile() {
		return mobile;
	}

	/**
	 * 登陆手机
	 * 
	 * @param mobile 登陆手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * 登陆方式
	 * 
	 * @return logintype 登陆方式
	 */
	public String getLogintype() {
		return logintype;
	}

	/**
	 * 登陆方式
	 * 
	 * @param logintype 登陆方式
	 */
	public void setLogintype(String logintype) {
		this.logintype = logintype == null ? null : logintype.trim();
	}

	/**
	 * 是否实名
	 * 
	 * @return isrealname 是否实名
	 */
	public Integer getIsrealname() {
		return isrealname;
	}

	/**
	 * 是否实名
	 * 
	 * @param isrealname 是否实名
	 */
	public void setIsrealname(Integer isrealname) {
		this.isrealname = isrealname;
	}

	/**
	 * 微信openid
	 * 
	 * @return wechatopenid 微信openid
	 */
	public String getWechatopenid() {
		return wechatopenid;
	}

	/**
	 * 微信openid
	 * 
	 * @param wechatopenid 微信openid
	 */
	public void setWechatopenid(String wechatopenid) {
		this.wechatopenid = wechatopenid == null ? null : wechatopenid.trim();
	}

	/**
	 * 微信唯一标示
	 * 
	 * @return wechatunionid 微信唯一标示
	 */
	public String getWechatunionid() {
		return wechatunionid;
	}

	/**
	 * 微信唯一标示
	 * 
	 * @param wechatunionid 微信唯一标示
	 */
	public void setWechatunionid(String wechatunionid) {
		this.wechatunionid = wechatunionid == null ? null : wechatunionid.trim();
	}

	/**
	 * 微信绑定手机
	 * 
	 * @return wechatmobile 微信绑定手机
	 */
	public String getWechatmobile() {
		return wechatmobile;
	}

	/**
	 * 微信绑定手机
	 * 
	 * @param wechatmobile 微信绑定手机
	 */
	public void setWechatmobile(String wechatmobile) {
		this.wechatmobile = wechatmobile == null ? null : wechatmobile.trim();
	}

	/**
	 * 用户头像
	 * 
	 * @return userphoto 用户头像
	 */
	public String getUserphoto() {
		return userphoto;
	}

	/**
	 * 用户头像
	 * 
	 * @param userphoto 用户头像
	 */
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto == null ? null : userphoto.trim();
	}

	/**
	 * 省
	 * 
	 * @return province 省
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 省
	 * 
	 * @param province 省
	 */
	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	/**
	 * 市
	 * 
	 * @return city 市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 市
	 * 
	 * @param city 市
	 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/**
	 * 区县
	 * 
	 * @return district 区县
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * 区县
	 * 
	 * @param district 区县
	 */
	public void setDistrict(String district) {
		this.district = district == null ? null : district.trim();
	}

	/**
	 * 是否启用：1 启用；0 禁用
	 * 
	 * @return isenable 是否启用：1 启用；0 禁用
	 */
	public Integer getIsenable() {
		return isenable;
	}

	/**
	 * 是否启用：1 启用；0 禁用
	 * 
	 * @param isenable 是否启用：1 启用；0 禁用
	 */
	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}

	/**
	 * 年龄
	 * 
	 * @return age 年龄
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 年龄
	 * 
	 * @param age 年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 性别：1 男；2 女
	 * 
	 * @return sex 性别：1 男；2 女
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 性别：1 男；2 女
	 * 
	 * @param sex 性别：1 男；2 女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 邮箱
	 * 
	 * @return email 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * 
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 最后登录时间
	 * 
	 * @return lastlogintime 最后登录时间
	 */
	public String getLastlogintime() {
		return lastlogintime;
	}

	/**
	 * 最后登录时间
	 * 
	 * @param lastlogintime 最后登录时间
	 */
	public void setLastlogintime(String lastlogintime) {
		this.lastlogintime = lastlogintime == null ? null : lastlogintime.trim();
	}

	/**
	 * 备注
	 * 
	 * @return remark 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * 
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * 创建时间
	 * 
	 * @return createtime 创建时间
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createtime 创建时间
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime == null ? null : createtime.trim();
	}

	/**
	 * 更新时间
	 * 
	 * @return updatetime 更新时间
	 */
	public String getUpdatetime() {
		return updatetime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updatetime 更新时间
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime == null ? null : updatetime.trim();
	}
}
package com.vauen.yann.common.result;

import com.vauen.yann.common.config.GlobalConfig;

public class ResultJsonUtil {

	private String msg;
	private Integer msgCode = 100;
	private Object data;
	private boolean success = true;

	
	public ResultJsonUtil() {
		super();
	}

	public ResultJsonUtil(String msg, Integer msgCode, Object data, boolean success) {
		super();
		this.msg = msg;
		this.msgCode = msgCode;
		this.data = data;
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public ResultJsonUtil setMsg(String msg) {
		this.msg = msg;
		this.msgCode = GlobalConfig.SUCCESS_CODE;
		this.success = true;
		return this;
	}

	public ResultJsonUtil setError(String msg) {
		this.msg = msg;
		this.msgCode = GlobalConfig.FAIL_CODE;
		this.success = false;
		return this;
	}

	public Integer getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(Integer msgCode) {
		this.msgCode = msgCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}

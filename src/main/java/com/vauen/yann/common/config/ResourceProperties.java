package com.vauen.yann.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "com.vauen.yann.source")
@PropertySource(value = "classpath:resource.properties")
public class ResourceProperties {

	/** Face++ API_KEY */
	private String apiKey;
	/** Face++ API_SECRET */
	private String apiSecret;
	/** Face++ 身份证识别url */
	private String ocrIdcardUrl;
	/** Face++ 银行卡识别url */
	private String ocrBankcardUrl;
	
	public ResourceProperties() {
		super();
	}

	public ResourceProperties(String apiKey, String apiSecret, String ocrIdcardUrl, String ocrBankcardUrl) {
		super();
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.ocrIdcardUrl = ocrIdcardUrl;
		this.ocrBankcardUrl = ocrBankcardUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getOcrIdcardUrl() {
		return ocrIdcardUrl;
	}

	public void setOcrIdcardUrl(String ocrIdcardUrl) {
		this.ocrIdcardUrl = ocrIdcardUrl;
	}

	public String getOcrBankcardUrl() {
		return ocrBankcardUrl;
	}

	public void setOcrBankcardUrl(String ocrBankcardUrl) {
		this.ocrBankcardUrl = ocrBankcardUrl;
	}

}

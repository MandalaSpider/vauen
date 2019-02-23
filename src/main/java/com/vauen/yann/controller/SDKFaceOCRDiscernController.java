package com.vauen.yann.controller;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.vauen.yann.common.config.ResourceProperties;
import com.vauen.yann.common.result.ResultJsonUtil;
import com.vauen.yann.third.ocr.discern.HttpFaceOcrUtil;
import com.vauen.yann.third.ocr.discern.PictureCompressionUtil;
import com.vauen.yann.third.ocr.discern.StreamUtil;
import com.vauen.yann.utils.ObjectUtils;

@Controller
@RequestMapping("/ocr")
//@RestController
public class SDKFaceOCRDiscernController {
	
	@Autowired
	private ResourceProperties resource;

	
	@RequestMapping(value = "/discern" , method=RequestMethod.POST)
	@ResponseBody
	public ResultJsonUtil discern(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file, String type) {
		ResultJsonUtil results = new ResultJsonUtil();
		try {
			boolean b = StreamUtil.dataCheck(file, type);// 校验图片是否为空
			if (!b) return results.setError("请确认证件或证件类型");
			// MultipartFile转换为File Start
			File f = null;
			InputStream ins = file.getInputStream();
			f = new File(file.getOriginalFilename());
			StreamUtil.inputStreamToFile(ins, f);
			if (ObjectUtils.isNotEmpty(f)) {
				byte[] fromFile = HttpFaceOcrUtil.getBytesFromFile(f);// 原图大小
				BigDecimal initial = new BigDecimal(f.length());// 图片初始大小
				BigDecimal initialKb = initial.divide(new BigDecimal(1024));// 转Kb
				BigDecimal fin = initialKb.divide(new BigDecimal(1024));// 转换为兆M
				if (fin.compareTo(new BigDecimal(2)) > 0) {// 如果大于2兆 压缩图片大小
					fromFile = PictureCompressionUtil.compression(f);// 压缩图片
				}
				HashMap<String, byte[]> byteMap = new HashMap<>();
				String url = getUrl(type);// 根据类型判断 识别的是身份证还是银行卡 1身份证 2银行卡
				HashMap<String, String> map = HttpFaceOcrUtil.getInstance().putParam(resource.getApiKey(),resource.getApiSecret());// 构建API_KEY和API_SECRET必须参数
				byteMap.put("image_file", fromFile);// 构建文件参数
				byte[] bacd = HttpFaceOcrUtil.post(url, map, byteMap);
				String result = new String(bacd);// byte[]转String
				f.delete();
				results = StreamUtil.strToJson(type, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			results.setError("网络异常,请联系管理员");
		}
		return results;
	}
	
	public  String getUrl(String type) {
		int types = Integer.parseInt(type);
		String url = null;
		if (types == 1) {
			url = resource.getOcrIdcardUrl();
		}
		if (types == 2) {
			url = resource.getOcrBankcardUrl();
		}
		return url;

	}
}

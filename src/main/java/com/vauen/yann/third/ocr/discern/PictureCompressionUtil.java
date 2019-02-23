package com.vauen.yann.third.ocr.discern;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;

/**
* @ClassName: PictureCompressionUtil  
* @Description: TODO(图片压缩工具类)  
* @author 作者：Mike  
* @date 2019年2月21日
 */
public class PictureCompressionUtil {
	
	/**
	* @Title: compression  
	* @Description: TODO(压缩图片)  
	* @author 作者：Mike 
	* @date 2019年2月21日 
	* @param @param f
	* @param @return
	* @param @throws IOException    参数  
	* @return byte[]    返回类型  
	* @throws
	 */
	public static byte[] compression(File f) throws IOException {
		byte[] fromFile = HttpFaceOcrUtil.getBytesFromFile(f);
		byte[] scale = compressPicForScale(fromFile, 2048);//TODO:因face++一般要求图片最大2兆        所以指定2048    可根据实际需求调整
		return scale;

	}
	/**
	* @Title: compressPicForScale  
	* @Description: TODO(将图片压缩至指定大小)  
	* @author 作者：Mike 
	* @date 2019年2月21日 
	* @param @param imageBytes		源图片字节数组
	* @param @param desFileSize		指定图片大小，单位kb
	* @param @return
	* @param @throws IOException    参数  
	* @return byte[]    返回类型  
	* @throws
	 */
	public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize) throws IOException {
		if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
			return imageBytes;
		}
		long srcSize = imageBytes.length;
		double accuracy = getAccuracy(srcSize / 1024);
		while (imageBytes.length > desFileSize * 1024) {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
			Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(outputStream);
			imageBytes = outputStream.toByteArray();
		}
		return imageBytes;
	}
	/**
	* @Title: getAccuracy  
	* @Description: TODO(自动调节精度(经验数值))  
	* @author 作者：Mike 
	* @date 2019年2月21日 
	* @param @param size		源图片大小
	* @param @return    参数  		图片压缩质量比
	* @return double    返回类型  
	* @throws
	 */
	private static double getAccuracy(long size) {
		double accuracy;
		if (size < 900) {
			accuracy = 0.85;
		} else if (size < 2047) {
			accuracy = 0.6;
		} else if (size < 3275) {
			accuracy = 0.44;
		} else {
			accuracy = 0.4;
		}
		return accuracy;
	}

}

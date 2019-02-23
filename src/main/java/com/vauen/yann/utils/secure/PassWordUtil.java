package com.vauen.yann.utils.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.vauen.yann.utils.ObjectUtils;

public class PassWordUtil {

	/** 加密方式为AES */
	private static final String ALGORITHM_NAME = "AES";
	/** 编码方式AES */
	private static final String DEFAULT_CHARSET = "utf-8";
	/** 算法/模式/补码方式 */
	private static final String ALGORITHM_STR = ALGORITHM_NAME + "/CBC/PKCS5Padding";
	/** 编码方式AES */
	public static final String PASSWORD_SALT = "18300665808";

	public static void main(String[] args) {
//		System.out.println(encrypt("123456"));
		System.out.println(decrypt("Vxw/VWIL/20otGUBqVKuRw==", MD5Util.MD5(PASSWORD_SALT).substring(10, 26), DEFAULT_CHARSET));
	}
	/**
	 * AES加密，默认编码集UTF-8
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @return 加密结果
	 */
	public static String encrypt(String content) {
		return encrypt(content, getAesKey(), DEFAULT_CHARSET);
	}

	/**
	 * AES加密，默认编码集UTF-8
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @return 加密结果
	 * @throws Exception
	 */
	public String encryptException(String content) throws Exception {
		return encryptException(content, getAesKey(), DEFAULT_CHARSET);
	}

	/**
	 * AES解密，默认编码集UTF-8
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @return 解密结果
	 */
	public String decrypt(String content) {
		return decrypt(content, getAesKey(), DEFAULT_CHARSET);
	}

	/**
	 * 老的加密方法，加密以前加密字段。新加密业务不要使用这个方法
	 * 
	 * @param content 需要加密的内容
	 * @return
	 */
	public String encryptOld(String content) {
		return encrypt(content,PASSWORD_SALT, DEFAULT_CHARSET);
	}

	/**
	 * 老的解密方法，解密以前加密字段。新解密业务不要使用这个方法
	 * 
	 * @param content 需要解密的内容
	 * @return
	 */
	public String decryptOld(String content) {
		return decrypt(content,PASSWORD_SALT, DEFAULT_CHARSET);
	}

	/**
	 * 获取加密后的AesKey秘钥
	 * 
	 * @return 加密后的AesKey秘钥
	 */
	private static String getAesKey() {
		// 截取md5之后的32位的10-26位字符串
		return MD5Util.MD5(PASSWORD_SALT).substring(10, 26);
	}

	/**
	 * AES加密
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @param charset 字符编码
	 * @return 加密结果
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	private static String encryptException(String content, String key, String charset) throws Exception {
		/**
		 * 1、对字符串进行翻转 2、对字符串进行左位移（对加密内容进行左移n位（n值取决于字符串长度/2）） 3、对字符串进行加密
		 */
		if (ObjectUtils.isNotEmpty(content)) {
			// 对加密的key进行处理
			Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, charset);
			// 1、对字符串进行翻转
			StringBuilder sb = new StringBuilder(content);
			String reverse = sb.reverse().toString();
			// 2、字符串进行左位移
			int moveNum = getMoveIndex(reverse);// 左位移个数
			reverse = leftMove(reverse, moveNum);
			// 3、字符串进行加密
			byte[] resultByte = cipher.doFinal(reverse.getBytes(charset));
			return (new sun.misc.BASE64Encoder()).encode(resultByte);
		}
		return null;
	}

	/**
	 * AES加密
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @param charset 字符编码
	 * @return 加密结果
	 */
	@SuppressWarnings("restriction")
	private static String encrypt(String content, String key, String charset) {
		try {
			/**
			 * 1、对字符串进行翻转 2、对字符串进行左位移（对加密内容进行左移n位（n值取决于字符串长度/2）） 3、对字符串进行加密
			 */
			if (ObjectUtils.isNotEmpty(content)) {
				// 对加密的key进行处理
				Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, charset);
				// 1、对字符串进行翻转
				StringBuilder sb = new StringBuilder(content);
				String reverse = sb.reverse().toString();
				// 2、字符串进行左位移
				int moveNum = getMoveIndex(reverse);// 左位移个数
				reverse = leftMove(reverse, moveNum);
				// 3、字符串进行加密
				byte[] resultByte = cipher.doFinal(reverse.getBytes(charset));
				return (new sun.misc.BASE64Encoder()).encode(resultByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字符串需要位移个数
	 * 
	 * @param content 需要位移的字符串内容
	 * @return 字符串需要位移个数
	 */
	private static int getMoveIndex(String content) {
		return content.length() / 2;// 左位移个数
	}

	/**
	 * AES解密
	 * 
	 * @param content 加密内容
	 * @param key     秘钥，必须为16位
	 * @param charset 字符编码
	 * @return 解密结果
	 */
	@SuppressWarnings("restriction")
	public static String decrypt(String content, String key, String charset) {
		try {
			/**
			 * 1、对字符串进行解密 2、对字符串进行右位移（对加密内容进行左移n位（n值取决于字符串长度/2）） 3、对字符串进行解密
			 */
			if (ObjectUtils.isNotEmpty(content)) {
				Cipher cipher = getCipher(Cipher.DECRYPT_MODE, MD5Util.MD5(PASSWORD_SALT).substring(10, 26), DEFAULT_CHARSET);
				// 1、对字符串进行解密
				byte[] inputByte = (new sun.misc.BASE64Decoder()).decodeBuffer(content);
				byte[] resultByte = cipher.doFinal(inputByte);
				String tContent = new String(resultByte, charset);
				// 2、字符串进行左位移
				int moveNum = getMoveIndex(tContent);// 右位移个数
				tContent = rightMove(tContent, moveNum);
				// 3、对字符串进行翻转
				StringBuilder sb = new StringBuilder(tContent);
				return sb.reverse().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * get cipher 获取cipher对象
	 * 
	 * @param mode    加密方式
	 * @param key     秘钥，必须为16位
	 * @param charset 字符编码
	 */
	private static Cipher getCipher(int mode, String key, String charset) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(charset), ALGORITHM_NAME);
		// 偏移量同秘钥相同
		IvParameterSpec ivParameterSpec = new IvParameterSpec(key.getBytes(charset));
		Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
		cipher.init(mode, secretKeySpec, ivParameterSpec);
		return cipher;
	}

	/**
	 * 对字符串进行左位移
	 * 
	 * @param content 需要左位移字符串
	 * @param index   位移个数
	 * @return 左位移后字符串
	 */
	private static String leftMove(String content, int index) {
		String first = content.substring(0, index);
		String second = content.substring(index);
		first = reChange(first);
		second = reChange(second);
		content = first + second;
		content = reChange(content);
		return content;
	}

	/**
	 * 对字符串进行处理
	 * 
	 * @param content 需要处理的字符串
	 * @return 处理后的字符串
	 */
	private static String reChange(String content) {
		char[] contents = content.toCharArray();
		int length = contents.length;
		for (int i = 0; i < length / 2; i++) {
			char temp = contents[i];
			contents[i] = contents[length - 1 - i];
			contents[length - 1 - i] = temp;
		}
		return String.valueOf(contents);
	}

	/**
	 * 对字符串进行右位移
	 * 
	 * @param content 需要右位移字符串
	 * @param index   位移个数
	 * @return 右位移后字符串
	 */
	private static String rightMove(String content, int index) {
		content = reChange(content);
		String first = content.substring(0, index);
		String second = content.substring(index);
		first = reChange(first);
		second = reChange(second);
		content = first + second;
		return content;
	}
}

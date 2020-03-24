package com.cheryev.crm.auth.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;


public class Md5Util {

	private static Logger logger = LoggerFactory.getLogger(Md5Util.class);


	private Md5Util() {
	}

	private static String mergeSaltAndPassword(String salt, String password) {
		if (password == null) {
			password = new String();
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return salt + password;
		}
	}

	/**
	 *
	 * @param str
	 *            未加密密码
	 * @return 加密后密码
	 */
	public static String md5Encode(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("GBK"));
			StringBuilder buf = new StringBuilder();
			for (byte b : md.digest()) {
				buf.append(String.format("%02x", b & 0xff));
			}
			return buf.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 *
	 * @param salt
	 *            加密盐
	 * @param passWord
	 *            未加密密码
	 * @return 加密后密码
	 */
	public static String md5Encode(String salt, String passWord) {
		try {
			String str = mergeSaltAndPassword(salt, passWord);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("GBK"));
			StringBuilder buf = new StringBuilder();
			for (byte b : md.digest()) {
				buf.append(String.format("%02x", b & 0xff));
			}
			return buf.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 检查密码是否一致
	 *
	 * @param salt
	 *            加密盐
	 * @param rawPass
	 *            未加密密码
	 * @param encPass
	 *            加密后密码
	 * @return true-一致,false-不一致
	 */
	public static boolean checkPasswordSame(String salt, String rawPass, String encPass) {
		String str = md5Encode(salt, rawPass);
		if(str==null){
			return false;
		}
		return str.equals(encPass);
	}
}

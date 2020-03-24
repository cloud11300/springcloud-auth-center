package com.cheryev.crm.auth.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWTVerifier;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class JWTUtil {
	@SuppressWarnings("rawtypes")
	private static Map kuMap = new ConcurrentHashMap();
	private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

	private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

	private static final String PAYLOAD = "payload";

	private static Base64 base64 = new Base64();

	public static Map<String, Object> decryptJwt(String token)
	{
		String payload = StringUtils.substringBetween(token, ".");
		byte[] b = base64.decode(payload);
		String json = new String(b);
		return (Map) JSON.parseObject(json, Map.class);
	}

}

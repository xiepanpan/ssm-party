package com.baobao.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;




/**
 * <P>json字符串和object的转化工具</P>
 * @author 陶焕(13294175866)
 * @date 2016年2月26日 上午10:49:30
 */
public class JsonBuilder {
	
	private static Logger log = Logger.getLogger(WebUtils.class);
	
	private static class JsonHolder {

		private static final JsonBuilder JSON_BUILDER = new JsonBuilder();
		private static ObjectMapper mapper;

		static {
			mapper = new ObjectMapper();
			mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		}

		private JsonHolder() {
		}
	}

	private JsonBuilder() {
	}

	/**
	 * 
	 * <p>对象转化成json字符串</p>
	 * @param object
	 * @return  json字符串
	 * @author 陶焕(13294175866)
	 */
	public static String toJson(Object object) {
		try {
			return JsonHolder.mapper.writeValueAsString(object);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * <p>json字符串转化成对象</p>
	 * @param json
	 * @param c
	 * @return 对象
	 * @author 陶焕(13294175866)
	 */
	public static Object fromJson(String json, Class c) {
		json = clearJson(json);
		try {
			return JsonHolder.mapper.readValue(json, c);
		} catch (Exception e) {
		}
		return null;
	}

	private static String clearJson(String json) {
		if (json == null || json.isEmpty())
			return null;
		else
			return json.replaceAll("\n", "");
	}

}

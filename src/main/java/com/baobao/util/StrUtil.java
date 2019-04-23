package com.baobao.util;

public class StrUtil {
	//处理空格
	
	public static String strTrimSpace(String str){
		if(str!=null){
			return str.replaceAll("\\s*", "");
		}
		return str;
		
	}
}

package com.baobao.util;

import java.util.regex.Pattern;

public class MathUtil {
	static String regx = "^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$";//科学计数法正则表达式
    static Pattern pattern = Pattern.compile(regx);
    public static boolean isENum(String input){//判断输入字符串是否为科学计数法
        return pattern.matcher(input).matches();
    }
    public static boolean isIdcard(String idcard) {  
        return idcard == null || "".equals(idcard) ? false : Pattern.matches(  
                "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idcard);  
    }  
}

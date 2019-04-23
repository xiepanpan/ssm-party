package com.baobao.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.poi.util.StringUtil;

/**
 * @author: 李利刚
 * @E-mail: lgzc_work@163.com
 * @date: 2017-08-26 18:20
 * @describe:
 */

public class CommUtils {

    public static String MD5(String string) {
        if (string.isEmpty()) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    /**
     * 	将字符串为空转换为“”
     */
    public static String isEmpty(String str){
    	if(str==null){
    		str="";
    	}
		return str;
    	
    }
}

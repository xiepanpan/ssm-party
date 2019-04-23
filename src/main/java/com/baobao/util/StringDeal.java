package com.baobao.util;

public class StringDeal {
    public static String getUserIdAboveFour(int userId) {
        return String.format("%04d", userId);
    }
    public static String removeNonBmpUnicode(String str) {    
    	   if (str == null) {    
    	       return null;    
    	   }    
    	   str = str.replaceAll("[^\\u0000-\\uFFFF]", "");    
    	  return str;    
    	}    
}

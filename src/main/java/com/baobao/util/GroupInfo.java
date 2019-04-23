package com.baobao.util;

import com.google.gson.JsonParser;

public class GroupInfo {
		protected static final String APP_KEY = "a5319fab99b7f4184d1f8d19";
	 
	    protected static final String MASTER_SECRET ="7b0df5857a118c93a9706a92";
	   
	    protected static JsonParser parser = new JsonParser();

	  
		public static JsonParser getParser() {
			return parser;
		}
		public static void setParser(JsonParser parser) {
			GroupInfo.parser = parser;
		}
		public static String getAppKey() {
			return APP_KEY;
		}
		public static String getMasterSecret() {
			return MASTER_SECRET;
		}
	    
}

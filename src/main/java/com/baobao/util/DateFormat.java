package com.baobao.util;

import java.util.Date;

public class DateFormat {
	public static String getformatDate(Date date){
		Integer mon = date.getMonth();
		Integer day = date.getDate();
		String strmon =  String.valueOf(mon);
		String strday = String.valueOf(day);
		String str = "";
		String strd ="";
		String result ="";
		if(mon>0&&mon<10){
			 str ="0" + strmon;
		}else{
			 str = strmon;
		}
		if(day>0&&day<10){
			strd = "0"+strday;
		}else{
			strd = strday;
		}
		result = str+"-"+strd;
		return result;
		
	}
}

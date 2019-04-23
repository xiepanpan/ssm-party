package com.baobao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class GetLatAndLngByBaidu {
	 /** 
	 * @param addr 
	 * 查询的地址 
	 * @return 
	 * @throws IOException 
	 */ 
	public static Map<String, Double> getLatAndLngByAddress(String addr){
        String address = "";
        String lat = "";
        String lng = "";
        try {  
            address = java.net.URLEncoder.encode(addr,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
        +"ak=km6iat7VGBHdGU1T8KmfwPgwwkmAuBMs&output=json&address=%s",address);
        URL myURL = null;
        URLConnection httpsConn = null;  
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    lat = data.substring(data.indexOf("\"lat\":") 
                    + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":") 
                    + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                }
                insr.close();
            }
        } catch (Exception e) {
        	return null;
        }
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("lat", Double.parseDouble(lat));
        map.put("lng", Double.parseDouble(lng));
        return map;
}

	 public static void main(String[] args) throws IOException {
	 GetLatAndLngByBaidu getLatAndLngByBaidu = new GetLatAndLngByBaidu();
	 Map<String, Double> o = getLatAndLngByBaidu.getLatAndLngByAddress("阿拉善盟阿拉善左旗加油站");
	 System.out.println(o.get("lat").toString());//经度
	 System.out.println(o.get("lng").toString());//纬度
	 }

}



package com.baobao.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class GetLocalIp {
	public final static String sIP = getRealIp();
	public final static String sPORT = ":8080";
	public final static String sHOST_URL = "http://".concat(sIP).concat(sPORT);
	public final static String imgURL = sHOST_URL.concat("/imageUpload/");
	public final static String fileURL = sHOST_URL.concat("/fileUpload/");
	public static String getIp(){
		InetAddress iddr = null;
		try {
			iddr = iddr.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String localip=iddr.getHostAddress();
		return localip;
	}
	   public static String getRealIp() {  
	        String localip = null;// 本地IP，如果没有配置外网IP则返回它  
	        String netip = null;// 外网IP  
	  
	        try {
				Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();  
				InetAddress ip = null;  
				boolean finded = false;// 是否找到外网IP  
				while (netInterfaces.hasMoreElements() && !finded) {  
				    NetworkInterface ni = netInterfaces.nextElement();  
				    Enumeration<InetAddress> address = ni.getInetAddresses();  
				    while (address.hasMoreElements()) {  
				        ip = address.nextElement();  
				        if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP  
				            netip = ip.getHostAddress();  
				            finded = true;  
				            break;  
				        } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()  
				                && ip.getHostAddress().indexOf(":") == -1) {// 内网IP  
				            localip = ip.getHostAddress();  
				        }  
				    }  
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	  
	        if (netip != null && !"".equals(netip)) {  
	            return netip;  
	        } else {  
	            return localip;  
	        }  
	    } 

	public static void main(String[] args) {
		
	}
}

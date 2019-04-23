package com.baobao.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
/**
 * 
 * <P>Web工具包</P>
 * @author 陶焕(13294175866)
 * @date 2016年3月4日 上午9:18:24
 */
public class WebUtils {

	private static Logger log = Logger.getLogger(WebUtils.class);
	
	/**
	 * 
	 * <p>向客户端输出json</p>
	 * @param response
	 * @param result
	 * @author 陶焕(13294175866)
	 */
	public static void returnJson(HttpServletResponse response,Object result){
		try {
			response.setContentType("text/html;charset=UTF-8");
			if(result instanceof String){
				response.getWriter().write((String)result);
			}else{
				response.getWriter().write(JsonBuilder.toJson(result));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("向客户端输出Json出错:{}",e);
		}
	}
	
	/**
	 * 
	 * <p>返回文件或者文件流</p>
	 * @param response
	 * @param result
	 * @author 陶焕(13294175866)
	 */
	public static void returnFile(HttpServletRequest request,HttpServletResponse response,String contentType,File file,boolean isDownload){
		try {
		String filename = file.getName();//获取文件名称，在转化为子串
		String userAgent = request.getHeader("User-Agent");  
		log.info(userAgent);
		if (null != userAgent && -1 != userAgent.indexOf("MSIE") || null != userAgent  
                && -1 != userAgent.indexOf("Trident")) {// ie  
			filename = java.net.URLEncoder.encode(filename, "UTF-8");  
        } else if (null != userAgent && -1 != userAgent.indexOf("Mozilla")) {// 火狐,chrome等  
        	filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");  
        }  
		
		
		if(isDownload){
			//attachment --- 作为附件下载
			//inline --- 在线打开
			response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", filename)); // 文件名外的双引号处理firefox的空格截断问题
			//response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));  
		}
			returnStream(response,contentType,new FileInputStream(file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("向客户端输出流出错:{}",e);
		}
	}
	
	/**
	 * 
	 * <p>向客户端输出流</p>
	 * @param response
	 * @param in
	 * @author 陶焕(13294175866)
	 */
	public static void returnStream(HttpServletResponse response,String contentType,InputStream in){
		OutputStream out = null ;  
		try {
			int len = 0;
			byte buf[] = new byte[4096];// 缓存作用
			if(StringUtils.isNotBlank(contentType)){
				response.setContentType(contentType);
			}
			out = response.getOutputStream();// 输出流
			while ((len = in.read(buf)) > 0){ // 切忌这后面不能加 分号 ”;“
				out.write(buf, 0, len);// 向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("打开输出流出错:{}",e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}
	
	public static Map returnSuccessMsg(String msg){
		return returnSuccessMsg(msg,null);
	}
	
	public static Map returnSuccessMsg(String msg,Object data){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", 0);
		result.put("message", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map returnFailureMsg(int failureCode,String msg){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", failureCode);
		result.put("message", msg);
		return result;
	}
	
	/**
	 * 
	 * <p>获取项目的ContextPath,以"/"结尾</p>
	 * @param request
	 * @return
	 * @author 陶焕(13294175866)
	 */
	public static String getContextPath(HttpServletRequest request){
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+request.getContextPath();
		if(!basePath.endsWith("/")){
			basePath +="/";
		}
        return basePath;
    }

	/**
	 * 
	 * <p>获取项目的真实文件路径,以"/"结尾</p>
	 * @param request
	 * @return
	 * @author 陶焕(13294175866)
	 */
    public static String getRootRealPath(HttpServletRequest request){
    	String rootPath = request.getSession().getServletContext().getRealPath("/");
    	rootPath = rootPath.replace("\\", "/");
    	if(!rootPath.endsWith("/")){
    		rootPath +="/";
		}
        return rootPath;
    }
    /**
     * 
     * <p>获取请求的真实ip地址</p>
     * @param request
     * @return
     * @author 乐阳(15007199387)
     */
    public static String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }
}

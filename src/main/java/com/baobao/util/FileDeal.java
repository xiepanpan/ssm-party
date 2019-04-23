package com.baobao.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.TaskFile;

public class FileDeal {
	
	private FileDeal(){
		
	}
	
	private Logger logger = LoggerFactory.getLogger(FileDeal.class);
	
	private static FileDeal intance = null;
	
	public static FileDeal getIntence(){
		if (null==intance) {
			return new FileDeal();
		}else {
			return intance;
		}
	}
	
	/**
	 * 
	 * 上传文件
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月29日
	 */
	public  TaskFile uploadFile(HttpServletRequest request,MultipartFile myfile){
		TaskFile taskFile = null;
		String fileName = myfile.getOriginalFilename();
		String uploadFileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
		String url = GetImageUrl.getImageUrl(request)+System.getProperty("file.separator", "\\")+"fileUpload"+System.getProperty("file.separator", "\\")+uploadFileName;
		logger.info(url);
		File file = new File(url);
		
		String fielUrl =  request.getScheme()+"://"+request.getServerName()+":"+
                request.getServerPort()+request.getContextPath();
		if(!fielUrl.endsWith("/")){
			fielUrl +="/";
		}
		fielUrl +="file/getFile.app?name="+uploadFileName;
		System.out.println(request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\"));
		File parent = file.getParentFile();
		if(!parent.exists()){
			parent.mkdirs();
		}
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return taskFile;
			}
		}
		try {
			myfile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return taskFile;
		}
		taskFile = new TaskFile();
		taskFile.setTaskFileName(fileName);
		taskFile.setTaskFileUrl(fielUrl);
		taskFile.setTaskFileSize(myfile.getSize()/1024+"KB");
		return taskFile;
	}
	
	
	/**
	 * 
	 * 下载文件
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月29日
	 */
	public void downFile(HttpServletResponse response, File file) {
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition",
				"attachment;fileName=" + file.getName());
		byte[] buffer = new byte[1024];
		InputStream inStream = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			inStream = new FileInputStream(file);
			bis = new BufferedInputStream(inStream);
			os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
				bis.close();
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

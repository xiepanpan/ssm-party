package com.baobao.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UploadImage {
	
	public static String uploadImg(HttpServletRequest request,MultipartFile myfile){
		String fileName = myfile.getOriginalFilename();
		String uploadFileName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
		File file = new File(GetImageUrl.getImageUrl(request)+"/imageUpload"+System.getProperty("file.separator", "\\")+uploadFileName);
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
			}
		}
		try {
			myfile.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uploadFileName;
	}
	
	
	
	public static String dealImgUrl(String imgUrl){
		return imgUrl.substring(imgUrl.lastIndexOf("/")+1);
	}
	public static void main(String[] args) {
		String fileName ="localhost:8080/image/36699c6c-caf6-4ff3-80e3-8148b3b879d9.jpeg";
		//String uploadName = UUID.randomUUID()+fileName.substring(fileName.lastIndexOf("."));
		//System.out.println(uploadName);
		System.out.println(fileName.substring(fileName.lastIndexOf("/")+1));
	}
}

package com.baobao.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.ResultModel;
import com.baobao.common.service.TaskService;
import com.baobao.util.FileDeal;
import com.baobao.util.GetImageUrl;
import com.baobao.util.WebUtils;

@Controller
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private TaskService taskService;
	
	
	@RequestMapping("/getFile")
	@ResponseBody
	public Object getFile(HttpServletRequest request,HttpServletResponse response, String name){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String path=  GetImageUrl.getImageUrl(request)+System.getProperty("file.separator", "\\")+"fileUpload"+System.getProperty("file.separator", "\\");
			/*int i = newpathString.lastIndexOf("\\");
			String path2 = newpathString.substring(0, i);
			String userImageBaseSavePath = path2 + "\\user-image";*/
		File file = new File(path,name);
		WebUtils.returnFile(request, response, null, file, false);
		return null;
	} 
	
	
	/**
	 * 
	 * 文件下载
	 * @author 袁子龙（15071746320）
	 * @param url 文件的路径
	 * @return
	 * @date 2018年5月29日
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(HttpServletResponse response,HttpServletRequest request,String url){
		String[] fileUrl = url.split("name=");
		String fileString =  GetImageUrl.getImageUrl(request)+System.getProperty("file.separator", "\\")+"fileUpload"+System.getProperty("file.separator", "\\")+fileUrl[1];
	 	File file = new File(fileString);
	 	if (url==null) {
			return;
		}
	 	FileDeal.getIntence().downFile(response,file);
	}
	
	/**
	 * 
	 * 文件上传
	 * @author 袁子龙（15071746320）
	 * @param file 表单文件
	 * @return
	 * @date 2018年5月29日
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(MultipartFile file){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			resultModel.setStatus(true);
			resultModel.setData(taskService.uploadTaskFile(file));
			resultModel.setMessage("上传文件成功");
		} catch (Exception e) {
			resultModel.setMessage("上传文件失败");
			resultModel.setStatus(false);
		}
		return  resultModel;
	}
	
	
	@RequestMapping("/getPath")
	@ResponseBody
	public Object getPath(HttpServletRequest request){
		String url = GetImageUrl.getImageUrl(request)+System.getProperty("file.separator", "\\")+"fileUpload"+System.getProperty("file.separator", "\\");
		return url;
	}
}

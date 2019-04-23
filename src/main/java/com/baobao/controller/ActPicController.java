package com.baobao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.ResultModel;
import com.baobao.common.service.ActPicService;

@Controller
@RequestMapping("/actPic")
public class ActPicController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActPicService actPicService;
	
	@RequestMapping("/uploadPic.app")
	@ResponseBody
	public Object uploadPic(HttpServletRequest request,@RequestParam(value = "myfile",required = false)MultipartFile[] myfile,Integer actId,Integer brId){
		ResultModel<String> result = new ResultModel<String>();
		result.setStatus(false);
		try {
			Integer  i = this.actPicService.uploadActPic(request,actId,brId,myfile);
			if(i>0){
			result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@RequestMapping("/updatePic.app")
	@ResponseBody
	public Object updatePic(HttpServletRequest request,@RequestParam(value = "myfile",required = false)MultipartFile[] myfile,Integer actId,Integer brId){
		ResultModel<String> result = new ResultModel<String>();
		result.setStatus(false);
		try {
			Integer  i = this.actPicService.updateActPic(request,actId,brId,myfile);
			if(i>0){
				result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	
	@RequestMapping("/getImageInfo.app")
	@ResponseBody
	public Object getImageInfo(HttpServletRequest request,Integer brId,Integer actId){
		
		ResultModel<List<String>> result = new ResultModel<List<String>>();
		//List<String> data = new ArrayList<String>();
		result.setStatus(false);
		try {
			List<String> data = this.actPicService.getImageUrl(brId, actId);
			result.setData(data);
			result.setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/updateActImg.app")
	@ResponseBody
	public Object updateActImg(HttpServletRequest request,@RequestParam(value = "uploadImg",required = false)MultipartFile[] uploadImg,String[] deleteImg,Integer brId,Integer actId){
		ResultModel<List<String>> result = new ResultModel<List<String>>();
		result.setStatus(false);
		try {
			this.actPicService.updateImages(request,uploadImg, deleteImg, brId, actId);
			result.setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
}

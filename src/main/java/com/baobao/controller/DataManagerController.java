package com.baobao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.service.DataManService;

@Controller
@RequestMapping("/dataManager")
public class DataManagerController {
	@Autowired
	private DataManService dataManService;
	@RequestMapping("/exportMemInfo.htm")
	@ResponseBody
	public Object exportMemInfo(){
		
		return dataManService;
		
	}
	@RequestMapping("/importMemInfo.htm")
	@ResponseBody
	public int importMemInfo(HttpServletRequest request,@RequestParam(value="myfile",required = false)MultipartFile myfile){
		int i = 0;
		
		return i;
		
	}
}

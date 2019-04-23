package com.baobao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.service.IMService;

@Controller
@RequestMapping("/iM")
public class IMController {
	@Autowired
	private IMService iMSerivce;
	@RequestMapping("/createGroupByBrId.htm")
	@ResponseBody
	public Object  createGroupByBrId(HttpServletRequest request,Integer brId){
		Integer i = 0;
		if(brId!=null){
		 i = this.iMSerivce.createGroup(brId);
		}
		return i;
		
	}
	
}

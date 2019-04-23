/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月29日 上午11:53:34
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.mapping.HelpInfoModelMapper;
import com.baobao.common.model.HelpInfoModel;
import com.baobao.common.service.HelpManagerServcice;


/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月29日 上午11:53:34
 */
@Controller
@RequestMapping("/helpMananger")
public class HelpManangerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HelpManagerServcice helpManagerServcice;
	
	@Autowired
	private HelpInfoModelMapper HelpInfoModelMapper;
	@RequestMapping("/showHelp.htm")
	public ModelAndView showHelp(HttpServletRequest request,Integer flag){
		ModelAndView model = new ModelAndView("helpInfo");
		try{
		if(null!=flag&&flag==1){
			model.setViewName("helpInfo");
		}else{
			model.setViewName("webpages/list_help");
		}
		List<HelpInfoModel> result = this.helpManagerServcice.getHelpModels();
		
		model.addObject("result", result);
		}catch(Exception e){
			log.error("查询出错");
		}
		return model;
	}
	@RequestMapping("/insertHelp.htm")
	@ResponseBody
	public int insertHelp(HttpServletRequest request,HelpInfoModel helpInfo){
		int i = 0;
		try{
		i = this.helpManagerServcice.insertHelpModel(helpInfo);
		}catch(Exception e){
			log.error("插入出错");
		}
		return i;
		
	}
	@RequestMapping("/openHelpPage.htm")
	public ModelAndView openHelpPage(HttpServletRequest request,Integer helpId){
		ModelAndView model = new ModelAndView("dialog/help_edit");
		try {
			HelpInfoModel helpInfo = this.HelpInfoModelMapper.selectByPrimaryKey(helpId);
			model.addObject("helpInfo",helpInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
		
	}
	@RequestMapping("/updateHelpInfo.htm")
	@ResponseBody
	public int updateHelpInfo(HttpServletRequest request,HelpInfoModel helpInfo){
		int i = 0 ;
		try {
			i = this.HelpInfoModelMapper.updateByPrimaryKeySelective(helpInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/deleteById.htm")
	@ResponseBody
	public int deleteById(HttpServletRequest request,Integer helpId){
		int i = 0;
		try {
			i = this.HelpInfoModelMapper.deleteByPrimaryKey(helpId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/showHelpInfoById.htm")
	@ResponseBody
	public ModelAndView showHelpInfoById(HttpServletRequest request,Integer helpId,Integer flag){
		ModelAndView model = new ModelAndView();
		try{
		HelpInfoModel helpInfo = this.HelpInfoModelMapper.selectByPrimaryKey(helpId);
		if(null!=flag&&flag==1){
			model.setViewName("helpContext");
		}else{
			model.setViewName("webpages/details_help");
		}
		model.addObject("helpInfo", helpInfo);
		}catch(Exception e){
			log.error("查询出错");
		}
		return model;
		
	}
	@RequestMapping("/appShowHelp.htm")
	public ModelAndView appShowHelp(HttpServletRequest request){
		ModelAndView modelAndview = new ModelAndView();
		modelAndview.setViewName("webpages/mobile_help");
		List<HelpInfoModel> result = new ArrayList<HelpInfoModel>();
		try {
			result = this.helpManagerServcice.getHelpModels();
			modelAndview.addObject("result",result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndview;
	}
	@RequestMapping("/showHelpInfo.htm")
	public ModelAndView showHelpInfo(HttpServletRequest request,Integer id){
		ModelAndView modelAndView =new ModelAndView("webpages/mobile_detail_help");
		HelpInfoModel helpInfo = this.HelpInfoModelMapper.selectByPrimaryKey(id);
		modelAndView.addObject("helpInfo", helpInfo);
		return modelAndView;
	}
	@RequestMapping("/deleteBatch.htm")
	@ResponseBody
	public int deleteBatch(HttpServletRequest request,Integer Ids[]){
		Integer i = 0;
		i = this.helpManagerServcice.deleteBatch(Ids);
		return i;
		
	}
}

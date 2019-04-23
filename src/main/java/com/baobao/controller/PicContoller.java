/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月25日 下午1:58:06
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
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
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.mapping.ImageTypeModelMapper;
import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.ImageTypeModel;
import com.baobao.common.service.PicService;
import com.baobao.util.GetImageUrl;
import com.baobao.util.GetLocalIp;
import com.baobao.util.calFileSize;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月25日 下午1:58:06
 */
@Controller
@RequestMapping("/pic")
public class PicContoller {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PicService picService;
	@Autowired
	private ImageTypeModelMapper imageMapper;
	@RequestMapping("/showPicManager.htm")
	public ModelAndView showPicManager(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/picManager");
		try{
			ArrayList<ImageTypeModel> result = this.picService.showPic();
			List<String> sizeList = new ArrayList<String>();
			for(ImageTypeModel img:result){
				String path = img.getImageUrl();
				String sizestr = "";
				float size = calFileSize.getFileSize(request,path);
				if(size>1024){
					sizestr = size/((float)1024)+"M";
				}else{
					sizestr=size+"KB";
				}
				sizeList.add(sizestr);
			}
			modelAndView.addObject("parentUrl", GetLocalIp.imgURL);
			modelAndView.addObject("sizeList",sizeList);
			modelAndView.addObject("result", result);
		}catch(Exception e){
			log.error("查询出错");
		}
		return modelAndView;
		
	}
	@RequestMapping("/insertPic.htm")
	@ResponseBody
	public int insertPic(HttpServletRequest request,ImageTypeModel imageType,@RequestParam(value = "myfile",required = false)MultipartFile myfile){
		int i = 0;
		try {
			i = this.picService.insertImg(request, myfile, imageType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	
	}
	@RequestMapping("/updatePic.htm")
	@ResponseBody
	public int updatePic(HttpServletRequest request,ImageTypeModel image,@RequestParam(value = "myfile",required = false)MultipartFile myfile,ImageTypeModel imgBo){
		int i= 0;
		try {
			i =this.picService.updateImg(request, myfile,image);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/updatePicPage.htm")
	public ModelAndView updatePicPage(HttpServletRequest request,Integer id){
		ModelAndView model = new ModelAndView("dialog/pic_edit");
		try {
			ImageTypeModel img = this.imageMapper.selectByPrimaryKey(id);
			model.addObject("item", img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
		
	}
	@RequestMapping("/deleteById.htm")
	@ResponseBody
	public int deleteById(HttpServletRequest request,Integer id){
		Integer i = 0;
		try {
			i = this.imageMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	public static void main(String[] args) {  
	    FileChannel fc= null;  
	    try {  
	        File f= new File("D:\\test.xlsx");  
	        if (f.exists() && f.isFile()){  
	            FileInputStream fis= new FileInputStream(f);  
	            fc= fis.getChannel();  
	           System.out.print(Math.round(fc.size()/((float)1024)));
	        
	        }  
	    } catch (FileNotFoundException e) {  
	       
	    } catch (IOException e) {  
	         
	    } finally {  
	        if (null!=fc){  
	            try{  
	                fc.close();  
	            }catch(IOException e){  
	                
	            }  
	        }   
	    }  
	}  
}

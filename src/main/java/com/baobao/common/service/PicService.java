/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月25日 下午1:59:13
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.mapping.ImageTypeModelMapper;
import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.ImageTypeModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.util.UploadImage;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月25日 下午1:59:13
 */
@Service
public class PicService {
	@Autowired
	private ImageTypeModelMapper imageTypeModelMapper;
	public ArrayList<ImageTypeModel> showPic(){
		ArrayList<ImageTypeModel> list = this.imageTypeModelMapper.selectAll();
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertImg(HttpServletRequest request,MultipartFile myfile,ImageTypeModel image){
		int i = 0;
		try {
			/*String fileName=myfile.getOriginalFilename();//file.getOriginalFilename()是得到上传时的文件名
			System.out.println(fileName);
			String uploadFileName=UUID.randomUUID()+fileName.substring(fileName.length()-4);
			System.out.println(uploadFileName);
			//文件保存路径
			File file=new File(request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\")+uploadFileName);
			File parent = file.getParentFile();
			if(!parent.exists()){
				parent.mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			myfile.transferTo(file);*/
			String uploadFileName = UploadImage.uploadImg(request, myfile);
			image.setImageUrl(uploadFileName);
			i = this.imageTypeModelMapper.insertSelective(image);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return i;

	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateImg(HttpServletRequest request,MultipartFile myfile,ImageTypeModel image){
		
				int i = 0;
				try {
					if(!myfile.isEmpty()){
					/*String fileName=myfile.getOriginalFilename();//file.getOriginalFilename()是得到上传时的文件名
					System.out.println(fileName);
					String uploadFileName=UUID.randomUUID()+fileName.substring(fileName.length()-4);
					System.out.println(uploadFileName);
					//文件保存路径
					File file=new File(request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\")+uploadFileName);
					File parent = file.getParentFile();
					if(!parent.exists()){
						parent.mkdirs();
					}
					if(!file.exists()){
						file.createNewFile();
					}
					myfile.transferTo(file);*/
					String uploadFileName = UploadImage.uploadImg(request, myfile);
  					image.setImageUrl(uploadFileName);
					}
					
					i = this.imageTypeModelMapper.updateByPrimaryKeySelective(image);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return i;
	}
}

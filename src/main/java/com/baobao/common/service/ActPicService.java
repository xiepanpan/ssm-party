package com.baobao.common.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.mapping.ActPicModelMapper;
import com.baobao.common.model.ActPicModel;
import com.baobao.util.GetImageUrl;
import com.baobao.util.GetLocalIp;
import com.baobao.util.UploadImage;

@Service
public class ActPicService {
	@Autowired
	private ActPicModelMapper actPicMapper;
	/**
	 * 上传活动图片
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int uploadActPic(HttpServletRequest request,Integer actId,Integer brId,MultipartFile[] myfile){
		int j = 0 ;
		
			ActPicModel actPicModel = new ActPicModel();
			actPicModel.setActPicActid(actId);
			actPicModel.setActPicBrid(brId);
			if(myfile.length>0){
				for(int i = 0;i<myfile.length;i++){
				String uploadFileName = UploadImage.uploadImg(request, myfile[i]);
				actPicModel.setActPicUrl(uploadFileName);
				j = this.actPicMapper.insertSelective(actPicModel);
				}
			}
		
		return j;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateActPic(HttpServletRequest request,Integer actId,Integer brId,MultipartFile[] myfile){
		int j = 0 ;
		actPicMapper.deleteByActId(actId);
			ActPicModel actPicModel = new ActPicModel();
			actPicModel.setActPicActid(actId);
			actPicModel.setActPicBrid(brId);
			
			if(myfile.length>0){
				for(int i = 0;i<myfile.length;i++){
				String uploadFileName = UploadImage.uploadImg(request, myfile[i]);
				actPicModel.setActPicUrl(uploadFileName);
				j = this.actPicMapper.insertSelective(actPicModel);
				}
			}
		
		return j;
		
	}
	public List<String> getImageUrl(Integer brId,Integer actId){
		ActPicModel model = new ActPicModel();
		model.setActPicBrid(brId);
		model.setActPicActid(actId);
		
		List<String> data = this.actPicMapper.selectImageByBrId(model);
		List<String> newdata = new ArrayList<String>();
		for(int i = 0;i<data.size();i++){
			String a = GetLocalIp.imgURL+data.get(i);
			newdata.add(a);
			//data[i] = "http://139.224.60.3/hbjiandang/Images/"+data[i];
		}
		return newdata; 
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateImages(HttpServletRequest request,MultipartFile[] uploadImg,String[] deleteImg,Integer brId,Integer actId){
		
		if(uploadImg!=null&&uploadImg.length>0){
			ActPicModel aModel = new ActPicModel();
			for(MultipartFile iUrl:uploadImg){
				aModel.setActPicBrid(brId);
				aModel.setActPicActid(actId);
				String uploadFile = UploadImage.uploadImg(request, iUrl);
				aModel.setActPicUrl(uploadFile);
				this.actPicMapper.insertSelective(aModel);
			}
		}
		if(deleteImg!=null&&deleteImg.length>0){
			ActPicModel aModel = new ActPicModel();
			for(String dUrl:deleteImg){
				this.actPicMapper.deleteByImgUrl(UploadImage.dealImgUrl(dUrl));
			}
		}
	
		
	}
	
}

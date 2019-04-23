/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月23日 下午3:57:42
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.enums.NewsInfoEnum;
import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.mapping.CarouselModelMapper;
import com.baobao.common.mapping.ImageTypeModelMapper;
import com.baobao.common.mapping.NewsInfoModelMapper;
import com.baobao.common.model.BannerModel;
import com.baobao.common.model.CarouselModel;
import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.ImageTypeModel;
import com.baobao.common.model.InformationModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.StudyPageModel;
import com.baobao.util.GetLocalIp;
import com.baobao.util.UploadImage;

/**
 * @author 夏思明
 * @date 2017年8月23日 下午3:57:42
 */
@Service
public class NewsService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NewsInfoModelMapper newsInfoModelMapper;
	@Autowired
	private CarouselModelMapper carouselModelMapper;

	@Autowired
	private ImageTypeModelMapper imageTypeModelMapper;

	
	public ArrayList<NewsInfoModel> selectNews(Integer type){
		ArrayList<NewsInfoModel> newsList = this.newsInfoModelMapper.selectNewsByType(type);
		return newsList;
	}
	

	public ArrayList<NewsInfoModel> selectNews(SearchCondition cmd){
		ArrayList<NewsInfoModel> newsList = this.newsInfoModelMapper.getNews(cmd);
		return newsList;
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertNewsInfo(NewsInfoModel newsInfo){
		int i = this.newsInfoModelMapper.insertSelective(newsInfo);
		return i;
	}
	
	@Transactional(readOnly = true)
	public NewsInfoModel selectByprimarykey(int newsId){
		NewsInfoModel news = new NewsInfoModel();
		news = newsInfoModelMapper.selectByPrimaryKey(newsId);
		return news;
	}
	
	/**
	 * APP用于查询
	 * @return
	 */
	@Transactional(readOnly = true)
	public ResultModel<StudyPageModel> getAllNews(HttpServletRequest request) throws Exception{
		log.info("NewsService getAllNews方法");
		ResultModel<StudyPageModel> model = new ResultModel<StudyPageModel>(); // 返回的结果集
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); //格式化时间
		model.setData(new StudyPageModel());
		model.setInfo(0); 
		model.setMessage("");
		List<NewsInfoModel> newModels = null; // 新闻信息集合
		// 查询
		//BannerModel bannerModel = new BannerModel(); // 轮播(对象的引用为同一个)
		newModels = newsInfoModelMapper.selectAllNews(null);
		if(!CollectionUtils.isEmpty(newModels)){
			// 遍历查询的结果集，根据不同种类，封装到不同的集合
			for (NewsInfoModel newModel : newModels) {
				InformationModel informationModel = new InformationModel(); // 信息
				BannerModel bannerModel = new BannerModel(); // 轮播
				if (null!=newModel.getNewsType()&&newModel.getNewsType() >= 21 && newModel.getNewsType() <= 32) { // 时政和理论
					BeanUtils.copyProperties(informationModel, newModel); // newModel复制属性到informationModel
					// 字段名不同，不能复制属性，需手动set
					informationModel.setId(newModel.getTid()); // id
					informationModel.setReadCount(newModel.getReadcount()); // 点击量
					informationModel.setReleaseTime(sdf.format(newModel.getReleasetime())); //发布时间
					informationModel.setDetailsPageUrl(GetLocalIp.sHOST_URL+"/hbjiandang/newsController/appSelectNew.htm?newsId="+informationModel.getId()); // 新闻地址
				} else { // 轮播
					//String imgUrl =GetLocalIp.sHOST_URL+"/hbjiandang/Images/"+newModel.getImgUrl();
					String imgUrl =GetLocalIp.imgURL+newModel.getImgUrl();
					bannerModel.setImageUrl(imgUrl); // 轮播图片地址
					bannerModel.setDetailsPageUrl(GetLocalIp.sHOST_URL+"/hbjiandang/newsController/appSelectNew.htm?newsId="+newModel.getTid()); // 轮播新闻地址
					bannerModel.setId(newModel.getTid());
				}
				switch (newModel.getNewsType()) { // 新闻类型
				
					case NewsInfoEnum.ONE:		  	// 轮播
						model.getData().getBannerList().add(bannerModel);
						break;
					case NewsInfoEnum.TWENTY_ONE: 	// 理论 -系列讲话
						model.getData().getTheoryStudy().getSpeech().add(informationModel);
						break;
					case NewsInfoEnum.TWENTY_TWO: 	// 理论 -规章制度
						model.getData().getTheoryStudy().getRulesAndRegulations().add(informationModel);
						break;
					case NewsInfoEnum.THIRTY_ONE: 	// 时政 -国内时政
						model.getData().getCurrentPoliticsStudy().getDomesticNews().add(informationModel);
						break;
					case NewsInfoEnum.THIRTY_TWO: 	// 时政 -省内时政
						model.getData().getCurrentPoliticsStudy().getProvincialNews().add(informationModel);
						break;
					default: 
						model.setData(null);
						break;
				}
			}
		}
		model.setStatus(true); // 操作成功
		return model;
	}
	
	/**
	 * 修改新闻
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateNews(NewsInfoModel record) {
		return newsInfoModelMapper.updateByPrimaryKeySelective(record);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertImageAll(HttpServletRequest request,ImageBoModel imgBO,MultipartFile myfile){

		int i = 0;
		CarouselModel cModel = new CarouselModel();
		ImageTypeModel imgModel = new ImageTypeModel();
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
			if(imgBO!=null){
				cModel.setCarouselImgurl(uploadFileName);
				cModel.setImgId(imgBO.getImgId());
				imgModel.setImageId(imgBO.getImgId());
				imgModel.setImageType(imgBO.getType());
			}
			//cModel.setCarouselImgurl(uploadFileName);
			this.carouselModelMapper.insertSelective(cModel);
			this.imageTypeModelMapper.insertSelective(imgModel);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}//转存文件
		
		return 1;
		
		
	}
	/**
	 * 插入带图片的轮播新闻
	 * @param request
	 * @param newsInfo
	 * @param myfile
	 * @return
	 * @author 夏思明
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertImage(HttpServletRequest request,NewsInfoModel newsInfo,MultipartFile myfile){

		int i = 0;
	
		try {
			/*String fileName=myfile.getOriginalFilename();//file.getOriginalFilename()是得到上传时的文件名
			System.out.println(fileName);
			String uploadFileName=UUID.randomUUID()+fileName.substring(fileName.length()-4);
			System.out.println(uploadFileName);
			//文件保存路径
			File file=new File(request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\")+uploadFileName);
			//File file=new File(request.getSession().getServletContext().getRealPath("")+"\\Images\\"+uploadFileName);
			File parent = file.getParentFile();
			if(!parent.exists()){
				parent.mkdirs();
			}
			if(!file.exists()){
				file.createNewFile();
			}
			String urlpath = request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\")+uploadFileName;
			System.out.println(urlpath);
			myfile.transferTo(file);*/
			String uploadFileName = UploadImage.uploadImg(request, myfile);
			newsInfo.setImgUrl(uploadFileName);
			i = this.newsInfoModelMapper.insertSelective(newsInfo);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		return 1;
		
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateImage(HttpServletRequest request,NewsInfoModel newsInfo,MultipartFile myfile){
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
			newsInfo.setImgUrl(uploadFileName);
			}
			i = this.newsInfoModelMapper.updateByPrimaryKeySelective(newsInfo);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return 1;
	}
	//批量删除
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteBatch(Integer[] ids){
		Integer i = 0;
		List<Integer> list = Arrays.asList(ids);
	    i =	this.newsInfoModelMapper.deleteBatch(list);
		return i;
	}

	public String getTextById(Integer id){
		return newsInfoModelMapper.selectByPrimaryKey(id).getNewsContext();
	}
	
}

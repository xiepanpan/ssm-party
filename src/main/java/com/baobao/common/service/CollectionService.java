package com.baobao.common.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.mapping.CollectionMapper;
import com.baobao.common.mapping.CollectionModelMapper;
import com.baobao.common.model.Collection;
import com.baobao.common.model.CollectionModel;
import com.baobao.common.model.InformationModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.util.GetLocalIp;


@Service
public class CollectionService {
	
	@Autowired
	private CollectionModelMapper collectionModelMapper;
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addCollection(Integer userId,Integer newId) throws Exception{
		CollectionModel record = new CollectionModel(); // 学习收藏model
		record.setMemberId(userId);
		record.setNewId(newId);
		record.setIsCollected(null);
		int i = this.collectionModelMapper.selectByNUId(record);
		if(i>0){
			record.setIsCollected(0);
			this.collectionModelMapper.updateByNewId(record);
		}else{
		record.setIsCollected(0);
		collectionModelMapper.insertSelective(record);
		}
		System.out.println(1);
	}
	
	@Transactional(readOnly=true)
	public List<InformationModel> getStudyCollection(Integer userId,Integer page) throws Exception{
		List<InformationModel> infoModels = new ArrayList<InformationModel>(); // 新闻信息集合
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); //格式化时间
		List<NewsInfoModel> newsList = null; // 新闻集合
		if(page>=1){
		Integer start=((page-1)*10);
		Integer end =10;
		newsList = collectionModelMapper.getStudyCollection(userId,start,end);
		if(CollectionUtils.isNotEmpty(newsList)){
			for(NewsInfoModel news:newsList){
				InformationModel informationModel = new InformationModel();
				BeanUtils.copyProperties(informationModel, news);
				informationModel.setId(news.getTid()); // id
				informationModel.setReadCount(news.getReadcount()); // 点击量
				informationModel.setReleaseTime(sdf.format(news.getReleasetime())); //发布时间
				informationModel.setDetailsPageUrl(GetLocalIp.sHOST_URL+"/hbjiandang/newsController/appSelectNew.htm?newsId="+informationModel.getId()); // 新闻地址
				infoModels.add(informationModel);
			}
		}
		}
		return infoModels;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void cancelCollection(Integer userId,Integer newId){
		CollectionModel cmodel = new CollectionModel();
		cmodel.setMemberId(userId);
		cmodel.setNewId(newId);
		cmodel.setIsCollected(1);
		this.collectionModelMapper.updateByNewId(cmodel);
		System.out.println(1);
	}
	@Transactional(readOnly=true)
	public boolean isCollected(Integer memberId,Integer id){
		boolean flag = false;
		CollectionModel cmodel = new CollectionModel();
		cmodel.setMemberId(memberId);
		cmodel.setNewId(id);
		cmodel.setIsCollected(0);
		int i = this.collectionModelMapper.selectByNUId(cmodel);
		if(i==1){
			flag = true;
		}
		return flag;
		
	}
	
	
	@Autowired
	private CollectionMapper collectionMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	
	
	/**
	 * 
	 * 根据收藏对象的id取消或收藏
	 * @author 袁子龙（15071746320）
	 * @param id 收藏对象的id
	 * @return Boolean
	 * @date 2018年6月6日
	 */
	public Boolean updateCollection(Integer id){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		Collection collection = check(userId, id);
		if (null!=collection) {
			if (collectionMapper.deleteByPrimaryKey(collection.getCollectionId())>0) {
				return true;
			}
		}else {
			collection = new Collection();
			collection.setCollectionObjectId(id);
			collection.setCollectionUserId(userId);
			if (collectionMapper.insertSelective(collection)>0) {
				return true;
			}
		}
		return false;
	}
	
	
	public Integer getCollectNumber(Integer id){
		return collectionMapper.getCollectNumber(id);
	}
	
	public Collection check(Integer userId,Integer id){
		return collectionMapper.selectByUserAndObject(userId, id);
	}
}

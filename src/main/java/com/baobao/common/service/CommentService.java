package com.baobao.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.mapping.CommentMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.mapping.TaskFileMapper;
import com.baobao.common.model.Comment;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.TaskFile;
import com.baobao.util.FileDeal;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private TaskFileMapper taskFileMapper;
	
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	/**
	 * 
	 * 添加评论
	 * @author 袁子龙（15071746320）
	 * @param comment 评论model
	 * @param fileIds 评论文件id集合
	 * @return Boolean
	 * @date 2018年6月6日
	 */
	public Boolean addComment(Comment comment,Integer[] fileIds){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		comment.setCommentUserId(userId);
		comment.setCommentTime(new Date());
		comment.setCommentId(Integer.parseInt(sequenceModelMapper.getId()));
		
		if (null!=fileIds&&fileIds.length>0) {
			taskFileMapper.insertTask(fileIds, comment.getCommentId());
		}
		return commentMapper.insertSelective(comment)>0?true:false;
	}
	
	public Boolean addComment(Comment comment,List<Integer> list){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		comment.setCommentUserId(userId);
		comment.setCommentTime(new Date());
		comment.setCommentId(Integer.parseInt(sequenceModelMapper.getId()));
		if (list.size()>0) {
			taskFileMapper.insertTask(list, comment.getCommentId());
		}
			
		return commentMapper.insertSelective(comment)>0?true:false;
	}
	
	
	/**
	 * 
	 * 删除评论
	 * @author 袁子龙（15071746320）
	 * @param id 评论id
	 * @return Boolean
	 * @date 2018年6月6日
	 */
	public Boolean deleteComment(Integer id){
		return commentMapper.deleteByPrimaryKey(id)>0?true:false;
	}
	
}

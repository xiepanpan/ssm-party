package com.baobao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.Comment;
import com.baobao.common.model.ResultModel;
import com.baobao.common.service.CommentService;
import com.baobao.common.service.TaskService;

@RequestMapping("/comment")
@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private TaskService taskService;
	/*
	 * 添加评论
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public Object addComment(Comment comment,Integer[] fileIds){
		ResultModel resultModel = new ResultModel();
		try {
			if (commentService.addComment(comment, fileIds)) {
				resultModel.setStatus(true);
				resultModel.setMessage("添加评论成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("添加评论失败");
			}
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("添加评论异常");
		}
		return resultModel;
	}
	
	@RequestMapping("/addComment_ios")
	@ResponseBody
	public Object addComment_ios(Comment comment,@RequestParam(value="files")MultipartFile[] files){
		ResultModel resultModel = new ResultModel();
		try {
			List<Integer> list = taskService.upload(files);
			if (commentService.addComment(comment, list)) {
				resultModel.setStatus(true);
				resultModel.setMessage("添加评论成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("添加评论失败");
			}
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("添加评论异常");
		}
		return resultModel;
	}
	
	/*
	 * 删除评论
	 */
	@RequestMapping("/deleteComment")
	@ResponseBody
	public Object deleteComment(Integer commentId){
		ResultModel resultModel = new ResultModel();
		try {
			if (commentService.deleteComment(commentId)) {
				resultModel.setStatus(true);
				resultModel.setMessage("删除评论成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("删除评论失败");
			}
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("删除评论异常");
		}
		return resultModel;
	}
}

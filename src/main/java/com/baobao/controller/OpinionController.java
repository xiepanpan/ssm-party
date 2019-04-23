package com.baobao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.model.Opinion;
import com.baobao.common.model.ResultModel;
import com.baobao.common.service.OpinionService;

/*
 * 建言献策Controller
 */
@RequestMapping("/opinion")
@Controller
public class OpinionController {

	
	@Autowired
	private OpinionService opinionService;
	
	/*
	 * app添加意见
	 */
	@RequestMapping("/addOpinion")
	@ResponseBody
	public Object addOpinion(Opinion opinion){
		ResultModel resultModel = new ResultModel();
		try {
			if (opinionService.addOpinion(opinion)) {
				resultModel.setStatus(true);
				resultModel.setMessage("提交意见成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("提交意见失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("提交意见异常");
		}
		return resultModel;
	}
	
	/*
	 * web端获取建言献策
	 */
	@RequestMapping("/getOpinion")
	public ModelAndView getOpinion(){
		ModelAndView mav = new ModelAndView();
		List<Opinion> opinions = opinionService.getOpinions();
		mav.setViewName("opinion");
		mav.addObject("opinions", opinions);
		return mav;
	}
	
	
	/*
	 * 删除建言献策
	 */
	@RequestMapping("/deleteOpinion")
	@ResponseBody
	public Object deleteOpinion(Integer id){
		ResultModel resultModel = new ResultModel();
		try {
			if (opinionService.deleteOpinion(id)) {
				resultModel.setStatus(true);
				resultModel.setMessage("删除数据成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("删除数据失败");
			}
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("删除数据异常");
		}
		return resultModel;
	}
}

package com.baobao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.model.ResultModel;
import com.baobao.common.service.GoodService;

@RequestMapping("/good")
@Controller
public class GoodController {

	@Autowired
	private GoodService goodService;
	
	
	/*
	 * 点赞
	 */
	@RequestMapping("/addGood")
	@ResponseBody
	public Object addGood(Integer id){
		ResultModel resultModel = new ResultModel();
		try {
			if (goodService.getGood(id)) {
				resultModel.setStatus(false);
				resultModel.setData(goodService.getGoodNumber(id));
				resultModel.setMessage("你已经点赞了");
				return resultModel;
			}
			if (goodService.addGood(id)) {
				resultModel.setStatus(true);
				resultModel.setMessage("点赞成功");
				resultModel.setData(goodService.getGoodNumber(id));
				return resultModel;
			}else {
				resultModel.setData(goodService.getGoodNumber(id));
				resultModel.setStatus(false);
				resultModel.setMessage("点赞失败");
				return resultModel;
			}
			
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("点赞异常");
			return resultModel;
		}
	}
	
}

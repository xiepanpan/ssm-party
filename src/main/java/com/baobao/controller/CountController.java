package com.baobao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.cmd.DataCmd;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.andshow.ActBranchCountModel;
import com.baobao.common.model.andshow.CountScore;
import com.baobao.common.service.DataCountService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



@RequestMapping("/count")
@Controller
public class CountController {

	@Autowired
	private DataCountService dataCountService;
	
	
	/*
	 * 获取党支部排名
	 */
	@RequestMapping("/getBranchCount")
	@ResponseBody
	public Object getBranchCount(DataCmd cmd,@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		ResultModel<List<ActBranchCountModel>> resultModel = new ResultModel<List<ActBranchCountModel>>();
		try {
			Page page =PageHelper.startPage(pageIndex, 10);
			resultModel.setData(dataCountService.getActBranchCountModels(cmd));
			resultModel.setMessage("获取数据成功");
			resultModel.setPageTotal(page.getPageSize());
			resultModel.setPageIndex(pageIndex);
			resultModel.setStatus(true);
		} catch (Exception e) {
			resultModel.setMessage("获取数据异常");
			resultModel.setStatus(false);
		}
		return resultModel;
	}
	
	
	/*
	 * 获取党员排名
	 */
	@RequestMapping("/getMemberCount")
	@ResponseBody
	public Object getMemberCount(DataCmd cmd,@RequestParam(defaultValue="1",required=false)Integer pageIndex){
		ResultModel<List<CountScore>> resultModel = new ResultModel<List<CountScore>>();
		try {
			Page page = PageHelper.startPage(pageIndex, 10);
			resultModel.setData(dataCountService.getCountScores(cmd));
			resultModel.setMessage("获取数据成功");
			resultModel.setPageTotal(page.getPageSize());
			resultModel.setPageIndex(pageIndex);
			resultModel.setStatus(true);
		} catch (Exception e) {
			resultModel.setMessage("获取数据异常");
			resultModel.setStatus(false);
		}
		return resultModel;
	}
}

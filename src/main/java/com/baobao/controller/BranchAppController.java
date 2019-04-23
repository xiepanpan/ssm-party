package com.baobao.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.model.ResultModel;
import com.baobao.common.model.TotalBranchInfoModel;
import com.baobao.common.service.BranchAppService;

@Controller
@RequestMapping("/branchApp")
public class BranchAppController {
	@Autowired
	private BranchAppService branchAppService;
	@RequestMapping("/getCountBranchesInfo.app")
	@ResponseBody
	public Object getCountBranchesInfo(HttpServletRequest request,Integer page){
		List<Map<String, Integer>> data = new ArrayList<Map<String,Integer>>();
		ResultModel<List<Map<String, Integer>>> result = new ResultModel<List<Map<String,Integer>>>();
		result.setStatus(false);
		if(page!=null){
			try {
				data = branchAppService.getBranchesInfo( page);
				result.setData(data);
				result.setStatus(true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	@RequestMapping("/getInfoByGrade.app")
	@ResponseBody
	public Object getInfoByGrade(HttpServletRequest request){
		ResultModel<TotalBranchInfoModel> result = new ResultModel<TotalBranchInfoModel>();
		result.setStatus(false);
		TotalBranchInfoModel data = new TotalBranchInfoModel();
		try {
			data = this.branchAppService.getBranchInfoAll();
			if(data!=null){
				result.setData(data);
				result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}

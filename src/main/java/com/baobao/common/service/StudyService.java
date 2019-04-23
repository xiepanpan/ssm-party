package com.baobao.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.ActRecordModelMapper;
import com.baobao.common.mapping.StudyRecordModelMapper;
import com.baobao.common.model.ActRecordModel;
import com.baobao.common.model.ActivityCountModel;
import com.baobao.common.model.StudyRecordModel;
import com.baobao.common.model.StudyRecordNewModel;
import com.baobao.common.model.StudyRecordPageModel;

@Service
public class StudyService {
	@Autowired
	private ActRecordModelMapper actRecordMapper;
	@Autowired
	private StudyRecordModelMapper studyReMapper;
	
	public List<ActivityCountModel> getStudyRecordById(Integer userId){
		//根据id获取前6个月的数据
		List<ActivityCountModel> stuList = new ArrayList<ActivityCountModel>();
		stuList = this.actRecordMapper.getListForMonths(userId);
		return stuList;
		
	}
	public StudyRecordPageModel getStuPage(Integer userId){
		StudyRecordPageModel stuPage = new StudyRecordPageModel();
		List<StudyRecordNewModel> stuList = new ArrayList<StudyRecordNewModel>();
 		Integer yearCount = this.studyReMapper.countYearRe(userId);
		Integer monthCount = this.studyReMapper.countMonthRe(userId);
		stuList = this.studyReMapper.getStuNewList(userId);
		stuPage.setStudyRecordList(stuList);
		stuPage.setToMonthCountTime(yearCount);
		stuPage.setToYearCountTime(yearCount);
		return stuPage;
		
	}
}

package com.baobao.common.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.multipart.MultipartFile;

import cn.jmessage.api.common.model.group.GroupPayload;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.group.GroupClient;

import com.baobao.common.cmd.BranchCondition;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.util.GroupInfo;
import com.baobao.util.PageParamCommand;
import com.baobao.util.ReadExcel;
import com.google.gson.JsonObject;

@Service
public class BranchService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	@Autowired
	private IMService imService;
	@Transactional(readOnly = true)
	public List<BranchInfoModel> getBranchBypage(BranchCondition cmd)
			throws Exception {
		log.info("BranchService,获得所有支部信息getBranchList方法");
		List<BranchInfoModel> results = new ArrayList<BranchInfoModel>();
		results = branchInfoModelMapper.getBypage(cmd);
		return results;
	}

	@Transactional(readOnly = true)
	public ArrayList<BranchInfoModel> getBranchInfoModels(BranchCondition cmd) {
		ArrayList<BranchInfoModel> list = this.branchInfoModelMapper
				.getBranchList(cmd);
		return list;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insertBranch(BranchInfoModel branch) {
		MemberInfoModel model = new MemberInfoModel();
		try {
			Integer branchId = Integer.parseInt(sequenceModelMapper.getId());
			branch.setBranchId(branchId);
			
			//imService.createGroup(branchId);
			if (branch.getBrSeId() != null&&branch.getBrSeId()!=-1) {
				model.setMemberJob("支部书记");
				model.setMemberId(branch.getBrSeId());
				model.setMemberName(branch.getBranchSecretary());
				MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrSeId());
				branch.setBranchSecretary(memInfo.getMemberName());
				this.memberInfoModelMapper.changeJobById(model);
				MemRoleModel memRoleModel = new MemRoleModel(
						branch.getBrSeId(), branch.getBranchId());
				memRoleModel.setRelationRole(1);
				memRoleModelMapper.insertSelective(memRoleModel);
			}
			if (branch.getBrOrgId() != null&&branch.getBrOrgId() != -1) {
				model.setMemberId(branch.getBrOrgId());
				model.setMemberJob("支部组织委员");
				model.setMemberName(branch.getBranchOrganizationCommittee());
				MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrOrgId());
				branch.setBranchOrganizationCommittee(memInfo.getMemberName());
				this.memberInfoModelMapper.changeJobById(model);
				MemRoleModel memRoleModel = new MemRoleModel(
						branch.getBrOrgId(), branch.getBranchId());
				memRoleModel.setRelationRole(1);
				memRoleModelMapper.insertSelective(memRoleModel);
			}
			if (branch.getBrXcId() != null&&branch.getBrXcId() != -1) {
				model.setMemberId(branch.getBrXcId());
				model.setMemberJob("支部宣传委员");
				model.setMemberName(branch.getBranchPublicityCommittee());
				MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrXcId());
				branch.setBranchPublicityCommittee(memInfo.getMemberName());
				this.memberInfoModelMapper.changeJobById(model);
				MemRoleModel memRoleModel = new MemRoleModel(
						branch.getBrXcId(), branch.getBranchId());
				memRoleModel.setRelationRole(1);
				memRoleModelMapper.insertSelective(memRoleModel);
			}
			this.branchInfoModelMapper.insertSelective(branch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("插入失败");
			e.printStackTrace();
		}
		return 1;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int readExcelBranch(MultipartFile file) {
		int j = 0;
		try {

			ReadExcel readExcel = new ReadExcel();
			List<List<Object>> list = readExcel.read2007Excel(file, 0);
			List<BranchInfoModel> branchList = new ArrayList<BranchInfoModel>();
			System.out.println(branchList);
			int i = 0;
			for (List<Object> list2 : list) {
				if (i > 0) {
					BranchInfoModel branchInfo = new BranchInfoModel();
					branchInfo.setBranchName(list2.get(0).toString());
					branchInfo.setBranchOrganizationCommittee(list2.get(1)
							.toString());
					branchInfo.setBranchPublicityCommittee(list2.get(2)
							.toString());
					branchInfo.setBranchSecretary(list2.get(3).toString());
					branchList.add(branchInfo);
				}
				i++;
			}
			j = this.branchInfoModelMapper.insertBranchInfoBatch(branchList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int updateBranchJob(BranchInfoModel branch) {
		MemberInfoModel model = new MemberInfoModel();
		try {
			BranchInfoModel branchSearch = new BranchInfoModel();
			List<Integer> list = new ArrayList<Integer>();
			if (branch != null) {
				model.setMemberInbranchid(branch.getBranchId());
				model.setMemberJob("普通成员");
				//首先清除原先职务人的职位
				branchSearch =this.branchInfoModelMapper.selectByPrimaryKey(branch.getBranchId());
				list.add(branchSearch.getBrSeId());
				list.add(branchSearch.getBrOrgId());
				list.add(branchSearch.getBrXcId());
				this.memberInfoModelMapper.updateJobByIds(list);
				memRoleModelMapper.deleteByBranchId(branch.getBranchId());
				if (branch.getBrSeId() != null&&branch.getBrSeId() !=-1) {
					MemberInfoModel model1 = new MemberInfoModel();
					model.setMemberJob("支部书记");
					model.setMemberId(branch.getBrSeId());
					model1 = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrSeId());
					if(model1!=null&&model1.getMemberName()!=null){
						branch.setBranchSecretary(model1.getMemberName());
					}
					//model.setMemberName(branch.getBranchSecretary());
					this.memberInfoModelMapper.changeJobById(model);
					MemRoleModel memRoleModel = new MemRoleModel(
							branch.getBrSeId(), branch.getBranchId());
					memRoleModel.setRelationRole(1);
					memRoleModelMapper.insertSelective(memRoleModel);
				}
				if (branch.getBrOrgId() != null&&branch.getBrOrgId() != -1) {
					MemberInfoModel model2 = new MemberInfoModel();
					model.setMemberId(branch.getBrOrgId());
					model.setMemberJob("支部组织委员");
					model2 = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrOrgId());
					if(model2!=null&&model2.getMemberName()!=null){
						branch.setBranchOrganizationCommittee(model2.getMemberName());
					}
					//model.setMemberName(branch.getBranchOrganizationCommittee());
					this.memberInfoModelMapper.changeJobById(model);
					MemRoleModel memRoleModel = new MemRoleModel(
							branch.getBrOrgId(), branch.getBranchId());
					memRoleModel.setRelationRole(1);
					memRoleModelMapper.insertSelective(memRoleModel);
				}
				if (branch.getBrXcId() != null&&branch.getBrXcId() !=-1) {
					MemberInfoModel model3 = new MemberInfoModel();
					model.setMemberId(branch.getBrXcId());
					model.setMemberJob("支部宣传委员");
					model3 = this.memberInfoModelMapper.selectByPrimaryKey(branch.getBrXcId());
					if(model3!=null&&model3.getMemberName()!=null){
						branch.setBranchPublicityCommittee(model3.getMemberName());
					}
					//model.setMemberName(branch.getBranchPublicityCommittee());
					this.memberInfoModelMapper.changeJobById(model);
					MemRoleModel memRoleModel = new MemRoleModel(
							branch.getBrXcId(), branch.getBranchId());
					memRoleModel.setRelationRole(1);
					memRoleModelMapper.insertSelective(memRoleModel);
				}
				this.branchInfoModelMapper.updateByPrimaryKeySelective(branch);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("更新失败");
			e.printStackTrace();
			return 0;
		}

		return 1;

	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int deleteById(Integer branchId){
		Integer  i = 0;
		memRoleModelMapper.deleteByBranchId(branchId);
		i = this.branchInfoModelMapper.deleteBYSoft(branchId);
		this.memberInfoModelMapper.deleteMembersByBrId(branchId);
		return i;
		
	}
	//批量删除
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, Object> deleteBatch(Integer[] Ids){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer j  = 0;
		PageParamCommand cmd = new PageParamCommand();
		String msg = "下存在人员，不能删除";
		String smsg="成功删除";
		List<Integer> ids2 = new ArrayList<Integer>();
		for(int i = 0;i<Ids.length;i++){
			BranchInfoModel b = this.branchInfoModelMapper.selectByPrimaryKey(Ids[i]);
			List<Integer> result = this.memberInfoModelMapper.getMemIdByBrId(Ids[i]);
			if(result!=null&&result.size()>0){
				msg= "  ".concat(b.getBranchName().concat(msg));
			}else{
				smsg="  ".concat(b.getBranchName().concat(smsg));
				ids2.add(Ids[i]);
			}
			/*
			memRoleModelMapper.deleteByBranchId(Ids[i]);
			this.memberInfoModelMapper.deleteMembersByBrId(Ids[i]);*/
		}
		if(ids2!=null&&ids2.size()>0){
		j = this.branchInfoModelMapper.deleteBatch(ids2);
		}
		if(Ids.length==j){
			map.put("status", true);
		}else{
			map.put("status", false);
		}
		map.put("i", j);
		map.put("msg", msg);
		map.put("smsg", smsg);
		return map;
	}
}

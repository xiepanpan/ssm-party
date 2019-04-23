package com.baobao.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.group.CreateGroupResult;

import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.GroupInfoModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.GroupInfoModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.util.PageParamCommand;
import com.baobao.util.StringDeal;
import com.baobao.util.createGroup;
import com.baobao.util.managerMember;

@Service
public class IMService {
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	@Autowired
	private GroupInfoModelMapper groupMapper;
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	public void regiterMembers(List<MemberInfoModel> list){
		//List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		//list = this.memberInfoModelMapper.getAllMembers();
		managerMember.registerMembers(list);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Integer createGroup(Integer brId){
		//int branchId = 1;
		Integer j = 0;
		List<Integer> brList = new ArrayList<Integer>();
		brList.add(brId);
		PageParamCommand cmd = new PageParamCommand();
		cmd.setBrIds(brList);
		List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		//根据支部ID获取所有人
		list = this.memberInfoModelMapper.selectMembersByBrIdPage(cmd);
		String[] JoinList = new String[list.size()];
		//根据支部ID获取支部的Model
		BranchInfoModel branch = this.branchInfoModelMapper.selectByPrimaryKey(brId);
		//党支部书记的ID
		Integer brSJId = branch.getBrSeId();
		if(brSJId==null){
			return 0;
		}
		for(int i =0;i<list.size();i++){
			/*System.out.println(list.get(i).getMemberId());
			if(!list.get(i).getMemberId().equals(brSJId)){*/
			JoinList[i] = StringDeal.getUserIdAboveFour(list.get(i).getMemberId());
		/*	}*/
		}
		CreateGroupResult result = createGroup.createGroup(StringDeal.getUserIdAboveFour(brSJId),"支部工作群","desc",JoinList);
		for(int i =0;i< result.getMembers_username().size()+1;i++){
		GroupInfoModel gInfo = new GroupInfoModel();
		if(i==result.getMembers_username().size()){
			gInfo.setGroupId(result.getGid());
			gInfo.setMemUsername(String.valueOf(result.getOwner_username()).replace("\"",""));
		}else{
		gInfo.setGroupId(result.getGid());
		gInfo.setMemUsername(String.valueOf(result.getMembers_username().get(i)).replace("\"",""));
		}
		j = this.groupMapper.insertSelective(gInfo);
		}
		return j;
		
	}
	
}

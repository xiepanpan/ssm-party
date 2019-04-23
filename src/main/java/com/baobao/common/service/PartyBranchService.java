package com.baobao.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.PartyBranchModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PartyBranchModel;

@Service
public class PartyBranchService {
	
	@Autowired
	private PartyBranchModelMapper partyBranchModelMapper;
	
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	@Autowired
	private BranchInfoModelMapper branchMapper;
	/**
	 * 
	 * 添加或者修改党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String, Object> editPartyBranch(PartyBranchModel partyBranchModel){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (partyBranchModel.getPartyBranchType()==0) {
			if (partyBranchModelMapper.selectSchoolFaculty().size()>0) {
				map.put("data", -1);
				map.put("message", "已存在校级党支部，无法添加");
				return map;
			}
		}
		Integer type = 0;
		String prefix = "";
		if (partyBranchModel.getPartyBranchType()==0) {
			
			type = 3;
			prefix="校级";
		}else if (partyBranchModel.getPartyBranchType()==1) {
			type =2;
			prefix="院级";
		}
		MemberInfoModel model = new MemberInfoModel();
		if (partyBranchModel.getPartyId()!=null) {
			PartyBranchModel partyBranchModel2 = partyBranchModelMapper.selectByPrimaryKey(partyBranchModel.getPartyId());
			if(partyBranchModel.getPartyBranchName().equals(partyBranchModel2.getPartyBranchName())){
				map.put("status", false);
			}else{
				map.put("status", true);
			}
			map.put("id", partyBranchModel.getPartyId());
			map.put("name", partyBranchModel.getPartyBranchName());
			List<Integer> ids = new ArrayList<>();
			memRoleModelMapper.deleteByBranchId(partyBranchModel.getPartyId());
				if (partyBranchModel.getPartyBranchSecretaryId()!=null) {
					if (!partyBranchModel.getPartyBranchSecretaryId().equals(partyBranchModel2.getPartyBranchSecretaryId())) {
						ids.add(partyBranchModel2.getPartyBranchSecretaryId());
					}
					MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchSecretaryId());
					partyBranchModel.setPartyBranchSecretary(memInfo.getMemberName());
						MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchSecretaryId(),partyBranchModel.getPartyId());
						memRoleModel.setRelationRole(type);
						memRoleModelMapper.insertSelective(memRoleModel);
						model.setMemberJob(prefix+"党分支书记");
						model.setMemberId(partyBranchModel.getPartyBranchSecretaryId());
						this.memberInfoModelMapper.changeJobById(model);
				}
				if (partyBranchModel.getPartyBranchOrganizationId()!=null) {
					if (!partyBranchModel.getPartyBranchOrganizationId().equals(partyBranchModel2.getPartyBranchOrganizationId())) {
						ids.add(partyBranchModel2.getPartyBranchOrganizationId());
					}
					MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchOrganizationId());
					partyBranchModel.setPartyBranchOrganization(memInfo.getMemberName());
					MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchOrganizationId(),partyBranchModel.getPartyId());
					memRoleModel.setRelationRole(type);
					memRoleModelMapper.insertSelective(memRoleModel);
					model.setMemberJob(prefix+"党分支组织委员");
					model.setMemberId(partyBranchModel.getPartyBranchOrganizationId());
					this.memberInfoModelMapper.changeJobById(model);
				}
				if (partyBranchModel.getPartyBranchPublicityId()!=null) {
					if (!partyBranchModel.getPartyBranchPublicityId().equals(partyBranchModel2.getPartyBranchPublicityId())) {
						ids.add(partyBranchModel2.getPartyBranchPublicityId());
					}
					MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchPublicityId());
					partyBranchModel.setPartyBranchPublicity(memInfo.getMemberName());
					MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchPublicityId(),partyBranchModel.getPartyId());
					memRoleModel.setRelationRole(type);
					memRoleModelMapper.insertSelective(memRoleModel);
					model.setMemberJob(prefix+"党分支宣传委员");
					model.setMemberId(partyBranchModel.getPartyBranchPublicityId());
					this.memberInfoModelMapper.changeJobById(model);
				}
				if (ids.size()!=0) {
					memberInfoModelMapper.updateJobByIds(ids);
				}
			map.put("data",partyBranchModelMapper.updateByPrimaryKeySelective(partyBranchModel));
			return map;
		}
		partyBranchModel.setPartyId(Integer.parseInt(sequenceModelMapper.getId()));
		
		if (partyBranchModel.getPartyBranchSecretaryId()!=null) {
			MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchSecretaryId());
			partyBranchModel.setPartyBranchSecretary(memInfo.getMemberName());
			MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchSecretaryId(),partyBranchModel.getPartyId());
			memRoleModel.setRelationRole(type);
			memRoleModelMapper.insertSelective(memRoleModel);
			model.setMemberJob(prefix+"党分支书记");
			model.setMemberId(partyBranchModel.getPartyBranchSecretaryId());
			this.memberInfoModelMapper.changeJobById(model);
		}
		if (partyBranchModel.getPartyBranchOrganizationId()!=null) {
			MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchOrganizationId());
			partyBranchModel.setPartyBranchOrganization(memInfo.getMemberName());
			MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchOrganizationId(),partyBranchModel.getPartyId());
			memRoleModel.setRelationRole(type);
			memRoleModelMapper.insertSelective(memRoleModel);
			model.setMemberJob(prefix+"党分支组织委员");
			model.setMemberId(partyBranchModel.getPartyBranchOrganizationId());
			this.memberInfoModelMapper.changeJobById(model);
		}
		if (partyBranchModel.getPartyBranchPublicityId()!=null) {
			MemberInfoModel memInfo = this.memberInfoModelMapper.selectByPrimaryKey(partyBranchModel.getPartyBranchPublicityId());
			partyBranchModel.setPartyBranchPublicity(memInfo.getMemberName());
			MemRoleModel memRoleModel = new MemRoleModel(partyBranchModel.getPartyBranchPublicityId(),partyBranchModel.getPartyId());
			memRoleModel.setRelationRole(type);
			memRoleModelMapper.insertSelective(memRoleModel);
			model.setMemberJob(prefix+"党分支宣传委员");
			model.setMemberId(partyBranchModel.getPartyBranchPublicityId());
			this.memberInfoModelMapper.changeJobById(model);
		}
		int i= partyBranchModelMapper.insertSelective(partyBranchModel);
		map.put("data", i);
		map.put("id", partyBranchModel.getPartyId());
		map.put("name", partyBranchModel.getPartyBranchName());
		return map;
	}
	
	/**
	 * 
	 * 查询所有的党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(readOnly = true)
	public List<PartyBranchModel> getPartyBranchModels(PartyCmd partyCmd){
		return partyBranchModelMapper.selectAll(partyCmd);
	}
	/**
	 * 
	 * 查询院级的党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(readOnly = true)
	public List<PartyBranchModel> getPartyFaculty(PartyCmd partyCmd){
		return partyBranchModelMapper.selectFaculty(partyCmd);
	}
	/**
	 * 
	 * 根据id查询党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(readOnly = true)
	public PartyBranchModel getPartyById(Integer partyId){
		
		return partyBranchModelMapper.selectByPrimaryKey(partyId);
	}
	/**
	 * 
	 * 根据查询条件查询党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(readOnly = true)
	public List<PartyBranchModel> selectListBySearch(String searchValue){
		List<PartyBranchModel> list = new ArrayList<PartyBranchModel>();
		list = this.partyBranchModelMapper.selectListBySearch(searchValue);
		return list;
		
	}
	/**
	 * 
	 * 根据查询条件查询党分支
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * 2017年9月15日
	 */
	@Transactional(readOnly = true)
	public List<PartyBranchModel> selectListBySupertId(String searchValue){
		List<PartyBranchModel> list = new ArrayList<PartyBranchModel>();
		list = this.partyBranchModelMapper.selectListBySearch(searchValue);
		return list;
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteById(Integer partyId){
		PartyBranchModel partyBranchModel = partyBranchModelMapper.selectByPrimaryKey(partyId);
		List<Integer> ids = new ArrayList<>();
		ids.add(partyBranchModel.getPartyBranchOrganizationId());
		ids.add(partyBranchModel.getPartyBranchPublicityId());
		ids.add(partyBranchModel.getPartyBranchSecretaryId());
		memberInfoModelMapper.updateJobByIds(ids);
		memRoleModelMapper.deleteByBranchId(partyId);
		return partyBranchModelMapper.deleteById(partyId);
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public Map<String, Object> deleteBranchBatch(Integer[] ids){
		Map<String, Object> map = new HashMap<String, Object>();
		//List<String> list = new ArrayList<String>();//记录不能删除的党支部名称
		String msg ="下存在分支部,不能删除";
		String smsg="删除成功";
		Integer i =  0;
		List<Integer> ids2 = new ArrayList<>();
		List<Integer> memberIds = new ArrayList<Integer>();
		for(int j=0;j<ids.length;j++){
			PartyBranchModel partyBranchModel = partyBranchModelMapper.selectByPrimaryKey(ids[j]);
			if(partyBranchModel.getPartyBranchType()==0){
				msg = partyBranchModel.getPartyBranchName().concat(msg);
			}else{
			List<Map<Integer, String>> result = this.branchMapper.getBranchChildId(ids[j]);
			if(result==null||result.size()<=0){
				smsg="  ".concat(partyBranchModel.getPartyBranchName().concat(smsg));
				ids2.add(ids[j]);
				memberIds.add(partyBranchModel.getPartyBranchOrganizationId());
				memberIds.add(partyBranchModel.getPartyBranchPublicityId());
				memberIds.add(partyBranchModel.getPartyBranchSecretaryId());
				memberInfoModelMapper.updateJobByIds(memberIds);
				memRoleModelMapper.deleteByUserId(memberIds);//当前支部不可能存在人员
			}else{
				msg ="  ".concat(partyBranchModel.getPartyBranchName().concat(msg));
			}
			}
		}
		if(ids2!=null&&ids2.size()>0){
			
			i = partyBranchModelMapper.updateBatch(ids2);
		}
		if(ids2.size()==ids.length){
			map.put("status",true);
		}else{
			map.put("status",false);
		}
		map.put("smsg", smsg);
		map.put("msg",msg);
		map.put("i", i);
		return map;
	}
}

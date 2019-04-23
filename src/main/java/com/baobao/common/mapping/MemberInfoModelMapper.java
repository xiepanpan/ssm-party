package com.baobao.common.mapping;



import java.util.ArrayList;
import java.util.List;








import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.UserInfoAll;
import com.baobao.util.PageParamCommand;


public interface MemberInfoModelMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(MemberInfoModel record);

    int insertSelective(MemberInfoModel record);

    MemberInfoModel selectByPrimaryKey(Integer memberId);
    
    //Page<MemberInfoModel> selectMembersByPage(PageParamCommand cmd);
    
    ArrayList<MemberInfoModel> selectMembersByPage(PageParamCommand cmd);
    
    ArrayList<MemberInfoModel> selectMembersByBrIdPage(PageParamCommand cmd);
    
    int updateByPrimaryKeySelective(MemberInfoModel record);
    
    int updateByPrimaryKey(MemberInfoModel record);

	/**
	 * @param tel
	 * @return
	 */
    MemberInfoModel login(@Param("memberTel")String tel);
    /**
     * 新的登录获取用户的方法
     * @param tel
     * @return
     */
    UserInfoAll LoginGetInfo(@Param("memberTel")String tel);
    List<MemberInfoModel> getBranchUsersInfoByUserId(Integer userId);

	/**
	 * @return
	 */
	List<MemberInfoModel> getbranchName();
	
	List<MemberInfoModel> getbranchById(List<Integer> list);

	/**
	 * @param memberTel
	 * @return
	 */
	String findMemberTel(String memberTel);
	
	MemberInfoModel getUserInfoByUserId(Integer userId);

	/**
	 * @param memberId
	 * @return
	 */
	MemberInfoModel selectBranchBymemberId(Integer memberId);

	/**
	 * @param miModelList
	 * @return
	 */
	int importMemberExcel(List<MemberInfoModel> miModelList);

	/**
	 * 查询党员所有手机号
	 */
	List<String> getMemberPhone();
	/**
	 * 查询所有的党员姓名
	 */
	List<MemberInfoModel> getMemberName(PageParamCommand cmd);
	
	int changeJobByName(MemberInfoModel model);
	int changeJobById(MemberInfoModel model);
	//根据支部id将所在支部多有成员覆盖为普通成员
	int updateJob(MemberInfoModel model);
	
	int updateJobByIds(List<Integer> list);
	/**
	 * 根据手机号查询ID
	 */
	String  getUIdByPhone(Integer memberId);
	/**
	 * 获取当前支部下的所有成员
	 */
	List<Map<Integer,String>> getChildInfo(Integer memberInbranchid);
	//测试注册
	List<MemberInfoModel> getAllMembers();
	
	//根据党支部删除党员
	int deleteMembersByBrId(@Param("memberInbranchid") Integer memberInbranchid);
	
	//更新职位所选的三个成员的职位
	int updateMemberJobByIds(List<Integer> list);
	
	//管理员通用查询
	MemberInfoModel selectByAdminTel(@Param("memberTel")String tel);
	
	//批量删除
	int deletedByBatch(List<Integer> list);
	//根据所选的多个Id查询党员信	
	List<MemberInfoModel> getMembersByIds(List<Integer> ids);
	
	//通过支付页面查出所需要的人的名称
	List<String> getPayName(List<Integer> ids);
	//通过支付页面查出所需要的人的名称和电话
	List<Map<String,Object>> getPayTelName(List<Integer> ids);
	//通过支付页面的支部查出手机
	List<String> getTelByIds(List<Integer> list);
	
	//通过支付页面查询
	List<String> getTelByName(@Param("memberName") String memberName);
	//根据所给的子支部查询党员
	List<Integer> getMemIdByBrId(@Param ("memberInbranchid") Integer memberInbranchid);

}
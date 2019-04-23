package com.baobao.util;


import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.common.model.Members;
import cn.jmessage.api.common.model.RegisterInfo;
import cn.jmessage.api.common.model.RegisterPayload;
import cn.jmessage.api.common.model.UserPayload;
import cn.jmessage.api.group.GroupClient;
import cn.jmessage.api.user.UserClient;

import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.MemberInfoModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;

public class managerMember {
	 @Autowired
	 private MemberInfoModelMapper memInfoMapper;
	 private static GroupClient groupClient;
	 private static UserClient userClient =   new UserClient(GroupInfo.APP_KEY,
				GroupInfo.MASTER_SECRET);
	 private static JMessageClient  client = new JMessageClient(GroupInfo.APP_KEY,
				GroupInfo.MASTER_SECRET);
	 public static RegisterInfo registerMember(MemberInfoModel memberInfo){
		String id = String.valueOf(memberInfo.getMemberId());
		while(id.length()<4){
			id="0"+id;
		}
		RegisterInfo registerInfo = RegisterInfo.newBuilder()
					.setUsername(id)
					.setPassword("123456").build();
		
		return registerInfo;
		 
	 }
	 public static void updateNickName(MemberInfoModel memberInfo){
		 RegisterInfo registerInfo =  registerMember(memberInfo);
		 UserPayload payload = UserPayload.newBuilder()
                 .setNickname(memberInfo.getMemberName())
                 .setRegion("shenzhen")
                 .setBirthday("2015-04-01")
                 .build();
		 try {
			System.out.println(registerInfo.USERNAME);
			userClient.updateUserInfo(String.valueOf("0"+memberInfo.getMemberId()), payload);
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public static void  registerMembers(List<MemberInfoModel> memList){
		 try {
			RegisterInfo[] registerInfos = new RegisterInfo[memList.size()];
			int i = 0;
			for(MemberInfoModel memberInfo:memList){
				RegisterInfo registerInfo = registerMember(memberInfo);
				 registerInfos[i++] = registerInfo;
			}
			client.registerUsers(registerInfos);
			for(MemberInfoModel meModel:memList){
			UserPayload payload = UserPayload.newBuilder()
	                 .setNickname(meModel.getMemberName())
	                 .build();
			userClient.updateUserInfo(StringDeal.getUserIdAboveFour(meModel.getMemberId()), payload);
			}
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
	 }
	public static void main(String[] args) {
		Integer i = 129;
		i = Integer.parseInt("0"+i);
		System.out.println(i);
	}
}

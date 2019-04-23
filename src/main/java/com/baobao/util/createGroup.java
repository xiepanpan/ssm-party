package com.baobao.util;

import java.util.ArrayList;
import java.util.List;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.group.GroupClient;
import cn.jmessage.api.group.GroupInfoResult;
import cn.jmessage.api.group.MemberListResult;


public  class createGroup {

	private static JMessageClient client = new JMessageClient(GroupInfo.getAppKey(),
					GroupInfo.getMasterSecret());
	private static GroupClient group = new GroupClient(GroupInfo.getAppKey(),
					GroupInfo.getMasterSecret());
	public static CreateGroupResult createGroup(String createId,String groupName,String desc,String[] list){
		CreateGroupResult group = new CreateGroupResult();
		try {
			System.out.println(createId);
			 group = client.createGroup(createId,groupName, desc, list);
			
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;
	}
	public GroupInfoResult getGroupInfo(){
		
		return null;
		
	}
	public static List<String> getGroupMembers(Long id){
		ArrayList<String> list = new ArrayList<String>();
		  GroupClient groupClient = new GroupClient(GroupInfo.getAppKey(),GroupInfo.getMasterSecret());
		   try {
			MemberListResult res = groupClient.getGroupMembers(id);
		
			for(int i = 0;i<res.getMembers().length;i++){
				list.add(res.getMembers()[i].getUsername());
				System.out.println(res.getMembers()[i].getUsername());
			}
			System.out.println(list.size());
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

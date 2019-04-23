/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月30日 下午2:47:47
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.util;

/**
 * 返回对应职务角色的ID
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月30日 下午2:47:47
 */
public class MemberJobType {
	public static int getType(String jobType){
		if(jobType!=null){
			if(jobType.endsWith("书记")){
			return 1;
			}else if(jobType.endsWith("组织委员")){
				return 2;
			}else if(jobType.endsWith("宣传委员")){
				return 3;
			}
		}
		return 0;
	}
}

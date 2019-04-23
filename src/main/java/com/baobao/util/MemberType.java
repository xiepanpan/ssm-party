/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月30日 下午2:52:12
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.util;

/**
 * 返回对应党员类型的ID
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月30日 下午2:52:12
 */
public class MemberType {
	public static int getType(String mType){
		if(mType!=null){
		if(mType.equals("正式党员")){
		return 1;
		}else if(mType.equals("预备党员")){
			return 2;
		}else if(mType.equals("积极分子")){
			return 3;
		}
		}
		return 0;
	}
}

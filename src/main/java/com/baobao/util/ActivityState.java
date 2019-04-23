/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年9月4日 上午11:21:57
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.util;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年9月4日 上午11:21:57
 */
public interface ActivityState {
	  int UNSTART_ACTIVITY = 0x01; //未开始
	  int UNDERWAY_ACTIVITY = UNSTART_ACTIVITY<<1; //进行中
	  int COMPLETED_ACTIVITY = UNDERWAY_ACTIVITY<<1; //已完成
	  int ABANDON_ACTIVITY = COMPLETED_ACTIVITY<<1; //放弃
}

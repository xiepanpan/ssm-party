package com.baobao.thread;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.baobao.common.service.TimeService;


public class TimerManager extends TimerTask{
	private static Logger log = Logger.getLogger(TimerTask.class);

    private TimeService timeService;
    public TimerManager(TimeService timeService) {
        this.timeService=timeService;
    }
    
    @Override
    public void run() {
        try {
        	 log.info("开始活动的完成");
        	timeService.finishAct();
        	timeService.finishTask();
        } catch (Exception e) {
            log.info("# -------------解析信息发生异常--------------");
            e.printStackTrace();
        }
    }
	
	

}

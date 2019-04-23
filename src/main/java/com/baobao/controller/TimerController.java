package com.baobao.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baobao.common.service.TimeService;
import com.baobao.thread.TimerManager;


@Controller
public class TimerController {
	
	
	
    public static final long PERIOD_TIME = 1000*60*60*1; // 每隔 多少毫秒 执行（毫秒）
    //public static final long PERIOD_TIME = 1000*60; // 每隔 多少毫秒 执行（毫秒）
    
    @Autowired
    private TimeService timeService;
    
    
    // 加载完类 加载此方法
    @PostConstruct
    public void init() {
    		Calendar now = Calendar.getInstance();
    	 Calendar calendar = Calendar.getInstance();  
         calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时  
         calendar.set(Calendar.MINUTE, 0);       // 控制分  
         calendar.set(Calendar.SECOND, 0);       // 控制秒  
         Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的0：00：00
        Timer timer = new Timer();
        
        // 启动一个线程 执行任务
        TimerManager task = new TimerManager(timeService);
        
        // 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task, time, PERIOD_TIME);
    }
    
}

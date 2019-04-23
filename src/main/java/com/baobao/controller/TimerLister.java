package com.baobao.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;





import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.baobao.thread.TaskTime;



public class TimerLister implements ServletContextListener {
	

    private Timer timer=null;
    
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		 Calendar now = Calendar.getInstance();//服务器正常的时间
		  
		  
		  //执行任务,每天
		  long time_updateDB = 24*60*60*1000; 
		  Calendar everyday = Calendar.getInstance();
		  everyday.set(Calendar.HOUR_OF_DAY, 0);
		  everyday.set(Calendar.MINUTE, 0);
		  everyday.set(Calendar.SECOND, 0);
		  if(now.compareTo(everyday) > 0){
		   everyday.add(Calendar.DAY_OF_MONTH, 1);
		  }

		  timer = new Timer(true);
		  sce.getServletContext().log("时器已启动");
		  timer.schedule(new TaskTime(sce.getServletContext()), everyday.getTime(),time_updateDB);
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		 if(timer!=null){
			 timer.cancel();
			 sce.getServletContext().log("定时器已销毁");
			 }
		
	}
    
   
    
    // 加载完类 加载此方法
   
    
}

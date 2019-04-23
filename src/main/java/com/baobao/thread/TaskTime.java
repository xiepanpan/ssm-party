package com.baobao.thread;

import java.util.TimerTask;

import javax.servlet.ServletContext;

public class TaskTime extends TimerTask {

	
	
	private ServletContext servletContext;

	public TaskTime(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void run() {
		

	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}

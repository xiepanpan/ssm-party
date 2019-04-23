package com.baobao.common.socket;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SocketServiceLoader implements ServletContextListener{
	//socket server 线程 
	private SocketThread socketThread; 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		socketThread.closeSocketServer(); 
		socketThread.interrupt(); 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//新建线程类 
		socketThread=new SocketThread(null); 
		//启动线程 
		socketThread.start(); 
	}

}

package com.baobao.common.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketThread extends Thread{
	private ServerSocket serverSocket = null;
    private ExecutorService exec;
    private Map<String,PrintWriter> storeInfo;  
	public SocketThread(ServerSocket serverSocket){
		try { 
			if(null == serverSocket){ 
			this.serverSocket = new ServerSocket(5050);
		    storeInfo = new HashMap<String, PrintWriter>();  
	        exec = Executors.newCachedThreadPool();
			System.out.println("socket start"); 
			} 
			} catch (Exception e){ 
			System.out.println("SocketThread创建socket服务出错"); 
			e.printStackTrace(); 
			} 
	}
	public void run(){ 
		while(!this.isInterrupted()){ 
		try { 
		Socket socket = serverSocket.accept(); 

		if(null != socket && !socket.isClosed()){ 
		//处理接受的数据 
		exec.execute(new SocketOperate(socket));
		//new SocketOperate(socket).start(); 
		} 
		socket.setSoTimeout(30000); 

		}catch (Exception e) { 
		e.printStackTrace(); 
		} 
		} 
	} 
	public void closeSocketServer(){ 
		try { 
		if(null!=serverSocket && !serverSocket.isClosed()) 
		{ 
		serverSocket.close(); 
		} 
		} catch (IOException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 
	} 
	 
}

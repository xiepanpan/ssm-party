package com.baobao.common.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class SocketOperate implements Runnable{
	private Socket socket;
	private Map<String,PrintWriter> storeInfo;
	public SocketOperate(Socket socket) {
		super();
		this.socket = socket;
	}
	@Override
	public void run() {
		
		 try {
			/*BufferedReader bReader = new BufferedReader(  
				         new InputStreamReader(socket.getInputStream(), "UTF-8"));  */
			ObjectInputStream odata = new ObjectInputStream(socket.getInputStream());
				     //服务端将昵称验证结果通过自身的输出流发送给客户端  
			PrintWriter ipw = new PrintWriter(  
			         new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),true);
			UserInfo user = (UserInfo)odata.readObject();
			System.out.println(user.getContext());
			//将对应的管道流与用户ID键值对关联
			if(user!=null&&user.getSenderId()!=null&&user.getReceiverId()!=null){
				ipw.println(user.getContext()); 
				storeInfo.put("user.getReceiverId()",ipw);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	  
}

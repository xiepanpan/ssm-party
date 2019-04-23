package com.baobao.common.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TestServer {
	public static void main(String[] args) {
		//private Map<String,Socket> dd= new HashMap<String, Socket>();
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true){
			Socket socket = ss.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());  
		    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
		    UserInfo user = (UserInfo) in.readObject();
		    
		    System.out.println(user.getContext());
		    out.flush();  
	        in.close();  
	        out.close();  
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

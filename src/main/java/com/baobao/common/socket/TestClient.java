package com.baobao.common.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient {
	public static void main(String[] args) {
	
		try {
			Socket	s = new Socket("127.0.0.1", 8888);
			UserInfo u = new UserInfo();
			u.setContext("你啥都好说");
			u.setSenderId("123");
			u.setReceiverId("234");
			u.setType(0);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());  
	        ObjectInputStream in = new ObjectInputStream(s.getInputStream());  
	        out.writeObject(u);
	        out.flush();  
	        in.close();  
	        out.close();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

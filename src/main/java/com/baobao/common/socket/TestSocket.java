package com.baobao.common.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TestSocket {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(5050);
			Socket s = ss.accept();
			
			BufferedReader b = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str = null;
			if ((str= b.readLine()) != null) {
				s.getOutputStream().write(str.getBytes());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

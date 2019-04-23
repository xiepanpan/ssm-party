package com.baobao.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SysListener implements HttpSessionListener {
	/**
	 * key:   sessionId 
	 * value: tel(用户电话即账号)
	 */
	private static Map<String, String> sessionIdAndTelMap = new HashMap<String, String>(); // 存放当前有效的session
	
	/**
	 * key:   sessionId
	 * value: session(与sessionId对应)
	 */
	private static Map<String, HttpSession> seesionIdAndSessionmap = new HashMap<String, HttpSession>(); // 存放所有的session
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("开始监听");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("sdds");
	}

	/**
	 * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	 * 
	 * @param sUserName
	 *            String-登录的用户名称
	 * @return boolean-该用户是否已经登录过的标志
	 */
	public static boolean isAlreadyEnter(HttpSession session, String tel) {
		boolean flag = false;
		// 如果该用户已经登录过，则不允许登陆
		if (sessionIdAndTelMap.containsValue(tel)) {
			flag = true;
			/*// 遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和tel)
			Iterator<Entry<String, String>> iter = sessionIdAndTelMap.entrySet().iterator();
			Map.Entry<String,String> entry;
			while (iter.hasNext()) {
				entry = (Map.Entry<String,String>) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (((String) val).equals(tel)) {
					iter.remove(); // 移除原来的sessionID和tel(只是从map中移除了该sessionId，实际该session并未失效)
					seesionIdAndSessionmap.get(key).removeAttribute("userId"); // 移除上一个登陆的session中的
					System.out.println(seesionIdAndSessionmap.get(key).getAttribute("userId"));
				}
			}*/
		} else {
			flag = false;
			sessionIdAndTelMap.put(session.getId(), tel);
		}
		return flag;
	}

	/**
	 * isOnline-用于判断用户是否在线
	 * 
	 * @param session
	 *            HttpSession-登录的用户名称
	 * @return boolean-该用户是否在线的标志
	 */
	public static boolean isOnline(String tel) {
		boolean flag = true;
		if (sessionIdAndTelMap.containsValue(tel)) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean isOnline(HttpSession session) {
		boolean flag = true;
		System.out.println(session.getId());
		if (sessionIdAndTelMap.containsKey(session.getId())) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean signOut(HttpSession session) {
		boolean flag = true;
		if (sessionIdAndTelMap.containsKey(session.getId())) {
			sessionIdAndTelMap.remove(session.getId());
		} 
		return flag;
	}
}

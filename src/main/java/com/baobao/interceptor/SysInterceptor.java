/*package com.baobao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baobao.listener.SysListener;

@Repository
public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		log.info("进入拦截器!");
		// 获取请求地址
		String url = request.getRequestURL().toString();
		// 如果是向登陆界面发送请求则直接通过
		if (url.indexOf("Login.app") >= 0) {
			log.info("当前请求登陆界面,拦截器验证通过!");
			return true;
		}
		// 如果是向后台发送请求验证session是否生效则直接通过
		if (url.indexOf("getUserIdFromSession.htm") >= 0) {
			log.info("当前请求验证session是否生效,拦截器验证通过!");
			return true;
		}
		// 获取session
		HttpSession session = request.getSession();
		// 获取登陆用户Id
		Integer userId = (Integer) session.getAttribute("userId");
		// 判断当前用户是否在线
		boolean flag = SysListener.isOnline(session);
		// 如果登陆用户Id存在于session中,并且保持在线,则请求通过
		if (userId != null && flag) {
			log.info("当前用户已登录,拦截器验证通过!");
			return true;
		}
		// 不符合条件的，跳转到登录界面
		request.setAttribute("status", false); // 跳转状态码,false代表跳转不符合条件
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		log.info("拦截器验证未通过!");
		return false;
	}
	
}
*/
package com.baobao.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

public class GetImageUrl {
	
	public static String getImageUrl(HttpServletRequest request){
		Path p = Paths.get(request.getSession().getServletContext().getRealPath("/"));
		System.out.println(p.getParent().toString());
		return p.getParent().toString();
		
	}
}

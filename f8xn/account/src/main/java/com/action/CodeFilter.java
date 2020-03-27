package com.action;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(filterName = "codeFilter",urlPatterns = "/*")
public class CodeFilter implements Filter{

	private String codestr;
	
	public void destroy() {
		this.codestr = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		
		 if(codestr != null){
			 request.setCharacterEncoding(codestr);
		     response.setCharacterEncoding(codestr);
			 response.setContentType("text/html;charset="+codestr);
		 }
		 
		 arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		codestr = arg0.getInitParameter("codestr");
		System.out.println(" 字符过滤器codestr = "+codestr);
	}

}

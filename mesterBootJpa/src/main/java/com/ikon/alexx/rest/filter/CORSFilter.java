package com.ikon.alexx.rest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CORSFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
		 HttpServletResponse httpResponse = (HttpServletResponse) response;
		  if (req.getMethod()== "OPTIONS" ){
			  httpResponse.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-localhost, Authorization");
			  httpResponse.addHeader("Access-Control-Allow-Origin", "http://localhost");
			  httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
			  httpResponse.addHeader("Access-Control-Max-Age?", "3600");
		  }else {
			  chain.doFilter(request, response); 
		  }
		
	}
 
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("asdasffgagfawgha");
	}

}

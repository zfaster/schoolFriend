package com.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sys.bean.privilege.Employee;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String page = request.getRequestURI().substring(request.getContextPath().length());
		if(page.indexOf("?")!=-1){
			page = page.substring(0, page.indexOf("?"));
		}
		if(!page.contains(".") || page.contains(".jsp")){
			Employee employee = (Employee)request.getSession().getAttribute("employee");
			if(employee == null &&  !page.equals("/control/login.jsp") && !page.equals("/control/privilege/employee!login")){
				response.sendRedirect(request.getContextPath()+"/control/login.jsp");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
}

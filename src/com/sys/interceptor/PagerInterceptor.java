package com.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sys.system.SystemContext;

public class PagerInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String offset = request.getParameter("pager.offset");
		int _pageSize = 10;
		if(offset != null){
			int _offset = 0;
			try{
				_offset = Integer.parseInt(offset);
			}catch(Exception e){
				_offset = 0;
			}
			if(_offset<0) _offset = 0;
			SystemContext.setOffset(_offset);
		}
		String pageSize = request.getParameter("pageSize");
		if(pageSize!=null){
			
			_pageSize = 10;
			try{
				_pageSize = Integer.parseInt(pageSize);
				if(_pageSize < 0){
					_pageSize = 10;
				}
			}catch(Exception e){
				_pageSize = 10;
			}
			session.setAttribute("pageSize", _pageSize);
		}
		if(session.getAttribute("pageSize") != null){
			pageSize = session.getAttribute("pageSize").toString();
			_pageSize = 10;
			try{
				_pageSize = Integer.parseInt(pageSize);
				
			}catch(Exception e){
				_pageSize = 10;
			}
		}
		SystemContext.setPageSize(_pageSize);
		String result = invocation.invoke();
		
		SystemContext.removePageInfo();
		return result;
	}

}

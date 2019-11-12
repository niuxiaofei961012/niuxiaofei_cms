package com.niuxiaofei.cms.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.niuxiaofei.cms.domain.User;

/**
 * 
 * @ClassName: GeneralInterceptor 
 * @Description:用户拦截器
 * @author:nxf 
 * @date: 2019年7月30日 上午10:11:23
 */
public class GeneralInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//false 表示,当前的请求的有没有session,如果没有则不创建session,true:没有session则创建session,建议使用false
	
		HttpSession session = request.getSession(false);
		if(null==session || null== session.getAttribute(CMSConstant.LOGIN_GENERAL)) {
			request.setAttribute("user",new User());
			request.setAttribute("msg", "请登录后再下一步操作...");
		  request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		}
		return true;
	}
}

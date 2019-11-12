package com.niuxiaofei.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.niuxiaofei.cms.domain.User;
import com.niuxiaofei.cms.utils.CMSConstant;
/***
 * 
 * @ClassName: AdminController 
 * @Description:个人中心
 * @author: charles
 * @date: 2019年7月19日 上午10:50:25
 */
@Controller
public class MyController {
	@Resource
	private RedisTemplate<String,User> redisTemplate;
	
	/**
	 * 
	 * @Title: toIndex 
	 * @Description:跳转到用户页面
	 * @return
	 * @return: String
	 */
	@GetMapping("my")
	public String toIndex(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String key = (String) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		User user = redisTemplate.opsForValue().get(key);
		request.setAttribute("u",user);
		return "my/index";
		
	}

}

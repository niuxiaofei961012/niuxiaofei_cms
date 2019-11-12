package com.niuxiaofei.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/***
 * 
 * @ClassName: AdminController 
 * @Description: 管理员的控制器
 * @author: charles
 * @date: 2019年7月19日 上午10:50:25
 */

/**
 * 
 * @ClassName: AdminController 
 * @Description:跳转管理员页面
 * @author:nxf 
 * @date: 2019年7月30日 上午10:04:58
 */
@RequestMapping("admin")
@Controller
public class AdminController {
	@GetMapping({"","/","index","admin"})
	public String toIndex() {
		return "admin/index";
	}
}

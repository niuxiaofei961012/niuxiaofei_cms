package com.niuxiaofei.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niuxiaofei.cms.domain.User;
import com.niuxiaofei.cms.service.UserService;
import com.niuxiaofei.cms.utils.CMSConstant;
import com.niuxiaofei.cms.vo.UserVO;
import com.niuxiaofei.common.utils.CMSRuntimeException;

@Controller
public class PassportController {
	@Resource
	private UserService userService;
	
	@Resource
	private RedisTemplate<String,User> redisTemplate;

	/**
	 * 
	 * @Title: toReg 
	 * @Description: 跳转到注册页面
	 * @param model
	 * @param userVO
	 * @return
	 * @return: String
	 */
	@GetMapping("toReg")
	public String toReg(Model model, UserVO userVO) {
		return "passport/reg";
	}
	/**
	 * 
	 * @Title: reg 
	 * @Description: 注册
	 * @param userVO
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @return: String
	 */
	@PostMapping("reg")
	public String reg(@Valid UserVO userVO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "passport/reg";
		}
		try {
			userService.insert(userVO);
			redirectAttributes.addAttribute("msg", "恭喜你注册成功,请登录");
			redirectAttributes.addAttribute("username", userVO.getUsername());
			return "redirect:toLogin";
		} catch (CMSRuntimeException e) {
			model.addAttribute("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "未知错误");
		}
		return "passport/reg";
	}

	/**
	 * 
	 * @Title: toLogin 
	 * @Description: 跳转登录页面
	 * @param user
	 * @return
	 * @return: String
	 */
	@GetMapping("toLogin")
	public String toLogin(User user) {
		return "passport/login";
	}

	/**
	 * 
	 * @Title: login 
	 * @Description:登录
	 * @param user
	 * @param result
	 * @param session
	 * @param model
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(@Valid User user, BindingResult result, HttpSession session, Model model) {
		if (result.hasErrors()) {
			return "passport/login";
		}
		try {
			User u = userService.login(user);
			if (u.getRole().equals(CMSConstant.ROLE_ADMIN)) {
				String key = "user_admin";
				session.setAttribute(CMSConstant.LOGIN_ADMIN,key);
				redisTemplate.opsForValue().set(key,u);
				return "redirect:admin";
			} else {
				String key = "user_general";
				session.setAttribute(CMSConstant.LOGIN_GENERAL,key);
				redisTemplate.opsForValue().set(key,u);
				return "redirect:my";
			}
		} catch (CMSRuntimeException e) {
			model.addAttribute("msg", e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "未知错误");
		}
		return "passport/login";
	}

	/**
	 * 
	 * @Title: logout
	 * @Description:注销
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		// 清空session
		if (null != session) {
			session.invalidate();
		}
		return "redirect:toLogin";
	}
}

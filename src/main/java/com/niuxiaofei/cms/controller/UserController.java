package com.niuxiaofei.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niuxiaofei.cms.service.UserService;
import com.niuxiaofei.cms.utils.PageUtil;

@RequestMapping("user")
@Controller
public class UserController {
	@Resource
	private UserService userService;
	
	/**
	 * 
	 * @Title: selects 
	 * @Description:查询用户
	 * @param model
	 * @param page
	 * @param locked
	 * @param username
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("selects")
	public String selects(Model model,@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="")String locked,
			@RequestParam(defaultValue="")String username,
			@RequestParam(defaultValue="3")Integer pageSize){
		
		HashMap<Object, Object> map = new HashMap<>();
		map.put("username", username);
		map.put("locked", locked);
		
		PageHelper.startPage(page, pageSize);
		List<Map> list = userService.selects(map);
		
		PageInfo<Map> pageInfo = new PageInfo<>(list);
		String info = PageUtil.page(page, pageInfo.getPages(), "/user/selects?username="+username+"&locked="+locked, pageSize);
		
		model.addAttribute("list", list);
		model.addAttribute("username", username);
		model.addAttribute("locked", locked);
		model.addAttribute("info", info);
		return "user/list";
	}
	
	/**
	 * 
	 * @Title: updateLocked 
	 * @Description:修改用户状态
	 * @param id
	 * @param locked
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateLocked")
	public boolean updateLocked(Integer id,Integer locked) {
		return userService.updateLocked(id,locked)>0;
	}
}

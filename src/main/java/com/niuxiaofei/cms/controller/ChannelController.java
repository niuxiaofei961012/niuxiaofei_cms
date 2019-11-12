package com.niuxiaofei.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niuxiaofei.cms.domain.Category;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.service.CategoryService;
import com.niuxiaofei.cms.service.ChannelService;

@Controller
public class ChannelController {
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 
	 * @Title: getChannel 
	 * @Description:获取所有栏目
	 * @return
	 * @return: List
	 */
	@ResponseBody
	@GetMapping("getChannel")
	public List<Channel> getChannel() {
		return channelService.getChannel();
	}
	
	/**
	 * 
	 * @Title: getCategory 
	 * @Description:根据栏目ID获取栏目下分类
	 * @param channelId
	 * @return
	 * @return: List
	 */
	@ResponseBody
	@GetMapping("getCategory")
	public List<Category> getCategory(Integer channelId) {
		return categoryService.getCategory(channelId);
	}
	
}

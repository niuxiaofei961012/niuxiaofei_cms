package com.niuxiaofei.cms.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niuxiaofei.cms.domain.Collects;
import com.niuxiaofei.cms.service.CollectsService;

@Controller
public class CollectsController {

	@Resource
	private CollectsService collectsService;

	/** 
	 * @Title: collectArticle 
	 * @Description: 收藏
	 * @param entity
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("/collectArticle")
	@ResponseBody
	public boolean collectArticle(Collects entity) {
		return collectsService.insertObject(entity) > 0;
	}

	/** 
	 * @Title: disCollectArticle 
	 * @Description:取消收藏
	 * @param entity
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("/disCollectArticle")
	@ResponseBody
	public boolean disCollectArticle(Collects entity) {
		return collectsService.deleteObjectByUserIdAndArticleId(entity.getUser().getId(),
				entity.getArticle().getId()) > 0;
	}
}

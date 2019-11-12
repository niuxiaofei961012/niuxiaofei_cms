package com.niuxiaofei.cms.dao;

import java.util.List;

import com.niuxiaofei.cms.domain.Category;

public interface CategoryMapper {
	/**
	 * 
	 * @Title: getCategory 
	 * @Description:分类
	 * @param id
	 * @return
	 * @return: List
	 */
	List<Category> getCategory(Integer channelId);
}

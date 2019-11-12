package com.niuxiaofei.cms.service;

import java.util.List;


import com.niuxiaofei.cms.domain.Category;

public interface CategoryService {

	/**
	 * 
	 * @Title: getCategory 
	 * @Description:根据栏目id查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> getCategory(Integer channelId);

}

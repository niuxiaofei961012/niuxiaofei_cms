package com.niuxiaofei.cms.service;

import java.util.List;
import java.util.Map;

import com.niuxiaofei.cms.domain.Article;

public interface ArticleService {

	/**
	 * 
	 * @Title: insert 
	 * @Description:增加文章
	 * @param article
	 * @return
	 * @return: int
	 */
	int insert(Article article);

	/**
	 * 
	 * @Title: getTitles 
	 * @Description:获取所有文章
	 * @param article
	 * @return
	 * @return: List<Map>
	 */
	List<Map> getTitles(Article article);

	/**
	 * 
	 * @Title: get 
	 * @Description:根据ID获取文章
	 * @param id
	 * @return
	 * @return: Map
	 */
	Map get(Integer id);

	/**
	 * 
	 * @Title: update 
	 * @Description: 修改文章
	 * @param id
	 * @param status
	 * @return
	 * @return: int
	 */
	int update(Integer id, Integer status);

	/**
	 * 
	 * @Title: selects 
	 * @Description:展示所有文章
	 * @return
	 * @return: List
	 */
	List selects();

	/**
	 * 
	 * @Title: select 
	 * @Description: 根据id查询图片信息
	 * @param id
	 * @return
	 * @return: Map
	 */
	Map select(Integer id);

	/** 
	 * @Title: delArticleById 
	 * @Description: 删除文章
	 * @param ids
	 * @return
	 * @return: boolean
	 */
	boolean delArticleById(String[] ids);

	/** 
	 * @Title: updateHits 
	 * @Description:修改点击量
	 * @param id
	 * @return: void
	 */
	void updateHits(Integer id);
	
	
}

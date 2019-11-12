package com.niuxiaofei.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuxiaofei.cms.dao.ArticleMapper;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.service.ArticleService;
import com.niuxiaofei.common.utils.DateUtil;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Resource
	private ArticleMapper articleMapper;

	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Override
	public int insert(Article article) {
		return articleMapper.insert(article);
	}

	@Override
	public List<Map> getTitles(Article article) {
		return articleMapper.getTitles(article);
	}

	@Override
	public Map get(Integer id) {
		return articleMapper.get(id);
	}

	@Override
	public int update(Integer id, Integer status) {
		return articleMapper.update(id,status);
	}

	@Override
	public List selects() {
		return articleMapper.selects();
	}

	@Override
	public Map select(Integer id) {
		return articleMapper.select(id);
	}

	@Override
	public boolean delArticleById(String[] ids) {
		// TODO Auto-generated method stub
		return articleMapper.delArticleById(ids)>0;
	}

	@Override
	public void updateHits(Integer id) {
		articleMapper.updateHits(id);
	}


}

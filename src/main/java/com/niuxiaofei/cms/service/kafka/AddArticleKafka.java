package com.niuxiaofei.cms.service.kafka;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuxiaofei.cms.dao.ArticleMapper;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.utils.ESUtils;

public class AddArticleKafka implements MessageListener<String,String>{
	@Resource
	private ArticleMapper articleMapper;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		//获取监听到的字符串
		String value = data.value();
		//将字符串转为对象
		try {
			Article article = objectMapper.readValue(value,Article.class);
			//将得到的对象存入数据库
			articleMapper.insert(article);
			//将对象存入elasticsearch
			ESUtils.saveObject(elasticsearchTemplate,article.getId(),article);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

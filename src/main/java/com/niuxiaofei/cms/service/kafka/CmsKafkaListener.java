package com.niuxiaofei.cms.service.kafka;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.service.ArticleService;
import com.niuxiaofei.cms.utils.ESUtils;

public class CmsKafkaListener implements MessageListener<String, String> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	@Resource
	private ArticleService articleService;


	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String value = data.value();

		//添加文章kafka
		try {
			Article article = objectMapper.readValue(value, Article.class);
			articleService.insert(article);
			ESUtils.saveObject(elasticsearchTemplate, article.getId(), article);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

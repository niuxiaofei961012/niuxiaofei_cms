package com.niuxiaofei.cms.service.kafka;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

import com.niuxiaofei.cms.dao.ArticleMapper;
import com.niuxiaofei.cms.service.ArticleService;

public class updateKafka implements MessageListener<String,String>{
	@Resource
	private ArticleService articleService;
	
	
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		if(data.key().equals("nxf_cms_hit")) {
			//获取监听到的字符串
			String value = data.value();
			articleService.updateHits(Integer.valueOf(value));
		}
	}
	
}

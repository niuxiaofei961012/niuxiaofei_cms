package com.nxf.cms.jsoupTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.domain.Category;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.service.CategoryService;
import com.niuxiaofei.cms.service.ChannelService;
import com.niuxiaofei.common.utils.RandomUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-beans.xml","classpath:spring-producer.xml"})
public class ArticleImport {
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings("deprecation")
	@Test
	public void importArticle() throws IOException {
		File src = new File("h:/小说/仙逆");
		File[] files = src.listFiles();
		int i = 1;
    	for (File file : files) {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf8"));
			StringBuffer sb = new StringBuffer();
			String content = null;
			while((content=br.readLine())!=null) {
				sb.append(content);
			}
			String title = file.getName().substring(0,file.getName().length()-4);
			Article article = new Article();
			//随机时间
			article.setCreated(new Date(2000,0,1,1,i,1));
			//随机点击量
			article.setHits(RandomUtil.random(0,10000));
			//随机热点
			article.setHot(RandomUtil.random(0,1));
			//文章标题
			article.setTitle(title);
			//文章内容
			article.setContent(sb.toString());
			//文章状态
			article.setStatus(RandomUtil.random(0,1));
			//文章类型(图片,文档)
			article.setType(0);
			//文章标题样式
			article.setTitleType(RandomUtil.random(1,3));
			//文章图片
			File pic = new File("h:/pic");
			File[] photos = pic.listFiles();
			int index = RandomUtil.random(0,photos.length-1);
			article.setPicture(photos[index].getName());
			//文章栏目
			List<Channel> channel = channelService.getChannel();
			int channel_index = RandomUtil.random(0,channel.size()-1);
			article.setChannelId(channel.get(channel_index).getId());
			//文章分类
			List<Category> category = categoryService.getCategory(channel_index);
			if(category!=null && category.size()>0) {
				int category_index = RandomUtil.random(0,category.size()-1);
				article.setCategoryId(category.get(category_index).getId());
			}
			//用户
			article.setUserId(RandomUtil.random(1,6));
			String string = objectMapper.writeValueAsString(article);
			i+=7;
			kafkaTemplate.sendDefault(string);
			
		}
	}
}

package com.nxf.cms.addArticle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niuxiaofei.cms.dao.CategoryMapper;
import com.niuxiaofei.cms.dao.ChannelMapper;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.common.utils.DateUtil;
import com.niuxiaofei.common.utils.RandomUtil;
import com.niuxiaofei.common.utils.StreamUtil;

/**
 * @ClassName: AddArticle
 * @Description: 添加文章测试类
 * @author:NXF
 * @date: 2019年8月28日 上午8:38:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-beans.xml" })
public class AddArticle {
	@Resource
	private ChannelMapper channelMapper;
	
	@Resource
	private CategoryMapper categoryMapper;
	
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * @Title: addArticleTest
	 * @Description: 添加文章测试方法
	 * @return: void
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void addArticleTest() throws IOException {
		// 抓取的文章地址
		File src = new File("h:/xiaoshuo/xianni");
		File[] files = src.listFiles();
		for (File file : files) {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
			StringBuffer sb = new StringBuffer();
			String content = null;
			while ((content = br.readLine()) != null) {
				sb.append(content);
			}
			//初始化文章对象
			Article article = new Article();
			
			//文章标题去除.txt
			String title = file.getName().substring(0,file.getName().length()-4);
			article.setTitle(title);
			
			//文章内容
			article.setContent(sb.toString());
			
			//文章摘要
			String zhaiYao = sb.toString().substring(0,140);
			article.setZhaiYao(zhaiYao);
			
			//点击量  调用小一工具类
			article.setHits(RandomUtil.random(0,5000));
			
			//是否热门 调用小一工具类
			article.setHot(RandomUtil.random(0,1));
			
			//频道
			article.setChannelId(RandomUtil.random(1,9));
			
			//栏目
			article.setCategoryId(RandomUtil.random(1,2));
			
			//发布日期 调用小一工具类
			article.setCreated(DateUtil.randomDate(new Date(2019,0,1)));
			
			//图片
			article.setPicture("6");
			
			//用户
			article.setUserId(RandomUtil.random(1,6));
			
			//状态
			article.setStatus(RandomUtil.random(0,1));
			
			//将对象转成字符串
			String value = objectMapper.writeValueAsString(article);
			//将对象字符串发送到kafka
			kafkaTemplate.sendDefault("nxf_cms_article",value);
			
		}
	}
}

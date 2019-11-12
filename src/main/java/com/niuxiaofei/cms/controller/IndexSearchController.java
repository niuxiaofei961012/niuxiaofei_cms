package com.niuxiaofei.cms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.utils.CMSConstant;
import com.niuxiaofei.cms.utils.ESUtils;
import com.niuxiaofei.cms.utils.PageUtil;
import com.niuxiaofei.cms.utils.TypeEnum;
import com.niuxiaofei.cms.vo.ArticleVo;
import com.niuxiaofei.cms.service.ArticleService;
import com.niuxiaofei.cms.service.CategoryService;
import com.niuxiaofei.cms.service.ChannelService;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Controller
public class IndexSearchController {
	@Resource
	private ChannelService channelService;
	
	@Resource
	private CategoryService categoryService;

	@Resource
	private ArticleService articleService;

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * @Title: toIndex
	 * @Description: 进入首页
	 * @return
	 * @return: String
	 * @throws InterruptedException
	 */
	@GetMapping("search")
	public String toSearch(Article article, Model model, @RequestParam(defaultValue = "1") Integer page,String key)
			throws InterruptedException {
		// 文章只能显示审核
		article.setStatus(CMSConstant.ARTICLE_STATUS_CHECKED);
		model.addAttribute("article", article);

		// 返回所有栏目
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				List<Channel> channels = channelService.getChannel();
				model.addAttribute("channels", channels);
			}
		});

		// 如果用户没有点击栏目,就默认显示热点文章
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				Integer rows = 5;
				//开始时间
				long startTime = System.currentTimeMillis();
				AggregatedPage<?> pageInfo = ESUtils.selectObjects(elasticsearchTemplate, Article.class, page, rows, new String[] {"title","zhaiYao"}, key);
				//结束时间
				long endTime = System.currentTimeMillis();
				model.addAttribute("pageInfo", pageInfo);
			    model.addAttribute("page",page);
			    model.addAttribute("key",key);
			    model.addAttribute("time",endTime-startTime);
			}
		});

		// 具体栏目或分类下的文章
		Thread t3 = new Thread(new Runnable() {
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				List<Map> articles = articleService.getTitles(article);
				model.addAttribute("articles", articles);
			}

		});

		// 获取最新文章
		Thread t4 = new Thread(new Runnable() {
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				int pageSize = 10;
				PageHelper.startPage(0, pageSize);
				List<Map> lasts = articleService.getTitles(null);
				model.addAttribute("lasts", lasts);
				// 专题
				List specials = articleService.selects();
				model.addAttribute("specials", specials);
			}
		});

		// 最新图片
		Thread t5 = new Thread(new Runnable() {
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				Gson gson = new Gson();
				List<ArticleVo> pics = new ArrayList<>();
				// 给对象赋值 为了查询图片信息
				Article article2 = new Article();
				article2.setType(TypeEnum.IMAGE.getValue());
				PageHelper.startPage(1, 10);
				List<Map> maps = articleService.getTitles(article2);

				for (Map map : maps) {
					// 获取以json存储是内容
					String str = (String) map.get("content");
					// 解析json格式
					JsonElement jsonElement = new JsonParser().parse(str);
					// 装为数组对象
					JsonArray jsonArray = jsonElement.getAsJsonArray();
					for (JsonElement element : jsonArray) {
						ArticleVo vo = gson.fromJson(element, ArticleVo.class);
						// 获取图片集的ID
						vo.setId((Integer) map.get("id"));
						// 封装到集合
						pics.add(vo);
						break;// 只获取图片集的第一个
					}
				}
				model.addAttribute("pics", pics);
			}
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		
		return "index/indexSearch";
	}

	/**
	 * @Title: get
	 * @Description:点击标题跳转详细信息页面
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("getArticle")
	public String getArticle(Model model, Integer id) {
		@SuppressWarnings("rawtypes")
		Map map = articleService.get(id);
		model.addAttribute("map", map);
		return "index/articledetail";
	}
}

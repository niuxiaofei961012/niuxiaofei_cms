package com.niuxiaofei.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.niuxiaofei.cms.utils.PageUtil;
import com.niuxiaofei.cms.utils.TitleTypeEnum;
import com.niuxiaofei.cms.utils.TypeEnum;
import com.niuxiaofei.cms.vo.ArticleVo;
import com.niuxiaofei.cms.domain.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.niuxiaofei.cms.domain.Article;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.domain.Collects;
import com.niuxiaofei.cms.service.ArticleService;
import com.niuxiaofei.cms.service.CategoryService;
import com.niuxiaofei.cms.service.ChannelService;
import com.niuxiaofei.cms.service.CollectsService;
import com.niuxiaofei.cms.utils.CMSConstant;

@RequestMapping("/article")
@Controller
public class ArticleController {
	
	@Resource
	private ArticleService articleService;

	@Resource
	private RedisTemplate<String, User> redisTemplate;

	@Resource
	private ChannelService channelService;

	@Resource
	CategoryService categoryService;
	
	@Resource
	private CollectsService collectsService;
	
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;

	/**
	 * 
	 * @Title: select
	 * @Description: 根据id查询图片信息
	 * @return
	 * @return: String
	 */
	@GetMapping("select")
	public String select(Integer id, Model model) {
		Gson gson = new Gson();
		List<ArticleVo> pics = new ArrayList<>();

		Map map = articleService.select(id);
		// 获取以json存储是内容
		String str = (String) map.get("content");
		// 解析json格式
		JsonElement jsonElement = new JsonParser().parse(str);
		// 装为数组对象
		JsonArray jsonArray = jsonElement.getAsJsonArray();
		for (JsonElement element : jsonArray) {
			ArticleVo vo = gson.fromJson(element, ArticleVo.class);
			// 封装到集合
			pics.add(vo);
		}
		model.addAttribute("pics", pics);
		model.addAttribute("map", map);
		TitleTypeEnum[] style = TitleTypeEnum.values();
		model.addAttribute("style", style);
		return "index/pics";
	}

	/**
	 * 
	 * @Title: toPublishpic
	 * @Description:去添加图片页面
	 * @return
	 * @return: String
	 */
	@GetMapping("toPublishPic")
	public String toPublishpic(Model model) {
		// 将文章类型枚举传入前台
		TypeEnum[] values = TypeEnum.values();
		model.addAttribute("type", values);
		// 将文章标题类型传入前台
		TitleTypeEnum[] style = TitleTypeEnum.values();
		model.addAttribute("style", style);
		return "my/article/publishpic";
	}

	/**
	 * 
	 * @Title: toPublish
	 * @Description:跳转到发布文章页面
	 * @return
	 * @return: String
	 */
	@GetMapping("toPublish")
	public String toPublish() {
		return "my/article/publish";
	}

	/**
	 * 
	 * @Title: publishPic
	 * @Description: 添加图片
	 * @param files
	 * @param picContents
	 * @param request
	 * @param article
	 * @return
	 * @return: String
	 */
	@PostMapping("publishpic")
	public String publishPic(MultipartFile[] files, String[] picContents, HttpServletRequest request, Article article) {
		Gson gson = new Gson();
		List list = new ArrayList<>();
		String filename = null;
		// 存放图片的位置
		String path = "h:/pic/";
		// 判断是否有文件
		if (null != files && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				// 获取最原始的名称
				String originalFilename = files[i].getOriginalFilename();
				// 防止命名冲突获取新的名字
				filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

				File f = new File(path + filename);

				try {
					// 将图片存入磁盘
					files[i].transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 创建新对象存放文章content字段
				ArticleVo vo = new ArticleVo();
				vo.setId(i);
				vo.setUrl(filename);
				vo.setContent(picContents[i]);
				list.add(vo);

			}
		}

		// 讲对象转为json格式
		String json = gson.toJson(list);

		// 赋值给要存入数据库的文章对象
		article.setContent(json);

		article.setStatus(CMSConstant.ARTICLE_STATUS_NEW);
		article.setPicture(filename);
		article.setChannelId(9);
		article.setDeleted(0);
		article.setHot(0);
		article.setHits(0);
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		article.setUserId(user.getId());
		// 添加文章
		articleService.insert(article);
		return "my/index";
	}

	/**
	 * 
	 * @Title: publish
	 * @Description:发布文章
	 * @param article
	 * @param file
	 * @param request
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean publish(Article article, MultipartFile file, HttpServletRequest request) {
		String path = "h:/pic/";
		if (null != file) {
			String originalFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

			File f = new File(path + filename);

			try {
				file.transferTo(f);
				article.setPicture(filename);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		article.setStatus(CMSConstant.ARTICLE_STATUS_NEW);
		article.setDeleted(0);
		article.setHot(0);
		article.setHits(0);
		HttpSession session = request.getSession(false);
		String key = (String) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		User user = redisTemplate.opsForValue().get(key);
		article.setUserId(user.getId());//
		return articleService.insert(article) > 0;
	}

	/**
	 * @Title: getTitles
	 * @Description:显示我的文章
	 * @param model
	 * @param page
	 * @param pageSize
	 * @param request
	 * @return
	 * @return: List
	 */
	@GetMapping("getTitles")
	public String getTitles(Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "2") Integer pageSize, HttpServletRequest request, Article article) {

		PageHelper.startPage(page, pageSize);
		HttpSession session = request.getSession(false);
		String key = (String) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		User user = redisTemplate.opsForValue().get(key);
		article.setUserId(user.getId());
		List<Map> titles = articleService.getTitles(article);

		PageInfo<Map> pageInfo = new PageInfo<>(titles);
		String pages = PageUtil.page(page, pageInfo.getPages(), "/article/getTitles", pageSize);

		model.addAttribute("titles", titles);
		model.addAttribute("pages", pages);
		return "my/article/articletitles";
	}

	/**
	 * @Title: get
	 * @Description:根据ID获取文章
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("get")
	public String get(HttpServletRequest request,Model model,Integer id) {
		// 收藏的对象
		Collects collects = null;
		Map map = articleService.get(id);
		model.addAttribute("map", map);
		// 获取session对象
		HttpSession session = request.getSession();
		String key = (String) session.getAttribute(CMSConstant.LOGIN_GENERAL);
		User user = redisTemplate.opsForValue().get(key);
		if (user != null) {
			// 查询当前用户的对应文章id 是否已经被收藏过
			collects = collectsService.selectObjectByUserIdAndArticleId(user.getId(), id);
		}

		model.addAttribute("collects", collects);
		model.addAttribute("user", user);

		return "my/article/articledetail";
	}

	/**
	 * 
	 * @Title: getTitlesByAdmin
	 * @Description: 管理员获取文章信息
	 * @param model
	 * @param request
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("getTitlesByAdmin")
	public String getTitlesByAdmin(Model model, HttpServletRequest request, Article article,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
		HttpSession session = request.getSession(false);

		PageHelper.startPage(page, pageSize);

		List<Map> titles = articleService.getTitles(article);

		PageInfo<Map> pageInfo = new PageInfo<>(titles);

		String pages = PageUtil.page(page, pageInfo.getPages(), "/article/getTitlesByAdmin", pageSize);

		model.addAttribute("titles", titles);
		model.addAttribute("pages", pages);
		model.addAttribute("article", article);// 查询条件
		return "admin/article/articletitles";
	}

	/**
	 * 
	 * @Title: getByAdmin
	 * @Description:管理员根据ID获取文章详情
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@GetMapping("getByAdmin")
	public String getByAdmin(Integer id, Model model) {
		Map map = articleService.get(id);
		model.addAttribute("map", map);
		return "admin/article/articledetail";
	}

	/**
	 * 
	 * @Title: update
	 * @Description:修改文章状态
	 * @param id
	 * @param status
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("update")
	public boolean update(Integer id, Integer status) {
		return articleService.update(id, status) > 0;
	}

	/**
	 * @Title: delArticleById
	 * @Description:删除
	 * @param id
	 * @param status
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("delArticleById")
	public boolean delArticleById(String[] ids) {
		return articleService.delArticleById(ids);
	}
	
	/** 
	 * @Title: onSend 
	 * @Description:给kafka发送消息
	 * @param ids
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("updateHits")
	public boolean onSend(Integer id) {
		try {
			kafkaTemplate.sendDefault("nxf_cms_hit",id+"");
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	
}

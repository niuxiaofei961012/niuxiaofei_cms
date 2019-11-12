package com.niuxiaofei.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.niuxiaofei.cms.dao.CategoryMapper;
import com.niuxiaofei.cms.domain.Category;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.service.CategoryService;
import com.niuxiaofei.cms.utils.CMSConstant;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private RedisTemplate<String, Channel> redisTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategory(Integer channelId) {
		List<Category> list = new ArrayList<Category>();
		if (redisTemplate.hasKey(CMSConstant.NXF_CMS_REDIS_KEY_HASH)) {
			if (redisTemplate.opsForHash().hasKey(CMSConstant.NXF_CMS_REDIS_KEY_HASH,
					CMSConstant.NXF_CMS_REDIS_KEY_HASH + channelId)) {
				list = (List<Category>) redisTemplate.opsForHash().get(CMSConstant.NXF_CMS_REDIS_KEY_HASH,
						CMSConstant.NXF_CMS_REDIS_KEY_HASH + channelId);
			} else {
				list = categoryMapper.getCategory(channelId);
				redisTemplate.opsForHash().put(CMSConstant.NXF_CMS_REDIS_KEY_HASH,
						CMSConstant.NXF_CMS_REDIS_KEY_HASH + channelId, list);
			}
		} else {
			list = categoryMapper.getCategory(channelId);
			redisTemplate.opsForHash().put(CMSConstant.NXF_CMS_REDIS_KEY_HASH,
					CMSConstant.NXF_CMS_REDIS_KEY_HASH + channelId, list);
		}
		return list;
	}

}

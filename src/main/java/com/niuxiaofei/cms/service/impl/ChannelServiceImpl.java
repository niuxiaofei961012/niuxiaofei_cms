package com.niuxiaofei.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.niuxiaofei.cms.dao.ChannelMapper;
import com.niuxiaofei.cms.domain.Channel;
import com.niuxiaofei.cms.service.ChannelService;
import com.niuxiaofei.cms.utils.CMSConstant;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Resource
	private ChannelMapper channelMapper;

	@Resource
	private RedisTemplate<String,Channel> redisTemplate;
	
	@Override
	public List<Channel> getChannel() {
		List<Channel> list = new ArrayList<Channel>();
		if(redisTemplate.hasKey(CMSConstant.NXF_CMS_REDIS_KEY_LIST)) {
			list = redisTemplate.opsForList().range(CMSConstant.NXF_CMS_REDIS_KEY_LIST, 0, -1);
		}else {
			list = channelMapper.getChannel();
			redisTemplate.opsForList().leftPushAll(CMSConstant.NXF_CMS_REDIS_KEY_LIST, list);
		}
		return list;
	}

	
}

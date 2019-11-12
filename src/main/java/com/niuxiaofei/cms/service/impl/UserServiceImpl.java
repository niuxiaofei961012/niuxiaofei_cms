package com.niuxiaofei.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxiaofei.cms.dao.UserMapper;
import com.niuxiaofei.cms.domain.User;
import com.niuxiaofei.cms.service.UserService;
import com.niuxiaofei.cms.utils.CMSConstant;
import com.niuxiaofei.cms.utils.Md5Util;
import com.niuxiaofei.cms.vo.UserVO;
import com.niuxiaofei.common.utils.AssertUtil;
import com.niuxiaofei.common.utils.CMSRuntimeException;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User selectUser(String username) {
		return userMapper.selectUser(username);
	}


	@Override
	public boolean insert(UserVO userVO) {
		User user = userMapper.selectUser(userVO.getUsername());
		if (null != user) {
			throw new CMSRuntimeException("用户名已存在");
		}
		if (!userVO.getPassword().equals(userVO.getRepassword())) {
			throw new CMSRuntimeException("两次密码不一致");
		}
		userVO.setLocked(CMSConstant.USER_STATUS_UNLOCKED);
		userVO.setRole(CMSConstant.ROLE_GENERAL);
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		return userMapper.insert(userVO) > 0;
	}

	@Override
	public User login(User user) {
		AssertUtil.hasText(user.getUsername(), "用户名不能为空");
		AssertUtil.hasText(user.getPassword(), "密码不能为空");

		User user1 = userMapper.selectUser(user.getUsername());
		
		AssertUtil.notNull(user1, "用户名不存在");

		AssertUtil.isTrue(user1.getLocked() == 1, "账号被禁用");
		
		AssertUtil.isTrue(Md5Util.md5Encoding(user.getPassword()).equals(user1.getPassword()), "密码错误!");
		return user1;
	}

	@Override
	public List<Map> selects(HashMap<Object, Object> map) {
		return userMapper.selects(map);
	}

	@Override
	public int updateLocked(Integer id, Integer locked) {
		return userMapper.updateLocked(id, locked);
	}

}

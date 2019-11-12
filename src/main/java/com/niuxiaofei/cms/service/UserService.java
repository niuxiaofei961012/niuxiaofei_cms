package com.niuxiaofei.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.niuxiaofei.cms.domain.User;
import com.niuxiaofei.cms.vo.UserVO;

public interface UserService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: int
	 */
	boolean insert(UserVO userVO);
	/**
	 * 
	 * @Title: selectUser 
	 * @Description: TODO
	 * @param username
	 * @return
	 * @return: boolean
	 */
	User selectUser(String username);
	/**
	 * 
	 * @Title: login 
	 * @Description:登录
	 * @param user
	 * @return: void
	 */
	User login(User user);
	/**
	 * 
	 * @Title: selects 
	 * @Description:查询
	 * @param map
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selects(HashMap<Object, Object> map);
	/**
	 * 
	 * @Title: updateLocked 
	 * @Description:修改状态
	 * @param id
	 * @param locked
	 * @return
	 * @return: int
	 */
	int updateLocked(Integer id, Integer locked);
}

package com.niuxiaofei.cms.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.niuxiaofei.cms.domain.User;
import com.niuxiaofei.cms.vo.UserVO;

public interface UserMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description:添加用户
	 * @param user
	 * @return
	 * @return: int
	 */
	int insert(UserVO userVO);
	
	/**
	 * 
	 * @Title: selectUser 
	 * @Description:验证用户
	 * @param username
	 * @return
	 * @return: int
	 */
	User selectUser(@Param(value="username")String username);

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
	int updateLocked(@Param(value="id")Integer id, @Param(value="locked")Integer locked);
	
}

package com.niuxiaofei.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.niuxiaofei.cms.domain.Collects;


/** 
 * @ClassName: CollectsMapper 
 * @Description: TODO
 * @author: chj
 * @date: 2019年8月23日 下午4:31:47  
 */
public interface CollectsMapper {

	/** 
	 * @Title: selectObjectsByUserId 
	 * @Description: 根据用户的id查询用户的收藏文章
	 * @param userId
	 * @return
	 * @return: List<Collects>
	 */
	List<Collects> selectObjectsByUserId(Integer userId);
	
	
	/** 
	 * @Title: selectObjectByUserIdAndArticleId 
	 * @Description: 检测当前用户是否收藏过该文章
	 * @param userId
	 * @param articleId
	 * @return
	 * @return: Collects
	 */
	Collects selectObjectByUserIdAndArticleId(@Param("userId")Integer userId,@Param("articleId")Integer articleId);


	
	
	/** 
	 * @Title: deleteObjectByUserIdAndArticleId 
	 * @Description: 删除用户收藏的一篇文章
	 * @param userId
	 * @param articleId
	 * @return
	 * @return: int
	 */
	int deleteObjectByUserIdAndArticleId(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
	
	
	
	/** 
	 * @Title: insertObject 
	 * @Description: 添加收藏
	 * @param entity
	 * @return
	 * @return: int
	 */
	int insertObject(Collects entity);


}

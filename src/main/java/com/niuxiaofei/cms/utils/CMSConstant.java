package com.niuxiaofei.cms.utils;
/**
 * 
 * @ClassName: CMSConstant 
 * @Description:系统常量
 * @author:nxf 
 * @date: 2019年7月18日 下午7:04:35
 */
public class CMSConstant {
	
	public static final String NXF_CMS_REDIS_KEY_LIST = "nxf_cms_redis_key_list";//redis listKEY
	public static final String NXF_CMS_REDIS_KEY_HASH = "nxf_cms_redis_key_hash";//redis hashKEY
	
	public static final Integer USER_STATUS_UNLOCKED=0;//用户锁0代表不锁定
	public static final Integer USER_STATUS_LOCKED=1;//用户锁1代表锁定
	
	public static final String ROLE_ADMIN = "1";//管理员
	public static final String ROLE_GENERAL="0";//普通用户
	
	public static final String LOGIN_ADMIN="LOGIN_ADMIN";//管理员sessionkey
	public static final String LOGIN_GENERAL="LOGIN_GENERAL";//普通用户sessionkey
	
	public static final String URL_GENERAL="/admin";//普通用户URL
	public static final String URL_ADMIN="/my";//管理员URL
	
	public static final Integer ARTICLE_STATUS_NEW=0;// 文章刚发布
	public static final Integer ARTICLE_STATUS_CHECKED=1;// 文章已审核
	public static final Integer ARTICLE_STATUS_UNCHECKED=-1;// 文章未审核通过
	
}

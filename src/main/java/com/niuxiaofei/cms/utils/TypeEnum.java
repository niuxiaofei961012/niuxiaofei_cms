/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: TypeEnum.java 
 * @Prject: niuxiaofei_cms
 * @Package: com.niuxiaofei.cms.utils 
 * @Description: TODO
 * @author: nxf 
 * @date: 2019年7月31日 上午8:36:42 
 * @version: V1.0   
 */
package com.niuxiaofei.cms.utils;

/** 
 * @ClassName: TypeEnum 
 * @Description:发布文章形式
 * @author:nxf 
 * @date: 2019年7月31日 上午8:36:42  
 */
public enum TypeEnum {
	/**
	 * HTML表示现在以文字形式发布的文章，
	 * IMAGE表示该文章不是文本内容而是图片
	 */
	
	HTML("文字形式",0),IMAGE("图片形式",1);
	private String key;
	private Integer value;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	private TypeEnum(String key, Integer value) {
		this.key = key;
		this.value = value;
	}
	
	public static void main(String[] args) {
		TitleTypeEnum[] style = TitleTypeEnum.values();
		for (int i = 0; i < style.length; i++) {
			
		}
	}
}

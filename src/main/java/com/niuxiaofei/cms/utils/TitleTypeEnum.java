/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: TitleTypeEnum.java 
 * @Prject: niuxiaofei_cms
 * @Package: com.niuxiaofei.cms.utils 
 * @Description: TODO
 * @author: nxf 
 * @date: 2019年7月31日 上午9:49:07 
 * @version: V1.0   
 */
package com.niuxiaofei.cms.utils;

/** 
 * @ClassName: TitleTypeEnum 
 * @Description: 文章标题枚举
 * @author:nxf 
 * @date: 2019年7月31日 上午9:49:07  
 */
public enum TitleTypeEnum {
	I("font-style:italic",1),B("font-weight:800",2),R("color:red",3);
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
	private TitleTypeEnum(String key, Integer value) {
		this.key = key;
		this.value = value;
	}
	
}

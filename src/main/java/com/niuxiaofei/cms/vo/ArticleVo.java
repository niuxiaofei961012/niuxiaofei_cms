package com.niuxiaofei.cms.vo;

import com.niuxiaofei.cms.domain.Article;

public class ArticleVo extends Article {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ArticleVo [url=" + url + "]";
	}
	
}

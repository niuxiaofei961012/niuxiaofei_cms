package com.niuxiaofei.cms.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 
 * @ClassName: Special 
 * @Description:专题
 * @author:nxf 
 * @date: 2019年7月30日 上午10:12:29
 */
public class Special implements Serializable{
	private Integer id;
	private String title;
	private String abstracts;
	private Date created;
	private Set<Article> articles;
	
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "Special [id=" + id + ", title=" + title + ", abstracts=" + abstracts + ", created=" + created
				+ ", articles=" + articles + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abstracts == null) ? 0 : abstracts.hashCode());
		result = prime * result + ((articles == null) ? 0 : articles.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Special other = (Special) obj;
		if (abstracts == null) {
			if (other.abstracts != null)
				return false;
		} else if (!abstracts.equals(other.abstracts))
			return false;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}

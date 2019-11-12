package com.niuxiaofei.cms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Collects
 * @Description: TODO
 * @author: chj
 * @date: 2019年8月23日 下午4:24:05
 */
public class Collects implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private User user;// user_id
	private Article article;// article_id;
	private Date created;

	public Collects() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collects(Integer id, User user, Article article, Date created) {
		super();
		this.id = id;
		this.user = user;
		this.article = article;
		this.created = created;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Collects [id=" + id + ", user=" + user + ", article=" + article + ", created=" + created + "]";
	}

}

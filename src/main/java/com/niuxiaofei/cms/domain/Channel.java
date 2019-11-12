package com.niuxiaofei.cms.domain;

import java.io.Serializable;

public class Channel implements Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: 栏目
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;

    private String description;

    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}
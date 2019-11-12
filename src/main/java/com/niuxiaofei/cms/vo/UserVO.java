package com.niuxiaofei.cms.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.niuxiaofei.cms.domain.User;

public class UserVO extends User implements Serializable{
	private String repassword;
	
	@NotNull(message="密码不能为空")
	@Size(max=18,min=6,message="长度必须在6-18之间")
	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public String toString() {
		return "UserVO [repassword=" + repassword + "]";
	}

	
	
}

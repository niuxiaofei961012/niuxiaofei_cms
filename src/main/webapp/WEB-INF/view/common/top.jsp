<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/css/jquery/screen.css">
<div class="container">
	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="/" title="CMS"><img alt="CMS"
			src="/resource/images/logo.png"></a>

		<!-- 搜索框：在专业高级二学完ElasticSearch后实现 -->
		<form class="form-inline" action="${pageContext.request.contextPath}/search">
			<div class="input-group">
				<input type="text" name="key" class="form-control"
					placeholder="输入关键字..." aria-label="key"
					aria-describedby="basic-addon1" value="${key}">
				<div class="input-group-prepend">
					<button class="input-group-btn btn btn-outline-primary"
						id="basic-addon1" type="submit">搜索</button>
				</div>
			</div>
		</form>

		<!-- 右边登录注册 -->
		<ul class="nav">
			<c:choose>
				<%-- 登录显示用户菜单 --%>
				<c:when test="${sessionScope.LOGIN_GENERAL != null}">
					<li class="nav-item"><a class="nav-link" href="/my/home">
							<img alt="" src="/resource/images/default_avatar.png"
							style="max-height: 2.5rem" class="rounded img-fluid">
					</a></li>
					<li class="nav-item">
						<div class="dropdown" style="padding-top: 0.4rem;">
							<a href="#" class="nav-link dropdown-toggle" role="button"
								id="dropdownMenuButton" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <c:out
									value="${u.nickname}" default="CMS-User" />
							</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="/my/">个人主页</a> <a
									class="dropdown-item" href="#">个人设置</a> <a
									class="dropdown-item" href="#">我的文章</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="/logout">退出</a>
							</div>
						</div>
					</li>
				</c:when>
				<c:otherwise>
					<%-- 未登录显示登录注册链接 --%>
					<li class="nav-item"><a class="nav-link" href="/toReg">注册</a></li>
					<li class="nav-item"><a class="nav-link" href="/toLogin">登录</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
</div>
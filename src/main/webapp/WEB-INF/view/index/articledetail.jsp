<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>文章详情</title>
</head>
<body>
	<a href="/">返回</a>
	<div class="container" align="center">
		<dl>
			<dt>
				<h2>${map.title }</h2>
				<h5>作者:${map.nickname} 发布时间: ${map.updated}  点击量:${map.hits} </h5>
			</dt>
			<dd >${map.content}</dd>
		</dl>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	$(function(){
		id = ${map.id};
		$.post(
			"${pageContext.request.contextPath}/article/updateHits",
			{id:id},
			function(i){
				
			},
			"json"
		);
	})
</script>
</body>
</html>
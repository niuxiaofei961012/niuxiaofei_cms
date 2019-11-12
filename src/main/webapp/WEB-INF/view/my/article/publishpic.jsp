<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布图片文章</title>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<!-- <h1 align="center">发布图片文字</h1> -->

		<div class="form-group">
			<button type="button" class="btn btn-warning" onclick="addDiv()">增加</button>
			<button type="button" class="btn btn-dark" onclick="publish()">发布</button>
		</div>
		<form action="/article/publishpic" method="post" enctype="multipart/form-data" id="form1">
			<div class="form-group">
				<label>图片标题</label> <input type="text" name="title"
					class="form-control">
			</div>
			<div>
				选择文章形式
				<select name="type">
					<option>请选择</option>
					<c:forEach items="${type}" var="t">
						<option value="${t.getValue()}">${t.getKey()}</option>
					</c:forEach>
				</select>
				选择标题样式
				<select name="titleType">
					<option>请选择</option>
					<c:forEach items="${style}" var="s">
						<option value="${s.getValue()}">${s.getKey()}</option>
					</c:forEach>
				</select>
			</div>
			
			<div id="mdiv" class="form-group">
				<div class="card" style="float: left">
					<input type="file" name="files">
					<div class="card-body">
						<textarea rows="5" cols="40" name="picContents"></textarea>
					</div>
				</div>
			</div>
		</form>

	</div>
	<script type="text/javascript">
		function addDiv() {
			//增加上传图片 div
			$("#mdiv").append("<div class='card' style='float: left'> <input type='file' name='files'> <div class='card-body'> <textarea rows='5' cols='40' name='picContents'></textarea></div></div>")

		}
		
		//发布
		function publish(){
			$("#form1").submit();
		}
	</script>
	<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</body>
</html>
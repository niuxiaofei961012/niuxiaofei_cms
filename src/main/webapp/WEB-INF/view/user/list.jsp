<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户列表</title>
</head>
<body>
	<div class="container-fluid">
		<div>
			<div class="form-group form-inline">
				<label for="username">用户名</label> <input type="text"
					class="form-control" id="username" placeholder="查询条件"
					value="${map.username }"> <label for="locked">状态</label> <select
					class="form-control" id="locked">
					<option value="">全部</option>
					<option value="0">正常</option>
					<option value="1">禁用</option>
				</select>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-success" onclick="query()">查询</button>
				&nbsp;
				<a href="admin">返回</a>
			</div>
			<table class="table table-hover table-dark">
				<thead>
					<tr>
						<th>序号</th>
						<th>用户名</th>
						<th>性别</th>
						<th>生日</th>
						<th>注册日期</th>
						<th>修改日期</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="u" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${u.username }</td>
							<td>${u.gender==0?"男":"女" }</td>
							<td>${u.birthday }</td>
							<td>${u.create_time }</td>
							<td>${u.update_time }</td>
							<td><c:if test="${u.locked==1}">
									<button type="button" class="btn btn-success"
										onclick="updateLocked(${u.id},this)">禁用</button>
								</c:if> <c:if test="${u.locked==0}">
									<button type="button" class="btn btn-success"
										onclick="updateLocked(${u.id},this)">正常</button>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="mx-auto form-inline" style="width: 200px;">
				${info}
			</div>
		</div>
</body>
<script type="text/javascript">
	$(function(){
		$(".page-link").click(function(){
			var url = $(this).attr("data");
			$('#content-wrapper').load(url);
		})
			$("#locked").val('${locked}');
	})
	
	
	function updateLocked(id,obj){
		$.ajax({
			type:"post",
			url:"/user/updateLocked",
			data:{id:id,locked:$(obj).text()=="正常"?1:0},
			success:function(i){
				if(i){
					var text = $(obj).text()=="正常"?"禁用":"正常";
					var style = text == "正常"?"btn btn-success":"btn btn-success";
					$(obj).text(text);
					$(obj).attr("class",style);				
				}
			}
		})
	}
	
	//查询
	function query(){
		var url="/user/selects?username="+$("#username").val()+"&locked="+$("#locked").val()
			$("#content-wrapper").load(url);
	}
</script>
</html>
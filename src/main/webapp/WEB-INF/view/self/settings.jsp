<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.bobo.cms.utils.*"%>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="container-fulid" align="center">
		<h3>
			文章标题样式设置
			</h1>
			<hr />
			<div>

				<form action="" id="form1">
					<div class="form-group">
						<label for="titleColor">标题设置:</label>
				
						<c:forEach items="${styles }" var="c">
							<input type="radio" name="styles" value="${c.getCode() }" ${map.styles==c.getCode()?"checked":"" }>${c.getValue() }
    					</c:forEach>
					</div>
					
					<button type="button" class="btn btn-info" onclick="save()">设置</button>
					
				</form>
				
			</div>
	</div>
	
	<script type="text/javascript">
	function save(){
		$.post("/styles/settings",$("#form1").serialize(),function(obj){
			if(obj){alert("设置成功")}
			else{
				alert("设置失败")
			}
		})
		
		
	}
	
	
	</script>
</body>
</html>
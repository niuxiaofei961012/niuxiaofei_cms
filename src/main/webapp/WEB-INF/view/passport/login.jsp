<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>欢迎回来</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/resource/css/bootstrap.min.css"/>
 	<link rel="stylesheet" type="text/css" href="/resource/css/cms.css"/>
 <link rel="stylesheet"  type="text/css"  href="/resource/css/jquery/screen.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    	
    	
    </style>
  </head>
  <body>
    <jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	
	<!-- 登录注册页面 -->
	<div style="height: 50px;"></div>
	
	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
				<div class="passport_panel">
					<div class="card">
					  <div class="card-header">欢迎回来</div>
					  <div class="card-body">
									<div class="form-group">
										<span style="color: red">${msg}</span>
									</div>
					    <form:form action="login" method="post" id="valiateform" modelAttribute="user">
					    	<div class="form-group">
					    		<label for="username">用户名</label>
					    		<form:input path="username" id="username" placeholder="请输入用户名"
					    		 class="form-control" value="${username}"/>
					    	</div>
					    	<div class="form-group">
					    		<label for="password">密码</label>
					    		<form:password path="password" id="password" placeholder="请输入密码"
					    		 class="form-control"/>
					    	</div>
									<button type="submit" class="btn btn-info">登录</button>
								<p class="w-100" align="right">
									如果没有有帐号，请<a href="/toReg">点这里注册</a>
								</p>
						</form:form>
						</div>
					  </div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
					<img src="/resource/images/guest.jpg" alt="..." class="rounded-circle img-thumbnail"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<img src="/resource/images/guest1.jpg" alt="..." class="rounded-circle img-thumbnail"/>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div><br/><br/></div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
	
	<script type="text/javascript">
		$(function(){
			$("#valiateform").validate({
				rules:{
					username:{
						required:true,
						rangelength:[2,10],
					},
					password:{
						required:true,
						rangelength:[6,18],
					}
				},
				messages:{
					username:{
						required:"用户名不能为空",
						rangelength:"用户名必须是2-10位",
					},
					password:{
						required:"密码不能为空",
						rangelength:"密码必须是6-18位",
					}
				}
			})
		})
	</script>
  </body>
</html>
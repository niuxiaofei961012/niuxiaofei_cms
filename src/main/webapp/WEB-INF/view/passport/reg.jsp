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
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>欢迎注册</title>

<!-- Bootstrap -->
<style type="text/css">
.form-inline label {
	display: inline-block;
}
</style>

</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
				<div class="passport_panel">
					<div class="card">
						<div class="card-header">欢迎注册</div>
						<div class="card-body">
										<span style="color: red">${msg}</span>
								<form:form modelAttribute="userVO" id="formValidate" action="reg" method="post">
									
									<div class="form-group">
										<label for="username">用户名</label>
										<form:input path="username" id="username" class="form-control" />
										<form:errors path="username" cssStyle="color:red"/>
									</div class="form-group">
										<label for="password">密码</label>
										<form:password path="password" id="password" class="form-control"/>
										<form:errors path="password" cssStyle="color:red"/>
									<div class="form-group">
										<label for="repassword">确认密码</label>
										<form:password path="repassword" id="repassword" class="form-control"/>
										<form:errors path="repassword" cssStyle="color:red"/>
									</div>
									<div class="form-group">
										<label for="nickname">昵称</label>
										<form:input path="nickname" id="nickname" class="form-control" />
										<form:errors path="nickname" cssStyle="color:red"/>
									</div>
									<div class="form-group">
										<label for="gender">性别</label>
										<form:radiobutton path="gender" value="0" />男
										<form:radiobutton path="gender" value="1" />女
									</div>
									<%-- <div class="form-group">
										<label for="birthday">出生日期</label>
										<form:input path="birthday" id="birthday" class="form-control" onclick="WdatePicker()"/>
									</div> --%>
									<button type="submit" class="btn btn-secondary">注册</button>
								</form:form>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br /> <br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />
</body>		
<script type="text/javascript">
	$(function(){
		$("#formValidate").validate({
			rules:{
				username:{
					required:true,
					rangelength:[2,10],
				},
				password:{
					required:true,
					rangelength:[6,18],
				},
				repassword:{
					required:true,
					rangelength:[6,18],
					equalTo:"#password",
				},
				birthday:{
					required:true,
				},
				nickname:{
					required:true,
					rangelength:[4,10],
				},
			},
			messages:{
				username:{
					required:"用户名不能为空",
					rangelength:"必须是2-10位",
				},
				password:{
					required:"密码不能为空",
					rangelength:"必须是6-18位",
				},
				repassword:{
					required:"密码不能为空",
					rangelength:"必须是6-18位",
					equalTo:"两次密码必须一致",
				},
				birthday:{
					required:"出生日期不能为空",
				},
				nickname:{
					required:"昵称不能为空",
					rangelength:"必须是4-10位",
				},
			}
		})
	})
</script>
</html>

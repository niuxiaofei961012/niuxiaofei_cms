<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>CMS系统</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	<div>
		<br />
	</div>
	<div class="container">
		<div class="row">
			<!-- 栏目-->
			<div class="col-md-2 ">
				<ul class="list-group">
					<li class="list-group-item  text-center" id="channel"><a
						class="channel" href="/">热门</a></li>

					<c:forEach items="${channels}" var="c">
						<li class="list-group-item text-center" id="channel${c.id }"><a
							class="channel" href="/?channelId=${c.id}">${c.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中间内容主体区 -->
			<div class="col-md-7 split min_h_500">
				<!-- 默认显示图片轮播+热点内容 -->
				<c:if test="${article.channelId ==null }">

					<div id="hot">
						<div id="carousel" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<li data-target="#carousel" data-slide-to="0" class="active"></li>
								<li data-target="#carousel" data-slide-to="1"></li>
								<li data-target="#carousel" data-slide-to="2"></li>
							</ol>
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img class="d-block w-100" src="/pic/4.jpg" alt="First slide">
								</div>
								<div class="carousel-item">
									<img class="d-block w-100" src="/pic/5.jpg" alt="Second slide">
								</div>
								<div class="carousel-item">
									<img class="d-block w-100" src="/pic/7.jpg" alt="Third slide">
								</div>
							</div>
							<a class="carousel-control-prev" href="#carousel" role="button"
								data-slide="prev"> <span class="carousel-control-prev-icon"
								aria-hidden="true"></span> <span class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carousel" role="button"
								data-slide="next"> <span class="carousel-control-next-icon"
								aria-hidden="true"></span> <span class="sr-only">Next</span>
							</a>
						</div>
						<br>
						<!-- 新闻热点 -->
						<c:forEach items="${hotArticles}" var="c">
							<div class="media">
								<img class="align-self-start mr-3" src="/pic/${c.picture }"
									alt="no pic">
								<div class="media-body">
									<%-- <h5 class="mt-0" style="
							<c:if test="${c.titleType==1}">font-style:italic</c:if>
							<c:if test="${c.titleType==2}">font-weight:800</c:if>
							<c:if test="${c.titleType==3}">color:red</c:if>
							"> --%>
									${c.title }	&nbsp;&nbsp;&nbsp;<a href="/get?id=${c.id }">详情</a>
									
									${c.zhaiYao}
									<p class="blog_item_footer">
										<span class="glyphicon glyphicon-user" title="作者"></span>作者：${c.username}&nbsp;&nbsp;
										&nbsp; <span class="glyphicon glyphicon-time" title="发布时间"></span>发布时间：&nbsp;
										${c.updated }&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
									<span>
									<%-- 	${c.content} --%>
									</span>
								</div>
							</div>

							<br />
						</c:forEach>
					</div>
					<div id="page">${pageInfo}</div>
				</c:if>
				<c:if test="${article.channelId!=null }">
					<!-- 分类 -->
					<div id="category">
						<ul class="nav">
							<!--栏目下所有 分类 -->
							<li class="nav-item" id="cat"><a class="nav-link"
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categorys}" var="c">
								<li class="nav-item" id="cat${c.id }"><a class="nav-link"
									href="/?channelId=${c.channelId}&categoryId=${c.id}">${c.name }</a></li>
							</c:forEach>
						</ul>
					</div>
					<br />
					<!-- 文章 -->
					<div id="article">
						<c:forEach items="${articles}" var="c">
							<div class="media">
								<img class="align-self-start mr-3" src="/pic/${c.picture }"
									alt="no pic" width="50px" height="50px">
								<div class="media-body">
									<h5 class="mt-0"
										style="
							<c:if test="${c.titleType==1}">font-style:italic</c:if>
							<c:if test="${c.titleType==2}">font-weight:800</c:if>
							<c:if test="${c.titleType==3}">color:red</c:if>
							"
										 >${c.title} &nbsp;&nbsp;&nbsp; <a href="javascript:detail(${c.id},${c.type})">跳转详细信息</a>
									</h5>
									<p class="blog_item_footer">
										<span class="glyphicon glyphicon-user" title="作者"></span>作者：${c.username}&nbsp;&nbsp;
										&nbsp; <span class="glyphicon glyphicon-time" title="发布时间"></span>发布：&nbsp;
										${c.updated }&nbsp;&nbsp;&nbsp;&nbsp;
									</p>
								</div>
							</div>
							<br />
						</c:forEach>
					</div>
				</c:if>
			</div>
			<div class="col-md-3">
				<div class="card">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<ol>
							<c:forEach items="${lasts}" var="article">
								<li class="text-truncate"><a href="/get?id=${article.id}">${article.title}</a></li>
							</c:forEach>
						</ol>
					</div>

					<div class="card-header">专题</div>
					<div class="card-body">
						<c:forEach items="${specials}" var="s">
									${s.title}
						<ol>
								<li class="text-truncate"><c:forEach items="${s.articles}"
										var="a" begin="0" end="3">
										<br>
										<a href="">${a.title}</a>
									</c:forEach></li>
							</ol>
							<hr>
						</c:forEach>
					</div>

					<div class="card">
						<div class="card-header">最新图片</div>
						<div class="card-body">
							<c:forEach items="${pics}" var="p">
								<a href="javascript:getPic(${p.id})"> <img alt=""
									src="/pic/${p.url}" width="50px" height="50px">
								</a>
							</c:forEach>
						</div>


					</div>
					<%-- <div class="card">
						<div class="card-header">友情链接</div>
						<ol>
							<li class="text-truncate text-center"><a href="">牛晓飞</a></li>
							<!-- links   link -->
							<c:forEach items="${linkList}" var="l">
							<li class="text-truncate text-center"><a href="${l.url}"
								target="_blank">${l.text}</a></li>
						</c:forEach>
						</ol>
					</div> --%>
				</div>
			</div>
		</div>
		<br />
		<jsp:include page="/WEB-INF/view/common/footer.jsp" />
		<script type="text/javascript">
			//为左侧频道绑定点击事件
			$(function() {
				//为栏目添加高亮的样式
				$("#channel${article.channelId}").addClass(
						'list-group-item-warning');
				$("#cat${article.categoryId}").addClass(
						'list-group-item-warning');
				//分页的点击事件
				$('.page-link').click(function(e) {
					//获取点击的的url
					var url = $(this).attr('data');
					//在中间区域显示地址的内容
					location.replace(url);
				});
			})
			//查看文章明细
			function detail(id, type) {
				//如果是图片频道
				if (type == 1) {
					getPic(id);
				} else {//普通文章
					window.open("/article/get?id=" + id, "_blank")
				}
			}

			function getPic(id) {
				//	 window.open("/getArticleDetail?id="+id);
				window.open("/article/select?id=" + id, "_blank")
			}
		</script>
</body>
</html>
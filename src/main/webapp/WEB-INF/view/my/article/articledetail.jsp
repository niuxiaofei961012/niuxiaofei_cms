<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="keyword" content="中国，人民">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/bootstrap3/css/bootstrap.min.css" />
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

	
<title>文章详情</title>

</head>
<body>
	<div class="container" align="center">
		<dl>
			<dt>
				<h2 id="articleId" data-id="${map.id }">${map.title }</h2>
				<h5>
				作者:${map.nickname } 
				发布时间:  <fmt:formatDate value="${map.updated }" pattern="yyyy-MM-dd HH:mm:ss"/>
				点击量:<b>${map.hits}</b>
				
				<c:if test="${collects==null}">
				   <span class="glyphicon glyphicon-star-empty" data-article-id="${map.id}" onclick="collectArticle(this)" data-user-id="${user==null?0:user.id}"></span>
				</c:if>
				
				 <c:if test="${collects!=null}">
				    <span class="glyphicon glyphicon-star"  data-article-id="${map.id}" data-user-id="${user==null?0:user.id}" onclick="disCollect(this)"></span>
				 </c:if>
				
			   
			    </h5>
			</dt>
			<dd >${map.content }</dd>
		</dl>
	</div>
	
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">

  function disCollect(obj){
	  //获取用户id值
     var userId= obj.dataset.userId;
     //获取文章的id值
     var articleId = obj.dataset.articleId;

	 
      $.post("${pageContext.request.contextPath}/disCollectArticle",{"article.id":articleId,"user.id":userId},function(data){
                  if(data){
                            alert("取消收藏成功");
                            window.location.reload();

                      }else{
                            alert("取消收藏失败");
                        }
             },"json");
     
  }


  function collectArticle(obj){
	  //获取用户id值
     var userId= obj.dataset.userId;
     //获取文章的id值
     var articleId = obj.dataset.articleId;

	 //判断用户是否登录
     if(userId==0){
              window.location.href="${pageContext.request.contextPath}/toLogin";
     }else{
         $.post("${pageContext.request.contextPath}/collectArticle",{"article.id":articleId,"user.id":userId},function(data){
                  if(data){
                            alert("收藏成功");
                            window.location.reload();
                      }else{
                            alert("收藏失败");
                        }
             },"json")
      }
  }


   $(function(){
	        //更新点击量
            updateHit();
	 });



	 

   function updateHit(){
	    //获取Dom对象
	    var articleDom = document.getElementById("articleId");
	    //Dom对象转换程jquery对象的方式 $(articleDom)
	    //把jquery对象转换程dom对象
	    var articleIdDom = $("#articleId")[0];
	    //获取自定义属性的值 data-id   data-id-name="chj"   articleIdDom.dataset.idName
	    var id= articleIdDom.dataset.id;
        $.post("${pageContext.request.contextPath}/article/updateHits",{id:id},function(data){},"json");
            
	 } 
</script>
</body>
</html>
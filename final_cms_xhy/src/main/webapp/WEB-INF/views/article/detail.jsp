<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/public/css/bootstrap.min.css" rel="stylesheet">
<link href="/public/css/index.css" rel="stylesheet">
<title>${article.title }</title>
<script type="text/javascript">
	var articleId = "${id}";
</script>
</head>
<body>
	<nav class="nav justify-content-start" style="background-color: #222;">
		<a class="nav-link navbar-brand" href="/"> 
			<img src="https://v4.bootcss.com/docs/4.3/assets/brand/bootstrap-solid.svg"
			width="30" height="30" alt="">
		</a>
		<c:if test="${USER_SESSION_ID!=null }">
			<a class="nav-link" href="/user/center">发文</a>
			<a class="nav-link" href="/user/center">个人中心</a>
			<a class="nav-link" href="javascript:;">${USER_SESSION_ID.nickname }</a>
			<a class="nav-link" href="/user/logout">退出</a>
		</c:if>
		<c:if test="${USER_SESSION_ID==null }">
			<a class="nav-link" href="/user/login">登录</a>
		</c:if>
	</nav>
	<div class="container-fluid">
		<div class="row offset-1" style="margin-top: 15px;">
			<div class="col-7">
				<h1>${article.title }</h1>
				<div style="margin-top: 10px;margin-bottom: 10px;font-weight: bold;color: #777;">
					<span>${user.nickname }</span> 
					<span><fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
					<span style="font-size: 24px;">收藏</span>
					<span style="font-size: 24px;">已收藏</span>
					<button onclick="tousushow()" class="btn btn-warning">投诉</button>
				</div>
				<div style="font-size: 24">
					${article.content }
				</div>
				<div id="comment">
					<div class="row" style="margin-top: 10px;">
						  <div class="col-10">
							  <form class="was-validated">
							    <textarea class="form-control" rows="2" id="content" placeholder="请输入评论内容" required></textarea>
							  </form>
						  </div>
						  <div style="margin-top: 10px;">
						    <button type="button" class="btn btn-primary" onclick="addComment();">评论</button>
						  </div>
					</div>
					
				</div>
				
				<jsp:include page="../comment/list.jsp"></jsp:include>
			</div>
			
			<div class="col-3">
				<c:if test="${articleList.size()>0 }">
					<div class="right">
						<div>相关文章</div>
						<ul class="list-unstyled">
							<c:forEach items="${articleList }" var="item">
								<li class="media">
									<a href="/article/${item.id }.html"><img style="width: 64px; height: 64px;"	src="${item.picture }" class="mr-3" alt=""></a>
									<div class="media-body">
										<h5 class="mt-0 mb-1"><a href="/article/${item.id }.html">${item.title }</a></h5>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</div>
		</div>
	</div>


	<!-- articletousu -->
	<div class="modal" tabindex="-1" role="dialog" id="tousuModel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Modal title</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      <form>
	      
	      <p>投诉文章地址</p>
	      <p><a href="http://127.0.0.1:8080/article/${id}.html">127.0.0.1:8080/article/${id}.html</a></p>
	      <p>投诉类型</p>
	      <select name="tousuType">
	      	<option value="1">政治敏感话题</option>
	      	<option value="2">宣传色情内容</option>
	      	<option value="3">诈骗信息</option>
	      	<option value="4">侮辱性词汇</option>
	      	</select>
	      <p>投诉详情</p>
	        <textarea rows="" cols="" class="form-control" name="tousucontent"></textarea>
	      </form>
	        
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" onclick="tousu()">投诉</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script type="text/javascript" src="/public/js/jquery.min.1.12.4.js"></script>
	<script type="text/javascript" src="/public/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	function tousushow(){
		$("#tousuModel").modal('show');
	}
	
		$.get("/comment/list",{articleId:articleId},function(res){
			$("#comment").append(res);
		},"html");
		
		function addComment(){
			var content = $("#content").val();
			if(content==null || content==""){
				alert("评论内容不能为空");
				return;
			}
			$.post("/comment/add",{articleid:articleId,content:content},function(res){
				if(res.result){
					$("#content").val("");
					gotoPage(1);
				}else if(res.errorCode==10000){
					alert("你还未登录");
					location.href="/user/login";
				}
			})
		}
		
		
		function tousu(){
			var content=$("[name=tousucontent]").val();
			alert(content)
			$.post('/tousu/add',{tousucontent:content,tousuType:$("[name=tousuType]").val(),articleid:articleId},function(flag){
				
				if(flag.result){
					$("#tousuModel").modal('hide');
					alert("投诉成功")
				}else{
					$("#tousuModel").modal('hide');
					alert(flag.message);
					location="/user/login"
				}
			})
		}
	</script>
</body>
</html>
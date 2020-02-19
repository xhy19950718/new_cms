<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<input type="button" class="btn btn-success" value="添加手残" onclick="add()">
	<table class="table">
		<c:forEach items="${list }" var="list">
			<tr>
				<td>
					<div>
						<a href="${list.url }">${list.text }</a>
						<p><span>时间：</span>${list.created }  <a class="ml-5" href="javascript:;" onclick="del(${list.id})">删除</a></p>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="modal" id="add" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">添加收藏</h5>
       
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         <form>
        	<p>站点名称</p>
        	<input class="form-control" type="text" name="text">
        	<p>URL</p>
        	<input class="form-control" type="text" name="url">
        	<input type="hidden" id="created" name="created">
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input type="button" class="btn btn-primary" value="添加" onclick="doadd()">
      </div>
    </div>
  </div>
</div>
</body>

<script type="text/javascript">
	function del(id){
		alert("adsf")
		$.post("/user/dellike",{id:id},function(flag){
			if(flag){
				reload();
			}
		})
	}
	function add(){
		$("#add").modal('show')
	}
	function doadd(){
		var time=new Date();
		$("#created").val(time);
		$.post('/user/doadd',$("form").serialize(),function(flag){
			if(flag){
				alert("添加成功");
				$("#add").modal('hide')
				reload();
			}else{
				alert("url有误");
			}
			
		})
		
	}
	
</script>
</html>
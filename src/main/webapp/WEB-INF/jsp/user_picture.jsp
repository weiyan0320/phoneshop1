<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>修改头像</title>

		<!-- Bootstrap -->
		<link href="css/bootstrap.css" rel="stylesheet">
		<!-- layui -->
		<link href="css/style.css" rel="stylesheet">
        <link href="css/layui.css" media="all" rel="stylesheet">
		<!--引用自己定义好的样式-->
		<link href="css/yitao.css" rel="stylesheet">
		<script src="js/jquery-3.2.1.js"></script>
		<script src="js/bootstrap.js"></script>
		<script src="js/layui.js" type="text/javascript"></script>
		<!-- 引入jQuery表单校验插件js -->
        <script src="js/jquery.validate.min.js"></script>
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
        
        
        <!--修改头像-->
		<div class="container" style="background: url('img/background.jpg') ;">
			<div class="row">
				<div class="col-md-4"></div>
	
				<div class="col-md-8 col-xs-8">
					<div
						style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 30px;margin-bottom: 30px; background: #fff;">
						<p style="font-size: 15px; text-align: center">修改头像</p>
						<div>&nbsp;</div>
						<form id="myform3" class="form-horizontal" method="post">
							<div class="form-group">
								<div class="layui-input-block">
								    <!-- 显示原头像 -->
									<img style="width:100px;height:100px;" src="${pageContext.request.contextPath }/${user.userimg }">
								
									<button type="button" class="layui-btn" id="test1">
									  <i class="layui-icon">&#xe67c;</i>上传图片
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
        
	
	   <script type="text/javascript">
			layui.use([ 'form','upload','layer' ], function() {
				var form = layui.form;
				var layer=layui.layer;
				var upload = layui.upload;
				var uploadInst = upload.render({
				    elem: '#test1' //绑定元素
				    ,url: '${pageContext.request.contextPath }/userUploadPicture' //上传接口
				    ,done: function(res){
				    	layer.msg('上传成功！', {
							icon : 1,
							time : 2000
						});
				    	window.location.href='${pageContext.request.contextPath }/showuserpicture';//重定向上传图片路径
				    }
				    ,error: function(){
				    	layer.msg('上传失败，请重试！', {
							icon : 5,
							time : 2000
						});
				    }
				  });
			});
		 </script>
	</body>

</html>
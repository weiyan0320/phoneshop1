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
		<title>修改密码</title>

		<!-- Bootstrap -->
		<link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/modules/layer/default/layer.css" rel="stylesheet">
		<!--引用自己定义好的样式-->
		<link href="css/yitao.css" rel="stylesheet">
		<script src="js/jquery-3.2.1.js"></script>
		<script src="js/bootstrap.js"></script>
		<!-- 引入jQuery表单校验插件js -->
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/lay/modules/layer.js" type="text/javascript"></script>
        <style type="text/css">
		a {
			text-decoration: none;
		}
		
		a:hover {
			text-decoration: none;
		}
		/*将表单校验字体颜色变成红色*/
		.error {
			color: red;
		}
		</style>
		<script type="text/javascript">
		//自定义校验规则，检查原密码是否正确
		$.validator.addMethod(
		//规则名称
		"checkOldPassword",
		//效验函数
		function(value, param) {
			//定义一个标记判断是否让规则通过
			var flag=false;//默认不让通过规则，就是提示红色的字
			//value代表输入框的值param代表规则的值写true
			//自定义效验规则，如果原密码错误不通过规则返回false，否则返回true
			$.ajax({
				"async":false,
				"type": "POST",
				"url": "${pageContext.request.contextPath }/checkOldPassword",
				"data" : {"inputpassword":value},
				"success" : function(data) {
					flag=data;
				},
				"dataType":"json"
			});
			
			return !flag;
			
		});
		
		//使用jQuery进行注册表单效验,使用默认规则
		 $(function() {
			$('#myform3').validate({
				//效验规则,根据name
				rules : {
					oldpassword : {
						required : true,
						"checkOldPassword" : true
					},
					password : {
						required : true
					},
					repassword : {
						required : true,
						equalTo : "#password"
					}
					
				},
				messages : {
					oldpassword : {
						required : "原密码不能为空！",
						"checkOldPassword" : "原密码错误"
					},
					password : {
						required : "新密码不能为空！"
					},
					repassword : {
						required : "确认新密码不能为空！",
						equalTo : "两次输入密码不一致！"
					}
				},
				submitHandler:function(form){
					//ajax提交修改密码
					var data = $("#myform3").serialize(); //表单数据
			        $.ajax({
						"async":false,
						"type": "POST",
						"url": "${pageContext.request.contextPath }/changePassword",
						"data" : data,
						"success" : function(data) {
							if(data){
								layer.msg("修改密码成功！",{icon:1,anim:6,time:2000});
							}else{
								layer.msg("修改密码失败！",{icon:5,anim:6,time:3000});
							}
						},
						"dataType":"json"
					});
			    }
			});
			
			//返回上一步
			 $("#backbutton").click(function(){
				 window.history.back(-1); 
	    	 });
			
		});  
		
		</script>
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
        
        
        <!--修改密码-->
		<div class="container" style="background: url('img/background.jpg') ;">
			<div class="row">
				<div class="col-md-4"></div>
	
				<div class="col-md-8 col-xs-8">
					<div
						style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 20px;margin-bottom: 20px; background: #fff;">
						<p style="font-size: 15px; text-align: center">修改密码</p>
						<div>&nbsp;</div>
						<form id="myform3" class="form-horizontal" method="post">
							<div class="form-group">
								<label for="username" class="col-sm-3 col-xs-3 control-label">当前密码</label>
								<div class="col-sm-6 col-xs-6">
									<input type="text" class="form-control" id="oldpassword"
										name="oldpassword" placeholder="请输入当前密码">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3"
									class="col-sm-3 col-xs-3 control-label">新密码</label>
								<div class="col-sm-6 col-xs-6">
									<input type="password" class="form-control" id="password"
										name="password" placeholder="请输入新密码">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3"
									class="col-sm-3 col-xs-3 control-label">确认新密码</label>
								<div class="col-sm-6 col-xs-6">
									<input type="password" class="form-control" id="confirmpwd"
										name="repassword" placeholder="请输入确认新密码">
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-8 col-xs-offset-4 col-xs-8">
									<button type="submit" class="btn btn-info">提交</button>&nbsp;&nbsp;
									<button type="button" class="btn btn-info" id="backbutton">返回</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
        
	

	</body>

</html>
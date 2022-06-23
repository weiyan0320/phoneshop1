<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>个人资料修改</title>

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
    
	
	//自定义效验规则检查手机号码
	$.validator.addMethod(
	//规则名称
	"checkTelephone",
	//效验函数
	function(value, param) {
		
		//第一个字符： ^ 匹配输入字符串的开始位置 -》  ^1 匹配输入第一个字符是1
    	//第二个字符：使用 [a-z]代表字符范围 -》[3-8]代表3到8之间任意一个字符
    	//还剩九个字符：\d 匹配一个数字字符。等价于 [0-9]就是取0到9之间任意一个数字
    	//然后再\d{9} 匹配\d确定的 9 次
    	//$  匹配输入字符串的结束位置
    	//在正则表达式中两边加“/”表示这个范围内的就是正则表达式了，相当于提示分界的作用；
        return /^1[3-8]\d{9}$/.test(value); 
		
		
	});
	
	//使用jQuery进行注册表单效验,使用默认规则
	$(function() {
		$('#myform2').validate({
			//效验规则,根据name
			rules : {
				username : {
					required : true,
					"checkUserName" : true
				},
				password : {
					required : true
				},
				email : {
					required : true,
					email : true
				},
				name : {
					required : true
				},
				sex : {
					required : true
				},
				birthday : {
					required : true
				},
				telephone : {
					required : true,
					//使用自己定义手机号码规则
					"checkTelephone":true
				},
				address : {
					required : true,
				}
				
			},
			messages : {
				username : {
					required : "用户名不能为空！",
					"checkUserName" : "用户名已经存在"
				},
				password : {
					required : "密码不能为空！"
				},
				email : {
					required : "邮箱不能为空！",
					email : "邮箱格式不正确！"
				},
				name : {
					required : "姓名不能为空！",
				},
				sex : {
					required : "性别必须选择一个！"
				},
				birthday : {
					required : "出生日期不能为空！"
				},
				telephone : {
					required : "手机号码不能为空！",
					"checkTelephone" :"请输入正确的手机号码！"
				},
				address : {
					required : "家庭地址不能为空！"
				}

			},
			submitHandler:function(form){
				//ajax提交注册信息
				var data = $("#myform2").serialize(); //表单数据
		        $.ajax({
					"async":false,
					"type": "POST",
					"url": "${pageContext.request.contextPath }/updateuserinfo", 
					"data" : data,
					"success" : function(data) {//对后台返回的json数据进行处理
						if(data.code=="00"){
							//注册成功
							layer.msg("个人资料修改成功!",{icon:1,anim:2,time:2000},function(){
								window.location.href = "${pageContext.request.contextPath }/showuserinfo";
							});
						}else{
							layer.msg("个人资料修改失败！",{icon:5,anim:6,time:3000});
							//显示错误信息
							$("#errormessage").html("<label class='error'>"+data.message+"</label>");
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
	<!--用户注册-->
	<div class="container" style="background: url('img/background.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<p style="font-size: 20px; text-align: center">个人资料修改</p>
				<form id="myform2" class="form-horizontal"  method="post" style="margin-top: 5px;">
				    <input type="hidden" class="form-control" name="uid"  value="${user.uid }">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								name="username" placeholder="请输入用户名" value="${user.username }" disabled="disabled">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id=password
								name="password" placeholder="请输入密码" value="${user.password }">
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="inputEmail3"
								name="email" placeholder="Email" value="${user.email }">
						</div>
					</div>

					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="usercaption"
								name="name" placeholder="请输入姓名" value="${user.name }">
						</div>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> 
							  <input type="radio" name="sex" id="inlineRadio1" value="male" <c:if test="${user.sex== 'male'}">checked="checked"</c:if>/> 男
							</label> 
							<label class="radio-inline"> 
							  <input type="radio" name="sex" id="inlineRadio2" value="female" <c:if test="${user.sex== 'female'}">checked="checked"</c:if>/> 女
							</label> 
							<label class="error" for="sex" style="display: none">您没有第三种选择</label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday" value="${user.birthday }">
						</div>
					</div>
					<div class="form-group">
						<label for="telephone" class="col-sm-2 control-label">手机号码</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="telephone"
								name="telephone" placeholder="请输入手机号码" value="${user.telephone }">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-2 control-label">家庭地址</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="address"
								name="address" placeholder="请输入家庭地址" value="${user.address }">
						</div>
					</div>
					
					<!-- 提示错误信息 -->
                    <div id="errormessage">
						
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-info">提交</button>&nbsp;&nbsp;
							<button type="button" class="btn btn-info" id="backbutton">返回</button>
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

</body>

</html>

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
		<title>添加收货地址</title>

		<!-- Bootstrap -->
		<link href="css/bootstrap.css" rel="stylesheet">
		<link href="css/modules/layer/default/layer.css" rel="stylesheet">
		<!--引用自己定义好的样式-->
		<link href="css/yitao.css" rel="stylesheet">
		<script src="js/jquery-3.2.1.js"></script>
		<script src="js/bootstrap.js"></script>
		<!-- 引入jQuery表单校验插件js -->
        <script src="js/jquery.validate.min.js"></script>
        <!-- 引入省市区jquery插件 -->
        <script src="js/distpicker.js"></script>
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
			$('#myform4').validate({
				//效验规则,根据name
				rules : {
					receiver_name : {
						required : true,
					},
					receiver_address : {
						required : true,
					},
					receiver_province : {
						required : true,
					},
					receiver_city : {
						required : true,
					},
					receiver_district : {
						required : true,
					},
					receiver_phone : {
						required : true,
						//使用自己定义手机号码规则
						"checkTelephone":true
					}
					
				},
				messages : {
					receiver_name : {
						required : "姓名不能为空！",
					},
					receiver_address : {
						required : "详细地址不能为空！",
					},
					receiver_province : {
						required : "请选择省份！",
					},
					receiver_city : {
						required : "请选择市！",
					},
					receiver_district : {
						required : "请选择区！",
					},
					receiver_phone : {
						required : "手机号码不能为空！",
						"checkTelephone" :"请输入正确的手机号码！"
					}
				},
				submitHandler:function(form){
					//ajax提交修改密码
					var data = $("#myform4").serialize(); //表单数据
			        $.ajax({
						"async":false,
						"type": "POST",
						"url": "${pageContext.request.contextPath }/addship",
						"data" : data,
						"success" : function(data) {
							if(data){
								layer.msg("添加收货地址成功！",{icon:1,anim:2,time:2000},function(){
									window.location.href = "${pageContext.request.contextPath }/showshippinginfo";
								});
							}else{
								layer.msg("添加收货地址失败！",{icon:5,anim:6,time:3000});
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
        
        
        <!--收货地址界面-->
		<div class="container" style="background: url('img/background.jpg') ;">
			<div class="row">
				<div class="col-md-4"></div>
	
				<div class="col-md-8 col-xs-8">
					<div
						style="width: 460px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 20px;margin-bottom: 20px; background: #fff;">
						<p style="font-size: 15px; text-align: center">添加收货地址</p>
						<div>&nbsp;</div>
						<form id="myform4" class="form-horizontal" method="post">
							
							<div class="form-group">
								<label for="receiver_name" class="col-sm-3 control-label">收货人姓名:</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="receiver_name"
										name="receiver_name" placeholder="请输入收货人姓名" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="receiver_phone" class="col-sm-3 control-label">手机号码</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="receiver_phone"
										name="receiver_phone" placeholder="请输入手机号码" value="">
								</div>
							</div>
							<div class="form-group">
								<label for="cityaddrss" class="col-sm-3 col-xs-3 control-label">所在地区:</label>
								<div class="col-sm-8 col-xs-8">
								      <div data-toggle="distpicker" style="margin-top: 8px;" class="col-sm-8 col-xs-8">
									      <select data-province="-- 选择省 --" id="receiver_province" name="receiver_province"></select>
									      <select data-city="-- 选择市 --" id="receiver_city" name="receiver_city"></select>
									      <select data-district="-- 选择区 --" id="receiver_district" name="receiver_district"></select>
								      </div>
									 
								</div>
							</div>
							<div class="form-group">
								<label for="receiver_address"
									class="col-sm-3 col-xs-3 control-label">详细地址:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" class="form-control" id="receiver_address"
										name="receiver_address" placeholder="请输入街道、门牌号、小区等" value="">
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/layui.css">
<script src="js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="js/layui.js"></script>
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md12">
				<div class="layui-card">
					<div class="layui-card-header">设置我的资料</div>
					<div class="layui-card-body" pad15="">
					<form id="formData" class="layui-form">
						<div class="layui-form" lay-filter="">
							<div class="layui-form-item">
							    <input type="hidden" name="adminId" value="${admin.adminId }"/>
								<label class="layui-form-label">我的角色</label>
								<div class="layui-input-inline">
									<select name="role" lay-verify="" disabled="disabled">
										<option value="1" selected="">管理员</option>
									</select>
								</div>
								<div class="layui-form-mid layui-word-aux">当前角色不可更改为其它角色</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">用户名</label>
								<div class="layui-input-inline">
									<input type="text" name="adminName" placeholder="请输入用户名"  disabled="disabled" value="${admin.adminName }"
										class="layui-input">
								</div>
								<div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">密码</label>
								<div class="layui-input-inline">
									<input  name="adminPass" placeholder="请输入密码" type="password" value="${admin.adminPass }"  lay-verify="required|oldPass"
										class="layui-input">
								</div>
								<div class="layui-form-mid layui-word-aux">6到16个字符</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">手机</label>
								<div class="layui-input-inline">
									<input type="text" name="adminPhone" placeholder="请输入手机" value="${admin.adminPhone }" lay-verify="phone"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button class="layui-btn" lay-submit="" lay-filter="*">确认修改</button>
								</div>
							</div>
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(function() {
		layui.use([ 'layer', 'table', 'form', 'upload' ],function() {
			var table = layui.table;
			var layer = layui.layer;
			var form = layui.form;
			form.verify({
				oldPass: function (value) {
	                if (!/^[a-z0-9_-]{6,18}$/.test(value)) {
	                    return "密码格式不正确！";
	                }
	            },
	        });
			form.on('submit(*)',function() {
				$.ajax({
						type : "post",
						url : "${pageContext.request.contextPath }/updateAdmin",
						data : $("#formData").serialize(),
						success : function(data) {
							if (data == "success") {
								layer.msg("	修改成功！",{
													icon : 1,
													shade : 0.4,
													anim : 1,
													time : 1000
												});
							} else {
								layer.msg("修改失败，请重试！",{
													icon : 5,
													shade : 0.4,
													anim : 6,
													time : 1000
												});
							}
						}
					});
					return false;
				});
		});
	});
</script>
</html>

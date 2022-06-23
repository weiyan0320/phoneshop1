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
		<title>地址管理</title>

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
			$(function(){
			   //设置默认地址
	    	   $("#buttondefaultship").click(function(){
	    		   var val = $('input[name="radioshipp"]:checked').val(); 
				   window.location.href = "${pageContext.request.contextPath }/updateDefaultShip?id="+val;
				   layer.msg('设置成功', { icon: 1, shade: 0.4, time: 1000 });
	    	   });
			   //显示添加收货地址页面
	    	   $("#buttonaddship").click(function(){
				   window.location.href = "${pageContext.request.contextPath }/showaddship";
	    	   });
			});
			 //当用户点击删除按钮，删除收货地址
            function deleteShip(sid)
            {
	       		layer.confirm("你确定要删除该收货地址？",function(){
	       			  location.href="${pageContext.request.contextPath }/deleteShip?id="+sid;
				      layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 });
			     })
            }
			 
            //编辑收货地址
            function editShip(sid)
            {
         	   location.href="${pageContext.request.contextPath }/showeditship?id="+sid;
            }
		</script>
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
        
        
        <!--收货地址界面-->
		<div class="container" style="background: url('img/background.jpg') ;">
			<div class="row">
				<div class="col-md-1"></div>
	
				<div class="col-md-10 col-xs-10">
					<div style="width: 100%; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 20px;margin-bottom: 20px; background: #fff;">
						<h1>
							<a name="section3">地址管理</a>
						</h1>
						<hr />
						<button type="button" class="btn btn-info" id="buttonaddship">添加</button>
						<button type="button" class="btn btn-info" id="buttondefaultship">设置为默认地址</button>
						<hr />
						<form id="myform4" class="form-horizontal" method="post">
							<table class="table table-bordered" >
								<tbody>
									<tr class="active">
										<th>是否默认</th>
										<th>收货人</th>
									    <th>电话</th>
									    <th>所在地区</th>
									    <th>详细地址</th>
									    <th>操作</th>
									    <c:forEach items="${shipinfoList }" var="ship">
											<tr>
											    <td width="10%">
											     <label><input name="radioshipp" type="radio" value="${ship.id}" <c:if test="${ship.default_flag== '1'}">checked="checked"</c:if>/></label>
											    </td>
												<td width="15%">${ship.receiver_name}</td>
												<td width="15%" style="vertical-align:middle">${ship.receiver_phone}</td>
												<td width="25%" style="vertical-align:middle">${ship.receiver_province}${ship.receiver_city}${ship.receiver_district}</td>
												<td width="15%" style="vertical-align:middle"><span class="subtotal">${ship.receiver_address}</span></td>
												<td width="20%">
												   <button type="button" class="btn btn-warning" onclick="editShip('${ship.id}')">编辑</button>&nbsp;&nbsp;
												   <button type="button" class="btn btn-danger" onclick="deleteShip('${ship.id}')">删除</button>
												</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
        


	</body>

</html>
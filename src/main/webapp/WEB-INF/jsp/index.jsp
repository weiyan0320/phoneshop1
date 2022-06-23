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
		<title>手机专卖店首页</title>

		<!-- Bootstrap -->
		<link href="css/bootstrap.css" rel="stylesheet">
		<!--引用自己定义好的样式-->
		<link href="css/yitao.css" rel="stylesheet">
		<script src="js/jquery-3.2.1.js"></script>
		<script src="js/bootstrap.js"></script>
		
	</head>

	<body>
		<!-- 引入header.jsp -->
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	
		<div class="container" style="margin-top:10px ;">
			
			<div class="row">
				<div class="container">
					<div class="row" >
						
						<!-- 使用jstl遍历存储在request域中商品的集合 ,需要引入标签-->
						<c:forEach items="${newProductList }" var="newproduct">						   
						   <div class="col-xs-5 col-sm-4 col-md-2 img" style="text-align: center;width:190px;height:230px;margin-left:4px">
							<a href="${pageContext.request.contextPath }/productInfo?pid=${newproduct.pid}"><img src="${pageContext.request.contextPath }/${newproduct.pimage }" style="width:160px;height:170px;" /></a>
							<p><a href="${pageContext.request.contextPath }/productInfo?pid=${newproduct.pid}">${newproduct.pname }</a></p>
							<p><span style="font-size:17px;color:#e33333">¥${newproduct.shop_price }</span>&nbsp;&nbsp;</p>
						   </div>						
						</c:forEach>
					</div>
				</div>			
			</div>
		</div>
		
		<div class="container" style="margin-top:10px ;">
			
			<div class="row">
				<div class="container">
					<div class="row">	
						<!-- 使用jstl遍历存储在request域中商品的集合 ,需要引入标签-->
						<c:forEach items="${hotProductList }" var="hotproduct">
						   
						   <div class="col-xs-5 col-sm-4 col-md-2 img" style="text-align: center;width:190px;height:230px;margin-left:4px">
							<a href="${pageContext.request.contextPath }/productInfo?pid=${hotproduct.pid}"><img src="${pageContext.request.contextPath }/${hotproduct.pimage }" style="width:160px;height:170px;" /></a>
							<p><a href="${pageContext.request.contextPath }/productInfo?pid=${hotproduct.pid}">${hotproduct.pname }</a></p>
							<p><span style="font-size:17px;color:#e33333">¥${hotproduct.shop_price }</span>&nbsp;&nbsp;</p>
						   </div>
						</c:forEach>
					</div>
				</div>		
			</div>
		</div>
	</body>

</html>
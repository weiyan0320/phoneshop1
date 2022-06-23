<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>订单成功</title>

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
	   
        
       <div class="container" >
            <div class="row" style="margin-top:50px;margin-bottom:140px;" >
                <div class="col-md-8">
                    <img style="margin-left:100px;float:left;height:450px;width:350px;" src="img/paysuccess.png">
                    <span style="color:#7ABD54;font-size:18px;">感谢您，订单支付成功！</span>
                </div>
                <div class="col-md-1" style="margin-top:10px;">
                    <a href="${pageContext.request.contextPath }/index">继续购物</a>
                    <a href="${pageContext.request.contextPath }/findReadyToDeliverOrder">查看订单</a>
                </div>
            </div>
       </div>
    
</body>
</html>
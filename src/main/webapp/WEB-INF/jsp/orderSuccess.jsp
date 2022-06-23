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
	   
        <!-- 如果用户是货到付款 -->
        <c:if test="${order.payment_type==1 }">
           <div class="container" >
                <div class="row" style="margin-top:50px;margin-bottom:140px;" >
                    <div class="col-md-8">
                        <img src="img/icon.png"><span style="color:#7ABD54;font-size:18px;">感谢您，订单提交成功！</span>我们会尽快为您发货~ 订单号：${order.oid }
                    </div>
                    <div class="col-md-1" style="margin-top:10px;">
                        <a href="${pageContext.request.contextPath }/index">继续购物</a>
                    </div>
                    <div  class="col-md-3" style="margin-top:7px;">
		                                      货到付款: <span style="font-size:18px;color:#e4393c">￥${order.total }元</span>
	                </div>
                </div>
           </div>
        </c:if>
       
       <!-- 如果用户是在线支付 -->
       <c:if test="${order.payment_type==2 }">
           <div class="container" style="margin-top:10px;">
               <div class="row" style="margin-top:30px;">
                   <div class="col-md-8">
                      <img src="img/icon.png"><span style="color:#7ABD54;font-size:18px;">订单提交成功，请尽快付款！</span><span>订单号： ${order.oid } </span>
                   </div>
                   <div  class="col-md-4" style="text-align:right;">
		                               应付金额: <span style="font-size:20px;color:#e4393c">￥${order.total }元</span>
	                </div>
               </div>
               <div class="row" style="margin-top:10px;margin-left:10px;">
                    <div>
                        <span class="pay_word2">请您在提交订单后 <em style="color:red;">24小时</em> 内完成支付，超时订单会自动取消。</span>
                    </div>
                   
               </div>
               
               <!-- 支付选择 -->
               <div class="row" style="text-align:center;margin-top:30px;">
                    
                        <div>
                            <!-- 支付宝支付 -->
                            <label class="radio-inline">
						       <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="支付宝" checked> <img alt="" src="img/pay/pay0.jpg">
						    </label>
                        </div>
	                    
	                    <!-- 支付按钮 -->
		                <div style="text-align:center;margin-top:40px;margin-bottom:20px;">	  
					      <input id="payOrder" type="button" width="100" value="立即支付"  style="background:#e4393c ;height:35px;width:100px;color:white;">	
			            </div>
                  
		              <script type="text/javascript">
		                  $(function(){
		               	   $("#payOrder").click(function(){
		               		//访问后台
			               	location.href="${pageContext.request.contextPath }/orderPay?orderNumber= ${order.oid }";
		               	   });
		                  });
		               </script>
                    
                 </div>
                 
           </div>
       
       </c:if>
       
       
   
       
       
      
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<style>
/* 设置下拉框鼠标以上，更改背景颜色 */
.dropdown-menu > li > a:hover {
   background: #5bc0de!important;
}
.dropdown-menu > li > a {
   display: block;
   padding: 3px 20px;
   clear: both;
   font-weight: normal;
   line-height: 1.42857;
   color: #333;
   white-space: nowrap;
}
</style>
<div class="container" style="margin-top:5px;background-color:#f7f9f9">
	<!--网站的头部-->
	<div class="row">
		<div class="col-md-6 col-sm-6 col-xs-6"
			style="padding: 10px;margin-top:5px;">
			<ul class="list-inline">
				<li class="hidden-xs" style="color:#23527c;">你好，欢迎来到手机专卖店</li>
				<c:if test="${empty sessionScope.user }">
					<li><a href="${pageContext.request.contextPath }/login" style="color:#23527c;">你好，请登录</a></li>
					<li><a href="${pageContext.request.contextPath }/register" style="color:#23527c;">免费注册</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
				  <li style="color:#23527c;">欢迎您，<span style="color:red">${sessionScope.user.username }</span></li>
				  <li><a href="${pageContext.request.contextPath }/deleteUser" style="color:#23527c;">退出登录</a></li>
				</c:if>
				
			</ul>
		</div>
		<div class="col-md-6 col-sm-6 col-xs-6"
			style="padding: 15px;">
			<ul class="list-inline text-right ">
			    <!-- 个人基本信息 -->
			    <c:if test="${!empty sessionScope.user }">
				    <li class="dropdown">
				       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							<div style="margin-top:-5px;float:left;width:30px; height:30px; border-radius:50%; overflow:hidden;">
							<img src="${pageContext.request.contextPath }/${user.userimg }" style="width:30px;height:30px;">
							</div>&nbsp;&nbsp;<span style="color:#23527c;">${sessionScope.user.username}</span> 
						</a>
						<ul class="dropdown-menu" style="min-width:100px">
						    <li><a href="${pageContext.request.contextPath }/showpassword">修改密码</a></li>
						    <li><a href="${pageContext.request.contextPath }/showuserpicture">修改头像</a></li>
						    <li><a href="${pageContext.request.contextPath }/showshippinginfo">地址管理</a></li>
							<li><a href="${pageContext.request.contextPath }/showuserinfo">个人资料修改</a></li>
						</ul>
					</li>
				</c:if>
				<li><a href="${pageContext.request.contextPath }/cart"><span
						class="glyphicon glyphicon-shopping-cart" style="color: #FF0000;"
						aria-hidden="true"></span><span style="color:#23527c;">我的购物车</span></a></li>
				<li class="dropdown">
			        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="color: #23527c;">我的订单</a>
					<ul class="dropdown-menu" style="min-width:100px">
					    <li><a href="${pageContext.request.contextPath }/showReadyPayOrder">待付款</a></li>
					    <li><a href="${pageContext.request.contextPath }/findReadyToDeliverOrder">待发货</a></li>
					    <li><a href="${pageContext.request.contextPath }/findReadyToReceiveOrder">待收货</a></li>
						<li><a href="${pageContext.request.contextPath }/findFinishOrder">完成</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>

</div>
<!--logo和搜索-->
<div class="container" style="margin-top: 5px;">
	<div class="row" style="background: #f7f9f9;">
		<!--logo-->
		<div class="col-md-6 col-sm-6 col-xs-6 hidden-xs">
			<img src="img/logo.png" />
		</div>
		<!-- 站内搜索，对键盘弹起事件绑定函数 -->
		<script type="text/javascript">
		
		  //div设置鼠标移上事件绑定的方法,obj接收事件绑定函数传输该标签的对象this
		  function funOnMouseOver(obj)
		  {
			  //获取div对象调用方法设置背景颜色
			  $(obj).css("background","#e7ecf0");
		  }
		  //鼠标移出div触发事件调用的方法
		  function funOnMouseOut(obj)
		  {
			 //获取div对象调用方法设置背景颜色
			 $(obj).css("background","#fff");
		  }
		  
		  //当单击div，获取div内容，将该值写入input里面
		  function getDivValue(obj)
		  {
			  //获取单击div的内容
			  var divText=$(obj).text();
			  //将该值写入到输入搜索框中
			  $("#search").val(divText);
			  //还要让提示信息的div隐藏
			  $("#inputdiv").css("display","none");
		  }
		  
		  //当点击搜索下拉div其他地方，让该div隐藏
		  //获取整个页面document文档对象，单击任何地方，让搜索下面提示div隐藏，不管是除div外面要隐藏，其实点击div里面
		  //也是要隐藏，相当于整个文档对象任何地方点击都要隐藏搜索下面提示div
		   $(document).bind('click', function() {  
              
              $('#inputdiv').css('display', 'none'); //点击的不是div或其子元素  ,让该div隐藏
              
          });  
		  
		   function getSearchContent(obj)
		   {
			   //1.obj获取函数传输过来的搜索框对象，通过对象获取文本框输入的值
			   var inputvalue=obj.value;
			   //alert(inputvalue);
			  /*  alert(obj.value); */
			  //2.通过ajax异步不刷新请求，将获取文本框内容，作为商品名称进行商品后台模糊查询
			   $.ajax({
				   
				   url: "${pageContext.request.contextPath }/getProductNameByInput",    //发送请求的地址
				   data: {"inputvalue":inputvalue},  //发送到服务器的数据,json格式的数据，这里是对象格式，将文本框值传输到后台
				   type: "POST",  //请求方式post
				   success: function(data){    //成功请求的回调函数
				     //data封装后台查询出来的List<Product>的json格式字符串对象,里面先是数组[]，数组里面存储对象{}
				     //数据格式为[{},{},{}]集合看做数组[]，里面对象{}
				     //现在要将该json格式的字符串中的所有商品的名称，通过循环动态写div标签并添加商品名称作为内容,使用jQuery
				    
				     //判断data数组（集合)json对象是否有数据，有就让div显示
				     if(data.length>0)
			    	 {
				    	 var content="";
					     for(var i=0;i<data.length;i++)
				    	 {
					    	 //data代表json格式的对象
					    	 //循环获取所有的商品名称拼接div，组成字符串
					    	 //设置一下鼠标移上onmouseover事件和onmouseout移出设置显示div的背景颜色
					    	 content+="<div style='padding:5px;' onmouseover='funOnMouseOver(this)' onmouseout='funOnMouseOut(this)' onclick='getDivValue(this)'>"+data[i].pname+"</div>";
				    	 }
					    
					     //将拼接的字符串通过jQuery写入到指定位置
					     $("#inputdiv").html(content);
					     //让inputdiv默认隐藏现在显示数据，将属性display设置为block
					     $("#inputdiv").css("display","block");
			    	 }
				     
				   },
				   dataType:"json"  //返回值的数据类型是json格式
				});
			   
		   }
		   
		   //使用js提交搜索表单内容,当页面加载完毕
		   $(function(){
			  //当用户单击搜索按钮，判断用户是否输入值，如果没输入，就不提交，否则提交表单给后台
			  $("#buttonsubmit").click(function(){
				  //获取搜索表单中文本框中的值
				   var valueText=$("#search").val();
				   //判断用户是否输入值,如果用户输入就提交，否则就不提交，不做处理
				   if(valueText!="")
				   {
					 //如果用户输入数据，就提交表单搜索框的输入信息，
					 //提交表单搜索框信息
					  $("#myform").submit();
				   }
			  });
			  
			  $(function(){
					//当鼠标指针位于元素上方时,显示下拉框
					$('li.dropdown').mouseover(function() {   
					     $(this).addClass('open');    
					 });
					//当鼠标指针位于离开,隐藏下拉框
					$('li.dropdown').mouseout(function() {        
						 $(this).removeClass('open');    
					}); 
				 });
		   });
		</script>
		<!--搜索-->
		<div class="col-md-6 col-sm-6 col-xs-12" >
             <!-- 这里使用表单形式，将搜索框内容提交给后台 -->
			<form id="myform" action="${pageContext.request.contextPath }/selectProductInfoListByInput" class="navbar-form navbar-right" style="margin-top:30px;" method="get">
			    <!-- 这是搜索输入框 -->
		        <div class="form-group col-xs-8 col-md-7 col-sm-7" style="position:relative;">
			          <!-- 设置一个键盘弹起时间绑定函数，设置 autocomplete属性可以让历史记录不显示  -->
			          <input id="search" name="searchContent" type="text" class="form-control" placeholder="请输入关键字" style="width:170px;" onkeyup="getSearchContent(this)" onblur="hideDiv()" autocomplete="off">
			        
			      <!-- 
			                    绝对定位一个div块,请牢记绝对定位元素的定位值是相对于第一个具有定位属性position 为 relative 或者 absolute的祖先元素
			        z-index 属性设置元素的堆叠顺序。拥有更高堆叠顺序的元素总是会处于堆叠顺序较低的元素的前面。值越大，越在上面显示 ,
			        Z-index 仅能在定位元素上奏效  , 例如 position:absolute;        
			       -->
			          <!-- 默认隐藏该div，设置display为none,当有数据时候让它显示，使用js实现 -->
			          <div id="inputdiv" style="display:none;width:170px;background-color:#fff;position: absolute;z-index:10;">
			               
			          </div>
			          
			         		
		        </div>
		        <div class="col-xs-4 col-md-5 col-sm-5">
		            
		                <!-- 这是搜索按钮，这是一个普通按钮，使用js提交表单 -->
	                    <button type="button" id="buttonsubmit" class="btn btn-primary">搜索</button>
		        </div>
		        </form>
		</div>
	</div>
</div>

<!--导航栏-->
<div class="container" style="margin-top:10px;">
	<div class="row">
		<nav class="navbar navbar-inverse" style="margin-bottom:10px;" >
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="${pageContext.request.contextPath }/index">网站首页</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
				
				<!-- 使用jstl循环进行 -->
				<c:forEach items="${categoryList }" var="category">
				     <li><a href="${pageContext.request.contextPath }/productListInfo?cid=${category.cid}">${category.cname }</a></li>
				</c:forEach>
				

				</ul>

			</div>

		</div>

		</nav>
	</div>

</div>
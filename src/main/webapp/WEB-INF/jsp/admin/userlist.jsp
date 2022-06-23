<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="css/layui.css">
<script type="text/javascript" src="js/jquery.1.12.4.min.js"></script>
<script type="text/javascript" src="js/layui.js"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form">
<div class="layui-form-item" style="margin:15px;height:30px;">
    	<div style="height:40px;width:70px;float:left;line-height:40px;">用户名:</div>
        <input type="text" class="layui-input" style="width:250px;float:left;" id="keyword" name="keyword" value="" lay-verify="" placeholder="请输入用户名" autocomplete="off">
	    <div class="layui-input-inline" style="width:150px;text-align:center;">
	        <a class="layui-btn" id="search" data-type="reload" name="search">
	            <i class="layui-icon"></i>搜索
	        </a>
	    </div> 
</div>
</form>
<div>
    <table id="users" lay-filter="users"></table>
</div>
<div id="formData" style="width:700px;display:none;padding-top:15px;">
	<form class="layui-form" id="userForm">
		<input type="hidden" name="uid" id="uid" />
		<div class="layui-form-item">
			    <label class="layui-form-label">用户名</label>
			    <div class="layui-input-block">
			      <input type="text" name="username" id="username" required lay-verify="required" value="" class="layui-input" disabled="disabled"/>
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">真实姓名</label>
			    <div class="layui-input-block">
			      <input type="text" name="name" id="name" required lay-verify="required" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">性别</label>
			    <div class="layui-input-block">
	               <input name="sex" value="male" type="radio" title="男" id="inlineRadio1">
	               <input name="sex" value="female" type="radio" title="女" id="inlineRadio2" >
	            </div>		    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">邮箱</label>
			    <div class="layui-input-block">
			      <input type="text" name="email" id="email" required lay-verify="required" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">生日</label>
			    <div class="layui-input-block">
			      <input type="text" name="birthday" id="birthday" required lay-verify="required" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">电话</label>
			    <div class="layui-input-block">
			      <input type="text" name="telephone" id="telephone" required lay-verify="required" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">家庭地址</label>
			    <div class="layui-input-block">
			      <input type="text" name="address" id="address" required lay-verify="required" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item">
			    <label class="layui-form-label">用户头像</label>
			    <img style="width:160px;height:110px;" src="" id="userImg">
			    <button type="button" class="layui-btn layui-btn-warm" id="uploadUserImg">
				  <i class="layui-icon">&#xe67c;</i>上传图片
				</button>
				<div style="display:inline-block;" id="newImg"></div>
		</div>
	</form>
</div>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" title="编辑" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs" title="刪除" lay-event="delete">刪除</a>
</script>
<script type="text/html" id="dateTpl">
    {{ layui.laytpl.fn(d.editdate) }}
</script>
<script type="text/javascript">
var table;
var layer;
var form;
var upload;
var laydate;
        layui.use(['layer', 'table','form','upload','laydate'], function ()
        {
            table = layui.table;
            layer = layui.layer;
            form =layui.form;
            upload=layui.upload;
            laydate=layui.laydate;
            layui.laytpl.fn = function (value)
            {
                //json日期格式转换为正常格式
                var date = new Date(parseInt(value.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + day;
            }
            var uploadInst = upload.render({
                elem: '#uploadUserImg' //绑定元素
                ,url: '${pageContext.request.contextPath }/uploadAdminUserImg' //上传接口
                ,data:'json'
                ,done: function(res){
                  layer.msg("上传图片成功！",{icon:1,time:2000});
                  var str="<img style='width:160px;height:110px;' src='img/upload/"+res.userImg+"' /><input type='hidden' name='userimg' value='"+res.userImg+"' />";
                  $("#newImg").html(str);
                }
                ,error: function(){
                  layer.msg("图片上传失败！请重试！",{icon:5,time:2000});
                }
              });
            //--------------方法渲染TABLE----------------
            var tableIns = table.render({
                elem: '#users'
                , id: 'users'
                , url: '${pageContext.request.contextPath }/findAllUser'
                ,width:1140
                , cols: [[
                     { field: 'uid', title: '用户编号', width: 100, align: 'center' }
                     , { field: 'username', title: '用户名', width: 120, align: 'center'}
                     , { field: 'sex', title: '性别', width: 100, align: 'center' }
                    , { field: 'name', title: '姓名', width: 150, align: 'center' }
                    , { field: 'email', title: '用户邮箱', width: 150, align: 'center' }
                   ,{field:'userImg', title: '用户头像', width: 150, align: 'center',templet: function(d){
                	   		var str="<img style='width:28px;height:32px;' src='"+d.userimg+"' />";
                        	return str;
                   		}
                   }
                   , { field: 'birthday', title: '出生日期', width: 150, align: 'center' }
                   , { field: 'telephone', title: '手机号码', width: 150, align: 'center' }
                   , { field: 'address', title: '家庭地址', width: 150, align: 'center' }
                  ,{title: '操作', fixed: 'right', width: 160, align: 'center', toolbar: '#bar'}
                ]]
                , page: true
                , limits: [5, 10, 15]
                , limit: 10 //默认采用10
                , done: function (res, curr, count)
                {
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    //console.log(res);
                    //得到当前页码
                    //console.log(curr);
                    $("#curPageIndex").val(curr);
                    //得到数据总量
                    //console.log(count);
                }
            });
            
            //#region --------------搜索----------------
            $("#search").click(function ()
            {
            	var word=$("#keyword").val();
                tableIns.reload({
                    where: {
                    	keyword: word
                    },page: {
                    curr: 1 //重新从第 1 页开始
                  }
                });
            });
            //#endregion
            //工具条事件监听
            table.on('tool(users)', function (obj)
            { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'edit')
                { //编辑
                    layer.open({
                        type: 1,
                        title: '编辑用户信息',
                        shade: 0.4,  //阴影度
                        fix: false,
                        shadeClose: true,
                        maxmin: false,
                        area: ['800px;', '400px;'],    //窗体大小（宽,高）
                        content: $('#formData'),
                        success: function (layero, index)
                        {
                            var body = layer.getChildFrame('body', index); //得到子页面层的BODY
                            $("#uid").val(data.uid);
                            $("#username").val(data.username);
                            $("#name").val(data.name);
                            if(data.sex=='male'){
                            	$("#inlineRadio1").prop('checked',true);
                            }else{
                            	$("#inlineRadio2").prop('checked',true);
                            }
                            $("#email").val(data.email);
                            $("#birthday").val(data.birthday);
                            $("#telephone").val(data.telephone);
                            $("#address").val(data.address);
                            $("#userImg").attr("src",data.userimg);
                            form.render();
                            body.find('#hidValue').val(index); //将本层的窗口索引传给子页面层的hidValue中
                        },
                        btn:['修改','取消'],
                        yes: function(index, layero){
                        	$.post('${pageContext.request.contextPath }/updateAdminUser',$('#userForm').serialize(),function(data){
                                if (data == 'success')
                                {
                                    parent.layer.msg('修改用户信息成功！', { icon: 1, shade: 0.4, time: 1000 });
                                    $("#search").click();
                                    $("#handle_status").val('');
                                }
                                else
                                {
                                    parent.layer.msg('修改用户信息失败！', { icon: 5, shade: 0.4, time: 1000 });
                                }
                                layer.close(index);
                                $("#newImg").html("");
                        	}); 
                        }
                    });
                }else if(layEvent === 'delete'){
                	layer.confirm('是否删除该用户？', {
                		  btn: ['确认', '取消'] //可以无限个按钮
                		  ,btn1: function(index, layero){
                			  $.ajax({
                				   type: "POST",
                				   url: "${pageContext.request.contextPath }/deleteAdminUser",
                				   data: "uid="+data.uid,
                				   success: function(msg){
                				     if(msg=='success'){
                				    	 parent.layer.msg('删除成功', { icon: 1, shade: 0.4, time: 1000 });
                				     }else{
                				    	 parent.layer.msg('删除失败', { icon: 5, shade: 0.4, time: 1000 });
                				     }
                				   }
                				});
                			  $(tr).remove();
                			  layer.close(index);
                		  }
                		});
                }
            });
            
            //日期控件
          	//执行一个laydate实例
            laydate.render({
              elem: '#birthday' //指定元素
            });
        });
    </script>
</body>
</html>
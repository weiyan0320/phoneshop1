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
<!-- 表格横滚动条隐藏，悬停显示 -->
<style type="text/css">
.layui-table-body{
    overflow-x:hidden;
}
 
.layui-table-body:hover{
    overflow-x:auto;
}
</style>
</head>
<body>

<div class="layui-form-item" style="margin:15px;15px;height:30px;">
    <div class="layui-input-inline">
        <input type="text" id="filter" name="filter" value="" lay-verify="" placeholder="请输入搜索内容" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-input-inline">
        <button class="layui-btn" id="search" data-type="reload" name="search">
            <i class="layui-icon"></i>搜索
        </button>
    </div>
</div>
<div>
    <table id="goods" lay-filter="goods"></table>
</div>
<div id="editForm" style="display:none;width:800px;padding-top:10px;">
    <form id="formData" class="layui-form">
    	<input type="hidden" name="pid" id="pid" >
    	<div class="layui-form-item">
			    <label class="layui-form-label">商品名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="pname" id="pname" required lay-verify="required" placeholder="请输入商品名称" value="" class="layui-input" />
			    </div>			    
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">销售价格</label>
			    <div class="layui-input-block">
			      <input type="text" name="shop_price" id="shop_price" required lay-verify="required" placeholder="请输入商品价格" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item">
			    <label class="layui-form-label">商品库存</label>
			    <div class="layui-input-block">
			      <input type="text" name="stock" id="stock" required lay-verify="required" placeholder="请输入商品库存" value="" class="layui-input" />
			    </div>			    
		</div>
		<div class="layui-form-item" lay-filter="test">
			    <label class="layui-form-label">商品类别</label>
			    <div class="layui-input-inline">
			      <select name="category.cid" id="category" required lay-verify="required" lay-filter="category.cid">
			      </select>
			    </div>			    
		</div>
	
		<div class="layui-form-item">
			    <label class="layui-form-label">商品图片</label>
			    <img style="width:260px;height:180px;" src="" id="pimage">
			    <button type="button" class="layui-btn layui-btn-warm" id="uploadGoodsImg">
				  <i class="layui-icon">&#xe67c;</i>上传图片
				</button>
				<div style="display:inline-block;" id="newImg"></div>
		</div>
		
		<div class="layui-form-item">
			    <label class="layui-form-label">商品描述</label>
			    <div class="layui-input-block">
			      <textarea name="pdesc" id="pdesc"  required lay-verify="required" class="layui-textarea"></textarea>
			    </div>			    
		</div>
    </form>
</div>
<script type="text/html" id="bar">
    <a class="layui-btn layui-btn-xs layui-btn-normal" title="编辑" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-danger" title="刪除" lay-event="delete">刪除</a>
	<a class="layui-btn layui-btn-xs " title="上架" lay-event="publish">上架</a>
    <a class="layui-btn layui-btn-xs layui-btn-warm" title="下架" lay-event="unpublish">下架</a>
</script>
<script type="text/html" id="dateTpl">
    {{ layui.laytpl.fn(d.editdate) }}
</script>
<script type="text/javascript">
//在layui table加载完成后，重新设置表格高度为100%,不限制表格高度，不会在表格上出现垂直滚动条
var table;
var layer;
var form;
var upload;
        layui.use(['layer', 'table','form','upload'], function ()
        {
            table = layui.table;
            layer = layui.layer;
            form =layui.form;
            upload=layui.upload;
            //上传图片
            var uploadInst = upload.render({
                elem: '#uploadGoodsImg' //绑定元素
                ,url: '${pageContext.request.contextPath }/uploadImg' //上传接口
                ,data:'json'
                ,done: function(res){
                  layer.msg("上传图片成功！",{icon:1,time:2000});
                  var str="<img style='width:280px;height:160px;' src='img/upload/"+res.src+"' /><input type='hidden' name='pimage' value='"+res.src+"' />";
                  $("#newImg").html(str);
                }
                ,error: function(){
                  layer.msg("图片上传失败！请重试！",{icon:5,time:2000});
                }
              }); 
            $.ajax({
     		   type: "POST",
     		   url: "${pageContext.request.contextPath }/selectAllCategoryInfo",
     		   success: function(arr){
	               		var str="";
	               		for(var i=0;i<arr.length;i++){
	               			str=str+"<option value='"+arr[i].cid+"'>"+arr[i].cname+"</option>";
	               		}
	       				$("#category").html(str);
	       				form.render();
     		   }
     		}); 
           
            //--------------方法渲染TABLE----------------
            var tableIns = table.render({
                elem: '#goods'
                , id: 'goods'
                , url: '${pageContext.request.contextPath }/findBySplitPage'
                , cols: [[
                      { field: 'pid', title: '编号', width: 80, align: 'center' }
                     , { field: 'pname', title: '商品名称', width: 140, align: 'center' }
                    , { field: 'shop_price', title: '销售价格', width: 110,sort:true, align: 'center' }
                    , { field: 'stock', title: '库存', width: 80, align: 'center' }
                    , { field: 'pflag', title: '是否上下架', width: 100, align: 'center',templet: function(d){
                    	if(d.pflag==1){
                    		return "上架"
                    	}else{
                    		return "下架"
                    	}
                        
                   	   }
                   	 }
                    ,{field:'category', title: '分类', width: 100 ,align: 'center',templet: function(d){
                        return d.category.cname
                   	}
                  }
                    ,{field:'pimage', title: '商品图片', width: 120 ,align: 'center',templet: function(d){
                          return '<img src="'+d.pimage+'" width="28px" height="32px"/>'
                     	}
                    }
                    , { field: 'pdesc', title: '商品描述', width: 160, align: 'center' }
                    
                    , {title: '操作', fixed: 'right', width: 250, align: 'center', toolbar: '#bar' }
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
                var strFilter = $("#filter").val();//输入框的值
                tableIns.reload({  //数据表格重新获取数据
                    where: {
                        keyword: strFilter
                    },page: {
                    curr: 1 //重新从第 1 页开始
                  }
                });
            });
            //#endregion

            //#endregion
            //工具条事件监听
            table.on('tool(goods)', function (obj)
            { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                var pid = data.pid;
                if (layEvent === 'edit')
                { //编辑
                    layer.open({
                        type: 1,
                        title: '编辑商品信息',
                        shade: 0.4,  //阴影度
                        fix: false,
                        shadeClose: true,
                        maxmin: false,
                        area: ['900px;', '540px;'],    //窗体大小（宽,高）
                        content: $('#editForm'),
                        success: function (layero, index)
                        {
                            var body = layer.getChildFrame('body', index); //得到子页面层的BODY
                            $("#pid").val(data.pid);
                            $("#pname").val(data.pname);
                            $("#shop_price").val(data.shop_price);
                            $("#stock").val(data.stock);
                            $("#pflag").val(data.pflag);
                            $("#category").val(data.category.cid);
                            $("#pimage").attr("src",data.pimage);
                            $("#pdesc").val(data.pdesc);
                            form.render();//默认对全部类型的表单进行一次更新
                            body.find('#hidValue').val(index); //将本层的窗口索引传给子页面层的hidValue中
                        },
                        btn:['修改','取消'],
                        yes: function(index, layero){
                        	$.post('${pageContext.request.contextPath }/updateProduct',$('#formData').serialize(),function(data){
                                if (data == 'success')
                                {
                                    parent.layer.msg('修改成功', { icon: 1, shade: 0.4, time: 1000 });
                                    $("#search").click();
                                }
                                else
                                {
                                    parent.layer.msg('修改失败', { icon: 5, shade: 0.4, time: 1000 });
                                }
                                $("#newImg").html("");
                                layer.close(index);
                        	}); 
                        }
                    });
                }else if(layEvent === 'delete'){
                	layer.confirm('是否删除该商品？', {
                		  btn: ['确认', '取消'] //可以无限个按钮
                		  ,btn1: function(index, layero){
                			  $.ajax({
                				   type: "POST",
                				   url: "${pageContext.request.contextPath }/deleteProduct",
                				   data: "pid="+data.pid,
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
                }else if(layEvent === 'publish'){
                	layer.confirm('是否上架该商品？', {
              		  btn: ['确认', '取消'] //可以无限个按钮
              		  ,btn1: function(index, layero){
              			  $.ajax({
              				   type: "POST",
              				   url: "${pageContext.request.contextPath }/publishProductInfo",
              				   data: "pid="+data.pid,
              				   success: function(msg){
              				     if(msg=='success'){
              				    	 parent.layer.msg('上架成功', { icon: 1, shade: 0.4, time: 1000 });
              				     }else{
              				    	 parent.layer.msg('上架失败', { icon: 5, shade: 0.4, time: 1000 });
              				     }
              				    //刷新页面,
                   				table.reload('goods');
              				   }
              				});
              			  layer.close(index);
              		  }
              		});
              }else if(layEvent === 'unpublish'){
              	layer.confirm('是否下架该商品？', {
          		  btn: ['确认', '取消'] //可以无限个按钮
          		  ,btn1: function(index, layero){
          			  $.ajax({
          				   type: "POST",
          				   url: "${pageContext.request.contextPath }/unpublishProductInfo",
          				   data: "pid="+data.pid,
          				   success: function(msg){
          				     if(msg=='success'){
          				    	 parent.layer.msg('下架成功', { icon: 1, shade: 0.4, time: 1000 });
          				     }else{
          				    	 parent.layer.msg('下架失败', { icon: 5, shade: 0.4, time: 1000 });
          				     }
          				    //刷新页面,
               				table.reload('goods');
          				   }
          				});
          			  layer.close(index);
          				
          		  }
          		});
          }
            });
        });
        
        
     
    </script>
</body>
</html>
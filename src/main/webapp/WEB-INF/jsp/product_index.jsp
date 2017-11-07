<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title></title>
		<style>
			* {
				margin: 0;
				padding: 0;
			}
		</style>
		<script type="text/javascript">
		
		function godelete(){
			array = Util.getSelected("#tableId");
			if(array.length == 0){
				$.messager.alert('系统提示',"请选择您要删除的数据");
				return;
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){ $.post(
						  "${ctx}/product/delete.action", //url
						   {ids:array}, //data
						    function(data) { //callback
						      $.messager.alert('系统提示',data.msg);
						        if(data.status == Util.SUCCESS){
						           $('#tableId').datagrid('reload');
						           }
						     },
						   "json" //type
					 );
			    }    
			}); 
			
		}
		
		function doSearch(){
			$('#tableId').datagrid('load',{
				'name':$("#name").val(),
				'model':$("#model").val(),
				'price':$("#price").val(),
				'stock':$("#stock").val(),
				'remark':$("#remark").val()
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/product/add.action";
				$('#form').form("clear");
			}
			
			function openUpdateDialog(){
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","修改信息");
				url = "${ctx}/product/update.action";
				$('#form').form('load',row);
			}
			
			function dosave(){
				$('#form').form('submit', {    
				    url:url,    
				    onSubmit: function(){    
				        return $(this).form("validate");
				    },       
				    success:function(data){    
				    	var data = eval('(' + data + ')');
				        if(data.status == Util.SUCCESS){
				        	$.messager.alert("系统提示",data.msg);
				        	$("#dialog").dialog('close');
				        	$('#tableId').datagrid('reload');
				        }    
				    }    
				});  

			}
			
			function doclose(){
				$('#dialog').dialog('close');
			}
			
		</script>
	</head>
	
	<body>
		<table id="tableId" class="easyui-datagrid"  fitColumns="true" toolbar="#toolbar" pagination="true"
			data-options="fit:true,singleSelect:false,url:'${ctx}/product/findAll.action',method:'get'">
				<thead>
					<tr id="target">
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',width:50,align:'center'">编号</th>
						<th data-options="field:'name',width:100,align:'center'">产品名称</th>
						<th data-options="field:'model',width:100,align:'center'">型号</th>
						<th data-options="field:'unit',width:100,align:'center'">单位</th>
						<th data-options="field:'price',width:250,align:'center'">价格</th>
						<th data-options="field:'stock',width:250,align:'center'">库存</th>
						<th data-options="field:'remark',width:250,align:'center'">备注</th>
					</tr>
				</thead>
			</table>
			
			<!-- toolbar -->
			<div id="toolbar">
				<div>
					<a href="javascript:openaddDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
					<a href="javascript:godelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
					<a href="javascript:openUpdateDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">修改</a>
				</div>
				<div>
					<input  class="easyui-textbox" id="name" prompt="产品名称"/>
					<input  class="easyui-textbox" id="model" prompt="型号"/>
					<input  class="easyui-textbox" id="price" prompt="价格"/>
					<input  class="easyui-textbox" id="stock" prompt="库存"/>
					<input  class="easyui-textbox" id="remark" prompt="备注"/>
					<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
				</div>
			</div>
	</body>
	<!-- 添加用户隐藏的div开始 -->
	<div id="dialog" class="easyui-dialog" style="width:500px;height:200px;" closed="true" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
    			<input type="hidden" id="id" name="id"/>
			    <table cellpadding="5">
		    		<tr>
		    			<td>产品名称:</td>
		    			<td><input id="name" class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
		    			<td>型号:</td>
		    			<td><input id="model" class="easyui-textbox" type="text" name="model" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>单价:</td>
		    			<td><input id="unit" class="easyui-textbox" type="text" name="unit" data-options="required:true"></input></td>
		    			<td>价格:</td>
		    			<td><input id="price" class="easyui-textbox" type="text" name="price" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>库存:</td>
		    			<td><input id="stock" class="easyui-textbox" type="text" name="stock" data-options="required:true"></input></td>
		    			<td>备注:</td>
		    			<td><input id="remark" class="easyui-textbox" type="text" name="remark" data-options="required:true"></input></td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a href="javascript:dosave();" class="easyui-linkbutton" iconCls = "icon-add">提交</a>
			    	<a href="javascript:doclose();" class="easyui-linkbutton" iconCls = "icon-cancel">关闭</a>
			    </div>    
			</form>  
    	</div>
    	<!-- 添加用户隐藏的div结束 -->
</html>
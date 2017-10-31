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
						  "${ctx}/user/delete.action", //url
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
				'password':$("#password").val(),
				'trueName':$("#trueName").val(),
				'email':$("#email").val(),
				'phone':$("#phone").val(),
				'roleName':$("#roleName").val()
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/user/add.action";
				$('#form').form("clear");
			}
			
			function openUpdateDialog(){
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","修改信息");
				url = "${ctx}/user/update.action";
				$('#form').form('load',row);
			}
			
			function dosave(){
				$('#form').form('submit', {    
				    url:url,    
				    onSubmit: function(){    
				        if($("#roleName").combobox("getValue") == "") {
				        	$.messager.alert("系统提示", "请选择用户角色");
				        	return false;
				        }
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
			
		</script>
	</head>
	
	<body>
		<table id="tableId" class="easyui-datagrid" fitColumns="true" toolbar="#toolbar" pagination="true"
			data-options="fit:true,singleSelect:false,url:'${ctx}/user/findAll.action',method:'get'">
				<thead>
					<tr id="target">
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',width:50,align:'center'">编号</th>
						<th data-options="field:'name',width:100,align:'center'">用户名</th>
						<th data-options="field:'password',width:100,align:'center'">密码</th>
						<th data-options="field:'trueName',width:100,align:'center'">真是姓名</th>
						<th data-options="field:'email',width:250,align:'center'">邮箱</th>
						<th data-options="field:'phone',width:250,align:'center'">手机号</th>
						<th data-options="field:'roleName',width:250,align:'center'">角色</th>
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
					<input  class="easyui-textbox" id="name" prompt="用户名"/>
					<input  class="easyui-textbox" id="password" prompt="密码"/>
					<input  class="easyui-textbox" id="trueName" prompt="真实姓名"/>
					<input  class="easyui-textbox" id="email" prompt="邮箱"/>
					<input  class="easyui-textbox" id="phone" prompt="手机号"/>
					<select id="roleName" panelHeight='auto' class="easyui-combobox" editable="false" style="width:170px">
			    				<option value=""></option>
			    				<option value="系统管理员">系统管理员</option>
			    				<option value="销售主管">销售主管</option>
			    				<option value="客户经理">客户经理</option>
			    				<option value="高管">高管</option>
		    				</select>
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
		    			<td>用户名:</td>
		    			<td><input id="name" class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
		    			<td>密码:</td>
		    			<td><input id="password" class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>真实姓名:</td>
		    			<td><input id="trueName" class="easyui-textbox" type="text" name="trueName" data-options="required:true"></input></td>
		    			<td>邮箱:</td>
		    			<td><input id="email" class="easyui-textbox" type="text" name="email" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>手机号:</td>
		    			<td><input id="phone" class="easyui-textbox" type="text" name="phone" data-options="required:true"></input></td>
		    			<td>角色:</td>
		    			<td>
		    				<select id="roleName" class="easyui-combobox" panelHeight='auto' name="roleName" editable="false" style="width:170px">
			    				<option value=""></option>
			    				<option value="系统管理员">系统管理员</option>
			    				<option value="销售主管">销售主管</option>
			    				<option value="客户经理">客户经理</option>
			    				<option value="高管">高管</option>
		    				</select>
		    			</td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a href="javascript:dosave();" class="easyui-linkbutton" iconCls = "icon-add">提交</a>
			    	<a  class="easyui-linkbutton" iconCls = "icon-cancel">关闭</a>
			    </div>    
			</form>  
    	</div>
    	<!-- 添加用户隐藏的div结束 -->

</html>
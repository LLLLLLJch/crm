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
			function goAdd(){
				$('#addId').dialog('open');
			}
			
			function add(){
				var options = {
						url:"${ctx}/user/add.action",
						type:"post",
						dataType:"json",
						data:$('#addForm').serialize(),
						 success:function(data){
							if(data.status == 0){
								$('#addId').dialog('close');
								$.messager.alert('我的消息','添加成功');
							}else{
								$.messager.alert('我的消息','添加失败');
							}
						}
					};
					$.ajax(options);
			}
			
			function godelete(){
				array = Util.getSelected("#tableId");
				if(array.length == 0){
					$.messager.alert('系统提示',"请选择您要删除的数据");
					return;
				}
				$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
				    if (r){    
				    		 $.post(
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
			
			function toUpdate(){
				var row = $('#tableId').datagrid('getSelected');
				if (row){
					  $("#updateId").dialog('open');
					  $('#id').textbox("setValue", row.id);
					  $('#userName').textbox("setValue", row.userName);
					  $('#password').textbox("setValue", row.password);
					  $('#trueName').textbox("setValue", row.trueName);
					  $('#email').textbox("setValue", row.email);
					  $('#phone').textbox("setValue", row.phone);
					  $('#roleName').textbox("setValue", row.roleName);
				}
			}
			
			function update(){
				var options = {
					url:"${ctx}/user/update.action",
				    type:"post",
				    dataType:"json",
				    data:$("#updateForm").serialize(),
			        success:function(data){
				       if(data.status == 0){
				    	   $.messager.alert('我的消息',data.msg);
				    	   $('#updateId').dialog('close');
				    	   $('#tableId').datagrid('reload');
				       }else{
				    	   $.messager.alert('我的消息',data.msg);
				       }
			  	  }
			};
			 $.ajax(options)
			}
			
			function doSearch(value){
				$('#tableId').datagrid('load',{
					'userName':value
				});
			}
		</script>
	</head>
	
	<body>
		<table id="tableId" class="easyui-datagrid"  fitColumns="true" toolbar="#toolbar" pagination="true"
			data-options="fit:true,singleSelect:false,url:'${ctx}/user/findAll.action',method:'get'">
				<thead>
					<tr id="target">
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',width:50,align:'center'">编号</th>
						<th data-options="field:'userName',width:100,align:'center'">用户名</th>
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
				<a href="javascript:goAdd();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
				<a href="javascript:godelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
				<a href="javascript:toUpdate();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">修改</a>
				<input class="easyui-searchbox" data-options="prompt:'用户名',searcher:doSearch"style="width:150px"></input>
			</div>
	</body>
	<!-- 添加用户隐藏的div开始 -->
	<div id="addId" style="display:none;overflow:hidden;width:300px;height:400px;">
    		<form id="addForm" action="${ctx}/user/add.action" method="post">   
			    <table cellpadding="5">
		    		<tr>
		    			<td>用户名:</td>
		    			<td><input class="easyui-textbox" type="text" name="userName" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>密码:</td>
		    			<td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>真实姓名:</td>
		    			<td><input class="easyui-textbox" type="text" name="trueName" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>邮箱:</td>
		    			<td><input class="easyui-textbox" type="text" name="email" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>手机号:</td>
		    			<td><input class="easyui-textbox" type="text" name="phone" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>角色:</td>
		    			<td>
		    				<select class="easyui-combobox" name="roleName"><option value="系统管理员">系统管理员</option><option value="销售主管">销售主管</option><option value="客户经理">客户经理</option><option value="高管">高管</option></select>
		    			</td>
		    		</tr>
		    	</table>
			    <div>   
			        <input class="easyui-validatebox" type="button" onclick="add();" value="提交"/>   
			    </div>    
			</form>  
    	</div>
    	<!-- 添加用户隐藏的div结束 -->

    	<!-- 修改用户隐藏的div开始 -->
    	<div id="updateId" style="display:none;overflow:hidden;width:300px;height:400px;">
    		<form id="updateForm" action="${ctx}/user/update.action" method="post">   
			   <table cellpadding="5">
		    		<tr>
		    			<td>编号:</td>
		    			<td><input id="id" class="easyui-textbox" type="text" name="id" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>用户名:</td>
		    			<td><input id="userName" class="easyui-textbox" type="text" name="userName" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>密码:</td>
		    			<td><input id="password" class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>真实姓名:</td>
		    			<td><input id="trueName" class="easyui-textbox" type="text" name="trueName" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>邮箱:</td>
		    			<td><input id="email" class="easyui-textbox" type="text" name="email" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>手机号:</td>
		    			<td><input id="phone" class="easyui-textbox" type="text" name="phone" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>角色:</td>
		    			<td>
		    				<select id="roleName" class="easyui-combobox" name="roleName"><option value="系统管理员">系统管理员</option><option value="销售主管">销售主管</option><option value="客户经理">客户经理</option><option value="高管">高管</option></select>
		    			</td>
		    		</tr>
		    	</table>
			    <div>   
			        <input class="easyui-validatebox" type="button" onclick="update();" value="提交"/>   
			    </div>   
			</form>  
    	</div>
    	<!-- 修改用户隐藏的div结束 -->
</html>
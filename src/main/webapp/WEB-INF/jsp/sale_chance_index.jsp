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
						  "${ctx}/saleChance/delete.action", //url
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
				'customerName':$("#customerName").val(),
				'overview':$("#overview").val(),
				'createMan':$("#createMan").val(),
				'createTime':$("#createTime").val(),
				'status':$("#status").val()
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/saleChance/add.action";
				$('#form').form("clear");
			}
			
			function openUpdateDialog(){
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","修改信息");
				url = "${ctx}/saleChance/update.action";
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
			$(function(){
				$('#tableId').datagrid({
					url:"${ctx}/saleChance/findAll.action",
					method:'get',
					fit:true,
					singleSelect:false,
					toolbar:'#toolbar',
					rownumbers:true,
					fitColumns:true,
					pagination:true,
					columns:[[
					    {field:'ck',checkbox:true},    
					    {field:'id',title:'编号',width:50},    
					    {field:'customerName',title:'客户名称',width:100,align:'center'},    
					    {field:'overview',title:'概要',width:100,align:'center'},    
					    {field:'linkMan',title:'联系人',width:100,align:'center'},    
					    {field:'linkPhone',title:'联系电话',width:100,align:'center'},    
					    {field:'createMan',title:'创建人',width:100,align:'center'},    
					    {field:'createTime',title:'创建时间',width:100,align:'center',
					    	formatter: formatDateTime
						},    
						{field:'status',title:'状态', width:80,
							formatter: function(value,row,index){
								if (value == 0){
									return "未分配";
								} else {
									return "已分配";
								}
							}
						}
					]]
				});
			});
			


		</script>
	</head>
	
	<body>
		<table id="tableId"></table>
			<!-- toolbar -->
			<div id="toolbar">
				<div>
					<a href="javascript:openaddDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
					<a href="javascript:godelete();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
					<a href="javascript:openUpdateDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">修改</a>
				</div>
				<div>
					<input  class="easyui-textbox" id="customerName" prompt="客户名称"/>
					<input  class="easyui-textbox" id="overview" prompt="概要"/>
					<input  class="easyui-textbox" id="createMan" prompt="创建人"/>
					<input  class="easyui-textbox" id="createTime" prompt="创建时间"/>
					<select id="status" class="easyui-combobox" editable="false" style="width:170px">
			    				<option value=""></option>
			    				<option value="0">未分配</option>
			    				<option value="1">已分配</option>
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
		    				<select id="roleName" class="easyui-combobox" name="roleName" editable="false" style="width:170px">
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
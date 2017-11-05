<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				 'status':$("#status").val() ,
				 'startDate':$("#startDate").val() ,
				 'endDate':$("#endDate").val() ,
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/saleChance/add.action";
				$('#form').form("clear");
				$("#createTimeId").textbox('setValue',Util.getCurrentDateTime());
				$("#createManId").textbox('setValue',"${user.name}");
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
					    {field:'createTime',title:'创建时间',width:100,align:'center'},    
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
				$("#assignManId").combobox({
					onChange:function(){
						var date = Util.getCurrentDateTime();
						$('#assignTimeId').textbox('setValue',date);
					}
				})
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
					客户名称：<input  class="easyui-textbox" id="customerName"/>
					概要：<input  class="easyui-textbox" id="overview"/>
					创建人：<input  class="easyui-textbox" id="createMan"/>
					状态：<select id="status" panelHeight='auto' class="easyui-combobox" editable="false" style="width:170px">
			    				<option value=""></option>
			    				<option value="0">未分配</option>
			    				<option value="1">已分配</option>
		    				</select>
		    		<div>
					Start Date:<input id="startDate" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
					End Date:<input id="endDate" class="easyui-datebox" data-options="sharedCalendar:'#cc'">
					<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
		    		</div>
					<div id="cc" class="easyui-calendar"></div>
				</div>
			</div>
	</body>
	<!-- 添加用户隐藏的div开始 -->
	<div id="dialog" class="easyui-dialog" style="width:600px;height:400px;" closed="true" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
    			<input type="hidden" id="id" name="id"/>
			    <table cellpadding="5">
		    		<tr>
		    			<td>客户名称:</td>
		    			<td><input id="customerNameId" class="easyui-textbox" name="customerName" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>机会来源:</td>
		    			<td><input id="chanceSourceId" class="easyui-textbox" name="chanceSource"></input></td>
		    		</tr>
		    		<tr>
		    			<td>联系人:</td>
		    			<td><input id="linkManId" class="easyui-textbox" name="linkMan"></input></td>
		    			<td>联系电话:</td>
		    			<td><input id="linkPhoneId" class="easyui-textbox" name="linkPhone"></input></td>
		    		</tr>
		    		<tr>
		    			<td>成功几率(%):</td>
		    			<td><input id="successRateId" class="easyui-textbox" name="successRate" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>概要:</td>
		    			<td><input id="overviewId" class="easyui-textbox" name="overview"></input></td>
		    		</tr>
		    		<tr>
		    			<td>机会描述:</td>
		    			<td><input id="descriptionId" class="easyui-textbox" name="description"></input></td>
		    		</tr>
		    		<tr>
		    			<td>创建人:</td>
		    			<td><input id="createManId" class="easyui-textbox" name="createMan" data-options="required:true">
		    			<font color="red">*</font></input></td>
		    			<td>创建时间:</td>
		    			<td><input id="createTimeId" class="easyui-textbox" name="createTime"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>指派给:</td>
		    			<td><input class="easyui-combobox" name="assignMan" id="assignManId" 
		    			 data-options="
						 	url:'${ctx}/saleChance/findAssignMan.action',
						 	valueField: 'assignMan',
						 	textField: 'assignMan',
					 		panelHeight:'auto' "></input></td>
		    			<td>指派时间:</td>
		    			<td><input class="easyui-textbox" value="" id="assignTimeId" name="assignTime"></input></td>
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
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
						  "${ctx}/customerService/delete.action", //url
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
			
			function process(){
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","分配");
				url = "${ctx}/customerService/serviceFeedback.action";
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
				        }else{
				        	$.messager.alert("系统提示",data.msg);
				        }    
				    }    
				});  

			}
			$(function(){
				$('#tableId').datagrid({
					url:"${ctx}/customerService/findProcessed.action",
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
					    {field:'customer',title:'客户',width:100,align:'center'},    
					    {field:'overview',title:'概要',width:100,align:'center'},    
					    {field:'serviceType',title:'服务类型',width:100,align:'center'},    
					    {field:'createPeople',title:'创建人',width:100,align:'center'},    
					    {field:'createTime',title:'创建日期',width:100,align:'center'},    
					    {field:'assigner',title:'分配人',width:100,align:'center'},    
					    {field:'assignTime',title:'分配日期',width:100,align:'center'},    
					    {field:'serviceDealPeople',title:'服务处理人',width:100,align:'center'},    
					    {field:'serviceDealTime',title:'服务处理日期',width:100,align:'center'},    
					]]
				});
				$("#assignerId").combobox({
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
					<a href="javascript:process();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">反馈</a>
				</div>
			</div>
	</body>
	<!-- 添加用户隐藏的div开始 -->
	<div id="dialog" class="easyui-dialog" style="width:600px;height:400px;" closed="true" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
    			<input type="hidden" id="id" name="id"/>
			    <table cellpadding="5">
		    		<tr>
		    			<td>服务类型:</td>
		    			<td><input id="serviceTypeId" class="easyui-textbox" name="serviceType" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    			<td>客户:</td>
		    			<td><input id="customerId" class="easyui-textbox" name="customer"></input></td>
		    		</tr>
		    		<tr>
		    			<td>概要:</td>
		    			<td><input id="overviewId" class="easyui-textbox" name="overview"></input></td>
		    		</tr>
		    		<tr>
		    			<td>服务请求:</td>
		    			<td><input id="serviceRequestId" class="easyui-textbox" name="serviceRequest" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>创建人:</td>
		    			<td><input id="createPeopleId" class="easyui-textbox" name="createPeople" data-options="required:true">
		    			<font color="red">*</font></input></td>
		    			<td>创建时间:</td>
		    			<td><input id="createTimeId" class="easyui-textbox" name="createTime"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>分配给:</td>
		    			<td><input class="easyui-combobox" name="assigner" id="assignerId" 
		    			 data-options="
						 	url:'${ctx}/user/findManger.action',
						 	valueField: 'assigner',
						 	textField: 'trueName',
					 		panelHeight:'auto' "></input></td>
		    			<td>分配时间:</td>
		    			<td><input class="easyui-textbox" value="" id="assignTimeId" name="assignTime"></input></td>
		    		</tr>
		    		<tr>
		    			<td>服务处理:</td>
		    			<td><input id="serviceDealId" class="easyui-textbox" name="serviceDeal" data-options="required:true"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>处理人:</td>
		    			<td><input id="serviceDealPeopleId" class="easyui-textbox" name="serviceDealPeople" data-options="required:true">
		    			<font color="red">*</font></input></td>
		    			<td>处理时间:</td>
		    			<td><input id="serviceDealTimeId" class="easyui-datetimebox" name="serviceDealTime"></input>
		    			<font color="red">*</font></td>
		    		</tr>
		    		<tr>
		    			<td>处理结果:</td>
		    			<td><input id="serviceDealResultId" class="easyui-textbox" name="serviceDealResult" data-options="required:true">
		    			<font color="red">*</font></input></td>
		    			<td>客户满意度:</td>
		    			<td>
		    				<select class="easyui-combobox" panelHeight='auto' style="width: 170px;" data-options="required:true" name="satisfy">
		    					<option value="☆">☆</option>
		    					<option value="☆☆">☆☆</option>
		    					<option value="☆☆☆">☆☆☆</option>
		    					<option value="☆☆☆☆">☆☆☆☆</option>
		    					<option value="☆☆☆☆☆">☆☆☆☆☆</option>
		    				</select><font color="red">*</font>
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
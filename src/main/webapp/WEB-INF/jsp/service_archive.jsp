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
				'customer':$("#customer").val(),
				'overview':$("#overview").val(),
				 'serviceType':$("#serviceType").val() ,
				 'startDate':$("#startDate").val() ,
				 'endDate':$("#endDate").val() ,
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert('系统提示','请选择对象');
					return;
				}
				$.post(
					"${ctx}/customerService/findServiceDetail.action",	
					{id:row.id},
					function(data){
						console.log(data);
						alert(data.serviceType);
						$("#serviceTypeId").val(data.serviceType);
						$("#customerId").val(data.customer);
						$("#overviewId").val(data.overview);
						$("#serviceRequestId").val(data.serviceRequest);
						$("#createPeopleId").val(data.createPeople);
						$("#createTimeId").val(data.createTime);
						$("#assignerId").val(data.assigner);
						$("#assignTimeId").val(data.assignTime);
						$("#serviceDealId").val(data.serviceDeal);
						$("#serviceDealPeopleId").val(data.serviceDealPeople);
						$("#serviceDealTimeId").val(data.serviceDealTime);
						$("#serviceDealResultId").val(data.serviceDealResult);
						$("#satisfyId").val(data.satisfy);
					},
					"json"
				);
			}
			$(function(){
				$('#tableId').datagrid({
					url:"${ctx}/customerService/findArchive.action",
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
					<a href="javascript:openaddDialog();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">查看客户服务详情</a>
				</div>
				<div>
					客户名称：<input  class="easyui-textbox" id="customer"/>
					概要：<input  class="easyui-textbox" id="overview"/>
					服务类型：<input class="easyui-combobox" id="serviceType"  name="serviceType"  
		    			data-options="
		    			required:true,
		    			url:'${ctx}/dataDic/findServiceType.action',
		    			editable:true ,
		    			valueField: 'dataDicValue',    
       				    textField: 'dataDicValue' "></input>
		    		<div>
					Start Date:<input id="startDate" class="easyui-datetimebox" data-options="sharedCalendar:'#cc'">
					End Date:<input id="endDate" class="easyui-datetimebox" data-options="sharedCalendar:'#cc'">
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
		    			<td>服务类型:</td>
		    			<td><input id="serviceTypeId" type="text" name="serviceType"></input></td>
		    			<td>客户:</td>
		    			<td><input id="customerId" type="text" name="customer" value=""></input></td>
		    		</tr>
		    		<tr>
		    			<td>概要:</td>
		    			<td><input id="overviewId" type="text" name="overview"></input></td>
		    		</tr>
		    		<tr>
		    			<td>服务请求:</td>
		    			<td><input id="serviceRequestId" type="text" name="serviceRequest"></input></td>
		    		</tr>
		    		<tr>
		    			<td>创建人:</td>
		    			<td><input id="createPeopleId" type="text" name="createPeople"></input></td>
		    			<td>创建时间:</td>
		    			<td><input id="createTimeId" type="text" name="createTime"></input></td>
		    		</tr>
		    		<tr>
		    			<td>分配给:</td>
		    			<td><input type="text" name="assigner" id="assignerId" ></input></td>
		    			<td>分配时间:</td>
		    			<td><input type="text" value="" id="assignTimeId" name="assignTime"></input></td>
		    		</tr>
		    		<tr>
		    			<td>服务处理:</td>
		    			<td><input id="serviceDealId" type="text" name="serviceDeal"></input>
		    		</tr>
		    		<tr>
		    			<td>处理人:</td>
		    			<td><input id="serviceDealPeopleId" type="text" name="serviceDealPeople">
		    			<td>处理时间:</td>
		    			<td><input id="serviceDealTimeId" type="text" name="serviceDealTime"></input>
		    		</tr>
		    		<tr>
		    			<td>处理结果:</td>
		    			<td><input id="serviceDealResultId" type="text" name="serviceDealResult">
		    			<td>客户满意度:</td>
		    			<td>
		    				<input id="satisfyId" type="text" panelHeight='auto' style="width: 170px;" name="satisfy"/>
		    			</td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a  class="easyui-linkbutton" iconCls = "icon-cancel">关闭</a>
			    </div>    
			</form>  
    	</div>
    	<!-- 添加用户隐藏的div结束 -->
</html>
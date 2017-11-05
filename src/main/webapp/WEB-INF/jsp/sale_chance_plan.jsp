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
			$(function(){
				$('#tableId').datagrid({
				    url:"${ctx}/saleChance/findStatusIsOne.action",
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
					    {field:'linkMan',title:'联系人',width:100,align:'center'},    
					    {field:'linkPhone',title:'联系电话',width:100,align:'center'},    
					    {field:'assignMan',title:'指派人',width:100,align:'center'},    
					    {field:'assignTime',title:'指派时间',width:100,align:'center'},    
						{field:'devResult',title:'开发状态', width:80,
							formatter: function(value,row,index){
								if (value == 0){
									return "未开发";
								}else if(value == 1) {
									return "开发中";
								}else if(value == 2){
									return "开发成功";
								}else{
									return "开发失败";
								}
							}
						},
					    {field:'a',title:'查看详情',width:100,align:'center',
							formatter: function(value,row,index){
								if(row.devResult==0||row.devResult==1){
									return "<a href='javascript:openCusDevPlanTab("+row.id+")'>开发</a>";
								}else{
									return "<a href='javascript:openCusDevPlanInfoTab("+row.id+")'>查看详情</a>";
								}
							}
					    }
					]]
				});
			});
			
			//可以修改添加开发信息
			function openCusDevPlanTab(id){
				window.parent.openTab('客户开发计划项管理'+id+'','${ctx}/cusDevPlan/index.action?saleChanceId='+id,'icon-khkfjh');
			}
			//只能查看开发信息
			function openCusDevPlanInfoTab(id){
				window.parent.openTab('查看客户开发计划项'+id+'','${ctx}/cusDevPlan/index.action?saleChanceId='+id+'&show=true','icon-khkfjh');
			}
		</script>
	</head>
	
	<body>
		<table id="tableId"></table>
	</body>
</html>
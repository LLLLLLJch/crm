<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/resources/thirdlib/jquery-easyui/jquery.edatagrid.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		$.post(
			"${ctx}/customerLoss/findById.action",
			{id:'${param.id}'},
			function(data){
				$('#customerNoId').val(data.customerNo);
				$('#customerNameId').val(data.customerName);
				$('#customerManagerId').val(data.customerManager);
				$('#lastOrderTimeId').val(data.lassOrderTime);
			},
			"json"
		);
		
		$("#datagrid").edatagrid({
		 	url:'${ctx}/customerLossMeasure/findAll.action?lossId=${param.id}',//只查询已分配咨询师的
		 	saveUrl: "${ctx}/customerLossMeasure/add.action?lossId=${param.id}",
			updateUrl:"${ctx}/customerLossMeasure/update.action?lossId=${param.id}",
			destroyUrl:"${ctx}/customerLossMeasure/deleteById.action",
			method:'post',
			fit:true,
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'measure',title:'暂缓措施',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			]]
		});
	});
	
	function confirmLoss(){
		$.post(
			"${ctx}/customerLoss/confirmLoss.action",
			{id:'${param.id}'},
			function(data){
				if(data.status == Util.SUCCESS){
					$.messager.alert('系统提示',data.msg);
				}else{
					$.messager.alert('系统提示',data.msg);
				}
			},
			"json"
		);
	}
</script>
<body>
	<!-- 营销机会信息面板  开始 -->
	<div id="p" class="easyui-panel" title="流失客户信息" style="width: 700px;height: 400px;padding: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户编号：</td>
	   			<td><input type="text" id="customerNoId" name="customerNo" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="customerNameId" name="customerName" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>客户经理：</td>
	   			<td><input type="text" id="customerManagerId" name="customerManager" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>上次下单时间：</td>
	   			<td><input type="text" id="lastOrderTimeId" name="lastOrderTime" readonly="readonly"/></td>
	   		</tr>
	   	</table>
	 </div>
	 <!-- 营销机会信息面板  结束  -->
	 
	<!-- 客户开发计划项table -->
	<div style="OVERFLOW:auto;WIDTH:700px;HEIGHT:300px">
	<table id="datagrid"></table>
	<!-- toolbar 开始 -->
	 <div id="toolbar">
	 	<c:if test="${param.show!='true' }">
		 	<a href="javascript:$('#datagrid').edatagrid('addRow');" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
		 	<a href="javascript:$('#datagrid').edatagrid('destroyRow')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="javascript:$('#datagrid').edatagrid('saveRow');$('#datagrid').edatagrid('reload')" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
			<a href="javascript:$('#datagrid').edatagrid('cancelRow')" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
			<a href="javascript:confirmLoss();" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">确认流失</a>
	 	</c:if>
	 </div>
	</div>
	<!-- toolbar 结束 -->
</body>
</html>

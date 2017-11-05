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
			"${ctx}/customer/findById.action",
			{id:'${param.customerId}'},
			function(data){
				$('#numId').val(data.num);
				$('#nameId').val(data.name);
			},
			"json"
		);
		
		$("#datagrid").edatagrid({
		 	url:'${ctx}/customerLinkman/findAll.action?customerId=${param.customerId}',//只查询已分配咨询师的
		 	saveUrl: "${ctx}/customerLinkman/add.action?customerId=${param.customerId}",
			updateUrl:"${ctx}/customerLinkman/update.action?customerId=${param.customerId}",
			destroyUrl:"${ctx}/customerLinkman/deleteById.action",
			method:'post',
			fit:true,
			singleSelect:true,
			toolbar:'#toolbar',
			rownumbers:true,
			fitColumns:true,
			pagination:true,
			columns:[[    
			     {field:'ck',checkbox:true},   
			     {field:'id',title:'编号',width:50,align:'center'},    
			     {field:'linkName',title:'客户姓名',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'gender',title:'性别',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'position',title:'职位',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'officePhone',title:'办公电话',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'phone',title:'手机',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			]]
		});
	});
	
	function stopDevelopemnt(){
		$.post(
			"${ctx}/customerLinkman/stopDevelopment.action",
			{id:'${param.customerLinkmanId}'},
			function(data){
				if(data.status == Util.SUCCESS){
					$.messager.alert('系统提示',data.msg);
					$('#datagrid').datagrid('reload');
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
	<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px;height: 200px;padding: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户编号：</td>
	   			<td><input type="text" id="numId" name="num" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="nameId" name="name" readonly="readonly"/></td>
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
	 	</c:if>
	 </div>
	</div>
	<!-- toolbar 结束 -->
</body>
</html>

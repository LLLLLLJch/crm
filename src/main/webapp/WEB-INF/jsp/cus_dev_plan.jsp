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
			"${ctx}/saleChance/findById.action",
			{id:'${param.saleChanceId}'},
			function(data){
				$('#customerNameId').val(data.customerName);
				$('#chanceSourceId').val(data.chanceSource);
				$('#linkManId').val(data.linkMan);
				$('#linkPhoneId').val(data.linkPhone);
				$('#successRateId').val(data.successRate);
				$('#overviewId').val(data.overview);
				$('#descriptionId').val(data.description);
				$('#createManId').val(data.createMan);
				$('#createTime').val(data.createTime);
				$('#assignManId').val(data.assignMan);
				$('#assignTimeId').val(data.assignTime);
			},
			"json"
		);
		
		$("#datagrid").edatagrid({
		 	url:'${ctx}/cusDevPlan/findAll.action?saleChanceId=${param.saleChanceId}',//只查询已分配咨询师的
		 	saveUrl: "${ctx}/cusDevPlan/add.action?saleChanceId=${param.saleChanceId}",
			updateUrl:"${ctx}/cusDevPlan/update.action?saleChanceId=${param.saleChanceId}",
			destroyUrl:"${ctx}/cusDevPlan/deleteById.action",
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
			     {field:'planDate',title:'日期',width:100,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'planItem',title:'计划内容',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'exeAffect',title:'执行结果',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}}  
			]]
		});
	});
	
	function stopDevelopemnt(){
		$.post(
			"${ctx}/saleChance/stopDevelopment.action",
			{id:'${param.saleChanceId}'},
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
	<div id="p" class="easyui-panel" title="销售机会信息" style="width: 700px;height: 400px;padding: 10px">
	 	<table cellspacing="8px">
	   		<tr>
	   			<td>客户名称：</td>
	   			<td><input type="text" id="customerNameId" name="customerName" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>机会来源</td>
	   			<td><input type="text" id="chanceSourceId" name="chanceSource" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>联系人：</td>
	   			<td><input type="text" id="linkManId" name="linkMan" readonly="readonly"/></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>联系电话：</td>
	   			<td><input type="text" id="linkPhoneId" name="linkPhone" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>成功几率(%)：</td>
	   			<td><input type="text" id="successRateId" name="successRate" readonly="readonly"/></td>
	   			<td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   		</tr>
	   		<tr>
	   			<td>概要：</td>
	   			<td colspan="4"><input type="text" id="overviewId" name="overview" style="width: 420px" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>机会描述：</td>
	   			<td colspan="4">
	   				<textarea rows="5" cols="50" id="descriptionId" name="description" readonly="readonly"></textarea>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>创建人：</td>
	   			<td><input type="text" readonly="readonly" id="createManId" name="createMan" /></td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>创建时间：</td>
	   			<td><input type="text" id="createTimeId" name="createTime" readonly="readonly"/></td>
	   		</tr>
	   		<tr>
	   			<td>指派给：</td>
	   			<td>
	   				<input type="text" readonly="readonly" id="assignManId" name="assignMan" />
	   			</td>
	   			<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	   			<td>指派时间：</td>
	   			<td><input type="text" id="assignTimeId" name="assignTime" readonly="readonly"/></td>
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
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">开发成功</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="stopDevelopemnt()">终止开发</a>
	 	</c:if>
	 </div>
	</div>
	<!-- toolbar 结束 -->
</body>
</html>

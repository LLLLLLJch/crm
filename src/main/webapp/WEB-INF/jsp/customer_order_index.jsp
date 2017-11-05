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
	var customerStatus = [{"value":"0","text":"未付款"},{"value":"1","text":"已付款"}];
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
		 	url:'${ctx}/customerOrder/findAll.action?customerId=${param.customerId}',//只查询已分配咨询师的
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
			     {field:'orderNo',title:'订单号',width:100,align:'center',editor:{type:'validatebox',options:{required:true}}},    
			     {field:'orderDate',title:'订购日期',width:80,align:'center',editor:{type:'datebox',options:{required:true}}},    
			     {field:'address',title:'送货地址',width:80,align:'center',editor:{type:'validatebox',options:{required:true}}},  
			     {field:'status',title:'状态',width:80,
			    	 formatter: function(value,row,index){
			    		 if(value == 0){
			    			 return "未回款";
			    		 }else{
			    			 return "已回款";
			    		 }
			    	 }
			     }
			     ]]
		});
	});
	
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
	</div>
	<!-- toolbar 结束 -->
	
</body>
</html>

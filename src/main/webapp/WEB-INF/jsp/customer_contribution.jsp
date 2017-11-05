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
		
		function doSearch(){
			$('#tableId').datagrid('load',{
				'name':$("#name").val(),
				'password':$("#password").val(),
				'trueName':$("#trueName").val(),
				'email':$("#email").val(),
				'phone':$("#phone").val(),
				'roleName':$("#roleName").val()
			});
		}
			
		</script>
	</head>
	
	<body>
		<table id="tableId" class="easyui-datagrid" fitColumns="true" toolbar="#toolbar" pagination="true"
			data-options="fit:true,singleSelect:false,url:'${ctx}/customer/findCustomerContribution.action',method:'get'">
				<thead>
					<tr id="target">
						<th data-options="field:'name',width:100,align:'center'">客户名成</th>
						<th data-options="field:'totalContribution',width:100,align:'center'">订单总金额</th>
					</tr>
				</thead>
			</table>
			
			<!-- toolbar -->
			<div id="toolbar">
				<div>
					<input  class="easyui-textbox" id="name" prompt="客户名"/>
					<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
				</div>
			</div>
	</body>

</html>
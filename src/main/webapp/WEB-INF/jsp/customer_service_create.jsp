<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function dosave(){
		var options={
			url:"${ctx}/customerService/add.action",
			type:"post",
			dataType:"json",
			data:$('#form').serialize(),
			success:function(data){
				if(data.status == Util.SUCCESS){
					$.messager.alert('系统提示',data.msg);
				}else{
					$.messager.alert('系统提示',data.msg);
				}
			}
		};
		$.ajax(options);
	}
</script>
</head>
<body>
		<div class="easyui-panel" title="服务创建" style="width:600px;height:400px;" left="50" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
			    <table cellpadding="5">
		    		<tr>
		    			<td>服务类型:</td>
		    			<td><input class="easyui-combobox" id="serviceType"  name="serviceType"  
		    			data-options="
		    			required:true,
		    			url:'${ctx}/dataDic/findServiceType.action',
		    			editable:true ,
		    			valueField: 'dataDicValue',    
       				    textField: 'dataDicValue' "></input></td>
		    			<td>客户:</td>
		    			<td><input id="customer" class="easyui-textbox" name="customer" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>概要:</td>
		    			<td><input id="overview" class="easyui-textbox" name="overview" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>服务请求:</td>
		    			<td><input id="serviceRequest" class="easyui-textbox" name="serviceRequest" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>创建人:</td>
		    			<td><input id="createPeople" class="easyui-textbox" name="createPeople" data-options="required:true"></input></td>
		    			<td>创建时间:</td>
		    			<td><input id="createTime" class="easyui-datetimebox" name="createTime" data-options="required:true"></input></td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a href="javascript:dosave();" class="easyui-linkbutton" iconCls = "icon-add">提交</a>
			    </div>    
			</form>  
    	</div>
</body>
</html>
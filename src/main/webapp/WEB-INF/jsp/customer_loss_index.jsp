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
		
		function godelete(){
			array = Util.getSelected("#tableId");
			if(array.length == 0){
				$.messager.alert('系统提示',"请选择您要删除的数据");
				return;
			}
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
			    if (r){ $.post(
						  "${ctx}/customerLoss/delete.action", //url
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
				'customerName':$("#name").val(),
				'customerNo':$("#num").val(),
				'status':$("#status").val(),
			});
		}
			
			var url;
			function openaddDialog(){
				$("#dialog").dialog("open").dialog("setTitle","添加信息");
				url = "${ctx}/customerLoss/add.action";
				$('#form').form("clear");
			}
			
			function openUpdateDialog(){
				var row = $('#tableId').datagrid('getSelected');
				if(row == null){
					$.messager.alert("系统提示","请选择你要修改的对象");
					return;
				}
				$("#dialog").dialog("open").dialog("setTitle","修改信息");
				url = "${ctx}/customerLoss/update.action";
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
				$("#tableId").datagrid({
				 	url:'${ctx}/customerLoss/findAll.action',
					fit:true,
					singleSelect:true,
					toolbar:'#toolbar',
					rownumbers:true,
					fitColumns :true,
					pagination:true,
					columns:[[    
					     {field:'ck',checkbox:true},   
					     {field:'id',title:'编号',width:50,align:'center'},    
					     {field:'customerNo',title:'客户编号',width:50,align:'center'},    
					     {field:'customerName',title:'客户名称',width:200,align:'center'},    
					     {field:'customerManager',title:'客户经理',width:150,align:'center'},    
					     {field:'lastOrderTime',title:'上次下单日期',width:100,align:'center'}, 
					     {field:'confirmLossTime',title:'确认流失日期',width:250,align:'center'},
					     {field:'status',title:'客户状态',width:150,align:'center',
					    	formatter: function(value,row,index){
					    		if(value == 0){
					    			return "暂缓流失";
					    		}else{
					    			return "确认流失";
					    		}
					    	}	 
					     },
					     {field:'lossReason',title:'流失原因',width:150,align:'center'},
					     {field:'a',title:'操作',width:150,align:'center',
					    	 formatter: function(value,row,index){
					    		 if(row.status == 0){
					    			 return "<a href='javascript:Suspendloss("+row.id+");'>暂缓流失</a>";
					    		 }else{
					    			 return "确认客户流失";
					    		 }
					    	 }
					     }  
					     
					]]
				});
		});
			function Suspendloss(id){
				window.parent.openTab('客户流失暂缓措施管理'+id+'','${ctx}/customerLossMeasure/index.action?id='+id+'','icon-add');
			}
			
		</script>
	</head>
	
	<body>
		<table id="tableId"></table>
			
			<!-- toolbar -->
			<div id="toolbar">
				<div>
					客户名称：<input  class="easyui-textbox" id="name" name="customerName"/>
					客户编号：<input  class="easyui-textbox" id="num" name="customerNo"/>
					客户状态：<select id="status" name="status">
						     <option value="">请选择</option>
						     <option value="0">暂缓流失</option>
						     <option value="1">确认流失</option>
						  </select>
					<a href="javascript:doSearch();" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
				</div>
			</div>
	</body>
</html>
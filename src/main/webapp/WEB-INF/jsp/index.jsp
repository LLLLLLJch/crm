<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			* {
				margin: 0;
				padding: 0;
			}
		</style>
		<script>
			function openTab(title,url,iconCls){
				if($('#tabsId').tabs('exists',title)) {
					//如果这个tab已经打开了，就只需要设置这个tab为选中的状态
						$('#tabsId').tabs('select',title);
				} else {
					//如果这个tab没有被选中，就需要打开这个tab
					var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"+url+"'></iframe>";
					$('#tabsId').tabs('add',{
						title:title,
						iconCls:iconCls,
						closable:true,
						content:content	
					})
				}
			}
			
			function updatePassword(){
				$("#dialog").dialog('open');
				$('#form').form("clear");
			}
			
			 $(function() {
			       $("#name").blur(function(){
			           var name = $(this).val();
			           $.post(
			               "${ctx}/user/checkName.action", //url
			               {"name":name}, //data
			               function(data) { //callback
			                  if(data.status == Util.ERROR ) {//用户已经存在
			                      $("#nameInfo").html("用户名不存在");
			                      $("#nameInfo").css("color", "red");
			                  } else {
			                      $("#nameInfo").html("用户名正确");
			                      $("#nameInfo").css("color", "green");
			                  }
			               },
			               "json" //type
			           );
			       });
			    }); 
				 
			 $(function() {
			       $("#surePassword").blur(function(){
			           var newPassword = $("#newPassword").val();
			           var surePassword = $("#surePassword").val();
			           $.post(
			               "${ctx}/user/surePassword.action", //url
			               {"newPassword":newPassword,"surePassword":surePassword}, //data
			               function(data) { //callback
			                  if(data.status == Util.ERROR ) {//用户已经存在
			                      $("#newPasswordInfo").html("两次输入的用户名不一致");
			                      $("#newPasswordInfo").css("color", "red");
			                  } else {
			                      $("#newPasswordInfo").html("两次输入的用户名正确");
			                      $("#newPasswordInfo").css("color", "green");
			                  }
			               },
			               "json" //type
			           );
			       });
			    });
			 
			 	function dosave(){
			 		$('#form').form('submit', {    
					    url:"${ctx}/user/updatePassword.action",    
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
			 	
			 	function goexit(){
			 		$.messager.confirm('系统提示', '你确定要退出吗', function(r){
						if (r){
							/* $.post(
								"${ctx}/login/goexit.action",
								function(){ */
									window.location.href="${ctx}/login/goexit.action";
							/* 	},
								"json"
							); */
						}
					});
			 	}
		</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north'" style="height:100px;background-image: url('${ctx}/resources/img/u=1811189987,613661944&fm=27&gp=0.jpg')">
			<div style="float:right">
				<h2 style="color:white">欢迎您：${user.name}</h2>
			</div>
		</div>
		<div data-options="region:'south',split:false" style="height:25px;"></div>
		<div region="west" style="width: 200px" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="营销管理" data-options="selected:true,iconCls:'icon-yxgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('营销机会管理','${ctx}/saleChance/index.action','icon-yxjhgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-yxjhgl'"
					style="width: 150px">营销机会管理</a> <a
					href="javascript:openTab('客户开发计划','${ctx}/saleChance/salePlan.action','icon-khkfjh')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khkfjh'"
					style="width: 150px">客户开发计划</a>
			</div>
			<div title="客户管理" data-options="iconCls:'icon-khgl'"
				style="padding: 10px;">
				<a
					href="javascript:openTab('客户信息管理','${ctx}/customer/index.action','icon-khxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khxxgl'"
					style="width: 150px;">客户信息管理</a> <a
					href="javascript:openTab('客户流失管理','${ctx}/customerLoss/index.action','icon-khlsgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsgl'"
					style="width: 150px;">客户流失管理</a>
			</div>
			<div title="服务管理" data-options="iconCls:'icon-fwgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('服务创建','${ctx}/customerService/index.action','icon-fwcj')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">服务创建</a>
				<a
					href="javascript:openTab('服务分配','${ctx}/customerService/allocation.action','icon-fwfp')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">服务分配</a>
				<a
					href="javascript:openTab('服务处理','${ctx}/customerService/process.action','icon-fwcl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">服务处理</a>
				<a
					href="javascript:openTab('服务反馈','${ctx}/customerService/feedback.action','icon-fwfk')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">服务反馈</a>
				<a
					href="javascript:openTab('服务归档','${ctx}/customerService/goServiceArchive.action','icon-fwgd')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">服务归档</a>
			</div>
			<div title="统计报表" data-options="iconCls:'icon-tjbb'"
				style="padding: 10px">
				<a href="javascript:openTab('客户贡献分析','${ctx}/customer/goCustomerContributionPage.action','icon-khgxfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgxfx'"
					style="width: 150px;">客户贡献分析</a> <a
					href="javascript:openTab('客户构成分析','${ctx}/customer/goCustomerCompositionPage.action','icon-khgcfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgcfx'"
					style="width: 150px;">客户构成分析</a> <a
					href="javascript:openTab('客户服务分析','${ctx}/customer/goCustomerServiceAnalysisPage.action','icon-khfwfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khfwfx'"
					style="width: 150px;">客户服务分析</a> <a
					href="javascript:openTab('客户流失分析','khlsfx.jsp','icon-khlsfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsfx'"
					style="width: 150px;">客户流失分析</a>
			</div>
			<div title="基础数据管理" data-options="iconCls:'icon-jcsjgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('数据字典管理','${ctx}/dataDic/index.action','icon-sjzdgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-sjzdgl'"
					style="width: 150px;">数据字典管理</a> <a
					href="javascript:openTab('产品信息查询','${ctx}/product/index.action','icon-cpxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cpxxgl'"
					style="width: 150px;">产品信息查询</a> 
					<a href="javascript:openTab('用户信息管理','${ctx}/user/index.action','icon-user')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-item'"
				style="padding: 10px">
				<a href="javascript:updatePassword();"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword'"
					style="width: 150px;">修改密码</a> 
					<a href="javaScript:goexit();" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			</div>
		</div>
	</div>
	<div region="south" style="height: 25px; padding: 5px" align="center">
		Java1707CRM管理系统
	</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="tabsId" class="easyui-tabs" data-options="fit:true" style="width:500px;height:250px;">   
			    <div data-options="title:'首页'"style="padding:20px;display:none; background-image:url(${ctx}/resources/img/u=924351836,356584454&fm=27&gp=0.jpg)">   
			    </div>   
			</div>  
		</div>
		
		<div id="dialog" class="easyui-dialog" style="width:500px;height:200px;" closed="true" buttons="#dialog-button">
    		<form id="form" action="" method="post">   
    			<input type="hidden" id="id" name="id"/>
			    <table cellpadding="5">
		    		<tr>
		    			<td>用户名:</td>
		    			<td><input class="easyui-validatebox" id="name" type="text" name="name" data-options="required:true"></input>
		    			<span id="nameInfo"></span></td>
		    		</tr>
		    		<tr>
		    			<td>原密码:</td>
		    			<td><input class="easyui-validatebox" name="password" type="text" data-options="required:true"></input>
		    			<span id="passwordInfo"></span></td>
		    		</tr>
		    		<tr>
		    			<td>新密码:</td>
		    			<td><input class="easyui-validatebox" id="newPassword" type="text" name="newPassword" data-options="required:true"></input></td>
		    		</tr>
		    		<tr>
		    			<td>确认新密码:</td>
		    			<td><input class="easyui-validatebox" id="surePassword" type="text" data-options="required:true"></input>
		    			<span id="newPasswordInfo"></span></td>
		    		</tr>
		    	</table>
			    <div id="dialog-button">   
			    	<a href="javascript:dosave();" class="easyui-linkbutton" iconCls = "icon-add">提交</a>
			    	<a  class="easyui-linkbutton" iconCls = "icon-cancel">关闭</a>
			    </div>    
			</form>  
    	</div>
		
	</body>

</html>
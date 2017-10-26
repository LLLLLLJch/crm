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
			
		</script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north'" style="height:100px"></div>
		<div data-options="region:'south',split:false" style="height:25px;"></div>
		<div region="west" style="width: 200px" title="导航菜单" split="true">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="营销管理" data-options="selected:true,iconCls:'icon-yxgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('营销机会管理','saleChanceManage.jsp','icon-yxjhgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-yxjhgl'"
					style="width: 150px">营销机会管理</a> <a
					href="javascript:openTab('客户开发计划','cusdevplanManage.jsp','icon-khkfjh')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khkfjh'"
					style="width: 150px">客户开发计划</a>
			</div>
			<div title="客户管理" data-options="iconCls:'icon-khgl'"
				style="padding: 10px;">
				<a
					href="javascript:openTab('客户信息管理','customerManage.jsp','icon-khxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khxxgl'"
					style="width: 150px;">客户信息管理</a> <a
					href="javascript:openTab('客户流失管理','customerLossManage.jsp','icon-khlsgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khlsgl'"
					style="width: 150px;">客户流失管理</a>
			</div>
			<div title="服务管理" data-options="iconCls:'icon-fwgl'"
				style="padding: 10px">
				<a
					href="javascript:openTab('服务创建','customerServiceCreate.jsp','icon-fwcj')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcj'" style="width: 150px;">服务创建</a>
				<a
					href="javascript:openTab('服务分配','customerServiceAssign.jsp','icon-fwfp')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfp'" style="width: 150px;">服务分配</a>
				<a
					href="javascript:openTab('服务处理','customerServiceProce.jsp','icon-fwcl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwcl'" style="width: 150px;">服务处理</a>
				<a
					href="javascript:openTab('服务反馈','customerServiceFeedback.jsp','icon-fwfk')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwfk'" style="width: 150px;">服务反馈</a>
				<a
					href="javascript:openTab('服务归档','customerServiceFile.jsp','icon-fwgd')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-fwgd'" style="width: 150px;">服务归档</a>
			</div>
			<div title="统计报表" data-options="iconCls:'icon-tjbb'"
				style="padding: 10px">
				<a href="javascript:openTab('客户贡献分析','khgxfx.jsp','icon-khgxfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgxfx'"
					style="width: 150px;">客户贡献分析</a> <a
					href="javascript:openTab('客户构成分析','khgcfx.jsp','icon-khgcfx')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-khgcfx'"
					style="width: 150px;">客户构成分析</a> <a
					href="javascript:openTab('客户服务分析','khfwfx.jsp','icon-khfwfx')"
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
					href="javascript:openTab('数据字典管理','dataDicManage.jsp','icon-sjzdgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-sjzdgl'"
					style="width: 150px;">数据字典管理</a> <a
					href="javascript:openTab('产品信息查询','productSearch.jsp','icon-cpxxgl')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-cpxxgl'"
					style="width: 150px;">产品信息查询</a> 
					<a href="javascript:openTab('用户信息管理','${ctx}/user/index.action','icon-user')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user'" style="width: 150px;">用户信息管理</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-item'"
				style="padding: 10px">
				<a href="javascript:openPasswordModifyDialog()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-modifyPassword'"
					style="width: 150px;">修改密码</a> <a href="javascript:logout()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-exit'" style="width: 150px;">安全退出</a>
			</div>
		</div>
	</div>
	<div region="south" style="height: 25px; padding: 5px" align="center">
		Java1707CRM管理系统
	</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<div id="tabsId" class="easyui-tabs" data-options="fit:true" style="width:500px;height:250px;">   
			    <div data-options="title:'首页'"style="padding:20px;display:none;">   
			        首页    
			    </div>   
			</div>  
		</div>
	</body>

</html>
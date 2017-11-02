<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="../common/header.jsp"%>
<html>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>登录(Login)</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="${ctx}/resources/thirdlib/login/assets/css/reset.css">
        <link rel="stylesheet" href="${ctx}/resources/thirdlib/login/assets/css/supersized.css">
        <link rel="stylesheet" href="${ctx}/resources/thirdlib/login/assets/css/style.css">
		<script src="${ctx}/resources/thirdlib/login/assets/js/jquery-1.8.2.min.js" ></script>
        <script src="${ctx}/resources/thirdlib/login/assets/js/supersized.3.2.7.min.js" ></script>
        <script src="${ctx}/resources/thirdlib/login/assets/js/supersized-init.js" ></script>
        <script src="${ctx}/resources/thirdlib/login/assets/js/scripts.js" ></script>


        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="assets/js/html5.js"></script>
        <![endif]-->

    </head>

    <body style="background-image:url(${ctx}/resources/thirdlib/login/assets/img/3.jpg)">

        <div class="page-container">
            <h1>登录</h1>
            <form action="${ctx}/login/login.action" method="post">
                <input name="name" prompt="用户名" class="easyui-textbox" style="width:300px"><br/>
                <input name="password" class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:300px"><br/> 
                <select name="roleName" class="easyui-combobox" style="width:200px;"><br/>
                	<option value=""><font color="red">请选择</font></option>
                	<option value="系统管理员 "><font color="red">系统管理员 </font></option>
                	<option value="销售主管">销售主管</option>
                	<option value="客户经理">客户经理</option>
                	<option value="高管">高管</option>
                </select>
                <button type="submit" class="submit_button">登录</button>
                <div class="error"><span>+</span></div>
            </form>          
        </div>	
    </body>

</html>


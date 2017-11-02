<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">  
     var ctx="${pageContext.request.contextPath}";  
</script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/thirdlib/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/thirdlib/jquery-easyui/themes/icon.css" />
<!--jquery核心库-->
<script src="${pageContext.request.contextPath}/resources/thirdlib/jquery-easyui/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<!--easyui核心库-->
<script src="${pageContext.request.contextPath}/resources/thirdlib/jquery-easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resources/thirdlib/jquery-easyui/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>

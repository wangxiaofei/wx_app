<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%
    response.setStatus(HttpServletResponse.SC_OK);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Miaozhen Systems 知客1.0</title>
<link href="<s:url value='/theme/import.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function jump(url) {
	window.location = url;
}
</script>
</head>
<body>
	<div class="map_content">
		<div class="map_left">系统信息</div>
	</div>
	<div class="map_center fs14">
		<img src="<s:url value='/theme/pic/alerts-important.gif' />" width="32" height="32" />
		404 not found<br /> 抱歉，您要查看的页面不存在，您可以：<br />
		<br />
		<input type="button" value="返回上一页" class="btBule" 	title="返回上一页" onclick="history.back(-1);" />&nbsp;
		<!-- <input type="button" value="返回首页" class="btBule" title="返回首页" 	onclick="jump('<s:url value='/task/index' />');" /> -->
	</div>
</body>
</html>
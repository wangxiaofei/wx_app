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
		<img src="<s:url value='/theme/pic/u0_normal.png' />" width="167" height="146" /><br />
		Sorry，您没有权限访问这个页面，你可以：<br />
		<br /> <input type="button" value="返回登录页" class="btBule"
			title="返回登录页" onclick="jump('<s:url value='/' />');" />
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<s:url value='/theme/import.css' />" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="map_content">
		<div class="map_left">系统信息</div>
	</div>
	<div class="map_center fs14">
		<img src="<s:url value='/theme/pic/alerts-important.gif' />" width="32" height="32" />
		500 server error<br /> 
		<c:choose>
			<c:when test="${errMsg!=null}"><s:message code="${errMsg}"/></c:when>
			<c:otherwise>服务器端出错，请稍后再试。</c:otherwise>
		</c:choose>
		
		<br/>
		<input type="button" value="返回上一页" class="btBule" 	title="返回上一页" onclick="history.back(-1);" />
	</div>
</body>
</html>
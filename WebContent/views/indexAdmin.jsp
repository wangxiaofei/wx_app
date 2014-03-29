<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PlanNex</title>
<script type="text/javascript">
<!--
function resetFrameSize(h,w) {
	document.getElementById("f1").rows=h+",*";
	document.getElementById("f2").cols=w+",*";
}
function getFrameSize() {
	var o=new Object();
	o.h=document.getElementById("f1").rows.split(",")[0];
	o.w=document.getElementById("f2").cols.split(",")[0];
	return o;
}
-->
</script>
<script src="js/menu.js" type="text/javascript"></script>
</head>

<frameset id="f1" rows="80,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="menu/topAdmin.html" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />

    
    <frame src="task/taskListAdmin.html" name="mainFrame" id="mainFrame" title="mainFrame" />


  </frameset>
</frameset>



<noframes><body id="page2">
</body>
</noframes></html>

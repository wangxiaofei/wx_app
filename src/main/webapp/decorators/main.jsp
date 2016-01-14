<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/authentication.tld" prefix="au"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title><decorator:title default="PlanNex" /></title>
<link href="<s:url value='/theme/import.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/jquery-ui.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/drop.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/jquery-alerts.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/grid/css/st_grid.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/global.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/bar.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/other.css' />" rel="stylesheet"
	type="text/css" />
<link href="<s:url value='/theme/treeMenu.css' />" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value='/grid/js/st_grid.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-ui.js' />"></script>
<script type="text/javascript"
	src="<s:url value='/js/jquery-alerts.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/dialog.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/escape.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/converter.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/checker.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/main.js' />"></script>
<script type="text/javascript" src='<s:url value="/js/drop.js"></s:url>'></script>
<link rel="stylesheet"
	href='<s:url value="/js/zTree/css/zTreeStyle/zTreeStyle.css"/>'
	type="text/css" />
<script type="text/javascript"
	src='<s:url value="/js/zTree/js/jquery.ztree.core-3.5.js"/>'></script>
<script type="text/javascript"
	src='<s:url value="/js/zTree/js/jquery.ztree.excheck-3.5.js"/>'></script>
<script type="text/javascript">
	var interval;
	$(function() {
		flushTableStyle();
		loadLastDate();
		//init();
		leftFlush();
		$("#h_official").click(function() {
			$("#officialNormal").toggle();
		});
		$("#h_compared").click(function() {
			$("#officialCompared").toggle();
		});
		$("#h_custom").click(function() {
			$("#customNormal").toggle();
		});
		$("#h5_c_compared").click(function() {
			$("#customCompared").toggle();
		});
		$("#customAdd").click(function() {
			var url = "<s:url value='/task/operationChecker' />";
			var data = {
				userId : "${sessionKeyUser.userId}"
			};
			if (!ajaxExc(url, data)) {
				jAlert("您的任务次数已全部用完，不可再增加任务。若要增加任务次数请联系客服人员。", "系统消息");
				return false;
			}

			jump("<s:url value='/task/authAddTask' />");
			return false;
		});
		$("#officialAdd").click(function() {
			jump("<s:url value='/task/authAddTask' />");
			return false;
		});
		
		
	});

	function hightLightLeft(id) {

		interval = setInterval("hll(" + id + ");", 50);
		sampleInfo(id);
	}

	function hll(id) {
		$("#l" + id).attr("class", "in");
		if (typeof $("#l" + id).attr("class") != "undefined") {
			clearInterval(interval);
		}
	}

	function hightLightNav(id) {
		$("#tops").find("li").each(function(idx) {
			$(this).removeClass("selected");
		});
		$("#" + id).addClass("selected");
	}

	function jump(url) {
		window.location = url;
	}

	function leftFlush() {
		showAddImg();
		showNormalUser();
		officalList("officialNormal", "officialCompared");
		customList("${sessionKeyUser.role}", "${sessionKeyUser.userId}",
				"customNormal", "customCompared");
	}

	/**
	 * 添加按钮 位置分角色显示
	 */
	function showAddImg() {
		if ('admin' == "${sessionKeyUser.role}") {
			$("#add_official").show();
			$("#add_normal").hide();
		} else {
			$("#add_normal").show();	
			$("#add_official").hide();
		}
	}
	function showNormalUser() {
		if ('admin' == "${sessionKeyUser.role}") {
			allUserList("customNormal", "customCompared");
		} else {
			// clear
			$("#liseid").hide();
		}
	}

	function sampleInfo(taskId) {
		var url = "/task/sample/info";
		var params = {
			taskId : taskId,
			timestamp : new Date()
		};
		$.getJSON(url, params, function(data) {
			if ('admin' == "${sessionKeyUser.role}") {
				$("#sample_div").append("人群样本: ");
				$("<strong>").text(milliFormat(data.sample)).appendTo(
						$("#sample_div"));
			}
			$("#sample_div").append("&nbsp;&nbsp;&nbsp;&nbsp;时间段: 从");
			$("#sample_div").append(
					new Date(data.startTime).format('yyyy-MM-dd'));
			$("#sample_div").append("至");
			$("#sample_div")
					.append(new Date(data.endTime).format('yyyy-MM-dd'));
			$("#sample_div").append("");
			$("#sample_div").show();
		});
	}

	$(function() {
		$('ul.siteNav li').click(function() {
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
			$('.userInfo li.selected').removeClass('selected');
		});
		$('#topLinks li a').click(function() {
			$('#siteNavId li.selected').removeClass('selected');
			$(this).parent().addClass('selected');
			$(this).parent().siblings().removeClass('selected');
		});
	});
	function flushTableStyle(){
		$('table').not(".no-highLight").find("tbody tr").each(
				function(idx, obj) {
					if (idx % 2 == 0) {
						$(obj).removeClass("t2").addClass("t1");
					} else {
						$(obj).removeClass("t1").addClass("t2");
					}
					$(obj).mouseover(function() {
						$(this).addClass("t3");
					}).mouseout(function() {
						$(this).removeClass("t3");
					});
				});
	}
	function hightLightNav(i){
	    $("#siteNavId").find("li").each(function(idx){
	        if(i == idx){
	            $(this).addClass("selected");
	        }else{
	            $(this).removeClass("selected");
	        }
	    });
	    $('.userInfo li.selected').removeClass('selected');
	    $('#topLinks li a').click(function() {
	        $('#siteNavId li.selected').removeClass('selected');
	        $(this).parent().addClass('selected');
	        $(this).parent().siblings().removeClass('selected');
	    });
	};
	function loadLastDate(){
		var url = '<s:url value="/sys/getLastDate"></s:url>';
		var param ={
				
		};
		$.post(url,param,function(data){
			if(data){
				$("#lastDate").html("系统可计算截止日期："+data);	
			}			
		},'json');	
	}	
</script>
<decorator:head />
</head>
<body class="back">
	<div class="top">
		<h1>
			<img src='<s:url value="/theme/pic/PlanNex_logo.png"/>' width="215" height="80" />
		</h1>

		<ul class="siteNav" id="siteNavId">
			<c:if test="${sessionScope.user.auth eq 'normal'}">
				<li class="selected"><a href='<s:url value="/task/toTaskList"/>'
					 title="任务管理">任务管理</a></li>
			</c:if>
			<c:if test="${sessionScope.user.auth eq 'admin'}">
			<li class="selected"><a href='<s:url value="/task/toAdminTaskList"/>'
				title="任务管理">任务管理</a></li>
			<li><a href='<s:url value="/user/loadUsers"/>'
				title="用户管理">用户管理</a></li>
			</c:if>
		</ul>

		<div class="userInfo">
			<ul id="topLinks">
				<li id="lastDate"></li>
				<li><a href="<s:url value='/help'/>" target="_blank">产品介绍</a></li>
				<li class="Welcome">欢迎您：${sessionScope.user.userName }</li>
				<li><a href='<s:url value="/logout"/>' target="_top">退出</a></li>				
			</ul>
		</div>
	</div>
</body>
<decorator:body />
</html>
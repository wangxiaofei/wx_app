<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PlanNex</title>
<link href="theme/import.css" rel="stylesheet" type="text/css" />
<link href="theme/drop.css" rel="stylesheet" type="text/css" />
<link href="theme/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="js/tabColor.js" type="text/javascript"> </script>

<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script>
function jump(url){

window.location=url;
}
function changeTag(Index){
	var lis=parent.window.frames[0].document.getElementsByTagName("li");
			for(var j=0;j<lis.length;j++) {
				if(j==Index) {
					lis[j].className="selected";
				
				} else {
					lis[j].className="";
				}				
	}
}
</script>
</head>



<body class="back">
<div class="top">
<h1 ><img src="theme/pic/PlanNex_logo.png" width="215" height="80" /></h1>
</div>
<div class="content">

  <div class="tab_content">
    <p><strong class="currentPage">如何登录</strong><br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请在浏览器输入<a href="http://plannex.miaozhen.com">http://plannex.miaozhen.com</a>，输入已分配的用户名和密码登录系统。 <br />
  此系统只支持火狐浏览器，其他浏览器的支持在以后的版本中增加。 <br /><br />
  <strong class="currentPage">软件功能</strong><br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PlanNex是基于Monitor活动日志计算跨活动、跨媒体的，相交样本量，impression，click，clicker，UV1+…20+指标计算系统。并且运算条件中还支持地域的选择，TA的选择等。 <br />
  不读出来：在介绍的时候通过操作界面来解释。 <br /><br />
  <strong class="currentPage">任务状态</strong><br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务状态包括（未提交，列队中，计算中，已完成，失败）五中状态。 <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未提交：表示创建任务后，不提交到列队只是单独保存。 <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;列队中：系统每次只能同时计算2个任务，所以任务提交后任务进入排队状态，等待计算。 <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;计算中：从列队中取任务将任务发送到计算系统，计算并输出结果。 <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成：任务已经计算完成并可以下载报告同时邮件通知任务创建者。 <br />
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;失败任务：在计算过程中出现任何错误，都将停止计算，任务状态为失败同时邮件通知任务创建者。<br /> <br />
<strong class="currentPage">创建任务</strong></p>
      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击任务列表左上方创建按钮创建任务。 <br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务由任务基本信息和N多子任务组成。 <br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务基本信息包括，输入任务名称及广告主信息，选择计算方式（排重和OverLap），选择人群类型（所有网民，稳定人群，目标受众），选择Panel及受众属性，选择IP库版本及备注信息。 <br />
        子任务信息包括，输入任务名称，campaign ID及监测点ID，计算方式（Total和byday），选择开始时间、结束时间和选择地域（地域与Monitor地域相同）。 <br />
        可以创建多个子任务，子任务开始和结束时间及地域为统一选择，不支持单独监测点选择时间段（即在一个子任务中，不能为每个监测点选择时间段）。 <br /><br />
        <strong class="currentPage">媒体打包及Campaign打包介绍 </strong><br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;子任务录入campaign ID和监测点ID支持多行录入，campaign ID支持录入多个活动以英文分号分隔，监测点ID支持录入多个监测点以英文分号分隔。 <br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果计算跨Campaign的数据，在campaign ID这一列中录入单个或多个campaign ID，监测点这一列录入监测点id（监测点ID必须是前面campaign id这个campaign中包含的）。 <br /><br />
        <span ><strong class="currentPage">Excel模板导入子任务</strong></span><br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;避免手工录入错误，系统提供Excel导入功能，只允许导入子任务相关信息。 <br />
      </p>
      <p>&nbsp;</p>
      <p><strong>如何开通帐号并使用 </strong></p>
      <p><strong>提交邮件申请给  </strong><a href="mailto:gushi@miaozhen.com"><strong>gushi@miaozhen.com</strong></a><br />
        <strong>邮件内容中包含：部门，姓名，邮箱（邮箱就是用来收结果报告） </strong><br />
      <strong>提交申请2天内，会收到帐号密码。</strong></p>
      <p><br />
        使用中可能存在的问题 </p>
<ol>
  <li>campaign id和spid必须对应，否则会计算出的指标误差较大。 </li>
  <li>多个活动CampaignID或多个监测点ID录入用英文分号隔开。 </li>
<li>模板导入，地域列表参照模板中地域Sheet录入，防止地域名称错误导致某地域没有计算出结果。</li>  </ol>   
  <p>&nbsp;</p>
  <p ><strong class="currentPage">视频教程在线播放</strong></p>
  <p><a href="hetemp/video.swf">视频步骤介绍</a></p>
  <p><a href="hetemp/p1.swf">第一部分</a> 如何登录系统</p>
  <p><a href="hetemp/p2.swf">第二部分</a> 软件功能介绍</p>
  <p><a href="hetemp/p3.swf">第三部分</a> 用一个实际的案例教会大家使用系统</p>
  <p><a href="hetemp/p4.swf">第四部分</a> 申请帐号</p>
  <p><a href="hetemp/p5.swf">第五部分</a> 遇到的问题</p>
  <p>&nbsp;</p>
  <p><a href="hetemp/update.txt">What's New</a></p>
  <p>&nbsp;</p></div></div>
</body>
<script type="text/javascript">
initial(0);
</script>
<script src="js/dialog.js" type="text/javascript"></script>
<script type="text/javascript" src="js/drop.js"></script>
</html>

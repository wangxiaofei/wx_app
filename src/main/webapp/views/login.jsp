<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PlanNex</title>
<link href='<s:url value="/theme/login.css"/>' rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href='<s:url value="/js/st-validate2.0/css/st-validate.css"/>' />
<script type="text/javascript" src='<s:url value="/js/st-validate2.0/js/st-validate2.0.js"/>'></script>

<script type="text/javascript" src="<s:url value='/js/rsa.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/hash.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common-check.js'/>"></script>

<script type="text/javascript" src='<s:url value="/js/jquery.js"/>'></script>
<script type="text/javascript" src='<s:url value="/js/jquery.watermark.js"/>'></script>

<script type="text/javascript">
var exponent='${exponent}';
var modulus='${modulus}';
var key = RSAUtils.getKeyPair(exponent, '', modulus);

var cookieLogin = false;
var rememberedUsername = getCookie("auun");
var rememberedPassword = getCookie("auunpd");
$(function() {
	//if(null != '${username}' && "" != '${username}'){
	//	$('input#pd').focus();
	//}
	
    $('input#user').watermark('loginName');
    $('input#pd').watermark('password');
    $('input#rememberMe').watermark('rememberMe');
    //$('input#answer').watermark('');
    
   // $('#kaptchaImage').click(function () {//生成验证码  
   //     $(this).hide().attr('src', "<s:url value='/checkImage'/>?" + Math.floor(Math.random()*100) ).fadeIn();      
   //  });   

    var seed = $("input#seed").val();
	
	if(rememberedUsername !=null && rememberedUsername != "" && rememberedPassword != null
	 			&& rememberedPassword != "" ){
	    $('input#user').val(rememberedUsername);
	    $('input#pd').val('******');
	    $('input#loginName').val(rememberedUsername);
	    $('input#password').val(RSAUtils.encryptedString(key,rememberedPassword+seed ));
	    $('input#answer').focus();
	    cookieLogin = true;
	}
	
	/*$('#answer').blur(function(){
		var answer = trim($('input#answer').val());
		if(null == answer || "" == answer){
			$('#errorMsg').html("");
			return false;
		}
		
		$.get("<s:url value='/imgCmp'/>?answer="+answer,function(data){
			if(false == data){
				$('#errorMsg').html("");
				return false;
			}	
		});
		$('#errorMsg').html("");
	});*/
	
	$('#btnLogin').click(function(){
		checkLogin();
	});
});

function checkLogin(){
	var username = trim($('input#user').val());
	if(null == username || "" == username){
		cookieLogin = false;
		$('#errorMsg').html('<s:message code="login.username.emputy"/>');
		$('input#user').focus();
		return false;
	}
	var password = trim($('input#pd').val());
	if(null == password || "" == password){
		cookieLogin = false;
		$('#errorMsg').html('<s:message code="login.password.emputy"/>');
		$('input#pd').focus();
		return false;
	}
	
	if(true == cookieLogin && username != rememberedUsername){
		cookieLogin = false;
	}
	if(true == cookieLogin && password != '******'){
		cookieLogin = false;
	}
	//var answer = trim($('input#answer').val());
	//if(null == answer || "" == answer){
	//	$('#errorMsg').html("");
	//	$('input#answer').focus();
	//	return false;
	//}
	
	//$.get("<s:url value='/imgCmp'/>?answer="+answer,function(data){
	//	if(false == data){
	//		$('#errorMsg').html("/>");
	//		$('input#answer').focus();
	//		return false;
	//	}	
	//	$('#errorMsg').html("");
	//	var seed = $('input#seed').val();
	//	if(!cookieLogin){
	//		$('input#loginName').val(username);
	//		$('input#password').val(RSAUtils.encryptedString(key, MD5(password)+seed ));
	//	}
	//	$('form#loginForm').submit();
	//});
	
	$('#errorMsg').html("");
	var seed = $('input#seed').val();
	if(!cookieLogin){
		$('input#loginName').val(username);
		$('input#password').val(RSAUtils.encryptedString(key, MD5(password)+seed ));
		//$('input#password').val(password);
	}
	$('form#loginForm').submit();

}

//get cookie
function getCookie(c_name)
{
	if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				return document.cookie.substring(c_start, c_end);
			}
		}
	return "";
}

$(function(){
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	checkLogin();
	     }
	};
});  

//用户按下Enter按键
function enterKeyClick(obj, evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "")
	keyCode = evt.keyCode ? evt.keyCode
			: (evt.which ? evt.which : evt.charCode);
	if (keyCode == 13) {
		checkLogin();
	}
}

function tip(){
	$("#errorMsg").html("");
}
</script>

</head>

<body>
<div class="wrap">
<div class="main clearfix">
<div class="head">
    <div class="logo"></div>
</div>
<div class="content">
<form action='<s:url value="/login"/>' id="loginForm" method="post">
	<input type="hidden" id="seed" name="seed" value="${seed }"/>
	<input type="hidden" id="loginName" name="loginName"/>
	<input type="hidden" id="password" name="password"/>
  <ul>
     <li><input id="user" type="text"  class="text" value="${userName}" onfocus="this.className='textHover';this.onmouseout='';" onblur="this.className='text';this.onmouseout=function(){this.className='text'};" onmousemove="this.className='textHover'" onmouseout="this.className='text'"/></li>
     <li><input id="pd" type="password"  class="text" onfocus="this.className='textHover';this.onmouseout='';" onblur="this.className='text';this.onmouseout=function(){this.className='text'};" onmousemove="this.className='textHover'" onmouseout="this.className='text'"/></li>
     <li class="btWidth"><input type="button" id="btnLogin" class="btn" value="登&nbsp;&nbsp;录" ></li>
<!--      <li class="loginVc"><input name="keyword" type="text"  size="16" class="search_kw" value="请输入验证码" onfocus="this.value==this.defaultValue?this.value='':null;" onblur="this.value==''?this.value='请输入验证码':null;"> -->
<!--       <a href="#"><img src="theme/pic/vc.png" width="100" height="40" title="Try another one"/></a></li> -->
     <li class="loginPw"><input name="rememberMe" value="true" type="checkbox" class="checkbox" id="rememberMe" />保持登录状态</li>
     <li class="validation" id="errorMsg"><c:if test="${null ne errorCode and '' ne errorCode }"><s:message code="${errorCode}"/></c:if></li>
  </ul>
</form>
</div></div>
</div>

<div class="foot">
 <div class="fontContent">

版权所有 2013 北京思博途信息技术有限公司 <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备09014037号</a><br/>
<span class="fontCopy">Copyright &copy; 2013 SuperTool Incorporated. All rights reserved.</span><a href="mailto:jiajinliang@miaozhen.com">Email:plannexService@miaozhen.com 
</a></div>

</div>
</body>
<script type="text/javascript">
  $(function() {
      $('input#user').watermark('用户名');
      $('input#password').watermark('密码');
  });
</script>
</html>

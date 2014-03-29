/**
 * 检验用户名是否合法
 * */
function isValidUsername(username){//字母或下划线开头，6-30个字符,允许的字符有a-zA-Z0-9._@
	var pattern=/^([a-zA-Z]|[_]){1}([a-zA-Z0-9]|[._@]){5,29}$/;
	if(!pattern.exec(username)){
		return false;
	}
	return true;
}

/**
 * 检验email是否合法
 */
function isValidEmail(email){
	var pattern=/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
	if(!pattern.exec(email)){
		return false;
	}
	return true;
}

/**
 * 检验密码是否合法
 * */
function isValidPassword(password){
	var pattern=/^([a-zA-Z0-9]|[._@!#$%^&*]){6,16}$/;
	if(!pattern.exec(password)){
		return false;
	}
	return true;
}

/**
 * 检验电话号码是否合法
 * */
function isValidTelephone(tel){
	var pattern=/^([\d-+]*)$/;
	if(!pattern.exec(tel)){
		return false;
	}
	return true;
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}

/**
 * 检验是否输入
 */
function emptyCheck(id){
	var v = trim($("#"+id).val());
	if(null == v || "" == v){
		return true;
	}
	return false;
}

/**
 * 检查时间字符串是否合法
 * */
function checkTime(length,timeStr,errorMsgId,errorMsg){
	var times = timeStr.split(",");
	if(times.length != length){
		$('#'+errorMsgId).html(errorMsg);
		return false;
	}
	for(var i=0;i < times.length ;i++){
		if(times[i] == ""){
			$('#'+errorMsgId).html(errorMsg);
			return false;
		}
	}
	
	return true;
}
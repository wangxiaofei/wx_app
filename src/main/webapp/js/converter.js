/**
 * 取百分数 保留n位 四舍五入
 * @param n
 * @returns {String}
 */
Number.prototype.toPercent = function(n) {
	n = n || 2;
	return (Math.round(this * Math.pow(10, n + 2)) / Math.pow(10, n))
			.toFixed(n)
			+ '%';
};

/**
 * date format
 * @param format
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
		"S" : this.getMilliseconds()
	//millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

/**
 * role converter
 * 
 * @param role
 * @returns {String}
 */
function roleConverter(role) {
	if (role == 'admin') {
		return "管理员";
	} else if (role == 'normal') {
		return "普通用户";
	} else {
		return "未知角色" + role;
	}
}

/**
 * task type converter
 * 
 * @param taskType
 * @returns
 */
function taskTypeConverter(taskType) {
	var r;
	if (taskType == 1) {
		r = "单人群分析";
	} else if (taskType == 2) {
		r = "交叉分析";
	} else if (taskType == 3) {
		r = "人群对比";
	} else {
		r = "未知类型" + taskType;
	}
	return r;
}

/**
 * task status converter
 * 
 * @param status
 * @returns
 */
function taskStatusConverter(status) {
	var r;
	if (status == 1) {
		r = "未验证";
	} else if (status == 2) {
		r = "验证中";
	} else if (status == 3) {
		r = "未开始";
	} else if (status == 4) {
		r = "运算中";
	} else if (status == 5) {
		r = "已完成";
	} else if (status == 6) {
		r = "已发布";
	} else if (status == 8) {
		r = "运算失败";
	} else if (status == 7){
		r = "验证失败";
	} else {
		r = "未知状态" + status;
	}
	return r;
}

/**
 * 用户总任务数 converter
 * 
 * @param role
 * @param taskCount
 * @returns
 */
function taskCountConverter(role, taskCount) {
	if (role == 'normal' && typeof taskCount != "undefined") {
		return taskCount;
	} else {
		return "-";
	}
}

/**
 * 小数转化成百分数
 * @param rate
 * @returns
 */
function rateOfProgressConverter(rate) {
	if (typeof rate == "undefined") {
		rate = 0;
	}
	return rate.toPercent(2);
}

function timeConverter(time) {
	if (typeof time == "undefined") {
		return "";
	} else {
		return new Date(time).format('yyyy-MM-dd hh:mm');
	}
	
}

/**
 * 状态 非完成 及 发布的 无超链接
 * @param taskId
 * @param taskName
 * @param taskStatusId
 * @param taskTypeId
 * @returns
 */
function taskNameConverter(taskId, taskName, taskStatusId, taskTypeId) {
	if (taskStatusId >= 5) {
		return "<a href='/custom/viewEntrance/"+taskId+"'>"+taskName+"</a>";
	} 
	return taskName;
} 

/**
 * 字符 千分数 转换
 * @param s
 * @returns {String}
 */
function milliFormat(data) {
	if (typeof data == "undefined" || null == data) {
		return "";
	}
	return (data + '').replace(/(\d{1,3})(?=(\d{3})+$)/g, "$1,");
}

/**
 * 百分比转换
 * @param percentage
 * @returns
 */
function progressConverter(percentage) {
	if (typeof percentage != 'undefined' && '' != percentage) {
		var tm = parseFloat(percentage);
		return rateOfProgressConverter(tm);
	}
	return '';
}

function thousandCharacter(val){
    return (val+'').replace(/(\d{1,3})(?=(\d{3})+$)/g,"$1,");
}


/**
 * 保留
 * @param data
 * @returns {String}
 */
function thousand_bak(data) {
	if (typeof data == "undefined") {
		return "";
	}
    
	var s = String(data);
	var j = 0;
	var str = s.split('.');
	var value = '';
	for (var i = str[0].length - 1; i >= 0; i--) {
	    if (j >= 3) {
	        value = str[0][i] + ',' + value;
	        j = 0;
	    }
	    else {
	        value = str[0][i] + value;
	    }
	    j++;

	}
	if (str[1] != undefined) {
	    value = value + '.';
	    j = 0;
	    for (var i = 0; i < str[1].length; i++) {
	        if (j >= 3) {
	            value =value+','+ str[1][i] ;
	            j = 0;
	        }
	        else {
	            value = value + str[1][i];
	        }
	        j++;
	    }
	}
	return value;
}

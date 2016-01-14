/**
 * 官方列表
 * @param url
 * @param ul id
 * @param prefix 对方/普通任务
 */
function officalList(id, id_c) {
	$("#" + id).children().remove();
	$("#" + id_c).children().remove();
	officalListDemography("/task/officialNormalList", id);
	officalListDemographyCompare("/task/officialComparedList", id_c);
}
function officalListDemography(url, id) {
	$.getJSON(url,{timestamp: new Date()}, function(data) {
		$.each(data, function(i, item) {
//			var liid = prefix + "_li_" + i;
			var liid = "l" + item.taskId;
			var aid = "offical" + "_a_" + i;
			var content = item.label;
			var inputNum = content.replace(/[^\x00-\xff]/g, "**").length;
			if (inputNum > 20) {
				content  = substr(item.label, 20) + "...";
			}
			$("<li />").attr("id", liid).appendTo("#" + id);
			var url = "/custom/viewEntrance/" + item.taskId;
			$("<a>" + content + "</a>").attr("href", url).attr("title", item.label).attr("id", aid).appendTo("#" + liid);
		});
	});
}
function officalListDemographyCompare(url, id) {
	$.getJSON(url,{timestamp: new Date()}, function(data) {
		$.each(data, function(i, item) {
//			var liid = prefix + "_li_" + i;
			var liid = "l" + item.taskId;
			var aid = "officialCompared" + "_a_" + i;
			var content = item.label;
			var inputNum = content.replace(/[^\x00-\xff]/g, "**").length;
			if (inputNum > 20) {
				content  = substr(item.label, 20) + "...";
			}
			$("<li />").attr("id", liid).appendTo("#" + id);
			var url = "/custom/viewEntrance/" + item.taskId;
			$("<a>" + content + "</a>").attr("href", url).attr("title", item.label).attr("id", aid).appendTo("#" + liid);
		});
	});
}

/**
 * 管理员角色登录  获取普通用户列表
 */
function allUserList(id, id_c) {
	// clear
	clearCustomTasks(id);
	$("#" + id_c).children().remove();
	var url = "/account/allUserList";
	$.getJSON(url, {timestamp: new Date()}, function(data) {
		$.each(data, function(i, item) {
			
			$("<option>").attr("value", item.userId).text(item.userName).appendTo($("#liseid"));
		});
	});
}
/**
 * 自定义对比任务
 * @param url
 * @param id
 * @param prefix 对比/普通任务
 */
function customList(role, userId, id, id_c) {
	// TODO
	if ('admin' == role){
		// 全部普通用户
		fillData("/task/customList",{timestamp: new Date()}, id);
		fillData("/task/customComparedList",{timestamp: new Date()}, id_c);
	} else {
		// 单用户自定义
		taskListByUserId(userId, id, id_c);
	}
	
}
function substr(str, len) {
	if (!str || !len) {
		return '';
	}
	// 预期计数：中文2字节，英文1字节
	var a = 0;
	// 循环计数
	var i = 0;
	// 临时字串
	var temp = '';
	for (i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255) {
			// 按照预期计数增加2
			a += 2;
		} else {
			a++;
		}
		// 如果增加计数后长度大于限定长度，就直接返回临时字符串
		if (a > len) {
			return temp;
		}
		// 将当前内容加到临时字符串
		temp += str.charAt(i);
	}
	// 如果全部是单字节字符，就直接返回源字符串
	return str;
}  
/**
 * 全部 普通用户 任务
 */
function fillData(url, data, id) {
	$.getJSON(url, data, function(data) {
		$.each(data, function(i, item) {
			//var liid = prefix + "_li_" + i;
			var liid = "l" + item.taskId;
			var aid = id + "_a_" + i;
			//var spanid = prefix + "_span_" + i; 
			var content = item.label;
			var inputNum = content.replace(/[^\x00-\xff]/g, "**").length;
			if (inputNum > 20) {
				content  = substr(item.label, 20) + "...";
			}
			$("<li onmouseover=\"mouseover("+item.taskId+", '"+liid+"')\" onmouseout=\"mouseout("+item.taskId+", '"+liid+"')\" />").attr("id", liid).appendTo("#" + id);
			$("<a onclick=\"customClick("+item.taskId+");\"  >" + content + "</a>").attr("href", "#").attr("title", item.label).attr("id", aid).appendTo("#" + liid);
			if (id == 'customNormal' ) {
				// 自定义 普通任务 TODO
				$("#" + liid).append("<span class=\"menuSpan\" style=\"display:none\" onclick=\"viewTask("+item.taskId+");\"><a href=\"#\" ><img src=\"/theme/pic/view.gif\" width=\"16\" height=\"16\" title=\"查看任务信息\"/></a></span>");
				$("#" + liid).append("<span class=\"menuSpan2\" style=\"display:none\" onclick=\"delTask("+item.taskId+",'"+item.taskName+"', "+ item.taskStatusId +");\"><a href=\"#\" ><img src=\"/theme/pic/delete.gif\" width=\"16\" height=\"16\" title=\"删除任务\"/></a></span>");
				if (item.taskStatusId == 1) {
					$("#" + liid).append("<span onclick=\"verify("+item.taskId+","+item.createUserId+",'"+item.taskName+"');\"><img src=\"/theme/pic/edit.gif\" width=\"16\" height=\"16\" title=\"开始验证\"/></span>");
				} else if (item.taskStatusId == 2) {
					$("#" + liid).append("<span onclick=\"customClick("+item.taskId+");\" ><img src=\"/theme/pic/time.gif\" width=\"16\" height=\"16\" title=\"小数据运算中\"/></span>");
				} else if (item.taskStatusId == 3) {
					// 开始运算
					$("#" + liid).append("<span  onclick=\"calculate("+item.taskId+","+item.createUserId+",'"+item.taskName+"');\"><img src=\"/theme/pic/data.gif\" width=\"16\" height=\"16\" title=\"小数据运算完成\"/></span>");
				} else if (item.taskStatusId == 4) {
					$("#" + liid).append("<span onclick=\"customClick("+item.taskId+");\" ><img src=\"/theme/pic/time.gif\" width=\"16\" height=\"16\" title=\"报表运算中\"/></span>");
				} else if (item.taskStatusId == 7){
					// 验证失败
					$("#" + liid).append("<span onclick=\"verify("+item.taskId+","+item.createUserId+",'"+item.taskName+"');\"><img src=\"/theme/pic/edit.gif\" width=\"16\" height=\"16\" title=\"重新验证\"/></span>");
				} else if (item.taskStatusId == 8){
						// 运算失败
					$("#" + liid).append("<span  onclick=\"calculate("+item.taskId+","+item.createUserId+",'"+item.taskName+"');\"><img src=\"/theme/pic/data.gif\" width=\"16\" height=\"16\" title=\"重新运算\"/></span>");
				} else {
					// 隐藏 运算完成 图标
					//$("#" + liid).append("<span ><img src=\"/theme/pic/data.gif\" width=\"16\" height=\"16\" title=\"运算完成\"/></span>");
				}
			}
		});
	});
}

/**
 * 自定义列表 清除 （第一个li不清出）
 * @param id
 */
function clearCustomTasks(id) {
	$.each($("#" + id).children(), function(i, item) {
		if (i != 0) {
			$(item).remove();	
		}
	});
}

/**
 * 单用户 任务列表 
 * @param userId
 */
function taskListByUserId(userId, id, id_c) {
	// 构造自定义任务 列表 
	clearCustomTasks(id);
	$("#" + id_c).children().remove();
	var data = {createUserId: userId, timestamp: new Date()};
	if (typeof userId == 'undefined' || '' == userId) {
		data = {timestamp: new Date()};
	}
	var url = "/task/customListByUserId";
	var url_c = "/task/customComparedListByUserId";
	fillData(url, data, id);
	fillData(url_c, data, id_c);
}

/**
 * 小数据运算结果
 */
function calculate(taskId, userId) {
	// Demo 账户不可以运算
	if (-2 == userId) {
		jAlert("Demo用户不可对任务进行操作", "系统消息");
		return false;
	}
	var url = "/custom/smallData/" + taskId;
	jump(url);
}

/**
 * 验证
 * reVerify 是否是 重新验证
 */
function verify(taskId, userId, taskName, reVerify) {
	// Demo 账户不可以开始任务
	if (-2 == userId) {
		jAlert("Demo用户不可对任务进行操作", "系统消息");
		return false;
	}
	
    var url = "/task/operationChecker";
	var data = {userId: userId};
	if (typeof reVerify == 'undefined' && !ajaxExc(url, data)) {
		jAlert("该用户的任务次数已全部用完，不可再开始任务", "系统消息");
		return false;
	}
	jConfirm("确定开始验证该任务？验证开始后，将先进行小数据的运算。", "系统消息", function(flag){
        if(flag){
        	url = "/task/statusChange";
        	data = {taskId: taskId, status: 2, taskName: taskName};
        	ajaxExc(url, data);
        	jump("/task/index");
        }
     });
}

/**
 * 运算
 * @param taskId
 * @param userId
 * @param taskName
 * @param reVerify 是否重新运算
 * @returns {Boolean}
 */
function cal(taskId, userId, taskName, reVerify) {
	var url = "/task/operationChecker";
    var data = {userId: userId};
    if (typeof reVerify == 'undefined' && !ajaxExc(url, data)) {
        jAlert("该用户的任务次数已全部用完，不可再开始任务", "系统消息");
        return false;
    }
    jConfirm("确定继续运算该任务？继续运算开始后，任务将不可再修改，并将占用用户的一次任务次数。", "系统消息", function(flag){
        if(flag){
            url = "/task/statusChange";
            data = {taskId: taskId, status: 4, taskName: taskName};
            ajaxExc(url, data);
        	jump("/task/index");
        }
     });
}

function delTask(taskId, taskName, taskStatusId) {
	// 删除加入 ‘运行中’ 状态不可以 删除
	if (taskStatusId == 4) {
		jAlert("该任务正在运行，不能删除", "系统消息");
		return false;
	}
	jConfirm("	确定要删除该任务?删除后,任务将不存在.", "系统消息", function(flag){
        if(flag){
            var url = "/task/delete";
            var data = {taskId: taskId, taskName: taskName};
            ajaxExc(url, data);
        	jump("/task/index");
        }
     });
	return false;
}

function viewTask(taskId) {
	var url = "/task/viewTask?taskId=" + taskId;
	jump(url);
	return false;
}

function customClick(taskId){
	var url = "/custom/viewEntrance/" + taskId;
	jump(url);
	return false;
}

function mouseover(taskId, liid) {
	$.each($("#" + liid).children(".menuSpan2"), function(index, item) {
		$(item).show();
	});
	$.each($("#" + liid).children(".menuSpan"), function(index, item) {
		$(item).show();
	});
	
	return false;
}

function mouseout(taskId, liid) {
	$.each($("#" + liid).children(".menuSpan2"), function(index, item) {
		$(item).hide();
	});
	$.each($("#" + liid).children(".menuSpan"), function(index, item) {
		$(item).hide();
	});
	return false;
}

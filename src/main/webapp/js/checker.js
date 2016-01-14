/**
 * blank checker 
 * undefined or ' ' true
 * @param str
 * @returns {Boolean}
 */
function isBlank(str) {
    if (undefined == str || "" == $.trim(str)) {
         return true;
    } else {
        return false;
    }
}

/**
 * not blank checker
 * @param str
 * @returns
 */
function isNotBlank(str) {
    return !isBlank(str);
}

/**
 * username checker
 * 字母数字 下划线 20个字符
 * @param userName
 */
function execUserName(userName) {
    var patrn = /^\w{1,20}$/;
    if (patrn.exec(userName)) {
        return true;
    } else {
        return false;
    }
}

/**
 * trim fill back
 * @param obj
 */
function fillWithTrimVal(obj) {
    $(obj).val($.trim($(obj).val()));
}

/**
 * 正整数 int(11)
 * @param tc
 * @returns {Boolean}
 */
function execTaskCount(tc) {
    var patrn=/^[1-9]{1}[0-9]{0,10}$/; 
    if (patrn.exec(tc)) {
        return true;
    } else {
        return false;
    }
}

/**
 * 不含有 中文 必须要含有 @ . 
 * @param tc
 * @returns {Boolean}
 */
function execEmail(tc) {
    var patrn=/@+/; 
    if (patrn.exec(tc) == null) {
        return false;
    }
    patrn=/\.+/; 
    if (patrn.exec(tc) == null) {
        return false;
    }
    patrn=/[\u4e00-\u9fa5]+/; 
    if (patrn.exec(tc) != null) {
        return false;
    }
    return true;
}

/**
 * ajax excute
 */
function ajaxExc(url, data) {
    var resut = false;
    $.ajax({
        url : url,
        data : data,
        dataType:"html",
        type:"POST",
        async: false,
        cache: false,
        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
        success: function(data) {
            if ('true' == data) {
                resut = true;
            }
        },
        error: function () {
            jAlert('操作失败', '系统消息');
        }
    });
    
    return resut;
}

/**
 * pwd checker
 * 字母数字 下划线 6-16个字符
 * @param userName
 */
function execPassword(pwd) {
    var patrn = /^\w{6,16}$/;
    if (patrn.exec(pwd)) {
        return true;
    } else {
        return false;
    }
}
/**
 * 控制 输入域 字符 
 */
function maxLength(id) {
    $('textarea[maxlength]').unbind('propertychange').bind('propertychange', limitLength).unbind('input').bind('input', limitLength);
    function  limitLength(e){
        //get the limit from maxlength attribute
        var limit = Number($(this).attr('maxlength'));
        //get the current text inside the textarea
        var text = $(this).val();
        //count the number of characters in the text
        var chars = text.length;
        //check if there are more characters then allowed
        if(chars > limit){
            //and if there are use substr to get the text before the limit
            var new_text = text.substr(0, limit);
            //and change the current text with the new text
            $(this).val(new_text);
        }
    };
    $("#" + id).keydown(function(e){
    	var limit = Number($(this).attr('maxlength'));
        if(this.value.length >= limit){
            var code = e.keyCode;
            if(code != 8 && code != 37 && code != 39 && code != 38 && code != 40 && code != 46){
                return false;
            }
        };
    });
}
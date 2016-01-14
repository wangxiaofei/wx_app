/**
 * task正确性验证,保存或者修改按钮是否可用
 */
var timeV = false;// 时间验证，主要验证是否为空
var labelV = false;// 标签验证，主要验证是否为空
var keywordV = false;// 关键词验证，不能为空
var exactURLV = false;// 精确url验证，不能为空,必须有http://验证
var fuzzyURLV = false;// 模糊url验证,不能为空，必须有*号
var maxUrlV = true;// 限定条件，不能为空，正整数(有默认值故初始值为true)
var urlSetV = false;// 不能为空，非模糊url进行数据库验证
var demoV = true;//人群验证，不能为空，这个只有在交叉分析时存在
var anysisPathV = false;//人群行为路径分析
/**
 * 手工确定精确url这个验证
 * @param flag
 */
function exactUrlValid(flag){
	exactURLV = flag;
	canSave();
}
/**
 * 返回精确url验证的当前结果
 * @returns {Boolean}
 */
function isExactUrl(){
	return exactURLV;
}
/**
 * 保存按钮这个对象是否可用
 * 
 * @param id
 */
function canSave() {
	var keyV = keywordV;
	var exactV = exactURLV;
	var fuzzyV = fuzzyURLV;
	var maxV = maxUrlV;
	var setV = urlSetV;
	var deV = demoV ;
	var pathV = anysisPathV;
	var keywordChecked = $("input[name='canKeyword']").attr("checked");
	if(keywordChecked == true || keywordChecked=="checked"){
	}else{
		keyV = true;
	}
	var exactUrlChecked = $("input[name='canExactUrl']").attr("checked");
	if(exactUrlChecked == true || exactUrlChecked =="checked"){
	}else{
		exactV = true;
		maxV = true;
	}
	var fuzzyUrlChecked = $("input[name='canFuzzyUrl']").attr("checked");
	 if(fuzzyUrlChecked == true || fuzzyUrlChecked=="checked"){
     }else{
    	 fuzzyV = true;
     }
	 var urlSetChecked = $("input[name='canUrlSet']").attr("checked");
	 if(urlSetChecked == true || urlSetChecked=="checked"){
     }else{
    	 setV = true;
     }
	 setV = setV || anysisPathV;
	if (timeV && labelV && keyV && exactV && fuzzyV && maxV
			&& setV&& deV) {
		$("#saveId").removeAttr("disabled");
	} else {
		$("#saveId").attr("disabled", "disabled");
	}
}
/**
 * 验证时间不能为空，参数为验证结果对象
 * 
 * @param spanId
 */
function validTime(spanId) {
	var vDate = $("#" + spanId);
	if (isEmpty("#from") || isEmpty("#to")) {
		timeV = false;
		vDate.html("请选择日期");
	} else {
		timeV = true;
		vDate.html("");
	}
	canSave();
}
/**
 * 任务标签验证，参数为验证结果对象
 * 
 * @param spanId
 */
function validLabel(spanId) {
	var vLabel = $("#" + spanId);
	if (isEmpty("#label")) {
		vLabel.html("标签不能为空");
		labelV = false;
	} else if (dumpTaskName($("#label").val())) {
		vLabel.html("标签重复");
		labelV = false;
	} else {
		labelV = true;
		vLabel.html("");
	}
	canSave();
}
/**
 * 验证关键字是否为空，
 * 
 * @param obj
 */
function validKeyword() {
	var keywordChecked = $("input[name='canKeyword']").attr("checked");
	if (keywordChecked != true && keywordChecked != "checked") {
		return;// 只有选中关键字后才进行验证
	}
	var temp = true;
	$("input[name='keyword']").each(function(i){
		if(this.value==null || this.value.trim()==""){
			temp = temp && false;
			$(this).siblings("span").html("搜索关键词不能为空");
		}else{
			temp = temp && true;
			$(this).siblings("span").html("");
		}
	});
	keywordV = temp;
	canSave();
}
/**
 * 验证精确url，是否为空和是否是url格式
 * @param obj
 */
function validExactUrl(obj) {
	var exactUrlChecked = $("input[name='canExactUrl']").attr("checked");
	if (exactUrlChecked != true && exactUrlChecked != "checked") {
		return;// 只有选中精确url后才验证
	}
	if (isEmpty(obj)) {
		exactURLV =  false;
		$(obj).siblings("span").html("URL不能为空");
	} else {
		exactURLV = true;
		$(obj).siblings("span").html("");
		var value = $(obj).val();
		var valueAry = value.split("\n");
		if(valueAry.length>500){
			exactURLV =  false;
			$(obj).siblings("span").html("URL超过500条,请修改");
		}else{
			for(var i=0;i<valueAry.length;i++){
				var temp = valueAry[i];
				if(temp!=null && temp.trim() !="" && temp.indexOf("http://")== -1){
					exactURLV =  false;
					$(obj).siblings("span").html("请输入正确URL,格式形如:http://aaa.bb");
					break;
				}
			}
		}
	}
	canSave();
}
/**
 * 精确url限定条件验证，默认值为1，必须是正整数
 * 
 * @param spanId
 */
function validMaxUrl(spanId) {
	var exactUrlChecked = $("input[name='canExactUrl']").attr("checked");
	if (exactUrlChecked != true && exactUrlChecked != "checked") {
		return;// 只有选中精确url后才验证
	}
	var maxExactUrl = $("#maxExactUrl").val();
	var maxExactUrlLab = $("#" + spanId);
	var par = /^[1-9]\d*$/;
	if (!par.test(maxExactUrl)) {
		maxUrlV = false;
		maxExactUrlLab.html("请输入正整数");
	} else {
		maxUrlV = true;
		maxExactUrlLab.html("");
	}
	canSave();
}
/**
 * 模糊url验证，为空，或者是否有 * 号
 */
function validFuzzyUrl() {
	var fuzzyUrlChecked = $("input[name='canFuzzyUrl']").attr("checked");
	if(fuzzyUrlChecked != true&& fuzzyUrlChecked !="checked"){
		return;// 只有选中模糊url才进行验证
	}
	var temp = true;
	 $("input[name='fuzzyUrl']").each(function(i){
		if(this.value==null || this.value.trim()==""){
			temp = temp && false;
			$(this).siblings("span").html("URL不能为空");
		}else{
			temp = temp && true;
			$(this).siblings("span").html("");
			if(this.value.indexOf("*")== -1){//必须有*号
				temp = temp && false;
				$(this).siblings("span").html("请输入含有通配符\"*\"的字符串");
			}
		}
	});
	 fuzzyURLV = temp;
	canSave();
}
//验证人群访问路径分析是否选择，保证访问url和人群访问路径分析有一个选择就可以通过验证
function validPathAnlysis(){
    var pathAnlysisChecked = $("#pathAnysis").attr("checked");
    if(pathAnlysisChecked != true&& pathAnlysisChecked !="checked"){
    	anysisPathV = false;
    }else{
    	anysisPathV = true;
    }
    canSave();
}
/**
 * url分析设置验证，为空，或者有*号做为模糊url处理，或者精确url（要经过数据库url匹配）
 */
function validUrlSet() {
	 var urlSetChecked = $("input[name='canUrlSet']").attr("checked");
     if(urlSetChecked != true&& urlSetChecked !="checked"){
		return;// 只有选中模糊url才进行验证
	}
	var temp = true;
	 $("input[name='urlSet']").each(function(i){
		if(this.value==null || this.value.trim()==""){
			temp = temp && false;
			$(this).siblings("span").html("URL不能为空");
		}else{
			temp = temp && true;
			$(this).siblings("span").html("");
			if(this.value.indexOf("*")== -1){//判断是否有通配符，若有则当模糊url处理，否则进行数据库验证
				var validDB = validUrl(this.value);
				if(!validDB){
					temp = temp && false;
					$(this).siblings("span").html("URL不在数据库中,请修改");
				}
			}
		}
	});
	 urlSetV = temp;
	canSave();
}
/**
 * 验证一个对象是否是空值
 * 
 * @param uuid
 * @returns {Boolean}
 */
function isEmpty(uuid) {
	var id = $(uuid).val();
	if (id == null || id.trim() == "") {
		return true;
	} else {
		return false;
	}
}
/**
 * 添加一个tr，主要是使用与关键词、模糊url、url分析设置中的添加，删除
 * 
 * @param obj
 * @param targetName
 * @param maxNum
 */
function addTr(obj, targetName, maxNum) {
	var td = $(obj).parent("td");
	var tr = td.parent("tr");
	var num = $("input[name='" + targetName + "']").length;
	if (num >= maxNum) {
		jAlert("添加数量超过" + maxNum, "系统消息");
		return;
	}
	var newTr = tr.clone(true);
	newTr.find("input[name='" + targetName + "']").val("");
	var tds = newTr.children().find("span");
	tds.empty().append("");
	newTr.children().find("input[name$=cancel]").remove();
	newTr.children().first().empty().append("");
	var cancel = $('<input type="button"  name="' + targetName
			+ 'cancel" class="btc"></input>');
	cancel.bind('click', function() {
		newTr.remove();
		//验证
		validKeyword();
		validFuzzyUrl();
		validUrlSet();
	});
	newTr.children().last().find("input[type=button]").after(cancel);
	tr.after(newTr);
	//验证
	validKeyword();
	validFuzzyUrl();
	validUrlSet();
	return true;
}
/**
 * 删除一行
 * @param obj
 */
function deleteTr(obj){
	var td  = $(obj).parent("td");
    var tr = td.parent("tr");
    tr.remove();
}
/**
 * 判断任务名称是否重复
 * 
 * @param name
 * @returns {Boolean}
 */
function dumpTaskName(name) {
	var flag = false;
	var option = {
		taskName : name
	};
	$.ajax({
		type : "POST",
		async : false,
		url : "/task/dumpTaskName",
		data : option,
		success : function(data) {
			if (data == "dump") {
				flag = true;
			}
		}
	});
	return flag;
}
/**
 * 验证url是否在数据库中
 * 
 * @param url
 * @returns {Boolean}
 */
function validUrl(url) {
	var flag = false;
	var option = {
		setUrl : url
	};
	$.ajax({
		type : "POST",
		async : false,
		url : "/task/validUrlByDb",
		data : option,
		success : function(data) {
			if (data == "1") {
				flag = true;
			}
		}
	});
	return flag;
}
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
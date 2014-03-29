var MAIL_ERROR_EMPTY = "邮件及主题不能为空！";
var MAIL_ERROR_MAIL_FORMAT = "请使用秒针邮箱，最多可发送5个邮箱，谢谢！";
var MAIL_ERROR_THEME_TOO_LONG = "主题太长，请控制在80字符以内!";
var MAIL_ERROR_COMMETS_TOO_LONG = "注释太长，请控制在100字符以内";
var REGION_ERROR_EMPTY = "地域为空，请选择地域！";
var EXCEL_MAX_ROW = 1048500;
var IS_MAX_EXCEL = 209700;
var TOTAL_REGION_NUMBER = 156;

var DOWNLOAD_METHOD_SYN =1;
var DOWNLOAD_METHOD_ASYN = 2;

//ztree setting 
var setting = {
		async:{
			enable:true
		},
		view:{
			showLine:false,
			showIcon:false
		},
		check:{
			enable:true,
			chkboxType:{"Y":"s","N":"s"}
		}
};

//RegionDiv
function RegionDiv(url){
	this.url = url;
}

RegionDiv.prototype={
		constructor:RegionDiv,
		initCorners:function(){
			var settings = {
					tl: { radius: 8 },
					tr: { radius: 8 },
					bl: { radius: 8 },
					br: { radius: 8 },
					antiAlias: true,
					autoPad: true,
					validTags: ["div"]
			};

			var regionDiv = new curvyCorners(settings, "regionDiv");
			regionDiv.applyCornersToAll();
		},
		initRegionDiv: function(){
			
			this.initCorners();
			
			setting.async.url = this.url;
			
			$.fn.zTree.init($("#tree"), setting);
			
			// 地域定向  反选
			$("#oppositeAll").click(function(){
				var zTreeObj = $.fn.zTree.getZTreeObj("tree");
				
				var treeNode = zTreeObj.getCheckedNodes(true);
				var treeNodeUnChecked = zTreeObj.getCheckedNodes(false);
				if(treeNode.length > 0){
					for(var i=0;i<treeNode.length;i++) {
						
						treeNode[i].checked = false;
					}
				}
				if(treeNodeUnChecked.length > 0){
					for(var i=0;i<treeNodeUnChecked.length;i++) {
						
						treeNodeUnChecked[i].checked = true;
					}
				}
				zTreeObj.refresh();
			});
			//地域定向  全选
			$("#selectAllRegions").click(function(){
				var zTreeObj = $.fn.zTree.getZTreeObj("tree");
				zTreeObj.checkAllNodes(true);
			});
			
			$("#disselectAll").click(function(){
				var zTreeObj = $.fn.zTree.getZTreeObj("tree");
				zTreeObj.checkAllNodes(false);
			});
			
			$("#directDown").click(function(){
				$("#inputMailTo").attr("disabled","disabled");
				$("#inputMailTheme").attr("disabled","disabled");
				$("#inputMailCommets").attr("disabled","disabled");
			});
			
			$("#asynDown").click(function(){
				$("#inputMailTo").removeAttr("disabled","disabled");
				$("#inputMailTheme").removeAttr("disabled","disabled");
				$("#inputMailCommets").removeAttr("disabled","disabled");
			});
			
			
		},
		getCheckedNodes:function(){
			var selectedRgs = "";
			var zTreeObj = $.fn.zTree.getZTreeObj("tree");
			var treeNodes = zTreeObj.getCheckedNodes(true);
			if(treeNodes.length > 0){
				for(var i=0;i<treeNodes.length;i++) {
					selectedRgs += treeNodes[i]["id"] + ";" ;
				}
			}
			
			return selectedRgs;
		},
		getCheckedRegionNames:function(){
			var names = "";
			var zTreeObj = $.fn.zTree.getZTreeObj("tree");
			var treeNodes = zTreeObj.getCheckedNodes(true);
			if(treeNodes.length > 0){
				for(var i=0;i<treeNodes.length;i++) {
					names += treeNodes[i]["name"] + ";" ;
				}
			}
			return names;
		},
		getDownloadMethod:function(){
			var downloadMethod = $("#directDown").attr("checked")?DOWNLOAD_METHOD_SYN :DOWNLOAD_METHOD_ASYN;
			return downloadMethod;
		},
		getMailTo:function(){
			return $("#inputMailTo").val();
		},
		getMailTheme:function(){
			return $("#inputMailTheme").val();
		},
		getMailCommets:function(){
			return $("#inputMailCommets").val();
		},
		showMessage:function(msg){
			$("#errorMessage").text(msg);
		},
		checkValidate:function(){
			var mailTo = $("#inputMailTo").val();
			var mailTheme = $("#inputMailTheme").val();
			var mailCommets = $("#inputMailCommets").val();
			var reg = /^(([a-zA-Z0-9_-])+@miaozhen.com;){1,5}$/;
			
			if(this.getCheckedNodes() == ""){
				//this.showMessage(REGION_ERROR_EMPTY);
				$("#regionErrorMessage").text(REGION_ERROR_EMPTY); 
				return false;
			}
			
			if(this.getDownloadMethod() == DOWNLOAD_METHOD_ASYN)
			{
				if(mailTo == undefined || mailTheme == undefined){
					this.showMessage(MAIL_ERROR_EMPTY);
					return false;
				}
				
				if(reg.test(mailTo+";") == false){
					this.showMessage(MAIL_ERROR_MAIL_FORMAT);
					return false;
				}
				
				if(mailTheme.length > 80){
					this.showMessage(MAIL_ERROR_THEME_TOO_LONG);
					return false;
				}
				
				if(mailCommets.length > 100){
					this.showMessage(MAIL_ERROR_COMMETS_TOO_LONG);
					return false;
				}
				
			}
			
			this.showMessage("");
			return true;
		},
		lock:function(){
			var d= document.getElementById("bgDiv");
			if(d){
				d.style.width=window.screen.availWidth+"px";
				d.style.height=window.screen.availHeight+"px";
//				d.style.height=document.body.clientHeight+"px";
				d.style.display="block";
			}
		},
		release:function(){
			var d= document.getElementById("bgDiv");
			 if(d){
				d.style.display='none';
			 }
		},
		show:function(){
			if($("#directDown").attr("checked")){
				$("#inputMailTo").attr("disabled","disabled");
				$("#inputMailTheme").attr("disabled","disabled");
				$("#inputMailCommets").attr("disabled","disabled");
			}
			this.clearMessage();
			
			document.getElementById("regionDiv").style.display = "block";
			this.lock();
		},
		hide:function(){
			this.release();
			document.getElementById("regionDiv").style.display = "none";
		},
		clearMessage:function(){
			this.showMessage("");
		}
};
	
function maxRegionNumber(num1,num2){
	var mul = accMul(num1,num2);
	var div = parseInt(accDiv(EXCEL_MAX_ROW,mul));
	if(div < TOTAL_REGION_NUMBER){
		//$("#errorMessage").html("最多可选择的地域个数为：" + div); 
		$("#regionErrorMessage").html("最多可选择的地域个数为：" + div); 
	}
}

function checkRegionNum(num1,num2, regionNum){
	var mul = accMul(num1,num2);
	var div = parseInt(accDiv(EXCEL_MAX_ROW,mul));
	if(regionNum > div){
		return true;
	}else{
		return false;
	}
}

function asynDownload(num1,num2,num2){
	var mul1 = accMul(num1,num2);
	var mul = accMul(mul1,num2);
	var reFlag = true;
	if(mul > IS_MAX_EXCEL){
		if(!document.getElementById("asynDown").checked){
			var flag = window.confirm("报表过大, 下载时间长, 是否将同步下载替换为异步下载。");
			if(flag){
				document.getElementById("asynDown").checked = "checked";
				if(null != document.getElementById("downloadMethod")){
					document.getElementById("downloadMethod").value = 2;
				}
				reFlag = false;
			}
		}
	}
	return reFlag;
}
Number.prototype.mul = function (arg){
    return accMul(arg, this);
};
Number.prototype.div = function (arg){
    return accDiv(this, arg);
};
Number.prototype.add = function (arg){
    return accAdd(arg,this);
};
Number.prototype.sub = function (arg){
    return accSub(this,arg);
};
function accMul(arg1,arg2){
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{
    	m+=s1.split(".")[1].length;
    }catch(e){}
    try{
    	m+=s2.split(".")[1].length;
    }catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}
function accDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{
    	t1=arg1.toString().split(".")[1].length;
    }catch(e){}
    try{
    	t2=arg2.toString().split(".")[1].length;
    }catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""));
        r2=Number(arg2.toString().replace(".",""));
        return (r1/r2)*pow(10,t2-t1);
    }
}
function accAdd(arg1,arg2){
    var r1,r2,m;
    try{
    	r1=arg1.toString().split(".")[1].length;
    }catch(e){
    	r1=0;
    }
    try{
    	r2=arg2.toString().split(".")[1].length;
    }catch(e){
    	r2=0;
    }
    m=Math.pow(10,Math.max(r1,r2));
    return (accMul(arg1,m)+accMul(arg2,m))/m;
}
function accSub(arg1,arg2){    
    return accAdd(arg1,-arg2);
}
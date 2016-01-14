var childNum;
var setting = {
	view : {
		selectedMulti : false,
		showIcon : false
	},
	async : {
		enable : false,
		url : "<s:url value='/region/regionNodes'/>",
		autoParam : [ "id" ],
	// otherParam:["searchText", getOtherParam(), "ids", getIds],
	// otherParam:["ids", getIds]
	},
	data : { // 必须使用data
		simpleData : {
			enable : true,
			idKey : "id", // id编号命名 默认
			pIdKey : "pid", // 父id编号命名 默认
			rootPId : null
		// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
		}
	},
	check : {
		enable : true,
		chkStyle : "checkbox",
		// chkboxType: { "Y": "p", "N": "s" },
		radioType : "all"
	},
	callback : {
		onClick : onClick,
		onCheck : onCheck,
		onAsyncSuccess : asyncSuccess,
		onNodeCreated : zTreeOnNodeCreated
	}
};

function getOtherParam() {
	var otherParam = $('#searchTextHidden').val();
	return otherParam;
}
function getIds() {
	var ids = "${param.ids}";
	return ids;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("Tree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {

}

function asyncSuccess(event, treeId, treeNode, msg) {
	var select = $("#input_region_" + childNum).val();
	//alert(select);
	if (select != null && '' != select) {
		var selects = select.split(",");
		var treeObj = $.fn.zTree.getZTreeObj("Tree");
		for ( var i = 0; i < selects.length; i++) {
			var node = treeObj.getNodeByParam("id", selects[i], null);
			// treeObj.selectNode(node, true);
			onClick(event, treeId, node);
		}
	}
}
function zTreeOnNodeCreated() {
	var select = $("#input_region_" + childNum).val();
	if (select != null && '' != select) {
		var selects = select.split(",");
		var treeObj = $.fn.zTree.getZTreeObj("Tree");
		for ( var i = 0; i < selects.length; i++) {
			var node = treeObj.getNodeByParam("id", selects[i], null);
			treeObj.checkNode(node, !node.checked, false, true);
		}
	}
}

function showMenu() {
	var cityObj = $("#sel");
	var cityOffset = $("#sel").offset();
	$("#menu").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menu").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "sel" || event.target.id == "menu" || $(
			event.target).parents("#menu").length > 0)) {
		hideMenu();
	}
}

/*$(function() {
	// alert(treeNodes);
	$.fn.zTree.init($("#Tree"), setting, treeNodes);
});*/
function cancelRegion() {
	$("#regionTree").dialog("close");
}
function submitRegion() {
	$("#input_region_"+childNum).val('');
	var zTree = $.fn.zTree.getZTreeObj("Tree");
	checkNodes = zTree.getCheckedNodes(true);
	unCheckNodes = zTree.getCheckedNodes(false);
	var v = "";
	var value = "";
	for ( var i = 0, l = checkNodes.length; i < l; i++) {
		v += checkNodes[i].name + ",";
		// 将值赋值给Id隐藏域
		value += checkNodes[i].id + ",";

		// 赋值end
	}
	if (checkNodes.length == 0) {
		$("#input_region_" + childNum).val(null);
	}
	$("#input_region_" + childNum).val(value.substring(0, value.length - 1));
	$("#regiontext_" + childNum).val(v.substring(0, v.length - 1));
	$("#regionTree").dialog("close");
}
function showRegion(i) {
	$("#regionTree").dialog("destroy");
	childNum = i;
	$("#regionTree").dialog({
		autoOpen : true,
		height : 350,
		width : 600,
		modal : false,
		title : '地域选择',
		open:function(){
			$.fn.zTree.init($("#Tree"), setting,treeNodes);
		}
	});
}
var setting = {
		view: {
			selectedMulti: false,
			showIcon: false
		},
		async: {
			enable: true,
			url: "showTree",
			autoParam:["treeid"],
			otherParam:["typeid", getOtherParam]
		},
		data:{ // 必须使用data
 			simpleData : {
				enable : true,
				idKey : "treeid", // id编号命名 默认
				pIdKey : "treepid", // 父id编号命名 默认 
				rootPId : 0 // 用于修正根节点父节点数据，即 pIdKey 指定的属性值
			}
		},
		check:{
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},
		callback: {
			onClick: onClick,
			onCheck: onCheck,
			//onAsyncSuccess: asyncSuccess
		} 
};
	
	function getOtherParam(){
		var otherParam = $('#typeid').val();
		return otherParam;
	}
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}
	
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("orgTree");
		nodes = zTree.getCheckedNodes(true);
		v = "";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			//将值赋值给orgId隐藏域
			alert(nodes[i].treeid);
			document.getElementById('batchId').value = nodes[i].treeid;
			//赋值end
		}
		//if (v.length > 0 ) v = v.substring(0, v.length-1);
		//var Obj = $("#sel");
		//Obj.attr("value", v);
	}
	
	/*function asyncSuccess(event, treeId, treeNode, msg){
		var obj = $("#sel").val();
		var treeObj = $.fn.zTree.getZTreeObj("orgTree");
		var node = treeObj.getNodeByParam("name", obj, null);
		treeObj.selectNode(node, false);
		onClick(event, treeId, node);
	}*/
	
	/* function showMenu() {
		var cityObj = $("#sel");
		var cityOffset = $("#sel").offset();
		$("#menu").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	
		$("body").bind("mousedown", onBodyDown);
	}
	
	function hideMenu() {
		$("#menu").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	
	function onBodyDown(event) {
		if (!(event.target.id == "sel" || event.target.id == "menu" || $(event.target).parents("#menu").length>0)) {
			hideMenu();
		}
	}*/

	$(document).ready(function(){
		$.fn.zTree.init($("#orgTree"), setting);
	});
	
	function searchOrg(){
		$.fn.zTree.init($("#orgTree"), setting);
	}

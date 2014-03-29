// JavaScript Document by Richbox
function resizeFrame() {
	if($("topSize").value!=""&$("leftSize").value!="") {
		try {
			parent.resetFrameSize($("topSize").value,$("leftSize").value);
			visibleObj($("sizeReseter"),false);
		} catch(e) {
			alert(e);
		}
	} else {
		alert("²»ÔÊÐíÎª¿Õ");
	}
}
function getFrameSize() {
	try {
		var o=parent.getFrameSize();
		$("topSize").value=o.h;
		$("leftSize").value=o.w;
	} catch(e) {
		alert(e);
	}
}
function swfGetFrameSize() {
	var o=parent.getFrameSize();
	return o;
}
function swfSetFrameSize(o) {
	parent.resetFrameSize(o.h,o.w);
}
function visibleObj(Obj,v) {
	if(v==null||v=="undefind") {
		if(Obj.style.display=="block") {
			Obj.style.display="none";
		} else {
			Obj.style.display="block";
		}
	} else {
		if(v==true) {
			Obj.style.display="block";
		} else {
			Obj.style.display="none";
		}
	}
}
function $(Obj) {
	var o=document.getElementById(Obj);
	if(o!=null) {
		return o;
	} else {
		return false;
	}
}
function initTree(t) {
	var tree=$(t);
	var lis=tree.getElementsByTagName("li");
	//alert(lis.length);
	for(var i=0;i<lis.length;i++) {
		lis[i].nu=i;
		lis[i].onclick=function() {
			for(var j=0;j<lis.length;j++) {
				if(j==this.nu) {
					this.className="selected";
					this.blur();
				} else {
					lis[j].className="";
				}
			}
		}
	}
}
function goURL(num) {
	var link1="Menu/face.html";
	var link2="";
	switch(num) {
		case 0:
		link1="Menu/left.html";
		link2="media/mediaList.html";
		break;
		case 1:
		link1="Menu/leftCT.html";
		link2="finance/financeList.html";
		break;
		case 2:
		link1="Menu/leftMedia.html";
		link2="business/businessList.html";
		break;
		case 3:
		link2="Pattern/affirmPattern.html";
		break;
		case 4:
		link2="Advertiser/aderList.html";
		break;
		case 5:
		link1="Menu/leftCT.html";
		link2="CT/campaignList.html";
		break;
		case 6:
		link2="Launch/campaignList.html";
		break;

		case 7:
		link2="Sorting/sortingList.html";
		break;
		default:
		link2="start.html";
		break;
	}
	try {
		parent.document.getElementById("leftFrame").src=link1;
		parent.document.getElementById("mainFrame").src=link2;
	} catch(e) {
		//alert(e);
	}
}
// JavaScript Document by Richbox
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
function initNav() {
	var h3s=$("globalNav").getElementsByTagName("h3");
	var uls=$("globalNav").getElementsByTagName("ul");
	for(var i=0;i<h3s.length;i++) {
		h3s[i].target=uls[i];
		h3s[i].onclick=function() {
			for(var j=0;j<uls.length;j++) {
				if(uls[j]==this.target) {
					uls[j].className="selected";
				} else {
					uls[j].className="";
				}
			}
			initNavHeight();
		}
	}
	initNavHeight();
}
window.onresize=function() {
	initNav();
}
function initNavHeight() {
	var h3s=$("globalNav").getElementsByTagName("h3");
	var h=$("globalNav").offsetHeight;
	var si=getElementsByClassName($("globalNav"),"selected")[0];
	si.style.height=h-(h3s.length*27)-20+"px";
}
function getElementsByClassName(f,c) {
	var tags="";
	if(f.all) {
		tags=f.all;
	} else {
		tags=f.getElementsByTagName("*");
	}
	var ts=new Array();
	for(var i=0;i<tags.length;i++) {
		if(tags[i].className==c) {
			ts.push(tags[i]);
		}
	}
	//ts.reverse();
	return ts;
}
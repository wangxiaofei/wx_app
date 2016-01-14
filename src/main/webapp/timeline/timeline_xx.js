//时间线,参数时间格式为yyyy-MM-dd
var Timeline = function(id,from,to){	
	this.outId= id;
	this.start = strToDate(from);
	this.end = strToDate(to);
	this.section = 10;//时间线上显示多少天
	this.currentDate = this.start ;//时间线上的第一个时间是多少
	this.line = [];//显示的具体时间
	this.pre ;//往后翻
	this.next;//往前翻
	this.selectDay="" ; //选择的日期
	this.init = function(){
		this.initLine();
		var container = this.createContainer();
		container.appendChild(this.createPre());
		container.appendChild(this.createLine());
		container.appendChild(this.createNext());
		document.getElementById(id).appendChild(container);
		this.render();
	};
	this.init();
	
};
Timeline.prototype={
	//填充line
	initLine : function(){
		var distance = compareDate(this.currentDate,this.end);
		if(distance ==0){
			return;
		}
		this.line = [];
		var nowSection = this.section;
		if(distance<this.section){
			nowSection = distance+1;
		}
		for(var i=0;i<nowSection;i++){
			var now = addDate(this.currentDate,i);
			this.line[i] = dateToStr(now);
		}
	},
	createContainer : function(){
		var that = this;
		var container = document.createElement("div");
		container.id = "timeline_container_div";
		container.className = "timeline";
		//点击时间线外部容器其它地方时，取消时间的选择
		$("#"+that.outId).parent().click(function(){
			var tags = getElementsByClassName("clickLight");
			that.selectDay = "";
			if(tags.length>0){
				if(typeof(dayClickCallback)=="function"){
					dayClickCallback("");
				}
			}
			for(var i=0;i<tags.length;i++){
				tags[i].className = "liClass";
			}
    	});
    	$(container).click(function(e){
    		e.stopPropagation();
    	});
		return container;
	},
	//创建后翻元素
	createPre : function(){
		var preDiv = document.createElement("div");
		preDiv.className = "preClass_div";
		var span = document.createElement("span");
		span.innerHTML = "<img src='/timeline/pic/tl_left.png'>";
		span.className = "spanClass";
		var that = this;
		span.onclick = function(){
			var preDate =that.getCurrentDate(addDate(that.currentDate,-that.section));
			if(compareDate(preDate, that.currentDate)==0){
				return ;
			}
			that.currentDate = preDate;
			that.initLine();
			that.render();
			if(typeof(btnClickCallback)=="function"){
				btnClickCallback(-1);
			}
		};
		preDiv.appendChild(span);
		return preDiv;
	},
	//创建前翻元素
	createNext : function(){
		var nextDiv = document.createElement("div");
		nextDiv.className = "nextClass_div";
		var span = document.createElement("span");
		span.innerHTML = "<img src='/timeline/pic/tl_right.png'>";
		span.className = "spanClass";
		var that = this;
		span.onclick = function(){
			var nextDate =that.getCurrentDate(addDate(that.currentDate,that.section));
			if(compareDate(nextDate,that.end)==0){
				return ;
			}
			that.currentDate = nextDate;
			that.initLine();
			that.render();
			if(typeof(btnClickCallback)=="function"){
				btnClickCallback(1);
			}
		};
		nextDiv.appendChild(span);
		return nextDiv;
	},
	//防止当前日期超出选择的时间段，这时候要取临界值
	getCurrentDate : function(currentDate){
		if(currentDate.getTime()>this.end.getTime()){
			return this.end;
		}else if(currentDate.getTime()<this.start.getTime()){
			return this.start;
		}else{
			return currentDate;
		}
	},
	//创建时间线
	createLine : function(){
		var lineDiv = document.createElement("div");
		lineDiv.id = "timeline_div";
		lineDiv.className = "timeline_bottom";
		return lineDiv;
	},
	getLine : function(){
		return document.getElementById("timeline_div");
	},
	//显示
	render : function(){
		var that = this;
		var line = this.getLine();
		line.innerHTML = "";
		var ul = document.createElement("ul");
		for(index in that.line){
			var li = document.createElement("li");
			var lidiv = document.createElement("div");
			lidiv.className="liClass";
			lidiv.onclick = function(){
				var tags = getElementsByClassName("clickLight");
				for(var i=0;i<tags.length;i++){
					if(tags[i] !=this){
						tags[i].className = "liClass";
					}
				}
				if(this.className == "clickLight"){
					this.className = "liClass";
					that.selectDay = "";
				}else{
					this.className = "clickLight";
					that.selectDay = this.innerHTML;
				}
				if(typeof(dayClickCallback)=="function"){
					dayClickCallback(this.innerHTML);
				}
			};
			lidiv.innerHTML = that.line[index];
			li.appendChild(lidiv);
			
			ul.appendChild(li);
		}
		line.appendChild(ul);
	}
};
//只适合yyyy-MM-dd格式
var strToDate = function(str)
{
  var arys= new Array();
  arys=str.split('-');
  var newDate=new Date(arys[0],arys[1]-1,arys[2]); 
  return newDate;
};
var dateToStr = function(date){
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	if((month+"").length<2){
		month = "0"+month;
	}
	if((day+"").length<2){
		day = "0"+day;
	}
	return year+"-"+month+"-"+day;
};
//在date这个日期的基础上加减day天，正负表示加减
var addDate = function(date,day){
	var millons = date.getTime();
	millons = millons + day*24*60*60*1000;
	return new Date(millons);
};
//两个日期之间相差多少天
var compareDate = function (date1,date2){
	var d1 = date1.getTime();
	var d2 = date2.getTime();
	return Math.abs(d1-d2)/24/60/60/1000;
};
//获得相同className的元素
var getElementsByClassName = function(className){
	var el = [];
	var _el = document.getElementsByTagName("*");
	for(var i=0;i<_el.length;i++){
		if(_el[i].className == className){
			el[el.length] = _el[i];
		}
	}
	return el;
};
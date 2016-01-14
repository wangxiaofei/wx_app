jQuery(function($){
	var now  = new Date();
	var year = now.getFullYear();
	var yearRange = (year-20)+":"+year;
        $.datepicker.regional['zh-CN'] = {
        		monthNamesShort: ['一月','二月','三月','四月','五月','六月',
                '七月','八月','九月','十月','十一月','十二月'],
                monthNames: ['一','二','三','四','五','六',
                '七','八','九','十','十一','十二'],
                dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
                dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
                dayNamesMin: ['日','一','二','三','四','五','六'],
                dateFormat: 'yy-mm-dd',
                firstDay: 1,
                prevText:"上一月" ,
                nextText:"下一月",
                isRTL: false,
                showMonthAfterYear: true,
                yearRange:yearRange};
        $.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

$(function(){
	var from = $("#from");
	var to = $("#to");
	if(from){
		$("#from").datepicker({
			defaultDate:"+1w",
			changeMonth:true,
			changeYear: true,
			numberOfMonths:1,
			maxDate: "-1m",
			onClose:function(selectedDate){
				if(selectedDate==null || selectedDate==""){
				}else{
					$("#to").datepicker("option","minDate",selectedDate);
				}
			}
		}
	);
	}
	if(to){
		$("#to").datepicker({
			defaultDate:"+1w",
	        changeMonth:true,
	        changeYear: true,
	        numberOfMonths:1,
	        maxDate: "-1m",
	        onClose:function(selectedDate){
	        	if(selectedDate==null || selectedDate==""){
				}else{
					$("#from").datepicker("option","maxDate",selectedDate);
				}
	        }
		});
	}
});
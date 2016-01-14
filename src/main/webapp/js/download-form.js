function downloadReport(formId){
	var form = document.getElementById(formId);
	if(form){
		var url = $(form).attr("action");
		var inputList = $(form).children("input");
		var params = "date="+1;
		for(var i=0;i<inputList.length;i++){
			var input = $(inputList[i]);
			params = params+"&"+input.attr("id").replace("d_","")+"="+input.attr("value");
		}
		window.location.href = url+"?"+params;
	}
}

function downloadSubmit(formId) {
	document.getElementById(formId).submit();
}

$(document).ready(function(){
	accyear=$("#hiddenaccyear").val();
	$("#allstudent tbody tr").click(function(){
		var examid = $( this ).find(".examCode").attr("id");
		var classid = $( this ).find(".classid").attr("id");
		hschoolLocation=$("#hiddenlocation").val();

		if(!timetableset($( this ).find(".examCode").attr("id"))){
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			$(".validateTips").text("Set Time Table for this Exam.");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}

		$('#myValue').val(accyear+","+hschoolLocation+","+examid+","+classid);
		$('#myform').submit();
	});
	$("#back1").click(function(){
		$('#backValue').val(accyear+","+$("#historylocId").val());
		$('#backform').submit();
	});

	if($("#allstudent tbody tr").length==0){
		$('#allstudent tbody').append("<tr><td colspan='6'>No Records Found</td></tr>");
	}
});

function timetableset(examid){
	var status;
	var datalist={
			"examid":examid,	
			"location":$("#hiddenlocation").val(),
			"accyear":$("#historyacayearId").val(),
	};
	$.ajax({
		type : "POST",
		url : "examTimetablePath.html?method=timetableset",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			if(result.status =="true" ) {
				status=true;
			}else{
				status=false;
			}
		}
	});
return status;
}
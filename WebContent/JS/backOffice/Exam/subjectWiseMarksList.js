$(document).ready(function(){
	
	$("#allstudent tbody tr").click(function(){
		
		
		 var classid = $( this ).find(".classid").attr("id");
		var accyear = $("#hiddenaccyid").val();
		var examid =  $(this).find(".examCode").attr("id");
		var hschoolLocation=$("#hiddenlocid").val();
		
		 if(!timetableset($( this ).find(".examCode").attr("id"))){
			   $(".errormessagediv").show();
				$(".successmessagediv").hide();
				$(".validateTips").text("Time Table Is Not Setted For This Exam");
				setTimeout(function(){
					 $(".errormessagediv").hide();
				},3000);
				return false;
		   }
		 
		window.location.href="examCreationPath.html?method=getSubjectClass&accyear="+accyear+
		"&examid="+examid+"&hschoolLocation="+hschoolLocation+"&classid="+classid+"&historyacayear="+$("#historyacayear").val()
		+"&historylocation="+$("#historylocation").val();
		
	});
	if($("#allstudent tbody tr").length>0){

		$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
		pagination(50);
		$("#show_per_page").change(function(){
			pagination($(this).val());
		});
	}else{
		$('#allstudent tbody').append("<tr><td colspan='6'>No Records Found</td></tr>");
		$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records 0</span></div><div class='pagination pagelinks'></div></div>")
		pagination(50);
		$("#show_per_page").change(function(){
			pagination($(this).val());
		});
	}
	
	$("#back1").click(function(){
		window.location.href="examTimetablePath.html?method=subjectmarksList&historyacayear="+$("#historyacayear").val()
		+"&historylocation="+$("#historylocation").val();
	});

});

function timetableset(examid){
	var status;
	var datalist={
			"examid":examid,	
			"location":$("#hiddenlocid").val(),
			"accyear":$("#hiddenaccyid").val(),
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
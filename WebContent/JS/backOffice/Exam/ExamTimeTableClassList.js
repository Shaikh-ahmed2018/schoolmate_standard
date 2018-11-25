$(document).ready(function(){
	
	$('#exam').change(function(){
		getclasslist($('#exam'));
		tablerowclick();
	});
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	tablerowclick();
	
	$("#back1").click(function(){
		/*window.location.href = "examTimetablePath.html?method=getexamtimetableclass&accyear="+$("#historyaccyear").val()
		+"&locid="+$("#historylocation").val()+"&historyaccyear="+$("#historyaccyear").val()+"&historylocation="+$("#historylocation").val();*/
		
		$('#myValue').val($("#historyaccyear").val()+","+$("#historylocation").val());
		$('#myform').submit();
	});
	
	if($("#historyexam").val()!=""){
		$('#exam').val($('#historyexam').val());
		getclasslist($('#exam'));
	}
	
});
function tablerowclick(){
	 $("#allstudent tbody tr").click(function(){
			var accyear = $("#hiddenaccyear").val();
			var locid = $("#hiddenloc").val();
			var classid  = $("#classId").val();
			//var classname = $(this).find(".classid").text();
			var examid = $(this).attr('id');
			if(examid == "" || examid == undefined){
				$('.errormessagediv').show();
				$('.validateTips').text("Field Required - Exam");
				document.getElementById("exam").style.border = "1px solid #AF2C2C";
				document.getElementById("exam").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}else{
				
				//window.location.href = "examTimetablePath.html?method=getExamByClassWise&accyear="+accyear+"&locid="+locid+"&classid="+classid;
				/*window.location.href = "examTimetablePath.html?method=getexamtimetableclasssection&accyear="+accyear+
				"&locid="+locid+"&classid="+classid+"&examid="+examid;*/
				
				$('#myValue1').val(accyear+","+locid+","+classid+","+examid);
				$('#myform1').submit();
				
				/*window.location.href = "examTimetablePath.html?method=getexamtimetableclasssection&accyear="+accyear+
				"&locid="+locid+"&classid="+classid+"&classname="+classname+"&examid="+examid+"&historyaccyear="+$("#historyaccyear").val()
		        +"&historylocation="+$("#historylocation").val()+"&historyexam="+$("#exam").val();*/
			}
			
		});
}
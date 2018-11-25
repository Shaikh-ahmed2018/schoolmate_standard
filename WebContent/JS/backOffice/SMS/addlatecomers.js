$(document).ready(function() {
	
	totalSMSBalance = 0;
	getBalanceSMSCount();


	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});

	fSDate = new Date($("#historystartdate").val());
	fEDate = new Date($("#historyenddate").val());
	
	$("#dateId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
		}

	});
	
	$("#back1").click(function(){
		/*window.location.href="menuslist.html?method=latecomingstudentslist&historylocId="+$("#historylocId").val()
		+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historystartdate="+$("#historystartdate").val()+
		"&historyenddate="+$("#historyenddate").val();*/
		window.location.href="menuslist.html?method=latecomingstudentslist";
	});

	var currentDate = new Date();  
	$("#dateId").datepicker("setDate",currentDate);

	$("#description").val("Dear Parents,Your Child came late to school Today("+$("#dateId").val()+").Please ensure that this does not happen again in the future.");


	$("#dateId").change(function(){
		$("#description").val("Dear Parents,Your Child came late to school Today("+$("#dateId").val()+").Please ensure that this does not happen again in the future.");
	});
	getClassList();
	$("#locId").change(function(){
		getClassList();
	});	


	$("#classid").change(function(){
		getSectionList();
		getStudent();
	});

	$("#sectionid").change(function(){
		getStudentBySections();
	});	

	$("#save").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();

		var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(this).val(); 
		});

		var dateId = $("#dateId").val();
		var classid = $("#classid").val();
		var studentid = $("#studentid").val();
		var locId = $("#locId").val();
		var sectionid = $("#sectionid").val();
		var description = $("#description").val();
		var balanceSMS = totalSMSBalance;


		if(locId==""||locId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Requored - Branch");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(dateId==""||dateId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Late Arrived Date");
			document.getElementById("dateId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(classid==""||classid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Class");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(sectionid==""||sectionid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Divisions From List");
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(studentid==""||studentid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Students From List");
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else{
			datalist={
					"dateId"      : dateId.toString(),
					"classid"     : classid.toString(),
					"sectionid"	  : sectionid.toString(),
					"description" : description.toString(),
					"locId"       : locId.toString(),
					"studentid"   : studentList.toString(),
					"balanceSMS"   : balanceSMS.toString(),
			},
			$.ajax({
				type : 'POST',
				url : "smsPath.html?method=addlatecomers",
				data : datalist,
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.jsonResponse=="success"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Late Message Sent Successfully");
						setTimeout(function(){
							window.location.href="menuslist.html?method=latecomingstudentslist";
						},3000);
					}
					else if(result.jsonResponse=="insufficientSMSBalance"){
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("Insufficient SMS Balance To Send This SMS");
						 setTimeout(function(){
						    window.location.href="menuslist.html?method=latecomingstudentslist";
						 },3000);
					
					}
					else{
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Late Message Sending Failed");
						setTimeout(function(){
							window.location.href="menuslist.html?method=latecomingstudentslist";
						},3000);
					}
				}
			});
		}
	});

});

function getClassList(){
	var locationid=$("#locId").val();
	datalist={
			"locationid" : locationid
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classid').html("");
			$('#classid').append('<option value="">' +"----------Select----------"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'+result.ClassList[j].classcode+'">'+result.ClassList[j].classname+'</option>');
			}
		}
	});
}

function getSectionList(){
	datalist={
			"classidVal" : $("#classid").val(),
			"locationId":$("#locId").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			var j=0;
			var len=result.sectionList.length;
			for ( j = 0; j < len; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getStudentBySections() {

	var locId = $("#locId").val();
	var classid = $("#classid").val();

	var sectionlist = []; 
	$('#sectionid :selected').each(function(i, selected){ 
		sectionlist[i] = $(this).val();
	});
	datalist={
			"locId":locId,
			"classid":classid,
			"sectionlist":sectionlist.toString(),
	},

	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEventBySections",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#studentid').html("");
			var j=0;
			var len=result.studentlist.length;
			for(var j = 0; j < len; j++){
				$('#studentid').append(
						'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
			}
		}
	});
}

function getStudent() {

	var locId = $("#locId").val();
	var classid = $("#classid").val();

	datalist={
			"locId":locId,
			"classid":classid,
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEvent",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#studentid').html("");
			var j=0;
			var len=result.studentlist.length;
			for(var j = 0; j < len; j++){
				$('#studentid').append(
						'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
			}
		}
	});
}

function getBalanceSMSCount(){
	var locationid=$("#locId").val();
	
	datalist={
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getBalanceSMSCount",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			totalSMSBalance = result.smsCount;
		
			return totalSMSBalance;
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

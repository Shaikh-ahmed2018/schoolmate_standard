
$(document).ready(function() {
	
	totalSMSBalance = 0;
	getBalanceSMSCount();
	
	$("#back1").click(function(){
		/*window.location.href="menuslist.html?method=meetingslist&historylocId="+$("#historylocId").val()+
		"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historystartdate="+$("#historystartdate").val()+
		"&historyenddate="+$("#historyenddate").val()+"&historytitleid="+$("#historytitleid").val();*/
		window.location.href="menuslist.html?method=meetingslist";
	});

	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());

	$("#dateId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
			
			var title = $("#titleid").val();

			if(title == ""){
				$("#eventName").hide();
			}
			if(title == "event"){
				$("#eventName").show();
			}
			
			var startTime = $("#starttime1").val();
			var endTime = $("#endtime1").val();
			var selectedDate = $("#dateId").val();
			
			if(title=="meeting")
			{
				$("#eventName").hide();
				$("#description").val("Dear Parent, The Teachers-Parent Meeting organized on ("+selectedDate+") at ("+startTime+") to discuss about your child's performance. Request your presence in time.");
				var sms=$('#description').val();
				$('#maxlimit').text(parseInt(sms.length));
				
			}
			else
			{
				$("#description").val("Dear Parent, special event to celebrate "+$("#venueid").val()+" being held in our school on "+selectedDate+" - "+startTime+". Request your valuable presence and grace the occasion.");
				var sms=$('#description').val();
				$('#maxlimit').text(parseInt(sms.length));
			}

		}

	});
	
	$('.form_time').datetimepicker({
		
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0,
	});
	
	$("#eventName").hide();
	getClassList();
	
	$("#locId").change(function(){
		getClassList();
	});	

	$("#classid").change(function(){
		getSectionList();
		getSubject();
	});

	$("#sectionid").change(function(){
		getStudentBySections();
	});	


	$("#titleid").change(function(){

		var title = $("#titleid").val();

		if(title == ""){
			$("#eventName").hide();
		}
		if(title == "event"){
			$("#eventName").show();
		}

		var startTime = $("#starttime1").val();
		var endTime = $("#endtime1").val();
		var selectedDate = $("#dateId").val();
		
		if(title=="meeting")
		{
			$("#eventName").hide();
			$("#description").val("Dear Parent, The Teachers-Parent Meeting organized on ("+$("#dateId").val()+") at ("+$("#starttime1").val()+") to discuss about your child's performance. Request your presence in time.");
			var sms=$('#description').val();
			$('#maxlimit').text(parseInt(sms.length));
			
		}
		else
		{
			$("#description").val("Dear Parent, special event to celebrate "+$("#venueid").val()+" being held in our school on "+selectedDate+" - "+startTime+". Request your valuable presence and grace the occasion.");
			var sms=$('#description').val();
			$('#maxlimit').text(parseInt(sms.length));
		}
	});
	
	$("#starttime1").change(function(){
		
		var title = $("#titleid").val();

		if(title == ""){
			$("#eventName").hide();
		}
		if(title == "event"){
			$("#eventName").show();
		}
		
		var startTime = $("#starttime1").val();
		var endTime = $("#endtime1").val();
		var selectedDate = $("#dateId").val();
		
		if(title=="meeting")
		{
			$("#eventName").hide();
			$("#description").val("Dear Parent, The Teachers-Parent Meeting organized on ("+selectedDate+") at ("+startTime+") to discuss about your child's performance. Request your presence in time.");
			var sms=$('#description').val();
			$('#maxlimit').text(parseInt(sms.length));
			
		}
		else
		{
			$("#description").val("Dear Parent, special event to celebrate "+$("#venueid").val()+" being held in our school on "+selectedDate+" - "+startTime+". Request your valuable presence and grace the occasion.");
			var sms=$('#description').val();
			$('#maxlimit').text(parseInt(sms.length));
		}
	});

	$("#venueid").keyup(function(){
		
		var startTime = $("#starttime1").val();
		var endTime = $("#endtime1").val();
		var selectedDate = $("#dateId").val();
		
		$("#description").val("Dear Parent, special event to celebrate "+$("#venueid").val()+" being held in our school on "+selectedDate+" - "+startTime+". Request your valuable presence and grace the occasion.");

		var smscount=0;
		smscount+=$("#description").val().length;
		$('#maxlimit').text(parseInt(smscount));
		venue = $(this).val().trim();
		$('#maxlimit').text(Number(smscount));
		
	});


	$("#save").click(function(){
		$(".successmessagediv").hide();
		$(".errormessagediv").hide();

		var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(this).val(); 
		});

		var sectionlist = []; 
		$('#sectionid :selected').each(function(i, selected){ 
			sectionlist[i] = $(this).val();
		});

		var locId = $("#locId").val();
		var dateId = $("#dateId").val();
		var classid = $("#classid").val();
		var starttime = $("#starttime1").val();
		var subjectid = $("#subjectid").val();
		var studentid = $("#studentid").val();
		var titleid = $("#titleid").val();
		var sectionid = $("#sectionid").val();
		var endtime = $("#endtime1").val();
		var venueid = $("#venueid").val();
		var description = $("#description").val();
		var SMSCharacters = $('#maxlimit').text();
		var balanceSMS = totalSMSBalance;


		if(locId==""||locId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Branch");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(dateId==""||dateId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Date");
			document.getElementById("dateId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		} 

		else if(classid=="all"||classid==null || classid==""){
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
		else if(starttime==""||starttime==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Start Time");
			document.getElementById("starttime").style.border = "1px solid #AF2C2C";
			document.getElementById("starttime").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(endtime==""||endtime==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - End Time");
			document.getElementById("endtime").style.border = "1px solid #AF2C2C";
			document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(endtime < starttime){
			$(".errormessagediv").show();
			$(".validateTips").text("End Time Should Be More Than Start Time");
			document.getElementById("endtime").style.border = "1px solid #AF2C2C";
			document.getElementById("endtime").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(titleid==""||titleid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Title");
			document.getElementById("titleid").style.border = "1px solid #AF2C2C";
			document.getElementById("titleid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(studentid==""||studentid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Student From List");
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else{
			datalist={
					"dateId"      : dateId,
					"locId"       : locId,
					"classid"     : classid,
					"starttime"   : starttime,
					"subjectid"   : subjectid,
					"studentid"   : studentList.toString(),
					"titleid"     : titleid,
					"sectionid"   : sectionlist.toString(),
					"endtime"     : endtime,
					"description" : description,
					"SMSCharacters" : SMSCharacters,
					"balanceSMS" : balanceSMS,
			},

			$.ajax({
				type : 'POST',
				url : "smsPath.html?method=insertmeeting",
				data : datalist,
				async : false,
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.jsonResponse=="success"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Meeting/Event Message Sent Successfully");
						setTimeout(function(){
							window.location.href="menuslist.html?method=meetingslist";
						
						},3000);
					}
					else if(result.jsonResponse=="insufficientSMSBalance"){
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("Insufficient SMS Balance To Send This SMS");
						 setTimeout(function(){
						    window.location.href="menuslist.html?method=meetingslist";
						 },3000);
					
					}
					else{
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Meeting/Event Message Sending Failed");
						setTimeout(function(){
							window.location.href="menuslist.html?method=meetingslist";
						},3000);
					}
				}
			});
		}

	});

});

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

function getSubject() {

	var classid = $("#classid").val();

	datalist={
			"classid" : classid 
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getSubjectMeeting",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#subjectid').html("");
			$('#subjectid').append('<option value="all">' +"----------Select----------"+ '</option>');
			var j=0;
			var len=result.subjectlist.length;
			for(j = 0; j < len; j++){
				$('#subjectid').append(
						'<option value="'
						+ result.subjectlist[j].subjectId + '">'
						+ result.subjectlist[j].subjectName
						+ '</option>');
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

			var len=result.studentlist.length;
			for(var j = 0; j < len; j++){
				$('#studentid').append(
						'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
			}
		}
	});


}


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
			$('#classid').empty();
			$('#classid').append('<option value="all">' +"----------Select----------"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
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



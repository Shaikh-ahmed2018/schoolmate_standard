$(document).ready(function() {
	
	totalSMSBalance = 0;
	getBalanceSMSCount();

	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
	}

	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$("#back1").click(function() {
		window.location.href="menuslist.html?method=absentlist";
	});

	$(".select").change(function() {
		if($(".select").length == $(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});

	if($("#div1 .panel-body").text().trim()=="Nothing found to display."){
		$("#div1 .panel-body").empty();
		$("#div1 .panel-body").append('<table id="allstudent" class="table">' +
				'<thead><tr>' +
				'<th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
				'<th class="sortable">date<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">studentName<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">classname<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">section<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">smstext<img src="images/sort1.png" style="float: right"></a></th>'+
		'<tbody><tr><td colspan="6">No Records Found</td></tr></tbody></table>');

	}
	
	fSDate = new Date($("#historystartdate").val());
	fEDate = new Date($("#historyenddate").val());
	var today = new Date();

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
			var dates = $('#dateId').val();
			$('#smstext').val("Dear Parent, your child is absent to school today ("+dates+") as per our records. Request your timely action to maintain your child attendance in school.");
		}
	}).datepicker('setDate', today);
	
	var today_date = $('#dateId').val();
	
	$('#smstext').val("Dear Parent, your child is absent to school today ("+today_date+") as per our records. Request your timely action to maintain your child attendance in school.");


	$('#AbsentSave').click(function(){
		var locId=$('#locId').val();
		var classid = $('#classid').val();
		var date = $('#dateId').val();
		var smstext = $('#smstext').val();
		var student = $('#studentid').val();
		var section = $('#sectionid').val();
		var balanceSMS = totalSMSBalance;

		var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(this).val(); 
		});


		if (locId == "" || locId==null) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Branch");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if (date == "" || date == null || date ==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Absent Date");
			document.getElementById("dateId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if (classid =="all" || classid==null || classid=="") {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Class");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if (section == "" || section==null ) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Divisions From List");
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);

			return false;
		}

		else if (student == "" || student==null ) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Student From List");
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if (smstext == "" || smstext == null || smstext == undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - SMS Text");
			document.getElementById("smstext").style.border = "1px solid #AF2C2C";
			document.getElementById("smstext").style.backgroundColor ="#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		} 
		else{

			datalist={
					"date" : date ,
					"classid" : classid,
					"smstext" : smstext,
					"studentlist" : studentList.toString(),
					"locId":locId,
					"balanceSMS":balanceSMS,
			},

			$.ajax({
				type : 'POST',
				url : "absentSMS.html?method=storeAbsentSms",
				data : datalist,
				async : false,
				beforeSend: function()
				{
					$('#loader').show();
				},
				success : function(response) {
					var result = $.parseJSON(response);

					if(result.status=="success"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Absent Message Sent Successfully");
						setTimeout(function(){
							window.location.href ="menuslist.html?method=absentlist";
						},3000);
					}
					else if(result.status=="insufficientSMSBalance"){
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("Insufficient SMS Balance To Send This SMS");
						 setTimeout(function(){
						    window.location.href="menuslist.html?method=absentlist";
						 },3000);
					
					}
					else{

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Absent Message Sending Failed");
						setTimeout(function(){
							window.location.href ="menuslist.html?method=absentlist";
						},3000);
					}
				}
			});

		}
	});

	getClassList();
	$("#locId").change(function(){
		getClassList();

		$("#classid").val("");
		$('#sectionid').html("");
		$('#studentid').html("");

		if($("#locId")==""){
			$("#classid").val("");
			$('#sectionid').html("");
			$('#studentid').html("");
		}
	});	

	$("#classid").change(function(){
		getSectionList();
		getStudent();
	});

	$("#sectionid").change(function(){
		getStudentBySections();
	});	

});

function  validateEventSms(){

	var meetingstatus=false;
	var date=$('#dateId').val();
	var smstext=$('#smstext').val();


	var validatedetails = {
			"date" : date,
			"smstext" : smstext

	};

	$.ajax({
		type : 'POST',
		url : "absentSMS.html?method=validateAbsentSms",
		data : validatedetails,
		async:false,

		success : function(response) {
			var result = $
			.parseJSON(response);

			meetingstatus=result.status;
		}
	});

	return meetingstatus;

}

function getClassList(){
	var locationid=$("#locId").val();
	datalist={
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classid').html("");
			$('#classid').append('<option value="all">' +"----------Select----------"+ '</option>');
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

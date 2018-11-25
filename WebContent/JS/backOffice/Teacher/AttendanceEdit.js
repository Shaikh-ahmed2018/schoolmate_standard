$(document).ready(function() {
	
	$("#back1").click(function(){
		window.location.href = "teachermenuaction.html?method=attendaceStatus&historyacademicId="+$("#historyacademicId").val()+
		"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historyspecId="+$("#historyspecId").val()
		+"&historystartdate="+$("#historystartdate").val()+"&historyenddate="+$("#historyenddate").val();
	});
	
	getAttendenceStudentList();
	/*getStudentNameList();*/
	
	var dd =new Date();
	var day=dd.getDate();
	if(day < 10){
		day = "0" + day;
	}
	var month = dd.getMonth();
	if(month < 10){
		month = "0" + month;
	}
	var year = dd.getFullYear();
	
	var date = day +"-"+month+"-"+year;
	$("#date").val(date);
	
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	 
	
	$(".errormessagediv").hide();
	
	/*$("#date").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});*/
	
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});
	
	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
	});
	
	/*$("td[id]").one( "click", function() {
		  var idString =$( this ).attr( "id" );
		 window.location.href="reportaction.html?method=getSingleStdFeeStatusReportDetails&idString="+idString+"&classname="+classname+"&term="
		 +term+"&section="+section+"&accyear="+accyear;
	});*/
	
	$(".buttonstyle").click(function(){
		
		var idString =$( this ).attr( "id" );

		 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString;
			
	});
	
	
	$("#studentname").change(function(){
		
		$("#divIdList").animate({
			 scrollTop: $("tr."+$(this).val()).css('top','0')
		});
		$("tr."+$(this).val()).css({
			"background":"#9CDDE3",
			"position":"absolute",
				"top":"36px"
		});
		$("tr").not("."+$(this).val()).css({
			"background":"#FFF",
				"position":"static",
		});
	});
	

	
	$("#saveAttendance").click(function(){
		 
		var date =$("#date").val();
		
		var teacherIdArray=[];
		var statusArray=[];
		
		var teacherIdArray=[];
		var statusArray=[];
		var period = [];
		var periodvalue="";
		var periodStatus = [];
		var statusids = [];
        for(var k=1;k<=Number(getperiodcount());k++){
    	  
    	   period.push("period"+(k));
		}
      
        for(var k=1;k<=period.length;k++){
        	 period[k-1]=[];
	      	
	 		}
		accyear=$("#Acyearid").val();
		
		$('#allstudent tbody tr').each(function(){
			
			statusids.push($(this).attr("id"));
			
			var teacherID=$(this).attr("class");
			var status = $(this).find('.finalst').val();
			 for(k=1;k<=Number(getperiodcount());k++){
					var p = $(this).find("select[name='period"+k+"']").val();
					
					 if(p!=undefined && p!=""){
						 
						 periodvalue=periodvalue+","+"period"+k+"-"+p;
						}
				}

		
				
			teacherIdArray.push(teacherID);
		
			
			if(status!=undefined && status!=""){
				
				statusArray.push(status);
			}
			 
			periodvalue=periodvalue+":";
			
		});
	    
		var datalist={
				
				"date" : date,
				"teacherIdArray" : teacherIdArray.join(),
				"statusArray" : statusArray.join(),
				"periodvalue":periodvalue,
				"accyear":accyear,
				"statusids" : statusids.toString(),
				"attenid" : $("#attenid").val(),
			    "noofperiod":$("#noofperiod").val()
		};
		
		$.ajax({
			type : "POST",
			url : "StudentAttendanceActionPath.html?method=updateNewAttendanceStatus",
			data :datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(data) {
				var result = $.parseJSON(data);
				
				if(result.status=="true"){
					$("#loder").hide();
					$('.successmessagediv').show();
					$('.successmessage').text("Attendance updated successfully");
					
				setTimeout(function(){
					
					 window.location.href="teachermenuaction.html?method=attendaceStatus";
				 
				 },3000);
				
				}else{
					$("#loder").hide();
					$('.errormessagediv').show();
					$('.validateTips').text("Attendance not updated,Try later");
				}
			}
		});
		
	});
	 
	var hlocationId=$("#hlocationId").val(); 
	 $("#Acyearid").val($("#hiddenAcyearid").val());
	
	if(hlocationId!=undefined && hlocationId!=""){
		
		$("#locationname option[value='"+hlocationId +"']").attr('selected', 'true');
	
	}
	 
	$.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		data :{"locationId":$("#locationname").val()},
	    async:false,
		success : function(data) {

			var result = $.parseJSON(data);
			$("#classId").html("");
			$("#classId").append(
					'<option value="' + "" + '">' + "-------------Select------------" + '</option>');
			
			for ( var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

				$('#classId').append('<option value="'
										+ result.classDetailsIDandClassDetailsNameList[j].secDetailsId
										+ '">'
										+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName
										+ '</option>');

			}

		}
	});
	
	var hclassId=$("#hclass").val();
	
	if(hclassId!=undefined && hclassId!=""){
		
		$("#classId option[value='"+hclassId +"']").attr('selected', 'true');
		
		
		var classidVal = $("#classId").val();
       
		datalist = {
			"classidVal" : classidVal,
			"locationId":$("#locationname").val()
		},
		
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getClassSection",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$("#section").html("");
				$("#section").append(
						'<option value="' + "" + '">' + "-------------Select------------" + '</option>');
 
				for ( var j = 0; j < result.sectionList.length; j++) {
					$("#section").append(
							'<option value="' + result.sectionList[j].sectioncode
									+ '">' + result.sectionList[j].sectionnaem
									+ '</option>');
				}

			}
		});

		$("#section option[value=" + $("#hsection").val().trim() + "]").attr('selected', 'true');
		//getStudentNameList();
		$("#date").val($("#hdate").val().trim());
		 
	}

	/*$('#allstudent tr td').each(function(){
		
		
		var status=$(this).find('.statusclass').val();
		
		
		if(status!=undefined){
		
		var rowid=$(this).find('.statusclass').attr("id");
		
		$('#'+rowid).find('option').remove();
	
		var statusList=[];
		statusList.push("Present");
		statusList.push("Absent");
		statusList.push("Leave");
		
		
		for (var j = 0; j < statusList.length; j++) {
			

			$("#"+rowid).append('<option value="'+ statusList[j]+ '">'
									+ statusList[j]+ '</option>');
		}
		
		
		//$("#"+rowid+" option[value=" + status + "]").attr('selected', 'true');
		
		}
		
	});*/
	

$("#searchAttendanceList").click(function(){
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	
	window.location.href="teachermenuaction.html?method=attendaceStatus&startDate="+startDate+"&endDate="+endDate;	
	
	
});




$("#classId").change(function(){
	
	var classidVal = $("#classId").val();
	var locationId = $("#locationname").val();

	datalist = {
		"classidVal" : classidVal,
		"locationId":locationId,
	},
 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#section").html("");
			$("#section").append(
					'<option value="' + "" + '">' + "-------------Select------------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#section").append(
						'<option value="' + result.sectionList[j].sectioncode
								+ '">' + result.sectionList[j].sectionnaem
								+ '</option>');
			}

		}
	});


});


$("#classId").change(function(){
	
	var classidVal = $("#classId").val();
	var locationId = $("#locationname").val();

	datalist = {
		"classidVal" : classidVal,
		"locationId":locationId
	},
	
	$.ajax({
	
	type : 'POST',
	url : "StudentAttendanceActionPath.html?method=getClassSpec",
	data : datalist,
	async : false,
	success : function(response) {
		var result = $.parseJSON(response);
		$("#specialization").html("");
		$("#specialization").append(
				'<option value="' + "" + '">' + "-------------Select------------" + '</option>');

		for ( var j = 0; j < result.specList.length; j++) {
			$("#specialization").append(
					'<option value="' + result.specList[j].specId
							+ '">' + result.specList[j].specName
							+ '</option>');
		}

	}
	});
	
});




$("#search").click(function(){
	
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var spec = $("#specialization").val();
	if(classId==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select class");
		document.getElementById("classId").style.border = "1px solid #AF2C2C";
		document.getElementById("classId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
		
	}else if(section==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select section");
		document.getElementById("section").style.border = "1px solid #AF2C2C";
		document.getElementById("section").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
	}else if(date==""){
		
		$('.errormessagediv').show();
		$('.validateTips').text("Select Date");
		document.getElementById("date").style.border = "1px solid #AF2C2C";
		document.getElementById("date").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		
	}else{
		
		window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date+"&spec="+spec;
		
	}
});

$("#reset").click(function(){
	
	$("#classId").val("");
	$("#section").val("");
	$("#date").val("");
	 
});




$(".GetTimeTable").click(function(){
	
	var stuId=(this).id;
	var classId=$("#classId option:selected").text();
	var classname=$("#classId option:selected").val();
	
	var sectionId=$("#section option:selected").text();
	var sectionName=$("#section option:selected").val();
	var date=$("#date").val();
	var status=$(this).prev().find('input').val();
		
	window.location.href="StudentAttendanceActionPath.html?method=getStudentPeriodAttendance&classId="+classId+","+classname+"&section="+sectionId+","+sectionName+"&stuId="+stuId+"&date="+date+"&status"+status;
	
	
});

$("#UpdatePeriodAtt").click(function(){
	
	var studentId=$("#hstudentId").val();
	var classsId=$("#hclassId").val();
	var sectionId=$("#hsectionId").val();
	var date=$("#attendancedate").val();
	
	var period1=$("#period1").val();
	var period2=$("#period2").val();
	var period3=$("#period3").val();
	var period4=$("#period4").val();
	var period5=$("#period5").val();
	var period6=$("#period6").val();
	var period7=$("#period7").val();
	var period8=$("#period8").val();
	
	var datalist={
			
			"studentId":studentId,
			"classsId":classsId,
			"sectionId":sectionId,
			"date":date,
			"period1":period1,
			"period2":period2,
			"period3":period3,
			"period4":period4,
			"period5":period5,
			"period6":period6,
			"period7":period7,
			"period8":period8,
	};
		
	
	$.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=updateStudentPeriodAtt",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
		
			if(result.status=="true"){
				
				$('.successmessagediv').show();
				$('.successmessage').text("Period Attendance Saved Succesfully");
				
				setInterval(
						function() {
							
							var classId=$("#hclassId").val();
							var section=$("#hsectionId").val();
							var date=$("#attendancedate").val();
							
							window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date;
							
						}, 3000);
			
				
			}else{
				
				$('.errormessagediv').show();
				$('.validateTips').text("Period Attendance not Saved,Try later");
				
			}

		}
	});
});

$("#back").click(function(){
	window.location.href="teachermenuaction.html?method=attendaceStatus";
});
	
$('#excelDownload').click(function() {
			
			var startDate=$("#startdate").val();
			var endDate=$("#enddate").val();
			
			window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsXLS&startDate=" + startDate + "&endDate=" + endDate;
			
		});
$("#pdfDownload").click(function(){
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	
	window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsPDF&startDate=" + startDate + "&endDate=" + endDate;
		
});

$("#section").change(function(){
	getStudentNameList();
});

});



function HideError() 
{
	
document.getElementById("date").style.border = "1px solid #ccc";
document.getElementById("date").style.backgroundColor = "#fff";

document.getElementById("classId").style.border = "1px solid #ccc";
document.getElementById("classId").style.backgroundColor = "#fff";

document.getElementById("section").style.border = "1px solid #ccc";
document.getElementById("section").style.backgroundColor = "#fff";

}


function getTeacherName(){
	var classId=$("#classId").val();
	var sectionId=$("#section").val()

	datalist ={
		"classId":classId,
		"sectionId":sectionId
	};

	$.ajax({
		
		type : "POST",
		url : "StudentAttendanceActionPath.html?method=getteacherByClass",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			
			$("#teacher").val(result.teacherName[0].teacherName);
			$("#teacherid").val(result.teacherName[0].teacherID);
		}
	});
}

function s(){
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var spec = $("#specialization").val();
	datalist ={
			"classId":classId,
			"section":section,
			"date":date,
			"spec":spec,
	};

	$.ajax({
		type : "POST",
		url : "StudentAttendanceActionPath.html?method=getStudentAttendanceList",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			$('#studentname').empty();
			$('#studentname').append('<option value="all">' + "-------------Select-------------"	+ '</option>');
			for(var i=0;i<result.attendanceList.length;i++){
				$('#studentname').append('<option value="'+ result.attendanceList[i].studentid + '">'	+ result.attendanceList[i].studentname+ '</option>');
			}
		}
	});		
}

function getAttendenceStudentList(){

	var classId=$("#hclass").val();
	var section=$("#hsection").val();
	var date=$("#hdate").val();
	var spec = $("#hiddenspec").val();
	var locid = $("#hlocationId").val();
	var acyId = $("#hiddenAcyearid").val();
	
	
	datalist ={
			"classId":classId,
			"section":section,
			"date":date,
			"spec":spec,
			"acyId":acyId,
			"locid" :locid
	};

	$.ajax({

		type : "POST",
		url : "StudentAttendanceActionPath.html?method=modifyAttendance",
		data : datalist,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			$('#divIdList').show();
			$("#allstudent thead").empty();
			$("#allstudent thead").append("<tr>"
					+"<th style='min-width: 45px;height: 35px;'>Sl No</th>"
					+"<th style='min-width: 130px;text-align:center;'>Admission No.</th>"
					+"<th style='min-width: 170px;text-align:center;'>Student Name</th>"
					+"<th style='min-width: 100px;text-align:center;'>Atten.Status</th>"
					+"</tr>");
			   
			for(var k=1;k<=Number(getperiodcount());k++){
				$("#allstudent thead tr").append("<th style='min-width: 100px;text-align:center;'>Period "+k+"</th>");
			}
			
			if(result.attendancelist.length>0){
				
				$("#attenid").val(result.attendancelist[0].attid);
				
				$('#allstudent tbody').empty();
				for(var i=0;i<result.attendancelist.length;i++){
					$('#allstudent tbody').append("<tr class='"+result.attendancelist[i].studentid+"' id='"+result.attendancelist[i].satusid+"'>" 
							+"<td style='max-width: 45px;min-width: 45px;'>"+result.attendancelist[i].count+"</td>"
							+"<td style='max-width: 130px;min-width: 130px;'>"+result.attendancelist[i].addmissionNo+"</td>"
							+"<td style='max-width: 170px;min-width: 170px;'>"+result.attendancelist[i].studentname+"</td>"
							+"<td style='max-width: 100px;min-width: 100px;'><select style='width:95%;' name='status' class='form-control finalst' id='"+result.attendancelist[i].studentid+"status'><option value='Present'>Present</option><option value='Absent'>Absent</option><option value='Leave'>Leave</option></select></td>"
							+"</tr>");
					
					for(var k=1;k<=Number(getperiodcount());k++){
						$('#allstudent tbody tr.'+result.attendancelist[i].studentid).append("<td style='max-width: 100px;min-width: 100px;'><select name='period"+k+"' class='form-control statusclass' id='"+result.attendancelist[i].studentid+"period"+k+"'><option value='Present'>Present</option><option value='Absent'>Absent</option><option value='Leave'>Leave</option></select></td>");
					}
				}
			
				
				$('#allstudent tbody tr').each(function(j){
					
					periods = result.attendancelist[j].attendancestatus;
					$(this).find('.finalst').val(result.attendancelist[j].status)
					
					$($(this).find('.statusclass')).each(function(k){
						$(this).val(periods.split(",")[k]);
					});
				});
				
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='13'>NO Records Found</td></tr>");
			}

		}
	});
}

function getperiodcount(){
	
	var noofperiod=0;
	 var locId=$("#locationname").val();
	 var clsId=$("#classId").val();

		$.ajax({
				type : 'post',
				url : 'StudentAttendanceActionPath.html?method=getperiodcount',
				data : {
					"locId" : locId ,
					"clsId" : clsId,
				},
				async : false,
				success : function(data) {	
					var result = $.parseJSON(data);
					noofperiod=result.noofperiod;
				   $("#noofperiod").val(result.noofperiod);
				      
				
				}

			});
		return noofperiod;
}

$(document).ready(function() {
	
	
	$("#locationname").val($("#hiddenlocId").val());
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	 },
	});
	getClassList();
	
	$("#Acyearid").val($("#hiddenAcademicYear").val());
	  dateRange();
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var sectionid=$("#section").val();
	var classname=$("#classId").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
	getClassList();
	
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#section").val();
		getClassList();
		var classname=$("#classId").val();
		getSectionList(classname);
		getSpecilization(classname);
		
		
		if(locationid == '' || locationid == 'all'){
			$("#Acyearid option[value='all']").attr('selected', 'true');
			$("#classId option[value='all']").attr('selected', 'true');
			$("#section option[value='all']").attr('selected', 'true');
			$("#specialization option[value='']").attr('selected', 'true');
		}
		getStudentListBySection(locationid,accyear,classname,sectionid);
		
	});
	
	$(".buttonstyle").click(function(){
		var idString =$( this ).attr( "id" );
		 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString+
		 "&Acyearid="+Acyearid+"&locId="+locationid+"&classname="+classname+"&sectionid="+sectionid+
		 "&specId="+$("#specialization").val()+"&startdate="+$("#startdate").val()+"&enddate="+$("#enddate").val();
	});
	
	$("#Acyearid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#section").val();
		
		dateRange();
		$("#startdate").datepicker('destroy');
		$("#enddate").datepicker('destroy');
		var accyear=$("#Acyearid").val();
		
		if(accyear == '' || accyear == 'all'){
			getClassList();
			var classname=$("#classId").val();
			getSectionList(classname);
			getSpecilization(classname);L
			$("#classId option[value='All']").attr('selected', 'true');
			$("#section option[value='all']").attr('selected', 'true');
		}else{
			getClassList();
			var classname=$("#classId").val();
			getSectionList(classname);
			getSpecilization(classname);			
		}
		$('#divIdList').hide();
		$('#teacher').val("");
		
		$("#startdate").datepicker({
			dateFormat : "dd-mm-yy",
			minDate:minDate-1,
			maxDate : maxDate,
			changeMonth : "true",
			changeYear : "true",
			buttonImage : "images/calendar.GIF",
			buttonImageOnly : true
		});
		
		$("#enddate").datepicker({
			dateFormat : "dd-mm-yy",
			minDate:minDate-1,
			maxDate : maxDate,
			changeMonth : "true",
			changeYear : "true",
			buttonImage : "images/calendar.GIF",
			buttonImageOnly : true
		});
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});

	$("#classId").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
		if($(this).val() == "all"){
			$("#section").html("");
			$("#section").append(
					'<option value="all">ALL</option>');
		}else{
			getSectionList(classname);
		}
		getStudentListBySection(locationid,accyear,classname,sectionid);
		getSpecilization(classname);
		$("#section option[value='all']").attr('selected', 'true');
	});
	
	$("#section").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
	var flag = false;
	var dd =new Date();
	var day=dd.getDate();
	if(day < 10){
		day = "0" + day;
	}
	var month = dd.getMonth()+1;

	if(month < 10){
		month = "0" + month;
	}
	var year = dd.getFullYear();
	
	var date = day +"-"+(month)+"-"+year;
	$("#date").val(date);
	
	var classId=$("#classId").val();
	var section=$("#section").val();
	var date=$("#date").val();
	var teacher = $("#teacher").val();
	
	
	
	$("#date").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true
		
	});
	
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate:minDate-1,
		maxDate : maxDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
		    $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val());
		    var locationid=$("#locationname").val();
			var accyear=$("#Acyearid").val();
			var classname=$("#classId").val();
			var sectionid=$("#section").val();
		    getStudentListBySection(locationid,accyear,classname,sectionid)
		}
	});
	
	$("#startdate").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
	    getStudentListBySection(locationid,accyear,classname,sectionid)
	});
	
	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate:minDate-1,
		maxDate : maxDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
	        var locationid=$("#locationname").val();
			var accyear=$("#Acyearid").val();
			var classname=$("#classId").val();
			var sectionid=$("#section").val();
			
	        getStudentListBySection(locationid,accyear,classname,sectionid)
		}
	});
	$("#enddate").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
	    getStudentListBySection(locationid,accyear,classname,sectionid)
	});
	 Acyearid=$("#Acyearid").val();
	
	
	$("#specialization").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classId").val();
		var sectionid=$("#section").val();
		
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});

	$("#search").click(function(){
		
		flag = true;
		var classId=$("#classId").val();
		var section=$("#section").val();
		var date=$("#date").val();
		var teacher = $("#teacher").val();
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
			window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date+"&spec="+spec+"&teacher="+teacher;
		}
	});


	
	$("#saveAttendance").click(function(){
	
		var date =$("#date").val();
		var location=$('#locationname').val();
		var teacherid=$('#teacherid').val();

		var teacherIdArray=[];
		var statusArray=[];
		var period1=[];
		var period2=[];
		var period3=[];
		var period4=[];
		var period5=[];
		var period6=[];
		var period7=[];
		var period8=[];
		var period9=[];
		
		
		$('#allstudent tbody tr').each(function(){
			var studentID=$(this).attr("class");
			var status = $(this).find('.statusclass').val();
			var p1 = $(this).find("select[name='period1']").val();
			var p2 = $(this).find("select[name='period2']").val();
			var p3 = $(this).find("select[name='period3']").val();
			var p4 = $(this).find("select[name='period4']").val();
			var p5 = $(this).find("select[name='period5']").val();
			var p6 = $(this).find("select[name='period6']").val();
			var p7 = $(this).find("select[name='period7']").val();
			var p8 = $(this).find("select[name='period8']").val();
			var p9 = $(this).find("select[name='period9']").val();

			
			teacherIdArray.push(studentID.trim());
			
			if(status!=undefined && status!=""){
				
				statusArray.push(status);
			}
			
			if(p1!=undefined && p1!=""){
				period1.push(p1);
			}
			
			if(p2!=undefined && p2!=""){
				period2.push(p2);
			}
			
			if(p3!=undefined && p3!=""){
				period3.push(p3);
			}
			
			if(p4!=undefined && p4!=""){
				period4.push(p4);
			}
			
			if(p5!=undefined && p5!=""){
				period5.push(p5);
			}
			
			if(p6!=undefined && p6!=""){
				period6.push(p6);
			}
			
			if(p7!=undefined && p7!=""){
				period7.push(p7);
			}
			
			if(p8!=undefined && p8!=""){
				period8.push(p8);
			}
			
			if(p9!=undefined && p9!=""){
				period9.push(p9);
			}
		});
		
		
		var datalist={
				
				"date" : date,
				"teacherid":teacherid,
				"locationId":location,
				"teacherIdArray" : teacherIdArray.join(),
				"statusArray" : statusArray.join(),
				"period1":period1.join(),
				"period2":period2.join(),
				"period3":period3.join(),
				"period4":period4.join(),
				"period5":period5.join(),
				"period6":period6.join(),
				"period7":period7.join(),
				"period8":period8.join(),
				"period9":period9.join()
		};
		$.ajax({
			type : "POST",
			url : "StudentAttendanceActionPath.html?method=updateAttendanceStatus",
			data :datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				if(result.status=="true"){
					
					$('.successmessagediv').show();
					$('.successmessage').text("Attendance updated successfully");
					
				setTimeout(function(){
					
					 window.location.href="teachermenuaction.html?method=attendaceStatus";
				 
				 },6000);
				
				}else{
					
					$('.errormessagediv').show();
					$('.validateTips').text("Attendance not updated,Try later");

				}
			
			}
		});
	});

	/*$.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		async:false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#classId").html("");
			$("#classId").append('<option value="' + "" + '">' + "-------------Select------------" + '</option>');
			for ( var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {
				$('#classId').append('<option value="'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsId
						+ '">'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName
						+ '</option>');

			}
		}
	});*/
	
	var hclassId=$("#hclass").val();
	
	if(hclassId!=undefined && hclassId!=""){
		
		$("#classId option[value='"+hclassId +"']").attr('selected', 'true');
		
		
		var classidVal = $("#classId").val();

		datalist = {
			"classidVal" : classidVal
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
						'<option value="all">' + "-------------Select------------" + '</option>');

				for ( var j = 0; j < result.sectionList.length; j++) {
					$("#section").append(
							'<option value="' + result.sectionList[j].sectioncode
									+ '">' + result.sectionList[j].sectionnaem
									+ '</option>');
				}

			}
		});
		
		$("#section option[value=" + $("#hsection").val().trim() + "]").attr('selected','true');
		$("#date").val($("#hdate").val().trim());
		$("#teacher").val($("#hteachername").val().trim());
		
		
	}
	

	$('#allstudent tr td').each(function(){
		
		
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
									+  statusList[j]+ '</option>');
		}
		
		$("#"+rowid+" option[value=" + status + "]").attr('selected', 'true');
		
		}
		
	});
	

$("#searchAttendanceList").click(function(){
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	
	window.location.href="teachermenuaction.html?method=attendaceStatus&startDate="+startDate+"&endDate="+endDate;	
	
});

$("#reset").click(function(){
	
	$("#classId").val("");
	$("#section").val("");
	$("#date").val("");
	$("#teacher").val("");
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


$("#iconsimg").click(function(){
	if($("#locationname").val()=="all"){
		showError("#locationname","Select Branch Name");
		return false;
	}
});

$("#back").click(function(){
	
	var classId=$("#hclassId").val();
	var section=$("#hsectionId").val();
	var date=$("#attendancedate").val();
	
	window.location.href="StudentAttendanceActionPath.html?method=getStudentAttendanceDetails&classId="+classId+"&section="+section+"&date="+date;
});
	
$('#excelDownload').click(function() {
			
			var startDate=$("#startdate").val();
			var endDate=$("#enddate").val();
			
			window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsXLS&startDate=" + startDate + 
			"&endDate=" + endDate+"&locname="+$("#locationname option:selected").text()+"&Acyearid="+$("#Acyearid").val()+"&classId="+$("#classId").val()+"&section="+$("#section").val()+"&locationname="+$("#locationname").val();
			
		});
$("#pdfDownload").click(function(){
	
	
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
	var locId=$("#locationname").val(); 
	
	window.location.href = "StudentAttendanceActionPath.html?method=downloadStudentAttendanceDetailsPDF&startDate=" + startDate + 
	"&endDate=" + endDate+"&locname="+$("#locationname option:selected").text()+"&Acyearid="+$("#Acyearid").val()+"&classId="+$("#classId").val()+"&section="+$("#section").val()+"&locationname="+$("#locationname").val();
		
});

var hclassId=$("#hclass").val();

if(hclassId!=undefined && hclassId!=""){
	
	$("#classId option[value='"+hclassId +"']").attr('selected', 'true');
	
	var classidVal = $("#classId").val();
	var locationId=$("#locationname").val();
    
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
					'<option value="all">' + "-------------Select------------" + '</option>');

			for ( var j = 0; j < result.specList.length; j++) {
				$("#specialization").append(
						'<option value="' + result.specList[j].specId
								+ '">' + result.specList[j].specName
								+ '</option>');
			}
			
		}
		});
	$("#specialization option[value=" + $("#hspec").val().trim() + "]").attr('selected','true');
}

if($("#historylocId").val()!="" || $("#historyacademicId").val()!="" || $("#historystartdate").val()!="" || $("#historyenddate").val()!="")
{
	$("#Acyearid").val($("#historyacademicId").val());
	$("#locationname").val($("#historylocId").val()); 
	
	getClassList();
	$("#classId").val($("#historyclassname").val()); 
	
	getSectionList($("#classId").val());
	$("#section").val($("#historysectionid").val());
	
	getSpecilization($("#classId").val());
	$("#specialization").val($("#historyspecId").val());
	
	$("#startdate").val($("#historystartdate").val());
	$("#enddate").val($("#historyenddate").val());
	
	getStudentListBySection($("#locationname").val(),$("#Acyearid").val(),$("#classId").val(),$("#section").val());
 }

});

function getClassList(){
	var locationid=$("#locationname").val();
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

			$('#classId').html("");

			$('#classId').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classId').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function clickHideError(pointer) 
{
	
document.getElementById(pointer.id).style.border = "1px solid #ccc";
document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function getSectionList(classidVal){
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
					'<option value="all">ALL</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#section").append(
						'<option value="' + result.sectionList[j].sectioncode
								+ '">' + result.sectionList[j].sectionnaem
								+ '</option>');
			}

		}
	});

}

function getSpecilization(classidVal){
	datalist = {
		"classidVal" : classidVal,
		"locationId":$("#locationname").val()
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
				'<option value="">' + "-------------Select------------" + '</option>');

		for ( var j = 0; j < result.specList.length; j++) {
			$("#specialization").append(
					'<option value="' + result.specList[j].specId
							+ '">' + result.specList[j].specName
							+ '</option>');
		}
		
	}
	});
}


function getStudentListBySection(locationid,accyear,classname,sectionid){
	var startdate=$('#startdate').val();
	var enddate=$('#enddate').val();
	 
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"specialization":$("#specialization").val(),
			"startdate":startdate,
			"enddate":enddate,

	}, $.ajax({
		type : 'POST',
		url : "StudentAttendanceActionPath.html?method=getAttendenceByClassSectionList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var len=result.SearchAttendanceList.length;
			var i=0;
			
			if(len > 0){
			for( i=0;i< len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+(i+1)+"</td>" 
						+"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.SearchAttendanceList[i].classId+","+result.SearchAttendanceList[i].sectionId+","+result.SearchAttendanceList[i].date+","+result.SearchAttendanceList[i].specId+","+result.SearchAttendanceList[i].locationId+","+result.SearchAttendanceList[i].accYear+"' value='"+result.SearchAttendanceList[i].date+"'></a></td>"
						+"<td> "+result.SearchAttendanceList[i].classsection+"</td>"
						+"<td> "+result.SearchAttendanceList[i].specName+" </td>"
						+"<td> "+result.SearchAttendanceList[i].tot_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].present_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].absent_count+" </td>"
						+"<td> "+result.SearchAttendanceList[i].leave_count+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='10'>NO Records Found</td></tr>");
			}
			$("#loder").hide();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
			
			$(".buttonstyle").click(function(){
				var idString =$( this ).attr( "id" );
				 window.location.href="StudentAttendanceActionPath.html?method=editAttendance&idString="+idString+"&Acyearid="+Acyearid
				 +"&locId="+locationid+"&classname="+classname+"&sectionid="+sectionid+
				 "&specId="+$("#specialization").val()+"&startdate="+$("#startdate").val()+"&enddate="+$("#enddate").val();;
			});
		}
	});
}

function dateRange(){
	var accyear=$('#Acyearid').val();
	$.ajax({
		type:"POST", 
		url:"StudentAttendanceActionPath.html?method=daterange",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			minDate=result.startDate.split(",")[0];
			maxDate=result.startDate.split(",")[1];
			
		}
	
	});
}
function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv1").show();
	$(".validateTips1").text(errorMessage);
	$(".errormessagediv1").delay(3000).fadeOut();
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}





$(document).ready(function() {
$("#accyearp").val($("#haccYear").val());

	$("#add").click(function(){
		window.location.href="staffattendancepath.html?method=staffattendaceUpload&historylocId="+$("#dlocation").val()+
		"&historyacademicId="+$("#accyearp").val()+"&historystartdate="+$("#startdate").val()+
		"&historyenddate="+$("#enddate").val()+"&historystatus=add";
	});
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=getStaffAttendance&historylocId="+$("#historylocId").val()+
		"&historyacademicId="+$("#historyacademicId").val()+"&historystartdate="+$("#historystartdate").val()+
		"&historyenddate="+$("#historyenddate").val()+"&historyback="+$("historyback").val();
	});

$("#iconsimg").click(function(){
	if($("#dlocation").val()=="All"){
		 $(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name");
			document.getElementById("dlocation").style.border = "1px solid #AF2C2C";
			document.getElementById("dlocation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				document.getElementById("dlocation").style.border = "1px solid #ccc";
				document.getElementById("dlocation").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false
	
	}
});

	if($("#tatt").val()!=""){
		$('.errormessagediv1').show();
		$('.validateTips1').text($("#tatt").val());
		setTimeout(function(){
			$('.errormessagediv1').hide();
			 window.location.href="menuslist.html?method=getStaffAttendance";
		 },3000);
	}
	$("#department").change(function(){
		searchStaffAttendaceUpload();
	});
	
	if($("#haccYear").val()!=undefined){
		dateManagement();
	}
	$("#accyearp").change(function(){
		dateManagement();
		getAttendanceDetail();
	});
	
	if($("#historyacademicId").val()!="" && $("#historyacademicId").val()!=undefined
			&& $("#historyacademicId").val()!=null && $("#historystatus").val()=="add"){
		searchStaffAttendaceUpload();
	}else{
		getAttendanceDetail();
	}
	
	
	
	$("#dlocation").change(function(){
		if($("#dlocation").val()!="All"){
			getAttendanceDetail();
		}
	});	
	
	
		if($("#allstudent tbody tr").length ==0){
						//$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
			$(".pagebanner").hide();
			$(".pagelinks").hide();
					}
					$(".errormessagediv").hide();
					$("#date").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						buttonImage : "images/calendar.GIF",
						buttonImageOnly : true,
						onClose: function(selectedDate){
							if ((selectedDate != "")&& (selectedDate != undefined)) {
							searchStaffAttendaceUpload();
							}
						}
					});
					
					var hdeptId=$("#hdeptId").val();
					if(hdeptId!=null && hdeptId!=undefined){
					$("#department option[value='"+hdeptId.trim()+"']").attr('selected', 'true');
					}
					statusDropDown();
					$("#save2").click(function(){
						var date =$("#date").val();
						var teacherIdArray=[];
						var statusArray=[];
						$('#allstudent tr').each(function(){
							var teacherID=$(this).find('.hiddenclass').text();
							var status=$(this).find('.statusclass').val();
							if(teacherID!="TeacherId"){
								teacherIdArray.push(teacherID.trim());
							}
							if(status!=undefined && status!=""){
								statusArray.push(status);
							}
						});
						var datalist={
								"date" : date,
								"teacherIdArray" : teacherIdArray.join(),
								"statusArray" : statusArray.join()
								
						};
						$.ajax({
							type : "POST",
							url : "staffattendancepath.html?method=updateAttendanceStatus",
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
									 window.location.href="menuslist.html?method=getStaffAttendance";
								 },3000);
								
								}else{
									$("#loder").hide();
									$('.errormessagediv').show();
									$('.validateTips').text("Attendance not updated,Try later");
								}
							
							}
						});
					});
$("#searchAttendanceList").click(function(){
	getAttendanceDetail();
	//window.location.href="menuslist.html?method=getStaffAttendance&startDate="+startDate+"&endDate="+endDate;	
});
$("#search").click(function(){
	var attDate=$("#date").val();
	var department=$("#department").val();
	searchStaffAttendaceUpload();
	//window.location.href="staffattendancepath.html?method=searchStaffAttendaceUpload&attDate="+attDate+"&department="+department;	
});
	$('#excelDownload').click(function() {
		var locationId=$("#dlocation").val();
		var accYear=$("#accyearp").val();
		var startDate=$("#startdate").val();
		var endDate=$("#enddate").val();
			window.location.href = "staffattendancepath.html?method=downloadStaffAttendanceDetailsXLS&startDate="+startDate
			+"&endDate="+endDate+"&locationId="+locationId+"&accYear="+accYear;
			
		});
	$("#pdfDownload").click(function(){
				var locationId=$("#dlocation").val();
				var accYear=$("#accyearp").val();
				var startDate=$("#startdate").val();
				var endDate=$("#enddate").val();
				window.location.href = "staffattendancepath.html?method=downloadStaffAttendanceDetailsPDF&startDate="+startDate
				+"&endDate=" + endDate+"&locationId="+locationId+"&accYear="+accYear+"&branchName="+$("#dlocation option:selected").text();;
   		});
	
	if($("#historyback").val()!="historyback"){
		if($("#historylocId").val()!="" || $("#historyacademicId").val()!=""){
			
			if($("#historyacademicId").val()!=""){
				$("#accyearp").val($("#historyacademicId").val());
			}
			$("#dlocation").val($("#historylocId").val());
			$("#startdate").val($("#historystartdate").val());
			$("#enddate").val($("#historyenddate").val());
			getAttendanceDetail();
		}
	}
	
});
function submitAttendence() {
	document.location.href = "teacherAttendance.html?method=UploadAttendence";
}
function getAttendanceDetail(){
	var locationId=$("#dlocation").val();
	var accYear=$("#accyearp").val();
	var startDate=$("#startdate").val();
	var endDate=$("#enddate").val();
 
	datalist = {
			"startDate" :startDate,
			"endDate" :endDate,
			"locationId" :locationId,
			"accYear" :accYear,
	}, $.ajax({
		type : 'POST',
		url : "staffattendancepath.html?method=getStaffAttendanceSearch",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.staffAttendanceList.length>0){
				$(".pagebanner").show();
				$(".pagelinks").show();
				for(var i=0;i<result.staffAttendanceList.length;i++){
					$("#allstudent tbody").append('<tr>'
					+'<td>'+(i+1)+'</td>'
					+'<td>'+result.staffAttendanceList[i].date+'</td>'
					+'<td>'+result.staffAttendanceList[i].tot_count+'</td>'
					+'<td>'+result.staffAttendanceList[i].present_count+'</td>'
					+'<td>'+result.staffAttendanceList[i].absent_count+'</td>'
					+'<td>'+result.staffAttendanceList[i].holiday_count+'</td>'
					+'<td>'+result.staffAttendanceList[i].leave_count+'</td>'
					+'</tr>');
				}
				$(".numberOfItem").text("No. of Records "+$("#allstudent tbody tr").length);
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});	
			}
			else{
				$("#allstudent").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				$(".numberOfItem").text("No. of Records 0");
				
			}
		}
	});
}
function searchStaffAttendaceUpload(){
	var attDate=$("#date").val();
	var department=$("#department").val();
	var locId=$("#historylocId").val();
	datalist = {
			"attDate" :attDate,
			"department" :department,
			"locId":locId
	}, $.ajax({
		type : 'POST',
		url : "staffattendancepath.html?method=searchStaffAttendaceUpload",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.attendanceList.length>0){
				for(var i=0;i<result.attendanceList.length;i++){
					$("#allstudent tbody").append('<tr>'
					+'<td>'+(i+1)+'</td>'
					+'<td class="hiddenclass" headerClass="hiddenclass" >'+result.attendanceList[i].teacherId+'</td>'
					+'<td>'+result.attendanceList[i].regid+'</td>'
					+'<td>'+result.attendanceList[i].teacherName+'</td>'
					+'<td>'+result.attendanceList[i].designation+'</td>'
					+'<td>'+result.attendanceList[i].department+'</td>'
					+'<td><select name="status" class="form-control statusclass" id="'+result.attendanceList[i].teacherId+'status">'
					+'<option value="'+result.attendanceList[i].status+'">'+result.attendanceList[i].status+'</option>'
					+'</select></td>'
					+'</tr>');
				}
				$(".numberOfItem").text("No. of Records "+$("#allstudent tbody tr").length);
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});	
				statusDropDown();
			}
			else{
				$("#allstudent").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				$(".numberOfItem").text("No. of Records 0");
				
			}
		}
	});
	
}
function statusDropDown(){
	$('#allstudent tr').each(function(){
		var status=$(this).find('.statusclass').val();
		if(status!=undefined){
		var rowid=$(this).find('.statusclass').attr("id");
		$('#'+rowid).find('option').remove();
		var statusList=[];
		statusList.push("Present");
		statusList.push("Absent");
		statusList.push("Leave");
		statusList.push("Holiday");
		for (var j = 0; j < statusList.length; j++) {
			$("#"+rowid).append('<option value="'+ statusList[j]+ '">'+ statusList[j]+ '</option>');
		}
		$("#"+rowid+" option[value=" + status + "]").attr('selected', 'true');
		}
	});
}

function getAccYears(){
	var name;
	var accYear=$("#accyearp").val();
	datalist = {
			"accYear" :accYear,
	}, $.ajax({
		type : 'POST',
		url : "staffattendancepath.html?method=getAccyearNames",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			name=result.dates;
		}
		});
	return name;
}
function dateManagement(){
	$('#startdate').datepicker('destroy');
	$('#enddate').datepicker('destroy');
	var dates=getAccYears();
	var start=[];
	var start1=[];
	var end=[];
	var end1=[];
	start=dates.split(",")[0];
	end=dates.split(",")[1];
	start1=dates.split(",")[0].split("-");
	end=dates.split(",")[1].split("-");
	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate: new Date(start1[0], (start1[1]-1),start1[2]),
		maxDate: new Date(end[0],(end[1]-1),end[2]),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "")&& (selectedDate != undefined)) {
				var date2 = $('#startdate').datepicker('getDate');
				date2.setDate(date2.getDate() + 1);
				$("#enddate").datepicker("option","minDate", date2);
				if($("#dlocation").val()=="All"){
					$("#startdate").val("");
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required School Name");
					showError("#dlocation");
					setTimeout(function(){
						hideError("#dlocation");
						$('.errormessagediv').fadeOut();
						$('.errormessagediv').hide();
					 },3000);
				}else{
					getAttendanceDetail();
				}
				
			}
		}
	});
	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate: new Date(start1[0],(start1[1]-1),start1[2]),
		maxDate: new Date(end[0],(end[1]-1),end[2]),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "") && (selectedDate != undefined)) {
				var date2 = $('#enddate').datepicker('getDate');
				date2.setDate(date2.getDate() - 1);
				$("#startdate").datepicker("option", "maxDate", date2);
				if($("#dlocation").val()=="All"){
					$("#startdate").val("");
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required School Name");
					showError("#dlocation");
					setTimeout(function(){
						hideError("#dlocation");
						$('.errormessagediv').fadeOut();
						$('.errormessagediv').hide();
					 },3000);
				}else{
					getAttendanceDetail();
				}
			}
		}
	});
}
function showError(id){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
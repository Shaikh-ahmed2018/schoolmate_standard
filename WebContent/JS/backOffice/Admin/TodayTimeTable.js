$(document).ready(function() {
	var ClassId="ClassId";
	$("#allstudent tbody tr").each(function(){
		if($(this).find('.Day').html()=="Monday"){
			$(this).css("background-color", "#8bd5f8");
		}
		if($(this).find('.Day').html()=="Tuesday"){
			$(this).css("background-color", "#aff8b4");
		}
		if($(this).find('.Day').html()=="Wednesday"){
			$(this).css("background-color", "#dfa4ea");
		}
		if($(this).find('.Day').html()=="Thursday"){
			$(this).css("background-color", "#fbeca2");
		}
		if($(this).find('.Day').html()=="Friday"){
			$(this).css("background-color", "rgb(246, 186, 186)");
		}
		if($(this).find('.Day').html()=="Saturday"){
			$(this).css("background-color", "#79b5f4");
		}
	});
	
	$("select.teacher").each(function(){
		getTeacherList($(this),$(this).attr("name"));
	});
	

	var hviewBy=$("#hviewBy").val();
	

	$("#edit").click(function(){
		var count = 0;
		var TimeTableDetails = null;

		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			TimeTableDetails = this.value;
		});

		if (count > 1 || count == 0) {

			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");

		} else {
			window.location.href = "TimeTableActionPath.html?method=getTimeTableDetails&TimeTableDetails="
				+ TimeTableDetails;


			/*window.location.href = "TimeTableActionPath.html?method=getTeacherTimeTableDetails&TimeTableDetails="
					+ TimeTableDetails;*/
		}
	});


	var viewBy=$("#hviewBy").val();


	if(viewBy=='class'){



		$.ajax({
			type : 'POST',
			url : 'teacherregistration.html?method=getSubjects',
			data:{"classId":$("#hiddenClassId").val()},
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);

				for (var j = 0; j < data.SubjectList.length; j++) {

					$(".subject").append(
							'<option value="' + data.SubjectList[j].subjectid + '">'
							+ data.SubjectList[j].subjectname
							+ '</option>');
				}

			}

		});

	}else{

		$.ajax({
			type : 'POST',
			url : 'TimeTableActionPath.html?method=getClassSectionList',
			async : false,
			success : function(response) {

				var data = $.parseJSON(response);

				for (var j = 0; j < data.ClassSectionList.length; j++) {

					$(".subject")
					.append(
							'<option value="'
							+ data.ClassSectionList[j].classid
							+ '">'
							+ data.ClassSectionList[j].classname
							+ '</option>');



				}

			}

		});

	}


	$('#UpdateTimeTable').click(function() {
		var dayidArray = [];
		var period1Array = [];
		var period2Array = [];
		var period3Array = [];
		var period4Array = [];
		var period5Array = [];
		var period6Array = [];
		var period7Array = [];
		var period8Array = [];
		var period9Array = [];

		var tperiod1Array = [];
		var tperiod2Array = [];
		var tperiod3Array = [];
		var tperiod4Array = [];
		var tperiod5Array = [];
		var tperiod6Array = [];
		var tperiod7Array = [];
		var tperiod8Array = [];
		var tperiod9Array = [];

		$('table#allstudent tr:gt(0)').each(function() {

			var day = $(this).find('[name=dayid]').val().trim();
			var period1 = $(this).find('[name=period1]').val().trim();
			var period2 = $(this).find('[name=period2]').val().trim();
			var period3 = $(this).find('[name=period3]').val().trim();
			var period4 = $(this).find('[name=period4]').val().trim();
			var period5 = $(this).find('[name=period5]').val().trim();
			var period6 = $(this).find('[name=period6]').val().trim();
			var period7 = $(this).find('[name=period7]').val().trim();
			var period8 = $(this).find('[name=period8]').val().trim();
			var period9 = $(this).find('[name=period9]').val().trim();
			
			var tperiod1 = $(this).find('[name=tperiod1]').val().trim();
			var tperiod2 = $(this).find('[name=tperiod2]').val().trim();
			var tperiod3 = $(this).find('[name=tperiod3]').val().trim();
			var tperiod4 = $(this).find('[name=tperiod4]').val().trim();
			var tperiod5 = $(this).find('[name=tperiod5]').val().trim();
			var tperiod6 = $(this).find('[name=tperiod6]').val().trim();
			var tperiod7 = $(this).find('[name=tperiod7]').val().trim();
			var tperiod8 = $(this).find('[name=tperiod8]').val().trim();
			var tperiod9 = $(this).find('[name=tperiod9]').val().trim();

			if (day == '') {
				dayidArray.push('-');
			} else {
				dayidArray.push(day);
			}

			if (period1 == '') {
				period1Array.push('-');
			} else {
				period1Array.push(period1);
			}

			if (period2 == '') {
				period2Array.push('-');
			} else {
				period2Array.push(period2);
			}

			if (period3 == '') {
				period3Array.push('-');
			} else {
				period3Array.push(period3);
			}

			if (period4 == '') {
				period4Array.push('-');
			} else {
				period4Array.push(period4);
			}

			if (period5 == '') {
				period5Array.push('-');
			} else {
				period5Array.push(period5);
			}

			if (period6 == '') {
				period6Array.push('-');
			} else {
				period6Array.push(period6);
			}

			if (period7 == '') {
				period7Array.push('-');
			} else {
				period7Array.push(period7);
			}

			if (period8 == '') {
				period8Array.push('-');
			} else {
				period8Array.push(period8);
			}


			if (period9 == '') {
				period9Array.push('-');
			} else {
				period9Array.push(period9);
			}
			
			
			if (tperiod1 == '') {
				tperiod1Array.push('-');
			} else {
				tperiod1Array.push(tperiod1);
			}

			if (tperiod2 == '') {
				tperiod2Array.push('-');
			} else {
				tperiod2Array.push(tperiod2);
			}

			if (tperiod3 == '') {
				tperiod3Array.push('-');
			} else {
				tperiod3Array.push(tperiod3);
			}

			if (tperiod4 == '') {
				tperiod4Array.push('-');
			} else {
				tperiod4Array.push(tperiod4);
			}

			if (tperiod5 == '') {
				tperiod5Array.push('-');
			} else {
				tperiod5Array.push(tperiod5);
			}

			if (tperiod6 == '') {
				tperiod6Array.push('-');
			} else {
				tperiod6Array.push(tperiod6);
			}

			if (tperiod7 == '') {
				tperiod7Array.push('-');
			} else {
				tperiod7Array.push(tperiod7);
			}

			if (tperiod8 == '') {
				tperiod8Array.push('-');
			} else {
				tperiod8Array.push(tperiod8);
			}


			if (tperiod9 == '') {
				tperiod9Array.push('-');
			} else {
				tperiod9Array.push(tperiod9);
			}
			
		});
		var ClassId = $("#hiddenClass").val();
		var SectionId= $("#hiddenSection").val();
		var timetableID=$("#timetableId").val();

		var className=$("#classid").val().trim();
		
		var jsonObject = {
				'period1Array' : period1Array.join(","),
				'period2Array' : period2Array.join(","),
				'period3Array' : period3Array.join(","),
				'period4Array' : period4Array.join(","),
				'period5Array' : period5Array.join(","),
				'period6Array' : period6Array.join(","),
				'period7Array' : period7Array.join(","),
				'period8Array' : period8Array.join(","),
				'period9Array' : period9Array.join(","),
				
				'tperiod1Array' : tperiod1Array.join(","),
				'tperiod2Array' : tperiod2Array.join(","),
				'tperiod3Array' : tperiod3Array.join(","),
				'tperiod4Array' : tperiod4Array.join(","),
				'tperiod5Array' : tperiod5Array.join(","),
				'tperiod6Array' : tperiod6Array.join(","),
				'tperiod7Array' : tperiod7Array.join(","),
				'tperiod8Array' : tperiod8Array.join(","),
				'tperiod9Array' : tperiod9Array.join(","),
				
				'timetableID' : timetableID,
				'ClassId' : ClassId,
				'SectionId' : SectionId,
				'viewBy' : viewBy,
				'dayidArray' : dayidArray.join(",")
		};
		showPopup(jsonObject);
		
	});
	
	
	$("#excelDownload").click(function(){
			var viewBy=$("#hviewBy").val().trim();
			window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadXLS&viewBy="+viewBy;
		
			
	});

	
	
	
	$("#pdfDownload").click(function()
			{
		var viewBy=$("#hviewBy").val().trim();
		window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadPDF&viewBy="+viewBy;
	});

	/*$("#pdfDownload").click(function(){
		var viewBy=$("#classname").val().trim();

		if(viewBy=="class")
		{
			var viewBy=$("#classname").val().trim();
			window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadPDF&viewBy="+viewBy;
		}
		else
		{
			var viewBy=$("#classname").val().trim();
			window.location.href = "TimeTableActionPath.html?method=teacherTimeTableDownloadPDF&viewBy="+viewBy;
		}
	});*/
	
	
/*	$("#pdfDownload").click(function()
			{
			var searchTerm = $("#searchexamid").val().trim();
			window.location.href = "careerview.html?method=InternaljobPDFReport&searchTerm="+ searchTerm;
			});
	*/
	

	
	
	
	$('#ClassId').change(function() {
		getClassSection($('#ClassId').val());
		getTimeTableList($('#ClassId').val());
	});
	
	$('#sectionid').change(function() {
		if($('#sectionid').val() == "All"){
			getTimeTableList($('#ClassId').val());
		}else{
			getTimeTableListBySection($('#ClassId').val(),$('#sectionid').val());
		}
		
	});
});

function showPopup(jsonObject){
	$("#dialog").dialog({
		 autoOpen: true,
	     modal: true,
	     position: {my: "center middle",
            at: "center middle",
           },
	     title:'Save Time Table Details',
	    
	     buttons : {
	    	 "Yes" : function() {
	    		 $.ajax({
	    			 method : 'POST',
	    			 url : 'TimeTableActionPath.html?method=updateTodayTimeTableDetails',
	    			 data : jsonObject,
	    			 async : false,
	    			 success : function(data) {
	    				 var result = $.parseJSON(data);

	    				 if (result.timetable_Result == "success") {

	    					 /*$('.successmessagediv').show();*/
	    					 $(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
	    					 $('.successmessage').text("Update Time table Details Progressing...");

	    					 setInterval(function() {

	    						 window.location.href = "menuslist.html?method=gettodaytimetablelist";

	    					 }, 3000);
	    				 } else {
	    					 $('.successmessagediv').show();
	    					 $('.successmessage').text("TimeTable Details not Saved, Please try again");
	    				 }	
	    			 }
	    		 });
	    		 $(this).dialog("close");
	    	 },
	    	 "No" : function() {
	    		 $(this).dialog("close");
	    	 }
	     }
	});
}

function getClasses(classId) {
	
	var ClassId = "#" + classId;
	var location =$("#SchoolID").val();
	
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getClassDetailWithOutStream",
		data:{"location":location},
		
		success : function(response) {
			$(ClassId).html("");
			$(ClassId).append('<option value="ALL">' + "ALL" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.ClassList.length; j++) {

				$(ClassId).append(
						'<option value="' + result.ClassList[j].classcode
						+ '">' + result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}

function getTimeTableList(classId) {
	datalist = {
		"classId" : classId,
		"locationId":$("#SchoolID").val(),
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=gettimetablelist",
		data : datalist,
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				
				var len=result.ClassTimeTableList.length;
				if(len>0){
				for(var i=0;i<result.ClassTimeTableList.length;i++){
				$("#allstudent tbody").append("<tr><td><input type='checkbox' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
						+"<td>"+result.ClassTimeTableList[i].classname+"</td>" 
						+"<td> "+result.ClassTimeTableList[i].sectionname+" </td>"
						+"<td> "+result.ClassTimeTableList[i].teachername+"</td>"
						+"<td> "+result.ClassTimeTableList[i].timetableStatus+" </td>"
						+"<td> "+result.ClassTimeTableList[i].createdby+" </td>"
						+"<td> "+result.ClassTimeTableList[i].createddate+" </td>"
						+"<td> "+result.ClassTimeTableList[i].lastupdatedby+" </td>"
						+"<td> "+result.ClassTimeTableList[i].lastupdated+"</td>"
						+"</tr>");
				  }	
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
		}
	});

}

function getClassSection(sectionid) {
	datalist = {
		"classidVal" : sectionid
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
			$("#sectionid").html("");
			$("#sectionid").append('<option value= "All">' + "--Select--" + '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$("#sectionid").append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}
function getTimeTableListBySection(classId,sectionId){
	datalist = {
			"classId" : classId,
			"sectionId": sectionId,
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=gettimetablelistbysection",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
			
					$("#allstudent tbody").empty();
					var len=result.ClassTimeTableList.length;
					if(len>0){
					
					for(var i=0;i<len;i++){
					$("#allstudent tbody").append("<tr><td><input type='checkbox' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
							+"<td>"+result.ClassTimeTableList[i].classname+"</td>" 
							+"<td> "+result.ClassTimeTableList[i].sectionname+" </td>"
							+"<td> "+result.ClassTimeTableList[i].teachername+"</td>"
							+"<td> "+result.ClassTimeTableList[i].timetableStatus+" </td>"
							+"<td> "+result.ClassTimeTableList[i].createdby+" </td>"
							+"<td> "+result.ClassTimeTableList[i].createddate+" </td>"
							+"<td> "+result.ClassTimeTableList[i].lastupdatedby+" </td>"
							+"<td> "+result.ClassTimeTableList[i].lastupdated+"</td>"
							+"</tr>");
					  }	
					}else{
						$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+len);
			}
		});

}

function getTeacherList(pointer,data){
	var timetableId=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
	
	 $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getTodayTeacherList",
			data:{'tperiod':data,
					'timetableId':timetableId
			},
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
			
					$("select.teacher").empty();
					$("select.teacher").append("<option value=''>-----Select----</option>");
					var len=result.teacherList.length;
					if(len>0){
					
					for(var i=0;i<len;i++){
					$("select.teacher").append("<option value='"+result.teacherList[i].teacherId+"'>"+result.teacherList[i].teacherName+"</option>");
					  }	
					}
					$("select.teacher").each(function(){
						$(this).val($(this).next(".tperiod").val());
					});	
			}
		});

}




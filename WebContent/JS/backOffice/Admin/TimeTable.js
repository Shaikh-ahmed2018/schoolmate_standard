$(document).ready(function() {
	
	$("#timetable").append("<thead><tr>" +
			"<th>Day</th>" +
			"</tr></thead>");
	$("#timetable").append("<tbody><tr class='DAY1'><td>Monday</td></tr>" +
			"<tr class='DAY2'><td>Tuesday</td></tr>" +
			"<tr class='DAY3'><td>Wednesday</td></tr>" +
			"<tr class='DAY4'><td>Thursday</td></tr>" +
			"<tr class='DAY5'><td>Friday</td></tr>" +
			"<tr class='DAY6'><td>Saturday</td></tr>" +
	"</tbody>");
	for(var i=0;i<getperiodcount();i++){
		$("#timetable thead tr").append("<th>Period "+(i+1)+"</th>");
		$("#timetable tbody tr").append("<td class='period"+(i+1)+"'><select class='form-control subject' name='period"+(i+1)+"'><option value='-'>---Subject---</option></select><select class='form-control teacher' name='tperiod"+(i+1)+"'><option value='-'>---Teacher---</option></select></td>");
	}
	$(".subject").change(function(){
		getTeacherList($(this).val(),$(this));
	});
	$('.teacher').change(function(){
		var dayid=$(this).closest('tr').attr("class");
		var teacherId=$(this).val();
		var hteacher=$("input[name='"+$(this).closest("tr").attr("class")+"-"+$(this).closest("td").attr("class")+"']").val().split("-")[0];
		var period=$(this).closest('td').attr('class');
		var flag=$(this);
		if(hteacher!=teacherId&&teacherId!=="-"){
			checkTeacher(dayid,teacherId,period,flag);
		}
	});
	
	$("#iconsimg").click(function(){
		if($("#location").val()=="all"){
			 $(".errormessagediv").show();
				$(".validateTips").text("Field Required School Name");
				document.getElementById("location").style.border = "1px solid #AF2C2C";
				document.getElementById("location").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					document.getElementById("location").style.border = "1px solid #ccc";
					document.getElementById("location").style.backgroundColor = "#fff";
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false
		
		}
	});
	$("#accyear").val($("#haccyear").val());
	$("#accyear").change(function(){
		getTimeTableList();
	});
	
	var ClassId="ClassId";
	
	getClasses();
	getTimeTableList();
	$("#location").change(function(){
		getClasses();
		getClassSection();
		getTimeTableList();
		 
	});
	
	$("#classname").change(function(){
		var viewBy=$("#classname").val().trim();
		window.location.href = "menuslist.html?method=gettimetable&viewBy="+viewBy;

	});

	var hviewBy=$("#hviewBy").val();
	if(hviewBy!=undefined && hviewBy!=''){

		$("#classname option[value=" +hviewBy+ "]").attr('selected', 'true');
	}

	$("#backpage").click(function(){
		window.location.href ="menuslist.html?method=gettimetable&historylocId="+$("#historylocId").val()+
		"&historyacademicId="+$("#historyacademicId").val()+"&historyclass="+$("#historyclass").val()
		+"&historysection="+$("#historysection").val()+"&editoperation="+$("#editoperation").val();
	});

	$("#edit").click(function(){
		var count = 0;
		var TimeTableDetails = null;
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			TimeTableDetails = this.value;
		});

		if (count > 1 || count == 0) {

			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");

		} else {
			window.location.href = "TimeTableActionPath.html?method=getTimeTableDetails&TimeTableDetails="+TimeTableDetails+
			"&locationId="+locationId+"&accyear="+accyear+"&class="+$("#class").val()+"&section="+$("#section").val();

			/*window.location.href = "TimeTableActionPath.html?method=getTeacherTimeTableDetails&TimeTableDetails="
					+ TimeTableDetails;*/
		}
	});


	var viewBy=$("#hviewBy").val();


	if(viewBy=='class'){


		$.ajax({
			type : 'POST',
			url : 'teacherregistration.html?method=getSubjects',
			data:{   "classId":$("#hiddenClassId").val(),
				     "locationId":$("#locationId").val()
			},
			 
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
		var periodArray=[];
		var tperiodArray=[];
		for(var i=1;i<=getperiodcount();i++){
			periodArray.push("period"+i+"Array");
			tperiodArray.push("tperiod"+i+"Array");
		}
		
		periodArrayString=""
		tperiodArrayString="";
		$('table#timetable tr:gt(0)').each(function() {

			var day = $(this).attr("class");
			dayidArray.push(day);
			for(var k=0;k<periodArray.length;k++){
				var period = $(this).find('[name=period'+(k+1)+']').val().trim();
				var tperiod = $(this).find('[name=tperiod'+(k+1)+']').val().trim();
				periodArrayString=periodArrayString+","+period;
				tperiodArrayString=tperiodArrayString+","+tperiod;
				
			}
			periodArrayString=periodArrayString+":";
			tperiodArrayString=tperiodArrayString+":";
		
		});
		var ClassId = $("#hiddenClassId").val();
		var SectionId= $("#hiddenSecId").val();
		var timetableID=$("#hiddenId").val();

		var className=$("#classid").val().trim();
		var accYear=$("#accyearId").val().trim();
		
		var jsonObject = {
				'periodArrayString' : periodArrayString,
				'tperiodArrayString' : tperiodArrayString,
				'timetableID' : timetableID,
				'ClassId' : ClassId,
				'SectionId' : SectionId,
				'viewBy' : viewBy,
				'dayidArray' : dayidArray.join(","),
				'locationId':$("#locationId").val(),
				'accYear':accYear
		};
		showPopup(jsonObject);
		
	});
	
	
	$("#excelDownload").click(function(){
		var classId =$("#class").val();
		var locationId=$("#location").val();
		var accyer=$("#accyear").val();
		var section=$("#section").val();
		var viewBy=$("#hviewBy").val().trim();
			window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadXLS&viewBy="+viewBy+"&classId="+classId+"&locationId="+locationId+"&accyer="+accyer+"&section="+section;
			editClick();
			
	});

	
	
	
	$("#pdfDownload").click(function(){
		var classId =$("#class").val();
		var locationId=$("#location").val();
		var accyer=$("#accyear").val();
		var section=$("#section").val();
		var viewBy=$("#hviewBy").val().trim();
		window.location.href = "TimeTableActionPath.html?method=classTimeTableDownloadPDF&viewBy="+viewBy+"&classId="+classId+"&locationId="+locationId+"&accyer="+accyer+"&section="+section;
		editClick();
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
	

	
	
	
	$('#class').change(function() {
			getClassSection();
			getTimeTableList();
			//getTimeTableList($('#class').val());
	});
	
	$('#section').change(function() {
		/*if($('#section').val() == "All"){
			getTimeTableList($('#class').val());
		}else{
			getTimeTableListBySection($('#class').val(),$('#section').val());
		}*/
		getTimeTableList();
	});
	
	if($("#allstudent tbody tr").length==0){
		$('.pagebanner').hide()
		$('.mypage').hide()
	}
	 
	if($("#editoperation").val()!="editoperation"){
		
		 if($("#historylocId1").val()!="" || $("#historyacademicId1").val()!=""){
			
			if($("#historyacademicId1").val()!=""){
				$("#accyear").val($("#historyacademicId1").val());
			}
			$("#location").val($("#historylocId1").val());
			getClasses();
			$("#class").val($("#historyclass1").val());
			 
			getClassSection();
			$("#section").val($("#historysection1").val());
			getTimeTableList();
			
	   }
	}
	
	if($(".hiddenClassForUpdate").length>0){
		$(".hiddenClassForUpdate").each(function(){
			
			var dayperiod=$(this).attr("name");
			var subteacher=$(this).val();
			
			
			getTeacherList(subteacher.split("-")[1],$("tr."+dayperiod.split("-")[0]).find("td."+dayperiod.split("-")[1]).find(".subject"));
			$("tr."+dayperiod.split("-")[0]).find("td."+dayperiod.split("-")[1]).find(".teacher").val(subteacher.split("-")[0]);
			
			
			$("tr."+dayperiod.split("-")[0]).find("td."+dayperiod.split("-")[1]).find(".subject").val(subteacher.split("-")[1]);
		});
	}
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
	    			 url : 'TimeTableActionPath.html?method=updateTimeTableDetails',
	    			 data : jsonObject,
	    			 beforeSend: function(){
	    					$("#loder").show();
	    				},
	    			 success : function(data) {
	    				 var result = $.parseJSON(data);
	    				 
	    				 if (result.timetable_Result == "success") {
	    					 $("#loader").hide();
	    					 $('.successmessagediv').show();
	    					 $(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
	    					 $('.successmessage').text("Time Table Details Updating in Progress...");
	    					 setInterval(function() {
	    						 
	    						 window.location.href = "menuslist.html?method=gettimetable";
	    					 }, 3000);
	    				 } else {
	    					 $('.successmessagediv').show();
	    					 $('.successmessage').text("TimeTable Details Not Saved, Please try again");
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

function getClasses() {
	var location =$("#location").val();
	 
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getClassDetailWithOutStream",
		data:{"location":location},
		
		success : function(response) {
			$("#class").html("");
			$("#class").append('<option value="ALL">' + "ALL" + '</option>');
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.ClassList.length; j++) {

				$("#class").append(
						'<option value="' + result.ClassList[j].classcode
						+ '">' + result.ClassList[j].classname
						+ '</option>');
			}
			 
			$("#class").val($("#historyclass1").val());
			if($("#class").val()!="ALL"){
				getClassSection();
			}
		}
	});
}

function getTimeTableList() {
	$('.pagebanner').show();
	$('.mypage').show();
	var hiddenclass=$("#historyclass1").val();
	var className="";
	
	if(hiddenclass!="" && hiddenclass!=undefined){
		className=hiddenclass;
	}else{
		className=$("#class").val();
	}
	 
	datalist = {
		"classId" : className,
		"locationId":$("#location").val(),
		"accyer":$("#accyear").val(),
		"section":$("#section").val(),
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
				$("#allstudent tbody").append("<tr><td><input type='radio' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
						+"<td>"+result.ClassTimeTableList[i].classname+" - "+result.ClassTimeTableList[i].sectionname+"</td>" 
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

function getClassSection() {
  var classId=$("#class").val();
  var accyear=$("#accyear").val();
  var locationId=$("#location").val();

$.ajax({
	type : "GET",
	url : "reportaction.html?method=getSectionByClass",
	data : {"classId":classId,
			"location":locationId},
	async : false,
	success : function(data) {
		var result = $.parseJSON(data);

		$("#section").html("");
		$("#section").append('<option value="all">' + "ALL"	+ '</option>');

		for (var j = 0; j < result.SectionList.length; j++) {

			$("#section").append(
					'<option value="'
					+ result.SectionList[j].sectionId
					+ '">'
					+ result.SectionList[j].sectionname
					+ '</option>');
		}
		$("#section").val($("#historysection1").val());
		
		if($("#section").val()!="all"){
			getTimeTableList();
		}
	}
});}
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
					$("#allstudent tbody").append("<tr><td><input type='radio' name='selectBox' id='selectBox'  value='"+result.ClassTimeTableList[i].timetableId+","+result.ClassTimeTableList[i].classid+","+result.ClassTimeTableList[i].sectionid+"'></td>"
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

function getTeacherList(subid,pointer){
	 $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getTeacherListForTimeTable",
			data:{ 'subid':subid,
				   "locationId":$("#locationId").val()
			 },
		 
			async : false,
			success : function(response) {
				 
				var result = $.parseJSON(response);
			
					pointer.closest("td").find("select.teacher").empty();
					pointer.closest("td").find("select.teacher").append("<option value='-'>--Teacher--</option>");
					var len=result.teacherList.length;
					if(len>0){
					
					for(var i=0;i<len;i++){
						pointer.closest("td").find("select.teacher").append("<option value='"+result.teacherList[i].teacherId+"'>"+result.teacherList[i].teacherName+"</option>");
					  }	
					}
					
			}
		});

}
function checkTeacher(dayid,teacherId,period,flag){
	datalist={
			"dayid":dayid,
			"teacherId":teacherId,
			"period":period,
			"accyearId":$("#accyearId").val(),
			}
	 $.ajax({
			type : 'POST',
			url : "TimeTableActionPath.html?method=checkTeacher",
		    data:datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				 if (result.status == "alreadymapped") {
					 $('.errormessagediv').show();
						$('.validateTips').text("This Teacher Already Mapped To Period "+period+" for Different Class");
					 setInterval(function() {
						 $('.errormessagediv').hide();
					 }, 3000);
					 flag.val("");
				 }
			}
		});
}

function editClick(){
	$("#edit").click(function(){
		var count = 0;
		var TimeTableDetails = null;
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		$('input[name="selectBox"]:checked').each(function() {
			count = count + 1;
			TimeTableDetails = this.value;
		});

		if (count > 1 || count == 0) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record");
		} else {
			window.location.href = "TimeTableActionPath.html?method=getTimeTableDetails&TimeTableDetails="+TimeTableDetails+
			"&locationId="+locationId+"&accyear="+accyear+"&class="+$("#class").val()+"&section="+$("#section").val();

			/*window.location.href = "TimeTableActionPath.html?method=getTeacherTimeTableDetails&TimeTableDetails="
					+ TimeTableDetails;*/
		}
	});
}
function getperiodcount(){
	
	var noofperiod=0;
	 var locId=$("#locationId").val();
	 var clsId=$("#hiddenClassId").val();

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
				
				}

			});
		return noofperiod;
}
function getclassperiod(){
	datalist={
			"classId":$("#hiddenClassId").val(),
			"timeTableId":$("#hiddenId").val(),
			"sectionId":$("#hiddenSecId").val(),
			"accyearId":$("#accyearId").val(),
			"locationId":$("#locationId").val(),
			}
	 $.ajax({
			type : 'POST',
			url : "TimeTableActionPath.html?method=checkTeacher",
		    data:datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				 if (result.status == "alreadymapped") {
					 $('.errormessagediv').show();
						$('.validateTips').text("This Teacher Already Mapped To Period "+period+" for Different Class");
					 setInterval(function() {
						 $('.errormessagediv').hide();
					 }, 3000);
					 flag.val("");
				 }
			}
		});
}
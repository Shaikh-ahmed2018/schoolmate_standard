$(document).ready(function(){
	printpreview();
	var hacademicyear=$('#hacademicyaer').val();
	var globallocation=$('#school').val();
	
	$(".selectall").change(function(){
		$(".studentid").prop("checked",$(this).prop("checked"));
	});
	var count=0;
	$("#print").click(function(){
		$(".studentid:checked").each(function(){
			count ++;
		});
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select any one record.");
			$('.errormessagediv').delay(3000).slideUp();
		}
	});
	
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var streamId=$("#streamId").val();
	var sectionId=$("#sectionid").val();
	getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	
	
	$("#Acyearid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId !="" && locationid !=""){
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
		}
		
	});
	
	getStream($("#locationname"));
	
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		if(streamId !="" && accyear !=""){
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
		}
		getStream($(this));
		getClassList();
		getSectionList(classname);
	});
	$("#streamId").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		if(classname==""){
			classname="all";
		}
		getClassList();
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	$("#classname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		if(sectionId==""){
			sectionId="all";
		}
		
		getSectionList(classname);
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	
	$("#sectionid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
		var sectionId=$("#sectionid").val();
		
		getStudentListforPrint(locationid,accyear,classname,streamId,sectionId);
	});
	
	if($("#allstudent tbody td").length==0){
		$(".pagebanner").hide();
		$(".pagination ").hide();
	}
	
	if($("#historylocId").val()!="" || $("#historyacademicId").val()!=""){
		
		if($("#historyacademicId").val()!=""){
			$("#Acyearid").val($("#historyacademicId").val());
		}
		
		$("#locationname").val($("#historylocId").val());
		getStream($("#locationname"));
		$("#streamId").val($("#historystreamId").val());
		getClassList();
		$("#classname").val($("#historyclassname").val());
		getSectionList($("#classname").val());
		
		if($("#historysectionid").val()==""){
			$("#sectionid").val("all");
		}else{
			$("#sectionid").val($("#historysectionid").val());
		}
		 
		getStudentListforPrint($("#locationname").val(),$("#Acyearid").val(),$("#classname").val(),$("#streamId").val(),$("#sectionid").val());
	}
	
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=searchAllAcademicYearDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" <img src='"+result.SearchList[i].image+"' /> </td>"
						+"</tr>"); 
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					
					window.location.href="menuslist.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
				
				});
				checkboxselection(); 
		}
	});
}
function getClassList(){
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId
	},

	$.ajax({

		type : 'POST',
		url : "reportaction.html?method=getClassesByStream",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassesList[j].classId + '">'

						+ result.ClassesList[j].classname

						+ '</option>');

			}

		}


	});

}

function getSectionList(classname){
	datalist={
			"classname" : classname,
			"location":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentTransferReport.html?method=getSectionList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="all">' + "----------Select----------"	+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append('<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

						+ '</option>');
			}
			
		}
		
		
	});
}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentDetailsLByFilter",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].locationId+" "+result.getClassWiseList[i].academicYearId+" />'</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					pagination(100);
					
					checkboxselection(); 
			}
		});

	}



function printpreview(){
	$("#print").click(function(){
		var studentId=[];
		var locationId=[];
		var academicYearId=[];
		var streamId=[];
		var count=0;
		$("#allstudent tbody tr").find(".studentid:checked").each(function(){
			count++;
			studentId.push($(this).val().split(" ")[0]);
			locationId.push($(this).val().split(" ")[1]);
			academicYearId.push($(this).val().split(" ")[2]);
			streamId.push($(this).val().split(" ")[3]);
			
		});
		if(count>0){
		window.location.href="menuslist.html?method=PrintPreviewStudentMultipleIDCard&location="
			+locationId
			+"&accyear="
			+academicYearId
			+"&studentId="
			+studentId
			+"&streamId="
			+streamId+"&classname="+$("#classname").val()+"&sectionid="+$("#sectionid").val();
		}
		else{
			$("#errormessagediv").show();
			$(".validateTips").text("Select Any Record.");
			$("#errormessagediv").delay(2000).fadeOut();
		}
		
	});
	
}
function getStudentListforPrint(locationid,accyear,classname,streamId,sectionid){
	
	var datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"streamId":streamId,
			"sectionid" :sectionid,
		}; 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=StudentListforPrint",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					
					if(result.getSectionWiseList.length > 0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='studentid' value='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].academicYearId+" "+$('#streamId').val()+"' /></td>"
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+"  </td>"
							+"<td> <img class='thumbnai' src='"+result.getSectionWiseList[i].image+"' width='20' height='20' /> </td>"
							+"</tr>");
					  }
					}
					else{
						$("#allstudent tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
					}
					$(".pagebanner").show();
					$(".pagination ").show();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					
					checkboxselection(); 
				
			}
		});

	}
function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="all">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}
			 checkboxselection();    
		}
	});
}

function checkboxselection(){
	
	$(".selectall").change(function(){
		$(".studentid").prop("checked",$(this).prop("checked"));
	});
	
	
	$(".studentid").change(function(){
		if($(".studentid").length==$(".studentid:checked").length){
			$(".selectall").prop("checked",true);
		}
		else{
			$(".selectall").prop("checked",false);
		}
		
	});
	
}

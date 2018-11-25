$(document).ready(function() {
	
	$("#locationname").val($("#hiddenlocId").val());
	$("#locationname1").val($("#hiddenlocId").val());
	

	
	tabstatus=$("div.slideTab li.active").find("a").attr("id");
	 
	var hacademicyear=$('#hiddenAcademicYear').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	$(".Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$("#Reset").click(function(){
		$("#locationname,#classname,#sectionid").val("all");
		$("#Acyearid").val($('#hiddenAcademicYear').val());
		$("#searchBy").val("");
		//searchList;
		$('#sectionid').html("");
	    $('#sectionid').append('<option value="all">' + "----------Select----------"	+ '</option>');
		$("#allstudent tbody tr").empty();
	});  
	 
	
	if($("#promaccYear").val()==""){
		//searchList;
	}
	
	
	$("#promoted").click(function(){
		if($("#classname").val()!="all")
		searchList();
		else{
			$("#show_per_page").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  0");
			pagination(50);
		}
		tabstatus="promoted";
		  
	});
	
	 
	 /*$('#mytabs a[href="#second"]').tab('show');*/
	
	$("#demoted").click(function(){
		 
		 searchDemotedList();
		 tabstatus="demoted";
		 $('#promotedId').removeClass("active");
			$('#demotedDiv').addClass("active");
			$('#demotedDiv').css({
				'display':'block !important'
			});
	});
	
	
	$("#demotedReset").click(function(){
		$("#locationname1,#classid,#sectionid1").val("all");
		$(".Acyearid").val($('#hiddenAcademicYear').val());
		$("#searchBy1").val("");
		searchDemotedList();
	}); 
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $(this).attr("class").split(" ");
		window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+
		"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3]
		+"&historylocId="+$("#locationname").val()+"&historyclassname="+$("#classname").val()+
		"&historysectionid="+$("#sectionid").val()+"&historysearchBy="+$("#searchBy").val()+"&tabstatus="+tabstatus
		+"&promaccYear="+$("#Acyearid").val()+"&promlocId="+$("#locationname").val()+"&promclassname="+$("#classname").val()
		+"&promsectionid="+$("#sectionid").val()+"&prosearch="+$("#searchBy").val()+"&demoaccYear="+$(".Acyearid").val()+"&demolocId="+$("#locationname1").val()+"&democlassname="+$("#classid").val()
		+"&demosectionid="+$("#sectionid1").val()+"&demosearch="+$("#searchBy1").val();
	});
	
	getClassListDemoted();
	getClassList();
	
	$("#locationname").change(function(){
		$("#searchBy").val("");
		
		//changeAccYear();
		getClassList();
		var classname=$("#classname").val();
		//getSectionList(classname);
		
		$("#allstudent tbody tr").empty();
		
	});	
	
	
	//changeAccYear();
	//getClassList();
	var classname=$("#classname").val();
	//getSectionList(classname);

	$("#classname").change(function(){
	
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		
		if(this.value == "all"){
	      $('#sectionid').html("");
	      $('#sectionid').append('<option value="all">' + "----------Select----------"	+ '</option>');
	      $("#allstudent tbody tr").empty();
		}	
		else{
			getSectionList(classname);
			searchList();
			//getStudentPromotedClassList(locationid,accyear,classname);
		}
		
	});
	
	$("#classid").change(function(){
	
		$("#searchBy").val("");
		var locationid=$(".locationname").val();
		var accyear=$(".Acyearid").val();
		var classname=$("#classid").val();
	    if(this.value=="all"){
         $('#sectionid1').html("");
		 $('#sectionid1').append('<option value="all">' + "ALL"+ '</option>');	
	    }
	     getSectionListDemoted(classname);
			/*getStudentDemotedClassList(locationid,accyear,classname);*/
	    searchDemotedList();
	});
	
	$("#sectionid").change(function(){
		$("#searchBy").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		searchList();
		//getStudentPromotedClassSectionList(locationid,accyear,classname,sectionid);
	});
	
	$("#sectionid1").change(function(){
		$("#searchBy").val("");
		var locationid=$(".locationname").val();
		var accyear=$(".Acyearid").val();
		var classname=$("#classid").val();
		var sectionid=$("#sectionid1").val();
		searchDemotedList();
		//getStudentDemotedClassSectionList(locationid,accyear,classname,sectionid);
	});
	
	$("#Acyearid").change(function(){
		var classname=$("#classname").val();
		if(classname != "all"){
			searchList();
		}
	});
	
	$(".Acyearid").change(function(){
		$("#searchBy").val("");
		$("#classid,#sectionid1").val("");
		/*changeAccYearByDemoted();*/
		searchDemotedList();
		getClassListDemoted();
		var classname=$("#classid").val();
		getSectionListDemoted(classname);
	});
	
	$(".locationname").change(function(){
		$("#searchBy").val("");
		/*changeAccYearByDemoted();*/
		searchDemotedList
		getClassListDemoted();
		var classname=$("#classid").val();
		getSectionListDemoted(classname);
	});
	
	$("#search").click(function(){
		searchList();
	});
	 
	/*$("#searchBy").keydown(function(event){
		if (event.keyCode == 13) {
			searchList();
	   }
	});
	
	$("#searchBy1").keydown(function(event){
		if (event.keyCode == 13) {
			searchDemotedList();
	   }
	});*/
	
	$("#searchdemoted").click(function(){
		searchDemotedList();
	});
	
	$('#domoted').click(function(){
		$('#promotedId').hide();
		$('#demotedDiv').addClass("in");
		$('#demotedDiv').css({
			'display':'block !important'
		});
	});
		
		$("#addnew").click(function(){
			window.location.href="menuslist.html?method=getStudentForPromotion&tabstatus="+tabstatus
			+"&promaccYear="+$("#Acyearid").val()+"&promlocId="+$("#locationname").val()+"&promclassname="+$("#classname").val()
			+"&promsectionid="+$("#sectionid").val()+"&prosearch="+$("#searchBy").val()+"&demoaccYear="+$(".Acyearid").val()+"&demolocId="+$("#locationname1").val()+"&democlassname="+$("#classid").val()
			+"&demosectionid="+$("#sectionid1").val()+"&demosearch="+$("#searchBy1").val();
		});
		
		 
		 if($("#hiddentabstatus").val()!=""){
			 tabstatus=$("#hiddentabstatus").val();
			 $(".slideTab li").removeClass("active");
			 $(".slideTab a#"+$("#hiddentabstatus").val()).closest("li").addClass("active");
			 
			 if($("#hiddentabstatus").val()=="promoted"){
				  
			    if($("#promaccYear").val()!=""){
					$("#Acyearid").val($("#promaccYear").val());
				}
				 $("#locationname").val($("#promlocId").val());
				 getClassList();
				 $("#classname").val($("#promclassname").val());
				 getSectionList($("#classname").val());
				 $("#sectionid").val($("#promsectionid").val());
				 $("#searchBy").val($("#prosearch").val());
				 searchList();

				/* 
				 $('#demotedDiv').removeClass("active");
				 $('#promotedId').addClass("active");
				 $('#promotedId').css({
				   'display':'block !important'
				 });
				 
				 */
				 
			 }else{
				 
				 if($("#demoaccYear").val()!=""){
					 $(".Acyearid").val($("#demoaccYear").val());
					}
				 $(".locationname").val($("#demolocId").val());
				 getClassListDemoted();
				 $("#classid").val($("#democlassname").val());
				 getSectionListDemoted($("#classid").val());
				 $("#sectionid1").val($("#demosectionid").val());
				 $("#searchBy1").val($("#demosearch").val());
				
				 $('#promotedId').removeClass("active");
				 $('#demotedDiv').addClass("active");
				 $('#demotedDiv').css({
				   'display':'block !important'
				 });
				 
				 searchDemotedList();
			 }
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

			$('#classname').html("");

			$('#classname').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');

			}

		}


	});

}
function getClassListDemoted(){
	var locationid=$(".locationname").val();
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

			$('#classid').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classid').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');

			}

		}


	});

}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="all">ALL</option>');
			
			for ( var j = 0; j <result.sectionList.length; j++) {
                   
				$('#sectionid').append('<option value="'

						+ result.sectionList[j].sectioncode + '">'

						+ result.sectionList[j].sectionnaem

						+ '</option>');
			}
			
		}
		
		
	});
}

function getSectionListDemoted(classname){
	
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname1").val()
	},
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid1').html("");
			
			$('#sectionid1').append('<option value="all">ALL</option>');
			
			for ( var j = 0; j <result.sectionList.length; j++) {
                   
				$('#sectionid1').append('<option value="'

						+ result.sectionList[j].sectioncode + '">'

						+ result.sectionList[j].sectionnaem

						+ '</option>');
			}
			
		}
		
		
	});
}

function getStudentPromotedClassList(locationid,accyear,classname){

	datalist = {

			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,

	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=getStudentPromotedClassList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			
			var len=result.getPromotedClassWiseList.length;
			var i=0;
			
			if(len>0){
			for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.getPromotedClassWiseList[i].studentId+" "+result.getPromotedClassWiseList[i].locationId+" "+result.getPromotedClassWiseList[i].academicYearId+" "+result.getPromotedClassWiseList[i].promotionId+" "+"studentid"+"'>"+result.getPromotedClassWiseList[i].count+"</td>" 
						+"<td> "+result.getPromotedClassWiseList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].studentAdmissionNo+" </td>" 
						+"<td> "+result.getPromotedClassWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].classsection+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].toClassSection+" </td>"
						+"<td> "+result.getPromotedClassWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]
				+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3]
				+"&historylocId="+$("#locationname").val()+"&historyclassname="+$("#classname").val()+
				"&historysectionid="+$("#sectionid").val()+"&historysearchBy="+$("#searchBy").val();
			});
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			$("#loder").hide();
		}
	});
}

function getStudentPromotedClassSectionList(locationid,accyear,classname,sectionid){
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=getStudentPromotedClassSectionList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var len=result.getPromotedSectionWiseList.length;
			var i=0;
			
			if(len > 0){
			for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.getPromotedSectionWiseList[i].studentId+" "+result.getPromotedSectionWiseList[i].locationId+" "+result.getPromotedSectionWiseList[i].academicYearId+" "+result.getPromotedSectionWiseList[i].promotionId+" "+"studentid"+"'>"+result.getPromotedSectionWiseList[i].count+"</td>" 
						+"<td> "+result.getPromotedSectionWiseList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].studentAdmissionNo+" </td>" 
						+"<td> "+result.getPromotedSectionWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].studentrollno+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].classsection+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].toClassSection+" </td>"
						+"<td> "+result.getPromotedSectionWiseList[i].specilizationname+" </td>"
						+"</tr>");
			}	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+
				"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3]
				+"&historylocId="+$("#locationname").val()+"&historyclassname="+$("#classname").val()+
				"&historysectionid="+$("#sectionid").val()+"&historysearchBy="+$("#searchBy").val();
			});
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			$("#loder").hide();
		}
	});
}

function changeAccYear(){
 
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAllAcademicYearPromotedDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				
				var len=result.StudentPromotedList.length;
				var i=0;
				
				if(len > 0){
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentPromotedList[i].studentId+" "+result.StudentPromotedList[i].locationId+" "+result.StudentPromotedList[i].academicYearId+" "+result.StudentPromotedList[i].promotionId+" "+"studentid"+"'>"+result.StudentPromotedList[i].count+"</td>" 
						+"<td> "+result.StudentPromotedList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.StudentPromotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentPromotedList[i].studentFullName+"</td>"
						+"<td> "+result.StudentPromotedList[i].studentrollno+" </td>"
						+"<td> "+result.StudentPromotedList[i].classsection+" </td>"
						+"<td> "+result.StudentPromotedList[i].toClassSection+" </td>"
						+"<td> "+result.StudentPromotedList[i].specilizationname+" </td>"
						+"</tr>");
				}	
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
				}
				
				$("#allstudent tbody tr").click(function(){
					var student_Id = $(this).find(".studentid").attr("class").split(" ");
					window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+
					"&accyear="+student_Id[2]+"&promotedId="+student_Id[3]
					+"&historylocId="+$("#locationname").val()+"&historyclassname="+$("#classname").val()+
					"&historysectionid="+$("#sectionid").val()+"&historysearchBy="+$("#searchBy").val();
				});
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				$("#loder").hide();
		}
	});
}

function searchList(){
	var searchname = $("#searchBy").val().trim();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
    var status="promoted";
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
			"status":status,
	}, 
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentPromotedSearchList",
		data: datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$('#divIdVal').show();
			$('#allstudent tbody').empty(); 
			
			var len=result.StudentPromotedSearchList.length;
			var i=0;
			if(len > 0){
			for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentPromotedSearchList[i].studentId+" "+result.StudentPromotedSearchList[i].locationId+" "+result.StudentPromotedSearchList[i].academicYearId+" "+result.StudentPromotedSearchList[i].promotionId+" "+"studentid"+"'>"+result.StudentPromotedSearchList[i].count+"</td>" 
						+"<td> "+result.StudentPromotedSearchList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentFullName+"</td>"
						+"<td> "+result.StudentPromotedSearchList[i].classsection+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].toClassSection+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].fromspec+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].tospec+" </td>"
						+"</tr>");
			    }
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			
			$("#allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+
				"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3]
				+"&historylocId="+$("#locationname").val()+"&historyclassname="+$("#classname").val()+
				"&historysectionid="+$("#sectionid").val()+"&historysearchBy="+$("#searchBy").val()+"&tabstatus="+tabstatus
			+"&promaccYear="+$("#Acyearid").val()+"&promlocId="+$("#locationname").val()+"&promclassname="+$("#classname").val()
			+"&promsectionid="+$("#sectionid").val()+"&prosearch="+$("#searchBy").val()+"&demoaccYear="+$(".Acyearid").val()+"&demolocId="+$("#locationname1").val()+"&democlassname="+$("#classid").val()
			+"&demosectionid="+$("#sectionid1").val()+"&demosearch="+$("#searchBy1").val()+"&";
			});
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			$("#loder").hide();
		}
	
	});
}

function studentDemotedList(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +"><thead>"
			+"<tr><th>SI No</th>"+
			"<th>To Academic Year</th>" +
			"<th>Adm No.</th>" +
			"<th>Student</th>" +
			"<th>From Class-Div</th>" +
			"<th>To Class-Div</th>" +
			"<th>Specilaization</th>" +
			"</tr></thead>" +
			"</table>"
	);
	
	$.ajax({
		type : "POST",
		url : "menuslist.html?method=studentDemotedList",
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			if(result.getStudentDemotedList.length>0){
			for (var i = 0; i < result.getStudentDemotedList.length; i++) {

				$(".allstudent").append("<tr>"
						+"<td class='"+result.getStudentDemotedList[i].studentId+" "+result.getStudentDemotedList[i].locationId+" "+result.getStudentDemotedList[i].academicYearId+" "+result.getStudentDemotedList[i].promotionId+" "+"studentid"+"'>"+result.getStudentDemotedList[i].count+"</td>" 
						+"<td> "+result.getStudentDemotedList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.getStudentDemotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getStudentDemotedList[i].studentFullName+" </td>"
						+"<td> "+result.getStudentDemotedList[i].toClassSection+" </td>"
						+"<td> "+result.getStudentDemotedList[i].classsection+" </td>"
						+"<td> "+result.getStudentDemotedList[i].specilizationname+" </td>"
						+"</tr>");
			}	
		}else{
				$('.allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			});
			$("#show_per_page1").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.getStudentDemotedList.length);
			pagination(50);
			$("#loder").hide();
		}
		
	});
}

function getStudentDemotedClassList(locationid,accyear,classname){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +"><thead>"
			+"<tr><th>SI No</th>"+
			"<th>To Academic Year</th>" +
			"<th>Adm No.</th>" +
			"<th>Student</th>" +
			"<th>From Class-Div</th>" +
			"<th>To Class-Div</th>" +
			"<th>Specilaization</th>" +
			"</tr></thead>" +
			"</table>"
	);
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
	   }, 
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=getStudentDemotedClassList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			var len=result.getDemotedClassWiseList.length;
			var i=0;
			if(len>0){
			for(i=0;i<len;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.getDemotedClassWiseList[i].studentId+" "+result.getDemotedClassWiseList[i].locationId+" "+result.getDemotedClassWiseList[i].academicYearId+" "+result.getDemotedClassWiseList[i].promotionId+" "+"studentid"+"'>"+result.getDemotedClassWiseList[i].count+"</td>" 
						+"<td> "+result.getDemotedClassWiseList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].classsection+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].toClassSection+" </td>"
						+"<td> "+result.getDemotedClassWiseList[i].specilizationname+" </td>"
						+"</tr>");
			   }	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			 
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			});
			
			$("#show_per_page1").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+len);
			pagination(50);
			$("#loder").hide();
		}
	});
}

function getStudentDemotedClassSectionList(locationid,accyear,classname,sectionid){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +"><thead>"
			+"<tr><th>SI No</th>"+
			"<th>To Academic Year</th>" +
			"<th>Adm No.</th>" +
			"<th>Student</th>" +
			"<th>From Class-Div</th>" +
			"<th>To Class-Div</th>" +
			"<th>Specilaization</th>" +
			"</tr></thead><tbody></tbody>" +
			"</table>"
	);
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=getStudentDemotedClassSectionList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			
			$(".allstudent tbody").empty();
			if(result.getDemotedSectionWiseList.length>0){
			for(var i=0;i<result.getDemotedSectionWiseList.length;i++){
				$(".allstudent tbody").append("<tr>"
						+"<td class='"+result.getDemotedSectionWiseList[i].studentId+" "+result.getDemotedSectionWiseList[i].locationId+" "+result.getDemotedSectionWiseList[i].academicYearId+" "+result.getDemotedSectionWiseList[i].promotionId+" "+"studentid"+"'>"+result.getDemotedSectionWiseList[i].count+"</td>" 
						+"<td> "+result.getDemotedSectionWiseList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].studentFullName+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].classsection+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].toClassSection+" </td>"
						+"<td> "+result.getDemotedSectionWiseList[i].specilizationname+" </td>"
						+"</tr>");
			  }	
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			});
			
			$("#show_per_page1").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.getDemotedSectionWiseList.length);
			pagination(50);
			$("#loder").hide();
		}
	});
}

function searchDemotedList(){
	 
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"+
			"<thead>"+
			"<tr><th>SI No</th>"+
			"<th>To Academic Year</th>" +
			"<th>Adm No.</th>" +
			"<th>Student</th>" +
			"<th style='width:11%;'>From Class-Div</th>" +
			"<th>To Class-Div</th>" +
			"<th>Specilaization</th>" +
			"</tr></thead><tbody></tbody></table>"
	);
	var searchname = $("#searchBy1").val().trim();
	var locationid=$(".locationname").val();
	var accyear=$(".Acyearid").val();
	var classname=$("#classid").val();
	var sectionid=$("#sectionid1").val();
	var status="demoted";
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
			"status":status
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentPromotedSearchList",
		data: datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			
			if(result.StudentPromotedSearchList.length>0){	
				
			for(var i=0;i<result.StudentPromotedSearchList.length;i++){
				
				$("#allstudentd tbody").append("<tr>"
						+"<td class='"+result.StudentPromotedSearchList[i].studentId+" "+result.StudentPromotedSearchList[i].locationId+" "+result.StudentPromotedSearchList[i].academicYearId+" "+result.StudentPromotedSearchList[i].promotionId+" "+"studentid"+"'>"+result.StudentPromotedSearchList[i].count+"</td>" 
						+"<td> "+result.StudentPromotedSearchList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].studentFullName+"</td>"
						+"<td> "+result.StudentPromotedSearchList[i].classsection+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].toClassSection+" </td>"
						+"<td> "+result.StudentPromotedSearchList[i].specilization+" </td>"
						+"</tr>");
			}
				}
				else{
					$('#allstudentd tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
				}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]
				+"&promotedId="+student_Id[3]+"&tabstatus="+tabstatus
				+"&promaccYear="+$("#Acyearid").val()+"&promlocId="+$("#locationname").val()+"&promclassname="+$("#classname").val()
				+"&promsectionid="+$("#sectionid").val()+"&prosearch="+$("#searchBy").val()+"&demoaccYear="+$(".Acyearid").val()+"&demolocId="+$("#locationname1").val()+"&democlassname="+$("#classid").val()
				+"&demosectionid="+$("#sectionid1").val()+"&demosearch="+$("#searchBy1").val();
			});
			
			$("#show_per_page1").val(50);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records "+result.StudentPromotedSearchList.length);
			pagination(50);
			$("#loder").hide();
		}
	
	});
}

function changeAccYearByDemoted(){
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +"><thead>"
			+"<tr><th>SI No</th>"+
			"<th>To Academic Year</th>" +
			"<th>Adm No.</th>" +
			"<th>Student</th>" +
			"<th>From Class-Div</th>" +
			"<th>To Class-Div</th>" +
			"<th>Specilaization</th>" +
			"</tr></thead>" +
			"</table>"
	);
	var locationId = $(".locationname").val();
	var accyear = $(".Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAllAcademicYearDemotedDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.StudentDemotedList.length>0){
			for(var i=0;i<result.StudentDemotedList.length;i++){
				$("#demotedTable .allstudent tbody").append("<tr>"
						+"<td class='"+result.StudentDemotedList[i].studentId+" "+result.StudentDemotedList[i].locationId+" "+result.StudentDemotedList[i].academicYearId+" "+result.StudentDemotedList[i].promotionId+" "+"studentid"+"'>"+result.StudentDemotedList[i].count+"</td>" 
						+"<td> "+result.StudentDemotedList[i].promotedAcademicYearId+" </td>"
						+"<td> "+result.StudentDemotedList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.StudentDemotedList[i].studentFullName+"</td>"
						+"<td> "+result.StudentDemotedList[i].studentrollno+" </td>"
						+"<td> "+result.StudentDemotedList[i].classsection+" </td>"
						+"<td> "+result.StudentDemotedList[i].toClassSection+" </td>"
						+"<td> "+result.StudentDemotedList[i].specilizationname+" </td>"
						+"</tr>");
			   }	
			}
			else{
				$('#demotedTable .allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$(".allstudent tbody tr").click(function(){
				var student_Id = $(this).find(".studentid").attr("class").split(" ");
				window.location.href="menuslist.html?method=studentPromotedPage&studentId="+student_Id[0]+"&locationId="+student_Id[1]+"&accyear="+student_Id[2]+"&promotedId="+student_Id[3];
			});
			pagination(100);
			$("#show_per_page1").val(100);
			$(".numberOfItem1").empty();
			$(".numberOfItem1").append("No. of Records  "+result.StudentDemotedList.length);
			$("#loder").hide();
		}
	});
}
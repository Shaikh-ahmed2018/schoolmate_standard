$(document).ready(function(){
	
	$(".status").hide();	
	$("#locationname").val($("#hiddenlocId").val());
	var hacademicyear=$('#hiddenAcademicYear').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	getClassList();
	
	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		window.location.href="menuslist.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+
		"&accyear="+student_Id[1]+"&locationId="+student_Id[2]+"&classname="+$("#classname").val()+
		"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val()+"&status="+$("#status").val();
	
	});
	
	
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname").val($("#hiddenlocId").val());
		$("#classname").val("all");
		$("#sectionid").html("");
		$('#sectionid').append('<option value="all">----------Select----------</option>');
		$("#Acyearid").val($("#hacademicyaer").val());
		$("#searchvalue").val("");
		$("#status").val("active");
		cleartable();
	});
	
	 $("#searchvalue").keypress(function(e){
		    if(e.keyCode == 13){
		        e.preventDefault();
		        searchList();
 		 }	
 	 });
	
	 $("#status").change(function(){
		 searchList();
		});
	
	$("#search").click(function(){
		searchList();
	});	
	
	$("#Acyearid").change(function(){
		$("#searchvalue").val("");
		if($("#classname").val()!="All"){
			changeAccYear();
		}else{
			cleartable();
		}
		
	});
	
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		getClassList();
		cleartable();
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var classname=$("#classname").val();
		if(classname!="All")
		{
			getSectionList(classname);
			changeAccYear();
		}else{
			cleartable();
			$("#sectionid").html("");
			$('#sectionid').append('<option value="all">----------Select----------</option>');
		}
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		changeAccYear();
	});
	
	if($("#historyaccYear").val()!="" || $("#historylocId").val()!="" 
		|| $("#historysearchvalue").val()!="" || $("#historystatus").val()=="N"){ 
		 
		$("#Acyearid").val($("#historyaccYear").val());
		$("#locationname").val($("#historylocId").val());
		getClassList();
		$("#classname").val($("#historyclassname").val());
		if($("#classname").val()!="All")
		getSectionList($("#classname").val());
		else{
			$("#sectionid").html("");
			$('#sectionid').append('<option value="all">----------Select----------</option>');	
		}
		
		$("#sectionid").val($("#historysectionid").val());
		
		$("#searchvalue").val($("#historysearchvalue").val());
		$("#status").val($("#historystatus").val());
		searchList();
	}
	
});

function changeAccYear(){
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classid = $("#classname").val();
	var sectionid=$("#sectionid").val();
	var status=$("#status").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getStudentdisciplinaryactionListDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,
			"classid" : classid,
			"sectionid":sectionid,
			"status":status
		},
		beforeSend: function(){
			$("#loder").show();
		},
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.SearchList.length>0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				}
				$("#loder").hide();
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				$("#allstudent tbody tr").click(function(){
					var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					window.location.href="menuslist.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+
					"&locationId="+student_Id[2]+"&classname="+$("#classname").val()+
					"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val()+"&status="+$("#status").val();
				
				});
			
		}
	});
}
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

			$('#classname').append('<option value="All">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

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
			
			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentDetailsLByFilter",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getClassWiseList.length>0){
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$("#loder").hide();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						window.location.href="menuslist.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+
						"&locationId="+student_Id[2]+"&classname="+$("#classname").val()+
						"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val()+"&status="+$("#status").val();
					
					});
			}
		});
	}

function getStudentListBySection(locationid,accyear,classname,sectionid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentListBySection",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$("#loder").hide();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						window.location.href="menuslist.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+
						"&locationId="+student_Id[2]+"&classname="+$("#classname").val()+
						"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val()+"&status="+$("#status").val();
					
					});
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var status=$("#status").val(); 
	
   datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
			"status":status
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentDisciplinaryActionSearchByList",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classname+" </td>"
							+"<td> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$('#allstudent tbody').append("<tr><td colspan='6'>NO Records Found</td></tr>");
					}
					$("#loder").hide();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						window.location.href="menuslist.html?method=AddStudentAppraisalReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+
						"&locationId="+student_Id[2]+"&classname="+$("#classname").val()+
						"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val()+"&status="+$("#status").val();
					
					});
			}
		});
}
function cleartable(){
	$("#allstudent tbody").empty();
	pagination(100);
	$(".numberOfItem").empty();
	$(".numberOfItem").append("No. of Records  0.");
}
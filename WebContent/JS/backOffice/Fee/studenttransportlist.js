$(document).ready(function(){	

	$("#loder").hide();
 
$("#Acyearid").val($("#hiddenaccyear").val());	

$("#resetbtn").click(function(){
	$("#locationname").val("all");
	$("#Acyearid").val($("#hiddenaccyear").val());
	getClassList($("#locationname"));
	getSectionList();
	$("#istransport").val("N"); 
	$("#searchvalue").val("");
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
});	

if($("#historyacademicId").val()==""){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		getStudentListBySection(locationid,accyear,classname,sectionid);
}
$("#istransport").change(function(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
	pagination(100);
});

getClassList($("#Location"));

$("#allstudent tbody tr").click(function(){
    var student_Id = $( this ).find(".stuid").attr("class").split(" ");
	var classid=$(this).find(".classid").attr("class").split(" ");
	var sectionid=$(this).find(".sectionid").attr("class").split(" ");
    var loc_id=$(this).find(".studentid").attr("class").split(" ");
    var  acy_id=$(this).find(".studentid").attr("class").split(" ");
    
	window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+loc_id[1]+
	"&acy_id="+acy_id[0]+"&istransport="+$("#istransport").val()+"&historylocId="+$("#locationname").val()+
	"&historyacademicId="+$("#Acyearid").val()+"&historyclassname="+$("#classname").val()+"&historysectionid="+$("#sectionid").val()
	+"&historysearch="+$("#searchvalue").val()+"&isT="+$("#istransport").val();
	
});

$("#locationname").change(function(){
	$("#classname").val("all");
	$("#sectionid").val("");
	getClassList($("#locationname"));
	getSectionList();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
	pagination(100);
});

$("#Acyearid").change(function(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
	pagination(100);
});

$("#classname").change(function(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getSectionList(classname);
	getStudentListBySection(locationid,accyear,classname,sectionid);
	pagination(100);
	//getRouteNameList();
});

$("#sectionid").change(function(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
	pagination(100);
});

$("#search").click(function(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	getStudentListBySection(locationid,accyear,classname,sectionid);
});

$("#searchvalue").keyup(function(event) {
	 
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		getStudentListBySection(locationid,accyear,classname,sectionid);
});

pagination(100);
$("#ShowPerPage").val("100");
$("#ShowPerPage").change(function(){
	pagination(this.value);
});

  if($("#historylocId").val()!="" || $("#historyacademicId").val()!="" || $("#historysearch").val()!="" 
	  || $("#historyistransport").val()=="Y"){
	 
	  if($("#historyacademicId").val()!=""){
		  $("#Acyearid").val($("#historyacademicId").val());
	  }
	  $("#locationname").val($("#historylocId").val());
	  getClassList();
	  $("#classname").val($("#historyclassname").val());
	  getSectionList($("#classname").val());
	  $("#sectionid").val($("#historysectionid").val());
	  $("#searchvalue").val($("#historysearch").val());
	  $("#istransport").val($("#historyistransport").val());
	  
	  getStudentListBySection( $("#locationname").val(),$("#Acyearid").val(),$("#classname").val(),$("#sectionid").val());
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

			$('#classname').append('<option value="all">' + "ALL"	+ '</option>');

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
			
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
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
					if(result.getClassWiseList.length > 0){
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td class='classid "+result.getClassWiseList[i].classDetailId+"'> "+result.getClassWiseList[i].classname+" </td>"
							+"<td class='sectionid "+result.getClassWiseList[i].sectioncode+"'> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					$("#allstudent tbody tr").click(function(){
					    var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						var classid=$(this).find(".classid").attr("class").split(" ");
						var sectionid=$(this).find(".sectionid").attr("class").split(" ");
					    
						window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+
						"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+student_Id[2]+"&acy_id="+student_Id[1];
					});
				}else{
					$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
				}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
					
			}
		});
	}



function getStudentListBySection(locationid,accyear,classname,sectionid){
	 
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :$("#sectionid").val(),
			"istransport":$("#istransport").val(),
			"searchvalue":$("#searchvalue").val()
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentListByTransportRequestCancel",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length > 0){
						
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+(i+1)+"</td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td class='classid "+result.getSectionWiseList[i].classDetailId+"'> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td class='sectionid "+result.getSectionWiseList[i].sectioncode+"'> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					$("#allstudent tbody tr").click(function(){
						 var student_Id = $( this ).find(".studentid").attr("class").split(" ");
							var classid=$(this).find(".classid").attr("class").split(" ");
							var sectionid=$(this).find(".sectionid").attr("class").split(" ");
					    
						window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+
						"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+student_Id[2]+
						"&acy_id="+student_Id[1]+"&istransport="+$("#istransport").val()+"&historylocId="+$("#locationname").val()+
						"&historyacademicId="+$("#Acyearid").val()+"&historyclassname="+$("#classname").val()+"&historysectionid="+$("#sectionid").val()
						+"&historysearch="+$("#searchvalue").val();
					});
			}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					pagination(100);
					$("#loder").hide();
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	
datalist = {
		    "status" :"active",
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentSearchByList",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length > 0){
					
						for(var i=0;i<result.SearchList.length;i++){
							$("#allstudent tbody").append("<tr>"
									+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
									+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
									+"<td> "+result.SearchList[i].studentFullName+"</td>"
									+"<td class='classid "+result.SearchList[i].classDetailId+"'> "+result.SearchList[i].classname+" </td>"
									+"<td class='sectionid "+result.SearchList[i].sectioncode+"'> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
						}
						$("#allstudent tbody tr").click(function(){
							    var student_Id = $( this ).find(".studentid").attr("class").split(" ");
								var classid=$(this).find(".classid").attr("class").split(" ");
								var sectionid=$(this).find(".sectionid").attr("class").split(" ");
						    
							window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+student_Id[2]+"&acy_id="+student_Id[1];
							
						});
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					pagination(100);
					$("#loder").hide();
			   }
		});
}

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
				if(result.SearchList.length > 0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td class='classid "+result.SearchList[i].classDetailId+"'> "+result.SearchList[i].classname+" </td>"
						+"<td class='sectionid "+result.SearchList[i].sectioncode+"'> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}	
				$("#allstudent tbody tr").click(function(){
					
				 	var student_Id = $( this ).find(".studentid").attr("class").split(" ");
					var classid=$(this).find(".classid").attr("class").split(" ");
					var sectionid=$(this).find(".sectionid").attr("class").split(" ");
			    
					window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+student_Id[2]+"&acy_id="+student_Id[1];
			});
				}else{
					$("#allstudent tbody").append("<tr><td colspan=5>No Record Found</td></tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
		}
	});
}
function getStudentListByLoc(){
	
	datalist = {
			
			"location" :$("#locationname").val(),
			"accyear" :$("#Acyearid").val(),
			"classId" :$("#classname").val(),
			"sectionid" :$("#sectionid").val()
		}, 
			$.ajax({
			type : 'POST',
			url : "transport.html?method=getStudentListByLoc",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length > 0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td class='classid "+result.SearchList[i].classDetailId+"'> "+result.SearchList[i].classname+" </td>"
							+"<td class='sectionid "+result.SearchList[i].section_id+"'> "+result.SearchList[i].sectionnaem+" </td>"
							+"</tr>");
					}	
					$("#allstudent tbody tr").click(function(){
						
					 	var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						var classid=$(this).find(".classid").attr("class").split(" ");
						var sectionid=$(this).find(".sectionid").attr("class").split(" ");
				    
						window.location.href="transport.html?method=settransportstudentwise&student_id="+student_Id[0]+"&classid="+classid[1]+"&sectionid="+sectionid[1]+"&loc_id="+student_Id[2]+"&acy_id="+student_Id[1];
				});
				}else{
					$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
				}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					
			}
		});
	}


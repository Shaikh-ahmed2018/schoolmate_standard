$(document).ready(function(){
	
	$("#locationname").val($("#hiddenlocId").val());
 
	var hacademicyear=$('#hiddenAcademicYear').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');

	$("#allstudent tbody tr").click(function(){
		var student_Id = $( this ).find(".studentid").attr("class").split(" ");
		individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
		showContactDetails();
		$(".showHide").hide();
		$("#pageLoader").show();
		/*window.location.href="menuslist.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];*/
	
	});
	
	
	$("#resetbtn").on("click", function (e) {
		
		$("#Acyearid").val($('#hiddenAcademicYear').val());
		$("#locationname").val($("#hiddenlocId").val());
		
		$("#searchvalue").val("");
		$("#status").val("active");
		$("#sortingby").val("Alphabetical");
		$("#sortingorders").show();
		$("#house").hide();
		$("input[id='ASC']").prop("checked","checked");
		$("#classname").val("all");
		$("#sectionid").val("all");
	
		 $("#allstudent tbody").empty();
		 getStudentListBySection();
		
	});
	$("#search").click(function(){
		//searchList();
		$("#allstudent tbody").show();
		getStudentListBySection();
	});	
	$("#searchvalue").keypress(function(e){
		$("#allstudent tbody").show();
		var keyCode = e.which;
		if(keyCode==13){
			getStudentListBySection();
		}
	});	
	
	$("#Acyearid").change(function(){
		getStudentListBySection();
		getHouseList();
	});
	
	$("#locationname").change(function(){
		$("#allstudent tbody").show();
			$('#classname').html("");
			$('#classname').append('<option value="">' + "----------Select----------"	+ '</option>');
			$('#sectionid').append('<option value="">' + "----------Select----------"	+ '</option>');
			/*$('#classname').append('<option value="all">' + "ALL"	+ '</option>');*/
			$('#sectionid').html("");
			$('#sectionid').append('<option value="">' + "----------Select----------"	+ '</option>');
		/*	$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');*/
			getClassList();
			getStudentListBySection();
			getHouseList();
		
		
	});
	
	$("#classname").change(function(){
		getSectionList();
		getStudentListBySection();
		getClassSpecilization();
		   if($("#classname").val()==""){
				$('#sectionid').html("");
				$('#sectionid').append('<option value="">' + "----------Select----------"	+ '</option>');
				/*$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');*/
			}
		
	});
	
	$("#sectionid").change(function(){
		getStudentListBySection();
	});
	
	
	$("#housename").change(function(){
		$("#allstudent tbody").show();
		getStudentListBySection();
		//getStudentListByHouseWise();
	});
	
	$("#status").change(function(){
		if($("#status").val() == "inactive"){
			$("#allstudent tr>th:last").html("Current Status");
		}else{
			$("#allstudent tr>th:last").html("Status");
		}
		
		getStudentListBySection();
	});
	$("input[type='radio']:checked").attr('checked', false);
	sorting();
	
	$("#sortingby").change(function(){
		 $('#housename').html("");
		  $('#housename').append('<option value="">' +"ALL"+ '</option>');
		sorting();
		getStudentListBySection();
	});
	
	$("input[type='radio']").change(function(){
		getStudentListBySection();
	});
	
	if($("#locationname").val()!="all"){
		getClassList();
		 
		$('#sectionid').html("");
		$('#sectionid').append('<option value="">' + "----------Select----------"	+ '</option>');
		/*$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');*/
	}
	if($("#locationname").val()=="all"){
		$(".pagebanner").hide();
		$(".pagelinks").hide();
	}
	
    $("#close").click(function(){
		$("#pageLoader").hide();
	});
    $("#locationname").val($("#hiddenlocId").val());
    
 
    // from individualstudent search
    

	
	var status=$("#confidentialStatusId").val();
	if(status == "AVAILABLE"){
		$('#confidentialStatusId').css("background-color", "yellow");
	}else{
		$('#confidentialStatusId').css("background-color", "#fff");
	}
	
	$('#back').click(function(){
		window.location.href="menuslist.html?method=studentSearch";
	});
		$('#goPage').click(function(){
			
			var forwardPage = $('.nil').val();
			if(forwardPage=='adm_Form'){
				window.open(
						  "studentRegistration.html?method=editStudent&searchTerm="+$('#hstudentid').val()+","+$('#hacademicYearId').val()+"&status="+$("#studentStatusId").val(),
						  '_blank' // <- This is what makes it open in a new window.
						);
				//window.location.href="studentRegistration.html?method=editStudent&searchTerm="+$('#hstudentid').val()+","+$('#hacademicYearId').val();
				
			}else if(forwardPage=='conf_Report'){
				window.open(
						  "menuslist.html?method=AddStudentConfidentialReport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
						  '_blank' // <- This is what makes it open in a new window.
						);
				//window.location.href="menuslist.html?method=AddStudentConfidentialReport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
				
			}else if(forwardPage=='id_Card'){
				window.open(
						  "menuslist.html?method=PrintStudentSingleIDCard&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
						  '_blank' // <- This is what makes it open in a new window.
						);
				//window.location.href="menuslist.html?method=PrintStudentSingleIDCard&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
				
			}else if(forwardPage=='mis_Report'){
				window.open(
						  "menuslist.html?method=individualMisreport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val(),
						  '_blank' // <- This is what makes it open in a new window.
						);
				//window.location.href="menuslist.html?method=individualMisreport&studentId="+$('#hstudentid').val()+"&accyear="+$('#hacademicYearId').val()+"&locationId="+$('#hschoolNameId').val();
				
			}else{
				document.getElementById("nil1").style.border = "1px solid #AF2C2C";
    			document.getElementById("nil1").style.backgroundColor = "#FFF7F7";
          	    $(".errormessagediv").show();
	     		    $(".validateTips").text("Select Any One Action.");
	     		    setTimeout(function() {
	  				$('#errormessagediv').fadeOut();
	  			    },3000);
				/*window.open(
						  "menuslist.html?method=studentTransferCertificateList",
						  '_blank');*/ // <- This is what makes it open in a new window.
						
				//window.location.href="menuslist.html?method=studentTransferCertificateList";
			}
		});
	
	//showContactDetails();
	
	var StudentImage=$("#photohiddenid").val().trim();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#contacts').click(function(){
		showContactDetails();
	});
	$("#ContactAddr").click(function(){
		
		ShowStudentAddress();
	});
	
	$('#classHistory').click(function(){
		
		var studentId = $('#hstudentid').val();
		var accyear = $('#hacademicYearId').val();
		var locationId = $('#hschoolNameId').val();
	
		
		$('#studenttable').hide();
		$('#individualstudenttable').show();
		$('#Addressstudenttable').hide();
		
		$('#individualstudenttable').empty();
		$("#individualstudenttable").append("<table class='table individualstudenttable' id='allstudent' width='100%'" +">"
				+"<center><tr><th>SI No</th>"+
				"<th>Academic Year</th>" +
				"<th>Class</th>" +
				"<th>Division</th>"+
				"<th>Roll No</th>"+
				"<th>Status</th>"+
				"</center></tr>" +
				"</table>"
		);
		
		$.ajax({
			
			type : "POST",
			url : "studentRegistration.html?method=individualStudentSearch",
			data : {"studentId":studentId,
					"accyear":accyear,
					"locationId":locationId			
			},
			async : false,
			
			success : function(response) {

				var result = $.parseJSON(response);

			
				for (var j = 0; j < result.studentSearchList.length; j++) {
			
					$("#individualstudenttable .individualstudenttable").append("<tr>"
							+"<td>"+result.studentSearchList[j].count+"</td>" 
							+"<td> "+result.studentSearchList[j].academicYear+" </td>"
							+"<td> "+result.studentSearchList[j].classname+"</td>"
							+"<td> "+result.studentSearchList[j].sectionnaem+" </td>"
							+"<td> "+result.studentSearchList[j].studentrollno+" </td>"
							+"<td> "+result.studentSearchList[j].status+" </td>"
							+"</tr>");
					}	
			}
			
		});
	});
	
/////
    
    
    $("#studentImageId1").mouseover(function(){
    	$(this).attr("readonly", "readonly") 

    })
    
    
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

			$('#classname').append('<option value="">' +"---------Select--------"+ '</option>');
			/*$('#classname').append('<option value="all">' +"ALL"+ '</option>');*/

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
			"classidVal" : $("#classname").val(),
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
	});
}

function getHouseList(){
	 
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	datalist={
			"locid" : locationid,
			"accyear":accyear,
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getHouseList",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			  $('#housename').html("");
			  $('#housename').append('<option value="">' +"ALL"+ '</option>');
			var j=0;
			var len=result.houseList.length;
			for ( j = 0; j < len; j++) {
				$('#housename').append('<option value="'
						+ result.houseList[j].houseId +'">'+ result.houseList[j].houseName+'</option>');
			}
		}
	});
}


function getStudentListBySection(){
	 
	
	var start=(Number($(".page.active").text())*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
	if(start<0){
		start=0;
	}
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var sortingby=$("#sortingby").val();
	var housename=$("#housename").val();
	
	var status=$("#status").val();
	var search=$("#searchvalue").val();
	var orderby=$("input[type='radio']:checked").val();

	var myorder=sortingby+" "+orderby;

	
	
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			"specialization" :'-',
			"housename":housename,
			"status":status,
			"search":search,
			"myorder":myorder,
			"show_per_page":$("#show_per_page").val(),
			"startPoint":start,
		},
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentListBySections",
			data : datalist,
			async :false,
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(response) {
				var totalcount=0;
				var result = $.parseJSON(response);
				$("#loder").hide();
					$("#allstudent tbody").empty();
					
					var len=result.getSectionWiseList.length;
					var i=0;
					
					if(len>0){
						
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr title='Click here'>"
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classsection+" </td>"
							+"<td> "+result.getSectionWiseList[i].houseName+" </td>"
							+"<td> "+result.getSectionWiseList[i].status+" </td>"
							+"</tr>");
					};
					
					totalcount=result.getSectionWiseList[0].totalCount;
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					
					    paginationforstudent($("#show_per_page").val(),totalcount);
					
						
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+totalcount);
					$(".pagebanner").show();
					$(".pagelinks").show();
					$("#loder").hide();
					$("#allstudent tbody tr").click(function(){
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
						showContactDetails();
						$(".showHide").hide();
						$("#pageLoader").show();
						
						/*window.location.href="menuslist.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];*/
					
					});	
					
					/*$("input[type='radio']").change(function(){
						
						$("#loder").show();
						getStudentListBySection();
						setTimeout(function(){
							$("#loder").hide();
						})
					});*/
			}
		});
	
	}


function sorting(){
	if($("#sortingby").val()=="House"){
		$("#Female").prop('checked', false);
		$("#Male").prop('checked', false);
		$("#ASC").prop('checked',false);
		$("#DESC").prop('checked',false);
		$("#orderbyalp").hide();
		$("#house").show();
		$("#orderbygen").hide();
		getHouseList();
	}
	else if($("#sortingby").val()=="Alphabetical"){
		$("#ASC").prop('checked','checked');
		$("#Female").prop('checked', false);
		$("#Male").prop('checked', false);
		$("#orderbyalp").show();
		$("#house").hide();
		$("#orderbygen").hide();
	}else if($("#sortingby").val()=="Admission"){
		$("#ASC").prop('checked', 'checked');
		$("#Female").prop('checked', false);
		$("#Male").prop('checked', false);
		$("#orderbyalp").show();
		$("#house").hide();
		$("#orderbygen").hide();
	}else if($("#sortingby").val()=="Gender"){
		$("#ASC").prop('checked',false);
		$("#DESC").prop('checked',false);
		$("#Female").prop('checked', "checked");
		$("#orderbyalp").hide();
		$("#house").hide();
		$("#orderbygen").show();
	}
}

function individualStudentSearch(studentId,accyear,locationId){
	var jsonValue={
			"studentId":studentId,
			"accyear":accyear,
			"locationId":locationId,
	}
	$.ajax({
		type:'POST',
		url:'menuslist.html?method=individualStudentSearchPopUp',
		data:jsonValue,
		async:false,
		success: function(response){
			var result=$.parseJSON(response);
			$("#academicYear").val(result.studentSearchList[0].academicYear);
			$("#schoolName").val(result.studentSearchList[0].location);
			$("#studentFullName").val(result.studentSearchList[0].studentFullName);
			$("#admissionNo").val(result.studentSearchList[0].studentAdmissionNo);
			$("#studentRollNo").val(result.studentSearchList[0].studentrollno);
			$("#classId").val(result.studentSearchList[0].classname);
			$("#sectionId").val(result.studentSearchList[0].sectionnaem);
			$("#routeId").val(result.studentSearchList[0].route);
			$("#stageId").val(result.studentSearchList[0].stage_name);
			$("#studentStatusId").val(result.studentSearchList[0].studentStatus);
			$("#confidentialStatusId").val(result.studentSearchList[0].confidentialStatus);
			$("#houseId").val(result.studentSearchList[0].houseName);
			$("#secondLanguageId").val(result.studentSearchList[0].secondLanguage);
			$("#thirdLanguageId").val(result.studentSearchList[0].thirdLanguage);
			$("#imagePreview").attr("src",result.studentSearchList[0].studentPhoto);
			$("#hstudentid").val(result.studentSearchList[0].studentId);
			$("#hacademicYearId").val(result.studentSearchList[0].academicYearId);
			$("#hschoolNameId").val(result.studentSearchList[0].locationId);
			$("#photohiddenid").val(result.studentSearchList[0].studentPhoto).prop("readonly","readonly");
			
		}
	
	});
}

//// from individualdtudentsearch......

function showContactDetails(){
	
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hschoolNameId').val();

	
	$('#studenttable').show();
	$('#individualstudenttable').hide();
	$('#Addressstudenttable').hide();
	
	$('#studenttable').empty();
	$("#studenttable").append("<table class='table studenttable' id='allstudent' width='100%'" +">"
			+"<center><tr><th>SI No</th>"+
			"<th>Relationship</th>" +
			"<th>Name</th>" +
			"<th>Mobile No</th>"+
			"</center></tr>" +
			"</table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "studentRegistration.html?method=showContactDetails",
		data : {"studentId":studentId,
				"accyear":accyear,
				"locationId":locationId			
		},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

		
			for (var j = 0; j < result.studentSearchList.length; j++) {
		
				$("#studenttable .studenttable").append(
						"<tr>"
						+"<td>1</td>" 
						+"<td>Father</td>"
						+"<td> "+result.studentSearchList[j].fatherName+"</td>"
						+"<td> "+result.studentSearchList[j].fatherMobileNo+" </td>"
						+"</tr>"
						+"<tr>"
						+"<td>2</td>" 
						+"<td>Mother</td>"
						+"<td> "+result.studentSearchList[j].motherName+"</td>"
						+"<td> "+result.studentSearchList[j].motherMobileNo+" </td>"
						+"</tr>"
						+"<tr>"
						+"<td>3</td>" 
						+"<td>Guardian</td>"
						+"<td> "+result.studentSearchList[j].gaurdianName+"</td>"
						+"<td> "+result.studentSearchList[j].guardianMobileNo+" </td>"
						+"</tr>"
					);
				}	
		}
		
	});
}




function ShowStudentAddress(){
	
	var studentId = $('#hstudentid').val();
	var accyear = $('#hacademicYearId').val();
	var locationId = $('#hschoolNameId').val();

	$('#Addressstudenttable').show();
	$('#studenttable').hide();
	$('#individualstudenttable').hide();
	
	
	$('#Addressstudenttable').empty();
	$("#Addressstudenttable").append("<table class='table indiviualsearch' id='allstudent' width='100%'" +">"
			+"<center><tr><th >SI No</th>"+
			"<th style='width:100px;'>Permanent Address</th>" +
			"<th style='width:100px;'>Present Address</th>" +
			"</table>"
	);
	
	$.ajax({
		
		type : "POST",
		url : "studentRegistration.html?method=ShowStudentAddress",
		data : {"studentId":studentId,
				"accyear":accyear,
				"locationId":locationId			
		},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

		
			for (var j = 0; j < result.studentSearchList.length; j++) {
		
				$("#Addressstudenttable .indiviualsearch").append(
						"<tr>"
						+"<td>"+result.studentSearchList[j].count+"</td>" 
						+"<td style='width:100px;'> "+result.studentSearchList[j].address+" </td>"
						+"<td style='width:100px;'> "+result.studentSearchList[j].presentaddress+"</td>"
						+"</tr>"
					
					);
				}	
		}
		
	});
}

function HideError(pointer) 
{
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


///////


function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm,status){
	   
	var start=(Number($(".page.active").text())*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
	if(start<0){
		start=0;
	}
	datalist={
		"locationId" : locationId,
		"academicYear" : academicYear,
		"classId" : classId,
		"divisionId" : divisionId,
		"searchTerm" : $("#searchvalue").val(),
		"show_per_page":$("#show_per_page").val(),
		"startPoint":start,
		"status" : $("#status").val(), 
	},

	$.ajax({
	
	type : 'POST',
	url : "studentRegistration.html?method=getStudentRegistrationList",
	data : datalist,
	beforeSend: function(){
		$("#loder").show();
	},
	success : function(response) {
		
		var result = $.parseJSON(response);
		totalCount=0;
		$('#allstudent tbody').empty();
		
		var len=result.studentdetailslist.length;
		var i=0;
		
		var ieratorindex=parseInt($("#show_per_page").val());
		if(parseInt($("#show_per_page").val())>len){
			ieratorindex=len;
		}
		if(len>0){
			totalCount = result.studentdetailslist[0].totalCount;
			 
				for(i=0;i<parseInt(ieratorindex);i++){
					$('#allstudent tbody').append("<tr>" +
							"<td><input type='checkbox' class='select' value='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+"'></td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].dateofBirth+"</td>" +
							"<td>"+result.studentdetailslist[i].dateofjoin+"</td>" +
							"</tr>");
				}
		 }
		else{
			$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
		}
		checkboxsselect();
		$(".numberOfItem").text("No. of Records "+totalCount+".");
		
		if(totalCount==0){
			totalCount=1;
		}
		paginationforstudent($("#show_per_page").val(),totalCount);
		$("#loder").hide();
	}
	
});
	 
}
function paginationforstudent(list,no_of_items) {
var show_per_page = list;
var number_of_items = no_of_items;

var number_of_pages = Math.ceil(number_of_items / show_per_page);

$('.pagination').empty();
$('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
$('#current_page').val(0);
$('#show_per_page').val(show_per_page);

var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
var current_link = 0;
while (number_of_pages > current_link) {
    navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
	 current_link++;
}
navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

$('.controls').html(navigation_html);
$('.controls .page:first').addClass('active');
$(".controls").find(".page").hide();
$(".controls").find(".active").show();
$(".controls").find(".active").prev().prev().show();	
$(".controls").find(".active").prev().show();	
$(".controls").find(".active").next().show();	
$(".controls").find(".active").next().next().show();


}

function go_to_page(page_num) {
var show_per_page = parseInt($('#show_per_page').val(), 0);

start_from = page_num * show_per_page;

end_on = start_from + show_per_page;


locationId=$("#locationname").val();
if($("#locationname").val()==""){
	locationId="all";
}
academicYear=$("#Acyearid").val();
if($("#Acyearid").val()==""){
	academicYear="all";
}
classId=$("#classname").val();
if($("#classname").val()==""){
	classId="all";
}
divisionId=$("#sectionid").val();
if($("#sectionid").val()==""){
	divisionId="all";
}

var start=(Number(page_num+1)*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
if(start<0){
	start=0;
}
datalist={
		"locationId" : locationId,
		"academicYear" : academicYear,
		"classId" : classId,
		"divisionId" : divisionId,
		"searchTerm" : $("#searchvalue").val(),
		"show_per_page":$("#show_per_page").val(),
		"startPoint":start,
		"status":$("#status").val(),
},

$.ajax({
	
	type : 'POST',
	url : "studentRegistration.html?method=getStudentRegistrationList",
	data : datalist,
	beforeSend: function(){
		$("#loder").show();
	},
	success : function(response) {
		
		var result = $.parseJSON(response);
		$('#allstudent tbody').empty();
		var ieratorindex=parseInt($("#show_per_page").val());
		
		var len=result.studentdetailslist.length;
		var i=0;
		if(parseInt($("#show_per_page").val())>len){
			ieratorindex=len;
		}
		if(len>0){
			totalCount = result.studentdetailslist[0].totalCount;
			 
				for(i=0;i<parseInt(ieratorindex);i++){
				$('#allstudent tbody').append("<tr>" +
						"<td class='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+" "+result.studentdetailslist[i].locationId+" "+"studentid"+"'>"+result.studentdetailslist[i].sno+"</td>" + 
						"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
						"<td>"+result.studentdetailslist[i].studentFullName+"</td>" + 
						"<td>"+result.studentdetailslist[i].studentrollno+"</td>" +
						"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
						"<td>"+result.studentdetailslist[i].houseName+"</td>" +
						"<td>"+result.studentdetailslist[i].studentStatus+"</td>" +
						"</tr>");
			}
		}
		else{
			$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
		}
		pagination(100);
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records  "+len);
		$(".pagebanner").show();
		$(".pagelinks").show();
		$("#loder").hide();
		$("#allstudent tbody tr").click(function(){
			var student_Id = $( this ).find(".studentid").attr("class").split(" ");
			individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
			showContactDetails();
			$(".showHide").hide();
			$("#pageLoader").show();
			/*window.location.href="menuslist.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];*/
		});	
		
	}
});
$('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

$('#current_page').val(page_num);
 $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();

}


function previous() {
     
    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }


}

function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}



function getClassSpecilization(){
	var data = {
			"classId" : $("#classname").val(),
			"locationId":$("#locationname").val(),
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
		  if(result.jsonResponse.length <= 0){
			$('#specialization').append('<option value="-">NIL</option>');
			
		  }
		  else{
			$('#specialization').append('<option value="-">ALL</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}
		  }

		}
	});
}
function paginationforstudent(list,no_of_items) {
	
	
	
	var show_per_page = list;
    var number_of_items = no_of_items;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
  

}

function go_to_page(page_num) {
	
	
	 var show_per_page = parseInt($('#show_per_page').val(), 0);

	    start_from = page_num * show_per_page;

	    end_on = start_from + show_per_page;
	    var sno=start_from;
	$("#loder").show();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var sortingby=$("#sortingby").val();
	var housename=$("#housename").val();
	var specialization=$("#specialization").val();
	var status=$("#status").val();
	var search=$("#searchvalue").val();
	var orderby=$("input[type='radio']:checked").val();
	
	var start=(Number(page_num+1)*Number($("#show_per_page").val())-Number($("#show_per_page").val()));
	if(start<0){
		start=0;
	}
	
	var myorder=sortingby+" "+orderby;
	setTimeout(function(){
		datalist = {
				"location" :locationid,
				"accyear" :accyear,
				"classId" :classname,
				"sectionid" :sectionid,
				"sortingby" :sortingby,
				"orderby" :orderby,
				"specialization" :specialization,
				"housename":housename,
				"status":status,
				"search":search,
				"myorder":myorder,
				"show_per_page":$("#show_per_page").val(),
				"startPoint":start,
			},
			$.ajax({
				type : 'POST',
				url : "menuslist.html?method=getStudentListBySections",
				data : datalist,
				async :true,
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {
					var totalcount=0;
					var result = $.parseJSON(response);
						$("#allstudent tbody").empty();
						
						var len=result.getSectionWiseList.length;
						var i=0;
						
						if(len>0){
							
						for(i=0;i<len;i++){
							
							sno++;
							
						$("#allstudent tbody").append("<tr title='Click Here'>"
								+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+"studentid"+"'>"+sno+"</td>" 
								+"<td> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
								+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
								+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
								+"<td> "+result.getSectionWiseList[i].classsection+" </td>"
								+"<td> "+result.getSectionWiseList[i].houseName+" </td>"
								+"<td> "+result.getSectionWiseList[i].status+" </td>"
								+"</tr>");
						};
						
						totalcount=result.getSectionWiseList[0].totalCount;
						}else{
							$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
						}
						
						   
						
							
						$(".numberOfItem").empty();
						$(".numberOfItem").append("No. of Records  "+totalcount);
						$(".pagebanner").show();
						$(".pagelinks").show();
						$("#loder").hide();
						$("#allstudent tbody tr").click(function(){
							var student_Id = $( this ).find(".studentid").attr("class").split(" ");
							individualStudentSearch(student_Id[0],student_Id[1],student_Id[2]);
							showContactDetails();
							$(".showHide").hide();
							$("#pageLoader").show();
							
							/*window.location.href="menuslist.html?method=individualStudentSearch&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];*/
						
						});	
				}
			});
	},200);
	
	
	$('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
	 $(".controls").find(".page").hide();
	    $(".controls").find(".active").show();
	    $(".controls").find(".active").prev().prev().show();	
	    $(".controls").find(".active").prev().show();	
	    $(".controls").find(".active").next().show();	
	    $(".controls").find(".active").next().next().show();
	
}












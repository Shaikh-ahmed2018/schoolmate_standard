$(document).ready(function(){
	
	$("#Acyearid").val($("#hacademicyaer").val());
	$("#AcademicYearFor").val($("#hacademicyaer").val());
	getStudentListByJs($("#locationname").val(),$("#hacademicyaer").val(),"all","all","all");

	$("#resetbtn").click(function(){
		$("#locationname").val("all");
		$("#Acyearid").val($("#hacademicyaer").val());
		$("#classname").val("All"); 
		$("#sectionid").val("all"); 
		$("#searchvalue").val("");
		getStudentListByJs("all",$("#hacademicyaer").val(),"all","all","all");
	});
	
	getClassList();
	$("#locationname").change(function(){
		getClassList();
		getSectionList();
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
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#Acyearid").change(function(){
		
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
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#classname").change(function(){
		getSectionList($(this).val());
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
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#sectionid").change(function(){
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
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#search").click(function(){
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
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode=="13"){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()=="" || $("#classname").val()==undefined){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		}
	});
	
	$("#locId").change(function(){
		if($("#admissionNo").val().trim()!=""){
			$(".errormessagediv").hide();
			getstudentdetails();
			getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
		}
	});
	
    $("#AcademicYearFor").change(function(){
		if($("#admissionNo").val().trim()!=""){
			$(".errormessagediv").hide();
			getstudentdetails();
			getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
		}
	});
	
	$("#delete").click(function(){
		
		var count=0;
		getDataArray=[];
		accYearArray=[];
		$(".select:checked").each(function(){
			getDataArray.push($(this).closest("tr").attr("class").split(" ")[0]);
			accYearArray.push($(this).closest("tr").attr("class").split(" ")[1]);
			count++;
		});
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Atleast One Record");
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure to Delete?</p>");
		}
		
	});
	$("#dialog").dialog({
		autoOpen  : false,
		resizable: false,
		maxWidth:200,
		minHeight:180,
		Height:200,
		Width:200,
		modal     : true,
		title     : "Transport Fee Concession Remove",
		buttons   : {
			'Yes' : function() {

				var datalist = {'getDataArray':getDataArray.toString(),
						'accYearArray':accYearArray.toString()
				};
				$.ajax({
					type : "GET",
					url : "transport.html?method=deleteTranportConcessionDetails",
					data : datalist,
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(response) {

						var result = $.parseJSON(response);

						if (result.status == "true") {
							$("#loder").hide();
							$(".successmessagediv").show();
							$(".sucessmessage") .text("Deleting  Record Progressing...");

							$(".successmessagediv").delay(3000).slideUp("slow");
							setTimeout(function(){
								$("#selectAll").prop("checked",false);
								getStudentListByJs($("#locationname").val(),$("#Acyearid").val(),$("#classname").val(),$("#sectionid").val(),$("#searchvalue").val());
							},2000);
						}
						else {
							$(".errormessagediv").show();
							$(".validateTips").text("Record Not Deleted");
							$("#selectAll").prop("checked",false);
							$(".errormessagediv").delay(3000).slideUp("slow");
							
						}
					}

				});
				$(this).dialog('close');
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});
	$("#add").click(function(){
		$("#myDialog").dialog("open");
	});
	$("#myDialog").dialog({
		autoOpen: false,
		resizable: false,
		height: 520,
		width: 1100,
		modal: true,
		title     :"Transport Fee Concession",
		buttons   : {
			'Yes' : function() {
				if($("#admissionNo").val().trim()!="" || $("#admissionNo").val().length>0){
					if($("input[name='contype']:checked").val()!=undefined)
					{
						term=[];
						feecode=[];
						consfeeamount=[];
						invalidAmount=[];
						count=0;
						$("#termwiseconcession tbody tr").each(function()
						 {
							consfeeamount.push($(this).find(".termconsamount").val());
							term.push($(this).find("td:first").attr("id"));
						 });
						
						/*$("#termwiseconcession tbody tr").each(function()
						  {
							if($(this).find(".termconsamount").val().trim()!="0"){
								consfeeamount.push($(this).find(".termconsamount").val());
								term.push($(this).find("td:first").attr("id"));
							}
							else{
								invalidAmount.push($(this).find("#termconsamount"+count).attr('id'));
							}
							count++;
						});

					 if(invalidAmount.length>0){
						    $(".errormessagediv").show();
							$(".validateTips").text("Ennter valid Amount.");
							columncount=0;
							$(".errormessagediv").delay(3000).fadeOut("slow");
							for(var i=0;i<invalidAmount.length;i++){
								document.getElementById(invalidAmount[i]).style.border = "1px solid #AF2C2C";
				    			document.getElementById(invalidAmount[i]).style.backgroundColor = "#FFF7F7";
							}
							$('#termwiseconcession tbody tr').click(function (event) {
								$(".errormessagediv").hide();
						         document.getElementById($(this).find(".termconsamount").attr('id')).style.border = "1px solid #ccc";
								 document.getElementById($(this).find(".termconsamount").attr('id')).style.backgroundColor = "#fff";
						    });
							return false;
					 } 
					 else{ */
						var datalist = {
								'studentIdNo':$(".hstudentId").val(),
								'AcademicYearFor':$("#AcademicYearFor").val(),
								'classId':$("#hclassId").val(),
								'term':term.toString(),
								'consfeeamount':consfeeamount.toString(),
								'conType':$("input[name='contype']:checked").val()
						};

						$.ajax({
							type : "POST",
							url : "transport.html?method=saveTransportConcession",
							data : datalist,
							beforeSend: function(){
								$("#loder").show();
							},
							success : function(response) {

								var result = $.parseJSON(response);

								if (result.status =="true") {
									$("#loder").hide();
									$(".successmessagediv").show();
									$(".sucessmessage") .text("Adding Record Progressing...");
									$(".successmessagediv").delay(3000).slideUp("slow");
									setTimeout(function(){
										getStudentListByJs($("#locationname").val(),$("#Acyearid").val(),$("#classname").val(),$("#sectionid").val(),$("#searchvalue").val());
									},2000);
								}
								else {
									$(".errormessagediv").show();
									$(".validateTips").text("Alraedy Exist.");
									$(".errormessagediv").delay(3000).slideUp("slow");
								}
							 }
						 });
					  /* }*/
					}
				  else{
						$(".errormessagediv").show();
						$(".validateTips").text("Select concession Type !");
						$(".errormessagediv").delay(3000).slideUp("slow"); 
						return false;
					}
					removeSelection();
					$(this).dialog('close');
				}
				else{
					$(".errormessagediv").show();
					$(".validateTips").text("Enter Admission No.");
					$(".errormessagediv").delay(3000).slideUp("slow");
					return false;
				}

			},
			'No' : function() {
				removeSelection();
				$(this).dialog('close');
			}
		}
	});
	
	/*$("#admissionNo").autocomplete({
		source : function(request, response) {
				
			$.ajax({

				url : "feecollection.html?method=studentSearchbyadmissionNo",
				data : {
					searchTerm : request.term,
					
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.admissionNo,
							value : item.admissionNo,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};

			
			$("#admissionNo").val(ui.item.label);
			studentListbyAdmissionNo(ui.item.label,$("#AcademicYearFor").val())
			return false;
		}
	});*/
	
	$("#admissionNo").autocomplete({
		source : function(request, response) {
				
			$.ajax({
				url : "feecollection.html?method=studentSearchbyStudentID",
				data : {
					searchTerm : request.term,
					locId :$("#locId").val()
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.studentIdNo,
							value : item.studentIdNo,
						}
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};
			
			$("#admissionNo").val(ui.item.label);
			studentListbyStudentID($("#admissionNo").val(),$("#AcademicYearFor").val());
			return false;
		}
	});
	
	
	$("#scholorshipPercentage").keypress(function(){
		if(Number($(this).val())<=100 && Number($(this).val()) > 0){
			$("#scholorshipPercentage").val($(this).val());
		}
		else{
			$("#scholorshipPercentage").val("");
		}
	});
	$("#scholorshipPercentage").change(function(){
		if(Number($(this).val())<=100 && Number($(this).val()) > 0){
			$("#scholorshipPercentage").val($(this).val());
		}
		else{
			$("#scholorshipPercentage").val("");
		}
	});
	
	$("input[name='contype']").change(function(){
		if($(this).val()=="equal"){
			$(".partialconcession").hide();
			if($(".hstudentId").val()!=undefined && $(".hstudentId").val()!=""){
				$(".equalconcession").show();
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("Admission Number Required ! ");
				$(".errormessagediv").delay(2000).fadeOut();
			}
		}
		else if($(this).val()=="partial"){
			$(".equalconcession").hide();
			if($(".hstudentId").val()!=undefined && $(".hstudentId").val()!=""){
				$(".partialconcession").show();
				getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("StudentID No. / Admission No Required ! ");
				$(".errormessagediv").delay(2000).fadeOut();
				$(this).prop('checked',false);
			}
		
		}
		else{
			$(".equalconcession").hide();
			$(".partialconcession").hide();
		}
	});
	
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

			$('#classname').append('<option value="All">' + "ALL"	+ '</option>');

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
	});
}



function pagination(list) {

	
	
	
	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr').length;
   
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
   
    $('#allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');

	
	

}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
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
/*function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListByJsForScholorship",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			if(result.studentdetailslist.length>0){
				for(var i=0;i<result.studentdetailslist.length;i++){
					$('#allstudent tbody').append("<tr class='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+" "+result.studentdetailslist[i].locationId+"'>" +
							"<td><input type='checkbox' class='select' id='"+result.studentdetailslist[i].studentAdmissionNo+"' value='"+result.studentdetailslist[i].studentAdmissionNo+"' /></td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].concessionType+"</td>" +
							"<td><img src='"+result.studentdetailslist[i].image+"' width='40' height='40' /></td>" +
							"</tr>");
					
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
			pagination(100);
			$("#ShowPerPage").change(function(){
				pagination($(this).val());	
			});
		$("#selectAll").change(function(){
			$(".select").prop("checked",$(this).prop("checked"));
		});
		$(".select").change(function(){
			if($(".select:checked").length==$(".select").length){
				$("#selectAll").prop("checked",true);
			}
			else{
				$("#selectAll").prop("checked",false);
			}
		});
		}
	});
}*/
function studentListbyAdmissionNo(admissionNo,accyear){
$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=studentListbyAdmissionNo",
		data : {"admissionNo":admissionNo,
				"accyear":accyear
		},
		async : false,
		success : function(response) {
			var result=$.parseJSON(response);
			$(".hstudentId").val(result.stuList[0].studentId);
			$("#student").val(result.stuList[0].student);
			$("#class_section").val(result.stuList[0].class_section);
			$("#hclassId").val(result.stuList[0].classId);
			$("#hspecialization").val(result.stuList[0].specialization);
			$("#StudentID").val(result.stuList[0].stuIDNo);
			$(".hlocID").val(result.stuList[0].locID)
			$("input[value='equal']").attr("checked",false);
			$("input[value='partial']").attr("checked",false);
			$("input[value='full']").attr("checked",true);
			$(".equalconcession").hide();
			$(".partialconcession").hide();
		}
	});
}

function studentListbyStudentID(stuidno,accyear){
	var locId=$("#locationname").val();
	
	$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getTransportAvailableStudent",
			data : {"stuidno":stuidno,
					"accyear":accyear,
					"locId":locId
			},
			async : false,
			success : function(response) {
				var result=$.parseJSON(response);
				$(".hstudentId").val(result.studentId);
				$("#student").val(result.studentName);
				$("#class_section").val(result.class_section);
				$("#hclassId").val(result.classId);
				$("#hspecialization").val(result.specialization);
				$("#admissionNo").val(result.admissionNo);
				$(".hlocID").val(result.locationId);
				if(result.isTransport == "Y"){
					$(".hrouteId").val(result.routeId);
					$(".hstageId").val(result.stageId);
					$("#routename").val(result.routeName);
					$("#stagename").val(result.stageName);
					$(".concessionDiv").show();
				}else{
					$(".hrouteId").val("");
					$(".hstageId").val("");
					$("#routename").val("");
					$("#stagename").val("");
					$(".concessionDiv").hide();
				}
				$("input[value='equal']").attr("checked",false);
				$("input[value='partial']").attr("checked",false);
				$("input[value='full']").attr("checked",true);
				$(".equalconcession").hide();
				$(".partialconcession").hide();
			}
		});
	}

function getTermdetails(classId,accyear,specialization){
	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=transportTermDetailsForConcession",
		data : {"classId":classId,
			"accyear":accyear,
			"specialization":specialization,
			"locid" : $("#locId").val(),
			"studentId" : $(".hstudentId").val(),
			"stageId" : $(".hstageId").val(),
		},
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result=$.parseJSON(response);
			$('#termwiseconcession tbody').empty();
			for(var i=0;i<result.stuList.length;i++){
				$('#termwiseconcession tbody').append("<tr>" +
						"<td style='text-align:center;' id='"+result.stuList[i].termcode+"'>"+result.stuList[i].term+"</td>" +
						"<td style='text-align:center;'>"+result.stuList[i].noofmonths+"</td>" +
						"<td style='text-align:center;'>"+result.stuList[i].stageAmount+"</td>" +
						"<td class='confee' style='text-align:center;'>"+result.stuList[i].termAmount+"</td>" +
						"<td><input type='text' name='scholorshipAmount' id='termconsamount"+i+"' class='termconsamount' value='0' /></td>" +
				"</tr>");

			}
			$("#loder").hide();
			$(".termconsamount").keyup(function(){
				if(isNaN($(this).val())){
					$(this).val("0");
				}
				else  if($(this).val().trim()==""){
					$(this).val("0");
				}
				else if(Number($(this).val()) > Number($(this).closest("tr").find(".confee").text())){
					$(this).val($(this).closest("tr").find(".confee").text());
				}
			});
			$(".termconsamount").focus(function(){
				if($(this).val()=="0"){
					$(this).val("");
				}
				else if($(this).val()!="0" && $(this).val().length>0){
					$(this).val($(this).val());
				}
				else{
					$(this).val("0");
				}
			});
			$(".termconsamount").blur(function(){
				if($(this).val().length>0){
					$(this).val($(this).val());
				}
				else{
					$(this).val("0");
				}
			});

		}
	});
}
function removeSelection(){
	
	  $("#StudentID").val("");
	  $("#admissionNo").val("");
	  $("#student").val("");
	  $("#class_section").val("");
	  $("input[name='contype']").attr("checked",false);
	  $(".partialconcession").hide();
	
}

function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	
	datalist = {
			"location" :locationId,
			"accyear" :academicYear,
			"classId" :classId,
			"sectionid" :divisionId,
			"searchname" : searchTerm,
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=getTransportConcessionStudentList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();

			if(result.getSectionWiseList.length>0){
				for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+"'>"
							+"<td style='vertical-align: middle;'><input type='checkbox' class='select' id='"+result.getSectionWiseList[i].studentId+"' value='"+result.getSectionWiseList[i].studentId+"' /></td>" 
							/*+"<td style='vertical-align: middle;'> "+result.getSectionWiseList[i].studentIdNo+" </td>"+*/
							+"<td style='vertical-align: middle;'>"+result.getSectionWiseList[i].studentAdmissionNo+"</td>" +
							"<td style='vertical-align: middle;'>"+result.getSectionWiseList[i].studentnamelabel+"</td>" +
							"<td style='vertical-align: middle;'>"+result.getSectionWiseList[i].classsection+"</td>" +
							"<td style='vertical-align: middle;'>"+result.getSectionWiseList[i].concessionType+"</td>" +
							"<td><img src='"+result.getSectionWiseList[i].imageFileName+"' width='40' height='40' /></td>" +
							+"</tr>");
				};
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
			}


			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
			pagination(100);
			$("#loder").hide();
			$("#selectAll").change(function(){
				$(".select").prop("checked",$(this).prop("checked"));
			});
			$(".select").change(function(){
				if($(".select:checked").length==$(".select").length){
					$("#selectAll").prop("checked",true);
				}
				else{
					$("#selectAll").prop("checked",false);
				}
			});
		}
	});
}

function getstudentdetails(){
	 datalist = {
			 'accyId' :$("#AcademicYearFor").val(),
	  		 'admisNo':$("#admissionNo").val(),
	  		 "locId"  :$("#locId").val(),
      }; 
		$.ajax({
			type : "POST",
			url : "feecollection.html?method=getstudenttransportbyaccy",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				if (result.details[0].status == "found") {
					$("#admissionNo").val(result.details[0].admissionNo);
					$("#student").val(result.details[0].student); 
					$("#stagename").val(result.details[0].stage_name);
					$("#class_section").val(result.details[0].class_section);
					$("#routename").val(result.details[0].routeName);
					$(".concessionDiv").show(); 
					$(".partialconcession").show();
				 }
				else {
					$(".partialconcession").hide();
					$(".concessionDiv").hide();
					$("#student").val("");
					$("#stagename").val("");
					$("#class_section").val("");
					$("#routename").val("");
					$(".errormessagediv").show();
					$(".validateTips").text("Student details are not available for the selected Academic year and Branch");
					$(".errormessagediv").delay(3000).slideUp("slow");
				}
			}
		});
}


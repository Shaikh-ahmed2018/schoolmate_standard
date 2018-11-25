$(document).ready(function(){
	
	$("#Acyearid").val($("#hiddenAcademicYear").val());
	
	$("#locationname").val($("#hSchoolId").val());
	
	checkboxsselect();
	
	$("#resetbtn").click(function(){
		$("#locationname").val("");
		$("#Acyearid").val($("#hiddenAcademicYear").val());
		$("#classname").val("");
		$("#sectionid").val("");
		$("#searchvalue").val("");
		$("#status").val("active");
		$("#trash").text("InActive");
		getStudentListByJs("all",$("#hiddenAcademicYear").val(),"all","all",$("#searchvalue").val(),"active");
	});
	
	//$(".numberOfItem").text(" No. of Records "+$("#allstudent tbody tr").length+".");
	
	$("#search").click(function(){
		commonOperations();
		 $("#selectall").prop("checked",false);
	});
	
	$("#locationname").change(function(){
		getClassList();
		getSectionList($("#classname").val());
		commonOperations();
		 $("#selectall").prop("checked",false);
	});
	$("#classname").change(function(){
		 getSectionList($(this).val());
		 commonOperations();
		 $("#selectall").prop("checked",false);
	});
	
	$("#sectionid").change(function(){
		commonOperations();
		 $("#selectall").prop("checked",false);
	});
	
	if($("#hiddenstatus").val()=="valid"){
		$("#add").show();
	}
	else{
		$("#add").hide();
	}
	
	$("#status").change(function(){
		
		if($(this).val() == "active"){
			$("#trash").text("InActive");
		}else{
			$("#trash").text("Active");
		}
		commonOperations();
		$("#selectall").prop("checked",false);
		
	});
	
	
	$("#show_per_page").change(function(){
		commonOperations();
		 $("#selectall").prop("checked",false);
	});
		
		$("#Acyearid").change(function(){
			commonOperations();
			 $("#selectall").prop("checked",false); 
		});
		
		$("#searchvalue").keypress(function(e){
			
			if(e.keyCode==13){
				commonOperations();
			}
		});
	//getStudentListByJs("all",$("#globalAcademicYear").val(),"all","all","all");
	
	$("#editStudent").click(function() {
	
		var stdId=$('.select:checked').val();
		  if ($('.select:checked').length>1) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Student");
		}
		else if (stdId==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Student");
		} else {
			$('#studentid').stdId;
			accyear=stdId.split(" ")[1];
			stdId=stdId.split(" ")[0];
			window.location.href = "studentRegistration.html?method=editStudent&searchTerm="+stdId+","+accyear+"&status="+$("#status").val()+
			"&locId="+$("#locationname").val()+"&classname="+$("#classname").val()+"&sectionid="+$("#sectionid").val()+"&searchvalue="+$("#searchvalue").val();
		}
		
	});
	
	
	$("#trash").click(function(){
		
		count = 0;
		stdId = [];
		remarks = null;
		$(".select:checked").each(function(){
			stdId.push($(this).val().split(' ')[0]);
			count ++;
		});	
		
		if(count ==0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Student to "+$("#trash").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else {
			 $("#dialog").dialog("open");
			 dispalyDialog($("#dialog"));
		}

	});
	
	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,					    
		title:'Student Details',
		buttons : {
			"Yes" : function() {
				 studentcount=validatestudentcount();
				 
				if($("#inactivereason").val()=="" && $("#trash").text().toLowerCase() =="inactive"){
					document.getElementById("inactivereason").style.border = "1px solid #AF2C2C";
        			document.getElementById("inactivereason").style.backgroundColor = "#FFF7F7";
               	      
        				$(".errormessagediv").show();
		     		    $(".validateTips").text("Reason Required to "+$("#trash").text());
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
				}
				else if($("#inactivereason").val()=="" && $("#trash").text().toLowerCase() == "active"){
					document.getElementById("inactivereason").style.border = "1px solid #AF2C2C";
        			document.getElementById("inactivereason").style.backgroundColor = "#FFF7F7";
               	      
        				$(".errormessagediv").show();
		     		    $(".validateTips").text("Reason Required to "+$("#trash").text());
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
				}
				else if($("#otherreason").val().trim()=="" && $("#trash").text().toLowerCase() == "active"){
					 
					document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
        			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
               	      
        				$(".errormessagediv").show();
		     		    $(".validateTips").text("OtherReason Required to "+$("#trash").text());
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
				}
               else if(studentcount.split("-")[0]=="true" && studentcount.split("-")[1]==0 && $("#trash").text()=="Active"){
					$(".errormessagediv").show();
    				$(".validateTips").text("Number of student limit exceeded!!");
	     		    setTimeout(function() {
	  				$('#errormessagediv').fadeOut();
	  			    },3000);
			   }
				else if(studentcount.split("-")[0]=="true" && $("#trash").text()=="Active"){
						$(".errormessagediv").show();
        				$(".validateTips").text("You can activate only "+studentcount.split("-")[1]+" Students !!");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
				}
				else{
					
				 if($("#trash").text().toLowerCase() == "inactive"){
					if($("#inactivereason").val().toLowerCase() == "others"){
						remarks =$("#otherreason").val();
					}else{
						remarks = $("#inactivereason").val()
					}
				}
				else if($("#trash").text().toLowerCase() == "active"){
					remarks = $("#otherreason").val()
				}
				 var accyear=$("#Acyearid").val();
				 
				datalist = {
					"stdId" : stdId.toString(),
					"remarks" : remarks,
					"status" : $("#trash").text(),
					"accyear":accyear
				}
				$.ajax({
					type : "POST",
					url : "studentRegistration.html?method=deactivateStudent",
					data : datalist,
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(data) {
						var result = $.parseJSON(data);

						if (result.status == true) {
							$('.successmessagediv').show();
							$('.successmessage').text("Student "+" "+$("#trash").text()+" Successfully");

							setTimeout(function() {
								window.location.href ="menuslist.html?method=studentList&currentstatus="+$("#status").val();
								}, 1000);
						} else {
							$('.errormessagediv').show();
							$('.validateTips').text("Operation failed.Try again...");

							setTimeout(function() {
								window.location.href ="menuslist.html?method=studentList&currentstatus="+$("#status").val(); 
								}, 1000);
						}
					}
			  });
			}
				
				$(this).dialog("close");
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	
		$('#excelDownload').click(function() {
			var location =$("#locationname").val();
			if(location==""||location==undefined){
				$('.errormessagediv').show();
				$('.validateTips').text("Select Branch Name And Try Again");
			}else{
				window.location.href = 'studentRegistration.html?method=downloadStudentDetailsXLS&location='+location;
			}
		});
		
		$("#pdfDownload").click(function(){
			var location =$("#locationname").val();
			if(location==""||location==undefined){
				$('.errormessagediv').show();
				$('.validateTips').text("Select Branch Name And Try Again");
			}else{
				window.location.href = "studentRegistration.html?method=downloadStudentDetailsPDF&location="+location;
			}
		});
	if($("#historylocId").val().trim() == ""){
		$("#locationname").val($("#hSchoolId").val());
	}
	else{
		$("#locationname").val($("#historylocId").val());
	}
	
	if($("#historyacademicId").val()!=""){
		$("#Acyearid").val($("#historyacademicId").val());
	}
	
	getClassList();
	$("#classname").val($("#historyclassname").val());
	getSectionList($("#classname").val());
	$("#sectionid").val($("#historysectionid").val());
	$("#status").val($("#historystatus").val());
	$("#searchvalue").val($("#historysearchvalue").val());
	
	if($("#status").val() == "active"){
		$("#trash").text("InActive");
	}else{
		$("#trash").text("Active");
	}

	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		if($("#currentstatus").val()=="active"){ 
			$("#status").val("inactive");	
			$("#trash").text("Active");
			$("#editStudent").hide();
			}
			else{
				$("#status").val("active");
				$("#trash").text("InActive");
				$("#editStudent").show();
			 }
	}
	commonOperations();
	 
});

function checkboxsselect(){
	
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$(".select").change(function(){
        if($(".select").length==$(".select:checked").length){
	         $("#selectall").prop("checked",true);
         }
       else{
	           $("#selectall").prop("checked",false);
           }
	});
}

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
			var ieratorindex=parseInt($("#show_per_page").val());
			if(parseInt($("#show_per_page").val())>result.studentdetailslist.length){
				ieratorindex=result.studentdetailslist.length;
			}
			if(result.studentdetailslist.length>0){
				totalCount = result.studentdetailslist[0].totalCount;
				 
					for(var i=0;i<parseInt(ieratorindex);i++){
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
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			var ieratorindex=parseInt($("#show_per_page").val());
			if(parseInt($("#show_per_page").val())>result.studentdetailslist.length){
				ieratorindex=result.studentdetailslist.length;
			}
			if(result.studentdetailslist.length>0){
				totalCount = result.studentdetailslist[0].totalCount;
				 
					for(var i=0;i<parseInt(ieratorindex);i++){
					$('#allstudent tbody').append("<tr>" +
							"<td><input type='checkbox' class = 'select'  value='"+result.studentdetailslist[i].studentId+" "+result.studentdetailslist[i].academicYearId+"'></td>" +
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
				$('#allstudent tbody').append("<tr><td colspan='7'>No Records Found</td></tr>");
			}
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
			$('#classname').append('<option value="">ALL</option>');
			
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
			"locationId":$('#locationname').val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">ALL</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function commonOperations(){
	locationId=$("#locationname").val();
	if($("#locationname").val()==""){
		locationId="all";
	}
	academicYear=$("#Acyearid").val();

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
	 
	getStudentListByJs(locationId,academicYear,classId,divisionId,$("#searchvalue").val(),$("#status").val());
}

function dispalyDialog(pointer){
	$(pointer).empty();
	if($("#trash").text().toLowerCase() == "inactive"){
		$(pointer).append("<p class='warningfont'>Are you sure to "+$("#trash").text()+"?</p>");
		$(pointer).append('<label class="warningothers">Reason:</label>');
		$(pointer).append('<select name="inactivereason" class="warningfont" style="width: 100%;" id="inactivereason" onchange="HideError(this)">'
				+ '<option value="">' + "----------select----------" + '</option>'
				+ '<option value="Completed Studies">' + "Completed Studies"
				+ '<option value="Demoted">' + "Demoted"
				+ '</option>'
				+ '<option value="Failed">' + "Failed" 
				+ '</option>'
				+ '<option value="TC Given">' + "TC Given" 
				+ '<option value="Transffred">' + "Transffred" 
				+ '<option value="Others">' + "Others" 
				+ '</option>'+
		'</select>');
		 $(pointer).append('<div id="othreason" style="display: none;"><label class="warningothers" >OtherReason:</label><input class="warningfont" onclick="HideError(this)" style="width: 100%;" type="text" name=other id="otherreason"/></div>');
		 $("#inactivereason").change(function(){
			 if($(this).val().toLowerCase() == "others"){
				 $("#otherreason").val("");
				 $("#othreason").show();
			 }else{
				 $("#otherreason").val("");
				 $("#othreason").hide();
			 }
		 });
		
	}else if($("#trash").text().toLowerCase() == "active"){
		$(pointer).append("<p>Are you sure to "+$("#trash").text()+"?</p>");
		$(pointer).append('<label>Reason:</label>');
		$(pointer).append('<div id="othreason"><input type="text" name=other id="otherreason"/></div>');
	}
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

function validatestudentcount() {
     
	var value = "";

	var datas = {
			"count" : $(".select:checked").length,
	};
	
	$.ajax({
		type : "POST",
		url : "studentRegistration.html?method=validatestudentcount",
		data : datas,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			value1=result.status;
			if (value1.split("-")[0]=="true") {
				value = "true-"+value1.split("-")[1];
			}
			else if (value1.split("-")[0]=="false") {
				value ="false-"+value1.split("-")[1];
				
			} 
		}
	});
	return value;
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

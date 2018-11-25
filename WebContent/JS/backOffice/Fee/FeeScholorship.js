$(document).ready(function(){
	
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	checkboxselect();
	
	$("#Acyearid").val($("#hacademicyaer").val());
	$("#AcademicYearFor").val($("#hacademicyaer").val());
	getStudentListByJs();
	
	
	$("#resetbtn").click(function(){
        $("#locationname").val("all");
        $("#Acyearid").val($("#hacademicyaer").val());
        $("#classname").val("all");
        $("#sectionid").val("all");
        $("#searchvalue").val("");    
        $("#status").val("Y");
        
        getStudentListByJs();
});
	getClassList();
	$("#locationname").change(function(){
		getClassList();
		getStudentListByJs();
		//getFeeconseStatusList();
	});
	$("#Acyearid").change(function(){
		getStudentListByJs();
		//getFeeconseStatusList();
	});
	$("#classname").change(function(){
		getSectionList($(this).val());
		getStudentListByJs();
		//getFeeconseStatusList();
	});
	$("#sectionid").change(function(){
		getStudentListByJs();
	//	getFeeconseStatusList();
	});
	$("#search").click(function(){
		getStudentListByJs();
		//getFeeconseStatusList();
	});
	$("#searchvalue").keypress(function(e){
	
		if(e.keyCode=="13"){
			getStudentListByJs();
		}
	});
	

	reason=null;
	var status = "N";
  $("#status").change(function(){
	  getStudentListByJs();
	  if(this.value=="Y"){
		  $("#delete").text("InActive"); 
		  status = 'N';
		 // getFeeconseStatusList();
		  $("#selectAll").prop("checked",false);
	  }
	  else{
		  $("#delete").text("Active"); 
		  status = 'Y';
		 // getFeeconseStatusList();
		  $("#selectAll").prop("checked",false);
	  }
	 
  });
  
    getDataArray=[];
	accYearArray=[];
	$("#delete").click(function(){
		 var cnt=0
		$(".select:checked").each(function(){
			getDataArray.push($(this).val());
			accYearArray.push($(this).closest("tr").attr("class").split(" ")[1]);
			cnt++;
		});
		 if(cnt == 0){
				$(".errormessagediv").show();
				$(".validateTips") .text("Select any one record");
			
			}
		 else {
		
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
		$("#dialog").append('<label>Reason:</label>');
		$("#dialog").append('<select name="feecanreason" id="feecanreason" onclick="hideError(this)" style="width:100%;">'
				+ '<option value="">' + "----------select----------"
				+ '</option>'
				+ '<option value="Incorrect Entry">Incorrect Entry</option>'
				+ '<option value="Not In Use">' + "Not In Use" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>');

		$("#dialog").append('<select name="activereason" id="activereason" onclick="hideError(this)" style="width:100%;">'
				+ '<option value="">' + "----------select----------"
				+ '</option>'
				+ '<option value="Correct Entry">' + "Correct Entry"
				+ '</option>'
				+ '<option value="In use">' + "In use" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>');
		
		  $("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" name=other id="otherreason" onclick="hideError(this)"/></div>');
     
		  $("#othreason").hide();
  		  $("#activereason").hide();
  		  $('#feecanreason').change(function(){
  			$(".errormessagediv1").hide();
  			var othersres=$('#feecanreason').val();
  			if(othersres == 'others'){
  				
  				$("#othreason").show(); 
  				$("#activereason").hide();
  			}else{
  				$("#otherreason").val("");
  				$("#othreason").hide();
  				$("#feecanreason").show();
  				$("#activereason").hide();
  			}
  			 reason = $("#feecanreason").val();
  		});
  		  
  		if($("#status").val()=="N"){
				$("#othreason").hide();
				$("#activereason").show();
				$("#feecanreason").hide();
			}
  		$('#activereason').change(function(){
  		if($(this).val() == 'others'){
				$("#othreason").show(); 
				$("#activereason").show();
				$("#feecanreason").hide();
			}
  		else{
				$("#otherreason").val("");
				$("#othreason").hide();
				$("#feecanreason").hide();
				$("#activereason").show();
			}
  		reason = $("#activereason").val();
  		});
		 }
	
	});
	$("#dialog").dialog({
	    autoOpen  :false,
	    maxWidth  :300,
	    maxHeight :250,
	    width     :300,
	    maxheight :290,
	    minheight :250,
	    modal     :true,
	    title     :"Scholorship Student",
	    buttons   : {
	              'Yes' : function() {
	            	  
	            	  var inactivereason=$("#feecanreason").val();
						 var activereason=$("#activereason").val();
						 
		                 if($("#delete").text()=="Active" && activereason.length == 0)
		               	  {
		    				 showError("#activereason","Select Any One Reason");
		    					return false;
		               	  }
		                 else if($("#delete").text()=="InActive" && inactivereason.length == 0){
		                	
		                	 showError("#feecanreason","Select Any One Reason");
		    					return false;
		                 }
						 
		                 else if($("#delete").text()=="InActive" && $("#feecanreason").val() == "others"){
		                	 if($("#otherreason").val().length==0 || $("#otherreason").val().trim()=="") {
		                		 showError("#otherreason","Field Required OtherReason");
			    					return false;   	 }
		                	 
		                	 else{
							reason = $("#feecanreason").val()+"-"+$("#otherreason").val();
							inactivefeeschol(status,reason,getDataArray,accYearArray);
							$(this).dialog("close");
							
		                	 }
						}
		                 
						else if ($("#delete").text()=="Active" &&$("#activereason").val()=="others") {
							
							 if($("#otherreason").val().length==0 ||$("#otherreason").val().trim()=="" ){
								 showError("#otherreason","Field Required OtherReason");
					     		    return false;
		                	 }
							 else{
								
							reason =$("#otherreason").val();
							
							inactivefeeschol(status,reason,getDataArray,accYearArray);
							$(this).dialog("close");
							 }
						}
						else{
							inactivefeeschol(status,reason,getDataArray,accYearArray);
							$(this).dialog("close");
						}
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
	    autoOpen  : false,
	    maxWidth  :1200,
	    maxHeight :400,
	    width     :1200,
	    height    :400,
	    modal     : true,
	    title     : "Add Fee Concession Student",
	    buttons   : {
	              'Yes' : function() {

	            	  if($("#student").val().trim() == ""){
	            		  
	            		  	$(".errormessagediv").show();
	            			$(".validateTips").text("Field Required - Student Name");
	            			$(".errormessagediv").delay(3000).slideUp("slow");
	            			return false;
	            	  }
	            	  else if($("#class_section").val().trim() == ""){
	            		  
	            		  	$(".errormessagediv").show();
	            			$(".validateTips").text("Field Required - Class Details");
	            			$(".errormessagediv").delay(3000).slideUp("slow");
	            			return false;
	            	  }
	            	  
	            	  else if(($("#admissionNo").val()).length > 1){
	            		  flag = false;
	            	  	  if($("input[name='contype']:checked").val()=="partial"){
	            	  		term=[];
	            			feecode=[];
	            			consfeeamount=[];
	            		
	            			$("#termwiseconcession tbody tr").each(function(){
            					term.push($(this).find("td:first").attr("id"));
            					feecode.push($(this).find(".confee").attr("class").split(" ")[0]);
            					
            					if($(this).find(".termconsamount").val().trim() == "0"){
            						flag = true;
            					}
            					consfeeamount.push($(this).find(".termconsamount").val());
            				});
            					
	            			if(flag){
	            				
	            				$(".errormessagediv").show();
		            			$(".validateTips").text("Enter the Concession Amount for applicable terms");
		            			$(".errormessagediv").delay(3000).slideUp("slow");
		            			return false;
	            				
	            			}
	            			else{
	            				var datalist = {'admissionNo':$("#admissionNo").val(),
	            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
	            			  			'classId':$("#hclassId").val(),
	            			  			'term':term.toString(),
	            			  			'feecode':feecode.toString(),
	            			  			'consfeeamount':consfeeamount.toString(),
	            			  			'conType':$("input[name='contype']:checked").val()
	            	           };
	            				 $.ajax({
										type : "GET",
										url : "feecollection.html?method=addScholorshipStudent",
										data : datalist,
										async : false,

										success : function(response) {
											var result = $.parseJSON(response);
											if (result.status =="true") {
												$(".successmessagediv").show();
												$(".sucessmessage") .text("Adding Record Progressing...");
												
												$(".successmessagediv").delay(3000).slideUp("slow");
												setTimeout(function(){
													window.location.href = "feecollection.html?method=FeeScholorship";
													},2000);
											}
											else {
												$(".errormessagediv").show();
												$(".validateTips").text("Already Exist.");
												$(".errormessagediv").delay(3000).slideUp("slow");
											}
										}
									});
	            			}
	            	  }
	            	  	  else if($("input[name='contype']:checked").val()=="equal"){
		            	  		  
	            	  		  if($("#scholorshipAmount").val().trim() == ""){
	            	  			$(".errormessagediv").show();
		            			$(".validateTips").text("Field Required - Concession Amount");
		            			$(".errormessagediv").delay(3000).slideUp("slow");
		            			return false;
	            	  		  }
	            	  		  else{
			            	  			var datalist = {'admissionNo':$("#admissionNo").val(),
			            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
			            			  			'classId':$("#hclassId").val(),
			            			  			'term':'all',
			            			  			'feecode':'all',
			            			  			'consfeeamount':$("#scholorshipAmount").val(),
			            			  			'conType':$("input[name='contype']:checked").val()
			            	         };
	            	  		  
			            	  			$.ajax({
									type : "GET",
									url : "feecollection.html?method=addScholorshipStudentForEqual",
									data : datalist,
									async : false,
	
									success : function(response) {
										var result = $.parseJSON(response);
	
										if (result.status =="true") {
											$(".successmessagediv").show();
											$(".sucessmessage") .text("Adding Record Progressing...");
											
											$(".successmessagediv").delay(3000).slideUp("slow");
											setTimeout(function(){
												window.location.href = "feecollection.html?method=FeeScholorship";
												},2000);
										}
										else {
											$(".errormessagediv").show();
											$(".validateTips").text("Already Exist.");
											$(".errormessagediv").delay(3000).slideUp("slow");
										}
									}
								});
	            	  		  }
		            	  		 
	            	  	   }	
	            	  	  
	            	  	  else if($("input[name='contype']:checked").val()=="full"){
	            	  		  
		            	  		 var datalist = {'admissionNo':$("#admissionNo").val(),
			            			  			'AcademicYearFor':$("#AcademicYearFor").val(),
			            			  			'classId':$("#hclassId").val(),
			            			  			'term':'all',
			            			  			'feecode':'all',
			            			  			'consfeeamount':'000',
			            			  			'conType':$("input[name='contype']:checked").val()
			            	           };
		            	  		  
							$.ajax({
										type : "GET",
										url : "feecollection.html?method=addScholorshipStudentForEqual",
										data : datalist,
										async : false,
										success : function(response) {
											var result = $.parseJSON(response);
											if (result.status =="true") {
												$(".successmessagediv").show();
												$(".sucessmessage") .text("Adding Record Progressing...");
												
												$(".successmessagediv").delay(3000).slideUp("slow");
												setTimeout(function(){
													window.location.href = "feecollection.html?method=FeeScholorship";
													},2000);
											}
											else {
												$(".errormessagediv").show();
												$(".validateTips").text("Already Exist.");
												$(".errormessagediv").delay(3000).slideUp("slow");
											}
										}
									});
	            	  	  }
	            	  	  else{
	            	  		$(".errormessagediv").show();
	            			$(".validateTips").text("Select Concession Type !");
	            			$(".errormessagediv").delay(3000).slideUp("slow"); 
	            	  		  
	            	  	  }
						$(this).dialog('close');
	              }
	else{
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Correct Admission No.");
		$(".errormessagediv").delay(3000).slideUp("slow");
		return false;
	}
	},
	  'No' : function() {
	                  $(this).dialog('close');
	             }
	        }
	
	});
	
	$("#AcademicYearFor").change(function(){
		
		if($("#admissionNo").val().trim()!=""){
			getstudentdetails();
			getconcessiontype();
		}
		
	});
	
	
	$("#admissionNo").autocomplete({
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
			if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
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
			if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
				$(".partialconcession").show();
				getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
			}
			else{
				$(".errormessagediv").show();
				$(".validateTips").text("Admission Number Required ! ");
				$(".errormessagediv").delay(2000).fadeOut();
			}
		}
		else{
			$(".equalconcession").hide();
			$(".partialconcession").hide();
		}
	});
	/*$('#allstudent tbody tr').click(function(){
		$("#myDialog").dialog("open");
	});*/
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

				$('#classname').append('<option value="'+ result.ClassList[j].classcode + '">'+ result.ClassList[j].classname+ '</option>');
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
 

function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	var locationId=$("#locationname").val();
	var academicYear=$("#Acyearid").val();
	var classId=$("#classname").val();
	var divisionId=$("#sectionid").val();
	var searchTerm=$("#searchvalue").val();
	var status=$("#status").val();
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm,
			"status":status,
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
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.studentdetailslist[i].academicYear+"</td>" +
							"<td>"+result.studentdetailslist[i].studentAdmissionNo+"</td>" +
							"<td>"+result.studentdetailslist[i].studentFullName+"</td>" +
							"<td>"+result.studentdetailslist[i].classSectionId+"</td>" +
							"<td>"+result.studentdetailslist[i].concessionType+"</td>" +
							"<td>"+result.studentdetailslist[i].isActive+"</td>" +
							"<td>"+result.studentdetailslist[i].remark+"</td>" +
							"<td><img src='"+result.studentdetailslist[i].image+"' width='40' height='40' /></td>" +
							"</tr>");
					
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
				pagination(100);
				$("#ShowPerPage").change(function(){
					pagination($(this).val());	
				});
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='9'>NO Records Found</td></tr>");
			}
			checkboxselect();
		}
	});
}
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
			
			if(result.stuList[0].status == "found"){
				$("#hstudentId").val(result.stuList[0].studentId);
				$("#student").val(result.stuList[0].student);
				$("#class_section").val(result.stuList[0].class_section);
				$("#hclassId").val(result.stuList[0].classId);
				$("#hspecialization").val(result.stuList[0].specialization);
			}else if(result.stuList[0].status == "notfound"){
				$(".errormessagediv").show();
				$(".validateTips").text("Student details are not available for selected academic year");
				$(".errormessagediv").delay(3000).slideUp("slow");
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
		url : "studentRegistration.html?method=TermdeatilsForConcession",
		data : {"classId":classId,
				"accyear":accyear,
				"specialization":specialization
		},
		async : false,
		success : function(response) {
			var result=$.parseJSON(response);
			$('#termwiseconcession tbody').empty();
			for(var i=0;i<result.stuList.length;i++){
				$('#termwiseconcession tbody').append("<tr>" +
						"<td id='"+result.stuList[i].termcode+"'>"+result.stuList[i].term+"</td>" +
						"<td class='"+result.stuList[i].feecode+" confee'>"+result.stuList[i].termTuitionFeeAmount+"</td>" +
						"<td><input type='text' name='scholorshipAmount' class='termconsamount' value='0' /></td>" +
						"</tr>");
				
			}
			$(".termconsamount").keyup(function(){
				if(isNaN($(this).val())){
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
 
/*function getFeeconseStatusList(){
	datalist={
			"locationId" : $("#locationname").val(),
			"academicYear" : $("#Acyearid").val(),
			"classId" : $("#classname").val(),
			"divisionId" : $("#sectionid").val(),
			"searchTerm" : $("#searchvalue").val(),
			"status":$("#status").val(),
	  },
	
	$.ajax({
		type : 'POST',
		url : "addfee.html?method=getFeeconseStatusList",
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
							"<td>"+result.studentdetailslist[i].isActive+"</td>" +
							"<td>"+result.studentdetailslist[i].remark+"</td>" +
							"<td><img src='"+result.studentdetailslist[i].image+"' width='40' height='40' /></td>" +
							"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='9'>NO Records Found</td></tr>");
			}
			checkboxselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
			pagination(100);
			$("#ShowPerPage").change(function(){
				pagination($(this).val());	
			});
		}
	});

	$('#allstudent tbody tr').click(function(){
		  ;
		$("#myDialog").dialog("open");
	});
}*/


function inactivefeeschol(status,reason,getDataArray,accYearArray){
	 datalist = {
			 'getDataArray':getDataArray.toString(),
	  		 'accYearArray':accYearArray.toString(),
	  		 'reason':reason,'status':status,
	  		 'button':$("#delete").text()
        }; 

		$.ajax({
			type : "GET",
			url : "feecollection.html?method=deleteScholorDetails",
			data : datalist,
			async : false,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status == "true") {
					$(".successmessagediv").show();
					$(".sucessmessage") .text(""+$("#delete").text()+" the  Record Progressing...");
					
					$(".successmessagediv").delay(3000).slideUp("slow");
					setTimeout(function(){
						window.location.href = "feecollection.html?method=FeeScholorship";
						},2000);
				  }
				else {
					$(".errormessagediv").show();
					$(".validateTips").text("Record Not "+$("#delete").text()+"");
					$(".errormessagediv").delay(3000).slideUp("slow");
				}
			}
		});
}

function checkboxselect(){
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

function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

function getstudentdetails(){

	 datalist = {
			 'accyId':$("#AcademicYearFor").val(),
	  		 'admisNo':$("#admissionNo").val()
       }; 

		$.ajax({
			type : "GET",
			url : "feecollection.html?method=getstudentbyaccy",
			data : datalist,
			async : false,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.details[0].status == "found") {
					$("#student").val(result.details[0].student);
					$("#class_section").val(result.details[0].class_section); 
					$("#hclassId").val(result.details[0].classId);
					$("#hspecialization").val(result.details[0].specialization);
				 }
				else {
					$("#student").val("");
					$("#class_section").val("");
					$(".errormessagediv").show();
					$(".validateTips").text("Student details are not available for the selected academic year");
					$(".errormessagediv").delay(3000).slideUp("slow");
				}
			}
		});

}

function getconcessiontype(){

	var checkedval = $("input[name='contype']:checked").val();
	if(checkedval == "equal"){
		$(".partialconcession").hide();
		if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
		$(".equalconcession").show();
		}
		else{
			$(".errormessagediv").show();
			$(".validateTips").text("Admission Number Required ! ");
			$(".errormessagediv").delay(2000).fadeOut();
		}
	}
	else if(checkedval == "partial"){
		$(".equalconcession").hide();
		if($("#hstudentId").val()!=undefined && $("#hstudentId").val()!=""){
			$(".partialconcession").show();
			getTermdetails($("#hclassId").val(),$("#AcademicYearFor").val(),$("#hspecialization").val());
		}
		else{
			$(".errormessagediv").show();
			$(".validateTips").text("Admission Number Required ! ");
			$(".errormessagediv").delay(2000).fadeOut();
		}
	}
	else{
		$(".equalconcession").hide();
		$(".partialconcession").hide();
	}

}
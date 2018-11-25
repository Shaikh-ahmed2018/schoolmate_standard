$(document).ready(function() {
	var status = "N";
	
	if($("#hiddenlocId").val()!=""){
		
		$("#locationname").val($("#hiddenlocId").val());
		getClassName($("#locationname").val());
		$("#classname").val($("#hiddenclassname").val());
		getClassSpecilization($("#classname").val(),$("#locationname").val());
		 
		$("#specialization").val($("#hiddenspecId").val());
		
		$("#status").val($("#hiddenstatus").val());
		
		if($("#status").val()=="Y"){
			 $("#buttsts").val("N")
			 $("#delete").text("InActive"); 
			 $("#editsubject").show();
		}
		else{
			$("#buttsts").val("Y")
			$("#delete").text("Active"); 
			$("#editsubject").hide();
		}
		/*getStudentListforPrint($("#locationname").val(),$("#classname").val(),$("#specialization").val(),$("#status").val());*/
		getSubDetailsList();
	}else{
		if($("#delete").text() == "InActive"){
			$("#buttsts").val("N")
		}else{
			$("#buttsts").val("Y")
		}
		$("#locationname").val($("#curr_loc").val());
	}
	getClassName($("#locationname").val());
	getClassSpecilization($("#classname").val(),$("#locationname").val());

	
	if($("#allstudent tbody tr").length == 0){
		 $("#allstudent tbody ").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
		 pagination(100);
	}
	
		
	$("#locationname").change(function(){		
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var status=$("#status").val();

		 
		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getClassName($("#locationname").val());
		
		getStudentListforPrint(locationid,classname,specialization,status);
		
		getClassSpecilization($("#classname").val(),$("#locationname").val());
		
	});
	
	$("#classname").change(function(){
		getClassSpecilization($(this).val(),$("#locationname").val());
			//checkSubjectduplication();
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var status=$("#status").val();
	
			
		
		if(locationid==""){
			locationid="all";
			
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		//getStudentListforPrint(locationid,classname,specialization,status);
		getSubDetailsList();
		checkboxs();
	});	
	
	$("#specialization").change(function(){
    	var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var status=$("#status").val();
		
		if(locationid==""){
			locationid="all";
			 getSubDetailsList();
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getStudentListforPrint(locationid,classname,specialization,status);
		//getSubDetailsList();
		checkboxs();
	
	});	

	$(".subject").click(function(){
		$("#subjectOne").slideToggle();
	});

	
	$("#editsubject").click(function(){
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).attr("id").split(",")[0];
			cnt++;
		});
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Subject");
			return false;
		} 
		else
		{
			var subId = getData;
			window.location.href = "subject.html?method=getSubjectDetails&subjectcode="+subId+
			"&locId="+$("#locationname").val()+"&classname="+$("#classname").val()+
			"&specialization="+$("#specialization").val()+"&status="+$("#status").val();
			var result = $.parseJSON(response);
		}

	});

	$('#search').click(function() {
		var searchname = $("#searchname").val().trim();
		window.location.href = "menuslist.html?method=subjectdetails&searchname="+searchname+"&school="+$('#school').val().trim();
	});
	$("#searchname").keypress(function(e){
		if(e.keyCode==13){
			var searchname = $("#searchname").val().trim();
			window.location.href = "menuslist.html?method=subjectdetails&searchname="+searchname+"&school="+$('#school').val().trim();
		}
	});
	
	checkboxs();
	reason=null;
	
  $("#status").change(function(){
	 
	 if(this.value=="Y"){
		 $("#delete").text("InActive"); 
		 status = 'N';
		 $("#buttsts").val("N")
		 $("#editsubject").show();
	  }
	 else{
		  $("#delete").text("Active"); 
		  status = 'Y';
		  $("#buttsts").val("Y");
		  $("#editsubject").hide();
	  }
		  
	 getSubDetailsList();
	  $("#selectall").prop("checked",false);
  });
  

	$("#delete").click(function() {

		 maxval=350;
		 if($("#status").val()=="N"){
			maxval=280;
			$("#dialog").dialog({ width: maxval});
			}else{
				$("#dialog").dialog({ width: maxval});
			}
		
		var count=0;
		subjectlist=[];
		locationList=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id").split(",")[0];
			var location=$(this).attr("id").split(",")[1];
			subjectlist.push(list);
			locationList.push(location);
			count++;

		});

		if(subjectlist.length==0)

		{
			$('.errormessagediv').show();
			$('.validateTips').text("Select any Subject to"+" "+$("#delete").text());
			$('.errormessagediv').delay(3000).slideUp();
		}

		else{
			
			if($("#delete").text() == "InActive"){
				status = "N"
			}else{
				status ="Y";
			}
			
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
			$("#dialog").append('<p class="warningfont" id="warningreason" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Subject, you won\'t be able to view details related to this Subject.</p>');
			$("#dialog").append('<label>Reason:</label>');
			$("#dialog").append('<select name="feecanreason" style="width: 100%;" id="feecanreason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Incorrect Entry">Incorrect Entry</option>'
					+ '<option value="Not In Use">' + "Not In Use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');

				$("#dialog").append('<select name="activereason" style="width: 100%;" id="activereason" onchange="HideError(this)">'
						+ '<option value="">' + "----------select----------"
						+ '</option>'
						+ '<option value="Correct Entry">' + "Correct Entry"
						+ '</option>'
						+ '<option value="In use">' + "In use" 
						+ '</option>'
						+ '<option value="others">' + "Others" 
						+ '</option>'+
				'</select>');
				
				  $("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" style="width: 100%;" name=other id="otherreason" onclick="HideError(this)"/></div>');
		     
				  $("#othreason").hide();
		  		  $("#activereason").hide();
		  		  
		  		  $('#feecanreason').change(function(){
		  			$(".errormessagediv").hide();
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
	 				$("#warningreason").hide();
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
		autoOpen  : false,
		resizable : false,
		modal     : true,
		title     : "Subject Details",
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
	    					return false;   	
	    					}
               	 
               	 else{
					reason = $("#otherreason").val();
					inactiveActivesubDetails(status,reason,subjectlist,locationList);
					$(this).dialog("close");
               	 }
				}
                
				else if ($("#delete").text()=="Active" &&$("#activereason").val()=="others") {
					
					 if($("#otherreason").val().length==0 || $("#otherreason").val().trim()==""){
						 showError("#otherreason","Field Required OtherReason");
			     		    return false;
               	        }
					 else{
					reason = $("#otherreason").val();
					
					inactiveActivesubDetails(status,reason,subjectlist,locationList);
					$(this).dialog("close");
					 }
				}
				else{
					inactiveActivesubDetails(status,reason,subjectlist,locationList);
					$(this).dialog("close");
				}
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});
	
	$('#excelDownload').click(function() {
		window.location.href = 'subject.html?method=downloadsubjectDetailsXLS';
	});
	$("#pdfDownload").click(function(){
		window.location.href = "subject.html?method=downloadsubjectDetailsPDF";
	});

	
});

function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
}
function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">ALL</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});
	}

function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
            if($("#classname").val()!="" && result.jsonResponse.length>0){
            	
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
			
            else if($("#classname").val()=="") {
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
            else{
            	$('#specialization').append('<option value="-">NIL</option>');
            }
		    
		}
	});
}
	
function getStudentListforPrint(locationid,classname,specialization,status){

	var datalist = {
			"location" :locationid,
			"classname" :classname,
			"specialization":specialization,
			"status":status,
		}; 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=SubjectListforListOnchangeMethod",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					//	//alert(result.list.length);

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].subjectid+","+result.list[i].locationId+"'/></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specializationName+" </td>"
							+"<td> "+result.list[i].subjectname+" </td>"
							+"<td> "+result.list[i].subjectCode+" </td>"
							+"<td> "+result.list[i].remark+" </td>"
							/*+"<td> "+result.list[i].path+" </td>"*/
							/*+"<td style='text-align:center'><a href='subject.html?method=getsyllabusdownload&subjectid=<bean:write name='allsubjects' property='subjectid'/><img id='dwnd1' src='images/download.png'/></a></td>"*/

						+"</tr>");
					}	
			}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$("#loder").hide();
					checkboxs();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
		});

	}
function inactiveActivesubDetails(status,reason,subjectlist,locationList){
	dataList={'subjectlist':subjectlist.toString(),
			'locationList':locationList.toString(),
		    'reason':reason,
		    'status':status,
		    'button':$("#delete").text(),
	};

	  $.ajax({
		type:"POST",
		url:'subject.html?method=DeleteSubject',
		beforeSend: function(){
			$("#loder").show();
		},
	    data : dataList,
	    success:function(response) {
	    	var result=$.parseJSON(response);
	    	var flag = false;
	    	if(result.status=="inactivetrue"){
	    		$("#loder").hide();
	    		flag = true;
				$(".successmessagediv").show();
				$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Inactivating subject details progressing...");
				$(".successmessagediv").css({
					'z-index':'9999999',
				});
			}else if(result.status=="activetrue"){
				flag = true;
				$("#loder").hide();
				$('.successmessagediv').show();
				$('.validateTips').text("Activating the subject details progressing...");
				$(".successmessagediv").css({'z-index':'9999999'});
			}
	    	else if(result.status=="inactivefalse"){
	    		$("#loder").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Failed to inactive the subject details");
				$(".errormessagediv").css({
					'z-index':'9999999',
				});
			}
			else if(result.status=="activefalse"){
				$("#loder").hide();
				$('.errormessagediv').show();
				$('.validateTips').text("Failed to active the subject details");
				$(".errormessagediv").css({
					'z-index':'9999999'
				});
				
			}else{
				$("#loder").hide();
				$('.errormessagediv').show();
				$('.validateTips').text("Failed to perform the operation. Please try again...");
				$(".errormessagediv").css({
					'z-index':'9999999'
				});
				$("#selectall").prop("checked",false);
				$(".select").prop("checked",false);
				setTimeout(function(){
					$('.errormessagediv').hide();
				},3000);
			}
	    	if(flag){
	    		setTimeout(function(){
				window.location.href='menuslist.html?method=subjectdetails&status='+$("#buttsts").val()+"&locId="+$("#locationname").val();
				$("#selectall").prop("checked",false);
				$(".select").prop("checked",false);
				subjectlist=[];
				locationList=[];
				},3000);
	    	}
	    	
		}
	});
}

function getSubDetailsList(){
	dataList={
			 locationid:$("#locationname").val(),
			 classname:$("#classname").val(),
			 specialization:$("#specialization").val(),
			 status:$("#status").val(),	
	};
	 
	$.ajax({
            type:'GET',
            url:'subject.html?method=getSubDetailsList',
            beforeSend: function(){
    			$("#loder").show();
    		},
            data:dataList,
            success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].subjectid+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specializationName+" </td>"
							+"<td> "+result.list[i].subjectname+" </td>"
							+"<td> "+result.list[i].subjectCode+" </td>"
							+"<td> "+result.list[i].remark+" </td>"
							/*+"<td> "+result.list[i].path+" </td>"*/
							/*+"<td style='text-align:center'><a href='subject.html?method=getsyllabusdownload&subjectid=<bean:write name='allsubjects' property='subjectid'/><img id='dwnd1' src='images/download.png'/></a></td>"*/

						+"</tr>");
					}	
			}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					$("#loder").hide();
					checkboxs();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
            
	});
	
	
}

function checkboxs(){
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
function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
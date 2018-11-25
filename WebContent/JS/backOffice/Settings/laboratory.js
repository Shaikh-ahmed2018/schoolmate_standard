$(document).ready(function() {

	var status = "N";
	
	if($("#allstudent tbody tr").length == 0){
		var len = $("#allstudent tbody tr").length;
		$("#allstudent tbody").append("<tr>" 
				+"<td colspan='8'>No Records Founds</td>"
				+"</tr>");
		pagination(100);
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records "+len);
	}
	
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
		  $("#editlab").show();
	      }
	    else{
	    	$("#buttsts").val("Y")
	    	$("#delete").text("Active"); 
	    	 $("#editlab").hide();
	      }
		getStudentListforPrint();
	}else{
		if($("#delete").text() == "InActive"){
			$("#buttsts").val("N")
		}else{
			$("#buttsts").val("Y")
		}
		$("#locationname").val($("#curr_loc").val());
	}
	
	$("#selectall").change(function() {

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
	
	getClassName($("#locationname").val());
	
	$("#locationname").change(function(){
		
		$("#selectall").prop("checked",false);
		$("#specialization").val("all");
		
		if($("#locationname").val() =="all" || $("#locationname").val()==""){
			
			$("#classname").html("");
			$("#classname").append("<option value='all'>ALL</option>");
			getStudentListforPrint();
		}
		else
			{
			getClassName($(this).val());
			getStudentListforPrint();
			}
		
	});
	
	$("#classname").change(function(){
		getClassSpecilization($(this).val(),$("#locationname").val());
			//checkSubjectduplication();
		$("#selectall").prop("checked",false);

		getStudentListforPrint();
		
	});	
	
	$("#specialization").change(function(){

		$("#selectall").prop("checked",false);

		getStudentListforPrint();
		
	});	

	reason=null;
	
	$("#status").change(function(){
		if(this.value=="Y"){
			  $("#delete").text("InActive"); 
			  status = 'N';
			  $("#buttsts").val("N")
			  $("#editlab").show();
	    }
	    else{
	    	 $("#editlab").hide();
			  $("#delete").text("Active"); 
			  status = 'Y';
			  $("#buttsts").val("N")
	    }
		getStudentListforPrint();
	});
	
	$("#editlab").click(function(){
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).attr("id").split(",")[0];
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
			return false;
		} 
		else
		{
			var labId = getData;
			window.location.href = "subject.html?method=getLabDetails&labcode="+labId+"&locId="+$("#locationname").val()+"&classname="+$("#classname").val()+"&specId="+$("#specialization").val()+"&status="+$("#status").val();
			//var result = $.parseJSON(response);
		}
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
		lablist=[];
		
		$(".select:checked").each(function(){

			var list=$(this).attr("id").split(",")[0];
		
			lablist.push(list);
			
			count++;

		});

		if(lablist.length==0)

		{
			$('.errormessagediv').show();
			$('.validateTips').text("Select any Subject to"+" "+$("#delete").text());
			$('.errormessagediv').delay(5000).slideUp();		}

		else{
			
			if($("#delete").text() == "InActive"){
				status = "N";
				$("#buttsts").val("N")
			}else{
				status ="Y";
				$("#buttsts").val("Y")
			}
			
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
			$("#dialog").append('<p class="warningfont" id="warningreason" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Practical, you won\'t be able to view details related to this Practical.</p>');
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
		title     :'Laboratory Details',
		buttons   : {
			"Yes" : function() {
				
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
					inactiveActivesubDetails();
					$(this).dialog("close");
              	 }
				}
               
				else if ($("#delete").text()=="Active" &&  $("#activereason").val()=="others") {
					
					 if($("#otherreason").val().length==0 || $("#otherreason").val().trim()==""){
						 showError("#otherreason","Field Required OtherReason");
			     		    return false;
              	        }
					 else{
					reason = $("#otherreason").val();
					inactiveActivesubDetails();
					$(this).dialog("close");
					
					 }
				}
				else{ 
					$.ajax({
					type : "POST",
					url :"subject.html?method=DeleteLab",
					data :{"lablist" :lablist.toString(),
						"locationList":$("#locationname").val(),
						"reason":reason.toString(),
						"status":status
					   },
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(data)
					{	
						$("#loder").hide();
						var result = $.parseJSON(data);
						var flag = false;
						if(result.status == "inactivetrue"){
							flag = true;
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Inactivating practical details progressing...");
						}
						else if(result.status == "activetrue"){
							flag = true;
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Activating practical details progressing...");
						}else if(result.status == "false"){
							$(".errormessagediv").show();
							$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Failed to perform the operation. Try again...");
						}else if(result.status == "inactivefalse"){
							$(".errormessagediv").show();
							$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Practical is mapped cannot be inactive");
						}
						else if(result.status == "inactivefail"){
							$(".errormessagediv").show();
							$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
							$(".validateTips").text("Failed to perform the operation. Try again...");
						}
						if(flag){
							$("#selectall").prop('checked',false);
							$(".select").prop('checked',false);
							setTimeout(function(){
								$(".successmessagediv").hide();
								window.location.href="menuslist.html?method=laboratory&status="+$("#buttsts").val()+"&locId="+$("#locationname").val();
							},5000);
						}else{
							$(".errormessagediv").hide();
							$("#selectall").prop('checked',false);
							$(".select").prop('checked',false);
						}
					}                       
				});
				$(this).dialog("close");
			   }
			},  
			"No" : function() {
				$(this).dialog("close");
			}
		}	
	});
	
	$('#excelDownload').click(function() {
		window.location.href = 'subject.html?method=downloadLabDetailsXLS';
	});
	$("#pdfDownload").click(function(){
		window.location.href = "subject.html?method=downloadLabDetailsPDF";
	});
});

function inactiveActivesubDetails(){
	$.ajax({
		type:"POST",
		url :"subject.html?method=DeleteLab",
		
		data :{"lablist" :lablist.toString(),
			"locationList":$("#locationname").val(),
			"reason":reason.toString(),
			"status":status
		},
		async : false,
		beforeSend: function(){
			 $("#loder").show();
		},
		success : function(data)
		{	
			$("#loder").hide();
			var result = $.parseJSON(data);
			var flag = false;
			if(result.status == "inactivetrue"){
				flag = true;
				$(".successmessagediv").show();
				$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Inactivating practical details progressing...");
			}
			else if(result.status == "activetrue"){
				flag = true;
				$(".successmessagediv").show();
				$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Activating practical details progressing...");
			}else if(result.status == "false"){
				$(".errormessagediv").show();
				$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Failed to perform the operation. Try again...");
			}else if(result.status == "inactivefalse"){
				$(".errormessagediv").show();
				$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Practical is mapped cannot be inactive");
			}
			else if(result.status == "inactivefail"){
				$(".errormessagediv").show();
				$(".errormessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text("Failed to perform the operation. Try again...");
			}
			if(flag){
				$("#selectall").prop('checked',false);
				$(".select").prop('checked',false);
				setTimeout(function(){
					$(".successmessagediv").hide();
					window.location.href="menuslist.html?method=laboratory&status="+$("#buttsts").val()+"&locId="+$("#locationname").val();
				},5000);
			}else{
				$(".errormessagediv").hide();
				$("#selectall").prop('checked',false);
				$(".select").prop('checked',false);
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
		url : "menuslist.html?method=getSpecialization",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="all">'+ "ALL" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}

function getSubject(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=getSubjectByClass",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
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
	});
}


function getClassName(val) {
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="all">'+"ALL"+ '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});
	}






function getStudentListforPrint(){  
	
	var locationid=$("#locationname").val();
	var classname=$("#classname").val();
	var specialization=$("#specialization").val();
	var status=$("#status").val();
	
	var datalist = {
			"location" :locationid,
			"classname" :classname,
			"specialization":specialization,
			"status":status,
		}; 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=LabListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' name='select' class='select' id='"+result.list[i].lab_id+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specialization+" </td>"
							+"<td> "+result.list[i].subjtname+" </td>"
							+"<td> "+result.list[i].lab_name+" </td>"
							+"<td> "+result.list[i].subjectCode+" </td>"
							+"<td style='width=93px'> "+result.list[i].description+" </td>"
						+"</tr>");
					
					}	
					$(".select").change(function(){
				        if($(".select").length==$(".select:checked").length){
					         $("#selectall").prop("checked",true);
				         }
				       else{
					           $("#selectall").prop("checked",false);
				           }
				});
			}
					else{
						$("#allstudent tbody").append("<tr>" 
								+"<td colspan='8'>No Records Founds</td>"
								+"</tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
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
	$(".errormessagediv").delay(5000).fadeOut();
}

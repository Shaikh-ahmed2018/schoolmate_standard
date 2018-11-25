function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document).ready(function() 
  {
	

	
	var status = "N";
	
    	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	/*change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},*/
	});
    	
    	if($("#allstudent tbody tr").length==0){
    		$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
    		$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  0");
			pagination(100);
    	}
	
	$("#Acyearid").val($("#hacademicyaer").val());
	
	getFeeStatusList();
	$("#locationname").change(function(){
		var locationId=$("#locationname").val();
		if(locationId==""){
			locationId="all";
		}
		var academicYear=$("#Acyearid").val();

		var searchTerm=$("#searchvalue").val();
		if(searchTerm==""){
			searchTerm="all";
		}
		getFeeList(locationId,academicYear,searchTerm);
		$("#selectall").prop("checked",false);
	});
	
	$("#Acyearid").change(function(){
		var locationId=$("#locationname").val();
		if(locationId==""){
			locationId="all";
		}
		var academicYear=$("#Acyearid").val();

		var searchTerm=$("#searchvalue").val();
		if(searchTerm==""){

			searchTerm="all";
		}
		if(locationId !="all" || locationId != undefined){

			getFeeList(locationId,academicYear,searchTerm);
			$("#selectall").prop("checked",false);
		}
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode==13){
			var locationId=$("#locationname").val();
			if(locationId==""){
				locationId="all";
			}
			var academicYear=$("#Acyearid").val();

			var searchTerm=$("#searchvalue").val();
			if(searchTerm==""){
				searchTerm="all";
			}
			if(locationId !="all" || locationId !=undefined){

				getFeeList(locationId,academicYear,searchTerm);
				$("#selectall").prop("checked",false);
			}
		}
	});

	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	if(pageUrl.split("=")[1]=="feeDetailsList&searchvalue"){

		$("#searchvalue").focus();
	}


	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	
	
	checkboxsselect();
	reason=null;
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		if($("#currentstatus").val()=="Y"){ 
			$("#status").val("N");	
			$("#delete").text("Active");
			commonOperation();
			}
			else{
				$("#status").val("Y");
				$("#delete").text("InActive");
				commonOperation();
			 }
	}
	
  $("#status").change(function(){
	 
	  if(this.value=="Y"){
		  $("#delete").text("InActive"); 
		  status = 'N';
	  }
	  else{
		  $("#delete").text("Active"); 
		  status = 'Y';
	  }
	  getFeeStatusList();
	  $("#selectall").prop("checked",false);
  });
	

  getDataArray=[];
	$("#delete").click(function(){
		 //add array1
		var cnt = 0;

		$('.select:checked').each(function() {
			getDataArray.push($(this).val());
			cnt++;
		});
		if(cnt == 0){
			$(".successmessagediv").hide();
			$(".errormessagediv").show();
			$(".validateTips") .text("Select any one record");
		
		}
		else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
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
		resizable: false,
		modal     : true,
		title     : "Fee Details",
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
					saveDetails(status,reason,getDataArray);
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
					
					saveDetails(status,reason,getDataArray);
					$(this).dialog("close");
					 }
				}
				else{
					saveDetails(status,reason,getDataArray);
					$(this).dialog("close");
				}
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});

	$("#feeedit").click(function() {
				var value = $('.select:checked').val();
				if (value == undefined || value =="") {
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Select any one record");
				} 
				else if($('.select:checked').length > 1){
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Select any one record");
				}
				else {
					window.location.href="addfee.html?method=editFeeDetails&name="+value+
					"&historylocId="+$("#locationname").val()+"&historyacyearId="+$("#Acyearid").val()+
					"&historystatus="+$("#status").val();
				}
	});

	$("#search").click(function() 
			{
				var searchvalue=$('#searchvalue').val().trim();
				window.location.href = "menuslist.html?method=feeDetailsList&searchvalue="+ searchvalue;
			});
	$('#searchvalue').keypress(function(e){
		if(e.which==13)
			window.location.href = "menuslist.html?method=feeDetailsList&searchvalue="+$(this).val().trim();

	});

	$('#excelDownload').click(function() {
		var searchvalue=$('#feesearchid').val();
		window.location.href = 'addfee.html?method=FeeDetailsXLS&searchvalue='+searchvalue;
	});

	$("#pdfDownload").click(function(){
		var searchvalue=$('#feesearchid').val();
		window.location.href = "addfee.html?method=FeeDetailsPDFReport&searchvalue="+searchvalue;
	});	
	
	if($("#historylocId").val()!="" || $("#historyacyearId").val()!="" || $("#historystatus").val()=="N"){
		
		$("#locationname").val($("#historylocId").val());
		
		if($("#historyacyearId").val()!=""){
			$("#Acyearid").val($("#historyacyearId").val());
		}
		$("#status").val($("#historystatus").val());
		
		if($("#status").val()=="Y"){
			  $("#delete").text("InActive"); 
		  }
		  else{
			  $("#delete").text("Active"); 
		  }
		getFeeList($("#locationname").val(),$("#Acyearid").val(),"");
	}
	
	

 });


function getFeeList(locationId,academicYear,searchTerm){
	 
	var dataList={
			"locationId":locationId,
			"academicYear":academicYear,
			"searchTerm":searchTerm,
			"status":$("#status").val(),
	};
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeDetailsListbyjs',
		data:dataList,
		beforeSend: function(){
			$("#loder").show();
		},
		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.feelist.length>0){
				for(var i=0;i<result.feelist.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' value='"+result.feelist[i].id+"' /></td>" +
							"<td>"+result.feelist[i].academicYearName+"</td>" +
							"<td>"+result.feelist[i].locationName+"</td>" +
							"<td>"+result.feelist[i].feeType+"</td>" +
							"<td>"+result.feelist[i].name+"</td>" +
							"<td>"+result.feelist[i].description+"</td>" +
							"<td>"+result.feelist[i].status+"</td>" +
							"<td>"+result.feelist[i].remark+"</td>" +
							
					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.feelist.length);
			pagination(100);
		 }
	});
}

function checkboxsselect(){
	$("#selectall").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
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

function getFeeStatusList(){
	
	 dataList={
	"locationId":$("#locationname").val(),
	"academicYear":$("#Acyearid").val(),
	"status":$("#status").val(),
	};
	
	$.ajax({
		type:'POST',
		url:'addfee.html?method=getFeeStatusList',
		data:dataList,
		async:false,
		success: function(data){
			
			var result=$.parseJSON(data);
			$("#feedetaillist").empty();
			
			$("#feedetaillist").append("<table class='table' id='allstudent'><thead><tr><th><input type='checkbox' name='selectall' id='selectall'/></th><th>Academic Year</th><th>School</th><th>Fee Type</th><th>Fee Name</th><th>Description</th><th>Status</th><th>Remark</th></tr></thead><tbody></tbody></table>");
			
			
			if(result.feedetaillist.length>0){
				
				for(var i=0;i<result.feedetaillist.length;i++){
					
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' value='"+result.feedetaillist[i].id+"' /></td>" +
							"<td>"+result.feedetaillist[i].academicYearName+"</td>" +
							"<td>"+result.feedetaillist[i].locationName+"</td>" +
							"<td>"+result.feedetaillist[i].feeType+"</td>" +
							"<td>"+result.feedetaillist[i].name+"</td>" +
							"<td>"+result.feedetaillist[i].description+"</td>" +
							"<td>"+result.feedetaillist[i].status+"</td>" +
							"<td>"+result.feedetaillist[i].remark+"</td>" +
							
					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			 checkboxsselect();
			 $(".numberOfItem").empty();
			 $(".numberOfItem").append("No. of Records  "+result.feedetaillist.length);
			 pagination(100);
			}
	});
	
}


function saveDetails(status,reason,getDataArray){
	 datalist = {'getDataArray':getDataArray.toString(),'reason':reason,'status':status,'button':$("#delete").text()};//create json data3
	$.ajax({
		type : "GET",
		url : "addfee.html?method=deleteFeeDetails",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},

		success : function(response) {
			var result = $.parseJSON(response);
			if (result.jsonResponse == true) {
				$("#loder").hide();
				$(".successmessagediv.feedetails").show();
				$(".sucessmessage") .text($("#delete").text()+" the record progressing...");
				setTimeout(function(){
					 window.location.href ="menuslist.html?method=feeDetailsList&currentstatus="+$("#status").val();
				 },3000);
			}
			else {
				$("#loder").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fee Already Mapped.");
				$(".errormessagediv").delay(3000).slideUp("slow");
			}

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

function commonOperation(){
	var locationId=$("#locationname").val();
	if(locationId==""){
		locationId="all";
	}
	var academicYear=$("#Acyearid").val();

	var searchTerm=$("#searchvalue").val();
	if(searchTerm==""){
		searchTerm="all";
	}
	getFeeList(locationId,academicYear,searchTerm);
}


function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}




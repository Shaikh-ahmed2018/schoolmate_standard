function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
}

function myFunction() {
	document.getElementById("myForm").submit();   
}


$(document).ready(function() {

	if($("#hiddenstatus").val()!="" && $("#hiddenstatus").val()!=undefined){
		$("#status").val($("#hiddenstatus").val());
	}
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchname").val("");
		CasteListingByStatus($("#status").val());
	});
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		 if($("#currentstatus").val()=="Y"){ 
			 $("#editReligion").hide();
			 $("#status").val("N");
			$("#inactive").text("Active");
			CasteListingByStatus($("#status").val());
		}
		else{
			$("#status").val("Y");
			$("#editReligion").show();
			$("#inactive").text("InActive");
			CasteListingByStatus($("#status").val());
		 }
	}
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
	}

	checkboxsselect();
	$("#status").change(function(){
		 status=$("#status").val();
		 if($(this).val()=="Y"){ 
			 $("#editReligion").show();
			$("#inactive").text("InActive");
			CasteListingByStatus(status);
		}
		else{
			$("#editReligion").hide();
			$("#inactive").text("Active");
			CasteListingByStatus(status);
		 }
		 $("#selectall").prop("checked",false);
		});
	
	$("#editReligion").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;
		$('input[name="select"]:checked').map(function() {
			getData = $(this).attr("id");
			cnt++;
		});
		
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Caste");
			return false;
		} 
		else
		{
			var casteId = getData;
			window.location.href = "religionCastCasteCategory.html?method=editCaste&casteId="+casteId+"&status="+$("#status").val()+"&searchname="+$("#searchname").val();
			var result = $.parseJSON(response);
		}

	});

	$("#search").click(function(){
		casteDetailsSearch();
      });

$("#searchname").keypress(function(e){
	var key = e.which;
	if(key==13){
		casteDetailsSearch();
	}
  });
	
	$("#inactive").click(function(){
		casteList=[];
		var count =0;
		$(".select:checked").each(function(){
			var Ids=$(this).attr("id");
			casteList.push(Ids);
			count ++;
		});	
		 
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Records to "+$("#inactive").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p class='warningfont' >Are you sure to "+$("#inactive").text()+"?</p>");
			$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
			$("#dialog").append('<select name="inactivereason" class="warningfont" style="width: 100%;" id="inactivereason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Incorrect Entry">' + "Incorrect Entry"
					+ '</option>'
					+ '<option value="Not in use">' + "Not in use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
			
			$("#dialog").append('<select name="activereason" class="warningfont" style="width: 100%;" id="activereason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Correct Entry">' + "Correct Entry"
					+ '</option>'
					+ '<option value="In use">' + "In use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
			
			  $("#dialog").append('<div id="othreason"><label class="warningothers" >OtherReason:</label><input type="text" class="warningfont" style="width: 100%;" name=other id="otherreason" onclick="HideError(this)"/></div>');
	     
		  		  $("#othreason").hide();
		  		  $("#activereason").hide();
		  		  $('#inactivereason').change(function(){
		  			$(".errormessagediv").hide();
		  			var othersres=$('#inactivereason').val();
		  			if(othersres == 'others'){
		  				$("#othreason").show(); 
		  				$("#activereason").hide();
		  			}else{
		  				$("#otherreason").val("");
		  				$("#othreason").hide();
		  				$("#inactivereason").show();
		  				$("#activereason").hide();
		  			}
		  		});
		  		if($("#status").val()=="N"){
	  				$("#othreason").hide();
	  				$("#activereason").show();
	  				$("#inactivereason").hide();
	  			}
		  		$('#activereason').change(function(){
		  		if($(this).val() == 'others'){
	  				$("#othreason").show(); 
	  				$("#activereason").show();
	  				$("#inactivereason").hide();
	  			}
		  		});
		  	  reason = $("#inactivereason").val();
		}
	});

	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,					    
		title:'Caste Details',
		buttons : {
			"Yes" : function() {
				
				var inactivereason=$("#inactivereason").val();
				 var activereason=$("#activereason").val();
				 var otherreason=$("#otherreason").val();
				  
				 
               if((inactivereason== "" || inactivereason==null)&& $("#status").val()=="Y")
             	  {
               	document.getElementById("inactivereason").style.border = "1px solid #AF2C2C";
       			document.getElementById("inactivereason").style.backgroundColor = "#FFF7F7";
             	    $(".errormessagediv").show();
		     		    $(".validateTips").text("Select Any One Reason");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
             	  }
               else if((inactivereason=="others" && otherreason.trim()=="") && $("#status").val()=="Y"){
               	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
       			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
              	      
       				$(".errormessagediv").show();
		     		    $(".validateTips").text("OtherReason Required field");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
               }
               else if((activereason=="" || activereason==undefined) && $("#status").val()=="N"){
               	document.getElementById("activereason").style.border = "1px solid #AF2C2C";
       			document.getElementById("activereason").style.backgroundColor = "#FFF7F7";
       				
               	$(".errormessagediv").show();
		     		    $(".validateTips").text("Select Any One Reason");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
               }
               else if((activereason=="others" && otherreason.trim()=="") && $("#status").val()=="N"){
               	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
       			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
              	 $(".errormessagediv").show();
		     		    $(".validateTips").text("OtherReason Required field");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
               }
                else
                  {
				$.ajax({
					type : 'POST',
					url : "religionCastCasteCategory.html?method=deleteCaste",
					data : {
						"casteList":casteList.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
						
					},
					success : function(response) {
						var result = $.parseJSON(response);
						var flag = false;
						$('.errormessagediv').hide();

						if (result.status ==  "inactivetrue"){
							flag = true;
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Inactivating unmapped caste details progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						}else if(result.status == "inactivefalse"){
							$(".errormessagediv").show();
							$(".validateTips").text("Caste details are mapped cannot be inactive");
							$('.errormessagediv').delay(3000).slideUp();
						}else if(result.status == "inactivefail"){
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to perform the operation. Try again");
							$('.errormessagediv').delay(3000).slideUp();
						}else if(result.status == "activetrue"){
							flag = true;
							$(".successmessagediv").show();
							$(".validateTips").text("Activating caste details progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						}else if(result.status == "activefalse"){
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to perform the operation. Try again");
							$('.errormessagediv').delay(3000).slideUp();
						}
						if(flag){
							setTimeout(function(){
								window.location.href="menuslist.html?method=casteDetails&currentstatus="+$("#status").val();
							},3000);
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
	


	/*			
					$('#excelDownload')
					.click(
							function() {


								var searchTerm = $("#searchexamid").val().trim();



								window.location.href = 'streamDetails.html?method=downloadStreamDetailsXLS&searchTerm='+ searchTerm;

							});


					$("#pdfDownload").click(function(){


						var searchTerm = $("#searchexamid").val().trim();

            			window.location.href = 'streamDetails.html?method=downloadStreamDetailsPDF&searchTerm='+ searchTerm;

            		});*/
	
		if($("#historysearchname").val()!="" || $("#historystatus").val()=="N"){
			$("#searchname").val($("#historysearchname").val());
			$("#status").val($("#historystatus").val());
			
			if($("#status").val()=="Y"){ 
				$("#inactive").text("InActive");
			}
			else{
				$("#inactive").text("Active");
			 }
			casteDetailsSearch();
		}
	
});	

function CasteListingByStatus(status){
	 
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=CasteListingByStatus",
		data : {
			"status" :status,
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.list.length;
			
			if(len > 0){
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select' name='select' id='"+result.list[i].casteId+"'/></td>" 
						+"<td> "+result.list[i].religion+" </td>" 
						+"<td> "+result.list[i].caste+" </td>"
						+"<td> "+result.list[i].remarks+" </td>"
						+"</tr>");
				  }	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.list.length);
			pagination(100);
		}
	});
} 

function casteDetailsSearch(){
	
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=casteDetailsSearch",
		data : {
			"searchname":$("#searchname").val(),
			"status":$("#status").val(),
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.list.length;
			
			if(len > 0){
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select' name='select' id='"+result.list[i].casteId+"'/></td>" 
						+"<td> "+result.list[i].religion+" </td>" 
						+"<td> "+result.list[i].caste+" </td>"
						+"<td> "+result.list[i].remarks+" </td>"
						+"</tr>");
				  }	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.list.length);
			pagination(100);
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

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
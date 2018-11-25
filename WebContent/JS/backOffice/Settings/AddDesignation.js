
function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
}

function myFunction()

{
	var id = $('#designationid').val().trim();

	var name = $('#designation').val().trim();

	if (name == "" || name == null)
	{
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Designation Name");
		document.getElementById("designation").style.border = "1px solid #AF2C2C";
		document.getElementById("designation").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);

		return false;
	}

	if (name.match(/^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$/i)==null)
	{
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Designation Name");

		return false;
	}

	if (name.match(/^[0-9\s]+$/i))
	{

		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Only Numbers Not Allowed in Designation Name");

		return false;
	}

	var status = false;

	datalist = {
			"name" : name,
			"id" : id
	},

	$.ajax({
		type : "POST",
		url : "adddesignation.html?method=getnamecount",
		data : datalist,
		async : false,
		success : function(data)
		{
			var result = $.parseJSON(data);
			if (result.message=="1")
			{
				$('#designation').val($("#hiddendesignation").val());
				$(".successmessagediv").hide();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Name Already Exists!!");
				setTimeout(function() {
					$('.errormessagediv1').fadeOut();
				}, 3000);
				status = false;
			}
			else if (result.message=="10")
			{
				$('#designation').val($("#hiddendesignation").val());
				$(".successmessagediv").hide();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Name Already Exists make it Active!!");
				setTimeout(function() {
					$('.errormessagediv1').fadeOut();
				}, 3000);
				status = false;
			}
			else
			{
				status = true;
			}
		}
	});
	return status;

}

$(document).ready(function(){
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchname").val("");
		InActiveDesignationList();
	});
	if($("#allstudent tbody tr").length == 0){
		 $("#allstudent tbody ").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
	}
	
	$('#back1').click(function()
		{
			window.location.href ="menuslist.html?method=designationList&status="+$("#hiddenstatus").val()+"&searchname="+$("#hiddensearchname").val();
	  });
	checkboxsselect();
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
	{
		 if($("#currentstatus").val()=="Y"){ 
			 $("#status").val("N");
			$("#inactive").text("Active");
			InActiveDesignationList();
			$("#editDesignationId").hide();
		}
		else{
			$("#status").val("Y");
			$("#inactive").text("InActive");
			InActiveDesignationList();
			$("#editDesignationId").show();
		 }
	}
	
	$("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#inactive").text("InActive");
			InActiveDesignationList();
			$("#editDesignationId").show();
		}
		else{
			$("#inactive").text("Active");
			InActiveDesignationList();
			$("#editDesignationId").hide();
		 }
		 $("#selectall").prop("checked",false);
		});

	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	$('#view').click(function()
			{
		window.location.href = "menuslist.html?method=designationList&status="+$("#hiddenstatus").val()+"&searchname="+$("#hiddensearchname").val();
			});

	$('#submit').click(function()
			{
				var id = $('#designationid').val();
				var name = $('#designation').val();
				var description = $('#description').val();
				
				if (myFunction())
				{
					datalist = {
							"name" : name,
							"description" : description,
							"id" : id
					},
					
					$.ajax({
						type : "POST",
						url : "menuslist.html?method=submit",
						data : datalist,
						success : function(data)
						 {
							var result = $.parseJSON(data);
							$('.errormessagediv').hide();

							if (result.status == "Designation Added Successfully") {
								$(".errormessagediv1").hide();
								$(".successmessagediv").show();
								/*$(".successmessagediv").attr("style","width:150%;margin-left:350px;");*/
								$(".successmessage").text("Add Designation Progressing...");
							}
							else if (result.status == "Designation Update Successfully") {
								$(".errormessagediv1").hide();
								$(".successmessagediv").show();
								$(".successmessage").text("Update Designation Progressing...");
							}
							else {
								$(".errormessagediv1").show();
								$(".validateTips1").text(result.status);
							}
							setTimeout(function(){
								window.location.href = "menuslist.html?method=designationList&status="+$("#hiddenstatus").val()+"&searchname="+$("#hiddensearchname").val();
							},3000);
						 }
					});
				}
			});

	
	// edit function

	$('#editDesignationId').click(function()
	   {
		getData = $(".select:checked").attr("id");
        var len =$(".select:checked").length;

				if (len == 0 || len > 1) {
					$(".errormessagediv").show();
					$(".validateTips").text("Select Any One Designation");
				}
				else {
					window.location.href="adddesignation.html?method=Edit&name="+getData+"&status="+$("#status").val()+"&searchname="+$("#searchname").val();
				}
			});
	 
	
	$("#inactive").click(function(){
		var count =0;
		designationId=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id");
			designationId.push(list);
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
				$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
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
				
				  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name=other id="otherreason" onclick="HideError(this)"/></div>');
		     
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
	  			}else{
	  				$("#otherreason").val("");
	  				$("#othreason").hide(); 
	  				$("#activereason").show();
	  				$("#inactivereason").hide();
	  			}
		  		});
		  	  reason = $("#inactivereason").val();
		}
	});

	$("#dialog").dialog({
		autoOpen : false,
		resizable: false,
		modal    : true,					    
		title    :'Designation Details',
		buttons  : {
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
					url : "adddesignation.html?method=Delete",
					data : {
						"designation":designationId.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
					},
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text($("#inactive").text()+" the  Designation Detail Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						} else if(result.status == "error"){
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Designation Detail is Not "+$("#inactive").text());
							$('.errormessagediv').delay(3000).slideUp();
						}
						else if(result.status == "warning"){
							$(".errormessagediv").show();
							$(".validateTips").text("Designation Details Already Mapped.");
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Designation Detail is Mapped Cannot InActive");
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							window.location.href="menuslist.html?method=designationList&currentstatus="+$("#status").val();
						},3000);

					}

				});  
				$(this).dialog("close");
				$("#selectall").prop("checked",false);
				designationId=[];
			  }
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	

	$("#search").click(function()
	{
       InActiveDesignationList();
	});
	
	 $("#searchname").keypress(function(e){
		 var key = e.which;
		 if(key==13){
			  InActiveDesignationList();
			}
	 });

	$("#xlss").click(function()
			{
				window.location.href = "adddesignation.html?method=downloadDesignationDetailsXLS";
			});

	$("#pdfDownload").click(function()
			{
				window.location.href = "adddesignation.html?method=downloadDesignationDetailsPDF";

			});
	
	if($("#hiddenstatus1").val()=="N" || $("#hiddensearchname1").val()!="")
	{
		$("#status").val($("#hiddenstatus1").val());
		$("#searchname").val($("#hiddensearchname1").val());
		if($("#status").val()=="Y"){ 
			$("#inactive").text("InActive");
		}
		else{
			$("#inactive").text("Active");
		 }
		InActiveDesignationList();
	}

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

function InActiveDesignationList(){
	
	var status=$("#status").val();
	var searchname=$("#searchname").val();

	$.ajax({
		type : 'POST',
		url : "adddesignation.html?method=InActiveDesignationList",
		data : {
			"status" :status,
			"searchname":searchname,
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
						+"<td><input type='checkbox' class='select' name='select' id='"+result.list[i].desgid+"'/></td>" 
						+"<td> "+result.list[i].desgname+" </td>"
						+"<td> "+result.list[i].desgdes+" </td>"
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

function HideError(pointer) 
{

	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";

}
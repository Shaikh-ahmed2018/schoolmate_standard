$(document).ready(function() {
	$("#Acyearid").val($("#hiddenaccyear").val());
	var pageUrl1=window.location.href.substring(window.location.href.lastIndexOf("&")+1);
	var approveUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	var rejectUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	
	
	$("#locationname").val($("#hiddenloc").val());
	
	if($("#issuedList #allstudent tbody tr").length == 0){
		$("#issuedList #allstudent tbody").append("<tr>"+"<td colspan='6' style='text-align: center;'>No Records Founds</td>" +"</tr>");
		$('.pagebanner').hide();
		$('.pagelinks').hide();
	}
	
	// Code for tabs
	if($("#historytabstatus1").val()=="" || $("#historytabstatus1").val()==null 
			|| $("#historytabstatus1").val()==undefined){
		tabstatus="issuetabstatus";	
	}else{
		tabstatus=$("#historytabstatus1").val();
	}
	
	
	$("#addissue").click(function(){
		window.location.href="menuslist.html?method=admissionaddmenu&historysearch="+$("#searcissue").val()+
		"&historytabstatus="+tabstatus;
	});
	 
	$("#issuedtab").click(function(){
		$("#addissue").show();
		addissueddetails("%%");
		tabstatus="issuetabstatus";
	});
	
	$("#approvedtab").click(function(){
		$("#addissue").hide();
		addapproveddetails("%%");
		tabstatus="approvetabstatus";
	});
	
	$("#cancelledtab").click(function(){
		$("#addissue").hide();
		addcanceldetails("%%");
		tabstatus="canceltabstatus";
	});
	
	$("#rejectedtab").click(function(){
		$("#addissue").hide();
		addrejectdetails("%%");
		tabstatus="rejecttabstatus";
	});
	
	$("#submittedtab").click(function(){
		$("#addissue").hide();
		addisubmitdetails("%%");
		tabstatus="submittabstatus";
	});
	
	$("#processedtab").click(function(){
		$("#addissue").hide();
		addprocesseddetails("%%");
		tabstatus="processtabstatus";
	});
	
	// code for highlighting tabs
	if(pageUrl1=="Approved"){
		$("#approvedlink").attr("style","display:block");
		$(".tab-pane").not("#approvedlink").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#approvedlink']").parent("li").addClass("active");
		addapproveddetails("%%");
	}
	if(pageUrl1=="Rejected"){
		$("#rejected").attr("style","display:block");
		$(".tab-pane").not("#rejected").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#rejected']").parent("li").addClass("active");
		addrejectdetails("%%");
	}
     if(pageUrl1=="Cancelled"){
		$("#cancelled").attr("style","display:block");
		$(".tab-pane").not("#cancelled").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#cancelled']").parent("li").addClass("active");
		addcanceldetails("%%");
	  }
     if(pageUrl1=="Submitted"){
 		$("#submitted").attr("style","display:block");
 		$(".tab-pane").not("#submitted").attr("style","display:none");
 		$("li").removeClass("active");
 		$("li").find("a[href='#submitted']").parent("li").addClass("active");
 		addisubmitdetails("%%");
 	  }

     //
     
     getStream($("#locationname"));
 	$("#locationname").change(function(){
 		$("#stream").val("");
 		$("#class").val("");
 		getStream($(this));
 	});
 	
 	$("#stream").change(function(){
 		getClassList($(this));
 	});
     
     if(approveUrl.split("=")[1]=="ApprformDetails&curenquiryid"){
    	 $("#location").val($("#locname").val());
    	 getStream($("#location"));
    	 $("#stream").val($("#hiddenStreamId").val());
    	 getClassList($("#hiddenStreamId"));
    	 $("#class").val($("#updateClassCode").val());
    	 $("#Acyearid").val($("#accId").val());
     }else if(approveUrl.split("=")[1]=="EditissuedList&enquiryid"){
    	 $("#locationname").val($("#locname").val());
    	 getStream($("#locationname"));
    	 $("#stream").val($("#strname").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#classid").val());
    	 $("#Acyearid").val($("#accId").val());
     }
     else if(approveUrl.split("=")[1]=="issuedformEdit&curenquiryid"){
    	 $("#locationname").val($("#hiddenloc").val());
    	 getStream($("#locationname"));
    	 $("#stream").val($("#hiddenStreamId").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#updateClassCode").val());
    	 $("#Acyearid").val($("#hiddenaccyearId").val());
     }
     else if(approveUrl.split("=")[1]=="RejectformDetails&curenquiryid"){
    	 $("#locname").val($("#updatelocationname").val());
    	 getStream($("#locname"));
    	 $("#stream").val($("#strname").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#classid").val());
    	 $("#Acyearid").val($("#accId").val());
     }
     else if(approveUrl.split("=")[1]=="CancelformDetails&curenquiryid"){
    	 $("#locname").val($("#updatelocationname").val());
    	 getStream($("#locname"));
    	 $("#stream").val($("#strname").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#classid").val());
    	 $("#Acyearid").val($("#accId").val());
     }else if(approveUrl.split("=")[1]=="submitformDetails&curenquiryid"){
    	 $("#locname").val($("#updatelocationname").val());
    	 getStream($("#locname"));
    	 $("#stream").val($("#strname").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#classid").val());
    	 $("#Acyearid").val($("#accId").val());
     }else if(approveUrl.split("=")[1]=="processformDetails&curenquiryid"){
    	 $("#locname").val($("#updatelocationname").val());
    	 getStream($("#locname"));
    	 $("#stream").val($("#strname").val());
    	 getClassList($("#stream"));
    	 $("#class").val($("#classid").val());
    	 $("#Acyearid").val($("#accId").val());
     }
     
     
    // code for toggle tabs
	$(".issued").click(function(){
		$("#issuedOne").slideToggle();
	});
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	$(".reject").click(function(){
		$("#rejectedOne").slideToggle();
	});
	
	$(".cancelled").click(function(){
		$("#cancelledOne").slideToggle();
	});
	
	$(".submitted").click(function(){
		$("#submittedOne").slideToggle();
	});
	
	$(".processed").click(function(){
		$("#processedOne").slideToggle();
	});

	
	var hstream = $("#hiddenStreamId").val();
	$("#stu_parrelation option[value='"+$("#hiddenrelationname").val()+"']").attr('selected','true');
	
	//getStream($("#locationname"));
	//$("#stream").val($("#hiddenStreamId").val());
	 
	 
	
	//$("#class").val($("#updateClassCode").val()); 
	
	$("#stream").change(function(){

		var streamId=$("#stream").val();
		$.ajax({
			type : "GET",
			url : "registration.html?method=getClassesByStream",
			data : {"streamId":streamId},
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				$("#class").html("");
				$("#class").append(
						'<option value="' + "" + '">' + "----------Select----------"
						+ '</option>');
				for (var j = 0; j < result.ClassesList.length; j++) {

					$("#class").append(
							'<option value="'
							+ result.ClassesList[j].classId
							+ '">'
							+ result.ClassesList[j].classname
							+ '</option>');
				}
			}
		});
	});

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	
	$("#save").click(function(){
		
		var enq_code = $("#updateenquiryid").val();
		var parentfirstName=$("#parentfirstName").val();
		var parent_LastName=$("#parent_LastName").val();
		var parents_name=$("#parents_name").val();
		var parentEmailId=$("#parentEmailId").val();
		var address=$("#address").val();
		var stu_parrelation=$("#stu_parrelation").val();
		var mobile_number=$("#mobile_number").val();
		var stream=$("#stream").val();
		var classid=$("#class").val();

		if(parentfirstName.trim()=="" || parentfirstName.trim().length==0 ){
			$('.errormessagediv').show();
			$('.validateTips').text("Field  Required First Name");
		}
		/*else  if(parent_LastName.trim()=="" || parent_LastName.trim().length==0)
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Last Name");
		}*/
		else if(parentEmailId.trim()=="" ||parentEmailId.trim().length==0)
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Email");
		}
		else if (!parentEmailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)) {
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email");
		}
		else if($("#locationname").val() == ""){
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Location");
		}
		else if(stream=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Stream");
		}
		else if(classid=="")
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Class");
		}

		else if(mobile_number.length<10)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Enter 10 digit Mobile no");
			$("#mobile_number").focus();
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}

		else if (!mobile_number.match(/^(?!0{6})([0-9])+$/i)) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Mobile");
			$("#mobile_number").focus();
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}
		else if(stu_parrelation == "" || stu_parrelation == null){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Select the Relationship");
			$("#stu_parrelation").focus();
			document.getElementById("stu_parrelation").style.border = "1px solid #AF2C2C";
			document.getElementById("stu_parrelation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			},100);
		}
		else
		{
			var datalist = {
					"parentfirstName" : parentfirstName,
					"parent_LastName" : parent_LastName,
					"parents_name" : parents_name,
					"parentEmailId" : parentEmailId,
					"address" : address,
					"stu_parrelation" : stu_parrelation,
					"mobile_number" : mobile_number,
					"stream" : stream,
					"classid" : classid,
					"enq_code" : enq_code,
					"Acyearid":$("#Acyearid").val()
			};

			$.ajax({
				type : "POST",
				url : "menuslist.html?method=updateissuelist",
				data : datalist,
				success : function(data) {
					var result = $.parseJSON(data);
					$(".errormessagediv").hide();
					if(result.status == "updated successfully"){
						$('.errormessagediv').hide();
				        $(".successmessagediv").show();
						$(".validateTips").text("Updating Record Progressing...");
						setTimeout(function(){
							window.location.href="menuslist.html?method=tempadmissionMenu&historysearch="+$("#historysearch").val()
							   +"&historytabstatus="+$("#historytabstatus").val();
                        },3000);
					}
				}



			});
		}

			});

	
	$("#back2").click(function(){
		window.location.href="menuslist.html?method=tempadmissionMenu";
	});
	
	$("#back").click(function(){
		window.location.href="menuslist.html?method=tempadmissionMenu";
	}); 

	$("#cancelled").click(function(){
		
        $("#dialog3").dialog("open");
		$("#dialog3").empty();
		$("#dialog3").append("<p>Are you sure to Cancel?</p>");
		$("#dialog3").append('<label for="">Reason:</label>');
		$("#dialog3").append('<select name="canreason" style="width: 100%;" id="canreason">'
				+ '<option value="">' + "----------select----------"
				+ '</option>'
				+ '<option value="submitted delay">' + "Submitted delay"
				+ '</option>'
				+ '<option value="date closed">' + "Date closed" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>');
		  $("#dialog3").append('<div id="othreason"+ <label for="">OtherReason:</label>'+'<input type="text" style="width: 100%;" name=other id="otherreason"/></div>');
     
  		  $("#othreason").hide();
  		  
  		  $('#canreason').change(function(){
  			$(".errormessagediv").hide();
  			var othersres=$('#canreason').val();
  			if(othersres == 'others'){
  				$("#othreason").show();
  			}else{
  				$("#otherreason").val("");
  				$("#othreason").hide();
  				$("#canreason").show();
  			}
  		});
  	  reason = $("#canreason").val();
	});


	$("#dialog3").dialog({

		autoOpen: false,
		modal: true,
		title:'Cancel Details',
		buttons : {
			"Yes" : function() {
                  var cancelreason=$("#canreason").val();
                  var otherreason=$("#otherreason").val();
                  if(cancelreason.trim()== "" || cancelreason==null)
                	  {
                	    $(".errormessagediv").show();
		     		    $(".validateTips").text("Select Any One Reason");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
                	  }
                  else if(cancelreason=="others" &&otherreason.trim() == ""){
                	   $(".errormessagediv").show();
		     		    $(".validateTips").text("Field Required OtherReason");
		     		    setTimeout(function() {
		  				$('#errormessagediv').fadeOut();
		  			    },3000);
                  }
                  
                  else
                	  {
                	  
                	  
				      datalist ={
						"enquiryIdlist" :enquiryIdlist,
						"resn":$("#canreason").val(),
						"cancelreasn":$("#otherreason").val(),
						"approveSt" : $("#canreason option:selected").text()
				};

				$.ajax({

					type : "POST",
					url :"parentrequiresappointment.html?method=insertcancelledlist",
					data : datalist,
					success : function(data)
					{ 
						var result = $.parseJSON(data);
						$(".errormessagediv").hide();
						if(result.status == "Cancelled"){
							$(".successmessagediv").show();
							$(".validateTips").text("Cancelling Record Progressing...");
							setTimeout(function(){
								window.location.href="menuslist.html?method=tempadmissionMenu&"+result.status
								+"&historysearch="+$("#historysearch").val()+"&historytabstatus="+$("#historytabstatus").val();
							},4000);
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
	
	$(".buttonstyle").click(function(){
		var currentid= this.id;
		var curenquiryid =currentid;
		window.location.href="menuslist.html?method=issuedformEdit&curenquiryid="+curenquiryid;
	});

	$("#edit").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;

		$('input[type="checkbox"]:checked').map(function() {
			getData = $(this).attr("id");
			cnt++;
	    });

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select One Record");
			return false;
		} 
		else
		{
			var enquiryid = getData;
			window.location.href ="menuslist.html?method=EditissuedList&enquiryid="+enquiryid+
			"&historysearch="+$("#searcissue").val()+"&historytabstatus="+tabstatus;
		 }
	  });

	$("#save3").click(function()
			{
		window.location.href="menuslist.html?method=updateissuelist";
			});
	
	$("#back1").click(function()
	 {
	   window.location.href="menuslist.html?method=tempadmissionMenu&historysearch="+$("#historysearch").val()
	   +"&historytabstatus="+$("#historytabstatus").val();	
	 });

	
	var enquiryIdlist=$("#updateenquiryid").val();

	$("#approve").click(function(){
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are you sure to Approve?</p>");
		$("#dialog").append('<label for="">Reason:</label>');
		$("#dialog").append('<select name="reason" style="width: 100%;" id="reason">'
                + '<option value="">' + "----------select----------"
        		+ '</option>'
				+ '<option value="selectedcandidate">' + "Selected Candidate" 
				+ '</option>'
				+ '<option value="ready for next approval">' + "Ready for next approval" 
				+ '</option>'
				+ '<option value="others">' + "Others" 
				+ '</option>'+
		'</select>') ;
		+'<br/><br/><br/>';
		$("#dialog").append('<div id="othreason"+ <label for="">OtherReason:</label>'+'<input type="text" style="width: 100%;" name=other id="otherreason"/></div>');
		
		$("#othreason").hide();
		$('#reason').change(function(){
			$(".errormessagediv").hide();
			var othersres=$('#reason').val();
			if(othersres == 'others'){
				$("#othreason").show();
			}else{
				$("#otherreason").val("");
				$("#othreason").hide();
				$("#reason").show();
			}
		});
	    reason = $("#reason").val();
	});


	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,
		title:'Approve Details',
		buttons : {
			"Yes" : function() {
				var reasonname=$('#reason').val();
				var othreason=$("#otherreason").val();
			  if(reasonname =="" || reasonname == null)
          	   {
          	   $(".errormessagediv").show();
     		   $(".validateTips").text("Select Any One Reason");
     		    setTimeout(function() {
  				$('#errormessagediv').fadeOut();
  			    }, 100);
     		   return false;
          	   }
			  else if( reasonname == "others" && othreason.trim() == ""){
				 
				  $(".errormessagediv").show();
	     		   $(".validateTips").text("Field Required Other Reason");
	     		    setTimeout(function() {
	  				$('#errormessagediv').fadeOut();
	  			    }, 100);
	     		    return false;
			  }
               else
            	 
        	       {
			        datalist ={
					"enquiryIdlist" :enquiryIdlist,
					"resn":$("#reason").val(),
					"othereason":$("#otherreason").val(),
					"mobile_number":$("#mobile_number").val(),
					 "locationname":$("#locationname").val(),
					 "approveSt" : $("#reason option:selected").text(),
			       };
				$.ajax({
					type : "POST",
					url :"parentrequiresappointment.html?method=insertapprovedlist",
					data : datalist,
					beforeSend: function() {
						$('#loader').show();
					},
					success : function(data)
					{ 
						var result = $.parseJSON(data);
						$(".errormessagediv").hide();
						if(result.status == "Approved"){
							$('#loader').hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Approving Record Progressing...");
							setTimeout(function(){
								window.location.href="menuslist.html?method=tempadmissionMenu&tab="+result.status
								+"&historysearch="+$("#historysearch").val()+"&historytabstatus="+$("#historytabstatus").val();
							},4000);
						
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
	
	
	//code for search option
	$("#issuesearch").click(function() {
		var value="%%";
		if($("#searcissue").val().trim() == "")
			value = "%%"
		else
			value = $("#searcissue").val().trim();
		addissueddetails(value);
	});
	$("#searchpess").click(function() {
		var value="%%";
		if($("#searchprocess").val() == "")
			value = "%%"
		else
			value = $("#searchprocess").val();
		addprocesseddetails(value);
	});
	
	$("#searchsubmit").click(function() {
		var value="%%";
		if($("#searchdsbmit").val() == "")
			value = "%%"
		else
			value = $("#searchdsbmit").val();
		addisubmitdetails(value);
	});
	
	$("#searchcacl").click(function() {
		var value="%%";
		if($("#searchcancelled").val().trim() == "")
			value = "%%"
		else
			value = $("#searchcancelled").val().trim();
		addcanceldetails(value);
	});
	
	$("#searchrjct").click(function() {
		var value="%%";
		if($("#searchreject").val().trim() == "")
			value = "%%"
		else
			value = $("#searchreject").val().trim();
		addrejectdetails(value);
	});
	
	$("#apprsearch").click(function() {
		var value="%%";
		if($("#searchapprove").val().trim() == "")
			value = "%%"
		else
			value = $("#searchapprove").val().trim();
		addapproveddetails(value);
	});

	var enquiryIdlist=$("#updateenquiryid").val();
		
	$("#reject").click(function(){
			$("#dialog1").dialog("open");
			$("#dialog1").empty();
			$("#dialog1").append("<p>Are you sure to Reject?</p>");
			$("#dialog1").append('<label for="">Reason:</label>');
            $("#dialog1").append('<select name="reason" style="width: 100%;" id="rejectreason">'
            		+ '<option value="">' + "----------select----------"
    				+ '</option>'
					+ '<option value="poorperformance">' + "Poor Performance" 
					+ '</option>'
					+ '<option value="admission closed">' + "Admission Closed" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');
            $("#dialog1").append('<div id="othreason1"+ <label for="">OtherReason:</label>'+'<input type="text" style="width: 100%;" name=other id="otherreason1"/></div>');
            $("#othreason1").hide();
    		$('#rejectreason').change(function(){
    			$(".errormessagediv").hide();
    			var othersres=$('#rejectreason').val();
    			if(othersres == 'others'){
    				$("#othreason1").show();
    			}else{
    				$("#otherreason1").val("");
    				$("#othreason1").hide();
    				$("#rejectreason").show();
    			}
    		});
    	    reason = $("#rejectreason").val();
    	});

	
		$("#dialog1").dialog({		
			autoOpen: false,
			modal: true,
			title:'Reject Details',
			buttons : {
				"Yes" : function() {
				var rejreason=	$("#rejectreason").val();
				var othreason=$("#otherreason1").val();
	              if(rejreason =="" || rejreason == null)
			          	   {
			          	   $(".errormessagediv").show();
			     		   $(".validateTips").text("Select Any One Reason");
			     		    setTimeout(function() {
			  				$('#errormessagediv').fadeOut();
			  			    }, 100);
			          	   }
						
					else if(rejreason == "others" && othreason.trim() == ""){
						$(".errormessagediv").show();
			     		   $(".validateTips").text("Field Required OtherReason");
			     		    setTimeout(function() {
			  				$('#errormessagediv').fadeOut();
			  			    }, 100);
					}
					else
						{
					datalist ={
							"enquiryIdlist" :enquiryIdlist,
							"resn":$("#rejectreason").val(),
							"othereason":$("#otherreason1").val(),
							"approveSt" : $("#rejectreason option:selected").text()
					};
					$.ajax({
						type : "POST",
						url :"parentrequiresappointment.html?method=insertrejectedlist",
						data :datalist,
						beforeSend: function() {
							$('#loader').show();
						},
						success : function(data){
							var result = $.parseJSON(data);
							$(".errormessagediv").hide();
							if(result.status == "Rejected"){
								$('#loader').hide();
								$(".successmessagediv").show();
								$(".validateTips").text("Rejecting Record Progressing...");
								setTimeout(function(){
									window.location.href="menuslist.html?method=tempadmissionMenu&"+result.status
									+"&historysearch="+$("#historysearch").val()+"&historytabstatus="+$("#historytabstatus").val();
								},4000);
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
	
	$("#stream").change(function(){

		var streamId=$("#stream").val();

		$.ajax({

			type : "GET",
			url : "registration.html?method=getClassesByStream",
			data : {"streamId":streamId},
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				$("#class").html("");
				$("#class").append(
						'<option value="' + "" + '">' + "----------Select----------"
						+ '</option>');
				for (var j = 0; j < result.ClassesList.length; j++) {

					$("#class").append(
							'<option value="'
							+ result.ClassesList[j].classId
							+ '">'
							+ result.ClassesList[j].classname
							+ '</option>');
				}

			}

		});

	});
	
	$(".nav-tabs li a").click(function(){

		$(".tab-pane").attr("style","display:none");
		$($(this).attr("href")).attr("style","display:block");

	});
	$(".successmessagediv").hide();
	$(".errormessagediv1").hide();
	$(".successmessagediv").hide();


	$('#save2').click (function()
			{
	
		$('#save2').hide();
		var parentfirstName = $('#parentfirstName').val();
		var parent_LastName = $('#parent_LastName').val(); 
		var mobile_number = $('#mobile_number').val(); 
		var address = $('#address').val();
		var parentEmailId = $('#parentEmailId').val();
		var stream=$('#stream').val();
		var classid=$('#class').val();
		var parents_name=$('#parents_name').val();
		var stu_par_relation=$('#stu_parrelation').val();
		var location = $("#locationname").val();
		
		 if(location == "" || location == null || location == undefined)
			{
				$('#save2').show();
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required School");
				document.getElementById("locationname").style.border = "1px solid #AF2C2C";
				document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('#errormessagediv').fadeOut();
				}, 3000);

			}
		 else if(stream == "" || stream == null)
		{
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Stream");
			document.getElementById("stream").style.border = "1px solid #AF2C2C";
			document.getElementById("stream").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(classid == "" || classid == null)
		{
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class");
			$("#class").focus();
            document.getElementById("class").style.border = "1px solid #AF2C2C";
			document.getElementById("class").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}
		
		else if (parentfirstName == "" || parentfirstName == null) {
			$('#save2').show();
			$(".errormessagediv").show();

			$(".validateTips").text("Enter First Name");
			$("#parentfirstName").focus();
			document.getElementById("parentfirstName").style.border = "1px solid #AF2C2C";
			document.getElementById("parentfirstName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
				document.getElementById("parentfirstName").style.border = "1px solid #ccc";
				document.getElementById("parentfirstName").style.backgroundColor = "#fff";
			}, 3000);
		}

		else if (!parentfirstName.match(/[A-Za-z]+$/i)) {
			$('#save2').show();
			$('.errormessagediv').show();
			$('.validateTips').text("Enter a Valid Student First Name");
			$("#parentfirstName").focus();
			document.getElementById("parentfirstName").style.border = "1px solid #AF2C2C";
			document.getElementById("parentfirstName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
				document.getElementById("parentfirstName").style.border = "1px solid #ccc";
				document.getElementById("parentfirstName").style.backgroundColor = "#fff";
			}, 3000);

		}

		/*else if (parent_LastName == "" || parent_LastName == null) {
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Last Name");
			$("#parent_LastName").focus();
			document.getElementById("parent_LastName").style.border = "1px solid #AF2C2C";
			document.getElementById("parent_LastName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);



		}

		else if (!parent_LastName.match(/[A-Za-z]+$/i)) {
			$('#save2').show();
			$('.errormessagediv').show();
			$('.validateTips').text("Enter a Valid Student Last Name");
			$("#parent_LastName").focus();
			document.getElementById("parent_LastName").style.border = "1px solid #AF2C2C";
			document.getElementById("parent_LastName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}*/

		else if(parents_name == "" || parents_name == null)
		{
			$('#save2').show();
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Parents Name");
			$("#parents_name").focus();
			document.getElementById("parents_name").style.border = "1px solid #AF2C2C";
			document.getElementById("parents_name").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
				document.getElementById("parents_name").style.border = "1px solid #ccc";
				document.getElementById("parents_name").style.backgroundColor = "#fff";
			}, 3000);
		}

		else if(stu_par_relation == "")
		{
			$('#save2').show();
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Relationship");
			$("#stu_parrelation").focus();
			document.getElementById("stu_parrelation").style.border = "1px solid #AF2C2C";
			document.getElementById("stu_parrelation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
				document.getElementById("stu_parrelation").style.border = "1px solid #ccc";
				document.getElementById("stu_parrelation").style.backgroundColor = "#fff";
			}, 3000);
		}
		else if(parentEmailId == "" || parentEmailId == null)
		{
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Email");
			$("#parentEmailId").focus();
			document.getElementById("parentEmailId").style.border = "1px solid #AF2C2C";
			document.getElementById("parentEmailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}
		else if (!parentEmailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/)) {
			$('#save2').show();
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email.");
			$("#parentEmailId").focus();
			document.getElementById("parentEmailId").style.border = "1px solid #AF2C2C";
			document.getElementById("parentEmailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}
		else if (mobile_number == "" || mobile_number == null) {
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number");
			$("#mobile_number").focus();
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
		else if(mobile_number.length<10)
		{
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Enter 10 digit Mobile No.");
			$("#mobile_number").focus();
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}

		else if (!mobile_number.match(/^(?!0{6})([0-9])+$/i)) {
			$('#save2').show();
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Mobile No.");
			$("#mobile_number").focus();
			document.getElementById("mobile_number").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile_number").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);
		}
		else if(address == "" || address == null)
		{
			$('#save2').show();
			$(".errormessagediv").show();

			$(".validateTips").text("Field Required Address");
			$("#address").focus();


			document.getElementById("address").style.border = "1px solid #AF2C2C";
			document.getElementById("address").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			}, 3000);

		}
            
		else
		{
			var datalist = {
					"parentfirstName" : parentfirstName,
					"parent_LastName" : parent_LastName,
					"parents_name" : parents_name,
					"parentEmailId" : parentEmailId,
					"address" : address,
					"stu_par_relation" : stu_par_relation,
					"mobile_number" : mobile_number,
					"stream" : stream,
					"classid" : classid,
					"locationid" : location,
					"Acyearid":$("#Acyearid").val()
			};


			$.ajax({

				type : "POST",
				url : "registration.html?method=InsertNewRegistrationUser",
				data : datalist,
				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status == "true"){
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
						setTimeout(function(){
							$(".issuedList").empty();
							window.location.href="menuslist.html?method=tempadmissionMenu&historysearch="+$("#historysearch").val()
							 +"&historytabstatus="+$("#historytabstatus").val();
						},4000);
					}
				}
			});
		}
	});
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);

	if(pageUrl.split("&")[0]=="method=InsertNewRegistrationUser"){

		$(".successmessagediv").show();
		setTimeout(function(){
			window.location.href="menuslist.html?method=tempadmissionMenu";
		},2500);
	}

	//getReload();
	var reg=window.location.href.substr(window.location.href.lastIndexOf('&')+1);
	var splitr=reg.split('=');
	var checker=splitr[0];
	if(checker=='re_enter_password'){
		$(".successmessagediv").show();
		$(".successmessagediv").show();
		$(".successmessagediv").delay(2000).fadeOut("slow");
	}
	
	$("#print").click(function(){
		printApplication();
	});
	
	
	if($("#historytabstatus1").val()!=""){
		
		if($("#historysearch1").val()=="" || $("#historysearch1").val()==undefined){
			searchstatus="";
		}else{
			searchstatus=$("#historysearch1").val();
		}
		
	if($("#historytabstatus1").val()=="issuetabstatus")
	{
		$("#addissue").show();
		$("#searcissue").val(searchstatus);
		
		$("#issued").attr("style","display:block");
		$(".tab-pane").not("#issued").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#issued']").parent("li").addClass("active");
		
		addissueddetails($("#searcissue").val());
	}
	else if($("#historytabstatus1").val()=="approvetabstatus")
	{
		$("#addissue").hide();
		$("#searchapprove").val(searchstatus);
		
		$("#approvedlink").attr("style","display:block");
		$(".tab-pane").not("#approvedlink").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#approvedlink']").parent("li").addClass("active");
	
		addapproveddetails($("#searchapprove").val());
		 
	}
	else if($("#historytabstatus1").val()=="rejecttabstatus")
	{
		$("#addissue").hide();
		$("#searchreject").val(searchstatus);
		
		$("#rejected").attr("style","display:block");
		$(".tab-pane").not("#rejected").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#rejected']").parent("li").addClass("active");
	
		addrejectdetails($("#searchreject").val());
	}
	else if($("#historytabstatus1").val()=="canceltabstatus")
	{
		$("#addissue").hide();
		$("#searchcancelled").val(searchstatus);
		
		$("#cancelled").attr("style","display:block");
		$(".tab-pane").not("#cancelled").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#cancelled']").parent("li").addClass("active");
	
		addcanceldetails($("#searchcancelled").val());
	}
	else if($("#historytabstatus1").val()=="processtabstatus")
	{
		$("#addissue").hide();
		$("#searchprocess").val(searchstatus);
		
		$("#processed").attr("style","display:block");
		$(".tab-pane").not("#processed").attr("style","display:none");
		$("li").removeClass("active");
		$("li").find("a[href='#processed']").parent("li").addClass("active");
	
		addprocesseddetails($("#searchprocess").val());
	}
 }
	

});

function printApplication(){
	
	 var a = $("#printing-css-a4").val();
	 var b = document.getElementById("container").innerHTML;
	 var abd = '<style>' + a +'</style>' + b;
	    
	 var frame1 = $('<iframe />');
	 frame1[0].name = "frame1";
	 frame1.css({ "position": "absolute", "top": "-1000000px" });
	 $("body").append(frame1);
	 var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	 frameDoc.document.open();
	 //Create a new HTML document.
	 frameDoc.document.write('<html><head><title>DIV Contents</title>');
	 //Append the external CSS file.
	 frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
	 frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
	 frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
	 frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
	 frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
	 frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
	 frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
	 frameDoc.document.write('</head><body>');
 
	 //Append the DIV contents.
	 frameDoc.document.write(abd);
	 frameDoc.document.write('</body></html>');
	 frameDoc.document.close();
	 setTimeout(function () {
		 window.frames["frame1"].focus();
		 window.frames["frame1"].print();
		 frame1.remove();
	 }, 100);
}

function HideError() {

	document.getElementById("parentfirstName").style.border = "1px solid #ccc";
	document.getElementById("parentfirstName").style.backgroundColor = "#fff";

	document.getElementById("parent_LastName").style.border = "1px solid #ccc";
	document.getElementById("parent_LastName").style.backgroundColor = "#fff";

	document.getElementById("mobile_number").style.border = "1px solid #ccc";
	document.getElementById("mobile_number").style.backgroundColor = "#fff";

	document.getElementById("parents_name").style.border = "1px solid #ccc";
	document.getElementById("parents_name").style.backgroundColor = "#fff";

	document.getElementById("parentEmailId").style.border = "1px solid #ccc";
	document.getElementById("parentEmailId").style.backgroundColor = "#fff";
	
    document.getElementById("address").style.border = "1px solid #ccc";
	document.getElementById("address").style.backgroundColor = "#fff";
	
	document.getElementById("stu_parrelation").style.border = "1px solid #ccc";
	document.getElementById("stu_parrelation").style.backgroundColor = "#fff";

	document.getElementById("stream").style.border = "1px solid #ccc";
	document.getElementById("stream").style.backgroundColor = "#fff";

	document.getElementById("classid").style.border = "1px solid #ccc";
	document.getElementById("classid").style.backgroundColor = "#fff";

}

	function getStream(pointer){
 
		$.ajax({
			url : "classPath.html?method=getStreamDetailAction",
			async : false,
			data:{'school':pointer.val()},
			success : function(data) {
			
			var result = $.parseJSON(data);
			$('#stream').empty();
			$('#stream').append('<option value="">----------Select----------</option>');
				for ( var j = 0; j < result.streamList.length; j++) {
					$('#stream').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
				}
			}
		});
		
	}

	function getClassList(pointer){
		var streamId=pointer.val();

		datalist={
				"streamId" : streamId
		},
		$.ajax({
			type : 'POST',
			url : "reportaction.html?method=getClassesByStream",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$('#class').html("");
                 
				$('#class').append('<option value="All">' + "----------Select----------"	+ '</option>');
				for ( var j = 0; j < result.ClassesList.length; j++) {

					$('#class').append('<option value="'+result.ClassesList[j].classId+'">'+result.ClassesList[j].classname+'</option>');
				}
			}
		});
	}

	function addapproveddetails(searchtext){
		$('.pagebanner').show();
		$('.pagelinks').show();
		$("#approvedList").empty();
		$("#issuedList").empty();
		$("#rejectlist").empty();
		$("#cancellist").empty();
		$("#submittedlist").empty();
		$("#processedlist").empty();
		
		$("#approvedList").append('<table class="table" id="allstudent" class="approvedList">'+
			'<thead><tr>'+
			'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
			'<th>Father Name</th>'+
			'<th>Email</th>'+
			'<th>Mobile No.</th>'+
			'<th>Reasons</th>'+
			'</tr>'+
			'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getApprovedList",
			data : {"searchtext" : $("#searchapprove").val()},
			async : false,
			success : function(response) {
			 var result = $.parseJSON(response);
			 var i = 0;
			 var len = result.approvedlist.length;
			 
				if(len > 0){
				for(i = 0;i<len;i++){
					$("#allstudent tbody").append("<tr>"+
						"<td><a class='anchor'><input type='button' class='apprbuttonstyle' id='"+result.approvedlist[i].enquiryid+"' value='"+result.approvedlist[i].fullname+"' style='button-color: bll' /></a></td>"+
						"<td>"+result.approvedlist[i].parents_name+"</td>"+
						"<td>"+result.approvedlist[i].parentEmailId+"</td>"+
						"<td>"+result.approvedlist[i].mobile_number+"</td>"+
						"<td>"+result.approvedlist[i].reason+"</td>"+
						"</tr>");
				 }
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				
				pagination(100);
				$('.page').prop("class","page active");
				$('.page').css({"display":"inline"});
				$(".apprbuttonstyle").click(function(){
					var currentid= this.id;
					var curenquiryid =currentid;
					window.location.href="menuslist.html?method=ApprformDetails&curenquiryid="+curenquiryid+
					"&historysearch="+$("#searchapprove").val()+"&historytabstatus="+tabstatus;
			
				});
		
			}
			
		});
	
	}

function addissueddetails(searchtext){
	$('.pagebanner').show();
	$('.pagelinks').show();
	$("#approvedList").empty();
	$("#issuedList").empty();
	$("#rejectlist").empty();
	$("#cancellist").empty();
	$("#submittedlist").empty();
	$("#processedlist").empty();
		
		$("#issuedOne #issuedList").append('<table class="table" id="allstudent" class="issuedList">'+
			'<thead><tr>'+
			'<th>Select</th>'+
			'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
			'<th>Father Name</th>'+
			'<th>Email</th>'+
			'<th>Mobile No.</th>'+
			'<th>Address</th>'+
			'</tr>'+
			'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getIssuedList",
			data :{"searchtext" : $("#searcissue").val()},
			async : false,
			success : function(response) {

			 var result = $.parseJSON(response);
			 var len=result.issuedlist.length;
			 var i = 0;
			if(len > 0){
			for(i = 0;i<len;i++){
				
			$("#allstudent tbody").append("<tr>"+
					"<td style='text-align: center;'><input type='checkbox' name='select' class='select' style='text-align: center' id='"+result.issuedlist[i].enquiryid+"' /></td>"+
					"<td><a class='anchor'><input type='button' class='buttonstyle' id='"+result.issuedlist[i].enquiryid+"' value='"+result.issuedlist[i].fullname+"' style='button-color: bll' /></a></td>"+
					"<td>"+result.issuedlist[i].parents_name+"</td>"+
					"<td>"+result.issuedlist[i].parentEmailId+"</td>"+
					"<td>"+result.issuedlist[i].mobile_number+"</td>"+
					"<td>"+result.issuedlist[i].address+"</td>"+
					"</tr>");
			}
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='6' style='text-align: center;'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
			
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				
				$(".buttonstyle").click(function()
				 {
					var currentid= this.id;
					var curenquiryid =currentid;
					window.location.href="menuslist.html?method=issuedformEdit&curenquiryid="+curenquiryid+
					"&historysearch="+$("#searcissue").val()+"&historytabstatus="+tabstatus;

				 });
			}
		});
	}
	
function addrejectdetails(searchtext){
	$('.pagebanner').show();
	$('.pagelinks').show();
	$("#approvedList").empty();
	$("#issuedList").empty();
	$("#rejectlist").empty();
	$("#cancellist").empty();
	$("#submittedlist").empty();
	$("#processedlist").empty();
		
		
		$("#rejectedOne #rejectlist").append('<table class="table" id="allstudent" class="rejectlist">'+
			'<thead><tr>'+
			'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
			'<th>Father Name</th>'+
			'<th>Email</th>'+
			'<th>Mobile No.</th>'+
			'<th>Reasons</th>'+
			'</tr>'+
			'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getRejectedList",
			async : false,
			data :{"searchtext" : $("#searchreject").val()},
			success : function(response) {

			var result = $.parseJSON(response);
			
			var len=result.rejectlist.length;
			var i = 0;
			if(len > 0){
			for(i = 0;i<len;i++){
				
				$("#allstudent tbody").append("<tr>"+
						"<td><a class='anchor'><input type='button' class='rjtbuttonstyle' id='"+result.rejectlist[i].enquiryid+"' value='"+result.rejectlist[i].fullname+"' style='button-color: bll' /></a></td>"+
						"<td>"+result.rejectlist[i].parents_name+"</td>"+
						"<td>"+result.rejectlist[i].parentEmailId+"</td>"+
						"<td>"+result.rejectlist[i].mobile_number+"</td>"+
						"<td>"+result.rejectlist[i].rejectreason+"</td>"+
						"</tr>");
				}
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='5' style='text-align: center;'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
				pagination(100);
				$('.page').prop("class","page active");
				$('.page').css({"display":"inline"});
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				
				$(".rjtbuttonstyle").click(function()
						{
					var currentid= this.id;
					var curenquiryid =currentid;
					window.location.href="menuslist.html?method=RejectformDetails&curenquiryid="+curenquiryid+
					"&historysearch="+$("#searchreject").val()+"&historytabstatus="+tabstatus;

						});
			}
		});
	}
	
function addcanceldetails(searchtext){
	$('.pagebanner').show();
	$('.pagelinks').show();
	$("#approvedList").empty();
	$("#issuedList").empty();
	$("#rejectlist").empty();
	$("#cancellist").empty();
	$("#submittedlist").empty();
	$("#processedlist").empty();
		
		$("#cancelledOne #cancellist").append('<table class="table" id="allstudent" class="cancellist">'+
		'<thead><tr>'+
		'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
		'<th>Father Name</th>'+
		'<th>Email</th>'+
		'<th>Mobile No.</th>'+
		'<th>Reasons</th>'+
		'</tr>'+
		'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getCancelList",
			data : {"searchtext":$("#searchcancelled").val()},
			async : false,
			success : function(response) {

			var result = $.parseJSON(response);
			var len=result.cancellist.length;
			var i = 0;
			if(len > 0){
			for(i = 0;i<len;i++){
				
				$("#allstudent tbody").append("<tr>"+
						"<td><a class='anchor'><input type='button' class='cancelbuttonstyle' id='"+result.cancellist[i].enquiryid+"' value='"+result.cancellist[i].fullname+"' style='button-color: bll' /></a></td>"+
						"<td>"+result.cancellist[i].parents_name+"</td>"+
						"<td>"+result.cancellist[i].parentEmailId+"</td>"+
						"<td>"+result.cancellist[i].mobile_number+"</td>"+
						"<td>"+result.cancellist[i].cancelreason+"</td>"+
						"</tr>");
				}
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='5' style='text-align: center;'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				$('.page').prop("class","page active");
				$('.page').css({"display":"inline"});
				 $(".cancelbuttonstyle").click(function(){
						var currentid= this.id;
						var curenquiryid =currentid;
						window.location.href="menuslist.html?method=CancelformDetails&curenquiryid="+curenquiryid+
						"&historysearch="+$("#searchcancelled").val()+"&historytabstatus="+tabstatus;

							});
			}
		});
	}
	
function addisubmitdetails(searchtext){
	$('.pagebanner').show();
	$('.pagelinks').show();
	$("#approvedList").empty();
	$("#issuedList").empty();
	$("#rejectlist").empty();
	$("#cancellist").empty();
	$("#submittedlist").empty();
	$("#processedlist").empty();
		$("#submittedOne #submittedlist").append('<table class="table" id="allstudent" class="submittedlist">'+
								'<thead><tr>'+
								'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
								'<th>Father Name</th>'+
								'<th>Email</th>'+
								'<th>Mobile No.</th>'+
								'</tr>'+
								'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getSubmittedList",
			data : {"searchtext" : searchtext},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				
				if(result.submittedlist.length > 0){
				for(var i = 0;i<result.submittedlist.length;i++){
					
					$("#allstudent tbody").append("<tr>"+
							"<td><a class='anchor'><input type='button' class='submitbuttonstyle' id='"+result.submittedlist[i].enquiryid+"' value='"+result.submittedlist[i].fullname+"' style='button-color: bll' /></a></td>"+
							"<td>"+result.submittedlist[i].parents_name+"</td>"+
							"<td>"+result.submittedlist[i].parentEmailId+"</td>"+
							"<td>"+result.submittedlist[i].mobile_number+"</td>"+
							"</tr>");
				}
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='4' style='text-align: center;'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.submittedlist.length);
				
				 $(".submitbuttonstyle").click(function()
							{
					var currentid= this.id;
					var curenquiryid =currentid;
					window.location.href="menuslist.html?method=submitformDetails&curenquiryid="+curenquiryid;
							});
			}
		});
	}
	
function addprocesseddetails(searchtext){
		$('.pagebanner').show();
		$('.pagelinks').show();
		$("#approvedList").empty();
		$("#issuedList").empty();
		$("#rejectlist").empty();
		$("#cancellist").empty();
		$("#submittedlist").empty();
		$("#processedlist").empty();
		
		$("#processedOne #processedlist").append('<table class="table" id="allstudent" class="processedlist">'+
			'<thead><tr>'+
			'<th class="parentfirstName" style="min-width:150px;text-align:left">Student Name</th>'+
			'<th>Father Name</th>'+
			'<th>Email</th>'+
			'<th>Mobile No.</th>'+
			'</tr>'+
			'<tbody></tbody></table>');
		
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getProcessedList",
			data : {"searchtext" : $("#searchprocess").val()},
			async : false,
			success : function(response) {

		    var result = $.parseJSON(response);
		    var i = 0;
		    var len = result.processedlist.length;
		    if(len > 0){
				for(i = 0;i<len;i++){
				  $("#allstudent tbody").append("<tr>"+
						"<td><a class='anchor'><input type='button' class='processbuttonstyle' id='"+result.processedlist[i].enquiryid+"' value='"+result.processedlist[i].fullname+"' style='button-color: bll' /></a></td>"+
						"<td>"+result.processedlist[i].parents_name+"</td>"+
						"<td>"+result.processedlist[i].parentEmailId+"</td>"+
						"<td>"+result.processedlist[i].mobile_number+"</td>"+
						"</tr>");
				}
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='4' style='text-align: center;'>No Records Founds</td>" +"</tr>");
				$('.pagebanner').hide();
				$('.pagelinks').hide();
			}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+len);
				$('.page').prop("class","page active");
				$('.page').css({"display":"inline"});
				$(".processbuttonstyle").click(function(){
					var currentid= this.id;
					var curenquiryid =currentid;
					window.location.href="menuslist.html?method=processformDetails&curenquiryid="+curenquiryid+
					"&historysearch="+$("#searchprocess").val()+"&historytabstatus="+tabstatus;
				});
			}
		});
	}
	
function handleissue(e) {
	
	if($("#searcissue").val().trim() == "")
		value = "%%";
	else
		value = $("#searcissue").val().trim();
	
	//addissueddetails(value);
	if (e.keyCode === 13) {
	
		e.preventDefault(); // Ensure it is only this code that runs
		addissueddetails(value);
	}
}
	
function handleapprove(e) {
	
	if($("#searchapprove").val().trim() == "")
		value = "%%";
	else
		value = $("#searchapprove").val().trim();
	
	
	//addapproveddetails(value);
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that runs
		addapproveddetails(value);
	}
}

function handlereject(e) {
	
	if($("#searchreject").val().trim() == "")
		value = "%%";
	else
		value = $("#searchreject").val().trim();
	
	
	//addrejectdetails(value);
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that runs
		addrejectdetails(value);
	}
}

function handlecancel(e) {
	
	if($("#searchcancelled").val().trim().trim() == "")
		value = "%%";
	else
		value = $("#searchcancelled").val().trim();
	
	//addcanceldetails(value);
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that runs
		addcanceldetails(value);
	}
}

function handlesubmit(e) {
	
	if($("#searchdsbmit").val().trim()== "")
		value = "%%";
	else
		value = $("#searchdsbmit").val().trim();
	
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that runs
		addisubmitdetails(value);
	}
}

function handleprocess(e) {
	
	if($("#searchprocess").val().trim() == "")
		value = "%%";
	else
		value = $("#searchprocess").val().trim();
	
//	addprocesseddetails(value);
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that runs
		addprocesseddetails(value);
	}
}

function HideErrors(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function addpaginationdiv(id){
	id.append("<div class='pagebanner'>"+"<select id='show_per_page'>"+
									"<option value='50'>50</option>"+
									"<option value='100'>100</option>"+
									"<option value='200'>200</option>"+
									"<option value='300'>300</option>"+
									"<option value='400'>400</option>"+
									"<option value='500'>500</option></select> " +
									"<span class='numberOfItem'></span></div>"+
				                    "<div class='pagination pagelinks' style='top: -9px'></div>")
}
function myFunction() {

	var searchText = $("#searchValue").val();
	window.location.href = "menuslist.html?method=sectionList&searchText="
		+ searchText+"&school="+$('#school').val();
}

$(document).ready(function() {	
	
	if($("#inactive").text() == "InActive"){
		$("#butstatus").val("N")
	}else{
		$("#butstatus").val("Y")
	}
	
	if($("#hiddenlocId").val()!=""){
		
		$("#locationname").val($("#hiddenlocId").val());
		getStream($("#locationname"));
		$("#streamId").val($("#hiddenstreamId").val());
		getClassList();
		$("#classname").val($("#hiddenclassname").val());
		$("#status").val($("#hiddenstatus").val());
		
		if($("#status").val()=="Y"){ 
			$("#butstatus").val("N")
			$("#editClass").show();
			$("#inactive").text("InActive");
		}
		else{
			$("#butstatus").val("Y")
			$("#editClass").hide();
			$("#inactive").text("Active");
		 }
		getStudentListforPrint($("#locationname").val(),$("#classname").val(),$("#streamId").val(),$("#status").val());
	}else{
		$("#locationname").val($("#curr_loc").val());
	}
	
	
	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody").append("<tr>" +"<td colspan='7'>No Records Founds</td>"+"</tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records  "+$("#allstudent tbody tr").length);
		pagination(100);
	}	
		   getStream($("#locationname"));
		   getStudentListforPrint($("#locationname").val(),"all","all","Y");
	       checkboxsselect();
	       
	       $("#status").change(function(){
	    	   var locationid=$("#locationname").val();
				var classname=$("#classname").val();
				var streamId=$("#streamId").val();
				var status=$("#status").val();
				if(locationid==""){
					locationid="all";
				}
				if(classname==""){
					classname="all";
				}
				if(streamId==""){
					streamId="all";
				}
				
				if($(this).val()=="Y"){ 
					$("#butstatus").val("Y")
					$("#editClass").show()
					$("#inactive").text("InActive");
					getStudentListforPrint(locationid,classname,streamId,status);
				}
				else{
					$("#butstatus").val("N")
					$("#editClass").hide()
					$("#inactive").text("Active");
					getStudentListforPrint(locationid,classname,streamId,status);
				 }
				 $("#selectall").prop("checked",false);
	       });
	       
	       

	       if($("#locationname").val()!=""){
	    	   var locationid=$("#locationname").val();
				var classname=$("#classname").val();
				var streamId=$("#streamId").val();
				var status=$("#status").val();
				
				if(locationid==""){
					locationid="all";
				}
				if(locationid=="all"){
					$("#classname").val("");
				}
				if(classname==""){
					classname="all";
				}
				if(streamId==""){
					streamId="all";
				}
				getStudentListforPrint(locationid,classname,streamId,status);  
	       }
	       
 
 
			$("#locationname").change(function(){
				 $("#selectall").prop("checked",false);
				var locationid=$("#locationname").val();
				var classname=$("#classname").val();
				var streamId=$("#streamId").val();
				var status=$("#status").val();
				
				if(locationid==""){
					locationid="all";
				}
				if(locationid=="all"){
					$("#classname").val("");
				}
				if(classname==""){
					classname="all";
				}
				if(streamId==""){
					streamId="all";
				}
				getStream($(this));
				getClassList();
				getStudentListforPrint(locationid,classname,streamId,status);

			});
			$("#streamId").change(function(){
				 $("#selectall").prop("checked",false);
				var locationid=$("#locationname").val();
				var classname=$("#classname").val();
				var streamId=$("#streamId").val();
				var status=$("#status").val();
				
				if(locationid==""){
					locationid="all";
				}
				if(classname==""){
					classname="all";
				}
				if(streamId==""){
					streamId="all";
				}

				getClassList();
				getStudentListforPrint(locationid,classname,streamId,status);

			});
			$("#classname").change(function(){
				 $("#selectall").prop("checked",false);
				var locationid=$("#locationname").val();
				var classname=$("#classname").val();
				var streamId=$("#streamId").val();
				var status=$("#status").val();
				if(locationid==""){
					locationid="all";
				}
				if(classname==""){
					classname="all";
				}
				if(streamId==""){
					streamId="all";
				}

				getStudentListforPrint(locationid,classname,streamId,status);
			});

			$(".section").click(function(){
				$("#sectionOne").slideToggle();
			});

			setTimeout("removeMessage()" ,2000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);
		

			$('#editClass').click(function() {
						var count = 0;
						var count =$(".select:checked").length;
						if (count == 0 || count > 1) {
							$(".errormessagediv").show();
							$(".validateTips").text("Select any one Division");
							return false;
						} else {
							 class_id = $(".select:checked").attr("id").split(",")[0];
							window.location.href = "sectionPath.html?method=editSection&classCode="+class_id+"&locId="+$("#locationname").val()+"&streamId="+$("#streamId").val()+"&classname="+$("#classname").val()+"&status="+$("#status").val();
						}
					});
			sectionIdlist=[];
			locationIdlist=[];
			
			$("#inactive").click(function(){
				
				maxval=350;
				if($("#status").val()=="N"){
					maxval=280;
					$("#dialog").dialog({ width: maxval});
					}else{
						$("#dialog").dialog({ width: maxval});
					}
				
				var count =0;
				
				$(".select:checked").each(function(){
					var Ids=$(this).attr("id").split(",")[0];
					var list=$(this).attr("id").split(",")[1];
					locationIdlist.push(list); 
					sectionIdlist.push(Ids);
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
					$("#dialog").append('<p id="warningreason" class="warningfont" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Division, you won\'t be able to view details related to this Division.</p>');
					$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
					$("#dialog").append('<div><select style="width: 100%;" class="warningfont" name="inactivereason" id="inactivereason" onchange="HideError(this)">'
							+ '<option value="">' + "----------select----------"
							+ '</option>'
							+ '<option value="Incorrect Entry">' + "Incorrect Entry"
							+ '</option>'
							+ '<option value="Not in use">' + "Not in use" 
							+ '</option>'
							+ '<option value="others">' + "Others" 
							+ '</option>'+
					'</select><div>');
					
					$("#dialog").append('<div><select style="width: 100%;" class="warningfont" name="activereason" id="activereason" onchange="HideError(this)">'
							+ '<option value="">' + "----------select----------"
							+ '</option>'
							+ '<option value="Correct Entry">' + "Correct Entry"
							+ '</option>'
							+ '<option value="In use">' + "In use" 
							+ '</option>'
							+ '<option value="others">' + "Others" 
							+ '</option>'+
					'</select><div>');
					
					  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" class="warningfont" style="width: 100%;" name=other id="otherreason" onclick="HideError(this)"/></div>');
			     
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
			  				$("#warningreason").hide();
			  				$("#activereason").show();
			  				$("#inactivereason").hide();
			  			}
				  		$('#activereason').change(function(){
				  		if($(this).val() == 'others'){
			  				$("#othreason").show(); 
			  				$("#activereason").show();
			  				$("#inactivereason").hide();
			  			}else{
			  				$("#othreason").hide(); 
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
				title:'Division Details',
				maxheight:280,
				minheight:260,
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
							url : "sectionPath.html?method=deleteCampusClassSectionAction",
							data : {
								"locid":locationIdlist.toString(),
								"sectionIds":sectionIdlist.toString(),
								"inactivereason":$("#inactivereason").val(), 
								"activereason":$("#activereason").val(),
								"otherreason":$("#otherreason").val(),
								"status":$("#inactive").text(),
							},
							success : function(response) {
								var result = $.parseJSON(response);
							 
								$('.errormessagediv').hide();
								if (result.status =="true") {
									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
									$(".validateTips").text($("#inactive").text()+" the Division Details Progressing...");
									$('.successmessagediv').delay(3000).slideUp();
								} else if(result.status == "false"){
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Division is Not "+$("#inactive").text());
									$('.errormessagediv').delay(3000).slideUp();
								}
								else{
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Division is Mapped Cannot InActive");
									$('.errormessagediv').delay(3000).slideUp();
								}
								setTimeout(function(){
									window.location.href="menuslist.html?method=sectionList&status="+$("#butstatus").val()+"&locId="+$("#locationname").val();
								},3000);
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


			$("#search").click(function(){
				var searchText = $("#searchValue").val();
				window.location.href = "menuslist.html?method=sectionList&searchText="
					+ searchText+"&school="+$('#school').val();
			});

			$('#excelDownload').click(function() {
						var searchTerm = $("#searchexamid").val();
						window.location.href = "sectionPath.html?method=downloadSectionDetailsXLS&searchTerm="
							+ searchTerm;
			});

			$("#pdfDownload").click(function(){
				var searchTerm = $("#searchexamid").val();
				window.location.href = "sectionPath.html?method=downloadSectionDetailsPDF&searchTerm="+ searchTerm;
			});	
			
		});

function checkUpdateClassName() {
	var className = $("#className").val();
	var stream = $("#stream").val(); 
	var updateClassCode = $('#updateClassCode').val();
	var checkClassName = {
			"className" : className,
			"stream" : stream,
			"updateClassCode":updateClassCode,
	};

	var status = false;

	$.ajax({
		type : "POST",
		url : "classPath.html?method=classNameCheck",
		data : checkClassName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);

			status = result.status;

		}
	});

	return status;

}


function removeMessage() {


	$.ajax({
		type : "GET",
		url : "transport.html?method=removeMessage",
		async : false,
		success : function(data) {
			$('#serverMessage').empty();
		}
	});
}


function getStudentListforPrint(locationid,classname,streamId,status){
	
	 var status=$("#status").val();
	    var sts="Inactive"
			if(status=="N"){
				sts="Active"
			}
			else{
				sts="Inactive"
			}
	
	var datalist = {
			"location" :locationid,
			"classId" :classname,
			"streamId":streamId,
            "status":status,
	}; 
	$.ajax({
		type : 'POST',
		url : "menuslist.html?method=sectionListcheckTemporary",
		data : datalist,
		async:false,
		success : function(data) {

			var result = $.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.list.length>0){
				for(var i=0;i<result.list.length;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].streamName+"</td>"
							+"<td> "+result.list[i].className+" </td>"
							+"<td> "+result.list[i].sectionName+" </td>"
							+"<td> "+result.list[i].strength+" </td>"
							+"<td class='actiontd'>  </td>"
							+"</tr>");
				}
				

				if($("#editPermission").val()=="true"){
					$("td.actiontd").each(function(){
						$(this).append('<span  data-toggle="modal" data-target="#myModal" class="btn btn-xs btn-primary margin-t-5 editclass" title="Edit"><span class="glyphicon glyphicon-edit"></span> Edit</span>');
					});
				}
				if($("#delPermission").val()=="true"){
					$("td.actiontd").each(function(){
						$(this).append('<span  class="btn btn-xs btn-primary margin-t-5 inactive" title="Active/Inactive"><span class="glyphicon glyphicon-link"></span> '+sts+'</span>');
					});
				}
			}
			else{
				$("#allstudent tbody").append("<tr>" +"<td colspan='7'>No Records Founds</td>"+"</tr>");
			}
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.list.length);
			pagination(100);
		}
	});
}

function getClassList(){
	var locationname=$("#locationname").val();
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId,
			"locationname":locationname
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getClassesByStreamAndLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="all">ALL</option>');
			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classname').append('<option value="'+result.ClassesList[j].classId+'">'+result.ClassesList[j].classname+'</option>');
			}
		}
	});
}

function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val()},
		success : function(data) {

			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.streamList.length; j++) {
				$('#streamId').append('<option value="'+result.streamList[j].streamId+'">'+ result.streamList[j].streamName+'</option>');
			}
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

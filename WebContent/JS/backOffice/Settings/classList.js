function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv1").hide();
}

$(document).ready(function() {

	if($("#hiddenlocId").val().trim()!="" && $("#hiddenlocId").val()!= undefined){
		$("#locationname").val($("#hiddenlocId").val());
	}else{
		$("#locationname").val($("#cuur_loc").val());
	}

	
	
	
	
	var locationid=$("#locationname").val();
	 
	var streamId=$("#streamId").val();  
	var status=$("#status").val();
	getStudentListforPrint(locationid,streamId,status);
	

	
	getStream($("#locationname"));
	getStreamForEdit($("#location"));
	
	$("#locationname").change(function(){
		$("#selectall").prop("checked",false);
		var locationid=$("#locationname").val();
		var streamId=$("#streamId").val();
		if(locationid==""){
			locationid="all";
		}
	
		if(streamId==""){
			streamId="all";
		}
		getStream($(this));
		var status=$("#status").val();
		getStudentListforPrint(locationid,streamId,status);
		
	});
	$("#streamId").change(function(){
		$("#selectall").prop("checked",false);
		var locationid=$("#locationname").val();
		var streamId=$("#streamId").val();
		var status=$("#status").val();
		if(locationid==""){
			locationid="all";
		}
	
		if(streamId==""){
			streamId="all";
		}
		getStudentListforPrint(locationid,streamId,status);
		
	});
	
	
		$("#save").click(function(){
				
				

				var stream = $('#streamName').val();
				var className = $('#classId').val();
				var status = $('#statusId').val();
				var updateClassCode = $('#updateClassCode').val();
				var updateclassName =$('#hiddenclassname').val();

				if($("#location").val()=="" || $("#location").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required - Location Name");
					$("#locationname").focus();
					document.getElementById("location").style.border = "1px solid #AF2C2C";
					document.getElementById("location").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if (stream == "" || stream == null) {
					$(".errormessagediv").show();
					$(".validateTips").text(
					"Select Stream Name");
					$("#streamId").focus();
					document.getElementById("streamId").style.border = "1px solid #AF2C2C";
					document.getElementById("streamId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;

				}else if(className == "" || className == null){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required - Class Name");
					$("#classId").focus();
					document.getElementById("classId").style.border = "1px solid #AF2C2C";
					document.getElementById("classId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if($("#actionstatus").val()=="addaction" && checkClassName()==10){
					$(".errormessagediv").show();
					$(".validateTips").text("This Class & Stream Already Exist !! Make it Active");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if($("#actionstatus").val()=="addaction" && checkClassName()==1){
					$(".errormessagediv").show();
					$(".validateTips").text("Class & Stream is Already Mapped");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else {
					var datalist = {
							"stream" : stream,
							"className" : className,
							"status" :status,
							"updateClassCode":updateClassCode,
							"updateclassName" :updateclassName,
							"locationId":$("#location").val(),
							"hiddenStreamId":$("#hiddenStreamId").val(),
					};

					$.ajax({
						type : 'POST',
						url : "classPath.html?method=insertClassAction",
						data : datalist,
						success : function(response) {
							var result = $.parseJSON(response);
							if (result.status == "Class Added Successfully") {
								$(".successmessagediv").show();
								$(".validateTips").text("Adding Record Progressing...");
								setTimeout(function(){
									 location.reload();
								},3000);
							} else if(result.status == "Class Updated Successfully"){
								$(".successmessagediv").show();
								$(".validateTips").text("Updating Record Progressing...");
								setTimeout(function(){
									 location.reload();
								},3000);
							}
							else {
								$(".errormessagediv").show();
								$(".validateTips").text("Class & Stream is Already Mapped");
							}
						}
					});
				}
			
				
				
				
			});
			
			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);	
			
			$("#savebutton").click(function(){
				$("#actionstatus").value="addaction";
			});
			$(document).on("click",".editclass",function(){
							var getData ={"streamid":$(this).closest("tr").attr("id"),
									"classCode":$(this).closest("tr").attr("id").split(",")[0],
									"locId":$(this).closest("tr").attr("id").split(",")[1],
									"streamId":$("#streamId").val(),
									"status":$("#status").val()
							};
			
							$.ajax({
								type:'POST',
								url:'classPath.html?method=editClass',
								data:getData,
								async:false,
								success:function(data){
									var result=$.parseJSON(data);
									
									$("#location").val(result.list[0].locationId);
									$("#streamName").val(result.list[0].streamId);
									var classVal=result.list[0].classId.split("D")[1];
									$("#classId").val(classVal);
									$("#updateClassCode").val(result.list[0].classId);
									$("#hiddenStreamId").val(result.list[0].streamId);
									$("#hiddenclassname").val(result.list[0].className);
									$("#statusId").val(result.list[0].status);
									$("#actionstatus").value="editaction";
								}
							});
							
			
			});
			 
			$(document).on("click",".inactive",function(){
				maxval=350;
				if($("#status").val()=="N"){
					maxval=280;
					$("#dialog").dialog({ width: maxval});
					}else{
						$("#dialog").dialog({ width: maxval});
					}
				
			
				classIds=[];
				locationIdlist=[];
					var Ids=$(this).closest("tr").attr("id")[0];
					var list=$(this).closest("tr").attr("id")[1];
					locationIdlist.push(list); 
					classIds.push(Ids);
				
			
					$("#dialog").dialog("open");
					$("#dialog").empty();
					$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
					$("#dialog").append('<p id="warningreason" class="warningfont" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Class, you won\'t be able to view details related to this Class.</p>');
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
				
				 
			});

			$("#dialog").dialog({
				autoOpen: false,
				modal: true,	
				resizable: false,
				title:'Class Details',
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
							url : "classPath.html?method=deleteClass",
							data : {
								"locid":locationIdlist,
								"classIds":classIds,
								"inactivereason":$("#inactivereason").val(), 
								"activereason":$("#activereason").val(),
								"otherreason":$("#otherreason").val(),
								"status":$("#inactive").text(),
								
							},
							success : function(response) {
								var result = $.parseJSON(response);

								$('.errormessagediv').hide();

								if (result.status == true) {
									$(".successmessagediv").show();
									$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
									$(".validateTips").text($("#inactive").text()+" the Class Details Progressing...");
									$('.successmessagediv').delay(3000).slideUp();
								} else if(result.status == false){
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Class is Not "+$("#inactive").text());
									$('.errormessagediv').delay(3000).slideUp();
								}
								else{
									$(".errormessagediv").show();
									$(".validateTips").text("Selected Class is Mapped Cannot InActive");
									$('.errormessagediv').delay(3000).slideUp();
								}
								setTimeout(function(){
									window.location.href="menuslist.html?method=classList&currentstatus="+$("#status").val()+"&locId="+locationIdlist;
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

			$(".switch-cirle").click(function(){
				if($("#status").val()=="Y"){
					$("#inactive").text("InActive");
					$("#status").val("N");
					$(this).css({
						"left":"60px",
						
						
					});
					
					$("#text-status").css({"left":"7px"});
					$("#text-status").text("Inactive");
					getStudentListforPrint($("#locationname").val(),$("#streamId").val(),$("#status").val());
				}
				else{
					$("#inactive").text("Active");
					$("#status").val("Y");
					$(this).css({
						"left":"1px",
						
					});
					
					$("#text-status").css({"left":"30px"});
					$("#text-status").text("Active");
					getStudentListforPrint($("#locationname").val(),$("#streamId").val(),$("#status").val());
				}
			});

			$("#search").click(function(){
				var searchname = $("#searchname").val().trim();
				window.location.href = "menuslist.html?method=classList&searchname="
					+ searchname+"&school="+$("#school").val();
			});


			$("#searchname").keypress(function(event) {
				var searchText = $("#searchname").val();
				if (event.keyCode == 13) {

					window.location.href ="menuslist.html?method=classList&searchname="
						+ searchText+"&school="+$("#school").val();;
				}

			});

			$('#excelDownload').click(function() {
						var searchTerm = $("#searchexamid").val().trim();
						window.location.href = 'classPath.html?method=classPathDetailsXLS&searchTerm='+ searchTerm;
					});

			$("#pdfDownload").click(function(){
				var searchTerm = $("#searchexamid").val().trim();
				window.location.href = "classPath.html?method=classPathDetailsPDFReport&searchTerm="+ searchTerm;
			});	
			
			/*if($("#hiddenlocId").val()!="" || $("#hiddenstatus").val()=="N"){
				
				$("#locationname").val($("#hiddenlocId").val());
				 getStream($("#locationname"));
				 $("#streamId").val($("#hiddenstreamId").val()); 
				 $("#status").val($("#hiddenstatus").val());
				 if($("#status").val()=="Y"){ 
						$("#inactive").text("InActive");
					}
					else{
						$("#inactive").text("Active");
					 }
				 getStudentListforPrint($("#locationname").val(),$("#streamId").val(),$("#status").val());
				 
			}*/
			 
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


function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}

function getStudentListforPrint(locationid,streamId,status){
	
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
			"streamId" :streamId,
			"status":status,
		}; 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=classListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
				
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					
					$("#allstudent tbody").append("<tr id='"+result.list[i].classId+","+result.list[i].locationId+"'>"
							+"<td>"+(i+1)+"</td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].streamName+" </td>"
							+"<td> "+result.list[i].className+" </td>"
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
						$("#allstudent tbody").append("<tr>" +"<td colspan='5'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
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

function getStreamForEdit(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamName').empty();
			$('#streamName').append('<option value="">-------------Select-----------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamName').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
			}
		}
	});
}
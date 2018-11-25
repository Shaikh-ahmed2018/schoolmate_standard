function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function() {
	
	if($("#inactive").text() == "InActive"){
		$("#butstatus").val("N")
	}else{
		$("#butstatus").val("Y")
	}
	
	if($("#hiddenlocId").val()!="")
	{
		$("#locationname").val($("#hiddenlocId").val());
		getStream($("#locationname"));
		$("#streamId").val($("#hiddenstreamId").val());
		getClassList();
		$("#classname").val($("#hiddenclassname").val());
		$("#status").val($("#hiddenstatus").val());
		
		if($("#status").val()=="Y"){ 
			$("#editspec").show();
			$("#butstatus").val("N")
			$("#inactive").text("InActive");
		}
		else{
			$("#editspec").hide();
			$("#butstatus").val("Y")
			$("#inactive").text("Active");
		 }
		getSpecializationDetails($("#locationname").val(),$("#classname").val(),$("#streamId").val(),$("#status").val());
	}else{
		$("#locationname").val($("#curr_loc").val());
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var streamId=$("#streamId").val();
	    var status=$("#status").val();
		 
		getSpecializationDetails(locationid,classname,streamId,status);
	}
	getStream($("#locationname"));	
	
	/* if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
	 {
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
		
	 if($("#currentstatus").val()=="Y"){
		$("#status").val("N");
		$("#inactive").text("Active");
		getSpecializationDetails(locationid,classname,streamId,$("#status").val());
	}
	else{
		$("#status").val("Y");
		$("#inactive").text("InActive");
		getSpecializationDetails(locationid,classname,streamId,$("#status").val());
	 }
  }*/
	
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
		$("#editspec").show();
		$("#butstatus").val("N")
		$("#inactive").text("InActive");
		getSpecializationDetails(locationid,classname,streamId,status);
	}
	else{
		$("#editspec").hide();
		$("#butstatus").val("Y")
		$("#inactive").text("Active");
		getSpecializationDetails(locationid,classname,streamId,status);
	 }
	 $("#selectall").prop("checked",false);
	});
	
	
	$("#selectall").change(function(){
		$(".studentid").prop("checked",$(this).prop("checked"));
	});
	
	
	
	$("#locationname").change(function(){
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
		getStream($(this));
		getClassList();
		getSpecializationDetails(locationid,classname,streamId,status);
		
		checkboxsselect();
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
		getSpecializationDetails(locationid,classname,streamId,status);
		
		checkboxsselect();
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

		getSpecializationDetails(locationid,classname,streamId,status);
		
		checkboxsselect();
	});

	
	
	var schoolName=$("#school").val();
	
	$("#searchname").keypress(function(e){
		var searchText = $("#searchname").val();
		if(e.keyCode == 13){
			window.location.href ="menuslist.html?method=SpecializationList&searchText="
				+ searchText+"&SchoolName="+$("#school").val();
		}
	});
	
	$("#search").click(function(){
		var searchText = $("#searchname").val();
		window.location.href ="menuslist.html?method=SpecializationList&searchText="
			+ searchText+"&SchoolName="+$("#school").val();
	});

	$(".specializations").click(function(){
		$("#specializationOne").slideToggle();
	});


	
		
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);


	$("#editspec").click(function(){
		$(".successmessagediv").hide();
		
		getData = $(".select:checked").attr("id");
        var len =$(".select:checked").length;
        
		if (len==0|| len > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Record Only");
			return false;
		} 
		else
		{
		  window.location.href ="specialization.html?method=editSpecialization&specId="+getData+"&locId="+$("#locationname").val()+"&streamId="+$("#streamId").val()+"&classname="+$("#classname").val()+"&status="+$("#status").val();
		}
	});
	
	
	$("#inactive").click(function(){
		
		 maxval=350;
		 if($("#status").val()=="N"){
			maxval=280;
			$("#dialog").dialog({ width: maxval});
			}else{
				$("#dialog").dialog({ width: maxval});
			}
		
		var count =0;
		specializationIds=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id");
			specializationIds.push(list);
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
				$("#dialog").append('<p class="warningfont" id="warningreason" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Specialization, you won\'t be able to view details related to this Specialization.</p>');
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
	  				$("#warningreason").hide();
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
		
		title:'Specialization Details',
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
					url : "specialization.html?method=deleteSpec",
					data : {
						"specid":specializationIds.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
					},
					success : function(response) {
						var result = $.parseJSON(response);
						var flag = false;
						$('.errormessagediv').hide();

						if (result.status == "InActivetrue") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Inactivating specialization progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							flag = true;
						} 
						else if (result.status == "Activetrue") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Activating the specialization details progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							flag = true;
						}
						else if(result.status == "false"){
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to perform the operation. Try again");
							$('.errormessagediv').delay(3000).slideUp();
							$("#selectall").prop('checked',false);
							$(".select").prop('checked',false);
						}
						if(flag){
							$("#selectall").prop('checked',false);
							$(".select").prop('checked',false);
							setTimeout(function(){
								window.location.href ="menuslist.html?method=SpecializationList&status="+$("#butstatus").val()+"&locId="+$("#locationname").val();
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
});	



function getSpecializationDetails(locationid,classname,streamId,status)
{
	var datalist = {
			"location" :locationid,
			"classId" :classname,
			"streamId":streamId,
			"status":status,
		}; 
	
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=SpecializationListforListOnchangeMethod",
			data : datalist,
			async:false,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].spec_Id+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].stream_Name+"</td>"
							+"<td> "+result.list[i].class_Name+" </td>"
							+"<td> "+result.list[i].spec_Name+" </td>"
							+"<td> "+result.list[i].remarks+" </td>"
							+"</tr>");
					   }
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+len);
					pagination(100);
					$(".studentid").change(function(){
						if($(".studentid:checked").length==$(".studentid").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
			}
		});

	}

function getClassList(){
	var locationname=$("#locationname").val();
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId,
			"locationname":locationname,
	},

	$.ajax({

		type : 'POST',
		url : "reportaction.html?method=getClassesByStreamAndLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="">ALL</option>');

			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassesList[j].classId + '">'

						+ result.ClassesList[j].classname

						+ '</option>');

			}
		}

	});
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

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
			}
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

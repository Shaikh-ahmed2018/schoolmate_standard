function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
	document.getElementById("myForm").submit();   
}

$(document).ready(function() {
	

 
	if($("#hiddenstatus").val()!=""){
		$("#status").val($("#hiddenstatus").val());
	}
	
	checkboxsselect();
	
	InActiveLocationList();
	
	if($("#hiddensclst").val() == "inactive"){
		$("#add").hide();
	}
	
	$("#status").change(function(){
	 if($(this).val()=="Y"){ 
		$("#inactive").text("InActive");
		InActiveLocationList();
	}
	else{
		$("#inactive").text("Active");
		InActiveLocationList();
	 }
	 $("#selectall").prop("checked",false);
	});
	
	setTimeout("removeMessage()", 2000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 2000);


	$("#editstream").click(function(){
		$(".successmessagediv").hide();
		 
		getData = $(".select:checked").attr("id");
         var len =$(".select:checked").length;
         
		if (len==0|| len > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Record");
			return false;
		} 
		else
		{
			window.location.href ="locationDetails.html?method=editSchoolLocation&locid="+getData;
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
		locationIdlist=[];
		$(".select:checked").each(function(){

			var list=$(this).attr("id").split(",")[2];
			locationIdlist.push(list);
			count ++;
		});

		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Location to "+$("#inactive").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			    $("#dialog").dialog("open");
				$("#dialog").empty();
				$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
				$("#dialog").append('<p class="warningfont" id="warningreason" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Location, you won\'t be able to view details related to this Location.</p>');
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
				'</select></div>');
				
				$("#dialog").append('<div><select style="width: 100%;" class="warningfont" name="activereason" id="activereason" onchange="HideError(this)">'
						+ '<option value="">' + "----------select----------"
						+ '</option>'
						+ '<option value="Correct Entry">' + "Correct Entry"
						+ '</option>'
						+ '<option value="In use">' + "In use" 
						+ '</option>'
						+ '<option value="others">' + "Others" 
						+ '</option>'+
				'</select></div>');
				
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
		autoOpen: false,
		modal: true,
		title:'School Details',
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
					url : "locationDetails.html?method=InactiveLocation",
					data : {
						"locid":locationIdlist.toString(),
						"inactivereason":$("#inactivereason").val(), 
						"activereason":$("#activereason").val(),
						"otherreason":$("#otherreason").val(),
						"status":$("#inactive").text(),
					},
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(response) {
						var result = $.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {
							$("#loder").hide();
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text($("#inactive").text()+" the Location Details Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
						} else if(result.status == "false"){
							$("#loder").hide();
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Location is Not "+$("#inactive").text());
							$('.errormessagediv').delay(3000).slideUp();
						}
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Location is Mapped Cannot InActive");
							$('.errormessagediv').delay(3000).slideUp();
						}
						setTimeout(function(){
							InActiveLocationList();
						},3000);
					}
				});  

				$(this).dialog("close");
				$("#selectall").prop("checked",false);
				locationIdlist=[];
			  }
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	 
	if($("#custstatus").val()=="inactive"){
		/*$("#add").hide();*/
		$("#inactive").hide();
	}
	
	$("#searchname").keypress(function(event) {
		if (event.keyCode == 13) {
			InActiveLocationList();
		}
	});

	$("#search").click(function(){
		InActiveLocationList();
	});

  if($("#allstudent tbody tr").length == 0){
	  $("#allstudent tbody ").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
  }

	$('#excelDownload').click(function() {
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'locationDetails.html?method=downloadLocationDetailsXL&searchTerm='+ searchTerm+"&status="+$("#status").val();
	});

	$("#pdfDownload").click(function(){
		var searchTerm = $("#searchexamid").val().trim();
		window.location.href = 'locationDetails.html?method=downloadLocationDetailsPDF&searchTerm='+ searchTerm+"&status="+$("#status").val();
	});

});				


function InActiveLocationList(){
	var status=$("#status").val();
	 
	$.ajax({
		type : 'POST',
		url : "locationDetails.html?method=InActiveLocationList",
		data : {
			"status" :status,
		},
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.LocationList.length;
			
			if(len > 0){
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='td-"+result.LocationList[i].locId+"'><input type='checkbox' class='select' name='select' id='"+result.LocationList[i].location_id+","+result.LocationList[i].locAddId+","+result.LocationList[i].locId+"'/></td>" 
						+"<td> "+result.LocationList[i].locationname+" </td>" 
						+"<td>"+"<span>Contact No:- <span class='tablecontents'>"+result.LocationList[i].contactno+"</span></span><br />"
						+"<span>Email Id:- <span class='tablecontents'>"+result.LocationList[i].emailId+"</span></span><br />"
						+"<span>Web Site:- <span class='tablecontents'>"+result.LocationList[i].website+"</span></span><br />"
						+"<span>Address:- <span class='tablecontents'>"+result.LocationList[i].address+"</span></span><br /></td>"
						+"<td> "+result.LocationList[i].remarks+" </td>"
						+"</tr>");
				 }
				
				if($("#hPriveliges").val()!="Y"){
					
					$(":checkbox.select").each(function(){
						$(this).attr("id").split(",")[2];
						
						
						var checkstring="td-"+$(this).attr("id").split(",")[2];
						if(checkstring!="td-"+$("#hschoolLocation").val()){
							$(this).hide();
						}
						
					});
				}
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
			
		
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.LocationList.length);
			pagination(100);
			$("#loder").hide();
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

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


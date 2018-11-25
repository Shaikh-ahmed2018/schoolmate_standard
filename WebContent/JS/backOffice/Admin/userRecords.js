
function callAjax(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				jsonResult = result.teacherdetails;

			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}
function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	/*$(".form-control").not(id).css({
		"border":"0px solid #ccc",
		"background-color":"#fff"
	});*/
	
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
function Search(){
	var type=$("#typename").val();
	var searchText = $("#searchTextId").val();
	if(type==""){
		$(".errormessagediv").show();
		$(".validateTips").text("Select Any One Type");
		 showError("#typename");
		 setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		return false;
	}else if(searchText== ""){
		window.location.href = "menuslist.html?method=getUserRecords";
	}else{
		window.location.href = "menuslist.html?method=getUserRecords&searchText="+ searchText+"&type="+ type;
	}

}

$(document).ready(function() {
	 
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
		 
		 if($("#currentstatus").val()=="Y"){ 
			 $("#status").val("N");
			$("#inactive").text("Active");
			InActiveUserList();
		}
		else{
			$("#status").val("Y");
			$("#inactive").text("Block");
			InActiveUserList();
		 }
	}
	
	
	$("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#inactive").text("Block");
			InActiveUserList();
		}
		else{
			$("#inactive").text("Active");
			InActiveUserList();
		 }
		 $("#selectall").prop("checked",false);
		});
	
	
	 if($("#allstudent tbody tr").length==0){
			$("#allstudent tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
		}
	
	 checkboxsselect();
	 
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	$("#edit").click(function(){
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
			window.location.href="userManagement.html?method=getUserDetailsforEdit&userId="+getData+"&status="+$("#status").val();
		}
	});
	
	
	var hserachText = $("#hsearchTextId").val();
	var htype = $("#htype").val();
		
		if(htype!=""){
			
			$("#typename option[value="+htype+"]").attr('selected','true');
			
		}
		
	    if(hserachText!=""){
			
	    	$("#searchTextId").val(hserachText);
			
		}


 $("#searchButtonId").click(function() {
	 Search();
 });

 
 	$('#Edit').click(function() {
			var count = 0;
			var user_Code = null;

			$('input[name="userid"]:checked').each(function() {
				count = count + 1;
				user_Code = this.value;
			});
			if (count > 1 || count == 0) {
				$('.errormessagediv').show();
				$('.validateTips').text("Select Any User");
				return false;
			} else {
				$('.errormessagediv').hide();
				
				window.location.href="userManagement.html?method=changePasswordHome&userId="+user_Code;
			
			}					
	});
 	
 	
	$('#excelDownload')
	.click(
			function() {
				
				var hsearchTextId=$("#hsearchTextId").val();
				var type=$("#htype").val();
				window.location.href = 'userManagement.html?method=downloaduserManagementXLS&searchterm='+hsearchTextId+"&type="+ type;
		
			});
	$("#pdfDownload").click(function(){
		var hsearchTextId=$("#hsearchTextId").val();
		var type=$("#htype").val();
		
		window.location.href = "userManagement.html?method=downloaduserManagementPDF&searchterm="+hsearchTextId+"&type="+ type;
			
	});
		
 	

	$("#inactive").click(function(){
		var count =0;
		userIds=[];
		auserid=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id").split(",")[0];
			userIds.push(list);
			auserid.push($(this).attr("id").split(",")[1])
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
				
				$("#dialog").append('<div id="othreason"><textarea style="width: 100%;" class="warningfont" rows="4" name=other id="otherreason" onclick="HideError(this)"/></textarea></div>');
		  		 
		  	  reason = $("#otherreason").val();
		}
	});
	
	 	$("#dialog").dialog({	
	
			autoOpen: false,
			modal: true,
			title:'User Details',
			buttons : {
				"Yes" : function() {
					
					 var otherreason=$("#otherreason").val();
					 
					 if((otherreason.trim()=="") && $("#status").val()=="Y"){
		                	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
		        			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
		               	      
		        				$(".errormessagediv").show();
				     		    $(".validateTips").text("Reason Required field");
				     		    setTimeout(function() {
				  				$('#errormessagediv').fadeOut();
				  			    },3000);
		                }
					 else if((otherreason.trim()=="") && $("#status").val()=="N"){
		                	document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
		        			document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";
		        				$(".errormessagediv").show();
				     		    $(".validateTips").text("Reason Required field");
				     		    setTimeout(function() {
				  				$('#errormessagediv').fadeOut();
				  			    },3000);
		                   }
	 	            else{
					    var user = {
							"userId" : userIds.toString(),
							"status":$("#inactive").text(),
							"remarks":otherreason,
							"auserid":auserid.toString()
						  };
					    $.ajax({
								type : "GET",
								url : "userManagement.html?method=blockUserByStatus",
								data : user,
								async : false,

								success : function(data) {
									var result = $.parseJSON(data);
									$('.errormessagediv').hide();
									if(result.status=="true"){
									$(".successmessagediv").show();
									$(".successmessage").text($("#inactive").text()+" the User Detail Progressing...");
									setTimeout(function(){									   
										window.location.href="menuslist.html?method=getUsersettings&currentstatus="+$("#status").val();
									  },3000);
								  }
									else{
										$('.errormessagediv').show();
										$('.validateTips').text("Selected User Detail is Not "+$("#inactive").text());
										setTimeout(function(){									   
											window.location.href="menuslist.html?method=getUsersettings&currentstatus="+$("#status").val();
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
	 	
	 	if($("#historystatus").val()=="N"){
	 		$("#status").val($("#historystatus").val());
	 		if($("#status").val()=="Y"){ 
				$("#inactive").text("Block");
			}
			else{
				$("#inactive").text("Active");
			 }
	 		InActiveUserList();
	 	}
 
});

function InActiveUserList(){
	var datalist = {
			"status" :$("#status").val()
		}; 
	
		$.ajax({
			type : 'POST',
			url : "userManagement.html?method=InActiveUserList",
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
							+"<td><input type='checkbox' class='select' id='"+result.list[i].userCode+"' /></td>"
							+"<td> "+result.list[i].roleName+" </td>"
							+"<td> "+result.list[i].userName+"</td>"
							+"<td> "+result.list[i].email+" </td>"
							+"<td> "+result.list[i].mobile+" </td>"
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
			


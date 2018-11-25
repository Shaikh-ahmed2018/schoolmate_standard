$(document).ready(function() {
	 var status = "N";
	
	$("#resetbtn").click(function(){
		$("#typename").val("all");
		$("#status").val("Y");
		$("#searchTextId").val("");
		userlistsearch();
	});
		$("#Edit").click(function(){
		var count = 0;
		var user_Code = null;
		$('.select:checked').each(function() {
			user_Code =$(this).attr("id");
			count++;
		});
		if (count > 1 || count == 0) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Record");
			return false;
		} else {
			$('.errormessagediv').hide();
			window.location.href="userManagement.html?method=changePasswordHome&userId="+user_Code+"&typename="+$("#typename").val()+"&status="+$("#status").val()+"&searchTextId="+$("#searchTextId").val();
		
		}					
});
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
	}
	
	$("#search").click(function(){
		userlistsearch();
	});
	$("#typename").change(function(){
		userlistsearch();
	});
	
	$("#searchTextId").keypress(function(e){
		
		var key = e.which;
		if(key==13){
			userlistsearch();
		}
	});	

			checkboxsselect();
			
			if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
			{
				 if($("#currentstatus").val()=="Y")
				 { 
					 $("#status").val("N");
					 status="N";
					$("#delete").text("Active");
					userlistsearch();
				}
				else{
					$("#status").val("Y");
					status="Y";
					$("#delete").text("Block");
					userlistsearch();
				 }
			}
			
			 reason=null;

		 $("#status").change(function(){
			 if($(this).val()=="Y"){ 
				$("#delete").text("Block");
				status = "N";
			}
			else{
				$("#delete").text("Active");
				
			 }
			 $("#selectall").prop("checked",false);
			 userlistsearch();
			
			});
 
 
 $("#delete").click(function() {

		var count=0;
		teacherlist=[];
		
		$(".select:checked").each(function(){
			var list=$(this).attr("id");
			teacherlist.push(list);
			count++;

		});

		if(count==0)
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Record To"+" "+$("#delete").text());
			$('.errormessagediv').delay(3000).slideUp();
		}

		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#delete").text()+"?</p>");
			$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
			$("#dialog").append('<div id="othreason"><textarea style="width: 100%;" class="warningfont" rows="3" name=other id="otherreason" onclick="HideError(this)"/></textarea></div>');	 
	  	    reason = $("#otherreason").val();
		}
		$("#headenuserId").val(teacherlist);

	});
	
 $("#dialog").dialog({	
		
		autoOpen: false,
		modal: true,
		title:'Password Maintenance',
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
						"teachId" :$("#headenuserId").val().toString(),
						"status":$("#delete").text(),
						"remarks":otherreason,	
					  };
				    $.ajax({
							type : "GET",
							url : "userManagement.html?method=blockUserStatus",
							data : user,
							beforeSend: function(){
								$("#loder").show();
							},

							success : function(data) {
								var result = $.parseJSON(data);
								$('.errormessagediv').hide();
								$("#loder").hide();
								if(result.status=="true"){
								$(".successmessagediv").show();
								$(".successmessage").text($("#delete").text()+" the User Detail Progressing...");
								setTimeout(function(){
								   teacherlist=[];
								   $("#selectall").prop("checked",false);		
									 window.location.href="menuslist.html?method=getUserRecords&currentstatus="+$("#status").val();
								  },3000);
								
								}
								else{
									$("#loder").hide();
									$('.errormessagediv').show();
									$('.validateTips').text("Selected User Detail is Not "+$("#delete").text());
									setTimeout(function(){
									    teacherlist=[];
										$("#selectall").prop("checked",false);	
										window.location.href="menuslist.html?method=getUserRecords&currentstatus="+$("#status").val();
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
 
 if($("#historytypename").val()!="" || $("#historystatus").val()=="N" || $("#historysearchTextId").val()!=""){
	 
	 $("#typename").val($("#historytypename").val());
	 $("#status").val($("#historystatus").val());
	 $("#searchTextId").val($("#historysearchTextId").val());
	  
	 if($("#status").val()=="Y"){ 
			$("#delete").text("Block");
		}
		else{
			$("#delete").text("Active");
	  }
	 userlistsearch();
 }
 
});

function userlistsearch(){
	
var datalist = {
			"status":$("#status").val(),
		    "type":$("#typename").val(),
            "searchText":$("#searchTextId").val().trim(),
			
		}; 
		$.ajax({
			type : 'POST',
			url : "userManagement.html?method=userListSearch",
			data : datalist,
			async:false,
			
			success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.userRecords.length>0){
					for(var i=0;i<result.userRecords.length;i++){
					//	//alert(result.list.length);

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.userRecords[i].userId+"'/></td>"
							+"<td> "+result.userRecords[i].firstName+" </td>"
							+"<td> "+result.userRecords[i].designation+"</td>"
							+"<td> "+result.userRecords[i].userName+" </td>"
							+"<td> "+result.userRecords[i].mobile+" </td>"
							

						+"</tr>");
					}	
			}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.userRecords.length);
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


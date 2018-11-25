$(document).ready(function(){
	 var status = "N";
	 $("#resetbtn").click(function(){
			$("#status").val("Y");
			$("#searchtext").val("");
			bankEntrySearch();
		});
	checkboxsselect();
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
		pagination(100);
	}
	
	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
	{
		 if($("#currentstatus").val()=="Y"){ 
			 $("#status").val("N");
			 $("#Remove").text("Active");
			 status='Y';
			 $("#Edit").hide();
			 bankEntrySearch();
		}
		else{
			$("#status").val("Y");
			status='N';
			$("#Edit").show();
			$("#Remove").text("InActive");
			bankEntrySearch();
		 }
	}
	
	$("#Edit").click(function(){
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).val();
			cnt++;
		});
		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			setTimeout(function() {
				$('#errorhover').fadeOut();
			}, 3000);
			return false;
		}
		else {
			window.location="bankMaster.html?method=editBank&bankId="+getData+"&status="+$("#status").val()+"&searchtext="+$("#searchtext").val();
		}
	});
	
	$("#search").click(function(){
		
		bankEntrySearch();
 	});
	
	$("#searchtext").keypress(function(e){
		var key = e.which;
		if(key==13){
			bankEntrySearch();
		}
	});
	
	 reason=null;
	
	 $("#status").change(function(){
		 if($(this).val()=="Y"){ 
			$("#Remove").text("InActive");
			status='N';
			$("#Edit").show();
		}
		else{
			$("#Remove").text("Active");
			status='Y';
			$("#Edit").hide();
		 }
		 bankEntrySearch();
		
		});
	 
	

	$("#Remove").click(function(){
		var bankId=[];
		var count =0;
		$(".select:checked").each(function(){
			var Ids=$(this).val();
			bankId.push(Ids);
			count ++;
		});	
		
		$("#bankids").val(bankId);
		
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Records to "+$("#Remove").text());
			$('.errormessagediv').delay(3000).slideUp();
		}

			else{
				$("#dialog").dialog("open");
				$("#dialog").empty();
				$("#dialog").append("<p>Are You Sure To "+$("#Remove").text()+"?</p>");
				/*$("#dialog").append('<label>Reason:</label>');*/
				$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
				$("#dialog").append('<div id="othreason"><textarea style="width: 100%;" class="warningfont" rows="3" name=other id="otherreason" onclick="HideError(this)"/></textarea></div>');
		  		 
		  	  	reason = $("#otherreason").val();
			}
		
		});

	
			$("#dialog").dialog({	
				autoOpen: false,
				resizable: false,
				modal: true,
				title:'Bank Master',
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
					
								$.ajax({
									method:'post',
									url:'bankMaster.html?method=removeBank',
									data:{"bankId": $("#bankids").val().toString(),"reason":otherreason,"status":status,"button":$("#status").val()},
									async:false,
									success: function(data){   
										var result = $.parseJSON(data);
										if(result.result == "success")
										{
											$('.errormessagediv').hide();
											$(".successmessagediv").show();
											$(".validateTips").text("Bank "+$("#Remove").text()+" Successfully");
											$('#selectall').prop('checked', false);
											setTimeout(function() {
												$(".successmessagediv").hide();
												window.location="menuslist.html?method=bankEntry&currentstatus="+$("#status").val();
											}, 2000);
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
			if($("#historysearchname").val()!="" || $("#historystatus").val()=="N"){
				$("#searchtext").val($("#historysearchname").val());
				$("#status").val($("#historystatus").val());
				
				if($("#status").val()=="Y"){ 
					$("#Remove").text("InActive");
				}
				else{
					$("#Remove").text("Active");
				 }
				 bankEntrySearch();
			}
			
});

function bankEntrySearch(){
	
	var status=$("#status").val();
	var searchText =$("#searchtext").val().trim();
	$.ajax({
		type : 'POST',
		url :'bankMaster.html?method=bankEntrySearch',
		data : {
			
			"status":status,
			"searchText":searchText,
		},
		async : false,
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.bankEntrySearchlist.length>0){
				for(var i=0;i<result.bankEntrySearchlist.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td><input type='checkbox' class='select'  value='"+result.bankEntrySearchlist[i].id+"'/></td>" 
						+"<td> "+result.bankEntrySearchlist[i].name+" </td>"
						+"<td> "+result.bankEntrySearchlist[i].shortname+"</td>"
						+"<td> "+result.bankEntrySearchlist[i].reason+" </td>"
						
						+"</tr>");
				}
				}
				else{
					$('#allstudent tbody').append("<tr><td colspan='4'>NO Records Found</td></tr>");
				}
				checkboxsselect()
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.bankEntrySearchlist.length);
		}
	});
}
function checkboxsselect(){
	$('#selectall').change(function(){
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
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function()
{
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv1").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv1").hide();
		}
	},
	});
	
	$("#add").click(function(){
		window.location.href = "termfee.html?method=addtermdetails&historylocId="+$("#locationname").val()+
		"&historyacademicId="+$("#Acyearid").val();
	});
	
	
	$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='deleteId' style='position: absolute;top:0;bottom:0;margin:auto;'></span>");

	if($("#hiddendelete").val().trim()!="true"){
		$("#deleteId").hide();
	}else{
		$("#deleteId").show();
	}
	
	$("#Edit").click(function(){

		$(".successmessagediv").hide();
			
		getData = $('.academic_Checkbox_Class:checked').val();
        len = $('.academic_Checkbox_Class:checked').length;

	if (len > 1 || len == 0 ) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Select any one record");
			return false;
		} 
		else
		{
			window.location.href = "termfee.html?method=edittermDetails&id="+getData;
	 }
	});
				
					
					$("#Acyearid").val($("#hacademicyaer").val());
					$("#locationname").change(function(){
						var locationId=$(this).val();
						if(locationId=="" || locationId == undefined){
							locationId="all";
						}
						getTermListByJs(locationId,$("#Acyearid").val());
						$("#selectall").prop("checked",false);
						
						
					});
					
					$("#Acyearid").change(function(){
						
						var locationId=$("#locationname").val();
						if(locationId=="" || locationId == undefined){
							locationId="all";
						}
						getTermListByJs(locationId,$(this).val());
						$("#selectall").prop("checked",false);
						 
					});
				
				
					getTermListByJs($("#locationname").val(),$("#Acyearid").val());
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);
					
					
					
					 checkboxsselect();
					reason=null;
					var status = "N";
				  $("#status").change(function(){
					 
					  if(this.value=="Y"){
						  $("#delete").text("InActive"); 
						  status = 'N';
						  getTermListByJs($("#locationname").val(),$("#Acyearid").val());
						  $("#selectall").prop("checked",false);
					  }
					  else{
						  $("#delete").text("Active"); 
						  status = 'Y';
						  getTermListByJs($("#locationname").val(),$("#Acyearid").val());
						  $("#selectall").prop("checked",false);
					  }
					  getTermListByJs($("#locationname").val(),$("#Acyearid").val());
					  $("#selectall").prop("checked",false);
					  checkboxsselect();
				  });
				
				  
					  $("#deleteId").click(function()
						 {
					       getDataArray=[];
						   getDataArray.push($(this).closest("tr").find("span.academic_Checkbox_Class").attr("id").split('_')[1]);		
									$("#dialog").dialog("open");
									$("#dialog").empty();
									$("#dialog").append("<p>Are You Sure to Delete?</p>");
							});
			
			$("#dialog").dialog({
			    autoOpen  : false,
			    modal     : true,
			    title     : "Term Details",
			    buttons   : {
			              'Yes' : function() {

 								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
								$.ajax({
											type : "GET",
											url : "termfee.html?method=deleteTermDetailsByOrder",
											data : datalist,
											async : false,
											success : function(response) {
												var result = $.parseJSON(response);
												if (result.jsonResponse == "true") {
													$(".successmessagediv").show();
													$(".validateTips").text("Term Details Deleted Successfully");
													$('.successmessagediv').delay(3000).slideUp();
													
													setTimeout(function() {
														location.reload();
													},3000);
												}
												else if (result.jsonResponse == "false") {
													$(".errormessagediv").show();
													$(".validateTips").text("Term Details not Deleted Successfully");
													$('.errormessagediv').delay(3000).slideUp();
												}
												else if (result.jsonResponse == "mapped") {
													$(".errormessagediv").show();
													$(".validateTips").text("Term Details Already Mapped");
													$('.errormessagediv').delay(3000).slideUp();
												}
												
											}
										});
								$(this).dialog('close');
							},
			              'No' : function() {
			                  $(this).dialog('close');
			                  }
			                }
			        });
		
			if($("#historylocId").val()!="" || $("#historyacademicId").val()!="")
			{
				if($("#historyacademicId").val()!=""){
					$("#Acyearid").val($("#historyacademicId").val());
				}
				$("#locationname").val($("#historylocId").val());
				
				getTermListByJs($("#locationname").val(),$("#Acyearid").val());
			}
					
  });

function getTermListByJs(locationId,accyear){
	
		$.ajax({
		type:'POST',
		url:'termfee.html?method=termListByJs',
		data:{"locationId":locationId,"accyear":accyear,
				"status":$("#status").val(),
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			
			var len=result.termlist.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>" +
							
							"<td><span name='select' class='academic_Checkbox_Class' id='academicCheckBox_"+result.termlist[i].termid+",'>"+(i+1)+"</span></td>" +
							"<td>"+result.termlist[i].accyear+"</td>" +
							"<td>"+result.termlist[i].locationName+"</td>" +
							"<td>"+result.termlist[i].termname+"</td>" +
							"<td>"+result.termlist[i].startdate+"</td>" +
							"<td>"+result.termlist[i].enddate+"</td>" +
							"</tr>");
				   }
				
                $("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='deleteId' style='position: absolute;top:0;bottom:0;margin:auto;'></span>");
                if($("#hiddendelete").val().trim()!="true"){
            		$("#deleteId").hide();
            	}else{
            		$("#deleteId").show();
            	}
                $("#deleteId").click(function()
						{
				    getDataArray=[]; //add array1
					getDataArray.push($(this).closest("tr").find("span.academic_Checkbox_Class").attr("id").split('_')[1]);		
								$("#dialog").dialog("open");
								$("#dialog").empty();
								$("#dialog").append("<p>Are You Sure to Delete?</p>");
						});
		
		$("#dialog").dialog({
		    autoOpen  : false,
		    modal     : true,
		    title     : "Term Details",
		    buttons   : {
		              'Yes' : function() {

							/*var datalist = 'name='
									+ getData[0];
*/								var datalist = {'getDataArray':getDataArray.toString()};//create json data3
							$.ajax({
										type : "POST",
										url : "termfee.html?method=deleteTermDetailsByOrder",
										data : datalist,
										async : false,

										success : function(response) {

											var result = $.parseJSON(response);

											if (result.jsonResponse == "true") {
												$(".successmessagediv").show();
												$(".validateTips").text("Term Details Deleted Successfully");
												$('.successmessagediv').delay(3000).slideUp();
												
												setTimeout(function() {
													location.reload();
												},3000);
											}
											else if (result.jsonResponse == "false") {
												$(".errormessagediv").show();
												$(".validateTips").text("Term Details not Deleted Successfully");
												$('.errormessagediv').delay(3000).slideUp();
											}
											else if (result.jsonResponse == "mapped") {
												$(".errormessagediv").show();
												$(".validateTips").text("Term Details Already Mapped");
												$('.errormessagediv').delay(3000).slideUp();
											}

											

										}
									});
							$(this).dialog('close');

						},
		              'No' : function() {
		                 
		                  $(this).dialog('close');
		              }
		                }
		});
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
			}
			checkboxsselect();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.termlist.length);
			pagination(100);
		}
	});
}

function saveTermDetail(status,reason,getDataArray){
	
	
	 datalist = {'getDataArray':getDataArray.toString(),
            'reason':reason,'status':status,'button':$("#delete").text()};//create json data3
				$
						.ajax({
							type : "GET",
							url : "termfee.html?method=deleteTermDetails",
							data : datalist,
							async : false,

							success : function(response) {

								var result = $.parseJSON(response);

								if (result.jsonResponse =="true")
								{
									$(".successmessagediv").show();
									$(".sucessmessage").text("Term Details "+" " + $("#delete").text()+" "+ " Successfully");
									setTimeout(function(){
										window.location.href = "menuslist.html?method=termList&result="
											+ result.jsonResponse;
									},3000);
              					}
							 if (result.jsonResponse =="false")
								{
									$(".successmessagediv").show();
									$(".sucessmessage").text("Term Details not "+" " + $("#delete").text()+" "+ " Successfully"); 
									setTimeout(function(){
										window.location.href = "menuslist.html?method=termList&result="
											+ result.jsonResponse;
									},3000);
								}

								if(result.jsonResponse =="warning"){

									$(".errormessagediv").show();
									$(".validateTips").text("Term Details Already Mapped");

								}

								setTimeout(function() {

											location.reload();

										},2000);

							}
						});
	
}

function  checkboxsselect(){
	$("#selectall").change(function(){
		$(".academic_Checkbox_Class").prop('checked', $(this).prop("checked"));
	});
	
	$(".academic_Checkbox_Class").change(function(){
		if($(".academic_Checkbox_Class").length==$(".academic_Checkbox_Class:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
}


function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv1").show();
	$(".validateTips1").text(errorMessage);
	$(".errormessagediv1").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
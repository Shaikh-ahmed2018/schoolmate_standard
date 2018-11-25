function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
    document.getElementById("myForm").submit();   
 }

function myFunction2() {
	var x = document.getElementById("routeLogicName").value;
	if(!x.match(/^[A-Za-z]+$/i)){
		$('.errormessagediv').show();
		$('.validateTips').text("Route Short Name Should be Alphabet");

		document.getElementById("routeLogicName").style.border = "1px solid #AF2C2C";
		document.getElementById("routeLogicName").style.backgroundColor = "#FFF7F7";
		$('#routeLogicName').val('');
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
	}
}

function myFunction4() {
	var x1 = document.getElementById("halttime").value;
	if(!x1.match(/^[0-9]+$/)){
		$('.errormessagediv').show();
		$('.validateTips').text("Halt time Should be number");

		document.getElementById("halttime").style.border = "1px solid #AF2C2C";
		document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
		$('#halttime').val('');
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
	}
}

 function haltName1(){
	 var x2 = document.getElementById("halttime").value;
	 if(!x2.match(/^[0-9]+$/)){
			$('.errormessagediv').show();
			$('.validateTips').text("Halt time Should be number");
			document.getElementById("halttime").style.border = "1px solid #AF2C2C";
			document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
			$('#halttime').val('');
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		} else if(!x2 < 0 || x2 > 59){
			
			$('.errormessagediv').show();
			$('.validateTips').text("Halt time Should be between 0 to 59");

			document.getElementById("halttime").style.border = "1px solid #AF2C2C";
			document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
			$('#halttime').val('');
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
	 
 }

var stagesidArray = [];
$(document).ready(function() {
	
	$("#back1").click(function(){
		window.location.href = 'menuslist.html?method=schoolWiseRoutMaster&historylocId='+$("#historylocId").val();
	});
	
	                routeMasterSettingBySearch();
	                
	                $("#status").change(function(){
	           		 if($(this).val()=="Y"){ 
	           			$("#editDesignationId").show();
	           			$("#inactive").text("InActive");
	           			routeMasterSettingBySearch();
	           		}
	           		else{
	           			$("#editDesignationId").hide();
	           			$("#inactive").text("Active");
	           			routeMasterSettingBySearch();
	           		 }
	           		 $("#selectall").prop("checked",false);
	           		});
	                
					$("#selectall").change(function(){
						 $(".select").prop('checked', $(this).prop("checked"));
					});
					
			var pageurl=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
			if(pageurl!="addRouteScreen"){
				$("#myTable").attr("style","display:table");
				$("#myTable tbody tr").each(function(){
					$("#tableid .selectBox[value="+$(this).attr("id")+"]").attr("checked",true);
					
				});
			}
					/*$('.errormessagediv').hide();
					$('.successmessagediv').hide();*/
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					$('#excelDownload').click(function() {
						 window.location.href = 'transport.html?method=RouteDetailsXLS';
					 });
					
					$("#pdfDownload").click(function(){
						window.location.href = "transport.html?method=RouteDetailsPDFReport";
					});	

					$("#plus").click(function() {
						 window.location.href = "transport.html?method=addRouteScreen&locationid="+$("#locationid").val()+
						 "&historystatus="+$("#status").val()+"&historysearch="+$("#routeIdlist").val();
					 });
					
					$("#editDesignationId").click(function() {
								var cnt = 0;
								$('.select:checked').map(function() {
									getData = $(this).attr("id");
									cnt++;
								});
								if (cnt == 0 || cnt > 1) {
				     				$(".errormessagediv").show();
				     				$(".validateTips").text("Select Atleast One Record");
				     				return false;
				     			}  
								else {
				     				var routeIdlist=getData;
									 window.location.href = "transport.html?method=editRouteMasterDetails&routeIdlist="+routeIdlist.trim()+
									"&locationid="+$("#locationid").val()+"&historystatus="+$("#status").val()+"&historysearch="+$("#routeIdlist").val();
								}
							});
					
					
					$('#inactive').click(function() {
						var count =0;
						routeCode=[];
						$(".select:checked").each(function(){
							var list=$(this).attr("id");
							routeCode.push(list);
							count ++;
						});	
						if(count == 0)	{
							$('.errormessagediv').show();
							$('.validateTips').text("Select any record.");
							$('.errormessagediv').delay(3000).slideUp();
						}
						else 	{
						    $("#dialog").dialog("open");
							$("#dialog").empty();
							$("#dialog").append("<p class='warningfont'>Are you sure to "+$("#inactive").text()+"?</p>");
							$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
							$("#dialog").append('<select name="inactivereason" class="warningfont" style="width: 100%;" id="inactivereason" onchange="HideError1(this)">'
									+ '<option value="">' + "----------select----------"
									+ '</option>'
									+ '<option value="Incorrect Entry">' + "Incorrect Entry"
									+ '</option>'
									+ '<option value="Not in use">' + "Not in use" 
									+ '</option>'
									+ '<option value="others">' + "Others" 
									+ '</option>'+
							'</select>');
							
							$("#dialog").append('<select name="activereason" class="warningfont" style="width: 100%;" id="activereason" onchange="HideError1(this)">'
									+ '<option value="">' + "----------select----------"
									+ '</option>'
									+ '<option value="Correct Entry">' + "Correct Entry"
									+ '</option>'
									+ '<option value="In use">' + "In use" 
									+ '</option>'
									+ '<option value="others">' + "Others" 
									+ '</option>'+
							'</select>');
							
							  $("#dialog").append('<div id="othreason"><label class="warningothers">OtherReason:</label><input type="text" style="width: 100%;" class="warningfont" name="other" id="otherreason" maxlength="249" onclick="HideError1(this)"/></div>');
					     
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
						autoOpen: false,
						resizable: false,
						modal: true,					    
						title:'Route Details',
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
									type : "POST",
									url : "transport.html?method=removeRouteMasterDetails",
									data : {
										"routeCode" :routeCode.toString(),
										"inactivereason":$("#inactivereason").val(), 
										"activereason":$("#activereason").val(),
										"otherreason":$("#otherreason").val(),
										"status":$("#inactive").text(),
										"locationid":$("#locationid").val(),
									},
									success : function(response) {
										var result = $.parseJSON(response);

										$('.errormessagediv').hide();

										if (result.status == "true") {
											$(".successmessagediv").show();
											$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
											$(".successmessage").text($("#inactive").text()+" the Route Detail Progressing...");
											$('.successmessagediv').delay(3000).slideUp();
										} else if(result.status == "false"){
											$(".errormessagediv").show();
											$(".successmessage").text("Selected Route Detail is Not "+$("#inactive").text());
											$('.errormessagediv').delay(3000).slideUp();
										}
										setTimeout(function(){
											window.location.href="menuslist.html?method=routeMasterSettings&location="+$("#locationid").val()
											+"&historylocId="+$("#historylocId").val()+"&currentstatus="+$("#status").val();
										},3000);
									}
								});  

								$(this).dialog("close");
								$("#selectall").prop("checked",false);
								routeCode=[];
							  }
							},
							"No" : function() {
								$(this).dialog("close");
							}
						}
					});
							
								

	$("#list").click(function() {
		 window.location.href ="menuslist.html?method=routeMasterSettings&location="+$('#locationid').val().trim()
		 +"&historystatus="+$("#historystatus").val()+"&historysearch="+$("#historysearch").val();
    });

	$("#searchid").click(function() {
		 routeMasterSettingBySearch();
		 });
	$("#routeIdlist").keypress(function(event) {
		if (event.keyCode == 13) {
		 routeMasterSettingBySearch();
		}
	});
	$("#resetbtn").click(function(){
		$("#routeIdlist").val("");
		$("#status").val("active");
		routeMasterSettingBySearch();
	});
	$("#routeIdlist").keydown(function(event) {
		if (event.keyCode == 13) {
			  routeMasterSettingBySearch();
		}
	});
										
								
					$("#myDialog").dialog({
					    autoOpen  : false,
					    resizable : false,
					    width     : 330,
					    modal     : true,
					    title     : "Stages",
					    buttons   : {
					              'OK' : function() {
					            	  $("#myTable tbody").empty();
					               
					                  var stagesNameArray=[];
					                  $('.selectBox:checked').each(function(){
					                	  var check=$(this).val();
					                	  stagesidArray.push(check);
					                	  stagesNameArray.push($(this).parent('td').next().text());
					                  });
					                 
					               $("#totalStops").val(stagesNameArray.length);
					                  $("#myTable").attr("style","display:table");   	
					                 
					      			for(var i=0;i<stagesNameArray.length;i++){
					      			$("#myTable tbody").append("<tr><td>"+(i+1)+"</td><td>"+ stagesNameArray[i]+"</td></tr>")	
					      			}
					      			 $(this).dialog('close');	
					              },
					              'Close' : function() {
					           
					                  $(this).dialog('close');
					              }
					                }
					});

					//Open the dialog box when the button is clicked.
					$('#checklocations').click(function() {
						  $("#myDialog").dialog("open");
					});
							
					$("#displayid").change(function(){

					  $(".select").prop('checked', $(this).prop("checked"));
						 
						});
			
		  if($("#historystatus").val()=="N" || $("#historysearch").val()!=""){
			  
			  $("#routeIdlist").val($("#historysearch").val());
			  
			  $("#status").val($("#historystatus").val());
			  if($("#status").val()=="Y"){ 
         			$("#inactive").text("InActive");
         		}
         		else{
         			$("#inactive").text("Active");
         		 }
			  routeMasterSettingBySearch();	
			}	
		  
		  if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined)
		  {
				if($("#currentstatus").val()=="Y"){ 
					$("#status").val("N");	
					$("#editDesignationId").hide();
					$("#inactive").text("Active");
					routeMasterSettingBySearch();
					}
					else{
					$("#status").val("Y");
					$("#editDesignationId").show();
					$("#inactive").text("InActive");
					routeMasterSettingBySearch();
				   }
		 }

		  if($("#time1").val()!=""){
			  
			  $("#starttime").val($("#time1").val())
			  $("#endtime").val($("#time2").val())
			  $('.form_time').datetimepicker({
					 
				    weekStart: 1,
				    todayBtn:  1,
					autoclose: 1,
					todayHighlight: 1,
					startView: 1,
					minView: 0,
					maxView: 1,
					forceParse: 0
				});
		  }
		  
   });

	/*$(document).ready(function() {
		
		$('#datetimepicker3').datetimepicker({
			pickDate : false
		});
		$('#datetimepicker4').datetimepicker({
			pickDate : false
		});
		
	});*/

 

function createRow(val1) {
	/*
	 * var tt = $('table#allstudent tr:last input[name=stopNo]').val(); if ((tt !=
	 * undefined) && (tt != '')) { var thenum = tt.replace(/^\D+/g, ''); for (
	 * var i = thenum; i > 0; i--) { removeRow(i); } }
	 */

	var value = val1;
	allstudent = 0;
	for ( var j = 0; j < value; j++) {
		addMoreRows();
	}
}

function removeRow(removeNum) {

	jQuery('#rowId' + removeNum).remove();
}

var allstudent = 0;
function addMoreRows() {
	allstudent++;
	$('#allstudent tr')
			.last()
			.after(
					'<tr id="rowId'
							+ allstudent
							+ '" cellpadding="5" cellspacing="5"><td><input value="    '
							+ allstudent
							+ '" type="text" name="stopNo" readonly="readonly" style="margin-left: 8px;height: 108%" size="3%""/></td><td><select name="stopName" id="stopName'
							+ allstudent
							+ '" class="form-control" onClick="return testFunction(this)"><option value=" ">----------Select----------</option></select><input type="hidden" class="htageid" id="htageid'
							+ allstudent
							+ '"></td><td class="arrivalTime"><div onclick="return getTime();" class="datetimepicker input-append"><input type="text" class="form-control" data-format="hh:mm" size="20%" name="arrTime" onClick="getDeptTime('
							+ allstudent
							+ ');" /><img src="./images/time1.jpg" width="25" height="20" class="add-on" style="margin-top:-14%;margin-left: 86%;"/></div></td><td><input name="haltTime" class="haltTime" id="haltTimeID" readonly="readonly" type="text"  maxlength="2" style="width: 38px;height: 32px;margin: 0 40px;    text-align: center;" /></td><td><input name="depTime" readonly="readonly" class="form-control" type="text" maxlength="5" /></td><td><input type="text" name="distance" class="form-control"   maxlength="3" /></td></td></tr>');

	$('.haltTime').val($('#hidstopid').val());

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStopNames",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			for ( var j = 0; j < result.stopslist.length; j++) {
				$("#stopName" + allstudent).append(
						'<option value="' + result.stopslist[j].stageCode
								+ '">' + result.stopslist[j].stageName
								+ '</option>');
			}

		}
	});

	/*
	 * <select class="form-control" name="category" id="category"> <option
	 * value=" ">----------Select----------</option> </select>
	 */

}
function getTime() {
	$('.datetimepicker').datetimepicker({
		pickDate : false,
		onClose : function() {
			getDeptTime(removeNum);
		}
	});
}
function getDeptTime(removeNum) {
	var dept_time = "";

	var regexpforInteger = /^([0-9])+$/;
	$('table#allstudent td input[name=arrTime]').blur(
			function() {
				var arrival_Time = $(this).parents('tr:first').find(
						'td:nth-child(3)').find('input').val().trim();
				var halt_Time = $(this).parents('tr:first').find(
						'td:nth-child(4)').find('input').val().trim();

				if ((arrival_Time != '') && (halt_Time != '')
						&& (regexpforInteger.test(halt_Time.trim()))) {

					var t1 = arrival_Time.split(/\D/);

					var x1 = parseInt(t1[0]) * 60 * 60 + parseInt(t1[1]) * 60;
					var x2 = parseInt('00') * 60 * 60 + parseInt(halt_Time)
							* 60;
					var s = x1 + x2;
					var m = Math.floor(s / 60);
					s = s % 60;
					var h = Math.floor(m / 60);
					m = m % 60;
					if (h.toString().length == 1) {
						h = "0" + h;
					}
					if (m.toString().length == 1) {
						m = "0" + m;
					}
					dept_time = h + ":" + m;
					$(this).parents('tr:first').find('td:nth-child(5)').find(
							'input').val(dept_time);
				}
			});
}

function testFunction(current) {

	var curid = "#" + current.id;

	// var arr = current.id.split('stopName');

	// var stagid = "#htageid" + arr[1].trim();

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getStopNames",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			/* $(curid).html(""); */
			/*
			 * $(curid).append( '<option value="' + "" + '">' +
			 * "-----Select-----" + '</option>');
			 */

			for ( var j = 0; j < result.stopslist.length; j++) {
				$(curid).append(
						'<option value="' + result.stopslist[j].stageCode
								+ '">' + result.stopslist[j].stageName
								+ '</option>');
			}

		}
	});

	/*
	 * $(curid).autocomplete({ source : function(request, response) {
	 * 
	 * $.ajax({
	 * 
	 * url : "transport.html?method=getStopNames", data : { searchTerm :
	 * request.term }, async : false, success : function(data) { var result =
	 * $.parseJSON(data);
	 * 
	 * response($.map(result.stopslist, function(item) { return { label :
	 * item.stageName, value : item.stageCode, } })); } }); },
	 * 
	 * select : function(event, ui) {
	 * 
	 * $(curid).val(ui.item.label); $(stagid).val(ui.item.value);
	 * 
	 * return false; }
	 * 
	 * });
	 */

}

function checkRouteNo() {
	routeid = $("#routeid").val().trim();

	if (routeid == "" || routeid == null) {
		routeid = "NULL";
	}
	var checkName = {
		"routeNo"    : $("#routeNo").val(),
		"routeid"    : routeid,
		"locationid" :$("#locationid").val()
	};
	var status = false;
	$.ajax({
		type : "POST",
		url : "transport.html?method=checkrouteNo",
		data : checkName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			text = result.status;

			if (text == true) {
				$('.errormessagediv').show();
				$('.validateTips').text("Route No already Exist");
				status = true;
			} else {
				$('.errormessagediv').hide();
			}
		}
	});

	return status;

}


function routeMasterSettingBySearch(){
	var datalist = {
			"searchvalue" :$("#routeIdlist").val(),
			"location":$('#locationid').val(),
			"status":$("#status").val(),
		}; 
		$.ajax({
			type : 'POST',
			url : "transport.html?method=routeMasterSettingBySearch",
			data : datalist,
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.list.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].routeCode+"' /></td>"
							+"<td> "+result.list[i].routeNo+" </td>"
							+"<td> "+result.list[i].routeName+"</td>"
							+"<td> "+result.list[i].stratTime+" </td>"
							+"<td> "+result.list[i].endTime+" </td>"
							+"<td> "+result.list[i].totalStops+" </td>"
							+"<td> "+result.list[i].totalDistance+" </td>"
							+"<td> "+result.list[i].halttime+" </td>"
							+"<td> "+result.list[i].remarks+" </td>"
							+"</tr>");
					    }
					}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='9'>No Records Founds</td>" +"</tr>");
					}
					checkboxsselect();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. Of Records "+len+".");
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
 

function stagecheckbox(){
	$(".selectAll").change(function(){
		$(".selectBox").prop('checked', $(this).prop("checked"));
	});
	
	$(".selectBox").change(function(){
        if($(".selectBox").length==$(".selectBox:checked").length){
	         $(".selectAll").prop("checked",true);
         }
       else{
	           $(".selectAll").prop("checked",false);
           }
	});
}
$(document).ready(function() {
	
	$('#datetimepicker3').datetimepicker({
		pickDate : false
	});
	$('#datetimepicker4').datetimepicker({
		pickDate : false
	});
	
});
function HideError(pointer) 
{
document.getElementById(pointer.id).style.border = "1px solid #ccc";
document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

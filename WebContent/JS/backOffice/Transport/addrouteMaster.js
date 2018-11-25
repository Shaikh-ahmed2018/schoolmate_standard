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
	$('#routeLogicName').keyup(function(){
	    $(this).val($(this).val().toUpperCase());
	});
	 $('#routeLogicName').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-&.(\][)\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, (, ), [, ], &");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	     
	    });
	 $('#routeNo').keypress(function (e) {
	        var regex = new RegExp(/^[\sa-zA-Z0-9.-]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics , . , and -");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
					$("#totalDistance").keypress(function(evt){
						var iKeyCode = (evt.which) ? evt.which : evt.keyCode;
						if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57))
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Only Number.");
							document.getElementById("totalDistance").style.border = "1px solid #AF2C2C";
							document.getElementById("totalDistance").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
					});	
					
					$("#halttime").keypress(function(evt){
						var iKeyCode = (evt.which) ? evt.which : evt.keyCode;
						if (iKeyCode != 46 && iKeyCode > 31 && (iKeyCode < 48 || iKeyCode > 57)) 
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Only Number.");
							document.getElementById("halttime").style.border = "1px solid #AF2C2C";
							document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
						else if(!$("#halttime").val() < 0 || $("#halttime").val() > 59){
							
							$('.errormessagediv').show();
							$('.validateTips').text("Halt time Should be between 0 to 59");

							document.getElementById("halttime").style.border = "1px solid #AF2C2C";
							document.getElementById("halttime").style.backgroundColor = "#FFF7F7";
							$('#halttime').val('');
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
						}
						
					}); 
					
					$(".starttime1").click(function(){
						document.getElementById("starttime1").style.border = "1px solid #ccc";
						document.getElementById("starttime1").style.backgroundColor = "#fff";
					});
					$(".endtime1").click(function(){
						document.getElementById("endtime1").style.border = "1px solid #ccc";
						document.getElementById("endtime1").style.backgroundColor = "#fff";
					});
					$("#totalDistance").click(function(){
						HideError(this);
					});
					
					$("#halttime").click(function(){
						HideError(this);
					});
					
					$("#routeLogicName").click(function(){
						HideError(this);
					});
					stagecheckbox();
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

	$("#list").click(function() {
		 window.location.href ="menuslist.html?method=routeMasterSettings&location="+$('#locationid').val().trim()
		 +"&historystatus="+$("#historystatus").val()+"&historysearch="+$("#historysearch").val();
    });

	$("#routeIdlist").keypress(function(event) {
		if (event.keyCode == 13) {
		 routeMasterSettingBySearch();
		}
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
					

	     $("#save").click(function() {

	    	 if(stagesidArray.length==0){
					$("#myTable tbody tr").each(function(){
						stagesidArray.push($(this).attr("id"));	
					});	
				}
				var routeName = $('#routeName').val().trim();
				var routeNo = $('#routeNo').val().trim();
				var routeLogicName = $('#routeLogicName').val();
				var totalStops = $('#totalStops').val().trim();
				var startTime = $('#starttime1').val().trim();
						
				var endTime = $('#endtime1').val().trim();

				var totalDistance = $('#totalDistance').val().trim();

				var halttime = $('#halttime').val().trim();

				var routeid = $('#routeid').val().trim();
				var location=$('#locationid').val().trim();
				if (!checkTimefor()) {
					$('.errormessagediv').show();
					$('.validateTips').text("Start time should be less then End time");
					return false;
				}
				if (routeNo == "" || routeNo == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required - Route No");
					document.getElementById("routeNo").style.border = "1px solid #AF2C2C";
					document.getElementById("routeNo").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					
					return false;
				} 
				
				else if (checkRouteNo()) {
					$('.errormessagediv').show();
					$('.validateTips').text("Route No already Exist");
					document.getElementById("routeNo").style.border = "1px solid #AF2C2C";
					document.getElementById("routeNo").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				} else if (routeName == "" || routeName == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required - Route Name");
					document.getElementById("routeName").style.border = "1px solid #AF2C2C";
					document.getElementById("routeName").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					
					return false;
				} else if (startTime == ""|| startTime == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required - Start Time");
					document.getElementById("starttime1").style.border = "1px solid #AF2C2C";
					document.getElementById("starttime1").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				} else if (endTime == "" || endTime == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Field Required - End Time");
					document.getElementById("endtime1").style.border = "1px solid #AF2C2C";
					document.getElementById("endtime1").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}  else if (stagesidArray == "" || stagesidArray == null) {
					$('.errormessagediv').show();
					$('.validateTips').text("Click 'Add Stage' Button  below for for adding stages");
					document.getElementById("stagesidArray").style.border = "1px solid #AF2C2C";
					document.getElementById("stagesidArray").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 5000);
					return false;
				} else {
					if (routeid == "" || routeid == null) {
						routeid = "NULL";
					}
					var Check = {
						"routeName" : routeName,
						"routeNo" : routeNo,
						"routeLogicName" : routeLogicName,
					"totalStops" : totalStops,
						"startTime" : startTime,
						"endTime" : endTime,
					"totalDistance" : totalDistance,
						"routeid" : routeid,
						"stagesidArray":stagesidArray.toString(),
						"haltTime": halttime,
						"location":location
					};
					
					$.ajax({
						type : "POST",
						url : "transport.html?method=insertRouteMasterDetails",
						data : Check,
						success : function(data) {
							var result = $
							.parseJSON(data);
							if (result.status == "success") {
								$('.successmessagediv').show();
								$('.validateTips').text("Adding Record Progressing...");
								setTimeout(function() {
											window.location.href ="menuslist.html?method=routeMasterSettings&location="+location;
										}, 3000);
								
							}
							else if (result.status == "faild") {
								$('.errormessagediv').show();
								$('.validateTips').text("Route Insertion failed");
								setTimeout(function() {
											window.location.href = "transport.html?method=addRouteScreen&locationid="+location;
										}, 3000);
							}
							else if(result.status == "success1") {
									$('.successmessagediv').show();
									$('.validateTips').text("Updating Record Progressing...");
									setTimeout(function() {
									   window.location.href ="menuslist.html?method=routeMasterSettings&location="+location;
									 }, 3000);
								}
						}
					});

				}
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
			  
			  $("#starttime1").val($("#time1").val())
			  $("#endtime1").val($("#time2").val())
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
var status = false;
function checkTimefor() {
	var ftime = document.getElementById("starttime1").value;
	var ttime = document.getElementById("endtime1").value;
	
	if ((ftime != "") && (ttime != "")) {

		var ftimeSplitHour = ftime.split(':')[0];
		var ftimeSplitMin = ftime.split(':')[1];
		//var ftimeSplitSec = ftime.split(':')[2];

		var ttimeSplitHour = ttime.split(':')[0];
		var ttimeSplitMin = ttime.split(':')[1];
		//var ttimeSplitSec = ttime.split(':')[2];
        
		 
		if (ftimeSplitHour.charAt(0) == 0) {
			ftimeSplitHour = ftimeSplitHour.charAt(1);
		}
		if (ttimeSplitHour.charAt(0) == 0) {
			ttimeSplitHour = ttimeSplitHour.charAt(1);
		}

		if (ftimeSplitMin.charAt(0) == 0) {
			ftimeSplitMin = ftimeSplitMin.charAt(1);
		}
		if (ttimeSplitMin.charAt(0) == 0) {
			ttimeSplitMin = ttimeSplitMin.charAt(1);
		}

		/*if (ftimeSplitSec.charAt(0) == 0) {
			ftimeSplitSec = ftimeSplitSec.charAt(1);
		}
		if (ttimeSplitSec.charAt(0) == 0) {
			ttimeSplitSec = ttimeSplitSec.charAt(1);
		}*/

		ftimeSplitHour = parseInt(ftimeSplitHour);
		ttimeSplitHour = parseInt(ttimeSplitHour);
		ftimeSplitMin = parseInt(ftimeSplitMin);
		ttimeSplitMin = parseInt(ttimeSplitMin);

		/*ftimeSplitSec = parseInt(ftimeSplitSec);
		ttimeSplitSec = parseInt(ttimeSplitSec);
*/
		if (ftimeSplitHour > ttimeSplitHour) {
			$(".errormessagediv").show();
			$(".validateTips").text("The End Time should not less than Start Time");
			return false;
			status = false;
			document.getElementById("endtime1").value = "";
		}
		if (ttimeSplitHour == ftimeSplitHour) {
			if (ftimeSplitMin > ttimeSplitMin) {
				$(".validateTips").text("The End Time should not less than Start Time");
				$(".errormessagediv").show();
				return false;
				status = false;
				document.getElementById("endtime1").value = "";
			}
			/*if (ftimeSplitMin == ttimeSplitMin) {
				if (ftimeSplitSec >= ttimeSplitSec) {
					$(".validateTips").text("The End Time should not less than Start Time");
					$(".errormessagediv").show();
					return false;
					status = false;
					document.getElementById("endtime1").value = "";
				}
			}*/
		} else {
			$(".errormessagediv").hide();
			status = true;
		}
	} else {
		status = true;
	}
	return status;
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

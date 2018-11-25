var dateexist=null;
var maxDate=0;
minDate=0;
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction()
{
	var status = false;
	var id=$('#termmasterid').val();
	var name = $('#termname').val();
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();   
	var transId="N";
	
  if($("#locationname").val()=="" || $("#locationname").val()==null || $("#locationname").val().trim()=="")
	{
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Branch");
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 2000);
		return false;
	}
	else if(name=="" || name==null || name.trim()=="")
	{
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Term Name");
		document.getElementById("termname").style.border = "1px solid #AF2C2C";
		document.getElementById("termname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 2000);
		
		return false;
	}
	else if (startdate == "" || startdate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Start Date");
		document.getElementById("startdate").style.border = "1px solid #AF2C2C";
		document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		
		return false;
	}
	
	/*if(accstartdate > startdate ){
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Start Date should greater than Accyear Date");
		return false;
		
	}*/

	else if (enddate == "" || enddate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - End Date");
		document.getElementById("enddate").style.border = "1px solid #AF2C2C";
		document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	
	
/*	if(enddate < accenddate ){
		
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("End Date should less than Accyear Date");
		return false;
		
	}*/
	
	else if (transId == "" || transId == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Transport Type");
		
		return false;
	}
	else{
		datalist = {"name" : name, 
					"id" :id,
					"locationId":$("#locationname").val(),
					"accyear":$("#Acyearid").val()
				},
		
		$.ajax({
			
		type : "POST",
		
		url : "termfee.html?method=getnamecount",
		
		data : datalist,
		async:false,
		
		success : function(data)
			{
				var result = $.parseJSON(data);
				 
				
				if (result.message=="inactive")
				{
					$(".successmessagediv").hide();
					$(".errormessagediv1").show();
					$(".validateTips1").text("This Term Name Already Exist !! Make it Active");
					status = false;
				}
				if(result.message=="true")
				{
						$(".successmessagediv").hide();
						$(".errormessagediv1").show();
						$(".validateTips1").text("Term Name Already Exist");
						document.getElementById("termname").style.border = "1px solid #AF2C2C";
						document.getElementById("termname").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv1').fadeOut();
						}, 3000);
						return false;
						
						status = false;
				}
				else
				{
						status = true;
				}
			}
	});
		
		return status;	
    }
}

$(document).ready(function(){
	 
	$("#Acyearid").val($("#hacademicyaer").val().trim()) 
	$("#locationname").val($("#curr_loc").val());
	
	if($("#termmasterid").val()!=""){
		$("#Acyearid").val($("#hiddenaccyearId").val());
		$("#startdate").removeClass("hasDatepicker");
		
	}
	
	$("#termname").keypress(function(e){
		 var k=e.keyCode;
		 $return = ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32  || (k >= 48 && k <= 57));
		  if(!$return) {
		 /*if(!((key == 8) || (key == 16) || !(e.shiftKey && (key >= 48 && key <= 57))  || (key == 32) || (key == 46) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90) ||  (key >= 96 && key <= 105))){*/
				 
				$(".errormessagediv1").show();
				$(".validateTips1").text("Sorry, You can't enter special character");
				return false;
			}
	});
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv1").hide();
	}, 3000);
	
	
	
	var trans = $('#radio').val();
	
	if (trans == "YES") {
		$("#transIdY").attr("checked", true);
	} else if (trans == "NO") {
		$("#transIdN").attr("checked", true);
	}

	$('#startdate').datepicker('destroy');
	$('#enddate').datepicker('destroy');
	dateRange($("#Acyearid").val());
	
	$("#startdate").datepicker({
		maxDate:$("#enddates").val()-1,
		minDate:$("#lastDate").val(),
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		onSelect:function(dateStr){
			var min = $(this).datepicker('getDate'); // Get selected date
			$("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
		}

	});

	$("#enddate").datepicker({
		maxDate:$("#enddates").val()-1,
		minDate:$("#lastDate").val(),
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		onSelect:function(){
			
			var max = $(this).datepicker('getDate'); // Get selected date
	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
		}

	});
	

	
	
	$("#AccStartDate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});

	$("#AccEndDate").datepicker({

		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",

	});
	
	
	
	$("#save").click(function() 
				{
	
					var id=$('#termmasterid').val();
					var name = $('#termname').val();
					var description = $('#termdescription').val();
					var startdate = $('#startdate').val();
					var enddate = $('#enddate').val();
					var transId='N';
					
					if (myFunction())
					{
					
					  datalist = {"name" : name, 
							"description" : description, 
							"startdate":startdate, 
							"enddate":enddate, 
							"transId":transId, 
							"locationId":$("#locationname").val(),
							"academic_year":$("#Acyearid").val(),
							"id" :id},
					
					$.ajax({
					type : "POST",
					url : "termfee.html?method=addtermfeedetails",
					data : datalist,
					success : function(data)
						{
					
						var result = $.parseJSON(data);
						if (result.jsonResponse == "Term Details Added Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Adding Record Progressing...");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=termList&historylocId="+$("#historylocId").val()+
								"&historyacademicId="+$("#historyacademicId").val();
							},3000);
						}
						if (result.jsonResponse == "Term Details not Added Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Term Details not Added Successfully");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=termList&historylocId="+$("#historylocId").val()+
								"&historyacademicId="+$("#historyacademicId").val();
							 },3000);
						}
						if (result.jsonResponse == "Term Details Updated Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Updating Record progressing..");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=termList&historylocId="+$("#historylocId").val()+
								"&historyacademicId="+$("#historyacademicId").val();
							 },3000);
						}

						if (result.jsonResponse == "Term Details not Updated Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Term Details not Updated Successfully");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=termList&historylocId="+$("#historylocId").val()+
								"&historyacademicId="+$("#historyacademicId").val();
							 },3000);

						}
					}
			 	   	
				});
			}
		});
	
	$("#back1").click(function()
			{
				window.location.href = "menuslist.html?method=termList&historylocId="+$("#historylocId").val()+
				"&historyacademicId="+$("#historyacademicId").val();
			});
	
	
	$("#view").click(function(event)
			{
				window.location.href = "menuslist.html?method=termList";
				
			});
	
	$("#locationname").change(function(){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($("#Acyearid").val());
		
		$("#startdate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(dateStr){
				var min = $(this).datepicker('getDate'); // Get selected date
				$("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
			}

		});

		$("#enddate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(){
				
				var max = $(this).datepicker('getDate'); // Get selected date
		        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			}

		});
		
	});
	$("#Acyearid").change(function(){
		$('#startdate').val("");
		$('#enddate').val("");
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($(this).val());
		
		$("#startdate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(dateStr){
				
				
				        var min = $(this).datepicker('getDate'); // Get selected date
				        $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
			}
		});

		$("#enddate").datepicker({
			maxDate:maxDate-1,
			minDate:minDate,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
			onSelect:function(){
				
				var max = $(this).datepicker('getDate'); // Get selected date
		        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			}
		});
	});
	
		});
function dateValidateoverLap(){
	var da=new Date();
	var year=da.getFullYear();
	var month=da.getMonth()+1;
	if(month<10){
		month="0"+month;
	}
	var day=da.getDate();
	if(day<10){
		day="0"+day;
	}
	date=year+"-"+month+"-"+day;
	var datalist={'date':date};
	$.ajax({
	type : "POST",
	
	url : "termfee.html?method=dateOverLapValidate",
	
	data : datalist,
	
	success : function(data)
	
		{
		
		
		var result = $.parseJSON(data);
			
		dateexist=result.status;
	
	
}
		
});
	
	return $("#lastDate").val();
}
function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
function dateRange(accyear){
	
	$.ajax({
		type:"POST", 
		url:"termfee.html?method=daterange",
		data:{"accyear":accyear,
		"locationId":$("#locationname").val()	
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			minDate=result.startDate.split(",")[0];
			maxDate=result.startDate.split(",")[1];
			$("#lastDate").val(result.startDate.split(",")[0]);
			$("#enddates").val(result.startDate.split(",")[1]);
		}
	
	});
}
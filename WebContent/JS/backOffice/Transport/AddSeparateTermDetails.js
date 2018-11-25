var dateexist=null;
var maxDate=0;
minDate=0;
value="";
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction()
{
	
	var status = false;
	var id=$('#termmasterid').val();
	var name = $('#termname').val();
	var description = $('#termdescription').val();
	var startdate = $('#startdate').val();
	var enddate = $('#enddate').val();   
	var transId=$('input[name=trans]:checked').val();
	var locId=$("#locationname").val();
	
	if(locId=="" || name==null || locId.trim()=="")	
	{
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Branch Name");
		document.getElementById("locationname").style.border = "1px solid #AF2C2C";
		document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		
		return false;
	
	}
	
	if(name=="" || name==null || name.trim()=="")	
	{
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - Term Name");
		document.getElementById("termname").style.border = "1px solid #AF2C2C";
		document.getElementById("termname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		
		return false;
	
	}
	
	
	
	if (startdate == "" || startdate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - the StartDate");
		document.getElementById("startdate").style.border = "1px solid #AF2C2C";
		document.getElementById("startdate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	
	

	if (enddate == "" || enddate == undefined) {
		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Field Required - the EndDate");
		document.getElementById("enddate").style.border = "1px solid #AF2C2C";
		document.getElementById("enddate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv1').fadeOut();
		}, 3000);
		return false;
	}
	
	
		datalist = {"name" : name, "id" :id, "locationId":$("#locationname").val(),"accyear":$("#Acyearid").val()},
		$.ajax({
			
		type : "POST",
		
		url : "transport.html?method=getTermnamecount",
		
		data : datalist,
		async:false,
		
		success : function(data)
			{
				var result = $.parseJSON(data);
				
				if(result.message)
					{
						
						$(".successmessagediv").hide();
						$(".errormessagediv1").show();
						$(".validateTips1").text("Term Name Already Exists");
						document.getElementById("termname").style.border = "1px solid #AF2C2C";
						document.getElementById("termname").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv1').fadeOut();
						}, 3000);
						return false;
						status=false;
					}
					else
					{
						status=true;
					}
			}
	   	
	});
		return status;	

}


$(document).ready(function(){
	
	$("#Acyearid").val($("#current_academicYear").val());
	
	
	$('#termname').keypress(function (e) {
        var regex = new RegExp(/^[\sa-zA-Z0-9]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv1").show();
		$(".validateTips1").text("Sorry You Can't Enter Special Character");
		setTimeout(function() {
			$('#errormessagediv1').fadeOut();
		    },3000);
        return false;
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
	$("#Acyearid").change(function(){
		$('#startdate').val("");
		$('#enddate').val("");
		$("#noofmonths").val("");
		dateRange($("#Acyearid").val());
		checkdatevalue=checkdateRange($("#Acyearid").val());
		commondate();
	});
	
	 checkdatevalue="";
	
	if($("#locationname").val()!=""){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($("#Acyearid").val());
		checkdatevalue=checkdateRange($("#Acyearid").val());
		commondate();
	}
	
	$("#locationname").change(function(){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($("#Acyearid").val());
		
		checkdatevalue=checkdateRange($("#Acyearid").val());
		commondate();
	});
	 
	
	
	$("#back1").click(function(){
		 window.location.href = "menuslist.html?method=separateTransportTermList&historylocId="+$("#historylocId").val()+
		  "&historyacademicId="+$("#historyacademicId").val();
	});
	
	$("#Acyearid").change(function(){
		$('#startdate').datepicker('destroy');
		$('#enddate').datepicker('destroy');
		dateRange($(this).val());
		var checkdatevalue=checkdateRange($(this).val());
		
		commondate();
		
	});
	
	$("#save").click(function() 
				{
					var id=$('#termmasterid').val();
					var name = $('#termname').val();
					var description = $('#termdescription').val();
					var startdate = $('#startdate').val();
					var enddate = $('#enddate').val();
					var noofmonths = $('#noofmonths').val();
					var transId='Y';
					if (myFunction())
					{
					datalist = {
							"name" : name, 
							"description" : description, 
							"startdate":startdate, 
							"enddate":enddate, 
							"transId":transId,
							"locationId":$("#locationname").val(),
							"academic_year":$("#Acyearid").val(),
							"id" :id,
							"noofmonths" :noofmonths
							},
					
					$.ajax({
					type : "POST",
					url : "transport.html?method=addtermSeparatefeedetails",
					data : datalist,
					success : function(data)
						{
						var result = $.parseJSON(data);
						if (result.jsonResponse == "Term Details Added Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Adding Record Progressing...");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=separateTransportTermList&historylocId="+$("#historylocId").val()+
								  "&historyacademicId="+$("#historyacademicId").val();
						   },3000);
						}

						if (result.jsonResponse == "Term Details not Added Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Term Details not Added Successfully");

							setTimeout(function(){
								window.location.href = "menuslist.html?method=separateTransportTermList&historylocId="+$("#historylocId").val()+
							    "&historyacademicId="+$("#historyacademicId").val();
						     },3000);

						}
						if (result.jsonResponse == "Term Details Updated Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Updating Record progressing..");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=separateTransportTermList&historylocId="+$("#historylocId").val()+
								 "&historyacademicId="+$("#historyacademicId").val();
							 },3000);
						}

						if (result.jsonResponse == "Term Details not Updated Successfully") {
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Term Details not Updated Successfully");

							setTimeout(function() {
								window.location.href = "menuslist.html?method=separateTransportTermList&historylocId="+$("#historylocId").val()+
							    "&historyacademicId="+$("#historyacademicId").val();
							},3000);

						}
						
				   }
			 	   	
				});
	  }
				
 });
	
	
	
	
	$("#view").click(function(event)
			
			
			{
				window.location.href = "menuslist.html?method=separateTransportTermList";
				
			});
	
		});

function monthDiff(d1, d2) {
    var months;
    months = (d2.getFullYear() - d1.getFullYear()) * 12;
    months -= d1.getMonth();
    months += d2.getMonth();
    return months <= 0 ? 0 : months;
}

function dateRange(accyear){
	
	$.ajax({
		type:"POST", 
		url:"termfee.html?method=transportdaterange",
		data:{"accyear":$("#Acyearid").val(),
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

function checkdateRange(accyear){
	
	$.ajax({
		type:"POST", 
		url:"termfee.html?method=transportdaterange",
		data:{"accyear":accyear,
		"locationId":$("#locationname").val()	
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			value=result.startDate;
		}
	
	});
	
	return value;
}

function commondate(){
	
	var checkStartdate=checkdatevalue.split(",")[0];
	var checkEnddate=checkdatevalue.split(",")[1];
	
	$("#startdate").click(function(){
		 
		if(checkStartdate==checkEnddate){
			 $("#startdate").datepicker("hide");
			   $(".successmessagediv").hide();
				$(".errormessagediv1").show();
				$(".validateTips1").text("Term setup completed in "+$("#Acyearid option:selected").text()+" Academic Year.");
				setTimeout(function() {
					 $(".errormessagediv1").hide();
				},5000);
		}else{
			$(".errormessagediv1").hide();
			$("#startdate").datepicker("show");
		     $("#startdate").show();
			} 
	});
	
	$("#enddate").click(function(){
		 
		if(checkStartdate==checkEnddate){
			 $("#enddate").datepicker("hide");
			   $(".successmessagediv").hide();
			   $(".errormessagediv1").show();
			   $(".validateTips1").text("Term setup completed in "+$("#Acyearid option:selected").text()+" Academic Year.");

			   setTimeout(function() {
				  $(".errormessagediv1").hide();
				},5000);
		}else if($("#startdate").val()==""){
			 $("#enddate").datepicker("hide");
			   $(".successmessagediv").hide();
			   $(".errormessagediv1").show();
			   $(".validateTips1").text("Select Start Date.");

			   setTimeout(function() {
				  $(".errormessagediv1").hide();
				},5000);
		}
		else{
			$(".errormessagediv1").hide();
			$("#enddate").datepicker("show");
		     $("#enddate").show();
			} 
	});
	
	$("#startdate").datepicker({
		maxDate:maxDate-1,
		minDate:minDate,
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
		onSelect:function(dateStr){
			 
	        var min = $(this).datepicker('getDate'); // Get selected date
	        $("#enddate").datepicker('option', 'minDate', min || $("#lastDate").val()); // Set other min, default to today
	   
	        if($("#enddate").datepicker('getDate') != null){
				sd = new Date(min);
				ed = new Date($("#enddate").datepicker('getDate'));
				ed.setDate(ed.getDate() - sd.getDate());
				$("#noofmonths").val(monthDiff(sd,ed)+1);
			}
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
	        sd = new Date($("#startdate").datepicker('getDate'));
			ed = new Date(max);
			ed.setDate(ed.getDate() - sd.getDate());
			$("#noofmonths").val(monthDiff(sd,ed)+1);
		}

	});
}

function HideError(pointer){

	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

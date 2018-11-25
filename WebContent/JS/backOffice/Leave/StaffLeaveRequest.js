$(document).ready(function(){
	
	$("#dwdbut").hide();
	
	if($("#hiddensno").val()!=""){
		
		$("#leavetype").val($("#hiddenleavetype").val());
		$("#startsessionDay").val($("#hiddenstartsession").val());
		$("#endsessionDay").val($("#hiddenendsession").val());
		$("#leavetype").val($("#hiddenleavetype").val());
		$("#requesttoid").val($("#hiddenrequestto").val());
		getleavepolicies($("#leavetype"));
		
	}
	if($("#hiddenprofile").val().trim()!=''){
		$('#downloadIdTitle ,#document2btn').show();
		$('#fileupload').hide();
	}
	else
	{
		$('#fileupload').show();
		$('#downloadIdTitle ,#document2btn').hide();
	}
	
	$("#downloadIdTitle").click(function(){
		
		$("#fileupload").show();
		
		$("#downloadIdTitle").hide();
		$("#document2btn").hide();
		
	});
	
var fileupload = $("#hiddenprofile").val();
	
	$("#document2btn").attr('name',$("#hiddenprofile").val());
	
	$('#document2btn').click(
			function() {
				
				var path = $(this).attr('name');
			
				
				if(path == "" || path == null){
					
					$('.errormessagediv').show();
					$('.validateTips').text("No File for download");
					return false;
					
				}
				else{
					
					window.location.href = "teachermenuaction.html?method=downloadfile&Path="
						+ path.trim();
				}
				
});
	
	
	$("#leavetype").change(function(){
		$("#Fromdate").datepicker("destroy");
 		$("#todate").datepicker("destroy");
		
		getleavepolicies($(this));
		
		var isprob = parseFloat($("#ISPROB").val());	
		var doj = parseFloat($("#DOJ").val());	
		
		if(isprob !=0 && doj < isprob){
			$(".errormessagediv").show();
			$(".validateTips").text("Not eligible to apply for"+" "+$("#leavetype option:selected").text());
			$(this).val("");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
	});
	
	$("#startsessionDay,#endsessionDay").change(function(){
		callSession();
	})
	
	$("#endsessionDay").val("SH");
	
	$("#save").click(function(event){
		
		$(".errormessagediv").hide();
		var minla = parseFloat($("#MINLPM").val());
		var maxnla = parseFloat($("#MINLPM").val());
		var totalleaves = parseFloat($("#totalleaves").val());
		var availableL = parseFloat($("#TOTL").val());
	    var  file  = document.getElementById('fileupload').value;
		if($("#leavetype").val().trim() == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Leave type");
			document.getElementById("leavetype").style.border = "1px solid #AF2C2C";
			document.getElementById("leavetype").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}else if($("#requesttoid").val().trim() == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Requesting to");
			document.getElementById("requesttoid").style.border = "1px solid #AF2C2C";
			document.getElementById("requesttoid").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
		else if($("#Fromdate").val().trim() == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - From Date");
			document.getElementById("Fromdate").style.border = "1px solid #AF2C2C";
			document.getElementById("Fromdate").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}else if($("#todate").val().trim() == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - To Date");
			document.getElementById("todate").style.border = "1px solid #AF2C2C";
			document.getElementById("todate").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
		else if($("#reason").val().trim() == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Reason");
			document.getElementById("reason").style.border = "1px solid #AF2C2C";
			document.getElementById("reason").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}else if(totalleaves == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("No.Of leaves must be greater than 0");
			document.getElementById("totalleaves").style.border = "1px solid #AF2C2C";
			document.getElementById("totalleaves").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
		else if(availableL == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("Leaves are not available for selected leave type");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
		else if(totalleaves > availableL){
			$(".errormessagediv").show();
			$(".validateTips").text("Applied leaves must be less than available leaves");
			document.getElementById("totalleaves").style.border = "1px solid #AF2C2C";
			document.getElementById("totalleaves").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
		else if(minla!=0 && totalleaves < minla){
			$(".errormessagediv").show();
			$(".validateTips").text("Min"+" "+minla+" "+$("#leavetype option:selected").text()+" "+"leave allowed per month");
			document.getElementById("totalleaves").style.border = "1px solid #AF2C2C";
			document.getElementById("totalleaves").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
		else if(maxnla!=0 && totalleaves > maxnla){
			$(".errormessagediv").show();
			$(".validateTips").text("Max"+" "+maxnla+" "+$("#leavetype option:selected").text()+" "+"leave allowed per month");
			document.getElementById("totalleaves").style.border = "1px solid #AF2C2C";
			document.getElementById("totalleaves").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}
		else if($("#reason").val().trim()==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Reason");
			document.getElementById("reason").style.border = "1px solid #AF2C2C";
			document.getElementById("reason").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
		}else if (file!="" && !isImage(file)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Upload valid file");
			document.getElementById("fileupload").style.border = "1px solid #AF2C2C";
			document.getElementById("fileupload").style.backgroundColor = "#FFF7F7";
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
        }
		else{ //stop submit the form, we will post it manually.
	        event.preventDefault();

	   
	        var form = $('#leaverequestid')[0];

			
	        var data = new FormData(form);

		
	        data.append("leavetype", $("#leavetype").val());
	        data.append("requestto", $("#requesttoid").val());
	        data.append("fromdate", $("#Fromdate").val());
	        data.append("todate", $("#todate").val());
	        data.append("starttime", $("#startsessionDay").val());
	        data.append("endtime", $("#endsessionDay").val());
	        data.append("reason", $("#reason").val());
	        data.append("totalleave", $("#totalleaves").val());
	        data.append("accyid", $("#academic_year").val());
	        data.append("sno", $("#hiddensno").val());
	        data.append("leavecode",$("#leavetype option:selected").text().split("-")[0])
	        $("#save").prop("disabled", true);

	        $.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "teachermenuaction.html?method=leaveRequestEntry",
	            data: data,
	            processData: false,
	            contentType: false,
	            cache: false,
	            timeout: 600000,
	            success: function(data) {
	            	
	            	var result = $.parseJSON(data);
	            	
	            	if(result.status == "true"){
	            		$(".successmessagediv").show();
		    			$(".validateTips").text("Leave request sent successfully");
		    			setTimeout(function(){
		    				$(".successmessagediv").hide();
		    				window.location.href='teachermenuaction.html?method=leaveRequest';
		    			},3000);
	            	}else{

	            		$(".errormessagediv").show();
		    			$(".validateTips").text("Leave Request no sent. Try again!!! ");
		    			setTimeout(function(){
		    				$(".errormessagediv").hide();
		    			},3000);
	            	
	            	}
	            	
	                $("#save").prop("disabled", false);

	            },
	            error: function (e) {

            		$(".errormessagediv").show();
	    			$(".validateTips").text("Leave Request not sent. Try again!!! ");
	    			setTimeout(function(){
	    				$(".errormessagediv").hide();
	    			},3000);
            	
	                $("#save").prop("disabled", false);

	            }
	        });}
	});
	
});

function getApprovers(){
	
	$.ajax({
		
		type : "POST",
		url : "teachermenuaction.html?method=getApprovers",
		data : {"aid" : $("#leavetype").val()},
		async : false,
		success : function(data){
			 var result = $.parseJSON(data);
			 $('#requesttoid').empty();
			 $('#requesttoid').append("<option value=''>--------Select----------</option>");
			 $.each(result['aList'],function(i,aList) {
	                var option = $('<option/>');
	                option.attr('value', result.aList[i].teacherId).text(result.aList[i].teacherName);
	                $('#requesttoid').append(option);
	        });
			
		}
	});
}
function getleavepolicies(pointer){
	
	$.ajax({
		
		type : "POST",
		url : "teachermenuaction.html?method=getleavepolicies",
		data : {"aid" : $("#leavetype").val()},
		async : false,
		success : function(data){
			var obj = $.parseJSON(data);
			 $.each(obj['details'],function(i,details) {
				 $("#MINLPM").val(obj.details[i].min_lea_per_month);
				 $("#MAXLPM").val(obj.details[i].max_leav_per_month);
				 $("#MAXCLPM").val(obj.details[i].max_consecu_lea_perm);
				 $("#LACCU").val(obj.details[i].accumuLations);
				 $("#LCYC").val(obj.details[i].leave_cycle);
				 $("#CLCSTD").val(obj.details[i].custom_stdate);
				 $("#CLCENDD").val(obj.details[i].custom_enddate);
				 $("#ISPROB").val(obj.details[i].isprobationary);
				 $("#ISCARRY").val(obj.details[i].carryFs);
				 $("#WKIN").val(obj.details[i].week_off_inclusion);
				 $("#ELEAVE").val(obj.details[i].available_leave);
				 $("#DATER").val(obj.details[i].daterange);
				 
			 });
			 		totleaves = parseFloat($("#allstudent tbody tr").find("."+pointer.val()).text());
			 		balleaves = parseFloat($('#'+pointer.val()).find('.balL').text());
			 		
			 		if(balleaves!=0 && balleaves<totleaves)
			 		$("#TOTL").val(balleaves);
			 		else
			 		$("#TOTL").val(totleaves);
			 			
			 		var mindate = new Date($("#CLCSTD").val());
			 		var maxdate = new Date($("#CLCENDD").val())
			 		
			 		var disableddates = [];
			 		
			 		if($("#DATER").val()!=""){
			 			for(var i=0;i<$("#DATER").val().split(",").length;i++){
			 				disableddates.push(formatChange($("#DATER").val().split(",")[i]));
			 			}
			 		}
			 		function DisableSpecificDates(date) {
			 		    var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
			 		    return [disableddates.indexOf(string) == -1];
			 		}
			 		
			 		$("#Fromdate").datepicker({
						dateFormat : "dd-mm-yy",
						beforeShowDay: DisableSpecificDates,
						minDate : mindate,
						maxDate : maxdate,
						changeMonth : true,
						changeYear : true,
						onClose : function(selectedDate) {
							if(selectedDate!="")
							$("#todate").datepicker("option","minDate",selectedDate);
							if($("#Fromdate").val().trim()!="" && $("#todate").val().trim()!=""){
								days = noOfDays($("#Fromdate").val(),$("#todate").val())
								$("#totalleaves").val(days);
								callSession();
							}
						}
					});	

					$("#todate").datepicker({
						dateFormat : "dd-mm-yy",
						beforeShowDay: DisableSpecificDates,
						minDate : mindate,
						maxDate : maxdate,
						changeMonth : true,
						changeYear : true,
						onClose : function(selectedDate) {
							if(selectedDate!="")
								$("#Fromdate").datepicker("option","maxDate",selectedDate);
							if($("#Fromdate").val().trim()!="" && $("#todate").val().trim()!=""){
								days = noOfDays($("#Fromdate").val(),$("#todate").val())
								$("#totalleaves").val(days);
								callSession();
							}
								
						}
					});	
					
					
					
					$("#startsessionDay,#endsessionDay").change(function(){
						callSession();
					});	
			}
	});
}

function callSession(){
	
		var shalfday = $("#startsessionDay").val();
		var ehalfday = $("#endsessionDay").val();
		var stdate = $("#Fromdate").val();
		var enddate = $("#todate").val();
		
		var days = noOfDays($("#Fromdate").val(),$("#todate").val())
		$("#totalleaves").val(days);
		
		total_leave = $("#totalleaves").val();
					
		if((stdate!=undefined || stdate!="") && (enddate!=undefined || enddate!="")){
			
			if(shalfday == "FH" && ehalfday == "FH" ){
				total = total_leave - 0.5;
			}
			else if(shalfday == "FH" && ehalfday == "SH"){
				total = total_leave;
			}
			else if(shalfday == "SH" && ehalfday == "SH" ){
				total = total_leave - 0.5;
			}
			else if( (shalfday == "SH" && ehalfday == "FH"))
			{
				total = total_leave - 1;
			}
			
			$("#totalleaves").val(total);
			
		}
}

function noOfDays(stdate,enddate) {
	var totalSundays = 0;
	var d1 = new Date(toDate(stdate));
	var d2 = new Date(toDate(enddate));
	 
	  //Get 1 day in milliseconds
	  var one_day=1000*60*60*24;
	 
	  // Convert both dates to milliseconds
	  var date1_ms = d1.getTime();
	  var date2_ms = d2.getTime();
	 
	  // Calculate the difference in milliseconds
	  var difference_ms = date2_ms - date1_ms;
	  if($("#WKIN").val() == "N")
	  totalSundays = getSundayCount(d1,d2)
	  
	  // Convert back to days and return
	  return (Math.round(difference_ms/one_day)+1)-totalSundays; 
}
//get sunday count
function getSundayCount(startDate,endDate){

	var totalSundays = 0;

	for (var i = startDate; i <= endDate; ){
	    if (i.getDay() == 0){
	        totalSundays++;
	    }
	    i.setTime(i.getTime() + 1000*60*60*24);
	}
	return totalSundays;
}

function getSundayDate(startDate,endDate){
	var d1 = new Date(toDate(stdate));
	var d2 = new Date(toDate(enddate));
	
	for (var i = d1; i <= d2; ){
	    if (i.getDay() == 0){
	        sundaydate = i;
	    }
	    i.setTime(i.getTime() + 1000*60*60*24);
	}
	return sundaydate;
}

function toDate(selector) {
	  var from = selector.split("-")
	  return new Date(from[2], from[1] - 1, from[0])
}
function formatChange(selector) {
	  var from = selector.split("-")
	  return (from[2]+"-"+from[1]+"-"+from[0]);
}
function weekDay() {

    var date = new Date,
        days = ['Sunday','Monday','Tuesday','Wednesday',
                'Thursday','Friday','Saturday'],
        prefixes = ['First', 'Second', 'Third', 'Fourth', 'Fifth'];

    return prefixes[0 | date.getDate() / 7] + ' ' + days[date.getDay()];

}

function getWeek(year,month,day) {
    var when = new Date(year,month,day);
    var newYear = new Date(year,0,1);
    var offset = 7 + 1 - newYear.getDay();
    if (offset == 8) offset = 1;
    var daynum = ((Date.UTC(y2k(year),when.getMonth(),when.getDate(),0,0,0) - Date.UTC(y2k(year),0,1,0,0,0)) /1000/60/60/24) + 1;
    var weeknum = Math.floor((daynum-offset+7)/7);
    if (weeknum == 0) {
        year--;
        var prevNewYear = new Date(year,0,1);
        var prevOffset = 7 + 1 - prevNewYear.getDay();
        if (prevOffset == 2 || prevOffset == 8) weeknum = 53; else weeknum = 52;
    }
    return weeknum;
}

function isImage(filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
    case 'jpg':
    case 'doc':
    case 'pdf':
    case 'png':
      
        return true;
    }
    return false;
}

function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}
function HideError(pointer){
	$(pointer).css({
	"background-color" : "#fff",
    "border" : "1px solid #ccc"
	});
}

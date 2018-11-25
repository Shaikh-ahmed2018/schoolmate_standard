$(document).ready(function() {
	
	totalSMSBalance = 0;
	getBalanceSMSCount();
	
	
	 var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth()+1;
	    var yyyy = today.getFullYear();
	    if(dd<10){
	        dd='0'+dd;
	    } 
	    if(mm<10){
	        mm='0'+mm;
	    } 
	    var today = dd+'-'+mm+'-'+yyyy;
	
	$("#date").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		minDate : -30,
		changeMonth : "true",
		changeYear : "true",
	
	}).datepicker('setDate', today);
	
	var today_date=$('#date').val();
	
	
	    $("#date").change(function(){
			today_date=$('#date').val();
			 var subjectid = $("#subjectid option:selected").text();
			$('#smstext').val("Dear Parent, Please check your Child's Diary. There is Home Work to be Completed in ("+subjectid+") by ("+today_date+").");           
		});
		
		
	     $("#subjectid").change(function(){
	    	 var subjectid = $("#subjectid option:selected").text();
	    	 today_date=$('#date').val();
	    	 $('#smstext').val("Dear Parent, Please check your Child's Diary. There is Home Work to be Completed in ("+subjectid+") by ("+today_date+").");           
		});
	     
		$('#smstext').val("Dear Parents, Please Check your Child's Diary. There is Home Work to be Completed");
	
		getClassList();
	 $("#locId").change(function(){
		getClassList();
	});	
		
	$("#classid").change(function(){
		$('#classid').show();
	    $('#sectionid').show();
	    getSectionList();
	    getSubject();
	    getStudent();
	});
	
	$("#sectionid").change(function(){
		   $('#classid').show();
		   $('#sectionid').show();
		    getStudent();
	});
	
	$("#back1").click(function(){
	   window.location.href="menuslist.html?method=homeworklist";
	}); 
	
	$("#save").click(function(){
		
		$(".successmessagediv").hide();
      	$(".errormessagediv").hide();
		var dateId = $("#date").val();
		var classid = $("#classid").val();
		var sectionid = $("#sectionid").val();
		var subjectid = $("#subjectid").val();
		var description = $("#smstext").val();
		var locId=$("#locId").val();
		var balanceSMS = totalSMSBalance;
		
		var studentlist = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentlist[i] = $(this).val();
		});
		
		if(dateId==""||dateId==null){			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Date");
			document.getElementById("date").style.border = "1px solid #AF2C2C";
			document.getElementById("date").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
       else if(locId==""||locId==null){			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Branch");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(classid==""||classid=="all"){		
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Class");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(sectionid==""||sectionid=="all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Division");
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
	
		}
		else if(subjectid==""||subjectid==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Subject");
			document.getElementById("subjectid").style.border = "1px solid #AF2C2C";
			document.getElementById("subjectid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(description==""||description==null){			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Description");
			document.getElementById("smstext").style.border = "1px solid #AF2C2C";
			document.getElementById("smstext").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if($("#studentid").val() == "" || $("#studentid").val() == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Students From List");
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else
			{
		
		datalist={
				"dateId" : dateId ,
				"classid" : classid,
				"sectionid" : sectionid,
				"subjectid" : subjectid,
				"description" : description,
				"studentlist" : studentlist.toString(),
				"locId":locId,
				"balanceSMS":balanceSMS,
				
		},
		
		$.ajax({
			type : 'POST',
			url : "smsPath.html?method=insertsms",
			data : datalist,
			beforeSend: function() {
				$('#loader').show();
			},
			
			success : function(response) {
				
				var result = $.parseJSON(response);
				
				if(result.jsonResponse=="success"){
					
					$(".errormessagediv").hide();
					$(".successmessagediv").show();
					 $(".validateTips").text("Home Work Message Sent Successfully");
					 setTimeout(function(){
						   window.location.href="menuslist.html?method=homeworklist";
					 },3000);
				}
				else if(result.jsonResponse=="insufficientSMSBalance"){
					
					$(".errormessagediv").show();
					$(".successmessagediv").hide();
					 $(".validateTips").text("Insufficient SMS Balance To Send This SMS");
					 setTimeout(function(){
					    window.location.href="menuslist.html?method=homeworklist";
					 },3000);
				
				}
				else{
					
					$(".errormessagediv").show();
					$(".successmessagediv").hide();
					 $(".validateTips").text("Home Work Message Sending Failed");
					 setTimeout(function(){
					    window.location.href="menuslist.html?method=homeworklist";
					 },3000);
				}
			}
		});
		
			}
	});

});


function getClassList(){
	
	var locationid=$("#locId").val();
	datalist={
			"locationid" : locationid
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classid').empty();
			$('#classid').append('<option value="all">' +"----------Select----------"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}

function getSectionList(){
	datalist={
			"classidVal" : $("#classid").val(),
			"locationId":$("#locId").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			$('#sectionid').append('<option value="">' +"----------Select----------"+ '</option>');
			var j=0;
			var len=result.sectionList.length;
			for ( j = 0; j < len; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}


function getStudent() {
	
	var locId = $("#locId").val();
	var classid = $("#classid").val();

	var sectionlist = []; 
	$('#sectionid :selected').each(function(i, selected){ 
		sectionlist[i] = $(this).val();
	});
		datalist={
				"locId":locId,
				"classid":classid,
				"sectionlist":sectionlist.toString(),
		   },
		
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEventBySections",
		data : datalist,
		success : function(response) {
	        var result = $.parseJSON(response);
				$('#studentid').html("");
				var j=0;
				var len=result.studentlist.length;
					for(var j = 0; j < len; j++){
						$('#studentid').append(
								'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
					}
		       }
	     });
}

function getSubject() {
	
	var classid = $("#classid").val();
	var sectionid = $("#sectionid").val();
	var locationId = $("#locId").val();
	
	datalist={
			"classid" : classid ,
			"sectionid" : sectionid,
			"locationId" : locationId,
	},
	
	$.ajax({
		
		type : 'POST',
		url : "smsPath.html?method=getSubject",
		data : datalist,
		success : function(response) {
			
           var result = $.parseJSON(response);
           $('#subjectid').html("");
			$('#subjectid').append('<option value="">' +"----------Select----------"+ '</option>');
           				
				for(var j = 0; j < result.sublist.length; j++){
					
					$('#subjectid').append('<option value="'

							+ result.sublist[j].subjectid + '">'

							+ result.sublist[j].subjectname

							+ '</option>');
				}
		}
	});
	
}

function getBalanceSMSCount(){
	var locationid=$("#locId").val();
	
	datalist={
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getBalanceSMSCount",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			totalSMSBalance = result.smsCount;
			
			return totalSMSBalance;
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

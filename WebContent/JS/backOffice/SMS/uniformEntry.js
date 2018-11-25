
$(document).ready(function(){
	
	$('#validateTable').hide();
	
	$('#save').click(function(){
		var date=$('#date').val();
		var classid=$('#classid').val();
		var section=$('#sectionid').val();
		var student=$('#studentid').val();
		var locId=$('#locId').val(); 
		var smstext=$('#smstext').val();
		
		var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(this).val(); 
		});
		
		if(date==""||date==null){
			$('.errormessagediv').show();
			$('.validateTips').text("please select Date");
			
			document.getElementById("date").style.border = "1px solid #AF2C2C";
			document.getElementById("date").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(locId==""||locId==null){
			$('.errormessagediv').show();
			$('.validateTips').text("please select Location");
			
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(classid==""||classid==null){
			$('.errormessagediv').show();
			$('.validateTips').text("please select ClassName");
			
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(section==""||section==null){
			$('#errormessagediv').show();
			$('.validateTips').text("please select SectionName");
			
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(student==""||student==null){
			$('#errormessagediv').show();
			$('.validateTips').text("please select StudentName");
			
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else{
			
			datalist={
					"date" : date ,
					"classid" : classid,
					"section" : section,
					"smstext" : smstext,
					"studentlist" : studentList.toString(),
					"locId":locId,
			},
			
			$.ajax({
				type : 'POST',
				url : "smsPath.html?method=SendUniform",
				data : datalist,
				async : false,
				beforeSend: function()
				{
					$('#loader').show();
				},
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.status=="success"){
						
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						 $(".validateTips").text("Uniform Issue Sent Successfully");
						 $(".successmessagediv").fadeOut(3000);
						 
						 setTimeout(function(){
								window.location.href ="menuslist.html?method=uniformlist";
						 },2000);
					}
					else{
						
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						 $(".validateTips").text("Uniform Issue Sending Failed");
						 $(".errormessagediv").fadeOut(3000);
						 
						 setTimeout(function(){
								
								window.location.href ="menuslist.html?method=uniformlist";
						 
						 },2000);
					}
				}
			});
			
				}
		});
	
	
	$("#locId").change(function(){
		getClassList();
	});	
	
	$("#back").click(function(){
		window.location.href ="menuslist.html?method=uniformlist";
	});	
	
	
	$("#classid").change(function(){
		getSectionList();
		getStudent();
	});
	
	$("#sectionid").change(function(){
		getStudentBySections();
	});	
	
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
		
			$('#smstext').val("Your child did not wear uniform to school on " +today_date+". Please mahe sure this does not happen in the future");
		
	});

$('#smstext').val("Your child did not wear uniform to school today.Please ensure that this does not happen in the future.");
	
	
	
	
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
			$('#classid').html("");
			$('#classid').append('<option value="all">' +"------Select------"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'+result.ClassList[j].classcode+'">'+result.ClassList[j].classname+'</option>');
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

function getStudentBySections() {
	
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

function getStudent() {
	
	var locId = $("#locId").val();
	var classid = $("#classid").val();

		datalist={
				"locId":locId,
				"classid":classid,
		   },
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEvent",
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



function validations(){
	var category=$("#category").val();
	var classid=$('#classname').val();
	var section=$('#section').val();
	var date=$('#date').val();
	var smstext=$('#smstext').val();
	var student=$('#student').val();
	
	if(category==""){
		
		$('#validateTable').show();
		$('.validateTips').text("please select category");
		
		return false;
		
	}
	
	
	if(category=="all"){
		
		 if(date==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select date");
				
				return false;
				
			}else if(smstext==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please enter text for sms");
				
				return false;
				
			}else{
				
			if(validateMeetingSms()){
				
				var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
				
				if(x){
					
					return true;
					
				}else{
					
					$('#validateTable').show();
					$('.validateTips').text("Uniform message already exist");
					return false;
				}
				
			}else{
				
				return true;
			}
		}
		
	}else{
		
		 if(classid==null || classid==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select class");
				
				return false;
				
			}
		 
		 if(classid.length==classlength){
				
			  if(date==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please select date");
					
					return false;
					
				}else if(smstext==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please enter text for sms");
					
					return false;
					
				}else{
					
				if(validateMeetingSms()){
					
					var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
					
					if(x){
						
						return true;
						
					}else{
						
						$('#validateTable').show();
						$('.validateTips').text("Uniform message already exist");
						
						return false;
						
						
					}
					
				}else{
					
					return true;
				}

			}
				
			}else{
		 
		  if(section==null || section==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select section");
				
				return false;
				
				
			}
		  
		  if(section.length==sectionlength){
			  
			  if(date==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please select date");
					
					return false;
					
				}else if(smstext==""){
					
					$('#validateTable').show();
					$('.validateTips').text("please enter text for sms");
					
					return false;
					
				}else{
					
					
					
				if(validateMeetingSms()){
					
					var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
					
					if(x){
						
						return true;
						
					}else{
						
						$('#validateTable').show();
						$('.validateTips').text("Uniform message already exist");
						
						return false;
						
						
					}
					
				}else{
					
					return true;
				}

			}
			  
		  }else{
			  
			  if(student==null || student==""){
				  
				  $('#validateTable').show();
					$('.validateTips').text("please select student");
					
					return false;
			  }else
		   if(date==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please select date");
				
				return false;
				
			}else if(smstext==""){
				
				$('#validateTable').show();
				$('.validateTips').text("please enter text for sms");
				
				return false;
				
			}else{
				
				
				
			if(validateMeetingSms()){
				
				var x = confirm("Uniform message already exist with same content and same date do you want to send for principal approvel");
				
				if(x){
				
					return true;
					
				}else{
					
					$('#validateTable').show();
					$('.validateTips').text("Uniform message already exist");
					
					return false;
				}
				
			}else{
				
				return true;
			}
		   }
		 }
	   }
	}
}




function  validateMeetingSms(){
	
	var meetingstatus=false;
	var date=$('#date').val();
	var smstext=$('#smstext').val();
	
	
	var validatedetails = {
			"date" : date,
			"smstext" : smstext
			
		};
	
		$.ajax({
					type : 'POST',
					url : "uniformsms.html?method=validateUniformSms",
					data : validatedetails,
					async:false,
					
					success : function(response) {
						var result = $
						.parseJSON(response);
						
						meetingstatus=result.status;
						
						
					}
				});
		
		return meetingstatus;
		
}

function selectAllCLasses(){
	
	 if($('#classcheckbox').prop("checked") == true){
         $('#classname option').prop('selected', true);
         $('#sectioncheckbox').hide();
         $('#studentcheckbox').hide();
         $("#section").html("");
         $("#student").html("");
         
         var classidVal=$("#classname").val();
         
         document.getElementById("section").disabled = true;
 		document.getElementById("student").disabled = true;
 		document.getElementById("section").style.background = "#B8B894";
 		document.getElementById("student").style.background = "#B8B894";
         
     }else{
    	    $('#sectioncheckbox').show();
            $('#studentcheckbox').show();
    	 
    	 $('#classname option').prop('selected', false);
    	 document.getElementById("section").disabled = false;
  		document.getElementById("student").disabled = false;
  		document.getElementById("section").style.background = "#FFFFFF";
  		document.getElementById("student").style.background = "#FFFFFF";
    	 $("#section").html("");
     }
}


function selectAllSections(){
	
	
	 if($('#sectioncheckbox').prop("checked") == true){
		 
		 $('#studentcheckbox').hide();
         $('#section option').prop('selected', true);
         
    	 $("#student").html("");
         
 		document.getElementById("student").disabled = true;
 		document.getElementById("student").style.background = "#B8B894";
         
         
     }else{
    	 
    	 
    	 $('#section option').prop('selected', false);
    	 document.getElementById("student").disabled = false;
  		document.getElementById("student").style.background = "#FFFFFF";
    	 $("#student").html("");
    	 
     }
}

function selectAllStudents(){
	 if($('#studentcheckbox').prop("checked") == true){
        $('#student option').prop('selected', true);
       
    }else{
   	 
   	 $('#student option').prop('selected', false);
   	 
   	 document.getElementById("student").disabled = false;
 		document.getElementById("student").style.background = "#FFFFFF";
   	 
    }
}






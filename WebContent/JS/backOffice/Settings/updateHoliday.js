var saveFlag=true;
var holidayflag= false;
$(document).ready(function(){
	

	   $("#holidayname").change(function(){
		   holidayValidate();
	   }); 
	   
	   $("#back1").click(function(){
		   window.location.href="menuslist.html?method=holidaymaster&historylocId="+$("#historylocId").val()+"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();
	   });
	   
		$("#locationname").change(function(){
			validateDate();
			holidayValidate();
		});
	   $("#accyYear").change(function(){
		  
		   $("#date").val("");
		   $("#WeekDays").val("");
		   getAcademicYearStartDate();
		});
	$("select[name='holidaytype']").val($("#hiddenholidayType").val());
	$("select[name='accyYear']").val($("#hiddenyear").val());
	$("select[name='location']").val($("#hiddenlocation").val());
	
	var hiddendate =$("#hiddenDate").val();
	
	$("#saveid").click(function(){
		
		var location= $("#locationname").val();
		var date = $("#date").val();
		var holidayname = $("#holidayname").val().trim();
		var year = $("#accyYear").val().trim();
		var weekdays = $("#WeekDays").val().trim();
		var holidaytype = $("#holidaytype").val().trim();
		var holiid =$("#hiddenholidayid").val().trim();
		
		if(holidayname == "" || holidayname == undefined){
			
			$(".errormessagediv").show();
				$(".validateTips").text("Field required - Holiday Name");
				$("#holidayname").css({'border-color':'#B70606'});
				return false;
		}
		 /*else if(!holidayname.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)){
			 $(".errormessagediv").show();
				$(".validateTips").text("Holiday Name should be characters");
				$("#holidayname").css({'border-color':'#B70606'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }*/ 
		
		else if($("#date").val().trim() == "" || $("#date").val() == undefined){
		 $(".errormessagediv").show();
			$(".validateTips").text("Field required - Holiday Date");
			$("#date").css({'border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(validateDate()){
			return false;
		}
		else if(holidayValidate()){
			return false;
		}
		
		else{
		
		datalist ={
				
				"location":location,
				"date" : date,
				"holidayname" : holidayname,
				"year" :year,
				"weekdays" : weekdays,
				"holidaytype" :holidaytype,
				"holiid" :holiid
		};
		
		$.ajax({
	
			type : "POST",
			
			url : "holidayMaster.html?method=addHolidayDetail",
			
			data : datalist,
			
			success: function(response) {
	 			 
	 			var result = $.parseJSON(response);
	 			
	 			$('.errormessagediv').hide();
				$(".successmessagediv").show();
				
				
				$(".validateTips").text("Update Record progressing...");	
				setTimeout(function() {
	 			  window.location.href="menuslist.html?method=holidaymaster&historylocId="+$("#historylocId").val()+"&historyacyearId="+$("#historyacyearId").val()+"&historystatus="+$("#historystatus").val();
				},2000);
	 		   }
	
	
	
		});
		
		
		}
		
	});
var weekday=new Array(7);
	
	weekday[0]="Monday";
	weekday[1]="Tuesday";
	weekday[2]="Wednesday";
	weekday[3]="Thursday";
	weekday[4]="Friday";
	weekday[5]="Saturday";
	weekday[6]="Sunday";
	
	  
	 $('.date').datepicker({
	    	dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			changeMonth : "true",
			changeYear : "true",
			numberOfMonths : 1,
			minDate : new Date($("#startdate").val()),
			maxDate : new Date($("#enddate").val()),
			onSelect: function(dateText, inst) { 
				validateDate();
		        var date = $(this).datepicker('getDate');
		            day  = date.getUTCDay();
		            $(this).parents('.panel-body').find('input[name=weekDays]').val(weekday[day]);
		         
		    }
		});
	 
});


function validateDate(){

	var hiddendate =$("#hiddenDate").val().trim();
	var location= $("#locationname").val().trim();
	var date = $("#date").val();
	var accYear=$("#accyYear").val();
	
	datalist ={
		"location":location,
		"date":date,
		"accYear":accYear
	};
	if(hiddendate !=date || $("#hiddenlocation").val() != location || $("#hiddenyear").val() != accYear){
 $.ajax({
	 
	 type : "POST",
	 
	 url: "holidayMaster.html?method=dateValidatation",
	 
	 data : datalist,
	 
	 success: function(data) {
	 

		 var result=$.parseJSON(data);	
			if(result.dateVal=='exit')
			{
				
				 $(".errormessagediv").show();
				 errorMsg="Date Already Exists, Select Another Date";
				$(".validateTips").text("Date already exists, Select another date");
				$(".date").css({'border-color':'#B70606'});
				$(".date").val("");
				$("#WeekDays").val("");
				  setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
				 
				  saveFlag=false;
				 return false;
							}
			
			else{
				
				saveFlag=true;
				return true;
			}
	 }
 });
	}	
	else{
		saveFlag=true;
		$(".date").css({'border-color':'#B70606'});
		return true;
		
	}
	
}
function holidayValidate(){

	
	datalist = {
		
			"holiday":$("#holidayname").val().trim(),
			"location":$("#locationname").val().trim(),
			"academicYear":$("#accyYear").val().trim()
	};
	if($("#hiddeholidayName").val().trim().toLowerCase() != $("#holidayname").val().trim().toLowerCase() || $("#hiddenlocation").val() != location || $("#hiddenyear").val() != accYear){
	$.ajax({
		
		type : "POST",
		
		url :"holidayMaster.html?method=HolidayNameValidate",
		
		data : datalist,
			
		success: function(data) {
		 var result=$.parseJSON(data);	
		
			if(result.dateVal=='exit')
			{
			
				 $(".errormessagediv").show();
				 errorMsg="Holiday Name Already Exist";
				$(".validateTips").text("Holiday Name Already Exist");
				$("#holidayname").val("");
				$("#holidayname").css({'border-color':'#B70606'});
				  setTimeout(function() {$('#errorhover').fadeOut();}, 3000);
				  
				  saveFlag=false;
					return false;
			}
			else{
				saveFlag=true;
				return true;
			}
	 			
	 		  }
	}); 
	}
	else{
		saveFlag=true;
		$("#holidayname").css({'border-color':'#ddd'});
		return true;
	}
}

function getAcademicYearStartDate(){

	var accyear=$('#accyYear').val();
	$.ajax({
		type:"POST", 
		url:"menuslist.html?method=getAcademicYearStartDate",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
		var  result=$.parseJSON(response);
		
		$("#startdate").val(result.startdate);
		$("#enddate").val(result.enddate);
		 $(".date").datepicker("destroy");
		 
		 var weekday=new Array(7);
			
			weekday[0]="Monday";
			weekday[1]="Tuesday";
			weekday[2]="Wednesday";
			weekday[3]="Thursday";
			weekday[4]="Friday";
			weekday[5]="Saturday";
			weekday[6]="Sunday";
		 
		 $('.date').datepicker({
		    	dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				changeMonth : "true",
				changeYear : "true",
				numberOfMonths : 1,
				minDate : new Date($("#startdate").val()),
				maxDate : new Date($("#enddate").val()),
				onSelect: function(dateText, inst) { 
					validateDate();
			        var date = $(this).datepicker('getDate');
			            day  = date.getUTCDay();
			            $(this).parents('.panel-body').find('input[name=weekDays]').val(weekday[day]);
			         
			    }
			});
		 
		}
	});
}

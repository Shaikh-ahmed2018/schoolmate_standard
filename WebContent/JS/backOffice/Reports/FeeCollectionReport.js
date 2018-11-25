$(document).ready(function(){	
	$("#accyear").val($("#hacademicyaer").val());
	
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	
	getClassList();
 	getTerm();
 	
 	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());
	
	$("#start").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
		    $("#end").datepicker('option', 'minDate', min);
		    //getFeeCollectionPaymodeReport();
		}
	
	});
	
	$("#end").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
		}

	});
 	
	$('.reset').click(function(){
		$("#accyear").val($("#hacademicyaer").val());
		$("#location").val($("#hschoolLocation").val());
		$("#class").val("all");
		$("#PaymentType").val("ALL");
		$("#termName").val("all");
		$("#start").val("")
		$("#end").val("");
		$("#allstudent").empty();
		$("#pagebars").hide();
		$(".paymenttypeonline").hide();
		$(".paymenttype").hide();
		
	});
	
	$("#searchs").click(function(){
		
		if($("#PaymentType").val()== 'ONLINE')
		{
			getonlinelist();
		}else{
			getFeeCollectionPaymodeReport();
		}
	});
	
	$("#location").change(function(){
		
    	if($('#location').val()==""){
    		
    		$('#class').html("");
			$('#class').append('<option value="">----------Select----------</option>');
			
			$('#section').html("");
			$('#section').append('<option value="">----------Select----------</option>');
			
			$('#termName').html("");
			$('#termName').append('<option value="">----------Select----------</option>');
			
			$("#studentlisttable").html("");
			$("#pagebars").html("");
			
		}else{
			getClassList();
	      	getTerm();
	      	$('#section').html("");
			$('#section').append('<option value="all">----------Select----------</option>');
			
		}
	});
	
	$("#accyear").change(function(){
		
		getTerm();
		getAcademicYearStartDate();
		academicyearchange();
	});
	
	$("#PaymentType").change(function(){
		
		if($("#PaymentType").val() == 'OFFLINE'){
			
			$("#paymode").val("all")
			
			$(".paymenttypeonline").hide();
			$(".paymenttype").show();
			
		}
		else if($("#PaymentType").val()== 'ONLINE')
		{
			$(".paymode").val("all")
			$(".paymenttypeonline").show();
			$(".paymenttype").hide();
		}
		else{
			$(".paymenttypeonline").hide();
			$(".paymenttype").hide();	
		}
		
	});

	$("#class").change(function(){
		
		if($(this).val().trim() == "all"){
			$('#section').html("");
			$('#section').append('<option value="">-----------Select----------</option>');
		}
		else{
			getSection();
		}
		
	});
	
	if($("#studentlisttable #allstudent tbody tr").length==0){
		
		$("#studentlisttable #allstudent  tbody").append("<tr><td colspan='9'>No Record Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No of Records "+0+".");
		pagination(100);
	}
		
	$("#download").click(function(){
		
		if($("#allstudent tbody tr").length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}else if($("#allstudent tbody tr:first td:first").text() == "No Records Found"){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
		
	});
	
	$("#pdfDownload").click(function(){
			window.location.href = 'reportaction.html?method=FeeCollectionPdfReport&AccId='+$("#accyear").val()
			+'&ClassId='+$("#class").val()+'&SectionId='+$("#section").val()
			+'&Paymode='+$("#paymode").val()+'&PaymentType='+$("#PaymentType").val()
			+'&termId='+$("#termName").val()
			+'&locationname='+$("#location option:selected").text()+"&locid="+$("#location").val()+
			"&Classname="+$("#class option:selected").text()+
			"&Sectionname="+$("#section option:selected").text()+
			"&paymode="+$("#paymode option:selected").text()+"&PaymentType="+$("#PaymentType option:selected").text()
			+"&accyear="+$("#accyear option:selected").text()+"&termName="+$("#termName option:selected").text()+
			"&startdate="+$("#start").val()+"&endate="+$("#end").val();
	});
	
       $("#excelDownload").click(function(){
    
   			window.location.href = 'reportaction.html?method=FeeCollectionExcelReport&AccId='+$("#accyear").val()
   	   		+'&ClassId='+$("#class").val()+'&SectionId='+$("#section").val()
   	   		+'&Paymode='+$("#paymode").val()+'&PaymentType='+$("#PaymentType").val()
   	   		+'&termId='+$("#termName").val()
   	   		+'&locationname='+$("#location option:selected").text()+"&locid="+$("#location").val()+
   	   		"&Classname="+$("#class option:selected").text()+
   	   		"&Sectionname="+$("#section option:selected").text()+
   	   		"&paymode="+$("#paymode option:selected").text()+"&PaymentType="+$("#PaymentType option:selected").text()
   	   		+"&accyear="+$("#accyear option:selected").text()+"&termName="+$("#termName option:selected").text()+
   	   	    "&startdate="+$("#start").val()+"&endate="+$("#end").val();
       });
	
      
       $("#print").click(function(){
   		$.ajax({
   			type:"POST",
   			url:'reportaction.html?method=FeeCollectionPrintDetails&AccId='+$("#accyear").val()
   	   		+'&ClassId='+$("#class").val()+'&SectionId='+$("#section").val()
   	   		+'&Paymode='+$("#paymode").val()+'&PaymentType='+$("#PaymentType").val()
   	   		+'&termId='+$("#termName").val()
   	   		+'&locationname='+$("#location option:selected").text()+"&locid="+$("#location").val()+
   	   		"&Classname="+$("#class option:selected").text()+
   	   		"&Sectionname="+$("#section option:selected").text()+
   	   		"&paymode="+$("#paymode option:selected").text()+"&PaymentType="+$("#PaymentType option:selected").text()
   	   		+"&accyear="+$("#accyear option:selected").text()+"&termName="+$("#termName option:selected").text(),
   			success:function(data){
   				
   			}
   		});
   	});
       

});

function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="all">----------Select----------</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}


function getSection(){
	datalist={
			"classidVal" : $("#class").val(),
			"locationId":$("#location").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#section').html("");
			
			$('#section').append('<option value="all">----------Select----------</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#section').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}
function getFeeCollectionReport(){
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			termId:$("#termName").val(),
			
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionReport",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+
					"<tr><th style='width:10px;'>Sl.No.</th>"+
					"<th style='width:85px;'>Bill Date</th>" +
					"<th style='width:50px;'>Bill No</th>"+
					"<th style='width:80px;'>Class</th>"+
					"<th style='width:70px;'>Term</th>"+
					"<th style='width:100px;'>Name</th>"+
					"<th style='width:100px;'>Permanent Address</th>"+
					"<th style='width:150px;'>Nature Of Payment</th>"+
					"<th style='width:100px;'>Amount</th>"+
					"</tr></thead><tbody></tbody><tfoot><tr><td ColSpan='8'>Total</td><td></td></tr><tfoot>" +
					"</table>"
			);
			
			if(result.FeeReport.length>0){
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td  style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
			}
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);
			   pagination(100);
		}
	 
	});
	
}

function getfeecollectionclasslist(){
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			termId:$("#termName").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getfeecollectionclasslist",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+
					"<tr><th style='width:10px;'>Sl.No.</th>"+
					"<th style='width:85px;'>Bill Date</th>" +
					"<th style='width:50px;'>Bill No</th>"+
					"<th style='width:80px;'>Class</th>"+
					"<th style='width:70px;'>Term</th>"+
					"<th style='width:100px;'>Name</th>"+
					"<th style='width:100px;'>Permanent Address</th>"+
					"<th style='width:150px;'>Nature Of Payment</th>"+
					"<th style='width:100px;'>Amount</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
			);
			
			if(result.FeeReport.length>0){
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("##studentlisttable #allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				
			}
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);
			   pagination(100);
		}
	 
	});
	
}

function getFeeCollectionSectionReport(){
	
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			termId:$("#termName").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionSectionReport",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+
					"<tr><th style='width:10px;'>Sl.No.</th>"+
					"<th style='width:85px;'>Bill Date</th>" +
					"<th style='width:50px;'>Bill No</th>"+
					"<th style='width:80px;'>Class</th>"+
					"<th style='width:70px;'>Term</th>"+
					"<th style='width:100px;'>Name</th>"+
					"<th style='width:100px;'>Permanent Address</th>"+
					"<th style='width:150px;'>Nature Of Payment</th>"+
					"<th style='width:100px;'>Amount</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
			);
			
			if(result.FeeReport.length>0){
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:252px;'>"+result.FeeReport[i].permanentaddress+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				
			}
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append("No. of Records  "+result.FeeReport.length);
			   pagination(100);
		}
	 
	});
	
}



function getFeeCollectionPaymodeReport(){
	
	if($(".paymode:selected").val() == undefined){
		paymodid = "all";
	}else{
		paymodid = $(".paymode:selected").val();
	}
	
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			paymodid:$(".paymode").val(),
			paymenttype:$("#PaymentType").val(),
			termId:$("#termName").val(),
			startdate : $("#start").val(),
			endate : $("#end").val()
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getFeeCollectionPaymodeReport",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+
					"<tr><th style='width:10px;'>Sl.No.</th>"+
					"<th style='width:85px;'>Bill Date</th>" +
					"<th style='width:50px;'>Bill No</th>"+
					"<th style='width:80px;'>Class</th>"+
					"<th style='width:70px;'>Term</th>"+
					"<th style='width:100px;'>Name</th>"+
					"<th style='width:150px;'>Nature Of Payment</th>"+
					"<th style='width:100px;'>Amount</th>"+
					"</tr></thead><tbody></tbody><tfoot><tr><td ColSpan='7' style='font-weight:600;'>Total</td><td class='grandtot' style='font-weight:600;'>0.0</td></tr></tfoot>" +
					"</table>"
			);
			
			$("#pagebars").empty();
			getpagebaner();
			
			
			if(result.FeeReport.length>0){
				$('#studentlisttable').append('<option value="ALL">' + ""+'</option>');
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td  style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td class='total' style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						+"</tr>"
				);
			}
				var grandtot = 0;
				$("#allstudent tbody tr").each(function(){
					
					grandtot = grandtot + parseInt($(this).find('.total').text());
					
				});
				$("#allstudent tfoot tr").find('.grandtot').text(grandtot);
				
				$("#show_per_page").change(function(){
					   var grandtot = 0;
					$("#allstudent tbody tr").each(function(){
						grandtot = grandtot + parseFloat($(this).find('.total').text());
					});
					$("#allstudent tfoot tr").find('.grandtot').text(grandtot);
				});
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				
			}
			
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append(" No.of Records "+result.FeeReport.length);
			   pagination(100);
			   
		}
	 
	});
	
}

function getonlinelist(){
	
	datalist = {
			 
			location:$("#location").val(),
			accyear:$("#accyear").val(),
			classid:$("#class").val(),
			sectionid:$("#section").val(),
			paymenttype:$("#paymode").val(),
			paymodid:$("#PaymentType").val(),
			startdate : $("#start").val(),
			endate : $("#end").val(),
			termId : $("#termName").val()
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getonlinelist",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+
					"<tr><th style='width:10px;'>Sl.No.</th>"+
					"<th style='width:85px;'>Bill Date</th>" +
					"<th style='width:50px;'>Bill No</th>"+
					"<th style='width:80px;'>Class</th>"+
					"<th style='width:70px;'>Term</th>"+
					"<th style='width:100px;'>Name</th>"+
					"<th style='width:150px;'>Nature Of Payment</th>"+
					"<th style='width:100px;'>Amount</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>");
			$("#pagebars").empty();
			getpagebaner();
			
			
			if(result.FeeReport.length>0){
				
			for(var i=0;i<result.FeeReport.length;i++){

				$("#studentlisttable #allstudent tbody").append(
						"<tr>"+
						"<td style='width:5px;'>"+result.FeeReport[i].sno+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].billdate+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].chlnno+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].classname+"</td>"+
						"<td  style='width:100px;'>"+result.FeeReport[i].termName+"</td>"+
						"<td style='width:100px;'>"+result.FeeReport[i].studentname+"</td>"+
						"<td style='width:10px;'>"+result.FeeReport[i].paymentMode+"</td>"+
						"<td style='width:50px;'>"+result.FeeReport[i].amount_paid_so_far+"</td>"+
						+"</tr>"

				);

			}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				
			}
			  
			   $(".numberOfItem").empty();
			   $(".numberOfItem").append(" No. of Records  "+result.FeeReport.length);
			   pagination(100);
		}
	 
	});

}
function getTerm(){
	//alert($("#location").val())
	datalist={
			"locId" :$("#location").val(),
			"accId" :$("#accyear").val(), 
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			var j;
			var len= result.data.length;
			$('#termName').html("");
			$('#termName').append('<option value="all">----------Select----------</option>');
			for ( j = 0; j <len; j++) {
				$('#termName').append('<option value="'
						+ result.data[j].termname+ '">'
						+ result.data[j].termId
						+ '</option>');
			}
		}
	});
}
function getAcademicYearStartDate(){
	
	var accyear=$('#accyear').val();
	$.ajax({
		type:"POST", 
		url:"menuslist.html?method=getAcademicYearStartDate",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
		var  result=$.parseJSON(response);
		$("#hiddenstartdate").val(result.startdate);
		$("#hiddenenddate").val(result.enddate);
		
		}
		
	});

}


function academicyearchange(){
	
	$('#start').datepicker('destroy');
	$('#end').datepicker('destroy');
	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());
	
	$("#start").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
		    $("#end").datepicker('option', 'minDate', min);
		   
		}
	
	});
	
	$("#end").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
	        $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
	       
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
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

function getpagebaner(){
	$("#pagebars").append("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option>"+
			"<option value='200'>200</option><option value='300'>300</option>"+
			"<option value='400'>400</option>"+
			"<option value='500'>500</option></select>"+
			"<span  class='numberOfItem'></span></div>"+
			"<div class='pagination pagelinks'></div>");
}




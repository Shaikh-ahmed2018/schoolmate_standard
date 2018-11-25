$(document).ready(function() {
	
	
	/*$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	

	
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		defaultDate : "+1w",
		changeMonth : true,
		changeYear : true
		
	});	*/
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "")&& (selectedDate != undefined)) {
				var date2 = $('#Fromdate').datepicker('getDate');
				date2.setDate(date2.getDate());
				$("#todate").datepicker("option","minDate", date2);
			}
		}
	});
	
	$("#todate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onClose : function(selectedDate) {
			if ((selectedDate != "") && (selectedDate != undefined)) {
				
				var date2 = $('#todate').datepicker('getDate');
				date2.setDate(date2.getDate() - 1);
				$("#Fromdate").datepicker("option", "maxDate", date2);
			}
		}
	});
	
	$("#dropdown").click(function(){
		$("#hbox").slideToggle("slow");
		
	});

	$('.col-md-10, .vertical').click(function(){
		$("#hbox").hide();
	});
	
	$("#locationid").change(function(){
		 $("#hiddenlocation").val($("#locationid").val());
	});
	
	
	
	$("#search").click(function(e){
		e.preventDefault();
	     var fromdate=$("#Fromdate").val();
		 var todate=$("#todate").val();
		 if(fromdate=="" && todate=="" ){
			$("#txtstyle, #txtstyle").slideToggle();
		 }
	
		 if(fromdate==""){
			 $('.errormessagediv').show();
			 $('.validateTips').text("Select From Date");
			 return false;
		
		 }
		 else if(todate==""){
			 $('.errormessagediv').show();
			 $('.validateTips').text("Select To Date");
			 return false;
		 }
    
		 else{
			 var fromdate=$("#Fromdate").val();
			 
			 var todate=$("#todate").val();
			 var schoolname=$("#locationid").val();
			
			 window.location.href="reportaction.html?method=getExpenditureDetailsReport&fromdate="
					+fromdate
					+"&todate="
					+todate+"&schoolname="+schoolname;
		
			 	//return true;
	
			 	//document.getElementById("stuattnid").submit();
		 }
    
});
	
	
	
	$("#excelDownload").click(function(){
		
		window.location.href = 'reportaction.html?method=ExpenditureXLS';
		
			
		
	});

	
	
$("#pdfDownload").click(function(){
		
		window.location.href = 'reportaction.html?method=ExpenditurePDF';
		
	});


/*$("#print").click(function(){
	
	window.location.href = 'reportaction.html?method=ExpenditurePDF';
	
});*/
	

});










function validate(){
	
	
}	

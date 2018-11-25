
$(document).ready(function(){

	$(".checkde .A").val("0");//first time
	

	$("#back1").click(function(){
		window.location.href = "feeCollectionNew.html?method=feeCollectionList&historylocId="+$("#historylocId").val()
		+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionId="+$("#historysectionId").val()+"&historysearch="+$("#historysearch").val();
	});
	

	$(".ispayingExtra").click(function(){
		if($(".ispayingExtra").prop('checked') == true){
			$(".isExtraAmt").show();
				$(".isExtraAmt").val("0");
		}else{
			$(".isExtraAmt").hide();
		}
	});
	
	$("#dd_cheque_date").datepicker({
		minDate:-180,
		maxDate:0,
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
	});
	$("#paymentMode").change(function(){
	if($(this).val()=="Cheque"){
		$("#dd_cheque_date").datepicker("destroy");
		$("#dd_cheque_date").datepicker({
			minDate:-180,
			
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
		});
	}
	else{

		$("#dd_cheque_date").datepicker("destroy");
		$("#dd_cheque_date").datepicker({
			minDate:-180,
			maxDate:0,
			changeMonth : true,
			changeYear : true,
			dateFormat : "dd-mm-yy",
		});
	
	}
	});
	
	if($("#reason").val() == 'notfound'){
		$(".errormessagediv").show();
		$(".validateTips").text("Class Fee SetUp not found");
		setTimeout(function(){
			$(".errormessagediv").hide();
		},3000);
	}
	
	
	
	$("select#paymentMode").change(function(){
		
		
		$("#dd_cheque_bank").val("");
		$("#paymentParticulars").val("");
		$("#dd_cheque_date").val("");
		
		$("#dd_cheque_bank").css({"border-color": "rgb(221, 221, 221)"});
		$("#paymentParticulars").css({"border-color": "rgb(221, 221, 221)"});
		$("#paymentParticulars").css({"border-color": "rgb(221, 221, 221)"});
		
		if($(this).val()=="Cash" || $(this).val()==""){
			$(".cashcardtotal").hide();
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
		}else if($(this).val()=="Card"){
			$(".cashcardtotal").hide();
			$("#dd_cheque_bank").show();
			$("#paymentParticulars,#dd_cheque_date").hide();
		}
		else if($(this).val()=="Card+Cash"){
			$(".extraAmt").show();
			
			$(".cashcardtotal").show();
			$("#dd_cheque_bank").show();
			$(".cashcardtotal").empty();
			$("#paymentParticulars,#dd_cheque_date").hide();

			$(".discountTab tr.headings").after("<tr class='cashcardtotal'>"+
					"<th style='width: 206px !important;'>Cash Amount</th>"+
					"<th><input type='text' id='cashamt' style='text-align:right;' value='0'/></th>"+
					"</tr>"+
					"<tr class='cashcardtotal'>"+
					"<th style='width: 150px !important;'>Card Amount</th>"+
					"<th><input type='text' style='text-align:right;' id = 'cardamt' value='0'/></th>"+
					"</tr>");

			
			$(".totalPay").val(parseFloat($(".grandTotal").val()).toFixed(2));
			var cardamt1 =0;
			$("#cashamt").keyup(function(){
				
				var cardamt1=parseFloat($("#cardamt").val());
				var finalamt=0;
				var grandtotal=parseFloat($(".grandTotal").val());
				var cashamt=$("#cashamt").val();
				
				if(cashamt > grandtotal){
					$('.errormessagediv').show();
					$('.validateTips').text("Cash Amount Should Be Less Then Grand Total.");
					$("#cashamt").val("");
				}else{
					cardfinAmt=grandtotal-cashamt;
					$("#cashamt").val(cashamt);
					$("#cardamt").val(cardfinAmt);
				}
			});
			$("#cardamt").keyup(function(){
				var cashamt=$("#cashamt").val();
				var finalamt1=0;
				var grandtotal1=parseFloat($(".grandTotal").val());
				var cardamt1=$("#cardamt").val();
				if(cardamt1 > grandtotal1){
					$('.errormessagediv').show();
					$('.validateTips').text("Card Amount Should Be Less Then Grand Total.");
					$("#cardamt").val("");
				}else{
					cashfinAmt=grandtotal1-cardamt1;
					$("#cardamt").val(cardamt1);
					$("#cashamt").val(cashfinAmt);
				}
			});
			
		}
		else if($(this).val()=="Online"){
			$(".cashcardtotal").hide();
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").show();
			$("#paymentParticulars").attr("placeholder","Enter Transaction Number.");
			$("#dd_cheque_date").attr("placeholder","Select Payment Date.");
		}
		else  if($(this).val()=="DD"){  // DD
			$(".cashcardtotal").hide(); 
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").show();
			$("#paymentParticulars").attr("placeholder","Enter "+$(this).val()+" Number.");
			$("#dd_cheque_date").attr("placeholder","Enter DD Date");

		}
		else{
			$(".cashcardtotal").hide();  // check
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").show();
			$("#paymentParticulars").attr("placeholder","Enter "+$(this).val()+" Number.");
			$("#dd_cheque_date").attr("placeholder","Enter Check Date");
		}
	});
	
	$("#selectAll").change(function(){
		$("input:checkbox.select").prop('checked', $(this).prop("checked"));
		if($(this).prop("checked"))
		$("#isdiscountapp").attr('disabled',false);
		else
			$("#isdiscountapp").attr('disabled','disabled');
	});
	
	
	$(":checkbox.select").change(function(){
		
		if($("#feestable input:radio").is(":visible")){
			if($(this).prop("checked")==true){
				if($(this).closest("tr").prev("tr").find("input:radio").is(":hidden")==true){
					if($(this).closest("tr").prev("tr").find(":checkbox.select").prop("checked")==true){
						$(this).prop("checked",$(this).prop("checked"));
					}
					else{
						$(this).prop("checked",false);
					}
				}
				
				}
			else{
				if($(this).closest("tr").next("tr").find(":checkbox.select").prop("checked")==true){
					$(this).prop("checked",true);
				}
				else{
					$(this).prop("checked",false);
				}
			}
		}
		
		else{
			
			if($(this).prop("checked")==true){
				
			if($(this).closest("tr").prev("tr").find(":checkbox.select").prop("checked")==true || $(this).closest("tr").prev("tr").find(":checkbox.select").prop("disabled")==true){
				$(this).prop("checked",true);
			}
			else{
				if($(this).closest("tr").prev("tr").find(":checkbox.select").prop("checked")==undefined){
					$(this).prop("checked",true);
				}
				else{
					$(this).prop("checked",false);
				}
				
			}
		}
			else{
				if($(this).closest("tr").next("tr").find(":checkbox.select").prop("checked")==true){
					$(this).prop("checked",true);
				}
				else{
					$(this).prop("checked",false);
				}	
				
			}
		}
		
	if($(":checkbox.select:checked").length==$(":checkbox.select").length){
		$("#selectAll").prop("checked",true);
	}
	else{
		$("#selectAll").prop("checked",false);
	}
		
	});
	

	
	
	$("#termsid").change(function(){
		if($(this).val()==""){
			$(".percentages").hide();
			$('.allstudent tr.discountrow').remove();
			  $("#myDialog .allstudent").each(function(j){
				  $("")
			  });
		}
		else{
			$(".percentages").val("0");
			$(".percentages").show();
			$('.allstudent tr.discountrow').remove();
			$("."+$(this).val()).find('.lastrow').after("<tr class='discountrow'>" +
						"<th>"+" "+"</td>" +
						"<th style='text-align:left !important;padding-left:5px;'>Discount</th>" +
						"<th ><input type='text'  class='discountAmt' name='discountAmt' value='0' readonly='readonly' style='text-align:right;' /></th>" +
						"</tr>");
			
			var finaltot = parseFloat($("."+$(this).val()+" "+"tbody tr").find("input[name='totalAmount']").val()) - parseFloat($("."+$(this).val()+" "+"tbody tr").find("input[name='discountAmt']").val());
			$("."+$(this).val()+" "+"tbody tr").find("input[name='payingAmount']").val(parseFloat(finaltot).toFixed(2));
			
		}
	});
	


	
	$(".pay").each(function(){
		
		if($(this).attr("name")=="Paid")
		{
			$(this).hide();
			$(this).next(".view").hide();
			$(this).next().next(".print").show();
		}
	});
	
	$("#feestable tbody tr").each(function(){
		
		if($(this).find("span").text() == "Paid"){
			$(this).find('.Paid').find('input[type="checkbox"]').prop('disabled', true);
			$("#selectAll").prop('disabled', true);
			$(this).find(".receipt").show();
		}
		if($(this).find("span").text() == "Pending"){
			$(this).find('.Pending').find('input[type="checkbox"]').hide();
			$(this).find('.Pending').find('input[type="radio"]').show();
			$("#selectAll").prop('disabled', true);
			$(this).find(".receipt").show();
		}
	});
	
	$('input[type="radio"]').click(function(){
		 if ($(this).hasClass("imChecked")) {
			 $(this).removeClass("imChecked");
			 $(this).prop('checked', false);
			 $("#feestable tbody tr").find('.Not').find('.select').prop('disabled', false);
			 $("#selectAll").prop('disabled', true)
		 } else { 
			 $(this).prop('checked', true);
			 $(this).addClass("imChecked");
			 $("#feestable tbody tr").find('.Not').find('.select').prop('disabled', true);
			 $("#selectAll").prop('disabled', true);
		 };
	});
	
	
	$(".print").click(function(){
			
		var locId = $("#locid").val();
		if($(".receiptno:checked").length>0){
		$('.errormessagediv').hide();
		var receiptno=$(".receiptno:checked").val();
		var terms="";
		$(".receiptno:checked").each(function(){
			terms=$(this).attr("id");
		});
		
		 amtpaid = $(".receiptno:checked").closest("tr").find('.amounts').text();
		
			var billdate=$(".billdate").text();
			$("#Dialog").empty();
			feeCollectionDetailsPrintBy(receiptno,terms,billdate,locId,amtpaid);
			$("#Dialog").dialog('open');
			$(".ui-dialog-titlebar").text("Fee Collection");
			$(".ui-dialog-buttonset button:nth-child(2)").show();
			$(".ui-dialog-buttonset button:nth-child(1)").show();
		}else{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Record");
		}
	});
	
	$("#Dialog").dialog({
		autoOpen  : false,
		maxWidth:500,
		maxHeight:600,
		width:500,
		height:600,
		modal     : true,
		buttons   : {
			'Print' : function() {
				var a=$("#printing-css").val();

				var b= document.getElementById("Dialog").innerHTML;
				var abd='<style>'+a+'</style>' + b;
				var frame1 = $('<iframe />');
				frame1[0].name = "frame1";
				frame1.css({ "position": "absolute", "top": "-1000000px" });
				$("body").append(frame1);
				var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
				frameDoc.document.open();
				//Create a new HTML document.
				frameDoc.document.write('<html><head><title>DIV Contents</title>');
				
				//Append the external CSS file.

				frameDoc.document.write('</head><body>');


				//Append the DIV contents.
				frameDoc.document.write('<p>School Copy</p>');
				frameDoc.document.write(abd);

				frameDoc.document.write('<p>Student Copy</p>');
				frameDoc.document.write(abd);
				frameDoc.document.write('</body></html>');
				frameDoc.document.close();
				setTimeout(function () {
					window.frames["frame1"].focus();
					window.frames["frame1"].print();
					frame1.remove();
				}, 100);

				$("#Dialog").empty();
				$(this).dialog('close');
			},
			'Close' : function() {
				$("#Dialog").empty();

				$(this).dialog('close');
			}
		}
	});

	
	$(".pay").each(function(){
		if($(this).attr("name")=="Not Paid")
		{
			$(".pay").not(this).hide();
			return false;
		}
	});
	
	//radiobutton changes
	$("input[name='discCalcu']").change(function(){
		
		if($(this).val()=="discPrcent"){
		$(".discount").hide();
		$(".checkde").show();
	}else{
		$(".discount").show();
		$(".discount").val("");
		$(".checkde").hide();
	}
	});
	
	$(".paynow").click(function(){
		
		$(".paying").not($(".paying:last")).attr("readonly",true);	
		
		var checkedCheckboxLength =  $("input:checkbox.select:checked").length;
		var checkboxLength = $("input:checkbox.select").length;
	
		if( $("input:checkbox.select:last").prop("checked")){
			$(".extraAmt").hide();
		}else{
			$(".extraAmt").show();
		}
		
		if($(".selectterm:checked").length == 0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select The Terms");
			$('.errormessagediv').delay(2000).fadeOut();
		}
	
		else if($(".imChecked").prop('checked')){
			$("#feepayment").val("balfees");
			$("#myDialog .paymentMode").show();
			$("#myDialog .myDailogTable").empty();
			$("#myDialog .discountTable").empty();
			$("#myDialog .grandtotal").empty();
			value = $(".imChecked:checked").val();
			id =  $(".imChecked:checked").attr('id')

			$("#termdetails").val(value);
			$("#amtdetails").val(id);
			paybalancefees(value,id,$(".imChecked:checked"));

			$("#myDialog").dialog('open');
			$(".ui-dialog-titlebar").text("Fee Collection");
			$(".ui-dialog-buttonset button:nth-child(1)").show();
		}
		else{
			$("#myDialog .paymentMode").show();
			$("#myDialog .myDailogTable").empty();
			$("#myDialog .discountTable").empty();
			$("#myDialog .grandtotal").empty();
			
			$("#feepayment").val("termfees");
			termname=[];
			termid = [];
			feesetcode = [];
			pointers = [];
		
			$(".select:checked").each(function(){
				termid.push($(this).val().split("-")[0]);
				feesetcode.push($(this).val().split("-")[1]);
				pointers.push($(this).attr("id"));
				termname.push($(this).closest("tr").find(".heading").text());
			});
			
			feeCollectionDetailsNew($(this),termid.toString(),feesetcode.toString(),pointers.toString(),termname.toString());
			
			$("#myDialog").dialog('open');
			$(".ui-dialog-titlebar").text("Fee Collection");
			$(".ui-dialog-buttonset button:nth-child(1)").show();
			
		}
	});
	
	
	$(".pay").click(function(){
	
		$("#myDialog .paymentMode").show();
		$("#myDialog .discountTable").show();
		$("#myDialog .myDailogTable").empty();
		$("#myDialog .discountTable").empty();
		termname=$(this).closest("tr").find(".heading").text();
		
		feeCollectionDetails($(this));
		
		$("#myDialog").dialog('open');
		
		$(".ui-dialog-titlebar").text($(this).closest("tr").find(".heading").text()+" Fee Collection");
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
	});
	
	$("#myDialog").dialog({
	    autoOpen  : false,
	    maxWidth:500,
        maxHeight: 500,
        width: 500,
        height: 500,
	    modal     : true,
	    buttons   : {
	              'Pay' : function() {
	            	  
	            	  var flag=true;
	            	  
	            	  if($("#feepayment").val() == "balfees"){
	            		  if($("select#paymentMode").val() !="" && $("select#paymentMode").val() !=undefined){
	            			  if($("select#").val() !="Cash" && $("select#paymentMode").val() !="Card" && $("select#paymentMode").val() != "Card+Cash"){
	            				  flag=false;
	            				  message="Select Payment Particulars type.";


	            				  if($("#dd_cheque_bank").val().length>0){
	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Bank Name.";
	            					  getError(message,"#dd_cheque_bank");
	            					  return false;
	            				  }
	            				  if($("#paymentParticulars").val().length>0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter "+$("select#paymentMode").val()+" No.";
	            					  getError(message,"#paymentParticulars");
	            					  return false;
	            				  }
	            				  if($("#dd_cheque_date").val().length>0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Select "+$("select#paymentMode").val()+" Date.";
	            					  getError(message,"#dd_cheque_date");
	            					  return false;
	            				  }
	            			  }
	            			  else if ($("select#paymentMode").val() == "Card+Cash" ){

	            				  if($("#dd_cheque_bank").val().length>0){
	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Bank Name.";
	            					  getError(message,"#dd_cheque_bank");
	            					  return false;
	            				  }
	            				  if($("#cashamt").val() !=0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Cash Amount";
	            					  getError(message,"#cashamt");
	            					  return false;
	            				  }
	            				  if($("#cardamt").val() !=0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Card Amount";
	            					  getError(message,"#cardamt");
	            					  return false;
	            				  }
	            			  }else if($("select#paymentMode").val() == "Card"){
	            				  if($("#dd_cheque_bank").val().length>0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Bank Name.";
	            					  getError(message,"#dd_cheque_bank");
	            					  return false;
	            				  }
	            			  }
	            			  if($("#admissiondate").val() ==""){
	            				  flag=false;
	            				  message="Select Bill Date";
	            				  getError(message,"#admissiondate");
	            				  return false;
	            			  }

	            			  if(flag){
	            				  $.ajax({
	            					  type : 'POST',
	            					  url : "feeCollectionNew.html?method=paybalFees",
	            					  data : {
	            						  "value":$("#termdetails").val(),
	            						  "id":$("#amtdetails").val(),
	            						  "paymentMode":$("select#paymentMode").val(),
	            						  "dd_cheque_bank":$("#dd_cheque_bank").val(),
	            						  "dd_cheque_date":$("#dd_cheque_date").val(),
	            						  "paymentParticulars":$("#paymentParticulars").val(),
	            						  "billdate" : $("#admissiondate").val(),
	            						  "addmissionNo":$("#hstudentid").val(),
	            						  'accodemicyear':$("#haccYear").val(),
	            						  'totalAmt' : $("#totalAmount").val(),
	            						  'payingAmount' : $("#payingAmount").val(),
	            						  "cashAmount":$("#cashamt").val(),
	          							  "cardAmount":$("#cardamt").val(),
	            					  },
	            					  async : false,
	            					  success : function(data) {
	            						  var result=$.parseJSON(data);
	            						  if(result.status=="success"){
	            							  $('.successmessagediv').show();
	            							  $('.sucessmessage').text("Fee Submit Progressing... ");

	            							  setTimeout(function(){
	            								  location.reload();
	            							  },2000);

	            						  }else{
	            							  $('.errormessagediv').show();
	            							  $('.validateTips').text("Fee Collection Not Done, Try Later.");
	            						  }
	            					  }
	            				  });

	            				  $("#myDialog .myDailogTable").empty();
	            				  $("#myDialog .discountTable").empty();
	            				  $("#isdiscountapp").val("");
	            				  $("select#paymentMode").val("");
	            				  $(".terms").hide();
	            				  $(".paymentMode").hide();
	            				  $(".percentages").hide();
	            				  $("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
	            				  $(this).dialog('close');
	            			  }else{
	            				  getError(message,"select#paymentMode");
	            			  }
	            		  }else{
	            			  $("select#paymentMode").focus();
	            			  $("select#paymentMode").css({
	            				  "border-color":"#f00"
	            			  });
	            			  $('.errormessagediv').show();
	            			  $('.errormessagediv').delay(2000).fadeOut();
	            			  $('.validateTips').text("Select Payment Mode. 45"); 
	            		  }
	            	  }else{
	            		  url = 'feeCollectionNew.html?method=saveFeeCollection';
		            	  
	            		  if($("select#paymentMode").val() !="" && $("select#paymentMode").val() !=undefined){
	            		
		            		  if($("select#paymentMode").val() !="Cash" && $("select#paymentMode").val() !="Card" && $("select#paymentMode").val() !="Card+Cash"){
		            			  flag=false;
		            			  message="Select Payment Particulars type.";
		            			  if($("#dd_cheque_bank").val().length>0){

		            				  flag=true;
		            			  }
		            			  else{
		            				  flag=false;
		            				  message="Enter Bank Name.";
		            				  getError(message,"#dd_cheque_bank");
		            				  return false;
		            			  }
		            			  
		            			  if($("#dd_cheque_bank").val().match("[A-Za-z]")){

		            				  flag=true;
		            			  }
		            			  else{
		            				  flag=false;
		            				  message="Enter Correct Bank Name.";
		            				  getError(message,"#dd_cheque_bank");
		            				  return false;
		            			  }
		            			  
		            			  if($("#paymentParticulars").val().length>0){

		            				  flag=true;
		            			  }
		            			  else{
		            				  flag=false;
		            				  message="Enter "+$("select#paymentMode").val()+" No.";
		            				  getError(message,"#paymentParticulars");
		            				  return false;
		            			  }
		            			  
		            			  if($("#dd_cheque_date").val().length>0){

		            				  flag=true;
		            			  }
		            			  else{
		            				  flag=false;
		            				  message="Select Date.";
		            				  getError(message,"#dd_cheque_date");
		            				  return false;
		            			  }
		            		  }else if ($("select#paymentMode").val() == "Card+Cash" ){

	            				  if($("#dd_cheque_bank").val().length>0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Bank Name.";
	            					  getError(message,"#dd_cheque_bank");
	            					  return false;
	            				  }
	            				  if($("#cashamt").val() !=0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Cash Amount";
	            					  getError(message,"#cashamt");
	            					  return false;
	            				  }
	            				  if($("#cardamt").val() !=0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Card Amount";
	            					  getError(message,"#cardamt");
	            					  return false;
	            				  }
	            			  }else if($("select#paymentMode").val() == "Card"){
	            				  if($("#dd_cheque_bank").val().length>0){

	            					  flag=true;
	            				  }
	            				  else{
	            					  flag=false;
	            					  message="Enter Bank Name.";
	            					  getError(message,"#dd_cheque_bank");
	            					  return false;
	            				  }
	            			  }
		            		  if($("#admissiondate").val() ==""){
		            			  flag=false;
		            			  message="Select Bill Date";
		            			  getError(message,"#admissiondate");
		            			  return false;
		            		  }
		            		  if($("#isdiscountapp").val() !="" && $("#selectAll").prop("checked")){

		            			  if($("#isdiscountapp").val() =="Y" && $("#termsid").val() !=""){
		            				  if($("#termsid").val().length>0){
		            					  flag=true;
		            				  }else{
		            					  flag=false;
		            					  message="Select Term";
		            					  getError(message,"#termsid");
		            					  return false;
		            				  }

		            				  if($("#percentages").val()!="0"){

		            					  flag=true;
		            				  }
		            				  else{
		            					  flag=false;
		            					  message="Enter Percentage";
		            					  getError(message,"#percentages");
		            					  return false;
		            				  }
		            			  }else{
		            				  flag=true;
		            			  }
		            		  }else if($("#selectAll").prop("checked")){
		            			  flag=false;
		            			  message="Select Discount";
		            			  getError(message,"#isdiscountapp");
		            			  return false;
		            		  }
		            		  /* if(isNaN($(".payingAmount").val()) || $(".payingAmount").val()==0){
		            			 flag=false;
		            			 message="Enter Number greater than zreo.";
		            		 }*/
		            		  var actualAmts = [];
		            		  var feeAmountArray=[];
		            		  var feeIdArray=[];
		            		  var advanceCarry=0.0;
		            		  var duesCarry=0.0;
		            		  var feetypeId = [];

		            		  var termids = [];
		            		  var totalamt =[];
		            		  var fineAmount = [];
		            		  var payingAmount = [];
		            		  var totalAmount = [];
		            		  var advanceCarrys = [];
		            		  var duesCarrys = [];
		            		  var concessions = [];
		            		  var actualAmtsArray = [];
		            		  var feeAmountsArray = [];
		            		  var feeIdArrays = [];
		            		  var feetypeIds = [];
		            		  var termfeeamout = [];
		            		  var prefeecol =[];
		            		  $(".myDailogTable .allstudent").each(function(j){
		            			  var actualAmts = [];
		            			  var feeAmountArray=[];
		            			  var feeIdArray=[];
		            			  var advanceCarry=0.0;
		            			  var duesCarry=0.0;
		            			  var feetypeId = [];

		            			  termids.push($(this).attr("class").split(" ")[2]);
		            			  totalAmount.push($(this).find(".footer").find(".totalAmount"+j).val());
		            			  payingAmount.push($(this).find(".footer").find(".payingAmount"+j).val());
		            			  concessions.push($(this).find(".concessionAmount"+j).val());
		            			  duesCarrys.push($(this).find(".collapsable").find("input[name='dueAmount']").val());
		            			  fineAmount.push($(this).find(".collapsable").find("input[name='FineAmount']").val());
		            			  duesCarrys.push($(this).find(".collapsable").find("input[name='dueAmount']").val());
		            			
		            			  prefeecol.push($(this).find(".collapsable").find("input[name='dueAmount']").attr('id'));
		            			 
		            			  $("#myDialog #allstudent"+j+" tr[id]").each(function(i){	

		            				  var amount=$(this).find(".Feeamount"+j).val();
		            				  var actualAmt = $(this).find(".actualAmt"+j).attr("class").split(" ")[1];
		            				  feeAmountArray.push(amount);
		            				  actualAmts.push(actualAmt);
		            				  var feeId=$(this).find(".Feeamount"+j).attr("name").split(" ")[0];
		            				  var feetype = $(this).find(".Feeamount"+j).attr("name").split(" ")[1];
		            				  feeIdArray.push(feeId);
		            				  feetypeId.push(feetype);

		            			  });

		            			/*  if(parseInt($(".payingAmount"+j).val()) > parseInt($(".totalAmount"+j).val())){
		            				  advanceCarry=parseFloat($(".payingAmount"+j).val())-parseFloat($(".totalAmount"+j).val());
		            			  }
*/

		            			  advanceCarrys.push(advanceCarry);
		            			  actualAmtsArray.push(actualAmts.toString().replace(/,/g,"-"));
		            			  feeAmountsArray.push(feeAmountArray.toString().replace(/,/g,"-"));
		            			  feeIdArrays.push(feeIdArray.toString().replace(/,/g,"-"));
		            			  feetypeIds.push(feetypeId.toString().replace(/,/g,"-"));
		            		  }); 
		            		var  discountAmtlist=0;
		            		  if($("input[name='discCalcu']").val()=="discPrcent"){
		            			  discountAmtlist=$(".B.discPrcent").val();
		            		  }
		            		  else{
		            			  discountAmtlist=  $("#discountAmt").val();
		            		  }
		            		
		            		
		            		  
		            		  var datalist={
		            				  "concessionAmount":concessions.toString(),
		            				  "addmissionNo":$("#hstudentid").val(),
		            				  "termid":termids.toString(),
		            				  'accodemicyear':$("#haccYear").val(),
		            				  'classd':$("#hclassId").val(),
		            				  'specialization':$("#hspecialization").val(),
		            				  "feeIdArray" : feeIdArrays.toString(),
		            				  "paymentMode":$("select#paymentMode").val(),
		            				  "dd_cheque_bank":$("#dd_cheque_bank").val(),
		            				  "dd_cheque_date":$("#dd_cheque_date").val(),
		            				  "paymentParticulars":$("#paymentParticulars").val(),
		            				  "feeSetting":$("#myDialog #allstudent").attr("id"),
		            				  "feeAmountArray" : feeAmountsArray.toString(),
		            				  "totalAmount":totalAmount.toString(),
		            				  "fineAmount":fineAmount.toString(),
		            				  "payingAmount":payingAmount.toString(),
		            				  "advanceCarry":advanceCarrys.toString(),
		            				  "duesCarry":duesCarrys.toString(),
		            				  "actualamtarray" : actualAmtsArray.toString(),
		            				  "feesetcode" : $("#feesetcode").val(),
		            				  "feetypeId" : feetypeIds.toString(),
		            				  "discountAmt" : discountAmtlist,
		            				  "distermid" : $("#termsid").val(),
		            				  "billdate" : $("#admissiondate").val(),
		            				  "prefeecol" : prefeecol.toString(),
		            				  "cashAmount":$("#cashamt").val(),
		  							  "cardAmount":$("#cardamt").val(),
		  							  "totalPay": $(".totalPay").val(),
		  							  "extraAmtCarryFrwd": $(".isExtraAmt").val()
		  							  
		  							 
		            		  };
		            		  if(flag){
		            			  $.ajax({
		            				  type : "POST",
		            				  url : url,
		            				  data :datalist,
		            				  async : false,
		            				  success : function(data) {
		            					  var result = $.parseJSON(data);
		            					  if(result.status=="true"){
		            						  $('.successmessagediv').show();
		            						  $('.sucessmessage').text("Fee Submit Progressing... ");

		            						  setTimeout(function(){
		            							  window.location.href=window.location.href+"&Print"
		            						  },2000);

		            					  }else{
		            						  $('.errormessagediv').show();
		            						  $('.validateTips').text("Fee Collection Not Done, Try Later");
		            					  }
		            				  }
		            			  });
		            			  $("#myDialog .myDailogTable").empty();
		            			  $("#myDialog .discountTable").empty();
		            			  $("#isdiscountapp").val("");
		            			  $("select#paymentMode").val("");
		            			  $(".terms").hide();
		            			  $(".percentages").hide();
		            			  $("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
		            			  $(this).dialog('close');
		            		  }
		            		  else{
		            			  getError(message,"select#paymentMode");
		            		  }
		            		  
		            	  }
	            		  
	            		  
		            	 else{
		            		 $("select#paymentMode").focus();
		            		 $("select#paymentMode").css({
		            			 "border-color":"#f00"
		            		 });
		            		 $('.errormessagediv').show();
		            		 $('.errormessagediv').delay(2000).fadeOut();
								$('.validateTips').text("Select Payment Mode. 23"); 
		            	 }
	            	  }
	              },
	              'Close' : function() {
	            	  $("#myDialog .myDailogTable").empty();
	            	  $("#myDialog .discountTable").empty();
	            	  $("#isdiscountapp").val("");
	            	  $("select#paymentMode").val("");
	            	  $(".terms").hide();
	            	  $(".percentages").hide();
	            		$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
	                  $(this).dialog('close');
	              }
	                }
	});
	
	$("#admissiondate").datepicker({

		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			if ((selectedDate != "") && (selectedDate != undefined)) {
				$('#admissiondate').datepicker('getDate');
			}
		}
	
	});
	
	$(".view").click(function(){
		
		$("#myDailogTable").empty();
		$("#myDialog .discountTable").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feepaidDetails($(this));
		$("#myDailogTable").dialog('open');
		
		$(".ui-dialog-titlebar").text($(this).closest("tr").find(".heading").text()+" Fee Collection");
		
	});
	
	
	$("#myDailogTable").dialog({
	    autoOpen  : false,
	    maxWidth:800,
        maxHeight: 600,
        width: 800,
        height: 600,
	    modal     : true,
	    buttons   : {
	    	 'Close' : function() {
	    		  $(this).dialog('close');
	    	 }
	    }
	});
	
	$("input:radio.receiptno:first").prop("checked",true);
	
$("span.glyphicon.glyphicon-menu-hamburger").click(function(){
	$(this).closest("table").find("tr").not("tr.heading").slideToggle();
	
});
	var url=window.location.href.substring(window.location.href.lastIndexOf("&")+1);
	
	if(url=="Print"){
		$("#feestable tr").not("tr.heading").hide();
		$("#printtable tr").not("tr.heading").show();
	}
	else{
		$("#printtable tr").not("tr.heading").hide();
		$("#feestable tr").not("tr.heading").show();
		
	}
}); //jquery

function feeCollectionDetailsDues(pointer){
	
	var dataList={'term':pointer.closest("tr").find(".heading").attr("id").split(",")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'feesetcode' : pointer.closest("tr").find(".heading").attr("id").split(",")[1]
			};
	$("#feesetcode").val(pointer.closest("tr").find(".heading").attr("id").split(",")[1]);
	
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feeCollectionDetailsDues',
		data:dataList,

		success : function(response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDialog .myDailogTable #allstudent").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;' class='actualAmt "+result.FeeCollectionDetails[i].actualAmt+"'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td ><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+"' value='"+result.FeeCollectionDetails[i].outStandingAmountArray+"'  style='text-align:right;' /></td>" +
						"</tr>");
			}
			
			dues = result.FeeCollectionDetails[result.FeeCollectionDetails.length].predues;
			
			
			
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td ><input type='text' id='advanceAmount' readonly class='advanceAmount' name='advanceAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[1]).toFixed(2)+"' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+2)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
					"<td ><input type='text' id='dueAmount' readonly class='dueAmount' name='dueAmount' value='"+dues+"'  style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+3)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td ><input type='text' id='FineAmount' readonly class='Feeamount' name='FineAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"'  style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+4)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td ><input type='text' id='concessionAmount' readonly class='concessionAmount' name='concessionAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[3]).toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>"+termname+" Total</th>" +
					"<th ><input type='text' class='totalAmount totamt' readonly name='totalAmount' value=''  style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
			
				$("#myDialog .myDailogTable #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
				});
				totalAmount=totalAmount-(parseFloat($(".advanceAmount").val())+parseFloat($(".concessionAmount").val()));
				$("#myDialog .myDailogTable #allstudent").append("<tr>" +
						"<th></th>" +
						"<th>Paying</th>" +
						"<th ><input type='text' class='payingAmount' name='payingAmount' value='"+totalAmount.toFixed(2)+"'  style='text-align:right;' /></th>" +
						"" +
						"</tr>");
				
				
				$("#myDialog .myDailogTable #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
				 $(".Feeamount").change(function(){
						var sumsmt = 0;
						$(".Feeamount").each(function(){
							sumsmt = parseFloat(sumsmt) + parseFloat($(this).val());
						});
						
						$(".payingAmount").val(sumsmt.toFixed(2));
				});	
				 
				 $(".Feeamount").change(function(){
						var sumsmt = 0;
						$(".Feeamount").each(function(){
							sumsmt = parseFloat(sumsmt) + parseFloat($(this).val());
						});
						
						$(".payingAmount").val(sumsmt.toFixed(2));
				});
				 
				
		
		}
	});
	
	
	
	
	
	
	
	
	
}

function feeCollectionDetails(pointer){
	var dataList={'term':pointer.closest("tr").find(".heading").attr("id").split(",")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'feesetcode' : pointer.closest("tr").find(".heading").attr("id").split(",")[1],
			'studentId' : $("#hstudentid").val()
			};
	
	
	$("#feesetcode").val(pointer.closest("tr").find(".heading").attr("id").split(",")[1]);
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feeCollectionDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#myDialog .myDailogTable").append("<table class='classfeesetup "+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id").split(",")[0]+"' width='100%' id='allstudent'>" +
					"<tr class='headings'>"+
					"<th colspan='3' style='font-size: 17px; text-align: left !important; background-color: #f5f5f5; font-family: Roboto Regular !important;'>"+
					"<span class='glyphicon glyphicon-menu-hamburger collapse' style='vertical-align: -5px; margin-right: 5px; padding-left: 5px;'></span>Fees</th>"+
					"</tr>"+
					"<tr class='collapsable'><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDialog .myDailogTable #allstudent").append("<tr class='collapsable' id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;' class='actualAmt "+result.FeeCollectionDetails[i].actualAmt+"'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td ><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+" "+result.FeeCollectionDetails[i].feetype+"' value='"+result.FeeCollectionDetails[i].actualAmt+"'  style='text-align:right;' /></td>" +
						"</tr>");
			}
			
			$("#myDialog .myDailogTable #allstudent").append("<tr class='collapsable'>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td ><input type='text' id='advanceAmount' readonly class='advanceAmount' name='advanceAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[1]).toFixed(2)+"' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr  class='collapsable'>" +
					"<td>"+(result.FeeCollectionDetails.length+2)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
					"<td ><input type='text' id='dueAmount' readonly class='Feeamount' name='dueAmount' value='"+pointer.closest('tr').find('.balfees').attr("class").split(" ")[2]+"'  style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr class='collapsable'>" +
					"<td>"+(result.FeeCollectionDetails.length+3)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td ><input type='text' id='FineAmount' readonly class='Feeamount' name='FineAmount' value='"+pointer.closest('tr').find('.balfees').attr("class").split(" ")[3]+"'  style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr class='collapsable'>" +
					"<td>"+(result.FeeCollectionDetails.length+4)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td ><input type='text' id='concessionAmount' class='concessionAmount' name='concessionAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[3]).toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog .myDailogTable #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>"+termname+" Total</th>" +
					"<th ><input type='text' class='totalAmount' readonly name='totalAmount' value=''  style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
				$("#myDialog .myDailogTable #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
				});
				totalAmount=totalAmount-(parseFloat($(".advanceAmount").val())+parseFloat($(".concessionAmount").val()));
				$("#myDialog .myDailogTable #allstudent").append("<tr>" +
						"<th></th>" +
						"<th ><input type='text' class='payingAmount' name='payingAmount' value='"+totalAmount.toFixed(2)+"'  style='text-align:right;' /></th>" +
						"" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
				
				 $(".Feeamount").change(function(){
						var sumsmt = 0;
						$(".Feeamount").each(function(){
							sumsmt = parseFloat(sumsmt) + parseFloat($(this).val());
						});
						
						$(".payingAmount").val(sumsmt.toFixed(2));
				});
				 
				 $(".headings").click(function(){
						
						$(this).closest("table.classfeesetup").find(".collapsable").slideToggle();
					});
				 
				 if($("select#paymentMode").val()=="Card+Cash"){
					
						$(".discountTab tr.headings").after("<tr>"+
								"<th style='width: 206px !important;'>Cash Amount</th>"+
								"<th><input type='text' id='cashamt' style='text-align:right;'  value='0'/></th>"+
								"</tr>"+
								"<tr>"+
								"<th style='width: 150px !important;'>Card Amount</th>"+
								"<th><input type='text' style='text-align:right;' id = 'cardamt' value='0'/></th>"+
								"</tr>");
							}
						}
	
	
	
	
	

	
	
	});
	
}


function feepaidDetails(pointer){

	var dataList={'term':pointer.closest("tr").find(".heading").attr("id").split(",")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val(),
			'feeindetailid' : pointer.closest("tr").find(".heading").attr("id").split(",")[2],
			'feecollectionId' :pointer.closest("tr").find(".heading").attr("id").split(",")[1],
			"action" :"view"
			};
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feePaidDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			
			if(pointer.attr('name').split(",")[3].toLowerCase() =="DD"){
				$("#myDailogTable>col-md-12>first").append("<label class='ddetails'>DD No.:</label><span class='ddetails'>"+pointer.attr('name').split(",")[3] +"</span>");
			}
			
			$("#myDailogTable").append("<h3 style='text-align:center;'>"+$("th.schoolname").text()+"</h3>" +
					"<h3 style='text-align:center;'>"+$('#schadd').val()+"</h3>" +
					"<hr>" +
					"<div class='row'>" +
					"<div class='col-md-12'>" +
						"<div class='col-md-6 first' style='text-align:justify;'>" +
							"<label style='padding:inherit'>AdmnNo</label><span>:</span>&nbsp;&nbsp;<span>"+$("#haddmissionno").val()+"</span>"+
							"<br>"+
							"<label style='padding:inherit'>Name</label><span>:</span>&nbsp;&nbsp;<span>"+$("#hstuName").val()+"</span>"+
							"<br>"+
							"<label style='padding:inherit'>Class & Div</label><span>:</span>&nbsp;&nbsp;<span>"+$("#hclassname").val()+"</span>"+
							"<br>"+
						"</div>"+
						"<div class='col-md-6 second' style='text-align:justify;'>" +
							"<label style='padding:inherit'>Paid Date</label><span>:</span>&nbsp;&nbsp;<span>"+pointer.attr('id')+"</span>"+
							"<br>" +
							"<label style='padding:inherit'>Receipt No</label><span>:</span>&nbsp;&nbsp;<span>"+pointer.attr('name').split(",")[1]+"</span>"+
							"<br>"+
							"<label style='padding:inherit'>Payment Mode</label><span>:</span>&nbsp;&nbsp;<span class='ddetails text-uppercase'>"+pointer.attr('name').split(",")[3]+"</span>"+
						"</div>" +
					"</div>"+
					"</div>"+
					"<hr>" +
					"<h3 style='text-align:center;'>FEE PAYMENT DETAILS</h3>" +
					"<hr>" +
					"");
			
			$("#myDailogTable").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"<th>Fee Collected</th>"+
					"<th>Tot.Fee Collected</th>"+
					"<th>Balance Amount</th>"+
					"</tr>" +
					"</table>");
		
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
				
				$("#myDailogTable #allstudent").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						"<td>"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align:right !important;'>"+result.FeeCollectionDetails[i].actualAmt+"</td>" +
						"<td style='text-align:right !important;'>"+result.FeeCollectionDetails[i].paidAmt+"</td>" +
						"<td style='text-align:right !important;'>"+result.FeeCollectionDetails[i].totPaidAmt+"</td>" +
						"<td style='text-align:right !important;'>"+result.FeeCollectionDetails[i].outStandingAmountArray+"</td>" +
				"</tr>");
			}
			
			$("#myDailogTable #allstudent").append("<tr>" +
					"<td>"+(result.FeeCollectionDetails.length+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right !important;'>"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"</td>" +
					"<td style='text-align:right !important;'>"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"</td>" +
					"<td style='text-align:right !important;'>"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"</td>" +
					"<td style='text-align:right !important;'>"+parseFloat(pointer.parent('td').attr('id').split(",")[0]).toFixed(2)+"</td>" +
			"</tr>");
			
			$("#myDailogTable #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>Total</th>" +
					"<th style='text-align:right !important;'>"+parseFloat(result.FeeCollectionDetails[result.FeeCollectionDetails.length-1].feeAmountArray.toFixed(2))+"</th>" +
					"<th style='text-align:right !important;'>"+parseFloat(result.FeeCollectionDetails[result.FeeCollectionDetails.length-1].feePayingAmountArray.toFixed(2))+"</th>" +
					"<th style='text-align:right !important;'>"+parseFloat(result.FeeCollectionDetails[result.FeeCollectionDetails.length-1].totPaidAmt.toFixed(2))+"</th>" +
					"<th style='text-align:right !important;'>"+parseFloat(result.FeeCollectionDetails[result.FeeCollectionDetails.length-1].totBalAmt.toFixed(2))+"</th>" +
			"</tr>");
		}
		
	});

	
}
function feeCollectionDetailsPrint(pointer){
	
	var due=0.0;
	var adv=0.0;
	if(isNaN(parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id')))){
		due=0.0;
	}
	else{
		due=parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id').split(",")[2]).toFixed(2);
	}
	
	var dataList={'term':pointer.val().split("-")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val(),
			"action" : "print",
			'feeindetailid' :  pointer.val().split("-")[2],
			'feecollectionId': pointer.val().split("-")[1],
			'receiptNo' : pointer.attr('id')
			};
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feePaidDetails',
		data:dataList,
		
		success : function(response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#Dialog").append("<h3 style='text-align:center;'>"+$("th.schoolname").text()+"</h3>" +
					"<h3 style='text-align:center;'>"+$('#schadd').val()+"</h3>" +
					"<hr>" +
					"<div style='display:inline-block;width:210px;'><span>Student Id No:</span><span><b>"+$("#hstudentidno").val()+"</b></span></div>"+
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Admission No:</span><span><b>"+$("#haddmissionno").val()+"</b></span></div>"+""+"<div style='display:inline-block;width:230px; text-align:right'><span class='paiddate'>Paid Date:</span><span><b>"+pointer.closest('tr').find('.paiddate').text()+"</b></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Name:</span><b>"+$("#hstuName").val()+"</b></span></div>"+""+"<div style='display:inline-block;width:230px; text-align:right'><span  class='receipt'>Receipt No:</span><span><b>"+pointer.attr('name').split(",")[0]+"</b></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Class & Div:</span><b>"+$("#hclassname").val() +"</b></div>"+"<div style='display:inline-block;width:230px; text-align:right'><span  class='receipt'>Payment Mode:</span><span><b>"+pointer.attr('name').split(",")[2]+"</b></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span class='ddetails'>DD No.:</span><span class='ddetails'><b>"+pointer.attr('name').split(",")[3] +"</b></span></div>"+"<div style='display:inline-block;width:230px; text-align:right'><span class='ddetails'>DD Date:</span><span class='ddetails'><b>"+pointer.attr('name').split(",")[4]+"</b></span></div>" +
					"<hr style='margin-top: 0;'>" +
					"<h3 style='text-align:center;'>FEE RECEIPT</h3>" +
					"<div style='text-align:center;'>"+termname +"</div>"+
					"");
		
			if((pointer.attr('name').split(",")[2]).toLowerCase()=='Cash'){
				$('.ddetails').hide();
			}
			$("#Dialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%' id='printtable'>" +
				
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			prebal = 0.0;
			for(var i=0;i<result.FeeCollectionDetails.length;i++){
			
				actualAmt = result.FeeCollectionDetails[i].actualAmt;
				paidAmt = parseFloat(result.FeeCollectionDetails[i].totPaidAmt);
				prebal = parseFloat(result.FeeCollectionDetails[0].outStandingAmountArray).toFixed(2);
				predues = parseFloat(result.FeeCollectionDetails[0].dueCarry).toFixed(2);
				totalamt = parseFloat(result.FeeCollectionDetails[0].totalFeeAmt).toFixed(2);
				totPaidAmt = parseFloat(result.FeeCollectionDetails[0].totPaidAmt).toFixed(2);
				disAmt = parseFloat(result.FeeCollectionDetails[0].disAmt).toFixed(2);
				$("#Dialog #printtable").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align:right;'><input type='text' class='Feeamount' name='"+result.FeeCollectionDetails[i].feecode+"' value='"+parseFloat(result.FeeCollectionDetails[i].actualAmt).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
						"</tr>");
			}
			
			$("#Dialog #printtable").append("<tr>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right;'><input type='text' id='FineAmount' class='Feeamount' name='FineAmount' value='"+parseFloat(pointer.parent('td').attr('id')).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Pre. Dues</td>" +
					"<td style='text-align:right;'><input type='text' id='predues' class='predues' name='predues' value='"+prebal+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td style='text-align:right;'><input type='text' id='concessionAmount' class='concessionAmount' name='concessionAmount' value='"+parseFloat(pointer.parent('td').attr('id').split(",")[3]).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Discount</td>" +
					"<td style='text-align:right;'><input type='text' id='disamt' class='disamt' name='disamt' value='"+disAmt+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			totalAmount= parseFloat(totalamt) - disAmt;
			$("#Dialog #printtable").append("<tr>" +
			
					"<th style='text-align:left;'>Total</th>" +
					"<th style='text-align:right;'><input type='text' class='totalAmount' name='totalAmount' value='"+totalAmount+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<th style='text-align:left;'>Paid Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='paidAmount' name='paidAmount' value='"+totPaidAmt+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			$("#Dialog #printtable").append("<tr>" +
					
					"<th style='text-align:left;'>Due Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='dueAmount' name='dueAmount' value='"+predues+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
				$("#Dialog #printtable tbody").find(".totalAmount").val(totalAmount.toFixed(2));
		}
	
	});
	
}
function getError(message,id){
	$("input,select").not(id).css({
		 "border-color":"#ddd"
	});
	$(id).focus();
	 $(id).css({
		 "border-color":"#f00"
	 });
	$('.errormessagediv').show();
	 $('.errormessagediv').delay(2000).fadeOut();
		$('.validateTips').text(message);
}


function feeCollectionDetailsNew(pointer,termid,feesetcode,pointers,termname){

	var dataList={'term': termid.replace(/,/g,"-"),//pointer.closest("tr").find(".heading").attr("id").split(",")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'feesetcode' : feesetcode.replace(/,/g,"-"),
			'studentId' : $("#hstudentid").val()
			};
	$("#feesetcode").val(feesetcode);
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feeCollectionDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			
			for(var i = 0;i<result.FeeCollectionDetails.length;i++){
				var totalAmount=0.0;
				$("#myDialog .myDailogTable").append("<table class='allstudent classfeesetup "+termid.split(",")[i]+"' width='100%' id='allstudent"+i+"' style='margin-bottom: 20px'>" +
						"<tr class='headings'>"+
						"<th colspan='3' style='font-size: 17px; text-align: left !important; background-color: #f5f5f5; font-family: Roboto Regular !important;'>"+
						"<span class='glyphicon glyphicon-menu-hamburger collapse' style='vertical-align: -5px; margin-right: 5px; padding-left: 5px;'></span>"+termname.split(",")[i]+"</th>"+
						"</tr>"+
						"<tr class='collapsable'><th>Sl.No.</th>" +
						"<th style='text-align:left !important'>Fee Description</th>" +
						"<th>Fee Amount</th>"+
						"</tr>" +
						"</table>");
				
				len = result.FeeCollectionDetails[i].feeNamelist.length;
				
				for(var j=0;j<len;j++){
					
					$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='collapsable' id=rowid"+result.FeeCollectionDetails[i].feeNamelist[j].sno+">" +
							"<td>"+(j+1)+"</td>" +
							"<td style='text-align:left !important;padding-left:5px;' class='actualAmt"+i+" "+result.FeeCollectionDetails[i].feeNamelist[j].actualAmt+"'>"+result.FeeCollectionDetails[i].feeNamelist[j].feename+"</td>" +
							"<td style='width:250px;'><input type='text' class='Feeamount"+i+" amount "+result.FeeCollectionDetails[i].feeNamelist[j].feetype+"' readonly name='"+result.FeeCollectionDetails[i].feeNamelist[j].feecode+" "+result.FeeCollectionDetails[i].feeNamelist[j].feetype+"' value='"+result.FeeCollectionDetails[i].feeNamelist[j].actualAmt+"'  style='text-align:right;' /></td>" +
							
					"</tr>");
				}
				
				$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='collapsable'>" +
						"<td>"+(len+1)+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
						"<td style='width:250px;'><input type='text'  readonly class='advanceAmount"+i+"' name='advanceAmount' value='"+parseFloat(pointers.split(",")[i].split("-")[3]).toFixed(2)+"' style='text-align:right;' /></td>" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent"+i).append("<tr  class='collapsable'>" +
						"<td>"+(len+2)+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
						"<td style='width:250px;'><input type='text' id ='"+pointers.split(",")[i].split("-")[5]+"' readonly class='Feeamount"+i+" amount' name='dueAmount' value='"+pointers.split(",")[i].split("-")[1]+"'  style='text-align:right;' /></td>" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='collapsable'>" +
						"<td>"+(len+3)+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
						"<td style='width:250px;'><input type='text' readonly class='Feeamount"+i+" amount' name='FineAmount' value='"+pointers.split(",")[i].split("-")[2]+"'  style='text-align:right;' /></td>" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='collapsable'>" +
						"<td>"+(len+4)+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
						"<td style='width:250px;'><input type='text'  class='concessionAmount"+i+"' name='concessionAmount' value='"+pointers.split(",")[i].split("-")[4]+"' readonly='readonly' style='text-align:right;' /></td>" +
						"</tr>");
				$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='footer  lastrow'>" +
						"<th></th>" +
						"<th>"+termname.split(",")[i]+" Total</th>" +
						"<th style='width:250px;'><input type='text' class='totalAmount"+i+"' readonly name='totalAmount' value=''  style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
						"" +
						"</tr>");
				
				
					$("#myDialog .myDailogTable #allstudent"+i+" tbody").find(".Feeamount"+i).each(function(){
						
						$(this).val(parseFloat($(this).val()).toFixed(2));
						totalAmount=totalAmount+parseFloat($(this).val());
					});
					
					
					
				//	totalAmount=totalAmount-(parseFloat($(".advanceAmount"+i).val())+parseFloat($(".concessionAmount"+i).val()));
				//	totalAmount=totalAmount-parseFloat($(".concessionAmount"+i).val());
					
					if(parseFloat($(".advanceAmount"+i).val()) >= totalAmount){  //advance more
						
						var nextAdvanceCarry = (parseFloat($(".advanceAmount"+i).val()) - totalAmount);
					
						$(".paying").prop("readonly",true);
						$(".ispayingExtra").show();
						$(".ispayingExtra").prop("checked",true);
						$(".ispayingExtra").attr("disabled",true);
						$(".isExtraAmt").show();
						$(".isExtraAmt").val(nextAdvanceCarry);
						$(".extraAmtNote").empty();
						$(".extraAmtNote").show();
						$(".extraAmtNote").append("Extra Amt.'<b>"+nextAdvanceCarry+"</b>' is Carry forwarding to next Term.");
						
						$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='footer'>" +
								"<th></th>" +
								"<th>Paying</th>" +
								"<th style='width:250px;'><input type='text' class='payingAmount"+i+" paying number-only "+totalAmount.toFixed(2)+"' readonly='readonly'  onkeypress='return isNumberKey(event)'  name='payingAmount' value='"+totalAmount.toFixed(2)+"' style='text-align:right;' /></th>" +
								"</tr>");
						
						$("#myDialog .myDailogTable #allstudent"+i+" tbody").find(".totalAmount"+i).val(parseFloat(totalAmount).toFixed(2));
						
					}else{
						
						totalAmount=totalAmount-(parseFloat($(".advanceAmount"+i).val())+parseFloat($(".concessionAmount"+i).val()));
						
						$("#myDialog .myDailogTable #allstudent"+i).append("<tr class='footer'>" +
								"<th></th>" +
								"<th>Paying</th>" +
								"<th style='width:250px;'><input type='text' class='payingAmount"+i+" paying number-only "+totalAmount.toFixed(2)+"'  onkeypress='return isNumberKey(event)'  name='payingAmount' value='"+totalAmount.toFixed(2)+"' style='text-align:right;' /></th>" +
								"</tr>");
						
						$("#myDialog .myDailogTable #allstudent"+i+" tbody").find(".totalAmount"+i).val(parseFloat(totalAmount).toFixed(2));
						
					}
					var sumsmt = 0;
			}
			
			
			$("#myDialog .discountTable").append("<table class='allstudent discountTab "+termid.split(",")[1]+"' width='100%' id='allstudent' style='margin-bottom: 20px'>" +
					"<tr class='headings'>"+
					"<th class='table2'>Grand Total</th>"+
					"<th  style='width:250px;'><input type='text' class='grandTotal' readonly='readonly'  ></th>"+
					"</tr>" +
					"<tr>"+
					"<th><label class ='classD'>Discount in</label><label class='classA'><input type='radio' name='discCalcu' checked  value='discPrcent' />% </label><label class='classB'><input type='radio' name='discCalcu' value='discAmt' />Amount</label></th>"+
					"<th style='width:250px;'><input type='text' class='discount discAmt disc' placeholder='Amount'></input><input type='number' min='0' max='100' pattern='^[0-9]$' class='checkde A form-control discPrcent disc' placeholder='%'></input><input type='text' class='checkde B form-control discPrcent disc' placeholder='discount' readonly='readonly' style='width:126px;' value='0'></input></th>"+
					"</tr>" +
					"<tr>"+
					"<th>Total Pay</th>"+
					"<th style='width:250px;'><input type='text' readonly='readonly' class='totalPay'></th>"+
					"</tr>" +
					"</table>");
			
			var SinglePayAmt = 0;
			var totamt = 0;
			var totpayamt = 0;
			var TotalpayingAmt = 0;
			var original = parseFloat($(".totalPay").val());
			
			$(".paying").not($(".paying:last")).attr("readonly",true);	
			
			var payingAmtt = parseFloat($(".paying:last").val());
			 $(".paying").keyup(function(){
				 var lastTotamt = parseFloat($("input[name='totalAmount']:last").val());
			 
				 if(parseFloat($(this).val()) > lastTotamt.toFixed(2)){
					$('.errormessagediv').show();
					$('.validateTips').text("Paying Amount Should Be < or = To Term Total.");
					 $(".paying:last").val(payingAmtt.toFixed(2));
				 }else{
					 $('.errormessagediv').hide();
				 }
			 });
			
			
			 var termPaying = 0.0; 
			 GrandTotalcalculation(termPaying);
		
			 
			 $("input[name='discCalcu']").change(function(){
				 $(".disc").hide();
				 $("."+$(this).val()).show();
				
				 if($("input[name='discCalcu']").val()=="discPrcent"){
							percentageCalculation($(".A"));
					}
				 else{
					 if(isNaN($(".discount").val())){
					 $(".totalPay").val(Number($(".grandTotal").val())- Number($(".discount").val()));
					 }
				 }
			 });	 
			 
			 
				
		 $("input[name='payingAmount']").keyup(function(){
			 
			 var termPaying = 0.0; 
			 $("input[name='payingAmount']").each(function(){ 
				 $("#cashamt").val("0");
				 $("#cardamt").val("0");
				 var currentpaying = 0;
					if(!(isNaN($(this).val()))){
						if($(this).val()<1){
							$(this).val(1);
						}
						currentpaying = Number($(this).val());
					}
					termPaying = parseFloat(termPaying) + parseFloat(currentpaying);
				});
			 
			 
			 $(".grandTotal").val(termPaying.toFixed(2)) ;
			 $(".totalPay").val(termPaying.toFixed(2));
			 
				
				
					
				// hideShowExtraAmt(pointer);
				 // hide and show extra paying checkbox when last paying amt less than actual
				 var lastTotamt = Number($(this).closest(".classfeesetup").find("tr.lastrow").find("input[name='totalAmount']").val());
				 var lastPayingAmt = Number($(this).val());
				 
				
				 if(!(isNaN($(this).val()))){
					 if( $("input:checkbox.select:last").prop("checked")){
							$(".extraAmt").hide();
							 $(".isExtraAmt").val("0");
						}else{
							if(Number(lastPayingAmt) < Number(lastTotamt) ){
									$(".extraAmt").hide();
									$(".isExtraAmt").val("0");
							}	
							 else {
								 $(".extraAmt").show();
							 }
							
						}
						
					
				 }else{
					 $(".extraAmt").hide();
				 }
				 
			var grandTotal = Number($(".grandTotal").val());
			var original = parseFloat($(".grandTotal").val());
			var tot = Number($(".grandTotal").val());
			$(".totalPay").val(tot.toFixed(2));
			
			var grandTotal = Number($(".grandTotal").val());
			
				if($("input[name='discCalcu']").val()=="discPrcent"){
					$("input[value='discPrcent']").prop("checked",true);
						$(".discount").hide();
						$(".checkde").show();
						percentageCalculation($(".A"));
				}
				else{
					$("input[value='discAmt']").prop("checked",true);
					 if(isNaN($(".discount").val())){
						 tot = Number($(".discount").val()) + Number($(".totalPay").val(termPaying)) ;
					 $(".grandTotal").val(tot.toFixed(2)) ;
					 $(".totalPay").val(termPaying.toFixed(2));
						
					 }
					 else{
					 $(".grandTotal").val(termPaying.toFixed(2)) ;
					 $(".totalPay").val(termPaying.toFixed(2));
					 }
				}
		});
		 
			$(".paying").not($(".paying:last")).attr("readonly",true);	
			
			var checkedCheckboxLength =  $("input:checkbox.select:checked").length;
			
			if(checkedCheckboxLength ==1){
				$("tr.collapsable").css({"display":"table-row"});
			}
			else{
				$(".headings").click(function(){
					$(this).closest("table.classfeesetup").find(".collapsable").slideToggle();
				});
			}
		 
			 $(".A").change(function(){
					percentageCalculation($(this));
			 });
				
			$(".A").keyup(function(){
					percentageCalculation($(this))
			});
				
				$(".discount").change(function(){
					var TotalpayingAmt = parseFloat($(".grandTotal").val());
					if( Number($(this).val()) <= TotalpayingAmt &&  Number($(this).val()) !=0 ){
						var discountFee = parseFloat(TotalpayingAmt) - parseFloat($(this).val());
						$(".totalPay").val(discountFee.toFixed(2));
						
						var discpercent = (Number($(this).val()) /TotalpayingAmt)*100;
						$(".A").val(discpercent);
						
					}else{
						
						$(this).val("0");
						$(".totalPay").val(original.toFixed(2));
					}
				});
				
				$(".discount").keyup(function(){
					var TotalpayingAmt = parseFloat($(".grandTotal").val());
					
					if( Number($(this).val()) <= TotalpayingAmt &&  Number($(this).val()) !=0 ){
						var discountFee = parseFloat(TotalpayingAmt) - parseFloat($(this).val());
						$(".totalPay").val(discountFee.toFixed(2));
						
						
					}else{
						$(this).val("0");
						$(".totalPay").val(TotalpayingAmt.toFixed(2));
					}
				});
		}//success
	}); //ajax
}


function getTermdetails(){
	
	$.ajax({
			
			type : 'POST',
			url : "studentRegistration.html?method=TermdeatilsForConcession",
			data : {"classId":$("#hclassId").val(),
					"accyear":$("#haccYear").val(),
					"specialization":$("#hspecialization").val(),
					"locid" : $("#locid").val()
			},
			async : false,
			success : function(response) {
				var result=$.parseJSON(response);
				$('#termsid').empty();
				$('#termsid').append("<option value=''>-----Select-----</option>");
				for(var i=0;i<result.stuList.length;i++){
					$('#termsid').append(
							"<option value='"+result.stuList[i].termcode+"'>"+result.stuList[i].term+"</option>");
					
				}
			}
		});
}

function paybalancefees(value,id,pointer){
	
	
	$("#myDialog .myDailogTable").append("<table class='allstudent classfeesetup "+value.split("-")[0]+"' width='100%'  style='margin-bottom: 20px'>" +
			"<tr class='headings'>"+
			"<th colspan='3' style='font-size: 17px; text-align: left !important; background-color: #f5f5f5; font-family: Roboto Regular !important;'>"+
			"<span class='glyphicon glyphicon-menu-hamburger collapse' style='vertical-align: -5px; margin-right: 5px; padding-left: 5px;'></span>"+pointer.closest('tr').find('.heading').text()+"</th>"+
			"</tr>"+
			"<tr><th>Sl.No.</th>" +
			"<th style='text-align:left !important'>Fee Description</th>" +
			"<th>Fee Amount</th>"+
			"</tr>" +
			"</table>");
	
	$("#myDialog .myDailogTable .allstudent").append("<tr>" +
			"<td>1</td>" +
			"<td style='text-align:left !important;padding-left:5px;'>Balance Amount</td>" +
			"<td ><input type='text' readonly style='text-align:right;' value='"+id.split("-")[0]+"'/></td>" +
			"</tr>");
	
	$("#myDialog .myDailogTable .allstudent").append("<tr>" +
			"<th></th>" +
			"<th>Total</th>" +
			"<th ><input type='text' readonly name='totalAmount' id = 'totalAmount' value='"+id.split("-")[0]+"'  style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
			"" +
			"</tr>");
	
	$("#myDialog .myDailogTable .allstudent").append("<tr>" +
			"<th></th>" +
			"<th>Paying</th>" +
			"<th ><input type='text' class='payingAmount'   name='payingAmount' id='payingAmount' value='"+id.split("-")[0]+"'  style='text-align:right;' /></th>" +
			"" +
			"</tr>");

	
}

function balfeespayment(){
	
	var flag=true;
	if($("select#paymentMode").val() !="" && $("select#paymentMode").val() !=undefined){
		 $(".paying").not($(".paying:last")).attr("readonly",true);	
		if($("select#paymentMode").val() !="Cash" && $("select#paymentMode").val() !="Card" && $("select#paymentMode").val() != "Card+Cash"){
			flag=false;
			message="Select Payment Particulars type.";


			if($("#dd_cheque_bank").val().length>0){

				flag=true;
			}
			else{
				flag=false;
				message="Enter Bank Name.";
				getError(message,"#dd_cheque_bank");
				return false;
			}
			if($("#paymentParticulars").val().length>0){

				flag=true;
			}
			else{
				flag=false;
				message="Enter "+$("select#paymentMode").val()+" No.";
				getError(message,"#paymentParticulars");
				return false;
			}
			if($("#dd_cheque_date").val().length>0){

				flag=true;
			}
			else{
				flag=false;
				message="Select "+$("select#paymentMode").val()+" Date.";
				getError(message,"#dd_cheque_date");
				return false;
			}
		}
		else if ($("select#paymentMode").val() == "Card" || $("select#paymentMode").val() == "Card+Cash" ){
			if($("#dd_cheque_bank").val().length>0){
				flag=true;
			}
			else{
				flag=false;
				message="Enter Bank Name.";
				getError(message,"#dd_cheque_bank");
				return false;
			}
		}
		if(flag){
			$.ajax({
				type : 'POST',
				url : "feeCollectionNew.html?method=paybalFees",
				data : {
					"value":$("#termdetails").val(),
					"id":$("#amtdetails").val(),
					"paymentMode":$("select#paymentMode").val(),
					"dd_cheque_bank":$("#dd_cheque_bank").val(),
					"dd_cheque_date":$("#dd_cheque_date").val(),
					"paymentParticulars":$("#paymentParticulars").val(),
					
					"addmissionNo":$("#hstudentid").val(),
					'accodemicyear':$("#haccYear").val(),
					'totalAmt' : $("#totalAmount").val(),
					'payingAmount' : $("#payingAmount").val()
				},
				async : false,
				success : function(response) {
					var result=$.parseJSON(response);
					if(result.status=="true"){
						$('.successmessagediv').show();
						$('.sucessmessage').text("Fee Submit progressing... ");

						setTimeout(function(){
							location.reload();
						},2000);

					}else{
						$('.errormessagediv').show();
						$('.validateTips').text("Fee collection not done.Try later");
					}
				}
			});

			$("#myDialog .myDailogTable").empty();
			$("#myDialog .discountTable").empty();
			$("#isdiscountapp").val("");
			$("select#paymentMode").val("");
			$(".terms").hide();
			$(".paymentMode").hide();
			$(".percentages").hide();
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
			$(this).dialog('close');
		}else{
			getError(message,"select#paymentMode");
		}
	}else{
		$("select#paymentMode").focus();
		$("select#paymentMode").css({
			"border-color":"#f00"
		});
		$('.errormessagediv').show();
		$('.errormessagediv').delay(2000).fadeOut();
		$('.validateTips').text("Select Payment Mode dsfdsfds."); 
	}
}

function feeCollectionDetailsPrintNew(pointer){
	//alert("hello="+('#schadd').val());
	var due=0.0;
	var adv=0.0;
	if(isNaN(parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id')))){
		due=0.0;
	}
	else{
		due=parseFloat(pointer.closest('tr').prev('tr').find(".print").parent('td').attr('id').split(",")[2]).toFixed(2);
	}
	
	var dataList={'term':pointer.val().split("-")[0],
			'accYear':$("#haccYear").val(),
			'classId':$("#hclassId").val(),
			'specialization':$("#hspecialization").val(),
			'student':$("#hstudentid").val(),
			"action" : "print",
			'feeindetailid' :  pointer.val().split("-")[2],
			'feecollectionId': pointer.val().split("-")[1],
			'receiptNo' : pointer.attr('id'),
			'locId' : $("#locid").val(),
			};
	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feePaidDetails',
		data:dataList,

		success : function(
				response) {
			var result = $.parseJSON(response);
			var totalAmount=0.0;
			$("#Dialog").append("<h3 style='text-align:center;'>"+$("th.schoolname").text()+"</h3>" +
					"<h3 style='text-align:center;'>"+$('#schadd').val()+"</h3>" +
					"<hr>"+
					"<table style='width:100%;'>"+
					"<tr>" +
					"<td>Student Id No</td>"+
					"<td>:</td>"+
					"<td>Student Id No</td>"+
					"<td>Paid Date</td>"+
					"<td>:</td>"+
					"<td>Paid Date</td>"+
					"</tr>"+
					"<tr>" +
					"<td>Admis No</td>"+
					"<td>:</td>"+
					"<td>Student Id No</td>"+
					"<td>Pay Mode</td>"+
					"<td>:</td>"+
					"<td>Pay Mode</td>"+
					"</tr>"+
					"<tr>" +
					"<td>Class&Div</td>"+
					"<td>:</td>"+
					"<td>Class&Div</td>"+
					"<td>Pay Mode</td>"+
					"<td>:</td>"+
					"<td>Pay Mode</td>"+
					"</tr>"+
					"</table>"+
					/*"<hr>" +
					"<div style='display:inline-block;width:210px;'><span>Student Id No:</span><span></span></div><div style='display:inline-block;width:230px; text-align:right'><span class='paiddate'>Paid Date:</span><span></span></div>"+
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Admission No:</span><span></span></div><div style='display:inline-block;width:230px; text-align:right'><span class='paiddate'>Paid Date:</span><span></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Name:</span><div style='display:inline-block;width:230px; text-align:right'><span  class='receipt'>Receipt No:</span><span></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span>Class & Div:</span><div style='display:inline-block;width:230px; text-align:right'><span  class='receipt'>Payment Mode:</span><span></span></div>" +
					"<br>" +
					"<div style='display:inline-block;width:210px;'><span class='ddetails'>DD No.:</span><span class='ddetails'></span></div>"+"<div style='display:inline-block;width:230px; text-align:right'><span class='ddetails'>DD Date:</span><span class='ddetails'></span></div>" +
				*/	"<hr style='margin-top: 0;'>" +
					"<h3 style='text-align:center;'>FEE RECEIPT</h3>" +
					"<div style='text-align:center;'>"+termname +"</div>"+
					"");
		
			/*if((pointer.attr('name').split(",")[2]).toLowerCase()=='cash'){
				$('.ddetails').hide();
			}*/
			$("#Dialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%' id='printtable'>" +
				
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			prebal = 0.0;
			for(var i=0;i<result.FeeCollectionDetails.feeNamelist.length;i++){
				
				$("#Dialog #printtable").append("<tr id=rowid"+result.FeeCollectionDetails[i].sno+">" +
						
						"<td style='text-align:left !important;padding-left:5px;'>"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align:right;'><input type='text' class='Feeamount' value='"+parseFloat(result.FeeCollectionDetails[i].totPaidAmt).toFixed(2)+"' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
						"</tr>");
			}
			
			$("#Dialog #printtable").append("<tr>" +
					"<td style='text-align:left !important;padding-left:5px;'>Fine</td>" +
					"<td style='text-align:right;'><input type='text' id='FineAmount' class='Feeamount' name='FineAmount' value='' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Pre. Dues</td>" +
					"<td style='text-align:right;'><input type='text' id='predues' class='predues' name='predues' value='' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td style='text-align:right;'><input type='text' id='concessionAmount' class='concessionAmount' name='concessionAmount' value='' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<td style='text-align:left !important;padding-left:5px;'>Discount</td>" +
					"<td style='text-align:right;'><input type='text' id='disamt' class='disamt' name='disamt' value='' readonly='readonly' style='text-align:right;width:100px;' /></td>" +
					"</tr>");
			$("#Dialog #printtable").append("<tr>" +
			
					"<th style='text-align:left;'>Total</th>" +
					"<th style='text-align:right;'><input type='text' class='totalAmount' name='totalAmount' value='' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			
			$("#Dialog #printtable").append("<tr>" +
					
					"<th style='text-align:left;'>Paid Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='paidAmount' name='paidAmount' value='' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
			$("#Dialog #printtable").append("<tr>" +
					
					"<th style='text-align:left;'>Due Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='dueAmount' name='dueAmount' value='' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"" +
					"</tr>");
				$("#Dialog #printtable tbody").find(".totalAmount").val(totalAmount.toFixed(2));
		}
		
	});
}

function feeCollectionDetailsPrintBy(receiptno,terms,billdate,locId,amtpaid){
	var dataList={
		'receiptNo':receiptno,
		'accYearId':$("#haccYear").val(),
		'studentId':$("#hstudentid").val(),
		'locationId':$("#hlocationId").val(),
		'locId':locId,
	};

	$.ajax({
		type:'post',
		url:'feeCollectionNew.html?method=feePaidDetails',
		data:dataList,

		success : function(response) {
			var result = $.parseJSON(response);
			
			var totalAmount=0.0;
			$("#Dialog").append("<table class='table feetable' style='margin-bottom: 0px;margin-top: 0px;'>" +
									"<thead>" +
									"<tr>"+
									"<th style='text-align:center;font-size:15px;'>"+result.schoolName+
									"</th>" +
									"</tr>" +
									"</thead>" +
									"<tbody>" +
									"<tr>"+
									"<td style='padding:0;' ><p style='font-size:13px;text-align:center;font-family:initial;font-weight: bold;margin-bottom: 0px;margin-top: 0px;'><span style='font-weight: 500;font-style: italic;color: black;'>Affiliation No. :"+result.affilNo+"<br></span><b style='font-weight:500;'>"+result.address+"<br>Tel : "+result.contactNo+",  Website:"+result.website+"<br> Email :"+result.email+" <b></p></td>" +
									"</tr>" +
									"<tr>" +
									"<td style='text-align:center;'><h3 style='font-weight: bold;'>FEE RECEIPT</h3>" +
									"</td>" +
									"</tr>"+
									"</tbody>"+
								"</table>" +
								"<table  class='table feetable'>" +
								"<tbody>" +
								"<tr>" +
								"<td class='stu_details'>Academic Year</td>" +
								"<td class='stu_details'>:</td>" +
								"<td class='stu_details'>"+$("#haccyearname").val()+"</td>" +
								"<td class='stu_details'>Receipt No.</td>" +
								"<td class='stu_details'>:</td>" +
								"<td class='stu_details'>"+receiptno+"</td>" +
								"</tr>" +
								"<tr>" +
								"<td class='stu_details'>Student Name</td>" +
								"<td class='stu_details'>:</td>" +
								"<td class='stu_details'>"+$("#hstuName").val()+"</td>" +
								"<td class='stu_details'>Paid Date</td>" +
								"<td class='stu_details'>:</td>" +
								"<td class='stu_details'>"+result.billdate+"</td>" +
							"</tr>" +
							"<tr>" +
							"<td class='stu_details'>Admission No.</span></td>" +
							"<td class='stu_details'>:</td>" +
							"<td class='stu_details'>"+$("#haddmissionno").val()+"</td>" +
							"<td class='stu_details'>Term</td>" +
							"<td class='stu_details'>:</td>" +
							"<td class='stu_details'>"+terms+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td class='stu_details'>Class-Section</span></td>" +
						"<td class='stu_details'>:</td>" +
						"<td class='stu_details'>"+$("#hclassname").val()+"</td>" +
						"</tr>" +
								"</tbody>" +
								"</table>" +
								"<div class='fee-desc'>" +
								"<table class='table printtables' id='printtables' style='margin-bottom: 0px;'>" +
								"<tbody><tr>" +
								"<th>Sl.No</th><th>Particulars</th><th style='text-align: right;' class='border_left'>Amount</th></tr>" +
								"</tbody>" +
								"</table>" +
								"<div class='amtWords'></div>" +
								"<div class='row'>"+
								"<div class='col-md-12'>" +
								"<div class='col-md-6 leftpart'>" +
								"<span><strong>Payment Mode :</strong>"+result.paymentmode+"</span>"+
								"</div>" +
								"<div class='col-md-6 rightpart'></div>" +
								"</div></div>" +
								"</div>");
			$(".stu_details").css({
				"padding-bottom" : "0px",
				 "padding-top": "0px"
			});
			for(var i=0;i<result.FeeCollectionDetails.length;i++){	
				$("#Dialog #printtables tbody").append(
					"<tr style='vertical-align: text-top;'>" +
						"<td >"+result.FeeCollectionDetails[i].sno+"</td>" +
						"<td >"+result.FeeCollectionDetails[i].feename+"</td>" +
						"<td style='text-align: right;' class='border_left'>"+parseFloat(result.FeeCollectionDetails[i].totPaidAmt)+"</td>" +
				"</tr>");
			}
			
			$("#Dialog #printtables").append(
					"<tfoot>" +
					"<tr class='footborder'>" +
					"<td colspan='2' style='text-align: right;'><strong>TOTAL</strong></td>" +
					"<td class='actualAmt border_left' style='text-align: right;'><strong>"+result.actualamt+"</strong></td>" +
					"</tr>" +
					"<tr class='footborder'>" +
					"<td colspan='2' style='text-align: right;'><strong>Advance Amt.</strong></td>" +
					"<td class='advanceAmt border_left' style='text-align: right;'><strong>"+result.advanceamt+"</strong></td>" +
					"</tr>" +
					"<tr class='footborder'>" +
					"<td colspan='2' style='text-align: right;'><strong>Bal Amt.</strong></td>" +
					"<td class='balAmt border_left' style='text-align: right;'><strong>"+result.balanceamt+"</strong></td>" +
					"</tr>" +
					"<tr class='footborder' id='footborder'>" +
					"<td colspan='2' style='text-align: right;'><strong>Total Amt. Paid</strong></td>" +
					"<td class='border_left' style='text-align: right;'><strong class='totAmtpaid'></strong></td>" +
					"</tr>" +
					"</tfoot>");
			var fineAmt = 0.0;
			
			if(result.fineamt != 0.0){
				$("#Dialog #printtables tfoot tr:first").after(
					"<tr class='footborder'>" +
						"<td colspan='2' style='text-align: right;'><strong>Fine Amt.</strong></td>" +
						"<td class='fineAmt border_left' style='text-align: right;'><strong>"+result.fineamt+"</strong></td>" +
					"</tr>"	
				);
				fineAmt = parseFloat($(".fineAmt").text());
			}
				
			var totAmtpaid = parseFloat($(".actualAmt").text())+parseFloat($(".advanceAmt").text())+fineAmt;
			$(".totAmtpaid").text(amtpaid);
			
				$("#Dialog .amtWords").append("<p style='text-align : right;'>("+convertNumberToWords(amtpaid).toUpperCase()+")</p>");
				
				if(result.paymentmode == "Cheque" || result.paymentmode == "DD"){
					$(".rightpart").append("<br /><span><strong>"+result.paymentmode+" No. :</strong></span><span>"+result.chequeno+"</span><br />" +
							"<span><strong>Cheque Date :</strong></span><span>"+result.chequedate+"</span>");
					$(".leftpart").append("<span><strong>Bank Name :</strong></span><span>"+result.bankname+"</span>");
					
				}else if(result.paymentmode == "Card"){
					$(".rightpart").append("<br /><span><strong>Bank Name :</strong></span>"+result.bankname+"</span>");
				}else if(result.paymentmode == "Online"){
					$(".rightpart").append("<br /><span><strong>Txn. No. :</strong></span><span>"+result.chequeno+"</span><br />"+"<span><strong>Txn. Date :</strong></span><span>"+result.chequedate+"</span>");
					$(".leftpart").append("<span><strong>Bank Name :</strong></span><span>"+result.bankname+"</span>");
					
				}else if(result.paymentmode == "Card+Cash"){
					$(".rightpart").append("<br /><span><strong>Cash Amount :</strong></span><span>"+result.cashamt+"</span>"+"<span><strong>Bank Name :</strong></span><span>"+result.bankname+"</span>");
					$(".leftpart").append("<span><strong>Card Amount :</strong></span><span>"+result.cardamt+"</span>");
				}
				$(".leftpart").append("<br /><span><strong>Cashier :</strong></span><span>"+result.cashier+"</span>");
		}
	});
	
}

function GrandTotalcalculation(termPaying){
	
	 $("input[name='payingAmount']").each(function(){ 
		 var currentpaying = 0;
			if(!(isNaN($(this).val()))){
				currentpaying = Number($(this).val());
		}
			termPaying = parseFloat(termPaying) + parseFloat(currentpaying);
		});
	 $(".grandTotal").val(termPaying.toFixed(2)) ;
	 $(".totalPay").val(termPaying.toFixed(2));
	
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode != 46 && charCode > 31 
     && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function discountRadioButtonChanges(grandTotal){
	
		 $(".totalPay").val(grandTotal.toFixed(2));
		if($("input[name='discCalcu']").val()=="discPrcent"){
			$(".discount").hide();
			$(".checkde").show();
			
			$(".totalPay").val(grandTotal.toFixed(2));
		}else{
			$(".discount").show();
			$(".discount").val("");
			$(".checkde").hide();
			$(".totalPay").val(grandTotal.toFixed(2));
		}
	
}
function percentageCalculation(pointer){

	var actualAmt = Number($(".grandTotal").val());
	var percentNumber = Number(pointer.val());
	if(!(isNaN(pointer.val()))){
		percentNumber = Number(pointer.val());
	}
	
	var discountAmt  = Number(((percentNumber/100)*(actualAmt)));
	var amtAfterDisc= actualAmt - Math.ceil(discountAmt);
	
	$(".discount").val(discountAmt);
	if(percentNumber > 100){
		pointer.val("");
		$(".B").val("");
		$(".totalPay").val(actualAmt.toFixed(2));
	}else if(pointer.val()=="0"){
		$(".B").val("0");
		$(".totalPay").val(actualAmt.toFixed(2));
	}
	else if(pointer.val()=="0" && $(".B").val()=="0"){
		$(".totalPay").val(actualAmt.toFixed(2));
	}
	else{
		$(".B").val(Math.ceil(discountAmt));	
		$(".totalPay").val(amtAfterDisc.toFixed(2));
	}
	$(".totalPay").prop("readonly", true);

}

function convertNumberToWords(amount) {
    var words = new Array();
    words[0] = '';
    words[1] = 'One';
    words[2] = 'Two';
    words[3] = 'Three';
    words[4] = 'Four';
    words[5] = 'Five';
    words[6] = 'Six';
    words[7] = 'Seven';
    words[8] = 'Eight';
    words[9] = 'Nine';
    words[10] = 'Ten';
    words[11] = 'Eleven';
    words[12] = 'Twelve';
    words[13] = 'Thirteen';
    words[14] = 'Fourteen';
    words[15] = 'Fifteen';
    words[16] = 'Sixteen';
    words[17] = 'Seventeen';
    words[18] = 'Eighteen';
    words[19] = 'Nineteen';
    words[20] = 'Twenty';
    words[30] = 'Thirty';
    words[40] = 'Fourty';
    words[50] = 'Fifty';
    words[60] = 'Sixty';
    words[70] = 'Seventy';
    words[80] = 'Eighty';
    words[90] = 'Ninety';
    amount = amount.toString();
    var atemp = amount.split(".");
    var number = atemp[0].split(",").join("");
    var n_length = number.length;
    var words_string = "";
    if (n_length <= 9) {
        var n_array = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0);
        var received_n_array = new Array();
        for (var i = 0; i < n_length; i++) {
            received_n_array[i] = number.substr(i, 1);
        }
        for (var i = 9 - n_length, j = 0; i < 9; i++, j++) {
            n_array[i] = received_n_array[j];
        }
        for (var i = 0, j = 1; i < 9; i++, j++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                if (n_array[i] == 1) {
                    n_array[j] = 10 + parseInt(n_array[j]);
                    n_array[i] = 0;
                }
            }
        }
        value = "";
        for (var i = 0; i < 9; i++) {
            if (i == 0 || i == 2 || i == 4 || i == 7) {
                value = n_array[i] * 10;
            } else {
                value = n_array[i];
            }
            if (value != 0) {
                words_string += words[value] + " ";
            }
            if ((i == 1 && value != 0) || (i == 0 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Crores ";
            }
            if ((i == 3 && value != 0) || (i == 2 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Lakhs ";
            }
            if ((i == 5 && value != 0) || (i == 4 && value != 0 && n_array[i + 1] == 0)) {
                words_string += "Thousand ";
            }
            if (i == 6 && value != 0 && (n_array[i + 1] != 0 && n_array[i + 2] != 0)) {
                words_string += "Hundred and ";
            } else if (i == 6 && value != 0) {
                words_string += "Hundred ";
            }
        }
        words_string = words_string.split("  ").join(" ");
    }
    return words_string+" "+"only";
}

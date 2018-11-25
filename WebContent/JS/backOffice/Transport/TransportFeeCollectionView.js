$(document).ready(function(){
	count=2;

	$("#dd_cheque_date").datepicker({
		minDate:-180,
		maxDate:0,
		changeMonth : true,
		changeYear : true,
		dateFormat : "dd-mm-yy",
	});
	
	$("#back1").click(function(){
		window.location.href = "menuslist.html?method=transportFeeCollection&historylocId="+$("#historylocId").val()
		+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val();
	});
	
	$(".selectmonth.Paid").change(function(){
		if($(this).attr("class").split(" ")[1]=="Paid")
		{
		$(this).prop("checked",false);
		}
	});
	$(".selectmonth.Paid").each(function(){
		if($(this).attr("class").split(" ")[1]=="Paid")
		{
			count++;
		}
	});
	
	var value1=2;
	var value2=$(".payablefee tbody tr").length;
	
	/*$(".print").each(function(){
		if($(this).attr("name").split(",")[0]=="Paid")
		{
			value1++;
			$(this).show(); 
		}else{
			$(this).hide(); 
		}
	});*/
	
	$('input[type="radio"]').click(function()
	 {
		$('.errormessagediv').hide();
		
		 if ($(this).hasClass("receiptno")) {
			 $(this).removeClass("receiptno");
			 $(this).prop('checked', false);
			 $(".selectmonth").closest("tr").prev("tr").find(".selectmonth").prop("disabled",false)
		 } else { 
			 $(this).prop('checked', true);
			 $(this).addClass("receiptno");
			 $(".selectmonth").closest("tr").prev("tr").find(".selectmonth").prop("disabled",true)
		 };
	});
	
	if(value1==parseInt(value2)){
		$(".pay").hide();
	}else{
		$(".pay").show();
	}
	
	$(".view").each(function(){
		if($(this).attr("name").split(",")[0]=="Paid")
		{
			$(this).show(); 
		}else{
			$(this).hide(); 
		}
	});
	
	$("#paymentMode").change(function(){
		
		$("#dd_cheque_bank").val("");
		$("#paymentParticulars").val("");
		$("#dd_cheque_date").datepicker("hide");
		$("#dd_cheque_date").val("");
		
		if($(this).val()=="cash" || $(this).val()==""){
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").hide();
		}
		else{
			$("#dd_cheque_date").val("");
			$("#dd_cheque_date").datepicker({
				minDate:-180,
				maxDate:0,
				changeMonth : true,
				changeYear : true,
				dateFormat : "dd-mm-yy",
			});
			
			$("#paymentParticulars,#dd_cheque_date,#dd_cheque_bank").show();
			$("#paymentParticulars").attr("placeholder","Enter "+$(this).val()+" number.");
		}
	});
	
	
	$(".table.allstudent tbody tr:nth-child("+count+")").find(".refund").show();
	 
	$(".print").click(function(){
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
		printTermId=$(this).attr("name").split(",")[1];
		printMonth=$(this).attr("name").split(",")[2];
		printAmount=$(this).attr("name").split(",")[3];
		printReceptNo=$(this).attr("name").split(",")[4];
		printPaidDate=$(this).attr("name").split(",")[5];
		
		$("#printDialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feeCollectionDetailsPrint($(this));
		$("#printDialog").dialog('open');
		$(".ui-dialog-titlebar").text("TRANSPORT FEE FOR THE YEAR OF "+$("#hiddenAcademicYearName").val());
	});
	
	
	$(".print1").click(function(){
		
		if($("input[name='receiptno']:checked").val()!=undefined && $("input[name='receiptno']:checked").val()!=""){
			$(".ui-dialog-buttonset button:nth-child(1)").show();
			 
			feeCollectionDetailsPrintHistory($(this));
			$("#printDialog").dialog('open');
			$(".ui-dialog-titlebar").text("TRANSPORT FEE FOR THE YEAR OF "+$("#hiddenAcademicYearName").val());
		}else{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any Record for Print ");
		}
		
	});
	
	$(".view").click(function(){
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		
		viewTermId=$(this).attr("name").split(",")[1];
		viewMonth=$(this).attr("name").split(",")[2];
		viewAmount=$(this).attr("name").split(",")[3];
		viewReceptNo=$(this).attr("name").split(",")[4];
		viewPaidDate=$(this).attr("name").split(",")[5];
		
		$("#viewDialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		feeCollectionDetailsView($(this));
		$("#viewDialog").dialog('open');
		$(".ui-dialog-titlebar").text("TRANSPORT FEE FOR THE YEAR OF "+$("#hiddenAcademicYearName").val());
	});
	
	
	
	$(".pay").click(function(){
		 if($(".selectmonth.Not.Paid:checked").length>0){
				$("#mainDialog .paymentMode").show();
			 $('.errormessagediv').hide();
			// $("#myDialog").empty();
			 termname=$(this).closest("tr").find(".heading").text();
			 feeCollectionDetails($(this));
		$("#mainDialog").dialog('open');
		$(".ui-dialog-titlebar").text($(".feeName").text());
		$(".ui-dialog-buttonset button:nth-child(1)").show();
		 }
		 else{
			 $('.errormessagediv').show();
				$('.validateTips').text("Select Any Record for Payement ");
		 }
		
		
	});
	 
	$("#mainDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    width     :500,
	    modal     : true,
	    buttons   : {
	              'Pay' : function() {
	            	  
	            	  var flag=true;
		            	 if($("#paymentMode").val() !="" && $("#paymentMode").val() !=undefined){
		            		 if($("#paymentMode").val() !="cash"){
		            			 flag=false;
		            			 message="Select Payment Particulars type.";
		            		
		            		 if($("#paymentParticulars").val().length>0){
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Enter DD/Cheque No.";
		            			 getError(message,"#paymentParticulars");
		            			 return false;
		            		 }
		            		 if($("#dd_cheque_bank").val().length>0){
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Enter Bank Name.";
		            			 getError(message,"#dd_cheque_bank");
		            			 return false;
		            		 }
		            		 if($("#dd_cheque_date").val().length>0){
		            			 flag=true;
		            		 }
		            		 else{
		            			 flag=false;
		            			 message="Select DD/Cheque Date.";
		            			 getError(message,"#dd_cheque_date");
		            			 return false;
		            		 }
		            	 }
		            		
		            if(isNaN($(".payingAmount").val()) || $(".payingAmount").val()==0){
		            			 flag=false;
		            			 message="Enter Number greater than zreo.";
		            }
	            	  
	            	  var termid=[];
	            	  var monthName=[];
	            	  var monthlyAmount=[];
	            	  $(".selectmonth.Not.Paid:checked").each(function(){
	            		  termid.push($(this).val().split(",")[0]);
	            		  monthName.push($(this).val().split(",")[1]);
	            		  monthlyAmount.push($(this).val().split(",")[2]);
	            	  });
					var datalist={						
							"addmissionNo":$("#hstudentid").val(),
							"termid":termid.toString(),
							'accodemicyear':$("#haccYear").val(),
							"totalAmount":$(".totalAmount").val(),
							"monthlyAmount":monthlyAmount.toString(),
							"monthName":monthName.toString(),
							"paymentMode":$("#paymentMode").val(),
							"dd_cheque_bank":$("#dd_cheque_bank").val(),
							"dd_cheque_date":$("#dd_cheque_date").val(),
							"paymentParticulars":$("#paymentParticulars").val(),
							"payingAmount":$(".payingAmount").val(),
							"loc_Id"  :  $("#loc_Id").val()
					};
					if(flag){
					$.ajax({
						type : "POST",
						url : "feecollection.html?method=saveTransportFeeCollection",
						data :datalist,
						beforeSend: function(){
							$("#loder").show();
						},
						success : function(data) {
							var result = $.parseJSON(data);
							
							if(result.status=="true"){
								$("#loder").hide();
								$('.successmessagediv').show();
								$('.sucessmessage').text("Fee Submit progressing... ");
							setTimeout(function(){
								 location. reload();
							 },2000);
							}else{
								$("#loder").hide();
								$('.errormessagediv').show();
								$('.validateTips').text("Fee collection not done.Try later");
							}
						}
					});
					 $("#myDialog").empty();
	            	  $(this).dialog('close');
		          }
					else{
	            		 getError(message,"#paymentMode");
					}
		        }
	            	  else{
		            		 $("#paymentMode").focus();
		            		 $("#paymentMode").css({
		            			 "border-color":"#f00"
		            		 });
		            		 $('.errormessagediv').show();
		            		 $('.errormessagediv').delay(2000).fadeOut();
								$('.validateTips').text("Select Payment Mode type."); 
		            	 }
	              },
	              'Close' : function() {
	            	  $("#myDialog").empty();
	                  $(this).dialog('close');
	              }
	                }
	});

	$(".refund").click(function(){
		$("#Dialog").empty();
		termname=$(this).closest("tr").find(".heading").text();
		$("#Dialog").append("<p>Are You Sure to Refund?</p>");
		$("#Dialog").dialog('open');
		refundTermId=$(this).attr("id").split(",")[0];
		refundTermMonth=$(this).attr("id").split(",")[1];
		refundAmount=$(this).attr("id").split(",")[2];
		refrecieptNo=$(this).attr("id").split(",")[3];
		$(".ui-dialog-titlebar").text("Refund "+termname+" "+$(this).attr("id").split(",")[1]);
	});
	
	
	$("#printDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    modal     : true,
	    width     :500,
	    buttons   : {
	              'Print' : function() {
 
	            	   var a=$("#printing-css").val();
	            	   var b= document.getElementById("printDialog").innerHTML;
	            	   var abd='<style>' + a +'</style>' + b;
	            	    var frame1 = $('<iframe />');
		    	        frame1[0].name = "frame1";
		    	        frame1.css({ "position": "absolute", "top": "-1000000px" });
		    	        $("body").append(frame1);
		    	        var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
		    	        frameDoc.document.open();
		    	        //Create a new HTML document.
		    	        frameDoc.document.write('<html><head><title>eCampus Pro</title>');
		    	        //Append the external CSS file.
		    	      
		    	        frameDoc.document.write('</head><body>');
		    	      
		    	        //Append the DIV contents.
		    	        frameDoc.document.write('<p>School Copy</p>');
		    	        frameDoc.document.write('<div class="schoolId">'+abd+'</div>');

						frameDoc.document.write('<p>Student Copy</p>');
						frameDoc.document.write('<div class="studentId">'+abd+'</div>');
						
						
		    	        frameDoc.document.write('</body></html>');
		    	        frameDoc.document.close();
		    	        setTimeout(function () {
		    	            window.frames["frame1"].focus();
		    	            window.frames["frame1"].print();
		    	            frame1.remove();
		    	        }, 100);
		    	        
					 $("#printDialog").empty();
	            	  $(this).dialog('close');
	              },
	              'Close' : function() {
	            	  $("#printDialog").empty();
	                  $(this).dialog('close');
	              }
	         }
	 });
	
	$("#viewDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    modal     : true,
	    width     :500,
	    buttons   : {
	              'Close' : function() {
	            	  $("#viewDialog").empty();
	                  $(this).dialog('close');
	              }
	         }
	 });
	
	$("#Dialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    modal     : true,
	    width     :300,
	    buttons   : {
	              'Refund' : function() {
 
					var datalist={ 
							"addmissionNo":$("#hstudentid").val(),
							"termid":refundTermId,
							'accodemicyear':$("#haccYear").val(),
							"monthlyAmount":refundAmount,
							"monthName":refundTermMonth,
							"refrecieptNo":refrecieptNo,
							"refundstatus":"rf",
					}; 
					$.ajax({
						type : "POST",
						url : "feecollection.html?method=refundTransportFeeCollection",
						data :datalist,
						beforeSend: function(){
							$("#loder").show();
						},
						success : function(data) {
							var result = $.parseJSON(data);
 
							if(result.status=="true"){ 
								$("#loder").hide();
								$('.successmessagediv').show();
								$('.sucessmessage').text("Fee Refund progressing... ");
 
							setTimeout(function(){ 
								 location.reload(); 
							 },3000); 
							}else{
								$('.errormessagediv').show();
								$('.validateTips').text("Fee Refunding not done.Try later");
							}
						}
					});
					 $("#Dialog").empty();
	            	  $(this).dialog('close');
	              },
	              'Close' : function() {
	            	  $("#Dialog").empty();
	           
	                  $(this).dialog('close');
	              }
	         }
	});
	
 
	
	$(".selectmonth.Not.Paid").change(function(){
		 
		if($(this).closest("tr").prev("tr").find(".selectmonth.Not.Paid").prop("checked")!= undefined){
		if($(this).closest("tr").prev("tr").find(".selectmonth.Not.Paid").prop("checked")==true ){
			if($(this).prop("checked")==true){
			  $(this).prop("checked",true);
			}
		else{
			if($(this).closest("tr").next("tr").find(".selectmonth.Not.Paid").prop("checked")==true )
			  $(this).prop("checked",true);
			else
			  $(this).prop("checked",false);
		 }
		}
		else{
			$(this).prop("checked",false);
		 }
		}
		else if($(".selectmonth:checked").length>=1)
		{
			$(this).prop("checked",true);
		 }
		 
	});
	
	
});
function feeCollectionDetails(pointer){
	 
	termId=[];
	var advance=0.0;
	var balance=0.0;
	var concession=0.0;
	$(".selectmonth:checked").each(function(){
		var list=$(this).attr("value");
		termId.push(list.split(",")[0]);
	});
	
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=getAdvanceOrBalanceForTransportFee',
		data:{
			"stuId":$("#hstudentid").val(),
			"termId":termId.toString(),
			"acyId":$("#haccYear").val()
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			 
			advance=result.data[0].advanceAmt;
			balance=result.data[0].dueAmt;
			concession=result.data[0].concession;
		}
	});
	 i=1;
			var totalAmount=0.0;
			$("#myDialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' width='100%' id='allstudent'>" +
					"<tr><th>Sl.No.</th>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			$(".selectmonth.Not.Paid:checked").each(function(){
				
				
				$("#myDialog #allstudent").append("<tr id=rowid"+i+">" +
						"<td>"+i+"</td>" +
						"<td style='text-align:left !important;padding-left:5px;'>"+$(this).closest("tr").find(".heading").text()+" "+$(this).closest("tr").find(".transportMonth").text()+" Fee</td>" +
						"<td ><input type='text' class='Feeamount' name='transportfee' value='"+$(this).closest("tr").find(".transportfee").text()+"' readonly='readonly' style='text-align:right;' /></td>" +
						"</tr>");
				i++;
			});
			$("#myDialog #allstudent").append("<tr id=rowid"+i+">" +
					"<td>"+i+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Concession</td>" +
					"<td ><input type='text' class='concession' name='concession' value='"+concession+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			
			$("#myDialog #allstudent").append("<tr id=rowid"+i+">" +
					"<td>"+i+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Advance</td>" +
					"<td ><input type='text' class='advance' name='advanceamt' value='"+advance.toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
		
			$("#myDialog #allstudent").append("<tr id=rowid"+(i+1)+">" +
					"<td>"+(i+1)+"</td>" +
					"<td style='text-align:left !important;padding-left:5px;'>Dues</td>" +
					"<td ><input type='text' class='Feeamount' name='dueamt' value='"+balance.toFixed(2)+"' readonly='readonly' style='text-align:right;' /></td>" +
					"</tr>");
			$("#myDialog #allstudent").append("<tr>" +
					"<th></th>" +
					"<th>Total</th>" +
					"<th ><input type='text' class='totalAmount' name='totalAmount' value='' readonly='readonly' style='text-align:right;background-color:rgba(255, 224, 0, 0.22);' /></th>" +
					"" +
					"</tr>");
			
				$("#myDialog #allstudent tbody").find(".Feeamount").each(function(){
					totalAmount=totalAmount+parseFloat($(this).val());
				});
			 
				totalAmount=totalAmount-concession;
				 
				totalAmount=totalAmount-Number($("#myDialog #allstudent tbody").find(".advance").val());
			 
				$("#myDialog #allstudent").append("<tr>" +
						"<th></th>" +
						"<th>Paying</th>" +
						"<th ><input type='text' class='payingAmount' name='payingAmount' value='"+totalAmount.toFixed(2)+"'  style='text-align:right;' onkeypress='return CheckIsNumeric(event);' /></th>" +
						"" +
						"</tr>");
				$("#myDialog #allstudent tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
}
function feeCollectionDetailsPrint(pointer){

	var city=$("#hiddenaddress1").val().split(",")[0];
	var state=$("#hiddenaddress1").val().split(",")[1];
	var country1=$("#hiddenaddress1").val().split(",")[2];
	var country=country1.split("-")[0];
	var pincode=country1.split("-")[1];
	
	 advance1=0.0;
	 balance1=0.0;
	 concession1=0.0;
	 paidAmount1=0.0;
	 paymentmode1="";
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=transportFeeCollectionDetailsPrint',
		data:{
			"stuId":$("#hstudentid").val(),
			"termId":printTermId,
			"acyId":$("#haccYear").val(),
			"printReceptNo":printReceptNo
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			 
			advance1=result.data[0].advanceAmt;
			balance1=result.data[0].dueAmt;
			concession1=result.data[0].concession;
			paidAmount1=result.data[0].paidAmt;
			paymentmode1=result.data[0].feeType;
		}
	});
	
			var totalAmount=0.0;
			$("#printDialog").append("<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:17px !important;'>"+$("#hiddenSchoolName").val()+"</h3>" +
					"<h3 style='text-align:center;margin-top:6px;margin-bottom:-3px;font-size:15px !important;'>"+$("#hiddenaddress").val()+" - "+pincode+"</h3>" +
					"<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:15px !important;'>City - "+city+", State - "+state+", Country - "+country+"</h3>" +
					"<hr>" +
					"<span>AdmnNo:</span><span>"+$("#haddmissionno").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp"+"<span class='paiddate'>Paid Date:</span><span>"+printPaidDate+"</span>" +
					"<br>" +
					"<span>Name:</span>"+$("#hstuName").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+"<span  class='receipt'>Receipt No:</span><span>"+printReceptNo+"</span>" +
					"<br>" +
					"<span>Class & Div:</span>"+$("#hclassname").val() +"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+"<span class='printpaymentmode'><span class='paymentmode' style='margin-left:-26px;'>Payment Mode:</span><span>"+paymentmode1+"</span></span>" +
					"<hr>" +
					"<h3 style='text-align:center;margin-top:-7px;'>TRANSPORT FEE RECEIPT</h3>" +
					"<div style='text-align:center;margin-top:-4px;margin-bottom:-6px;'>"+termname+" "+printMonth+" Fee</div>"+
					"<hr>");
			
			$("#printDialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%;margin-top:-7px;' id='printtable'>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;font-weight:normal;'>"+termname+" "+printMonth+" Fee</th>" +
					"<th style='text-align:right;'><input type='text' class='printAmount' name='printAmount' value='"+printAmount+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"</tr>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;font-weight:normal;'>Concession</th>" +
					"<th style='text-align:right;'><input type='text' class='concessionAmount' name='concessionAmount' value='"+concession1+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"</tr>");
		
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;font-weight:normal;'>Advance</th>" +
					"<th style='text-align:right;'><input type='text' class='advanceAmount' name='advanceAmount' value='"+advance1+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					 "</tr>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;font-weight:normal;'>Dues</th>" +
					"<th style='text-align:right;'><input type='text' class='duesAmount' name='duesAmount' value='"+balance1+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					 "</tr>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;'> Total</th>" +
					"<th style='text-align:right;'><input type='text' class='totalAmount' name='totalAmount' value='"+totalAmount+"' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					 "</tr>");
			
			$("#printDialog #printtable tbody").find(".Feeamount").each(function(){
				totalAmount=totalAmount+parseFloat($(this).val());
			});
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:left;'> Paid Amount</th>" +
					"<th style='text-align:right;'><input type='text' class='paidAmount1' name='paidAmount1' value='"+paidAmount1+"' readonly='readonly' readonly='readonly' style='text-align:right;width:100px;' /></th>" +
					"</tr>");
				
				$("#printDialog #printtable tbody").find(".totalAmount").val(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
}

function feeCollectionDetailsPrintHistory(pointer){

	var city=$("#hiddenaddress1").val().split(",")[0];
	var state=$("#hiddenaddress1").val().split(",")[1];
	var country1=$("#hiddenaddress1").val().split(",")[2];
	var country=country1.split("-")[0];
	var pincode=country1.split("-")[1];
	
	
	
	var printhistoryReceptNo=$(".receiptno:checked").val();
	var termId=[];
	var totalAmount=0.0,historypaidAmount=0.0;
	var historypaidDate="";
	termId.push(pointer.attr("id"));
	 advance1=0.0;
	 balance1=0.0;
	 concession1=0.0;
	 paymentmode1="";
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeCollectionDetailsPrintHistory',
		data:{
			"stuId":$("#hstudentid").val(),
			"acyId":$("#haccYear").val(),
			"printReceptNo":printhistoryReceptNo,
			"termids":termId.toString(),
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			 
			advance1=result.data[0].advanceAmt;
			balance1=result.data[0].dueAmt;
			concession1=result.data[0].concession;
			historypaidAmount=result.data[0].paidAmt;
			paymentmode1=result.data[0].feeType;
			historypaidDate=result.data[0].paidDate;
			totalAmount=result.data[0].totalAmount;
		}
	});
	
			
			$("#printDialog").append("<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:17px !important;'><strong>"+$("#hiddenSchoolName").val()+"</strong></h3>" +
					"<h1 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:12px !important;'>(Branch - "+$("#hiddenbranchname").val()+" )</h1>" +
					"<h3 style='text-align:center;margin-top:6px;margin-bottom:-3px;font-size:13px !important;'>"+$("#hiddenaddress").val()+" - "+pincode+"</h3>" +
					"<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:13px !important;'>City - "+city+", State - "+state+", Country - "+country+"</h3>" +
					"<hr style='margin-bottom:10px;margin-top:10px;'>" +
					"<div class='table_info'>"+
					"<table class='table desctable'>" +
					"<tbody>" +
					"<tr>" +
					"<td>AdmnNo</td>"+
					"<td>:</td>"+
					"<td>"+$("#haddmissionno").val()+"</td>"+
					"<td>Paid Date</td>"+
					"<td>:</td>"+
					"<td>"+historypaidDate+"</td>"+
					"</tr>" +
					"<tr>" +
					"<td>Name</td>"+
					"<td>:</td>"+
					"<td>"+$("#hstuName").val()+"</td>"+
					"<td>Receipt No</td>"+
					"<td>:</td>"+
					"<td>"+printhistoryReceptNo+"</td>"+
					"</tr>" +
					"<tr>" +
					"<td>Class & Div</td>"+
					"<td>:</td>"+
					"<td>"+$("#hclassname").val()+"</td>"+
					"<td>Payment Mode</td>"+
					"<td>:</td>"+
					"<td>"+paymentmode1+"</td>"+
					"</tr>" +
					"</tbody>" +
					"</table>"+
					"</div>"+
					"<hr>" +
					"<h3 style='text-align:center;margin-top:-7px;'><strong>TRANSPORT FEE RECEIPT</strong></h3>"
					);
			
			$("#printDialog").append("<div class='tbldiv' style='width:90%;margin:auto;margin-top:15px;'><table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%;margin-top:-7px;' id='printtable'><thead><tr>" +
					"<th style='text-align:center !important;'>Sl No.</th>" +
					"<th style='text-align:left !important'>Particulars</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr></thead><tbody></tbody><tfoot></tfoot>" +
					"</table></div>");
			
			
			$.ajax({
				type:'POST',
				url:'feecollection.html?method=getTermnameAndAmountPrintHistory',
				data:{
					"stuId":$("#hstudentid").val(),
					"acyId":$("#haccYear").val(),
					"printReceptNo":printhistoryReceptNo,
				},
				async:false,
				success:function(response){
					var result=$.parseJSON(response);
					len = result.data.length;
					for(var i=0;i<result.data.length;i++){
						$("#printDialog #printtable").append("<tr>" +
								"<th style='text-align:center;font-weight:normal;width:10%'>"+(i+1)+"</th>" +
							"<th style='text-align:left;font-weight:normal;padding: 5px'>"+result.data[i].termName+" "+result.data[i].monthName+" Fee</th>" +
							"<th style='text-align:right;padding-right: 5px'>"+result.data[i].totalAmount+"</th>" +
							"</tr>");
					 }
				}
			});
			
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+1)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Concession</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+concession1+"</th>" +
					"</tr>");
		
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+2)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Advance</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+advance1+"</th>" +
					 "</tr>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+3)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Dues</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+balance1+"</th>" +
					 "</tr>");
			
			$("#printDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Total</th>" +
					"<th style='text-align:right;padding-right: 5px' class='totalAmount'>"+parseFloat(totalAmount).toFixed(2)+"</th>" +
					 "</tr>");
			
			 
			$("#printDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Paid Amount</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+parseFloat(historypaidAmount).toFixed(2)+"</th>" +
					"</tr>");
				
				$("#printDialog #printtable tbody").find(".totalAmount").text(parseFloat(totalAmount).toFixed(2));
				$(".Feeamount").each(function(){
					$(this).val(parseFloat($(this).val()).toFixed(2));
				});
}

function feeCollectionDetailsView(pointer){
  
	var city=$("#hiddenaddress1").val().split(",")[0];
	var state=$("#hiddenaddress1").val().split(",")[1];
	var country1=$("#hiddenaddress1").val().split(",")[2];
	var country=country1.split("-")[0];
	var pincode=country1.split("-")[1];
	
	var advance1=0.0;
	var balance1=0.0;
	var concession1=0.0;
	var paidAmount1=0.0;
	var paymentmode1="";
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=transportFeeCollectionDetailsPrint',
		data:{
			"stuId":$("#hstudentid").val(),
			"termId":viewTermId,
			"acyId":$("#haccYear").val(),
			"printReceptNo":viewReceptNo
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			 
			advance1=result.data[0].advanceAmt;
			balance1=result.data[0].dueAmt;
			concession1=result.data[0].concession;
			paidAmount1=result.data[0].paidAmt;
			paymentmode1=result.data[0].feeType;
		}
	});
 
			var totalAmount=0.0;
			/*$("#viewDialog").append("<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:17px !important;'>"+$("#hiddenSchoolName").val()+"</h3>" +
					"<h1 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:12px !important;'>(Branch - "+$("#hiddenbranchname").val()+" )</h1>" +
					"<h3 style='text-align:center;margin-top:6px;margin-bottom:-3px;font-size:15px !important;'>"+$("#hiddenaddress").val()+" - "+pincode+"</h3>" +
					"<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:15px !important;'>City - "+city+", State - "+state+", Country - "+country+"</h3>" +
					"<hr>" +
					"<span style='margin-left: 3%;'>AdmnNo:</span><span>"+$("#haddmissionno").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp"+"<span  style='margin-left: 35%;'>Paid Date:</span><span>"+viewPaidDate+"</span>" +
					"<br>" +
					"<span style='margin-left: 3%;'>Name:</span>"+$("#hstuName").val()+"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+"<span  style='margin-left: 32%;'>Receipt No:</span><span>"+viewReceptNo+"</span>" +
					"<br>" +
					"<span style='margin-left: 3%;'>Class & Div:</span>"+$("#hclassname").val() +"</span>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp"+"<span  style='margin-left: 15%;'><span class='paymentmode'>Payment Mode:</span><span>"+paymentmode1+"</span></span>" +
					"<hr>" +
					"<h3 style='text-align:center;margin-top:-7px;'>TRANSPORT FEE RECEIPT</h3>" +
					"<div style='text-align:center;margin-top:-4px;margin-bottom:-6px;'>"+termname+" "+viewMonth+" Fee</div>"+
					"<hr>");*/
			
			$("#viewDialog").append("<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:17px !important;'><strong>"+$("#hiddenSchoolName").val()+"</strong></h3>" +
					"<h1 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:12px !important;'>(Branch - "+$("#hiddenbranchname").val()+" )</h1>" +
					"<h3 style='text-align:center;margin-top:6px;margin-bottom:-3px;font-size:13px !important;'>"+$("#hiddenaddress").val()+" - "+pincode+"</h3>" +
					"<h3 style='text-align:center;margin-top:4px;margin-bottom:-6px;font-size:13px !important;'>City - "+city+", State - "+state+", Country - "+country+"</h3>" +
					"<hr style='margin-bottom:10px;margin-top:10px;'>" +
					"<div class='table_info'>"+
					"<table class='table desctable'>" +
					"<tbody>" +
					"<tr>" +
					"<td>AdmnNo</td>"+
					"<td>:</td>"+
					"<td>"+$("#haddmissionno").val()+"</td>"+
					"<td>Paid Date</td>"+
					"<td>:</td>"+
					"<td>"+viewPaidDate+"</td>"+
					"</tr>" +
					"<tr>" +
					"<td>Name</td>"+
					"<td>:</td>"+
					"<td>"+$("#hstuName").val()+"</td>"+
					"<td>Receipt No</td>"+
					"<td>:</td>"+
					"<td>"+viewReceptNo+"</td>"+
					"</tr>" +
					"<tr>" +
					"<td>Class & Div</td>"+
					"<td>:</td>"+
					"<td>"+$("#hclassname").val()+"</td>"+
					"<td>Payment Mode</td>"+
					"<td>:</td>"+
					"<td>"+paymentmode1+"</td>"+
					"</tr>" +
					"</tbody>" +
					"</table>"+
					"</div>"+
					"<hr>" +
					"<h3 style='text-align:center;margin-top:-7px;'><strong>TRANSPORT FEE RECEIPT</strong></h3>"
					);
			
			$("#viewDialog").append("<div class='tbldiv' style='width:90%;margin:auto;margin-top:15px;'><table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%;margin-top:-7px;' id='printtable'><thead><tr>" +
					"<th style='text-align:center !important;'>Sl No.</th>" +
					"<th style='text-align:left !important'>Particulars</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr></thead><tbody></tbody><tfoot></tfoot>" +
					"</table></div>");
			
			
			
			/*$("#viewDialog").append("<table class='"+pointer.attr("id")+"' name='"+pointer.closest("tr").find(".heading").attr("id")+"' style='width:100%;margin-top:-7px;' id='printtable'>" +
					"<th style='text-align:left !important'>Fee Description</th>" +
					"<th style='text-align:right;'>Fee Amount</th>"+
					"</tr>" +
					"</table>");*/
			
			/*$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+1)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Concession</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+concession1+"</th>" +
					"</tr>");
		
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+2)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Advance</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+advance1+"</th>" +
					 "</tr>");
			
			$("#printDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>"+(len+3)+"</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Dues</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+balance1+"</th>" +
					 "</tr>");
			
			$("#printDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Total</th>" +
					"<th style='text-align:right;padding-right: 5px' class='totalAmount'>"+parseFloat(totalAmount).toFixed(2)+"</th>" +
					 "</tr>");
			
			 
			$("#printDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Paid Amount</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+parseFloat(historypaidAmount).toFixed(2)+"</th>" +
					"</tr>");*/
			
			
			$("#viewDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>1</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>"+termname+" "+viewMonth+" Fee</th>" +
					"<th style='text-align:right;padding-right: 5px' class='feeamt'>"+parseFloat(viewAmount).toFixed(2)+"</th>" +
					"</tr>");
			
			$("#viewDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>2</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Concession</th>" +
					"<th style='text-align:right;padding-right: 5px' class='lessamt'>"+parseFloat(concession1).toFixed(2)+"</th>" +
					"</tr>");
		
			$("#viewDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>3</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Advance</th>" +
					"<th style='text-align:right;padding-right: 5px' class='lessamt'>"+parseFloat(advance1).toFixed(2)+"</th>" +
					 "</tr>");
			
			$("#viewDialog #printtable").append("<tr>" +
					"<th style='text-align:center;font-weight:normal;'>4</th>" +
					"<th style='text-align:left;font-weight:normal;padding: 5px'>Dues</th>" +
					"<th style='text-align:right;padding-right: 5px' class='feeamt'>"+parseFloat(balance1).toFixed(2)+"</th>" +
					 "</tr>");
			
			$("#viewDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Total</th>" +
					"<th style='text-align:right;padding-right: 5px' class='totalAmount'>"+totalAmount+"</th>" +
					 "</tr>");
			
			$("#viewDialog #printtable tbody").find(".feeamt").each(function(){
				totalAmount=totalAmount+parseFloat($(this).text());
			});
			var lessAmt = 0.0;
			$("#viewDialog #printtable tbody").find(".lessamt").each(function(){
				lessAmt=lessAmt+parseFloat($(this).text());
			});
			
			totalAmount = totalAmount - lessAmt;
			
			$("#viewDialog #printtable tfoot").append("<tr>" +
					"<th style='text-align:right;padding-right: 5px' colspan='2'> Paid Amount</th>" +
					"<th style='text-align:right;padding-right: 5px'>"+parseFloat(paidAmount1).toFixed(2)+"</th>" +
					"</tr>");
				
				$("#viewDialog #printtable tfoot").find(".totalAmount").text(parseFloat(totalAmount).toFixed(2));
				
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

function HideError(pointer) 
{
	$('.errormessagediv').hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
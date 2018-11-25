$(document).ready(function(){
	totalFeesAmount = 0;
	feeCode = [];
	$(".TermTable").hide();
	$(".partialTable").hide();
	$(".tab").hide();
	$("#add").show();
	$("#edit").hide();
	$("#cancel").hide();
	var flag = false; 

	var StudentImage=$("#hiddenImage").val();
	if(StudentImage!=""){
		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	getConcessionTypes();

	$("#add").click(function(){
		
		$(".termName").val("");
		if(!checkFeeSetup()){
			$(".errormessagediv").show();
			$(".validateTips").text("Fee Is Not Setted For This Class"); 
			$(".errormessagediv").delay(3000).fadeOut();
			return false;
		}
		else if($(".commontable tbody tr").length > 0){
			
			var conType = $(".commontable tbody tr").find('.selectFee').attr("id").split(",")[5];
			var conTypeName = $(".commontable tbody tr").find('.selectFee').attr("id").split(",")[6];
			var conTypeBy = $(".commontable tbody tr").find('.selectFee').attr("id").split(",")[7];
			
			
			var pointer = $('.termName').find('option[value="'+conType+','+conTypeName+','+conTypeBy+'"]');
			var pointere = $('.termName').find('option[value=""]');
			$('.termName option').not(pointer).not(pointere).remove();
			
		}
		$("#dialog").dialog("open");
		$("#dialog").dialog("option",'title','Add Fee Concession');
	});
	
	$("#scholorshipAmount").keypress(function(e){
		
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number"); 
			$(".errormessagediv").delay(2000).fadeOut();
			return false;
		}
		
	});
	
	$("#dialog").dialog({
		height: 'auto',
		width: 'auto',
		autoOpen: false,
		modal: false,
		resizable: false, 
		position: ['center', 'top+58'],
		buttons   : {
			'Save' : function() {
				
				var concessionId = $("#termId option:selected").val().split(",")[0];
				var concessiontype = $("#termId option:selected").val().split(",")[1];
				var concessionBy = $("#termId option:selected").val().split(",")[2];
				var concessionName = $("#termId option:selected").html();
				var studentId = $("#hiddenstudentid").val();
				var class_Id = $("#classidhidden").val();
				var acaYear = $("#hiddenaccyid").val();
				var locId = $("#hiddenlocid").val();
				var sectionId = $("#hiddensectionid").val();
				var hiddentrmcode = null;
				var hiddenfeecode = null;
				var hiddenacyId = null;
				var hiddencontype = null;
				var hiddenConId = null;
				var hiddenConBy = null;
				
				if($("#termId option:selected").val() == "" || $("#termId option:selected").val() == undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Fee Concession Name");
					$(".errormessagediv").delay(3000).slideUp();
					flag = false;
				}else{

					if(concessionId == "1"){
						con_amount = [];
						con_percent = [];
						termCode = [];
						feeCode = [];

						var amt = getTotalClassFees();
						con_amount.push(amt);
						con_percent.push(0);
						termCode.push("all");
						feeCode.push("all");
						flag = true;
					}
					else if(concessionId == "2"){
						con_amount = [];
						con_percent = [];
						termCode = [];
						feeCode = [];

						con_amount.push($("#scholorshipAmount").val());
						con_percent.push(0);
						termCode.push("all");
						feeCode.push("all");
						
						if($("#scholorshipAmount").val() >= getTotalAmountForClass()){
							$(".errormessagediv").show();
							$(".validateTips").text("Entered Amount Should Be Less Than Total Amount");
							$(".errormessagediv").delay(3000).slideUp();
							flag = false;
						}else if($("#scholorshipAmount").val() == "" || $("#scholorshipAmount").val() == undefined){
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Concession Amount");
							$(".errormessagediv").delay(3000).slideUp();
							flag = false;
							
						}else if($("#scholorshipAmount").val() == 0){
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Valid Amount");
							$(".errormessagediv").delay(3000).slideUp();
							flag = false;
						}
						else{
							flag = true;
						}

					}else if(concessionId == "3"){

						$(".tb tbody tr").each(function(){

							con_amount = [];
							con_percent = [];
							termCode = [];
							feeCode = [];

							$(".tb tbody input[type=checkbox]:checked").each(function () {
								termCode.push($(this).attr('id').split(",")[0]);
								if($(this).closest('tr').find(".concessionPercent").val() == '0' || $(this).closest('tr').find(".concessionPercent").val() == "" || $(this).closest('tr').find(".concessionAmt").val() == 0){
									$(".errormessagediv").show();
									$(".validateTips").text("Enter Valid Number");
									$(".errormessagediv").delay(3000).slideUp();
									flag = false;
								}else{
									
									if($(this).is('[disabled=disabled]')){
										
									}else{
										con_percent.push($(this).closest('tr').find(".concessionPercent").val());
										con_amount.push($(this).closest('tr').find(".concessionAmt").val());
										flag = true;
									}
								}
								feeCode.push($(this).closest('tr').find(".feecode").attr('id'));
							});
						});
					}

					var datalist = {
							"admissionNo" : $("#subcode").val(),
							"accyear" : acaYear,
							"classId" : class_Id,
							"concessionType" : concessiontype,
							"concessionId" : concessionId,
							"concessionPercent" : con_percent.toString(),
							"concessionAmt" : con_amount.toString(),
							"termCode" : termCode.toString(),
							"feeCode" : feeCode.toString(),
							"studentId" : studentId,
							"concessionstyle" : concessionBy,
							"hiddentrmcode" : hiddentrmcode,
							"hiddenfeecode" : hiddenfeecode,
							"hiddenacyId" : hiddenacyId,
							"hiddencontype" : hiddencontype,
							"hiddenConId" : hiddenConId,
							"hiddenConBy" : hiddenConBy,
					};
					if(!flag){

						return false;
					}else{

						$.ajax({
							type : 'POST',
							url : "feecollection.html?method=saveFeeConcessionRequest",
							data : datalist,
							beforeSend: function(){
								$("#loder").show();
							},
							success : function(response) {
								var result = $.parseJSON(response);
								if(result.status == "success"){
									$("#loder").hide();
									$(".successmessagediv").show();
									$(".validateTips").text("Fee Concession Request Saved Successfully");
									setTimeout(function(){
										window.location.href = "feecollection.html?method=setFeeRequestStudentWise&student_id="+studentId+"&classid="+class_Id+"&sectionid="+sectionId+"&loc_id="+locId+"&acy_id="+acaYear;
									},3000);
								}else if(result.status == "fail"){
									$("#loder").hide();
									$(".errormessagediv").show();
									$(".validateTips").text("Fee Concession Request Failed");
									setTimeout(function(){
										window.location.href = "feecollection.html?method=FeeConcessionRequest";
									},3000);
								}
							}
						});
					}
					$(this).dialog('close');
				}
			},
			'Close' : function() {
				$(".termName").val("");
				$("#partialConcession").hide();
				$("#termConcession").hide();
				$("#feetable").hide();
				$(".selectTerms").prop("checked",false);
				$(".tb").empty();
				$( ".selector" ).dialog({ dialogClass: 'no-close' });
				$(this).dialog('close');
			}
		}
	});
	
	$(".ui-icon-closethick").click(function(){
		$(".termName").val("");
		$(".termName").attr("disabled",false);
		$( ".selector" ).dialog({ dialogClass: 'no-close' });
		$("#partialConcession").hide();
		$("#termConcession").hide();
		$("#feetable").hide();
		$(".selectTerms").prop("checked",false);
		$(".tb").empty();
	});
	
	$("#edit").click(function(){
		getTermDetailsClassBased();
		getConcessionTypes();
		
		getData = $(".selectFee:checked").attr("id");
         var len = $(".selectFee:checked").length;
		if (len==0|| len > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			setTimeout(function(){
				$('.errormessagediv').fadeOut();
			},3000);
			return false;
		} 
		else
		{
			var trmcode = getData.split(",")[0];
			var feecode = getData.split(",")[1];
			var acyId = getData.split(",")[2];
			var classId = getData.split(",")[3];
			var concessiontype = getData.split(",")[5];
			var concessiontId = getData.split(",")[6];
			var concessionBy = getData.split(",")[7];
			var termName = getData.split(",")[8];
			var locId = getData.split(",")[9];
			var conAmt = getData.split(",")[10];
			var conPercent = getData.split(",")[11];
			var trmcount = trmcode.split("M")[1];
			var studId = $("#hiddenstudentid").val();
			var hiddentrmcode = null;
			var hiddenfeecode = null;
			var hiddenacyId = null;
			var hiddencontype = null;
			var hiddenConId = null;
			var hiddenConBy = null;
		
			$("#termId option[value='"+concessiontype+","+concessiontId+","+concessionBy+"']").attr('selected',true);
			$("#termId").attr('disabled',true);
			
			if(concessiontype=="1"){
				hiddentrmcode = getData.split(",")[0];
				hiddenfeecode = getData.split(",")[1];
				hiddenacyId = getData.split(",")[2];
				hiddencontype = getData.split(",")[5];
				hiddenConId = getData.split(",")[6];
				hiddenConBy = getData.split(",")[7];
			}
			else if(concessiontype=="2"){
				$("#feetable").hide();
				$("#partialConcession").show();
				$("#termConcession").hide();
				$("#scholorshipAmount").val(conAmt);
				var status = checkFeesPaidStatus(studId,$("#hiddenaccyid").val(),hiddentrmcode);
				
				if(status == "Paid"){
					$("#scholorshipAmount").attr("disabled",true);
				}
				
			}
			else if(concessiontype=="3"){
				$("#partialConcession").hide();
				$("#feetable").hide();
				$("#termConcession").show();
			}
			else{
				$("#partialConcession").hide();
				$("#termConcession").hide();
				$("#feetable").hide();
			}
			
			$(".selectTerms[value="+trmcode+"]").prop("checked",true);
			$(".selectTerms").attr("disabled",true);
			
			$(".table tbody tr").each(function(){
				$(this).find(".selectTerms").not($(".selectTerms:checked")).closest("tr").remove();
				
			});
			
			$(".tb").empty();
			selectTerms(trmcode,termName,concessionBy,trmcount,acyId,locId,$(".selectTerms[value="+trmcode+"]"),classId,studId);
			
			$(".table"+trmcount+" tbody tr").each(function(){
				if(concessionBy == 'A'){
					
					if($(this).find('#'+feecode).attr('id')!= undefined){
						$('#collapse'+trmcount+'').toggle();
						$(this).find('.select').prop("checked",true);
						$(this).closest("tr").find(".concessionAmt").prop("readonly",false);
						$(this).closest("tr").find(".concessionAmt").css({"background-color":"#ffffff"});
						$(this).closest("tr").find(".concessionAmt").val(conAmt);
						getTotalAmount(trmcount);
					}
					$(this).find(".select").not($(".select:checked")).closest("tr").remove();
					
				}else{
					if($(this).find('#'+feecode).attr('id')!= undefined){
						$('#collapse'+trmcount+'').toggle();
						$(this).find('.select').prop("checked",true);
						$(this).closest("tr").find(".concessionPercent").prop("readonly",false);
						$(this).closest("tr").find(".concessionPercent").css({"background-color":"#ffffff"});
						$(this).closest("tr").find(".concessionPercent").val(conPercent);
						$(this).closest("tr").find(".concessionAmt").val(conAmt);
						getTotalAmount(trmcount);
					}
					$(this).find(".select").not($(".select:checked")).closest("tr").remove();
				}
				
			});
			
			$("#dialog").dialog("open");
			$('.selector').dialog({ dialogClass: 'myPosition' });
			$("#dialog").dialog("option",'title', "Modify Fee Concession");
			
			if($("#scholorshipAmount").prop('disabled')){
				
				$(".ui-dialog-buttonset").hide();
				
			};
			
			$("#dialog").dialog({
				height: 'auto',
				width: 'auto',
				autoOpen: false,
				modal: false,
				resizable: false, 
				position: ['center', 'top+58'],
				modal     : true,
				buttons   : {
					'Update' : function() {
						
						if(concessionId == "2" && $("#scholorshipAmount").val() == 0){
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Amount"); 
							$(".errormessagediv").delay(2000).fadeOut();
							return false;
						}
						var flag = false;
						var concessionId = $("#termId option:selected").val().split(",")[0];
						var concessiontype = $("#termId option:selected").val().split(",")[1];
						var concessionBy = $("#termId option:selected").val().split(",")[2];
						var concessionName = $("#termId option:selected").html();
						var studentId = $("#hiddenstudentid").val();
						var class_Id = $("#classidhidden").val();
						var acaYear = $("#hiddenaccyid").val();
						var locId = $("#hiddenlocid").val();
						var sectionId = $("#hiddensectionid").val();

						if(concessionId == "1"){
							con_amount = [];
							con_percent = [];
							termCode = [];
							feeCode = [];

							var amt = getTotalClassFees();
							con_amount.push(amt);
							termCode.push("all");
							feeCode.push("all");
							flag = true;
						}
						else if(concessionId == "2"){
							con_amount = [];
							con_percent = [];
							termCode = [];
							feeCode = [];

							con_amount.push($("#scholorshipAmount").val());
							termCode.push("all");
							feeCode.push("all");
							
							if($("#scholorshipAmount").val() >= getTotalAmountForClass()){
								$(".errormessagediv").show();
								$(".validateTips").text("Entered Amount Should Be Less Than Total Amount");
								$(".errormessagediv").delay(3000).slideUp();
								flag = false;
							}else if($("#scholorshipAmount").val() == "" || $("#scholorshipAmount").val() == undefined){
								$(".errormessagediv").show();
								$(".validateTips").text("Field Required - Concession Amount");
								$(".errormessagediv").delay(3000).slideUp();
								flag = false;
								
							}else if($("#scholorshipAmount").val() == 0){
								$(".errormessagediv").show();
								$(".validateTips").text("Enter Valid Amount");
								$(".errormessagediv").delay(3000).slideUp();
								flag = false;
							}
							else{
								flag = true;
							}

						}else if(concessionId == "3"){
							
							$(".tb tbody tr").each(function(){

								con_amount = [];
								con_percent = [];
								termCode = [];
								feeCode = [];

								$(".tb tbody input[type=checkbox]:checked").each(function () {
									termCode.push($(this).attr('id').split(",")[0]);
									if($(this).closest('tr').find(".concessionPercent").val() == '0' || $(this).closest('tr').find(".concessionPercent").val() == "" || $(this).closest('tr').find(".concessionAmt").val() == 0){
										$(".errormessagediv").show();
										$(".validateTips").text("Enter Valid Number");
										$(".errormessagediv").delay(3000).slideUp();
										flag = false;
									}else{
										con_percent.push($(this).closest('tr').find(".concessionPercent").val());
										con_amount.push($(this).closest('tr').find(".concessionAmt").val());
										flag = true;
									}
									feeCode.push($(this).closest('tr').find(".feecode").attr('id'));
								});
							});
						}
						
						var datalist = {
								"admissionNo" : $("#subcode").val(),
								"accyear" : acaYear,
								"classId" : class_Id,
								"concessionType" : concessiontype,
								"concessionId" : concessionId,
								"concessionPercent" : con_percent.toString(),
								"concessionAmt" : con_amount.toString(),
								"termCode" : termCode.toString(),
								"feeCode" : feeCode.toString(),
								"studentId" : studentId,
								"concessionstyle" : concessionBy,
								"hiddentrmcode" : hiddentrmcode,
								"hiddenfeecode" : hiddenfeecode,
								"hiddenacyId" : hiddenacyId,
								"hiddencontype" : hiddencontype,
								"hiddenConId" : hiddenConId,
								"hiddenConBy" : hiddenConBy,
						};
						
						if(!flag){
							return false;
						}else{
							$.ajax({
								type : 'POST',
								url : "feecollection.html?method=saveFeeConcessionRequest",
								data : datalist,
								beforeSend: function(){
									$("#loder").show();
								},
								success : function(response) {
									var result = $.parseJSON(response);
									if(result.status == "success"){
										$("#loder").hide();
										$(".successmessagediv").show();
										$(".validateTips").text("Fee Concession Request Updated Successfully");
										$(".successmessagediv").delay(3000).slideUp();
										setTimeout(function(){
											window.location.href = "feecollection.html?method=setFeeRequestStudentWise&student_id="+studentId+"&classid="+class_Id+"&sectionid="+sectionId+"&loc_id="+locId+"&acy_id="+acaYear;
										},3000);
									}else if(result.status == "fail"){
										$("#loder").hide();
										$(".errormessagediv").show();
										$(".validateTips").text("Fee Concession Request Updation Failed");
										setTimeout(function(){
											window.location.href = "feecollection.html?method=setFeeRequestStudentWise&student_id="+studentId+"&classid="+class_Id+"&sectionid="+sectionId+"&loc_id="+locId+"&acy_id="+acaYear;
										},3000);
									}
								}
							});
						}
						$(this).dialog('close');
					},
					'Close' : function() {
						$(".termName").val("");
						$(".termName").attr("disabled",false);
						$("#partialConcession").hide();
						$("#termConcession").hide();
						$("#feetable").hide();
						$( ".selector" ).dialog({ dialogClass: 'no-close' });
						$(this).dialog('close');
					}
				}
			});
		}
	});
	
	
	$("#cancel").click(function(){
		
		getData = $(".selectFee:checked").attr("id");
        var len = $(".selectFee:checked").length;
		if (len==0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any Record");
			setTimeout(function(){
				$('.errormessagediv').fadeOut();
			},3000);
			return false;
		} 
		else
		{
			$("#dialog1").dialog("open");
			$("#dialog1").empty();
			$("#dialog1").append("<p class='warningfont'>Are you sure to Cancel ?</p>");
		}
	});
	
	$("#termId").change(function(){
		
		getTermDetailsClassBased();
		$('.tb').empty();
		if($(this).val().split(",")[0]=="2"){
			$( ".selector" ).dialog({ dialogClass: 'no-close' });
			$("#feetable").hide();
			$("#partialConcession").show();
			$("#termConcession").hide();
		}
		else if($(this).val().split(",")[0]=="3"){
			$('.selector').dialog({ dialogClass: 'myPosition' });
			$("#partialConcession").hide();
			$("#feetable").hide();
			$("#termConcession").show();
		}
		else{
			$( ".selector" ).dialog({ dialogClass: 'no-close' });
			$("#partialConcession").hide();
			$("#termConcession").hide();
			$("#feetable").hide();
		}
	});
	
	$("#termConcession .table tbody tr").each(function(){
		
		if($(this).find(".paidstatus").text() == "Paid"){
			$(this).find(".selectTerms").prop("disabled", true);
		}
		
	});
	
	$("#dialog1").dialog({
		autoOpen: false,
		modal: true,
		title:'Cancel Concession Fees',
		maxHeight:280,
		height:170,
		width:300,
		maxWidth:300,
		buttons : {
			"Yes" : function() {
				
				cancelFees = [];
				cancelCount = 0;
				
				var studentId = $("#hiddenstudentid").val();
				var class_Id = $("#classidhidden").val();
				var acaYear = $("#hiddenaccyid").val();
				var locId = $("#hiddenlocid").val();
				var sectionId = $("#hiddensectionid").val();
				
				$(".selectFee:checked").each(function(){
					var cancel = "";
					var list=$(this).attr("id");
					cancel = cancel+":"+list;
					cancelFees.push(cancel);
					cancelCount++;
				});
				
				datalist = {

						"cancelFees" : cancelFees.toString(),
						"cancelCount" : cancelCount,
				}, 
				$.ajax({
					type : 'POST',
					url : "feecollection.html?method=cancelRequestConcessionFees",
					data : datalist,
					async : false,
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(response) {
						
						var result = $.parseJSON(response);
					
						if(result.status == "success"){
							$("#loder").hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Fee Concession Request Deleted Successfully.");
							setTimeout(function(){
								window.location.href = "feecollection.html?method=setFeeRequestStudentWise&student_id="+studentId+"&classid="+class_Id+"&sectionid="+sectionId+"&loc_id="+locId+"&acy_id="+acaYear;
							},3000);
						}else if(result.status == "fail"){
							$("#loder").hide();
							$(".errormessagediv").show();
							$(".validateTips").text("To Cancel, First Delete The Paid Fees And Proceed Cancel.");
							$(".errormessagediv").delay(3000).slideUp();
						}
					}
				});
				$(this).dialog("close");
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	
	getConcessionFees();
	
	if($(".commontable tbody tr").length > 0){
		$("#edit").show();
		$("#cancel").show();
	}else{
		$("#edit").hide();
		$("#cancel").hide();
	}
	
	$("#back1").click(function(){
		window.location.href="feecollection.html?method=FeeConcessionRequest&historylocId="+$("#historylocId").val()+
		"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()+
		"&historysectionid="+$("#historysectionid").val()+"&historysearchvalue="+$("#historysearchvalue").val()+
		"&historyisConcession="+$("#historyisConcession").val();
	});

});

function getTotalAmount(termcount){
	var sumAmount = 0.0;
	
	$(".table"+termcount+" tbody .concessionAmt").each(function () {
		conAmt = $(this).closest('tr').find(".concessionAmt").val();
		sumAmount = Number(sumAmount)+ parseFloat(conAmt);
	});
	$(".table"+termcount+" tr").find(".totalFeeAmount").val(parseFloat(sumAmount).toFixed(2));
}

function getConcessionFees(){
	
	var location = $("#hiddenlocid").val();
	var academic = $("#hiddenaccyid").val();
	var classid = $("#classidhidden").val();
	var student = $("#hiddenstudentid").val();
	
	datalist = {

			"locationId" : location,
			"accyear" : academic,
			"classId" : classid,
			"studentId" : student,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getRequestedFees",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			for(var i=0;i<result.list.length;i++){

				if(result.list[i].concessionType == "1"){
					$("#add").hide();
					$(".tab").show();
					$(".partialTable").show();
					$(".TermTable").hide();
					$(".par_table tbody").append("<tr>"
							+"<td><input type='checkbox' name='selectFees' class='selectFee' style='text-align: center' id='"+result.list[i].termcode+","+result.list[i].feecode+","+academic+","+classid+","+student+","+result.list[i].concessionType+","+result.list[i].concessionId+","+result.list[i].concessionStyle+","+result.list[i].term+","+location+","+result.list[i].concessionAmount+","+result.list[i].concessionPercent+"'/></td>" 
							+"<td>"+result.list[i].concessionName+"</td>"
							+"<td>"+result.list[i].concessionAmount+"</td>"
							+"</tr>");
					checkboxsselect();
				}
				else if(result.list[i].concessionType == "2"){
					$("#add").hide();
					$(".tab").show();
					$(".partialTable").show();
					$(".TermTable").hide();
					$(".par_table tbody").append("<tr>"
							+"<td><input type='checkbox' name='selectFees' class='selectFee' style='text-align: center' id='"+result.list[i].termcode+","+result.list[i].feecode+","+academic+","+classid+","+student+","+result.list[i].concessionType+","+result.list[i].concessionId+","+result.list[i].concessionStyle+","+result.list[i].term+","+location+","+result.list[i].concessionAmount+","+result.list[i].concessionPercent+"'/></td>" 
							+"<td>"+result.list[i].concessionName+"</td>"
							+"<td>"+result.list[i].concessionAmount+"</td>"
							+"</tr>");
					checkboxsselect();
				}
				else if(result.list[i].concessionType == "3"){
					$(".tab").show();
					$(".TermTable").show();
					$(".partialTable").hide();
					$(".trm_table tbody").append("<tr>"
							+"<td><input type='checkbox' name='selectFees' class='selectFee' style='text-align: center' id='"+result.list[i].termcode+","+result.list[i].feecode+","+academic+","+classid+","+student+","+result.list[i].concessionType+","+result.list[i].concessionId+","+result.list[i].concessionStyle+","+result.list[i].term+","+location+","+result.list[i].concessionAmount+","+result.list[i].concessionPercent+"'/></td>" 
							+"<td>"+result.list[i].concessionName+"</td>"
							+"<td>"+result.list[i].term+"</td>"
							+"<td>"+result.list[i].feename+"</td>"
							+"<td>"+result.list[i].feeAmount+"</td>"
							+"<td>"+result.list[i].concessionAmount+"</td>"
							+"</tr>");
					checkboxsselect();

				}
				else{
					$(".TermTable").hide();
					$(".partialTable").hide();
				}
			}
		}
	});
}

function getTotalClassFees(){
	amount = null;
	datalist = {

			"classId" : $("#classidhidden").val(),
			"acy_Id" : $("#hiddenaccyid").val(),
			"loc_Id" : $("#hiddenlocid").val(),
	}, 
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getClassWiseAllFees",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			amount = result.clsAmt;
		}
	});
	return amount;
}

function checkFeesPaidStatus(studId,hiddenacyId,hiddentrmcode){
	paidStatus = null;
	datalist = {

			"studId" : studId,
			"hiddenacyId" : hiddenacyId,
			"hiddentrmcode" : hiddentrmcode,
	}, 
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=checkFeesPaidStatus",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			paidStatus = result.status;
		}
	});
	return paidStatus;
}

function selectTerms(term_Id,term_Name,concessionBy,termcount,hiddenAcyId,hiddenLocId,pointer,classId,studId){
	
	if(pointer.prop("checked")==true){
		$("#feetable").show();

		datalist = {

				"termId" : term_Id,
				"acy_Id" : hiddenAcyId,
				"loc_Id" : hiddenLocId,
				"classId" : classId,
				"studId" : studId,
		}, 
		$.ajax({
			type : 'POST',
			url : "feecollection.html?method=getTermWiseAllFees",
			data : datalist,
			async : false,

			success : function(response) {

				var result = $.parseJSON(response);
				if(concessionBy == "P"){
					$('.tb').append('<div class="panel panel-default remove'+termcount+' showFees"><div class="panel-heading" role="tab" style="background-color: #f5f5f5;border: 1px solid #B5B0B0;">'
							+'<h4 class="panel-title">'
							+'<a class="#collapse'+termcount+'" role="button" data-toggle="collapse" data-parent="#accordion'+termcount+'" href="#collapse'+termcount+'"'
							+'style="color: #fff;" aria-expanded="true" aria-controls="#collapse'+termcount+'">'
							+'<i class="glyphicon glyphicon-menu-hamburger" style="color:black;"></i>'
							+'&nbsp;&nbsp;<lable style="color:black;">'+term_Name+'</lable></a></h4></div>'
							+'<div id="collapse'+termcount+'" style="border:1px solid #B5B0B0;" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapse'+termcount+'">'
							/*+'<div id="shrink'+termcount+'">'*/
							+'<div class="panel-body">'
							+'<table class="table'+termcount+'" id="allstudent" style="width:100%">'
							+'<thead><tr>'
							/*+"<th><input type='checkbox' class='selectAll' style='text-align: center' onClick='selectAll()'/></td>"*/
							+"<th style='text-align: center;'>Select</td>"
							+"<th style='text-align: center;'>Fee Name</td>"
							+"<th style='text-align: center;'>Fee Amt</td>"
							+"<th style='text-align: center;'>Concession %</td>"
							+"<th style='text-align: center;'>Concession Amt</td>"
							+"</tr>"
							+'</thead><tbody>'
							+'</tbody><table></div></div>');
					$(".table"+termcount+" tbody").empty();
					for(var i=0;i<result.feeList.length;i++){
						$(".table"+termcount+" tbody").append("<tr>"
								+"<td><input type='checkbox' name='select' class='select' style='text-align: center' id='"+term_Id+","+result.feeList[i].feecode+",P'/></td>" 
								+"<td class='feecode' id="+result.feeList[i].feecode+">"+result.feeList[i].feename+"</td>"
								+"<td class='feeAmount'>"+result.feeList[i].feeAmount+"</td>"
								+"<td><input type='text' class='concessionPercent' style='width: 100px; background-color:#EBEBE4; border:1px solid #ABADB3;' readonly=readonly/></td>"
								+"<td><input type='text' class='concessionAmt' value='0.0' style='width: 100px; background-color:#EBEBE4; border:1px solid #ABADB3; text-align: right' readonly=readonly/></td>"
								+"</tr>");
					}
					$(".table"+termcount+" tbody").append("<tr>"
							+"<td></td>"
							+"<td></td>"
							+"<td></td>"
							+"<td style='font-weight: 700;'>Total</td>"
							+"<td><input type='text' name='totalFeeAmount' class='totalFeeAmount' value='0.0' readonly=readonly' style='width: 100px; text-align: right;background-color: rgba(255, 224, 0, 0.22);' readonly='readonly'/></td>"
							+"</tr>");

					$(".concessionPercent").keyup(function(){
						if(isNaN($(this).val())){
							$(this).val("");
						}
						/*else  if($(this).val().trim()==""){
							$(this).val("");
						}*/
						else if(Number($(this).val()) > 100){
							$(this).val($(this).closest("tr").find(".concessionPercent").text());
						}
					});
					
					$(".table"+termcount+" .selectAll").change(function(){
						$(".table"+termcount+" .select").prop('checked', $(this).prop("checked"));
						if($(".table"+termcount+" .selectAll").prop("checked")){
							$(".table"+termcount+" .select").closest("tr").find(".concessionPercent").prop("readonly",false);
							$(".table"+termcount+" .select").closest("tr").find(".concessionPercent").css({"background-color":"#ffffff"});
							getTotalAmount(termcount);
						}else{
							$(".table"+termcount+" .select").closest("tr").find(".concessionPercent").prop("readonly",true);
							$(".table"+termcount+" .select").closest("tr").find(".concessionPercent").val("");
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").val(0.0);
							$(".table"+termcount+" .select").closest("tr").find(".concessionPercent").css({"background-color":"#EBEBE4"});
							getTotalAmount(termcount);
						}
					});

					$(".table"+termcount+" .select").change(function(){
						if($(".table"+termcount+" .select").length==$(".select:checked").length){
							$(".table"+termcount+" .selectAll").prop("checked",true);
						}
						else{
							$(".table"+termcount+" .selectAll").prop("checked",false);
						}
					});

					$(".table"+termcount+" .select").change(function(){

						if($(this).prop("checked") == true){
							$(this).closest("tr").find(".concessionPercent").prop("readonly",false);
							$(this).closest("tr").find(".concessionPercent").css({"background-color":"#ffffff"});
							getTotalAmount(termcount);
						}
						else{
							$(this).closest("tr").find(".concessionPercent").prop("readonly",true);
							$(this).closest("tr").find(".concessionPercent").val("");
							$(this).closest("tr").find(".concessionAmt").val(0.0);
							$(this).closest("tr").find(".concessionPercent").css({"background-color":"#EBEBE4"});
							getTotalAmount(termcount);
						}
					});

					$(".concessionPercent").keypress(function(e){
						if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Only Number"); 
							$(".errormessagediv").delay(2000).fadeOut();
							return false;
						}
					});

					$(".concessionPercent").keyup(function(){
						var conPercent = $(this).val();
						var feeAmt = $(this).closest('tr').find(".feeAmount").text();

						var total = ((Number(conPercent)/100)*Number(feeAmt));
						$(this).closest('tr').find(".concessionAmt").val(total);
						getTotalAmount(termcount);
					});
					
				}else{
					$('.tb').append('<div class="panel panel-default remove'+termcount+'"><div class="panel-heading" role="tab" style="background-color: #f5f5f5;border: 1px solid #B5B0B0;">'
							+'<h4 class="panel-title">'
							+'<a class="#collapse'+termcount+'" role="button" data-toggle="collapse" data-parent="#accordion'+termcount+'" href="#collapse'+termcount+'"'
							+'style="color: #fff;" aria-expanded="true" aria-controls="#collapse'+termcount+'">'
							+'<i class="glyphicon glyphicon-menu-hamburger" style="color:black;"></i>'
							+'&nbsp;&nbsp;<lable style="color:black;">'+term_Name+'</lable></a></h4></div>'
							+'<div id="collapse'+termcount+'" style="border:1px solid #B5B0B0;" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapse'+termcount+'">'
							+'<div class="panel-body">'
							+'<table class="table'+termcount+'" id="allstudent" style="width:100%">'
							+'<thead><tr>'
							/*+"<th><input type='checkbox' class='selectAll' style='text-align: center' onClick='selectAll()' /></td>"*/
							+"<th style='text-align: center;'>Select</td>"
							+"<th style='text-align: center;'>Fee Name</td>"
							+"<th style='text-align: center;'>Fee Amt</td>"
							+"<th style='text-align: center;'>Concession Amt</td>"
							+"</tr>"
							+'</thead><tbody>'
							+'</tbody><table></div></div>');
					$(".table"+termcount+" tbody").empty();
					for(var j=0;j<result.feeList.length;j++){
						$(".table"+termcount+" tbody").append("<tr>"
								+"<td><input type='checkbox' name='select' class='select' style='text-align: center' id='"+term_Id+","+result.feeList[j].feecode+",A'/></td>" 
								+"<td class='feecode' id="+result.feeList[j].feecode+">"+result.feeList[j].feename+"</td>"
								+"<td class='feeamount'>"+result.feeList[j].feeAmount+"</td>"
								+"<td><input type='text' class='concessionAmt' value='0.0' style='width: 100px; text-align: right; background-color:#EBEBE4; border:1px solid #ABADB3;' readonly=readonly/></td>"
								+"</tr>");
					}
					$(".table"+termcount+" tbody").append("<tr>"
							+"<td></td>"
							+"<td></td>"
							+"<td style='font-weight: 700;'>Total</td>"
							+"<td><input type='text' name='totalFeeAmount' class='totalFeeAmount' value='0.0' style='width: 100px; text-align: right;background-color: rgba(255, 224, 0, 0.22);' readonly='readonly'/></td>"
							+"</tr>");
					

					$(".concessionAmt").keyup(function(){
						if(isNaN($(this).val())){
							$(this).val("");
						}
						/*else  if($(this).val().trim()==""){
							$(this).val("0");
						}*/
						else if(Number($(this).val()) > Number($(this).closest("tr").find(".feeamount").text())){
							$(this).val($(this).closest("tr").find(".feeamount").text());
						}
					});
					
					$(".table"+termcount+" .selectAll").change(function(){
						$(".table"+termcount+" .select").prop('checked', $(this).prop("checked"));
						if($(".table"+termcount+" .selectAll").prop("checked")){
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").prop("readonly",false);
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").css({"background-color":"#ffffff"});
							getTotalAmount(termcount);
						}else{
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").val(0.0);
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").prop("readonly",true);
							$(".table"+termcount+" .select").closest("tr").find(".concessionAmt").css({"background-color":"#EBEBE4"});
							getTotalAmount(termcount);
						}

					});

					$(".table"+termcount+" .select").change(function(){
						if($(".table"+termcount+" .select").length==$(".select:checked").length){
							$(".table"+termcount+" .selectAll").prop("checked",true);
						}
						else{
							$(".table"+termcount+" .selectAll").prop("checked",false);
						}
					});

					$(".table"+termcount+" .select").change(function(){

						if($(this).prop("checked")==true){
							$(this).closest("tr").find(".concessionAmt").prop("readonly",false);
							$(this).closest("tr").find(".concessionAmt").css({"background-color":"#ffffff"});
							getTotalAmount(termcount);
						}
						else{
							$(this).closest("tr").find(".concessionAmt").val(0.0);
							$(this).closest("tr").find(".concessionAmt").prop("readonly",true);
							$(this).closest("tr").find(".concessionAmt").css({"background-color":"#EBEBE4"});
							getTotalAmount(termcount);
						}
					});

					$(".concessionPercent").keypress(function(e){
						if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Only Number"); 
							$(".errormessagediv").delay(2000).fadeOut();
							return false;
						}
					});

					$(".concessionAmt").keyup(function(){
						getTotalAmount(termcount);
					});

				}

			}
		});
	}else{
		$(".tb .remove"+termcount+"").remove();
	}
	
}

function getTermConcessionFees(term_Id,termcount,concessionBy){
	
	feeCode = [];
	
	var location = $("#hiddenlocid").val();
	var academic = $("#hiddenaccyid").val();
	var classid = $("#classidhidden").val();
	var student = $("#hiddenstudentid").val();
	
	datalist = {

			"locationId" : location,
			"accyear" : academic,
			"classId" : classid,
			"studentId" : student,
			"term_Id" : term_Id,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getTermConcessionFees",
		data : datalist,
		async : false,

		success : function(response) {
			
			var result = $.parseJSON(response);
		
			for(var i=0;i<result.list.length;i++){
				
				$(".table"+termcount+" tbody tr").each(function(){
					if(concessionBy == 'A'){
						
						if($(this).find('#'+result.list[i].feecode).attr('id')!= undefined){
							$(this).find('.select').prop("disabled",true);
							$(this).closest("tr").find(".concessionAmt").prop("readonly",true);
							$(this).closest("tr").find(".concessionAmt").val(result.list[i].concessionAmount);
							getTotalAmount(termcount);
						}
						
					}else{
						if($(this).find('#'+result.list[i].feecode).attr('id')!= undefined){
							$(this).find('.select').prop("disabled",true);
							$(this).closest("tr").find(".concessionPercent").prop("readonly",true);
							$(this).closest("tr").find(".concessionPercent").val(result.list[i].concessionPercent);
							$(this).closest("tr").find(".concessionAmt").val(result.list[i].concessionAmount);
							getTotalAmount(termcount);
						}
					}
					
				});
			}
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function checkFeeSetup(){
	var checkfeesetup = false;
	var location = $("#hiddenlocid").val();
	var academic = $("#hiddenaccyid").val();
	var classid = $("#classidhidden").val();
	var student = $("#hiddenstudentid").val();

	datalist = {

			"locationId" : location,
			"accyear" : academic,
			"classId" : classid,
			"studentId" : student,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=checkFeeSetup",
		data : datalist,
		async : false,

		success : function(response) {
			
			var result = $.parseJSON(response);

			checkfeesetup = result.status;
		}
	});
	return checkfeesetup;
}

function getTotalAmountForClass(){

	var location = $("#hiddenlocid").val();
	var academic = $("#hiddenaccyid").val();
	var classid = $("#classidhidden").val();
	var student = $("#hiddenstudentid").val();

	datalist = {

			"locationId" : location,
			"accyear" : academic,
			"classId" : classid,
			"studentId" : student,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getTotalAmountForClass",
		data : datalist,
		async : false,

		success : function(response) {
			
			var result = $.parseJSON(response);

			totalFeesAmount = result.status;
		}
	});
	return totalFeesAmount;

}

function checkboxsselect(){
	$(".selectFees").change(function(){
		$(".selectFee").prop('checked', $(this).prop("checked"));
	});
	
	$(".selectFee").change(function(){
		if($(".selectFee").length==$(".selectFee:checked").length){
			$(".selectFees").prop("checked",true);
		}
		else{
			$(".selectFees").prop("checked",false);
		}
	});
}

function getTermDetailsClassBased(){
	
	var location = $("#hiddenlocid").val();
	var academic = $("#hiddenaccyid").val();
	var classid = $("#classidhidden").val();
	var student = $("#hiddenstudentid").val();

	datalist = {

			"locationId" : location,
			"accyear" : academic,
			"classId" : classid,
			"studentId" : student,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getTermDetailsClassBased",
		data : datalist,
		async : false,

		success : function(response) {
			$(".table tbody").empty();
		var result = $.parseJSON(response);
		if(result.termfees.length > 0){
			for(var i=0; i<result.termfees.length; i++ ){
				$(".table tbody").append('<tr><td class="selectTerm" id="'+result.termfees[i].termId+','+result.termfees[i].term+'">'+(i+1)+'</td>'
						+'<td>'+result.termfees[i].term+'</td>'
						+'<td>'+result.termfees[i].termAmount+'</td>'
						+'<td>'+result.termfees[i].status+'</td>'
						+'<td  style="text-align: center;"><input type="checkbox" name="selecTerms" class="selectTerms" id="'+result.termfees[i].termId+','+result.termfees[i].term+','+result.termfees[i].sno+'" value="'+result.termfees[i].termId+'"</td>'
						+'</tr>');
			}
		}
		$(".selectTerms").click(function () {
			var term_Id = $(this).attr("id").split(",")[0];
			var term_Name = $(this).attr("id").split(",")[1];
			var concessionBy = $("#termId").val().split(",")[2];
			var termcount = term_Id.split("M")[1];
			var hiddenAcyId = $("#hiddenaccyid").val();
			var hiddenLocId = $("#hiddenlocid").val();
			var classId = $("#classidhidden").val();
			var studId = $("#hiddenstudentid").val();
			selectTerms(term_Id,term_Name,concessionBy,termcount,hiddenAcyId,hiddenLocId,$(this),classId,studId);
			getTermConcessionFees(term_Id,termcount);
			
		});
		}
	});
}

function getConcessionTypes(){

	var location = $("#hiddenlocid").val();

	datalist = {

			"locationId" : location,
	},
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getConcessionTypes",
		data : datalist,
		async : false,

		success : function(response) {
			var result = $.parseJSON(response);
			if(result.feeConcession.length > 0){
				$("#termId").empty();
				$("#termId").append('<option value="">----------Select----------</option>');
				for(var j=0; j<result.feeConcession.length; j++ ){
					$('#termId').append(
							'<option value="'+ result.feeConcession[j].concessionType+','+result.feeConcession[j].concessionId+','+result.feeConcession[j].concessionBy+'">'
							+ result.feeConcession[j].concessionName+ '</option>');
				}
			}
		}
	});
}
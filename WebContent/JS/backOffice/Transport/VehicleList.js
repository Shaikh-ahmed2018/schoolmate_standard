function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

/*function myFunction() {

	document.getElementById("myForm").submit();   
}*/


$(document).ready(function() {

			if($("#allstudent tbody tr").length ==0){
				$("#allstudent tbody").append("<tr><td colspan='8'>NO Records Found</td></tr>");
			}

			$("#vehicle").click(function(){
				$("#vehicleclose").slideToggle();
			});
			setTimeout("removeMessage()", 4000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);


			$('#add').click(function() {
				window.location.href = "transport.html?method=addvehicledetails&historylocId="+$("#locationname").val()
				+"&historystatus="+$("#status").val()+"&historysearch="+$("#searchname").val();
			});

			$('#editVehicle').click(function() {	
				var cnt = 0;	
				$('.select:checked').map(function() {
					getData = $(this).attr("id");
					cnt++;
				});
				if (cnt == 0 || cnt > 1) {
					$(".errormessagediv").show();
					$(".validateTips").text("Select Atleast One Record");
					return false;
				}  else {
					var vehicleIdlist = getData;
					window.location.href = "transport.html?method=getSingleVehicleDetails&vehicleIdlist="+vehicleIdlist
					+"&historylocId="+$("#locationname").val()+"&historystatus="+$("#status").val()+
					"&historysearch="+$("#searchname").val();
				}
			});
				
			$("#locationname").change(function(){
				getListOfVehicleDetails();	
			})	

			checkboxsselect();
			reason=null;
			var status = "N";
			$("#status").change(function(){
				if(this.value=="Y"){
					$("#deleteVehicle").text("InActive"); 
					status = 'N';
				}
				else{
					$("#deleteVehicle").text("Active"); 
					status = 'Y';
				}
				getListOfVehicleDetails();
				$("#selectall").prop("checked",false);
			});
			
			if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){
				 
				if($("#currentstatus").val()=="Y"){ 
					$("#status").val("N");	
					$("#editVehicle").hide();
					$("#deleteVehicle").text("Active");
					getListOfVehicleDetails();
					}
					else{
					$("#status").val("Y");
					$("#editVehicle").show();
					$("#deleteVehicle").text("InActive");
					getListOfVehicleDetails();
				   }
			}
			getDataArray=[];
			$("#deleteVehicle").click(function(e){
				var count = 0;
				$(".select:checked").map(function() {

					var checkdep_id = $(this).attr("id");
					getDataArray.push(checkdep_id);

					count++;
				});
				if(count==0){
					$(".errormessagediv").show();
					$(".validateTips").text("Select Atleast One Record");

					return false;

				}else{
					if($(this).text()=="InActive"){
						status="N";
					}
					else{
						status="Y";
					}
					
					
					$("#dialog").dialog("open");
					$("#dialog").empty();
					$("#dialog").append("<p>Are You Sure To "+$("#deleteVehicle").text()+"?</p>");
					$("#dialog").append('<label>Reason:</label>');
					$("#dialog").append('<select name="feecanreason" style="width: 100%;" id="feecanreason" onchange="HideError(this)">'
							+ '<option value="">' + "----------select----------"
							+ '</option>'
							+ '<option value="Incorrect Entry">Incorrect Entry</option>'
							+ '<option value="Not In Use">' + "Not In Use" 
							+ '</option>'
							+ '<option value="others">' + "Others" 
							+ '</option>'+
					'</select>');

					$("#dialog").append('<select name="activereason" style="width: 100%;" id="activereason" onchange="HideError(this)">'
							+ '<option value="">' + "----------select----------"
							+ '</option>'
							+ '<option value="Correct Entry">' + "Correct Entry"
							+ '</option>'
							+ '<option value="In use">' + "In use" 
							+ '</option>'
							+ '<option value="others">' + "Others" 
							+ '</option>'+
					'</select>');

					$("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" style="width: 100%;" name="other" maxlength="249" id="otherreason" onclick="HideError(this)"/></div>');

					$("#othreason").hide();
					$("#activereason").hide();
					$('#feecanreason').change(function(){
						$(".errormessagediv").hide();
						var othersres=$('#feecanreason').val();
						if(othersres == 'others'){

							$("#othreason").show(); 
							$("#activereason").hide();
						}else{
							$("#otherreason").val("");
							$("#othreason").hide();
							$("#feecanreason").show();
							$("#activereason").hide();
						}
						reason = $("#feecanreason").val();
					});

					if($("#status").val()=="N"){
						$("#othreason").hide();
						$("#activereason").show();
						$("#feecanreason").hide();
					}
					$('#activereason').change(function(){

						if($(this).val() == 'others'){
							$("#othreason").show(); 
							$("#activereason").show();
							$("#feecanreason").hide();
						}
						else{
							$("#otherreason").val("");
							$("#othreason").hide();
							$("#feecanreason").hide();
							$("#activereason").show();
						}
						reason = $("#activereason").val();
					});
				}

			});

			$("#dialog").dialog({
				autoOpen  : false,
				resizable : false,
				modal     : true,
				title     : "Vehicle Details",
				buttons   : {
					'Yes' : function() {
						var inactivereason=$("#feecanreason").val();
						var activereason=$("#activereason").val();

						if($("#deleteVehicle").text()=="Active" && activereason.length == 0)
						{
							showError("#activereason","Select Any One Reason");
							return false;

						}
						else if($("#deleteVehicle").text()=="InActive" && inactivereason.length == 0){

							showError("#feecanreason","Select Any One Reason");
							return false;
						}


						else if($("#deleteVehicle").text()=="InActive" && $("#feecanreason").val() == "others"){

							if($("#otherreason").val().length==0 || $("#otherreason").val().trim()=="") {
								showError("#otherreason","Field Required OtherReason");
								return false;   	
							}

							else{
								reason = $("#otherreason").val();
								deleteVehicleList(status,reason,getDataArray);
								$(this).dialog("close");
							}
						}

						else if ($("#deleteVehicle").text()=="Active" &&$("#activereason").val()=="others") {

							if($("#otherreason").val().length==0 || $("#otherreason").val().trim()==""){
								showError("#otherreason","Field Required OtherReason");
								return false;
							}
							else{
								reason = $("#otherreason").val();

								deleteVehicleList(status,reason,getDataArray);
								$(this).dialog("close");
							}
						}
						else{
							deleteVehicleList(status,reason,getDataArray);
							$(this).dialog("close");
						}
					},
					'No' : function() {
						$(this).dialog('close');
					}
				}
			});
		$("#search").click(function(){
			searchvehicledetails();
		});
		$("#resetbtn").click(function(){ 
			$("#status").val("active");
			$("#searchname").val("");
			searchvehicledetails();
		});
		$("#searchname").keypress(function(e){
			var key = e.which;
			if(key==13){
			searchvehicledetails();
			}
		});
			$('#excelDownload').click(function() {
						window.location.href = 'transport.html?method=VehicleDetailsXLS';
					});

			$("#pdfDownload").click(function(){
				window.location.href = "transport.html?method=VehicleDetailsPDFReport";
			});	


			if($("#historylocId").val()!="" || $("#historystatus").val()=="N" || $("#historysearch").val()!="")
			{
				$("#locationname").val($("#historylocId").val());
				$("#searchname").val($("#historysearch").val());
				$("#status").val($("#historystatus").val());
				
				if($("#status").val()=="Y"){
					$("#deleteVehicle").text("InActive"); 
					status = 'N';
				}
				else{
					$("#deleteVehicle").text("Active"); 
					status = 'Y';
				}
			 
			     getListOfVehicleDetails();
			}

 });


function deleteVehicleList(status,reason,getDataArray){
	dataList={
			'getDataArray':getDataArray.toString(),
			'reason':reason,
			'status':status,
			'button':$("#deleteVehicle").text(),
	};


	$.ajax({
		type : 'POST',
		url : "transport.html?method=deleteVehicleDetails",
		async : false,
		data : dataList,
		success : function(response) {
			var result = $.parseJSON(response);

			$('.errormessagediv').hide();


			if(result.status=="true"){

				$(".successmessagediv").show();
				$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".successmessage").text($("#deleteVehicle").text()+" "+"Vehicle Details Progressing...");
				$(".successmessagediv").css({
					'z-index':'9999999',
				});
				setTimeout(function(){

					window.location.href="menuslist.html?method=vehicleList&currentstatus="+$("#status").val();
					$("#selectall").prop("checked",false);
					getDataArray=[];

				},2000);
			}

			else if(result.status=="false"){
				$('.errormessagediv').show();
				$('.validateTips').text("Selected Vehicle Details is not"+" "+$("#deleteVehicle").text());
				$(".errormessagediv").css({
					'z-index':'9999999'
				});
				setTimeout(function(){

					window.location.href="menuslist.html?method=vehicleList&currentstatus="+$("#status").val();
					$("#selectall").prop("checked",false);
					getDataArray=[];

				},2000);
			}
			else{
				$('.errormessagediv').show();
				$('.validateTips').text("Selected Vehicle Details is Cannot"+" "+$("#deleteVehicle").text());
				$(".errormessagediv").css({
					'z-index':'9999999'
				});

			}


		}

	});

}
function getListOfVehicleDetails(){

	dataList={

			"status":$("#status").val(),
			"locationname":$("#locationname").val(),
	};

	$.ajax({
		type:'POST',
		url:'transport.html?method=getListOfVehicleDetails',
		data:dataList,
		async:false,
		success: function(data){

			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.getvehiclelist.length>0){

				for(var i=0;i<result.getvehiclelist.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' id='"+result.getvehiclelist[i].vehiclecode+"' /></td>" +
							"<td>"+result.getvehiclelist[i].vehicleregno+"</td>" +
							"<td>"+result.getvehiclelist[i].vehiclename+"</td>" +
							"<td>"+result.getvehiclelist[i].vehicletype+"</td>" +
							"<td>"+result.getvehiclelist[i].permitvalidity+"</td>" +
							"<td>"+result.getvehiclelist[i].driverName+"</td>" +
							"<td>"+result.getvehiclelist[i].routename+"</td>" +
							"<td>"+result.getvehiclelist[i].reson+"</td>" +

					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			checkboxsselect();	
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.getvehiclelist.length);
			pagination(100);
		}
	});
}


function searchvehicledetails(){
	  ;
	dataList={
			"searchname":$("#searchname").val(),
			"status":$("#status").val(),
			"locationname":$("#locationname").val(),
	};

	$.ajax({
		type:'POST',
		url:'transport.html?method=searchvehicledetails',
		data:dataList,
		async:false,
		success: function(data){

			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.getvehiclelist.length>0){

				for(var i=0;i<result.getvehiclelist.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' id='"+result.getvehiclelist[i].vehiclecode+"' /></td>" +
							"<td>"+result.getvehiclelist[i].vehicleregno+"</td>" +
							"<td>"+result.getvehiclelist[i].vehiclename+"</td>" +
							"<td>"+result.getvehiclelist[i].vehicletype+"</td>" +
							"<td>"+result.getvehiclelist[i].permitvalidity+"</td>" +
							"<td>"+result.getvehiclelist[i].driverName+"</td>" +
							"<td>"+result.getvehiclelist[i].routename+"</td>" +
							"<td>"+result.getvehiclelist[i].reson+"</td>" +

					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			checkboxsselect();	
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.getvehiclelist.length);
			pagination(100);
		}
	});
}


function checkboxsselect(){
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});

	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
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
function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

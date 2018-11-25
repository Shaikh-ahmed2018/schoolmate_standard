$(document).ready(function() {

	$("#back1").click(function(){
		window.location.href="menuslist.html?method=vehicleList&historylocId="+$("#historylocId").val()
		+"&historystatus="+$("#historystatus").val()+"&historysearch="+$("#historysearch").val();
	});	
	
	$('#vehicleregno').keyup(function(){
	    $(this).val($(this).val().toUpperCase());
	});
	
	$('#vehicleregno').keypress(function (e) {
        var regex = new RegExp(/^[a-zA-Z0-9-&)(\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").show();
		$(".validateTips").text("Enter only Alphanumeric , '-' , ' ( ' and ' ) ' Characters");
		setTimeout(function() {
			$('#errormessagediv').fadeOut();
		    },3000);
        return false;
    });
	
	$("#drivername").change(function(){
		valideDriverCode();
		if($(this).val().trim()==""){
			$("#experience").val("");
			$("#dlexpiray").val("");
			$("#dlno").val("");
			$("#phoneno").val("");
			$("#doj").val("");
			$("#licencedrive").val("");
			
		}
		else{
		
			getDriverEntireDetails();
		}
	});
	
	routeCode();
	driverCode();
	$("#locationname").change(function(){
		routeCode();
		driverCode();
	});
	
	
					$("#uploadinsurance").show();
					$("#document1btn").hide();
					$("#deleteProfile").hide();
					
					$("#").show();
					$("#document2btn").hide();
					$("#deleteIDProof").hide();
					
					$("#taxpaid").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : -1,
						yearRange : 'c-5:c+5',
						changeMonth : "true",
						changeYear : "true",
				      
				        onSelect: function (date) {
				            var date2 = $('#taxpaid').datepicker('getDate');
				          
				            
				            date2.setMonth(date2.getMonth()+12);
				            date2.setDate(date2.getDate()-1);
				            var day=date2.getDate();
				            if(day<10){
				            	day="0"+day;
				            }
				            var month=date2.getMonth()+1;
				            if(month<10){
				            	month="0"+month;
				            }
				            var year=date2.getFullYear();
				            $('#taxexpirydate').val(day+"-"+month+"-"+year);
				        }
				    });
					
					$("#pollution").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : 0,
						yearRange : "1997",
						changeMonth : true,
						changeYear : true,
						dateFormat : 'dd-mm-yy',
						numberOfMonths : 1,
					});
					
					
					$("#issueddate").datepicker({
						dateFormat : "dd-mm-yy",
						maxDate : -1,
						yearRange : 'c-5:c+5',
						changeMonth : "true",
						changeYear : "true",
				      
				        onSelect: function (date) {
				            var date2 = $('#issueddate').datepicker('getDate');
				            date2.setMonth(date2.getMonth() + 12);
				            date2.setDate(date2.getDate()-1);
				            var day=date2.getDate();
				            if(day<10){
				            	day="0"+day;
				            }
				            var month=date2.getMonth()+1;
				            if(month<10){
				            	month="0"+month;
				            }
				            var year=date2.getFullYear();
				            $('#expirydate').val(day+"-"+month+"-"+year);
				            //sets minDate to dt1 date + 1
				           
				        }
				    });
					
					$("#expirydate").attr( 'readOnly' , 'true' );
					
					$("#fc").datepicker({
						dateFormat : "dd-mm-yy",
						 minDate : 0,
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
					});
					
					$("#permitvalidity").datepicker({
						dateFormat : "dd-mm-yy",
						 minDate : 0,
						 yearRange : 'c-30:c+30',
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
					});
					
					
					$("#todate").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true
					});	
					
					

					var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
					if (pageUrl == "transport.html?method=saveVehicleDetails"){
						$(".successmessagediv").show();
						 setTimeout(function(){
							 window.location.href = "menuslist.html?method=vehicleList";
						 },2000);
					}
					
					setTimeout("removeMessage()", 4000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 2000);
			
					var vehicleidHidden=$('#hvehicleid').val().trim();
				
					if (vehicleidHidden == "" || vehicleidHidden == undefined ) {
						driverCode();
					} 
					else{
						driverCode();
						$("#drivername").val($("#driverCode").val());
						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
					}
					
					var vehicleidHidden=$('#hvehicleid').val().trim();
					if (vehicleidHidden == "" || vehicleidHidden == undefined ) {
						routeCode();
					} else {
						routeCode();
						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
						$("#routename option[value="+ $('#routecodeid').val() + "]").attr('selected', 'true');
					}
					
					$("#saveid").click(function() {
						var fistInput = document.getElementById("enginenumber").value;
						var secondInput = document.getElementById("chassisno").value;
						var driverCode = "";
						var routename = $("#routename").val();
						if ($("#driverCode").val() == null || $("#driverCode").val() == "") {
							driverCode = $("#drivername").val();
						} else {
							driverCode = $("#driverCode").val();
						}
						var updatevehiclecode = $("#hvehicleid").val();
						var status = $("#statusId").val();
						var vehicleregno = $("#vehicleregno").val().trim();
                        var locationname=$("#locationname").val();
						var vehicletype = $("#vehicletype").val();
						var chassisno = $("#chassisno").val();
						var vehiclename = $("#vehiclename").val();
						var nameofvehicle = $("#nameofvehicle").val();
						var enginenumber = $("#enginenumber").val();
						var taxpaid = $("#taxpaid").val();
						var pollution = $("#pollution").val();
						var fc = $("#fc").val();

						var permitvalidity = $("#permitvalidity").val();


						var companyname = $("#companyname").val();
						var issueddate = $("#issueddate").val();
						var expirydate = $("#expirydate").val();
						var doneby = $("#doneby").val();

						
					 if (locationname== "") {
							$(".errormessagediv").show();
							$(".validateTips").text("Select Branch Name");

							document.getElementById("locationname").style.border = "1px solid #AF2C2C";
							document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
						
					 else if (vehicleregno .trim() == ""|| vehicleregno.trim() == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required- Registration Number.");

							document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
							document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
						else if (registernumberValidation() == 1) {
                             
							$(".errormessagediv").show();
							$(".validateTips").text("Vehicle Register Number already Exists");

							document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
							document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						} 
						
						else if (vehiclename.trim() == ""|| vehiclename.trim() == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Vehicle Name");

							document.getElementById("vehiclename").style.border = "1px solid #AF2C2C";
							document.getElementById("vehiclename").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						} 
						
						else if (vehicletype.trim() == ""|| vehicletype.trim() == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Vehicle Type");

							document.getElementById("vehicletype").style.border = "1px solid #AF2C2C";
							document.getElementById("vehicletype").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
						
						else if (enginenumber .trim() == ""|| enginenumber .trim()== null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Engine  Number");
							document.getElementById("enginenumber").style.border = "1px solid #AF2C2C";
							document.getElementById("enginenumber").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
					
						
						else if (chassisno.trim() == "") {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Chassis Number");
							document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
							document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						} 
						else if (chassisno.length > 17) {
							$(".errormessagediv").show();
							$(".validateTips").text("Chassis Number length should be less than or equal to 17 characters.");
							document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
							document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
						else if (taxpaid == ""|| taxpaid == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Select Tax Issued Date");

							document.getElementById("taxpaid").style.border = "1px solid #AF2C2C";
							document.getElementById("taxpaid").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
						
						
						else if (fistInput.trim() === secondInput.trim()) {
							$(".errormessagediv").show();
							$(".validateTips").text("Engine and Chassis Number Cannot be Same");
							document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
							document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
					
						else if (pollution == ""|| pollution == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Last Emission Test Date");

							document.getElementById("pollution").style.border = "1px solid #AF2C2C";
							document.getElementById("pollution").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}	

															
						else if (companyname.trim() == ""|| companyname.trim() == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Company Name");
							document.getElementById("companyname").style.border = "1px solid #AF2C2C";
							document.getElementById("companyname").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
						else if (doneby.trim() == ""|| doneby.trim() == null) {
							$(".errormessagediv").show();
							$(".validateTips").text("Field Required - Policy Number");
							document.getElementById("doneby").style.border = "1px solid #AF2C2C";
							document.getElementById("doneby").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);
							return false;
						}
						else if (issueddate == ""	|| issueddate == null) {
						$(".errormessagediv").show();
						$(".validateTips").text("Select Issued Date");
						document.getElementById("issueddate").style.border = "1px solid #AF2C2C";
						document.getElementById("issueddate").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					} else if (expirydate == "" || issueddate == "" || issueddate == null) {
						$(".errormessagediv").show();
						$(".validateTips").text("Select Issued Date");
						document.getElementById("expirydate").style.border = "1px solid #AF2C2C";
						document.getElementById("expirydate").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					} else if (chkdate == true) {
						$(".errormessagediv").show();
						$(".validateTips").text("Vehicle Insurance dates already Exists");
						document.getElementById("expirydate").style.border = "1px solid #AF2C2C";
						document.getElementById("expirydate").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}
					else if (fc == "" || fc == null) {
						$(".errormessagediv").show();
						$(".validateTips").text("Select Fitness Certificate Date");
						document.getElementById("fc").style.border = "1px solid #AF2C2C";
						document.getElementById("fc").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}else if (permitvalidity == ""|| permitvalidity == null) {
						$(".errormessagediv").show();
						$(".validateTips").text("Select Permit Validity Date");
						document.getElementById("permitvalidity").style.border = "1px solid #AF2C2C";
						document.getElementById("permitvalidity").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}
						
					else if (valideDriverCode()) {
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}
					 
					else if(chassisnovalidation()){
					
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}
					 
					
					else if (status == "update"&& updateregisternumberValidation()) {

						$(".errormessagediv").show();
						$(".validateTips").text("Vehicle Register Number already Exists");
						document.getElementById("vehicleregno").style.border = "1px solid #AF2C2C";
						document.getElementById("vehicleregno").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					} else if (status == "update"&& updatechassisnovalidation()) {

						$(".errormessagediv").show();
						$(".validateTips").text("Chassis Number already Exists");
						document.getElementById("chassisno").style.border = "1px solid #AF2C2C";
						document.getElementById("chassisno").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					} else if (checkforduplicateSave()) {
						$(".errormessagediv").show();
						$(".validateTips").text("Vehicle Details Already Exists");

						return false;

					} else if (checkforduplicateupdate()&& status == "update") {
						$(".errormessagediv").show();
	                	$(".validateTips").text("Vehicle Details Already Exists");

						return false;
					} else if (routename = ""&& routename == null) {

						$(".errormessagediv").show();
						$(".validateTips").text("Select Route Name");
						document.getElementById("routename").style.border = "1px solid #AF2C2C";
						document.getElementById("routename").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						return false;
					}
				
					else {

						document.getElementById("myForm").submit();		
					}

					});

					var updateVehicleCode = $("#hvehicleid").val();
					
					if(updateVehicleCode != null || updateVehicleCode != "")
				    {
						
						
						
						var hrcfileid = $("#hrcfileid").val();
						var hinsurancefileid = $("#hinsurancefileid").val();
						if(hinsurancefileid !="" && hinsurancefileid !=undefined){
							$("#document1btn").attr('name',hinsurancefileid);
							
							$("#uploadinsurance").hide();
							$("#document1btn").show();
							$("#deleteProfile").show();
						}
						
						if(hrcfileid !="" && hrcfileid !=undefined){
							$("#document2btn").attr('name',hrcfileid);
							$("#uploadrcfile").hide();
							$("#document2btn").show();
							$("#deleteIDProof").show();
						}
						
						$("#deleteIDProof").click(function(){
						
							$("#uploadrcfile").show();
							$("#document2btn").hide();
							$("#deleteIDProof").hide();
							$("#hrcfileid").val("");
						
						});
						
						$("#deleteProfile").click(function(){
							$("#uploadinsurance").show();
							$("#document1btn").hide();
							$("#deleteProfile").hide();
							$("#hinsurancefileid").val("");
						});
						
						
						$("#uploadrcfile").change(function(){
							var filename = $("#uploadrcfile").val().split(".").pop().toLowerCase();
							var idstatus=true;
							
							if(filename=="jpg" || filename=="png" || filename=="pdf"){
								idstatus=false;
							}
							if(idstatus){
								$("#uploadrcfile").val(""); 
								$("#deleteIDProof").hide();
								 $(".errormessagediv").show();
									$(".validateTips").text("Accepts jpg, png and pdf format Only");
									 $("#uploadrcfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
									 setTimeout(function() {
										 $('.errormessagediv').fadeOut();}, 3000);
							          }
						    });
						
						
						$("#uploadinsurance").change(function(){
							var filename = $("#uploadinsurance").val().split(".").pop().toLowerCase();
							var idstatus=true;
							
							if(filename=="jpg" || filename=="png" || filename=="pdf"){
								idstatus=false;
							}
							if(idstatus){
								$("#uploadinsurance").val(""); 
								$("#deleteProfile").hide();
								 $(".errormessagediv").show();
									$(".validateTips").text("Accepts jpg, png and pdf format Only");
									 $("#uploadinsurance").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
									 setTimeout(function() {
										 $('.errormessagediv').fadeOut();}, 3000);
							          }
						    });
						
						$('.downloadDoc1').click(function() {
									var path = $(this).attr('name');
									
									/*//alert("insurance path "+path);*/
									
									window.location.href = "transport.html?method=downloadInsuranceFile&Path="
											+ path.trim();
								});
						
						
						$('.downloadDoc2').click(
								
								function() {
									
									var path = $(this).attr('name');
									
									/*//alert("rc path "+path);*/
						 			
									window.location.href = "transport.html?method=downloadRcFile&Path="+path.trim();
								});
						
					}
					
					var hiddenfuel = $("#hiddenfuelId").val();
					$("#fuelengine option[value=" + hiddenfuel + "]").attr(
							'selected', 'true');
                     
					var chkdate = false;
		
					if($("#statusId").val()=="update"){
						$("#locationname").val($("#hiddenlocid").val());
						
					}

				});

function registernumberValidation() {

	var regnovalid=0;
	var vehicleregno = $("#vehicleregno").val();
	var status = $("#statusId").val();
	var vehicleId=$("#hvehicleid").val();
	var vehicleregnumber = {
		"vehicleregno" : vehicleregno,
		"locId":$("#locationname").val(),
		"vehicleId":$("#hvehicleid").val()
	};
 
		$.ajax({
			type : 'POST',
			url : "transport.html?method=registernumberValidation",
			data : vehicleregnumber,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
               
				if (result.status=="true") {
					regnovalid=1;
				} else{
					regnovalid = 0;
				}

			}
		});

	 return regnovalid;

}
var regno = null;
function updateregisternumberValidation() {

	var vehicleregno = $("#vehicleregno").val();
	var vehicleCode = $("#updatevehicleCode").val();

	var vehicleregnumber = {
		"vehicleregno" : vehicleregno,
		"vehicleCode" : vehicleCode,
		"locId":$("#locationname").val()
	};

	$.ajax({
		type : 'POST',
		url : "transport.html?method=updateregisternumberValidation",
		data : vehicleregnumber,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);


			if (result.status=="inactive")
			{
				$(".successmessagediv").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("This Vehicle Register No  Already Exist !! Make it Active");
				regno = 1;
			}
			
			if (result.status=="true") {
				
				$(".errormessagediv").show();
				$(".validateTips").text("Vehicle Register No already Exists");
				$("#vehicleregno").val("");
				return false;
				regno=1;

			} else{
				regno = 0;
				$(".errormessagediv1").hide();
			}

		}

	});

	return regno;

}

function clearAll() {

	$("#vehicleregno").val("");
	$("#vehicletype").val("");
	$("#typeofbody").val("");
	$("#makersname").val("");
	$("#manufacturerdate").val("");
	$("#chassisno").val("");
	$("#settingcapacity").val("");
	$("#fuelengine").val("");
	$("#colorofbody").val("");

	$("#vehiclename").val("");

	/*
	 * var vehiclename = $( "#vehiclename") .val();
	 */
	$("#companyname").val("");
	$("#issueddate").val("");
	$("#expirydate").val("");
	$("#doneby").val("");
}
function chassisnovalidation() {

	var chas = null;
	var chassisno = $("#chassisno").val();
	var status = $("#statusId").val();

	var chassisnumber = {
		"chassisno" : chassisno,
		"locId":$("#locationname").val()
	};
	
	if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=chassisnovalidationvalidation",
			data : chassisnumber,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status) {
					chas = true;

					$(".errormessagediv").show();
					$(".validateTips").text("Chassis No already Exists");
					return false;

				} else {
					chas = false;

					$(".errormessagediv").hide();
				}

			}

		});
	}

	return chas;

}

var updatechass = null;
function updatechassisnovalidation() {
	var updatechassisno = $("#chassisno").val();
	var vehicleCode = $("#hvehicleid").val();

	var chassisnumber = {
		"chassisno" : updatechassisno,
		"vehicleCode" :vehicleCode,
		"locId":$("#locationname").val()
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=updatechassisnovalidation",
		data : chassisnumber,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			if (result.status) {
				updatechass = true;


			} else {
				updatechass = false;
				

			}

		}

	});

	return updatechass;

}

function checkforduplicateSave() {

	var status = $("#statusId").val();

	var isduplicate = null;

	var vehicleregno = $("#vehicleregno").val();
	var vehiclename = $("#vehiclename").val();
	var enginenumber = $("#enginenumber").val();
	var chassisno = $("#chassisno").val();
	var vehicletype = $("#vehicletype").val();
	var taxpaid = $("#taxpaid").val();
	var pollution = $("#pollution").val();
	////alert(taxpaid);

	var vehicledata = {
		"vehicleregno" : vehicleregno,
		"vehiclename" : vehiclename,
		"enginenumber" : enginenumber,
		"chassisno" : chassisno,
		"vehicletype" : vehicletype,
		"taxpaid" : taxpaid,
		"pollution":pollution,
		"locId":$("#locationname").val()
	};
	if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=checkforduplicateAddTime",
			data : vehicledata,
			async : false,

			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status) {
					isduplicate = true;

				} else {
					isduplicate = false;

				}

			}

		});
	}

	return isduplicate;

}

var isduplicateupdate = null;
function checkforduplicateupdate() {

	var vehicleCode = $("#hvehicleid").val();
	
	var vehicleregno = $("#vehicleregno").val();
	var vehiclename = $("#vehiclename").val();
	var enginenumber = $("#enginenumber").val();
	var chassisno = $("#chassisno").val();
	var vehicletype = $("#vehicletype").val();
	var taxpaid = $("#taxpaid").val();
	var pollution = $("#pollution").val();
	
	if($("#hvehicleno").val()!=vehicleregno){
	
	var vehicledata = {
		"vehicleregno" : vehicleregno,
		"vehiclename" : vehiclename,
		"enginenumber" : enginenumber,
		"chassisno" : chassisno,
		"vehicletype" : vehicletype,
		"taxpaid" : taxpaid,
		"pollution" : pollution,
		"vehicleCode" : vehicleCode,
		"locId":$("#locationname").val()
	};
	
	$.ajax({
		type : 'POST',
		url : "transport.html?method=checkforduplicateUpdateTime",
		data : vehicledata,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);

			if (result.status) {
				isduplicateupdate = true;

			} else {
				isduplicateupdate = false;

			}

		}

	});

	return isduplicateupdate;
  }
}

function getDriverEntireDetails() {
	var dataval = {
		"driverid" : $('#drivername').val(),
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getDriverEntireDetails",
		data : dataval,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$('#experience').val(result.driverlist[0].experience);
			$('#licencedrive').val(result.driverlist[0].license);
			$('#phoneno').val(result.driverlist[0].mobile);
			$('#doj').val(result.driverlist[0].dateofJoin);
			$('#dlno').val(result.driverlist[0].drivingliecenseNo);
			$('#dlexpiray').val(result.driverlist[0].dl_validity);
            
		}
	});
}
function showdriverdetails()
{
	
	$("#drivername").change(function(){
		
		if($(this).val().trim()==""){
			$("#experience").val("");
			$("#dlexpiray").val("");
			$("#dlno").val("");
			$("#phoneno").val("");
			$("#doj").val("");
			$("#licencedrive").val("");
			
		}
		else{
			getDriverEntireDetails();
		}
	});
	
	}


function getRouteEntireDetails() {

	var dataval = {
		"routeid" : $('#routename').val(),
	};
	$.ajax({
		type : 'POST',
		url : "transport.html?method=GetRouteEntireDetails",
		data : dataval,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#totalstops').val(result.routelist[0].totalStops);
			$('#totaldistance').val(result.routelist[0].totalDistance);
			$('#routeno').val(result.routelist[0].routeCode);
			$('#halttime').val(result.routelist[0].halttime);
			
		}
	});
}

function driverCode(){		
	 var location="";
	 var location=$("#locationname").val();
	
	var datalist = {
			"locid" : location,
	    };
	
	$.ajax({
		type : 'POST',
		url : "transport.html?method=getDriverDetails",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#drivername').html("");
			$('#drivername').append(
					'<option value="' + "" + '">'
					+ "-----Select-----"
					+ '</option>');
			for ( var j = 0; j < result.drivernamelist.length; j++) {
				$('#drivername').append(
						'<option value="'
						+ result.drivernamelist[j].driverCode
						+ '">'
						+ result.drivernamelist[j].driverName
						+ '</option>');
			}
			
		}
	});
}

function routeCode(){
    var location="";
	if($("#locationname").val()!="" && $("#locationname").val()!=null){
		location=$("#locationname").val();
	}
	if($("#hiddenlocid").val()!="" && $("#hiddenlocid").val()!=null){
		location=$("#hiddenlocid").val();
	}
     
	 var datalist = {
			"locid" : location,
	};
	
$.ajax({
	type : 'POST',
	url : "transport.html?method=getrouteDetails",
	data : datalist,
	async : false,
	success : function(response) {
		var result = $.parseJSON(response);
		$('#routename').html("");
		$('#routename').append(
				'<option value="' + "" + '">'
						+ "-----Select-----"
						+ '</option>');
		for ( var j = 0; j < result.routelist.length; j++) {
			$('#routename')
					.append(
							'<option value="'
									+ result.routelist[j].routeCode
									+ '">'
									+ result.routelist[j].routeName
									+ '</option>');
		}
		
		$('#routename').val($('routecodeid').val());

	}
});
}

function valideDriverCode() {
    var regno = false;
	var drivername = $("#drivername").val();
	var status = $("#statusId").val();
    var hiddendriverId=$("#driverCode").val();

	if (status == "update" && hiddendriverId != drivername) {

		$.ajax({
			type : 'POST',
			url : "transport.html?method=valideDriverCode",
			data : {
				"drivername":$("#drivername").val(),
				"locId":$("#locationname").val()
			},
			 
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
					
				if (result.status=="true") {
					
					$(".errormessagediv").show();
					$(".validateTips").text("Driver Name Already Mapped..");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					regno=true;
					return false;
				} 
				else{
					regno=false;
					$(".errormessagediv").hide();
				}
			}

		});
	}
	
	else if (status != "update") {
		$.ajax({
			type : 'POST',
			url : "transport.html?method=valideDriverCode",
			data : {  
				"drivername":$("#drivername").val(),
				"locId":$("#locationname").val()
			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);

				if (result.status=="true") {
					
					$(".errormessagediv").show();
					$(".validateTips").text("Driver Name Already Mapped..");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					regno=true;
					return false;
					

				} else{
					regno=false;
					$(".errormessagediv").hide();
				}
			}

		});
	}
	else{
		regno=false;
	}

	return regno;
}


function CheckIsNumeric(objEvt) {
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if (charCode > 31 && charCode != 46 && charCode != 45 &&(charCode < 48 || charCode > 57)) {
        
        return false;
    }
    else {
        
        return true;
    }
}

function isValidNumber($Number){
    var regex = new RegExp(/^[A-Z]{2}[ -]{0,1}[0-9]{1,2}[ ]{0,1}(?: [A-Z])?(?: [A-Z]*)? [ ]{0,1}[0-9]{4}$/);
    if (regex.test($Number)){
       return false;
    }
    else {
       return true;
   }
}

function removeMessage() { 

	$(".successmessagediv").hide();

}
function HideError(pointer){
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
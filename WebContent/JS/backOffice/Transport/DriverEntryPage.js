
function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
}
$(document).ready(function() {
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=driverList&historylocId="+$("#historylocId").val()+
		"&historystatus="+$("#historystatus").val()+"&historysearch="+$("#historysearch").val();
	}); 
	
	$("#locationname").val($("#locId").val());
	
	hiddenexperience="";
	if($("#hiddenexperience").val().trim()!="" || $("#hiddenexperience").val()!=undefined){
		hiddenexperience=$("#hiddenexperience").val();
	}
	$("#experience").val(hiddenexperience);
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if (pageUrl == "driverDetailsPath.html?method=savedriverval"){
		$(".errormessagediv").hide();
		$(".successmessagediv").show();
		 setTimeout(function(){
			 window.location.href = "menuslist.html?method=driverList";
		 },3000);
	}
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	
var licensetodrive = $('#hlicensetodrive').val();
	
if(licensetodrive!=null)
  {
    var licensetodrive1 = licensetodrive.split(",");
	var s1 = licensetodrive1[0];
	var s2 = licensetodrive1[1];
	var s3 = licensetodrive1[2];
	
	$("#vehicletodrive option[value="+s1+"]").attr("selected",'true');
	$("#vehicletodrive option[value="+s2+"]").attr("selected",'true');
	$("#vehicletodrive option[value="+s3+"]").attr("selected",'true');
  }
	
	
	
var trans = $('#radio').val();
	
	if (trans == "Male") {
		$("#genderM").attr("checked", true);
	} else if (trans == "Female") {
		$("#genderF").attr("checked", true);
	}
	
	
	
	
	$("#dateofBirthId").datepicker({
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$("#todate").datepicker("option", "minDate", selectedDate);
		}
	});
	
	
	$("#dateofJoinId").datepicker({
		dateFormat : "dd-mm-yy",
		yearRange : 'c-65:c+65',
		maxDate : 0,
		changeMonth : "true",
		changeYear : "true",
		onClose : function(selectedDate) {
			$("#todate").datepicker("option", "minDate", selectedDate);
		}
	});
	
	$("#dl_validity").datepicker(
			{
				dateFormat : "dd-mm-yy",
				yearRange : 'c-65:c+65',
				minDate  : 0,
				changeMonth : "true",
				changeYear : "true",
				onClose : function(selectedDate) {
					var date2 = $('#dl_validity').datepicker('getDate');
					date2.setDate(date2.getDate() - 1);
					$("#dl_issued_date").datepicker("option","minDate", date2);
				}
			});
	
	/*$("#name").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Text."); 
			document.getElementById("name").style.border = "1px solid #AF2C2C";
			document.getElementById("name").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").delay(2000).fadeOut();
			return false;
		} 
	});*/
	
	$('#name').keypress(function (e) {
        var regex = new RegExp(/^[a-zA-Z\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").show();
        document.getElementById("name").style.border = "1px solid #AF2C2C";
		document.getElementById("name").style.backgroundColor = "#FFF7F7";
		$(".validateTips").text("Enter Only Text.");
		setTimeout(function() {
			$('#errormessagediv1').fadeOut();
		    },3000);
        return false;
    });
	
	$("#mobile").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("mobile").style.border = "1px solid #AF2C2C";
			document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$("#uploadlicence").change(function(){
		var filename = $("#uploadlicence").val().substr($("#uploadlicence").val().length - 3).trim();
		 
		var status=true;
		
		if(filename=="pdf" || filename=="jpg" || filename=="png"){
			status=false;
		}
		
		if(status){
			document.getElementById("uploadlicence").style.border = "1px solid #AF2C2C";
			document.getElementById("uploadlicence").style.backgroundColor = "#FFF7F7";
			$("#uploadlicence").val("");
			$(".errormessagediv").show();
			$(".validateTips").text("Select file with only png , jpg and pdf formats.");
			setTimeout(function() {
				$(".errormessagediv").fadeOut();
			}, 3000);
		  }
	});
	
	/*$("#father_name").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Text."); 
			document.getElementById("father_name").style.border = "1px solid #AF2C2C";
			document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").delay(2000).fadeOut();
			return false;
		  }
		});*/
	
	$('#father_name').keypress(function (e) {
        var regex = new RegExp(/^[a-zA-Z\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").show();
        document.getElementById("father_name").style.border = "1px solid #AF2C2C";
		document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
		$(".validateTips").text("Enter Only Text.");
		setTimeout(function() {
			$('#errormessagediv1').fadeOut();
		    },3000);
        return false;
    });
	
	$('#drivingliecenseNo').keypress(function (e) {
        var regex = new RegExp(/^[a-zA-Z0-9-&.(\][)\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").show();
		$(".validateTips").text("Allows only alphanumerics and -, (, ), [, ], &");
		setTimeout(function() {
			$('#errormessagediv1').fadeOut();
		    },3000);
        return false;
    });
	
	$('#experience').keypress(function (e) {
        var regex = new RegExp(/^[0-9.\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").show();
		$(".validateTips").text("Allows only numerics and .");
		setTimeout(function() {
			$('#errormessagediv1').fadeOut();
		    },3000);
        return false;
    });
	
	$("#emerg_contact").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 45 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
			document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	 });
		
	$("#savedriver").click(function(){
            var drivercode = $('#drivercode').val();
			var name =  $("#name").val();
			var fatherName =  $("#father_name").val();
			var dob =  $("#dateofBirthId").val();
			var gender=$('input[name=gender]:checked').val();
			var mobile = $("#mobile").val();
			var emerg_contact = $("#emerg_contact").val();
			var enq_dateofJoin = $("#dateofJoinId").val();
			var exp = $("#experience").val();
			var address = $("#address").val();
			var drivingLicenseNo = $("#drivingliecenseNo").val();
			var dlValidity = $("#dl_validity").val();
			var age = $("#ageId").val();
			var locList = $("#vehicletodrive").val();

			var mob = parseFloat($('#mobile').val());
			var ecmob = parseFloat($('#emerg_contact').val());
			var fileName=$("#uploadlicence").val().split('.').pop().toLowerCase();
			var fileNameCheck=$("#uploadlicence").val();
			var locId=$("#locationname").val();
			var driverExperience=age-18;
			
			
			if(locId==undefined || locId=="all"){
				  $(".errormessagediv").show();
					$(".validateTips").text("Field Required - Branch Name");
					
					document.getElementById("locationname").style.border = "1px solid #AF2C2C";
					document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			}
			else if(name==null || name==""){
				  $(".errormessagediv").show();
					$(".validateTips").text("Field Required - Driver Name");
					
					document.getElementById("name").style.border = "1px solid #AF2C2C";
					document.getElementById("name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			}
			else if(!name.match(/[A-Za-z]+$/i))
			{
				 $(".errormessagediv").show();
				$(".validateTips").text("Driver Name Should Be Alphabet");
				document.getElementById("name").style.border = "1px solid #AF2C2C";
				document.getElementById("name").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else if(fatherName==null || fatherName==""){
				 $(".errormessagediv").show();
			    $(".validateTips").text("Field Required - Father's Name");
			    document.getElementById("father_name").style.border = "1px solid #AF2C2C";
				document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false; 
			}
			else if(!fatherName.match(/[A-Za-z]+$/i))
			{
				 $(".errormessagediv").show();
				 $(".validateTips").text("Father's Name Should Be Alphabet");
				 document.getElementById("father_name").style.border = "1px solid #AF2C2C";
					document.getElementById("father_name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(dob==null || dob==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Date Of Birth");
				 document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
				
			}
			else if(age < 18){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Age Should be 18 or Older");
				 document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(mobile==null || mobile==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Mobile Number");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(!mobile.match(/^([0-9])+$/i)){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Invalid Mobile Number");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(mobile.length < 10){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Invalid Mobile Number");
				 document.getElementById("mobile").style.border = "1px solid #AF2C2C";
					document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}else if (isNaN(mob) || (mob === 0)) {
				
				$('.errormessagediv').show();
				$('.validateTips').text("Invalid Mobile Number");
				document.getElementById("mobile").style.border = "1px solid #AF2C2C";
				document.getElementById("mobile").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);

				return false;
			}
			else if(gender==null || gender==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Gender");
				return false;
			}
			else if(enq_dateofJoin==null || enq_dateofJoin==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Date of Joining");
				 document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(parseInt(enq_dateofJoin.split("-")[2])-parseInt(dob.split("-")[2])<18){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter valid Date Of Joining");
				 document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
					document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(emerg_contact==null || emerg_contact==""){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Emergency Contact Number");
				 document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
					document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(emerg_contact.length < 10){
				
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter valid Emergency Contact Number");
				 document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
					document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if (isNaN(ecmob) || (ecmob === 0)) {
				
				$('.errormessagediv').show();
				$('.validateTips').text("Enter valid Emergency Contact Number");
				document.getElementById("emerg_contact").style.border = "1px solid #AF2C2C";
				document.getElementById("emerg_contact").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);

				return false;
			}
			else if(address==null || address.trim()==""){
				 $(".errormessagediv").show();
			    $(".validateTips").text("Field Required - Address");
			    document.getElementById("address").style.border = "1px solid #AF2C2C";
				document.getElementById("address").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else if(exp==null || exp==""){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Field Required - Experience");
				 document.getElementById("experience").style.border = "1px solid #AF2C2C";
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}

			else if(!exp.match("^[0-9.]+$")){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Experience Sholud be Numeric");
				 document.getElementById("experience").style.border = "1px solid #AF2C2C";
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			else if(exp > parseFloat(driverExperience)){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter Valid Experience");
				 document.getElementById("experience").style.border = "1px solid #AF2C2C";
					document.getElementById("experience").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			
			else if(drivingLicenseNo==null || drivingLicenseNo.trim()==""){
				 
				 $(".errormessagediv").show();
					$(".validateTips").text("Field Required - Driving License Number");
					document.getElementById("drivingliecenseNo").style.border = "1px solid #AF2C2C";
					document.getElementById("drivingliecenseNo").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					
					return false;
				}
			else if(drivingLicenseNo.length < 10){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Enter Valid Driving License No");
				 document.getElementById("drivingliecenseNo").style.border = "1px solid #AF2C2C";
					document.getElementById("drivingliecenseNo").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				return false;
			}
			
		  else if(fileName == "exe" || fileName == "ini" || fileName == "html" || fileName == "htm" || fileName == "zip"){
				$(".errormessagediv").show();
				$(".validateTips").text("Invalid file!");
				document.getElementById("uploadlicence").style.border = "1px solid #AF2C2C";
				document.getElementById("uploadlicence").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			
			else if(dlValidity==null || dlValidity==""){
				  $(".errormessagediv").show();
					$(".validateTips").text("Field required - DL Validity Upto");
					document.getElementById("dl_validity").style.border = "1px solid #AF2C2C";
					document.getElementById("dl_validity").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			
			else if(locList==null || locList==""){
				  $(".errormessagediv").show();
					$(".validateTips").text("Field required - License To Drive");
					document.getElementById("vehicletodrive").style.border = "1px solid #AF2C2C";
					document.getElementById("vehicletodrive").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			/*else if(validateDriver()== 1){
				 $(".errormessagediv").show();
					$(".validateTips").text(" Driver already exists");
					document.getElementById("name").style.border = "1px solid #AF2C2C";
					document.getElementById("name").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			        }*/
			
			
			else if(validateLicense() == 1 ) {
                $(".errormessagediv").show();
				$(".validateTips").text("License No already exists");
				document.getElementById("drivingliecenseNo").style.border = "1px solid #AF2C2C";
				document.getElementById("drivingliecenseNo").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else{
				//document.getElementById("driverformid").submit();
				var form = $('#driverformid')[0];
				var data = new FormData(form);
				
				$.ajax({
					type : "POST",
					url : "driverDetailsPath.html?method=savedriverval",
					data :data,
					async : false,
					cache: false,
					contentType: false,
					processData: false,
					success : function(data) {
						var result = $.parseJSON(data);
						if(result.status=="success1")
						{
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Adding Record Progressing...");
						}
						else if(result.status=="success2")
						{
								$(".successmessagediv").hide();
							 	$(".errormessagediv").show();
								$(".validateTips").text("Driver Not Added Successfully");
						}
						else if(result.status=="success3")
						{
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".validateTips").text("Updating Record Progressing...");
						}else if(result.status=="success4"){
							$(".successmessagediv").hide();
						 	$(".errormessagediv").show();
							$(".validateTips").text("Driver Not Updated Successfully");
						}
						
						setTimeout(function() {
							window.location.href="menuslist.html?method=driverList";
						}, 2000);
					}
				});
			}
		/*	datalist = {
				"drivercode" : drivercode,
				 "name" : name,
				 "fatherName" : fatherName,
					"dob" : dob ,
					"gender" : gender ,
					"mobile" : mobile ,
					 "emerg_contact" : emerg_contact,
					"enq_dateofJoin" : enq_dateofJoin,
					"exp" : exp,
					 "address" : address,
				    "drivingLicenseNo" : drivingLicenseNo,
				    "dl_issued_date" :  dl_issued_date,
				     "dlValidity" : dlValidity,
					"age" : age ,
					"license" : locList.join(",")
						
			};*/
			
			/*$.ajax({
				type : 'POST',
				url : "driverDetailsPath.html?method=saveDriver",
				data : datalist ,
				async : false,
				success : function(data){
					
					
					var result = $.parseJSON(data);
					
				
					
					if(result.jsonResponse=="Driver Created Successfully")
						{
						
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						 $(".validateTips").text("Driver Created Successfully");
						 
						 setTimeout(function(){
							 
						 location.reload();
						 
						 },5000);
						
						}
					
					
					 if(result.jsonResponse=="Driver Update Successfully")
					{
					
						$(".errormessagediv").hide();
					    $(".successmessagediv").show();
					    $(".validateTips").text("Driver Update Successfully");
					    
					     setTimeout(function(){
					    	 
					     window.location.href="menuslist.html?method=driverget";
					     
						 },7000);
						
						}
					    
					    
					 setTimeout(function(){
							
						 location.reload();
					 
					 },3000); 
					    
					
				
					}
					
					
				//}
			
			
			});*/
			
			/*}*/	
			
	
			/* setTimeout(function(){
				
				 location.reload();
			 
			 },3000);*/
			
			
	});
	
	
/*$("#uploadlicence").attr('val',$('#').val());

	
	if ($('#hlicenseupload').val() == "") {

		$('#downloadlicenceid').hide();
		
		$('#deleteProfile').hide();

	  }
		else 
		{
			
     	$('#downloadlicenceid').show();
		$('#deleteProfile').show();

     	
    	}
	
	
	$("#downloadlicenceid").click(function(){
		
		window.location.href = "driverDetailsPath.html?method=downloaddriverlicence&filepath="
				+ $('#uploadlicence').attr('val');
			
			var filepath = {'filepath':$('#fileupload').attr('name'),
					         'sno':$('#sno').val()};
			//alert(JSON.stringify(filepath));
			var result = callAjax("LeaveStatus.do?parameter=download",filepath);
		});
	*/
	
	
	
	
	var birthcertificate=$("#hlicenseupload").val();
	
	
	if(birthcertificate!="" && birthcertificate!=undefined){
		
		$("#downloadlicenceid").attr('name',birthcertificate);
		
		$("#uploadlicence").hide();
		$("#downloadlicenceid").show();
		$("#deleteProfile").show();
		
		}
	else
		{
		$("#uploadlicence").show();
		$("#downloadlicenceid").hide();
		$("#deleteProfile").hide();
		}
	
		
		
		$('.downloadDoc1').click(function() {
					var path = $(this).attr('name');
					window.location.href = "driverDetailsPath.html?method=downloaddriverlicenc&Path="+ path.trim();
				});
		
		$("#deleteProfile").click(function(){
			$("#uploadlicence").show();
			$("#downloadlicenceid").hide();
			$("#deleteProfile").hide();
			$("#hlicenseupload").val("");
		});
		
});

function ageCalculateAdd() {
	var doofBirth = $('#dateofBirthId').val();
	var birthYear = doofBirth.split("-")[2];
	var currentYear = new Date().getFullYear();
	var yearDiff = parseInt(currentYear) - parseInt(birthYear);
	$('#ageId').val(yearDiff);
}

function validateDriver(){
	var driver_validate=0;
	var name =  $("#name").val();
	var dob =  $("#dateofBirthId").val();
	var mobile = $("#mobile").val();
	var enq_dateofJoin = $("#dateofJoinId").val();
	 var drivercode = $('#drivercode').val();
	 var streamObject = {
				"name" : name,  "dob" : dob,  "mobile" : mobile,"enq_dateofJoin" : enq_dateofJoin ,"drivercode" : drivercode	};
	 
	 $.ajax({

			type : "POST",
			url : 'driverDetailsPath.html?method=validateDriver',
			data : streamObject,
			async : false,
			success : function(data) {
				
		    var result = $.parseJSON(data);
			if (result.status=="true") {
				driver_validate = 1;
				}
				else 
				{
					driver_validate = 0;
				}
			}
		});
		return driver_validate;
}


// for license validation//

function validateLicense(){
	var License_validate=0;
	if($("#hlicense").val()==$("#drivingliecenseNo").val()){
		License_validate=0;
	}else{
		var drivingliecenseNo =  $("#drivingliecenseNo").val().trim();

		streamObject = {
				"drivingliecenseNo" : drivingliecenseNo,
				          "locId"   : $("#locationname").val()
		 },

		$.ajax({
			type : "POST",
			url : 'driverDetailsPath.html?method=validateLicense',
			data : streamObject,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				if (result.status == true) {
					License_validate = 1;
				}
				else 
				{
					License_validate = 0;
				}
			}
		});
	}
	return License_validate;
}

function HideError(pointer) 
{
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

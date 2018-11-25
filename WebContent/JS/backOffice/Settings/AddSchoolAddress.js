function readURL(input,id) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			id.attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	}
}

$(document).ready(function(){
	
	$("#boardimagePreview").hide();
	$("#schoolimagePreview").hide();
	
	$(".errormessagediv").hide();
	$("successmessagediv").hide();
	
	
	$("#contactno").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("contactno").style.border = "1px solid #AF2C2C";
			document.getElementById("contactno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	    }
	});
	
	$("#landlineno").keypress(function(e){
		 
		if (e.which != 8 && e.which != 0 && (e.which < 45 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		if($("#landlineno").val().indexOf('-') > 1){
			if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
				$(".errormessagediv").show();
				$(".validateTips").text("Enter Only Number.");
				document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
				document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
		}
	});
	
	var bordlogo=$("#hiddenboardlogoId").val();
	
	if(bordlogo!=""){
		
		$("#downloadlicenceid").attr('name',bordlogo);
		
		$("#boardlogo").hide();
		
		$("#downloadlicenceid").show();
		$("#deleteProfile").show();
		
		if($("#hiddenboardlogoId").val()!=""){
		     $("#boardimagePreview").show();
		}
		
		$("#boardimagePreview").attr('src',$("#hiddenboardlogoId").val());
		
		
	}else{
		$("#downloadlicenceid").hide();
		$("#deleteProfile").hide();
		$("#boardimagePreview").hide();
	}
	
	$('.downloadDoc1').click(function() {
		var path = $(this).attr('name');
		window.location.href = "locationDetails.html?method=downloadBoardLogo&Path="+ path.trim();
	});
	
	$("#deleteProfile").click(function(){
		$("#boardlogo").show();
		$("#downloadlicenceid").hide();
		$("#deleteProfile").hide();
		$("#hiddenboardlogoId").val("");
		$("#boardimagePreview").hide();
	});
	
	 $('#locationname').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-_&.(\][)\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, _ , (, ), [, ], &");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
	 
	 $('#board').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-_&.(\][)\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, _ , (, ), [, ], &");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
	 
	 $('#affilno').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-_&.(\][)\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, _ , (, ), [, ], &");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
	
	 $('#schoolcode').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-_&.(\][)\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, _ , (, ), [, ], &");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
	
	 $('#pincode').keypress(function (e) {
	        var regex = new RegExp(/^[a-zA-Z0-9-.\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics and -, .");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
	    });
 
	 $('#back11').click(function() {
		 window.location.href="menuslist.html?method=schoolLocation";
	 });
	
	$('#saveid').click(function(event) {
       
		event.preventDefault();
		var locname = $("#schollid").val();
		var locAddId = $("#locAddId").val();
		var board = $("#board").val();
		var affilno = $("#affilno").val();
		var schoolcode = $("#schoolcode").val();
		/*var website = $("#website").val();*/
		var emailId = $("#emailId").val();
		var contactno = $("#contactno").val();
		var address = $("#address").val();
		var schoollogo = $("#schoollogo").val();
		var boardlogo = $("#boardlogo").val();
		var location_code = $("#updatelocationid").val().trim();
		
		var landlineno= $("#landlineno").val();
		var address2=$("#address2").val();
		var countryId=$("#countryId").val(); 
		var stateId=$("#stateId").val(); 
		var cityId=$("#cityId").val();
		var pincode=$("#pincode").val();
		
		var convalue=true;
		var pincodevalid=false;
		
		
		if(landlineno.indexOf('-') != -1){
			var conlen=landlineno.split('-')[1].length;
			convalue=false;
		}
		 
		if(pincode.indexOf('-') == 0){
			pincodevalid=true;
		}else if(pincode.indexOf(' ') == 0){
			pincodevalid=true;
		}
		 

		if (locname == "all" || locname == null || locname == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Branch Name");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		} 
		else if (locAddId.trim() == "all" || locAddId == null || locAddId == "") {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Location Name");
			document.getElementById("locAddId").style.border = "1px solid #AF2C2C";
			document.getElementById("locAddId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}
		else if(!locAddId.match(/^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/)) {

			$('.errormessagediv').show();
			$('.validateTips').text("Invalied Location Name");
			document.getElementById("locAddId").style.border = "1px solid #AF2C2C";
			document.getElementById("locAddId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}
		else if(locAddId.length <3){
			$('.errormessagediv').show();
			$('.validateTips').text("Location Name should Contain Atleast 3 Characters");
			document.getElementById("locAddId").style.border = "1px solid #AF2C2C";
			document.getElementById("locAddId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(locAddId.length >50 ){
			$('.errormessagediv').show();
			$('.validateTips').text("Location Name Too Long!!!");
			document.getElementById("locAddId").style.border = "1px solid #AF2C2C";
			document.getElementById("locAddId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		else if (validateDeptName() == 10) {

			$('.errormessagediv').show();
			$('.validateTips').text("This School Already Exist !! Make it Active");
			return false;
		}
		else if (validateDeptName() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("School Name Already Exist");
			return false;
		}

		else if (validateLocNameUpdate() == 10) {

			$('.errormessagediv').show();
			$('.validateTips').text("This School Already Exist !! Make it Active");
			return false;
		}
		else if (validateLocNameUpdate() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("School Name already Exist");
			return false;
		}
		else if (board.trim() == "" || board == null || board==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Affiliated to");
			document.getElementById("board").style.border = "1px solid #AF2C2C";
			document.getElementById("board").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (schoolcode.trim() == "" || schoolcode == null || schoolcode==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - School Code ");
			document.getElementById("schoolcode").style.border = "1px solid #AF2C2C";
			document.getElementById("schoolcode").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (emailId.trim() == "" || emailId == null || emailId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Email Id");
			document.getElementById("emailId").style.border = "1px solid #AF2C2C";
			document.getElementById("emailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if ((emailId.trim() == "" && emailId == null && emailId==undefined)&&!emailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/))
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email");
			document.getElementById("emailId").style.border = "1px solid #AF2C2C";
			document.getElementById("emailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else if (contactno.trim()=="" || contactno==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Contact No. Sholud not be empty");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (contactno.length!=10) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Contact No.");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (landlineno.indexOf('-') != -1 && landlineno.indexOf('-')<3 && (landlineno!="" || landlineno!=undefined)) {
			$(".errormessagediv").show();
			$(".validateTips").text("STD code should contain atleast 3 numbers");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		/*else if (landlineno.indexOf('-') != -1 && landlineno.indexOf('-')>7 && (landlineno!="" || landlineno!=undefined)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Land Line No");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (landlineno.indexOf('-') != -1 && conlen<6 && (landlineno!="" || landlineno!=undefined)) {
			$(".errormessagediv").show();
			$(".validateTips").text("valid Land Line No. number should contain atleast 6 numbers");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (landlineno.indexOf('-') != -1 && conlen>8 && (landlineno!="" || landlineno!=undefined)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid valid Land Line No.");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (convalue && (landlineno.length==11 || landlineno.length>12 || landlineno.length<10) && (landlineno!="" || landlineno!=undefined)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Land Line No.");
			document.getElementById("landlineno").style.border = "1px solid #AF2C2C";
			document.getElementById("landlineno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}*/
		else if (address.trim() == "" || address == null || address==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Address");
			document.getElementById("address").style.border = "1px solid #AF2C2C";
			document.getElementById("address").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		/*else if ((schoollogo.trim() == "" || schoollogo == null || schoollogo==undefined) && ($("#hiddenschoollogoId").val()=="" || $("#hiddenschoollogoId").val()==null)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select School Logo");
			document.getElementById("schoollogo").style.border = "1px solid #AF2C2C";
			document.getElementById("schoollogo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}*/
		else if (countryId == "" || countryId == null || countryId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Country");
			document.getElementById("countryId").style.border = "1px solid #AF2C2C";
			document.getElementById("countryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (stateId == "" || stateId == null || stateId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - State");
			document.getElementById("stateId").style.border = "1px solid #AF2C2C";
			document.getElementById("stateId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (cityId == "" || cityId == null || cityId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - City/District");
			document.getElementById("cityId").style.border = "1px solid #AF2C2C";
			document.getElementById("cityId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (pincode.trim()== "" || pincode == null || pincode==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Pin Code.");
			document.getElementById("pincode").style.border = "1px solid #AF2C2C";
			document.getElementById("pincode").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (pincodevalid) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Pin Code.");
			document.getElementById("pincode").style.border = "1px solid #AF2C2C";
			document.getElementById("pincode").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else {
			var formdata;
				formdata = new FormData();
			  /*file1=$("#schoollogo")[0].files[0];*/
			  file2=$("#boardlogo")[0].files[0];
			  /*if(file1 !=undefined ){
				  formdata.append("schoollogo",file1);
				 // formdata.append("schoollogo",$("#hiddenschoollogoId").val().substring($("#hiddenschoollogoId").val().lastIndexOf("/")+1,$("#hiddenschoollogoId").val().length));
			  }*/

			  if(file2!=undefined ){
				  formdata.append("boardlogo",file2);
				  //formdata.append("boardlogo",$("#hiddenboardlogoId").val().substring($("#hiddenboardlogoId").val().lastIndexOf("/")+1,$("#hiddenboardlogoId").val().length));  
			  }
			
			  
			formdata.append("locationname",locname);
			formdata.append("affilno",affilno);
			formdata.append("board",board);
			formdata.append("address",address); 
			formdata.append("schoolcode",schoolcode);
			formdata.append("locAddId",locAddId); 
			formdata.append("emailId",emailId); 
			formdata.append("contactno",contactno); 
			formdata.append("update_loc",location_code);
			
			formdata.append("operation",$("#operation").val());
			formdata.append("hiddenschoollogoId",$("#hiddenschoollogoId").val());
			formdata.append("hiddenboardlogoId",$("#hiddenboardlogoId").val());
			
			formdata.append("landlineno",landlineno);
			formdata.append("address2",address2);
			formdata.append("countryId",countryId);
			formdata.append("stateId",stateId);
			formdata.append("cityId",cityId);
			formdata.append("pincode",pincode);
			formdata.append("hiddenaddId",$("#hiddenaddId").val());  
			formdata.append("hiddenlocaddressId",$("#hiddenlocaddressId").val());
			 $.ajax({
			        type: "POST",
			        url: "locationDetails.html?method=insertLocationAddress",
			        data: formdata,
			        cache:false,
					contentType: false,
					processData: false,
					beforeSend: function(){
						$("#loder").show();
					},
			        success : function(data) {
						var result = $.parseJSON(data);
						if(result.status == "fail"){
							$("#loder").hide();
							$('.errormessagediv').show();
							$(".successmessagediv").hide();
							$(".validateTips").text("Failed to save record...");
						}else{
							$("#loder").hide();
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".successmessage").text(result.status);
						}
						setTimeout(function() {
							window.location.href="menuslist.html?method=schoolLocation";
						}, 3000);
					}
			    });
			 
			return false;
		}
	});
	
	
	$("#back1").click(function() {
		window.location.href = "menuslist.html?method=schoolLocation";
	});
	
	$("#schoollogo").change(function() {
		var fileval = $("#schoollogo").val();

		if (fileval != undefined && fileval != ''){
			var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
			
			if(extval != "jpeg" && extval != "jpg" && extval != "png"){
				$('.errormessagediv').show();
				$('.validateTips').text("School Logo Image accepts only .jpg & .png format");
				document.getElementById("schoollogo").style.border = "1px solid #AF2C2C";
				document.getElementById("schoollogo").style.backgroundColor = "#FFF7F7";
				setTimeout(	function() {
					$('#errorhover').fadeOut();
					document.getElementById("schoollogo").style.border = "1px solid #ccc";
					document.getElementById("schoollogo").style.backgroundColor = "#fff";
				}, 500);
				$('.errormessagediv').fontSize = "25px";
				$("#schoollogo").val("");
				$("#schoolimagePreview").hide();
				return false;
			}else{
				$("#schoolimagePreview").show();
				readURL(this,$("#schoolimagePreview"));
			}
		}
	});
	
	$("#boardlogo").change(function() {
		var fileval = $("#boardlogo").val();

		if (fileval != undefined && fileval != ''){
			var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
			if(extval != "jpeg" && extval != "jpg" && extval != "png"){
				$('.errormessagediv').show();
				$('.validateTips').text("Board Logo Image accepts only .jpg & .png format");
				document.getElementById("boardlogo").style.border = "1px solid #AF2C2C";
				document.getElementById("boardlogo").style.backgroundColor = "#FFF7F7";
				setTimeout(	function() {
					$('#errorhover').fadeOut();
					document.getElementById("boardlogo").style.border = "1px solid #ccc";
					document.getElementById("boardlogo").style.backgroundColor = "#fff";
				}, 500);
				$('.errormessagediv').fontSize = "25px";
				$("#boardlogo").val("");
				$("#boardimagePreview").hide();
				return false;
			}else{
				$("#boardimagePreview").show();
				readURL(this,$("#boardimagePreview"));
			}
		}
	});

	$("#countryId").val($("#hiddencountry").val());
	$(".states").val($("#hiddenstate").val());
	$(".cities").val($("#hiddencity").val())
});

function validateDeptName() {
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	var hiddenlocName =$("#updatelocationname").val().trim();
	
		
			var locname = $('#schollid').val();
			var locid = $('#updatelocationid').val().trim();
			if(hiddenlocName != locname){
			var deptname_object = {
				"locname" : locname,
				"locid" : locid
			};

			$.ajax({

				type : "GET",
				url : "locationDetails.html?method=validatelocationname",
				data : deptname_object,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result.des_available=="inactive") {
						$(".errormessagediv1").show();
						$(".validateTips1").text("This Location Already Exist !! Make it Active");
						desname_validate = 10;

					}
					else if (result.des_available=="true") {
						$(".errormessagediv1").show();
						$(".validateTips1").text("Location Already Exist");
						desname_validate = 1;

					}
					else {
						desname_validate = 0;
					}

				}

			});}else{
				
				desname_validate = 0;
			}

	return desname_validate;

}

function validateLocNameUpdate() {
	
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	
	 if(splitedurl=="editLocation"){
		 DeptName_validate_update = 0;
	 }else{

			var DeptName_validate_update = 0;
			var locname = $('#schollid').val();
			var locid = $('#updatelocationid').val();
			var deptObject = {
				"locname" : locname,
				"locid" : locid
			};

			$.ajax({

				type : "GET",
				url : "locationDetails.html?method=validateLocNameUpdate",
				data : deptObject,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result.des_available=="inactive") {
						$('.errormessagediv').show();
						$('.validateTips').text("This Location Already Exist !! Make it Active");
						DeptName_validate_update = 10;
					}
					else if (result.des_available=="true") {
						$('.errormessagediv').show();
						$('.validateTips').text("Location Name Already Exists");
						DeptName_validate_update = 1;
					} 
					else {
						DeptName_validate_update = 0;
					}

				}

			});
	 }

	return DeptName_validate_update;
}

function HideError(pointer) 
{
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
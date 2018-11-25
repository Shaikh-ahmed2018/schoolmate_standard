var hteacherID;

$(document).ready(function() {

	$('#back1').click(function() {
		window.location.href="menuslist.html?method=staffList&historylocId="+$("#historylocId").val()+
		"&historydepName="+$("#historydepName").val()+"&historydesigId="+$("#historydesigId").val()+"&historysearchvalue="+$("#historysearchvalue").val()
		+"&historystatus="+$("#historystatus").val();
	});
	
			var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
			if(pageUrl=="teacherregistration.html?method=teacherUpdation"){
				if($(".errormessagediv .validateTips").text()==""){
					$(".successmessagediv").show();
					setTimeout(function(){
						window.location.href="menuslist.html?method=staffList";
					},2000);
				}
			}

			
			var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();

			if($("#reportingTo").val() !=null || $("#reportingTo").val() != undefined){
				$("#reportingTo").val($("#reportingToHidden").val());
			}

			if($("#fatherMobile").val() !=null || $("#fatherMobile").val() != undefined){
				$("#fatherMobile").val($("#fatherMobileHidden").val());
			}

			if($("#motherMobile").val() !=null || $("#motherMobile").val() != undefined){
				$("#motherMobile").val($("#motherMobileHidden").val());
			}

			$('#student_admission_id').hide();
			$('#student_admission_label').hide();
			$("#idproof").hide();
			$("#teaprofile").hide();
			
			getSchoolName();
           
		
			
		

			if($("#hiddenimage").val()!="" && $("#hiddenimage").val()!=undefined){


				$('#imagePreview').attr('src', $("#hiddenimage").val());

				$('#teaImageId').attr('src', $("#hiddenimage").val());

				$("#document1btn").attr('name',$("#hiddenprofile").val());

				$("#document2btn").attr('name',$("#hiddenidproof").val());

				$("#document3btn").attr('name',$("#hiddenimage").val());

				$("#document4btn").attr('name', $("#hiddenattachment1").val());
				$("#document5btn").attr('name', $("#hiddenattachment2").val());
				$("#document6btn").attr('name', $("#hiddenattachment3").val());
				$("#document7btn").attr('name', $("#hiddenattachment4").val());
				$("#document8btn").attr('name', $("#hiddenattachment5").val());

			}
			$('.downloadDoc').click(function() {

						var path = $(this).attr('name');
						window.location.href = "teacherregistration.html?method=downloadDocument&Path="
							+ path.trim();
					});
			
			$("#deleteIDProof").click(function(){

				$("#idproof").show();

				$("#document2btn").hide();
				$("#deleteIDProof").hide();
				$("#downloadIdTitle").hide();

			});

			$("#deleteProfile").click(function(){

				$("#teaprofile").show();

				$("#document1btn").hide();
				$("#deleteProfile").hide();
				$("#downloadProfileTitle").hide();

			});


			$("#deleteTeacherImage").click(function(){

				$("#teaImageId").show();
				$("#document3btn").hide();
				$("#deleteTeacherImage").hide();
				$("#downloadTeacherImage").hide();
				$("#imagePreview").hide();
				$("#hiddenimage").val("");

			});

			if($("#hiddenimage").val()!="" && $("#hiddenimage").val()!=undefined){
                 
				$("#teaImageId").hide();
				$("#document3btn").show();
				$("#deleteTeacherImage").show();
				$("#downloadTeacherImage").show();
				$("#imagePreview").show();
				 readURL(this);
			
			
				
			
			}
			


			if($("#hiddenattachment1").val()!="" && $("#hiddenattachment1").val()!=undefined){
				$("#fileattachment1Div").show();
				$("#document4btn").show();
				$("#deleteFile1").show();
				$("#downloadFileAttachment1Title").show();
			}

			if($("#hiddenattachment2").val()!="" && $("#hiddenattachment2").val()!=undefined){
				$("#fileattachment2Div").show();
				$("#document5btn").show();
				$("#deleteFile2").show();
				$("#downloadFileAttachment2Title").show();
			}

			if($("#hiddenattachment3").val()!="" && $("#hiddenattachment3").val()!=undefined){
				$("#fileattachment3Div").show();
				$("#document6btn").show();
				$("#deleteFile3").show();
				$("#downloadFileAttachment3Title").show();
			}

			if($("#hiddenattachment4").val()!="" && $("#hiddenattachment4").val()!=undefined){
				$("#fileattachment4Div").show();
				$("#document7btn").show();
				$("#deleteFile4").show();
				$("#downloadFileAttachment4Title").show();
			}

			if($("#hiddenattachment5").val()!="" && $("#hiddenattachment5").val()!=undefined){
				$("#fileattachment5Div").show();
				$("#document8btn").show();
				$("#deleteFile5").show();
				$("#downloadFileAttachment5Title").show();
			}

          
			if($("#hiddenidproof").val().trim() == ""){
				$("#idproof").show();
				$("#document2btn").hide();
				$("#downloadIdTitle").hide();
				$("#deleteIDProof").hide();
				
			}
			else{
				$("#idproof").hide();
				$("#document2btn").show();
				$("#downloadIdTitle").show();
				$("#deleteIDProof").show();
			}

			
			
			if($("#hiddenprofile").val().trim() == ""){
				$("#teaprofile").show();
				$("#document1btn").hide();
				$("#downloadProfileTitle").hide();
				$("#deleteProfile").hide();
				
			}
			else{
				$("#teaprofile").hide();
				$("#document1btn").show();
				$("#downloadProfileTitle").show();
				$("#deleteProfile").show();
			}

			if($("#hiddenimage").val().trim() == ""){
				$("#teaImageId").show();
				$("#downloadTeacherImage").hide();
				$("#document3btn").hide();
				$("#downloadTeacherImage").hide();
				$("#imagePreview").hide();
				$("#deleteTeacherImage").hide();
				
			}
			else{
				$("#teaImageId").hide();
				$("#downloadTeacherImage").show();
				$("#deleteTeacherImage").show();
				$("#document3btn").show();
				
			}

			$("#teaImageId").change(function(){
				$("#imagePreview").show();
				 readURL(this);
				$("#deleteTeacherImage").show();
				$("#deleteTeacherImage").css({"left":"100px"});
		
			});
			$("#deleteFile1").click(function(){
				$("#filaattachment1").show();
				$("#document4btn").remove();
				$("#deleteFile1").hide();
				$("#downloadFileAttachment1Title").hide();

			});

			$("#deleteFile2").click(function(){
				$("#filaattachment2").show();
				$("#document5btn").remove();
				$("#deleteFile2").hide();
				$("#downloadFileAttachment2Title").hide();

			});

			$("#deleteFile3").click(function(){
				$("#fileattachment3label").hide();
				$("#document6btn").hide();
				$("#deleteFile3").hide();
				$("#downloadFileAttachment3Title").hide();

			});
			$("#deleteFile4").click(function(){
				$("#fileattachment4label").hide();
				$("#document7btn").hide();
				$("#deleteFile4").hide();
				$("#downloadFileAttachment4Title").hide();

			});
			$("#deleteFile5").click(function(){

				$("#fileattachment5label").hide();
				$("#document8btn").hide();
				$("#deleteFile5").hide();
				$("#downloadFileAttachment5Title").hide();

			});
			$("#aadhaarnumber").keypress(function(e){
				if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
					return false;
				}
			});
			var department = "#" + "department";
			$.ajax({
				type : 'POST',
				url : 'teacherregistration.html?method=getDepartment',
				async : false,
				success : function(response) {
					var data = $.parseJSON(response);

					for (var j = 0; j < data.DEPARTMENTLIST.length; j++) {

						$(department)
						.append(
								'<option value="'
								+ data.DEPARTMENTLIST[j].depId
								+ '">'
								+ data.DEPARTMENTLIST[j].depName
								+ '</option>');
					}

					var hiddendept=$("#hiddendept").val();
					$("#department option[value=" + hiddendept + "]").attr('selected', 'true');

				}

			});

			var designation = "#" + "designation";

			$.ajax({
				type : 'POST',
				url : 'teacherregistration.html?method=getDesignation',
				async : false,
				success : function(response) {
					var data = $.parseJSON(response);

					for (var j = 0; j < data.DESIGNATIONLIST.length; j++) {

						$(designation)
						.append(
								'<option value="'
								+ data.DESIGNATIONLIST[j].desgid
								+ '">'
								+ data.DESIGNATIONLIST[j].desgname
								+ '</option>');
					}

					var hiddendes=$("#hiddendes").val();
					$("#designation option[value=" + hiddendes + "]").attr('selected', 'true');

				}
			});

			/*var hiddenprimary=$("#hiddenprimary").val();

					$("#primaryid option[value=" + hiddenprimary + "]").attr('selected', 'true');

					var hiddensecoundary=$("#hiddensecoundary").val();
					$("#secondaryid option[value=" + hiddensecoundary + "]").attr('selected', 'true');
			 */




               
			var hiddenteatype=$("#hiddenteatype").val();
			
			$("#teachingtype option[value='"+hiddenteatype+"']").attr('selected', 'true');

			var hiddengender=$("#hiddengender").val();
			$("#gender option[value='"+hiddengender+"']").attr('selected', 'true');

			var hiddenroleid=$("#hroleid").val();
			$("#roleId option[value='"+hiddenroleid+"']").attr('selected', 'true');

		/*	var husertype=$("#husertype").val();
			$("#usertype option[value='"+husertype+"']").attr('selected', 'true');*/
			$("#usertype").val($("#husertype").val());

			if($('#hstudent_admission_id').val()!=null)			
			{
				$("#student_admission_id").val($('#hstudent_admission_id').val());

				$('input[name=is_student_studying][value='+ $('#his_student_studying').val() + ']').attr('checked', true);
				
				if($('#his_student_studying').val()=="Y")
				{
					$('#student_admission_label').show();
					$('#student_admission_id').show();
					$('#studentName').parent("div").parent("div").show();
					
				}
				
				else
				{
					$('#student_admission_label').hide();
					$('#student_admission_id').hide();
					$('#studentName').parent("div").parent("div").hide();

				}


			}  

			var hmaritalstatus=$("#hmaritalstatus").val();
			$("#maritalstatus option[value=" + hmaritalstatus + "]").attr('selected', 'true');


			if($("#hiddenattachment1").val()!="" && $("#hiddenattachment1").val()!=undefined){

				$("#document4btn").attr('name',$("#hiddenattachment1").val());

			} 

			if($("#hiddenattachment2").val()!="" && $("#hiddenattachment2").val()!=undefined){

				$("#document5btn").attr('name',$("#hiddenattachment2").val());

			}  

			if($("#hiddenattachment3").val()!="" && $("#hiddenattachment3").val()!=undefined){

				$("#document6btn").attr('name',$("#hiddenattachment3").val());

			}   

			if($("#hiddenattachment4").val()!="" && $("#hiddenattachment4").val()!=undefined){

				$("#document7btn").attr('name',$("#hiddenattachment4").val());

			}   

			if($("#hiddenattachment5").val()!="" && $("#hiddenattachment5").val()!=undefined){

				$("#document8btn").attr('name',$("#hiddenattachment5").val());

			}  






			/*  var hiddenblood=$("#hiddenblood").val();

					    $("#bloodId option[value="+hiddenblood+"]").attr('selected', 'true');*/

			$("#dateofbirthid").datepicker(
					{

						dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						maxDate : -1,
						changeMonth : "true",
						changeYear : "true",
						onClose : function(selectedDate) {
							$("#joindateid").datepicker("option",
									"minDate", selectedDate);

						}

					});

			$("#joindateid").datepicker(
					{

						dateFormat : "dd-mm-yy",
						yearRange : 'c-65:c+65',
						maxDate : 0,
						changeMonth : "true",
						changeYear : "true",
						onClose : function(selectedDate) {
							$("#dateofbirthid").datepicker("option",
									"maxDate", selectedDate);

						}

					});
	$('#save').click(function() {
		if(validateform())
		{
			var form = $('#teacherupdateform')[0];
			var data = new FormData(form);
			//document.getElementById("").submit();
			$.ajax({
				type : "POST",
				url : "teacherregistration.html?method=teacherUpdation",
				data : data,
				async : false,
				cache: false,
				contentType: false,
				processData: false,
				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status=="success")
					{
						$('.errormessagediv').hide();
						$(".successmessagediv1").show();
						$(".validateTips").text("Updating Record Progressing...");
					}else{
						$(".successmessagediv").hide();
					 	$(".errormessagediv").show();
						$(".validateTips").text("Staff Not Updated Successfully");
					}
					setTimeout(function() {
						window.location.href="menuslist.html?method=staffList";
					}, 2000);
				}
			});
		}

	});
			var cnt=0;
			$(".fileattachmentDiv").each(function(){
				var styleId=$(this).attr("style");
				if(styleId=="display: none;"){
					var fileId=$(this).attr("id");
					cnt=parseInt(fileId.split("ment")[1]);
					return false;
				}
			});
			$("#fileUploaddynmic").click(function(){

				var check = null;

				if(cnt<5){

					$("#fileattachment"+cnt+"Div").show();
					$("#fileattachment"+cnt+"label").show();
					$("#filaattachment"+cnt).show();

					cnt++;
				}else{

					$("#errorhover").show();

					$(".error").text("You can add only Five  files");

					setTimeout(function() {$('#errorhover').fadeOut();}, 3000);

					$('html, body').animate({ scrollTop: $('#errorhover').offset().top }, 'fast');
				}




			});
			$('.radio-inline').change(function(){
				var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();
				

				if(is_student_studying=="Y")
				{
					$('#student_admission_label').show();
					$('#student_admission_id').show();
					$('#studentName').parent("div").parent("div").show();
				}
				
				else
				{
					$('#student_admission_label').hide();
					$('#student_admission_id').hide();
					$('#studentName').parent("div").parent("div").hide();

				}
			});

		/*	$('#student_admission_id').change(function(){



				var student_admission_id = $("#student_admission_id").val();



				datalist = {"student_admission_id" : student_admission_id},

				$.ajax(
						{

							type: "POST",

							url: "teacherregistration.html?method=getStudentDetails",

							data: datalist,

							success: function(data)

							{

								var result = $.parseJSON(data);

								$("#studentName").val(result.status);
							}


						});
			});	*/

			$("#usertype").change(function(){
			if($("#usertype").val()==" "){
				$("#roleId").val(" ");
				 $("#uname").val(" ");
			}
			});
			 
			
			$("#student_admission_id").autocomplete({
				source : function(request, response) {
						
					$.ajax({

						url : "feecollection.html?method=studentSearchbyadmissionNo",
						data : {
							searchTerm : request.term,
							"locId"    : $("#schoolName").val()
						},
						async : false,
						success : function(data) {
							var result = $.parseJSON(data);
							response($.map(	result.jsonResponse,function(item) {
								return {
									label : item.admissionNo,
									value : item.studentId,
								}
							}));
						}
					});
				},
				select : function(event, ui) {

					var searchTerm = ui.item.value;

					studentDetails = {
							'searchTerm' : searchTerm
					};

					
					$("#student_admission_id").val(ui.item.label);
					studentListbyAdmissionNo(ui.item.label)
					return false;
				}
			});
			
			$('#student_admission_id').keyup(function(e){
				if(e.keyCode == 8){
					$("#studentName").val("");
				}
			});
			
			
		});


function validateform() {


	var teacherId = $("#regno").val();
	var abbreviate = $("#abbreviate").val();
	var firstname = $("#fname").val();
	var lastname = $("#lname").val();
	var department = $("#department").val();
	var designation = $("#designation").val();
	var teachingtype = $("#teachingtype").val();
	var usertype = $("#usertype").val();
	var role = $("#roleId").val();
	var reportingTo=$("#reportingTo").val();
	var username = $("#uname").val();
	var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();
	var student_admission_id = $("#student_admission_id").val();
	var gender = $("#gender").val();
	var dateofbirth = $("#dateofbirthid").val();
	var mobilenumber = $("#mnumber").val();
	var teacheremail = $("#teacherEmail").val();
	var panNumber = $("#panNumber").val();
	var aadhaarnumber = $("#aadhaarnumber").val();
	var maritalstatus = $("#maritalstatus").val();
	var presentadd=$("#presentadd").val().trim();
	var permanentadd=$("#permanentadd").val().trim();
	var joiningdate = $("#joindateid").val();
	var accountNumber=$("#accountNumber").val();


	var teaprofile = $("#teaprofile").val();
	var teaImageId = $("#teaImageId").val();
	var bvalid=true;

	

	var dob = Date.parse(dateConverter(dateofbirth));
	var doj = Date.parse(dateConverter(joiningdate));


	/*if (teacherId.length == 0) {
		$(".validateTips").text("Enter Staff Id");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	if (checkregistrationNo(teacherId)) {
		$(".validateTips").text("Staff Id Exists");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}*/
	if (abbreviate.trim().length == 0) {
		$(".validateTips").text("Field Required - Abbreviate Id");
		$(".errormessagediv").show();

		bvalid = false;
		return false;
	}

	else if (checkAbreviativeId(abbreviate) == 1) {
		/*$(".validateTips").text("Staff Abbreviative Id Exists");
		$(".errormessagediv").show();*/
		document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
		document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
	    $('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if (firstname.trim().length == 0) {
		$(".validateTips").text("Field Required - First Name");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (firstname.trim().length <= 2) {
		$(".validateTips").text("Name contain minimum 3");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (!firstname.match(/^[a-z]([a-z_" "])+$/i)) {
		$(".validateTips").text("Name should be Alphabet");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	else if (department.length == 1) {
		$(".validateTips").text("Field Required - Department");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}


	else if (designation.length == 1) {
		$(".validateTips").text("Field Required - Designation");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	

	else if(usertype.trim()!="" && role.trim()==""){
		$(".validateTips").text("Field Required - Role");
		$(".errormessagediv").show();
		document.getElementById("roleId").style.border = "1px solid #AF2C2C";
		document.getElementById("roleId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if(role.trim()!="" && usertype.trim()==""){
		$(".validateTips").text("Field Required - User Type");
		$(".errormessagediv").show();
		document.getElementById("usertype").style.border = "1px solid #AF2C2C";
		document.getElementById("usertype").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if(usertype.trim()!="" && role.trim()!=""&& username.trim()==""){
		$(".validateTips").text("Field Required - User Name");
		$(".errormessagediv").show();
		document.getElementById("uname").style.border = "1px solid #AF2C2C";
		document.getElementById("uname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if (location==""||location==undefined) {
		$(".validateTips").text("Field Required - Branch Name");
		$(".errormessagediv").show();
		document.getElementById("schoolName").style.border = "1px solid #AF2C2C";
		document.getElementById("schoolName").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;

	}

	else if (is_student_studying == "Y") {
		if (student_admission_id.length == 1) {
			$(".validateTips").text("Field Required - Admission Number");
			$(".errormessagediv").show();

			document.getElementById("student_admission_id").style.border = "1px solid #AF2C2C";
			document.getElementById("student_admission_id").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
	}


	else if (gender.trim().length == 1) {
		$(".validateTips").text("Field Required - Gender");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	else if (dateofbirth.length == 0) {
		$(".validateTips").text("Field Required - Date of Birth");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	else if (dob >= doj) {
		$(".validateTips").text(
		"Date of Birth should be lesser than Date of Joining");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	else if (mobilenumber.trim().length == 0) {
		$(".validateTips").text("Field Required - Mobile Number");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (mobilenumber.trim().length < 10) {
		$(".validateTips").text("Mobile Number should be min 10 & max 11 digits ");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (!mobilenumber.match(/^(?!0{6})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Mobile Number");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

/*	else if (teacheremail.trim().length == 0) { 
		$(".validateTips").text("Enter Email Id"); $(".errormessagediv").show();
		document.getElementById("teacherEmail").style.border = "1px solid #AF2C2C";
		document.getElementById("teacherEmail").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid=false; 
		return false; 
	}
	else if (!teacheremail.trim().match(/^([a-zA-Z])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/)) {
		$(".validateTips").text("Enter valid Email ID");
		$(".errormessagediv").show(); 
		document.getElementById("teacherEmail").style.border = "1px solid #AF2C2C";
		document.getElementById("teacherEmail").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid=false; 
		return false; 
	} 
*/

	else if (accountNumber.trim().length ==" " && accountNumber.trim().length==undefined) {
	 if (!accountNumber.match(/^(?!0{6})([0-9])+$/i)) {
		$(".validateTips").text("Enter Only Numbers In Account Number");
		$(".errormessagediv").show();
		document.getElementById("accountNumber").style.border = "1px solid #AF2C2C";
		document.getElementById("accountNumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid=false;
		return false;
	}
	 else if (accountNumber.trim().length < 4){
		 $(".validateTips").text("Account Number contain minimum 4");
			$(".errormessagediv").show();

			document.getElementById("panNumber").style.border = "1px solid #AF2C2C";
			document.getElementById("panNumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
	 }
	 
	}
	
	else if (panNumber.trim().length == 0) {
		$(".validateTips").text("Field Required - Pan Number");
		$(".errormessagediv").show();

		document.getElementById("panNumber").style.border = "1px solid #AF2C2C";
		document.getElementById("panNumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	
	else if (!panNumber.match(/^[a-zA-Z0-9]*$/i)) {
		$(".validateTips").text("Enter valid  Pan Number");
		$(".errormessagediv").show();

		document.getElementById("panNumber").style.border = "1px solid #AF2C2C";
		document.getElementById("panNumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	
	else if(panNumber.trim().length < 10) {
		$(".validateTips").text("Pan Number should be 10 digits");
		$(".errormessagediv").show();

		document.getElementById("panNumber").style.border = "1px solid #AF2C2C";
		document.getElementById("panNumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (aadhaarnumber.trim().length == 0) {
		$(".validateTips").text("Field Required - Aadhaar Number");
		$(".errormessagediv").show();

		document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (!aadhaarnumber.match(/^(?!0{6})([0-9])+$/i)) {
		$(".validateTips").text("Enter valid Aadhaar Number");
		$(".errormessagediv").show();

		document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (aadhaarnumber.trim().length <12){
		$(".validateTips").text("Aadhaar Number should be 12 digits");
		$(".errormessagediv").show();

		document.getElementById("aadhaarnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("aadhaarnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (maritalstatus.length == 1 || maritalstatus == null || maritalstatus == "") {
		$(".validateTips").text("Field Required - Marital Status");
		$(".errormessagediv").show();

		document.getElementById("maritalstatus").style.border = "1px solid #AF2C2C";
		document.getElementById("maritalstatus").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}


	else if (presentadd.length == 0) {
		$(".validateTips").text("Field Required - Present Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (presentadd.length < 5) {
		$(".validateTips").text("Please Enter The Valid Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}


	else if (permanentadd.length == 0) {
		$(".validateTips").text("Field Required - Permanent Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}
	else if (permanentadd.length < 5) {
		$(".validateTips").text("Please Enter The Valid Address");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	else if(!(lastname =="" || lastname==undefined)){
		if (!lastname.match(/^[a-zA-Z\s]+$/g)) {
			$(".validateTips").text("Name should be Alphabet");
			$(".errormessagediv").show();
			bvalid = false;
			return false;
		}
	}else{}

	var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
	var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;

	if (teaprofile.length > 0) {

		if (!teaprofile.match(docreg) && !teaprofile.match(pdfreg)) {
			$(".validateTips").text("Profile should be pdf or docs Format ");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}
	}

	var imgReg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.jpg|.jpeg|.JPEG|.gif|.JPG|.png|.PNG)$/;
	if (teaImageId.length > 0) {
		if (!teaImageId.match(imgReg)) {
			$(".validateTips").text("Upload only jpg or png  formats only ");
			$(".errormessagediv").show();
			bvalid=false;
			return false;
		}
	}

	if (dob >= doj) {
		$(".validateTips").text(
		"Joining date should be greater than Date of Birth");
		$(".errormessagediv").show();
		bvalid=false;
		return false;
	}

	


	return bvalid;

}

function dateConverter(dateString) {
	var dateArray = [];
	var dateStringArray = dateString.split("-");
	dateArray.push(dateStringArray[2]);
	dateArray.push(dateStringArray[1]);
	dateArray.push(dateStringArray[0]);
	var dateString1 = dateArray.join("-");
	return dateString1;
}

function checkusername(username,teacherid) {
	var teacherid=$("#teacherid").val();


	var userdata = {
			"username" : username,
			"teacherId"  : teacherid
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkUsername",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;
}


function checkAbreviativeId(abbreviate) {

    var status1 = false;
	if($("#hiddenAbrivation").val()==$("#abbreviate").val()){
		status1=0;
	}
	else{
	var userdata = {
			"abbreviate" : abbreviate,
	};
	

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkAbbreviativeId",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			 if (result.status=="inactive")
				{
					$(".errormessagediv").show();
					$(".validateTips").text("This Abbreviate Id   Already Exist !! Make it Active");
					status1 = 1;
				}
				 else if(result.status=="true") {
					$(".errormessagediv").show();
					$(".validateTips").text("Abbreviate Id  Already Exist");
					status1 = 1;

				} 
				else {
					status1 = 0;
				}

		}

	});
	}
	
	return status1;
}

function checkmail(mailid) {

	var teacherId=$("#teacherid").val();
	var maildata = {
			"mailid" : mailid,
			"teacherId" : teacherId
	};
	var status1 = false;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkMail",
		data : maildata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			status1 = result.status;

		}

	});

	return status1;

}

function removeMessage() {

	$(".successmessagediv").hide();

}

function checkregistrationNo(username,teacherid) {
	var teacherid=$("#teacherid").val();


	var userdata = {
			"registrtno" : username,
			"teacherId"  : teacherid
	};
	var status1 = 0;

	$.ajax({

		type : "GET",
		url : "teacherregistration.html?method=checkRegistrationNo",
		data : userdata,
		async : false,

		success : function(data) {

			var result = $.parseJSON(data);

			 if (result.status=="inactive")
				{
					$(".errormessagediv").show();
					$(".validateTips").text("This Staff Id  Already Exist !! Make it Active");
					status1 = 1;
				}
				 else if(result.status=="true") {
					$(".errormessagediv").show();
					$(".validateTips").text("Staff Id Already Exist");
					status1 = 1;

				} 
				else {
					status1 = 0;
				}

		}

	});

	return status1;
}




function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}


function getSchoolName(){
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getSchoolLocation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#schoolName').empty();
			for ( var j = 0; j < result.locationList.length; j++) {

				$('#schoolName').append(
						'<option value="'
						+ result.locationList[j].location_id
						+ '">'
						+ result.locationList[j].locationname
						+ '</option>');
			}
			var hschoolname=$("#hschoolname").val();
			
			$("#schoolName option[value=" + hschoolname + "]").attr('selected', 'true');
			
			
		}
	});
}

function studentListbyAdmissionNo(){
	
	$.ajax({
			
			type : 'POST',
			url : "teacherregistration.html?method=studentListbyAdmissionNo",
			data : {   "admissionNo":$("#student_admission_id").val(),"locId":$("#schoolName").val()
					
			},
			async : false,
			success : function(response) {
				var result=$.parseJSON(response);
				if(result.stuList[0].status == "found"){
					$("#hstudentId").val(result.stuList[0].studentId);
					$("#studentName").val(result.stuList[0].student);

				}
			
				
			}
		});
	}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}




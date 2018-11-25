$(document).ready(function() {

	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	if(pageUrl=="teacherregistration.html?method=teacherregister"){
		if($(".errormessagediv .validateTips").text()==""){
			$(".successmessagediv").show();
			setTimeout(function(){
				window.location.href="menuslist.html?method=staffList";
			},2000);
		}
	}
	$("#aadhaarnumber").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			return false;
		}
	});
	$("#mnumber").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("mnumber").style.border = "1px solid #AF2C2C";
			document.getElementById("mnumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	    }
	});
	
	$("#fatherMobile").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("fatherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	    }
	});
	
	$("#motherMobile").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("motherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("motherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	    }
	});
	
	$("#spouseMobile").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number.");
			document.getElementById("spouseMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("spouseMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
	    }
	});
	

	getSchoolName();

	$('#student_admission_id').hide();
	$('#student_admission_label').hide();

	/*$(".image").hide();
	$(".gallery").click(function() {

		$(".image").show();

	});
*/
	$("#imagePreview").hide();
	$("#deleteteacher_image").hide();

	$("#deleteFileIdProof").hide();
	$("#deleteFileProfile").hide();



	$("#teaImageId").change(function() {

		$("#imagePreview").show();
		$("#deleteteacher_image").show();
		readURL(this);

	});

	$("#deleteteacher_image").click(function() {
		$("#imagePreview").hide();
		$("#teaImageId").val("");
		$("#deleteteacher_image").hide();

	});
	
	$("#maritalstatus").change(function(){
		var marriageStatus=$("#maritalstatus").val();
		if(marriageStatus=="Single"){
			$("#spousename").val();
			$("#spouseMobile").val();
			$("#spousename").prop("readonly", true);
			$("#spouseMobile").prop("readonly", true);
		}else{
			$("#spousename").prop("readonly", false);
			$("#spouseMobile").prop("readonly", false);
		}
	});

	$("#idproof").change(function() {

		$("#deleteFileIdProof").show();

	});

	$("#teaprofile").change(function() {

		$("#deleteFileProfile").show();

	});



	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);



	var department = "#" + "department";

	$.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDepartment',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);

			for ( var j = 0; j < data.DEPARTMENTLIST.length; j++) {

				$(department)
				.append(
						'<option value="'
						+ data.DEPARTMENTLIST[j].depId
						+ '">'
						+ data.DEPARTMENTLIST[j].depName
						+ '</option>');
			}

		}

	});

	var designation = "#" + "designation";

	$.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDesignation',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);

			for ( var j = 0; j < data.DESIGNATIONLIST.length; j++) {

				$(designation)
				.append(
						'<option value="'
						+ data.DESIGNATIONLIST[j].desgid
						+ '">'
						+ data.DESIGNATIONLIST[j].desgname
						+ '</option>');
			}

		}

	});

	var hiddenprimary = $("#hiddenprimary").val();

	$("#primaryid option[value=" + hiddenprimary + "]").attr(
			'selected', 'true');

	var hiddensecoundary = $("#hiddensecoundary").val();
	$("#secondaryid option[value=" + hiddensecoundary + "]")
	.attr('selected', 'true');

	var hiddendept = $("#hiddendept").val();
	$("#department option[value=" + hiddendept + "]").attr(
			'selected', 'true');

	var hiddendes = $("#hiddendes").val();
	$("#designation option[value=" + hiddendes + "]").attr(
			'selected', 'true');

	var hiddenteatype = $("#hiddenteatype").val();
	$("#teachingtype option[value=" + hiddenteatype + "]")
	.attr('selected', 'true');

	var hiddengender = $("#hiddengender").val();
	$("#gender option[value=" + hiddengender + "]").attr(
			'selected', 'true');

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
	var dNow = new Date();
	var Day = dNow.getDate();
	if (Day < 10) {
		Day = '0' + Day;
	} // end if
	var Month = dNow.getMonth() + 1;
	if (Month < 10) {
		Month = '0' + Month;
	}

	var localdate = Day + '-' + Month + '-'
	+ dNow.getFullYear();
	$("#joindateid").val(localdate);
	$("#loder").hide();
	$('#save').click(function() {
		if (validateform()) {
			
			
			var form = $('#teacherform')[0];
			var data = new FormData(form);
			
			//document.getElementById("teacherform").submit();
			$.ajax({
				type : "POST",
				enctype: 'multipart/form-data',
				url : "teacherregistration.html?method=teacherregister",
				//data : $("#teacherform").serialize(),
				data:data,
				async : false,
				cache: false,
				contentType: false,
				processData: false,
				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status=="success"){
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
					}else if(result.status=="duplicate"){
						$(".successmessagediv").hide();
					 	$(".errormessagediv").show();
						$(".validateTips").text("Staff already Exists");
					}else{
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Staff Registration Not Completed");
					}
					setTimeout(function() {
						window.location.href="menuslist.html?method=staffList";
					}, 2000);
				}
			});
			
			// }
		}
		;

	});

	/*
	 * $('#editTeacher') .click( function() {
	 * 
	 * 
	 * var count = 0; $(
	 * 'input.vehicle_Checkbox_Class:checkbox:checked') .map(
	 * function() {
	 * 
	 * var vehicle_id = $( this).attr( "id"); var split_id =
	 * vehicle_id .split('_'); getData = split_id[1]
	 * .split(',');
	 * 
	 * count++; }); if (count == 0 || count > 1) {
	 * $(".errormessagediv").show(); $(".validateTips").text(
	 * "Check any one checkbox"); return false;
	 *  } else {
	 * 
	 * window.location.href =
	 * "subject.html?method=getSubjectDetails&subjectcode="+getData[0];
	 *  } });
	 */
	
	var cnt = 0;
	$("#fileUploaddynmic").click(function() {

				var check = null;

				if (cnt < 5) {

					cnt++;

					$("#fileattachment" + cnt + "Div")
					.show();
					$("#fileattachment" + cnt + "label")
					.show();
					$("#filaattachment" + cnt).show();
					$("#deleteFile" + cnt).show();

				} else {

					$("#errormessagediv").show();

					$(".validateTips")
					.text(
					"You can add only Five  files");

					setTimeout(function() {
						$('#errorhover').fadeOut();
					}, 3000);

					$('html, body').animate(
							{
								scrollTop : $(
										'#errorhover')
										.offset().top
							}, 'fast');
				}

	});

	$("#deleteFileIdProof").click(function() {

		$("#idproof").val("");
		$("#deleteFileIdProof").hide();


	});
	
	$("#idproof").change(function(){
		var filename = $("#idproof").val().split(".").pop().toLowerCase();
		var idstatus=true;
		
		if(filename=="jpg" || filename=="png" || filename=="pdf"){
			idstatus=false;
		}
		if(idstatus){
			$("#idproof").val(""); 
			$("#deleteFileIdProof").hide();
			 $(".errormessagediv").show();
				$(".validateTips").text("Accepts jpg, png and pdf format Only");
				 $("#idproof").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {
					 $('.errormessagediv').fadeOut();}, 3000);
		          }
	    });
	
	$("#teaImageId").change(function(){
		var filename = $("#teaImageId").val().split(".").pop().toLowerCase();
		var idstatus=true;
		
		if(filename=="jpg" || filename=="png"){
			idstatus=false;
		}
		if(idstatus){
			$("#teaImageId").val("");
			$("#deleteteacher_image").hide(); 
			$("#imagePreview").hide();
			 $(".errormessagediv").show();
				$(".validateTips").text("Accepts jpg and png format Only");
				 $("#teaImageId").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {
					 $('.errormessagediv').fadeOut();}, 3000);
		          }
	    });
	
	$("#teaprofile").change(function(){
		var filename = $("#teaprofile").val().split(".").pop().toLowerCase();
		var idstatus=true;
		
		if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
			idstatus=false;
		}
		if(idstatus){
			$("#teaprofile").val(""); 
			$("#deleteFileProfile").hide();
			 $(".errormessagediv").show();
				$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
				 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {
					 $('.errormessagediv').fadeOut();}, 3000);
		          }
	    });  
	    
	    $("#filaattachment1").change(function(){
			var filename = $("#filaattachment1").val().split(".").pop().toLowerCase();
			var idstatus=true;
			if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
				idstatus=false;
			}
			if(idstatus){
				$("#teaprofile").val(""); 
				$("#deleteFileProfile").hide();
				 $(".errormessagediv").show();
					$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
					 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {
						 $('.errormessagediv').fadeOut();}, 3000);
			          }
		    });
	    
	    $("#filaattachment2").change(function(){
			var filename = $("#filaattachment2").val().split(".").pop().toLowerCase();
			var idstatus=true;
			if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
				idstatus=false;
			}
			if(idstatus){
				$("#teaprofile").val(""); 
				$("#deleteFileProfile").hide();
				 $(".errormessagediv").show();
					$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
					 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {
						 $('.errormessagediv').fadeOut();}, 3000);
			          }
		    });
	    
	    $("#filaattachment3").change(function(){
			var filename = $("#filaattachment3").val().split(".").pop().toLowerCase();
			var idstatus=true;
			if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
				idstatus=false;
			}
			if(idstatus){
				$("#teaprofile").val(""); 
				$("#deleteFileProfile").hide();
				 $(".errormessagediv").show();
					$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
					 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {
						 $('.errormessagediv').fadeOut();}, 3000);
			          }
		    });
	    
	    $("#filaattachment4").change(function(){
			var filename = $("#filaattachment4").val().split(".").pop().toLowerCase();
			var idstatus=true;
			if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
				idstatus=false;
			}
			if(idstatus){
				$("#teaprofile").val(""); 
				$("#deleteFileProfile").hide();
				 $(".errormessagediv").show();
					$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
					 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {
						 $('.errormessagediv').fadeOut();}, 3000);
			          }
		    });
	    
	    $("#filaattachment5").change(function(){
			var filename = $("#filaattachment5").val().split(".").pop().toLowerCase();
			var idstatus=true;
			if(filename=="jpg" || filename=="png" || filename=="pdf" || filename=="docx"){
				idstatus=false;
			}
			if(idstatus){
				$("#teaprofile").val(""); 
				$("#deleteFileProfile").hide();
				 $(".errormessagediv").show();
					$(".validateTips").text("Accepts pdf, doc, jpg and png format Only");
					 $("#teaprofile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {
						 $('.errormessagediv').fadeOut();}, 3000);
			          }
		    });

	$("#deleteFileProfile").click(function() {

		$("#teaprofile").val("");
		$("#deleteFileProfile").hide();

	});

	$("#deleteFile1").click(function() {

		$("#fileattachment" + 1 + "Div").hide();
		$("#fileattachment" + 1 + "label").hide();
		$("#filaattachment" + 1).hide();
		$("#filaattachment" + 1).val("");
		$("#deleteFile" + 1).hide();
		cnt = 0;

	});
	$("#deleteFile2").click(function() {

		$("#fileattachment" + 2 + "Div").hide();
		$("#fileattachment" + 2 + "label").hide();
		$("#filaattachment" + 2).hide();
		$("#filaattachment" + 2).val("");
		$("#deleteFile" + 2).hide();
		cnt = 0;

	});

	$("#deleteFile3").click(function() {

		$("#fileattachment" + 3 + "Div").hide();
		$("#fileattachment" + 3 + "label").hide();
		$("#filaattachment" + 3).hide();
		$("#filaattachment" + 3).val("");
		$("#deleteFile" + 3).hide();
		cnt = 0;

	});
	$("#deleteFile4").click(function() {

		$("#fileattachment" + 4 + "Div").hide();
		$("#fileattachment" + 4 + "label").hide();
		$("#filaattachment" + 4).hide();
		$("#filaattachment" + 4).val("");
		$("#deleteFile" + 4).hide();
		cnt = 0;

	});

	$("#deleteFile5").click(function() {

		$("#fileattachment" + 5 + "Div").hide();
		$("#fileattachment" + 5 + "label").hide();
		$("#filaattachment" + 5).hide();
		$("#filaattachment" + 5).val("");
		$("#deleteFile" + 5).hide();
		cnt = 0;

	});

	$('.radio-inline').change(function() {
				var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked')
						.val();

				if (is_student_studying == "Y") {
					$('#student_admission_label')
					.show();
					$('#student_admission_id').show();
					$('#studentName').parent("div")
					.parent("div").show();
				}

				else {

					$('#student_admission_label').hide();
					$('#student_admission_id').hide();
					$('#studentName').parent("div")
					.parent("div").hide();

				}
			});

	/*$('#student_admission_id').change(function() {

				var student_admission_id = $("#student_admission_id").val();

				datalist = {
						"student_admission_id" : student_admission_id
				},

				$.ajax({
					type : "POST",

					url : "teacherregistration.html?method=getStudentDetails",

					data : datalist,

					success : function(data)
					{
						var result = $
						.parseJSON(data);

						$("#studentName").val(result.status);
							
					}

				});
			});*/
	
	$("#usertype").change(function(){
		
	if($("#usertype").val()==" "){
		$("#roleId").val(" ");
		 $("#uname").val(" ");
	}
	});
	
	$("#schoolName").change(function(){
		$("#student_admission_id").val(""); 
		$("#studentName").val("");
	});
	
	
	$("#student_admission_id").autocomplete({
		source : function(request, response) {
				
			$.ajax({

				url : "feecollection.html?method=studentSearchbyadmissionNo",
				data : {
					searchTerm : request.term,
					"locId":$("#schoolName").val()
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

function readyOnly(){
	 
}

function commonNumberValidation(Id){
	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		$(".errormessagediv").show();
		$(".validateTips").text("Enter Only Number.");
		document.getElementById(Id).style.border = "1px solid #AF2C2C";
		document.getElementById(Id).style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		return false;
    }
}

function callAjax(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				jsonResult = result.jsonResponse;
			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}
function validateform() {
	var presentadd = $("#presentadd").val().trim();
	var permanentadd = $("#permanentadd").val().trim();
	var fathername = $("#fathername").val().trim();
	var mothername = $("#mothername").val().trim();
	var teacherId = $("#regno").val().trim();
	var firstname = $("#fname").val().trim();
	var lastname = $("#lname").val().trim();
	var username = $("#uname").val().trim();
	var qualification = $("#qualification").val().trim();
	var primaryid = $("#primaryid").val();
	var secondaryid = $("#secondaryid").val();
	var teacheremail = $("#teacherEmail").val().trim();
	var mobilenumber = $("#mnumber").val().trim();
	var designation = $("#designation").val();
	var teaprofile = $("#teaprofile").val();
	var teaImageId = $("#teaImageId").val();
	var dateofbirth = $("#dateofbirthid").val();
	var joiningdate = $("#joindateid").val();
	var idproof = $("#idproof").val();
	var teachingtype = $("#teachingtype").val();
	var department = $("#department").val();
	var role = $("#roleId").val();
	var gender = $("#gender").val();
	
	/*var location = $("#locationid").val();*/
	var bvalid = true;
	var usertype = $("#usertype").val();
	var abbreviate = $("#abbreviate").val();
	var reportingTo = $("#reportingTo").val();
	var maritalstatus = $("#maritalstatus").val();
	var dob = Date.parse(dateConverter(dateofbirth));
	var doj = Date.parse(dateConverter(joiningdate));
	var aadhaarnumber = $("#aadhaarnumber").val().trim();
	var panNumber = $("#panNumber").val();
	var is_student_studying = $('.radio-inline[name="is_student_studying"]:checked').val();
	var student_admission_id = $("#student_admission_id").val();
	var spouseMobile = $("#spouseMobile").val();
	var motherMobile = $("#motherMobile").val();
	var fatherMobile = $("#fatherMobile").val();
	var accountNumber=$("#accountNumber").val();
	var location=$("#schoolName").val();

	if (teacherId.trim().length == 0) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required - Staff Id");
		document.getElementById("regno").style.border = "1px solid #AF2C2C";
		document.getElementById("regno").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (checkregistrationNo(teacherId) == 1) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
	/*	$(".validateTips").text("Staff Id Exists");
		$(".errormessagediv").show();*/
		document.getElementById("regno").style.border = "1px solid #AF2C2C";
		document.getElementById("regno").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}

	else if (abbreviate.trim().length == 0) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		
		$(".validateTips").text("Field Required - Abbreviate Name");
		$(".errormessagediv").show();
		document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
		document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (checkAbreviativeId(abbreviate) == 1) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
		document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}

	else if (firstname.trim().length == 0) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("Field Required - First Name");
		$(".errormessagediv").show();
		document.getElementById("fname").style.border = "1px solid #AF2C2C";
		document.getElementById("fname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (firstname.trim().length <= 2) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("Name contain minimum 3");
		$(".errormessagediv").show();
		document.getElementById("fname").style.border = "1px solid #AF2C2C";
		document.getElementById("fname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if (!firstname.trim().match(/^[a-z]([a-z_" "])+$/i)) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("First Name should be Alphabet");
		$(".errormessagediv").show();
		document.getElementById("fname").style.border = "1px solid #AF2C2C";
		document.getElementById("fname").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}




	else if (department.length == 0) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("Field Required - Department");
		$(".errormessagediv").show();
		document.getElementById("department").style.border = "1px solid #AF2C2C";
		document.getElementById("department").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}

	else if (designation.length == 1) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("Field Required - Designation");
		$(".errormessagediv").show();
		document.getElementById("designation").style.border = "1px solid #AF2C2C";
		document.getElementById("designation").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}


	else if (teachingtype.length == 1) {
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
		$(".validateTips").text("Field Required - Staff Type ");
		$(".errormessagediv").show();
		document.getElementById("teachingtype").style.border = "1px solid #AF2C2C";
		document.getElementById("teachingtype").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;

	}
	
	else if(usertype.trim()!="" && role.trim()==""){
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
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
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
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
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
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
		if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
			$(".collapseOneA").trigger("click");
		}
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
			if($(".collapseOneA").attr("class") == "collapseOneA collapsed"){
				$(".collapseOneA").trigger("click");
			}
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

	else if (gender.length == 1) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Field Required - Gender");
		$(".errormessagediv").show();

		document.getElementById("gender").style.border = "1px solid #AF2C2C";
		document.getElementById("gender").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (dateofbirth.length == 0) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Field Required - Date of Birth");
		$(".errormessagediv").show();

		document.getElementById("dateofbirthid").style.border = "1px solid #AF2C2C";
		document.getElementById("dateofbirthid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (dob >= doj) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Date of Birth should be lesser than Date of Joining");
		$(".errormessagediv").show();

		document.getElementById("joindateid").style.border = "1px solid #AF2C2C";
		document.getElementById("joindateid").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}

	else if (mobilenumber.trim().length == 0) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Field Required - Mobile Number");
		$(".errormessagediv").show();

		document.getElementById("mnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("mnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (mobilenumber.trim().length < 10) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		
		$(".validateTips").text("Mobile Number should be min 10 & max 11 digits ");
		$(".errormessagediv").show();

		document.getElementById("mnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("mnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
		return false;
	}
	else if (!mobilenumber.trim().match(/^(?!0{6})([0-9])+$/i)) {
		
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Enter valid Mobile No");
		$(".errormessagediv").show();

		document.getElementById("mnumber").style.border = "1px solid #AF2C2C";
		document.getElementById("mnumber").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);

		bvalid = false;
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
	}*/
 


/*	else if (accountNumber.trim().length ==" " && accountNumber.trim().length==undefined) {
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
	}*/

		 
	
	
	else if (panNumber.trim().length == 0) {
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
		$(".validateTips").text("Enter valid Pan Number");
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
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
		
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
	else if (aadhaarnumber.trim().length < 12){
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
		if($(".collapseTwoA").attr("class") == "collapseTwoA collapsed"){
			$(".collapseTwoA").trigger("click");
		}
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
		
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		$(".validateTips").text("Field Required - Present Address");
		$(".errormessagediv").show();
		document.getElementById("presentadd").style.border = "1px solid #AF2C2C";
		document.getElementById("presentadd").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if (presentadd.length < 5) {
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		$(".validateTips").text("Please Enter The Valid Address");
		$(".errormessagediv").show();
		document.getElementById("presentadd").style.border = "1px solid #AF2C2C";
		document.getElementById("presentadd").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}

	else if (permanentadd.length == 0) {
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		$(".validateTips").text("Field Required - Permanent Address");
		$(".errormessagediv").show();
		document.getElementById("permanentadd").style.border = "1px solid #AF2C2C";
		document.getElementById("permanentadd").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid = false;
		return false;
	}
	else if (permanentadd.length < 5) {
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		$(".validateTips").text("Please Enter The Valid Address");
		$(".errormessagediv").show();
		document.getElementById("permanentadd").style.border = "1px solid #AF2C2C";
		document.getElementById("permanentadd").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		bvalid =false;
		return false;
	}
	
	if(!(lastname.trim() =="" || lastname.trim()==undefined)){
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		if (!lastname.match(/^[a-zA-Z\s]+$/g)) {
			$(".validateTips").text("Name should be Alphabet");
			$(".errormessagediv").show();
			document.getElementById("lname").style.border = "1px solid #AF2C2C";
			document.getElementById("lname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			bvalid = false;
			return false;
		}
	}else{}
	
	if(!(accountNumber.trim()==""||accountNumber.trim()==undefined)){
	if (accountNumber.trim().length < 4){
		if($(".collapseThreeA").attr("class") == "collapseThreeA collapsed"){
			$(".collapseThreeA").trigger("click");
		}
		 $(".validateTips").text("Account Number contain minimum 4");
			$(".errormessagediv").show();

			document.getElementById("accountNumber").style.border = "1px solid #AF2C2C";
			document.getElementById("accountNumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
	 }
	}
	 else{}
/* if (!teacheremail.trim().match(/^([a-zA-Z])(([a-zA-Z0-9])*([\._-])?([a-zA-Z0-9]))*@(([a-zA-Z0-9\-])+(\.))+([a-zA-Z]{2,4})+$/)) {
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
 else{}*/

	var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
	var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;

	if (teaprofile.length > 0) {
		if (!teaprofile.match(docreg) && !teaprofile.match(pdfreg)) {
			$(".validateTips").text("Profile should be pdf or docs Format ");
			$(".errormessagediv").show();
			document.getElementById("teaprofile").style.border = "1px solid #AF2C2C";
			document.getElementById("teaprofile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			bvalid = false;
			return false;
		}
	}

	var imgReg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.jpg|.jpeg|.JPEG|.gif|.JPG|.png|.PNG)$/;
	if (teaImageId.length > 0) {
		if (!teaImageId.match(imgReg)) {
			$(".validateTips").text("Upload only jpg or png  formats only");
			$(".errormessagediv").show();

			document.getElementById("teaImageId").style.border = "1px solid #AF2C2C";
			document.getElementById("teaImageId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
	}

	if (fatherMobile.length != 0) {

		if (fatherMobile.length < 10) {
			$(".validateTips").text("Father Mobile Number should be min 10 & max 11 digits ");
			$(".errormessagediv").show();

			document.getElementById("fatherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		} else if (!fatherMobile.match(/^(?!0{6})([0-9])+$/i)) {
			$(".validateTips").text("Enter valid Father Mobile No");
			$(".errormessagediv").show();

			document.getElementById("fatherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
	}

	if (motherMobile.length != 0) {

		if (motherMobile.length < 10) {
			$(".validateTips").text("Mother Mobile Number should be min 10 & max 11 digits ");
			$(".errormessagediv").show();

			document.getElementById("motherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("motherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		} else if (!motherMobile.match(/^(?!0{6})([0-9])+$/i)) {
			$(".validateTips").text("Enter valid Mother Mobile Number");
			$(".errormessagediv").show();

			document.getElementById("motherMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("motherMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
	}

	if (spouseMobile.length != 0) {

		if (spouseMobile.length < 10) {
			$(".validateTips").text(
			"Spouse Mobile Number should be min 10 & max 11 digits ");
			$(".errormessagediv").show();

			document.getElementById("spouseMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("spouseMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		} else if (!spouseMobile.match(/^(?!0{6})([0-9])+$/i)) {
			$(".validateTips").text("Enter valid Spouse Mobile Number");
			$(".errormessagediv").show();

			document.getElementById("spouseMobile").style.border = "1px solid #AF2C2C";
			document.getElementById("spouseMobile").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			bvalid = false;
			return false;
		}
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

function checkusername(username, teacherid) {

	var userdata = {
			"username" : username,
			"teacherId" : teacherid
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

function checkmail(mailid) {

	var maildata = {
			"mailid" : mailid
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

function checkregistrationNo(username, teacherid) {

	var locId=$("#schoolName").val();
	
	var userdata = {
			"registrtno" : username,
			"teacherId"  : teacherid,
			"locId"      :locId
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

function checkAbreviativeId(abbreviate) {

	var locId=$("#schoolName").val();
	
	var userdata = {
			"abbreviate" : abbreviate,
			"locId"      :locId
	};
	var status1 = 0;

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


		}
	});
}

function studentListbyAdmissionNo(){
	var locId=$("#schoolName").val();
	
	$.ajax({
			
			type : 'POST',
			url : "teacherregistration.html?method=studentListbyAdmissionNo",
			data : {  
				"admissionNo":$("#student_admission_id").val(),
				"locId":locId	
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

function collapse(pointer){
	$(".panel-collapse.collapse").removeClass("in",function(){
		$("#"+pointer).closest(".panel-collapse.collapse").addClass("in");
	});
}

function errorHighlight(pointer){
	document.getElementById(""+pointer+"").style.border = "1px solid #AF2C2C";
	document.getElementById(""+pointer+"").style.backgroundColor = "#FFF7F7";
}

function HideError(pointer){
	$('.errormessagediv').hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

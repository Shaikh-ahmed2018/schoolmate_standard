$(document).ready(function() {

	getOccupation();
	getSchoolLocation();
	getOccupation();
	 categoryId = "category";
		getCategory(categoryId);
	
	getCategory("category");
	
	$("#feeConcession").change(function(){
		if($("#feeConcession").val()=="Yes"){
			$("#consdiv").show();
			getConcessionTypes();
		}else{
			$("#consdiv").hide();
		}
	});
	
	
	if($("#hiddenconcessiontype").val()!=""){
		$("#feeConcession").val("Yes");
		$("#consdiv").show();
		getConcessionTypes();
		$('#concessiontype').val($("#hiddenconcessiontype").val());
	}
	
	$("input").attr('autocomplete', 'off');
	
	$("#Print").click(function(){
		id=$(".select:checked").attr("id");
		getAdmissionDetails(id);
		printApplication();
	});
	
	$("#academicYear").change(function(){
		getHouse($("#schoolLocationId").val());
	});
	$("#back1").click(function(){
		 
		if($("#hiddenbackId").val()=="searchb"){
			 window.location.href = "menuslist.html?method=individualStudentSearch&studentId="+$("#studentid").val()+"&accyear="+$("#acchiddenId").val()+"&locationId="+$("#schoollocationhiddenid").val();
		}else if($("#hiddenbackId").val()=="mis"){
			window.location.href = "menuslist.html?method=individualMisreport&studentId="+$("#studentid").val()+"&accyear="+$("#acchiddenId").val()+"&locationId="+$("#schoollocationhiddenid").val();
		}else{
		   window.location.href = "menuslist.html?method=studentList&historystatus="+$("#historystatus").val()+
		   "&historyacademicId="+$("#historyacademicId").val()+"&historylocId="+$("#historylocId").val()+
		   "&historyclassname="+$("#historyclassname").val()+"&historysectionid="+$("#historysectionid").val()+
		   "&historysearchvalue="+$("#historysearchvalue").val();
		}
	});
	
	$("#primarypersonId").val($("#hprymarycntperson").val()); 
	$("#hidprimary").val($("#hprymarycntperson").val());
	var categoryId = "category";
	var url=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(url.split("&")[0] == "studentRegistration.html?method=editStudent"){
		$('#admissionform').hide();
	}
 
	if(url.split("&")[0]!= "menuslist.html?method=studentList"){
		
		$("#dateofBirthId").datepicker({
			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			maxDate : -730,
			changeMonth : "true",
			changeYear : "true",
			yearRange : '1997:' + (new Date).getFullYear(),
			onClose : function(selectedDate) {
				if ((selectedDate != "") && (selectedDate != undefined)) {
					var date2 = $('#dateofBirthId').datepicker('getDate');
					date2.setDate(date2.getDate() + 1);
					$("#dateofJoinId").datepicker("option","minDate", date2);
				}
			}
		});
		$("#dateofJoinId").datepicker({

			dateFormat : "dd-mm-yy",
			yearRange : 'c-65:c+65',
			maxDate : 0,
			changeMonth : "true",
			changeYear : "true",
			onClose : function(selectedDate) {
				if ((selectedDate != "") && (selectedDate != undefined)) {
					var date2 = $('#dateofJoinId').datepicker('getDate');
					date2.setDate(date2.getDate() - 1);
					$("#dateofBirthId").datepicker("option", "maxDate", date2);
				}
			}
		
		});
		
		
		var searchlocId="";
		 if($("#historylocId").val()!=""){
			searchlocId=$("#historylocId").val();
		}
		else{
			searchlocId=$("#schoolLocationId").val();
		}
		
		 
		
		$("#SearchStudent").autocomplete({
			source : function(request, response) {

				$("#studentSibilingIdIntId").val("");

				$("#fatherNameId").attr('readonly', false);
				$("#fatherMobileNoId").attr('readonly', false);

				$("#fatherQualificationId").attr('readonly', false);
				$("#fatheremailId").attr('readonly', false);

				$("#motherNameId").attr('readonly', false);
				$("#motherMobileNoId").attr('readonly', false);
				$("#motherQualificationId").attr('readonly', false);
				$("#motheremailId").attr('readonly', false);

				$("#gaurdianNameId").attr('readonly', false);
				$("#guardianMobileNoId").attr('readonly', false);
				$("#guardianemailId").attr('readonly', false);
				
				$("#fatherPanNo").attr('readonly', false);
				$("#motherPanNo").attr('readonly', false);

				$("#motheroccupationId").attr('readonly', false);
				$("#fatheroccupationId").attr('readonly', false);
				$("#paddrs").attr('readonly', false);
				$('#primarypersonId').attr('disabled',false);
				$("#motherNameId").val('');
				$("#motherMobileNoId").val('');
				$("#motherQualificationId").val('');
				$("#fatherNameId").val('');
				$("#fatherMobileNoId").val('');
				$("#fatherQualificationId").val('');
				$("#fatheremailId").val('');
				$("#motheremailId").val('');
				$("#gaurdianNameId").val('');
				$("#guardianMobileNoId").val('');
				$("#guardianemailId").val('');
				$("#parentId").val('');
				$("#sibilingClassId").val('');
				$("#studentSibilingIdIntId").val('');
				$("#sibilingadminnoId").val('');
				$("#paddrs").val('');
				$("#motheroccupationId").val('');
				$("#fatheroccupationId").val('');
				$("#primarypersonId option[value='']").attr('selected', 'true');
				$("#hidprimary").val("")
				$("#hprymarycntperson").val("");
				$("#hidprimary").val();
				$("#fatherPanNo").val();
				$("#motherPanNo").val();
				/*	}*/


				$.ajax({

					url : "studentRegistration.html?method=studentSearchbySibling",
					data : {
						searchTerm : request.term,
						"locId" : searchlocId
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.studentnamelabel,
								value : item.studentidlabel,
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
				var studentList = callAjax("studentRegistration.html?method=studentSearchByParent",studentDetails);
				
				viewStudentDetails(studentList);
				$("#SearchStudent").val(ui.item.label);
				$("#studentSibilingIdIntId").val(searchTerm);

				return false;
			}
		});
		
		
		$("#SearchStudent").change(function(){
			
			if($("#SearchStudent").val() ==''){
				
				$("#sibilingClassId").val('');
				$("#sibilingadminnoId").val('');
				$("#fatherNameId").val('');
				$("#fatherMobileNoId").val('');
				$("#motherNameId").val('');
				$("#motherMobileNoId").val('');
				
			}
			
		});
		
		$("#temp_studentName").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByStudentName",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.studentnamelabel,
								value : item.studentidlabel,
							}
						}));
					}
				});
			},
			select : function(event, ui) {
				var searchTerm = ui.item.label;
			/*	studentDetails = {
						'searchTerm' : searchTerm
				};*/
				$("#temp_studentName").val(ui.item.label);
				getTempAdmissionDetailsListById();
				
				
				return false;
			}
		});
		
		$("#temp_parentName").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByParentName",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.parentNameLabel,
								value : item.studentidlabel,
							}
						}));
					}
				});
			},
			select : function(event, ui) {
				var searchTerm = ui.item.label;
				/*studentDetails = {
						'searchTerm' : searchTerm
				};*/
				$("#temp_parentName").val(ui.item.label);
				getTempAdmissionDetailsListById();
				
				return false;
			}
		});
		
		$("#temp_mobileNo").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "studentRegistration.html?method=searchStudentByMobileNo",
					data : {
						searchTerm : request.term
					},
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.parentMobileLabel,
								value : item.studentidlabel,
							}
						}));
					}
				});
			},
			select : function(event, ui) {
				var searchTerm = ui.item.label;
				/*studentDetails = {
						'searchTerm' : searchTerm
				};*/
				$("#temp_mobileNo").val(ui.item.label);
				getTempAdmissionDetailsListById();
				
				return false;
			}
		});
		
	/*	$("#temp_studentName,#temp_mobileNo,#temp_parentName").keyup(function(){
			getTempAdmissionDetailsListById();
		});*/
		
		
		$("#admissionDialog").dialog({
		    autoOpen  : false,
		    maxWidth  :	1000,
	        maxHeight : 500,
	        width     : 1000,
	        height    : 500,
		    modal     : true,
		    title     : "Admission Gallery",
		    buttons   : {
		    	'OK'  : function() {
		    		 
		    		if($('.select:checked').length==0){
		    			$(".errormessagediv").show();
		        		$(".validateTips").text("Select Any Record");
		        		setTimeout(function() {
		        			$('#errormessagediv').hide();
		        		}, 1000);
		        		return false;
		    		}
		    		
		    		var pointer = $("#admissionstudent input[name='select']:checked");
		    		var val= $("#admissionstudent input[name='select']:checked").attr("id");
		    		var studetails = $("#admissionstudent input[name='select']:checked").val();
		    		tempDetails = {
							'tempid' : val
					};
		    		
		    		stuaccyid = studetails.split(' ')[0];
		    		stulocid = studetails.split(' ')[1];
		    		stustreamid = studetails.split(' ')[2];
		    		stuclsid = studetails.split(' ')[3];
		    		stufirstName = pointer.closest("tr").find('.stuname').attr('id').split("-")[0];
		    		stulastName = pointer.closest("tr").find('.stuname').attr('id').split("-")[1];
		    		sturel = pointer.closest("tr").find('.relation').attr('class').split(" ")[1];
		    		phone = pointer.closest("tr").find('.phone').text();
		    		parent = pointer.closest("tr").find('.relation').text();
		    		
		    		$("#hiddentempregid").val(val);
		    		$("#hiddenenquiryid").val(val)
		    		
		    		//$("#schoolLocationId").val(stulocid);
		    		
		    		getCategory("category");
		    		$("#category").val(stustreamid);
		    		
		    		getClassName("studClassId", "category");
		    		
		    		$("#studClassId").val(stuclsid);
		    		

		    		getClassSection("#studSectionId", "#studClassId");
		    		getClassSpecilization($("#studClassId").val(),$("#schoolLocationId").val().trim());
		    		getHouse($("#schoolLocationId").val());
		    		
		    		$("#academicYear").val(stuaccyid);
		    		$("#studentFirstNameId").val(stufirstName);
		    		$("#studentLastNameId").val(stulastName);
		    		
		    		if(sturel == "father"){
		    			$("#fatherNameId").val(parent);
		    			$("#fatherMobileNoId").val(phone);
		    		}else if(sturel == "mother"){
		    			$("#motherNameId").val(parent);
		    			$("#motherMobileNoId").val(phone);
		    		}else if(sturel == "guardian"){
		    			$("#gaurdianNameId").val(parent);
		    			$("#guardianMobileNoId").val(phone);
		    		}
		    		
		    		$("#primarypersonId").val(sturel);
		    		$("#hidprimary").val(sturel);
		    		 $(this).dialog('close');
		    	},
		    	'Close' : function() {
	                 $(this).dialog('close');
	             }
		    }
		});
	}
	
	if(url.split("&")[0] == "studentRegistration.html?method=modifyStudentDetails"){
		if($(".errormessagediv1 .validateTips1").text()=="")
		{
			$(".successmessagediv1").show();
			setTimeout(function(){
				window.location.href="menuslist.html?method=studentList";
			},2000);
		}
	}
	
	if(url.split("&")[0] == "studentRegistration.html?method=saveStudentRegistration"){
		if($(".errormessagediv1 .validateTips1").text()=="")
		{
			$(".successmessagediv1").show();
			setTimeout(function(){
				window.location.href="menuslist.html?method=studentList";
			},2000);
		}
	}
	
	
	
	
	$('.setImage').attr('src','images/profile_photo.png');
	
	if($('#nationalityhiddenid').val() != ""){
		$("#nationalityId  option[value='"+$('#nationalityhiddenid').val()+"' ]").attr('selected', 'true');
	}
	getAcademicYear();
	$("#workingTeacherId").change(function(){
		var staffId=$(this).val();
		var validatation=0;
		$.ajax({
			type : 'POST',
			url : "teacherregistration.html?method=getTeacherName",
			data: {'teachercode':staffId},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				validatation=result.jsonResponse.length;
				
				if(validatation >0){
					$("#workingTeacherName").val(result.jsonResponse[0].tfastname+" "+result.jsonResponse[0].tlastname);

				}else{
					$('#workingTeacherId').val("");
					$('#workingTeacherName').val("");
					$('.errormessagediv').css({
						'display' : 'block'
					});
					$('.validateTips').text("Invalid Id!!! Field Required A Valid Id");
					$("#workingTeacherId").css({
						'border':'1px solid #f00'
					});
					setTimeout(function() {
	        			$('#errorhover').fadeOut();
	        			document.getElementById("workingTeacherId").style.border = "1px solid #ccc";
	            		document.getElementById("workingTeacherId").style.backgroundColor = "#fff";
	        		}, 3000);
					return false;
				}
			}

		});
	});	
	
	
	$("#studSectionId").change(function(){
		
		
		var schoolLocationId = $("#schoolLocationId").val();
		var studClassId = $("#studClassId").val();
		var studSectionId = $("#studSectionId").val();
		var academicYear = $("#academicYear").val();
		
		$.ajax({
			type : 'POST',
			url : "dashboardData.html?method=classStudentCount",
			data: {'schoolLocationId':schoolLocationId,'studClassId':studClassId,'studSectionId':studSectionId,'academicYear':academicYear},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				if(parseInt(result.data[1]) > parseInt(result.data[0])){
					
					$('#studSectionId').val("");
					$('#studSectionId').val("");
					$('.errormessagediv').css({
						'display' : 'block'
					});
					$('.validateTips').text("Student limit exceed for this division");
					$("#studSectionId").css({
						'border':'1px solid #f00'
					});
					setTimeout(function() {
	        			$('#errorhover').fadeOut();
	        			document.getElementById("studSectionId").style.border = "1px solid #ccc";
	            		document.getElementById("studSectionId").style.backgroundColor = "#fff";
	        		}, 3000);
					return false;
				}
			}

		});
		
	});
	
	
	
	
	
	
	
	
	$('#checkAddressId').click(function(){
        if($(this).prop("checked") == true){
            var postadd=$('#paddrs').val();
        
            if(postadd != ""){
            	$('#presentadd').val(postadd);
            }else{
            	$(".errormessagediv").show();
            	
            	$(".validateTips").css({
            		'width':'300px'
            	});
        		$(".validateTips").text("Field Required Permanent Address");
        		$("#paddrs").focus();
        		document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
        		document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
        		setTimeout(function() {
        			$('#errorhover').fadeOut();
        		}, 3000);
        		
        		$('#checkAddressId').prop("checked",false) ;
            }
        }
        else if($(this).prop("checked") == false){
        	$('#presentadd').val('');
        }
    });
	
	$('#relationshipId').hide();
	$('#relationshipLabel').hide();

	$('#workingTeacherId').hide();
	$('#workingTeacherIdLabel').hide();

	$('#workingTeacherNameLabel').hide();
	$('#workingTeacherName').hide();


	$('#primarypersonId').change(function(){
		$("#hidprimary").val($(this).val());
		var primaryPerson=$(this).val();
		if(primaryPerson=="guardian"){
			////alert("show relationship");
			$('#relationshipId').show();
			$('#relationshipLabel').show();
		}else{
			////alert("hide relationship");
			$('#relationshipId').hide();
			$('#relationshipLabel').hide();
		}
	});
	$('#isWorking').change(function(){
		var isWorking=$(this).val();
		if(isWorking=="Yes"){
			////alert("show relationship");
			$('#workingTeacherId').show();
			$('#workingTeacherIdLabel').show();
			$('#workingTeacherNameLabel').show();
			$('#workingTeacherName').show();
		}else{
			////alert("hide relationship");
			$('#workingTeacherId').hide();
			$('#workingTeacherIdLabel').hide();
			$('#workingTeacherNameLabel').hide();
			$('#workingTeacherName').hide();
			$('#workingTeacherId').val("");
			$('#workingTeacherName').val("");
		}

	});

	


	if($("#sibilingadminnoId").val()!="" && $("#sibilingadminnoId").val()!=undefined){

		$("#fatherNameId").attr('readonly', true);
		$("#fatherMobileNoId").attr('readonly', true);

		$("#fatherQualificationId").attr('readonly', true);
		$("#fatheremailId").attr('readonly', true);

		$("#motherNameId").attr('readonly', true);
		$("#motherMobileNoId").attr('readonly', true);
		$("#motherQualificationId").attr('readonly', true);
		$("#motheremailId").attr('readonly', true);

		$("#gaurdianNameId").attr('readonly', true);
		$("#guardianMobileNoId").attr('readonly', true);
		$("#guardianemailId").attr('readonly', true);
		$("#motheroccupationId").attr('readonly', true);
		$("#fatheroccupationId").attr('readonly', true);
		$("#paddrs").attr('readonly', true);


		$('#primarypersonId').attr('disabled',true);
	}


	$("#primarypersonId").change(function(){
		$("#hprymarycntperson").val($("#primarypersonId").val());
		$("#hidprimary").val($(this).val());
	});

	// saving detils with validations

	$("#document1btn").hide();
	$("#document2btn").hide();
	$("#deleteProfile").hide();
	$("#deleteIDProof").hide();
    
	 
	$("#save").click(function(){
		$("#academicYear").prop("disabled",false);
		
		var form = $('#formstudent')[0];
		var data = new FormData(form);
	
		
		data.append("locationname",$("#schoolLocationId").val());
		
		if (validateFunction())
		{
			if (($('#studentid').val() == "" || $('#studentid').val() == null) ) {
				
				$("#primarypersonId").prop("disabled",false);
				
				$.ajax({
					type : "POST",
					enctype: 'multipart/form-data',
					url : "studentRegistration.html?method=saveStudentRegistration",
					data : data,
					beforeSend: function(){
						$("#loder").show();
					},
					cache: false,
					contentType: false,
					processData: false,
					success : function(data) {
						var result = $.parseJSON(data);
						if(result.status == "success"){
							$("#loder").hide();
							$('.errormessagediv').hide();
							$("#loder").hide();
							$(".successmessagediv").show();
							$('.successmessage').text("Adding Record in Progressing...");
							setTimeout(function() {
								window.location.href="menuslist.html?method=studentList";
							}, 3000);
						}else if(result.status == "error"){
							$("#loder").hide();
							$('.errormessagediv').show();
							$("#loder").hide();
							$(".successmessagediv").hide();
							$(".validateTips").text("Failed to save student details...");
							setTimeout(function() {
								window.location.href="menuslist.html?method=studentList";
							}, 3000);
						}else if(result.status == "duplicate"){
							$("#loder").hide();
							$('.errormessagediv').show();
							$("#loder").hide();
							$(".successmessagediv").hide();
							$(".validateTips").text("Student Already Registered  With This Details");
						}
						
					}
				});
				 $("#save").prop("disabled", true);
			} 
			else
			{
				$("#primarypersonId").prop("disabled",false);
			
				
				$.ajax({
					type : "POST",
					enctype: 'multipart/form-data',
					url : "studentRegistration.html?method=modifyStudentDetails",
					data : data,
					beforeSend: function(){
						$("#loder").show();
					},
					cache: false,
					contentType: false,
					processData: false,
					success : function(data) {
						var result = $.parseJSON(data);
						
						if(result.status == "success"){
							$("#loder").hide();
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							//$(".validateTips").text("Updating Record in Progress...");
							$('.successmessage').text("Updating Record in Progress...");
							setTimeout(function() {
								 window.location.href = "menuslist.html?method=studentList&historystatus="+$("#historystatus").val()+
								   "&historyacademicId="+$("#historyacademicId").val()+"&historylocId="+$("#historylocId").val()+
								   "&historyclassname="+$("#historyclassname").val()+"&historysectionid="+$("#historysectionid").val()+
								   "&historysearchvalue="+$("#historysearchvalue").val();
							}, 3000);
						}else if(result.status == "error"){
							$('.errormessagediv').show();
							$("#loder").hide();
							$(".successmessagediv").hide();
							$(".validateTips").text("Student Is Not Registred.");
							setTimeout(function() {
								 window.location.href = "menuslist.html?method=studentList&historystatus="+$("#historystatus").val()+
								   "&historyacademicId="+$("#historyacademicId").val()+"&historylocId="+$("#historylocId").val()+
								   "&historyclassname="+$("#historyclassname").val()+"&historysectionid="+$("#historysectionid").val()+
								   "&historysearchvalue="+$("#historysearchvalue").val();
							}, 3000);
						}else if(result.status == "duplicate"){
							$('.errormessagediv').show();
							$("#loder").hide();
							$(".successmessagediv").hide();
							$(".validateTips").text("Student Already Registered  With This Details");
						}
						
					}
				});
				 $("#save").prop("disabled", true);
			
			}
		}

	});

	$("#imagePreview").show();
	$("#imageFatherPreview").hide();
	$("#imageMotherPreview").hide();
	$("#imageGuardianPreview").hide();
	$("#studentstatuslable").hide();
		
	var setImage=$('.setImage').attr("src");
	var setHiddenImage=$('#photohiddenid').val().trim();
	if((setHiddenImage == 'images/profile_photo.png' || setHiddenImage == "") && setImage == 'images/profile_photo.png' ){
		$("#removeSpanId").hide();
		//$("#imagePreview").prop("src","c")
	}else if(setHiddenImage == 'images/profile_photo.png'){
		$("#removeSpanId").hide();
	}else{
		$("#removeSpanId").show();
	}
	
	$("#studentImageId1").change(function() {
		$("#removeSpanId").show();
		if(checkFile1() == false){
			$("#imagePreview").hide();
			
		}else{
			$("#imagePreview").show();
			/*$("#removeSpanId").hide();*/
			readURL(this);
		}
		
	});
	
	$("#removeSpanId").click(function(){
		$('.setImage').attr('src','images/profile_photo.png');
		$('#photohiddenid').val("");
		$("#removeSpanId").hide();
	});

	$("#fatherImageId").change(function() {
		if(checkFile2() == false){
			$("#imageFatherPreview").hide();
			
		}else{
			$("#imageFatherPreview").show();
			readFatherURL(this);
		}
	});

	$("#motherImageId").change(function() {
		if(checkFile4() == false){
			$("#imageMotherPreview").hide();
		}else{
			$("#imageMotherPreview").show();
			readMotherURL(this);
		}
	});

	$("#guardianImageId").change(function() {
		if(checkFile3() == false){
			$("#imageGuardianPreview").hide();
		}else{
			$("#imageGuardianPreview").show();
			readGuardianURL(this);
		}
	});

	$("#cencessionY").click(function() {
		$("#scholarShipId").show();
		$("#cencessionlable").show();

	});

	$("#cencessionN").click(function() {

		$("#scholarShipId").hide();
		$("#cencessionlable").hide();

	});

	$("#transportId").change(function() {
		if($("#transportId").val() == 'Y'){
			$("#transportcategoryType").hide();
			$("#trnsnoreason").hide();
			$("#transcategory").show();
			$("#transportcategorylabel").show();
			$("#transportlocationlabel").show();
			$("#translocation").show();
			getTransportLocations();
			$("#routelabel").show();
			$("#route").show();
			getRouteDetails();
			getTransportCategory();
		}else{
			$("#transportcategoryType").show();
			$("#trnsnoreason").show();
			$("#transportlocationlabel").hide();
			$("#transcategory").hide();
			$("#transportcategorylabel").hide();
			$("#translocation").hide();

			$("#routelabel").hide();
			$("#route").hide();
		}
		
 
	});
	 
	   
	$("#translocation").change(function() {
		getRouteDetails();

	});


	$("#physicalchalreason").hide();
	$("#physicalchlngres").hide();

	$("#physicallyChallengedId").click(function(){
		if($("#physicallyChallengedId").val() == "YES"){
			$("#physicalchalreason").show();
			$("#physicalchlngres").show();
		}else{
			$("#physicalchalreason").val("");
			$("#physicalchalreason").hide();
			$("#physicalchlngres").hide();
		}
		

	});

	$("#transportIdN").click(function() {

		$("#transportlocationlabel").hide();
		$("#transcategory").hide();
		$("#transportcategorylabel").hide();
		$("#translocation").hide();

		$("#routelabel").hide();
		$("#route").hide();

	});
	/*$("#transcategory").change(function() {
		if ($("#transcategory").val().trim() != '') {

			if (getTransportCategoryType($("#transcategory").val().trim()) == 'Y') {

				$("#trnporttypestatus").val('Y');

				$("#transportlocationlabel").show();
				$("#translocation").show();
				getTransportLocations();

			} else {
				$("#trnporttypestatus").val('N');
				$("#transportlocationlabel").hide();
				$("#translocation").hide();
				$("#routelabel").hide();
				$("#route").hide();
			}

		} else {
			$("#trnporttypestatus").val('N');
			$("#transportlocationlabel").hide();
			$("#translocation").hide();
			$("#routelabel").hide();
			$("#route").hide();
		}

	});*/



	$("#translocation").change(function() {
		$("#routelabel").show();
		$("#route").show();
		
	});


	// hiding ids
	$('.successmessagediv').hide();

	$('#cencessionlable').hide();
	$('#transportcategorylabel').hide();
	$('#transportlocationlabel').hide();

	$("#transcategory").hide();
	$("#translocation").hide();

	$("#routelabel").hide();
	$("#route").hide();
	$('#admissionDialog').hide();

	// Editing Fuction

	$("#editStudent").click(function() {
		var stdId=$('input[name="selectBox"]:checked').val();
		
		if (stdId==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any One Student");
		} else {
			$('#studentid').stdId;
			accyear=stdId.split(" ")[1];
			stdId=stdId.split(" ")[0];
			window.location.href = "studentRegistration.html?method=editStudent&searchTerm="+ stdId+","+accyear;
		}

	});

	// Delete Function

	$("#trash").click(function() {
		var stdId=$('input[name="selectBox"]:checked').val();

		if (stdId==undefined)  {
			$('.errormessagediv').show();
			$('.validateTips').text(
			"Select Any One Student");
		} else {
			$('#studentid').stdId;

			var Check = {
					"studentid" : stdId
			};
			
			$.ajax({
				type : "POST",
				url : "studentRegistration.html?method=deactivateStudent",
				data : Check,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);

					if (result.status == true) {
						$('.successmessagediv').show();
						$('.successmessage').text("Student Deleted Successfully");

						setTimeout(function() {	location.reload(); }, 1000);
					} else {
						$('.errormessagediv').show();
						$('.validateTips').text("Student already mapped can't Delete");

						setTimeout(function() { location.reload(); }, 1000);
					}

				}
			});
		}

	});
	
	$('#excelDownload').click(function() {

		window.location.href = 'studentRegistration.html?method=downloadStudentDetailsXLS';

	});
	$("#pdfDownload").click(function(){

		window.location.href = "studentRegistration.html?method=downloadStudentDetailsPDF";

	});
	
	
	$('#admissionform').click(function() {
		$("#temp_studentName").val("");
		$("#temp_parentName").val("");
		$("#temp_mobileNo").val("");
		getTempAdmissionDetailsList();
		$("#admissionDialog").dialog("open");
	});
	
	
	$("#temp_studentName").keyup(function(){
		if($(this).val()==""){
			getTempAdmissionDetailsList();
		}
	});
	$("#temp_parentName").keyup(function(){
		if($(this).val()==""){
			getTempAdmissionDetailsList();
		}
	});
	$("#temp_mobileNo").keyup(function(){
		if($(this).val()==""){
			getTempAdmissionDetailsList();
		}
	});
	// calling methods

	getLocalAcademicYear();
	getReligionDetails();

	$('#religion').change(function() {
		getCasteDetails();
		$('#casteCategoryId').val("");
	});
	
	$('#casteId').change(function() {
		getCasteCategoryDetails();
		
	});
	
	$('#studClassId').change(function() {
		var classId=$('#studClassId').val();
		var section = "#" + "studSectionId";
		//var subjectId = "#" + "studSubId";
		getClassSection(section, studClassId);
		getClassSpecilization(classId,$("#schoolLocationId").val().trim(),"-");
		
	});

	$('workingTeacherId').change(function(){
		var workingTeacherId=$('workingTeacherId').val();

	});

	categoryId = "category";
	getCategory(categoryId);
	getHouse($("#schoolLocationId").val());

$("#schoolLocationId").change(function(){
	 categoryId = "category";
	getCategory(categoryId);
	getHouse($(this).val());
	getClassName("studClassId", "category");
	
	/*removed autogeneration student admission No.
	 * if($("#studentid").val() != ""){
		
	}else{
		getAdmissionNo();
	}*/
	
});

if($("#schoolLocationId").val() != undefined){
	categoryId = "category";
	getCategory(categoryId);
}
	
	$('#category').change(function() {
		var classId = "studClassId";
		getClassName(classId, category);
		$('#studSectionId').val("");
		$('#specilization').val("");
	});
	
	$("#transportId option[value='N' ]").attr('selected', 'true');
	$("#isWorking option[value='No' ]").attr('selected', 'true');
	$("#studentMediumId option[value='English' ]").attr('selected', 'true');
	
	$('#removefileattach1').click(function(){
		$('#fileattachment1Div').remove();
	});
	$('#removefileattach2').click(function(){
		$('#fileattachment2Div').remove();
	});

	$('#removefileattach3').click(function(){
		$('#fileattachment3Div').remove();
	});
	$('#removefileattach4').click(function(){
		$('#fileattachment4Div').remove();
	});
	$('#removefileattach5').click(function(){
		$('#fileattachment5Div').remove();
	});

  
	if ($('#streamhiddenId').val() != "" && $('#streamhiddenId').val()!=null){
		
		$("#schoolLocationId option[value="+ $('#schoollocationhiddenid').val() + "]").attr('selected', 'true');
		getLocalAcademicYear();
		/*getQuota();
		getConssition();*/
		getTransportLocations();
		getTransportCategory();

		var categoryId = "category";
 
		getCategory(categoryId);

		var classId1 = "studClassId";

		getClassName(classId1, $('#streamhiddenId').val());

		var subjectId1 = "#" + "studSectionId";

		getClassSection(subjectId1, $('#classhiddenid').val());

		$('#studentIdhidden').val($('#studentid').val());
		if($('#studentid').val()!=undefined)
		$('#studentIdhidden').val($('#studentid').val().trim());
		
		
		if($('#classhiddenid').val()!=undefined && $('#schoollocationhiddenid').val()!=undefined)
		getClassSpecilization($('#classhiddenid').val().trim(),$('#schoollocationhiddenid').val().trim());
		
		
		
		var StudentImage=$("#photohiddenid").val();

		if(StudentImage!=""){

			$("#imagePreview").show();
			$('#imagePreview').attr('src', StudentImage);
		}

		var fatherImage=$("#fatherphotohiddenid").val();

		if(fatherImage!=undefined && fatherImage!="" && fatherImage.split("/")[2] != "noImage.png"){

			$("#imageFatherPreview").show();
			$('#imageFatherPreview').attr('src', fatherImage);
		}else{
			$("#imageFatherPreview").hide();
		}
		
		var motherImage=$("#motherphotohiddenid").val();
		if(motherImage!=undefined && motherImage!="" && motherImage.split("/")[2] != "noImage.png"){

			$("#imageMotherPreview").show();
			$('#imageMotherPreview').attr('src', motherImage);
		}else{
			$("#imageMotherPreview").hide();
		}
		
		var guardImage=$("#guardphotohiddenid").val();

		if(guardImage!=undefined && guardImage!="" && guardImage.split("/")[2] != "noImage.png"){

			$("#imageGuardianPreview").show();
			$('#imageGuardianPreview').attr('src', guardImage);
		}else{
			$("#imageGuardianPreview").hide();
		}
		
		var birthcertificate=$("#birthcertificatehiddenid").val();
		var transfercertificate=$("#transfercertificatehiddenid").val();
		var certificate1=$("#certificate1hiddenid").val();
		var certificate2=$("#certificate2hiddenid").val();
		var certificate3=$("#certificate3hiddenid").val();
		var certificate4=$("#certificate4hiddenid").val();
		var certificate5=$("#certificate5hiddenid").val();

 
		if(birthcertificate.trim()!="" && birthcertificate!=undefined){

			$("#document1btn").attr('name',birthcertificate);
			$("#uploadBirth").hide();
			$("#document1btn").show();
			$("#deleteProfile").show();

		}

		if(transfercertificate.trim()!="" && transfercertificate!=undefined){
           
			$("#document2btn").attr('name',transfercertificate);
			$("#uploadTransfer").hide();
			$("#document2btn").show();
			$("#deleteIDProof").show();
		}
		
		if(certificate1.trim()!="" && certificate1!=undefined){
			
			$("#fileattachment1Div").show();
			$("#document4btn").attr('name',certificate1);
			$("#firstdwnatt").show();
			$("#firstuplatt").hide();
		}
		
		if(certificate2.trim()!="" && certificate2!=undefined){
			$("#fileattachment2Div").show();
			$("#document5btn").attr('name',certificate2);
			
			$("#secdwnatt").show();
			$("#secuplatt").hide();
		}
		if(certificate3.trim()!="" && certificate3!=undefined){
			$("#fileattachment3Div").show();
			$("#document6btn").attr('name',certificate3);
			
			$("#thirddwnatt").show();
			$("#thirduplatt").hide();
		}
		if(certificate4.trim()!="" && certificate4 !=undefined){
			$("#fileattachment4Div").show();
			$("#document7btn").attr('name',certificate4);
			
			$("#fourdwnatt").show();
			$("#fouruplatt").hide();
		}
		if(certificate5.trim()!="" && certificate5 !=undefined){
			$("#fileattachment5Div").show();
			$("#document2btn").attr('name',certificate5);
		
			$("#fivedwnatt").show();
			$("#fivesuplatt").hide();
		}

		$("#firstdwnfile").click(function(){
			$("#firstdwnatt").hide();
			$("#firstuplatt").show();
		});
		
		$("#secdwnfile").click(function(){
			$("#secdwnatt").hide();
			$("#secuplatt").show();
		});
		$("#thirddwnfile").click(function(){
			$("#thirddwnatt").hide();
			$("#thirduplatt").show();
		});
		$("#fourdwnfile").click(function(){
			$("#fourdwnatt").hide();
			$("#fouruplatt").show();
		});
		$("#fivedwnfile").click(function(){
			$("#fivedwnatt").hide();
			$("#fivesuplatt").show();
		});
		
		$('.downloadDoc').click(function() {
			var path = $(this).attr('name');
			window.location.href = "studentRegistration.html?method=downloadDocument&Path="
				+ path.trim();
		});

		$("#deleteIDProof").click(function(){
             
			$("#uploadTransfer").show();
			$("#document2btn").hide();
			$("#deleteIDProof").hide();
			$("#transfercertificatehiddenid").val("");
		});

		$("#deleteProfile").click(function(){
			$("#uploadBirth").show();
			$("#document1btn").hide();
			$("#deleteProfile").hide();
			$("#birthcertificatehiddenid").val("");
			
		});
		
	if($('#classhiddenid').val()!="" || $('#classhiddenid').val()!=undefined){
		
		$("#primarypersonId option[value='"+ $('#selected_Primary_hiddenId').val()+"']").attr('selected', 'true');

		$("#hidprimary").val($('#selected_Primary_hiddenId').val());
		
		$('#parentId').val($('#parenthiddenId').val());

		$("#academicYear option[value="+ $('#acchiddenId').val() + "]").attr('selected', 'true');
		 
		/*$("#category").val($('#streamhiddenId').val());*/
		 
		$("#category option[value="+ $('#streamhiddenId').val() + "]").attr('selected', 'true');

		$("#studClassId option[value="+ $('#classhiddenid').val() + "]").attr('selected', 'true');
        
		$("#studSectionId option[value="+ $('#sectionhiddenid').val() + "]").attr('selected', 'true');

		$("#studentquotaname option[value="+ $('#quotahiddenid').val() + "]").attr('selected', 'true');

		$('input[name=rte][value='+ $('#rtehiddenid').val() + ']').attr('checked', true);

		$('input[name=hostel][value='+ $('#hostelhiddenid').val() + ']').attr('checked', true);

		$('input[name=cencession][value='+ $('#concessionaplicablehidden').val()+ ']').attr('checked', true);

		if ($("#concessionaplicablehidden").val()!=undefined && $("#concessionaplicablehidden").val().trim() == "Y") {

			$('#cencessionlable').show();

			$("#scholarShipId option[value="+ $('#concessionhiddenid').val()+ "]").attr('selected', 'true');
		}
	 
		$("#isWorking option[value="+ $('#parentsguardianhiddenid').val() + "]").attr('selected', 'true');
		
		$("#studentMediumId option[value="+ $('#mediumhiddenid').val() + "]").attr('selected', 'true');
		
		$("#specilization option[value="+ $('#specilizationhiddenid').val() + "]").attr('selected', 'true');
		

		$('#transportId option[value='+ $('#transporthiddenid').val() + ']').attr('selected', true);
		
		$('#firstlang option[value='+ $('#firstlanghiddenid').val() + ']').attr('selected', true);
		
		$('#secondlang option[value='+ $('#secondlanghiddenid').val() + ']').attr('selected', true);
		
		$('#thirdlang option[value='+ $('#thirdlanghiddenid').val() + ']').attr('selected', true);
		
		if($('#hiddentrnsnoreason').val()!=""){
			$("input[type='radio']").each(function(){
                			
				if($(this).attr('class')==$('#hiddentrnsnoreason').val()){
					$(this).prop("checked",true);
				}
				
				//$('.trnsnoreason option[value='+ $('#hiddentrnsnoreason').val() + ']').attr('checked', true);
			});	
		}
		
		
		
		
		
	}
		if($('#parentsguardianhiddenid').val()!=undefined && $('#parentsguardianhiddenid').val().trim() == 'Yes'){
			$('#workingTeacherId').show();
			$('#workingTeacherIdLabel').show();
			$('#workingTeacherNameLabel').show();
			$('#workingTeacherName').show();
		}

		if ($('#transporthiddenid').val()!=undefined && $('#transporthiddenid').val().trim() == "Y") {
			getTransportLocations();
			$("#transportcategoryType").hide();
			$("#trnsnoreason").hide();
			$("#transcategory").show();

			$("#transportcategorylabel").show();
			$("#transcategory option[value="+ $('#transportcategoryhiddenid').val() + "]").attr('selected', 'true');
			
			$("#transportlocationlabel").show();
			$("#translocation").show();
			$("#translocation option[value="+ $('#transportlocationhiddenid').val() + "]").attr('selected', 'true');
			$("#route").show();
			$("#routelabel").show();
			getRouteDetails();
			$("#route option[value="+ $('#transportroutehiddenid').val() + "]").attr('selected', 'true');

			if ($('#typecollectfeehidden').val().trim() == "Y") {

				$("#trnporttypestatus").val($('#typecollectfeehidden').val().trim());
			}
		}
		getReligionDetails();
		
		getCasteDetails();
		
		getCasteCategoryDetails();
		
		$('#genderId option[value='+ $('#genderhiddenid').val().trim()+ ']').attr('selected', true);

		$("#bloodGroupId  option[value='"+$('#bloodhiddenid').val().trim()+"' ]").attr('selected', 'true');
		
		$("#mothertongueId  option[value='"+$('#mothertongehiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#religion  option[value='"+$('#religionhiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#casteId  option[value='"+$('#castehiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#casteCategoryId  option[value='"+$('#castecategoryhiddenid').val().trim()+"' ]").attr('selected', 'true');
		$("#studentStatusId  option[value='"+$('#statushiddenid').val().trim()+"' ]").attr('selected', 'true');

		$('#physicallyChallengedId option[value='+ $('#physicallychallengedhiddenid').val().trim() + ']').attr('selected', true);
		if ($('#physicallychallengedhiddenid').val().trim() == "YES") {

			$("#physicalchalreason").val($('#physicallychallengeddescriptionhiddenid').val().trim());

			$("#physicalchlngres").show();
			$("#physicalchalreason").show();
			
		}
		
		$("#studentstatuslable").show();

		$("#studentStatusId option[value="+ $('#studentStatushiddentid').val()+ "]").attr('selected', 'true');

		$('input[name=primaryPerson][value='+ $('#selected_Primary_hiddenId').val()+ ']').attr('checked', true);

		$("#paddrs").val($('#addresshiddenid').val());
		/*if($('#presentaddresshiddenid').val() != ""){
			$('#checkAddressId').prop('checked', true);
			$("#presentadd").val($('#presentaddresshiddenid').val());
		}else{
			$('#checkAddressId').prop("checked",false) ;
		}*/
		$("#presentadd").val($('#presentaddresshiddenid').val());
		
		$("#fatherOccupation option[value="+ $('#fatheroccupationhiddenid').val()+ "]").attr('selected', 'true');
		
		$("#motherOccupation option[value="+ $('#motheroccupationhiddenid').val()+ "]").attr('selected', 'true');
		
		$("#guardianOccupation option[value="+ $('#guardoccupationhiddenid').val()+ "]").attr('selected', 'true');
		
	}

	// date pickers
	
	var admissionhiddenid=$('#admissionhiddenid').val();
	if(admissionhiddenid != "" || admissionhiddenid != null){
		$("#dateofJoinId").val(admissionhiddenid);
	}else{
		var dNow = new Date();
		var Day=dNow.getDate();
		if (Day < 10) {
			Day = '0' + Day;
		} //end if
		var Month = dNow.getMonth() + 1;
		if (Month < 10) {
			Month = '0' + Month;
		} 

		var localdate= Day + '-' + Month + '-' + dNow.getFullYear();
		$("#dateofJoinId").val(localdate);
	}
	
	/*$("#studentFirstNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
		
	});*/
	
	/*$("#applicationNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});*/
	
	$('#applicationNoId').keypress(function (e) {
        var regex = new RegExp(/^[a-zA-Z0-9-\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
        	 HideError(this);
            return true;
        }
        e.preventDefault();
        $(".errormessagediv").hide();
        $(".errormessagediv1").show();
		$(".validateTips1").text("Allows only alphanumerics and -");
		setTimeout(function() {
				$('.errormessagediv1').hide();
		},3000);
        return false;
     
    });
	$('#mothertongueId').keypress(function (e) {
		
        var regex = new RegExp(/^[a-zA-Z\s]*$/);
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
                 HideError(this);
            return true;
        }
        e.preventDefault();
      /*  $(".errormessagediv").show();
                $(".validateTips").text("Allows only alphanumerics and -, (, ), [, ], &");
                setTimeout(function() {
                        $('#errormessagediv').fadeOut();
                    },3000);*/
        return false;
     
    });
	
	
	
	/*$("#studentLastNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});*/
	
	
	
	/*$("#fatherNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});*/
	
	$("#fatherMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	$("#aadharId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	$("#emergencynumber").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	/*$("#motherNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});*/
	
	$("#motherMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv").hide();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});
	
	/*$("#gaurdianNameId").keydown(function(e){
		var key=e.keyCode;
		if(!((key == 8) || ((key == 16)) || (key == 32) || (key == 46) || (key == 9) || (key >= 35 && key <= 40) || (key >= 65 && key <= 90))){
			e.preventDefault();
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Text.");
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});*/
	
	$("#guardianMobileNoId").keypress(function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$(".errormessagediv1").show();
			$(".validateTips1").text("Enter Only Number."); 
			$(".errormessagediv1").delay(2000).fadeOut();
			return false;
		}
	});

	
	
	if($("span.successmessage").text()=="Student details updated Successfully"){
		$("#div1 #successmessages").attr("style","display:block");
	} 

	var cnt=0;
	$("#fileUploaddynmic").click(function(){
		var check = null;

		if(cnt<5){
			cnt++;
			$("#fileattachment"+cnt+"Div").show();
			$("#fileattachment"+cnt+"label").show();
			$("#filaattachment"+cnt).show();
			$("#deleteFile" + cnt).show();
			$(".dwnl" + cnt).hide();
		}
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
	

	
	
	
	
	if($("#househiddenid").val()!=""){
		getHouse($("#schoolLocationId").val());
	}
	getHouse($("#schoolLocationId").val());
	
	
		var res=checkFeesOfStudent();
		if(res=="true"){
			$("#studClassId option").not($("#studClassId option[value="+$("#classhiddenid").val()+"]")).remove();
			$("#category option").not($("#category option[value="+$("#streamhiddenId").val()+"]")).remove();
			$("#studSectionId option").not($("#studSectionId option[value="+$("#sectionhiddenid").val()+"]")).remove();
			$("#academicYear option").not($("#academicYear option[value="+$("#acchiddenId").val()+"]")).remove();
			$("#studClassId,#category,#studSectionId,#academicYear").click(function(){
				$(".errormessagediv1").show();
				$(".validateTips1").text("Student Already Mapped With Fees"); 
				$(".errormessagediv1").delay(2000).fadeOut();
			});
		}
		
		if($('#hPriveliges').val()=="N" ){
            
       $("#schoolLocationId").find("option").not("option[value="+$("#hschoolLocation").val()+"],option[value='']").remove();
    }
		
		$("#uploadBirth").change(function(){
			var filename = $("#uploadBirth").val().substr($("#uploadBirth").val().length - 3).trim();
			 
			var status=true;
			
			if(filename=="pdf" || filename=="jpg" || filename=="png"){
				status=false;
			}
			
			if(status){
				document.getElementById("uploadBirth").style.border = "1px solid #AF2C2C";
				document.getElementById("uploadBirth").style.backgroundColor = "#FFF7F7";
				$("#uploadBirth").val("");
				$(".errormessagediv").show();
				$(".validateTips").text("Select file with only .png ,.jpg and .pdf extension.");
				setTimeout(function() {
					$(".errormessagediv").fadeOut();
				}, 3000);
			  }
		});
		
		$("#uploadTransfer").change(function(){
			var filename = $("#uploadTransfer").val().substr($("#uploadTransfer").val().length - 3).trim();
			
            var status=true;
			
			if(filename=="pdf" || filename=="jpg" || filename=="png"){
				status=false;
			}
			
			if(status){
				document.getElementById("uploadTransfer").style.border = "1px solid #AF2C2C";
				document.getElementById("uploadTransfer").style.backgroundColor = "#FFF7F7";
				$("#uploadTransfer").val("");
				$(".errormessagediv").show();
				$(".validateTips").text("Select file with only .png , .jpg and .pdf extension.");
				setTimeout(function() {
					$(".errormessagediv").fadeOut();
				}, 3000);
			   }
		});
	$("#istransferstudent").change(function(){
		if($(this).val()=="Yes"){
			$("#feeapplicabletermlabel").show();
			getTermList($("#schoolLocationId").val(),$("#academicYear").val(),$("#feeapplicableterm"));
		}
		else{
			$("#feeapplicabletermlabel").hide();
			$("#feeapplicableterm").empty();
		}
	});
	
	if($("#istransferstudenthidden").val()=="Yes"){
		$("#istransferstudent").val($("#istransferstudenthidden").val());
		$("#feeapplicabletermlabel").show();
		getTermList($("#schoolLocationId").val(),$("#academicYear").val(),$("#feeapplicableterm"));
	}
	
	
	
	$("#specilization").change(function(){
		
		var classId = $("#studClassId").val();
		if($("#specilization").val()==""){
			getClassLanguage(classId,locationId,'-');
		}else{
			getClassLanguage(classId,locationId,$('#specilization').val().trim());
		}
	});
	
	$("#studentrollno").keypress(function(e) {
		var k = e.keyCode,
		$return = ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32  || (k >= 48 && k <= 57) || k == 47 || k == 92 || k == 45 || k == 32);
		if(!$return) {
			return false;
		}

	})
	
	if($("#modiallow").val() == "notallow"){
		$("#academicYear").prop("disabled",true);
	}
});
// fuctions to get values
function checkFeesOfStudent(){
	var res;
	var stuId=$("#studentid").val().trim();
	var accYear=$("#academicYear").val().trim();
	var dataList={
			"stuId":stuId,
			"accYear":accYear,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=checkFeesOfStudent",
		data:dataList,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.status=="true"){
				res=result.status;
			}
		}
	});
	return res;
}
function getLocalAcademicYear(academicYear) {
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#academicYear").html("");
			

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#academicYear").append(
						'<option value="'+ result.jsonResponse[j].academicYearId + '">'+ result.jsonResponse[j].academicYear+ '</option>');
			}
		}
	
	});
	
	$("#academicYear option[value="+$("#hacademicyaer").val().trim()+"]").attr("selected",true);
}

function getCategory(categoryval) {
	 
	var category = "#" + categoryval;
	if($("#schoolLocationId").val()!=""){
		 locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val();
		if($("#schoollocationhiddenid").val()!=undefined)
		locationId=$("#schoollocationhiddenid").val().trim();
	}
	 
	var dataList={
			"locationId":locationId,
	};
	$.ajax({
		type : 'GET',
		url : "studentRegistration.html?method=getCategory",
		data:dataList,
		async : false,
		success : function(response) {
			$("#category").html("");
			$("#category").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			var result = $.parseJSON(response);
		
			for ( var j = 0; j < result.CategoryList.length; j++) {

				$("#category").append(
						'<option value="' + result.CategoryList[j].streemcode
						+ '">' + result.CategoryList[j].streemname
						+ '</option>');
			}
		}
	});
}
function getClassName(classidval, category) {

	var classid = "#"+classidval;
	var categoryVal = null;
	
	if ($("#category").val() !="" ) {
		categoryVal = $("#category").val();
	}
	else if (($('#streamhiddenId').val() != "" && $('#streamhiddenId').val() != null && $("#category").val() =="" )) {
		categoryVal = $("#streamhiddenId").val();
	}else if($('#streamhiddenId').val() == $("#category").val()){
		categoryVal = $("#streamhiddenId").val();
	}else {
		categoryVal = $('#category').val();
	}
	
	if($("#schoolLocationId").val()!=""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val();
		if($("#schoollocationhiddenid").val()!=undefined)
		locationId=$("#schoollocationhiddenid").val().trim();
	}
	
	datalist = {
			"categoryVal" : categoryVal,
			"location":locationId,
	}, 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassDetail",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(classid).html("");
			$(classid).append('<option value="' + "" + '">----------Select----------</option>');
			
			for ( var j = 0; j < result.ClassList.length; j++) {
				$(classid).append(
						'<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}

		}
	});

}
function getClassSection(sectionid, studClassId) {
	
	var classidVal = null;
	
	if ( $("#studClassId").val() !="" ) {
		classidVal = $("#studClassId").val();
	}
	else if (($('#classhiddenid').val() != "" && $('#classhiddenid').val() != null && $("#studClassId").val() =="" )) {
		classidVal = $("#classhiddenid").val();
	}else if($('#classhiddenid').val() == $("#studClassId").val()){
		classidVal = $("#classhiddenid").val();
	}else {
		classidVal = $('#studClassId').val();
	}
	
	if($("#schoolLocationId").val()!=""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val();
		if($("#schoollocationhiddenid").val()!=undefined)
		locationId=$("#schoollocationhiddenid").val().trim();
	}
	
	/*if ($('#classhiddenid').val() == null || $('#classhiddenid').val() == "") {
		classidVal = $("#studClassId").val();
	} else {
		classidVal = $('#classhiddenid').val();
	}*/
	datalist = {
		"classidVal" : classidVal,
		"locationId" : locationId,
	}, $.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(sectionid).html("");
			$(sectionid).append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$(sectionid).append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}

		}
	});

}

function getTransportCategory() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategory",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#transcategory").html("");
			$("#transcategory").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			for ( var j = 0; j < result.transportCategory.length; j++) {
				$("#transcategory").append(
						'<option value="'
						+ result.transportCategory[j].transptyId + '">'
						+ result.transportCategory[j].transptyname
						+ '</option>');
			}

		}
	});

}
function getTransportLocations() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportStages",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$("#translocation").html("");
			$("#translocation").append('<option value="' + "" + '">' + "----------Select----------" + '</option>');
			for ( var j = 0; j < result.transportstages.length; j++) {
				$("#translocation").append(
						'<option value="' + result.transportstages[j].stageCode
						+ '">' + result.transportstages[j].stageName
						+ '</option>');
			}

		}
	});

}

function getTransportCategoryType(typeId) {
	var type = typeId;
	 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTransportCategoryType",
		data : {
			"typeId" : typeId
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			type = result.transportTypeStatus;

		}
	});

	return type;

}
function validatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#motherMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}
			});
		}
	}
}
function fathervalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#fatherMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function gaurdianvalidatePhoneNo() {
	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var phoneId = $('#guardianMobileNoId').val();
		if ((phoneId != '' && phoneId != undefined)) {
			phoneNo = {
				"phoneId" : phoneId
			},

			$.ajax({
				url : "studentRegistration.html?method=validatePhoneNo",
				data : phoneNo,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}
function mothervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {

		var emailid = $('#motheremailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function fathervalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#fatheremailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function gaurdianvalidateEmail() {

	var siblingVal = $("#sibilingadminnoId").val();

	if (siblingVal.length != 0) {

	} else {
		var emailid = $('#guardianemailId').val();

		if ((emailid != '' && emailid != undefined)) {
			emailCheck = {
				"emailid" : emailid
			},

			$.ajax({
				url : "studentRegistration.html?method=validateEmail",
				data : emailCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					$('.errormessagediv').css({
						'display' : ''
					});
					$('.validateTips').text(response.message);
				}

			});
		}
	}
}

function ageCalculate() {
	
	var doofBirth = $('#dateofBirthId').val();

	var birthYear = doofBirth.split("-")[2];

	var currentYear = new Date().getFullYear();

	var yearDiff = parseInt(currentYear) - parseInt(birthYear);

	$('#ageId').val(yearDiff);

}

// validations

function validateFunction() {
	
	var bValid = true;
	$("#hprymarycntperson").val($("#primarypersonId").val());

	//Student Details 
	
	var studentFirstName = $('#studentFirstNameId').val();
	var studentrollno = $('#studentrollno').val(); 
	var dateofJoin = $('#dateofJoinId').val();
	var academicYear = $('#academicYear').val(); 
	var category = $('#category').val(); 
	var studClassId = $('#studClassId').val(); 
	var studSectionId = $('#studSectionId').val(); 
	var schoolname=$("#schoolLocationId").val();
	var istransport = $('#transportId').val();
	var transportCategory = $('#transcategory').val(); 
	var transportLocation = $('#translocation').val();
	var staffid=$('#workingTeacherId').val();
	var isWorking=$('#isWorking').val();
	var applicationNoId=$('#applicationNoId').val();
	
	//For personal information  
	
	var dateofBirth = $('#dateofBirthId').val(); 
	var gendertype = $('#genderId').val();
	var religion = $('#religion').val(); 
	var caste = $('#casteId').val(); 
	var castecatagory = $('#casteCategoryId').val();
	var physicallychallengedtype = $('#physicallyChallengedId').val();
	var physicallychallengereason=$("#physicalchalreason").val();
	var studentStatus=$("#studentStatusId").val();
	var mothertongueval=$("#mothertongueId").val();
	
	var aadharId = $("#aadharId").val();
	var emergencynumber = $("#emergencynumber").val().trim();
	//Prents Information 
	
	var fathername = $('#fatherNameId').val();
	var fathermobileno = $('#fatherMobileNoId').val();
	var motherNameId = $('#motherNameId').val();
	var motherMobileNoId = $('#motherMobileNoId').val();
	var gaurdianNameId = $("#gaurdianNameId").val().trim();
	var guardianMobileNoId = $('#guardianMobileNoId').val().trim();
	var primaryperson = $('#hprymarycntperson').val();
	var addr = $("#paddrs").val().trim();
	var presentadd = $("#presentadd").val().trim();
	var regex = new RegExp("^[0-9-!@#$%&*?]");
	var specl=$("#specilization").val();
	
	var fatherPanNo=$("#fatherPanNo").val().trim();
	var motherPanNo=$("#motherPanNo").val().trim();
	
      
	
	if (studentFirstName.trim()  == "" || studentFirstName  == null) {
		if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
			 $('.collapseOneA ').trigger('click');
		}
		$(".errormessagediv").show();
		$(".validateTips").text("Field Required First Name.");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
			document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;

	} else
	
	if (!studentFirstName.match(/[A-Za-z. ]+$/i) ) {
		if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
			 $('.collapseOneA ').trigger('click');
		}
		$('.errormessagediv').show();
		$('.validateTips').text("Field Required Valid First Name.");
		$("#studentFirstNameId").focus();
		document.getElementById("studentFirstNameId").style.border = "1px solid #AF2C2C";
		document.getElementById("studentFirstNameId").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('#errorhover').fadeOut();
			document.getElementById("studentFirstNameId").style.border = "1px solid #ccc";
			document.getElementById("studentFirstNameId").style.backgroundColor = "#fff";
		}, 1000);

		bValid= false;

	}
		else if(schoolname == null || schoolname.trim() == ""){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
			 $('.collapseOneA ').trigger('click');
			}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Branch Name.");
			$("#schoolLocationId").focus();
			document.getElementById("schoolLocationId").style.border = "1px solid #AF2C2C";
			document.getElementById("schoolLocationId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("schoolLocationId").style.border = "1px solid #ccc";
				document.getElementById("schoolLocationId").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;
		}else if(dateofJoin == null || dateofJoin.trim() == ""){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
			 $('.collapseOneA ').trigger('click');
			}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Admission Date.");
			//$("#dateofJoinId").focus();
			document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
			/*	document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
				document.getElementById("dateofJoinId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}else if (studentrollno.trim()  == "" || studentrollno.trim()  == null) {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
			 $('.collapseOneA ').trigger('click');
			}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Admission Number.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;

		}else if(checkRollnumber()){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Admission Number Already Exist.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
	
		else if(checkRegistrationNo() && applicationNoId!=""){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Registration No Already Exist.");
			$("#applicationNoId").focus();
			document.getElementById("applicationNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("applicationNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("applicationNoId").style.border = "1px solid #ccc";
				document.getElementById("applicationNoId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
		
		else if (!studentrollno.match(/[0-9\s]+$/i)) {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Valid Admission Number.");
			$("#studentrollno").focus();
			document.getElementById("studentrollno").style.border = "1px solid #AF2C2C";
			document.getElementById("studentrollno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studentrollno").style.border = "1px solid #ccc";
				document.getElementById("studentrollno").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;

		} else if (dateofJoin  == '') {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".validateTips").text("Field Required Admission Date.");
			$(".errormessagediv").show();
			$("#dateofJoinId").focus();
			document.getElementById("dateofJoinId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofJoinId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("dateofJoinId").style.border = "1px solid #ccc";
				document.getElementById("dateofJoinId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid= false;

		} else if (academicYear  == null || academicYear  == "") {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#academicYear").focus();
			document.getElementById("academicYear").style.border = "1px solid #AF2C2C";
			document.getElementById("academicYear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("academicYear").style.border = "1px solid #ccc";
				document.getElementById("academicYear").style.backgroundColor = "#fff";*/
			},1000);

			bValid= false;

		} else if (category  == null || category  == "") {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Stream.");
			$("#category").focus();
			document.getElementById("category").style.border = "1px solid #AF2C2C";
			document.getElementById("category").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("category").style.border = "1px solid #ccc";
				document.getElementById("category").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid= false;

		} else if (studClassId  == null || studClassId  == "") {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class.");
			$("#studClassId").focus();
			document.getElementById("studClassId").style.border = "1px solid #AF2C2C";
			document.getElementById("studClassId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studClassId").style.border = "1px solid #ccc";
				document.getElementById("studClassId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid= false;

		} else if (studSectionId  == null || studSectionId  == "") {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Section.");
			$("#studSectionId").focus();
			document.getElementById("studSectionId").style.border = "1px solid #AF2C2C";
			document.getElementById("studSectionId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studSectionId").style.border = "1px solid #ccc";
				document.getElementById("studSectionId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid= false;

		}
	   else if (transportCheck() && (istransport == "" || istransport == undefined)) {
		   if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Transport.");

			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("transportId").style.border = "1px solid #ccc";
				document.getElementById("transportId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid= false;

		} else if (transportCheck() && (istransport == "Y" && transportCategory.trim() == "")) {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Transport Category.");
			$("#transcategory").focus();
			document.getElementById("transcategory").style.border = "1px solid #AF2C2C";
			document.getElementById("transcategory").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("transcategory").style.border = "1px solid #ccc";
				document.getElementById("transcategory").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		}else if(transportCheck() &&(transportCategory.trim()!="") && (getTransportCategoryType($("#transcategory").val().trim()) == 'Y') && transportLocation.trim() == ""){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Transport Location.");
			$("#translocation").focus();
			document.getElementById("translocation").style.border = "1px solid #AF2C2C";
			document.getElementById("translocation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("translocation").style.border = "1px solid #ccc";
				document.getElementById("translocation").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		}else if (isWorking == "Yes" && staffid.trim() == "") {
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				 $('.collapseOneA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Search Staff/Teacher ID.");
			$("#transcategory").focus();
			document.getElementById("workingTeacherId").style.border = "1px solid #AF2C2C";
			document.getElementById("workingTeacherId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("workingTeacherId").style.border = "1px solid #ccc";
				document.getElementById("workingTeacherId").style.backgroundColor = "#fff";*/
			},1000);

			bValid=false;
		}else if (dateofBirth == null || dateofBirth == "") {
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Date Of Birth.");
			//$("#dateofBirthId").focus();
			$('.collapseTwoA').removeClass('collapsed');
			$('.collapseTwoA').prop('aria-expanded','true');
			
			document.getElementById("dateofBirthId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateofBirthId").style.backgroundColor = "#FFF7F7";
			
			
			
			
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("dateofBirthId").style.border = "1px solid #ccc";
				document.getElementById("dateofBirthId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		} else if (gendertype == "" || gendertype == null) {
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Gender.");
			document.getElementById("genderId").style.border = "1px solid #AF2C2C";
			document.getElementById("genderId").style.backgroundColor = "#FFF7F7";
			$("#genderId").focus();
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("genderId").style.border = "1px solid #ccc";
				document.getElementById("genderId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		}else if (religion == "" || religion == null){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Religion.");
			$("#religion").focus();
			document.getElementById("religion").style.border = "1px solid #AF2C2C";
			document.getElementById("religion").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("religion").style.border = "1px solid #ccc";
				document.getElementById("religion").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;

		} else if (caste == "" || caste == null){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Caste.");
			$("#casteId").focus();
			document.getElementById("casteId").style.border = "1px solid #AF2C2C";
			document.getElementById("casteId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("casteId").style.border = "1px solid #ccc";
				document.getElementById("casteId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		}else if(castecatagory == null || castecatagory == ""){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Caste Category.");
			$("#casteCategoryId").focus();
			document.getElementById("casteCategoryId").style.border = "1px solid #AF2C2C";
			document.getElementById("casteCategoryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("casteCategoryId").style.border = "1px solid #ccc";
				document.getElementById("casteCategoryId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		}else if(mothertongueval.trim() == "" || mothertongueval.trim() == null){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mother Tongue.");
			$("#mothertongueId").focus();
			document.getElementById("mothertongueId").style.border = "1px solid #AF2C2C";
			document.getElementById("mothertongueId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("mothertongueId").style.border = "1px solid #ccc";
				document.getElementById("mothertongueId").style.backgroundColor = "#fff";*/
			}, 1000);

			bValid=false;
		} 
		else if((aadharId != "") && aadharId.length!=12){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Aadhar Number.");
			$("#aadharId").focus();
			document.getElementById("aadharId").style.border = "1px solid #AF2C2C";
			document.getElementById("aadharId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("aadharId").style.border = "1px solid #ccc";
				document.getElementById("aadharId").style.backgroundColor = "#fff";*/
			}, 2000);

			bValid=false;
		}
		else if((aadharId != "" && aadharId !=null) && validstudentaadharId()==1){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Aadhar Number already exist..");
			$("#aadharId").focus();
			document.getElementById("aadharId").style.border = "1px solid #AF2C2C";
			document.getElementById("aadharId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("aadharId").style.border = "1px solid #ccc";
				document.getElementById("aadharId").style.backgroundColor = "#fff";*/
			}, 2000);

			bValid=false;
		}
		else if((emergencynumber != "") && emergencynumber.length!=10){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Emergency No.");
			$("#mothertongueId").focus();
			document.getElementById("emergencynumber").style.border = "1px solid #AF2C2C";
			document.getElementById("emergencynumber").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("emergencynumber").style.border = "1px solid #ccc";
				document.getElementById("emergencynumber").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		}
		else if (physicallychallengedtype == "" || physicallychallengedtype == null) {
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Physically Challenged.");
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("physicallyChallengedId").style.border = "1px solid #ccc";
				document.getElementById("physicallyChallengedId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		}else if (physicallychallengedtype =='YES' && physicallychallengereason == "") {
			
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required Reason.");
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("physicalchalreason").style.border = "1px solid #ccc";
				document.getElementById("physicalchalreason").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		}
		else if(studentStatus == ""){
			if($(".collapseTwoA").attr("class")=="collapseTwoA collapsed"){
				$('.collapseTwoA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Status.");
			$("#studentStatusId").focus();
			document.getElementById("studentStatusId").style.border = "1px solid #AF2C2C";
			document.getElementById("studentStatusId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("studentStatusId").style.border = "1px solid #ccc";
				document.getElementById("studentStatusId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;

		}else if (fathername.trim() == null || fathername.trim() == "") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$("#collapseTwo").addClass("panel-collapse collapse");
			$("#collapseThree").addClass("panel-collapse collapse in");
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Father Name.");
		/*	$("#fatherNameId").focus();collapseThree*/
			
			document.getElementById("fatherNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("fatherNameId").style.border = "1px solid #ccc";
				document.getElementById("fatherNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;

		}else if (!fathername.match(/[A-Za-z. ]+$/i)) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Father Name.");
			$("#fatherNameId").focus();
			document.getElementById("fatherNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("fatherNameId").style.border = "1px solid #ccc";
				document.getElementById("fatherNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;

		}
		else if(fathermobileno == "") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number.");
			$("#fatherMobileNoId").focus();
			document.getElementById("fatherMobileNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherMobileNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("fatherMobileNoId").style.border = "1px solid #ccc";
				document.getElementById("fatherMobileNoId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;

		}
		else if (motherNameId.trim() == null || motherNameId.trim() == "") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mother Name.");
			$("#motherNameId").focus();
			document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("motherNameId").style.border = "1px solid #ccc";
				document.getElementById("motherNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;

		}else if (!motherNameId.match(/[A-Za-z. ]+$/i)) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Mother Name.");
			$("#motherNameId").focus();
			document.getElementById("motherNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("motherNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("motherNameId").style.border = "1px solid #ccc";
				document.getElementById("motherNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;

		} 
		else if (motherMobileNoId == "" || motherMobileNoId == null) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number.");
			$("#motherMobileNoId").focus();
			document.getElementById("motherMobileNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("motherMobileNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("motherMobileNoId").style.border = "1px solid #ccc";
				document.getElementById("motherMobileNoId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
		else if ((fatherPanNo != "" && fatherPanNo != null) && fatherPanNo.length!=10) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid PAN No.");
			$("#motherMobileNoId").focus();
			document.getElementById("fatherPanNo").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherPanNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("fatherPanNo").style.border = "1px solid #ccc";
				document.getElementById("fatherPanNo").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
	
		else if ((fatherPanNo != "" && fatherPanNo != null) && validfatherPanNo()==1 && ($("#SearchStudent").val()=="" || $("#SearchStudent").val()==null )) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("PAN No. already exist");
			$("#motherMobileNoId").focus();
			document.getElementById("fatherPanNo").style.border = "1px solid #AF2C2C";
			document.getElementById("fatherPanNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("fatherPanNo").style.border = "1px solid #ccc";
				document.getElementById("fatherPanNo").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
		else if ((motherPanNo != "" && motherPanNo != null) && motherPanNo.length!=10) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid PAN No.");
			$("#motherMobileNoId").focus();
			document.getElementById("motherPanNo").style.border = "1px solid #AF2C2C";
			document.getElementById("motherPanNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("motherPanNo").style.border = "1px solid #ccc";
				document.getElementById("motherPanNo").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
		else if ((motherPanNo != "" && motherPanNo != null) && validmotherPanNo()==1 && ($("#SearchStudent").val()=="" || $("#SearchStudent").val()==null )) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("PAN No. already exist");
			$("#motherMobileNoId").focus();
			document.getElementById("motherPanNo").style.border = "1px solid #AF2C2C";
			document.getElementById("motherPanNo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("motherPanNo").style.border = "1px solid #ccc";
				document.getElementById("motherPanNo").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid= false;
		}
	
		/*else if (gaurdianNameId == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Guardian Name.");
			$("#gaurdianNameId").focus();
			document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
				document.getElementById("gaurdianNameId").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;

		}*/else if (gaurdianNameId != "" && !gaurdianNameId.match(/[A-Za-z. ]+$/i)) {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Valid Guardian Name");
			$("#gaurdianNameId").focus();
			document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
				document.getElementById("gaurdianNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;

		} 
		/*else if (guardianMobileNoId == "") {

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number.");
			$("#guardianMobileNoId").focus();
			document.getElementById("guardianMobileNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("guardianMobileNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("guardianMobileNoId").style.border = "1px solid #ccc";
				document.getElementById("guardianMobileNoId").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;
		}*/
		else if (primaryperson == null || primaryperson == "") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Primary Person.");
			$("#primarypersonId").focus();
			document.getElementById("primarypersonId").style.border = "1px solid #AF2C2C";
			document.getElementById("primarypersonId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("primarypersonId").style.border = "1px solid #ccc";
				document.getElementById("primarypersonId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		} 
		else if (primaryperson == "guardian" && $("#gaurdianNameId").val().trim()=="") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Guardian Name.");
			$("#primarypersonId").focus();
			document.getElementById("gaurdianNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("gaurdianNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("gaurdianNameId").style.border = "1px solid #ccc";
				document.getElementById("gaurdianNameId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		}
		else if (primaryperson == "guardian" && $("#guardianMobileNoId").val().trim()=="") {
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Mobile Number.");
			$("#primarypersonId").focus();
			document.getElementById("guardianMobileNoId").style.border = "1px solid #AF2C2C";
			document.getElementById("guardianMobileNoId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("guardianMobileNoId").style.border = "1px solid #ccc";
				document.getElementById("guardianMobileNoId").style.backgroundColor = "#fff";*/
			}, 3000);

			bValid=false;
		}
	else if (addr.trim() == "" || addr == null){
		if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
			$('.collapseThreeA ').trigger('click');
			}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Permanent Address.");
			$("#paddrs").focus();
			document.getElementById("paddrs").style.border = "1px solid #AF2C2C";
			document.getElementById("paddrs").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("paddrs").style.border = "1px solid #ccc";
				document.getElementById("paddrs").style.backgroundColor = "#fff";*/
			}, 3000);
			bValid= false;

		}
		else if (presentadd.trim() == "" || presentadd == null){
			if($(".collapseThreeA").attr("class")=="collapseThreeA collapsed"){
				$('.collapseThreeA ').trigger('click');
				}
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Present Address.");
			$("#presentadd").focus();
			document.getElementById("presentadd").style.border = "1px solid #AF2C2C";
			document.getElementById("presentadd").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				/*document.getElementById("presentadd").style.border = "1px solid #ccc";
				document.getElementById("presentadd").style.backgroundColor = "#fff";*/
			}, 3000);
			bValid= false;
		}
		else if(($("#studClassId").val()=="CCD14" ||$("#studClassId").val()=="CCD15") && (specl == null || specl == "")){
			if($(".collapseOneA").attr("class")=="collapseOneA collapsed"){
				$('.collapseOneA ').trigger('click');
			}

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Specialization.");
			$("#studSectionId").focus();
			document.getElementById("specilization").style.border = "1px solid #AF2C2C";
			document.getElementById("specilization").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("specilization").style.border = "1px solid #ccc";
				document.getElementById("specilization").style.backgroundColor = "#fff";
			}, 1000);

			bValid= false;
		}
		
		else{
			return bValid;
		}
	
}


function checkRollnumber() {

	var status = false;

	var rollNumber = $("#studentrollno").val();
	var hiddenRollNumber=$("#studentrollnohiddenid").val().trim();
	var locationId=$("#schoolLocationId").val();
	var hidLocation = $("#schoollocationhiddenid").val().trim();
	
	/*if($("#schoollocationhiddenid").val()==""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val().trim();
	}*/
 
	if ((rollNumber != '' && rollNumber != undefined )) {
		if(rollNumber == hiddenRollNumber && hidLocation ==locationId){
			status= false;
		}else{
			rollNumberCheck = {
					"rollNumber" : rollNumber,
					"locationId":locationId
			},

			$.ajax({
				url : "studentRegistration.html?method=validateroleNumber",
				data : rollNumberCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);

					if (response.message == "True") {
						$('.errormessagediv').css({
							'display' : ''
						});
						$('.validateTips').text("Admission Number already exist");

						status = true;

					} else {

						$('.errormessagediv').css({
							'display' : 'none'
						});

						status = false;
					}
				}

			});
		}
		return status;
	}
	
}

function checkRegistrationNo() {

	var status = false;

	var registrationNo = $("#applicationNoId").val();
	var hiddenRegistrationNo=$("#hiddenRegistrationNo").val();
	var locationId=$("#schoolLocationId").val();
	var hidLocation = $("#schoollocationhiddenid").val().trim();
	var stuId=$("#studentid").val();
	/*if($("#schoollocationhiddenid").val()==""){
		locationId=$("#schoolLocationId").val();
	}
	else{
		locationId=$("#schoollocationhiddenid").val().trim();
	}*/

	if ((registrationNo != '' && registrationNo != undefined )) {
		if(registrationNo == hiddenRegistrationNo && hidLocation ==locationId){
			status= false;
		}else{
			rollNumberCheck = {
					"registrationNo" : registrationNo,
					"locationId":locationId,
					"academicYear":$("#academicYear").val(),
					"stuId":stuId
			},
			$.ajax({
				url : "studentRegistration.html?method=validateRegistrationNo",
				data : rollNumberCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					if (response.message == "true") {
						status = true;
					} else {
						status = false;
					}
				}
			});
		}
		return status;
	}
}

function validstudentaadharId() {

	var status1 = 0;
	var stuId=$("#studentid").val();

			rollNumberCheck = {
					"aadharId":$("#aadharId").val(),
					"stuId":stuId
			},
			$.ajax({
				url : "studentRegistration.html?method=validstudentaadharId",
				data : rollNumberCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					if (response.message == "true") {
						status1 = 1;
					} else {
						status1 = 0;
					}
				}
			});
		 
		return status1;
	}

function validfatherPanNo() {

	var status1 = 0;
	var parenthiddenId=$("#parenthiddenId").val();

			rollNumberCheck = {
					"fatherPanNo":$("#fatherPanNo").val(),
					"parenthiddenId":parenthiddenId
			},
			$.ajax({
				url : "studentRegistration.html?method=validfatherPanNo",
				data : rollNumberCheck,
				async : false,
				success : function(data) {

					var response = $.parseJSON(data);
					 
					if (response.message == "true") {
						status1 = 1;
					} else {
						status1 = 0;
					}
				}
			});
		 
		return status1;
	} 
	
	function validmotherPanNo() {

		var status1 = 0;
		var parenthiddenId=$("#parenthiddenId").val();

				rollNumberCheck = {
						"motherPanNo":$("#motherPanNo").val(),
						"parenthiddenId":parenthiddenId
				},
				$.ajax({
					url : "studentRegistration.html?method=validmotherPanNo",
					data : rollNumberCheck,
					async : false,
					success : function(data) {

						var response = $.parseJSON(data);
						if (response.message == "true") {
							status1 = 1;
						} else {
							status1 = 0;
						}
					}
				});
			 
			return status1;
		}
 

function readyOnly() {}

function HideError(pointer) {
	$(".errormessagediv").hide();
	$('.errormessagediv1').hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function getRouteDetails(){
	var value = null;
	if (($('#transportlocationhiddenid').val() != "" && $('#transportlocationhiddenid').val() != null && $("#translocation").val() =="" )) {
		value = $("#transportlocationhiddenid").val();
	}else if($('#transportlocationhiddenid').val() == $("#translocation").val()){
		value = $("#transportlocationhiddenid").val();
	}else {
		value = $('#translocation').val();
	}
	
	var data = {
		"value" : value
	};

	$.ajax({
		type : 'POST',
		url : "transport.html?method=getRouteDetails",
		data : data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#route').html("");
			$('#route').append('<option value="' + "" + '">' + "----------Select----------"	+ '</option>');
			for ( var j = 0; j < result.routelist.length; j++) {
				$('#route').append('<option value="' + result.routelist[j].routeCode + '">' + result.routelist[j].routeName	+ '</option>');
			}


		}
	});
}



function getReligionDetails(){
	
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getReligionForDropDown",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#religion').empty();
			$('#religion').append('<option value="' + "" + '">'	+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#religion').append(
						'<option value="'
						+ result.jsonResponse[j].religionId
						+ '">'
						+ result.jsonResponse[j].religion
						+ '</option>');
			}


		}
	});
}


function getCasteDetails(){
	
	var religionId = null;
	if (($('#religionhiddenid').val() != "" && $('#religionhiddenid').val() != null && $("#religion").val() =="" )) {
		religionId = $("#religionhiddenid").val();
	}else if($('#religionhiddenid').val() == $("#religion").val()){
		religionId = $("#religionhiddenid").val();
	}else {
		religionId = $('#religion').val();
	}
	
	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
		}
	});
}


function getCasteCategoryDetails(){
	//var casteId=$('#casteId').val();
	var casteId = null;
	if (($('#castehiddenid').val() != "" && $('#castehiddenid').val() != null && $("#casteId").val() =="" )) {
		casteId = $("#castehiddenid").val();
	}else if($('#castehiddenid').val() == $("#casteId").val()){
		casteId = $("#castehiddenid").val();
	}else {
		casteId = $('#casteId').val();
	}
	
	var data = {
			"casteId" : casteId
	};
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteCategoryForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteCategoryId').append(
						'<option value="'
						+ result.jsonResponse[j].casteCategoryId
						+ '">'
						+ result.jsonResponse[j].casteCategory
						+ '</option>');
			}

		}
	});
}

function getClassSpecilization(classId,locationId){
	var data = {
			"classId" : classId,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specilization').empty();
			$('#specilization').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');
			
			
			if(result.jsonResponse.length>0){
				
				for ( var j = 0; j < result.jsonResponse.length; j++) {
					$('#specilization').append(
							'<option value="'
							+ result.jsonResponse[j].spec_Id
							+ '">'
							+ result.jsonResponse[j].spec_Name
							+ '</option>');
				}
				getClassLanguage(classId,locationId,$('#specilization').val().trim());
			}
			else{
				getClassLanguage(classId,locationId,"-");
			}
		}
	});
}

function getOccupation(){
	
	$.ajax({
		type : 'POST',
		url : "teacherregistration.html?method=getOccupation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').empty();
			$('#fatherOccupation,#motherOccupation,#guardianOccupation').append(
					'<option value="' + "" + '">'
							+ "----------Select--------"
							+ '</option>');
		
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				
				
			
				$('#fatherOccupation,#motherOccupation,#guardianOccupation')
						.append(
								'<option value="'
										+ result.jsonResponse[j].occupationId
										+ '">'
										+ result.jsonResponse[j].occupation
										+ '</option>');
			}
		}
	});
}



function getSchoolLocation(){
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getSchoolLocation",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#schoolLocationId').empty();
			
			for ( var j = 0; j < result.locationList.length; j++) {

				$('#schoolLocationId').append('<option value="'+ result.locationList[j].location_id + '">'
						+ result.locationList[j].locationname + '</option>');
			}
		}
	});
}


function getAcademicYear() {
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAcademicYear",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#globalAcademicYear").html("");
			

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$("#globalAcademicYear").append(
						'<option value="'
								+ result.jsonResponse[j].academicYearId + '">'
								+ result.jsonResponse[j].academicYear
								+ '</option>');
			}
			$("#globalAcademicYear option[value="+$("#hacademicyaer").val()+"]").attr("selected",true);
		}
	});

}

function checkFile1(){
	var fileval = $("#studentImageId1").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Student Photo Should be a jpeg,jpg and png File");
			document.getElementById("studentImageId1").style.border = "1px solid #AF2C2C";
    		document.getElementById("studentImageId1").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#studentImageId1").val("");
			return false;
		}
	}
}

function checkFile2(){
	var fileval = $("#fatherImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("fatherImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("fatherImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#fatherImageId").val("");
			return false;
		}
	}
}

function checkFile3(){
	var fileval = $("#guardianImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("guardianImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("guardianImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#guardianImageId").val("");
			return false;
		}
	}
}

function checkFile4(){
	var fileval = $("#motherImageId").val();
	
	if (fileval != undefined && fileval != ''){
		var extval = fileval.substring(fileval.lastIndexOf('.') + 1);
		if(extval != "jpeg" && extval != "jpg" && extval != "png" && extval != "PNG" && extval != "JPG" && extval != "JPEG"){
			$('.errormessagediv').show();
			$('.validateTips').text("Photo Should be a jpeg,jpg and png File");
			document.getElementById("motherImageId").style.border = "1px solid #AF2C2C";
    		document.getElementById("motherImageId").style.backgroundColor = "#FFF7F7";
			$('.errormessagediv').delay(3000).fadeOut("slow");
			$("#motherImageId").val("");
			return false;
		}
	}
}


function viewStudentDetails(studentList) {
	$("#fatherNameId").val(studentList[0].fatherName);
	$("#fatherMobileNoId").val(studentList[0].fatherMobileNo);
	$("#fatherQualificationId").val(studentList[0].fatherQualification);
	$("#fatheremailId").val(studentList[0].fatheremailId);

	$("#motherNameId").val(studentList[0].motherName);
	$("#motherMobileNoId").val(studentList[0].motherMobileNo);
	$("#motherQualificationId").val(studentList[0].motherQualification);
	$("#motheremailId").val(studentList[0].motheremailId);

	$("#gaurdianNameId").val(studentList[0].gaurdianName);
	$("#guardianMobileNoId").val(studentList[0].guardianMobileNo);
	$("#guardianemailId").val(studentList[0].guardianemailId);

	$("#sibilingadminnoId").val(studentList[0].sibilingadminno);
	$("#parentId").val(studentList[0].parentId);
	$("#sibilingClassId").val(studentList[0].sibilingClass);
	$("#primarypersonId").val(studentList[0].primaryPerson);
	$("#hidprimary").val(studentList[0].primaryPerson);
	$("#hprymarycntperson").val(studentList[0].primaryPerson);
	$("#studentSibilingIdIntId").val(studentList[0].studentSibilingIdInt);
	$("#sibilingClassID").val(studentList[0].sibilingClassId);
	
	$("#motheroccupationId").val(studentList[0].motheroccupation);
	$("#fatheroccupationId").val(studentList[0].fatheroccupation);
	$("#paddrs").val(studentList[0].address);
	
	$("#fatherPanNo").val(studentList[0].fatherPanNo);
	$("#motherPanNo").val(studentList[0].motherPanNo);
	
	$("#fatherPanNo").attr('readonly', true);
	$("#motherPanNo").attr('readonly', true);
	
	$("#fatherNameId").attr('readonly', true);
	$("#fatherMobileNoId").attr('readonly', true);

	$("#fatherQualificationId").attr('readonly', true);
	$("#fatheremailId").attr('readonly', true);

	$("#motherNameId").attr('readonly', true);
	$("#motherMobileNoId").attr('readonly', true);
	$("#motherQualificationId").attr('readonly', true);
	$("#motheremailId").attr('readonly', true);

	$("#gaurdianNameId").attr('readonly', true);
	$("#guardianMobileNoId").attr('readonly', true);
	$("#guardianemailId").attr('readonly', true);

	$("#motheroccupationId").attr('readonly', true);
	$("#fatheroccupationId").attr('readonly', true);
	$("#paddrs").attr('readonly', true);
	
	$('#primarypersonId').attr('disabled',true);

}

function viewTempAdmissionStudentDetails(tempStudentList) {
	
	$("#hiddentempregid").val(tempStudentList[0].tempregid);
	$("#studentFirstNameId").val(tempStudentList[0].studentFirstName);
	$("#studentLastNameId").val(tempStudentList[0].studentLastName);
	$("#dateofJoinId").val(tempStudentList[0].createDate);
	
	$("#mothertongueId").val(tempStudentList[0].mothertongue);
	
	//$("#studClassId").val(tempStudentList[0].classname); 
	var stream=$("#category").val();
 
	$("#schoolLocationId option[value='"+ tempStudentList[0].locationId +"']").attr('selected', 'true');
	 
	getCategory($("#schoolLocationId").val());
	$("#category option[value='"+ tempStudentList[0].streemcode +"']").attr('selected', 'true');
	$("#dateofBirthId").val(tempStudentList[0].dateofBirth);
	ageCalculate($("#dateofBirthId").val());
	$("#genderId option[value='"+ tempStudentList[0].gender +"']").attr('selected', 'true');
	$("#nationalityId option[value='"+ tempStudentList[0].nationality +"']").attr('selected', 'true');
	$("#religion option[value='"+ tempStudentList[0].religion +"']").attr('selected', 'true');
	$("#casteId option[value='"+ tempStudentList[0].caste +"']").attr('selected', 'true');
	$("#casteCategoryId option[value='"+ tempStudentList[0].casteCategory +"']").attr('selected', 'true');
	$("#mothertongueId option[value='"+ tempStudentList[0].mothertongue +"']").attr('selected', 'true');
	$("#aadharId").val(tempStudentList[0].aadharno);
	$("#fatherNameId").val(tempStudentList[0].fatherName);
	$("#fatherMobileNoId").val(tempStudentList[0].fatherMobileNo);
	$("#fatherQualification").val(tempStudentList[0].fatherQualification);
	$("#fatherOccupation option[value='"+ tempStudentList[0].fatheroccupation +"']").attr('selected', 'true');
	$("#fatherDepartment").val(tempStudentList[0].fatherDepartment);
	$("#fatherDesignation").val(tempStudentList[0].fatherDesignation);
	$("#fatherofficeaddress").val(tempStudentList[0].fatherOfficeAddress);
	$("#fatherAnnualIncome").val(tempStudentList[0].fatherAnnualIncome);
	$("#fatherEmail").val(tempStudentList[0].fatheremailId);
	$("#motherNameId").val(tempStudentList[0].motherName);
	$("#motherMobileNoId").val(tempStudentList[0].motherMobileNo);
	$("#motherQualificationId").val(tempStudentList[0].motherQualification);
	$("#motherOccupation option[value='"+ tempStudentList[0].motheroccupation +"']").attr('selected', 'true');
	$("#motherDepartment").val(tempStudentList[0].motherDepartment);
	$("#motherDesignation").val(tempStudentList[0].motherDesignation);
	$("#motherofficeaddress").val(tempStudentList[0].motherOfficeAddress);
	$("#motherAnnualIncome").val(tempStudentList[0].motherAnnualIncome);
	$("#motherEmail").val(tempStudentList[0].motheremailId);
	$("#paddrs").val(tempStudentList[0].address);
	$('#checkAddressId').prop("checked",true) ;
	$("#presentadd").val(tempStudentList[0].presentaddress);
	$("#primarypersonId option[value='"+ tempStudentList[0].primaryPerson +"']").attr('selected', 'true');
	$("#hidprimary").val(tempStudentList[0].primaryPerson)
	$('#imagePreview').attr('src', tempStudentList[0].imageFileName);
	$('#photohiddenid').val(tempStudentList[0].imageFileName),
	$("#hiddenenquiryid").val(tempStudentList[0].enquiryId);
	
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
function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imagePreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}

function readFatherURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageFatherPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}
}


function readMotherURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageMotherPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}



function readGuardianURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#imageGuardianPreview').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
	}

}
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$('#locationname').val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}


function getClassLanguage(classId,location,specializationId){

	
	var data = {
			"classId" : classId,
			"locationId":location,
			"specializationId":specializationId,
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getLangauageOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#firstlang').empty();
			$('#firstlang').append('<option value="all">'+ "----------Select----------" + '</option>');
			$('#secondlang').empty();
			$('#secondlang').append('<option value="all">'+"----------Select----------" + '</option>');
			$('#thirdlang').empty();
			$('#thirdlang').append('<option value="all">'+ "----------Select----------" + '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#firstlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
				$('#secondlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
				$('#thirdlang').append('<option value="'+ result.jsonResponse[j].subjectCode+ '">'+ result.jsonResponse[j].subjectname + '</option>');
			}
			$('#secondlang').change(function(){
				var firstlang=$('#firstlang').val();
				var secondlang=$(this).val();
				if(firstlang == secondlang){
					$("#secondlang").val("all");
					$("#thirdlang").val("all");
				}else{
					$("#secondlang  option[value='"+secondlang+"' ]").attr('selected', 'true');
				}
			});
			$('#thirdlang').change(function(){
				var firstlang=$('#firstlang').val();
				var secondlang=$('#secondlang').val();
				var thirdlang=$(this).val();
				if((secondlang == thirdlang) || (firstlang == thirdlang)){
					$("#thirdlang").val("all");
				}else{
					$("#thirdlang  option[value='"+thirdlang+"' ]").attr('selected', 'true');
				}
			});	

		}
	});
}
function getTempAdmissionDetailsList(){
		
		var temp_stu_Name = $("#temp_studentName").val();
		var temp_par_Name = $("#temp_parentName").val();
		var temp_mob_No	= $("#temp_mobileNo").val();
		var schoolid = $("#schoolLocationId").val();
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=searchStudentByTempAdmission",
			data : {
				'studentName' : temp_stu_Name,
				'parentName' : temp_par_Name,
				'mobileNo' : temp_mob_No,
				'schoolid' : schoolid,
			},
				async : false,
				success : function(response) 
				{
					var result = $.parseJSON(response);
				
					$("#admissionstudent tbody").empty();
					for ( var j = 0; j < result.jsonResponse.length; j++) {
						$("#admissionstudent tbody").append(
						"<tr>"+
						"<td><input type='radio' name='select' class='select' value ='"+result.jsonResponse[j].acyId+" "+result.jsonResponse[j].locId+" "+result.jsonResponse[j].streamId+" "+result.jsonResponse[j].classId+"' id='"+result.jsonResponse[j].temp_regid+"'></td>" +
						"<td>"+result.jsonResponse[j].accyear+"</td>"+
						"<td class='stuname "+result.jsonResponse[j].studentfirstName+"-"+result.jsonResponse[j].studentLastName+"' id='"+result.jsonResponse[j].studentfirstName+"-"+result.jsonResponse[j].studentLastName+"'>"+result.jsonResponse[j].studentname+"</td>"+
						"<td class='relation "+result.jsonResponse[j].relationship+"'>"+result.jsonResponse[j].parents+"</td>"+
						"<td class='phone'>"+result.jsonResponse[j].preferedphno+"</td>"+
						"<td>"+result.jsonResponse[j].classname+"</td>"+
						"</tr>"
						
						);
					}
				
			},
		});
}

function getTempAdmissionDetailsListById(){
	var StudentName = $("#temp_studentName").val();
	var mobileNO = $("#temp_mobileNo").val();
	var parentName = $("#temp_parentName").val();
	var locid=$("#schoolLocationId").val();
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getTempAdmissionDetailsListById",
		data : {
			'StudentName' : StudentName,
			'mobileNO' : mobileNO,
			'parentName' : parentName,
			'locid' : locid,
		},
			async : false,
			success : function(response) 
			{
				var result = $.parseJSON(response);
				if(result.jsonResponse.length > 0){
				$("#admissionstudent tbody").empty();
				for ( var j = 0; j < result.jsonResponse.length; j++) {
					$("#admissionstudent tbody").append(
					"<tr>"+
					"<td><input type='radio' name='select' class='select' value ='"+result.jsonResponse[j].acyId+" "+result.jsonResponse[j].locId+" "+result.jsonResponse[j].streamId+" "+result.jsonResponse[j].classId+"' id='"+result.jsonResponse[j].temp_regid+"'></td>" +
					"<td>"+result.jsonResponse[j].accyear+"</td>"+
					"<td class='stuname "+result.jsonResponse[j].studentfirstName+"-"+result.jsonResponse[j].studentLastName+"' id='"+result.jsonResponse[j].studentfirstName+"-"+result.jsonResponse[j].studentLastName+"'>"+result.jsonResponse[j].studentname+"</td>"+
					"<td class='relation "+result.jsonResponse[j].relationship+"'>"+result.jsonResponse[j].parents+"</td>"+
					"<td class='phone'>"+result.jsonResponse[j].preferedphno+"</td>"+
					"<td>"+result.jsonResponse[j].classname+"</td>"+
					"</tr>"
					);
				}
				}else{
					$("#admissionstudent tbody").empty();
					$("#admissionstudent tbody").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
				}
		},
	});
}



function getHouse(locationId){
	var academicYear=$("#academicYear").val();
	 
	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getHouse",
		data : {
			"locationId":locationId,
			"academicYear":academicYear},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#studHouseId').empty();

			$('#studHouseId').append('<option value="">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.houseList.length; j++) {

				$('#studHouseId').append('<option value="' + result.houseList[j].houseId
						+ '">' + result.houseList[j].houseName
						+ '</option>');
			}
			$('#studHouseId').val($("#househiddenid").val());
		}
	});
}

function getAdmissionNo(){
	var locationId=$('#schoolLocationId').val();
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getAdmissionNo",
		data : {"locationId":locationId},
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$('#studentrollno').empty();
			$('#studentrollno').val(result.admissionNo);
		}
	});

}
function printApplication(){


	
	 var a=$("#printing-css").val();
		var b = document.getElementById("container").innerHTML;

	  
	
	    var abd='<style>' + a +'</style>' + b;
	    
var frame1 = $('<iframe />');
frame1[0].name = "frame1";
frame1.css({ "position": "absolute", "top": "-1000000px" });
$("body").append(frame1);
var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
frameDoc.document.open();
//Create a new HTML document.
frameDoc.document.write('<html><head><title>DIV Contents</title>');
//Append the external CSS file.

frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/Parent/PrintAppForm.css" rel="stylesheet">');
 frameDoc.document.write('</head><body>');


//Append the DIV contents.
frameDoc.document.write(abd);
frameDoc.document.write('</body></html>');
frameDoc.document.close();
setTimeout(function () {
    window.frames["frame1"].focus();
    window.frames["frame1"].print();
    frame1.remove();
}, 100);
	
}

function getAdmissionDetails(id){

	$.ajax({
		type : "POST",
		url : "studentRegistration.html?method=getAdmissionDetails",
		data : {"temadmissionid" : id},
		async :false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#locname").append(result.stuList[0].location);
			$("#locaddress").append(result.stuList[0].locationAddress);
			
			$("#container .classname").text(result.stuList[0].classname);
			$("#container .stuName").text(result.stuList[0].studentFirstName.toUpperCase());
			$("#container .dobf").text(result.stuList[0].dateofBirth);
			$("#container .dobw").text(result.stuList[0].dateofBirthInWords);
			$("#container .gender").text(result.stuList[0].gender);
			$("#container .ageason").text(result.stuList[0].age);
			
			if(result.stuList[0].casteCategory=="SC" || result.stuList[0].casteCategory=="ST" || result.stuList[0].casteCategory=="OBC")
			$("#container .isscst").text("YES");
			else
				$("#container .isscst").text("NO");	
			$("#container .mottongue").text(result.stuList[0].mothertongue);
			$("#container .fatname").text(result.stuList[0].fatherName);
			$("#container .motName").text(result.stuList[0].motherName);
			$("#container .fatOccu").text(result.stuList[0].fatheroccupation+" "+result.stuList[0].fatherDesignation);
			$("#container .motOccu").text(result.stuList[0].motheroccupation+" "+result.stuList[0].motherDesignation);
			$("#container .fathoffiaddr").text(result.stuList[0].fatherOfficeAddress);
			$("#container .motoffiaddr").text(result.stuList[0].motherOfficeAddress);
			$("#container .fatincome").text(result.stuList[0].fatherAnnualIncome);
			$("#container .motincome").text(result.stuList[0].motherAnnualIncome);
			$("#container .peraddr").text(result.stuList[0].address);
			$("#container .fathphone").text(result.stuList[0].fatherMobileNo);
			$("#container .motphone").text(result.stuList[0].motherMobileNo);
			$("#container .stuimg").attr('src',result.stuList[0].image);
			$("#container .nationality").text(result.stuList[0].nationality);
			$("#container .religion").text(result.stuList[0].religion);
			$("#container .casteName").text(result.stuList[0].caste);
			$("#container .preaddr").text(result.stuList[0].presentaddress);
			$("#container .aadharNo").text(result.stuList[0].aadharno);

		}
	});

}
function getConcessionTypes(){
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getConcessionType",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#concessiontype').empty();
			$('#concessiontype').append('<option value="">' + "----------Select----------"	+ '</option>');
			for ( var j = 0; j < result.concessiondetaillist.length; j++) {
				$('#concessiontype').append('<option value="' + result.concessiondetaillist[j].concessionId
						+ '">' + result.concessiondetaillist[j].concessionName
						+ '</option>');
			}
		}
	});


}
function transportCheck(){
	if($('#transportId').val()==undefined){
		return false;
	}
	return true;

}
function getTermList(schoolLocationId,academicYear,pointer){
	
	$.ajax({
		type:'POST',
		url:'termfee.html?method=termListByJs',
		data:{"locationId":schoolLocationId,"accyear":academicYear,
				"status":"Y",
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			pointer.empty();
			var len=result.termlist.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){
					pointer.append("<option value='"+result.termlist[i].termid+"'>"+result.termlist[i].termname+"</option>");
				}
			}
		var abcd=$("#feeapplicabletermhidden").val();
		var termList=abcd.substring(1,abcd.length-1);
		var termListArray=[];
		var termId=termList.split(",");
		for(var i=0;i<termId.length;i++){
			termListArray.push(termId[i].trim());
		}
		
			pointer.val(termListArray);
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


function onClickColorChange(pointer){
	
	$(pointer).css("border", "1px solid #ccc");
	$(pointer).css("backgroundColor", "#fff");
	
	
}

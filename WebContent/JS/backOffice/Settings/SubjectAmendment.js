$(document).ready(function() {
	$("#subjectCode").on('blur keyup paste change',function(e){
		minlength=4;
		maxlength=8;
		this.value=this.value.toUpperCase();
		if(this.value.length > maxlength){
			this.value = this.value.substr(0,maxlength);

		}
	});

	$("#back1").click(function(){
		window.location.href="menuslist.html?method=subjectdetails&locId";
	});

	if($("#schoolId").val()!=""){
		$("#locationname").val($("#schoolId").val());
		getClassName($("#schoolId"));
	}
	$("#locationname").change(function(){
		getClassName($(this));
		checkSubjectduplication();
	});

	if($("#hiddenclassid").val()!=""){
		$("#classnameid").val($("#hiddenclassid").val());
		getClassSpecilization($("#hiddenclassid").val());

	}

	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	var url=pageUrl.split("&");
	var finalurl=url[0];
	if(finalurl=="subject.html?method=updateSubject")
	{
		if($("#errormessagediv  .validateTips").text()==""){
			$(".successmessagediv").show();
			setTimeout(function(){
				window.location.href="menuslist.html?method=subjectdetails";
			},5000);
		}
	}

	var hiddensubjectname=$("#hiddensubject").val();
	var hiddenclassid=$("#hiddenclassid").val();


	$("#document1btn").hide();
	$("#deleteProfile").hide();

	getClassSpecilization($("#classnameid").val(),$("#locationname").val());

	$("#classnameid").change(function(){
		checkSubjectduplication();
		getClassSpecilization($(this).val(),$("#locationname").val());

		if($("#hiddenclassid").val()!=""){
			$("#clsname").val(($("#classnameid option:selected").text()));
		}
		else{
			$("#clsname").val(($("#classnameid option:selected").text()));
		}
		
		if($(this).val()=="CCD14" || $(this).val()=="CCD15"){
			$(".notmandatory").show();
		} else{
			$(".notmandatory").hide();
		}
		
	});	

	$("#subjtname").change(function(){
		if(hiddensubjectname !=$("#subjtname").val() || hiddenclassid!=$("#classnameid").val()){
			checkSubjectduplication();

		}
	});	

	/* $('input[type=file]').change(function () {
						 var val = $(this).val().toLowerCase();
						 var regex = new RegExp("(.*?)\.(docx|doc|pdf|ppt|xls|jpg|jpeg|txt|png|xlsx)$");
						  if(!(regex.test(val))) {
						 $(this).val('');
						 $(
							".errormessagediv")
							.show();
					     $(".validateTips")
							.text(
									"Select Correct file format ");
						 } }); */

	var hiddenclass=$("#hiddenclassid").val().trim(); 

	$("#classnameid option[value="+ hiddenclass + "]").attr('selected', 'true');

	var hiddensubject=$("#hiddensubject").val();
	var hiddendescription=$("#hiddendescription").val();
	var hiddenislab=$("#hiddenislab").val();
	var hiddensubtype=$("#hiddensubtype").val();
	var hiddenislang=$("#hiddenislangu").val();

	$("#subjtname").val(hiddensubject);
	$("#description").val(hiddendescription);
	$("#isLangauge").val(hiddenislang);
	$("#isLab").val(hiddenislab);
	$("#subtype").val(hiddensubtype);


	setTimeout("removeMessage()" ,5000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 5000);


	$('#save').click(function() {


		var status=$("#statusid").val();

		var reg = /^\d+$/;

		$(".errormessagediv").show();

		subjtname = $("#subjtname").val().trim();

		var  classname = $("#classnameid").val();
		var specialization = $("#specialization").val();
		var subjtname = $("#subjtname").val().trim(); 
		var subjectCode = $("#subjectCode").val().trim(); 
		var isLangauge = $("#isLangauge").val(); 
		var isLab = $("#isLab").val();  
		var subtype = $("#subtype").val();
		var description = $("#description").val();
		var locationId=$("#locationname").val();


		var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
		var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;

		if(locationId=="")
		{
			$(".errormessagediv").show();	
			$(".validateTips").text("Field Required -School Name");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);


			return false;
		}

		else if(classname=="")
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Class");
			return false;
		}

		else if(subjtname=="")
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Subject Name");
			return false;
		}


		else if(subjtname.length < 2)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Subject Should Contain min 2 Characters");
			return false;
		}


		else if($("#subjectCode").val().trim()==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Subject Code");
			document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
			document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
			return false; 
		}

		else if(checkDuplicateSubCodeCount()){ 

			return false; 
		}



		else if($("#isLangauge").val()==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Is Language");
			document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
			document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
			return false; 
		}

		else if($("#isLab").val()==""){

			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Is Lab");
			showError("#subjectCode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}


		else if($("#subtype").val()==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Sub Type");
			showError("#subjectCode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}

		else {
			$(".errormessagediv").hide();

			var datalist = {
					"classname"    : classname,
					"subjtname"    : subjtname,
					"subjectCode"  : subjectCode,
					"isLangauge"   : isLangauge, 
					"isLab"        : isLab,
					"subtype"      : subtype,
					"description"  : description,
					"specialization" : specialization,
					"locationId"    :locationId,
					"hiddensubId":$("#hiddensubjectid").val()
			};

			$.ajax({
				type : 'POST',
				url : "subject.html?method=addSubject",
				data : datalist,
				success : function(response) {
					var result = $.parseJSON(response);

					if (result.status== "update") {

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Updating Record progressing...");
						setTimeout(function() {
							window.location.href="menuslist.html?method=subjectdetails";
						}, 5000);
					}
					else if (result.status=="alreadyexist") 
					{
						$( ".errormessagediv").show();
						$( ".validateTips").text("This Subject Already Exist !! Make it Active");
						setTimeout(function() {
							window.location.href="menuslist.html?method=subjectdetails";
						}, 5000);
					}
					else if (result.status=="updatefail") 
					{
						$( ".errormessagediv").show();
						$( ".validateTips").text("Faile to Update Subject details");
						setTimeout(function() {
							window.location.href="menuslist.html?method=subjectdetails";
						}, 5000);
					}

				}
			});

		}
	});

	var path=$("#hiddenfile").val();
	if(path !== "" && path!=undefined){

		$("#document1btn").attr('name',path);
		$("#file").hide();

		$("#document1btn").show();
		$("#deleteProfile").show();

	}

	$("#deleteProfile").click(function(){

		$("#file").show();

		$("#document1btn").hide();
		$("#deleteProfile").hide();

	});





	$("#document1btn").click(function(){


		var path = $(this).attr('name');


		if(path == ""){

			$(".errormessagediv").show();
			$(".validateTips")
			.text(
			"No file for downloading");
		}
		else{


			window.location.href = "subject.html?method=getpathdownload&Path="+ path;
		}


	});






});
function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="-">NIL</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}
			$("#specialization").val($("#hspecialization").val());

		}
	});
}
function getClassName(pointer) {



	$.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		data:{"locationId":pointer.val()},
		async:false,

		success : function(data) {



			var result = $.parseJSON(data);
			$('#classnameid').html("");
			$('#classnameid').append('<option value="">' + "---------Select---------" + '</option>');

			for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

				$('#classnameid').append(
						'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
			}





		}
	});}

function clearFields() {


	document.getElementById("classname").value = "";
	document.getElementById("subjtname").value = "";
	document.getElementById("file").value = "";
	document.getElementById("description").value = "";
}

function removeMessage() {	

	$(".successmessagediv").hide();

}

function checkSubjectduplication() {

	var classId = $("#classnameid").val();
	var subject = $("#subjtname").val().trim(); 
	var locationId=$("#locationname").val();
	var specialization=$("#specialization").val();
	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"specialization":specialization,

	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=validateSubNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {


			var result = $.parseJSON(data);

			if(result.des_available =="inactive" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name '"+$("#subjtname").val()+"' Already Exists With this Class !! Make it Active");
				$("#subjtname").val("");
				$("#subjtname").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);

			}

			if(result.des_available =="true" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name '"+$("#subjtname").val()+"' Already Exists With this Class");
				$("#subjtname").val("");
				$("#subjtname").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);

			}

		}
	});
}

function checkDuplicateSubCodeCount(){
	var flag =false;
	var subjectcode=$("#subjectCode").val();
	var hiddensubcode=$("#hiddensubcode").val();
	if(subjectcode!=hiddensubcode){
		var classId = $("#classname").val();
		var subject = $("#subjtname").val(); 
		var locationId=$("#locationname").val();
		var specialization=$("#specialization").val();
		var subjectcode=$("#subjectCode").val();
		var checkSubjectName = {
				"classId" : classId,
				"subject" : subject,
				"locationId":locationId,
				"specialization":specialization,
				"subjectcode":subjectcode,
		};

		$.ajax({
			type : "POST",
			url : "subject.html?method=checkDuplicateSubCodeCount",
			data : checkSubjectName,
			async : false,
			success : function(data) {


				var result = $.parseJSON(data);
				if(result.des_available =="inactive" ) {
					$(".errormessagediv").show();
					$(".validateTips").text("Subject Code  Already Exists With this Class !! Make it Active");
					$("#subjectCode").css({'border-color':'#f00'});

					setTimeout(function(){
						$(".errormessagediv").hide();
					},5000);
					flag=true;
					return false;
				}

				else if(result.des_available =="true" ) {
					$(".errormessagediv").show();
					$(".validateTips").text("Subject Code  Already Exists.");
					$("#subjectCode").css({'border-color':'#f00'});

					setTimeout(function(){
						$(".errormessagediv").hide();
					},5000);
					flag=true;
					return false;
				}
				else{
					flag=false;

				}

			}
		});
	}
	else{
		flag=false;
	}
	return flag;
}


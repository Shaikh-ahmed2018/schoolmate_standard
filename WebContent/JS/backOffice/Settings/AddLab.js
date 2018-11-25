var spelen = 0;
$(document).ready(function() {

	$("#lab_name").change(function(){
		value=$("#lab_name").val();
		var  id="lab_name";
		capital(value,id);
	});

	if($("#hiddenlocationid").val()!="" & $("#hiddenlocationid").val()!=null )
	{
		$("#locationname").val($("#hiddenlocationid").val()) ; 
		getClassName($("#locationname").val());
		$("#classname").val($("#hiddenclassid").val());
		getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
		$("#subject").val($("#hiddensubject").val());

	}

	getClassName($("#locationname").val());
	getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());

	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();


		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getClassName($("#locationname").val());
		getSubjectName($("#classname").val(),$("#locationname").val(),specialization);
	});

	$("#classname").change(function(){

		getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
		getClassSpecilization($(this).val(),$("#locationname").val());

	});
	$("#subject").change(function(){
		if($(this).val()!='-' && $("#lab_name").val().trim() !="")
			checkSubjectduplication();
	});

	$("#lab_name").change(function(){
		if($(this).val().trim!="" && $("#subject").val().trim() !="-")
			checkSubjectduplication();
	});

	$("#specialization").change(function(){
		if($("#classname").val()!="")
			getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
	});

	$('input[type=file]').change(function () {

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
		} }); 
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="subject.html?method=addingLabDetails"){
		$(".successmessagediv").show();
		setTimeout(function(){
			window.location.href="menuslist.html?method=laboratory";
		},2500);
	}

	$('#save').click(function() 
			{

		$(".errormessagediv").show();

		var  classname = $("#classname").val();
		var lab_name=$("#lab_name").val().trim();
		var subject = $("#subject").val();
		var description = $("#description").val();
		var locationId=$("#locationname").val();
		var specialization = $("#specialization").val();
		var subjectCode = $("#subjectCode").val();

		var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
		var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;

		if(locationId=="")
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Select Branch Name");
			showError("#locationname");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else   if(classname==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Class");
			showError("#classname");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else if(subject=="")
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Select subject..");
			showError("#subject");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else if(subject.length < 2)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Subject Name cannot be empty...");
			showError("#subject");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else if(lab_name == "")
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Practical Name....");
			showError("#lab_name");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else if(lab_name.length < 1)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Practical Name cannot be empty...");
			showError("#lab_name");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
		}
		else if(subjectCode ==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field required - Practical Code");
			showError("#subjectCode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false; 
		}
		else if(checkLabCode()){
			$(".errormessagediv").show();
			$(".validateTips").text("Practical Code already exists...");
			showError("#subjectCode");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false; 
		}
		else {

			var data = {
					"classname":classname,"lab_name":lab_name,
					"subject":subject,"description":description,
					"locationId":locationId,"specialization":specialization,
					"subjectCode":subjectCode
			};

			$.ajax({
				type : 'POST',
				url : "subject.html?method=addingLabDetails",
				data: data,
				async : false,
				success : function(response) {

					var result = $.parseJSON(response);
					if(result.status == "success"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
						$(".validateTips").text("Adding record progressing...");
						setTimeout(function() {
							window.location="menuslist.html?method=laboratory";
						}, 5000);

					}else{
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Failed to add the record. Try again");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 5000);
						return false;
					}

				}

			});

		}


			});

});

function checkLabCode(){
	var flag = false;
	var data = {
			"labCode" : $("#subjectCode").val(),
			"locId" : $("#locationname").val()
	}
	$.ajax({
		type : 'POST',
		url : "subject.html?method=checkLabCode",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			if(result.status == "invalid"){
				flag = true;
			}
		}
	});

	return flag;
}

function getSubjectName(classId,location,spec){

	var data = {
			"classId" : classId,
			"locationId":location,
			"specId" : spec
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getSubjectByClass",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			$('#subject').empty();
			$('#subject').append('<option value="-">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#subject').append(
						'<option value="'
						+ result.jsonResponse[j].subjectId
						+ '">'
						+ result.jsonResponse[j].subjectName
						+ '</option>');
			}


		}
	});
}
function clearFields() {


	document.getElementById("classname").value = "";
	document.getElementById("subjtname").value = "";
	document.getElementById("file").value = "";
	document.getElementById("description").value = "";
}

function checkSubjectduplication() {

	var locationId=$("#locationname").val();
	var classId = $("#classname").val();
	var subject = $("#subject").val(); 
	var subname = $("#subject").text(); 
	var lab_name = $("#lab_name").val();

	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"lab_name" : lab_name
	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=validateLabNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {


			var result = $.parseJSON(data);


			if(result.des_available =="true") {

				$(".errormessagediv").show();
				$(".validateTips").text("Practical already exists with selected subject");

				document.getElementById("lab_name").style.border = "1px solid #AF2C2C";
				document.getElementById("lab_name").style.backgroundColor = "#FFF7F7";
				$("#lab_name").val("");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);
				return false;
			}
		}
	});
}

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
			$('#specialization').append('<option value="-">----------Select----------</option>');
			spelen = result.jsonResponse.length; 

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}

function getClassName(val) {



	$.ajax({
		url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
		data:{"locationId":val},
		async:false,

		success : function(data) {



			var result = $.parseJSON(data);
			$(classname).html("");

			$(classname).append('<option value="">' + "----------Select----------" + '</option>');

			for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

				$(classname).append(
						'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
						+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
			}

		}
	});}

function showError(id){

	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});

}	
function capital(value,id)
{
	if(value.length>0)
	{
		var str=value.replace(value.substr(0,1),value.substr(0,1).toUpperCase());
		document.getElementById(id).value=str;

	}
}
function HideError(id){
	$(".errormessagediv").hide();
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
}
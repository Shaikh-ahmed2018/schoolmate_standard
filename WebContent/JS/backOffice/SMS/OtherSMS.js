$(document).ready(function(){


	$('#validateTable').hide();

	
    
	
	
	
    
	$('#save').click(function(){


     	var studentList = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentList[i] = $(this).val(); 
		});
		
		var dateId = $("#date").val();
		var classid = $("#classid").val();
		var studentid = $("#studentid").val();
		var locId = $("#locId").val();
		var sectionid = $("#sectionid").val();
		var description = $("#smstext").val();


		
		 if(locId==""||locId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select  School Name");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		
		 else if(dateId==""||dateId==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Date");
			document.getElementById("date").style.border = "1px solid #AF2C2C";
			document.getElementById("date").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		
		else if(classid==""||classid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Class");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if(sectionid==""||sectionid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Section");
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		
		else if(studentid==""||studentid==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Student");
			document.getElementById("studentid").style.border = "1px solid #AF2C2C";
			document.getElementById("studentid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else{

			datalist={
					"dateId"      : dateId ,
					"classid"     : classid,
					"description" : description,
					"locId"       : locId,
					"studentid"   : studentList.toString(),

			},

			$.ajax({

				type : 'POST',
				url : "smsPath.html?method=SendOtherSMS",
				data : datalist,
				async : false,
				success : function(response) {

					var result = $.parseJSON(response);

					if(result.jsonResponse=="success"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Message sent successfully");

						setTimeout(function(){

							window.location.href = "menuslist.html?method=othersmslist";

						},3000);
					}
					else{

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Message sending failed");

						setTimeout(function(){

							window.location.href = "menuslist.html?method=othersmslist";

						},3000);

					}
				}
			});
		}
	});

	$("#locId").change(function(){
		getClassList();
	});	


	$("#classid").change(function(){
		getSectionList();
		getStudent();
	});

	$("#sectionid").change(function(){
		getStudentBySections();
	});	




	$('#sectionid,#classid').change(function() {
		var secList = []; 
		var classList = []; 

		$('#sectionid :selected').each(function(i, selected){ 
			secList[i] = $(selected).val(); 

			$("#hsectionid").val(secList);


		});

		$('#classid :selected').each(function(i, selected){ 
			classList[i] = $(selected).val(); 


			$("#hclassid").val(classList);

		});


		var studentlist = []; 
		$('#studentid :selected').each(function(i, selected){ 
			studentlist[i] = $(selected).val(); 

			$("#hstudentid").val(studentlist);
		});

	});

	$('#section').change(function() {
		$('#studentcheckbox').show();
		getStudent();


	});

	$('#student').change(function() {
		onchangeStudents();

	});

	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1;
	var yyyy = today.getFullYear();
	if(dd<10){
		dd='0'+dd;
	} 
	if(mm<10){
		mm='0'+mm;
	} 
	var today = dd+'-'+mm+'-'+yyyy;

	$("#date").datepicker({

		dateFormat : "dd-mm-yy",
		//maxDate : 0,
		minDate : 0,
		changeMonth : "true",
		changeYear : "true",

	}).datepicker('setDate', today);

	var today_date=$('#date').val();

});

var classlength=0;

function getClassName(classidval, category) {

	var categoryVal = $("#category").val();


	if (categoryVal == "all") {

		$('#sectioncheckbox').hide();
		$('#studentcheckbox').hide();
		$('#classcheckbox').hide();

		$("#classname").html("");
		$("#section").html("");
		$("#student").html("");
		document.getElementById("classname").disabled = true;
		document.getElementById("section").disabled = true;
		document.getElementById("student").disabled = true;
		document.getElementById("classname").style.background = "#B8B894";
		document.getElementById("section").style.background = "#B8B894";
		document.getElementById("student").style.background = "#B8B894";

	} else {

		$('#sectioncheckbox').show();
		$('#studentcheckbox').show();
		$('#classcheckbox').show();

		$('#classcheckbox').attr('checked', false);

		document.getElementById("classname").disabled = false;
		document.getElementById("section").disabled = false;
		document.getElementById("student").disabled = false;
		document.getElementById("classname").style.background = "#FFFFFF";
		document.getElementById("section").style.background = "#FFFFFF";
		document.getElementById("student").style.background = "#FFFFFF";




		var classid = "#" + classidval;
		var categoryVal = $("#category").val();

		datalist = {
				"categoryVal" : categoryVal
		},
		$
		.ajax({
			type : 'POST',
			url : "childinfo.html?method=getClassDetail",
			data : datalist,
			success : function(response) {
				var result = $
				.parseJSON(response);
				$(classid).html("");
				classlength=result.parentVOList.length;
				for ( var j = 0; j < result.parentVOList.length; j++) {
					$(classid)
					.append(
							'<option value="'
							+ result.parentVOList[j].classDetailId
							+ '">'
							+ result.parentVOList[j].classDetailsName
							+ '</option>');
				}

			}
		});

	}
}

var sectionlength=0;
function getClassSection(sectionid, classname) {

	var classid = $("#classid").val();



	datalist={
			"classid" : classid.join()
	},

	$.ajax({

		type : 'POST',
		url : "communicationPath.html?method=getSection",
		data : datalist,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#sectionid').html("");

			$('#sectionid').append();

			'<option value="' + "" + '">' + "---Select---"

			+ '</option>';

			for ( var j = 0; j < result.seclist.length; j++) {

				$('#sectionid').append(

						'<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

						+ '</option>');
			}
		}
	});
}

var studentlength=0;
function getStudent() {

	var sectionid = $("#sectionid").val();
	var classid = $("#classid").val();


	datalist={
			"classid" : classid.join(),
			"sectionid" : sectionid.join(),
	},

	$.ajax({

		type : 'POST',
		url : "smsPath.html?method=getStudentMeeting",
		data : datalist,
		success : function(response) {


			var result = $.parseJSON(response);

			$('#studentid').html("");

			$('#studentid').append();

			('<option value="' + "" + '">' + ""	+ '</option>');

			for(var j = 0; j < result.studentlist.length; j++){

				$('#studentid').append(

						'<option value="'

						+ result.studentlist[j].id + '">'

						+ result.studentlist[j].name

						+ '</option>');
			}
		}
	});

}

function onchangeStudents() {
	var student = $("#student").val();	

	if(studentlength==student.length){

		$('#studentcheckbox').attr('checked', true);
	}else{

		$('#studentcheckbox').attr('checked', false);

	}
}

function validations(){

	var category=$("#category").val();
	var classid=$('#classname').val();
	var section=$('#section').val();
	var date=$('#date').val();
	var smstext=$('#smstext').val();
	var student=$('#student').val();

	if(category==""){

		$('#validateTable').show();
		$('.validateTips').text("please select category");

		return false;

	}


	if(category=="all"){


		if(date==""){

			$('#validateTable').show();
			$('.validateTips').text("please select date");

			return false;

		}else if(smstext==""){

			$('#validateTable').show();
			$('.validateTips').text("please enter text for sms");

			return false;

		}else{



			if(validateMeetingSms()){

				var x = confirm("Other SMS Message Already Exist With Same Content And Same Date. Do You Want To Send For Principal Approval ?");

				if(x){

					return true;

				}else{

					$('#validateTable').show();
					$('.validateTips').text("Other SMS Message Already Exist");

					return false;


				}

			}else{

				return true;
			}

		}

	}else{

		if(classid==null || classid==""){

			$('#validateTable').show();
			$('.validateTips').text("please select class");

			return false;

		}

		if(classid.length==classlength){

			if(date==""){

				$('#validateTable').show();
				$('.validateTips').text("please select date");

				return false;

			}else if(smstext==""){

				$('#validateTable').show();
				$('.validateTips').text("please enter text for sms");

				return false;

			}else{



				if(validateMeetingSms()){

					var x = confirm("Other SMS Message Already Exist With Same Content And Same Date. Do You Want To Send For Principal Approval ?");

					if(x){

						return true;

					}else{

						$('#validateTable').show();
						$('.validateTips').text("Other SMS Message Already Exist");

						return false;


					}

				}else{

					return true;
				}

			}

		}else{

			if(section==null || section==""){

				$('#validateTable').show();
				$('.validateTips').text("please select section");

				return false;


			}



			if(section.length==sectionlength){

				if(date==""){

					$('#validateTable').show();
					$('.validateTips').text("please select date");

					return false;

				}else if(smstext==""){

					$('#validateTable').show();
					$('.validateTips').text("please enter text for sms");

					return false;

				}else{



					if(validateMeetingSms()){

						var x = confirm("Other SMS Message Already Exist With Same Content And Same Date. Do You Want To Send For Principal Approval ?");

						if(x){

							return true;

						}else{

							$('#validateTable').show();
							$('.validateTips').text("Other SMS Message Already Exist");

							return false;


						}

					}else{

						return true;
					}

				}

			}else{

				if(student==null || student==""){

					$('#validateTable').show();
					$('.validateTips').text("please select student");

					return false;
				}else
					if(date==""){

						$('#validateTable').show();
						$('.validateTips').text("please select date");

						return false;

					}else if(smstext==""){

						$('#validateTable').show();
						$('.validateTips').text("please enter text for sms");

						return false;

					}else{

						if(validateMeetingSms()){

							var x = confirm("Other SMS Message Already Exist With Same Content And Same Date. Do You Want To Send For Principal Approval ?");

							if(x){

								return true;

							}else{

								$('#validateTable').show();
								$('.validateTips').text("Other SMS Message Already Exist");

								return false;


							}

						}else{

							return true;
						}

					}

			}

		}

	}


}

function  validateMeetingSms(){

	var meetingstatus=false;
	var date=$('#date').val();
	var smstext=$('#smstext').val();


	var validatedetails = {
			"date" : date,
			"smstext" : smstext

	};

	$.ajax({
		type : 'POST',
		url : "uniformsms.html?method=validateUniformSms",
		data : validatedetails,
		async:false,

		success : function(response) {
			var result = $
			.parseJSON(response);

			meetingstatus=result.status;


		}
	});

	return meetingstatus;

}

function selectAllCLasses(){



	if($('#classcheckbox').prop("checked") == true){


		$('#classname option').prop('selected', true);

		$('#sectioncheckbox').hide();
		$('#studentcheckbox').hide();
		$("#section").html("");
		$("#student").html("");



		var classidVal=$("#classname").val();

		document.getElementById("section").disabled = true;
		document.getElementById("student").disabled = true;
		document.getElementById("section").style.background = "#B8B894";
		document.getElementById("student").style.background = "#B8B894";

	}else{


		$('#sectioncheckbox').show();
		$('#studentcheckbox').show();

		$('#classname option').prop('selected', false);
		document.getElementById("section").disabled = false;
		document.getElementById("student").disabled = false;
		document.getElementById("section").style.background = "#FFFFFF";
		document.getElementById("student").style.background = "#FFFFFF";
		$("#section").html("");



	}

}


function selectAllSections(){


	if($('#sectioncheckbox').prop("checked") == true){

		$('#studentcheckbox').hide();
		$('#section option').prop('selected', true);

		$("#student").html("");

		document.getElementById("student").disabled = true;
		document.getElementById("student").style.background = "#B8B894";


	}else{


		$('#section option').prop('selected', false);
		document.getElementById("student").disabled = false;
		document.getElementById("student").style.background = "#FFFFFF";
		$("#student").html("");



	}

}


function selectAllStudents(){


	if($('#studentcheckbox').prop("checked") == true){


		$('#student option').prop('selected', true);

	}else{


		$('#student option').prop('selected', false);

		document.getElementById("student").disabled = false;
		document.getElementById("student").style.background = "#FFFFFF";



	}

}

function getClassList(){
	var locationid=$("#locId").val();
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
			$('#classid').html("");
			$('#classid').append('<option value="">' +"------Select------"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'+result.ClassList[j].classcode+'">'+result.ClassList[j].classname+'</option>');
			}
		}
	});
}

function getSectionList(){
	datalist={
			"classidVal" : $("#classid").val(),
			"locationId":$("#locId").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			var j=0;
			var len=result.sectionList.length;
			for ( j = 0; j < len; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getStudentBySections() {

	var locId = $("#locId").val();
	var classid = $("#classid").val();

	var sectionlist = []; 
	$('#sectionid :selected').each(function(i, selected){ 
		sectionlist[i] = $(this).val();
	});
	datalist={
			"locId":locId,
			"classid":classid,
			"sectionlist":sectionlist.toString(),
	},

	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEventBySections",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#studentid').html("");
			var j=0;
			var len=result.studentlist.length;
			for(var j = 0; j < len; j++){
				$('#studentid').append(
						'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
			}
		}
	});
}

function getStudent() {

	var locId = $("#locId").val();
	var classid = $("#classid").val();

	datalist={
			"locId":locId,
			"classid":classid,
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getStudentMeetingAndEvent",
		data : datalist,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#studentid').html("");
			var j=0;
			var len=result.studentlist.length;
			for(var j = 0; j < len; j++){
				$('#studentid').append(
						'<option value="'+result.studentlist[j].id+'">'+result.studentlist[j].name+'</option>');
			}
		}
	});
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


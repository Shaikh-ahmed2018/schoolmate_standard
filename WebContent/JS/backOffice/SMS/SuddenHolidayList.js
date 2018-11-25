$(document).ready(function() {

	totalSMSBalance = 0;
	getBalanceSMSCount();

	$("#accyear").val($("#hacademicyaer").val());

	if($("#hiddenmsg").val()=="Holiday Details Sent Successfully"){
		$(".successmessagediv").show();
		$(".validateTips").text($("#hiddenmsg").val());

		setTimeout(function(){
			$(".successmessagediv").show();
		},3000);
		window.location.href="menuslist.html?method=suddenholiodayslist";
	}

	$("#back1").click(function(){
		window.location.href="menuslist.html?method=suddenholiodayslist";
	});

	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='3'>No Records Found</td></tr>");
		pagination(100);
	}
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});

	$(".select").change(function() {
		if($(".select").length == $(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
	getClassList();
	$("#locId").change(function(){
		getClassList();
	});	

	$("#classid").change(function(){

		$('#classid').show();
		$('#sectionid').show();
		getSectionList();
	});


	if($("#div1 .panel-body").text().trim()=="Nothing found to display."){
		$("#div1 .panel-body").empty();
		$("#div1 .panel-body").append('<table id="allstudent" class="table">' +
				'<thead><tr><th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
				'<th class="sortable">Holiday Date<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">Holiday Reason<img src="images/sort1.png" style="float: right"></a></th>'+
				'</tr></thead>'+
		'<tbody><tr><td colspan="3">No Records Found</td></tr></tbody></table>');

	}

	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();

	var today = dd + '-' + mm + '-' + yyyy;

	$("#dateId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:new Date($("#historyenddate").val()),
		minDate:new Date($("#historystartdate").val()),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){

			var min = $(this).datepicker('getDate'); // Get selected date
			$("#todate").datepicker('option', 'minDate', min);

		}

	}).datepicker('setDate', today);

	$("#hdateId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:new Date($("#historyenddate").val()),
		minDate:new Date($("#historystartdate").val()),
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
			$('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			
			today_date=$('#hdateId').val();
			Holidayreason=$('#Holidayreason').val();
			
			if(Holidayreason==null){

				$('#smstext').val("Dear Parents, Please Note that "+today_date+" is a Special Holiday. The School will be Closed on that Day. Enjoy the Holiday.");
			}else{

				$('#smstext').val("Dear Parent, Please note "+today_date+" is a Holiday due to "+Holidayreason+". The School remain Closed on that day. For more information contact School.");
			}


		}

	});

	$('#HolidaysSave').click(function(){

		var classList = []; 
		var sectionlist = []; 
		$('#sectionid :selected').each(function(i, selected){ 
			var selectedSection = $(this).val().split(",");
			sectionlist.push(selectedSection[0]);
			classList.push(selectedSection[1]);
		});
		var classCode = $("#classCode").val(classList);
		var sectionCode = $("#secCode").val(sectionlist);
		var date = $('#dateId').val();
		var smstext = $('#smstext').val();
		var Holidaydate=$('#hdateId').val();
		var today_date=$('#hdateId').val();
		var Holidayreason=null;
		var SMSCharacters = $('#smsCharacters').text();
		var locationId = $("#historylocId").val();
		var balanceSMS = totalSMSBalance;

		if (date == "") {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Date");
			document.getElementById("dateId").style.border = "1px solid #AF2C2C";
			document.getElementById("dateId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}else if (Holidaydate == "") {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Holiday Date");
			document.getElementById("hdateId").style.border = "1px solid #AF2C2C";
			document.getElementById("hdateId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if ($("#locId").val() == "" || $("#locId").val()==null || $("#locId").val()==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Branch");
			document.getElementById("locId").style.border = "1px solid #AF2C2C";
			document.getElementById("locId").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if ($("#classid").val() == "" || $("#classid").val()==null || $("#classid").val()==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Class");
			document.getElementById("classid").style.border = "1px solid #AF2C2C";
			document.getElementById("classid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if ($("#sectionid").val() == "" || $("#sectionid").val()==null || $("#sectionid").val()==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Select Division From List");
			document.getElementById("sectionid").style.border = "1px solid #AF2C2C";
			document.getElementById("sectionid").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}
		else if (smstext == "") {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - SMS Text");
			document.getElementById("smstext").style.border = "1px solid #AF2C2C";
			document.getElementById("smstext").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;

		} 
		else if ($("#Holidayreason").val().trim() == "" || $("#Holidayreason").val()==null || $("#Holidayreason").val()==undefined) {
			$('.errormessagediv').show();
			$('.validateTips').text("Field Required - Holiday Reason");
			document.getElementById("Holidayreason").style.border = "1px solid #AF2C2C";
			document.getElementById("Holidayreason").style.backgroundColor = "#FFF7F7";
			$(".errormessagediv").fadeOut(3000);
			return false;
		}

		else if(validateEventSms()){

			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Sudden holiday Already Exists With Same Content And Same Date. Do You Want To Send The Same ?</p>");

		}
		else{

			var datalist = {
					"date": date,
					"smstext": smstext,
					"Holidaydate": Holidaydate,
					"Holidayreason": Holidayreason, 
					"SMSCharacters": SMSCharacters,
					"classCode": classList.toString(),
					"sectionCode": sectionlist.toString(),
					"locationId":locationId,
					"balanceSMS":balanceSMS,
			};

			$.ajax({
				type : 'POST',
				url : "suddenHolidays.html?method=AddSuddenHoliday",
				data : datalist,
				async:false,
				success : function(response) {
					var result = $.parseJSON(response);

					if (result.status == "true") {
						$(".successmessagediv").show();
						$(".validateTips").text("Holiday Details Message Sent Successfully");
						setTimeout(function() {
							window.location.href="menuslist.html?method=suddenholiodayslist";
						}, 3000);
					}
					else if(result.status=="insufficientSMSBalance"){

						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Insufficient SMS Balance To Send This SMS");
						setTimeout(function() {
							window.location.href="menuslist.html?method=suddenholiodayslist";
						}, 3000);
					}
					else
					{
						$( ".errormessagediv").show();
						$( ".validateTips").text("Holiday Details Message Sending Failed");
						setTimeout(function() {
							window.location.href="menuslist.html?method=suddenholiodayslist";
						}, 3000);
					}
				}
			});
		}
	});

	$("#dialog").dialog({
		autoOpen  : false,
		resizable : false,
		modal     : true,
		title     : "Confirmation",
		buttons   : {
			'Yes' : function() {

				today_date=$('#hdateId').val();
				Holidayreason=$('#Holidayreason').val().trim();

				if(Holidayreason==null || Holidayreason==""){

					$('#smstext').val("Dear Parents, Please Note that "+today_date+" is a Special Holiday. The School will be Closed on that Day. Enjoy the Holiday.");
				}else{

					$('#smstext').val("Dear Parent, Please note "+today_date+" is a Holiday due to "+Holidayreason+". The School remain Closed on that day. For more information contact School.");
				}
				$(this).dialog('close');

				var classList = []; 
				var sectionlist = []; 
				$('#sectionid :selected').each(function(i, selected){ 
					var selectedSection = $(this).val().split(",");
					sectionlist.push(selectedSection[0]);
					classList.push(selectedSection[1]);
				});
				var classCode = $("#classCode").val(classList);
				var sectionCode = $("#secCode").val(sectionlist);
				var date = $('#dateId').val();
				var smstext = $('#smstext').val();
				var Holidaydate=$('#hdateId').val();
				var today_date=$('#hdateId').val();
				var Holidayreason=null;
				var SMSCharacters = $('#smsCharacters').text();
				var locationId = $("#historylocId").val();
				var balanceSMS = totalSMSBalance;

				var datalist = {
						"date": date,
						"smstext": smstext,
						"Holidaydate": Holidaydate,
						"Holidayreason": Holidayreason, 
						"SMSCharacters": SMSCharacters,
						"classCode": classList.toString(),
						"sectionCode": sectionlist.toString(),
						"locationId":locationId,
						"balanceSMS":balanceSMS,
				};

				$.ajax({
					type : 'POST',
					url : "suddenHolidays.html?method=AddSuddenHoliday",
					data : datalist,
					async:false,
					success : function(response) {
						var result = $.parseJSON(response);

						if (result.status == "true") {
							$(".successmessagediv").show();
							$(".validateTips").text("Holiday Details Message Sent Successfully");
							setTimeout(function() {
								window.location.href="menuslist.html?method=suddenholiodayslist";
							}, 3000);
						}
						else if(result.status=="insufficientSMSBalance"){

							$(".errormessagediv").show();
							$(".successmessagediv").hide();
							$(".validateTips").text("Insufficient SMS Balance To Send This SMS");
							setTimeout(function() {
								window.location.href="menuslist.html?method=suddenholiodayslist";
							}, 3000);
						}
						else
						{
							$( ".errormessagediv").show();
							$( ".validateTips").text("Failed TO Send Message");
							setTimeout(function() {
								window.location.href="menuslist.html?method=suddenholiodayslist";
							}, 3000);
						}
					}
				});


			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});


	$("#classid").change(function(){
		$('#classid').show();
		$('#sectionid').show();

	});


	$('#sectionid,#classid').change(function() {
		var secList = []; 
		var classList = []; 

		$('#sectionid :selected').each(function(i, selected){ 
			secList[i] = $(selected).val(); 

			$('#secCode').val(secList);

		});

		$('#classid :selected').each(function(i, selected){ 
			classList[i] = $(selected).val(); 

			$('#classCode').val(classList);

		});

	});


	var today_date=$('#hdateId').val();
	var Holidayreason=null;

	$('#smstext').val("Dear Parents, Please Note that "+today_date+" is a Special Holiday. The School will be Closed on that Day. Enjoy the Holiday.");
	var sms=$('#smstext').val();

	$("#dateId").change(function(){

		today_date=$('#hdateId').val();
		Holidayreason=$('#Holidayreason').val();
		if(Holidayreason==null){

			$('#smstext').val("Dear Parents, Please Note that "+today_date+" is a Special Holiday. The School will be Closed on that Day. Enjoy the Holiday.");
		}else{

			$('#smstext').val("Dear Parent, Please note "+today_date+" is a Holiday due to "+Holidayreason+". The School remain Closed on that day. For more information contact School.");
		}
	});

	$('#Holidayreason').change(function(){
		today_date=$('#hdateId').val();
		Holidayreason=$('#Holidayreason').val();

		$('#smstext').val("Dear Parent, Please note "+today_date+" is a Holiday due to "+Holidayreason+". The School remain Closed on that day. For more information contact School.");

	});


	$('#smsCharacters').text(parseInt(sms.length));
	$('#Holidayreason').keyup(function(){
		var maxlength=$('#smstext').attr('maxlength');
		Holidayreason=$('#Holidayreason').val().trim();
		$('#smstext').val("Dear Parent, Please note "+today_date+" is a Holiday due to "+Holidayreason+". The School remain Closed on that day. For more information contact School.");
		$('#smsCharacters').text(parseInt(parseInt(sms.length)+parseInt(Holidayreason.length)));
	});
});


function getSectionList(){

	datalist={
			"classidVal" : $("#classid").val(),
			"locationId":$("#locId").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getSectionForSMS",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			var j=0;
			var len=result.sectionList.length;
			for ( j = 0; j < len; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode +","+ result.sectionList[j].classcode + '">' 
						+ result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
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
			$('#classid').append('<option value="">' +"----------Select----------"+ '</option>');
			$('#classid').append('<option value="all">' +"All"+ '</option>');
			var j=0;
			var len=result.ClassList.length;
			for ( j = 0; j < len; j++) {
				$('#classid').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}

function  validateEventSms(){

	var meetingstatus=false;
	var date=$('#hdateId').val();
	var smstext=$('#smstext').val();

	var validatedetails = {
			"date" : date,
			"smstext" : smstext
	};

	$.ajax({
		type : 'POST',
		url : "suddenHolidays.html?method=validateSuddenHolidaysSms",
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

function getBalanceSMSCount(){
	var locationid=$("#locId").val();

	datalist={
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "smsPath.html?method=getBalanceSMSCount",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);

			totalSMSBalance = result.smsCount;

			return totalSMSBalance;
		}
	});
}


function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border ="1px solid #cccccc";
	document.getElementById(pointer.id).style.backgroundColor ="#ffffff";
}

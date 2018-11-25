
function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
$(document).ready(function() {
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
	}

	var status = null;

	$(".successmessagediv").hide();
	$(".validateTips").hide();

	var sucessmsg = $('#success').val();

	if (!sucessmsg == "") {
		$(".errormessagediv").hide();
		$(".successmessagediv").show();
		$(".validateTips").show();
		$(".validateTips").text("Leave Updated Successfully");
	}

	$("#search").click(function() {
		////alert("search is working");

		var searchTerm = $("#searchterm").val()
		.trim();

		////alert("searchTerm" + searchTerm);

		window.location.href = "teachermenuaction.html?method=leaveApproval&searchTerm="
			+ searchTerm;

	});

	$('#excelDownload').click(
			function() {
				var searchTerm = $("#searchid").val()
				.trim();
				////alert(searchTerm);

				window.location.href = 'teachermenuaction.html?method=downloadLeaveDetailsXLS&searchTerm='
					+ searchTerm;

			});
	$("#pdfDownload")
	.click(
			function() {
				var searchTerm = $("#searchid").val()
				.trim();

				window.location.href = "teachermenuaction.html?method=downloadLeaveDetailsPDF&searchTerm="
					+ searchTerm;

			});

	$("#editleaveapproval").click(function() {

		var snoid = $(".select:checked").attr('id');

		if (snoid == "" || snoid == null) {

			$(".errormessagediv").show();
			$(".validateTips1").text(
			"Select any one CheckBox");

			return false;
		}

		else {

			window.location.href = "teachermenuaction.html?method=LeaveApprovalScreen&snoid="
				+ snoid;
		}
	});

	var datestSplit = $("#fromdate").val().split('-');
	var newstDate = datestSplit[1]+'-'+datestSplit[0]+'-'+datestSplit[2];
	var dateenSplit = $("#todate").val().split('-');
	var newenDate = dateenSplit[1]+'-'+dateenSplit[0]+'-'+dateenSplit[2];
	
	$("#approvedstartdate").datepicker({
		dateFormat : 'dd-mm-yy',
		minDate : new Date(newstDate),
		maxDate : new Date(newenDate),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#approvedenddate").datepicker("option","minDate",selectedDate);
		}
	});	

	$("#approvedenddate").datepicker({
		dateFormat : 'dd-mm-yy',
		minDate : new Date(newstDate),
		maxDate : new Date(newenDate),
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#approvedstartdate").datepicker("option","maxDate",selectedDate);
		}
	});	


	var snoid = $("#snoid").val();

	$('#approvedstartdate,#approvedenddate,#approvedstartsessionDay,#approvedendsessionDay').change(function(){

		var total ="";
		var shalfday = $('#approvedstartsessionDay').val();
		var ehalfday = $('#approvedendsessionDay').val();
		var d1 = $('#approvedstartdate').datepicker('getDate');
		var d2 = $('#approvedenddate').datepicker('getDate');
		var total_reqleaves = $('#noofleaves').val();
		var oneDay = 24*60*60*1000;
		var diff = 0;
		if (d1 && d2) {
			diff = Math.round(Math.abs((d2.getTime() - d1.getTime())/(oneDay))+1);
		}
		var total_leave=diff;

		if(shalfday == "FH" && ehalfday == "FH" ){

			total = total_leave-0.5;
		}

		else if(shalfday == "FH" && ehalfday == "SH"){

			total = total_leave;
		}

		else if(shalfday == "SH" && ehalfday == "SH" ){
			total = total_leave-0.5;
		}
		else if( (shalfday == "SH" && ehalfday == "FH"))
		{
			total = total_leave-1;
		}

		$('#leavesapproved').val(total);

		/*var date1 = $('#approvedstartdate').val();
		var date2 = $('#approvedenddate').val();

		var da1 = Date.parse(dateConverter(date1));
		var da2 = Date.parse(dateConverter(date2));

		var dat1 = new Date(date1);

		if(d1 != "" && d2 != ""){
			if (d1 > d2) {

				$("#errormessagediv1").show();
				$(".validateTips1").text("Approved StartDate Should Be Less Then Approved EndDate");
				setTimeout(function() {
					$('#errormessagediv1')
					.fadeOut();
				}, 3000);
				return false;
			}
		}
		if (total > total_reqleaves) {
			$("#errormessagediv1").show();
			$(".validateTips1").text("Approved Leaves Should be Less than or Equal to Requested Leaves");
			setTimeout(function() {
				$('#errormessagediv1')
				.fadeOut();
			}, 3000);

		} else
			$('#leavesapproved').val(total);*/

		/*	var total = "";
										var shalfday = $(
												'#approvedstartsessionDay')
												.val();
										var ehalfday = $(
												'#approvedendsessionDay').val();

										var date1 = $('#approvedstartdate')
												.val();
										startDate = date1.split("-");
										var dstartdate = new Date(startDate[2],
												startDate[1] - 1, startDate[0]);

										var date2 = $('#approvedenddate').val();
										endDate = date2.split("-");
										var denddate = new Date(endDate[2],
												endDate[1] - 1, endDate[0]);

										var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds

										var diffDays = Math
												.round(Math.abs((dstartdate
														.getTime() - denddate
														.getTime())
														/ (oneDay)));

										var total_leave = diffDays + 1;

										if (shalfday == "FH"
												&& ehalfday == "FH") {

											total = total_leave - 0.5;

										}

										else if (shalfday == "FH"
												&& ehalfday == "SH") {

											total = total_leave;
										}

										else if (shalfday == "SH"
												&& ehalfday == "SH") {

											total = total_leave - 0.5;

										} else if ((shalfday == "SH" && ehalfday == "FH")) {

											total = total_leave - 1;

										}

										var d1 = Date
												.parse(dateConverter(date1));
										var d2 = Date
												.parse(dateConverter(date2));*/
		/*if (d1 > d2) {

											$("#errormessagediv1").show();
											$(".validateTips1")
													.text(
															"StartDate Should Be Less Then EndDate");
											setTimeout(function() {
												$('#errormessagediv1')
														.fadeOut();
											}, 3000);
											return false;
										}
		 */
	});

	$("#submit").click(function() {

				var snoid = $("#snoid").val();
				var fdate = $('#fromdate').val();
				var edate = $('#todate').val();
				var approved_leaves = $('#leavesapproved').val();
				var leavestatus = $('#approvedleavestatus').val();
				var ApprovedStartdate = $('#approvedstartdate').val();
				var ApprovedEndDate = $('#approvedenddate').val();
				var comments = $('#comments').val();
				var approvedstartsessionDay = $("#approvedstartsessionDay").val();
				var approvedendsessionDay = $("#approvedendsessionDay").val();
			

				if(leavestatus.trim() == ""){
					$(".errormessagediv1").show();
					$(".validateTips1").text("Field Required - Leave Status");

					document.getElementById("approvedleavestatus").style.border = "1px solid #AF2C2C";
					document.getElementById("approvedleavestatus").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv1').fadeOut();
					}, 3000);
					return false;
				}else{
					
					if(leavestatus.trim() == "Approved"){
						if (ApprovedStartdate == "" || ApprovedStartdate == null) {

							$(".errormessagediv1").show();
							$(".validateTips1").text("Field Required - Approved StartDate");

							document.getElementById("approvedstartdate").style.border = "1px solid #AF2C2C";
							document.getElementById("approvedstartdate").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;
						}
						
						else if (ApprovedEndDate == ""
							|| ApprovedEndDate == null) {

							$(".errormessagediv1").show();
							$(".validateTips1").text("Field Required - Approved EndDate");

							document.getElementById("approvedenddate").style.border = "1px solid #AF2C2C";
							document.getElementById("approvedenddate").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;

						}
						
						else if (approvedstartsessionDay == ''
							|| approvedstartsessionDay == null) {
							$(".errormessagediv1").show();
							$(".validateTips1").text("Field Required -Start Session");

							document.getElementById("approvedstartsessionDay").style.border = "1px solid #AF2C2C";
							document.getElementById("approvedstartsessionDay").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;
						} else if (approvedendsessionDay == ''
							|| approvedendsessionDay == null) {
							$(".errormessagediv1").show();
							$(".validateTips1").text("Field Required - End Session");
							document.getElementById("approvedendsessionDay").style.border = "1px solid #AF2C2C";
							document.getElementById("approvedendsessionDay").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;
						}
						else if (approved_leaves.trim() == "") {

							$(".errormessagediv1").show();
							$(".validateTips1").text("Field Required - Total Approved Leaves");
							document.getElementById("leavesapproved").style.border = "1px solid #AF2C2C";
							document.getElementById("leavesapproved").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;

						}else if(comments == "" || comments == null){
							$(".errormessagediv1").show();
							$(".validateTips1")
							.text("Field Required - Comments");
							document.getElementById("comments").style.border = "1px solid #AF2C2C";
							document.getElementById("comments").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;
						}
						else {
							saveLeavesApproveStatus();
						} 
					}else if(leavestatus == "Rejected"){
						if(comments == "" || comments == null){
							$(".errormessagediv1").show();
							$(".validateTips1")
							.text("Field Required - Comments");
							document.getElementById("comments").style.border = "1px solid #AF2C2C";
							document.getElementById("comments").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv1').fadeOut();
							}, 3000);
							return false;
						}else{
							saveLeavesApproveStatus();
						}
					}
				}
				
			});


	/*var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
					if(pageUrl=="teachermenuaction.html?method=ApprovingLeaveforleaveRequest"){

						$(".successmessagediv").show();
						$(".validateTips").text("Leave Request "+status+" Progressing...");
						$(".successmessagediv").attr("style","width:100%;");
						setTimeout(function(){
							window.location.href="teachermenuaction.html?method=leaveApproval";
						},3000);
					}*/

});

function saveLeavesApproveStatus(){
	
	data = {
			
			"snoid" : $("#snoid").val(),
			"fdate" : $('#fromdate').val(),
			"edate" : $('#todate').val(),
			"approved_leaves" : $('#leavesapproved').val(),
			"leavestatus" : $('#approvedleavestatus').val(),
			"ApprovedStartdate" : $('#approvedstartdate').val(),
			"ApprovedEndDate" : $('#approvedenddate').val(),
			"comments" : $('#comments').val(),
			"approvedstartsessionDay" : $("#approvedstartsessionDay").val(),
			"approvedendsessionDay" : $("#approvedendsessionDay").val()
	}
	$("#submit").prop("disabled", true);
	$.ajax({
		
		type : "POST",
		url : "teachermenuaction.html?method=ApprovingLeaveforleaveRequest",
		data : data,
		async : false,
		success : function(data){
			var obj = $.parseJSON(data);
			if(obj.status == "true"){

	     		$(".successmessagediv").show();
	 			$(".successmessage").text("Leave"+" "+$('#approvedleavestatus').val()+" "+"successfully");
	 			setTimeout(function(){
	 				$(".successmessagediv").hide();
	 				window.location.href = "teachermenuaction.html?method=leaveApproval";
	 			},3000);
			}else{
	     		$(".errormessagediv").show();
	 			$(".validateTips").text("Failed to approve. Try again!!! ");
	 			setTimeout(function(){
	 				$(".errormessagediv").hide();
	 			},3000);
			}
			 $("#submit").prop("disabled", false);
		},
		error: function (e) {

     		$(".errormessagediv").show();
 			$(".validateTips").text("Failed to approve. Try again!!! ");
 			setTimeout(function(){
 				$(".errormessagediv").hide();
 			},3000);
             $("#submit").prop("disabled", false);
         }
	
	});
	
	
}

function getvaldetails(value) {

	var s1 = value.id;

	var snoid = s1;
	/*
	 * //alert("snoid "+snoid);
	 */
	$("#snoid").val(snoid);

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


function HideError() 
{

	document.getElementById("approvedstartdate").style.border = "1px solid #ccc";
	document.getElementById("approvedstartdate").style.backgroundColor = "#fff";

	document.getElementById("approvedenddate").style.border = "1px solid #ccc";
	document.getElementById("approvedenddate").style.backgroundColor = "#fff";

	document.getElementById("leavesapproved").style.border = "1px solid #ccc";
	document.getElementById("leavesapproved").style.backgroundColor = "#fff";

	document.getElementById("approvedendsessionDay").style.border = "1px solid #ccc";
	document.getElementById("approvedendsessionDay").style.backgroundColor = "#fff";

	document.getElementById("approvedstartsessionDay").style.border = "1px solid #ccc";
	document.getElementById("approvedstartsessionDay").style.backgroundColor = "#fff";

	document.getElementById("approvedleavestatus").style.border = "1px solid #ccc";
	document.getElementById("approvedleavestatus").style.backgroundColor = "#fff";

}

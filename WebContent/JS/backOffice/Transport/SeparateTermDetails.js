function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document).ready(function()
 {
	$("#Acyearid").val($("#hacademicyaer").val());
	
	$('#add').click(function(){
	   window.location.href = "transport.html?method=addtSeparateTransportTermdetails&historylocId="+$("#locationname").val()+
	   "&historyacademicId="+$("#Acyearid").val();
	});
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records .0");
		
	}
	$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='delete' style='position: absolute;top:0;bottom:0;margin:-1px;'></span>");
	deleteterm();
	
	getTermListByJs($("#locationname").val(),$("#Acyearid").val());
	
	
	$("#locationname").change(function(){
		
		var locationId=$(this).val();
		if(locationId=="" || locationId == undefined){
			locationId="all";
		}
		getTermListByJs(locationId,$("#Acyearid").val());

	});
	$("#Acyearid").change(function(){
	
		var locationId=$("#locationname").val();
		if(locationId=="" || locationId == undefined){
			locationId="all";
		}
		getTermListByJs(locationId,$(this).val());
	});
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("?")+1);
	if(pageUrl.split("=")[1]=="separateTransportTermList&searchvalue"){
		$("#searchvalue").focus();
	}
	$('#searchvalue').keypress(function(e){
		if(e.which==13)
			window.location.href = "menuslist.html?method=separateTransportTermList&searchvalue="+$(this).val().trim();

	});

	$("#selectall").change(function(){
		
		$(".academic_Checkbox_Class").prop("checked",$(this).prop("checked"));

	});
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv1").hide();
	}, 3000);


	$("#termedit").click(function() {

				var cnt = 0;
				$('input.academic_Checkbox_Class:checked').map(
								function() {

									var term_id = $(
											this).attr(
													"id");

									var split_id = term_id
									.split('_');
									getData = split_id[1]
									.split(',');

									cnt++;
								});

				if (cnt == 0 || cnt > 1) {

					$(".successmessagediv").hide();
					$(".errormessagediv1").show();
					$(".validateTips1").text(
					"Select Any One Checkbox");

				}

				else {

					var id = getData[0];
					window.location.href = "termfee.html?method=edittermDetails&id="
						+ id;

				}

			});

	$("#search").click(function(){
		var searchvalue = $('#searchvalue').val();

		window.location.href = "menuslist.html?method=separateTransportTermList&searchvalue="
			+ searchvalue;

	});

	$('#excelDownload').click(function() {
		var searchvalue = $('#termsearchid')
		.val();
		window.location.href = 'termfee.html?method=downloadtermlistDetailsXLS&searchvalue='
			+ searchvalue;

	});
	$("#pdfDownload").click(function() {

		var searchvalue = $('#termsearchid').val();

		window.location.href = "termfee.html?method=downloadtermlistDetailsPDF&searchvalue="
			+ searchvalue;
	});
	
	if($("#historyacademicId").val()!="" || $("#historylocId").val()!="")
	{
		if($("#historyacademicId").val()!=""){
			$("#Acyearid").val($("#historyacademicId").val());
		}
		$("#locationname").val($("#historylocId").val());
		
		getTermListByJs($("#locationname"),$("#Acyearid"));
	}

 });

function getTermListByJs(locationId,accyear){

	$.ajax({
		type:'POST',
		url:'termfee.html?method=TransporttermListByJs',
		data:{"locationId":$("#locationname").val(),"accyear":$("#Acyearid").val()},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.termlist.length>0){
				for(var i=0;i<result.termlist.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><span name='select' class='academic_Checkbox_Class' id='academicCheckBox_"+result.termlist[i].termid+",'>"+(i+1)+"</span></td>" +
							"<td>"+result.termlist[i].accyear+"</td>" +
							"<td>"+result.termlist[i].locationName+"</td>" +
							"<td>"+result.termlist[i].termname+"</td>" +
							"<td>"+result.termlist[i].startdate+"</td>" +
							"<td>"+result.termlist[i].enddate+"" +
					"</tr>");
				}
				$("#allstudent tbody tr:last td:last").append("<span' class='glyphicon glyphicon-trash' id='delete' style='position: absolute;top:0;bottom:0;margin:-1px;'></span>");
				deleteterm();
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.termlist.length);
		}
	});
}

function deleteterm(){
	   
	       $("#delete").click(function()
			 {
				getDataArray=[]; //add array1
				getDataArray.push($(this).closest("tr").find("span.academic_Checkbox_Class").attr("id").split('_')[1]);		
				$("#dialog").dialog("open");
				$("#dialog").empty();
				$("#dialog").append("<p>Are You Sure to Delete?</p>");
			 });

			$("#dialog").dialog({
				autoOpen  : false,
				resizable : false,
				modal     : true,
				title     : "Term Details",
				buttons   : {
					'Yes' : function() {

					 var datalist = { 'getDataArray':getDataArray.toString()}; 
						 $.ajax({
							 type : "GET",
							 url : "transport.html?method=deleteSeparateTermDetails",
							 data : datalist,
							 async : false,

							 success : function(response) {
								 var result = $.parseJSON(response);    
								 if (result.jsonResponse =="Term Details Deleted Successfully") {
									 $(".successmessagediv").show();
									 $(".successmessagediv .validateTips").text("Term Details Deleted Successfully");

									 setTimeout(function() {
										 getTermListByJs($("#locationname").val(),$("#Acyearid").val());
									 },2000);
								 }
								 if (result.jsonResponse == "Term Details not Deleted Successfully") {
									 $(".errormessagediv").show();
									 $(".validateTips").text("Term Details not Deleted Successfully");
								 }

								 else if (result.jsonResponse == "Term Details Already Mapped") {
									 $(".errormessagediv").show();
									 $(".validateTips").text("Cannot Delete Record. "+ "Term Details Already Mapped");
								 }

								 setTimeout(function() {
									 getTermListByJs($("#locationname").val(),$("#Acyearid").val());
								 },2000);

							 }
						 });
						 $(this).dialog('close');

					},
					'No' : function() {

						$(this).dialog('close');
					}
				}
			});
}

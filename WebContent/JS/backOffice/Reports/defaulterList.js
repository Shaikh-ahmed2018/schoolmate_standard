$(document).ready(function(){
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No of Records "+0+".");
		 pagination(100);
		$("#download").show()
	}
	
	$("#academicYear").val($("#hacademicyaer").val());
	getClassByLoc();
	getTerm();
	$("#locationName").change(function(){
		getClassByLoc();
		$("#className").val("");
		$("#divisionName").val("");
		$("#termName").val("");
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No of Records "+0+".");
		 pagination(100);
		$("#download").show();
		getTerm();
		
		if($('#locationName').val()==""){
		
			$("#className").val("");
			$("#divisionName").val("");
			$("#termName").val("");
			$("#allstudent tbody").empty();
			$("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No of Records "+0+".");
			pagination(100);
			$("#download").show();
		}
	});
	$("#className").change(function(){
		if($(this).val() ==""){
			$('#divisionName').html("");
			$('#divisionName').append('<option value="">----------Select----------</option>');
		}else
		getDivision();
	});

	$("#academicYear").change(function(){
		getTerm();
	});
	
	$("#searchs").click(function(){
		if($("#className").val().trim() !="" && $("#termName").val().trim() != "")
		getDefaulterFeeList();
	});
	
	$(".reset").click(function(){
		$("#className").val("");
		$('#divisionName').html("");
		$('#divisionName').append('<option value="">----------Select----------</option>');
		$("#termName").val("");
		$("#academicYear").val($("#hacademicyaer").val());
		$('#locationName').val($("#hschoolLocation").val());
		$("#allstudent").empty();
		$("#page").hide();
	});
	
	$("#download").click(function(){
		if($("#locationName").val()=="" || $("#locationName").val()==undefined){
			showError("#locationName","Select Branch Name");
			return false;
		}
		if($("#className").val()=="" || $("#className").val()==undefined){
			showError("#className","Select Class");
			return false;
		}
		if($("#academicYear").val()=="" || $("#academicYear").val()==undefined){
			showError("#academicYear","Select academicYear");
			return false;
		}
		if($("#divisionName").val()=="" || $("#divisionName").val()==undefined){
			showError("#divisionName","Select Division ");
			return false;
		}
		if($("#termName").val()=="" || $("#termName").val()==undefined){
			showError("#termName","Select Terms");
			return false;
		} 
		
		if($("#allstudent tbody tr").length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}else if($("#allstudent tbody tr:first td:first").text() == "No Records Found"){
			$(".errormessagediv").show();
			$(".validateTips").text("No records found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
	});
	

	
//DOWNLOAD PDF AND EXCEL
	$('#excelDownload').click(function() {
				var locname = $("#locationName :selected").text();
				var classname=$("#className :selected").text();
				var	divname=$("#divisionName :selected").text();
				var termname=$("#termName :selected").text();
				var accname=$("#academicYear :selected").text();
				var locId = $("#locationName").val();
				var classId=$("#className").val();
				var	divId=$("#divisionName").val();
				var termId=$("#termName").val();
				var accId=$("#academicYear").val();
				//fee master action clss
				window.location.href ="addfee.html?method=FeeDetailsDefaulterXLS" +
						"&locId="+locId+"&accId="+accId+"&classId="+classId+"&divId="+divId+"&termId="+termId
					   +"&locname="+locname+"&classname="+classname+"&divname="+divname+"&termname="+termname+"&accname="+accname;
			});
	
	$("#pdfDownload").click(function(){
		var locname = $("#locationName :selected").text();
		var classname=$("#className :selected").text();
		var	divname=$("#divisionName :selected").text();
		var termname=$("#termName :selected").text();
		var accname=$("#academicYear :selected").text();
		var locId = $("#locationName").val();
		var classId=$("#className").val();
		var	divId=$("#divisionName").val();
		var termId=$("#termName").val();
		var accId=$("#academicYear").val();
		
		window.location.href = "addfee.html?method=FeeDetailsDefaulterPDF" +
				"&locId="+locId+"&accId="+accId+"&classId="+classId+"&divId="+divId+"&termId="+termId
			   +"&locname="+locname+"&classname="+classname+"&divname="+divname+"&termname="+termname+"&accname="+accname;
	});
		
	$("#print").click(function(){
		var locname = $("#locationName :selected").text();
		var classname=$("#className :selected").text();
		var	divname=$("#divisionName :selected").text();
		var termname=$("#termName :selected").text();
		var accname=$("#academicYear :selected").text();
		var locId = $("#locationName").val();
		var classId=$("#className").val();
		var	divId=$("#divisionName").val();
		var termId=$("#termName").val();
		var accId=$("#academicYear").val();
		$.ajax({
			type:'POST',
			url:"addfee.html?method=PrintDefautFeeReport" +"&locId="+locId+"&accId="+accId+"&classId="+classId+"&divId="+divId+"&termId="+termId +"&locname="+locname+"&classname="+classname+"&divname="+divname+"&termname="+termname+"&accname="+accname,
			async:false,
			success:function(data){
				
			}
		});
	});
	
});//end JQUERY

function getClassByLoc(){
		var locationid=$("#locationName").val();
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
				$('#className').html("");
				$('#className').append('<option value="">----------Select----------</option>');
				for ( var j = 0; j < result.ClassList.length; j++) {
					$('#className').append('<option value="'
							+ result.ClassList[j].classcode + '">'
							+ result.ClassList[j].classname
							+ '</option>');
				}
			}
		});
};

function getDivision(){
		datalist={
				"classidVal" : $("#className").val(),
				"locationId":$("#locationName").val()
		},
		$.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getClassSection",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$('#divisionName').html("");
				$('#divisionName').append('<option value="all">----------Select----------</option>');
				for ( var j = 0; j < result.sectionList.length; j++) {
					$('#divisionName').append('<option value="' + result.sectionList[j].sectioncode
							+ '">' + result.sectionList[j].sectionnaem
							+ '</option>');
				}
			}
		});
}

function getTerm(){
	datalist={
			"locId" : $("#locationName").val(),
			"accId" : $("#academicYear").val(), 
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			var j;
			var len= result.data.length;
			$('#termName').html("");
			$('#termName').append('<option value="">' +"----------Select----------"+ '</option>');
			for ( j = 0; j <len; j++) {
				$('#termName').append('<option value="'
						+ result.data[j].termname+ '">'
						+ result.data[j].termId
						+ '</option>');
			}
		}
	});
}
function getDefaulterFeeList(){
		$("#allstudent").show();
		datalist ={"locId":$("#locationName").val(),
					"classId":$("#className").val(),
					"divId":$("#divisionName").val(),
					"termId":$("#termName").val(),
					"accId":$("#academicYear").val(),
			};
		$.ajax({
			type:"post",
			url:"feecollection.html?method=getDefaulterFeeList",
			data:datalist,
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				$("#allstudent tbody").empty();
				$("#download").show();
				if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){
					
					$("#allstudent tbody").append("<tr>"
							+"<td>"+(i+1)+"</td>"
							+"<td>"+result.data[i].admissionNo+"</td>"
							+"<td>"+result.data[i].studentName+"</td>"
							+"<td>"+result.data[i].locationName+"</td>"
							+"<td>"+result.data[i].className+"</td>"
							+"<td>"+result.data[i].divisionName+"</td>"
							+"<td>"+result.data[i].termName+"</td>"
							+"<td>"+result.data[i].dueAmt+"</td>"
							+"</tr>");
					}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='8'>No Record Found</td></tr>");
					$("#download").hide();
				}
				$("#page").show();
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No of Records "+result.data.length+".");
				pagination(100);
			
			}
		});
	 }




function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}

function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
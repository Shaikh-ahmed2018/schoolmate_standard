$(document).ready(function(){
	
	$("#eventname").change(function(){
		getCategoryDropDown();
		getStageDropDown();
	});
	$("#categoryName").change(function(){
		getProgramDropDown();
	});
	
	$("#certificateon").change(function(){
		getTableOnCertificate();
	});
	$("#programName").change(function(){
		$("#certificateon").val("");
	});
	$("#printOn").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});
	
	$("#print").click(function(){
		var certificateon=$("#certificateon").val();
		if(certificateon=="merit"){
		var student= $(".student:checked").val().split(" ");
		var evId=student[0];
		var place=student[1];
		var catId=student[2];
		var progId=student[3];
		var accId=student[4];
		var admissionNo=student[5];
		var classsec=student[6];
		var StdName=student[7]+"  "+student[8]+"  "+student[9]+"  "+student[10];
		 if (evId == "" || evId==null) {
 			$(".errormessagediv").show();
 			$(".validateTips").text("Select Event");
 			showError("#eventname");
 			setTimeout(function() {
 				$('.errormessagediv').fadeOut();
 			}, 3000);
 			
 			return false;
 		}else if (catId == "" || catId==null) {
 			$(".errormessagediv").show();
 			$(".validateTips").text("Select Category");
 			showError("#categoryName");
 			setTimeout(function() {
 				$('.errormessagediv').fadeOut();
 			}, 3000);
 			
 			return false;
 		}
 		else if (progId == "" || progId==null) {
 			$(".errormessagediv").show();
 			$(".validateTips").text("Select Programme");
 			showError("#programName");
 			setTimeout(function() {
 				$('.errormessagediv').fadeOut();
 			}, 3000);
 			
 			return false;
 		}
 		else if (certificateon == "" || certificateon==null) {
 			$(".errormessagediv").show();
 			$(".validateTips").text("Select Certificate On");
 			showError("#programName");
 			setTimeout(function() {
 				$('.errormessagediv').fadeOut();
 			}, 3000);
 			return false;
 		}else if ($("#printOn").val() == "" || $("#printOn").val()==null) {
 			$(".errormessagediv").show();
 			$(".validateTips").text("Enter Print On");
 			showError("#printOn");
 			setTimeout(function() {
 				$('.errormessagediv').fadeOut();
 			}, 3000);
 			return false;
 		}
		 
		$.ajax({
			type:'post',
			data:{	
                "place":place,
			    "evId":evId,
				"catId":catId,
			    "progId":progId,
				"accId":accId,
				"certificateon":certificateon,
				"admissionNo":admissionNo,
				"classsec":classsec,
				"StdName":StdName,
			},
			url:"EventsMenu.html?method=meritCertificatePrint",
			async:false,
			success:function(data){
				var result = $.parseJSON(data);
				if (result) {
					setTimeout(function() {
					window.location.href="EventsMenu.html?method=CertificatePrinting";
				}, 2000);} else {
					DeptName_validate_update = 0;
				}
				}
		});
		}else{

			var student= $(".student:checked").val().split(" ");
			var evId=student[0];
			var place=student[1];
			var catId=student[2];
			var progId=student[3];
			var accId=student[4];
			var admissionNo=student[5];
			var classsec=student[6];
			var StdName=student[7]+"  "+student[8]+"  "+student[9]+"  "+student[10];
			 if (evId == "" || evId==null) {
	 			$(".errormessagediv").show();
	 			$(".validateTips").text("Select Event");
	 			showError("#eventname");
	 			setTimeout(function() {
	 				$('.errormessagediv').fadeOut();
	 			}, 3000);
	 			
	 			return false;
	 		}else if (catId == "" || catId==null) {
	 			$(".errormessagediv").show();
	 			$(".validateTips").text("Select Category");
	 			showError("#categoryName");
	 			setTimeout(function() {
	 				$('.errormessagediv').fadeOut();
	 			}, 3000);
	 			
	 			return false;
	 		}
	 		else if (progId == "" || progId==null) {
	 			$(".errormessagediv").show();
	 			$(".validateTips").text("Select Programme");
	 			showError("#programName");
	 			setTimeout(function() {
	 				$('.errormessagediv').fadeOut();
	 			}, 3000);
	 			
	 			return false;
	 		}
	 		else if (certificateon == "" || certificateon==null) {
	 			$(".errormessagediv").show();
	 			$(".validateTips").text("Select Certificate On");
	 			showError("#programName");
	 			setTimeout(function() {
	 				$('.errormessagediv').fadeOut();
	 			}, 3000);
	 			return false;
	 		}else if ($("#printOn").val() == "" || $("#printOn").val()==null) {
	 			$(".errormessagediv").show();
	 			$(".validateTips").text("Enter Print On");
	 			showError("#printOn");
	 			setTimeout(function() {
	 				$('.errormessagediv').fadeOut();
	 			}, 3000);
	 			return false;
	 		}
			 
			$.ajax({
				type:'post',
				data:{	
	                "place":place,
				    "evId":evId,
					"catId":catId,
				    "progId":progId,
					"accId":accId,
					"certificateon":certificateon,
					"admissionNo":admissionNo,
					"classsec":classsec,
					"StdName":StdName,
				},
				url:"EventsMenu.html?method=meritCertificatePrint",
				async:false,
				success:function(data){
					var result = $.parseJSON(data);
					if (result) {setTimeout(function() {
						window.location.href="EventsMenu.html?method=CertificatePrinting";
					}, 2000);} else {
						DeptName_validate_update = 0;
					}
					}
			});
		}
		
		
	});

});
function getCategoryDropDown(){
	var id = $("#eventname").val();
	
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getCategoryName",
		data :{"id":id,
				"flag":"onLoad",
				},
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
			$("#categoryName").empty();
			$("#categoryName").append("<option value=''>----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
				
			$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
					+result.data[i].categoryName+ "</option>");
			}
		}
	});
}
function getProgramDropDown(){
var catId = $("#categoryName").val();
var evId = $("#eventname").val();
datalist ={
		"catId":catId,
		"evId":evId,
		"flag":"onLoad",
};
$.ajax({
	type:'post',
	url:"EventsMenu.html?method=getprogramName",
	async:false,
	data:datalist,
	success:function(data){
		var result = $.parseJSON(data);
		$("#programName").empty();
		$("#programName").append("<option value=''>-----------Select----------</option>");
		for(var i=0;i<result.data.length;i++){
		$("#programName").append("<option value='"+result.data[i].progId+"'>" 
				+result.data[i].progName+"</option>");
		}
	}
});
}

function getStageDropDown(){
	var evId = $("#eventname").val();
	datalist ={
			"evId":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getStage",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageName").empty();
			$("#stageName").append("<option value=''>-----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
					+result.data[i].stageName+"</option>");
			}
		}
	});
}
function getTableOnCertificate(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var accId = $("#hacademicyaer").val();
	var certificateon=$("#certificateon").val();
	
	if(certificateon!="merit"){
		
		$(".place").hide();
		$(".sno").show();
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableOnCertificate",
		data:{	
		    "evId":evId,
			"catId":catId,
			"progId":progId,
			"accId":accId,
			"certificateon":certificateon,
		},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td><input type='radio' name='certificate' class='student' value='"+evId+" "+result.data[i].place+" "+catId+" "+progId+" "+accId+" "+result.data[i].admissionNo+" "+result.data[i].classSec+" "+result.data[i].studentName+"'></td>" +
									"<td>"+result.data[i].admissionNo+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
	}else{
		$(".place").show();
		$(".sno").hide();
		$.ajax({
			type:'POST',
			url:"EventsMenu.html?method=getTableOnCertificate",
			data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"accId":accId,
				"certificateon":certificateon,
			},
			async:false,
			success:function(data){
				var result= $.parseJSON(data);
				$("#individualPart tbody").empty();
				
				if(result.data.length>0){
					for(var i=0;i<result.data.length;i++){	
						$("#individualPart tbody").append("<tr>" +
										"<td><input type='radio' name='certificate' class='student' value='"+evId+" "+result.data[i].place+" "+catId+" "+progId+" "+accId+" "+result.data[i].admissionNo+" "+result.data[i].classSec+" "+result.data[i].studentName+"'></td>" +
										"<td>"+result.data[i].admissionNo+"</td>"+
										"<td>"+result.data[i].studentName+"</td>"+
										"<td>"+result.data[i].classSec+"</td>"+
										"<td>"+result.data[i].chestNoId+"</td>"+
										"<td>"+result.data[i].place+"</td>"+
										"</tr>");
						}
				}else{
					$("#individualPart tbody").append('<tr><td colspan="6">No records Found</td></tr>');
				}
				$(".noOfRecords").empty();
				$(".noOfRecords").append("No of Records "+result.data.length+".");
				pagination(50);
				}
			});
		
		
	}
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
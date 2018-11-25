$(document).ready(function(){
	
	if($("#hiddenperiodId").val()!=""){
		
		$("#locationname").val($("#hiddenlocId").val());
		getStream($("#locationname").val());
		$("#StreamName").val($("#hiddenstreamId").val());
		getClassList();
		$("#classId").val($("#hiddenclsId").val());
		
	}else
	getStream($("#locationname").val());
	
	
	$("#locationname").change(function(){
		getStream();
	});
	
	$("#StreamName").change(function(){
		getClassList();
		
	});
	
	$("#back").click(function(){
		window.location.href ="periodmaster.html?method=periodList";
	});
	
	$('#Save').click(function() {
		var stream = $('#StreamName').val();
		var className = $('#classId').val();

		

		if($("#locationname").val()=="" || $("#locationname").val()==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Branch");
			$("#locationname").focus();
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (stream == "" || stream == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required -  Stream");
			$("#StreamName").focus();
			document.getElementById("StreamName").style.border = "1px solid #AF2C2C";
			document.getElementById("StreamName").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}else if(className == "" || className == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Class");
			$("#classId").focus();
			document.getElementById("classId").style.border = "1px solid #AF2C2C";
			document.getElementById("classId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if($("#noofPeriod").val().trim()=="" || $("#noofPeriod").val().trim()==null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Number of Period");
			$("#noofPeriod").focus();
			document.getElementById("noofPeriod").style.border = "1px solid #AF2C2C";
			document.getElementById("noofPeriod").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(validateclassName()){
			/*	$('.errormessagediv').show();
				$(".validateTips").text("Bank Name Already Exist.");*/
				$("#classId").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
					$("#classId").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
				}, 3000);
				return false;
			}
		else {
			var datalist = {
					"stream" : stream,
					"className" : className,
					"locationId":$("#locationname").val().trim(),
				    "noofperiod":$("#noofPeriod").val().trim(),
				    "hiddenId":$("#hiddenperiodId").val(),
			};

			$.ajax({
				type : 'POST',
				url : "periodmaster.html?method=insertPeroidAction",
				data : datalist,
				success : function(response) {
					var result = $.parseJSON(response);
			
					if (result.status =="true") {
				
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
						setTimeout(function(){
							window.location.href ="periodmaster.html?method=periodList";
						},3000);
					} 
					else if (result.status =="update") {
						$(".successmessagediv").show();
						$(".validateTips").text("Updating Record Progressing...");
						setTimeout(function(){
							window.location.href ="periodmaster.html?method=periodList";
						},3000);
					} 
					else {
						$(".errormessagediv").show();
						$(".validateTips").text("Failed to Add Record..");
					}
				}
			});
		}
	});

	
	
});

function getStream(){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':$("#locationname").val()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#StreamName').empty();
			$('#StreamName').append('<option value="">-------------Select-----------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {
				$('#StreamName').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
			}
		}
	});
	
	

}

function getClassList(){
	var locationname=$("#locationname").val();
	var streamId=$("#StreamName").val();
	datalist={
			"streamId" : streamId,
			"locationname":locationname
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getClassesByStreamAndLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classId').html("");

			$('#classId').append('<option value="">' + "----------Select----------"	+ '</option>');
			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classId').append('<option value="'+result.ClassesList[j].classId+'">'+result.ClassesList[j].classname+'</option>');
			}
		}
	});
}

function validateclassName(){

	flag = false;
	
	var hclsId=$("#hiddenclsId").val();
	var locationname=$("#locationname").val();
	var clsId = $("#classId").val()
	
	if(clsId != hclsId){
		$.ajax({
			type : 'post',
			url : 'periodmaster.html?method=validateclassName',
			data : {
				"clsId" : clsId ,
				"locId" : locationname
			},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);				
			
			        if (result.result=="true") {
						$(".errormessagediv").show();
						$(".validateTips").text("Period Already Exits for this class");
						flag = true;

					} 
				else{
					flag = false;
				}
			}

		});
	}
	
	else{
		flag = false;
	}
	
	return flag;
}


function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}



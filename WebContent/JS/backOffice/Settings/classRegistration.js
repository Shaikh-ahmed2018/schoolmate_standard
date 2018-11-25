
function removeMessage() {
	$(".successmessagediv").hide();
	$(".successmessagediv1").hide();
}



$(document).ready(function(){
	    if($("#schoolId").val()!=""){
	    	$("#locationname").prop("disabled", true);
	    	$("#classId").prop("disabled", true);
	    }
	    
	    pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	 
		 $("#locationname").val($("#schoolId").val());
		 getStream($("#locationname"));
		 
			/*if($("select[name='class']").text()==$("input[name='hiddenclassname']").val())
						$("select[name='class'] option")*/

			var split_id =  $("input[name='updateClassCode']").val().split('D');
			getData = split_id[1];

			$("select[name='class']").val(getData);
			$("#locationname").change(function(){
				$("#classId").val("");
				getStream($(this));
			});
			
			$("#streamId").change(function(){
				if($("#streamId").val() != $("#hiddenStreamId").val() && pageurl!="classPath.html?method=addClass"){
					validateDuplicateLocation();
				}
				if($("#streamId").val() != "" && $("#classId").val()!=""){
					validateDuplicateLocation();
				}
			});
			
			$("#classId").change(function(){
				if($("#streamId").val() != "" && $(this).val()!=""){
					validateDuplicateLocation();
				}
			});
			
			setTimeout("removeMessage()", 3000);
			setInterval(function() {
				$(".errormessagediv").hide();
			}, 3000);	


			var hiddenStreamId=$("#hiddenStreamId").val().trim();
			 
			if(hiddenStreamId!=undefined && hiddenStreamId!=""){
				$("#streamId option[value=" + hiddenStreamId + "]").attr('selected', 'true');
			}

			$('#classSave').click(function() {
				var stream = $('#streamId').val();
				var className = $('#classId').val();
				var status = $('#statusId').val();
				var updateClassCode = $('#updateClassCode').val();
				var updateclassName =$('#hiddenclassname').val();

				if($("#locationname").val()=="" || $("#locationname").val()==null){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required - Location Name");
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
					$(".validateTips").text(
					"Select Stream Name");
					$("#streamId").focus();
					document.getElementById("streamId").style.border = "1px solid #AF2C2C";
					document.getElementById("streamId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;

				}else if(className == "" || className == null){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required - Class Name");
					$("#classId").focus();
					document.getElementById("classId").style.border = "1px solid #AF2C2C";
					document.getElementById("classId").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if(pageurl=="classPath.html?method=addClass" && checkClassName()==10){
					$(".errormessagediv").show();
					$(".validateTips").text("This Class & Stream Already Exist !! Make it Active");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if(pageurl=="classPath.html?method=addClass" && checkClassName()==1){
					$(".errormessagediv").show();
					$(".validateTips").text("Class & Stream is Already Mapped");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else {
					var datalist = {
							"stream" : stream,
							"className" : className,
							"status" :status,
							"updateClassCode":updateClassCode,
							"updateclassName" :updateclassName,
							"locationId":$("#locationname").val().trim(),
							"hiddenStreamId":$("#hiddenStreamId").val(),
					};

					$.ajax({
						type : 'POST',
						url : "classPath.html?method=insertClassAction",
						data : datalist,
						success : function(response) {
							var result = $.parseJSON(response);
							if (result.status == "Class Added Successfully") {
								$(".successmessagediv").show();
								$(".validateTips").text("Adding Record Progressing...");
								setTimeout(function(){
									window.location.href = "menuslist.html?method=classList&locId="+$("#hiddenlocId").val()+"&streamId="+$("#hiddenstreamId").val()+"&status="+$("#hiddenstatus").val();
								},3000);
							} else if(result.status == "Class Updated Successfully"){
								$(".successmessagediv").show();
								$(".validateTips").text("Updating Record Progressing...");
								setTimeout(function(){
									window.location.href = "menuslist.html?method=classList&locId="+$("#hiddenlocId").val()+"&streamId="+$("#hiddenstreamId").val()+"&status="+$("#hiddenstatus").val();
								},3000);
							}
							else {
								$(".errormessagediv").show();
								$(".validateTips").text("Class & Stream is Already Mapped");
							}
						}
					});
				}
			});

			$('#editClass').click(function() {

				$(".errormessagediv1").hide();
				var cnt = 0;
				$('input.class_Checkbox_Class:checkbox:checked').map(function() {
					if (cnt > 0) {
						$(".errormessagediv").show();
						$('.validateTips').text("You can update only one Class Details at a time");
						cnt++;
						return false;
					} else {
						var check_id = $(this).attr("id");
						var split_id = check_id.split('_');
						getData = split_id[1].split(',');
						var classCode=getData[0];
						cnt++;
					}
				});
				if (cnt == 0) {
					$(".errormessagediv").show();
					$('.validateTips').text("Select Class to Update");
				}
				if (cnt == 1) {
					window.location.href = "classPath.html?method=editClass&classCode="	+ classCode;
				}
			});
			    $('#back1').click(function() {
					window.location.href = "menuslist.html?method=classList&locId="+$("#hiddenlocId").val()+"&streamId="+$("#hiddenstreamId").val()+"&status="+$("#hiddenstatus").val();
				});

		});

function checkClassName() {
	var className = $('#classId').val();
	var stream = $("#streamId").val(); 
	var school=$("#locationname").val();
	var updateClassCode = $('#updateClassCode').val();
     
	var checkClassName = {
			"className" : className,
			"stream" : stream,
			"updateClassCode":updateClassCode,
			"school":school,
	};
	var value = 0;

	$.ajax({
		type : "POST",
		url : "classPath.html?method=classNameCheck",
		data : checkClassName,
		async : false,
		success : function(data) {

			var result = $.parseJSON(data);
			 
			
			if (result.status=="inactive") {
				$('.errormessagediv').show();
				$('.validateTips').text("This Class & Stream Already Exist !! Make it Active");
				value = 10;
			}
			else if (result.status=="true") {
				$('.errormessagediv').show();
				$('.validateTips').text("Class & Stream Already Exists");
				value = 1;
			} 
			else {
				value = 0;
			}
			
		}
	});
	return value;
}

function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">-------------Select-----------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
			}
		}
	});
}

function validateDuplicateLocation(){
	    var streamname_validate=0;
		var locationId=$("#locationname").val();
	 	var streamname=$("#streamId option:selected").text();
	 	var className = $('#classId').val();
	 	
		var classObject = {
			"locationId":locationId,
			"streamname" : streamname, 
			"className":className,
			"updateClassCode":$("#updateClassCode").val(),
		};
	
	$.ajax({
		type : "GET",
		url : 'classPath.html?method=validateDuplicateLocation',
		data : classObject,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			if (result.status=="true") 
		    {
				if(pageurl!="classPath.html?method=addClass"){
					$("#locationname").val($("#schoolId").val());
				}else{
					$('#classId').val("");
				}
				 
				$.ajax({
					url : "classPath.html?method=getStreamDetailAction",
					async : false,
					data:{'school':$("#locationname").val().trim()},
					success : function(data) {
						
						var result = $.parseJSON(data);
						$('#streamId').empty();
						$('#streamId').append('<option value="">-------------Select-----------</option>');
						for ( var j = 0; j < result.streamList.length; j++) {

							$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
									+ result.streamList[j].streamName+ '</option>');
						}
					}
				});
				
				$("#streamId").val($("#hiddenStreamId").val());
				$(".errormessagediv").show();
				$(".successmessagediv").hide();
				 $(".validateTips").text("Class already mapped in this Location!!");
			 
				 setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 4000);
			}
			else if (result.status=="inactive") 
		    {
				if(pageurl!="classPath.html?method=addClass"){
					$("#locationname").val($("#schoolId").val());
				}else{
					$('#classId').val("");
				}
				
				$.ajax({
					url : "classPath.html?method=getStreamDetailAction",
					async : false,
					data:{'school':$("#schoolId").val().trim()},
					success : function(data) {
						
						var result = $.parseJSON(data);
						$('#streamId').empty();
						$('#streamId').append('<option value="">-------------Select-----------</option>');
						for ( var j = 0; j < result.streamList.length; j++) {

							$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
									+ result.streamList[j].streamName+ '</option>');
						}
					}
				});
				
				$("#streamId").val($("#hiddenStreamId").val());
				$(".errormessagediv").show();
				$(".successmessagediv").hide();
				$(".validateTips").text("Class already exist in this location !! Make it Active");
				 setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 4000);
			} 
		}
	});
	return streamname_validate;
}

function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";

}

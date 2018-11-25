function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function callAjax(urlWithMethod, dataToBeSend) {
	
	$.ajax({
         
		url : urlWithMethod,
		data : dataToBeSend,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
			jsonResult = result.status;
			
			 setTimeout(function(){
				   
				 location.reload();
			 
			 },5000);
	
		}
	});
	return jsonResult;
}

function insertValidate() {

	
	
	$(".errormessagediv").hide();
	
	tips = $(".validateTips");
	var bValid = true;

	bValid = bValid && checkLengthText($('#streamName'), "Stream Name", 0, 30);

	bValid = bValid
			&& checkRegexpText($('#streamName'), /^[a-zA-Z\s]+$/g,
					"Name should be Alphabet");

	if (!bValid) {
		$(".errormessagediv").show();
	} else {

		$(".errormessagediv").hide();
	}

	return bValid;
		
}

$(document).ready(function() {
	
	var url=window.location.href.substring(window.location.href.lastIndexOf('?') + 1);

	getReligionDetails();
	
	var hiddenreligionId=$('#hiddenreligionId').val();
	var hiddencasteId=$('#hiddencasteId').val();
	if(hiddenreligionId != "" || hiddenreligionId == null){
		$("#religion option[value='"+ hiddenreligionId +"']").attr('selected', 'true');
		
		getCasteDetails();
		$("#casteId option[value='"+ hiddencasteId +"']").attr('selected', 'true');
	}
	
	$("#casteId").change(function(){
		if($('#hiddencasteId').val()!=$("#casteId").val()){
			validateCasteCategory();
		}
	});
	
	$('#back1').click(function() {
		window.location.href="menuslist.html?method=casteCategoryDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
	});
	
	$('#religion').change(function() {
		getCasteDetails();
	});
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);
	
	var pageurl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);

	$("#saveCasteCategoryId").click(function(){

		$(".successmessagediv").hide();
		$(".errormessagediv").hide();

		var religionId = $("#religion").val();
		var casteId = $("#casteId").val();
		var casteCategoryId=$("#casteCategoryNameId").val();
		var castecateVal=$("#castecategoryId").val();
		var hiddencastecategory=$("#hiddencastecategory").val();
		
		if(religionId == "" || religionId == null){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Religion");
			$("#religionNameId").focus();
			document.getElementById("religion").style.border = "1px solid #FF0000";
			document.getElementById("religion").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (casteId == "" || casteId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Caste");
			$("#religionNameId").focus();
			document.getElementById("casteId").style.border = "1px solid #FF0000";
			document.getElementById("casteId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if (casteCategoryId.trim() == "" || casteCategoryId.trim()==null) {

			$(".errormessagediv").show();

			$(".validateTips").text("Field Required - Caste Category");
			$("#religionNameId").focus();
			document.getElementById("casteCategoryNameId").style.border = "1px solid #FF0000";
			document.getElementById("casteCategoryNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);

			return false;
		}else if(!casteCategoryId.match(/^[a-zA-Z0-9-&.(\][)\s]*$/)) {

			$(".errormessagediv").show();
			$(".validateTips").text("Special Characters are not allowed in Cast Category!");
			$("#casteCategoryId").focus();
			document.getElementById("casteCategoryNameId").style.border = "1px solid #AF2C2C";
			document.getElementById("casteCategoryNameId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false; 
		}
		else{

			datalist = {
					"casteId" : casteId,
					"religionId":religionId,
					"casteCategoryId" : casteCategoryId,
					"castecateVal":castecateVal,
					"hiddencastecategory":hiddencastecategory
			};

			$.ajax({
				type : 'POST',
				url : "religionCastCasteCategory.html?method=insertCasteCategory",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					////alert("response"+result.jsonResponse);

					if(result.jsonResponse=="Record added successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record Progressing...");
						setTimeout(function(){
							window.location.href="menuslist.html?method=casteCategoryDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);

					}
					if(result.jsonResponse=="Failed to add record, try again..."){
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						
						$(".validateTips").text("Failed to add record, try again...");
						setTimeout(function(){
							window.location.href="menuslist.html?method=casteCategoryDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}

					if(result.jsonResponse=="Caste Category Updated Successfully"){

						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Update Caste Category progressing...");
						setTimeout(function(){
							window.location.href="menuslist.html?method=casteCategoryDetails&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}	
					if(result.jsonResponse=="Caste Category Already Exist"){
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Caste Category Already exists in this Religion & Caste");
						$("#religion").focus();
						document.getElementById("religion").style.border = "1px solid #AF2C2C";
						document.getElementById("religion").style.backgroundColor = "#FFF7F7";
						
						/*setTimeout(function(){
							window.location.href = "menuslist.html?method=casteCategoryDetails";
					    },3000);*/
					} 
					
					if(result.jsonResponse=="inactive"){
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$(".validateTips").text("This Caste Category Already exists!! Make it Active");
						$("#religion").focus();
						document.getElementById("casteCategoryNameId").style.border = "1px solid #AF2C2C";
						document.getElementById("casteCategoryNameId").style.backgroundColor = "#FFF7F7";
						
						/*setTimeout(function(){
							window.location.href = "menuslist.html?method=casteCategoryDetails";
					    },3000);*/
					}
					
				}
			});
		}
	});

});

/*function getCasteDetails(){
	var religionId=$('#religion').val();

	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append(
					'<option value="' + "" + '">' + "---------Select----------"	+ '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}


		}
	});
}*/


function getReligionDetails(){
	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getReligionForDropDown",
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#religion').empty();
			$('#religion').append('<option value="' + "" + '">'	+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#religion').append(
						'<option value="'
						+ result.jsonResponse[j].religionId
						+ '">'
						+ result.jsonResponse[j].religion
						+ '</option>');
			}


		}
	});
}

function getCasteDetails(){
	var religionId = null;
	if (($('#hiddenreligionId').val() != "" && $('#hiddenreligionId').val() != null && $("#religion").val() =="" )) {
		religionId = $("#hiddenreligionId").val();
	}else if($('#hiddenreligionId').val() == $("#religion").val()){
		religionId = $("#hiddenreligionId").val();
	}else {
		religionId = $('#religion').val();
	}
	
	var data = {
			"religionId" : religionId
	};

	$.ajax({
		type : 'POST',
		url : "religionCastCasteCategory.html?method=getCasteForDropDown",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#casteId').empty();
			$('#casteId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#casteId').append(
						'<option value="'
						+ result.jsonResponse[j].casteId
						+ '">'
						+ result.jsonResponse[j].caste
						+ '</option>');
			}
			$('#casteCategoryId').empty();
			$('#casteCategoryId').append('<option value="' + "" + '">'+ "----------Select----------"+ '</option>');
		}
	});
}

function validateCasteCategory(){
	var streamname_validate=0;
	  
	var religionId=$("#religion").val();
 	var casteId=$("#casteId").val();
 	var castecategoryId=$("#casteCategoryNameId").val();
 	
	var casteObject = {
		"religionId":religionId,
		"casteId" : casteId,
		"castecategoryId":castecategoryId
	};

$.ajax({
	type : "POST",
	url : 'religionCastCasteCategory.html?method=validateCasteCategory',
	data : casteObject,
	async : false,
	success : function(data) {
		var result = $.parseJSON(data);
		 
		if (result.status==1) 
	    {
			$("#religion").val($("#hiddenreligionId").val());
			getCasteDetails();
			$("#casteId").val($("#hiddencasteId").val());
			
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			 $(".validateTips").text("Caste Category Name already exist in this Religion!!");
			 setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 4000);
		}
		else if (result.status==10) 
	    {
			$("#religion").val($("#hiddenreligionId").val());
			getCasteDetails();
			$("#casteId").val($("#hiddencasteId").val());
			
			$(".errormessagediv").show();
			$(".successmessagediv").hide();
			 $(".validateTips").text("Caste Category Name already exist in this Religion!! Make it Active");
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
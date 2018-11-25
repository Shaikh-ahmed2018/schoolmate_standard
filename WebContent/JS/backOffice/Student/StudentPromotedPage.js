$(document).ready(function() {
	
	var promotedClass=$("#promotedClass").val();
	var LocationId=$("#hiddenLocationId").val();
	var hiddenSectionId=$("#hiddenSectiontoId").val();
	if(promotedClass != ""){
	
		getNewClassList();
		$("#newclass option[value='"+ promotedClass +"']").attr('selected', 'true');
		getSectionList();
		$("#newdivisionId option[value='"+ hiddenSectionId +"']").attr('selected', 'true');
		getClassSpecilization($("#newclass").val(),$("#hiddenLocationId").val());
	}
	$('#newclass').change(function(){
		getSectionList()
	});
	

	
	var StudentImage=$("#hiddenPhotoId").val().trim();
	if(StudentImage!=""){

		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
	
	$('#statusId').css("background-color", "green");
	
	$('#statusId').change(function() {
	    var selectedItem = $(this).find("option:selected");
	    $(this).css('backgroundColor', selectedItem.css('backgroundColor'));
	    
	    
	    if($('#statusId').val()=="demoted"){
	    	$('#promotedClassf').hide();
	    }else{
	    	 
	    	var value1=$('#hiddenClassId').val();
	    	var value2=value1.substring(0, 3);
	    	var num=parseInt(value1.substring(3, 4))+1;
	    	
	    	$("#newclass").val(value2+num);
	    	getSectionList();
	    	$('#promotedClassf').show();
	    }
	});
	
	$("#allstudent tbody tr").click(function(){

		window.location.href="menuslist.html?method=studentWithheld";

	});
	

	var studentstatus=$('#hiddenPromotedStatus').val();
	
	if(studentstatus == 'demoted'){
		$('#promotedClassf').hide();
		$('#newsectiondivid').hide();
		$('#newspecdivid').hide();
		$('#statusId').css({'background-color':'#f00'});
	}else{
		$('#promotedClassf').show();
		$('#newsectiondivid').show();
		$('#newspecdivid').show();
		$('#statusId').css({'background-color':'rgb(0, 128, 0)'});
	}
	
	$('#statusId').change(function(){
		var studentstatus=$('#statusId').val();
		if(studentstatus == 'demoted'){
			$('#newsectiondivid').hide();
			$('#newspecdivid').hide();
		}else{
			$('#newsectiondivid').show();
			$('#newspecdivid').show();
		}
	});
	
	if($('#hiddenPromotedStatus').val() != null || $('#hiddenPromotedStatus').val() != ""){
		$("#statusId option[value="+$('#hiddenPromotedStatus').val()+"]").attr('selected', 'true').css('backgroundColor');
	}
	
	if($('#hiddenClassId').val() != null || $('#hiddenClassId').val() != ""){
		var splitvalue=0;
		var columnValue = $('#hiddenClassId').val();
		var locationId=$("#hiddenLocationId").val();
		
		//getClassSection(columnValue, locationId);
		
	}
	
	$("#newdivisionId").change(function(){
		 
		var columnValue = $('#hiddenClassId').val();
		var locationId=$("#hiddenLocationId").val();
		getClassSpecilization(columnValue,locationId);
	});
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=NewstudentPromotionList&historyaccYear="+$("#historyaccYear").val()
		+"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()
		+"&historysectionid="+$("#historysectionid").val()+"&historysearchBy="+$("#historysearchBy").val()
		+"&tabstatus="+$("#hiddentabstatus").val()
		+"&promaccYear="+$("#promaccYear").val()+"&promlocId="+$("#promlocId").val()+"&promclassname="+$("#promclassname").val()
		+"&promsectionid="+$("#promsectionid").val()+"&prosearch="+$("#prosearch").val()+"&demoaccYear="+$("#demoaccYear").val()+"&demolocId="+$("#demolocId").val()+"&democlassname="+$("#democlassname").val()
		+"&demosectionid="+$("#demosectionid").val()+"&demosearch="+$("#demosearch").val();
	});


	$("#save").click(function(){
		var studentId=$('#studentid').val().trim();
		var academicyear_fromid=$('#hiddenFromAccyearId').val().trim();
		var locationId=$('#hiddenLocationId').val().trim();
		var status=$('#statusId').val().trim();
		var newclass=$("#newclass").val().trim();
		var newspec = $('#newspecilaizationId').val().trim();
		var promoted = $('#hiddenPromotedId').val().trim();
		var comments=$("#comments").val().trim();
		var newsection=$("#newdivisionId").val();
		
		if(status == 'promoted'){
			var datalist = {
					"studentId": studentId,
					"accyear": academicyear_fromid,
					"locationId": locationId,
					"status": status,
					"newsection": newsection,
					"newspec": newspec,
					"promotedId": promoted,
					"comments": comments,
					"newclass": newclass,
			};
			if(newsection != "" ){
				$.ajax({
					type : 'POST',
					url : 'studentRegistration.html?method=updateStudentPromotion',
					data : datalist,
					beforeSend: function() {
						$('#loader').show();
					},
					success:function(response){
						var result = $.parseJSON(response);
						$('#loader').hide();
						if(result.expense_Result == "success"){
							$('.successmessagediv').show();
							$('.validateTips').css({
								'visibility' : 'visible',
							});
							$('.validateTips').text("Updating Record Progressing...");
							setTimeout(function () {
								window.location.href="menuslist.html?method=NewstudentPromotionList&historyaccYear="+$("#historyaccYear").val()
								+"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()
								+"&historysectionid="+$("#historysectionid").val()+"&historysearchBy="+$("#historysearchBy").val()
								+"&tabstatus="+$("#hiddentabstatus").val()
								+"&promaccYear="+$("#promaccYear").val()+"&promlocId="+$("#promlocId").val()+"&promclassname="+$("#promclassname").val()
								+"&promsectionid="+$("#promsectionid").val()+"&prosearch="+$("#prosearch").val()+"&demoaccYear="+$("#demoaccYear").val()+"&demolocId="+$("#demolocId").val()+"&democlassname="+$("#democlassname").val()
								+"&demosectionid="+$("#demosectionid").val()+"&demosearch="+$("#demosearch").val();
							}, 3000);
						}
						else{
							$('.errormessagediv').show();
							$('.validateTips1').css({
								'visibility' : 'visible'
							});
							$('.validateTips1').text("Please try again, after some time");
							$('.errormessagediv').delay(3000).slideUp("slow");
						}	
					}
				});
			}else{
				$('.errormessagediv').show();
				$('.validateTips').css({
					'visibility' : 'visible'
				});
				$('.validateTips').text("Field Requeired New Division.");
				$('.errormessagediv').delay(3000).slideUp("slow");
			}
		}else{
			var newsection =$('#hiddenSectionId').val();
			var newclass=$("#hiddenClassId").val().trim();
			var datalist = {
					"studentId": studentId,
					"accyear": academicyear_fromid,
					"locationId": locationId,
					"status": status,
					"newsection": newsection,
					"newspec": newspec,
					"promotedId": promoted,
					"comments": comments,
					"newclass": newclass,
			};
			$.ajax({
				type : 'POST',
				url : 'studentRegistration.html?method=updateStudentPromotion',
				data : datalist,
				beforeSend: function() {
					$('#loader').show();
				},
				success:function(response){
					var result = $.parseJSON(response);
					$('#loader').hide();
					if(result.expense_Result == "success"){
						$('.successmessagediv').show();
						$('.validateTips').css({
							'visibility' : 'visible',
						});
						$('.validateTips').text("Updating Record Progressing...");
						setTimeout(function () {
							window.location.href="menuslist.html?method=NewstudentPromotionList&historyaccYear="+$("#historyaccYear").val()
							+"&historylocId="+$("#historylocId").val()+"&historyclassname="+$("#historyclassname").val()
							+"&historysectionid="+$("#historysectionid").val()+"&historysearchBy="+$("#historysearchBy").val()
							+"&tabstatus="+$("#hiddentabstatus").val()
							+"&promaccYear="+$("#promaccYear").val()+"&promlocId="+$("#promlocId").val()+"&promclassname="+$("#promclassname").val()
							+"&promsectionid="+$("#promsectionid").val()+"&prosearch="+$("#prosearch").val()+"&demoaccYear="+$("#demoaccYear").val()+"&demolocId="+$("#demolocId").val()+"&democlassname="+$("#democlassname").val()
							+"&demosectionid="+$("#demosectionid").val()+"&demosearch="+$("#demosearch").val();
						}, 3000);
					}
					else{
						$('.errormessagediv').show();
						$('.validateTips1').css({
							'visibility' : 'visible'
						});
						$('.validateTips1').text("Please try again, after some time");
						$('.errormessagediv').delay(3000).slideUp("slow");
					}	
				}
			});
		}
		
		
		
	});
	
	
});

/*function getClassSection(columnValue,locationId) {
	
	var datalist = {
		"classidVal" : columnValue,
		"locationId":locationId,
	}; 
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newdivisionId').empty();
			$('#newdivisionId').append('<option value="' + "" + '">' + "----------Select----------" + '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#newdivisionId').append(
						'<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
			$("#newdivisionId option[value='"+$('#hiddenSectiontoId').val()+"']").attr('selected', true);
		}
	});

}*/

function getClassSpecilization(columnValue,locationId){
 
	var data = {
			"classId" : columnValue,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newspecilaizationId').empty();
			$('#newspecilaizationId').append('<option value="' + "-" + '">'+ "----------Select----------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#newspecilaizationId').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}
			$("#newspecilaizationId").val($("#hiddenSpecilizationId").val());
		}
	});
}

function toCheckNextClassAvailable(toClass,locationId){
	var flag = true;
	var data = {
			"toClass" : toClass,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=toCheckNextClassAvailable",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.checkClass == "notpresent"){
				$('.errormessagediv').show();
				$('.validateTips1').css({
					'visibility' : 'visible'
				});
				$('.validateTips1').text("The Promotion of Next Class doesn't Exits In Current Location");
				$('.errormessagediv').delay(3000).slideUp("slow");
				
				flag=false; 
			}else{
				flag=true;
			}
		}
	});
	return flag;
}
function getNewClassList(){
	var classId=$("#hiddenClassId").val();
	var locationId=$('#hiddenLocationId').val().trim();

	
	var data = {
			"classId" : classId,
			"locationId":locationId,
	};
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getNewClassList",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newclass').empty();
			$('#newclass').append('<option value="' + "" + '">'+ "----------Select----------" + '</option>');
               
			for ( var j = 0; j < result.newClassList.length; j++) {
				$('#newclass').append(
						'<option value="'
						+ result.newClassList[j].classcode
						+ '">'
						+ result.newClassList[j].classname
						+ '</option>');
			}


		}
	
	});
}

function getSectionList(){
	var classname=$("#newclass").val();
	var locationId=$('#hiddenLocationId').val().trim();
	datalist={
			"classidVal" : classname,
			"locationId":locationId
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#newdivisionId').html("");
			$('#newdivisionId').append('<option value="all">' + "----------Select----------"	+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#newdivisionId').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}


$(document).ready(function(){	
	
	$(".tolalListField ul li").click(function(){
		
		var num=$(this).attr("id");
		
		if(($("#selected"+$(this).attr("id"))).length >0){
			$("input[name='"+$(this).attr("id")+"']").remove();
			$("#selected"+$(this).attr("id")).remove();
			$(this).css({
				"background-color":"#FEFEFE"
			});
		}
		else{
			
			$(".SelectedListField ul").append("<li id=selected"+$(this).attr('id')+">"+$(this).text()+"</li>");
		
			$(".SelectedListField").append("<input type='hidden' name='"+$(this).attr('id')+"' value='"+$(this).attr('id')+"' />");
			$(this).css({
				"background-color":"#C7C7C7"
			});
		}
		
	});
	$("#location").val($("#hiddenlocId").val());
	
	$("#accyear").val($("#hacademicyaer").val());
	$("#allstudent").hide();
	$(".selecteditems").hide();
	$("#genderName").hide();
	$("#gender").hide();

	if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){

		$(".selecteditems").show(1000);
		$("#allstudent").show(1000);
		$("#txtstyle, #txtstyle").slideToggle();
	}
	
	getClassList();
	$("#location").change(function(){
		getClassList();
		getSection();
	});
	
	$("#selection").change(function() {
		if($("#selection").val() == 'stulistgenwise'){
			$("#genderName").show();
			$("#gender").show();
		}else{
			$("#genderName").hide();
			$("#gender").hide();
		}
		

	});
	
	

	

	$("#class").change(function(){
		getSection();
	});
	
	$("#downloadexcell").click(function(){
		var formValueArray=[];
		var labelValueArray=[];
		$(".SelectedListField input").each(function(){
			if($(this).val()=="student_rollno"){
				formValueArray.push("CASE WHEN cs.student_rollno IS NULL THEN '-' WHEN cs.student_rollno='' THEN '-' ELSE cs.student_rollno END student_rollno");
			}
			else if($(this).val()=="Specialization_name"){
				formValueArray.push("CASE WHEN csp.isActive='N' THEN '-' WHEN csp.Specialization_name IS NULL THEN '-' WHEN csp.Specialization_name='' THEN '-' ELSE csp.Specialization_name END Specialization_name");
			}
			else if($(this).val()=="housename"){
				formValueArray.push("CASE WHEN chs.status='active' THEN '-' WHEN chs.housename IS NULL THEN '-' WHEN chs.housename='' THEN '-' ELSE chs.housename END housename");
			}
			else if($(this).val()=="student_bloodgroup_var"){
				formValueArray.push("CASE WHEN student_bloodgroup_var IS NULL THEN '-' WHEN student_bloodgroup_var='' THEN '-' ELSE student_bloodgroup_var END student_bloodgroup_var");
			}
			else if($(this).val()=="adharNo"){
				formValueArray.push("CASE WHEN adharNo IS NULL THEN '-' WHEN adharNo='' THEN '-' ELSE adharNo END adharNo");
			}
			else if($(this).val()=="smsNo"){
				formValueArray.push("CASE WHEN smsNo IS NULL THEN '-' WHEN smsNo='' THEN '-' ELSE smsNo END smsNo");
			}
			else if($(this).val()=="emergencyNo"){
				formValueArray.push("CASE WHEN emergencyNo IS NULL THEN '-' WHEN emergencyNo='' THEN '-' ELSE emergencyNo END emergencyNo");
			}
			else if($(this).val()=="casteCategory"){
				formValueArray.push("CASE WHEN cct.casteCategory IS NULL THEN '-' WHEN cct.casteCategory='' THEN '-' ELSE cct.casteCategory END casteCategory");
			}
			else if($(this).val()=="student_identificationmarks_var"){
				formValueArray.push("CASE WHEN student_identificationmarks_var IS NULL THEN '-' WHEN student_identificationmarks_var='' THEN '-' ELSE student_identificationmarks_var END student_identificationmarks_var");
			}
			else if($(this).val()=="student_identificationmarks1_var"){
				formValueArray.push("CASE WHEN student_identificationmarks1_var IS NULL THEN '-' WHEN student_identificationmarks1_var='' THEN '-' ELSE student_identificationmarks1_var END student_identificationmarks1_var");
			}
			else if($(this).val()=="fatherOfficeAddress"){
				formValueArray.push("CASE WHEN fatherOfficeAddress IS NULL THEN '-' WHEN fatherOfficeAddress='' THEN '-' ELSE fatherOfficeAddress END fatherOfficeAddress");
			} 
			else if($(this).val()=="fatherAnnualIncome"){
				formValueArray.push("CASE WHEN fatherAnnualIncome IS NULL THEN '-' WHEN fatherAnnualIncome='' THEN '-' ELSE fatherAnnualIncome END fatherAnnualIncome");
			}
			else if($(this).val()=="Qualification"){
				formValueArray.push("CASE WHEN Qualification IS NULL THEN '-' WHEN Qualification='' THEN '-' ELSE Qualification END Qualification");
			}
			else if($(this).val()=="email"){
				formValueArray.push("CASE WHEN email IS NULL THEN '-' WHEN email='' THEN '-' ELSE email END email");
			}
			else if($(this).val()=="motherOfficeAddress"){
				formValueArray.push("CASE WHEN motherOfficeAddress IS NULL THEN '-' WHEN motherOfficeAddress='' THEN '-' ELSE motherOfficeAddress END motherOfficeAddress");
			} 
			else if($(this).val()=="motherAnnualIncome"){
				formValueArray.push("CASE WHEN motherAnnualIncome IS NULL THEN '-' WHEN motherAnnualIncome='' THEN '-' ELSE motherAnnualIncome END motherAnnualIncome");
			}
			else if($(this).val()=="student_mother_mailid"){
				formValueArray.push("CASE WHEN student_mother_mailid IS NULL THEN '-' WHEN student_mother_mailid='' THEN '-' ELSE student_mother_mailid END student_mother_mailid");
			}
			else if($(this).val()=="student_motherqualification_var"){
				formValueArray.push("CASE WHEN student_motherqualification_var IS NULL THEN '-' WHEN student_motherqualification_var='' THEN '-' ELSE student_motherqualification_var END student_motherqualification_var");
			}
			else if($(this).val()=="student_gaurdianname_var"){
				formValueArray.push("CASE WHEN student_gaurdianname_var IS NULL THEN '-' WHEN student_gaurdianname_var='' THEN '-' ELSE student_gaurdianname_var END student_gaurdianname_var");
			}
			else if($(this).val()=="student_gardian_mobileno"){
				formValueArray.push("CASE WHEN student_gardian_mobileno IS NULL THEN '-' WHEN student_gardian_mobileno='' THEN '-' ELSE student_gardian_mobileno END student_gardian_mobileno");
			}
			else if($(this).val()=="guardianOfficeAddress"){
				formValueArray.push("CASE WHEN guardianOfficeAddress IS NULL THEN '-' WHEN guardianOfficeAddress='' THEN '-' ELSE guardianOfficeAddress END guardianOfficeAddress");
			}
			else if($(this).val()=="student_gardian_mailid"){
				formValueArray.push("CASE WHEN student_gardian_mailid IS NULL THEN '-' WHEN student_gardian_mailid='' THEN '-' ELSE student_gardian_mailid END student_gardian_mailid");
			}
			else if($(this).val()=="guardianQualification"){
				formValueArray.push("CASE WHEN guardianQualification IS NULL THEN '-' WHEN guardianQualification='' THEN '-' ELSE guardianQualification END guardianQualification");
			}
			else
			formValueArray.push($(this).val());
		});
		$(".SelectedListField li").each(function(){
			labelValueArray.push($(this).text());
		});
		if($(".SelectedListField ul").length > 0){
			$.ajax({

				type : 'POST',
				url : "reportaction.html?method=CustomizableStudentReportsExcell",
				data : {"formValueArray":formValueArray.toString(),
						"labelValueArray":labelValueArray.toString(),
						"location":$("#location").val(),
						"accyear":$("#accyear").val(),
						"class":$("#class").val(),
						"section":$("#section").val()
				},
				
				success : function(response) {
					
					window.location.href="reportaction.html?method=CustomizableStudentReportsExcellDownload";
				}
			});
		}
		else{
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any Field to Generate Report");
			$(".errormessagediv").delay(2000).fadeOut("slow");
			
		}

	});

});

function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}




function getStudentsStrength(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsStrength",
		data : datalist,

		success : function(data) {
			var result = $.parseJSON(data);
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'>"+"<tr><th>Class</th>"+
					"<th>Div</th>" +
					"<th>Girls</th>"+
					"<th>Boys</th>" +
					"<th>Total</th>" +
					"<th></th>" +
					"</tr>" +
					"</table>"
			);
			if(result.studentsStrength.length>0){
			for(var i=0;i<result.studentsStrength.length;i++){

				$("#studentlisttable #allstudent").append(
						"<tr>"+
						"<td>"+result.studentsStrength[i].classname+"</td>"+
						"<td>"+result.studentsStrength[i].sectionname+"</td>"+
						"<td>"+result.studentsStrength[i].girls+"</td>"+
						"<td>"+result.studentsStrength[i].boys+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInDiv+"</td>"+
						"<td>"+result.studentsStrength[i].totalStudentsInCls+"</td>"+
						+"</tr>"

				);

			}
		}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
			}
			
		}

	});
}

function getSection(){
	var classId=$("#class").val();
	var locationId=$("#location").val();
	$.ajax({
		type : "GET",
		url : "reportaction.html?method=getSectionByClass",
		data : {"classId":classId,
				"location":locationId},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="all">' + "ALL"	+ '</option>');

			for (var j = 0; j < result.SectionList.length; j++) {

				$("#section").append(
						'<option value="'
						+ result.SectionList[j].sectionId
						+ '">'
						+ result.SectionList[j].sectionname
						+ '</option>');

			}
		}
	});
}


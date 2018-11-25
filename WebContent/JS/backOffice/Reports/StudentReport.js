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

	
		$(".reset").click(function(){
			$("#class").val("all");
			$("#section").val("all");
			$("#selection").val("all");
			$("#status").val("active");
			$("#accyear").val($("#hacademicyaer").val());
			$("#location").val($("#hiddenlocId").val());
			$("#studentlisttable").empty();
			$(".pagebar").hide();
		});
	
	$("#accyear").val($("#hacademicyaer").val());
	$("#location").val($("#hiddenlocId").val());
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
		$("#class").val("");
		$("#section").val("");
		$("#selection").val("");


		if($("#location").val()=="all"){
			$("#class").val("");
			$("#section").val("");
			$("#selection").val("");


		}

	});

	$("#selection").change(function() {
		
		if($("#selection").val() == 'stulistgenwise'){
			$("#genderName").show();
			$("#gender").show();
		}else{
			$("#genderName").hide();
			$("#gender").hide();
		}
		if($("#selection").val()=="newtempadmlist"){
			$("#section option[value='all']").attr('selected', 'true');
			$('#section').prop('disabled', true);
			$('#status').prop('disabled', true);
		}else if($("#selection").val() == 'stulisthousewise'){
			$('#status').prop('disabled', true);
		}
		else{
			$('#section').prop('disabled', false);
			$('#status').prop('disabled', false);
		}

	});

	$("#search").click(function(){

		var accyear=$("#accyear").val();
		var selection=$("#selection").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var location =$("#location").val();
		var status =$("#status").val();
		
		if(accyear =="all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Academic Year");
			document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
			document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}

		else if(location =="" || location =="all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Branch Name");
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}

		  else if(classId == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Class");
			document.getElementById("class").style.border = "1px solid #AF2C2C";
			document.getElementById("class").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}

		/*  else if(section == "all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Division");
			document.getElementById("section").style.border = "1px solid #AF2C2C";
			document.getElementById("section").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}*/

		else if(selection =="all" || selection==""){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Report Type");
			document.getElementById("selection").style.border = "1px solid #AF2C2C";
			document.getElementById("selection").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}

		else if(selection == 'studob'){
			getstudentDOBWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == 'stulisthousewise'){
			getstudentsHouseWise(accyear,selection,classId,section,location);
		}
		else if(selection == "stufatheroccu"){
			getstudentfatheroccu(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stumotheroccu"){
			getstudentMotherOccuWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "sturelig"){
			getstudentDetailsReligionWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stulcatwise"){
			getstudentCategoryWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stulistadmission"){
			getstudentAdmissionWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stulistrollwise"){
			getstudentRollNoWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stulistalpha"){
			getstudentAlphaWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stuparlist"){
			getstudentParentWise(accyear,selection,classId,section,location,status);
		}

		else if(selection == "stulist"){
			getstudentList(accyear,selection,classId,section,location,status);
		}

		else if(selection == "stustanwise"){
			getstudentStandardWise(accyear,selection,classId,section,location,status);
		}
		else if(selection == "stuconde"){
			getstudentContactDetails(accyear,selection,classId,section,location,status);
		}

		else if(selection == "studept"){
			getstudentDepartmentList(accyear,selection,classId,section,location,status);
		}

		else if(selection == "busroutewise"){
			getstudentBusRouteWise(accyear,selection,classId,section,location);
		}

		else if(selection == "stuoptsub"){
			getstudentOptionalSubjectDetails(accyear,selection,classId,section,location);
		}

		else if(selection == "stuphonnum"){
			getstudentsWithPhoneNumbers(accyear,selection,classId,section,location,status);
		}

		else if(selection == "oldstulist"){
			getOldStudentsList(accyear,selection,classId,section,location);
		}

		else if(selection == "stustrngth"){
			getStudentsStrength(accyear,selection,classId,section,location);
		}

		else if(selection == "newadmlist"){
			getStudentsNewAdmissionList(accyear,selection,classId,section,location,status);
		}

		else if(selection == "newtempadmlist"){
			getStudentsTempNewAdmissionList(accyear,selection,classId,section,location);
		}
		else if(selection == "prmlist"){
			getStudentPromotionList(accyear,selection,classId,section,location,status);
		}

		else if(selection == "stulistgenwise"){
			$("#genderName").show();
			$("#gender").show();
			var gender =$("#gender").val();
			if(gender == "all"){
				$(".errormessagediv").show();
				$(".validateTips").text("Select Gender Type");
				document.getElementById("abbreviate").style.border = "1px solid #AF2C2C";
				document.getElementById("abbreviate").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
			else{
				getStudentListGenderWise(accyear,selection,classId,section,location,gender,status);
			}
		}

	});
	
	

	$("#iconsimg").click(function(){
		if($("#location").val()=="" || $("#location").val()=="all"|| $("#location").val()==undefined){
			showError("#location","Select Branch Name");
			return false;
		}

		if($("#accyear").val()=="" ||$("#accyear").val()=="all"|| $("#accyear").val()==undefined){
			showError("#accyear","Select academicYear");
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

	$("#excelDownload").click(function(){

		var selection = $("#selection").val();
		var report = "excel";
		if(selection == "stulisthousewise"){
			window.location.href ="reportaction.html?method=studentHouseWiseExcelReportInternally";
		}
		if(selection == "studob"){
			window.location.href = 'reportaction.html?method=getstudentDOBWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stufatheroccu"){
			window.location.href = 'reportaction.html?method=getstudentFatherOccuWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stumotheroccu"){
			window.location.href = 'reportaction.html?method=getstudentMotherOccuWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "sturelig"){
			window.location.href = 'reportaction.html?method=studentReligionWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "stulcatwise"){
			window.location.href = 'reportaction.html?method=getstudentCategoryWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stulist"){
			window.location.href = 'reportaction.html?method=getstudentListXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "stuparlist"){
			window.location.href = 'reportaction.html?method=getstudentParentWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "stustanwise"){
			window.location.href = 'reportaction.html?method=getstudentStandardWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "stuconde"){
			window.location.href = 'reportaction.html?method=getstudentContactDetailsXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text(); 
		}

		else if(selection == "studept"){
			window.location.href = 'reportaction.html?method=getstudentDepartmentListXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "busroutewise"){
			window.location.href = 'reportaction.html?method=getstudentBusRouteWiseXL&locId='+$("#location").val();
		}

		else if(selection == "stuoptsub"){
			window.location.href = 'reportaction.html?method=getstudentOptionalSubjectDetailsXL&locId='+$("#location").val();
		}

		else if(selection == "stuphonnum"){
			window.location.href = 'reportaction.html?method=getstudentWithPhoneNumbersXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "oldstulist"){
			window.location.href = 'reportaction.html?method=getOLdStudentsListXL&locId='+$("#location").val();
		}

		else if(selection == "stustrngth"){
			window.location.href = 'reportaction.html?method=getStudentsStrengthXL&locId='+$("#location").val();
		}

		else if(selection == "newadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsNewAdmissionListXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "newtempadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsTempNewAdmissionListXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text();
		}
		else if(selection == "prmlist"){
			window.location.href = 'reportaction.html?method=getStudentPromotionListXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "stulistgenwise"){
			window.location.href = 'reportaction.html?method=getStudentListGenderWiseXL&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stulistrollwise"){
			window.location.href="reportaction.html?method=getstudentListRollNoWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}
		else if(selection == "stulistalpha"){
			window.location.href="reportaction.html?method=getstudentListAlphaWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}
		else if(selection == "stulistadmission"){
			window.location.href="reportaction.html?method=getstudentListAdmisNoWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}

	});

	$("#pdfDownload").click(function(){

		var selection = $("#selection").val();
		var report = "pdf";


		if(selection == "stulisthousewise"){
			window.location.href ="reportaction.html?method=studentHouseWisePDFReportInternally";
		}
		if(selection == "studob"){
			window.location.href ='reportaction.html?method=studentDOBWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stufatheroccu"){
			window.location.href ='reportaction.html?method=getstudentFatherOccuWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}
		else if(selection == "stumotheroccu"){
			window.location.href ='reportaction.html?method=getstudentMotherOccuWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}
		else if(selection == "sturelig"){
			window.location.href ='reportaction.html?method=studentReligionWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}
		else if(selection == "stulcatwise"){
			window.location.href ='reportaction.html?method=getstudentCategoryPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}
		else if(selection == "stulistrollwise"){
			window.location.href="reportaction.html?method=getstudentListRollNoWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}
		else if(selection == "stulistalpha"){
			window.location.href="reportaction.html?method=getstudentListAlphaWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}
		else if(selection == "stulistadmission"){
			window.location.href="reportaction.html?method=getstudentListAdmisNoWise&accyear="+$("#accyear").val()+'&locId='+$("#location").val()+
			"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+"&name="+$("#accyear option:selected").text()+"&locName="+$("#location option:selected").text()+
			"&classname="+$("#class option:selected").text()+"&sectionnname="+$("#section option:selected").text()+"&report="+report+"&status="+$("#status").val()+"&stustatus="+$("#status option:selected").text(); 
		}
		else if(selection == "stuparlist"){
			window.location.href = 'reportaction.html?method=getstudentParentWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}

		else if(selection == "stulist"){
			window.location.href = 'reportaction.html?method=getstudentListPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}

		else if(selection == "stustanwise"){
			window.location.href = 'reportaction.html?method=getstudentStandardWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}

		else if(selection == "stuconde"){
			window.location.href = 'reportaction.html?method=getstudentContactDetailsPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text(); 			  
		}
		else if(selection == "studept"){
			window.location.href = 'reportaction.html?method=getstudentDepartmentListPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();			  
		}

		else if(selection == "busroutewise"){
			window.location.href = 'reportaction.html?method=getstudentBusRouteWisePDFReport&locId='+$("#location").val();			  
		}

		else if(selection == "stuoptsub"){
			window.location.href = 'reportaction.html?method=getstudentOptionalSubjectDetailsPDFReport&locId='+$("#location").val();
		}

		else if(selection == "stuphonnum"){
			window.location.href = 'reportaction.html?method=getstudentWithPhoneNumbersPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection == "oldstulist"){
			window.location.href = 'reportaction.html?method=getOldStudentsListPDFReport&locId='+$("#location").val();
		}

		else if(selection == "stustrngth"){
			window.location.href = 'reportaction.html?method=getStudentsStrengthPDFReport&locId='+$("#location").val();
		}

		else if(selection == "newadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsNewAdmissionListPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();;
		}
		else if(selection == "newtempadmlist"){
			window.location.href = 'reportaction.html?method=getStudentsTempNewAdmissionListPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text();
		}

		else if(selection == "prmlist"){
			window.location.href = 'reportaction.html?method=getStudentPromotionListPDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}

		else if(selection =="stulistgenwise"){
			window.location.href ='reportaction.html?method=getStudentListGenderWisePDFReport&locId='+$("#location").val()+"&locationname="+$("#location option:selected").text()+"&status="+$("#status option:selected").text();
		}


	});

	$("#class").change(function(){
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
					$("#section").append('<option value="all">' +"ALL"+ '</option>');

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

			$('#class').append('<option value="all">'+ "----------Select----------"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getstudentsHouseWise(accyear,selection,classId,section,location){
	var status=$("#status").val();	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},
	$.ajax({
		type : "POST",
		url : "reportaction.html?method=getstudentsHouseWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table house' id='allstudent' width='100%'>"+"<thead><tr class='first'>"+
					"<th rowspan='2' style='padding: 17px 0px 16px 0px;'>S.No</th>" +
					"<th rowspan='2' style='padding: 17px 0px 16px 0px;'>Class-Division</th>"+
					"<th rowspan='2' style='padding: 17px 0px 16px 0px;'>Total</th>"+
					"<th rowspan='2' style='padding: 17px 0px 16px 0px;'>Not Allocated</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"

			);

			$("#studentlisttable thead .first").append("<th colspan='"+result.studentdobList[0].examMarkList.length+"'>House</th>");	
			$("#studentlisttable thead").append("<tr class='second'></tr>");
			for(var i=0;i<result.studentdobList[0].examMarkList.length;i++){
				$("#studentlisttable thead .second").append("<th>"+result.studentdobList[0].examMarkList[i].houseName+"</th>");
			}

			if(result.studentdobList.length>0){
				for(var j=0;j<result.studentdobList.length;j++){
					$("#allstudent tbody").append(
							"<tr class='"+(j+1)+"'>"+
							"<td>"+(j+1)+"</td>"+
							"<td style='text-align:center;'>"+result.studentdobList[j].class_and_section+"</td>"+
							"<td style='text-align:center;'>"+result.studentdobList[j].count+"</td>"+
							"<td style='text-align:center;'>"+result.studentdobList[j].noStudentcount+"</td>"+
							+"</tr>"
					);
					for(var k=0;k<result.studentdobList[j].examMarkList.length;k++){
						$("."+(j+1)).append("<td style='text-align:center;'>"+result.studentdobList[j].examMarkList[k].studentcount+"</td>");
					}
				}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='5'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentdobList.length);
			pagination(100);
		}
	});
}	
function getstudentDOBWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();	
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDOBWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>DOB</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentdobList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentdobList[i].count+"</td>"+
							"<td>"+result.studentdobList[i].admissionNo+"</td>"+
							"<td>"+result.studentdobList[i].studentRollNo+"</td>"+
							"<td>"+result.studentdobList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentdobList[i].dob+"</td>"
							+"</tr>"

					);
				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}
	});
}	

function getstudentfatheroccu(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentFatherOccuWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Father's Name</th>"+
					"<th>Father's Occupation</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentfatheroccuList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentfatheroccuList[i].count+"</td>"+
							"<td>"+result.studentfatheroccuList[i].admissionNo+"</td>"+
							"<td>"+result.studentfatheroccuList[i].studentRollNo+"</td>"+
							"<td>"+result.studentfatheroccuList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentfatheroccuList[i].fatherName+"</td>"+
							"<td>"+result.studentfatheroccuList[i].occupation+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentMotherOccuWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentMotherOccuWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Mother's Name</th>"+
					"<th>Mother's Occupation</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentmotheroccuList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentmotheroccuList[i].count+"</td>"+
							"<td>"+result.studentmotheroccuList[i].admissionNo+"</td>"+
							"<td>"+result.studentmotheroccuList[i].studentRollNo+"</td>"+
							"<td>"+result.studentmotheroccuList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentmotheroccuList[i].student_mothername_var+"</td>"+
							"<td>"+result.studentmotheroccuList[i].occupation+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentDetailsReligionWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDetailsReligionWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentReligionWiseList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentReligionWiseList[i].count+"</td>"+
							"<td>"+result.studentReligionWiseList[i].admissionNo+"</td>"+
							"<td>"+result.studentReligionWiseList[i].studentRollNo+"</td>"+
							"<td>"+result.studentReligionWiseList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentReligionWiseList[i].religion+"</td>"+
							"<td>"+result.studentReligionWiseList[i].caste+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentCategoryWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentCategoryWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"<th>Category</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentCategoryWiseList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentCategoryWiseList[i].count+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].admissionNo+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].studentRollNo+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].religion+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].caste+"</td>"+
							"<td>"+result.studentCategoryWiseList[i].casteCategory+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentParentWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentParentWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Father's Name</th>"+
					"<th>Mobile Number</th>"+
					"<th>Mother's Name</th>"+
					"<th>Mobile Number</th>"+
					"<th>Address</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentParentList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentParentList[i].count+"</td>"+
							"<td>"+result.studentParentList[i].admissionNo+"</td>"+
							"<td>"+result.studentParentList[i].studentRollNo+"</td>"+
							"<td>"+result.studentParentList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentParentList[i].fatherName+"</td>"+
							"<td>"+result.studentParentList[i].mobileno+"</td>"+
							"<td>"+result.studentParentList[i].student_mothername_var+"</td>"+
							"<td>"+result.studentParentList[i].student_mothermobileno_var+"</td>"+
							"<td>"+result.studentParentList[i].address+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);

		}

	});
}

function getstudentList(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.getstudentDetailsList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.getstudentDetailsList[i].count+"</td>"+
							"<td>"+result.getstudentDetailsList[i].admissionNo+"</td>"+
							"<td>"+result.getstudentDetailsList[i].studentRollNo+"</td>"+
							"<td>"+result.getstudentDetailsList[i].studentnamelabel+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentStandardWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentStandardWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Gender</th>"+
					"<th>Religion</th>"+
					"<th>Caste</th>"+
					"<th>Category</th>"+
					"<th>DOB</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentStandardList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentStandardList[i].count+"</td>"+
							"<td>"+result.studentStandardList[i].admissionNo+"</td>"+
							"<td>"+result.studentStandardList[i].studentRollNo+"</td>"+
							"<td>"+result.studentStandardList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentStandardList[i].student_gender_var+"</td>"+
							"<td>"+result.studentStandardList[i].religion+"</td>"+
							"<td>"+result.studentStandardList[i].caste+"</td>"+
							"<td>"+result.studentStandardList[i].casteCategory+"</td>"+
							"<td>"+result.studentStandardList[i].dob+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);

		}

	});
}	

function getstudentContactDetails(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentContactDetails",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},

		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>"+
					"<th>Name</th>"+
					"<th>Address</th>" +
					"<th>Father's Name</th>" +
					"<th>Mobile No </th>" +
					"<th>Mother's Name</th>" +
					"<th>Mobile No</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentContactDetails.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentContactDetails[i].count+"</td>"+
							"<td>"+result.studentContactDetails[i].admissionNo+"</td>"+
							"<td>"+result.studentContactDetails[i].studentRollNo+"</td>"+
							"<td>"+result.studentContactDetails[i].studentnamelabel+"</td>"+
							"<td>"+result.studentContactDetails[i].address+"</td>"+
							"<td>"+result.studentContactDetails[i].fatherName+"</td>"+
							"<td>"+result.studentContactDetails[i].mobileno+"</td>"+
							"<td>"+result.studentContactDetails[i].student_mothername_var+"</td>"+
							"<td>"+result.studentContactDetails[i].student_mothermobileno_var+"</td>"
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentDepartmentList(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentDepartmentList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},

		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Class</th>" +
					"<th>Division</th>" +
					"<th>Specialization</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentDepartmentList.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentDepartmentList[i].count+"</td>"+
							"<td>"+result.studentDepartmentList[i].admissionNo+"</td>"+
							"<td>"+result.studentDepartmentList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentDepartmentList[i].classname+"</td>"+
							"<td>"+result.studentDepartmentList[i].sectionname+"</td>"+
							"<td>"+result.studentDepartmentList[i].specializationName+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentBusRouteWise(accyear,selection,classId,section,location){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentBusRouteWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Class</th>" +
					"<th>Division</th>" +
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Bus Point Name</th>" +
					"<th>Amount</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentBusRouteWise.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentBusRouteWise[i].count+"</td>"+
							"<td>"+result.studentBusRouteWise[i].classname+"</td>"+
							"<td>"+result.studentBusRouteWise[i].sectionname+"</td>"+
							"<td>"+result.studentBusRouteWise[i].admissionNo+"</td>"+
							"<td>"+result.studentBusRouteWise[i].studentnamelabel+"</td>"+
							"<td>"+result.studentBusRouteWise[i].busStageName+"</td>"+
							"<td>"+result.studentBusRouteWise[i].stageAmount+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getstudentOptionalSubjectDetails(accyear,selection,classId,section,location){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentOptionalSubjectDetails",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Class</th>"+
					"<th>Second Language</th>" +
					"<th>Third Language</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentOptionalSubject.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<result.studentOptionalSubject.length;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentOptionalSubject[i].count+"</td>"+
							"<td>"+result.studentOptionalSubject[i].admissionNo+"</td>"+
							"<td>"+result.studentOptionalSubject[i].studentnamelabel+"</td>"+
							"<td>"+result.studentOptionalSubject[i].class_and_section+"</td>"+
							"<td>"+result.studentOptionalSubject[i].secondLanguage+"</td>"+
							"<td>"+result.studentOptionalSubject[i].thirdLanguage+"</td>"+
							+"</tr>"

					);
				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);

		}

	});
}

function getstudentsWithPhoneNumbers(accyear,selection,classId,section,location){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentsWithPhoneNumbers",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Roll No</th>" +
					"<th>Name</th>"+
					"<th>Father's Name</th>" +
					"<th>Father's Mobile No</th>" +
					"<th>Mother's Name</th>" +
					"<th>Mother's Mobile No</th>" +
					"<th>Guardian Name</th>" +
					"<th>Guardian Contact No</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentWithPhoneNumber.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentWithPhoneNumber[i].count+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].admissionNo+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].studentRollNo+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].studentnamelabel+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].fatherName+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].fatherMobileNo+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].student_mothername_var+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].student_mothermobileno_var+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].guardianName+"</td>"+
							"<td>"+result.studentWithPhoneNumber[i].guardianMobileNo+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='10'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getOldStudentsList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getOldStudentsList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Sl.No.</th>"+
					"<th>Admn No</th>" +
					"<th>Std & Div</th>" +
					"<th>Name</th>"+
					"<th>Gender</th>" +
					"<th>Tc No</th>" +
					"<th>Tc Date</th>" +
					"<th>Reason For Leaving</th>" +
					"<th>Last Attendance Date</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.oldStudentList.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.oldStudentList[i].count+"</td>"+
							"<td>"+result.oldStudentList[i].admissionNo+"</td>"+
							"<td>"+result.oldStudentList[i].studentnamelabel+"</td>"+
							"<td>"+result.oldStudentList[i].fatherName+"</td>"+
							"<td>"+result.oldStudentList[i].fatherMobileNo+"</td>"+
							"<td>"+result.oldStudentList[i].student_mothername_var+"</td>"+
							"<td>"+result.oldStudentList[i].student_mothermobileno_var+"</td>"+
							"<td>"+result.oldStudentList[i].guardianName+"</td>"+
							"<td>"+result.oldStudentList[i].guardianMobileNo+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}

			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
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
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>Class</th>"+
					"<th>Div</th>" +
					"<th>Girls</th>"+
					"<th>Boys</th>" +
					"<th>Total</th>" +
					"<th></th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentsStrength.length;
			var i=0;	
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
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
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getStudentsTempNewAdmissionList(accyear,selection,classId,section,location){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsTempNewAdmissionList1",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Parent's Name</th>" +
					"<th>Email Id</th>" +
					"<th>Mobile Number</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentNewAdmissionList.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentNewAdmissionList[i].count+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].classname+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].fatherName+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].email+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].mobileno+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}


function getStudentsNewAdmissionList(accyear,selection,classId,section,location,status){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":$("#status").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentsNewAdmissionList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Division</th>" +
					"<th>Date Of Admission</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentNewAdmissionList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentNewAdmissionList[i].count+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].admissionNo+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].classname+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].sectionname+"</td>"+
							"<td>"+result.studentNewAdmissionList[i].dateOfJoining+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);

		}

	});
}

function getStudentPromotionList(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentPromotionList",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Roll No</th>" +
					"<th>Name</th>" +
					"<th>Result</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentPromotionList.length;
			var i=0;
			
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentPromotionList[i].count+"</td>"+
							"<td>"+result.studentPromotionList[i].studentRollNo+"</td>"+
							"<td>"+result.studentPromotionList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentPromotionList[i].promotionStatus+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}

function getStudentListGenderWise(accyear,selection,classId,section,location,gender,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"gender":gender,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentListGenderWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Roll No</th>" +
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.studentListGenderWise.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.studentListGenderWise[i].count+"</td>"+
							"<td>"+result.studentListGenderWise[i].studentRollNo+"</td>"+
							"<td>"+result.studentListGenderWise[i].admissionNo+"</td>"+
							"<td>"+result.studentListGenderWise[i].studentnamelabel+"</td>"+
							+"</tr>"

					);

				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}

	});
}


function getstudentAdmissionWise(accyear,selection,classId,section,location,status){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":$("#status").val(),
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getStudentListAdmiWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.stuList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.stuList[i].sno+"</td>"+
							"<td>"+result.stuList[i].admissionNo+"</td>"+
							"<td>"+result.stuList[i].studentnamelabel+"</td>"+
							"<td>"+result.stuList[i].studentRollNo+"</td>"+
							"<td>"+result.stuList[i].classname+"</td>"+
							"<td>"+result.stuList[i].sectionname+"</td>"+
							+"</tr>"
					);
				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}
	});
}

function getstudentRollNoWise(accyear,selection,classId,section,location,status){
	var status=$("#status").val();
	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentRollNoWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.stuList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.stuList[i].sno+"</td>"+
							"<td>"+result.stuList[i].admissionNo+"</td>"+
							"<td>"+result.stuList[i].studentnamelabel+"</td>"+
							"<td>"+result.stuList[i].studentRollNo+"</td>"+
							"<td>"+result.stuList[i].classname+"</td>"+
							"<td>"+result.stuList[i].sectionname+"</td>"+
							+"</tr>"
					);
				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}
	});
}
function getstudentAlphaWise(accyear,selection,classId,section,location,status){

	datalist = {
			"accyear" :accyear,
			"selection" :selection,
			"classId" :classId,
			"section" :section,
			"location" :location,
			"status":status,
	},

	$.ajax({

		type : "POST",
		url : "reportaction.html?method=getstudentAlphaWise",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(data) {
			var result = $.parseJSON(data);
			$("#loder").hide();
			$("#studentlisttable").empty();
			$("#studentlisttable").append("<table class='table' id='allstudent' width='100%'><thead>"+"<tr><th>SI.No</th>"+
					"<th>Admn No</th>" +
					"<th>Name</th>"+
					"<th>Roll No</th>" +
					"<th>Class</th>" +
					"<th>Division</th>" +
					"</tr>" +
					"</thead><tbody></tbody></table>"
			);
			$("#iconsimg").show();
			var len=result.stuList.length;
			var i=0;
			if(len>0){
				for(i=0;i<len;i++){

					$("#studentlisttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.stuList[i].sno+"</td>"+
							"<td>"+result.stuList[i].admissionNo+"</td>"+
							"<td>"+result.stuList[i].studentnamelabel+"</td>"+
							"<td>"+result.stuList[i].studentRollNo+"</td>"+
							"<td>"+result.stuList[i].classname+"</td>"+
							"<td>"+result.stuList[i].sectionname+"</td>"+
							+"</tr>"
					);
				}
			}
			else{
				$("#studentlisttable #allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
				$("#iconsimg").show();
			}
			$(".pagebar").show();$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);
			pagination(100);
		}
	});
}

/*function rollNoWiseDownload(){


}*/
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

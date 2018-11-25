$(document).ready(function(){	
	$("#accyear").val($("#hiddenaccyear").val());
	$(".pagebanner").hide();
	$(".pagelinks").hide();
	$("#reset").click(function(){
		$("#location").val("all");
		$("#accyear").val($("#hiddenaccyear").val());
		$("#class").val("all");
		$("#section").val("all");
		$("input[type=checkbox]").prop("checked",false);
		$("#allstudent tbody tr").empty();
		$(".pagebanner").hide();
		$(".pagelinks").hide();
	});
	
	$("#accyear").change(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		
		if(classId != "all"){
			getReportType(locationId,accyear,classId);
			
			if($("#reporttype").val() !="notfound" && $("#setup").val() !="notfound"){
				$("#termsid").show();
				if($("#reporttype").val() == "termbased"){
					$("#spanterms").show();
					$("#spanaccyear").hide();
				}else if($("#reporttype").val() == "academic"){
					$("#spanterms").hide();
					$("#spanaccyear").show();
				}
				$("#report").show();
				getStudentListBySection(locationId,accyear,classId,sectionid);
			}else{
				$("#report").hide();
				$("#termsid").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Report Card Setup not available for this Academic Year.");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
		}
	});
	getClassList();
	
	$("#location").change(function(){
		getClassList();
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		
		if(classId != "all"){
			getReportType(locationId,accyear,classId);
			
			if($("#reporttype").val() !="notfound" && $("#setup").val() !="notfound"){
				$("#termsid").show();
				if($("#reporttype").val() == "termbased"){
					$("#spanterms").show();
					$("#spanaccyear").hide();
				}else if($("#reporttype").val() == "academic"){
					$("#spanterms").hide();
					$("#spanaccyear").show();
				}
				$("#report").show();
				getStudentListBySection(locationId,accyear,classId,sectionid);
			}else{
				$("#report").hide();
				$("#termsid").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Report Card Setup not available for this Branch.");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
		}else{
			$("#section").html("");
			$("#section").append('<option value="all">' + "----------Select----------"	+ '</option>');
		}
	});
	
	$("#reset").click(function(){
		$('input[name="selectBox"]').prop('checked', false);
	});
	
	$("#class").change(function(){
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var classId=$("#class").val();
		var sectionid=$("#section").val();

		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId,
				"location":locationId},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);

					$("#section").html("");
					$("#section").append('<option value="all">' + "----------Select----------" + '</option>');

					for (var j = 0; j < result.SectionList.length; j++) {
						$("#section").append('<option value="'+ result.SectionList[j].sectionId + '">'
								+ result.SectionList[j].sectionname + '</option>');
					}
				}
		});
		getReportType(locationId,accyear,classId);
		
		if($("#reporttype").val() !="notfound" && $("#setup").val() !="notfound"){
			$("#termsid").show();
			if($("#reporttype").val() == "termbased"){
				$("#spanterms").show();
				$("#spanaccyear").hide();
			}else if($("#reporttype").val() == "academic"){
				$("#spanterms").hide();
				$("#spanaccyear").show();
			}
			$("#report").show();
			getStudentListBySection(locationId,accyear,classId,sectionid);
		}else{
			$("#report").hide();
			$("#termsid").hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Report Card Setup not available for this Class.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
	});
	
	$("#term1").click(function(){
		var classId=$("#class").val();
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		if($(this).is(':checked'))
		getTerm1ExamDetails(locationId,accyear,classId);
	});
	
	$("#term2").click(function(){
		var classId=$("#class").val();
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		if($(this).is(':checked'))
		getTerm2ExamDetails(locationId,accyear,classId);
	});
	
	$("#reportaccyear").click(function(){
		var classId=$("#class").val();
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		if($(this).is(':checked'))
		getAcademicExamDetails(locationId,accyear,classId);
	});
	
	$("#download").click(function(){
		var chkArray = [];
		var locationId=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var sectionId=$("#section").val();
		var examstypeid=$("#examstypeid").val();
		var examstypeidterm2=$("#examstypeidterm2").val();
		var examstypeidaccyear=$("#examstypeidaccyear").val();
		
		//var stdId=$('input[name="selectBox"]:checked').val();
		var stdId=[];
		var cnt=0;
		$(".stidentid:checked").each(function(){
			/*getData = $(this).attr("id");
			cnt++;*/
			stdId.push($(this).val());
			cnt ++;
		});
		
		if(stdId == undefined){
			stdId="";
		}
		var checkterm1=$("#term1:checked").val();
		if(checkterm1 == undefined){
			checkterm1="";
		}
		var checkterm2=$("#term2:checked").val();
		if(checkterm2 == undefined){
			checkterm2="";
		}
		var reportaccyear=$("#reportaccyear:checked").val();
		
		//var checkterm2=$("#term2").attr("checked",true).val();
		//var reportaccyear=$("#reportaccyear").attr("checked",true).val();
		
		var saveFlag=true;

		/* look for all checkboes that have a parent id called 'checkboxlist' attached to it and check if it was checked */
		$("#checkboxlist input:checked").each(function() {
			chkArray.push($(this).val());
		});
		// we join the array separated by the comma 
		
		/* check if there is selected checkboxes, by default the length is 1 as it contains one single comma */
		if(accyear  == null || accyear  == ""){
			
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Academic Year.");
			$("#academicYear").focus();
			document.getElementById("accyear").style.border = "1px solid #AF2C2C";
			document.getElementById("accyear").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("accyear").style.border = "1px solid #ccc";
				document.getElementById("accyear").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;

		}else if(locationId == 'all' || locationId  == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name.");
			$("#location").focus();
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("location").style.border = "1px solid #ccc";
				document.getElementById("location").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;
		}else if(classId == 'all' || classId  == ""){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Class Name.");
			$("#class").focus();
			document.getElementById("class").style.border = "1px solid #AF2C2C";
			document.getElementById("class").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				document.getElementById("class").style.border = "1px solid #ccc";
				document.getElementById("class").style.backgroundColor = "#fff";
			}, 500);
			saveFlag=false;
			return false;
		}else if(chkArray.length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Scholastic Areas.");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			saveFlag=false;
			return false;
			
		}else if(cnt==0){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Atleast One Student");
			$("#academicYear").focus();
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 500);
			saveFlag=false;
			return false;
		}
		else if(saveFlag){
			////alert(reportaccyear);
			if(reportaccyear != undefined){
				window.location.href = 'reportaction.html?method=downloadAcademicYearReportCard&classId='+classId+'&locationId='+locationId+'&accyearId='+accyear+'&terms='+reportaccyear+'&examstypeid='+examstypeidaccyear+'&stdId='+stdId+'&sectionId='+sectionId;
			}else{
				window.location.href = 'reportaction.html?method=downloadReportCard&classId='+classId+'&locationId='+locationId+'&accyearId='+accyear+'&terms1='+checkterm1+'&terms2='+checkterm2+'&examstypeid='+examstypeid+'&examstypeidterm2='+examstypeidterm2+'&stdId='+stdId+'&sectionId='+sectionId;
			}
		}
	});

	/*$("#class").change(function(){
		getTerm1Exams($("#accyear").val());
		getTerm2Exams($("#accyear").val());
		getFinalExams($("#accyear").val());
	});*/
	$("#section").change(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		getStudentListBySection(locationId,accyear,classId,sectionid);
	});
	$("#search").click(function(){
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		var sectionid=$("#section").val();
		getStudentListBySection(locationId,accyear,classId,sectionid);
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

			$('#class').append('<option value="all">' + "----------Select----------"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getTerm1Exams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm1Exams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#term1").val(result.examsid);
			$("#examstypeid").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
		}
	});

}

function getTerm2Exams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm2Exams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#term2").val(result.examsid);
			$("#examstypeidterm2").val(result.examstypeid);
			$("#examstypeprefixforterm2").val(result.examstypeprefix);
		}
	});

}

function getFinalExams(accyear){
	var classId=$("#class").val();
	var locationid=$("#location").val();
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationid,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getFinalExams",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#reportaccyear").val(result.examsid);
			$("#examstypeidaccyear").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
		}
	});

}

function getStudentListBySection(locationid,accyear,classname,sectionid){
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :"",
			"show_per_page":100,
			"startPoint":0,
			"status":"",
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=getStudentListBySectionReportCard",
		data : datalist,
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.getSectionWiseList.length>0){
				$(".pagebanner").show();
				$(".pagelinks").show();
				for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td> <input type='checkbox' name='selectBox' class='stidentid' value='"+result.getSectionWiseList[i].studentId+"'></td>" 
							+"<td class='"+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].classDetailId+" "+result.getSectionWiseList[i].sectioncode+" "+"studentid"+"'> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classname+" </td>"
							+"<td> "+result.getSectionWiseList[i].sectionnaem+" </td>"
							+"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
				//$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});
			}
			else{
				$("#allstudent").append("<tr><td colspan='6'>NO Records Found</td></tr>");
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  0");
			}
			select();
		}
	});
}

function select(){
	$(".selectAll").change(function() {
		$(".stidentid").prop('checked', $(this).prop("checked"));
	});
	
	$(".stidentid").change(function(){
		if($(".stidentid").length==$(".stidentid:checked").length){
			$(".selectAll").prop("checked",true);
		}
		else{
			$(".selectAll").prop("checked",false);
		}
	});
}

function getReportType(locationId,accyear,classId){
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationId,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getReportType",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#reporttype").val(result.reporttype);
			$("#setup").val(result.setup);
		}
	});
}

function getTerm1ExamDetails(locationId,accyear,classId){
	datalist={
		"accyear" : accyear,
		"classId" : classId,
		"locationid" : locationId,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm1ExamDetails",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#term1").val(result.examsid);
			$("#examstypeid").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
			
			if(Number(result.count) != 0){
				$("#term1").prop('checked',true);
			}else{
				$("#term1").prop('checked',false);
				
				$(".errormessagediv").show();
				$(".validateTips").text("Report Card Setup not available for this Term I");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
		}
	});
}

function getTerm2ExamDetails(locationId,accyear,classId){
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationId,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm2ExamDetails",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#term2").val(result.examsid);
			$("#examstypeidterm2").val(result.examstypeid);
			$("#examstypeprefixforterm2").val(result.examstypeprefix);
			
			if(Number(result.count) != 0){
				$("#term2").prop('checked',true);
			}else{
				$("#term2").prop('checked',false);
				$(".errormessagediv").show();
				$(".validateTips").text("Report Card Setup not available for this Term II");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
			}
		}
	});
}

function getAcademicExamDetails(locationId,accyear,classId){
	datalist={
			"accyear" : accyear,
			"classId" : classId,
			"locationid" : locationId,
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getAcademicExamDetails",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#reportaccyear").val(result.examsid);
			$("#examstypeidaccyear").val(result.examstypeid);
			$("#examstypeprefix").val(result.examstypeprefix);
			
			if(Number(result.count) != 0){
				$("#reportaccyear").prop('checked',true);
			}else{
				$("#reportaccyear").prop('checked',false);
				$(".errormessagediv").show();
				$(".validateTips").text("Report Card Setup not available for this Academic Year");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 2000);
				return false;
				
			}
		}
	});
}
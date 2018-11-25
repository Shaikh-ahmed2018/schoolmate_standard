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
	// for settings color js
		$("#accyear").val($("#hacademicyaer").val());
		$("#location").change(function(){
			getClassList();	
			$("#class").val("");
			$("#section").val("");
			$("#allstudent tbody").empty();
			$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+0);
			 pagination(100);
			
			if($("#location").val()=="all"){
				$("#class").val("");
				$("#section").val("");
				$("#allstudent tbody").empty();
				$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+0);
				pagination(100);
			}
			
			
			
		});
		
		$("#class").change(function(){
			getSectionList();
		});
		
				
		// for showing search destails
		
		if($("#hideenId").val()!="" && $("#hideenId").val()!=undefined ){
			
			$(".selecteditems").show(1000);
			$("#allstudent").show(1000);
			$("#txtstyle, #txtstyle").slideToggle();
			
		}
	
		if($("#location").val()=='all'){
			$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
		}
		
		$("#search").click(function(){
			
			var accyear=$("#accyear").val();
			var stream=$("#stream").val();
			var classId=$("#class").val();
			var section=$("#section").val();
			var Location=$("#location").val();
			
			if(accyear=="" && stream=="" && classId=="" && section==""){
				
				$("#txtstyle, #txtstyle").slideToggle();
				
				
			}
              if(Location==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Branch Name");
				
				return false;
				
			}
			
			
			if(accyear==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Academic Year");
				
				return false;
				
			}if(stream==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Stream");
				
				return false;
				
			}if(classId==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Class");
				
				return false;
				
			}if(section==""){
				
				$('.errormessagediv').show();
				$('.validateTips').text("Select Section");
				
				return false;
				
			}else{
			
				getstudentinActiveList();
			}
			
		});
		
		
		$("#iconsimg").click(function(){
			if($("#location").val()=="" || $("#location").val()=="all"|| $("#location").val()==undefined){
				showError("#location","Select Branch Name");
				return false;
			}
			else if($("#accyear").val()=="" || $("#accyear").val()=="all"|| $("#location").val()==undefined){
				showError("#accyear","Select academicYear");
				return false;
			}
			else if($("#class").val()=="" || $("#class").val()=="all"|| $("#class").val()==undefined){
				showError("#class","Select Class");
				return false;
			}
			else if($("#section").val()=="" || $("#section").val()=="all"|| $("#section").val()==undefined){
				showError("#section","Select Division");
				return false;
			}
			else if($("#allstudent tbody tr").length == 0){
				showError("#allstudent","No Record Found");
				return false;	
			}
		});
		
		$("#excelDownload").click(function(){
			 
          window.location.href ='reportaction.html?method=geInactivetStudentDetailExcelsReport&locationname='+$("#location option:selected").text()+'&locId='+$("#location").val()+
          '&accyear='+$("#accyear").val()+'&classId='+$("#class").val()+'&section='+$("#section").val();
		});
		
		$("#pdfDownload").click(function(){
				window.location.href ='reportaction.html?method=geInactivetStudentDetailPDFReport&accyear='+$("#accyear").val()+
				"&classId="+$("#class").val()+"&section="+$("#section").val()+"&location="+$("#location").val()+
				'&locationname='+$("#location option:selected").text();
		});

		
		$("#stream").change(function(){
			
			var streamId=$("#stream").val();
			
			$.ajax({
				type : "GET",
				url : "reportaction.html?method=getClassesByStream",
				data : {"streamId":streamId},
				async : false,
				success : function(data) {
					
					var result = $.parseJSON(data);
					$("#class").html("");
					$("#class").append(
							'<option value="' + "" + '">' + "----------Select----------"
									+ '</option>');

					for (var j = 0; j < result.ClassesList.length; j++) {
						
						$("#class").append(
										'<option value="'
												+ result.ClassesList[j].classId
												+ '">'
												+ result.ClassesList[j].classname
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

	function getSectionList()
	 {
		var classId=$("#class").val();
		var location=$("#location").val();

		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId,
				    "location":location
				},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$("#section").append('<option value="all">' + "----------Select----------"	+ '</option>');

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
	
	function getstudentinActiveList(){
		
		var accyear=$("#accyear").val();
		var stream=$("#stream").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var Location=$("#location").val();
		$.ajax({
			type : 'POST',
			url : "reportaction.html?method=geInactivetStudentDetailsReport",
			data : {
				"accyear":accyear,
				"classId":classId,
				"section":section,
				"Location":Location,
				
			},
			async : false,
			success : function(response) {
				
				var result = $.parseJSON(response);
				$("#iconsimg").show();
				$("#allstudent tbody").empty();
				if(result.StudentInfoList.length>0){
				for ( var j = 0; j < result.StudentInfoList.length; j++) {

					$("#allstudent tbody").append(
							"<tr>"+
							"<td>"+result.StudentInfoList[j].sno+"</td>"+
							"<td>"+result.StudentInfoList[j].admissionno+"</td>"+
							"<td>"+result.StudentInfoList[j].name+"</td>"+
							"<td>"+result.StudentInfoList[j].age+"</td>"+
							"<td>"+result.StudentInfoList[j].doj+"</td>"+
							"<td>"+result.StudentInfoList[j].fathername+"</td>"+
							"<td>"+result.StudentInfoList[j].fathermobno+"</td>"+
							"<td>"+result.StudentInfoList[j].mothername+"</td>"+
							"<td>"+result.StudentInfoList[j].monthermobno+"</td>"
							+"</tr>"
					);
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
					$("#iconsimg").hide();
				}
				$(".numberOfItem").empty();
				   $(".numberOfItem").append("No. of Records  "+result.StudentInfoList.length);
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

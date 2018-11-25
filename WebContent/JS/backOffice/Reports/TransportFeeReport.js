$(document).ready(function(){	
	
	$("#accyear").val($("#hacademicyaer").val());
//	getTransportFeeList($("#accyear").val());
	getClassList();
 $("#location").change(function(){
		var location=$("#location").val();
		getClassList();
		var classname=$("#class").val();
		getTransportFeeList(location);
		
	});
	
	$("#accyear").change(function(){
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		getClassList();
		getTerm();
		getTransportFeeList(accyear);
		
	});
	
	$("#class").change(function(){
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var location=$("#location").val();
		getSectionList(classId,location);
		getTerm();
		getTransportFeeList(classId);

	});
	
	$("#section").change(function()
			{
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var section=$("#section").val();
		getTerm();
		getTransportFeeList(section);
			});
	$("#term").change(function(){
		getTransportFeeList();
	});
	
	$("#termstatus").change(function(){
		var termstatus=$("#termstatus").val();
		getTransportFeeList(termstatus);
	});
	


	$("#excelDownload").click(function(){

		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var term=$("#term").val();
		var termstatusId=$("#termstatus").val();
		var classname=$("#class option:selected").text()+" - "+$("#section option:selected").text(); 
		var accYear=$("#accyear option:selected").text();
		var locationname=$("#location option:selected").text();
		if(location=="" && accyear=="" && classId=="" && section=="" && term==""){

			$('.errormessagediv').show();
			$('.validateTips').text("First Search the Transport Details");

		}
		else{

			window.location.href = 'transportfeereceipt.html?method=getTransportFeeExcelReport&location='+location+'&accyear='+accyear+'&classId='+classId+'&section='+section+'&term='+term+'&termstatusId='+termstatusId+'&classname='+classname+'&accYear='+accYear+'&locname='+$("#location option:selected").text();
				
		}
	});

	$("#pdfDownload").click(function(){
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var term=$("#term").val();
		var termstatusId=$("#termstatus").val();
		var classname=$("#class option:selected").text()+" - "+$("#section option:selected").text(); 
		var accYear=$("#accyear option:selected").text();
		var locationname=$("#location option:selected").text();
			window.location.href = 'transportfeereceipt.html?method=getTransportFeePDFReport&location='+location+
			'&accyear='+accyear+'&classId='+classId+'&section='+section+
			'&term='+term+'&termstatusId='+termstatusId+'&classname='+classname+
			'&accYear='+accYear+'&locationname='+$("#location option:selected").text();
			
		
	});
	$("#print").click(function(){
		
		
		var location=$("#location").val();
		var accyear=$("#accyear").val();
		var classId=$("#class").val();
		var section=$("#section").val();
		var term=$("#term").val();
		var termstatusId=$("#termstatus").val();
		var classname=$("#class option:selected").text()+" - "+$("#section option:selected").text(); 
		var accYear=$("#accyear option:selected").text();
		
		validate();
		
		
		$.ajax({
			type: "POST",
			url:"transportfeereceipt.html?method=printTransportFeeList&location="+location+"&accyear="+accyear+"&classId="+classId+"&section="+section+"&term="+ term+"&termstatusId="+termstatusId+"&classname="+classname+"&accYear="+accYear,
			success : function(data){
				
			}
		});
	});
/*
	$("#class").change(function(){

		var classId=$("#class").val();

		$.ajax({
			type : "GET",
			url : "transportfeereceipt.html?method=getSectionByClass",
			data : {"classId":classId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$('#section').append('<option value="">' + "ALL"	+ '</option>');

				for (var j = 0; j < result.SectionList.length; j++) {

					$("#section").append(
							'<option value="'
							+ result.SectionList[j].sectionId+'">'
							+ result.SectionList[j].sectionname+'</option>');
				}
			}
		});
	});*/
});

function getTerm(){

	var accyear=$("#accyear").val();
	var location=$("#location").val();
	datalist={
			"accyear" : accyear,
			"location":location
	},
	$.ajax({
		type : 'POST',
		url : "transportfeereceipt.html?method=getTerm",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#term').empty();
			$('#term').append('<option value="">'+ "ALL"+ '</option>');

			for ( var j = 0; j < result.termlist.length; j++)//Here termlist is the name given on key as JSONObject.
			{
				$('#term').append(
						'<option value="'+ result.termlist[j].termId+ '">'//Here termId is same name of vo class name.
						+ result.termlist[j].termname+'</option>');
			}
		}
	});
}

function validateloc()
{
	var location=$("#location").val();
	
	/*if(location==""){

		$("#txtstyle, #txtstyle").slideToggle();
	}
*/
	if(location==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Location");
		return false;
	}else{
		return true;
	}

	
	}




function validate(){
  
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	var classId=$("#class").val();
	var section=$("#section").val();
	var term=$("#term").val();

	if(location=="" && accyear=="" && classId=="" && section=="" && term==""){

		$("#txtstyle, #txtstyle").slideToggle();
	}

	if(location==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Location");
		return false;
	}
	else if(accyear==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Academic Year");
		return false;
	}
	else if(classId==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Class");
		return false;
	}
	else if(section==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Section");
		return false;
	}
	else if(term==""){
		$('.errormessagediv').show();
		$('.validateTips').text("Select Term");
		return false;
	}
	else{
		return true;
	}
}

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

			$('#class').append('<option value="">' + "----------Select----------"	+ '</option>');

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
		type : 'POST',
		url : "reportaction.html?method=getSectionByClassLoc",
		data : {"classId":classId,
			     "location":location
			     },
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#section").html("");
			$("#section").append('<option value="">' + "ALL"	+ '</option>');
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

function getTransportFeeList()
{
	var location=$("#location").val();
	var accyear=$("#accyear").val();
	var classId=$("#class").val();
	var section=$("#section").val();
	var term=$("#term").val();
	var termstatusId=$("#termstatus").val();
	
	
	datalist ={
			"location":location,
			"accyear" :accyear,
			"classId" :classId,
			"section" :section,
			"term"	  :term,
			"termstatusId":termstatusId,
	};
	$.ajax({

		type : "POST",
		url :"transportfeereceipt.html?method=gettransportfeeDetails",
		data : datalist,
		async:false,
		success:function(data){
			var result = $.parseJSON(data);

			$("#studenttable").empty();
			$("#studenttable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>SI No</th>"+
					"<th>Term</th>" +
					"<th>Admission No.</th>" +
					"<th>Name</th>" +
					"<th>Class</th>"+
					"<th>Section</th>"+
					"<th>Status</th>"+
					"<th>Amount paid</th>"+
					"</center></tr>" +
					"</table>"
			);
			if(result.studentList.length>0)
				{
		
				for(var i=0;i<result.studentList.length;i++){

					$("#allstudent").append(
							"<tr>"+
							"<td>"+result.studentList[i].sno+"</td>"+
							"<td>"+result.studentList[i].termname+"</td>"+
							"<td>"+result.studentList[i].admissionNo+"</td>"+
							"<td>"+result.studentList[i].studentnamelabel+"</td>"+
							"<td>"+result.studentList[i].classname+"</td>"+
							"<td>"+result.studentList[i].sectionname+"</td>"+
							"<td>"+result.studentList[i].status+"</td>"+
							"<td>"+result.studentList[i].amount_paid+"</td>"+
							+"</tr>"
					);
				}
				}else{
				$("#allstudent tbody").append("<tr><td colspan=7'>No Record Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentList.length);
			
		}
	});

	

}

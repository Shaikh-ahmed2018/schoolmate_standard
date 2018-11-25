$(document).ready(function(){
	getMainClassList();
	getMaximumMarkClassList($('#mainclass').val());
	
	$("#mainclass").change(function(){
		$("#marksetup,#typesetup").addClass("in");
		getMaximumMarkClassList($(this).val());
	});
	
	$(".grade").click(function(){
		$("#gradeOne").toggleClass("in");
	});
	$(".marksetup").click(function(){
		$("#marksetup").toggleClass("in");
	});
	$(".typesetup").click(function(){
		$("#typesetup").toggleClass("in");
	});
	locationId=$("#hiddenloc").val();
	accyearId=$("#hiddenaccyear").val();
	
	$(".maxtable tbody tr").click(function(){
		var classid = $(this).find(".classid").attr("id");
		var markid = $(this).find(".markid").attr("id");
		if(markid == ""){
			markid= " ";
		}
		var periodicmark = $(this).find(".periodicmark").attr("id");
		if(periodicmark == ""){
			periodicmark= " ";
		}
		$('#myValue').val(accyearId+","+locationId+","+classid+","+markid+","+periodicmark);
		$('#myform').submit();
	});
	
	$("#back1").click(function(){
		$("#backform").submit();
	});
	
});

function getMainClassList(){
	var locationid=$("#hiddenloc").val();
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
			$('#mainclass').html("");
			$('#mainclass').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#mainclass').append('<option value="' + result.ClassList[j].classcode + '">' + result.ClassList[j].classname + '</option>');
			}
		}
	});
}

function getMaximumMarkClassList(classId){
	$.ajax({
		type : "POST",
		url : "examTimetablePath.html?method=getMaximumMarkClassList",
		data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val(),"classId":classId},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table maxtable' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Class</th>" +
					"<th style='text-align:center;'>Status</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>");
			
			$("#reporttable").empty();
			$("#reporttable").append("<table class='table allstudent' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th style='width: 70px;'>Sl.No</th>" +
					"<th>Class</th>" +
					"<th style='text-align:center;'>Status</th>"+
					"</tr></thead><tbody></tbody>" +
			"</table>");

			var i=0;
			var len=result.ClassList.length;
			if(len>0){
				for(i=0;i<len;i++){
					$("#markstable .maxtable").append(
							"<tr>"+
							"<td class='markid' id='"+result.ClassList[i].markId+"'>"+result.ClassList[i].sno1+"</td>"+
							"<td style='width: 63%;' class='classid' id='"+result.ClassList[i].classId+"'>"+result.ClassList[i].classname+"</td>"+
							"<td style='text-align:center;' class='periodicmark' id='"+result.ClassList[i].periodicmark+","+result.ClassList[i].max_notebook_marks+","+result.ClassList[i].max_subenrich_marks+"'><span class='"+result.ClassList[i].status+"'>"+result.ClassList[i].status+"</span></td>"
							+"</tr>"
					);
				}
				
				for(i=0;i<len;i++){
					$("#reporttable .allstudent").append(
							"<tr>"+
							"<td class='setupId' id='"+result.ClassList[i].setupId+"'>"+result.ClassList[i].sno1+"</td>"+
							"<td style='width: 63%;' class='classid' id='"+result.ClassList[i].classId+"'>"+result.ClassList[i].classname+"</td>"+
							"<td style='text-align:center;' class='reporttype' id='"+result.ClassList[i].reporttype+","+result.ClassList[i].maxLimit+"'><span class='"+result.ClassList[i].reportstatus+"'>"+result.ClassList[i].reportstatus+"</span></td>"
							+"</tr>"
					);
				}
			}else{
				$("#allstudent tbody").append("<tr><td colspan='3'>No Record Found</td></tr>");
				$(".allstudent tbody").append("<tr><td colspan='3'>No Record Found</td></tr>");
			}
			
			$("maxtable tbody tr").click(function(){
				var classid = $(this).find(".classid").attr("id");
				var markid = $(this).find(".markid").attr("id");
				var periodicmark = $(this).find(".periodicmark").attr("id");
				$('#myValue').val(accyearId+","+locationId+","+classid+","+markid+","+periodicmark);
				$('#myform').submit();
			});
			
			$(".allstudent tbody tr").click(function(){
				var classid = $(this).find(".classid").attr("id");
				var setupId = $(this).find(".setupId").attr("id");
				var reporttype = $(this).find(".reporttype").attr("id");
				$('#termValue').val(accyearId+","+locationId+","+classid+","+setupId+","+reporttype);
				$('#termform').submit();
			});
		}
	});
}

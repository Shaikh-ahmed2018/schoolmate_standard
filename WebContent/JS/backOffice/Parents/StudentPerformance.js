$(document).ready(function() {
	
	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody tr").append("<td colspan='5'>No records found</td>")
	}
	
	$("#allstudent tbody tr").find('.Declared').append("<span class='result viewResult' title='click here' style='margin-right: 19px;'>Declared</span>");
	
	$("#parentchild").change(function(){
		getNextChildPerformanceDetails();
	});
	
	$(".viewResult").click(function(){
		examid = $(this).closest('tr').find('.Declared').attr('id');
		examprefx = $(this).closest('tr').find('.Declared').attr('class').split(' ')[1];
		window.location.href = "parentMenu.html?method=viewPerformanceDetails&examid="+examid+
		"&stuId="+$('#parentchild').val()+"&examprefx="+examprefx;
	});
});

function getNextChildPerformanceDetails(){
	
	data = {
			"stuId" : $('#parentchild').val(),
	}
	
	$.ajax({
		type:'POST',
		url:'parentMenu.html?method=getNextChildPerformanceDetails',
		data:data,
		async:false,
		success:function(response){
			var result = $.parseJSON(response);
			$("#student").text(result.student[0].studentname);
			$("#admissionNo").text(result.student[0].stdAdmisiionNo);
			$("#classdiv").text(result.student[0].classSection);
			$("#hiddenlocId").val(result.student[0].locid)
			$("#hiddenclassId").val(result.student[0].classid)
			$("#hiddensectionId").val(result.student[0].sectionid)
			$("#allstudent tbody").empty();
		if(result.examlist.length >0){
			for(var i = 0;i<result.examlist.length;i++){
				$("#allstudent tbody").append("<tr><td>"+result.examlist[i].sno+"</td>" +
						"<td>"+result.examlist[i].examtypename+"</td>" +
						"<td>"+result.examlist[i].examCode+"</td>" +
						"<td>"+result.examlist[i].examName+"</td>" +
						"<td id='"+result.examlist[i].examid+"' class='"+result.examlist[i].status+" "+result.examlist[i].examtypeprefix+"'></td>" +
				"</tr>");
			}
		}else{
			$("#allstudent tbody tr").append("<td colspan='5'>No records found</td>")
		}
			$("#allstudent tbody tr").find('.Declared').append("<span class='result viewResult' title='click here' style='margin-right: 19px;'>Declared</span>");
			
			$(".viewResult").click(function(){
				examid = $(this).closest('tr').find('.Declared').attr('id');
				examprefx = $(this).closest('tr').find('.Declared').attr('class').split(' ')[1];
				window.location.href = "parentMenu.html?method=viewPerformanceDetails&locid="+$("#hiddenlocId").val()+"&examid="+examid+
				"&classid="+$("#hiddenclassId").val()+"&secid="+$("#hiddensectionId").val();
			});
		}
	});
	
}
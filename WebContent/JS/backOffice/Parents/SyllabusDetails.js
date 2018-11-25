
$(document).ready(function() {
	
	getSubjectDetails();	
	
	$("#stuid").change(function(){
		
		$.ajax({
			type : "POST",
			url : "parentMenu.html?method=getStudentDetails",
			data : {"studid" : $("#stuid").val()},
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				$("#hclassid").val(result.details.split(" ")[0]);
				$("#hlocid").val(result.details.split(" ")[1]);
				getSubjectDetails();
			}
		});
	});
});


function getSubjectDetails() {
				
	$.ajax({
		
		type : "POST",
		url : "parentMenu.html?method=getSubjectDetails",
		data : {"classid" : $("#hclassid").val(),"locid" : $("#hlocid").val(),"specId" : $("#hspecid").val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#mytable").empty();
			$("#mytable").append("<table id='allstudent' class='table' width='100%'><thead><th>Sl.No</th><th>Subject</th><th>Description</th><th>Download Syllabus</th></thead><tbody></tbody></table>")
			if(result.dataList.length >0){
				for(var i=0;i<result.dataList.length;i++){
					$("#allstudent tbody").append("<tr>" +"<td>"+result.dataList[i].sno+"</td>"+
							"<td>"+result.dataList[i].subjectname+"</td>"+
							"<td>"+result.dataList[i].description+"</td>"+
							"<td class='"+result.dataList[i].status+"' id='"+result.dataList[i].path+"'></td>"+
							"</tr>");
				}
			
				$("#allstudent tbody tr").find('.Y').each(function(){
					var path = $(this).attr('id');
					$(this).append("<a style='text-decoration: underline;'><span class='' onclick='downloadfunction(this)' id='"+path+",download'>Download</span></a>");
				});
				$("#allstudent tbody tr").find('.N').each(function(){
					var path = $(this).attr('id');
					$(this).append("<span style='color:red;'>Not available</span></a>");
				});
			}else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>")
			}
			
			if(result.labList.length >0){
				$(".labdiv").show();
				$("#labtable").empty();
				$("#labtable").append("<table class='allstudent' width='100%'><thead><th>Sl.No</th><th>Subject</th></th><th>Lab</th><th>Description</th><th>Download Syllabus</th></thead><tbody></tbody></table>")
				
				for(var i=0;i<result.labList.length;i++){
					$(".allstudent tbody").append("<tr>" +"<td>"+result.labList[i].sno+"</td>"+
							"<td>"+result.labList[i].subjectname+"</td>"+
							"<td>"+result.labList[i].labName+"</td>"+
							"<td>"+result.labList[i].description+"</td>"+
							"<td class='"+result.labList[i].status+"' id='"+result.labList[i].path+"'></td>"+
							"</tr>");
				}
			
				$(".allstudent tbody tr").find('.Y').each(function(){
					var path = $(this).attr('id');
					$(this).append("<a style='text-decoration: underline;'><span class='' onclick='downloadfunction(this)' id='"+path+",download'>Download</span></a>");
				});
				$(".allstudent tbody tr").find('.N').each(function(){
					var path = $(this).attr('id');
					$(this).append("<span style='color:red;'>Not available</span></a>");
				});
			}else{
				$(".labdiv").hide();
			}
		}
	});
}


function getvaldetails(value){
	
	var s1 =value.id;
	var subjectid = s1;
	$("#subjectid").val(subjectid);
	
}


function downloadfunction(val){
	
	var subid =val.id;
	var subjectid=subid.split(",");

	if(subjectid == ""|| subjectid ==null){
		$('.errormessagediv').show();
		$('.validateTips').text("No File for download");
		return false;
	}
	else{
		window.location.href = "parentMenu.html?method=downloadSubject&subjectid="+subjectid[0];
	}
	
}


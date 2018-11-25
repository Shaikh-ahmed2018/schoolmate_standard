$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	
	 
	
	getexamsettingslist($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		getexamsettingslist($(this),$("#location"));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			
			/*window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#acayear").val()+"&historylocId="+$("#location").val();*/
			
			$('#myValue').val(accyear+","+location+","+$("#acayear").val()+","+$("#location").val());
			$('#myform').submit();
		});
	});
	
	$("#location").change(function(){
		getexamsettingslist($("#acayear"),$(this));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			/*window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#acayear").val()+"&historylocId="+$("#location").val();*/
			$('#myValue').val(accyear+","+location+","+$("#acayear").val()+","+$("#location").val());
			$('#myform').submit();
		});});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		/*window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+
		"&location="+location+"&historyacademicId="+$("#acayear").val()+"&historylocId="+$("#location").val();*/
		
		$('#myValue').val(accyear+","+location+","+$("#acayear").val()+","+$("#location").val());
		$('#myform').submit();
	});
	$(".exam").click(function(){
		$("#ExamOne").toggleClass("in");
		//$("#ExamOne").slideToggle();
	});
	
	if($("#historyacademicId").val()!=""){
		
		if($("#historyacademicId").val()!=""){
			$("#acayear").val($("#historyacademicId").val());
		}
		if($("#historylocId").val()==""){
			$("#location").val("%%");
		}else{
			$("#location").val($("#historylocId").val());
		}
		
		getexamsettingslist($("#acayear"),$("#location"));
	}
});

function getexamsettingslist(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "examCreationPath.html?method=getexamsettingslist",
		data : {"location":pointer.val(),"accyear":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Branch</th>" +
					"<th>Status</th>"+
					"</tr></thead>" +
					"<tbody>"+
					"</tbody>"+
					"</table>"
					);
		if(result.examlist.length>0){
					for(var i=0;i<result.examlist.length;i++){
				$("#markstable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.examlist[i].sno1+"</td>"+
						"<td class='accyear' id='"+result.examlist[i].accyearid+"'>"+result.examlist[i].accyear+"</td>"+
						"<td  class='locname' id='"+result.examlist[i].locationid+"'>"+result.examlist[i].locname+"</td>"
						+"<td><span class="+result.examlist[i].status+">"+result.examlist[i].status+"</span></td>"
						+"</tr>"
				);
			}
					$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
					pagination(50);
					$("#show_per_page").change(function(){
						pagination($(this).val());
					});
					
					$("#allstudent tbody tr").click(function(){
						var accyear = $( this ).find(".accyear").attr("id");
						var location = $( this ).find(".locname").attr("id");
						/*window.location.href="examTimetablePath.html?method=setExamDetails&accyear="+accyear+
						"&location="+location+"&historyacademicId="+$("#acayear").val()+"&historylocId="+$("#location").val();*/
						$('#myValue').val(accyear+","+location+","+$("#acayear").val()+","+$("#location").val());
						$('#myform').submit();
					});
				}
			
		}
	});

	
	
}
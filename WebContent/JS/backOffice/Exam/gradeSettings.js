$(document).ready(function(){
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	var year =$("#hiddenlocationid").val();
	 
	if(year!="%%"){
		$("#location").val(year);
	}
	
	
	gradeList($("#location"),$("#accyear"));
	
	$("#accyear").change(function(){
		gradeList($("#location"),$(this));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			/*window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();*/
			$('#myValue').val(accyear+","+location);
			$('#myform').submit();
		});
	});
	

	$("#location").change(function(){
		gradeList($(this),$("#accyear"));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			/*window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();*/

			$('#myValue').val(accyear+","+location);
			$('#myform').submit();
		});
	});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		/*window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+
		"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();*/

		$('#myValue').val(accyear+","+location);
		$('#myform').submit();
	});
	
	$(".grade").click(function(){
		$("#gradeOne").toggleClass("in");
		//$("#gradeOne").slideToggle();
		
	});
	pagination(100);
	
	if($("#historylocId").val()!="" || $("#historyacademicId").val()!=""){
		if($("#historyacademicId").val()!=""){
			$("#accyear").val($("#historyacademicId").val());
		}
		$("#location").val($("#historylocId").val());
		
		gradeList($("#location"),$("#accyear"));
	}
});

function gradeList(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url : "examCreationPath.html?method=gradeList",
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
					"<tbody></tbody></table>"
					);
			if(result.accYearList.length>0){
				for(var i=0;i<result.accYearList.length;i++){
					
					$("#markstable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.accYearList[i].sno1+"</td>"+
							"<td class='accyear' id='"+result.accYearList[i].accyearid+"'>"+result.accYearList[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.accYearList[i].locationid+"'>"+result.accYearList[i].locname+"</td>"
							+"<td><span class="+result.accYearList[i].status+">"+result.accYearList[i].status+"</span></td>"
							+"</tr>"
					);
					
				}
				
				$("#allstudent tbody tr").click(function(){
					var accyear = $( this ).find(".accyear").attr("id");
					var location = $( this ).find(".locname").attr("id");
					/*window.location.href="examCreationPath.html?method=addGradeSettings&accyear="+accyear+
					"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();*/
					
					$('#myValue').val(accyear+","+location);
				    $('#myform').submit();
				});
				
				$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});	
			}
		}
	});
}
$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	displaysubmarkslist($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		displaysubmarkslist($(this),$("#location"));
	
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var hschoolLocation=$( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+
			"&hschoolLocation="+hschoolLocation+"&historyacayear="+$("#acayear").val()+"&historylocation="+$("#location").val();
		});
		
	});
	$("#location").change(function(){
		displaysubmarkslist($("#acayear"),$(this));
	
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var hschoolLocation=$( this ).find(".locname").attr("id");
			window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+
			"&hschoolLocation="+hschoolLocation+"&historyacayear="+$("#acayear").val()+"&historylocation="+$("#location").val();
		});
		
	});
	
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var hschoolLocation=$( this ).find(".locname").attr("id");
		window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+
		"&hschoolLocation="+hschoolLocation+"&historyacayear="+$("#acayear").val()+"&historylocation="+$("#location").val();
	});
	
	$(".grade").click(function(){
		$("#gradeOne").toggleClass("in");
		//$("#gradeOne").slideToggle();
	});
	
	if($("#historyacayear").val()!="" || $("#historylocation").val()!="")
	{
		if($("#historyacayear").val()!=""){
			$("#acayear").val($("#historyacayear").val());
		}
		
		if($("#historylocation").val()==""){
			$("#location").val("%%");
		}else{
			$("#location").val($("#historylocation").val());
		}
		
		displaysubmarkslist($("#acayear"),$("#location"));
	}
	
});

function displaysubmarkslist(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "examTimetablePath.html?method=displaysubmarkslist",
		data : {"accyear":pointer.val(),"location":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Branch</th>" +
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
					);
		
			for(var i=0;i<result.examlist.length;i++){
				$("#markstable #allstudent").append(
						"<tr>"+
						"<td align='center'>"+result.examlist[i].sno1+"</td>"+
						"<td class='accyear' id='"+result.examlist[i].accyearid+"'>"+result.examlist[i].accyear+"</td>"+
						"<td  class='locname' id='"+result.examlist[i].locationid+"'>"+result.examlist[i].locname+"</td>"
						+"<td><span class="+result.examlist[i].status+">"+result.examlist[i].status+"</span></td>"
						+"</tr>"
				);
			}
			
			$("#allstudent tbody tr").click(function(){
				var accyear = $( this ).find(".accyear").attr("id");
				var hschoolLocation=$( this ).find(".locname").attr("id");
				window.location.href="examCreationPath.html?method=getSubjectmarksStatus&accyear="+accyear+
				"&hschoolLocation="+hschoolLocation+"&historyacayear="+$("#acayear").val()+"&historylocation="+$("#location").val();
			});
			
			$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
			pagination(50);
			$("#show_per_page").change(function(){
				pagination($(this).val());
			});
		}
	});

	
	

}
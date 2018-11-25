$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	
	if(year!="%%" && year!="" && year!=undefined){
		$("#location").val(year);
	}
	
	schoolList($("#location"),$("#accyear"));
	
	$("#accyear").change(function(){
		schoolList($("#location"),$(this));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="transport.html?method=addStageWiseAmountCollection&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();
		});
	});
	

	$("#location").change(function(){
		schoolList($(this),$("#accyear"));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="transport.html?method=addStageWiseAmountCollection&accyear="+accyear+
			"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();
		});
	});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		window.location.href="transport.html?method=addStageWiseAmountCollection&accyear="+accyear+
		"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();
	});
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});
	
	
	if($("#historyacademicId").val()!="" || $("#historylocId").val()!=""){
		if($("#historylocId").val()==""){
			$("#location").val("%%");
		}else{
			$("#location").val($("#historylocId").val());
		}
		$("#accyear").val($("#historyacademicId").val());
		
		schoolList($("#location"),$("#accyear"));
	}
	
});

function schoolList(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url : "stagefeesetup.html?method=schoolList",
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
					"<tbody></tbody></table>");
			var i=0;
			var len=result.accYearList.length;
			if(len>0){
				for(i=0;i<len;i++){
					$("#markstable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.accYearList[i].sno1+"</td>"+
							"<td class='accyear' id='"+result.accYearList[i].accyearid+"'>"+result.accYearList[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.accYearList[i].locationid+"'>"+result.accYearList[i].locname+"</td>"
							+"<td><span class="+result.accYearList[i].status+">"+result.accYearList[i].status+"</span></td>"
							+"</tr>");
				}
				
				$("#allstudent tbody tr").click(function(){
					var accyear = $( this ).find(".accyear").attr("id");
					var location = $( this ).find(".locname").attr("id");
					window.location.href="transport.html?method=addStageWiseAmountCollection&accyear="+accyear+
					"&location="+location+"&historyacademicId="+$("#accyear").val()+"&historylocId="+$("#location").val();
				});
			}else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}

		}
	});
}
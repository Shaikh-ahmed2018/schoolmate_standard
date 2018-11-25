$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	 
	if(year!="" && year!="%%"){
		$("#location").val(year);	
	}
	
	schoolList($("#location"));
	
//	$("#accyear").change(function(){
//		schoolList($("#location"),$(this));
//		$("#allstudent tbody tr").click(function(){
//			var accyear = $( this ).find(".accyear").attr("id");
//			var location = $( this ).find(".locname").attr("id");
//			window.location.href="menuslist.html?method=StageList&accyear="+accyear+"&location="+location+"&locname="+$( this ).find(".locname").text();
//		});
//	});
	

	$("#location").change(function(){
		schoolList($(this),$("#accyear"));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href="menuslist.html?method=StageList&location="+location+
			"&historylocId="+$("#location").val();
		});
	});
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		window.location.href="menuslist.html?method=StageList&location="+location+
		"&historylocId="+$("#location").val();
	});
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});
	
	if($("#historylocId").val()!=""){
		$("#location").val($("#historylocId").val());
		schoolList($("#location"),$("#accyear"));
	}
	
});

function schoolList(pointer,pointer1){
	$.ajax({
		
		type : "POST",
		url : "stagefeesetup.html?method=schoolListStage",
		data : {"location":pointer.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
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
							"<td  class='locname' id='"+result.accYearList[i].locationid+"'>"+result.accYearList[i].locname+"</td>"
							+"<td><span class="+result.accYearList[i].status+">"+result.accYearList[i].status+"</span></td>"
							+"</tr>");
				  }
				
				$("#allstudent tbody tr").click(function(){
					var accyear = $( this ).find(".accyear").attr("id");
					var location = $( this ).find(".locname").attr("id");
					window.location.href="menuslist.html?method=StageList&location="+location+
					"&historylocId="+$("#location").val();
				});
				
			}else{
				$("#markstable #allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}

		}
	});
}
$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#accyear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	
	if(year!="" && year!=undefined && year!="%%"){
		$("#location").val(year);
	}
	
	
	schoolList($("#location"),"");
	
	/*$("#accyear").change(function(){
		schoolList($("#location"),$(this));
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			window.location.href='menuslist.html?method=routeMasterSettings&schoolname='+$(this).find('.locname').text()+'&location='+location;
		});
	});*/
	

	$("#location").change(function(){
		schoolList($(this));
		$("#allstudent tbody tr").click(function(){
			var location = $( this ).find(".locname").attr("id");
			window.location.href='menuslist.html?method=routeMasterSettings&location='+location+
			"&historylocId="+$("#location").val();
		});
	});
	
	$("#allstudent tbody tr").click(function(){
		var location = $( this ).find(".locname").attr("id");
		window.location.href='menuslist.html?method=routeMasterSettings&location='+location+
		"&historylocId="+$("#location").val();
	});
	
	$(".grade").click(function(){
		$("#gradeOne").slideToggle();
	});
	
	if($("#historylocId").val()!=""){
		if($("#historylocId").val()==""){
			$("#location").val("%%");
		}else{
			$("#location").val($("#historylocId").val());
		}
		
		schoolList($("#location"),"");
	}
	
});

function schoolList(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "stagefeesetup.html?method=schoolListRoutMaster",
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
			
			for(var i=0;i<result.accYearList.length;i++){
				$("#markstable #allstudent tbody").append(
						"<tr>"+
						"<td>"+result.accYearList[i].sno1+"</td>"+
						"<td  class='locname' id='"+result.accYearList[i].locationid+"'>"+result.accYearList[i].locname+"</td>"
						+"<td><span class="+result.accYearList[i].status+">"+result.accYearList[i].status+"</span></td>"
						+"</tr>");
			}
			
			$("#allstudent tbody tr").click(function(){
				var location = $( this ).find(".locname").attr("id");
				window.location.href='menuslist.html?method=routeMasterSettings&location='+location+
				"&historylocId="+$("#location").val();
			});
		}
	});
}
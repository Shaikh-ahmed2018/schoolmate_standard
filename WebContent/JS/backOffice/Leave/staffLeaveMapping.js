$(document).ready(function(){
	
	$("#accyear").val($("#academic_year").val())
	
	callNextPage();
	
	$("#accyear").change(function(){
		getRecordList()
	});
	

	$("#location").change(function(){
		getRecordList()
	});
	
});

function getRecordList(){
	
	data = {
			"accyear" : $("#accyear").val(),
			"locid" : $("#location").val()
	}
	
	$.ajax({
		
		type : "POST",
		url : "leavebank.html?method=getLeavesMapStatusByFilter",
		async : false,
		data : data,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#allstudent tbody").empty();
			
			for(var i= 0;i < result.maplist.length; i++){
				$("#allstudent tbody").append("<tr>" +
						"<td>" +result.maplist[i].slno+ "</td>"+
						"<td class='mloc' id='"+result.maplist[i].locId+"'>" +result.maplist[i].locationName+ "</td>"+
						"<td class='maccy "+result.maplist[i].accyearcode+"' >" +result.maplist[i].accyear+ "</td>"+
						"<td><span class='"+result.maplist[i].mapstatus+"'>" +result.maplist[i].mapstatus+ "</td></span>"+
						"</tr>");
			}
			
			callNextPage();
			
		}
	});
}

function callNextPage(){
	$("#allstudent tbody tr").click(function(){
		var accyear = $(this).find('.maccy').attr('class').split(" ")[1];
		var locid = $(this).find('.mloc').attr('id');
		window.location.href = "leavebank.html?method=LeaveStaffList&accyear="+accyear+"&locid="+locid;
	});
}

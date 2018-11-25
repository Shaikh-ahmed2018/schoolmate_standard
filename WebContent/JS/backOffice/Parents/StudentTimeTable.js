$(document).ready(function() {
					
	var studentid = $("#studentid").val();
	$("#timetableid option[value ='"+studentid+ "']").attr('selected',true);
	
	getStuTimeTable();
	
	$('#timetableid').change(function(){
		$.ajax({
			type : "POST",
			url : "parentMenu.html?method=getStudentDetails",
			data : {"studid" : $("#timetableid").val()},
			async : false,
			success : function(data){
					var result = $.parseJSON(data);
					$("#hiddenclass").val(result.details.split(" ")[0]);
					$("#hiddenloc").val(result.details.split(" ")[1]);
					$("#hiddensec").val(result.details.split(" ")[2])
					getStuTimeTable();
				}
			});
		});
});

function getStuTimeTable(){
	
	datalist = {
			"studentid" : $('#timetableid').val(),
			"classid" : $("#hiddenclass").val(),
			"secid" : $("#hiddensec").val(),
			"locid" : $("#hiddenloc").val()
	}
	
	$.ajax({
		type : "POST",
		url : "parentMenu.html?method=getStudentTimetable",
		data :datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			var noofper = parseInt(result.noofper);
			$("#mytable").empty();
			$("#mytable").append("<table class='table' id='allstudent' width='100%'><thead><tr><th>Day</th></tr></thead><tbody></tbody></table>");
			
			for(var i =1; i<=noofper; i++){
				$("#allstudent thead tr").append("<th>Period"+i+"</th>");
			}
			if(result.List.length > 0){
				for(var i =0; i<result.List.length; i++){
					$("#allstudent tbody").append('<tr id="DAY'+i+'"><td>'+result.List[i].dayname+'</td></tr>');
					var addcol = noofper - parseInt(result.List[i].totperiods.length);
					for(var k=0; k<result.List[i].totperiods.length; k++){
						$("#DAY"+i).append("<td><span>"+result.List[i].totperiods[k]+"</span><br />"+result.List[i].totteach[k]+"</td>");
					}
					for(var j = 0; j<addcol; j++){
						$("#DAY"+i).append("<td></td>");
					}
					
				}
			}else{
				$("#allstudent tbody").append("<tr><td colspan='"+(noofper+1)+"'>No records found</td></tr>")
			}
			
		}
	});
}




/*function getStuTimeTable(){
	
	datalist = {
			"studentid" : $('#timetableid').val(),
			"classid" : $("#hiddenclass").val(),
			"secid" : $("#hiddensec").val(),
			"locid" : $("#hiddenloc").val()
	}
	
	$.ajax({
		type : "POST",
		url : "parentMenu.html?method=getStudentTimetable",
		data :datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			var days = result.Days.length;
			$("#mytable").empty();
			$("#mytable").append("<table class='table' id='allstudent' width='100%'><thead><tr><th>Day</th><th>Period1</th><th>Period2</th>" +
					"<th>Period3</th><th>Period4</th><th>Period5</th><th>Period6</th><th>Period7</th><th>Period8</th><th>Period9</th></tr></thead><tbody></tbody></table>");
			if(result.List.length > 0){
				for(var k=0;k<days;k++){
					$("#allstudent tbody").append("<tr id='"+result.Days[k]+"'></tr>");
				}
				
				$("#allstudent tbody tr").append("<td class='dayname' style='text-align: left;width: 100px;'></td>");
				for(var i=1;i<10;i++){
					$("#allstudent tbody tr").append("<td class='period"+i+"'></td>");
				}
				
				for(var i=0;i<result.List.length;i++){
					
					$("#allstudent tr#"+result.List[i].dayid+" td.dayname").text(result.List[i].dayname);
					$("#allstudent tr#"+result.List[i].dayid+" td.period1").text(result.List[i].period1);
					$("#allstudent tr#"+result.List[i].dayid+" td.period2").text(result.List[i].period2);
					$("#allstudent tr#"+result.List[i].dayid+" td.period3").text(result.List[i].period3);
					$("#allstudent tr#"+result.List[i].dayid+" td.period4").text(result.List[i].period4);
					$("#allstudent tr#"+result.List[i].dayid+" td.period5").text(result.List[i].period5);
					$("#allstudent tr#"+result.List[i].dayid+" td.period6").text(result.List[i].period6);
					$("#allstudent tr#"+result.List[i].dayid+" td.period7").text(result.List[i].period7);
					$("#allstudent tr#"+result.List[i].dayid+" td.period8").text(result.List[i].period8);
					$("#allstudent tr#"+result.List[i].dayid+" td.period9").text(result.List[i].period9);
				}
			}else{
				$("#allstudent tbody").append("<tr><td colspan=10>No Record Found</td></tr>")
			}
		}
	});
}*/
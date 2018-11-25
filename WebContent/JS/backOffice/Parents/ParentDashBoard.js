$(document).ready(function(){
	
	$('#myModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		})
	
	
	$("#stuinfo tbody tr").click(function(){
		
		var stuinfo = $(this).attr("id");
		getStuTimeTable(stuinfo)
		
	});
	
});

function getStuTimeTable(stuinfo){
	
	datalist = {
			"studentid" : stuinfo.split(",")[0],
			"classid" : stuinfo.split(",")[1],
			"secid" : stuinfo.split(",")[2],
			"locid" : stuinfo.split(",")[4]
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
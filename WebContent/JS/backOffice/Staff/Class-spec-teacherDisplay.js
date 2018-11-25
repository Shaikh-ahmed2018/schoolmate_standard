$(document).ready(function(){
	
	
	$("#acayear").val($("#accyearh").val());
	$("#locationName").val($("#hiddenlocName").val());
	
	$("select #acayear").attr("readonly",true);
	 

	$("#allstudent tbody tr").click(function(){
		var accyear = $("#accyearh").val();
		var location = $("#locationh").val();
		
		var teacherId = $(this).attr("id");
		window.location.href="teachmap.html?method=ClassTeacherMappingInfo&accyear="+accyear+"&location="+location+"&teacherId="+teacherId;
		
	});
	
	/*$("#allstudent tbody").find(tr['id']).val();*/
	
	$('#allstudent tr:contains("TEA2")').addClass('Set');
	
	if($(".emptytableCheck").text().trim().length==0){
		
		$(".emptytableCheck").append('<table class="table" id="allstudent">'+
							'<thead>'+
								'<tr>'+
									'<th>Sl.No</th>'+
									'<th>Academic Year</th>'+
									'<th>School Name</th>'+
									'<th>Registration Id</th>'+
									'<th>Teacher Name</th>'+
									'<th>Status</th>'+
								'</tr>'+
							'</thead>'+
							'<tbody>'+
								'<tr style="display: table-row;">'+
									'<td colspan="6">No Record found</td>'+
								'</tr></tbody></table>');
	}
	
	$("#back1").click(function(){
		window.location.href="teachmap.html?method=AddClassTeacherMapping&historyacayear="+$("#historyacayear").val()
		+"&historylocation="+$("#historylocation").val();
	});
	
}); //jquey



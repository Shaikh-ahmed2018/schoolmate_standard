/*$(document).ready(function(){
	  ;
	
$("#accyear").val($("#accyearh").val());
	
	

$("#allstudent tbody tr").click(function(){
	var  id = $(this).attr("id");
	window.location.href="teachmap.html?method=SubjectwiseTeacherList&id="+id;
	
});
	
});
*/


$(document).ready(function(){
	
	$("#acayear").val($("#accyearh").val());
	
	
	$("#acayear").change(function(){
		getAccyearList();
	});

	$("#location").change(function(){
		getAccyearList();
	});
	
	rowClickable();
		
	if($("#historyacayear").val()!="" || $("#historylocId").val()!=""){
		$("#acayear").val($("#historyacayear").val());
		$("#location").val($("#historylocId").val());
	}
	
	getAccyearList();
	
});//jquery

function getAccyearList(){

	datalist = {
			"accId":$("#acayear").val(),
			"locId": $("#location").val()
	}

	
	$.ajax({
		type:'POST',
		url:"teachmap.html?method=getAccyearLocStatus",
		data : datalist,
		async : false,
		success: function(response){
			var result = $.parseJSON(response);
			
			$("#allstudent tbody").empty();
			
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				$("#allstudent tbody").append("<tr id='"+result.data[i].accyearId+","+result.data[i].locationId+"'>" +
						"<td>"+(i+1)+"</td>" +
						"<td>"+result.data[i].accyearName+"</td>" +
						"<td>"+result.data[i].locationName+"</td>" +
						"<td><span class='"+result.data[i].status+"'>"+result.data[i].status+"</span></td>" +
						"</tr>");
				}
			
				}
			else{
				$("#allstudent tbody").append("<tr>" +
						"<td colspan='4'>No Records Founds</td>" +
						"</tr>");
				}
			
			rowClickable();
	
		}
		});
		
}

function rowClickable(){
$("#allstudent tbody tr").click(function(){
	var  id = $(this).attr("id");
	window.location.href="teachmap.html?method=SubjectwiseTeacherList&id="+id+
	"&historyacayear="+$("#acayear").val()+"&historylocId="+$("#location").val();
	
});

}
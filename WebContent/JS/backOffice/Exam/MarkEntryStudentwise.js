$(document).ready(function(){
	
	var hacademicyear=$('#hiddenaccyear').val();
	$("#acayear").val(hacademicyear);
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	disstumarksdetails($("#acayear"),$("#location"));
	
	$("#acayear").change(function(){
		disstumarksdetails($(this),$("#location"));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			
			$('#myValue').val(accyear+","+location);
			$('#myform').submit();
		}); 
		
		
	});
	$("#location").change(function(){
		disstumarksdetails($("#acayear"),$(this));
		
		$("#allstudent tbody tr").click(function(){
			var accyear = $( this ).find(".accyear").attr("id");
			var location = $( this ).find(".locname").attr("id");
			
			$('#myValue').val(accyear+","+location);
			$('#myform').submit();
		}); 
		
		
	});
	
	
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		
		$('#myValue').val(accyear+","+location);
		$('#myform').submit();
	}); 
	

	$(".Student").click(function(){
		$("#StudentOne").toggleClass("in");
		//$("#StudentOne").slideToggle();
	});
	
	if($("#historyacayearId").val()!="" || $("#historylocId").val()!=""){
		
		if($("#historyacayearId").val()!=""){
			$("#acayear").val($("#historyacayearId").val());
		}
		if($("#historylocId").val()==""){
			$("#location").val("%%");
		}else{
			$("#location").val($("#historylocId").val());
		}
		
		disstumarksdetails($("#acayear"),$("#location"));
	}
	
});

function disstumarksdetails(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "examTimetablePath.html?method=disstumarksdetails",
		data : {"location":pointer1.val(),"accyear":pointer.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr style='background: #f5f5f5;'>"+
					"<th>Sl.No</th>" +
					"<th>Academic Year</th>" +
					"<th>Branch</th>" +
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
					);
			if(result.markslist.length>0){
				for(var i=0;i<result.markslist.length;i++){
					
					$("#markstable #allstudent").append(
							"<tr>"+
							"<td>"+result.markslist[i].sno1+"</td>"+
							"<td class='accyear' id='"+result.markslist[i].accyearid+"'>"+result.markslist[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.markslist[i].locationid+"'>"+result.markslist[i].locname+"</td>"
							+"<td><span class="+result.markslist[i].status+">"+result.markslist[i].status+"</span></td>"
							+"</tr>"
					);
					
				}
				$("#allstudent").after("<div class='form-group clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});	
				
				$("#allstudent tbody tr").click(function(){
					var accyear = $( this ).find(".accyear").attr("id");
					var location = $( this ).find(".locname").attr("id");
					$('#myValue').val(accyear+","+location);
					$('#myform').submit();
				}); 
				
			}

		}
	});
}


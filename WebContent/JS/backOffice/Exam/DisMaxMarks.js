$(document).ready(function(){
	
	$("#accyear").val($("#curr_accy").val());
	
	$("#allstudent tbody tr").click(function(){
		var accyear = $( this ).find(".accyear").attr("id");
		var location = $( this ).find(".locname").attr("id");
		
		$('#myValue').val(accyear+","+location);
		$('#myform').submit();
	});
	
	getReportCardList($("#accyear"),$("#location"));
	
});

function getReportCardList(pointer,pointer1){
	$.ajax({
		type : "POST",
		url : "examCreationPath.html?method=getReportCardList",
		data : {"location":pointer.val(),"accyear":pointer1.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#markstable").empty();
			$("#markstable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th rowspan='2' style='vertical-align: middle;'>Sl.No</th>" +
					"<th rowspan='2' style='width:15%;vertical-align: middle;'>Academic Year</th>" +
					"<th rowspan='2' style='width:35%;vertical-align: middle;'>Branch</th>" +
					"<th colspan='2' style='text-align:center;'>Status</th>"+
					"</tr>" +
					"<tr>" +
					"<th style='width:22%;text-align:center;'>Maximum Marks Setup</th>" +
					"<th style='text-align:center;'>Report Setup</th>" +
					"</tr>" +
					"</thead>" +
					"<tbody>"+
					"</tbody>"+
					"</table>"
			);
			if(result.reportcardsettinglist.length>0){
				for(var i=0;i<result.reportcardsettinglist.length;i++){
					$("#markstable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.reportcardsettinglist[i].sno1+"</td>"+
							"<td class='accyear' id='"+result.reportcardsettinglist[i].accyearid+"'>"+result.reportcardsettinglist[i].accyear+"</td>"+
							"<td  class='locname' id='"+result.reportcardsettinglist[i].locationid+"'>"+result.reportcardsettinglist[i].locname+"</td>"
							+"<td style='text-align:center;'><span class="+result.reportcardsettinglist[i].status+">"+result.reportcardsettinglist[i].status+"</span></td>"
							+"<td style='text-align:center;'><span class="+result.reportcardsettinglist[i].reportstatus+">"+result.reportcardsettinglist[i].reportstatus+"</span></td>"
							+"</tr>"
					);
				}
				$("#allstudent").after("<div class='clearfix'><div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem' style='padding-left: 3px;'>No. of Records "+$('#allstudent tbody tr').length+"</span></div><div class='pagination pagelinks'></div></div>")
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
			}else{
				
			}

		}
	});
}
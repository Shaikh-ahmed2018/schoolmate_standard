$(document).ready(function(){
	
	
	getexamsbtselection();
	getclasslist($('#exam'));
	/*$('#exam').change(function(){
		getclasslist($('#exam'));
		tablerowclick();
	});*/
	
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	tablerowclick();
	
	$("#back1").click(function(){
		window.location.href = "examTimetablePath.html?method=getEaxmTimeTableListYear&historyaccyear="+$("#historyaccyear").val()
		+"&historylocation="+$("#historylocation").val();
		
	});
	
	if($("#historyexam").val()!=""){
		$('#exam').val($('#historyexam').val());
		getclasslist($('#exam'));
	}
	
});

function getexamsbtselection(){
	
	$.ajax({
		 	type: "POST",
		 	url: "examTimetablePath.html?method=getexamsbtselection",
		 	data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val()},
		 	async:false,
		 	success: function(data) {
				var result = $.parseJSON(data);
				$('#exam').html("");

				/*$('#exam').append('<option value="">' + "----------Select----------"	+ '</option>');*/

				for ( var j = 0; j < result.examlist.length; j++) {

					$('#exam').append('<option value="'

							+ result.examlist[j].examid + '">'

							+ result.examlist[j].examName

							+ '</option>');
				}
			}
	});
}
function getclasslist(pointer){
	$.ajax({
		
		type : "POST",
		url : "examTimetablePath.html?method=getclasslist",
		data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val(),"examcode":pointer.val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#mytable").empty();
			$("#mytable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
					"<th>Sl.No</th>" +
					"<th>Class</th>" +
					"<th>Status</th>"+
					"</tr></thead><tbody></tbody>" +
					"</table>"
					);

			 var i=0;
			var len=result.ClassList.length;
			if(len>0){
			for(i=0;i<len;i++){
				$("#mytable #allstudent").append(
						"<tr>"+
						"<td class='count' id='"+result.ClassList[i].count+"'>"+result.ClassList[i].sno1+"</td>"+
						"<td style='width: 63%;' class='classid' id='"+result.ClassList[i].classId+"'>"+result.ClassList[i].classname+"</td>"+
						"<td><span class='"+result.ClassList[i].status+"'>"+result.ClassList[i].status+"</span></td>"
						+"</tr>"
				);
			   }
			}else{
				$("#allstudent tbody").append("<tr><td colspan='3'>No Record Found</td></tr>");
			}
			
			tablerowclick();

		}
	});
}

function tablerowclick(){
	$("#allstudent tbody tr").click(function(){
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenloc").val();
		var classid  = $(this).find(".classid").attr("id");
		var classname = $(this).find(".classid").text();
		var count = $(this).find(".count").attr("id");
		if(Number(count) == 0){
			$('.errormessagediv').show();
			$('.validateTips').text("Set Exam for this Class.");
			document.getElementById("exam").style.border = "1px solid #AF2C2C";
			document.getElementById("exam").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else{
			$('#myValue').val(accyear+","+locid+","+classid);
			$('#myform').submit();
		}
	});
}
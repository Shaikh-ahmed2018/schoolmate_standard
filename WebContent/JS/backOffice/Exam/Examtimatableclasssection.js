$(document).ready(function(){
	
	getSectionList();
	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	var clasid = $("#hiddenclass").val();
	var section = $("#section").val();
	var accyear = $("#hiddenaccyear").val();
	var locid = $("#hiddenloc").val();
	var examid = $("#hiddenexamid").val();
	if(section == "all"){
		section = "%%";
	}
	
	getexamlistbyclass(locid,accyear,clasid,section,examid);
	
	$("#section").change(function(){
		var section = $("#section").val();
		if(section == "all"){
			section = "%%";
		}
		getexamlistbyclass(locid,accyear,clasid,section,examid);
		tablerowclick();
	});
	
	$("#back1").click(function(){
		accyear = $("#hiddenaccyear").val();
		locid = $("#hiddenloc").val();
		classid = $("#hiddenclass").val();
		//classname = $("#class").val();
		/*window.location.href = "examTimetablePath.html?method=getexamtimetableclass&accyear="+accyear+
		"&locid="+locid+"&historyaccyear="+$("#historyaccyear").val()+"&historylocation="+$("#historylocation").val()
		+"&historyexam="+$("#historyexam").val();*/
		
		$('#myValue').val(accyear+","+locid+","+classid);
		$('#myform').submit();
		
		/*window.location.href = "examTimetablePath.html?method=getExamByClassWise&accyear="+$("#hiddenaccyear").val()
		+"&locid="+$("#hiddenloc").val()+"&classid="+$("#hiddenclass").val();*/
	});
	 
	if($("#historysection").val()!=""){
		$("#section").val($("#historysection").val());
		var clasid = $("#hiddenclass").val();
		var section = $("#section").val();
		var accyear = $("#hiddenaccyear").val();
		var locid = $("#hiddenloc").val();
		var examid = $("#hiddenexamid").val();
		getexamlistbyclass(locid,accyear,clasid,section,examid);
	}
	
	tablerowclick();
});

function tablerowclick(){
	$("#allstudent tbody tr").click(function(){
		accyear = $("#hiddenaccyear").val();
		locid = $("#hiddenloc").val();
		classid = $("#hiddenclass").val();
		var section = $(this).find(".sectionid").attr("class").split(" ")[0];
		var examid = $(this).find(".examid").attr("class").split(" ")[0];
		var status = $(this).find(".status").text();
		
		if(status == "Set"){
			var timetableid = $(this).find(".status").attr("class").split(" ")[0];
			/*var timetableid = $(this).find(".status").attr("class").split(" ")[0];
			window.location.href = "examTimetablePath.html?method=dispalyexamsavepagedata&accyear="+accyear+
			"&locid="+locid+"&classid="+classid+"&examid="+examid+"&section="+section+"&timetableid="+timetableid+
			"&historyaccyear="+$("#historyaccyear").val()+"&historylocation="+$("#historylocation").val()+
			"&historysection="+$("#section").val()+"&historyexam="+$("#historyexam").val();*/
		$('#myValue1').val(accyear+","+locid+","+classid+","+examid+","+section+","+timetableid+","+$("#historyaccyear").val()+","+$("#historylocation").val()+","+$("#section").val()+","+$("#historyexam").val());
		$('#myform1').submit();
		}
		else{
		/*window.location.href = "examTimetablePath.html?method=dispalyexamsavepage&accyear="+accyear+
		"&locid="+locid+"&classid="+classid+"&examid="+examid+"&section="+section+
		"&historyaccyear="+$("#historyaccyear").val()+"&historylocation="+$("#historylocation").val()
		+"&historysection="+$("#section").val()+"&historyexam="+$("#historyexam").val();*/
		
		$('#myValue2').val(accyear+","+locid+","+classid+","+examid+","+section+","+$("#historyaccyear").val()+","+$("#historylocation").val()+","+$("#section").val()+","+$("#historyexam").val());
		$('#myform2').submit();
		}
	});
}


function getSectionList(){
	 
	$.ajax({
		type : 'POST',
		url : "studentTransferReport.html?method=getSectionList",
		data : {
			"classname":$("#hiddenclass").val(),
			"location":$("#hiddenloc").val()
			},
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#section').html("");
			$('#section').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.seclist.length; j++) {

				$('#section').append('<option value="'

						+ result.seclist[j].sectionId + '">'

						+ result.seclist[j].sectionName

						+ '</option>');
			}
		}
	});
}

function getexamlistbyclass(locationid,accyear,classname,sectionid,examid){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"examid":examid
		}, $.ajax({
			type : 'POST',
			url : "examTimetablePath.html?method=getexamlistbyclass",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#mytable").empty();
					$("#mytable").append("<table class='table' name='stuid' id='allstudent' width='100%'>"+"<tr>"+
							"<th>Sl.No</th>" +
							"<th>Exam Code</th>" +
							"<th>Exam Name</th>" +
							"<th>Start Date</th>"+
							"<th>End Date</th>"+
							"<th>Division</th>"+
							"<th>Status</th>"+
							"</tr> </table>");
                     var i=0;
					var len=result.examlist.length;
					if(len>0){
					for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td>"+result.examlist[i].sno1+"</td>" 
							+"<td>"+result.examlist[i].examCode+"</td>"
							+"<td  class='"+result.examlist[i].examid+" examid'>"+result.examlist[i].examName+"</td>"
							+"<td>"+result.examlist[i].startDate+"</td>"
							+"<td>"+result.examlist[i].endTime+"</td>"
							+"<td class='"+result.examlist[i].section+" sectionid'>"+result.examlist[i].sectionName+"</td>"
							+"<td class='"+result.examlist[i].timetableid+" status'><span class='"+result.examlist[i].status1+"'>"+result.examlist[i].status+"</span></td>"
							+"</tr>");
					   }
					}else{
						$("#allstudent tbody").append("<tr><td colspan='7'>No Record Found</td></tr>");
					}
					
					$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem'></span></div><div class='pagination pagelinks'></div>")
					pagination(50);
					$(".numberOfItem").empty();
			        $(".numberOfItem").append(" No. of Records  "+result.examlist.length);
					$("#show_per_page").change(function(){
						pagination($(this).val());
					});
					
			}
		});
	}

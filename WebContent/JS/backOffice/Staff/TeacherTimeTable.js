$(document)
		.ready(
				function() {
					$('#iconsimg').click(function(){
						check = [];
						$("#allstudent tbody tr td").not(".day").each(function(){
							
							if($(this).text()!=""){
								check.push("present");
								return false;
							}
							
						});
					
						if(check.length == 0){
							$("#myModal").hide();
							$(".modal-dialog").hide();
							$(".modal .fade .in").css({"display" : "none"});
							$(".errormessagediv").show();
							$(".validateTips").text("No Record Found..");
							return false;
						}else{
							$("#modal").show();
							$(".modal-dialog").show();
							$(".modal .fade .in").css({"display" : "block"});
						}
						
					});
					
			
							
	                    $('#excelDownload').click(function() {
								
								window.location.href = 'teachermenuaction.html?method=downloadTeacherTimetableXLS';
								
							});
					$("#pdfDownload").click(function(){
            			
            			window.location.href = "teachermenuaction.html?method=downloadTeacherTimetablePDF";
            				
            		});
					$("#print").click(function(){
						 singleprint();
					});
					
					$("#print1").click(function(){
						 singleprint1();
					});
					//MergeCommonRows($("#allstudent"));
					var userId=window.location.href.substring(window.location.href.lastIndexOf("=")+1);
				
					$.ajax({
						type:"POST",
						url:"teachermenuaction.html?method=viewTeacherTimeTableBYJs",
						data:{"userId":userId},
						async:false,
						success:function(response){
							var result=$.parseJSON(response);
							$("#allstudent tbody").empty();
							$("#allstudent thead").empty();
							$("#allstudent thead").append("<tr><th>Day</th></tr>");
							
							$("#allstudent tbody").append("<tr id='DAY1'><td>Monday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY2'><td>Tuesday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY3'><td>Wednesday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY4'><td>Thursday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY5'><td>Friday</td></tr>");
							$("#allstudent tbody").append("<tr id='DAY6'><td>Saturday</td></tr>");
							for(var i=1;i<=Number(getMaxPeriod());i++){
								$("#allstudent thead tr").append("<th>period"+i+"</th>");
								$("#allstudent tbody tr").append("<td class='period"+i+"'></td>");
						
							}

							for(var i=0;i<result.timeTableDetails.length;i++){
							
								$("#allstudent tr#"+result.timeTableDetails[i].dayid+" td."+result.timeTableDetails[i].periods).text(result.timeTableDetails[i].subjectid);
							}
							
							
						}
					});
					$.ajax({
						type:"POST",
						url:"teachermenuaction.html?method=viewTodayTeacherTimeTableBYJs",
						data:{"userId":userId},
						async:false,
						success:function(response){
							var result=$.parseJSON(response);
							$("#todayTimeTable thead").empty();
							$("#todayTimeTable tbody").empty();
							
							$("#todayTimeTable thead").append("<tr><th>Day</th></tr>");
							for(var i=1;i<=Number(getMaxPeriod());i++){
								$("#todayTimeTable thead tr").append("<th>period"+i+"</th>");
							}
						if(result.todaytimeTableDetails.length>0){
							$("#todayTimeTable tbody").append("<tr id='"+result.todaytimeTableDetails[0].dayid+"'></tr>");
							$("#todayTimeTable tbody tr").append("<td class='dateColumn'>"+result.todaytimeTableDetails[0].dayid+"</td>");
							$("#todayTimeTable tbody tr").append("<td id='dayname'>"+result.todaytimeTableDetails[0].dayname+"</td>");
							for(var i=1;i<=Number(getMaxPeriod());i++){
								$("#todayTimeTable tbody tr").append("<td class='period"+i+"'></td>");
							}
						
							for(var i=0;i<result.todaytimeTableDetails.length;i++){
								$("#todayTimeTable tr#"+result.todaytimeTableDetails[i].dayid+" td."+result.timeTableDetails[i].periods).text(result.timeTableDetails[i].subjectid);
							}
							
						}
							
							
						}
					});
					
					
				});


function MergeCommonRows(table) {
    var firstColumnBrakes = [];
    // iterate through the columns instead of passing each column as function parameter:
    for(var i=1; i<=table.find('th').length; i++){
        var previous = null, cellToExtend = null, rowspan = 1;
        table.find("td:nth-child(" + i + ")").each(function(index, e){
            var jthis = $(this), content = jthis.text();
            // check if current row "break" exist in the array. If not, then extend rowspan:
            if (previous == content && content !== "" && $.inArray(index, firstColumnBrakes) === -1) {
                // hide the row instead of remove(), so the DOM index won't "move" inside loop.
                jthis.addClass('hidden');
                cellToExtend.attr("rowspan", (rowspan = rowspan+1));
            }else{
                // store row breaks only for the first column:
                if(i === 1) firstColumnBrakes.push(index);
                rowspan = 1;
                previous = content;
                cellToExtend = jthis;
            }
        });
    }
    // now remove hidden td's (or leave them hidden if you wish):
    $('td.hidden').remove();
}

function singleprint(){		
 var a=$("#printing-css").val();
 
 var b= document.getElementById("scrolid").innerHTML;
 var abd='<style>' + a +'</style>' + b;
var frame1 = $('<iframe />');
frame1[0].name = "frame1";
frame1.css({ "position": "absolute", "top": "-1000000px" });
$("body").append(frame1);
var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
frameDoc.document.open();
//Create a new HTML document.
frameDoc.document.write('<html><title>...</title>');
//Append the external CSS file.
frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
frameDoc.document.write('<script type="text/javascript" src="JS/common.js"></script>');
frameDoc.document.write('<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">');
frameDoc.document.write('<body>');

//Append the DIV contents.

frameDoc.document.write(abd);
frameDoc.document.write('</body></html>');
frameDoc.document.close();
setTimeout(function () {
  window.frames["frame1"].focus();
  window.frames["frame1"].print();
  frame1.remove();
}, 100);
}


function singleprint1(){		
	 var a=$("#printing-css").val();
	 
	 var b= document.getElementById("scrolid1").innerHTML;
	 var abd='<style>' + a +'</style>' + b;
	var frame1 = $('<iframe />');
	frame1[0].name = "frame1";
	frame1.css({ "position": "absolute", "top": "-1000000px" });
	$("body").append(frame1);
	var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	frameDoc.document.open();
	//Create a new HTML document.
	frameDoc.document.write('<html><title>...</title>');
	//Append the external CSS file.
	frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
	frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
	frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
	frameDoc.document.write('<script type="text/javascript" src="JS/common.js"></script>');
	frameDoc.document.write('<link href="CSS/Admin/CommonTable.css" rel="stylesheet" type="text/css">');
	frameDoc.document.write('<body>');

	//Append the DIV contents.

	frameDoc.document.write(abd);
	frameDoc.document.write('</body></html>');
	frameDoc.document.close();
	setTimeout(function () {
	  window.frames["frame1"].focus();
	  window.frames["frame1"].print();
	  frame1.remove();
	}, 100);
	}
function getMaxPeriod(){
	var periodCountlocal=0;
	$.ajax({
		type:"POST",
		url:"teachermenuaction.html?method=getMaxPeriod",
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			periodCountlocal=result.PeriodCount;
		}
	});
	return periodCountlocal;
}

$(document).ready(function(){
	
	$("input,select").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	});
	
	
	
     $("#pdfDownload").click(function(){
    	    var evId= $("#eventname").val();
    		var catId= $("#categoryName").val();
    		var progId= $("#programName").val();
    		var houseId= $("#houseName").val();
    		var accId =$("#hacademicyaer").val();
    		var place =$("#place").val();
    		
    		
    		if (evId == "" || evId==null) {
    			$(".errormessagediv").show();
    			$(".validateTips").text("Select Event");
    			showError("#eventname");
    			setTimeout(function() {
    				$('.errormessagediv').fadeOut();
    			}, 3000);
    			
    			return false;
    		}else if (catId == "" || catId==null) {
    			$(".errormessagediv").show();
    			$(".validateTips").text("Select Category");
    			showError("#categoryName");
    			setTimeout(function() {
    				$('.errormessagediv').fadeOut();
    			}, 3000);
    			
    			return false;
    		}else if (place == "" || place==null) {
    			$(".errormessagediv").show();
    			$(".validateTips").text("Select Category");
    			showError("#place");
    			setTimeout(function() {
    				$('.errormessagediv').fadeOut();
    			}, 3000);
    			
    			return false;
    		}
    		else{
		   window.location.href = "EventsMenu.html?method=EventResultDownloadPDF&catid="+catId+"&eventId="+evId+"&programmeId="+progId+"&houseId="+houseId+"&accId="+accId+"&place="+place;
    		}
    		});	
     
     $("#print").click(function(){
    	 var evId= $("#eventname").val();
 		var catId= $("#categoryName").val();
 		var progId= $("#programName").val();
 		var houseId= $("#houseName").val();
 		var accId =$("#hacademicyaer").val();
 		var place =$("#place").val();
 		if (evId == "" || evId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Event");
			showError("#eventname");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (catId == "" || catId==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category");
			showError("#categoryName");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else if (place == "" || place==null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Category");
			showError("#place");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			
			return false;
		}else{
		window.location.href = "EventsMenu.html?method=EventResultPrint&catid="+catId+"&eventId="+evId+"&programmeId="+progId+"&houseId="+houseId+"&accId="+accId+"&place="+place;
		setTimeout(function() {
			window.location.href="EventsMenu.html?method=ResultPrinting";
		}, 2000);
		}
     });
     $('#excelDownload').click(function() {
			window.location.href = 'EventsMenu.html?method=EventParticipentListEXCEL';
		});
	$("#eventname").change(function(){
		getCategoryDropDown();
		getStageDropDown();
	});
	$("#categoryName").change(function(){
		getProgramDropDown();
		
	});

	$("#programName").change(function(){
		getEventStudentMeritListByProgramme();
		
		$("#print").hide();
		$("#pdfDownload").hide();
		//selectMeritParticipants();
	});
	$("#place").change(function(){
		$("#programName").val("");
		$("#print").show();
		$("#iconsimg").show();
		getEventStudentMeritListByOnlyEvent();
	});
	$("#stageName").change(function(){
		getProgrammeDate();
		getTableByData();
		getHouse();
		var evId = $("#eventname").val();
		var progId = $("#programName").val();
		var catId =$("#categoryName").val();
		datalist ={
				"evId":evId,
				"progId":progId,
				"catId":catId
		};
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getHouseWise",
			async:false,
			data:datalist,
			success:function(data){
				var result=$.parseJSON(data);
				result=result.data[0].houseName;
				if(result=="yes"){
					$("#houseD").show();
					getHouse();
					
				  }else{
				    getTableByData();
				  }
				}
		});
		
	});
	$("#houseName").change(function(){
		getTableByHouse();
	});
});

function getTableByHouse(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByHouse",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionNo+"</td>"+
									"<td>"+result.data[i].rollNumber+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="7">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
   }
function getHouse(){
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getHouse",
		async:false,
		data:datalist,
		success:function(data){
			var result=$.parseJSON(data);
			$("#houseName").empty();
			$("#houseName").append("<option value=''>------------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#houseName").append("<option value='"+result.data[i].houseId+"'>" 
					+result.data[i].houseName+ "</option>");
			}
	}
	});
}
function getProgrammeDate(){
	
	var evId = $("#eventname").val();
	var progId = $("#programName").val();
	var stage =$("#stageName").val();
	datalist ={
			"evId":evId,
			"progId":progId,
			"stage":stage
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getProgrammeDate",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#event").empty();
			$("#event").val(result.data[0].progDate);
			}
	});
}

function getEventName(){
	data={"flag":"Indiv"};
	$.ajax({
		type:'post',
		data:{"flag":"Indiv"},
		url:"EventsMenu.html?method=getEventNameList_Group",
		async:false,
		success:function(data){
			var result=$.parseJSON(data);
				$("#eventName").empty();
				$("#eventName").append("<option value=''>------------Select----------</option>");
				for(var i=0;i<result.data.length;i++){
				$("#eventNameListIndiv").append("<option value='"+result.data[i].eventId+"'>" 
						+result.data[i].eventName+ "</option>");
				}
		}
	});
}

function getCategoryDropDown(){
		var id = $("#eventname").val();
		
		$.ajax({
			type:'post',
			url:"EventsMenu.html?method=getCategoryName",
			data :{"id":id,
					"flag":"onLoad",
					},
			async:false,
			success:function(data){
				var result=$.parseJSON(data);
				$("#categoryName").empty();
				$("#categoryName").append("<option value=''>----------Select----------</option>");
				for(var i=0;i<result.data.length;i++){
					
				$("#categoryName").append("<option value='"+result.data[i].categoryId+"'>" 
						+result.data[i].categoryName+ "</option>");
				}
			}
		});
	}
function getProgramDropDown(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	datalist ={
			"catId":catId,
			"evId":evId,
			"flag":"onLoad",
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getprogramName",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#programName").empty();
			$("#programName").append("<option value=''>-----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#programName").append("<option value='"+result.data[i].progId+"'>" 
					+result.data[i].progName+"</option>");
			}
		}
	});
}

function getTableByData(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByData",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionno+"</td>"+
									"<td>"+result.data[i].rollNumber+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="7">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
   }


function getTableByDataHouse(){
	var catId = $("#categoryName").val();
	var evId = $("#eventname").val();
	var progId= $("#programName").val();
	var houseId = $("#houseName").val();
	var accId = $("#hacademicyaer").val();
	if(houseId==""){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getTableByData",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#individualPart tbody").empty();
			
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#individualPart tbody").append("<tr>" +
									"<td>"+(i+1)+"</td>" +
									"<td>"+result.data[i].admissionno+"</td>"+
									"<td>"+result.data[i].rollNumber+"</td>"+
									"<td>"+result.data[i].studentName+"</td>"+
									"<td>"+result.data[i].classSec+"</td>"+
									"<td>"+result.data[i].chestNoId+"</td>"+
									"<td>"+result.data[i].houseName+"</td>"+
									"</tr>");
					}
			}else{
				$("#individualPart tbody").append('<tr><td colspan="8">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
   }
function getEventStudentRegList(){
	var evId= $("#eventNameListGroup").val();
	var catId= $("#CategoryNameListGroup").val();
	var progId= $("#progNameListGroup").val();
	var houseId= $("#houseNameListGroup").val();
	var accId =$("#hacademicyaer").val();
	
	if(evId=="" || evId==null){
		evId="%%";
	}
	if(catId=="" || catId==null){
		catId="%%";
	}
	if(progId=="" || progId==null){
		progId="%%";
	}
	if(houseId=="" || houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentRegList",
		data:{	"evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr class='dispaly-table' id='"+result.data[i].registrationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].registrationNo+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
									"<th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School</th><th>Image</th>" +
							"</tr></thead></table></td>"+
							"<td>"+result.data[i].houseName+"</td>"+
							"<td><span class='glyphicon glyphicon-trash deleteRow'></span></td>"+
							"</tr>");
		 for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr class='"+result.data[i].participantsList[j].captainHighlight+"'>" +
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img  class='fancybox'  src='"+result.data[i].participantsList[j].imageUrl+"' width='20' height='20' /></td>" +
								"</tr>");
				
					}
				}
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
}

function getStageDropDown(){
	var evId = $("#eventname").val();
	datalist ={
			"evId":evId,
	};
	$.ajax({
		type:'post',
		url:"EventsMenu.html?method=getStage",
		async:false,
		data:datalist,
		success:function(data){
			var result = $.parseJSON(data);
			$("#stageName").empty();
			$("#stageName").append("<option value=''>-----------Select----------</option>");
			for(var i=0;i<result.data.length;i++){
			$("#stageName").append("<option value='"+result.data[i].stageId+"'>" 
					+result.data[i].stageName+"</option>");
			}
		}
	});
}
function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}

function selectMeritParticipants(){
	var evId= $("#eventname").val();
	var catId= $("#categoryName").val();
	var progId= $("#programName").val();
	var houseId= $("#houseName").val();
	var accId =$("#hacademicyaer").val();
	
	if(evId=="" || evId==null){
		evId="%%";
	}
	if(catId=="" || catId==null){
		catId="%%";
	}
	if(progId=="" || progId==null){
		progId="%%";
	}
	if(houseId=="" || houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentMeritList",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr>"+
							    "<td>"+(i+1)+"</td>" +
								"<td>"+result.data[i].admissionNo+"</td>"+
								"<td>"+result.data[i].studentName+"</td>"+
								"<td>"+result.data[i].rollNumber+"</td>"+
								"<td>"+result.data[i].className+"</td>"+
								"<td>"+result.data[i].location+"</td>"+
								"<td><img  class='fancybox'  src='"+result.data[i].imageUrl+"' width='20' height='20'/></td>" +
								"<td>"+result.data[i].place+"</td>"+
								"</tr>");
					}
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="8">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
}

function getEventStudentMeritListByOnlyEvent(){
	var evId= $("#eventname").val();
	var catId= $("#categoryName").val();
	var progId= $("#programName").val();
	var houseId= $("#houseName").val();
	var accId =$("#hacademicyaer").val();
	var place =$("#place").val();
	/*$("#second1").show();
	$("#allstudent").hide();*/
	
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentMeritListByOnlyEvent",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
				"place":place,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#second > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#second > tbody").append("<tr class='dispaly-table' id='"+result.data[i].registrationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].programmeName+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
									"<th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School</th><th>Image</th><th>Place</th>" +
							"</tr></thead></table></td>"+
							"</tr>");
		               for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr>" +
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img  class='fancybox'  src='"+result.data[i].participantsList[j].imageUrl+"' width='40' height='40' /></td>" +
								"<td>"+result.data[i].participantsList[j].place+"</td>"+
								"</tr>");
				
					}
				}
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
	
}
function getEventStudentMeritListByProgramme(){
	var evId= $("#eventname").val();
	var catId= $("#categoryName").val();
	var progId= $("#programName").val();
	var houseId= $("#houseName").val();
	var accId =$("#hacademicyaer").val();
	var place =$("#place").val();
	/*$("#second1").show();
	$("#allstudent").hide();*/
	
	if(evId=="" || evId==null){
		evId="%%";
	}
	if(catId=="" || catId==null){
		catId="%%";
	}
	if(progId=="" || progId==null){
		progId="%%";
	}
	if(houseId=="" || houseId==null){
		houseId="%%";
	}
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventStudentMeritListByProgramme",
		data:{	
			    "evId":evId,
				"catId":catId,
				"progId":progId,
				"houseId":houseId,
				"accId":accId,
				"place":place,
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#second > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#second > tbody").append("<tr class='dispaly-table' id='"+result.data[i].registrationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].programmeName+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
									"<th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School</th><th>Image</th><th>Place</th>" +
							"</tr></thead></table></td>"+
							"</tr>");
		               for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr>" +
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img  class='fancybox'  src='"+result.data[i].participantsList[j].imageUrl+"' width='40' height='40' /></td>" +
								"<td>"+result.data[i].participantsList[j].place+"</td>"+
								"</tr>");
				
					}
				}
			}else{
				$("#allstudent > tbody").append('<tr><td colspan="5">No records Found</td></tr>');
			}
			$(".noOfRecords").empty();
			$(".noOfRecords").append("No of Records "+result.data.length+".");
			pagination(50);
			}
		});
	
}
function showError(id,errorMessage){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}
$(document).ready(function(){
	var eid = $("#eventNameList").val().trim();
	var cid = $("#eventcategory").val().trim();
	var pid = $("#registrationNumberList").val().trim();
	getEventStudentRegList(eid,cid,pid);
	
	$("#eventNameList").change(function(){
		
			var eid = $("#eventNameList").val().trim();
			var cid = $("#eventcategory").val().trim();
			var pid = $("#registrationNumberList").val().trim();
			
			if($("#eventNameList").click){
				$("#eventcategory").val("");
				$("#registrationNumberList").val("");
			}
			
			getEventCategory(eid);
			getEventStudentRegList(eid,cid,pid);
	});
	
	$("#eventcategory").change(function(){
		var eid = $("#eventNameList").val().trim();
		var cid = $("#eventcategory").val().trim();
		var pid = $("#registrationNumberList").val().trim();
		
		
		if($("#eventcategory").click){
			$("#registrationNumberList").val("");
		}
		
		getProgramName(cid);
		getEventStudentRegList(eid,cid,pid);
		
	});
	
	$("#registrationNumberList").change(function(){
		var eid = $("#eventNameList").val().trim();
		var cid = $("#eventcategory").val().trim();
		var pid = $("#registrationNumberList").val().trim();
		
		
		if($("#eventNameList").val() == ""){
			$("#eventcategory").val("");
			$("#registrationNumberList").val("");
		}
		getEventStudentRegList(eid,cid,pid);
	});
	/*$("#addgroup").click(function(){
		$("#studentReg").dialog("open");
	});

	$("#studentReg").dialog({
	    autoOpen  : false,
	    maxWidth  :	950,
        maxHeight : 500,
        width     : 700,
        height    : 550,
	    modal     : true,
	    title     : "Student Registration",
	    buttons   : {
	    	'Save'  : function() {
	    		pointer = $(this);
	    		saveStudentReg(pointer);
	    	},
	    	'Close' : function() {
                 $(this).dialog('close');
             }
	    }
	});*/

		/*$("#dialog").dialog({
		autoOpen: false,
		modal: true,
		title : "Class Details",
		buttons : {

			'Yes' : function() {

						},

			'No' : function() {
				  $(this).dialog('close');
			}
		}
	});

	*/
	/*$("#programCaptain").autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "EventsMenu.html?method=getAdmissionNo",
				data : {
					searchTerm : request.term,
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(result.jsonResponse,function(item) {
						return {
							label : item.admissionNo,
							value : item.admissionNo,
						};
					}));
				}
			});
		},
		select : function(event, ui) {
			var searchTerm = ui.item.value;
			studentDetails = {
					'searchTerm' : searchTerm
			};
			$("#programCaptain").val(ui.item.label);
			return false;
		}
	});*/
	
/*	
	$("#selectall").change(function() {
		$("#select").prop('checked', true);
	});*/
	
	 $("#selectall").change(function () {
         $(".select").attr('checked', true);
         if($(".select").attr('checked', this.checked).length<0){
        	 $("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
         }
     });

	

$(".select").change(function() {
	if($(".select").length == $(".select:checked").length){
		$("#selectall").prop("checked",true);
	}
	else{
		$("#selectall").prop("checked",false);
	}
	});


$("#printall").click(function(){
	var admno=[];
	var prognm =[];
	var stunm =[];
	var cls =[];
	var rolno =[];
	var evenm =[];
	var evecat =[];
	var schlnm =[];
	var img =[];
	var count=0;
	$("#allstudent tbody tr td table tr").find(".select:checked").each(function(){
		count++;
		admno.push($(this).val().split("_")[0]);
		prognm.push($(this).val().split("_")[1]);
		stunm.push($(this).val().split("_")[2]);
		cls.push($(this).val().split("_")[3]);
		rolno.push($(this).val().split("_")[4]);
		evenm.push($(this).val().split("_")[5]);
		evecat.push($(this).val().split("_")[6]);
		schlnm.push($(this).val().split("_")[7]);
		img.push($(this).val().split("_")[8]);
	});
	if(count>0){
	window.location.href="EventsMenu.html?method=PrintPreviewEventMultipleIDCard&admno="
		+admno
		+"&prognm="+prognm
		+"&stunm="+stunm
		+"&cls="+cls
		+"&rolno="+rolno
		+"&evenm="+evenm
		+"&evecat="+evecat
		+"&schlnm="+schlnm
		+"&img="+img;
	}
	else{
		$("#errormessagediv").show();
		$(".validateTips").text("Select Any Record.");
		$("#errormessagediv").delay(2000).fadeOut();
	}
});
	
});//JQUERY ENDS

function getEventCategory(eid){
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getEventCategory",
		data:{'eid':eid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#eventcategory').empty();
			$('#eventcategory').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventCategory.length; j++) {

				$('#eventcategory').append('<option value="'+ result.eventCategory[j].categoryId+ '">'
						+ result.eventCategory[j].categoryName+ '</option>');

			}

		}
	});
}

function getProgramName(cid){
	
	$.ajax({
		type:'POST',
		url : "EventsMenu.html?method=getProgramName",
		data:{'cid':cid},
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#registrationNumberList').empty();
			$('#registrationNumberList').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.eventProgramName.length; j++) {

				$('#registrationNumberList').append('<option value="'+ result.eventProgramName[j].programmeId+ '">'
						+ result.eventProgramName[j].programmeName+ '</option>');
			}

		}
	});
}

function saveStudentReg(pointer){
	var participantsArray=[];
	var evId= $("#eventName").val();
	var catId = $("#categoryName").val();
	var progId = $("#programName").val();
	var houseId = $("#houseName").val();
	var programCaptain =$("#programCaptain").val().split("_")[0 ];
	var participantsName= $("#participantsList").val();
	participantsArray.push(participantsName);
	participantsArray.push(programCaptain);
	var info_staff = $("#info_staff").val();
	var info_synopsis =$("#info_synopsis").val();
	var info_req =$("#info_req").val();
	
datalist ={
			"evId":evId,
			"catId":catId,
			"progId":progId,
			"houseId":houseId,
			"programCaptain":programCaptain,
			"participantsName":participantsArray.toString(),
			"info_staff":info_staff,
			"info_synopsis":info_synopsis,
			"info_req":info_req,
				};
//alert(datalist.programCaptain);
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=saveEventStudentReg",
		data:datalist,
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			if(result.status=="true"){
				
				if(result.status=="true"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Adding Record Progressing...");
					$(".successmessagediv").delay(3000).fadeOut();
				}
    		else{
		 		$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Add Record...");
				$(".errormessagediv").delay(3000).fadeOut();
		 		 }
    		}
		}
	});
}

function getEventStudentRegList(eid,cid,pid){
	var evId= eid;
	var catId= cid;
	var regNo= pid;
	$.ajax({
		type:'POST',
		url:"EventsMenu.html?method=getEventIdPrintList",
		data:{	"evId":evId,
				"catId":catId,
				"regNo":regNo
			},
		async:false,
		success:function(data){
			var result= $.parseJSON(data);
			$("#allstudent > tbody").empty();
			if(result.data.length>0){
				for(var i=0;i<result.data.length;i++){	
					$("#allstudent > tbody").append("<tr>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+result.data[i].eventName+"</td>" +
							"<td hidden>"+result.data[i].eventId+"</td>" +
							"<td>"+result.data[i].categoryName+"</td>" +
							"<td hidden>"+result.data[i].categoryId+"</td>" +
							"<td>"+result.data[i].programmeName+"</td>" +
							"<td hidden>"+result.data[i].programmeId+"</td>" +
							"<td><table id='participants"+i+"'><thead><tr>" +
							"<th><input type='checkbox' id='selectall'/></th><th>Ad.No.</th><th>Name</th><th>Roll No.</th><th>Class</th><th>School Name</th><th>Image</th>" +
							"</tr></thead></table></td>"+
					"</tr>"); 
					for(var j=0;j<result.data[i].participantsList.length;j++){
						$("#participants"+i).append("<tr class='"+result.data[i].participantsList[j].admissionNo+"_"+result.data[i].programmeName+"_"+result.data[i].participantsList[j].studentName+"_"+result.data[i].participantsList[j].className+"_"+result.data[i].participantsList[j].rollNumber+"_"+result.data[i].eventName+"_"+result.data[i].categoryName+"_"+result.data[i].participantsList[j].location+"_"+result.data[i].participantsList[j].imageUrl+"_"+result.data[i].eventId+"_"+result.data[i].categoryId+"_"+result.data[i].programmeId+"_ allid'>" +
								"<td><input type='checkbox' class ='select' value='"+result.data[i].participantsList[j].admissionNo+"_"+result.data[i].programmeName+"_"+result.data[i].participantsList[j].studentName+"_"+result.data[i].participantsList[j].className+"_"+result.data[i].participantsList[j].rollNumber+"_"+result.data[i].eventName+"_"+result.data[i].categoryName+"_"+result.data[i].participantsList[j].location+"_"+result.data[i].participantsList[j].imageUrl+"_'></td>"+
								"<td>"+result.data[i].participantsList[j].admissionNo+"</td>"+
								"<td>"+result.data[i].participantsList[j].studentName+"</td>"+
								"<td>"+result.data[i].participantsList[j].rollNumber+"</td>" +
								"<td>"+result.data[i].participantsList[j].className+"</td>" +
								"<td>"+result.data[i].participantsList[j].location+"</td>" +
								"<td><img src='"+result.data[i].participantsList[j].imageUrl+"' width='20' height='20' /></td>" +
						"</tr>");
					}
				}
				
				 rowClickable();
				
				//deleteRow();
				 $("#selectall").change(function () {
			         $(".select").prop('checked',true);
			     });
			}else{
				$("#allstudent tbody").append("<tr>"+"<td colspan='5'>No Records Founds</td>" +"</tr>");
			}
			
			$(".select").change(function() {
				if($(".select").length == $(".select:checked").length){
					$("#selectall").prop("checked",true);
				}
				else{
					$("#selectall").prop("checked",false);
				}
				});
			}
		});
}

function rowClickable(){
	$('#allstudent tbody tr td table tr td').not('#allstudent tbody tr td table tr td:first-child').click(function(e){
	    	
	    	$("#allstudent tbody tr td table tr").click(function(){
	    		var admno=$(this).attr("class").split("_")[0];
	    		var prognm =$(this).attr("class").split("_")[1];
	    		var stunm =$(this).attr("class").split("_")[2];
	    		var cls =$(this).attr("class").split("_")[3];
	    		var rolno =$(this).attr("class").split("_")[4];
	    		var evenm =$(this).attr("class").split("_")[5];
	    		var evecat =$(this).attr("class").split("_")[6];
	    		var schlnm =$(this).attr("class").split("_")[7];
	    		var img =$(this).attr("class").split("_")[8];
	    		var evid =$(this).attr("class").split("_")[9];
	    		var catid =$(this).attr("class").split("_")[10];
	    		var prgid =$(this).attr("class").split("_")[11];
	    		
	    		window.location.href = "EventsMenu.html?method=PrintEventSingleIDCard&admno="+admno+"&prognm="+prognm+"&stunm="+stunm+"&cls="+cls+"&rolno="+rolno+"&evenm="+evenm+"&evecat="+evecat+"&schlnm="+schlnm+"&img="+img+"&evid="+evid+"&catid="+catid+"&prgid="+prgid;
	    	});
	});
}



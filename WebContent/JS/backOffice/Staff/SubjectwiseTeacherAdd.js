$(document).ready(function(){
	
	$("#locationName").val($("#hiddenlocationName").val());
	
	$(".selectAll").click(function(){
		$(this).prop("checked");
		$(".select").prop("checked");
	});
	 
	 $("#back").click(function(){
		 var accId = $("#accyearh").val();
		 var locId = $("#locationh").val();
		 var id = [accId,locId];
			window.location.href="teachmap.html?method=SubjectwiseTeacherList&id="+id;
	 });
 
$(".editinfo").click(function(){
	// var classId = pointer.closest('tr').attr('class');
		
	 $("#dialog2").dialog("open");
	 getSubjectList($(this));
	 appendSubjects($(this)); // append the selected  subjects in respective td
	 
	 pointers = $(this);
	
});

$("#dialog2").dialog({
	autoOpen: false,
	minWidth:50,
	minHeight:300,
	top:300,
	overflow :scroll,
	modal: true,
	title:'Subject-Teacher Mapping',
	buttons : {
		
		"Yes" : function() {
			
			var subjectNameArray=[];
			var subjectIdArray = []; 
			$(".select:checked").each(function(){
				subjectName = $(this).closest('tr').find("td:eq(2)").text();
				subjectIdArray.push($(this).val().trim());
				subjectNameArray.push(subjectName);
			});
			
			var removeClasssInfo=pointers.closest('tr').find('.appendValue').attr('class').split(" ")[1];
		
				pointers.closest('tr').find('.appendValue').removeClass(removeClasssInfo);
			
			
			var removeClassInfo=pointers.closest('tr').find('.subinfo').attr('class').split(" ")[1];
			//pointers.closest('tr').find('.subinfo').removeClass(removeClassInfo.split("-")[i]);
			
				pointers.closest('tr').find('.subinfo').removeClass(removeClassInfo);
			
			pointers.closest('tr').find('.subinfo').text(subjectNameArray.toString());    //for saving time append only text
			pointers.closest('tr').find('.appendValue').addClass("appendValue "+subjectIdArray.toString().replace(/,/g , ","));  //appendvalue for checkbox
			pointers.closest('tr').find('.subinfo').addClass("subinfo "+subjectIdArray.toString().trim().replace(/,/g , "-"));  //for saving time
			
			////alert("dfg "+subjectIdArray.toString());
			$(this).dialog("close");
		},
		
		"No" : function() {
			$(this).dialog("close");
		}
	}
}); 	 
	 

$("#save").click(function(){
	saveTeacherSubjectMap();
});
}); //jquery


function appendSubjects(pointers){ // edit time
	
	  var	subIdsAppendToCheckbox=[];
	  subId=$("td.appendValue").attr('class').split(" ")[1];
	  for(j=0;j<subId.split(",").length;j++){
		  subIdsAppendToCheckbox.push(subId.split(",")[j]);
	  }
		for(i=0;i<subIdsAppendToCheckbox.length;i++){
			$("#"+subIdsAppendToCheckbox[i]).prop("checked",true);
		}
}

function getSubjectList(pointer){ //for popup
	
		 var classId = pointer.closest('tr').attr('class');
			//$("td:nth-child(4)").append(specName);
		////alert("sdf "+classId);
		 
	$.ajax({
		type:	'POST',
		url:	"teachmap.html?method=getAllSubjectsByClass",
		data : {"classId":classId,"locId":$("#locationh").val()
		},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			$("#subjectTable tbody").empty();
			
			if(result.data.length>0){
			for(var i=0; i<result.data.length; i++){
				$("#subjectTable tbody").append("<tr>" +
						"<td><input type='checkbox' value="+result.data[i].subjectId+" class='select' id="+result.data[i].subjectId+"></td>" +
						"<td>"+result.data[i].subjectCode+"</td>" +
						"<td>"+result.data[i].subjectName+"</td>" +
						
						"</tr>");
			}
			}
			else{
				$("#subjectTable tbody").append("<tr><td colspan='3'>No Record Found</td></tr>");
				
			}
		}
	});
}

function saveTeacherSubjectMap(){
	
	var updateMapIdArray = [];
	var subjectArray=[];
	var classSecSpecIds = [];
	$("#allstudent tbody tr").each(function(){
			if($(this).find('.subinfo').attr('class').split('  ')[1]== undefined){
				if($(this).find('.subinfo').attr('class').split(' ')[1]==""){
					
					subjectArray.push(" ");
				}
				else{
					subjectArray.push($(this).find('.subinfo').attr('class').split(' ')[1]);
				}
				
			}
			else{
			
				if($(this).find('.subinfo').attr('class').split('  ')[1]==""){
					
					subjectArray.push(" ");
				}
				else{
					subjectArray.push($(this).find('.subinfo').attr('class').split('  ')[1]);
				}
				
			}
			classSecSpecIds.push($(this).attr('class'));
			updateMapIdArray.push($(this).attr('id'));
		
		
	});



	datalist = {
			classSecSpecIds :classSecSpecIds.toString(),
			accId : $("#accyearh").val(),
			locId :$("#locationh").val(),
			teacherId :  $("#teachrh").val(),
			subjectIdArray : subjectArray.toString(),
			updateMapIdArray :updateMapIdArray.toString(),
		}

		$.ajax({
			type:'POST',
			url:"teachmap.html?method=saveTeacherSubjectMapInfo",
			data : datalist,
			async:false,
			success: function(response){
				var result = $.parseJSON(response);
				
				
				if(result.data == "success"){
					$(".errormessagediv").hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Teacher-Subject Map Adding processing...");
						setTimeout(function() {
						var id=[$("#accyearh").val(),$("#locationh").val()];
						window.location.href="teachmap.html?method=SubjectwiseTeacherList&id="+id;
						}, 3000);
				}else{
					$(".successmessagediv").hide();
					$('.errormessagediv').show();
					$(".validateTips").text("Failed to Save Teacher-Subject Mapping");
				}
			
			}
		});
	


	
}







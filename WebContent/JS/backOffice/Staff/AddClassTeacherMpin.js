$(document).ready(function(){

   $("#locationName").val($("#hiddenlocationName").val());
	
	//loading time : not set
	if($("#allstudent > tbody > tr").length == 0 ){
		var i=0;
		$("#allstudent tbody").append('<tr><td><select class="classInfo'+i+'  clsrow"><option value="">----------Select----------</option></select></td><td><select class="sectionInfo'+i+'  secrow"><option value="">----------Select----------</option></select></td><td><select class="specInfo'+i+' specrow"><option value="-">GENERAL</option></select></td><div class="glyp-styles"><span class="glyphicon.glyphicon-trash" onclick="deleteRow(' + i +');></span></div></tr>');
	}
	
	 var rowsize=$("#allstudent > tbody > tr").length;
		
			for(var k=0;k<rowsize;k++){
				beforLoadInfo(k);
			} 
var i=rowsize-1;
	 $(".addRow").click(function (){  //" onclick appending row
		 
		 i++;
		 var row =  $('<tr id='+i+' class="add"><td><select class="classInfo'+i+'  clsrow"><option value="">----------Select----------</option></select></td><td><select class="sectionInfo'+i+'  secrow"><option value="">----------Select----------</option></select></td><td><select class="specInfo'+i+' specrow"><option value="-">GENERAL</option></select><div class="glyp-styles"><span class="glyphicon glyphicon-trash" onclick="deleteRow(' + i +');"></span></div></td></tr>');
		 $("#allstudent > tbody").append(row);
		
		 beforLoadInfo(i);
	    });
	 
	 
	 $("#save").click(function(){
		 
		 if($(".classInfo"+i+"").val()==""){
				$("#dialog").dialog("close");
				$(".successmessagediv").hide();
				$('.errormessagediv').show();
				$(".validateTips").text("Select Class Name");
				$(".classInfo"+i+"").css("backgroundColor", "#FFF7F7");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 1000);
				
			}
			else if($(".sectionInfo"+i+"").val()==""){
				$("#dialog").dialog("close");
				$(".classInfo"+i+"").css("backgroundColor", "#fff");
				$(".successmessagediv").hide();
				$('.errormessagediv').show();
				$(".validateTips").text("Select Section Name");
				$(".sectionInfo"+i+"").css("backgroundColor", "#FFF7F7");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 1000);
				
			}
		 	else if($(".specInfo"+i+"").val()==""){
			$("#dialog").dialog("close");
			$(".sectionInfo"+i+"").css("backgroundColor", "#fff");
			$(".successmessagediv").hide();
			$('.errormessagediv').show();
			$(".validateTips").text("Select Specialization Name");
			$(".specInfo"+i+"").css("backgroundColor", "#FFF7F7");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 1000);
		}
			else{
				 $("#dialog").dialog("open");
				 $("#dialog").empty();
				 $("#dialog").append("Are you sure want to Save Mappings?");
			}
		 
	 });
	 
	 $("#dialog").dialog({
			autoOpen: false,
			width:350,
			height:170,
			modal: true,	
			title:'Class-Teacher Mapping',
			buttons : {
				"Yes" : function() {
					
					saveTeacherClassMapping(i);
					$(this).dialog("close");
				},
				"No" : function() {
					$(this).dialog("close");
				}
			}
		});
	 
	
	 
	 $("#back").click(function(){
		 var accId = $("#accyearh").val();
		 var locId = $("#locationh").val();
		 var id = [accId,locId];
			window.location.href="teachmap.html?method=Class_spec_teacherDisplay&ids="+id;
			
	 });
	 
	 
	 $(".hiddenclassValue").each(function(){
	 	$(this).closest("td").find(".clsrow").val($(this).val());
	 });	 
	 	 
	 $(".hiddenSepcValue").each(function(){
	 	$(this).closest("td").find(".specrow").val($(this).val());
	 });		 
	 $(".hiddensecValue").each(function(){
		 	$(this).closest("td").find(".secrow").val($(this).val());
		 }); 
	 
});




function beforLoadInfo(i){
	
	getClassByLoc($("#locationh").val(),i);
	getSectionByClass($(".classInfo"+i).val(),i);
	getSpecialization($(".classInfo"+i).val(),i); 
	$(".specInfo"+i).click(function(){
		if($(".classInfo"+i).val()==null || $(".classInfo"+i).val()=="" ){
			$(".successmessagediv").hide();
			$('.errormessagediv').show();
			$(".validateTips").text("Select Class Name");
		}
	});
	$(".sectionInfo"+i).click(function(){
		if($(".classInfo"+i).val()==null || $(".classInfo"+i).val()=="" ){
			$(".successmessagediv").hide();
			$('.errormessagediv').show();
			$(".validateTips").text("Select Class Name");
		}
	});
	

	
	$(".classInfo"+i).change(function(){
		
		
		
		getSectionByClass($(this).val(),i);
		getSpecialization($(this).val(),i); 
	});
}

function deleteRow(i){
		
	
	var cls = $(".classInfo"+i+"").val();
	var sec = $(".sectionInfo"+i+"").val();
	var spec = $(".specInfo"+i+"").val();
	
	
	if($('#' + i).attr('class')=="add"){
		$('#' + i).remove();
	}

	else{
	////alert("delete row "+i);
	$.ajax({
		type:'POST',
		url:"teachmap.html?method=deleteClassTeacherMap",
		data :{"id":i},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			
			if(result.data == "success"){
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Class-Teacher Map Delete processing...");
			}else{
				$(".successmessagediv").hide();
				$('.errormessagediv').show();
				$(".successmessage").text("Failed to Delete Class Teacher Mapping");
			}
			setTimeout(function() {
				
				var accId = $("#accyearh").val();
				var locId = $("#locationh").val();
				var teacherId = $("#teachrh").val();
				
				var ids=[accId,locId];
				
				//window.location.href="teachmap.html?method=ClassTeacherMappingInfo&accyear="+accId+"&locId="+locId+"&teacherId="+teacherId;
				window.location.href="teachmap.html?method=Class_spec_teacherDisplay&ids="+ids;
			}, 3000);
			
			$('#' + i).remove();
		}
	});
	}

}


function getSectionByClass(classId,j){
	
	var abc=[];
	var def=[];
	$(".secrow").each(function(){
		abc.push($(this).val()); //  id : all row available id's
	});
	
	var locId = $("#locationh").val();
	
	$.ajax({
		type:'POST',
		url:"teachmap.html?method=getSectionByClass",
		data :{"classId":classId,"locId":locId},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			
			$(".sectionInfo"+j).html("");
			$(".sectionInfo"+j).append("<option value=''>----------Select----------</option>");
				
			for(var i=0; i<result.data.length; i++){
				def.push(result.data[i].sectionId);  //getting id's from db
			}
			var kjh=arr_diff(def,abc);
			
			for(var i=0; i<result.data.length; i++){
				if(jQuery.inArray(result.data[i].sectionId, kjh) !== -1){
					var row =  $(".sectionInfo"+j).append('<option value="'+result.data[i].sectionId+'">'+result.data[i].sectionname+'</option>');
				}
				}
		}
		
	});
}



function getSpecialization(classId,j){

	var locId = $("#locationh").val();
	$.ajax({
		type:'POST',
		url:"teachmap.html?method=getSpecbyClassLoc",
		data :{"classId":classId,"locId":locId},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			$(".specInfo"+j).html("");
			if(result.data.length>0){
				$(".specInfo"+j).append("<option value=''>----------Select----------</option>");
			}
			else{
				$(".specInfo"+j).append("<option value='-'>General</option>");
			}
			
				for(var i=0; i<result.data.length; i++){
					
						var row =  $(".specInfo"+j).append('<option value="'+result.data[i].specializationId+'">'
								+result.data[i].specializationName+'</option>');
				
				}
				$('.specInfo'+j).val($('.specInfo'+j).closest("td").find(".hiddenSpecValue").val());
		}
	});
}


function getClassByLoc(locId,j){

	$.ajax({
		type:'POST',
		url:"teachmap.html?method=getClassByLoc",
		data :{"locId":locId},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			
				for(var i=0; i<result.data.length; i++){
					var row =  $('.classInfo'+j).append('<option value="'+result.data[i].classId+'">'+result.data[i].classname+'</option>');
		}
				
					 
				$('.classInfo'+j).val($('.classInfo'+j).closest("td").find(".hiddenclassValue").val());
					 	
					 
		}
	});
}



function saveTeacherClassMapping(i){
	 
		$(".specInfo"+i+"").css("backgroundColor", "#fff");

		classArray =[];
		sectionArray = [];
		specArray=[];
		
		 $("#allstudent tbody tr").each(function(i){
			 classArray.push($(".classInfo"+i+"").val());
			 sectionArray.push($(".sectionInfo"+i+"").val());
			 specArray.push($(".specInfo"+i+"").val());
			 
			// validateDuplicateRow(classArray,sectionArray,specArray);
		 
		// specArray.push($(".specInfo"+i+"").val())
			 ////alert(specArray);
			

/*			 doesExisit = ($.inArray($(".specInfo"+i+"").val(), specArray) == -1) ? false : true;
			 //alert(doesExisit);
			 
			        if (!doesExisit) {
			        	//alert(doesExisit);
			        	specArray2.push($(this).val());
			        }	
			        else {
			        	$(".errormessagediv").show();
						$(".validateTips").text("Please Remove Duplicate data");
						$(".specInfo"+i+"").css("backgroundColor", "#FFF7F7");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
						
						return false;
					}
			        */
		 //each fn
				 
		 });
	 
	$.ajax({
		type:'POST',
		url:"teachmap.html?method=saveTeacherClassMapping",
		data :{"classId":classArray.toString(),"secId":sectionArray.toString(),
			"specId":specArray.toString(),"accId":$("#accyearh").val(),
			"locId":$("#locationh").val(),"teacherId":$("#teachrh").val(),
		},
		async:false,
		success: function(response){
			var result = $.parseJSON(response);
			
			if(result.data == "success"){
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text("Class-Teacher Map Adding processing...");
			}else{
				$(".successmessagediv").hide();
				$('.errormessagediv').show();
				$(".validateTips").text("Failed to Save Class Teacher Mapping");
			}
			setTimeout(function() {
				var accId = $("#accyearh").val();
				var locId = $("#locationh").val();
				var teacherId = $("#teachrh").val();
				
				var ids=[accId,locId]
				window.location.href="teachmap.html?method=Class_spec_teacherDisplay&ids="+ids+"&teacherId="+teacherId;
			}, 3000);
			
		}
		
	});
	
}



function arr_diff (a1, a2) {

    var a = [], diff = [];

    for (var i = 0; i < a1.length; i++) {
        a[a1[i]] = true;
    }

    for (var i = 0; i < a2.length; i++) {
        if (a[a2[i]]) {
            delete a[a2[i]];
        } else {
            a[a2[i]] = true;
        }
    }

    for (var k in a) {
        diff.push(k);
    }
    return diff;
}

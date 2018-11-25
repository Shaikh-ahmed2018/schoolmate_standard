
$(document).ready(function() {
	
	$("#academicYear").val($("#hiddenAcademicYear").val());
	$("#locationname").val($("#curr_loc").val());
	
	if($("#allstudent tbody tr").length == 0){
		 $("#allstudent tbody ").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
		 pagination(100);
		
	}
 
	var locationid=$("#locationname").val();
	var classname=$("#classname").val();
	var specialization=$("#specialization").val();
	var academicYear = $("#academicYear").val();
	 
	getSyllabusforPrint(locationid,classname,specialization,academicYear);
		
	getClassName($("#locationname").val());
	
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var academicYear = $("#academicYear").val();

		 
		if(locationid==""){
			locationid="all";
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getClassName($("#locationname").val());
		getSyllabusforPrint(locationid,classname,specialization,academicYear);
	
	});
	
	
	$("#academicYear").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var academicYear = $("#academicYear").val();
		getSyllabusforPrint(locationid,classname,specialization,academicYear);
		
	});
	
	
	$("#classname").change(function(){
		getClassSpecilization($(this).val(),$("#locationname").val());
			
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var academicYear=$("#academicYear").val();
		
		if(locationid==""){
			locationid="all";
			
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getSyllabusforPrint(locationid,classname,specialization,academicYear);
		
		// getSubDetailsList();
		// checkboxs();
	});	
	
	$("#specialization").change(function(){

		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		var academicYear=$("#academicYear").val();
		
		if(locationid==""){
			locationid="all";
			 // getSubDetailsList();
		}
		if(classname==""){
			classname="all";
		}
		if(specialization==""){
			specialization="all";
		}
		getSyllabusforPrint(locationid,classname,specialization,academicYear);
		// getSubDetailsList();
		// checkboxs();
	
	});	

	
});


function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">ALL</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});}

function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="">ALL</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}
	
function getSyllabusforPrint(locationid,classname,specialization,academicYear){

	var datalist = {
			"location" :locationid,
			"classname" :classname,
			"specialization":specialization,
			"academicYear":academicYear,
			"isApplicable":"S",
		}; 
		
	$.ajax({
			type : 'POST',
			url : "menuslist.html?method=syllabusListforListOnchangeMethod",
			data : datalist,
			async:false,
			
			success : function(data) {
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					$("#allstudent tbody").show();
					$(".pagebanner").show();
					if(result.jsonResponse.length>0){
					for(var i=0;i<result.jsonResponse.length;i++){
                           var x =parseInt(i+1);
                           var syllabusURL = result.jsonResponse[i].syllabus_url;
					$("#allstudent tbody").append("<tr>"
							+"<td class='hiddenID'id= '"+result.jsonResponse[i].syllabusId+"'>"+ x +"</td>"
							+"<td class='subId' id ='"+result.jsonResponse[i].classid+","+result.jsonResponse[i].specialization+","+result.jsonResponse[i].id+"'>"+result.jsonResponse[i].location_name+"</td>"
							+"<td> "+result.jsonResponse[i].className+"</td>"
							+"<td class='subName'> "+result.jsonResponse[i].subject_name+"</td>"
							+"<td><div><input type='file' name='uploadSubSylabus' class ='uploadSubSylabus' /></div></td>"
							+"<td><span class="+result.jsonResponse[i].syllabus_url+"><a href = subject.html?method=syllabusDownload&syllabus_url="+result.jsonResponse[i].syllabus_url+"><span class='downloadSyllabus' id='"+result.jsonResponse[i].syllabus_url+"'><img src='images/download.png'  height='28' width='30'></a></span></span></td>"
						+"</tr>");
					}	
			      }
				else{
						$("#allstudent tbody").show();
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					checkboxs();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.jsonResponse.length);
			}
		});
	    $(".-").hide();
	}


	
$(document).on('change', '.uploadSubSylabus', function(e){
	

	var row = $(this).parent().parent().children().index($(this).parent());
	var tr = $(this).closest('tr');
	var formdata;
    var className=$("#classname").val();
    

      
        	formdata = new FormData();
        	  file1=$(this)[row].files[0];
        	if(file1 != undefined )
      	  {
        		
        		
      		  formdata.append("addSyllabus",file1);
      		  formdata.append("subId",tr.find('.subId').attr('id'));
      		  formdata.append("syllabusId",tr.find('.hiddenID').attr('id'));
      		  formdata.append("school", tr.find('.subId').text());
      		  formdata.append("locationId", $("#locationname").val());
      		  formdata.append("academicYear", $("#academicYear").val());
      		  formdata.append("classname", $("#classname").val());
      		  formdata.append("specialization", $("#specialization").val());
      		  formdata.append("sub", tr.find('.subName').text());
      		  formdata.append("type","S");
      	  
	        	$.ajax({
	  		      type: "POST",
	  		      url: "subject.html?method=insertSubSyllabus",
	  		      data: formdata,
	                enctype: 'multipart/form-data',
	                processData: false,
	                contentType: false,
	  		      success : function(data) {
	  		    	  
	  					var result = $.parseJSON(data);
	  					if(result.status == "fail"){
	  						$('.errormessagediv').show();
	  						$(".successmessagediv").hide();
	  						$(".validateTips").text("Failed to save Syllabus...");
	  					}else{
	  						$('.errormessagediv').hide();
	  						$(".successmessagediv").show();
	  						$(".validateTips").text("Upload Successfull..");
	  						setTimeout(function(){
	  							$('.errormessagediv').hide();
		  						$(".successmessagediv").hide();
	  							getSyllabusforPrint($("#locationname").val(),$("#classname").val(),$("#specialization").val(),$("#academicYear").val());
	  						},2000);
	  					}
	  					
	  				}
	  		  });
        
      	  }
  
});



/*
 * $(document).on('click', '.downloadSyllabus', function(e){
 * 
 * 
 * var row = $(this).parent().parent().children().index($(this).parent()); var
 * tr = $(this).closest('tr');
 * 
 * var formdata;
 * 
 * formdata = new FormData();
 * 
 * //alert($(this).attr('id')); formdata.append("syllabus_url",
 * $(this).attr('id')); $.ajax({ type: "POST", url:
 * "subject.html?method=syllabusDownload", data: formdata, enctype:
 * 'multipart/form-data', processData: false, contentType: false, success :
 * function(data) {
 * 
 * var result = $.parseJSON(data); if(result.status == "fail"){
 * $('.errormessagediv').show(); $(".successmessagediv").hide();
 * $(".validateTips").text("Syllabus Download Failed..."); }else{
 * $('.errormessagediv').hide(); $(".successmessagediv").show();
 * $(".validateTips").text("Download Successfull.."); setTimeout(function(){
 * getSyllabusforPrint($("#locationname").val(),$("#classname").val(),$("#specialization").val(),$("#academicYear").val());
 * },2000); }
 *  } });
 * 
 * 
 * });
 */






function inactiveActivesubDetails(status,reason,subjectlist,locationList){
dataList={'subjectlist':subjectlist.toString(),
		'locationList':locationList.toString(),
	    'reason':reason,
	    'status':status,
	    'button':$("#delete").text(),
};


$.ajax({
	type:"POST",
	url:'subject.html?method=DeleteSubject',
	async : false,
    data : dataList,
    success:function(response) {
    	var result=$.parseJSON(response);
    	
    	if(result.status==true){

			$(".successmessagediv").show();
			$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
			$(".validateTips").text($("#delete").text()+" "+"Subject Details Progressing...");
			$(".successmessagediv").css({
				'z-index':'9999999',
			});
			setTimeout(function(){
				
				getSubDetailsList();
				 $("#selectall").prop("checked",false);
				subjectlist=[];
				locationList=[];
                
			},2000);
		}

		else if(result.status==false){
			$('.errormessagediv').show();
			$('.validateTips').text("Selected Subject is not"+" "+$("#delete").text());
			$(".errormessagediv").css({'z-index':'9999999'});
			setTimeout(function(){
			
				getSubDetailsList();
				 $("#selectall").prop("checked",false);
				subjectlist=[];
				locationList=[];

			},2000);
		}
		else{
			$('.errormessagediv').show();
			$('.validateTips').text("Selected Subject is Cannot"+" "+$("#delete").text());
			$(".errormessagediv").css({
				'z-index':'9999999'
			});
			
		}

		
	}
		
	
});
}

function getSubDetailsList(){
	dataList={
			 locationid:$("#locationname").val(),
			 classname:$("#classname").val(),
			 specialization:$("#specialization").val(),
			 status:$("#status").val(),	
			 

	};

	 
	$.ajax({
            type:'GET',
            url:'subject.html?method=getSubDetailsList',
            async:false,
            data:dataList,
            success : function(data) {
				 
				var result = $.parseJSON(data);
					$("#allstudent tbody").empty();
					if(result.list.length>0){
					for(var i=0;i<result.list.length;i++){
					// //alert(result.list.length);

					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select' id='"+result.list[i].subjectid+","+result.list[i].locationId+"' /></td>"
							+"<td> "+result.list[i].locationName+" </td>"
							+"<td> "+result.list[i].classname+"</td>"
							+"<td> "+result.list[i].specializationName+" </td>"
							+"<td> "+result.list[i].subjectname+" </td>"
							+"<td> "+result.list[i].description+" </td>"
							+"<td> "+result.list[i].remark+" </td>"
							/* +"<td> "+result.list[i].path+" </td>" */
							/*
							 * +"<td style='text-align:center'><a
							 * href='subject.html?method=getsyllabusdownload&subjectid=<bean:write
							 * name='allsubjects' property='subjectid'/><img
							 * id='dwnd1' src='images/download.png'/></a></td>"
							 */
						+"</tr>");
					}	
			}
					else{
						$("#allstudent tbody").append("<tr>" +"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					checkboxs();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.list.length);
			}
            
	});
	
	
}

function checkboxs(){
	
	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});

	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
		
	});
}
function showError(id,errorMessage){
	$(id).focus();
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
function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}






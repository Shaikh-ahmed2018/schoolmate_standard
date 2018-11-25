$(document).ready(function() {
	
	$("#lab_name").change(function(){
		value=$("#lab_name").val();
		var  id="lab_name";
			capital(value,id);
	});
	
	$("#goback").click(function()
	{
		window.location.href="menuslist.html?method=laboratory&locId="+$("#hiddenlocId").val()+"&classname="+$("#hiddenclassname").val()+"&specId="+$("#hiddenspecId").val()+"&status="+$("#hiddenstatus").val();
	 });

  if($("#hiddenlocationid").val()!="" & $("#hiddenlocationid").val()!=null )
  {
	$("#locationname").val($("#hiddenlocationid").val()) ; 
	getClassName($("#locationname").val());
	$("#classname").val($("#hiddenclassid").val());
	getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
	$("#subject").val($("#hiddensubject").val());
	
  }
	$("#locationname").change(function(){
		var locationid=$("#locationname").val();
		var classname=$("#classname").val();
		var specialization=$("#specialization").val();
		

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
		getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
	});
	
	$("#classname").change(function(){
		getSubjectName($("#classname").val(),$("#locationname").val(),$("#specialization").val());
		getClassSpecilization($(this).val(),$("#locationname").val());
	});
	
	$("#subject").change(function(){
		 checkSubjectduplication();
		});
	
	
	
	  $('input[type=file]').change(function () {
			 var val = $(this).val().toLowerCase();
			 var regex = new RegExp("(.*?)\.(docx|doc|pdf|ppt|xls|jpg|jpeg|txt|png|xlsx)$");
			  if(!(regex.test(val))) {
			 $(this).val('');
			 $(
				".errormessagediv")
				.show();
		     $(".validateTips")
				.text(
						"Select Correct file format ");
			 } }); 
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="subject.html?method=updateLab"){
		$(".successmessagediv").show();
		setTimeout(function(){
			window.location.href="menuslist.html?method=laboratory";
		},2500);
	}
		
	
	$("#lab_name").change(function(){
		
		validate($("#lab_name").val());
		
		
	});
	
	

	
	
	$('#save').click(function() 
			{
		
                           
							$(".errormessagediv").show();
							
							var  classname = $("#classname").val();
							var subject = $("#subject").val();
							var lab_name=$("#lab_name").val();
							var  description = $("#description").val();
							var locationId=$("#locationname").val();
							var subjectCode =  $("#subjectCode").val();
							var specialization = $("#specialization").val();
                            
							var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
						    var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
							
						    if(locationId=="")
                         	{
						    	$(".errormessagediv").show();	
						    	$(".validateTips").text("Select Branch Name");
						    	showError("#locationname");
										setTimeout(function() {
											$('.errormessagediv').fadeOut();
										}, 3000);
							return false;
                         	 }
						    
						    else   if(classname==""){
						    	$(".errormessagediv").show();
                             	$(".validateTips").text("Field Required - Class ");
							
                             	showError("#classname");
                             	setTimeout(function() {
                             		$('.errormessagediv').fadeOut();
                             	}, 3000);
                             		return false;
                            }
                            
                             
                            else if(subject=="")
                        	{
                        	 $(".errormessagediv").show();	
                                $(".validateTips").text("Field Required - Subject Name..");
     							showError("#subject");
									setTimeout(function() {
										$('.errormessagediv').fadeOut();
									}, 3000);
						
						
									return false;
                        	 }
                             
                             else if(subject.length < 2)
     						{
     							$(".errormessagediv").show();
     							$(".validateTips").text("Subject name Should Contain min 2 Characters");
     							showError("#subject");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
     							return false;
     						}
                             else if(lab_name=="")
                          	{
  								$(".errormessagediv").show();
  								$(".validateTips").text("Field Required - Lab Name");
  								showError("#lab_name");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
  								return false;
  							}
                              else if(lab_name.length < 3)
       						{
       							$(".errormessagediv").show();
       							$(".validateTips").text("Field Required - Lab Name");
  								showError("#lab_name");
  								setTimeout(function() {
  									$('.errormessagediv').fadeOut();
  								}, 2000);
  								return false;
       						}
                            
                             else if(subjectCode ==""){
                            	 $(".errormessagediv").show();
       							$(".validateTips").text("Field Required - Lab Code");
       							document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
                              	document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
       							return false; 
                             }
                             else if(subjectCode.length > 10){
                            	 $(".errormessagediv").show();
       							$(".validateTips").text("Invalid Lab Code");
       							document.getElementById("subjectCode").style.border = "1px solid #AF2C2C";
                              	document.getElementById("subjectCode").style.backgroundColor = "#FFF7F7";
       							return false; 
                             }

						else {
							     
								 var data = {
										 "classname":classname,"lab_name":lab_name,
										 "subject":subject,"description":description,
										 "locationId":locationId,"specialization":specialization,
										 "subjectCode":subjectCode,"hiddenlocationid":$("#hiddenlabid").val()
										 };
								
								 $.ajax({
									    type : 'POST',
										url : "subject.html?method=updateLab",
										data: data,
										async : false,
										success : function(response) {
									 
											var result = $.parseJSON(response);
											if(result.status=="success"){
												$(".errormessagediv").hide();
												$(".successmessagediv").show();
												$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
												$(".validateTips").text("Lab Details Update Progressing...");
				 								setTimeout(function() {
				 									window.location="menuslist.html?method=laboratory";
				 								}, 1000);
												
											}else{
												$(".errormessagediv").show();
				      							$(".validateTips").text("Saved Unsuccessfully ...");
				 								showError("#lab_name");
				 								setTimeout(function() {
				 									$('.errormessagediv').fadeOut();
				 									location.reload();
				 								}, 2000);
				 								return false;
								
							                   }
										    }
										});
 							        } 
							
			});				
						
	
	var path=$("#hiddenfile").val();
	if(path !== "" && path!=undefined){
		
		$("#document1btn").attr('name',path);
		$("#file").hide();
		
		$("#document1btn").show();
		$("#deleteProfile").show();
		
	}
	
	$("#deleteProfile").click(function(){
		
		$("#file").show();
		
		$("#document1btn").hide();
		$("#deleteProfile").hide();
		
	});
	
	

	
	
	$("#document1btn").click(function(){
		
		
		var path = $(this).attr('name');
		
		
		if(path == ""){
			
			$(".errormessagediv").show();
			$(".validateTips")
			.text(
					"No file for downloading");
		}
		else{
			
			
			window.location.href = "subject.html?method=getpathdownloadOfLab&Path="+ path;
		}
	
		
	});
	
	
				
			});

function checkSubjectduplication() {

	var classId = $("#classname").val();
	var subject = $("#subject").val(); 
	var subname = $("#subject").text(); 
	var locationId=$("#locationname").val();
	var specialization=$("#specialization").val();
	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"specialization":specialization,

	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=validateLabNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {

			
			var result = $.parseJSON(data);


			if(result.des_available =="true" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name Already Exists With this Class");
				
				$("#subject").css({'border-color':'#f00'});
				$("#subject").val("");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);

			}
			else{
				
				$("#subject").css({'border-color':'#ccc'});
				
			}

		}
	});
}



function getSubjectName(classId,location,spec){

	var data = {
			"classId" : classId,
			"locationId":location,
			"specId" : spec
	};
	$.ajax({
		type : 'POST',
		url : "subject.html?method=getSubjectByClass",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			
			$('#subject').empty();
			$('#subject').append('<option value="-">'+ "-------select--------" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#subject').append(
						'<option value="'
						+ result.jsonResponse[j].subjectId
						+ '">'
						+ result.jsonResponse[j].subjectName
						+ '</option>');
			}


		}
	});
}

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
			$('#specialization').append('<option value="-">'+ "General" + '</option>');

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

function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">' + "---------Select---------" + '</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}
	
	}
});}

function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}	
function capital(value,id)
{
	if(value.length>0)
		{
		var str=value.replace(value.substr(0,1),value.substr(0,1).toUpperCase());
		document.getElementById(id).value=str;
		
		}
}



function validate (value){
	
	if(value.length>3)
	{
		$(id).css({
			"border":"1px solid #2caf4e",
			
	      });
    }
	
	
}



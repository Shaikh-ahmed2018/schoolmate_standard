$(document).ready(function(){
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#locationname").val($("#hiddenlocId").val());
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	
	$(".ui-icon-closethick").hide();
	getClassList();
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var sortingby=$("#sortingby").val();
	var orderby=$("input[name='sorting']:checked").val();
	
	   $(".Appearance").change(function(){
		   if($(".Appearance").val()=="Others"){ 
			   $(".Appearance").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Appearance1").append('<input type="text" style="margin-left:7%;width: 165px;" class="appothers otherval" name="appearance" id="appothers"> <span class="glyphicon glyphicon-repeat appiconposition" id="appicon"></span>');
			
			  $(".appiconposition").click(function(){
					$(".Appearance").show();
					$(".Appearance").val("all");
					 $(".appothers").hide();
					 $(".appothers").val("");
					 $(".appiconposition").hide();
				});
		      } 
	     });
	   
	   
	   $(".Involvement").change(function(){
		   if($(".Involvement").val()=="Others"){ 
			   $(".Involvement").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Involvement1").append('<input type="text" style="margin-left:7%;width: 165px;" class="classInvolothers otherval" name="classInvolothers" id="classInvolothers"> <span class="glyphicon glyphicon-repeat classInvoliconposition" id="classInvoliconposition"></span>');
			
			  $(".classInvoliconposition").click(function(){
					$(".Involvement").show();
					$(".Involvement").val("all");
					 $(".classInvolothers").hide();
					 $(".classInvolothers").val("");
					 $(".classInvoliconposition").hide();
				});
		      } 
	     });
	
	   $(".Capacity").change(function(){
		   if($(".Capacity").val()=="Others"){ 
			   $(".Capacity").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Capacity1").append('<input type="text" style="margin-left:7%;width: 165px;" class="learncapothers otherval" name="learncapothers" id="learncapothers"> <span class="glyphicon glyphicon-repeat learncapiconposition" id="learncapiconposition"></span>');
			
			  $(".learncapiconposition").click(function(){
					$(".Capacity").show();
					$(".Capacity").val("all");
					 $(".learncapothers").hide();
					 $(".learncapothers").val("");
					 $(".learncapiconposition").hide();
				});
		      } 
	     });
	   
	   $(".Works").change(function(){
		   
		   if($(".Works").val()=="Others"){ 
			   $(".Works").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Works1").append('<input type="text" style="margin-left:7%;width: 165px;" class="homeworkothers otherval" name="homeworkothers" id="homeworkothers"> <span class="glyphicon glyphicon-repeat homeworkiconposition" id="homeworkiconposition"></span>');
			
			  $(".homeworkiconposition").click(function(){
					$(".Works").show();
					$(".Works").val("all");
					 $(".homeworkothers").hide();
					 $(".homeworkothers").val("");
					 $(".homeworkiconposition").hide();
				});
		      } 
	     });
	   
	   $(".Reading").change(function(){
		   if($(".Reading").val()=="Others"){ 
			   $(".Reading").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Reading1").append('<input type="text" style="margin-left:7%;width: 165px;" class="readothers otherval" name="readothers" id="readothers"> <span class="glyphicon glyphicon-repeat readiconposition" id="readiconposition"></span>');
			
			  $(".readiconposition").click(function(){
					$(".Reading").show();
					$(".Reading").val("all");
					 $(".readothers").hide();
					 $(".readothers").val("");
					 $(".readiconposition").hide();
				});
		      } 
	     });
	   
	   $(".Writing").change(function(){
		   if($(".Writing").val()=="Others"){ 
			   $(".Writing").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Writing1").append('<input type="text" style="margin-left:7%;width: 165px;" class="writeothers otherval" name="writeothers" id="writeothers"> <span class="glyphicon glyphicon-repeat writeiconposition" id="writeiconposition"></span>');
			
			  $(".writeiconposition").click(function(){
					$(".Writing").show();
					$(".Writing").val("all");
					 $(".writeothers").hide();
					 $(".writeothers").val("");
					 $(".writeiconposition").hide();
				});
		      } 
	     });
	   
	   $(".Fluency").change(function(){
		   if($(".Fluency").val()=="Others"){ 
			   $(".Fluency").hide();
			   $(".allstudent tbody tr").closest("tr").find(".Fluency1").append('<input type="text" style="margin-left:7%;width: 165px;" class="englishfluencyothers otherval" name="englishfluencyothers" id="englishfluencyothers"> <span class="glyphicon glyphicon-repeat englishfluencyiconposition" id="englishfluencyiconposition"></span>');
			
			  $(".englishfluencyiconposition").click(function(){
					$(".Fluency").show();
					$(".Fluency").val("all");
					 $(".englishfluencyothers").hide();
					 $(".englishfluencyothers").val("");
					 $(".englishfluencyiconposition").hide();
				});
		      } 
	     });
	   
	   $(".ECA").change(function(){
		   if($(".ECA").val()=="Others"){ 
			   $(".ECA").hide();
			   $(".allstudent tbody tr").closest("tr").find(".ECA1").append('<input type="text" style="margin-left:7%;width: 165px;" class="activitiesothers otherval" name="activitiesothers" id="activitiesothers"> <span class="glyphicon glyphicon-repeat activitiesiconposition" id="activitiesiconposition"></span>');
			
			  $(".activitiesiconposition").click(function(){
					$(".ECA").show();
					$(".ECA").val("all");
					 $(".activitiesothers").hide();
					 $(".activitiesothers").val("");
					 $(".activitiesiconposition").hide();
				});
		      } 
	     }); 
	     
	     $(".Books").change(function(){
			   if($(".Books").val()=="Others"){ 
				   $(".Books").hide();
				   $(".allstudent tbody tr").closest("tr").find(".Books1").append('<input type="text" style="margin-left:7%;width: 165px;" class="bagbooksothers otherval" name="bagbooksothers" id="bagbooksothers"> <span class="glyphicon glyphicon-repeat bagbooksiconposition" id="bagbooksiconposition"></span>');
				
				  $(".bagbooksiconposition").click(function(){
						$(".Books").show();
						$(".Books").val("all");
						 $(".bagbooksothers").hide();
						 $(".bagbooksothers").val("");
						 $(".bagbooksiconposition").hide();
					});
			      } 
		     });  
		     
		     $(".Behaviour").change(function(){
				   if($(".Behaviour").val()=="Others"){ 
					   $(".Behaviour").hide();
					   $(".allstudent tbody tr").closest("tr").find(".Behaviour1").append('<input type="text" style="margin-left:7%;width: 165px;" class="behaviourothers otherval" name="behaviourothers" id="behaviourothers"> <span class="glyphicon glyphicon-repeat behaviouriconposition" id="behaviouriconposition"></span>');
					
					  $(".behaviouriconposition").click(function(){
							$(".Behaviour").show();
							$(".Behaviour").val("all");
							$(".behaviourothers").hide();
							$(".behaviourothers").val("");
							$(".behaviouriconposition").hide();
						});
				      } 
			     });
	 
	$("#allstudent tbody tr").click(function(){
		$(".successmessagediv").hide();
		$(".errormessagediv").hide();
		$("#Dialog").dialog("open"); 
		stuId=$(this).attr("class").split(" ")[0];
		accyear=$(this).attr("class").split(" ")[1];
		locId=$(this).attr("class").split(" ")[2];
		classId=$(this).attr("class").split(" ")[3];
		SectionId=$(this).attr("class").split(" ")[4];
		AdmissionNo=$(this).attr("class").split(" ")[5];
		
		name=$(this).closest("tr").find(".studentName").text();
		classsection=$(this).closest("tr").find(".classdiv").text();
		
		className=$(this).find(".classdiv").attr("class").split(" ")[0];
		sectionName=$(this).find(".classdiv").attr("class").split(" ")[1]; 
		
		set=$(this).find(".class_name").attr("class").split(" ")[1];
		studentanalyticalperformanceTableBYStudentId();
		Savebuttonhidefunction();
		 
		$('#student').html("");
		$('#classdiv').html("");
		$('#admissionNo').html("");
		
		$("#student").append(name);
		$("#classdiv").append(classsection);
		$("#admissionNo").append(AdmissionNo);
		$("#hiddenstatus").val(set);
		if(set=="Set"){
			getStudentAnalyticalPerformanceForUpdate(stuId,accyear);
		} 
	});
	
	rowIds=[];
	selectvalue=[];
	otherval=[];
	ckeckedval=[];
	
	$("#Dialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    modal     : true,
	    title     : "Analytical Performance ",
	    width     : 960,
	    height    : 550,
	    buttons   : {
	    	
	    	       'Download':function() {
	    	    window.location.href ='reportaction.html?method=StudentAnalyticalPerformanceReportsPDF&studentId='+stuId+
	    	    '&accyear='+accyear+'&locId='+locId+'&className='+className+'&sectionName='+sectionName+'&studentname='+name+'&AdmissionNo='+AdmissionNo+'&classsection='+classsection;
	    	       },
	              'Save' : function() {
	            	  
	            	var locationid=$("#locationname").val();
	          		var accyear=$("#Acyearid").val();
	          		var classname=$("#classname").val();
	          		var sectionid=$("#sectionid").val();
	          		var sortingby=$("#sortingby").val();
	          		var orderby=$("input[name='sorting']:checked").val();
	            	  
	            	  $(".allstudent tbody tr").each(function(){
	            			var list=$(this).attr("class").split(" ")[0]+"-"+$(this).find(".checked:checked").val()+"-"+$(this).find(".selectbox").val()+"-"+$(this).find(".otherval").val();
	            			rowIds.push(list);
	            			 
	            			 if($(this).find(".checked:checked").val()==undefined || $(this).find(".checked:checked").val()=="undefined"){
	            				 point=$(this).find(".remarknames").text();
	   						  value1="true";
	   							return false;
	   					 }else{
	   						 value1="false";
	   					 }
	   	            	  
	            		});	
	            	  
	            	  if(value1=="true"){
	            		     rowIds=[];
   						     $(".errormessagediv").show();
   							 $(".validateTips").text("Required Analytical Performance Remarks for - "+point);
   							 $(".errormessagediv").delay(3000).fadeOut();
   				          }
	            	  else{	 
	            		   
	            	  var datalist = {
	            			'rowIds':rowIds.toString(),
      			  	        'stuId':stuId,
      			  	        'accyear':accyear,
      			  	        'locId':locId,
      			  	        'classId':classId,
      			  	        'SectionId':SectionId,
      	                 };
	            	  
	            	  $.ajax({
							type : "POST",
							url : "studentRegistration.html?method=SaveStudentAnalyticalPerformance",
							data : datalist,
							beforeSend: function(){
								$("#loder").show();
							},
							success : function(response) {
								var result = $.parseJSON(response);
                                  
								if (result.status =="true" || result.status ==true) {
									$("#loder").hide();
									$(".successmessagediv").show();
									$(".validateTips") .text("Analytical Performance added successfully...");
									
									/*$(".successmessagediv").delay(4000).slideUp("slow");*/
									setTimeout(function(){
										 $("#Dialog").dialog('close');
										 getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
										},3000);
								}else{
									$("#loder").hide();
									 $(".errormessagediv").show();
		   							 $(".validateTips").text("Failed to add Analytical Performance. Try again");
		   							/* $(".errormessagediv").delay(4000).fadeOut();*/
		   							setTimeout(function(){
		   								$("#Dialog").dialog('close');
		   								getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
								   },3000);
								}
							}
						});
	            	  }
	              },
	              'Cancel':function() {
	            	  $(this).dialog('close');
	            	   
	              },
	          }
	    });
	
	$("#resetbtn").on("click", function (e) {
		$("#classname").val("all");
		$("#sectionid").html("");
		$('#sectionid').append('<option value="all">----------Select----------</option>');
		$("#Acyearid").val($('#hacademicyaer').val());
		$("#locationname").val($("#hiddenlocId").val());
		$("#searchvalue").val("");
		$("#allstudent tbody").empty("");
		cleartable();
	});
	
	$("#search").click(function(){
		searchList();
	});	
	
	$("#searchvalue").keypress(function(e) {
	    if(e.which == 13) {
	    	searchList();
	    }
	});
	
	$("#Acyearid").change(function(){
		var classname=$("#classname").val();
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		if(classname!="all"){
			getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
		}else{
			cleartable();
		}
	});
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var sortingby=$("#sortingby").val();
	var orderby=$("input[name='sorting']:checked").val();
	
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getClassList();
		cleartable();
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		if(classname!="all"){
			getSectionList(classname)
			getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
		}else{
			cleartable();
			$('#sectionid').html("");
			$('#sectionid').append('<option value="all">----------Select----------</option>');
			
		}
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
 		getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#sortingby").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($("#sortingby").val()=="House"){
			$("#sortingorders").hide();
			$("#house").show();
			getHouseList();
		}
		else{
			$("#sortingorders").show();
			$("#house").hide();
		}
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
			 $("#ASC").hide();
			 $("p1").hide();
			 $("#DESC").hide();
			 $("p2").hide();
			 
			 	$("#Female").show();
		        $("p3").show();
		        $("#Male").show();
		        $("p4").show();
			}else{	
				orderby=$("input[name='sorting1']:checked").val();
				$("#Male").hide();	
				$("p3").hide();
		        $("#Female").hide();
		        $("p4").hide();
		        
				 $("#ASC").show();
				 $("p1").show();
				 $("#DESC").show();
				 $("p2").show();   
		}
		
		getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("input[name='sorting']:checked").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		
		getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
});

function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({

		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classname').html("");

			$('#classname').append('<option value="all">----------Select----------</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid').html("");
			
			$('#sectionid').append('<option value="all">ALL</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getHouseList(){
	 
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	datalist={
			"locid" : locationid,
			"accyear":accyear,
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getHouseList",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			  $('#housename').html("");
			  $('#housename').append('<option value="">' +"ALL"+ '</option>');
			var j=0;
			var len=result.houseList.length;
			for ( j = 0; j < len; j++) {
				$('#housename').append('<option value="'
						+ result.houseList[j].houseId +'">'+ result.houseList[j].houseName+'</option>');
			}
		}
	});
}


function getStudentListByAnalyticalPerformance(locationid,accyear,classname,sectionid,sortingby,orderby){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			"housename":$("#housename").val(),
			
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentListByAnalyticalPerformance",
			data : datalist,
			async : false,
			beforeSend: function(){
			     $("#loader").show();
			   },
			complete: function(){
			     $("#loader").hide();
			},
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.studentList.length;
					
					if(len > 0){
					for(var i=0;i<len;i++){
					$("#allstudent tbody").append("<tr class='"+result.studentList[i].studentId+" "+result.studentList[i].academicYearId+" "+result.studentList[i].locationId+" "+result.studentList[i].classDetailId+" "+result.studentList[i].sectioncode+" "+result.studentList[i].studentAdmissionNo+" "+"studentid"+"'>"
							+"<td class='"+result.studentList[i].studentId+" "+result.studentList[i].academicYearId+" "+result.studentList[i].locationId+" "+result.studentList[i].classDetailId+" "+result.studentList[i].sectioncode+" "+result.studentList[i].studentAdmissionNo+" "+"studentid"+"'>"+result.studentList[i].count+"</td>" 
							+"<td> "+result.studentList[i].studentAdmissionNo+" </td>"
							+"<td class='studentName'> "+result.studentList[i].studentnamelabel+"</td>"
							+"<td> "+result.studentList[i].studentrollno+" </td>"
							+"<td class='"+result.studentList[i].classname+" "+result.studentList[i].sectionnaem+" "+"classdiv"+"'> "+result.studentList[i].classsection+" </td>"
							+"<td> "+result.studentList[i].houseName+" </td>"
							+"<td><span class='class_name "+result.studentList[i].studentStatus+"'>"+result.studentList[i].studentStatus+"</span></td>"
							+"</tr>");
					}
					studenttable();
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
						
					pagination(100);	
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+len);
					
					$("#allstudent tbody tr").click(function(){
						$("#Dialog").dialog("open");
					});
					
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var housename=$("#housename").val();
	
datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
			"housename":housename,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentAnalyticPerformanceSearchByList",
			data : datalist,
			async : false,
			beforeSend: function(){
			     $("#loader").show();
			   },
			complete: function(){
			     $("#loader").hide();
			},
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.SearchList.length;
					if(len > 0){
					for(var i=0;i<len;i++){
					$("#allstudent tbody").append("<tr class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+result.SearchList[i].studentAdmissionNo+" "+"studentid"+"'>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+result.SearchList[i].studentAdmissionNo+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td class='studentName'> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td class='"+result.SearchList[i].classname+" "+result.SearchList[i].sectionnaem+" "+"classdiv"+"'> "+result.SearchList[i].classsection+" </td>"
							+"<td> "+result.SearchList[i].houseName+" </td>"
							+"<td> <span class='class_name "+result.SearchList[i].studentStatus+"'>"+result.SearchList[i].studentStatus+"</span></td>"
							+"</tr>");
					    }	
					studenttable();
					}
					else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+len);
					
					$("#allstudent tbody tr").click(function(){
						$("#Dialog").dialog("open");
					});
			}
		});
}

function studenttable(){
$("#allstudent tbody tr").click(function(){
		
		$("#Dialog").dialog("open"); 
		stuId=$(this).attr("class").split(" ")[0];
		accyear=$(this).attr("class").split(" ")[1];
		locId=$(this).attr("class").split(" ")[2];
		classId=$(this).attr("class").split(" ")[3];
		SectionId=$(this).attr("class").split(" ")[4];
		AdmissionNo=$(this).attr("class").split(" ")[5];
		
		name=$(this).closest("tr").find(".studentName").text();
		classsection=$(this).closest("tr").find(".classdiv").text();
		
		
		
		className=$(this).find(".classdiv").attr("class").split(" ")[0];
		sectionName=$(this).find(".classdiv").attr("class").split(" ")[1]; 
		
		set=$(this).find(".class_name").attr("class").split(" ")[1];
		studentanalyticalperformanceTableBYStudentId();
		Savebuttonhidefunction();
		 
		$('#student').html("");
		$('#classdiv').html("");
		$('#admissionNo').html("");
		
		$("#student").append(name);
		$("#classdiv").append(classsection);
		$("#admissionNo").append(AdmissionNo);
		$("#hiddenstatus").val(set);
		
		
		 
		if(set=="Set"){
			 
			getStudentAnalyticalPerformanceForUpdate(stuId,accyear);
		} 
	});
}


function getStudentListByHouseWise(){

	var housename = $("#housename").val();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"housename":housename,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentListByHouseWiseAnalyticalPerformance",
			data : datalist,
			async : false,
			beforeSend: function(){
			     $("#loader").show();
			   },
			complete: function(){
			     $("#loader").hide();
			},
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					var i=0;
					var len=result.SearchList.length;
					if(len > 0){
					for(var i=0;i<len;i++){
						$("#allstudent tbody").append("<tr class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+result.SearchList[i].studentAdmissionNo+" "+"studentid"+"'>"
								+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+result.SearchList[i].classDetailId+" "+result.SearchList[i].sectioncode+" "+result.SearchList[i].studentAdmissionNo+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
								+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
								+"<td class='studentName'> "+result.SearchList[i].studentFullName+"</td>"
								+"<td> "+result.SearchList[i].studentrollno+" </td>"
								+"<td class='"+result.SearchList[i].classname+" "+result.SearchList[i].sectionnaem+" "+"classdiv"+"'> "+result.SearchList[i].classsection+" </td>"
								+"<td> "+result.SearchList[i].houseName+" </td>"
								+"<td> <span class='class_name "+result.SearchList[i].studentStatus+"'>"+result.SearchList[i].studentStatus+"</span></td>"
								+"</tr>");
						    }
					studenttable();
					}
					else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+len);
					
					$("#allstudent tbody tr").click(function(){
						$("#Dialog").dialog("open");
					});
					
					pagination(100);
			}
		});
}

function getStudentAnalyticalPerformanceForUpdate(stuId,accyear){
	$("#Dialog").dialog("open");
	 
	$.ajax({
		type:'post',
		url:"studentRegistration.html?method=getStudentAnalyticalPerformanceForUpdate",
		data:{
				"stuId":stuId,
				"accyear":accyear,
			},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);
			 len=$(".allstudent tbody tr").length;
			 $(".allstudent tbody tr").each(function(i){
				 $(this).find("input[value="+result.list[i].selection+"]").attr("checked",true);
				 $(this).find(".selectbox").html("");
			     $(this).find(".selectbox").append(result.list[i].parentsOpinion);
			 });
		   }
	  });
	}

function Savebuttonhidefunction(){
	var datalist = {
			 "stuId":stuId,
			 "accyearId":accyear
	};
		$.ajax({
			type : "POST",
			url : "parentMenu.html?method=Savebuttonhidefunction",
			data : datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				 value = result.status;
               
				if(value == "true"){
					$(".selectheader").show();
					$(".selecttd").show();
					$(".ui-dialog-buttonset button:nth-child(2)").hide();
					$(".ui-dialog-buttonset button:nth-child(1)").show();
					$("input[type=radio]").attr('disabled', true);
					value = true;
					
				}else{
					$(".ui-dialog-buttonset button:nth-child(2)").show();
					$(".ui-dialog-buttonset button:nth-child(1)").hide();
					$(".selectheader").hide();
					$(".selecttd").hide();
					
					value = false;
				}
			}
		});
	return value;
}

function studentanalyticalperformanceTableBYStudentId(){

	 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentAnalyticalPerformanceTable",
			async : false,
			
			 success : function(response) {
				var result = $.parseJSON(response);
				
					var i=0;
					var len=result.studentAnalyticPerformanceList.length;
					$(".allstudent tbody").empty();
					if(len > 0){
						
					for(var i=0;i<len;i++){
						 
					$(".allstudent tbody").append("<tr class='"+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" rowId'>"
							+"<td> "+result.studentAnalyticPerformanceList[i].sno+"</td>" 
							+"<td class='remarknames'> "+result.studentAnalyticPerformanceList[i].remarksName+"<font color='red'>*</font> </td>"
							+"<td><input type='radio' class='checked' name="+result.studentAnalyticPerformanceList[i].remarksName+" value='excellent' id="+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" /></td>"
							+"<td><input type='radio' class='checked' name="+result.studentAnalyticPerformanceList[i].remarksName+" value='verygood' id="+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" /></td>"
							+"<td><input type='radio' class='checked' name="+result.studentAnalyticPerformanceList[i].remarksName+" value='good' id="+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" /></td>"
							+"<td><input type='radio' class='checked' name="+result.studentAnalyticPerformanceList[i].remarksName+" value='average' id="+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" /></td>"
							+"<td><input type='radio' class='checked' name="+result.studentAnalyticPerformanceList[i].remarksName+" value='needcare' id="+result.studentAnalyticPerformanceList[i].analyticalRemarksId+" /></td>"
							+"<td class='"+result.studentAnalyticPerformanceList[i].remarksName+"1 "+result.studentAnalyticPerformanceList[i].remark+"1 selecttd'>" +
							"<span class='selectbox'>"+result.studentAnalyticPerformanceList[i].parentsOpinion+"</span></td>"
							+"</tr>");
					   }	
					}
					else{
						$(".allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					
			}
		});
}

function empty(){
	$("input[name='Appearance']").attr("checked",false);
	  $("input[name='Class Involvement']").attr("checked",false);
	  $("input[name='Learning Capacity']").attr("checked",false);
	  $("input[name='Home Works']").attr("checked",false);
	  $("input[name='Reading']").attr("checked",false);
	  $("input[name='Writing']").attr("checked",false);
	  $("input[name='English Fluency']").attr("checked",false);
	  $("input[name='Activities (E/C/A)']").attr("checked",false);
	  $("input[name='Bag/ Books']").attr("checked",false);
	  $("input[name='Behaviour']").attr("checked",false);
}
function cleartable(){
	$("#allstudent tbody").empty("");
	pagination(100);	
	$(".numberOfItem").empty();
	$(".numberOfItem").append("No. of Records  "+0);
}

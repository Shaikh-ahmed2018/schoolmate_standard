$(document).ready(function(){
	 
	Savebuttonhidefunction();
	
	studentanalyticalperformanceBYStudentId();
	
	   $(".Appearance").change(function(){
		   AppearanceSelectOthers(); 
	     });
	   
	   
	   $(".Involvement").change(function(){
		   InvolvementSelectOthers();
	     });
	
	   $(".Capacity").change(function(){
		   CapacitySelectOthers();
	     });
	   
	   $(".Works").change(function(){
		   WorksSelectOthers();
	     });
	   
	   $(".Reading").change(function(){
		   ReadingSelectOthers();
	     });
	   
	   $(".Writing").change(function(){
		   WritingSelectOthers();
	     });
	   
	   $(".Fluency").change(function(){
		   FluencySelectOthers();
	     });
	   
	   $(".ECA").change(function(){
		   ECASelectOthers();
	     }); 
	     
	   $(".Books").change(function(){
	       BooksSelectOthers();    
	    });  
		     
		$(".Behaviour").change(function(){
			BehaviourSelectOthers();	  
		});
	 
		    stuId=$("#parentchild").val();     
		    accyearId=$("#hiddenaccyearId").val(); 
		    
		   
		    getStudentAnalyticalPerformanceForUpdate();
		    
		    getStudentNameAndAdmissionNo();
		    
		    $("#parentchild").change(function(){
		    	 
		    	studentanalyticalperformanceBYStudentId();
		    	getStudentAnalyticalPerformanceForUpdate();
		    	getStudentNameAndAdmissionNo();
		    	 
		    	Savebuttonhidefunction();
		    	 
		    	 $(".Appearance").change(function(){
		    		 AppearanceSelectOthers();
		  	     });
		    	 
		    	 $(".Involvement").change(function(){
		  		   InvolvementSelectOthers();
		  	     });
		    	 
		    	 $(".Capacity").change(function(){
		  		   CapacitySelectOthers();
		  	     });
		    	 $(".Works").change(function(){
		  		   WorksSelectOthers();
		  	     });
		    	 $(".Reading").change(function(){
		  		   ReadingSelectOthers();
		  	     });
		    	 
		    	 $(".Writing").change(function(){
		  		   WritingSelectOthers();
		  	     });
		    	 $(".Fluency").change(function(){
		  		   FluencySelectOthers();
		  	     });
		    	 $(".ECA").change(function(){
		  		   ECASelectOthers();
		  	     });
		    	 $(".Books").change(function(){
		  	       BooksSelectOthers();    
		  	    });
		    	$(".Behaviour").change(function(){
		 			BehaviourSelectOthers();	  
		 		});
		    });  
		    
		    
	opinion=[];
	remarksname=[];
	flag=false;
	
	$("#save").click(function(){
	   
	$(".allstudent tbody tr").each(function(){
		p=$(this); 
		var list=$(this).find(".remarks").val()+"--"+$(this).find(".otherval").val()+"--"+$(this).find(".excellent").text()+"--"+$(this).find(".verygood").text()+"--"+$(this).find(".good").text()+"--"+$(this).find(".average").text()+"--"+$(this).find(".needcare").text()+"--"+$(this).attr("class");
		opinion.push(list);
		var remarklist=$(this).find(".remarks").val();
		remarksname.push(p.find(".remarknames").text());
		if(($(this).find(".remarks").val()=="Others") && $(this).find(".otherval").val()==""){
			point=$(this).find(".remarknames").text();
			value="true";
			return false;
		}
		else if($(this).find(".remarks").val()=="all"){
			point=$(this).find(".remarknames").text();
			value="true";
			return false;
		}else{
			 value="false";
		}
	});
					 
	if(value!="true"){
		var datalist = {
			   'opinion':opinion.toString(),
			   'stuId':$("#parentchild").val(),
			   'locId':$("#hiddenlocId").val(),
			   'classId':$("#hiddenclassId").val(),
			   'sectionId':$("#hiddensectionId").val(),
  };
			            	  
	$.ajax({
									type : "POST",
									url : "parentMenu.html?method=SaveStudentAnalyticalPerformanceByParent",
									data : datalist,
									async : false,
									success : function(response) {
										var result = $.parseJSON(response);
										 
										if (result.status=="true") {
											$(".successmessagediv").show();
											$(".sucessmessage") .text("Adding Analytical Performance Successfully...");
											
											$(".successmessagediv").delay(3000).slideUp("slow");
											setTimeout(function(){
												window.location.href = "parentMenu.html?method=studentanalyticalperformance";
												});
										}
									}
							 });
						   
					   }
					  else{
						  $(".errormessagediv").show();
							 $(".validateTips").text("Required Parent Opinion on Student Remarks - "+point);
							 $(".errormessagediv").delay(5000).fadeOut();
				          } 
	    });
     });

function getStudentAnalyticalPerformanceForUpdate(){
	stuId=$("#parentchild").val();     
    accyearId=$("#hiddenaccyearId").val(); 
	$.ajax({
		type:'post',
		url:"parentMenu.html?method=getStudentAnalyticalPerformanceDetailForUpdate",
		data:{
				"stuId":stuId,
				"accyearId":accyearId,
			},
		asunc:false,
		success:function(data){
			var result =$.parseJSON(data);
			 len=$(".allstudent tbody tr").length;
			 $(".allstudent tbody tr").each(function(i){
				 
				 if(result.list[i].parentsOpinion=="Others"){
					 
					  $(this).find(".remarks ").hide();
					  $(this).find(".select").append('<input type="text" style="margin-left:1%;width: 136px;" class="otherval" name="otherval"> <span class="glyphicon glyphicon-repeat position"></span>');			  
					  $(this).find(".otherval").val(result.list[i].otherOpinion);
					  
					  pointer= $(this);
					  selectothers(pointer);
				 }
				 else if(result.list[i].parentsOpinion==""){
					 $(this).find(".remarks").val('all');
					 
				 }
				 else{
					 
					 $(this).find(".remarks").val(result.list[i].parentsOpinion);
				 }
				 
				    
			 });
		   }
	  });
	}

 

function studentanalyticalperformanceBYStudentId(){

	var stuId = $("#parentchild").val();
	 
    datalist = {
			"stuId" :stuId 
		},
		$.ajax({
			type : 'POST',
			url : "parentMenu.html?method=studentanalyticalperformanceBYStudentId",
			data : datalist,
			async : false,
			
			 success : function(response) {
				var result = $.parseJSON(response);
				
					var i=0;
					var len=result.studentAnalyticPerformanceList.length;
					$(".allstudent tbody").empty();
					if(len > 0){
						
					for(var i=0;i<len;i++){
						 
					$(".allstudent tbody").append("<tr class='"+result.studentAnalyticPerformanceList[i].analyticalRemarksId+"'>"
							+"<td> "+result.studentAnalyticPerformanceList[i].sno+"</td>" 
							+"<td class='remarknames'> "+result.studentAnalyticPerformanceList[i].remarksName+" </td>"
							+"<td><span class='excellent'>"+result.studentAnalyticPerformanceList[i].excellent+"</span></td>"
							+"<td><span class='verygood'>"+result.studentAnalyticPerformanceList[i].verygood+"</span></td>"
							+"<td><span class='good'>"+result.studentAnalyticPerformanceList[i].good+"</span></td>"
							+"<td><span class='average'>"+result.studentAnalyticPerformanceList[i].average+"</span></td>"
							+"<td><span class='needcare'>"+result.studentAnalyticPerformanceList[i].needcare+"</span></td>"
							+"<td class='"+result.studentAnalyticPerformanceList[i].remarksName+"1 "+result.studentAnalyticPerformanceList[i].remarksName+"1 select'>" +
									"<select class='"+result.studentAnalyticPerformanceList[i].remarksName+" "+result.studentAnalyticPerformanceList[i].remark+" remarks'> <option value='all'>-------select-------</option>" +
											"<option value='Y'>Y</option>" +
											"<option value='N'>N</option>" +
											"<option value='Y/Be Strict'>Y / Be Strict.</option>" +
											"<option value='Be Strict'>Be Strict.</option>" +
											"<option value='Will take care'>Will take care.</option>" +
											"<option value='Others'>Others</option></select>" +
											"</td>"
							+"</tr>");
					}	
					}
					else{
						$(".allstudent tbody").append("<tr>"+"<td colspan='8'>No Records Founds</td>" +"</tr>");
					}
					
			}
		});
}

function selectothers(pointer){
	pointer.find(".position").click(function(){
		 pointer.find(".remarks").show();
		 pointer.find(".remarks").val("all");
		 pointer.find(".otherval").hide();
		 pointer.find(".otherval").val("");
		 pointer.find(".position").hide();
		});
   }

function getStudentNameAndAdmissionNo(){
	  
	$.ajax({
		type:'post',
		url:"parentMenu.html?method=getStudentNameAndAdmissionNo",
		data:{
			 "stuId":$("#parentchild").val(),
			  "accyearId":$("#hiddenaccyearId").val(),
			},
		asunc:false,
		success:function(response){
			var result =$.parseJSON(response);
			$("#student").text(result.data[0].studentname);
			$("#admissionNo").text(result.data[0].stdAdmisiionNo);
			$("#classdiv").text(result.data[0].classSection);
		 }
	});
	}

function AppearanceSelectOthers(){
	if($(".Appearance").val()=="Others"){ 
		   $(".Appearance").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Appearance1").append('<input type="text" style="margin-left:1%;width: 136px;" class="appothers otherval" name="appearance" id="appothers"> <span class="glyphicon glyphicon-repeat appiconposition position" id="appicon"></span>');
		
		  $(".appiconposition").click(function(){
				$(".Appearance").show();
				$(".Appearance").val("all");
				 $(".appothers").hide();
				 $(".appothers").val("");
				 $(".appiconposition").hide();
			});
	      } 
}

function InvolvementSelectOthers(){
	if($(".Involvement").val()=="Others"){ 
		   $(".Involvement").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Involvement1").append('<input type="text" style="margin-left:1%;width: 136px;" class="classInvolothers otherval" name="classInvolothers" id="classInvolothers"> <span class="glyphicon glyphicon-repeat classInvoliconposition position" id="classInvoliconposition"></span>');
		
		  $(".classInvoliconposition").click(function(){
				$(".Involvement").show();
				$(".Involvement").val("all");
				 $(".classInvolothers").hide();
				 $(".classInvolothers").val("");
				 $(".classInvoliconposition").hide();
			});
	      } 
 }
  
function CapacitySelectOthers(){
	if($(".Capacity").val()=="Others"){ 
		   $(".Capacity").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Capacity1").append('<input type="text" style="margin-left:1%;width: 136px;" class="learncapothers otherval" name="learncapothers" id="learncapothers"> <span class="glyphicon glyphicon-repeat learncapiconposition position" id="learncapiconposition"></span>');
		
		  $(".learncapiconposition").click(function(){
				$(".Capacity").show();
				$(".Capacity").val("all");
				 $(".learncapothers").hide();
				 $(".learncapothers").val("");
				 $(".learncapiconposition").hide();
			});
	      }
  }

function WorksSelectOthers(){
	if($(".Works").val()=="Others"){ 
		   $(".Works").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Works1").append('<input type="text" style="margin-left:1%;width: 136px;" class="homeworkothers otherval" name="homeworkothers" id="homeworkothers"> <span class="glyphicon glyphicon-repeat homeworkiconposition position" id="homeworkiconposition"></span>');
		
		  $(".homeworkiconposition").click(function(){
				$(".Works").show();
				$(".Works").val("all");
				 $(".homeworkothers").hide();
				 $(".homeworkothers").val("");
				 $(".homeworkiconposition").hide();
			});
	      } 
  }

function ReadingSelectOthers(){
	if($(".Reading").val()=="Others"){ 
		   $(".Reading").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Reading1").append('<input type="text" style="margin-left:1%;width: 136px;" class="readothers otherval" name="readothers" id="readothers"> <span class="glyphicon glyphicon-repeat readiconposition position" id="readiconposition"></span>');
		
		  $(".readiconposition").click(function(){
				$(".Reading").show();
				$(".Reading").val("all");
				 $(".readothers").hide();
				 $(".readothers").val("");
				 $(".readiconposition").hide();
			});
	      }
 }

function WritingSelectOthers(){
	if($(".Writing").val()=="Others"){ 
		   $(".Writing").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Writing1").append('<input type="text" style="margin-left:1%;width: 136px;" class="writeothers otherval" name="writeothers" id="writeothers"> <span class="glyphicon glyphicon-repeat writeiconposition position" id="writeiconposition"></span>');
		
		  $(".writeiconposition").click(function(){
				$(".Writing").show();
				$(".Writing").val("all");
				 $(".writeothers").hide();
				 $(".writeothers").val("");
				 $(".writeiconposition").hide();
			});
	      }
 }

function FluencySelectOthers(){
	if($(".Fluency").val()=="Others"){ 
		   $(".Fluency").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Fluency1").append('<input type="text" style="margin-left:1%;width: 136px;" class="englishfluencyothers otherval" name="englishfluencyothers" id="englishfluencyothers"> <span class="glyphicon glyphicon-repeat englishfluencyiconposition position" id="englishfluencyiconposition"></span>');
		
		  $(".englishfluencyiconposition").click(function(){
				$(".Fluency").show();
				$(".Fluency").val("all");
				 $(".englishfluencyothers").hide();
				 $(".englishfluencyothers").val("");
				 $(".englishfluencyiconposition").hide();
			});
	      }
  }

function ECASelectOthers(){
	if($(".ECA").val()=="Others"){ 
		   $(".ECA").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Activities").append('<input type="text" style="margin-left:1%;width: 136px;" class="activitiesothers otherval" name="activitiesothers" id="activitiesothers"> <span class="glyphicon glyphicon-repeat activitiesiconposition position" id="activitiesiconposition"></span>');
		
		  $(".activitiesiconposition").click(function(){
				$(".ECA").show();
				$(".ECA").val("all");
				 $(".activitiesothers").hide();
				 $(".activitiesothers").val("");
				 $(".activitiesiconposition").hide();
			});
	      } 
 }
function BooksSelectOthers(){
	if($(".Books").val()=="Others"){ 
		   $(".Books").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Books1").append('<input type="text" style="margin-left:1%;width: 136px;" class="bagbooksothers otherval" name="bagbooksothers" id="bagbooksothers"> <span class="glyphicon glyphicon-repeat bagbooksiconposition position" id="bagbooksiconposition"></span>');
		
		  $(".bagbooksiconposition").click(function(){
				$(".Books").show();
				$(".Books").val("all");
				 $(".bagbooksothers").hide();
				 $(".bagbooksothers").val("");
				 $(".bagbooksiconposition").hide();
			});
	      }
}

function BehaviourSelectOthers(){
	 if($(".Behaviour").val()=="Others"){ 
		   $(".Behaviour").hide();
		   $(".allstudent tbody tr").closest("tr").find(".Behaviour1").append('<input type="text" style="margin-left:1%;width: 136px;" class="behaviourothers otherval" name="otherval" id="behaviourothers"> <span class="glyphicon glyphicon-repeat behaviouriconposition position" id="behaviouriconposition"></span>');
		
		  $(".behaviouriconposition").click(function(){
				$(".Behaviour").show();
				$(".Behaviour").val("all");
				$(".behaviourothers").hide();
				$(".behaviourothers").val("");
				$(".behaviouriconposition").hide();
			});
	      } 
}

function Savebuttonhidefunction() {
	
	var datalist = {
			 "stuId":$("#parentchild").val(),
			 "accyearId":$("#hiddenaccyearId").val()
	};
		$.ajax({
			type : "POST",
			url : "parentMenu.html?method=Savebuttonhidefunction",
			data : datalist,
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				status = result.status;
                
				if(status == "true"){
					status = true;
					$("#save").hide();
				}else{
					$("#save").show();
					status = false;
				}
			}
		});
	return status;

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
	$('.success').hide();
	$(".error").show();
	$(".errormsge").text(errorMessage);
	$(".error").delay(2000).fadeOut();
}

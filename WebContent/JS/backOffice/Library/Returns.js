$(document).ready(function(){
	$("#issueId").val($("#hiddenissueId").val());
	
if($("#hiddenusetype").val()=="Student"){
		$("input[name='requested_by'][value='Student']").prop('checked','checked');
		$("#teacher").hide();
		$("#studentname").show();
		
		$("#othersname").hide();
		$("#other").hide();
		
		$(".admissionno").show();
		$("#class").show();
		$("#divission").show();
		$("#department").hide();
		$("#designation").hide();
		$("#contactno").hide();
		$("#addr").hide();
		
		$("#studentname").prop("disable","disable");
		$("#student").attr("disable",true);
		$("#student1").attr("disable",true);
		$("#userType").prop("readonly",true);
		$("#accession_no").prop("readonly",true);
		$("#Fromdate").attr('readonly','readonly');
		
		
		$("#back").hide();
		$("#save").hide();
		$("#back1").show();
			      
		$("#usertype").show();
		$("#radiotype").text("Student");
	}
	else if($("#hiddenusetype").val()=="staff"){
		
		$("input[name='requested_by'][value='Teacher']").prop('checked','checked');
		
		$("#teacher").show();
		$("#studentname").hide();
		$("#student1").hide();
		$("#othersname").hide();
		$("#other").hide();
		$("#userType").prop("readonly",true);
		$("#accession_no").prop("readonly",true);
		$("#Fromdate").attr('readonly','readonly');
		
		$("#back1").show();
		$("#back").hide();
		$("#issue").hide();
		
		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		$("#department").show();
		$("#designation").show();
		$("#contactno").hide();
		$("#addr").hide();
		$("#save").hide();
		$("#usertype").show();
		$("#radiotype").text("Teacher");
	}
	
     else if($("#hiddenusetype").val()=="others"){
		
    	 $("input[name='requested_by'][value='other']").prop('checked','checked');
    	
    	$("#other").show();
 		$("#teacher").hide();
 		$("#studentname").hide();
 		$("#student1").hide();
 		
 		$("#userType").prop("readonly",true);
 		$("#accession_no").prop("readonly",true);
 		$("#Fromdate").attr('readonly','readonly');
 		
 		$("#back1").show();
 		$("#back").hide();
 		$("#issue").hide();
 		
 		$(".admissionno").hide();
		$("#class").hide();
		$("#divission").hide();
		$("#department").hide();
		$("#designation").hide();
		
		$("#hiddenotheraddr").val($("#otheradderss").val()).show();
		$("#otheradderss").show();
		
		$("#contactno").show();
		$("#addr").show();
		$("#save").hide();
 		$("#usertype").show();
 		$("#radiotype").text("Other");
	}
	
    
	$("#back1").click(function(){
	    window.location.href ="LibraryMenu.html?method=IssueReturnBySubScriberType&historysubscriberType="+$("#historysubscriberType").val()
	    +"&accyear="+$("#historyacademicId").val()+"&historygoto="+$("#historygoto").val()+
	    "&historystartedby="+$("#historystartedby").val()+"&historysearch="+$("#historysearch").val()+
	    "&historyorderby="+$("#historyorderby").val()+"&historyclassname="+$("#historyclassname").val()
	    +"&historysectionid="+$("#historysectionid").val()+"&historydepartment="+$("#historydepartment").val()+
	    "&historydesignation="+$("#historydesignation").val()+"&location="+$("#historylocId").val()
	    +"&issueId="+$("#issueId").val()+"&subId="+$("#subId").val()+"&subscriberType="+$("#subscriberType").val();
	});
	
	$("#Fromdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","maxDate",selectedDate);
		}
	});	
	$("#Lastdate").datepicker({
		dateFormat : "dd-mm-yy",
		minDate : 0,
		changeMonth :true,
		changeYear : true,
		onClose : function(selectedDate) {
			$("#todate").datepicker("option","minDate",selectedDate);
		}
	});	
	
		$("input[name='requested_by']").change(function(){
	
			$("#userType,#admissionno,#hiddenclassId,#hiddendivissionId").val("");
			$("#accession_no,#item,#bookTitle,#author,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			
			$("#Fromdate,#Lastdate,#hiddendepartment,#hiddendesignation,#hiddendivissionId").val("");
			$("#hiddencontactno,#hiddenotheraddr").val("");
			
				if($(this).val()=="Teacher")
					{
					$("input[name='usertype']").val("");
						$("#usertype").show();
						$(".admissionno").hide();
						$("#class").hide();
						$("#divission").hide();
						
						$("#department").show();
						$("#designation").show();
						$("#contactno").hide();
						$("#addr").hide();
						
						$("#radiotype").text("Subscriber No").append("<label><font color='red'>  *</font></label>");
					}
					else if($(this).val()=="Student"){
						$("input[name='usertype']").val("");
						$("#usertype").show();
						$(".admissionno").show();
						$("#class").show();
						$("#divission").show();
					    
						$("#department").hide();
						$("#designation").hide();
						$("#contactno").hide();
						$("#addr").hide();
						
						$("#radiotype").text("Subscriber No").append("<label><font color='red'>*</font></label>");
		          }
					else if($(this).val()=="other"){
						$("input[name='usertype']").val("");
						$("#usertype").show();
						
						$(".admissionno").hide();
						$("#class").hide();
						$("#divission").hide();
						
						$("#department").hide();
						$("#designation").hide();
						
						$("#contactno").show();
						$("#addr").show();
						
						$("#radiotype").text("Subscriber No").append("<label><font color='red'> *</font></label>");
		          }
				
				
		});
		$("#locationname").change(function(){
			$("#searchvalue").val("");
			
			//getClassList();
			var classname=$("#classname").val();
			//getSectionList(classname);
		});
		
		var userType=$("input[name='requested_by']:checked").val();
		
          $("#userType").autocomplete({
			
			source : function(request,response) { 
				
				if($("input[name='requested_by']:checked").val() == "Student")
				{
					$.ajax({

					url : "LibraryMenu.html?method=studentSearchbyadmissionNo",
					data : {
						searchTerm : request.term,
						locid : $("#locationname").val()
					},
					
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.subscriberNumber,
								value : item.studentId,
							}
						}));
					}
				});
			    }
		else if($("input[name='requested_by']:checked").val() == "Teacher")
		        { 
		           	$.ajax({
		           		url : "LibraryMenu.html?method=teacherSearchbyId",
		           		
				 	data : {
						searchTerm : request.term,
						locid : $("#locationname").val()
					},
					
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						
						response($.map(	result.jsonResponse,function(item) {
							return {
								label : item.subscriberNumber, 
								value : item.teacherId,
							}
						}));
					}
				});
			    }
		else if($("input[name='requested_by']:checked").val() =="other")
        { 
           	$.ajax({
           		url : "LibraryMenu.html?method=othersSearchbyId",
           		
		 	data : {
				searchTerm : request.term,
				locid : $("#locationname").val()
			},
			
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				
				response($.map(	result.jsonResponse,function(item) {
					return {
						label : item.subscriberNumber, 
						value : item.teacherId,
					}
				}));
			}
		});
	    }
				
			},
			select : function(event, ui) {

				var searchTerm = ui.item.value;

				studentDetails = {
						'searchTerm' : searchTerm.split("-")[0]
				};
				if($("input[name='requested_by']:checked").val() =="Student"){
					getSudentDetails(searchTerm.split("-")[0]);
				}
				else if($("input[name='requested_by']:checked").val() =="Teacher"){
					getTeacherDetails(searchTerm.split("-")[0]);
				}
                 else if($("input[name='requested_by']:checked").val() =="other"){
                	 getOtherDetails(searchTerm.split("-")[0]);
				}

				$("#userType").val(ui.item.label);
				$("#hiddenstuid").val(ui.item.value);
				return false;
			}
		
     });
          
          $('#accession_no').click(function() {
      		if($("input[name='requested_by']:checked").val()== undefined || $("input[name='requested_by']:checked").val()==""){
      			$(".errormessagediv").show();
      			$(".validateTips").text(" First Select The Subscriber Type ");
      						setTimeout(function() {
      				$('.errormessagediv').fadeOut();
      			}, 3000);
      		}
      	});
          $("#save").click(function() {
        	  if($("input[name='requested_by']:checked").val()==undefined){
        		  $(".validateTips").text(" Field Required- Issued To ");
        		  showError("#student");
        			$(".errormessagediv").show();
        						setTimeout(function() {
        				$('.errormessagediv').fadeOut();
        			}, 3000);
        			return false;
        		}
        	  else  if($("#userType").val()=="" ||$("#userType").val()==null){
        			$(".errormessagediv").show();
        			$(".validateTips").text("Field Required -Subscriber No");
        			showError("#userType");
        			setTimeout(function() {
        				$('.errormessagediv').fadeOut();
        			}, 3000);
        			return false;
        		}
        	  else if($("#accession_no").val()=="" ||$("#accession_no").val()==null){
      			$(".errormessagediv").show();
      			$(".validateTips").text(" Field required-Accession Number ");
      			showError("#accession_no");
      			setTimeout(function() {
      				$('.errormessagediv').fadeOut();
      			}, 3000);
      			return false;
      		}	
        	  else if($("#itemtype").val()=="" || $("#itemtype").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required -Item Type");
					showError("#itemtype");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
			 else if($("#bookTitle").val()=="" || $("#bookTitle").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required -Book Title");
					showError("#bookTitle");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
			 else if($("#author").val()=="" || $("#author").val()==undefined){
					$(".errormessagediv").show();
					$(".validateTips").text("Field Required -Author");
					showError("#author");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
			 }
      	else if($("#Fromdate").val()=="" || $("#Fromdate").val()==undefined){
      					$(".errormessagediv").show();
      					$(".validateTips").text("Field Required -Issued Date");
      					showError("#Fromdate");
      					setTimeout(function() {
      						$('.errormessagediv').fadeOut();
      					}, 3000);
      					return false;
      			 }	
      	else if($("#Lastdate").val()=="" || $("#Lastdate").val()==undefined){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required -Returned Date");
				showError("#Lastdate");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		 }
      		else{
      			
      		datalist = {
      				"accession_no":$("#hidden_accessionNo").val().trim(),	
      				"itemtype" : $("#itemtype").val().trim(),
      				"bookTitle" : $("#bookTitle").val().trim(),
      				"author" : $("#author").val().trim(),
      				"category" : $("#category").val().trim(),
      				"ddc" : $("#ddc").val().trim(),
      				"currentstatus" : $("#currentstatus").val().trim(),
      				"userId" : $("#hiddenstuid").val().trim(),
      				"usertype":$("input[name='requested_by']:checked").val(),
      				"shelfNo" : $("#shelfNo").val().trim(),
      				"locationname" : $("#hiddenlocation").val().trim(),
      				"fromdate" : $("#Fromdate").val().trim(),
      				"Lastdate" : $("#Lastdate").val(),
      				"issueId" : $("#issueId").val(),
      		},
      		$.ajax({ 
      			type : "POST",
      			url : "LibraryMenu.html?method=insertBookReturnDetails",
      			data : datalist,
      			async : false,
      			success : function(data) {
                   
      				var result = $.parseJSON(data);
      				
      				 if(result.status =="success"){
      						$(".successmessagediv").show();
      						$(".validateTips").text("Book Returns Successfully");
      						setTimeout(function() {
      							window.location.href="LibraryMenu.html?method=returns";
      						},3000);
      					}else if(result.status =="fail"){
      						$(".errormessagediv").show();
      						$(".validateTips").text("Book Returns Failed Please Try Again... ");
      						setTimeout(function() {
      							window.location.href="LibraryMenu.html?method=returns";
      						},3000);
      					}
      					}
      					});
      		}
      	});
          
          $("#accession_no").autocomplete({
        	  
  			source : function(request, response) {
  				$("#hidden_accessionNo").val("");
  				$("#hidden_teacherId").val("");
  				$.ajax({
  					url : "LibraryMenu.html?method=getAccessionNo",
  					data:{
  						accessionId : $("#accession_no").val(),
  						subid : $("#hiddenstuid").val().split("-")[1]
  					},
  					async:false,
  					success:function(data) {
  						
  						var result=$.parseJSON(data);
  						if(result.jsonResponse.length ==0){
  							$(".errormessagediv").show();
      						$(".validateTips").text("No Book Issued");
      						setTimeout(function() {
      							$(".errormessagediv").fadeOut();
      						},4000);
  						}else{response($.map(result.jsonResponse,function(item){
  							
  							return {
  								label : item.accessionNo,
  								value : item.accessionNoId
  							}
  					}));}
  						
  					}
  					
  					});
  		},
  		select : function(event, ui)
  		{
  			var searchTerm = ui.item.value;
  			var subscriberId=$("#hiddenstuid").val();
  			var accessionNo=$("#accession_no").val();
  			accessionDetails={
  					'searchTerm' : searchTerm,
  					'subscriberId':subscriberId,
  					'accessionNo':accessionNo,
  			};
  				$.ajax({
  					type:"POST",
  					url:"LibraryMenu.html?method=getBookReturnDetailsByAccessionNo",
  					data :accessionDetails,
  					async : false,
  					success:function(data){
  						var result = $.parseJSON(data);
  						var i=0;
  						var len=result.accessionList.length;
  						if(len >0){
  						for(i=0;i<len;i++){
  							$("#itemtype").val(result.accessionList[i].itemType);
  							$("#item").val(result.accessionList[i].item);
  							$("#bookTitle").val(result.accessionList[i].bookTitle);
  							$("#author").val(result.accessionList[i].author);
  							$("#category").val(result.accessionList[i].category);
  							$("#ddc").val(result.accessionList[i].ddc);
  							$("#currentstatus").val(result.accessionList[i].currentStatus);
  							$("#imagePreview").attr("src",result.accessionList[i].imageurl);
  							$("#shelfNo").val(result.accessionList[i].shelfNo);
  							$("#locationname").val(result.accessionList[i].location);
  							$("#hiddenlocation").val(result.accessionList[i].libLocId);
  							$("#Fromdate").val(result.accessionList[i].issued_date);
  							$("#Lastdate").val(GetTodayDate());
  							$("#issueId").val(result.accessionList[i].issueId);
  							if(result.accessionList[i].item=="Book"){
								$(".Book").show();
							}
							else{
								$(".Book").hide();
							}
  						}
  						}
  						else{
  							$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname,#Fromdate,#Lastdate").val("");
  						}
  					}
  				});
  				$("#accession_no").val(ui.item.label);
  				$("#hidden_accessionNo").val(searchTerm);
  			
  			return false;
  		}
  	});
});

function GetTodayDate() {
	   var tdate = new Date();
	   var dd = tdate.getDate(); //yields day
	   var MM = tdate.getMonth(); //yields month
	   var yyyy = tdate.getFullYear(); //yields year
	   var xxx = dd + "-" +( MM+1) + "-" + yyyy;
	   return xxx;
	}

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
function getSudentDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getStudentIssueDetailsBySubscriberNo",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
				$("#admissionno").val(result.accessionList[i].admissionNo);
				$("#hiddenclassId").val(result.accessionList[i].className);
				$("#hiddendivissionId").val(result.accessionList[i].sectionName);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	
	return false;
}

function getTeacherDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getTeacherIssueDetails",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
				$("#hiddendepartment").val(result.accessionList[i].department);
				$("#hiddendesignation").val(result.accessionList[i].designation);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	return false;
}

function getOtherDetails(searchTerm){
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=getOtherIssueDetails",
		data : {
			"searchTerm" : searchTerm,
			"userType" : $("#userType").val()
		},
		async : false,
		success:function(data){
			var result = $.parseJSON(data);
			var i=0;
			var len=result.accessionList.length;
			if(len >0){
			for(i=0;i<len;i++){
				$("#hiddencontactno").val(result.accessionList[i].contactNo);
				$("#hiddenotheraddr").val(result.accessionList[i].address);
			}
			}
			else{
				$("#itemtype,#bookTitle,#author,#category,#ddc,#currentstatus,#shelfNo,#locationname").val("");
			}
		}
	});
	
	return false;
}
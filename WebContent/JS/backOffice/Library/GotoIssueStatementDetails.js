$(document).ready(function(){

	var size=parseInt($("#hidensize").val());
	if(size==0){
		$("#allstudent tbody").empty();
		$("#allstudent tbody").append("<tr><td colspan=4>No Records Found</td></tr>");
	}
	
	if($("#hiddenusetype").val()=="Student"){
		$("#subscriberno").prop("readonly",true);
		$("#adminssionNo").prop("readonly",true);
		$("#name").prop("readonly",true);
		$("#rollNo").prop("readonly",true);
		$("#className").prop("readonly",true);
		$("#divisionName").prop("readonly",true);
		$("#status").prop("readonly",true);
		
		
		$(".department1").hide();
		$(".designation1").hide();
		$(".othercontact1").hide();
		$("#back1").show();
		
	}
	
	else if($("#hiddenusetype").val()=="staff"){
		
		$("#subscriberNo").prop("readonly",true);
		$("#name").prop("readonly",true);
		$("#department").prop("readonly",true);
		$("#designation").prop("readonly",true);
		$("#status").prop("readonly",true);
		
		$(".adminssionNo1").hide();
		$(".rollNo1").hide();
		$(".className1").hide();
		$(".divisionName1").hide();
		$(".othercontact1").hide();
		$("#back1").show();
		
	}
	
	else if($("#hiddenusetype").val()=="others"){
		
		$("#subscriberNo").prop("readonly",true); 
		$("#name").prop("readonly",true);
		$("#othercontact").prop("readonly",true);
		$("#status").prop("readonly",true);
		
		$(".adminssionNo1").hide();
		$(".rollNo1").hide();
		$(".className1").hide();
		$(".divisionName1").hide();
		
		$(".department1").hide();
		$(".designation1").hide();
		$("#back1").show();
		
	}
	
	$("#back1").click(function(){
	    window.location.href ="LibraryMenu.html?method=SearchSubscriberDetails&historysubscriberType="+$("#historysubscriberType").val()
	    +"&historyacademicId="+$("#historyacademicId").val()+"&historygoto="+$("#historygoto").val()+
	    "&historystartedby="+$("#historystartedby").val()+"&historysearch="+$("#historysearch").val()+
	    "&historyorderby="+$("#historyorderby").val()+"&historyclassname="+$("#historyclassname").val()
	    +"&historysectionid="+$("#historysectionid").val()+"&historydepartment="+$("#historydepartment").val()+
	    "&historydesignation="+$("#historydesignation").val()+"&historylocId="+$("#historylocId").val();
	});
	
	$("#allstudent tbody tr").click(function(){
		
		var subscriberId=$("#subscriberId").val();
		var subscriberType=$("#hiddenusetype").val();
		//var subscriberType=$("input[name='requested_by']:checked").val();
		var issuedid = $(this).closest(".issuedid").attr("id");
		var hiddenissuedId = $("#IssueId").val();

		
		if(issuedid==undefined){
			$(".errormessagediv").show();
			$(".validateTips").text(" No Records Found ");
            setTimeout(function() {
				    $('.errormessagediv').fadeOut();
			         }, 3000);
		}
		else{
		window.location.href ="LibraryMenu.html?method=issuestatementissue&subId="+subscriberId+
		"&subscriberType="+subscriberType+"&issuedid="+issuedid+
		"&historysubscriberType="+$("#historysubscriberType").val()
	    +"&historyacademicId="+$("#historyacademicId").val()+"&historygoto="+$("#historygoto").val()+
	    "&historystartedby="+$("#historystartedby").val()+"&historysearch="+$("#historysearch").val()+
	    "&historyorderby="+$("#historyorderby").val()+"&historyclassname="+$("#historyclassname").val()
	    +"&historysectionid="+$("#historysectionid").val()+"&historydepartment="+$("#historydepartment").val()+
	    "&historydesignation="+$("#historydesignation").val()+"&historylocId="+$("#historylocId").val();
		}
	});
});
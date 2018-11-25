$(document).ready(function(){
	$("#back1").click(function(){
		window.location.href="bankBranchMaster.html?method=bankbranchList&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
	});
	
	var hbankbranchId=$("#hbankBranchId").val();
	if(hbankbranchId != "" || hbankbranchId != undefined){
		var hbankbranchStatus=$("#hbankBranchStatus").val();
		var hbankId=$("#hbankId").val();
		/*$("#status option[value="+hbankbranchStatus+"]").attr('selected','true');*/
		$("#bankname option[value="+hbankId+"]").attr('selected','true');
	}
	
	
	
	$("#savechanges").click(function(){
			
		var names=$("#bankname").val().trim();
		var branchnames=$("#branchname").val().trim();
		var branchshortnames = $("#branchshortname").val().trim();
		var ifsccodes=$("#ifsccode").val().trim();
		var address = $("#address").val().trim();
		
		var ifsc = /^[A-Za-z]{4}[a-zA-Z0-9]{7}$/;
		 
		if(names == "" || names == undefined){
			$('.errormessagediv').show();
			$(".validateTips").text("Field Required - Bank Name.");
			$("#bankname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#bankname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
		else if(branchnames == "" || branchnames==undefined){
			$('.errormessagediv').show();
			$(".validateTips").text("Field Required - Branch Name.");
			$("#branchname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#branchname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
        else if(branchshortnames == "" || branchshortnames==undefined){
        	$('.errormessagediv').show();
			$(".validateTips").text("Field Required - Branch Short Name.");
			$("#branchshortname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#branchshortname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		     }
        else if( ifsccodes == "" || ifsccodes == undefined){
        	$('.errormessagediv').show();
			$(".validateTips").text("Field Required - IFSC Code.");
			$("#ifsccode").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#ifsccode").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
		else if( ifsccodes.trim() != "" && !ifsc.test(ifsccodes)){
        	$('.errormessagediv').show();
			$(".validateTips").text("Enter valid IFSC Code");
			$("#ifsccode").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#ifsccode").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
		else if(address == "" || address == undefined){
        	$('.errormessagediv').show();
			$(".validateTips").text("Field Required - Address.");
			$("#address").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#address").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
       
       else if(checkBranchExist()){
        /*	$('.errormessagediv').show();
			$(".validateTips").text("IFSC Code And Branch Already Registerd...");
			$("#ifsccode").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});*/
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
        	return false;
        }
        else{
        	
        	$.ajax({
        		    type: "post" ,
        			url:"bankBranchMaster.html?method=saveBranch",
        			data:{
        				"bankname":names,
        				"branchname":branchnames,
        				"branchshortname":branchshortnames,
        				"ifsccode": ifsccodes ,
        				"address": address ,
        				"hbankbranchId" :hbankbranchId,
        			      },
        		    async:false,
        		    success : function(data)
        		    {
        		 		var result = $.parseJSON(data);
        				if(result.result == "success")
        				{
        					$('.errormessagediv').hide();
    						$(".successmessagediv").show();
    						$(".successmessage").text("Bank Branch Adding Progressing...");
    						setTimeout(function() {
    							window.location.href="bankBranchMaster.html?method=bankbranchList&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
    						}, 2000);
        					
        				}
        				else if(result.result == "update"){
    						$('.errormessagediv').hide();
    						$(".successmessagediv").show();
    						$(".successmessage").text("Bank Branch Updating Progressing...");
    						setTimeout(function() {
    							window.location.href="bankBranchMaster.html?method=bankbranchList&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
    						}, 2000);
        				}
        				else
        				{
        					$('.errormessagediv').show();
    						$(".successmessagediv").hide();
    						$(".validateTips").text("Bank Branch Addition Failure, Try Again...");
    						setTimeout(function() {
    							window.location.href="bankBranchMaster.html?method=bankbranchList&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
    						}, 2000);
        				}
        		    }
        	    });
             }
	  });
	
});

function checkBranchExist()
{
	var flag=false;
	var ifsccode = $("#ifsccode").val().trim();
	var hifsccode = $("#hifscCode").val().trim();

	if(ifsccode != hifsccode ){
		
		$.ajax({
			
			type:"post",
			url:'bankBranchMaster.html?method=checkBranch',
			data : {"ifsccode":ifsccode},
			async:false,
			
			success : function(data){
				var result = $.parseJSON(data);
				
				if(result.status == "inactive")
				{
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("This IFSC Code And Branch Already Exist !! Make it Active");
					flag = true;
				}
				 else if (result.status=="true") {
						$(".errormessagediv").show();
						$(".validateTips").text("IFSC Code And Branch Already Registerd...");
						flag = true;

					} else{
					flag = false;
				}
			}
		});
	}
	else {
		flag=false;
	}
	
	return flag;
}
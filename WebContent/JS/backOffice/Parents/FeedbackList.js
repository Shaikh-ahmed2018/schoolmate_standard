
$(document).ready(function() {
	
	$(".table tbody tr").each(function(){
		if($(this).closest("tr").find(".downloadFeedBack").attr("id") == "" || $(this).closest("tr").find(".downloadFeedBack").attr("id") == undefined){
			$(this).closest("tr").find(".downloadFeedBack").text("Not Available");
			$(this).closest("tr").find(".downloadFeedBack").css({"text-decoration": "none","color": "red"});
		}else{
			$(this).closest("tr").find(".downloadFeedBack").show();
		}
		
	});

});

function getvaldetails(value){
	
	var s1 =value.id;
	var feedbackid = s1;
	
	$("#feedbackid").val(feedbackid);
}

function downloadfunction(val){
	
	var feedbackUrl = val.id;
	
	if(feedbackUrl == ""|| feedbackUrl == null || feedbackUrl == undefined){
		
		$('.errormessagediv').show();
		$('.validateTips').text("No File To Download");
		$('.errormessagediv').delay(3000).fadeOut();
		return false;
	}
	else{
		window.location.href = "parentMenu.html?method=downloadFeedback&feedback_url="+feedbackUrl;
		
	}
}




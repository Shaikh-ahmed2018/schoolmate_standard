
$(document).ready(function(){
	
	$("div[id^='collapseECS'] .panel-body").each(function(){
		if($(this).find(".row").has("div").length==0){
		$(this).closest(".panel-group").remove();
		}
	});
		
	
	$("#startActivation").click(function(){
		
		$.ajax({
			type:'POST',
			url:'ElectionMenu.html?method=CheckMachineStatus',
			data:{"localIp":$("#LocalIp").text()},
			success:function(response){
				var result=$.parseJSON(response);

				if(result.status=="true"){

					window.location.href="menuslist.html?method=showCategory";
				}
				else{
					//alert("Machine Not Activated");
				}
			}
		});
	
	
	});
	history.pushState(null, null, location.href);
	window.onpopstate = function(event) {
	    history.go(1);
	};
	selectVote();
});
function selectVote(){
	$(".candidate").click(function(){
		voteCast($(this));
		$(this).closest(".panel-group").remove();
		if($(".panel-group").length>0){
			
			selectVote();
			
		}
		else{
	setTimeout(function(){
		window.location.href="menuslist.html?method=voterMachineStart";
	},1000);
		}
		
	});
}
function voteCast(pointer){
	var admissionNo=pointer.find(".hadmissionNo").val();
	$.ajax({
		type:'POST',
		url:'ElectionMenu.html?method=voteCount',
		data:{"admissionNo":admissionNo,
			"localIp":$("#LocalIp").text(),
			"count":$(".panel-group").length
		},
		success:function(response){
			var result=$.parseJSON(response);

			if(result.status=="true"){
				$('.errormessagediv').hide();
				$('.successmessagediv').show();
				$(".validateTips").text("For Category "+pointer.closest(".panel-group").find("h4.panel-title").find("a").text()+" vote casted Thank you!");
				$(".successmessagediv").delay(2000).fadeOut();
				
				
			}
			else if(result.status=="illegal"){
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("illegal Action");
				$(".errormessagediv").delay(3000).fadeOut();
				setTimeout(function(){
					window.location.href="menuslist.html?method=voterMachineStart";
				},1000);
			}
			else{
				$('.successmessagediv').hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Fail to Cast Vote");
				$(".errormessagediv").delay(3000).fadeOut();
			}
		}
	});
}
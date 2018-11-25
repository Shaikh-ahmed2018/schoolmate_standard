$(document).ready(function() {

	var parentid = $('#parentvalid').val();
	var feedbackid = $('#feedbackcodeid').val();

	$("#back").click(function(){
		window.location.href="parentMenu.html?method=sendfeedback";
	});

	$("#saveid").click(function(event){
		//event.preventDefault();
		var feedbckto = $('#toid').val();
		var description= $('#descriptionid').val();
		var addfile= $('#addfile').val();

		if(feedbckto==""||feedbckto==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Select FeedBack To");
			setTimeout(function() {
				$('.errormessagediv').delay(3000).fadeOut();
			}, 3000);
			return false;
		}
		else if(description==""||description==null){

			$('.errormessagediv').show();
			$('.validateTips').text("Write the Description");
			$('.errormessagediv').delay(3000).fadeOut();
			return false;
		}
		else{

			var form = $('#feedbackid')[0];
			var data = new FormData(form);

			$.ajax({
				type : "POST",
				enctype: 'multipart/form-data',
				url : "parentMenu.html?method=savefeedback",
				data : data,
				async: false,
				cache: false,
				contentType: false,
				processData: false,
				success : function(data) {

					var result = $.parseJSON(data);
					
					if(result.status == "true"){
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$('.successmessage').text("FeedBack Created Successfully");
						setTimeout(function() {
							window.location.href="parentMenu.html?method=sendfeedback";
						}, 3000);
					}
					else{
						$('.errormessagediv').show();
						$(".successmessagediv").hide();
						$(".validateTips").text("Failed To Create FeedBack. Please Try Again!");
						setTimeout(function() {
							window.location.href="parentMenu.html?method=savefeedback";
						}, 3000);
					}
				}
			});
		}
	});

});  


$(document).ready(function(){
	
	$("#loder").hide();
	
	$(".topPannel").click(function(e){
		e.preventDefault();
		$($(this).attr("href")).toggleClass("in");
	});
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=CasteCategoryInsertData"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				/*window.location.href="menuslist.html?method=locationList";*/
			},3000);
		}

	}
	if(pageUrl=="settingXLUpload.html?method=CasteCategoryInsertData"){
		$("#saveid").hide();
	}
	$("#CasteCatefile").change(function(){
	$("#saveid").show();
	});
	

	$("#saveid").click(function(){
	var filename = $("#CasteCatefile").val().split(".").pop().toLowerCase();
	var fileNameCheck=$("#CasteCatefile").val();

	if(filename=="xlsx" || filename=="xls"){
		$("#Add").val("xls");
	 		$(".errormessagediv").hide();
	  		$("#CasteCatefile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			return true;
		}else{
			 $(".errormessagediv").show();
				$(".validateTips").text("Select Excel File Only");
				 $("#CasteCatefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
	});

	
	if(fileNameCheck==""){
		 $(".validateTips").text("Select Excel File");
		$(".errormessagediv").show();
		 $("#CasteCatefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
		return false;
	}
	else{
		$("#loder").show();
		document.getElementById("excelfileupload").submit();
 	}

	
	$("#CasteCatefile").change(function(){
		var filename = $("#CasteCatefile").val().split(".").pop().toLowerCase();
		if(filename=="xlsx" || filename=="xls"){
		$("#CasteCatefile").css({'backgroundColor' : 'transparent','border-color':'#ccc'});
		}
		else{
		$("#CasteCatefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		}
	});
});//jquery
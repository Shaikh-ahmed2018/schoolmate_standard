$(document).ready(function(){
	
	$("#loder").hide();
	
	$(".topPannel").click(function(e){
		e.preventDefault();
		$($(this).attr("href")).toggleClass("in");
	});
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=SpecializationExcelDataInsert"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
			},3000);
		}
	}
	if(pageUrl=="settingXLUpload.html?method=SpecializationExcelDataInsert"){
		$("#saveid").hide();
	}
	$("#specfile").change(function(){
		$("#saveid").show();
	});
	
	
	$("#saveid").click(function(){
		var filename = $("#specfile").val().split(".").pop().toLowerCase();
		var fileNameCheck=$("#specfile").val();

		if(filename=="xlsx" || filename=="xls"){
			$("#Add").val("xls");
		 		$(".errormessagediv").hide();
		  		$("#specfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
				return true;
			}else{
				 $(".errormessagediv").show();
					$(".validateTips").text("Select Excel File Only");
					 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
				return false;
			}	
		
		if(fileNameCheck==""){
			 $(".validateTips").text("Select Excel File");
				$(".errormessagediv").show();
			 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
		else{
			$("#loder").show();
			document.getElementById("excelfileupload").submit();
	 	}
	
	
});
	
	$("#saveid").click(function(){
		if($("#specfile").val()==""){
			 $(".errormessagediv").show();
			 $(".validateTips").text("Select Excel File");
			 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			return true;
		}
	});

	
	$("#specfile").change(function(){
		var filename = $("#specfile").val().split(".").pop().toLowerCase();
		if(filename=="xlsx" || filename=="xls"){
		$("#specfile").css({'backgroundColor' : 'transparent','border-color':'#ccc'});
		}
		else{
		$("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		}
	});


});//jquery
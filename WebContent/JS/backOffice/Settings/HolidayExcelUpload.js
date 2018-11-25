
$(document).ready(function(){
	
	$("#loder").hide();
	
	$(".topPannel").click(function(e){
		e.preventDefault();
		$($(this).attr("href")).toggleClass("in");
	});
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=HolidayExcelInsert"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
			},3000);
		}

	}
	
	
	if(pageUrl=="settingXLUpload.html?method=HolidayExcelInsert"){
		$("#saveid").hide();
	}
		$("#Holidayfile").change(function(){
			$("#saveid").show();
		});
	
	
/*$("#Holidayfile").change(function(){*/
	$("#saveid").click(function(){
	var filename = $("#Holidayfile").val().split(".").pop().toLowerCase();
	var fileNameCheck=$("#Holidayfile").val();
	
	if(filename=="xlsx" || filename=="xls"){
		$("#Add").val("xls");
	 		$(".errormessagediv").hide();
	  		$("#Holidayfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			return true;
		}else{
			 $(".errormessagediv").show();
				$(".validateTips").text("Select Excel File Only");
				 $("#Holidayfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}

	if(fileNameCheck==""){
		 $(".validateTips").text("Select Excel File");
			$(".errormessagediv").show();
		 $("#Holidayfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
		return false;
	}
	else{
		$("#loder").show();
		document.getElementById("excelfileupload").submit();
		$("#saveid").hide();
 	}
	
});
	
	$("#Holidayfile").change(function(){
		var filename = $("#Holidayfile").val().split(".").pop().toLowerCase();
		if(filename=="xlsx" || filename=="xls"){
		$("#Holidayfile").css({'backgroundColor' : 'transparent','border-color':'#ccc'});
		}
		else{
		$("#Holidayfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		}
	});


});//jquery
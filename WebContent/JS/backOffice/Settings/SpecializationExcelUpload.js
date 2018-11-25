$(document).ready(function(){
	
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=SpecializationExcelDataInsert"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="menuslist.html?method=classList";
			},3000);
		}
	}
	
	
	$("#specfile").change(function(){
		var filename = $("#specfile").val().split(".").pop().toLowerCase();
		var fileNameCheck=$("#specfile").val();

		if(fileNameCheck==""){
			 $(".validateTips").text("Select File");
				$(".errormessagediv").show();
			 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}else if(filename !="xlsx"){
			 $(".validateTips").text("Select Only Excel file with .xlsx extension.");
				$(".errormessagediv").show();
			 
			 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
		else{
			document.getElementById("excelfileupload").submit();
	 	}
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
	
});
	
	$("#saveid").click(function(){
		if($("#specfile").val()==""){
			 $(".errormessagediv").show();
			 $(".validateTips").text("Select Excel File");
			 $("#specfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			return true;
		}
	});


});//jquery
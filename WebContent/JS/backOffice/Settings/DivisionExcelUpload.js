$(document).ready(function(){
		
		var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
		if(pageUrl=="settingXLUpload.html?method=insertDivisionExcelFormat"){
			if($(".errormessagediv span").text()==""){
				$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
				setTimeout(function(){
				},3000);
			}
			
		}
	
	
	$("#Divisionfile").change(function(){
		var filename = $("#Divisionfile").val().split(".").pop().toLowerCase();
		var fileNameCheck=$("#Divisionfile").val();

		if(fileNameCheck==""){
			 $(".validateTips").text("Select File");
				$(".errormessagediv").show();
			 $("#Divisionfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}else if(filename !="xlsx"){
			 $(".validateTips").text("Select Only Excel file with .xlsx extension.");
				$(".errormessagediv").show();
			 
			 $("#Divisionfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
		else{
			document.getElementById("excelfileupload").submit();
	 	}
	if(filename=="xlsx" || filename=="xls"){
		$("#Add").val("xls");
	 		$(".errormessagediv").hide();
	  		$("#Divisionfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			return true;
		}else{
			 $(".errormessagediv").show();
				$(".validateTips").text("Select Excel File Only");
				 $("#Divisionfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}	
	
});

});//jquery

$(document).ready(function(){
	
	$("#loder").hide();
	
	$("#saveid").show();
	
	$(".topPannel").click(function(e){
		e.preventDefault();
		$($(this).attr("href")).toggleClass("in");
	});
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=CasteExcelInsert"){
		if($(".errormessagediv span").text()==""){
			
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				/*window.location.href="menuslist.html?method=locationList";*/
			},3000);
		}
	}
	
	if(pageUrl="settingXLUpload.html?method=CasteExcelInsert"){
		$("#saveid").show();
	}
	
$("#saveid").click(function(){
	
	var filename = $("#Castefile").val().split(".").pop().toLowerCase();
	var fileNameCheck=$("#Castefile").val();
	
	if(filename=="xlsx" || filename=="xls"){
		$("#Add").val("xls");
	 		$(".errormessagediv").hide();
	  		$("#Castefile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			return true;
		}else{
			 $(".errormessagediv").show();
				$(".validateTips").text("Select Excel File Only");
				 $("#Castefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
				 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}

	if(fileNameCheck==""){
		 $(".validateTips").text("Select File");
			$(".errormessagediv").show();
		 $("#Castefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
		return false;
	}
	else{
		$("#loder").show();
		document.getElementById("excelfileupload").submit();
 	}
	
	
	
});
	
$("#saveid").click(function(){
	if($("#Castefile").val()==""){
		 $(".errormessagediv").show();
		 $(".validateTips").text("Select Excel File");
		 $("#Castefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		return true;
	}
});

	$("#Castefile").change(function(){
		var filename = $("#Castefile").val().split(".").pop().toLowerCase();
		if(filename=="xlsx" || filename=="xls"){
			$("#Castefile").css({'backgroundColor' : 'transparent','border-color':'#ccc'});
		}
		else{
			$("#Castefile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		}
	});

});//jquery
$(document).ready(function(){
	
	$("#loder").hide();
	
	$(".topPannel").click(function(e){
		e.preventDefault();
		$($(this).attr("href")).toggleClass("in");
	});
	 
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="settingXLUpload.html?method=insertOccupationXLFormat"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				$("#Occupationfile").val(" ");
				window.location.href="";
			},3000);
		}
	}
	if(pageUrl=="settingXLUpload.html?method=insertOccupationXLFormat"){
		$("#saveid").hide();
	}
	
	$("#Occupationfile").change(function(){
		$("#saveid").show();
	});
	$("#saveid").click(function(){
		var filename = $("#Occupationfile").val().split(".").pop().toLowerCase();
		var fileNameCheck=$("#Occupationfile").val();
		
		if(filename=="xlsx" || filename=="xls"){
			$("#Add").val("xls");
		 		$(".errormessagediv").hide();
		  		$("#Occupationfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
				return true;
			}else{
				 $(".errormessagediv").show();
					$(".validateTips").text("Select Excel File Only");
					 $("#Occupationfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
				return false;
			}	

		if(fileNameCheck==""){
			 $(".validateTips").text("Select File");
				$(".errormessagediv").show();
			 $("#Occupationfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			return false;
		}
		else{
			$("#loder").show();
			document.getElementById("excelfileupload").submit();
		}

	
});

	
	$("#saveid").click(function(){
		if($("#Occupationfile").val()==""){
			 $(".errormessagediv").show();
			 $(".validateTips").text("Select Excel File");
			 $("#Occupationfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			return true;
		}
	});
	
	$("#Occupationfile").change(function(){
		var filename = $("#Occupationfile").val().split(".").pop().toLowerCase();
		if(filename=="xlsx" || filename=="xls"){
		$("#Occupationfile").css({'backgroundColor' : 'transparent','border-color':'#ccc'});
		}
		else{
		$("#Occupationfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
		}
	});
});//jquery
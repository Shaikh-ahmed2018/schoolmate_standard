function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };

$(document).ready(function(){
	
	if($("#hiddenstatus").val()=="true"){
		window.location.href="menuslist.html?method=studentPhotosheet";
	}
	
	
	$("#loder").hide();
	if($("#allstudent tbody tr").length==0){
		$(".val1").hide();
	}else{
		$(".val1").show();
	}
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	
	
	if(pageUrl=="uploadStudentXSL.html?method=insertStudentXSL"){
		
		if($(".errormessagediv span").text()==""){
			
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="menuslist.html?method=studentList";
				
			},3000);
		}
	
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				
				$(".errormessagediv").empty();
			},3000);
			
		}
	}
	
	
	if($("#hiddenstatus").val()=="true"){
		$(".successmessagediv").show();
		$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
		$(".validateTips").text($("#hiddencount").val()+" : Photos Uploaded Successfully...");
		$('.successmessagediv').delay(3000).slideUp();
	}else if($("#hiddenstatus").val()=="false"){
		$(".errormessagediv").show();
		$(".validateTips").text($("#hiddencount").val()+" : Photos Uploaded...");
		$('.errormessagediv').delay(3000).slideUp();
	}
	
	$("#saveid").click(function() 
			{
				var fileName=$("#studentfile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#studentfile").val();
				var check=true;
				if(fileName=="jpg" || fileName=="png"){
					check=false;
				}
				
				if(check){
					 $(".validateTips").text("Student image accepts only .jpg or .png format only.");
					 $(".errormessagediv").show();
					 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					 return false;
				} 
				else{
                    $("#loder").show();
					document.getElementById("excelfileupload").submit();
			 	}
         });
});

function checkFileSize(inputFile) {
    var max = 1 * 1024 * 1024; // 10MB
    iSize = (Math.round((inputFile.files[0].size / 1024) * 100) / 100);
     
    if (inputFile.files && iSize > (102.4).toFixed(2)) {
    	$(".validateTips").text("file size exceeded.It should be less than 100KB.");
		$(".errormessagediv").show();
		setTimeout(function(){
			$(".errormessagediv").hide();
		},3000);
        inputFile.value = null;  
    }
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
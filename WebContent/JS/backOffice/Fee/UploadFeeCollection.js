function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };

$(document).ready(function(){
	
	$("#saveid").click(function(){
		        var status=true;
				var fileName=$("#studentfile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#studentfile").val();
				
				if(fileName=="xlsx" || fileName=="xls"){
					status=false;
				}
				
				if(fileNameCheck==""){
					$(".validateTips").text("Select File");
					$(".errormessagediv").show();
					$("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(status){
					$(".validateTips").text("Select Excel file Only.");
					$(".errormessagediv").show();
					$("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				else{
					document.getElementById("excelfileupload").submit();
					/*$(".errormessagediv").hide();
			  		$("#studentfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
			 		return true;*/
			 	}
				if(fileName=="xlsx" || fileName=="xls"){
					
					$("#Add").val("xls");

				 		$(".errormessagediv").hide();
				  		$("#studentfile").css({'backgroundColor' : 'transparent','border':'1px solid #ccc'});
				 		
				 
						return true;
					}else{
						
						 $(".errormessagediv").show();
							$(".validateTips").text("Select Excel File Only");
							 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
							 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
						return false;
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
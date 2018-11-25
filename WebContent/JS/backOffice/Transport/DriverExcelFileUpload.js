
function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };

function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
//To disable f5
 /* jQuery < 1.7 */




$(document).ready(function(){
	$("#loder").hide();
	
	if($("#allstudent tbody tr").length==0){
		$(".val1").hide();
	}else{
		$(".val1").show();
	}
	
	$("#driverFile").click(function(){
	       document.getElementById("driverFile").style.border = "1px solid #ccc";
	      document.getElementById("driverFile").style.backgroundColor = "#fff";
    });
	
	$(document).bind("keydown", disableF5);
	/* OR jQuery >= 1.7 */
	$(document).on("keydown", disableF5);

	//To re-enable f5
	 /* jQuery < 1.7 */
	$(document).unbind("keydown", disableF5);
	/* OR jQuery >= 1.7 */
	$(document).off("keydown", disableF5);
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	
	if(pageUrl=="uploadDriverXSL.html?method=insertDriverXSL"){
		if($(".errormessagediv span").text()==""){
			
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="menuslist.html?method=driverList";
				
			},3000);
		}
	
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				
				$(".errormessagediv").empty();
			},3000);
			
		}
	}
	
	
	$("#saveid").click(function() 
			{
				/*$("#driverFile").hide();
				$("#inputnames").hide();
				$("#txtstyle").hide();*/
		        
		        var status=true;
				var fileName=$("#driverFile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#driverFile").val();
				
				if( fileName=="xlsx" || fileName=="xls"){
					status=false;
				}
				
				if(fileNameCheck==""){
					 $(".validateTips").text("Select Excel File");
						$(".errormessagediv").show();
					 
					 $("#driverFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(status){
					$(".validateTips").text("Select Excel file Only.");
						$(".errormessagediv").show();
						$("#driverFile").val("");
					 $("#driverFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				else{
					$("#driverFile").show();
					$("#inputnames").show();
					$("#txtstyle").show();
					$("#loder").show();
					document.getElementById("excelfileupload").submit();
					
			 	}

        });
});


function checkFileSize(inputFile) {
    var max = 1 * 1024 * 1024; // 10MB
    iSize = (Math.round((inputFile.files[0].size / 1024) * 100) / 100);
     
    if (inputFile.files && iSize > 100) {
    	$(".validateTips").text("file size exceeded.It should be less than 100KB.");
		$(".errormessagediv").show();
		setTimeout(function(){
			$(".errormessagediv").hide();
		},3000);
        inputFile.value = null;  
    }
}

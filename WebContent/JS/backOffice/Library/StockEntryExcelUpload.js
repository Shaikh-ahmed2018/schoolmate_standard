function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };
$(document).ready(function(){
	
	var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
	if(pageUrl=="uploadLibraryXSL.html?method=insertStockEntryXSL"){
		if($(".errormessagediv span").text()==""){
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="LibraryMenu.html?method=LibraryStockEntryDetailsList";
			},3000);
		}
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				$(".errormessagediv").empty();
			},3000);
		}
	}
	$("#saveid").click(function(){
				var fileName=$("#studentfile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#studentfile").val();
				var check=true;
				if(fileName=="xlsx" || fileName=="xls"){
					check=false;
				}
				
				if(fileNameCheck==""){
					 $(".validateTips").text("Select File");
						$(".errormessagediv").show();
					 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(check){
					 $(".validateTips").text("Select Excel file Only.");
						$(".errormessagediv").show();
					 
					 $("#studentfile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				else{
					document.getElementById("excelfileupload").submit();
			 	}
				 
         });
   });
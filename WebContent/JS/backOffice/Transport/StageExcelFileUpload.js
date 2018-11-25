
function disableF5(e) { if ((e.which || e.keyCode) == 116 || (e.which || e.keyCode) == 82) e.preventDefault(); };

function disableF5(e) { if ((e.which || e.keyCode) == 116) e.preventDefault(); };
//To disable f5
  jQuery < 1.7 




$(document).ready(function(){
	
   $("#loder").hide();
	if($("#allstudent tbody tr").length==0){
		$(".val1").hide();
	}else{
		$(".val1").show();
	}
	
	
	$("#stageFile").click(function(){
	       document.getElementById("stageFile").style.border = "1px solid #ccc";
	      document.getElementById("stageFile").style.backgroundColor = "#fff";
    });
	
	/*if($(".select").attr('id').split(",")[0]==""||$(".select").attr('id').split(",")[0]==undefined){
		$(".select").hide();
	}*/
	
	$("#override").click(function(){
		var cnt = 0;
		var stageId=[];
		var accid=[];
		var locid=[];
		var amount=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id").split(",");
			stageId.push(list[0]);
			accid.push(list[1]);
			locid.push(list[2]);
			amount.push(list[3]);
			cnt++;
		});
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".emptyclass").empty();
			$(".emptyclass").text("Select Any One Record to Override");
			return false;
		} else {
			datalist = {
				"stageId" :stageId.toString(),
				"accid":accid.toString(),
				"locid":locid.toString(),
				"amount":amount.toString(),
			},
			$.ajax({
				type : "POST",
				url : "uploadStageXSL.html?method=updateStageXSL",
				data : datalist,
				success : function(data) {
					var result = $.parseJSON(data);
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".validateTips").empty();
						$(".validateTips").text(result.status);
						setTimeout(function() {
							window.location.href="menuslist.html?method=SchoolWiseStageList";
					}, 2000);
				}

			});
		}
			});
	
	
	$("#selectall").change(function() {
		$(".select").prop('checked',$(this).prop("checked"));
	});

	$(".select").change(function(){
	         if($(".select").length==$(".select:checked").length){
		         $("#selectall").prop("checked",true);
	          }
	        else{
		           $("#selectall").prop("checked",false);
	            }
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
	
	if(pageUrl=="uploadStageXSL.html?method=insertStageXSL"){
		if($(".errormessagediv span").text()==""){
			
			$(".successmessagediv").find("span").not(".validateTips").parents(".successmessagediv").attr("style","display:block");
			setTimeout(function(){
				window.location.href="menuslist.html?method=SchoolWiseStageList";
				
			},3000);
		}
	
		if($(".errormessagediv span").text()!=""){
			 $(document).on("keydown", disableF5);
			setTimeout(function(){
				
				$(".errormessagediv").hide();
			},3000);
			
		}
	}
	
	
	$("#saveid").click(function(){
				var status=true;
				var fileName=$("#stageFile").val().split('.').pop().toLowerCase();
				var fileNameCheck=$("#stageFile").val();
				
				if(fileName=="xlsx" || fileName=="xls"){
					status=false;
				}
				
				if(fileNameCheck==""){
					 $(".validateTips").text("Select Excel File");
						$(".errormessagediv").show();
					 $("#stageFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}else if(status){
					$(".validateTips").text("Select Excel file Only.");
						$(".errormessagediv").show();
						$("#stageFile").val("");
					 $("#stageFile").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
					 setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
					return false;
				}
				else{
					$("#stageFile").show();
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
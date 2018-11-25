$(document).ready(function(){
	
/*	$("#backbutton").click(function(){
		window.location.href='menuslist.html?method=getClassFeeSetup';
	});*/
	
	$(".select.Y").prop("checked",true);
	$(".select.Y").each(function(){
		if($(this).prop("checked")==true){
			$(this).closest("tr").find(".classFeeAmount").prop("readonly",false);
			$(this).closest("tr").find(".classFeeAmountNew").prop("readonly",false);
			var sumAmount=0.0;
			$(this).closest("table.classfeesetup").find(".classFeeAmount").each(function(){
				 sumAmount=sumAmount+ parseFloat($(this).val());
			});
			var sumAmountNew=0.0;
			$(this).closest("table.classfeesetup").find(".classFeeAmountNew").each(function(){
				sumAmountNew=sumAmountNew+ parseFloat($(this).val());
			});
			$(this).closest("table.classfeesetup").find(".totalFeeAmount").val((sumAmount).toFixed(2));
			$(this).closest("table.classfeesetup").find(".totalFeeAmountNew").val((sumAmountNew).toFixed(2));
			
			var totYearFee=0.0;
			$(".totalFeeAmount").each(function(){
				totYearFee=totYearFee+Number($(this).val());
			});
			$("#tot-yearly-fee").val(totYearFee.toFixed(2));
			var totYearFeeNew=0.0;
			$(".totalFeeAmountNew").each(function(){
				totYearFeeNew=totYearFeeNew+Number($(this).val());
			});
			$("#tot-yearly-fee-new").val(totYearFeeNew.toFixed(2));
		}
		else{
			$(this).closest("tr").find(".classFeeAmount").prop("readonly",true);
			$(this).closest("tr").find(".classFeeAmountNew").prop("readonly",true);
		}
	});
	$(".selectAll").change(function(){
		$(this).closest("table.classfeesetup").find(".select").prop("checked",$(this).prop("checked"));
		if($(this).prop("checked")==true){
			$(this).closest("table.classfeesetup").find(".classFeeAmount").prop("readonly",false);
			$(this).closest("table.classfeesetup").find(".classFeeAmountNew").prop("readonly",false);
		}
		else{
			$(this).closest("table.classfeesetup").find(".classFeeAmount").prop("readonly",true);
			$(this).closest("table.classfeesetup").find(".classFeeAmountNew").prop("readonly",true);
			$(this).closest("table.classfeesetup").find(".classFeeAmount").val("0.00");
			$(this).closest("table.classfeesetup").find(".classFeeAmountNew").val("0.00");
			var sumAmount=0.0;
			$(this).closest("table.classfeesetup").find(".classFeeAmount").each(function(){
				sumAmount=sumAmount+ parseFloat($(this).val());
			});
			var sumAmountNew=0.0;
			$(this).closest("table.classfeesetup").find(".classFeeAmountNew").each(function(){
				sumAmountNew=sumAmountNew+ parseFloat($(this).val());
			});
			$(this).closest("table.classfeesetup").find(".select").each(function(){
				deleteFees($(this));
			});

			$(this).closest("table.classfeesetup").find(".totalFeeAmount").val((sumAmount).toFixed(2));
			$(this).closest("table.classfeesetup").find(".totalFeeAmountNew").val((sumAmountNew).toFixed(2));
			var totYearFee=0.0;
			$(".totalFeeAmount").each(function(){
				totYearFee=totYearFee+Number($(this).val());
			});
			$("#tot-yearly-fee").val(totYearFee.toFixed(2));
			var totYearFeeNew=0.0;
			$(".totalFeeAmountNew").each(function(){
				totYearFeeNew=totYearFeeNew+Number($(this).val());
			});
			$("#tot-yearly-fee-new").val(totYearFeeNew.toFixed(2));
		}
	});
	$(".select").change(function(){
	if($(this).prop("checked")==true){
		$(this).closest("tr").find(".classFeeAmount").prop("readonly",false);
		$(this).closest("tr").find(".classFeeAmountNew").prop("readonly",false);
	}
	else{
		$(this).closest("tr").find(".classFeeAmount").prop("readonly",true);
		$(this).closest("tr").find(".classFeeAmountNew").prop("readonly",true);
		$(this).closest("tr").find(".classFeeAmount").val("0.00");
		$(this).closest("tr").find(".classFeeAmountNew").val("0.00");
		var sumAmount=0.0;
		$(this).closest("table.classfeesetup").find(".classFeeAmount").each(function(){
			 sumAmount=sumAmount+ parseFloat($(this).val());
		});
		var sumAmountNew=0.0;
		$(this).closest("table.classfeesetup").find(".classFeeAmountNew").each(function(){
			sumAmountNew=sumAmountNew+ parseFloat($(this).val());
		});
		$(this).closest("table.classfeesetup").find(".totalFeeAmount").val((sumAmount).toFixed(2));
		$(this).closest("table.classfeesetup").find(".totalFeeAmountNew").val((sumAmountNew).toFixed(2));
		deleteFees($(this));
		var totYearFee=0.0;
		$(".totalFeeAmount").each(function(){
			totYearFee=totYearFee+Number($(this).val());
		});
		$("#tot-yearly-fee").val(totYearFee.toFixed(2));
		var totYearFeeNew=0.0;
		$(".totalFeeAmountNew").each(function(){
			totYearFeeNew=totYearFeeNew+Number($(this).val());
		});
		$("#tot-yearly-fee-new").val(totYearFeeNew.toFixed(2));
	}
	});
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=getClassFeeSetup&historylocId="+$("#historylocId").val()+
		"&historyacademicId="+$("#historyacademicId").val();
	});
	
	$(".classFeeAmount").change(function(){
		var sumAmount=0.0;
		$(this).val(parseFloat($(this).val()).toFixed(2));
		if(isNaN(parseFloat($(this).val()))){
			$(this).val("0.00");
		}
		else if($(this).val()<0){
			$(this).val("0.00");
		}
		$(this).closest("table.classfeesetup").find(".classFeeAmount").each(function(){
			 sumAmount=sumAmount+ parseFloat($(this).val());
		});
		$(this).closest("table.classfeesetup").find(".totalFeeAmount").val((sumAmount).toFixed(2));
		
		var totYearFee=0.0;
		$(".totalFeeAmount").each(function(){
			totYearFee=totYearFee+Number($(this).val());
		});
		$("#tot-yearly-fee").val(totYearFee.toFixed(2));
	});
	
	$(".classFeeAmountNew").change(function(){
		var sumAmount=0.0;
		$(this).val(parseFloat($(this).val()).toFixed(2));
		if(isNaN(parseFloat($(this).val()))){
			$(this).val("0.00");
		}
		else if($(this).val()<0){
			$(this).val("0.00");
		}
		$(this).closest("table.classfeesetup").find(".classFeeAmountNew").each(function(){
			sumAmount=sumAmount+ parseFloat($(this).val());
		});
		$(this).closest("table.classfeesetup").find(".totalFeeAmountNew").val((sumAmount).toFixed(2));
	});
	
	$("#save").click(function(){
		$("#dialog").dialog("open");
		$("#dialog").empty();
		$("#dialog").append("<p>Are You Sure to Save?</p>");
	});
	
	$("#dialog").dialog({
		autoOpen  : false,
		modal     : true,
		title     : "Class Fee SetUp",
		buttons   : {
			'Yes' : function() {
				var specializationCode=[];
				var feeCodeArray=[];
				var academicYearCode=$("#haccYear").val();
				var classCode=$("#hclassId").val();
				var loc_id=$("#hlocationId").val();

				$("table.classfeesetup").each(function(){
					$(this).find("tbody tr").each(function(){
						if($(this).find(".select:checked").val()!=undefined){
							feeCodeArray.push($(this).find(".select:checked").val().replace(",","-")+"-"+$(this).find(".classFeeAmount").val()+"-"+$(this).find(".classFeeAmountNew").val());
							specializationCode.push("-");
						}
					});
				});
				var dataList={
						'feeCodeArray':feeCodeArray.toString(),
						'academicYearCode':academicYearCode,
						'loc_id':loc_id,
						'classCode':classCode,
						'specializationCode':specializationCode.toString()
				};

				$.ajax({
					type:'post',
					url:'classfeesetup.html?method=insertFeeAmount',
					data:dataList,
					success:function(data){
						var result=$.parseJSON(data);
						if(result.status=="true"){
							$(".successmessagediv").show();
							$(".sucessmessage").text("Fee SetUp Progressing...");
							setTimeout(function(){
								window.location.href="menuslist.html?method=getClassFeeSetup";
							},2000);
						}
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Fee SetUp Failed");
						}
					}
				});
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});
	
	
	
	$(".heading").click(function(){
		$(this).closest("table.classfeesetup").find(".collapsable").slideToggle();
	});
	var totYearFee=0.0;
	$(".totalFeeAmount").each(function(){
		totYearFee=totYearFee+Number($(this).val());
	});
	$("#tot-yearly-fee").val(totYearFee.toFixed(2));
	var totYearFeeNew=0.0;
	$(".totalFeeAmountNew").each(function(){
		totYearFeeNew=totYearFeeNew+Number($(this).val());
	});
	$("#tot-yearly-fee-new").val(totYearFeeNew.toFixed(2));
	
});
function deleteFees(pointer){
	  var datalist = {'FeeSettingsCode':pointer.attr("class").split(" ")[2],
			  			'FeeCode':pointer.val().split(",")[0],
			  			'term':pointer.val().split(",")[1],
			  			'specCode':'-',
			  			'acadamicYear':$("#haccYear").val(),
			  			'classid':$("hclassId").val()
	  
	  };//create json data3
		$.ajax({
					type : "GET",
					url : "classfeesetup.html?method=deleteFees",
					data : datalist,
					async : false,

					success : function(
							response) {

						}
				});
}
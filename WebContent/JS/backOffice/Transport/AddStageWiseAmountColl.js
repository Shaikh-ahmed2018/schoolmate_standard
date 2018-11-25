$(document).ready(function(){
	
	$("input:text").live( "click", function() {
			$(this).select(); 
	})
	
	$("#accyear").change(function(){
		printaccyear=$("#accyear option:selected").text();
		 
		$("#accyear1").val("all");
		getAllStage($("#accyear").val());
	});
	
	
	$("#print").click(function(){
	     singleprint();
	});
	
	getPresentAndNextAccyear();
	getPreAcc();
	
	printaccyear=$("#accyear option:selected").text();
	
	$("#accyear1").change(function(){
		getAllStagefromPre($("#accyear1").val());
		 
		$("#allstudent tbody tr").each(function(){
			
			$("#"+$(this).find(".amountclass").attr('id')).attr('readonly', true);
			$("#"+$(this).find(".amountclass").attr('id')).css({"background-color":"#eeeeee"});
		});
		printaccyear=$("#accyear1 option:selected").text();
		if($("#accyear1").val()=="all"){
			printaccyear=$("#accyear option:selected").text();
			 
			getAllStage($("#accyear").val());
		}
		
	});
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=stageWiseFeeSetup&historyacademicId="+$("#historyacademicId").val()+
		"&historylocId="+$("#historylocId").val();
	});
	$("#table").append("<table class='table' id='allstudent' class='allstudent' width='100%'>"+
			"<thead>"+
			"<tr>"+
			"<th>Sl.No</th>" +
			"<th>Stage Name</th>" +
			"<th>Stage Amount</th>" +
			"</tr>" +
			"</thead>"+
			"<tbody></tbody></table>"
			);
	getAllStage($("#hiddenaccyear").val());
	
	 
	 $("#save").click(function(){
	    $("#savedialog").dialog("open");
		$("#savedialog").empty();
		
		if($("#accyear1").val()=="all"){
			$("#savedialog").append("<p style='font-family: Roboto,sans-serif !important;'>Are you sure to save <b>"+$("#accyear option:selected").text()+"</b> Stage Amounts ?</p>");
		}else{
			$("#savedialog").append("<p style='font-family: Roboto,sans-serif;'>Are you sure to save <b>"+$("#accyear1 option:selected").text()+"</b> Stage Amounts to <b>"+$("#accyear option:selected").text()+"</b> Stage?</p>");
		}
		
	 });
	 
	 value="false";
	 invalidcolumn=[];
	 
	 $("#savedialog").dialog({
			autoOpen: false,
			resizable: false,
			modal: true,					    
			title:'Stage Wise Amount',
			buttons : {
				"Yes" : function() {

					var stageid=[];
					var amount=[];
					var count=0;
					var columncount=0;
					
					
					$("#allstudent tbody tr").each(function(){
						
						/*if($(this).find("#amount"+count).val()!=0 && $(this).find("#amount"+count).val()>0 && $(this).find("#amount"+count).val().trim()!=""){
							stageid.push($(this).attr('id'));
							amount.push($(this).find("#amount"+count).val());
							columncount++;
						}*/
						
						stageid.push($(this).attr('id'));
						amount.push($(this).find("#amount"+count).val());
						columncount++;
						
						/*if($(this).find("#amount"+count).val().indexOf('.') == -1){
							value="true";
							invalidcolumn.push($(this).find("#amount"+count).attr('id')); 
						}else{
							value="false";
						}*/
						count++;
						
					});
					
					var locationid=$("#hiddenloc").val();
					var accyear=$("#accyear").val();
					
					
					if(value=="true" || invalidcolumn.length>0){
						$(".errormessagediv").show();
						$(".validateTips").text("Enter valid Amount.");
						columncount=0;
						$(".errormessagediv").delay(3000).fadeOut("slow");
						for(var i=0;i<invalidcolumn.length;i++){
							document.getElementById(invalidcolumn[i]).style.border = "1px solid #AF2C2C";
			    			document.getElementById(invalidcolumn[i]).style.backgroundColor = "#FFF7F7";
						}
						$('#allstudent tbody tr').click(function (event) {
							$(".errormessagediv").hide();
					         document.getElementById($(this).find(".amountclass").attr('id')).style.border = "1px solid #ccc";
							 document.getElementById($(this).find(".amountclass").attr('id')).style.backgroundColor = "#fff";
					    });
					}
					else if(stageid==","){
						$(".errormessagediv").show();
						$(".validateTips").text("No stages found.");
						$(".errormessagediv").delay(3000).fadeOut("slow");
					}
					else{
					datalist={
							"locationid" : locationid,
							"accyear":accyear,
							"stageid":stageid,
							"amount":amount
					}
					$.ajax({
						type : "POST",
						url:"stagefeesetup.html?method=addStageWiseAmount",
						data : datalist,
						beforeSend: function(){
							$("#loder").show();
						},
						success : function(data){
							var result = $.parseJSON(data);
							if(result.status == "success"){ 
								$("#loder").hide();
								$(".successmessagediv").show();
								$(".validateTips").text("Stage Amount Added Successfully.");
								setTimeout(function() {
								$('.successmessagediv').fadeOut();
								  window.location.href="menuslist.html?method=stageWiseFeeSetup&historyacademicId="+$("#historyacademicId").val()+
								  "&historylocId="+$("#historylocId").val();
							}, 3000);
					 }else{
						 $("#loder").hide();
						 $(".errormessagediv").show();
						 $(".validateTips").text("Try Again");
							setTimeout(function() {
							  $('.errormessagediv').fadeOut();
								location.reload();
							}, 3000);
					    }
					  }
					 });
				   }
				   $(this).dialog("close");
				    stageid=[];
					amount=[];
					invalidcolumn=[];
				},
				"No" : function() {
					$(this).dialog("close");
				}
			 }
		});
	 
	
});
function getAllStage(mainaccyear){
	
	var locationid=$("#hiddenloc").val();
	var accyear=mainaccyear;
	datalist={
			"locationid" : locationid,
			"accyear":accyear
	},
	
	$.ajax({
		type : 'POST',
		url : "stagefeesetup.html?method=getStage",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
		$('#allstudent tbody').html("");
		var len=result.stageList.length;
		var j=0;
		if(len!=0){
			
		 for ( j = 0; j < result.stageList.length; j++) {
			 $("#allstudent tbody").append("<tr id="+result.stageList[j].stageCode+">"+/*','+result.stageList[j].feeSetupCode+*/
						"<td>"+(j+1)+"</td>" +
						"<td style='text-align: left'>"+result.stageList[j].stageName+"</td>" +
						"<td><input type='text' id='amount"+j+"' maxlength='8' onkeypress='return  CheckIsAlphabet(event);' class='text amountclass' value='"+result.stageList[j].stageAmount+"' style='text-align:right;border:1px solid rgb(204, 204, 204);'></td>"+
						"</tr> "
						);
				}
		}else{
			$("#allstudent").append("<tbody><tr><td colspan='3'>No Records Found</td><tr></tbody>");
			}
		$("#loder").hide();
		validamount();
		}
	});
}

function getAllStagefromPre(mainaccyear){
	var locationid=$("#hiddenloc").val();
	var accyear=mainaccyear;
	datalist={
			"locationid" : locationid,
			"accyear":accyear
	},
	$.ajax({
		type : 'POST',
		url : "stagefeesetup.html?method=getStage",
		data : datalist,
		async : false,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
		$('#allstudent tbody').html("");
		var len=result.stageList.length;
		var j=0;
		if(len!=0){
			for (j = 0; j < len; j++) {
				$("#allstudent tbody").append("<tr id="+result.stageList[j].stageCode+">"+
						"<td>"+(j+1)+"</td>" +
						"<td style='text-align: left'>"+result.stageList[j].stageName+"</td>" +
						"<td><input type='text' class='amountclass' maxlength='8' onkeypress='return CheckIsAlphabet(event);' id='amount"+j+"' value='"+result.stageList[j].stageAmount+"' style='text-align:right;border:1px solid rgb(204, 204, 204);background-color :rgb(255, 255, 255);'></td>"+
						"</tr>"
						);
				}
		}else{
			$("#allstudent").append("<tbody><tr><td colspan='3'>No Records Found</td><tr></tbody>");
		}
		validamount();
		$("#loder").hide();
		}
	});
}
function getPreAcc(){
	var locationid=$("#hiddenloc").val();
	var accyear=$("#hiddenaccyear").val();
	datalist={
			"locationid" : locationid,
			"accyear":accyear
	},

	$.ajax({

		type : 'POST',
		url : "stagefeesetup.html?method=getPreAcc",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#accyear1').html("");

			$('#accyear1').append('<option value="all">' +"----------Select----------"+ '</option>');
			for ( var j = 0; j < result.list.length; j++) {

				$('#accyear1').append('<option value="'

						+ result.list[j].acadamicyear_id_int + '">'

						+ result.list[j].acadamicyear_year

						+ '</option>');
			}
		}
	});
}

function getPresentAndNextAccyear(){
	var locationid=$("#hiddenloc").val();
	var accyear=$("#hiddenaccyear").val();
	datalist={
			"locationid" : locationid,
			"accyear":accyear
	},

	$.ajax({

		type : 'POST',
		url : "stagefeesetup.html?method=getPresentAndNextAccyear",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#accyear').html("");

			for ( var j = 0; j < result.list.length; j++) {
				$('#accyear').append('<option value="'
						+ result.list[j].acadamicyear_id_int + '">'
						+ result.list[j].acadamicyear_year
						+ '</option>');
			}
		}
	});
}

function validamount(){
	$(".text").keyup(function(){
		var count1=0;
		$('#allstudent tbody tr').each(function () {
                checkval=Number($(this).find("#amount"+count1).val());
               
			 if($(this).find("#amount"+count1).val()==0 ||$(this).find("#amount"+count1).val()==""
					|| $(this).find("#amount"+count1).val()<0 ||($(this).find("#amount"+count1).val()).indexOf(".")==0)
				{
					$(this).find("#amount"+count1).val("0.0");
				}
			 else if(isNaN(checkval)){
					$(this).find("#amount"+count1).val("0.0");
				}
				count1++;
	      });
		});
}

function singleprint(){ 
	   var a=$("#printing-css").val();
	   var b= document.getElementById("table").innerHTML;
	   var abd='<style>' + a +'</style>' + b;
	    var frame1 = $('<iframe />');
      frame1[0].name = "frame1";
     
      $("body").append(frame1);
      var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
      frameDoc.document.open();
      frameDoc.document.write('<html><head><title style="color: white;">...</title>');
      //Create a new HTML document.
      
      //Append the external CSS file.
      frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
      frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
      frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
      frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
      frameDoc.document.write('<link href="CSS/Exam/Examsettings.css" rel="stylesheet" type="text/css">');
      frameDoc.document.write('</head><body>');
      frameDoc.document.write('<p style="font-family: Roboto,sans-serif;"><b>'+$("#printLocationName").val()+'<span>('+$("#hiddenlocname").val()+')</span></b></p>');
      frameDoc.document.write('<p style="margin-top: 14px;font-size: 13px;font-family: Roboto,sans-serif;">'+$("#printAddress").val()+'</p>');
     /* frameDoc.document.write('<p style="margin-top: 17px;">'+$("#printAddress2").val()+'</p>');*/
      frameDoc.document.write('<p><u>Stage Wise Amount</u></p>');
      frameDoc.document.write('<span id="printaccyearId"><b>Academic Year :  '+printaccyear+'</b></span>');
      //Append the DIV contents.
      frameDoc.document.write(abd);
      frameDoc.document.write('</body></html>');
      frameDoc.document.close();
      setTimeout(function () {
          window.frames["frame1"].focus();
          window.frames["frame1"].print();
          frame1.remove();
      }, 100);
	  
}

function  CheckIsAlphabet(objEvt) 
{
    var charCode = (objEvt.which) ? objEvt.which : event.keyCode
    if ((charCode  != 46) && (charCode  < 48 || charCode  > 57)) 
    {
    	return false;
    }
    else 
    {
        return true;
    }
 }
function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
}


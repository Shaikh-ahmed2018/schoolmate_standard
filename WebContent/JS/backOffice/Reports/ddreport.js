$(document).ready(function(){
	$("#page").hide();
	$("input,select,textarea").on({
		keypress: function(){
		if(this.value.length>0){
		hideError("#"+$(this).attr("id"));
		$(".errormessagediv").hide();
		}
	},
	change: function(){
		if(this.value.length>0 ){
		hideError("#"+$(this).attr("id"));
		
		$(".errormessagediv").hide();
		}
	},
	});
	
	
	
	var hacademicyear=$('#hacademicyaer').val();
	$("#Acyearid").val(hacademicyear);
	$("#locationname,#sectionid").val("all");
	
	$("#Acyearid").change(function(){
		getDetailsOfDD();
	});
	$("#locationname").change(function(){
		getTermList();
		getDetailsOfDD();
		$("#page").show();
	});
	
	$("#Termid").change(function(){
		getDetailsOfDD();
	});
	
	
	$("#iconsimg").click(function(){
		if($("#locationname").val()=="all" || $("#locationname").val()=="" || $("#locationname").val()==null|| $("#locationname").val()==undefined)
		{
			$(".errormessagediv").show();
			 showError("#locationname","Select Branch Name");
				return false;
		   }
		else if($("#Termid").val()=="" || $("#Termid").val()==null || $("#Termid").val()==undefined)
		{
			 $(".errormessagediv").show();
			 showError("#Termid","Select Term");
				return false;
		  }
	});
	

	$("#print").click(function(){
		
		$.ajax({
			type:"post",
			url:'reportaction.html?method=PrintDDDetails&AccId='+$("#Acyearid").val()+'&Termid='+$("#Termid").val()+'&locationid='+$("#locationname").val()+'&locationname='+$("#locationname option:selected").text(),
			success:function(data){
				
			}
		});
		
	});
	
	$("#excelDownload").click(function(){
		window.location.href ='reportaction.html?method=DetailsOfDDExcelReport&AccId='+$("#Acyearid").val()
		+'&Termid='+$("#Termid").val()
		+'&locationid='+$("#locationname").val()
		+'&locationname='+$("#locationname option:selected").text();
		
		});
	

	
		$("#pdfDownload").click(function(){
				window.location.href = 'reportaction.html?method=DetailsOfDDPdfReport&AccId='+$("#Acyearid").val()
				+'&Termid='+$("#Termid").val()
				+'&locationid='+$("#locationname").val()
				+'&locationname='+$("#locationname option:selected").text();
				
		});
		
});

pagination(100);

function getTermList(){
	
	var loc=$("#locationname").val();
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getTerm",
		
		data : {
			"locId" :$("#locationname").val(),
			"accId":$("#Acyearid").val()
			},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#Termid').html("");

			$('#Termid').append('<option value="all">' +"ALL"+ '</option>');
			var j = 0;
			var len=result.data.length;

			for ( j = 0; j<len; j++) {

				$('#Termid').append('<option value="'+ result.data[j].termname+'">'+ result.data[j].termId+'</option>');
			}
		}
	});
}


function getDetailsOfDD(){
	var Termid = $("#Termid").val();
	var Acyearid = $("#Acyearid").val();
	var locationname = $("#locationname").val();
	$("#individualstudenttable").show();
	
	$("#individualstudenttable").empty();
	$("#individualstudenttable").append("<table class='table' id='allstudent'  width='100%'" +">"
			+"<center><tr>"
			+"<th>Sl.No</th>" 
			+"<th>Name Of Bank</th>"
			+"<th>DD No</th>"
			+"<th>DD Date</th>"
			+"<th>Paid Date</th>"
			+"<th>Amount</th>"
			+"</tr>"
			+"</center></table>");
		
		$.ajax({
			type : 'POST',
			url : "reportaction.html?method=DDReportList",
			data : {
				"Termid":Termid,
				"Acyearid":Acyearid,
				"locationname":locationname
			},
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				
					var i=0;
					var len=result.SearchList.length;
					$("#iconsimg").show();
					if(len>0){
					for(i=0;i<len;i++){
					$("#individualstudenttable #allstudent").append("<tr>"
							+"<td> "+(i+1)+"</td>"
							+"<td> "+result.SearchList[i].bankName+"</td>"
							+"<td> "+result.SearchList[i].ddNo+" </td>"
							+"<td> "+result.SearchList[i].ddDate+" </td>"
							+"<td> "+result.SearchList[i].paidDate+" </td>"
							+"<td> "+result.SearchList[i].amount_paid+" </td>"
							+"</tr>");
					}
					
					}
					else{
						$("#individualstudenttable #allstudent").append("<tr><td ColSpan='6'>No Records Found</td></tr>");
						$("#iconsimg").hide();
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
				
					pagination(100);
			}
		});
}



function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
		});
}


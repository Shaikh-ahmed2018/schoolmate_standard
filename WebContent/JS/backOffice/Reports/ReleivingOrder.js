$(document).ready(function() {

	$("#dateofRelievingId").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:0,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,

	});          
	$(".dialog").hide(); 

	
	techList=[];
	
	$("#iconsimg").click(function(){
		var cnt = 0;
		$('.select:checked').map(function() {
			var techId = $(this).attr("id");
			techList.push(techId);
			cnt++;
		});

		if($("#allstudent tbody tr").length == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("No Records Found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
		else if($("#allstudent tbody tr:first td:first").text() == "No Records Found"){
			$(".errormessagediv").show();
			$(".validateTips").text("No Records Found");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},3000);
			return false;
		}
		else if($("#locationname").val()=="all"){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Branch Name");
			setTimeout(function() {
				$(".errormessagediv").hide();
			}, 3000);
			return false;
		}
		else if(cnt==0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Any Record");
			setTimeout(function() {
				$(".errormessagediv").hide();
			}, 3000);
			return false;
		}

		else{

			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To Download ?</p>");

		}
	});       

	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,					    
		title:' Relieving Order',
		buttons : {
			"Yes" : function() {
				var releivedate=$("#dateofRelievingId").val();
				if(releivedate==""){
					var d = new Date();

					var month = d.getMonth()+1;
					var day = d.getDate();

					releivedate = (day<10 ? '0' : '') + day +'-'+(month<10 ? '0' : '') + month + '-' +d.getFullYear();

				}		        
				window.location.href = 'staffreleivingorder.html?method=staffReleivingPDFReport&teacherId='+techList.toString()+'&releivedate='+releivedate+'&locname='+$("#locationname option:selected").text()+'&locId='+$("#locationname").val();  	   

				$(this).dialog("close");

			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});



	/*   $("#pdfDownload").click(function(){
		        var releivedate=$("#dateofRelievingId").val();
		          if(releivedate==""){
		        	var d = new Date();

		        	var month = d.getMonth()+1;
		        	var day = d.getDate();

		        	 releivedate = (day<10 ? '0' : '') + day +'-'+(month<10 ? '0' : '') + month + '-' +d.getFullYear();

		        }		        
		      window.location.href = 'staffreleivingorder.html?method=staffReleivingPDFReport&teacherId='+techList.toString()+'&releivedate='+releivedate+'&locname='+$("#locationname option:selected").text()+'&locId='+$("#locationname").val();  	   


		    });*/

	SearchRelievingOrder();
	$("#locationname").change(function(){
		SearchRelievingOrder();
	});
	$("#teachertype").change(function(){
		SearchRelievingOrder();
	});		        	
	$("#depName").change(function(){
		SearchRelievingOrder();
	});
	$("#designameid").change(function(){
		SearchRelievingOrder();
	});

}); 

function SearchRelievingOrder(){
	var locationId =$("#locationname").val();
	var depName =$("#depName").val();
	var designameid =$("#designameid").val();
	var teachtype=$("#teachertype").val();
	var relievedate=$("#dateofRelievingId").val();

	$.ajax({
		type : 'POST',
		url :'staffreleivingorder.html?method=SearchRelievingOrder',
		data : {
			"locationId" :locationId,
			"depName" : depName,
			"designameid":designameid,
			"teachtype":teachtype,

		},
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.relievinglist.length>0){
				for(var i=0;i<result.relievinglist.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select'  id='"+result.relievinglist[i].techId+"'/></td>" 
							+"<td> "+result.relievinglist[i].registrtionno+" </td>"
							+"<td> "+result.relievinglist[i].teachername+"</td>"
							+"<td> "+result.relievinglist[i].deptname+" </td>"
							+"<td> "+result.relievinglist[i].designame+" </td>"
							+"<td> "+result.relievinglist[i].mobno+" </td>"
							+"<td> "+result.relievinglist[i].emailId+" </td>"
							+"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='8'>NO Records Found</td></tr>");
			}
			checkboxs();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.relievinglist.length);


		}
	});

}
function checkboxs(){
	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});

	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
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
function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}
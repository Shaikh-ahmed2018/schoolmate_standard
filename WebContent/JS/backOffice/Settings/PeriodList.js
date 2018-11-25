$(document).ready(function() {	
	
	$("#locationname").val($("#curr_loc").val());
	
	getStream();
	checkboxsselect();
	$("#locationname").change(function(){
		getStream();
		getperiodlist();
	});
	
	$("#streamId").change(function(){
		getClassList();
		getperiodlist();
	});
	
	$("#classId").change(function(){
		getperiodlist();
	});
	
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='5'>No Records Found</td></tr>");
	}
	
	$("#edit").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).attr("id");
			cnt++;
		});
							
		if (cnt == 0 || cnt > 1) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select any one record");
				return false;
			} 
			else
				{
			var periodId = getData;
			window.location.href = "periodmaster.html?method=editPeriod&periodId="+periodId;
			/*var result = $.parseJSON(response);*/
				}

	});
	
	periodId=[];
	$("#delete").click(function(){
		var count =0;
		$(".select:checked").each(function(){
			var Ids=$(this).attr("id");
			periodId.push(Ids);
			count ++;
		});	
		
		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Records");
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p class='warningfont'>Are you sure you want to Remove?</p>");
		}
	});
	
	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,					    
		title:'Period Details',
		buttons : {
			"Yes" : function() {
			
				$.ajax({
					type : 'POST',
					url : "periodmaster.html?method=deletePeriod",
					data : {
						"PeriodId":periodId.toString(),
					
					},
					success : function(response) {
						var result = $.parseJSON(response);
					 
						$('.errormessagediv').hide();
						if (result.status =="true") {
							$(".successmessagediv").show();
							$(".successmessagediv").attr("style","width:150%;margin-left:-280px;");
							$(".validateTips").text("Remove Record Sucussfully");
							$('.successmessagediv').delay(3000).slideUp();
							setTimeout(function(){
								window.location.href ="periodmaster.html?method=periodList";
							},3000);
						} 
						
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Failed to Remove Record");
							$('.errormessagediv').delay(3000).slideUp();
							setTimeout(function(){
								window.location.href ="periodmaster.html?method=periodList";
							},3000);
						}
					
						
					
					}
				});  

				$(this).dialog("close");
			 
			},
			
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});
	
});

function getStream(){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':$("#locationname").val()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.streamList.length; j++) {
				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');
			}
		}
	});
	
	
}

function getClassList(){
	var locationname=$("#locationname").val();
	var streamId=$("#streamId").val();
	datalist={
			"streamId" : streamId,
			"locationname":locationname
	},
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getClassesByStreamAndLocation",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);

			$('#classId').html("");

			$('#classId').append('<option value="">ALL</option>');
			for ( var j = 0; j < result.ClassesList.length; j++) {

				$('#classId').append('<option value="'+result.ClassesList[j].classId+'">'+result.ClassesList[j].classname+'</option>');
			}
		}
	});
	
}

function getperiodlist(){

	 dataList={
			  locid:$("#locationname").val(),
			  streamId:$("#streamId").val(),
			  clsId:$("#classId").val(),
				};
				
				$.ajax({
					type:'POST',
					url:'periodmaster.html?method=getperiodlist',
					data:dataList,
					async:false,
					success: function(data){
						var result = $.parseJSON(data);
						$("#allstudent tbody").empty();
						
						if(result.list.length>0){
							for(var i=0;i<result.list.length;i++){

								$("#allstudent tbody").append("<tr>"
										+"<td><input type='checkbox' class='select' id='"+result.list[i].slno+"' /></td>"
										+"<td>"+result.list[i].locname+"</td>"
										+"<td>"+result.list[i].streamname+"</td>"
										+"<td>"+result.list[i].clsname+"</td>"
										+"<td>"+result.list[i].noofperiod+"</td>"
										+"</tr>");
							     }
						   }
						else{
							$("#allstudent tbody").append("<tr>" +"<td colspan='5'>No Records Founds</td>"+"</tr>");
						}
						checkboxsselect();
						$(".numberOfItem").empty();
						$(".numberOfItem").append("No. of Records  "+result.list.length);
						pagination(100);
						}
				});
				
}

function checkboxsselect(){
	$('#selectall').change(function(){
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

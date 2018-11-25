var monthCount=0;

$(document).ready(function(){
 
	
	getVehicleTypeList();
	getSelectedMonth();
	
	$("#back1").click(function(){
		window.location.href="transport.html?method=transportFeeSearch&historylocId="+$("#historylocId").val()
		+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()
		+"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val()
		+"&historyistransport="+$("#historyistransport").val();
	});
	
	var StudentImage=$("#hiddenImage").val();
	if(StudentImage!=""){
		$("#imagePreview").show();
		$('#imagePreview').attr('src', StudentImage);
	}
 
	$("#vehicletype").change(function()
	 {	
		if($("#vehicletype").val()=="all"){
			$(".routename").val("");
		}else{
			getRouteNameList();
		}
		$("#waivedOff").hide();
		document.getElementById("routename").style.border = "1px solid rgb(169, 169, 169)";
		document.getElementById("routename").style.backgroundColor = "#FFF";
		$(".amount").val("");
	 });
	
	$(".routename").change(function()
	{		
			$("#waivedOff").hide();
			document.getElementById("routename").style.border = "1px solid rgb(169, 169, 169)";
			document.getElementById("routename").style.backgroundColor = "#FFF";
			$(".amount").val("");
			var routeid = $(this).val();
			getstoplist(routeid);
			
	});
	$(".stopname").change(function(){
		$("#waivedOff").hide();
		document.getElementById("stopname").style.border = "1px solid rgb(169, 169, 169)";
		document.getElementById("stopname").style.backgroundColor = "#FFF";
		var stageid= $(this).val();
		var pointer=$(this);

		getamountandstatus(stageid,pointer);
	});
	
	$("#waivedOff").click(function(){
		$("#dialog1").dialog("open");
		$("#dialog1").empty();
		$("#dialog1").append("<p>Are You Sure to Cancel?</p>");
	 });

	$("#dialog1").dialog({
		autoOpen  : false,
		resizable: false,
		maxWidth:200,
		minHeight:200,
		Height:200,
		Width:200,
		modal     : true,
		title     : "Transport Request/Cancel",
		buttons   : {
			'Yes' : function() {

				var datalist = {
						"stuid" : $("#hiddenstuid").val(),
						"locid" : $("#hiddenlocid").val(),
						"accyear" : $("#hiddenaccyid").val(),
						"routeid" : $(".routename").val(),
						"stageid" : $(".stopname").val()
				};
				$.ajax({
					type : 'POST',
					url : "transport.html?method=waivedOfftransportrequest",
					data : datalist,
					beforeSend: function(){
						$("#loder").show();
					},
					success : function(response) {
						var result = $.parseJSON(response);
						if(result.status == "success"){
							$(".successmessagediv").show();
							$(".validateTips").text("Transport WaivedOff Successfully");
							setTimeout(function(){
								window.location.href="transport.html?method=transportFeeSearch&historylocId="+$("#historylocId").val()
								+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()
								+"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val()
								+"&historyistransport="+$("#historyistransport").val();
						},3000);
						}else if(result.status == "fail"){
							$(".errormessagediv").show();
							$(".validateTips").text("Transport WaivedOff Failed");
							setTimeout(function(){
								window.location.href="transport.html?method=transportFeeSearch&historylocId="+$("#historylocId").val()
								+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()
								+"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val()
								+"&historyistransport="+$("#historyistransport").val();
						},3000);
						}
					}
				});
				$(this).dialog('close');
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});
	
	
	$("#month").click(function(){
		getSelectedMonth();
		 
		if($(".allstudent tbody tr").length>0){
			$("#dialog").dialog("open");
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("Set the Transport Term with "+$("#accyear").val()+" year");
			setTimeout(function(){
				$(".errormessagediv").show();
		   },3000);
		}
	});
	
	$(".selectAll").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$(".selectAll").prop("checked",true);
		}
		else{
			$(".selectAll").prop("checked",false);
		}
		
			if($(".select:checked").length > 1){
					if($(this).closest("tr").prev("tr").find(".select").prop("checked")==true){
						$(this).prop("checked",$(this).prop("checked"));
					}
					else{
						$(this).prop("checked",false);
					}
				}
	});
	
	 $( "#dialog" ).dialog({
	       autoOpen: false,
	       resizable: false,
           minHeight: 400,
           minWidth: 300,
           height: 400,
           width: 300,
           closeOnEscape: true,
           modal: true,
           title:"Add Month Details",
           buttons: {
        	   "Add" : function() {
        		   count = 0;
        			monthList=[];
        			monthname =[];
        			$(".select:checked").each(function(){
        				var list=$(this).val();
        				monthList.push(list);
        				monthname.push($(this).closest("tr").find(".monthname").text());
        				count ++;
        			});	
        			if(count == 0){
        				$('.errormessagediv').show();
        				$('.validateTips').text("Select the Months");
        				$('.errormessagediv').delay(3000).slideUp();
        				return false;
        			}else{
        				if(monthList.length == 1){
        					$("#month").val(monthname[0]);
        				}else{
        					$("#month").val(monthname[0]+" - "+monthname[monthList.length-1]);
        				}
        				$("#startmonth").val(monthList[0]);
        				$("#endmonth").val(monthList[monthList.length-1]);
        			}
        			 
        			monthCount=count;
        		   $(this).dialog("close");
        	   },
  
               "Close": function () {
            	   $(".select").prop("checked",false);
            	   $(".selectAll").prop("checked",false);
                   $(this).dialog("close");
               }
           }
       });
	 
	
	$("#save").click(function(){
		
		var datalist = {
				"stuid" : $("#hiddenstuid").val(),
				"locid" : $("#hiddenlocid").val(),
				"accyear" : $("#hiddenaccyid").val(),
				"routeid" : $(".routename").val(),
				"stageid" : $(".stopname").val(),
				"stmonths" : $("#startmonth").val(),
				"endmonth" :$("#endmonth").val(),
				"monthCount":monthCount,
				"vehicletype":$(".vehicletype").val(),
		};
		
		if($(".vehicletype").val() =="all"){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Vehicle Type");
			document.getElementById("vehicletype").style.border = "1px solid #AF2C2C";
			document.getElementById("vehicletype").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if($(".routename").val() == "" || $(".routename").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Route");
			document.getElementById("routename").style.border = "1px solid #AF2C2C";
			document.getElementById("routename").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if($(".stopname").val() == "" || $(".stopname").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Stop");
			document.getElementById("stopname").style.border = "1px solid #AF2C2C";
			document.getElementById("stopname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if($("#month").val() == "" || $("#month").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Month");
			document.getElementById("month").style.border = "1px solid #AF2C2C";
			document.getElementById("month").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else{
			$.ajax({
				type : 'POST',
				url : "transport.html?method=savetransportrequest",
				data : datalist,
				beforeSend: function(){
					$("#loder").show();
				},
				success : function(response) {
					var result = $.parseJSON(response);
					if(result.status == "success"){
						$("#loder").hide();
						$(".successmessagediv").show();
						$(".validateTips").text("Transport Request Updating Progressing...");
						setTimeout(function(){
							window.location.href="transport.html?method=transportFeeSearch&historylocId="+$("#historylocId").val()
							+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()
							+"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val()
							+"&historyistransport="+$("#historyistransport").val();
					   },3000);
					}else if(result.status == "fail"){
						$(".errormessagediv").show();
						$(".validateTips").text("Transport Request Updation Failed...");
						setTimeout(function(){
							window.location.href="transport.html?method=transportFeeSearch&historylocId="+$("#historylocId").val()
							+"&historyacademicId="+$("#historyacademicId").val()+"&historyclassname="+$("#historyclassname").val()
							+"&historysectionid="+$("#historysectionid").val()+"&historysearch="+$("#historysearch").val()
							+"&historyistransport="+$("#historyistransport").val();
					    },3000);
					}
				}
			});
		}
	});
	
	if($(".allstudent tbody tr").length==0){
		$(".month").val("");
	}
	
	$(".select").change(function(){
		
		if($(this).closest("tr").prev("tr").find(".select").prop("checked")!= undefined){
		 
			if($(this).closest("tr").prev("tr").find(".select").prop("checked")==true ){
				if($(this).prop("checked")==true){
				  $(this).prop("checked",true);
				}
			else{
				if($(this).closest("tr").next("tr").find(".select").prop("checked")==true )
				  $(this).prop("checked",true);
				else
				  $(this).prop("checked",false);
			 }
			}
			else{
				$(this).prop("checked",false);
			 }
			}
			else if($(".select:checked").length>=1)
			{
				$(this).prop("checked",true);
			 }
	});
	
	
});

   function getRouteNameList(){

	   var locationid=$("#hiddenlocid").val();
	   datalist = {
			   "locationid" :locationid
		},
		$.ajax({
			type : 'POST',
			url : "transport.html?method=getRouteNameList",
			data : datalist,
			async : false,
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$(".routename").empty();
					$('.routename').append('<option value="">' + "----------Select----------"	+ '</option>');
					for ( var j = 0; j < result.routelist.length; j++) {
                       
						$('.routename').append('<option value="'

								+ result.routelist[j].routeCode + '">'

								+ result.routelist[j].routeName

								+ '</option>');
					}
			}
		});
}
   
   function getstoplist(routeid){
 
		   datalist = {
					"routeid" :routeid,
					"locId":$("#hiddenlocid").val()
				}, $.ajax({
					type : 'POST',
					url : "transport.html?method=getstoplist",
					data : datalist,
		 			async : false,
					
					success : function(response) {
						
						var result = $.parseJSON(response);
							$(".stopname").empty();
							$('.stopname').append('<option value="">' + "----------Select----------"	+ '</option>');
							for ( var j = 0; j < result.stoplist.length; j++) {
		                       
								$('.stopname').append('<option value="'+ result.stoplist[j].stage_id +'">'+result.stoplist[j].stopname
										+'</option>');
							}
					}
				});
		}
   
   
   function getamountandstatus(stageid,pointer)
   {
   
	   datalist = {
				"stageid" :stageid,
				"accId":$("#hiddenaccyid").val(),
				"locId":$("#hiddenlocid").val()
			}, $.ajax({
				type : 'POST',
				url : "transport.html?method=getamount",
				data : datalist,
	 			async : false,
				success : function(response) {
				var result = $.parseJSON(response);
			     
				 if(result.getamount==undefined || result.getamount==""){
					 $(".errormessagediv").show();
						$(".validateTips").text("Set the Stage wise amount for this Stop with "+$("#accyear").val()+" year");
						setTimeout(function(){
							$(".errormessagediv").show();
					},3000); 
				 }else{
					    var amount=result.getamount.split(",")[0];
			            var status =result.getamount.split(",")[1];  
			            pointer.closest('tr').find('.amount').val(amount);
			            pointer.closest('tr').find('.tranportstatus').val(status);
				     }
	              }
			 });
	   }
function getSelectedMonth(){
	 
	if($("#istransport").val() == 'Y' && $("#routeid").val()!=""){
		$("#waivedOff").show();
		$("#vehicletype").val($("#hiddenvehicle").val());
		getRouteNameList();
		$(".routename").val($("#routeid").val());
		$("#month").val($("#hiddenmonth").val());
		getstoplist($("#routeid").val());
		$(".stopname").val($("#stageid").val());
		getamountandstatus($("#stageid").val(),$(".stopname"));
		
		 if($(".select").length==$(".select:checked").length){
	         $(".selectAll").prop("checked",true);
         }
       else{
	           $(".selectAll").prop("checked",false);
           }
		
		if($("#startmonth").val() == "0000-00"){
			$(".selectAll").prop('checked',true);
			$(".select").prop('checked',true);
		}
		else{
			
			$('#'+$("#startmonth").val()).prop('checked', true);
			$("#"+$("#endmonth").val()).prop('checked',true);
			list=[];
			indexval=[];
			$(".select").each(function(i) {
				   if (this.checked) {
				       list.push(i);
				   }
				});
			for(var i=list[0];i<list[1]; i++){
				$("table.month tbody tr:nth-child("+(i+1)+")").find(".select").prop("checked",true);
			}
		}
	}
}

function getVehicleTypeList(){
		$.ajax({
			type : 'POST',
			url : "transport.html?method=getVehicleTypeList",
			async : false,
			
			success : function(response) {
				var result = $.parseJSON(response);
					$(".vehicletype").empty();
					$('.vehicletype').append('<option value="all">' + "----------Select----------"	+ '</option>');
					for ( var j = 0; j < result.routelist.length; j++) {
                    
						$('.vehicletype').append('<option value="'+result.routelist[j].typeId +'">'+result.routelist[j].typeName
								+'</option>');
					}
			  }
		});
}


function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

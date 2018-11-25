$(document).ready(function(){
	
	$("#accyear").val($("#curr_accy").val());
	
	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
	}
	
	$("#addleaves").click(function(){
		len = $(".select:checked").length;
		
		if(len == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
			setTimeout(function(){
				$(".errormessagediv").hide();
			},2000);
		}
		else{
			$("#leavedialog").dialog("open");
		}
	});
	
	$("#leaveback").click(function(){
		window.location.href="leavebank.html?method=StaffLeaveBank";
	});
	
	checkboxsselect();
	checkleaveboxselects($("#allLeaves"),$(".leaveselect"),$('.lapplicable'),'nol','lapplicable');
	
	$("#allstudent tbody tr").find('.Y').each(function(){
		$(this).closest('tr').find('.select').prop('disabled',true);
		$(this).append('<span class="glyphicon glyphicon-edit glyphipos edit" data-toggle="tooltip" data-original-title="Edit Leaves"></span><span class="glyphicon glyphicon-plus-sign addml" data-toggle="tooltip" data-original-title="Add Leaves"></span>');
	});
	
	$(".edit").click(function(){
		$("#addedit").html();
		$("#addedit").dialog("open");
		getStaffLeaves($(this));
		$("#updatetable").show();
		$("#addtable").hide();
		$(".ui-dialog-buttonset>#ladd").hide();
		$(".ui-dialog-buttonset>#lupdate").show();
	});
	
	$(".addml").click(function(){
		$("#addedit").html();
		$("#addedit").dialog("open");
		getUnmappedLeaves($(this));
		$("#addtable").show();
		$("#updatetable").hide();
		$(".ui-dialog-buttonset>#ladd").show();
		$(".ui-dialog-buttonset>#lupdate").hide();
	});
	
	$("#accyear").change(function(){
		getStaffLeaveBank();
	});
	
	
	$("#addedit").dialog({

		autoOpen 	: false,
		modal 		: true,
		maxWidth 	: 600,
		maxHeight	: "auto",
		width		: 600,
		height		: "auto",
		title     : "Leave Details",
		buttons   : {
			"Save": {
				text: 'Save', 
				id: 'ladd',  
				click: function(){
					addStaffLeaves();
					$(this).dialog('close');
				}
				},
				"Update": {
					text: 'Update', 
					id: 'lupdate',  
					click: function(){
						updateStafLeaves($("#addedit"))
						
						$("#addmul").prop('checked',false);
						}
					},
		'Close' : function() {
			$(".errormessagediv").hide();
			$(this).dialog('close');	
			$("#addmul").prop('checked',false);
		}
	}
	});
	
	$("#leavedialog").dialog({

		autoOpen 	: false,
		modal 		: true,
		maxWidth 	: 600,
		maxHeight	: "auto",
		width		: 600,
		height		: "auto",
		title     : "Leave Details",
		buttons   : {
			"Save": {
				text: 'Save', 
				id: 'savel',  
				click: function(){
					
					checkFields();
					$(this).dialog('close');	
					
				}
			},
			'Close' : function() {
				$(".errormessagediv").hide();
				$(this).dialog('close');	
			}
		}
	});
	
	$(".lapplicable").keyup(function(){
		
		var nol = parseFloat($(this).closest('tr').find('.nol').text());
		var lApplicable = parseFloat($(this).val());
		
		if(isNaN(lApplicable)){
			$(this).val(nol);
		}else if((lApplicable%0.5)!=0 && (lApplicable%0.5) < 0.5){
			$(this).val(nol);
		}
		else if(lApplicable > 0 && lApplicable > nol){
			$(this).val(nol);
		}
		
	});
	
});


function getStaffLeaveBank(){
	
	$.ajax({
		type : "POST",
		url : "leavebank.html?method=getStaffLeaveBank",
		data : {"academic_year" : $("#accyear").val(),
				"location" : $("#location").val()},
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			$(".allstudent thead").empty();
			$(".allstudent tbody").empty();
			$(".allstudent thead").append("<tr class='main-header'>" +
										  "<th rowspan='2' >Reg.Id</th>" +
										  "<th rowspan='2' style='min-width: 160px !important;'>Staff</th></tr>");
										 
			for(var i =0; i<result.llist.length; i++){
				$(".main-header").append("<th colspan='4'>" +result.llist[i].shortName+
												"</th>");
			}
			$(".allstudent thead").append("<tr class='leavetypes'></tr>");
			for(var j =0;j<result.llist.length;j++){
				
					for(k=0 ;k<result.total.length;k++){
						
						$('.leavetypes').append("<th>" +
								result.total[k] +
								"</th>");	
					}
			}
			for(var j =0;j<result.lbank.length;j++){
				
				$(".allstudent tbody").append("<tr class='teacher-details'>" +
						"<td>" +result.lbank[i].teaRegId+ "</td>"+
						"<td>" +result.lbank[i].teacherName+ "</td>"+
						"</tr>");
				var values = result.lbank[j].details;
				for(var i =0;j<values.length;i++){
					$(".teacher-details").append("<td>" +values[i].total_leave_year+"</td>" +
							"<td>" +values[i].totalleaves+"</td>" +
							"<td>" +values[i].lconsumed+"</td>" +
							"<td>" +values[i].lbal+"</td>");
				}
				
			}
		}
	});
}

function isNumberKey(evt){
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if (charCode > 31 && charCode !=46  && (charCode < 48 || charCode > 57))
	       return false;
	   return true;
}

function checkFields(){
	var status = false;
	len = $(".leaveselect:checked").length;

	if(len == 0){
		$(".errormessagediv").show();
		$(".validateTips").text("Select any one record");
		setTimeout(function(){
			$(".errormessagediv").hide();
		},3000);
	}else{
		
		var lids = [];
		var appl = [];
		var teaids = [];
		
		$('.select:checked').each(function(){
			teaids.push($(this).val());
		});
		
		$(".leaveselect:checked").each(function(){
			lids.push($(this).val());
			appl.push($(this).closest('tr').find('.lapplicable').val());
		});
		
		data = {
			"lids" : lids.toString(),
			"appl" : appl.toString(),
			"teaids" : teaids.toString(),
			"accyear" : $("#accyid").val(),
			"locid" : $("#locid").val()
		};
		
		$.ajax({
			
			type : "POST",
			url : "leavebank.html?method=addStaffLeaves",
			data : data,
			async : false,
			success : function(response){
				var result = $.parseJSON(response);
				if(result.staus = "success"){
					$(".successmessagediv").show();
					$(".success").text("Records added successfully");
					setTimeout(function(){
						$(".successmessagediv").hide();
						$("#selectall").prop('checked',false);
						getMapList();
					},3000);
				}else{
					$(".errormessagediv").show();
					$(".validateTips").text("Failed to add records");
					setTimeout(function(){
						$(".errormessagediv").hide();
					},3000);
				}
			}
		});
	}
}

function addStaffLeaves(){

	var status = false;
	len = $(".addsingle:checked").length;

	if(len == 0){
		$(".errormessagediv").show();
		$(".validateTips").text("Select any one record")
		setTimeout(function(){
			$(".errormessagediv").hide();
		},3000);
	}else{
		
		var lids = [];
		var appl = [];
		
		$(".addsingle:checked").each(function(){
			lids.push($(this).val());
			appl.push($(this).closest('tr').find('.addappl').val());
		});
		
		data = {
			"lids" : lids.toString(),
			"appl" : appl.toString(),
			"teaids" : $("#teaid").val(),
			"accyear" : $("#accyid").val(),
			"locid" : $("#locid").val(),
			"mapid" : $("#mapid").val()
		};
		
		$.ajax({
			
			type : "POST",
			url : "leavebank.html?method=addSingleStaffLeaves",
			data : data,
			async : false,
			success : function(response){
				var result = $.parseJSON(response);
				if(result.staus = "success"){
					$(".successmessagediv").show();
					$(".success").text("Records added successfully");
					setTimeout(function(){
						$(".successmessagediv").hide();
						$("#selectall").prop('checked',false);
						getMapList();
					},3000);
				}else{
					$(".errormessagediv").show();
					$(".validateTips").text("Failed to add records");
					setTimeout(function(){
						$(".errormessagediv").hide();
					},3000);
				}
			}
		});
	}

	
}

function checkboxsselect(){
	
	$("#selectall").change(function(){
		$(".select").not('.Y').prop('checked', $(this).prop("checked"));
	});
	
	$(".select").not('.Y').change(function(){
        if($(".select").not('.Y').length==$(".select:checked").not('.Y:checked').length){
	         $("#selectall").prop("checked",true);
         }
       else{
	           $("#selectall").prop("checked",false);
           }
	});
}

function getMapList(){
	
	$.ajax({
		
		type : "POST",
		url : "leavebank.html?method=getStaffMapSt",
		data : {"accyear" :$("#accyid").val() ,"locid" : $("#locid").val()},
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			
			$("#allstudent tbody").empty();
			for(var i = 0 ; i<result.maplist.length;i++){
				$("#allstudent tbody").append("<tr>"+
				"<td><input type='checkbox' class='select "+result.maplist[i].status+"' value='"+result.maplist[i].teacherId+"' id='"+result.maplist[i].leaveid+"'></td>"+
				"<td>"+result.maplist[i].teaRegId+"</td>"+
				"<td>"+result.maplist[i].teacherName+"</td>"+
				"<td class='"+result.maplist[i].status+"'><span class='"+result.maplist[i].mapstatus+"'>"+result.maplist[i].mapstatus+"</span></td>"+
				"</tr>"
				);
			}
			
			$("#allstudent tbody tr").find('.Y').each(function(){
				$(this).closest('tr').find('.select').prop('disabled',true);
				$(this).append('<span class="glyphicon glyphicon-edit glyphipos edit" data-toggle="tooltip" data-original-title="Modify Leaves"></span><span class="glyphicon glyphicon-plus-sign addml" data-toggle="tooltip" data-original-title="New Leaves"></span>');
			});
			
			 $('[data-toggle="tooltip"]').tooltip({
			        placement : 'bottom'
			    });
			
			$(".edit").click(function(){
				$("#addedit").html();
				$("#addedit").dialog("open");
				getStaffLeaves($(this));
				$("#updatetable").show();
				$("#addtable").hide();
				$(".ui-dialog-buttonset>#ladd").hide();
				$(".ui-dialog-buttonset>#lupdate").show();
			});
			
			$(".addml").click(function(){
				$("#addedit").html();
				$("#addedit").dialog("open");
				getUnmappedLeaves($(this));
				$("#addtable").show();
				$("#updatetable").hide();
				$(".ui-dialog-buttonset>#ladd").show();
				$(".ui-dialog-buttonset>#lupdate").hide();
			});
			
		}
	});
}

function getStaffLeaves(pointer){
	
	mapid = pointer.closest('tr').find('.select').attr('id');
	teaid = pointer.closest('tr').find('.select').val();	
	
	$("#mapid").val(mapid);
	$("#teaid").val(teaid);
	
	data = {
			"accyear" :$("#accyid").val() ,
			"locid" : $("#locid").val() ,
			"mapid" : mapid,
			"teaid" : teaid
	}
	
	$.ajax({
		type : "POST",
		url : "leavebank.html?method=getStaffMappedLeaves",
		data : data,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			
			$(".updateLeaves tbody").empty();
			for(var i = 0 ; i<result.MappedLeaves.length;i++){
				$(".updateLeaves tbody").append("<tr id='"+result.MappedLeaves[i].leaveid+","+result.MappedLeaves[i].noofleaves+"'>"+
				"<td>"+result.MappedLeaves[i].slno+"</td>"+
				"<td>"+result.MappedLeaves[i].shortName+"</td>"+
				"<td>"+result.MappedLeaves[i].leaveName+"</td>"+
				"<td>"+result.MappedLeaves[i].noofleaves+"</td>"+
				"<td><input size='4' type='text' class='form-control updateappl' value='"+result.MappedLeaves[i].available_leave+"' onkeypress='return isNumberKey(event)'></td>"+
				"</tr>"
				);
			}
			verifyAppL();
		}
	});
}

function verifyAppL(){
	
	$(".updateappl").keyup(function(){
		
		nol = parseFloat($(this).closest('tr').attr('id').split(',')[1]);
		lApplicable = parseFloat($(this).val());
		
		if(isNaN(lApplicable)){
			$(this).val(nol);
		}else if((lApplicable%0.5) != 0 && (lApplicable%0.5) <0.5){
			$(this).val(nol);
		}
		else if(lApplicable > 0 && lApplicable > nol){
			$(this).val(nol);
		}
	});
	
}

function updateStafLeaves(pointer){
	
	leaids = [];
	appleave = [];
	
	$(".updateLeaves tbody tr").each(function(){
		leaids.push($(this).attr('id').split(',')[0]);
		appleave.push($(this).find('.updateappl').val());
	});
	
	data = {
			"teaid" : $("#teaid").val(),
			"leaid" : leaids.toString(),
			"appleave" : appleave.toString(),
			"mapid" : $("#mapid").val()
	}
	
	$.ajax({
		type : "POST",
		url : "leavebank.html?method=updateStafLeaves",
		data : data,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			if(result.status == "success"){
				$(".successmessagediv").show();
				$(".success").text("Records updated successfully");
				setTimeout(function(){
					$(".successmessagediv").hide();
				},3000);
				$(pointer).dialog('close');
			}else{
				$(".errormessagediv").show();
				$(".validateTips").text("Failed to update records");
				setTimeout(function(){
					$(".errormessagediv").hide();
				},3000);
			}
		}
	});
}

function getUnmappedLeaves(pointer){
	
	teaid = pointer.closest('tr').find('.select').val();	
	
	$("#mapid").val(pointer.closest('tr').find('.select').attr('id'));
	$("#teaid").val(teaid);
	
	data = {
			"teaid" : $("#teaid").val(),
			"accyear" :$("#accyid").val(),
			"locid" : $("#locid").val()
	}
	
	$.ajax({
		type : "POST",
		url : "leavebank.html?method=getStaffUnmappedLeaves",
		data : data,
		async : false,
		success : function(response){
			var result = $.parseJSON(response);
			
			$(".addLeaves tbody").empty();
			
			if(result.list.length > 0){
				for(var i = 0; i<result.list.length; i++){
					$(".addLeaves tbody").append("<tr>"+
							"<td><input type='checkbox' class='addsingle' value='"+result.list[i].leaveid+"'></td>"+
							"<td>"+result.list[i].shortName+"</td>"+
							"<td>"+result.list[i].leaveName+"</td>"+
							"<td class='totl'>"+result.list[i].noofleaves+"</td>"+
							"<td><input size='4' maxlength='4' type='text' class='form-control addappl' readonly onkeypress='return isNumberKey(event)' value='"+result.list[i].noofleaves+"'></td>"+
							"</tr>");
				}
				checkleaveboxselects($("#addmul"),$(".addsingle"),$(".addappl"),'totl','addappl');
				$(".addappl").keyup(function(){
					
					nol = parseFloat($(this).closest('tr').find('.totl').text());
					lApplicable = parseFloat($(this).val());
					
					if(isNaN(lApplicable)){
						$(this).val(nol);
					}
					else if(lApplicable > 0 && lApplicable > nol){
						$(this).val(nol);
					}
					
				});
			}else{
				$(".addLeaves tbody").append("<tr><td colspan = '5'>Leaves Not Found</td></tr>")
			}
		}
	});
}

function checkleaveboxselects(mulsel,singsel,textbox,nols,textclass){
	
	mulsel.change(function(){
		singsel.prop('checked', $(this).prop("checked"));
		
		 if($(this).prop("checked") == true){
			 textbox.prop('readonly',false);
			 textbox.css({"background-color": "#fff"}); 
		 }else{
			 textbox.prop('readonly',true);
			 textbox.css({"background-color": "#f8f3f3"}); 
			 textbox.each(function(){
				 nol = $(this).closest('tr').find('.'+nols).text();
				 $(this).val(nol);
			 });
		 }
	});
	
	singsel.change(function(){
		
        if(singsel.length == singsel.filter(':checked').length){
        	mulsel.prop("checked",true);
        }
       else{
    	   mulsel.prop("checked",false);
        }
        
        if($(this).prop("checked") == true){
        	$(this).closest('tr').find('.'+textclass).prop('readonly',false);
        	$(this).closest('tr').find('.'+textclass).css({"background-color": "#fff"});
        }else{
        	$(this).closest('tr').find('.'+textclass).prop('readonly',true);
        	$(this).closest('tr').find('.'+textclass).css({"background-color": "#f8f3f3"});
        	nol = $(this).closest('tr').find('.'+nols).text();
			$(this).closest('tr').find('.'+textclass).val(nol);
        }
        
	});
}




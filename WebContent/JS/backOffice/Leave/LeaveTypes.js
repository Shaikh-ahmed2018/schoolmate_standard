rowid = 1;
$(document).ready(function(){

	$("#accyearp").val($("#curacayear").val())
	$("#locId").val($("#curr_loc").val());
	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records  "+0);
		pagination(100);
	}

	//addfirstleaves();	
	
	$("#addleavebank").click(function(){
		$("#leaveDialog").dialog("open");
		$("#academicyear").val($("#curacayear").val())
		$("#test").show();
		$("#update").hide();
	});	

	$("#locId").change(function(){
		getLeavetypes();
	});
	
	$("#accyearp").change(function(){
		getLeavetypes();
	});
	
	$(".glyphicon-edit").click(function(){
		$(".successmessagediv").hide();
		$(".errormessagediv").hide();
		$("#lvid").val($(this).closest("tr").attr('id'));
		$("#leaveDialog").dialog("open");
		getLeaveValues($(this))
		$("#test").hide();
		$("#update").show();
	});
	
	$(".glyphicon-trash").click(function(){
		$(".errormessagediv").hide();
		$(".successmessagediv").hide();
		$("#leavedelete").dialog("open");
		$("#leavedelete").find('.ui-dialog').css({
			"top" : "500px;"
		});
		$("#lvid").val($(this).closest("tr").attr('id'));
	});
	
	$("#lcode,#leaname,#nleave").keyup(function(){
		$(this).css({"background-color":"#fff",
					 "border":" 1px solid #ccc"
		});
	});
	
	$("#locationname").change(function(){
		$(this).css({"background-color":"#fff",
			 "border":" 1px solid #ccc"
		});
	});
	
	$(".leavecode,.leavename,.noofleaves").keyup(function(){
		$(this).css({"background-color":"#fff",
					 "border":" 1px solid #ccc"
		});
	});
	
	$("#leavedelete").dialog({
		
		resizable: false,
		autoOpen 	: false,
		modal 		: true,
		maxWidth 	: 300,
		maxHeight	: "auto",
		width		: 300,
		height		: "auto",
		title     : "Leave Types",
		buttons: {
			 "Yes": function() {
		        deleteLeaveDetails();
		        $(this).dialog("close");
		      },
		      "No": function() {
		          $(".errormessagediv").hide();
		          $(this).dialog("close");
		      }
		}
		
	}).dialog('widget').position({ my: 'center', at: 'center', of: $(this) });
	
	
	$("#leaveDialog").dialog({
		resizable: false,
		autoOpen 	: false,
		modal 		: true,
		maxWidth 	: 850,
		maxHeight	: "auto",
		width		: 850,
		height		: "auto",
		title     : "Leave Types",
		buttons   : {
			"Save": {
				text: 'Save', 
				id: 'test',  
				click: function(){
					
					if(!verifyTableData("add")){
						saveLeaveTypeDetails();
						clearfields();
						$(this).dialog('close');	
					}
				}
			} ,
			"Update": {
				text: 'Update', 
				id: 'update',  
				click: function(){
					if(!verifyTableData("edit")){
						updateLeaveDetails();
						clearfields();
						$(this).dialog("close");
					}
				}
	        },
			'Close' : function() {
				$(".errormessagediv").hide();
				$(this).dialog('close');	
				clearfields();
			}
		}
	});			 

	$("#addleaves").click(function(){
		addleaves();
	})
});

function verifyTableData(operation){
	
	var locId = $("#locationname");
	var accdemicY = $("#academicyear");
	leavecode = $("#leavecode");
	leavename = $("#leavename");
	noofleaves = $("#noofleaves");
	carryf =  $("#carryf");
	isgender = $("#isgender");
	isprob = $("#isprob"); 
	maxleave = $("#maxlm");
	minleave = $("#minlm");
	maxcons = $("#maxcl");
	weekoff = $("#weekoff");
	lcycle = $("#lcycle");
	
	nfleaves = parseFloat($("#noofleaves").val());
	
	var flag = false;
	
	if(locId.val().trim() == ""){
		showError(locId,"Field Required - School");
		flag = true;
	}else if(accdemicY.val().trim() == ""){
		showError(accdemicY,"Field Required - Academic Year");
		flag = true;
	}else if(leavename.val() == ""){
		showError(leavename,"Field Required - Leave Name");
		flag = true;
	}else if(verifyLeaveName(operation,leavename.val(),locId.val(),accdemicY.val())){
		showError(leavename,"Leave Name already exists for the selected academic year and school");
		flag = true;
	}
	else if(leavecode.val() == ""){
		showError(leavecode,"Field Required - Leave Code");
		flag = true;
	}else if(verifyLeaveCode(operation,leavecode.val(),locId.val(),accdemicY.val())){
		showError(leavecode,"Leave Code already exists for the selected academic year and school");
		flag = true;
	}
	else if(noofleaves.val() == ""){
		showError(noofleaves,"Field Required - No.Of Leaves");		
		flag = true;
	}else if(checkDec(noofleaves.val())){
		showError(noofleaves,"Enter valid number");		
		flag = true;
	}
	else if((nfleaves%0.5)!=0 && (nfleaves%0.5) < 0.5){
		showError(noofleaves,"Enter valid number");		
		flag = true;
	}
	else if(isprob.val().trim() == ""){
		showError(isprob,"Field Required - Probationary Period");	
		flag = true;
	}else if(minleave.val().trim() == ""){
		showError(minleave,"Field Required - Min. leaves per month");	
		flag = true;
	}else if(maxleave.val().trim() == ""){
		showError(maxleave,"Field Required - Max. leaves per month");
		flag = true;
	}else if(maxcons.val().trim() == ""){
		showError(maxcons,"Field Required - Max. consecutive leaves per month");
		flag = true;
	}
	//alert("flag "+ flag)
	return flag;
}

function verifyLeaveName(opn,lName,loc,accy){
	
	var IsFound = false;
	
	if(opn == "add"){
		IsFound = checkLeaveName(lName,loc,accy);
	}else if(opn == "edit" && $("#hlname").val().toLowerCase() != lName.toLowerCase()){
		IsFound = checkLeaveName(lName,loc,accy);
	}
	return IsFound;
}

function checkDec(value){
    var ex = /^[0-9]+\.?[0-9]*$/;
    if(ex.test(value)==false){
        return true;
    }
}

function verifyLeaveCode(opn,lCode,loc,accy){
	
	var IsFound = false;
	
	if(opn == "add"){
		IsFound = checkLeaveCode(lCode,loc,accy);
	}else if(opn == "edit" && $("#hlcode").val().toLowerCase() != lCode.toLowerCase()){
		IsFound = checkLeaveCode(lCode,loc,accy);
	}
	return IsFound;
}

function checkLeaveCode(leavecode,locid,accyear){

	var isfound = false;
	
	data = {
			"leavecode" : leavecode,
			"accyear" : accyear,
			"locid" : locid
	},
	$.ajax({
		type : 'POST',
		url : "leavebank.html?method=checkLeaveCode",
		data : data,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "found"){
				isfound = true;
			}
		}
	});
	return isfound;
}

function checkLeaveName(leavename,locid,accyear){
	var isfound = false;
		data = {
				"leavename" : leavename,
				"accyear" : accyear,
				"locid" : locid
		},
		$.ajax({
			type : 'POST',
			url : "leavebank.html?method=checkLeaveName",
			data : data,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "found"){
					isfound = true;
				}
			}
		});

	return isfound;
}

function deleteLeaveDetails(){
	
	$.ajax({
		type : 'POST',
		url : "leavebank.html?method=deleteLeaveDetails",
		data : {"leaveid" : $("#lvid").val()},
		beforeSend: function(){
			$("#loader").show();
		},
		success : function(data){
			var result = $.parseJSON(data);
			if(result.status == "success"){
				$("#loader").hide();
				$(".successmessagediv").show();
				$(".success").text("Leave Types deleted successfully...")
				setTimeout(function(){
					$(".successmessagediv").hide();
				},2000);
				getLeavetypes();
			}else{
				$("#loader").hide();
				$(".errormessagediv").show();
				$(".success").text("Failed to delete Leave Types")
				setTimeout(function(){
					$(".validateTips").hide();
				},2000);
			}
		},
		error : function(data){
			$("#loader").hide();
			$(".errormessagediv").show();
			$(".success").text("Failed to delete Leave Types")
			setTimeout(function(){
				$(".validateTips").hide();
			},2000);
		}
});
	
}

function changetxtBackgrd(){
	$("#lcode").css({"background-color":"#fff",
		 "border":" 1px solid #ccc"
	});
	$("#leaname").css({"background-color":"#fff",
		 "border":" 1px solid #ccc"
	});
	$("#nleave").css({"background-color":"#fff",
		 "border":" 1px solid #ccc"
	});
}

function clearfields(){
	$(".errormessagediv").hide();
	$("#leavecode").val("");
	$("#leavename").val("");
	$("#noofleaves").val("");
	$("#carryf").val("N");
	$("#isgender").val("A");
	$("#isprob").val("0");
	$("#maxlm").val("0");
	$("#minlm").val("0");
	$("#maxcl").val("0");
	$("#weekoff").val("N");
	$("#lcycle").val("A");
	
	$("#leavecode").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#leavename").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#noofleaves").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#carryf").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#isgender").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#isprob").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#leavecode").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#maxlm").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#minlm").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#maxcl").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#weekoff").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$("#lcycle").css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
}

function convertUpperCase(pointer){
	$(pointer).val($(pointer).val().toUpperCase());
}

function addleaves(){
	$(".allstudent tbody").append("<tr id='row"+rowid+"'>"+
							"<td><input type='text' name='leavecode' class='leavecode' maxlength='4' size='4'/></td>"+
							"<td><input type='text' name='leavename' class='leavename' onkeypress='return isAlphbets(event)'/></td>"+
							"<td class='leaveno'><input type='text' name='noofleaves' class='noofleaves'  maxlength='4' size='4' onkeypress='return isNumberKey(event)'/></td>"+
							"<td><label class='radio-inline'><input type='radio' value='YA' name='laccu"+rowid+"' class='laccu'>Yearly</label><label class='radio-inline'><input type='radio' value='MA' name='laccu"+rowid+"' class='laccu' >Monthly</label></td>"+
							"<td><label class='radio-inline'><input type='radio' value='YC' class='carryf' name='carryf"+rowid+"'>Yearly</label><label class='radio-inline'><input type='radio' value='MC' class='carryf' name='carryf"+rowid+"'>Monthly</label><span class='glyphicon glyphicon-remove delete radio-inline' onclick='removeRows(row"+rowid+")'></span></td>"+
							"</tr>");
	rowid++;
	convertUpperCase();
}

function addfirstleaves(){
	$(".allstudent tbody").append("<tr id='row"+rowid+"'>"+
							"<td><input type='text' name='leavecode' class='leavecode' maxlength='4' size='4'/></td>"+
							"<td><input type='text' name='leavename' class='leavename' onkeypress='return isAlphbets(event)'/></td>"+
							"<td class='leaveno'><input type='text' name='noofleaves' class='noofleaves' maxlength='4' size='4' onkeypress='return isNumberKey(event)'/></td>"+
							"<td><label class='radio-inline'><input type='radio' value='YA' name='laccu"+rowid+"' class='laccu'>Yearly</label><label class='radio-inline'><input type='radio' value='MA' name='laccu"+rowid+"' class='laccu' >Monthly</label></td>"+
							"<td><label class='radio-inline'><input type='radio' value='YC' class='carryf' name='carryf"+rowid+"'>Yearly</label><label class='radio-inline'><input type='radio' value='MC' class='carryf' name='carryf"+rowid+"'>Monthly</label></td>"+
							"</tr>");
	rowid++;
	convertUpperCase();
}

function removeRows(rowid){
	rowid.remove();
}

function isNumberKey(evt){
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && charCode !=46  && (charCode < 48 || charCode > 57))
       return false;
   return true;
}

function isAlphbets(evt){
	var charCode = (evt.which) ? evt.which : event.keyCode
	if(charCode != 32 && charCode > 31 && charCode!=40 && charCode!=41 && charCode!=91 && charCode!=93 && charCode!=45 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
		return false;
	return true;
}

function saveLeaveTypeDetails(){
	
	datalist = {
			"locId" : $("#locationname").val(),
			"accdemicY" : $("#academicyear").val(),
			"leavecode" : $("#leavecode").val(),
			"leavename" : $("#leavename").val(),
			"noofleaves" : $("#noofleaves").val(),
			"carryf" : $("#carryf").val(),
			"isgender" : $("#isgender").val(),
			"isprob" : $("#isprob").val(),
			"maxleave" : $("#maxlm").val(),
			"minleave" : $("#minlm").val(),
			"maxcons" : $("#maxcl").val(),
			"weekoff" : $("#weekoff").val(),
			"lcycle" : $("#lcycle").val()
	}
	
	$.ajax({
			type : 'POST',
			url : "leavebank.html?method=addLeavePolicy",
			data : datalist,
			beforeSend: function(){
				$("#loader").show();
			},
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "success"){
					$("#loader").hide();
					$(".successmessagediv").show();
					$(".success").text("Leave Types added successfully...")
					setTimeout(function(){
						$(".successmessagediv").hide();
					},2000);
					getLeavetypes();
				}else{
					$("#loader").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Failed to add Leave Types")
					setTimeout(function(){
						$(".errormessagediv").hide();
					},2000);
				}
			}
	});
}

function getLeavetypes(){
	
	data = {
			"location" : $("#locId").val(),
			"accyear" : $("#accyearp").val()
	}
	
	$.ajax({
		type : 'POST',
		url : "leavebank.html?method=getLeavetypes",
		data : data,
		beforeSend: function(){
			$("#loader").show();
		},
		success : function(data){
			var result = $.parseJSON(data);
			
			$("#allstudent tbody").empty();
			
			if(result.leaveDetails.length > 0){
				for(var i = 0; i< result.leaveDetails.length; i++){
					
					$("#allstudent tbody").append("<tr id='"+result.leaveDetails[i].leaveid+"'>" +
							"<td class='slno'>"+result.leaveDetails[i].count+"</td>" +
							"<td class='sname'>"+result.leaveDetails[i].shortName+"</td>" +
							"<input type='hidden' id='hlcode' class='form-control' />"+
							"<td class='lname'>"+result.leaveDetails[i].leaveName+"</td>" +
							"<input type='hidden' id='hlname' class='form-control' />"+
							"<td class='accu'>"+result.leaveDetails[i].noofleaves+"<span class='glyphicon glyphicon-trash glyphipos'></span><span class='glyphicon glyphicon-edit glyphipos'></span></td>"+
							"</tr>");
					$("#loader").hide();
				}
			}else{
					$("#allstudent tbody").append("<tr><td colspan='4'>NO Records Found</td></tr>");
					$("#loader").hide();
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.leaveDetails.length);
			pagination(100);
			
			$(".glyphicon-edit").click(function(){
				$(".successmessagediv").hide();
				$(".errormessagediv").hide();
				$("#lvid").val($(this).closest("tr").attr('id'));
				$("#leaveDialog").dialog("open");
				getLeaveValues($(this))
				$("#test").hide();
				$("#update").show();
			});
			$(".glyphicon-trash").click(function(){
				$(".successmessagediv").hide();
				$(".errormessagediv").hide();
				$("#leavedelete").dialog("open");
				$("#leavedelete").parent('.ui-dialog').css({
					"top" : "500px !important"
				});
				$("#lvid").val($(this).closest("tr").attr('id'));
				
			});
		}
	});
}

function updateLeaveDetails(){
	
	data = {
			"location" : $("#locationname").val(),
			"accyear" : $("#academicyear").val(),
			"leavecode" : $("#leavecode").val(),
			"leavename" :  $("#leavename").val(),
			"noofleave" : $("#noofleaves").val(),
			"carryf" : $("#carryf").val(),
			"isgender" : $("#isgender").val(),
			"isprob" : $("#isprob").val(),
			"maxleave" : $("#maxlm").val(),
			"minleave" : $("#minlm").val(),
			"maxcons" : $("#maxcl").val(),
			"weekoff" : $("#weekoff").val(),
			"lcycle" : $("#lcycle").val(),
			"leaveid" : $("#lvid").val()
	}
	
	$.ajax({
		type : 'POST',
		url : "leavebank.html?method=updateLeaveDetails",
		data : data,
		beforeSend: function(){
			$("#loader").show();
		},
		success : function(data){
			var result = $.parseJSON(data);
			
			if(result.status == "success"){
				$("#loader").hide();
				$(".successmessagediv").show();
				$(".success").text("Leave Type updated successfully...")
				setTimeout(function(){
					$(".successmessagediv").hide();
				},2000);
				getLeavetypes();
			}else{
				$("#loader").hide();
				$(".errormessagediv").show();
				$(".validateTips").text("Failed to update Leave Type")
				setTimeout(function(){
					$(".errormessagediv").hide();
				},2000);
			}
		}
	});
}

function getLeaveValues(pointer){
	
	$.ajax({
		type : 'POST',
		url : "leavebank.html?method=getLeavePolicy",
		data : {"id" : pointer.closest('tr').attr('id')},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#locationname").val(result.dataList[0].locId);
			$("#academicyear").val(result.dataList[0].accyear);
			$("#leavename").val(result.dataList[0].leaveName);
			$("#leavecode").val(result.dataList[0].shortName);
			$("#noofleaves").val(result.dataList[0].noofleaves);
			$("#isgender").val(result.dataList[0].isGenderSpec);
			$("#isprob").val(result.dataList[0].isprobationary);
			$("#minlm").val(result.dataList[0].min_lea_per_month);
			$("#maxlm").val(result.dataList[0].max_leav_per_month);
			$("#lcycle").val(result.dataList[0].leave_cycle);
			$("#maxcl").val(result.dataList[0].max_consecu_lea_perm);
			$("#carryf").val(result.dataList[0].carryFs);
			$("#weekoff").val(result.dataList[0].week_off_inclusion);
			$("#hlcode").val(result.dataList[0].shortName);
			$("#hlname").val(result.dataList[0].leaveName)
		}
	});
	
}

function showError(id,msg){
	id.css({"border": "1px solid rgb(175, 44, 44)",
		"background-color" : "rgb(255, 247, 247)"});
	$(".errormessagediv1").show();
	$(".validateTips1").text(msg)
	setTimeout(function(){
		$(".errormessagediv1").hide();
	},2000);
	return false;
}
function HideError(id){
	$(id).css({"border": "1px solid rgb(204, 204, 204)",
		"background-color" : "rgb(255, 255, 255)"});
	$(".errormessagediv1").hide();
}

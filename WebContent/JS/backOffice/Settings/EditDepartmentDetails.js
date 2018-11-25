function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}
function handle(e) {
	var searchname = $("#searchname").val();
	if (e.keyCode === 13) {
		e.preventDefault(); // Ensure it is only this code that rusn
		searchDepartmentDetailList();
		/*window.location.href = "departmentMenu.html?method=searchDepartment&searchname="+searchname+"&status="+$("#status").val();*/
	}
}


$(document).ready(function() {
	 
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchname").val("");
		getConcessionDetails();
	});
	
	if($("#allstudent tbody tr").length == 0){
		$("#allstudent tbody ").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
		$(".numberOfItem").empty();
		$(".numberOfItem").append("No. of Records "+$("#allstudent tbody tr").length);
		pagination(100);
		$("#selectall").prop("checked",false);
	}

	$("#dialog").hide();
	setTimeout("removeMessage()", 3000);

	setInterval(function() {
		$(".errormessagediv").hide();}, 3000);


	$("#editdep").click(function(){
		$(".errormessagediv").hide();
		var count = 0;
		$('.select:checked').map(function() {
			getData = $(this).attr("id");
			count++;
		});
		if (count == 0 || count > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one Department");
			return false;
		} 
		else
		{
			window.location.href = "departmentMenu.html?method=editDepartment&name="+getData+"&status="+$("#status").val()+"&searchname="+$("#searchname").val();	
		}

	});




	checkboxsselect();
	reason=null;
	var status = "N";
	$("#status").change(function(){

		if(this.value=="Y"){
			$("#deleteid").text("InActive"); 
			status = 'N';
			$("#editdep").show();
		}
		else{
			$("#deleteid").text("Active"); 
			status = 'Y';
			$("#editdep").hide();
		}
		searchDepartmentDetailList();
		$("#selectall").prop("checked",false);

	});

	$("#deleteid").click(function(e){
		var count = 0;
		var  getDataArray=[];
		$(".select:checked").map(function() {

			var checkdep_id = $(this).attr("id");
			getDataArray.push(checkdep_id);

			count++;
		});
		if(count==0){
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Department");

			return false;

		}else{
			
			if($(this).text()=="InActive"){
				status="N";
			}
			else{
				status="Y";
			}
			
			
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#deleteid").text()+"?</p>");
			$("#dialog").append('<p id="warningreason" class="warningfont" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Department, you won\'t be able to view details related to this Department.</p>');
			$("#dialog").append('<label>Reason:</label>');
			$("#dialog").append('<select name="feecanreason" style="width: 100%;" id="feecanreason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Incorrect Entry">Incorrect Entry</option>'
					+ '<option value="Not In Use">' + "Not In Use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');

			$("#dialog").append('<select name="activereason" style="width: 100%;" id="activereason" onchange="HideError(this)">'
					+ '<option value="">' + "----------select----------"
					+ '</option>'
					+ '<option value="Correct Entry">' + "Correct Entry"
					+ '</option>'
					+ '<option value="In use">' + "In use" 
					+ '</option>'
					+ '<option value="others">' + "Others" 
					+ '</option>'+
			'</select>');

			$("#dialog").append('<div id="othreason"><label for="">OtherReason:</label><input type="text" style="width: 100%;" name=other id="otherreason" onclick="HideError(this)"/></div>');

			$("#othreason").hide();
			$("#activereason").hide();
			$('#feecanreason').change(function(){
				$(".errormessagediv").hide();
				var othersres=$('#feecanreason').val();
				if(othersres == 'others'){

					$("#othreason").show(); 
					$("#activereason").hide();
				}else{
					$("#otherreason").val("");
					$("#othreason").hide();
					$("#feecanreason").show();
					$("#activereason").hide();
				}
				reason = $("#feecanreason").val();
			});

			if($("#status").val()=="N"){
				$("#othreason").hide();
				$("#activereason").show();
				$("#feecanreason").hide();
			}
			$('#activereason').change(function(){

				if($(this).val() == 'others'){
					$("#othreason").show(); 
					$("#activereason").show();
					$("#feecanreason").hide();
				}
				else{
					$("#otherreason").val("");
					$("#othreason").hide();
					$("#feecanreason").hide();
					$("#activereason").show();
				}
				reason = $("#activereason").val();
			});
		}
		$("#hiddendeptId").val(getDataArray);
	});

	$("#dialog").dialog({
		autoOpen  : false,
		resizable : false,
		modal     : true,
		title     : "Department Details",
		buttons   : {
			'Yes' : function() {
				var inactivereason=$("#feecanreason").val();
				var activereason=$("#activereason").val();

				if($("#deleteid").text()=="Active" && activereason.length == 0)
				{
					showError("#activereason","Select Any One Reason");
					return false;

				}
				else if($("#deleteid").text()=="InActive" && inactivereason.length == 0){

					showError("#feecanreason","Select Any One Reason");
					return false;
				}


				else if($("#deleteid").text()=="InActive" && $("#feecanreason").val() == "others"){

					if($("#otherreason").val().length==0 || $("#otherreason").val().trim()=="") {
						showError("#otherreason","Field Required OtherReason");
						return false;   	
					}

					else{
						reason = $("#otherreason").val();
						deleteDialogRecord(status,reason, $("#hiddendeptId").val());
						$(this).dialog("close");
					}
				}

				else if ($("#deleteid").text()=="Active" &&$("#activereason").val()=="others") {

					if($("#otherreason").val().length==0 || $("#otherreason").val().trim()==""){
						showError("#otherreason","Field Required OtherReason");
						return false;
					}
					else{
						reason = $("#otherreason").val();

						deleteDialogRecord(status,reason, $("#hiddendeptId").val());
						$(this).dialog("close");
					}
				}
				else{
					deleteDialogRecord(status,reason,$("#hiddendeptId").val());
					$(this).dialog("close");
				}
			},
			'No' : function() {
				$(this).dialog('close');
			}
		}
	});

	$("#searchname").keypress(function(e){
		var key = e.which;
		if(key==13){
			searchDepartmentDetailList();
		}

	});

	$("#search").click(function(){
		searchDepartmentDetailList();
	});

	if($("#hiddenstatus").val()!=""){
		$("#status").val($("#hiddenstatus").val());
		$("#searchname").val($("#hiddensearchname").val());

		if($("#status").val()=="Y"){
			$("#editdep").show();
			$("#deleteid").text("InActive"); 
		}
		else{
			$("#editdep").hide();
			$("#deleteid").text("Active"); 
		}
	}

	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){

		if($("#currentstatus").val()=="N"){ 
			$("#editdep").hide();
			$("#status").val("N");
			$("#deleteid").text("Active");
		}
		else{
			$("#editdep").show();
			$("#status").val("Y");
			$("#deleteid").text("InActive");
		}
		searchDepartmentDetailList();
	}
});			


function deleteDialogRecord(status,reason){
	dataList={
			'getDataArray':$("#hiddendeptId").val().toString(),
			'reason':reason,
			'status':status,
			'button':$("#deleteid").text(),
	};


	$.ajax({
		type : 'POST',
		url : "departmentMenu.html?method=deleteDepartment",
		async : false,
		data : dataList,
		success : function(response) {
			var result = $.parseJSON(response);
          var flag=false;
			$('.errormessagediv').hide();


			if(result.status=="true"){
				flag=true;
				$(".successmessagediv").show();
				$(".successmessagediv").attr("style","width:150%;margin-left:-250px;");
				$(".validateTips").text($("#deleteid").text()+" "+"Department Details Progressing...");
				$(".successmessagediv").css({
					'z-index':'9999999',
				});
			}
			else if(result.status=="false"){
				$('.errormessagediv').show();
				$('.validateTips').text("Selected department details cannot be"+" "+$("#deleteid").text());
				$(".errormessagediv").css({
					'z-index':'9999999'
				});
				
			}
			else{
				$('.errormessagediv').show();
				$('.validateTips').text("Selected department details cannot be"+" "+$("#deleteid").text());
				$(".errormessagediv").css({
					'z-index':'9999999'
				});
				
			}
 if(flag){
	 setTimeout(function(){
			
			$("#selectall").prop("checked",false);
			window.location.href = "menuslist.html?method=departmentDetails&currentstatus="+status;
		},3000);
 }
 else{
	 setTimeout(function(){
	 $('.errormessagediv').show();
	 },3000);
		getDataArray=[];
		$("#selectall").prop("checked",false);
	 
 }
 

		}

	});

}

function getDeptStatusList(){
	dataList={

			"status":$("#status").val(),
	};

	$.ajax({
		type:'POST',
		url:'menuslist.html?method=getDeptStatusList',
		data:dataList,
		async:false,
		success: function(data){

			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.DepartmentDetailsStatusList.length>0){

				for(var i=0;i<result.DepartmentDetailsStatusList.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' id='"+result.DepartmentDetailsStatusList[i].depId+"' /></td>" +
							"<td>"+result.DepartmentDetailsStatusList[i].depName+"</td>" +
							"<td>"+result.DepartmentDetailsStatusList[i].desc+"</td>" +
							"<td>"+result.DepartmentDetailsStatusList[i].remark+"</td>" +

					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
			}
			checkboxsselect();	
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.DepartmentDetailsStatusList.length);
			pagination(100);
		}
	});

}

function searchDepartmentDetailList(){
	dataList={
			"searchname":$("#searchname").val().trim(),
			"status":$("#status").val(),
	};

	$.ajax({
		type:'POST',
		url:'departmentMenu.html?method=searchDepartmentDetailList',
		data:dataList,
		async:false,
		success: function(data){

			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.searchDepartmentDetails.length>0){

				for(var i=0;i<result.searchDepartmentDetails.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' class='select' name='select' id='"+result.searchDepartmentDetails[i].depId+"' /></td>" +
							"<td>"+result.searchDepartmentDetails[i].depName+"</td>" +
							"<td>"+result.searchDepartmentDetails[i].desc+"</td>" +
							"<td>"+result.searchDepartmentDetails[i].remark+"</td>" +

					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
			}
			checkboxsselect();	
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.searchDepartmentDetails.length);
			pagination(100);
		}
	});

}


function checkboxsselect(){
	$("#selectall").change(function(){
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

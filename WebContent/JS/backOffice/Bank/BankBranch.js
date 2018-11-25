$(document).ready(function(){
	
	$("#resetbtn").click(function(){
		$("#status").val("Y");
		$("#searchtext").val("");
		searchBankBranch();
	});
	
	//var status = "N";

	checkboxsselect();
	if($("#allstudent tbody tr").length==0){
		$("#allstudent tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
		pagination(100);
	}

	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){

		if($("#currentstatus").val()=="Y"){ 
			$("#status").val("N");
			//status="N";
			$("#Edit").hide();
			$("#Remove").text("Active");
			searchBankBranch();
		}
		else{
			$("#status").val("Y");
			//status="Y";
			$("#Edit").show();
			$("#Remove").text("InActive");
			searchBankBranch();
		}
	}

	$("#Edit").click(function(){
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).val();
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			setTimeout(function() {
				$('#errorhover').fadeOut();

			}, 3000);
			return false;
		}
		else {
			window.location="bankBranchMaster.html?method=editBranch&branchId="+getData+"&status="+$("#status").val()+"&searchtext="+$("#searchtext").val();
		}
	});

	$("#search").click(function(){
		searchBankBranch();

	});

	$("#searchtext").keypress(function(e){
		var key = e.which;
		if(key==13){
			searchBankBranch();
		}
	});

	reason=null;

	$("#status").change(function(){
		if($(this).val()=="Y"){ 
			$("#Edit").show();
			$("#Remove").text("InActive");
		}
		else{
			$("#Edit").hide();
			$("#Remove").text("Active");
		}
		searchBankBranch();

	});

	$("#Remove").click(function(){
		var bankId=[];
		var count =0;
		$(".select:checked").each(function(){
			var Ids=$(this).val();
			bankId.push(Ids);
			count ++;
		});	

		$("#bankids").val(bankId);

		if(count == 0)	{
			$('.errormessagediv').show();
			$('.validateTips').text("Select Records to "+$("#Remove").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#Remove").text()+"?</p>");
			$("#dialog").append('<label class="warningothers" for="">Reason:</label>');
			$("#dialog").append('<div id="othreason"><textarea style="width: 100%;" class="warningfont" rows="3" name=other id="otherreason" onclick="HideError(this)"/></textarea></div>');

			reason = $("#otherreason").val();
		}

	});
	$("#dialog").dialog({	

		autoOpen: false,
		modal: true,
		title:'Bank Branch Master',
		buttons : {
			"Yes" : function() {

				var otherreason=$("#otherreason").val();

				if((otherreason.trim() == "")){
					document.getElementById("otherreason").style.border = "1px solid #AF2C2C";
					document.getElementById("otherreason").style.backgroundColor = "#FFF7F7";

					$(".errormessagediv").show();
					$(".validateTips").text("Field Required - Reason");
					setTimeout(function() {
						$('#errormessagediv').fadeOut();
					},3000);
				}
				else{
					$.ajax({
						method:'post',
						url:'bankBranchMaster.html?method=removeBranch',
						data:{
							"removeId":$("#bankids").val().toString(),
							"reason":otherreason,
							"button":$("#status option:selected").val(),
							},
						async:false,
						success: function(data){   
							var result = $.parseJSON(data);

							if(result.result == "success")
							{
								$('#selectall').prop('checked', false);
								$('.errormessagediv').hide();
								$(".successmessagediv").show();
								$(".validateTips").text("Bank Branch "+$("#Remove").text()+" Successfully");
								setTimeout(function() {
									$(".successmessagediv").show();
									//window.location="menuslist.html?method=bankbranchList&currentstatus="+$("#status").val();
									//window.location="menuslist.html?method=bankbranchList";
									if(result.status == "Y" && result.button == "N"){
										$("#status option[value='Y']").prop('selected', 'true');
										$("#Edit").show();
										$("#Remove").text("InActive");
									}else if(result.status == "N" && result.button == "Y"){
										$("#status option[value='N']").prop('selected', 'true');
										$("#Edit").hide();
										$("#Remove").text("Active");
									}
									searchBankBranch();
								}, 2000);
							}
						}
					});

					$(this).dialog("close");
				}
			},
			"No" : function() {
				$(this).dialog("close");
			}
		}
	});

	if($("#historysearchname").val()!="" || $("#historystatus").val()=="N"){
		$("#searchtext").val($("#historysearchname").val());
		$("#status").val($("#historystatus").val());

		if($("#status").val()=="Y"){ 
			$("#Edit").show();
			$("#Remove").text("InActive");
		}
		else{
			$("#Edit").hide();
			$("#Remove").text("Active");
		}
		searchBankBranch();
	}

});

function searchBankBranch(){
	var status= $("#status").val();
	var searchText = $("#searchtext").val().trim();
	$.ajax({
		type : 'POST',
		url :'bankBranchMaster.html?method=searchBankBranch',
		data : {

			"status":status,
			"searchText":searchText,
		},
		async : false,

		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.searchBankBranchList.length>0){
				for(var i=0;i<result.searchBankBranchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select'  value='"+result.searchBankBranchList[i].id+"'/></td>" 
							+"<td> "+result.searchBankBranchList[i].bankname+" </td>"
							+"<td> "+result.searchBankBranchList[i].branchName+"</td>"
							+"<td> "+result.searchBankBranchList[i].branchShortName+" </td>"
							+"<td> "+result.searchBankBranchList[i].ifscCode+" </td>"
							+"<td style='width:250px;'><textarea rows='3' cols='30' readonly='readonly' style='resize: none;'>"+result.searchBankBranchList[i].address+"</textarea> </td>"
							+"<td> "+result.searchBankBranchList[i].reason+" </td>"

							+"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}

			checkboxsselect()
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.searchBankBranchList.length);
			
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
function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


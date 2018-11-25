rowid = 1;
$(document).ready(function(){
	
	$("#accyearp").val($("#curacayear").val());
	
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
					}
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 3000);
					
					$("#totalleave").change(function() {

						var total = $('#totalleave').val();
						if (total <= 5 && total > 0) {
							var permonth = 1;
							$("#allowedleave").val(permonth);
						} else if (total == 0) {
							var permonth = 0;
							$("#allowedleave").val(permonth);
						} else {
							calculate(total);
						}
					});

					function calculate(total) {

						var permonth = Math.round(total / 12);
						$("#allowedleave").val(permonth);

					}
					
					$("#paidleave,#sickleave,#casualleave").change(function()
							
							{
								
								var pl = $("#paidleave").val();
								
								var sl = $("#sickleave").val();
								
								var cl = $("#casualleave").val();
								
								var totalval=Number(pl) + Number(sl) + Number(cl);
								
								$("#totalleave").val(totalval);
								
								if (totalval <= 5 && totalval > 0) {
									var permonth = 1;
									$("#allowedleave").val(permonth);
								} else if (totalval == 0) {
									var permonth = 0;
									$("#allowedleave").val(permonth);
								} else {
									calculate(totalval);
								}
								
								
								
								
							});
					
					
					
					
				$("#search")
					.click(
							function()

							{
								var searchvalue = $('#searchvalue').val();
						
								window.location.href ="menuslist.html?method=LeaveCategoryList&searchvalue="
									+ searchvalue;
								
							});		
										
					
					var hidden1 = $('#hiddenAccYear').val();

					$("#academicyear option[value=" + hidden1 + "]").attr(
							'selected', 'true');

					
					$('#xlss')
					.click(
							function() {
								
								window.location.href = 'leavebank.html?method=leavebankexcel';
								
							});

					$("#pdfDownload").click(function(){
						
						window.location.href = "leavebank.html?method=leavebankpdf";
							
					});	
					
					
/*	$('#addleavebank').click(function(){
		window.location.href = "leavebank.html?method=addingleaveCategoryscreen";
	});*/
					
					$('#submit').click(function(){

						var snoid = $("#snoid").val();

						var academicyear = $('#academicyear').val();

						var paidleave = $('#paidleave')	.val();

						var totalleave = $('#totalleave')	.val();

						var sickleave = $('#sickleave')	.val();

						var casualleave = $('#casualleave')	.val();

						var allowedleave = $('#allowedleave')	.val();

						if(academicyear=="")
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Select Academic Year");

							document.getElementById("academicyear").style.border = "1px solid #AF2C2C";
							document.getElementById("academicyear").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}

						else if(sickleave==""||sickleave==null)
						{
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Enter Sick Leave");
							document.getElementById("sickleave").style.border = "1px solid #AF2C2C";
							document.getElementById("sickleave").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
						else if(paidleave==""||paidleave==null)
						{
							$(".errormessagediv").show();
							$(".validateTips").text(
							"Enter Paid Leave");
							document.getElementById("paidleave").style.border = "1px solid #AF2C2C";
							document.getElementById("paidleave").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
						else if(casualleave==""||casualleave==null)
						{
							$(".errormessagediv").show();
							$(".validateTips").text("Enter Casual Leave");
							document.getElementById("casualleave").style.border = "1px solid #AF2C2C";
							document.getElementById("casualleave").style.backgroundColor = "#FFF7F7";
							setTimeout(function() {
								$('.errormessagediv').fadeOut();
							}, 3000);

							return false;
						}
					
						else{
							datalist = {
									"academicyear" : academicyear,
									"paidleave" : paidleave,
									"totalleave" : totalleave,
									"sickleave" : sickleave,
									"casualleave" : casualleave,
									"allowedleave" : allowedleave,
									"snoid" : snoid

							},
							callAjaxLeave("leavebank.html?method=validAddLeave", datalist);
							if (result.validate == false) {
								$.ajax({
									type : "POST",
									url : "leavebank.html?method=submit",
									data : datalist,
									success : function(data)
									{
										var result = $.parseJSON(data);

										window.location.href = "menuslist.html?method=LeaveBankList&result="+ result.jsonResponse;
									}
								});
							}else{
								$(".errormessagediv").show();
								$(".validateTips").text("Year Already Exists");
								
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
								}, 5000);
							}
							

						}
					});
					
				
					
					$("#allstudent tbody tr").click(function() {

								var idString =$( this ).find(".accyear").attr( "id" ).split(",");
									window.location.href = "leavebank.html?method=editleavetypes&accyear="
											+ idString[0]+"&loc="+idString[1];
					});
					
					
					 $("#deleteleavebank").click(function(){
						  
							var snoList = new Array();
							var selectArray = document.getElementsByName('selectBox');
							
							//alert("selectArray"+selectArray);
							
							var count = 0;
							
							for ( var i = 0; i < selectArray.length; i++) {
								if (selectArray[i].checked == true) {

									snoList.push(selectArray[i].value);
								}
							}

							$('input[name="selectBox"]:checked').each(function() {
								count = count + 1;
								
							});
							

							if (count > 1 || count == 0) {
								
								    $(".errormessagediv").show();
									$(".validateTips").text("Select Any One record");
									  setTimeout(function() { $('.errormessagediv').fadeOut();  }, 3000);
							} else {
								
								$("errormessagediv").hide();
								var X = confirm("Are you sure to delete the record");
								if(X){
									
														
									var leavelist={"year":snoList };
									
								
									
								    $.ajax({
										 type: 'POST', 
										  url: "leavebank.html?method=deleteLeavebank",
								          data:leavelist,
								          async:false,
										  success: function(response) {
											  
												var result = $.parseJSON(response);
												
												if (result.deletestatus == true) {
													deletekey = "Leave Data Deleted Successfully";
												} else {
													deletekey = "Deletion failed";
												}	 
												window.location.href="menuslist.html?method=LeaveBankList&deletekey="+ deletekey;
												
										  }
									 });
								   }
								}
						});
						addfirstleaves();			
	$("#addleavebank").click(function(){
		$("#myDialog").dialog("open");
	});	
					 
	$("#myDialog").dialog({
		
		 autoOpen 	: false,
		 modal 		: true,
		 maxWidth 	: 850,
		 maxHeight	: 450,
		 width		: 850,
		 height		: 450,
		    title     : "Leave Types",
		    buttons   : {
	 			"Save": {
		    	 text: 'Save', 
		         id: 'test',  
		         click: function(){
		        	 
		        	 	//verifyTableData();
		        	 	leaveCodes = [];
		        		leaveNames = [];
		        		noofLeaves = [];
		        		accumuLation = [];
		        		carryF = [];
		        		accdemicY = $("#locationname");
		        		locId = $("#academicyear");
		        		
		        		flag = false;
		        		
		        		if(accdemicY.val().trim() == ""){
		        			accdemicY.css({"border": "1px solid rgb(175, 44, 44)",
 							   "background-color" : "rgb(255, 247, 247)"});
		        			$(".errormessagediv").show();
		        			$(".validateTips").text("Field Required - Leave Code")
		        			setTimeout(function(){
		        				$(".errormessagediv").hide();
		        			},2000);
		        			flag = true;
		        			return false;
		        		}else if(locId.val().trim() == ""){
		        			locId.css({"border": "1px solid rgb(175, 44, 44)",
	 							   "background-color" : "rgb(255, 247, 247)"});
			        		$(".errormessagediv").show();
			        		$(".validateTips").text("Field Required - Leave Code")
			        		setTimeout(function(){
			        			$(".errormessagediv").hide();
			        		},2000);
			        		flag = true;
			        		return false;
		        		}
		        		else{
		        		$(".allstudent tbody tr").each(function(){
		        			
		        			leavecode = $(this).find('.leavecode');
		        			leavename = $(this).find('.leavename');
		        			noofleaves = $(this).find('.noofleaves')
		        			accuL = $(this).find('.laccu');
		        			carryf = $(this).find('.carryf');
		        			
		        			if(leavecode.val() == ""){
		        				leavecode.css({"border": "1px solid rgb(175, 44, 44)",
		        							   "background-color" : "rgb(255, 247, 247)"});
		        				$(".errormessagediv1").show();
		        				$(".validateTips1").text("Field Required - Leave Code")
		        				setTimeout(function(){
		        					$(".errormessagediv1").hide();
		        				},2000);
		        				flag = true;
		        				return false;
		        			}else if(leavename.val() == ""){
		        				leavename.css({"border": "1px solid rgb(175, 44, 44)",
     							   "background-color" : "rgb(255, 247, 247)"});
		        					$(".errormessagediv1").show();
		        					$(".validateTips1").text("Field Required - Leave Name")
		        					setTimeout(function(){
		        						$(".errormessagediv1").hide();
		        					},2000);
		        					flag = true;
		        					return false;
		        			}
		        			else if(noofleaves.val() == ""){
		        				noofleaves.css({"border": "1px solid rgb(175, 44, 44)",
	     										"background-color" : "rgb(255, 247, 247)"});
			        			$(".errormessagediv1").show();
			        			$(".validateTips1").text("Field Required - No.Of Leaves")
			        			setTimeout(function(){
			        				$(".errormessagediv1").hide();
			        			},2000);
			        			flag = true;
			        			return false;
		        			}else if(!accuL.is(':checked')){
		        				leavecode.css({"border": "1px solid rgb(175, 44, 44)",
     							   "background-color" : "rgb(255, 247, 247)"});
		        				$(".errormessagediv1").show();
			        			$(".validateTips1").text("Field Required - Accumulation for"+" "+leavecode.val());
			        			setTimeout(function(){
			        				$(".errormessagediv1").hide();
			        			},2000);
			        			flag = true;
			        			return false;
		        			}else if(!carryf.is(':checked')){
		        				leavecode.css({"border": "1px solid rgb(175, 44, 44)",
	     							   "background-color" : "rgb(255, 247, 247)"});
			        				$(".errormessagediv1").show();
				        			$(".validateTips1").text("Field Required - CarryForward for"+" "+leavecode.val());
				        			setTimeout(function(){
				        				$(".errormessagediv1").hide();
				        			},2000);
				        			flag = true;
				        			return false;
			        		}else{
			        			leaveCodes.push(leavecode.val());
			        			leaveNames.push(leavename.val());
			        			noofLeaves.push(noofleaves.val());
			        			accumuLation.push(accuL.val());
			        			carryF.push(carryf.val());
			        		}
		        		});
		        		}
		        		if(!flag){
		        			saveLeaveTypeDetails(leaveCodes,leaveNames,noofLeaves,accumuLation,carryF);
		        			$(this).dialog('close');	
		        			clearfields();
		        		}
		         }
	 			} ,
	 			'Close' : function() {
	 				$(this).dialog('close');	
	 				clearfields();
	 				
	              }
		    }
	});			 
		
	$("#addleaves").click(function(){
		addleaves();
	})
	
});

function clearfields(){
	 $(".errormessagediv").hide();
     $('.leavecode').val("");
     $('.leavename').val("");
     $('.laccu').prop("checked",false);
     $('.carryf').prop("checked",false);
     $('.noofleaves').val("");
     $(".allstudent tbody").find("tr:gt(0)").remove();
}

function convertUpperCase(){
	$(".leavecode").keyup(function(){
		$(this).val($(this).val().toUpperCase());
	});
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
							"<td class='leaveno'><input type='text' name='noofleaves' class='noofleaves'  maxlength='4' size='4' onkeypress='return isNumberKey(event)'/></td>"+
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
	if(charCode > 31 && charCode!=40 && charCode!=41 && charCode!=91 && charCode!=93 && charCode!=45 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122))
		return false;
	return true;
}

function saveLeaveTypeDetails(leaveCodes,leaveNames,noofLeaves,accumuLation,carryF){
	
	datalist = {
			"leaveCodes" : leaveCodes.toString(),
			"leaveNames" : leaveNames.toString(),
			"noofLeaves" : noofLeaves.toString(),
			"accumuLation" : accumuLation.toString(),
			"carryF" : carryF.toString(),
			"accYear" : $("#academicyear").val(),
			"locId" : $("#locationname").val()
	}
	
	$.ajax({
			type : 'POST',
			url : "leavebank.html?method=addLeavesCategory",
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
				}else{
					$("#loader").hide();
					$(".errormessagediv").show();
					$(".success").text("Failed to add Leave Types")
					setTimeout(function(){
						$(".validateTips").hide();
					},2000);
				}
			}
	});
}


function getvaldetails(value) {

	var s1 = value.id;

	var snoid = s1;
	
	 
	$("#snoid").val(snoid);

}

function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

/*
function deleteLeave() {

	var snoList = new Array();
	//alert("snoList"+snoList);
	
	var selectArray = document.getElementsByName('selectBox');
	var x = confirm("Confirm delete");
	for ( var i = 0; i < selectArray.length; i++) {
		if (selectArray[i].checked == true) {

			snoList.push(selectArray[i].value);
		}
	}

	if (snoList.length < 1) {
		$('.error-box').css({
			'visibility' : 'visible'
		});
		$("#errordiv").text("please select atleast one record");

	} else if(x) {
		var leavelist = {
			"year" : snoList

		};
		callAjaxLeave("leavebank.html?method=editleavebank", leavelist);

		if (result.deletestatus == true) {
			statusdelete = "Leave data deleted successfully";
		} else {
			statusdelete = "Deletion failed";
		}

	}

	window.location.href = "GetLeaves.do?parameter=getLeave&deletekey="
			+ statusdelete;

}
function selectAll() {

	var selectall = document.getElementsByName("selectall");
	var selectArray = document.getElementsByName('selectBox');

	if (selectall[0].checked == true) {
		for ( var i = 0; i < selectArray.length; i++) {
			selectArray[i].checked = true;
		}
	} else {
		for ( var i = 0; i < selectArray.length; i++) {
			selectArray[i].checked = false;
		}
	}
}
var result;
function callAjaxLeave(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				result = $.parseJSON(data);
				jsonResult = result;

			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}

*/

function HideError() 
{

document.getElementById("academicyear").style.border = "1px solid #ccc";
document.getElementById("academicyear").style.backgroundColor = "#fff";

document.getElementById("paidleave").style.border = "1px solid #ccc";
document.getElementById("paidleave").style.backgroundColor = "#fff";

document.getElementById("sickleave").style.border = "1px solid #ccc";
document.getElementById("sickleave").style.backgroundColor = "#fff";

document.getElementById("casualleave").style.border = "1px solid #ccc";
document.getElementById("casualleave").style.backgroundColor = "#fff";



}

var result;
function callAjaxLeave(urlWithMethod, dataToBeSend) {
	var jsonResult = "";
	try {
		$.ajax({
			type : "GET",
			url : urlWithMethod,
			data : dataToBeSend,
			async : false,
			success : function(data) {
				result = $.parseJSON(data);
				jsonResult = result;
			
			

			}
		});
	} catch (e) {
		jsonResult = "";
	}
	return jsonResult;
}
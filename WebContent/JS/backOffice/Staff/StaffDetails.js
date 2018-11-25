$(document).ready(function() {
	$("#resetbtn").click(function(){
		$("#depName").val("All");
		$("#designameid").val("all");
		$("#status").val("Y");
		$("#searchvalue").val("");
		searchTeacherList();
	});
	
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='9'>NO Records Found</td></tr>");
	}
	setTimeout("removeMessage()" ,3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);


	$("#locationname").change(function(){
		searchTeacherList();
	});			

	$("#designameid").change(function(){
		searchTeacherList();
	});			

	$("#depName").change(function(){
		searchTeacherList();
	});			

	$("#status").change(function(){
		searchTeacherList();
	});			


	$("#search").click(function(){
		searchTeacherList();
	});

	$("#searchvalue").keypress(function(e){
		/*searchTeacherList();*/
		var key = e.which;
		if(key==13){
			searchTeacherList();
		}
	});		

	checkboxs();

	var status = "N";


	$("#status").change(function(){

		if(this.value=="Y"){
			$("#deleteTeacher").text("InActive"); 
			status ='N';
		}
		else{
			$("#deleteTeacher").text("Active"); 
			status ='Y';
		}

		searchTeacherList();
		$("#selectall").prop("checked",false);
	});


	var studentIdlist=[];
	$("#deleteTeacher").click(function(){
		var count =0; 
		studentIdlist=[];
		$(".select:checked").each(function(){
			var list=$(this).attr("id");
			studentIdlist.push(list);
			count ++;
		});	
		/*var teacherId=$('input[name="selectBox"]:checked').val();*/
		if(count == 0){
			$('.errormessagediv').show();
			$('.validateTips').text("Select Record to"+"  "+ $("#deleteTeacher").text());
			$('.errormessagediv').delay(3000).slideUp();
		}
		else{
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are You Sure To "+$("#deleteTeacher").text()+"?</p>");
			$("#dialog").append('<p id="warningreason" class="warningfont" style="color:red;">*Warning&nbsp;:&nbsp;If you Inactivate this Staff, you won\'t be able to view details related to this Staff.</p>');
			$("#dialog").append('<label>Reason:</label>');
			$("#dialog").append('<input type="text" name="staffreason" style="width: 100%;" id="staffreason" onclick="HideError(this)"/>');

		}



	});
	$("#dialog").dialog({
		autoOpen: false,
		resizable: false,
		modal: true,					    
		title:'Staff Details',
		buttons : {
			"Yes" : function() {

				var inactivereason=$("#staffreason").val().trim();


				if($("#deleteTeacher").text()=="Active" && inactivereason.length == 0)
				{
					showError("#staffreason","Field Required -Reason");
					return false;

				}
				else if($("#deleteTeacher").text()=="InActive" && inactivereason.length == 0){

					showError("#staffreason","Field Required -Reason");
					return false;
				}

				else{
					deleteStaffDetails(status,studentIdlist);
					$(this).dialog("close");
				}
			},


			"No" : function() {
				$("#selectall").prop("checked",false);
				$(this).dialog("close");
			}
		}
	});


	$('#editTeacher').click(function() {
		var cnt = 0;
		$('.select:checked').map(function() {
			getData = $(this).attr("id");
			cnt++;
		});

		if (cnt == 0 || cnt > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
		}
		else {
			var name= getData;
			window.location.href ="teacherregistration.html?method=getTeacherDetails&teachercode="+name
			+"&historylocId="+$("#locationname").val()+"&historydepName="+$("#depName").val()+"&historydesigId="+$("#designameid").val()
			+"&historysearchvalue="+$("#searchvalue").val()+"&historystatus="+$("#status").val();
		}
	});
	$('#excelDownload').click(function() {
		window.location.href = "teacherregistration.html?method=downloadStaffDetailsXLS&locationId="+$("#locationname").val()+
		"&depName="+$("#depName").val()+"&designameid="+$("#designameid").val()+"&status="+$("#status").val()+"&locationname="+$("#locationname option:selected").text()+"&Deptname="+$("#depName option:selected").text()
		+"&designame="+$("#designameid option:selected").text()+"&statusname="+$("#status option:selected").text();
	});

	$("#pdfDownload").click(function(){
		window.location.href = "teacherregistration.html?method=downloadStaffDetailsPDF&locationId="+$("#locationname").val()+
		"&depName="+$("#depName").val()+"&designameid="+$("#designameid").val()+"&status="+$("#status").val()+"&locationname="+$("#locationname option:selected").text()+"&Deptname="+$("#depName option:selected").text()
		+"&designame="+$("#designameid option:selected").text()+"&statusname="+$("#status option:selected").text();

	});

	if($("#historylocId").val()!="" && $("#historydepName").val()!="" && $("#historydesigId").val()!=""
		&& $("#historysearchvalue").val()!="" && $("#historystatus").val()!=""){

		$("#locationname").val($("#historylocId").val());
		$("#depName").val($("#historydepName").val());
		$("#designameid").val($("#historydesigId").val());
		$("#searchvalue").val($("#historysearchvalue").val());
		$("#status").val($("#historystatus").val());
		if($("#status").val()=="Y"){
			status ='N';
			$("#deleteTeacher").text("InActive"); 
		}
		else{
			status ='Y';
			$("#deleteTeacher").text("Active"); 
		}

	}		

	if($("#currentstatus").val()!="" && $("#currentstatus").val()!=undefined){

		if($("#currentstatus").val()=="Y"){ 
			$("#status").val("N");	
			status ='N';
			$("#deleteTeacher").text("Active");
		}
		else{
			$("#status").val("Y");
			status ='Y';
			$("#deleteTeacher").text("InActive");
		}
	}
	searchTeacherList();
});


function salaryDeatails(){

	window.location.href = "teacherregistration.html?method=getTeacherSalary";

}

function searchTeacherList(){
	var locationId =$("#locationname").val();
	var depName =$("#depName").val();
	var designameid =$("#designameid").val();
	var status=$("#status").val();
	var searchname=$("#searchvalue").val().trim(); 

	$.ajax({
		type : 'POST',
		url :'teacherregistration.html?method=searchStaffDetails',
		data : {
			"locationId" :locationId,
			"depName" : depName,
			"designameid":designameid,
			"status":status,
			"searchname":searchname,
		},
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();

			var len=result.allTeacherDetailsList.length;
			var i=0;
			if(len>0){
				$("#iconsimg").show();
				for(i=0;i<len;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' class='select'  id='"+result.allTeacherDetailsList[i].teacherId+"'/></td>" 
							+"<td> "+result.allTeacherDetailsList[i].registartionNo+" </td>"
							/*	+"<td> "+result.allTeacherDetailsList[i].abbreviative_Id+"</td>"*/
							+"<td> "+result.allTeacherDetailsList[i].teacherName+" </td>"
							+"<td> "+result.allTeacherDetailsList[i].mobileNo+" </td>"
							+"<td> "+result.allTeacherDetailsList[i].qualification+" </td>"
							+"<td> "+result.allTeacherDetailsList[i].designation+" </td>"
							+"<td> "+result.allTeacherDetailsList[i].email+" </td>"
							+"<td> "+result.allTeacherDetailsList[i].remark+" </td>"
							+"</tr>");
				}
			}
			else{
				$("#iconsimg").hide();
				$('#allstudent tbody').append("<tr><td colspan='8'>No Records Found</td></tr>");
			}
			$("#loder").hide();
			checkboxs();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+len);


		}
	});
}

function deleteStaffDetails(status,studentIdlist){

	dataList={
			"status":status,
			"reason":$("#staffreason").val(),
			"teachercode":studentIdlist.toString(),
			'button':$("#deleteTeacher").text(),
	},

	$.ajax({
		type : 'POST',
		url : "teacherregistration.html?method=deleteStaffDetails",
		data : dataList,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);

			$('.errormessagediv').hide();

			if (result.status == true) {
				$("#loder").hide();
				$(".errormessagediv").hide();
				$(".successmessagediv").show();
				$(".validateTips").text($("#deleteTeacher").text()+"  "+"Record Progressing...");

				$("#selectall").prop("checked",false);
				studentIdlist=[];

				setTimeout(function(){
					window.location.href="menuslist.html?method=staffList&currentstatus="+$("#status").val();
				},3000); 
			}else if (result.status == false) {
				$("#loder").hide();
				$(".successmessagediv").hide();
				$(".errormessagediv").show();

				$(".validateTips").text("Failed to "+$("#deleteTeacher").text()+"  "+" the Record ");
				$("#selectall").prop("checked",false);
				studentIdlist=[];

				setTimeout(function(){
					window.location.href="menuslist.html?method=staffList&currentstatus="+$("#status").val();
				},3000); 
			}
			else {
				$("#loder").hide();
				$(".successmessagediv").show();
				$(".validateTips").text(result.status);
				$("#selectall").prop("checked",false);
				studentIdlist=[];

				setTimeout(function(){
					window.location.href="menuslist.html?method=staffList&currentstatus="+$("#status").val();
				},3000); 

			}


		}

	});  



}


function removeMessage() {
	$(".successmessagediv").hide();
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

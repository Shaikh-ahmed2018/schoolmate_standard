function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
	document.getElementById("myForm").submit();   
}

$(document).ready(function() {
	$(".addSchool").click(function(){
		$("#operation").val("Add");
	});
	$("#save").click(function(){

		event.preventDefault();
		var locname = $("#schoolId").val();
		var website = $("#website").val();
		var emailId = $("#emailId").val();
		var schoollogo = $("#schoollogo").val();
		var location_code = $("#updatelocationid").val().trim();
		
		var address = $("#address").val();
		
		var address2 = $("#address2").val();
		var countryId = $("#countryId").val();
		var stateId = $("#stateId").val();
		var cityId = $("#cityId").val();
		var pincode = $("#pincode").val();
		
		var pincodevalid=false;
		
		var cperson = $("#cpeson").val();
		var cmobile = $("#cmobileno").val();
		var clandline = $("#clandline").val();
		var cemailid = $("#cemailId").val();
		var schlaffno = $("#affiliation").val();
		
		 var filter = /^[0-9]{3,5}[-][0-9]{5,8}$/;
		 var phone = /^([6-9][0-9]{9})$/;
		 
		if (locname.trim() == "" || locname == null) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter School Name");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		} 
		else if(locname.length <3){
			$('.errormessagediv').show();
			$('.validateTips').text("School Name should Contain Atleast 3 Characters");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(locname.length >50 ){
			$('.errormessagediv').show();
			$('.validateTips').text("School Name Too Long!!!");
			document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 2000);
			return false;
		}
		
		else if (validateLocNameUpdate() == 10) {

			$('.errormessagediv').show();
			$('.validateTips').text("This School Already Exist !! Make it Active");
			return false;
		}
		else if (validateLocNameUpdate() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("School Name already Exist");
			return false;
		}
		else if (emailId.trim() == "" || emailId == null || emailId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Email Id");
			document.getElementById("emailId").style.border = "1px solid #AF2C2C";
			document.getElementById("emailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (!emailId.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/))
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Enter Valid Email");
			document.getElementById("emailId").style.border = "1px solid #AF2C2C";
			document.getElementById("emailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else if (address.trim() == "" || address == null || address==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Address1.");
			document.getElementById("address").style.border = "1px solid #AF2C2C";
			document.getElementById("address").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (countryId == "" || countryId == null || countryId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Country");
			document.getElementById("countryId").style.border = "1px solid #AF2C2C";
			document.getElementById("countryId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (stateId == "" || stateId == null || stateId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select State");
			document.getElementById("stateId").style.border = "1px solid #AF2C2C";
			document.getElementById("stateId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (cityId == "" || cityId == null || cityId==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select City/District");
			document.getElementById("cityId").style.border = "1px solid #AF2C2C";
			document.getElementById("cityId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		} 
		else if (pincode.trim()== "" || pincode == null || pincode==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Pin Code.");
			document.getElementById("pincode").style.border = "1px solid #AF2C2C";
			document.getElementById("pincode").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (pincodevalid) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid Pin Code.");
			document.getElementById("pincode").style.border = "1px solid #AF2C2C";
			document.getElementById("pincode").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (cperson.trim()== "" || pincode==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Pin Code.");
			document.getElementById("cpeson").style.border = "1px solid #AF2C2C";
			document.getElementById("cpeson").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (cemailid.trim() == "" && !cemailid.match(/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/))
		{
			$('.errormessagediv').show();
			$('.validateTips').text("Enter valid email");
			document.getElementById("cemailId").style.border = "1px solid #AF2C2C";
			document.getElementById("cemailId").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else if (cmobile.trim()== "" || cmobile==undefined) {
			$(".errormessagediv").show();
			$(".validateTips").text("Field required - Contact person mobile no.");
			document.getElementById("cmobileno").style.border = "1px solid #AF2C2C";
			document.getElementById("cmobileno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (cmobile.trim() != "" && !phone.test(cmobile)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid mobile no.");
			document.getElementById("cmobileno").style.border = "1px solid #AF2C2C";
			document.getElementById("cmobileno").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if (clandline.trim() != "" && !filter.test(clandline)){
			$(".errormessagediv").show();
			$(".validateTips").text("Enter valid landline no.");
			document.getElementById("clandline").style.border = "1px solid #AF2C2C";
			document.getElementById("clandline").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if (schlaffno.trim() == "" || schlaffno == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field required - School Affiliation No.");
			document.getElementById("affiliation").style.border = "1px solid #AF2C2C";
			document.getElementById("affiliation").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if ((schoollogo.trim() == "" || schoollogo == null || schoollogo==undefined) && ($("#hiddenschoollogoId").val()=="" || $("#hiddenschoollogoId").val()==null)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Upload school logo");
			document.getElementById("schoollogo").style.border = "1px solid #AF2C2C";
			document.getElementById("schoollogo").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else {
			var formdata;
				formdata = new FormData();
			  file1=$("#schoollogo")[0].files[0];
			  /*file2=$("#boardlogo")[0].files[0];*/
			  if(file1 !=undefined ){
				  formdata.append("schoollogo",file1);
				 // formdata.append("schoollogo",$("#hiddenschoollogoId").val().substring($("#hiddenschoollogoId").val().lastIndexOf("/")+1,$("#hiddenschoollogoId").val().length));
			  }

			  /*if(file2!=undefined ){
				  formdata.append("boardlogo",file2);
				  //formdata.append("boardlogo",$("#hiddenboardlogoId").val().substring($("#hiddenboardlogoId").val().lastIndexOf("/")+1,$("#hiddenboardlogoId").val().length));  
			  }*/
			
			  
			formdata.append("locationname",locname);
			/*formdata.append("affilno",affilno);
			formdata.append("board",board);*/
			formdata.append("address",address); 
			/*formdata.append("schoolcode",schoolcode);*/
			formdata.append("website",website); 
			formdata.append("emailId",emailId); 
			/*formdata.append("contactno",contactno);*/ 
			formdata.append("update_loc",location_code);
			
			formdata.append("operation",$("#operation").val());
			formdata.append("hiddenschoollogoId",$("#hiddenschoollogoId").val());
			/*formdata.append("hiddenboardlogoId",$("#hiddenboardlogoId").val());*/
			
			/*formdata.append("landlineno",landlineno);
			 formdata.append("hiddenaddId",$("#hiddenaddId").val());*/
			formdata.append("address2",address2);
			formdata.append("countryId",countryId);
			formdata.append("stateId",stateId);
			formdata.append("cityId",cityId);
			formdata.append("pincode",pincode);
			
			formdata.append("cperson",cperson);
			formdata.append("cmobileno",cmobile);
			formdata.append("saffiliationno",schlaffno);
			formdata.append("cemailId",cemailid);
			formdata.append("clandline",clandline);
			
			 $.ajax({
			        type: "POST",
			        url: "locationDetails.html?method=insertSchool",
			        data: formdata,
			        cache:false,
					contentType: false,
					processData: false,
					beforeSend: function(){
						$("#loder").show();
					},
			        success : function(data) {
						var result = $.parseJSON(data);
						if(result.status == "fail"){
							$("#loder").hide();
							$('.errormessagediv').show();
							$(".successmessagediv").hide();
							$(".validateTips").text("Failed to save record...");
						}else{
							$("#loder").hide();
							$('.errormessagediv').hide();
							$(".successmessagediv").show();
							$(".successmessage").text(result.status);
						}
						setTimeout(function() {
							window.location.href="menuslist.html?method=schoolList";
						}, 3000);
					}
			    });
			 
			return false;
		}
	
		
	})
	
	$("#status").val($("#hiddenstatus").val());
		
	SchoolList();
	setTimeout("removeMessage()", 2000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 2000);
	
});				


function SchoolList(){
	
	$.ajax({
		type : 'POST',
		url : "locationDetails.html?method=SchoolList",
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.LocationList.length;
			
			if(len > 0){
				
				// $("#add").hide();
				 $("#add").show();
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr id='"+result.LocationList[i].location_id+"'>"
						+"<td> "+result.LocationList[i].locationname+" </td>"
						+"<td>"+"<span>Email Id:- <span class='tablecontents'>"+result.LocationList[i].emailId+"</span></span><br />"
						+"<span>Web Site:- <span class='tablecontents'>"+result.LocationList[i].website+"</span></span><br />"
						+"<span>Address:- <span class='tablecontents'>"+result.LocationList[i].address+"</span></span><br />"
						+"<td><img src='"+result.LocationList[i].schoollogo+"' width='40' height='40' /></td>"
						+"<td class='actiontd'>  </td>"
						+"</tr>");
				 }	
				if($("#editPermission").val()=="true"){
					$("td.actiontd").each(function(){
						$(this).append('<span data-toggle="modal" data-target="#myModal" class="btn btn-xs btn-primary margin-t-5 editSchooId" title="Edit" onclick="editPopUp(this)"><span class="glyphicon glyphicon-edit"></span> Edit</span>');
					});
				}
				if($("#isvalid").val() == "modify"){
					$('#allstudent tbody tr').attr('title', 'Click here to view/modify the school details');
				}
				
			}
				else{
					 $("#add").show();
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
			 
			$("#loder").hide();
		}
	});
}  


function validateLocNameUpdate() {
	
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	
	 if(splitedurl=="editLocation"){
		 DeptName_validate_update = 0;
	 }else{

			var DeptName_validate_update = 0;
			var locname = $('#schoolId').val();
			var locid = $('#updatelocationid').val();
			var deptObject = {
				"locname" : locname,
				"locid" : locid
			};

			$.ajax({

				type : "GET",
				url : "locationDetails.html?method=validateLocNameUpdate",
				data : deptObject,
				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					if (result.des_available=="inactive") {
						$('.errormessagediv').show();
						$('.validateTips').text("This Location Already Exist !! Make it Active");
						DeptName_validate_update = 10;
					}
					else if (result.des_available=="true") {
						$('.errormessagediv').show();
						$('.validateTips').text("Location Name Already Exists");
						DeptName_validate_update = 1;
					} 
					else {
						DeptName_validate_update = 0;
					}

				}

			});
	 }

	return DeptName_validate_update;
}

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function editPopUp(element){
	var varid = $(element).closest("tr").attr("id");
	$.ajax({
		type :"POST",
		url : "locationDetails.html?method=editSchool",
		data :{"locid":varid},
		async  :false,
		success :function(data){
			var result = $.parseJSON(data);
			
			alert(result.editlist[0].locationname);
			$("#updatelocationname").val(result.editlist[0].locationname);
			$("#updatelocationid").val(result.editlist[0].location_id);
			$("#schoolId").val(result.editlist[0].locationname);
			$("#address").val(result.editlist[0].address);
			$("#cpeson").val(result.editlist[0].cperson);
			$("#hiddencountry").val(result.editlist[0].countryId);
			$("#hiddenstate").val(result.editlist[0].stateId);
			$("#hiddencity").val(result.editlist[0].cityId);
			$("#cmobileno").val(result.editlist[0].cmobileno);
			$("#saffiliationno").val(result.editlist[0].affilno);
			$("#website").val(result.editlist[0].website);
			$("#emailId").val(result.editlist[0].emailId);
			$("#address2").val(result.editlist[0].address2);
			$("#pincode").val(result.editlist[0].pinCode);
			$("#cemailId").val(result.editlist[0].cemailId);
			$("#clandline").val(result.editlist[0].clandline);
			$("#operation").val("Edit");
			$("#hiddenschoollogoId").val(result.editlist[0].schoollogo);
			
		}
		
	});
	
	
}


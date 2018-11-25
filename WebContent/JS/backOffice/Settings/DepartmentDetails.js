$(document).ready(function(){
    
	
	 $("input:text").keypress(function(event) {
         if (event.keyCode == 13) {
             event.preventDefault();
             return false;
         }
     });
	
	$(".errormessagediv").hide();
	$(".successmessagediv").hide();
	
	$('#back1').click(function() {
		var completeurl=window.location.href;
		var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
		
		if(splitedurl=="editDepartment"){
			window.location.href = "menuslist.html?method=departmentDetails&status="+$("#hiddenstatus").val()+"&searchname="+$("#hiddensearchname").val();
		}else{
			window.location.href = "menuslist.html?method=departmentDetails";
		}
		
	});

	$('#saveid').click(function() {

		var completeurl=window.location.href;
		var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
		var depname = $("#departmentid").val().trim();
		var descname = $("#descriptionid").val().trim();

		var department_code = $("#updatedepartmentid").val();

		if (depname == "" || depname == null) {
			
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Department Name");
			document.getElementById("departmentid").style.border = "1px solid #AF2C2C";
			document.getElementById("departmentid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		} 
		else if(!depname.match(/^[a-zA-Z0-9-&.(\][)\s]*$/)) {

			$('.errormessagediv').show();
			$('.validateTips').text("Department Name should be characters");
			document.getElementById("departmentid").style.border = "1px solid #AF2C2C";
			document.getElementById("departmentid").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;

		}
		else if (validateDeptName() == 1) {

			$('.errormessagediv').show();
			$('.validateTips').text("Department Name already Exist");
			return false;
		}
	
		else {

			datalist = {
					"departmname" : depname,
					"description" : descname,
					"update_dept" : department_code
			},

			$.ajax({

				type : "POST",

				url : "departmentMenu.html?method=addDepartment",

				data : datalist,

				async : false,

				success : function(data) {

					var result = $.parseJSON(data);

					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".successmessage").text(result.status);
					
					setTimeout(function() {
						if(splitedurl=="editDepartment"){
							window.location.href = "menuslist.html?method=departmentDetails&status="+$("#hiddenstatus").val()+"&searchname="+$("#hiddensearchname").val();
						}else{
							window.location.href = "menuslist.html?method=departmentDetails";
						}

					}, 3000);
				}

			});
			return false;

		}

	});
	
});

function validateDeptName() {

	desname_validate = 0;
	var deptname = $('#departmentid').val().trim();
	var deptid = $('#updatedepartmentid').val().trim();
	
	if($("#updatedepartmentid").val()!="" && ($("#updatedepartmentname").val().toLowerCase()!=$("#departmentid").val().toLowerCase())){
	
		var deptname_object = {
				"deptname" : deptname,
				"deptid" : deptid
		};

		$.ajax({

			type : "GET",
			url : "departmentMenu.html?method=validatedepartmentname",
			data : deptname_object,
			async : false,

			success : function(data) {

				var result = $.parseJSON(data);
			
				 
				 if (result.des_available=="inactive")
				{
					$(".errormessagediv").show();
					$(".validateTips").text("This Department Type  Already Exist !! Make it Active");
					desname_validate = 1;
				}
				 else if (result.des_available=="true") {
					$(".errormessagediv").show();
					$(".validateTips").text("Department Already Exist");
					desname_validate = 1;

				} 
				else {
					desname_validate = 0;
				}
			}
		});
	}
	else if($("#updatedepartmentid").val().trim() == ""){

		var deptname_object = {
				"deptname" : deptname,
				"deptid" : deptid
		};

		$.ajax({

			type : "GET",
			url : "departmentMenu.html?method=validatedepartmentname",
			data : deptname_object,
			async : false,
			success : function(data) {

				var result = $.parseJSON(data);
				
				 if (result.des_available=="inactive")
					{
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("This Department Type  Already Exist !! Make it Active");
						desname_validate = 1;
					}
				
				 else if (result.des_available=="true") {
					$(".errormessagediv").show();
					$(".validateTips").text("Department Already Exist");
					desname_validate = 1;

				} 
				else {
					desname_validate = 0;
				}

			}

		});
	}
	else{
		desname_validate = 0;
	}

	return desname_validate;

}

/*function validateDeptNameUpdate() {
	
	var completeurl=window.location.href;
	var splitedurl=(((completeurl.split("="))[1]).split("&"))[0];
	var DeptName_validate_update=0;
	if(splitedurl=="editDepartment"){
		DeptName_validate_update = 0;
	}
	else{
		var deptname = $('#departmentid').val();
		var deptid = $('#updatedepartmentid').val();

		var deptObject = {
				"deptname" : deptname,
				"deptid" : deptid
		};

		$.ajax({

			type : "GET",
			url : "departmentMenu.html?method=validateDeptNameUpdate",
			data : deptObject,
			async : false,

			success : function(data) {

				var result = $.parseJSON(data);

				if (result.des_available==true) {
					$('.errormessagediv').show();
					$('.validateTips').text("Dept Name Already Exists");
					DeptName_validate_update = 1;

				} else {
					DeptName_validate_update = 0;
				}

			}

		});
	}
	return DeptName_validate_update;
}*/

function HideError() 
{

	document.getElementById("departmentid").style.border = "1px solid #ccc";
	document.getElementById("departmentid").style.backgroundColor = "#fff";

}
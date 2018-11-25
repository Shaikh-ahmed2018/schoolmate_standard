$(document).ready(function(){
	
	if($("#historylocId").val()!=""){
		$("#locationname").val($("#historylocId").val());
		if($("#historyacademicId").val()!=""){
			$("#accyear").val($("#historyacademicId").val());
		}
		$("#searchvalue").val($("#historysearchvalue").val());
		$("#department").val($("#historydepartId").val());
		$("#designation").val($("#historydesigId").val());
	}else{
		$("#accyear").val($("#hacademicyaer").val());
	}
	
	
	$(".selectall").change(function(){
		$(".staffid").prop("checked",$(this).prop("checked"));
	});
	
	getDesignation();
	getDeaprtment();
	
	checkboxsselect();
	
	getIdCardStreamList();
	$("#locationname").change(function(){
		$(".selectall").prop("checked",false);
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#designation").change(function(){
		$(".selectall").prop("checked",false);
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#accyear").change(function(){
		$(".selectall").prop("checked",false);
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});
	$("#department").change(function(){
		$(".selectall").prop("checked",false);
		getSearchIdCardStreamList();
		$("#searchvalue").val("");
	});

	$("#search").click(function(){
		$(".selectall").prop("checked",false);
		searchList();
	});	
	
	$("#searchvalue").keypress(function(e){
		var key = e.which;
		if(key==13){
			$(".selectall").prop("checked",false);
			searchList();
		}
		
		
	});
	
	$("#resetbtn").on("click", function (e) {
		$("#locationname,#department,#designation").val("all");
		$("#accyear").val($("#hacademicyaer").val());
		$("#searchvalue").val("");
		searchList();
	});
	
	

	if($("#allstudent tbody td").length==0){
		$(".pagebanner").hide();
		$(".pagination").hide();
	}
	

	//searchList();
	
});

function getDeaprtment(){
	$.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDepartment',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);

			for ( var j = 0; j < data.DEPARTMENTLIST.length; j++) {
				$("#department")
						.append(
								'<option value="'
										+ data.DEPARTMENTLIST[j].depId
										+ '">'
										+ data.DEPARTMENTLIST[j].depName
										+ '</option>');
			}

		}

	});

}

function getDesignation(){
	$
	.ajax({
		type : 'POST',
		url : 'teacherregistration.html?method=getDesignation',
		async : false,
		success : function(response) {
			var data = $.parseJSON(response);
			$("#designation").empty();
			$("#designation").append("<option value=''>ALL</option>")
			for ( var j = 0; j < data.DESIGNATIONLIST.length; j++) {

				$("#designation").append(
								'<option value="'
										+ data.DESIGNATIONLIST[j].desgid
										+ '">'
										+ data.DESIGNATIONLIST[j].desgname
										+ '</option>');
			}

		}

	});
	
}

function checkboxsselect(){
	$(".selectall").change(function(){
		$(".staffid").prop('checked', $(this).prop("checked"));
	});
	
	$(".staffid").change(function(){
        if($(".staffid").length==$(".staffid:checked").length){
	         $(".selectall").prop("checked",true);
         }
       else{
	           $(".selectall").prop("checked",false);
           }
	});
}

function getIdCardStreamList(){
	var academicYear=$("#accyear").val();
	var locationId=$("#locationname").val();
	
	var dataList={"academicYear":academicYear,
					"locationId":locationId,
					"departmentId":"all",
					"designationId":"all",
	};
	$.ajax({
		type : 'POST',
		url : "IDCardMenu.html?method=StaffSingleIDCardDesignNew",
		//url :"IDCardMenu.html?method=NewstaffIdCardDesignList",
		//url : "menuslist.html?method=StaffSingleIDCardDesign",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data); 
			$('#allstudent tbody').empty();
			
			if(result.streamList.length > 0){
			for ( var j = 0; j < result.streamList.length; j++) {
				$('#allstudent tbody').append("<tr>" +
						"<td><input type='checkbox' class='staffid' value='"+result.streamList[j].teacherID+" "+result.streamList[j].locationId+" "+result.streamList[j].departmentId+" "+result.streamList[j].academicYearCode+"' /></td>" +
						"<td>"+ result.streamList[j].staffRegId+ "</td>" +
						"<td>"+ result.streamList[j].teacherName+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].designationName+ "</td>" +
						"<td>"+ result.streamList[j].departmentName+ "</td>" +
						"<td>"+ result.streamList[j].status+ "</td>" +
						"</tr>");
			}
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
			checkboxsselect();
			$(".pagebanner").hide();
			$(".pagination").hide();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			printpreview();
		}
	});
	
}
function getSearchIdCardStreamList(){
	
	
	var academicYear=$("#accyear").val();
	var locationId=$("#locationname").val();
	var designation=$("#designation").val();
	var department=$("#department").val();

	if(academicYear==""){
		academicYear="all";
	}

	if(locationId==""){
		locationId="all";
	}

	if(designation==""){
		designation="all";
	}

	if(department==""){
		department="all";
	}
	
	var dataList={
			"academicYear":academicYear,
			"locationId":locationId,
			"designationId":designation,
			"departmentId": department,
	};

	$.ajax({
		type : 'POST',
		url : "IDCardMenu.html?method=StaffSingleIDCardDesignNew", 
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data); 
			$('#allstudent tbody').empty();
			
			if(result.streamList.length > 0){
				for ( var j = 0; j < result.streamList.length; j++) {
				$('#allstudent tbody').append("<tr>" +
						"<td><input type='checkbox' class='staffid' value='"+result.streamList[j].teacherID+" "+result.streamList[j].locationId+" "+result.streamList[j].departmentId+" "+result.streamList[j].academicYearCode+"' /></td>" +
						"<td>"+result.streamList[j].staffRegId+ "</td>" +
						"<td>"+result.streamList[j].teacherName+ "</td>" +
						"<td>"+result.streamList[j].academicYear+ "</td>" +
						"<td>"+result.streamList[j].locationName+ "</td>" +
						"<td>"+result.streamList[j].designationName+ "</td>" +
						"<td>"+result.streamList[j].departmentName+ "</td>" +
						"<td>"+result.streamList[j].status+ "</td>" +
						"</tr>");
			}
		}
		else{
			$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
		}
			checkboxsselect();
			$(".pagebanner").hide();
			$(".pagination").hide();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			printpreview();
			
		}
	});
	
}


function printpreview(){
	$("#editStudent").click(function(){
		var staffid=[];
		var locationId=[];
		var designationId=[]; 
		var academicyear=[];
		var cnt = 0;
		$("#allstudent tbody tr").find(".staffid:checked").each(function(){
			staffid.push($(this).val().split(" ")[0]);
			locationId.push($(this).val().split(" ")[1]);
			designationId.push($(this).val().split(" ")[2]);
			academicyear.push($(this).val().split(" ")[3]);
			cnt++;
		});
		
		
		if (cnt == 0) {
			
				$(".errormessagediv").show();
				$(".validateTips").text("Please select atleast one record !");
				$(".errormessagediv").delay(2000).fadeOut();
				
				return false;
			} else{
				
			
		window.location.href="IDCardMenu.html?method=PrintPreviewStaffMultipleIDCard&staffid="+staffid+"&locationId="+locationId+"&designationId="+designationId+"&academicyear="+academicyear+
		"&searchvalue="+$("#searchvalue").val()+"&locId="+$("#locationname").val()+"&departId="+$("#department").val()+"&desigId="+$("#designation").val();
			}
			});
	
}

function searchList(){
	$("#allstudent tbody").empty();
	
	var academicYear=$("#accyear").val();
	var locationId=$("#locationname").val();
	var designation=$("#designation").val();
	var department=$("#department").val();
	var searchname = $("#searchvalue").val().trim();

	if(academicYear==""){
		academicYear="all";
	}

	if(locationId==""){
		locationId="all";
	}

	if(designation==""){
		designation="all";
	}

	if(department==""){
		department="all";
	}
	if(searchname==""){
		searchname="all";
	}

	
    datalist = {
			"academicYear" :academicYear,
			"locationId":locationId,
			 "designation":designation,
		   	"department": department,
			"searchname":searchname,
		}, $.ajax({
			type : 'POST',
			url : "IDCardMenu.html?method=getStaffSearchByList",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response); 
				  if(result.SearchList.length > 0){	
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr class='"+result.SearchList[i].teacherID+" "+result.SearchList[i].academicYear+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"
							+"<td><input type='checkbox' class='staffid' value='"+result.streamList[j].teacherID+" "+result.streamList[j].locationId+" "+result.streamList[j].departmentId+" "+result.streamList[j].academicYearCode+"' /></td>" 
							+"<td>"+result.SearchList[i].staffRegId+ "</td>" 
							+"<td>"+result.SearchList[i].teacherName+ "</td>" 
							+"<td>"+result.SearchList[i].academicYear+ "</td>" 
							+"<td>"+result.SearchList[i].locationName+ "</td>" 
							+"<td>"+result.SearchList[i].designationName+ "</td>" 
							+"<td>"+result.SearchList[i].departmentName+ "</td>" 
							+"<td>"+result.SearchList[i].status+ "</td>" 
							+"</tr>");
					}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
			}
				  checkboxsselect();
				  $(".pagebanner").hide();
					$(".pagination").hide();
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					printpreview();
	
					}
			});
}


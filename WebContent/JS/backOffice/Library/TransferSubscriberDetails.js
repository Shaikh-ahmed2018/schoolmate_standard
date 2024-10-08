
$(document).ready(function(){
	$("#Acyearid").val($("#hidenaccyear").val());
	
	getLibraryLocation($("#locationname").val(),$("#liblocation"));
	
	
	 if($("input[name='requested_by']").val()=="Student" && 
			 ($("#historylocId").val()=="" || $("#historylocId").val()==null )){
		 getTranferStudentListDetails();
		 
		 $("#Acyearid").change(function(){
			 getTranferStudentListDetails();
			 
			 getLibraryLocation($(this).val(),$("#liblocation"));
			 $("#liblocation").change(function(){
			  	   getTranferStudentListDetails();
		   }); 
	   });
		 
		 $("#locationname").change(function(){
			 getTranferStudentListDetails();
			
			 $("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
			 if($("#locationname").val()=="all" || $("#locationname").val()==""){
				 $("#classname").html("");
				 $("#classname").append("<option value='all'>ALL</option>"); 
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
			 }
			 else{
				 var classname=$("#classname").val();
				 getClassList();
				 getSectionList(classname); 
				 $("#sectionid").html("");
				 $("#sectionid").append("<option value='all'>ALL</option>");
			 }
			 
			 getLibraryLocation($(this).val(),$("#liblocation"));
			 $("#liblocation").change(function(){
			  	   getTranferStudentListDetails();
		   }); 
	   });
		 $("#classname").change(function(){
				var locationid=$("#locationname").val();
				var accyear=$("#Acyearid").val();
				var classname=$("#classname").val();
				
				if($("#classname").val()=="all" || $("#classname").val()==""){
					$("#sectionid").html("");
					 $("#sectionid").append("<option value='all'>ALL</option>");
				}
				else{
					getSectionList(classname);
				}
				TransferSubscriberbySearch();
			});
			
			$("#sectionid").change(function(){
				var locationid=$("#locationname").val();
				var accyear=$("#Acyearid").val();
				var classname=$("#classname").val();
				var sectionid=$("#sectionid").val();
				
				TransferSubscriberbySearch();
			});
		 
	 }
	    $("#liblocation").change(function(){
	  	   getTranferStudentListDetails();
        }); 
	    
	    getClassList();
	 
	 $("#resetbtn").on("click", function () {
	       	$("#locationname").val("all");
			$("#Acyearid").val($("#hidenaccyear").val());
			$("#liblocation").val("all");
			$("#classname").val("all");
			$("#sectionid").val("all");
			$("#department").val("all");
			$("#designation").val("all");
			$("#searchValue").val("");
		    $("input[name='requested_by'][value='Student']").prop('checked',true);
		    $(".staffstand").hide();
			$(".otherstable").hide();
		    $("#allstaff").hide();
			$(".stand").show();
			getTranferStudentListDetails();
			$(".allstudenttable").show();
		});
	 
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		       
		        TransferSubscriberbySearch();
 		 }	
 	 });
	 
	 $("#search").click(function(){
		 var searchname = $("#searchValue").val().trim();
		  TransferSubscriberbySearch();
 	 });
	 
	 getdepartmentlist();
		getDesignationList();
	 
	 $("input[name='requested_by']").change(function(){
		 $(".selectall").prop("checked",false);
		if($(this).val()=="Student"){
			$("#selectall").prop("checked",false);
			$(".stand").show();
			$(".allstudenttable").show();
			$(".staffstand").hide();
			$(".stafftable").hide();
			$(".otherstable").hide();
			getTranferStudentListDetails();
			
			studenthistory();
		}
		 
	else if($(this).val()=="staff"){
		 $(".selectall").prop("checked",false);
			$(".staffstand").show();
			$(".stafftable").show();
			$("#allstaff").show();
			$(".stand").hide();
			$(".allstudenttable").hide();
			$(".otherstable").hide();
			getTransferStaffListDetails();
			
			staffhistory();
		}
	else if($(this).val()=="others"){
		 $(".selectall").prop("checked",false);
			$(".otherstable").show();
			$(".stafftable").hide();
			$(".staffstand").hide();
			$(".stand").hide();
			$(".allstudenttable").hide();
			getTransferOthersListDetails();
			
			othershistory();
		}
	});
	  /*..........................GoTo Click..........................*/
	subscriberId=[];
	$("#transfer").click(function(){
		getLibraryLocationTransfer($("#locationname").val(),$("#libloc"));
		count =0;
		//subscriberId=$("input[name='select']:checked").val();
		$(".select:checked").each(function(){
			subscriberId.push($(this).val());
			count ++;
		});
		
		if($("#locationname").val()=="all" ||$("#locationname").val()=="" || $("#locationname").val()==null || 
   				$("#locationname").val()==undefined)
    	   {
   			$(".errormessagediv").show();
      			$(".validateTips").text("Select any record.");
      			showError("#locationname");
      			setTimeout(function() {
      				$('.errormessagediv').fadeOut();
      			  }, 3000);
      			return false;
    	   }
		
		
		else if(count == 0){
			$(".errormessagediv").show();
			$(".validateTips").text("Select any record.");
			$(".errormessagediv").fadeOut(3000);
		}
		 
		 else{
			 $("#TransferDialog").dialog("open");
			 
		 }
	});
	
	$("#TransferDialog").dialog({
	    autoOpen  : false,
	    resizable : false,
	    maxWidth  :	600,
        maxHeight : 400,
        width     : 373.84,
        height    : 230.84,
	    modal     : true,
	    title     : "Transfer Subscribers",
	    buttons   : {
	    	'Transfer'  : function() {
	    		
	    		var Locname=$("#libloc").val();
	    		if(Locname == "" || Locname ==null){
	    			$(".errormessagediv").show();
	    			$(".validateTips").text("Field Required-Location.");
	    			$("#libloc").focus();
	    			document.getElementById("libloc").style.border = "1px solid #FF0000";
	    			document.getElementById("libloc").style.backgroundColor = "#FFF7F7";
	    			setTimeout(function() {
	    				$('.errormessagediv').fadeOut();
	    			}, 3000);
	    			return false;
   			 }
	    		else {
	    			$(".selectall").prop("checked",false);
					$(".select").prop('checked',false);
	    			TransferStudent(subscriberId);	
					
					$(this).dialog('close');
	    		}
	    		$(this).dialog('close');
	    	},
				'No' : function() {
					$(this).dialog('close');
					$(".errormessagediv").hide();
				}
	    	}
	    });

		$("#TransferDialog1").dialog({
			autoOpen  : false,
			maxWidth  :	400,
			maxHeight : 300,
			width     : 350,
			height    : 180,
			modal     : true,
			title     : "Transfer Subscribers",
			buttons   : {
				'Yes'  : function() {
						TransferStudent(subscriberId);	
						$(this).dialog('close');
					  },
				'No' : function() {
					$(this).dialog('close');
					$(".errormessagediv").hide();
				}
			}
		});
      
      $(".selectall").change(function(){
			$(".select").prop('checked', $(this).prop("checked"));
		});	
      
 
      
      if($("#historylocId").val()!="" || $("#historyAcyearid").val()!="")
  	{
  		$("input[name='requested_by'][value='"+$("#historysubType").val()+"']").prop('checked', true);
      
    	  if($("#historysubType").val()=="Student"){
    		   
    		   $(".stand").show();
	  			$(".allstudenttable").show();
	  			$(".staffstand").hide();
	  			$(".stafftable").hide();
	  			$(".otherstable").hide();
    		  
    		  $("#locationname").val($("#historylocId").val());
    		  $("#Acyearid").val($("#historyAcyearid").val());
    		  
    		  $("#liblocation").val($("#historyliblocId").val());
    		  $("#classname").val($("#historyclassId").val());
    		  
    		  getSectionList($("#classname").val());
    		  $("#sectionid").val($("#historysectionid").val());
    		  $("#searchValue").val($("#historysearchValue").val());
    		  
    		  TransferSubscriberbySearch();
    		  studenthistory();
    	  }else if($("#historysubType").val()=="staff"){
	    	    $(".staffstand").show();
	  			$(".stafftable").show();
	  			$("#allstaff").show();
	  			$(".stand").hide();
	  			$(".allstudenttable").hide();
	  			$(".otherstable").hide();
    		  
    		  $("#locationname").val($("#historylocId").val());
    		  $("#Acyearid").val($("#historyAcyearid").val());
    		  $("#liblocation").val($("#historyliblocId").val());
    		  
    		  $("#department").val($("#historydepId").val());
    		  $("#designation").val($("#historydesigId").val());
    		  $("#searchValue").val($("#historysearchValue").val());
    		  TransferSubscriberbyStaffSearch();
    		  staffhistory();
    	  }else if($("#historysubType").val()=="others"){
    		  
    	    $(".otherstable").show();
  			$(".stafftable").hide();
  			$(".staffstand").hide();
  			$(".stand").hide();
  			$(".allstudenttable").hide();
    		  
    		  $("#locationname").val($("#historylocId").val());
    		  $("#Acyearid").val($("#historyAcyearid").val());
    		  $("#liblocation").val($("#historyliblocId").val());
    		  $("#searchValue").val($("#historysearchValue").val());
    		  
    		  TransferSubscriberbyotherSearch();
    		  othershistory();
    	  }
  	}
      
});
function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classname').html("");
			$('#classname').append('<option value="all">' +"ALL"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#classname').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}
function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			$('#sectionid').append('<option value="">' + "ALL"	+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}
function getdepartmentlist(){
	$.ajax({
	type : 'POST',
	url : 'teacherregistration.html?method=getDepartment',
	async : false,
	success : function(response) {
		var data = $.parseJSON(response);
		$("#department").empty();
		$("#department")
		.append('<option value="all">ALL</option>');
		for (var j = 0; j < data.DEPARTMENTLIST.length; j++) {
			$("#department")
			.append(
					'<option value="'
					+ data.DEPARTMENTLIST[j].depId
					+ '">'
					+ data.DEPARTMENTLIST[j].depName
					+ '</option>');
		}
		var hiddendept=$("#hiddendept").val();
		$("#department option[value=" + hiddendept + "]").attr('selected', 'true');
	}
  });
}
function getDesignationList(department){
    $.ajax({
	type : 'POST',
	url : 'teacherregistration.html?method=getDesignation',
	async : false,
	success : function(response) {
		var data = $.parseJSON(response);
		$('#designation').empty();
		$("#designation")
		.append('<option value="all">ALL</option>');
		for (var j = 0; j < data.DESIGNATIONLIST.length;j++) {
			$('#designation')
			.append(
					'<option value="'
					+ data.DESIGNATIONLIST[j].desgid
					+ '">'
					+ data.DESIGNATIONLIST[j].desgname
					+ '</option>');
		}
		var hiddendes=$("#hiddendes").val();
		$("#designation option[value=" + hiddendes + "]").attr('selected', 'true');
	}
  });
} 
function getTranferStudentListDetails(){

	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var liblocation=$("#liblocation").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='requested_by']:checked").val();
	
	 $.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTranferStudentListDetails",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select,
			"liblocation":liblocation,
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].location+" "+"studentid1"+"'><input type='checkbox' name='select' class='select'   value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}

function getTrasferStudentListByClassName(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='requested_by']:checked").val();
	var liblocation=$("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTrasferStudentListByClassName",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select,
			"liblocation":liblocation
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select' class='select' id='select'  value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			pagination(100);
			selectChange();
		}
	});
} 

function getTrasferStudentListBySection(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var select=$("input[name='requested_by']:checked").val();
	var liblocation=$("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTrasferStudentListBySection",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"select":select,
			"liblocation":liblocation
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select' class='select' id='select'  value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
				}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			pagination(100);
			selectChange();
		}
	});
} 


function TransferSubscriberbySearch(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var liblocid=$("#liblocation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='requested_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=TransferSubscriberbySearch",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"liblocid":liblocid,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstaff tbody").empty();
					$("#others tbody").empty();
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select' class='select' id='select'  value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].studentName+"</td>"
						+"<td> "+result.studentData[i].studentAdmissionNo+"</td>"
						+"<td> "+result.studentData[i].rollNO+" </td>"
						+"<td> "+result.studentData[i].classid+" </td>"
						+"<td> "+result.studentData[i].division+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}

        /* -------Staff----*/
function getTransferStaffListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var select=$("input[name='requested_by']:checked").val();
	var libloc = $("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTransferStaffListDetails",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department":department,
			"designation":designation,
			"select":select,
			"libloc":libloc
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].location+" "+"studentid"+"'><input type='checkbox' name='select' class='select' value='"+result.studentData[i].subscriberId+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}
function getStaffdetailsByDepartment(){
	
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var libloc = $("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTransferStaffdetailsByDepartment",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation":designation,
			"libloc":libloc
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select' class='select' value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}
function getStaffdetailsByDesignation(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var libloc = $("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTransferStaffdetailsByDesignation",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"libloc":libloc
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();

			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select'  class='select'   value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].libLoc+" </td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
				
			}
			pagintion(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}


function  TransferSubscriberbyStaffSearch(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var department=$("#department").val();
	var designation=$("#designation").val();
	var liblocid=$("#liblocation").val();
	var searchname = $("#searchValue").val();
	var select=$("input[name='requested_by']:checked").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=TransferSubscriberbyStaffSearch",
		data : {
			"location" :locationid,
			"accyear" :accyear,
			"department" :department,
			"designation" :designation,
			"liblocid":liblocid,
			"searchname":searchname,
			"select":select
		},
		async : false,
		success : function(response) { 
			var result = $.parseJSON(response);
			$("#allstaff tbody").empty();
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#others tbody").empty();
				$("#allstaff tbody").append("<tr>"
						+"<td><input type='checkbox'  name='select'  class='select'   value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>"
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].staffName+"</td>"
						+"<td> "+result.studentData[i].staffDepartment+"</td>"
						+"<td> "+result.studentData[i].staffDesignation+" </td>"
						+"<td> "+result.studentData[i].libLoc+"</td>"
						+"<td> "+result.studentData[i].status+"</td>"
						+"</tr>");
				}	
			}
			else{
				$("#allstaff tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}




 /* -------------Staff search Details---------------
     ------Search Staff Details By AnyWhere------*/

/*-----Staff List By Accyear and Location------*/

  /* --------------------------------Others Details---------------------------*/
function getTransferOthersListDetails(){
	var locationid = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	var select=$("input[name='requested_by']:checked").val();
	var libloc = $("#liblocation").val();
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getTransferOthersListDetails",
		data : {
			
			"location" :locationid,
			"accyear" :accyear,
			"select":select,
			"libloc":libloc
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
				if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].location+" "+"studentid"+"'><input type='checkbox' name='select'  class='select'   value='"+result.studentData[i].subscriberId+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].libLoc+"</td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
				else{
					$("#others tbody").append("<tr><td ColSpan='8'>No Records Found</td></tr>");
				}
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentData.length);
				selectChange();
		}
	});
}   
/*--------------------------------Others Details search Any Where---------------------------*/


function TransferSubscriberbyotherSearch(){
	var locationid = $("#locationname").val();
	var liblocid=$("#liblocation").val();
	var select=$("input[name='requested_by']:checked").val();
	var searchname = $("#searchValue").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=TransferSubscriberbyotherSearch",
		data : {
			"location" :locationid,
			"liblocid":liblocid,
			"select":select,
			"searchname":searchname,
			"accyear" :accyear,
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#others tbody").empty();
				
			if(result.studentData.length > 0){
				for(var i=0;i<result.studentData.length;i++){
					$("#allstudent tbody").empty();
					$("#allstaff tbody").empty();
				$("#others tbody").append("<tr>"
						+"<td class='"+result.studentData[i].studentId+" "+result.studentData[i].academicYearId+" "+result.studentData[i].locationId+" "+"studentid"+"'><input type='checkbox' name='select' class='select'  value='"+result.studentData[i].subscriberId+"' class='"+result.studentData[i].location+"'/></td>" 
						+"<td> "+result.studentData[i].subssciberNo+" </td>"
						+"<td> "+result.studentData[i].othereName+"</td>"
						+"<td> "+result.studentData[i].othereContactNumber+"</td>"
						+"<td> "+result.studentData[i].othereMail_Id+" </td>"
						+"<td> "+result.studentData[i].othereAddress+" </td>"
						+"<td> "+result.studentData[i].libLoc+"</td>"
						+"<td> "+result.studentData[i].status+" </td>"
						+"</tr>");
				}	
			}
			else{
				$("#others tbody").append("<tr><td ColSpan='8'>Nothing found to display</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.studentData.length);
			selectChange();
		}
	});
}

function TransferStudent(subscriberId){
	var locationid = $("#libloc").val();
		 datalist = {
			"subscriberId" :subscriberId.toString(),
			"location" :locationid,
		 };
		
		 $.ajax({
				type : "POST",
				url : "LibraryMenu.html?method=TransferStudent",
				data : datalist,
				async : false,
				success : function(data){
					var result = $.parseJSON(data);
					
					if(result.trasub == "transferSuccuss"){
						$("#selectall").prop('checked',false);
						$(".select").prop('checked',false);
				    	 $(".successmessagediv").show();
							$(".successmessage").text("Subscriber Transfered Successfully...");
							setTimeout(function() {
								$('.successmessagediv').fadeOut();
								if($("input[name='requested_by']:checked").val()=="Student"){
									
									/*TransferSubscriberbySearch();*/
									 
							 window.location.href="LibraryMenu.html?method=TransferSubscriberDetails&historylocId="+$("#locationname").val()+
							 "&historyliblocId="+$("#liblocation").val()+"&historysubType="+$("input[name='requested_by']:checked").val()+
							 "&historyAcyearid="+$("#Acyearid").val()+"&historyclassId="+$("#classname").val()+"&historysectionid="+$("#sectionid").val()
							 +"&historysearchValue="+$("#searchValue").val()+"&historydepId="+$("#department").val()+"&historydesigId="+$("#designation").val();	
									
								}else if($("input[name='requested_by']:checked").val()=="staff"){
									
									/*TransferSubscriberbyStaffSearch();*/
									 window.location.href="LibraryMenu.html?method=TransferSubscriberDetails&historylocId="+$("#locationname").val()+
									 "&historyliblocId="+$("#liblocation").val()+"&historysubType="+$("input[name='requested_by']:checked").val()+
									 "&historyAcyearid="+$("#Acyearid").val()+"&historyclassId="+$("#classname").val()+"&historysectionid="+$("#sectionid").val()+"&historysearchValue="+$("#searchValue").val()
									 +"&historydepId="+$("#department").val()+"&historydesigId="+$("#designation").val();
									
								}else{
									$("#selectall").prop('checked',false);
									$(".select").prop('checked',false);
									
									/*TransferSubscriberbyotherSearch();*/
									 window.location.href="LibraryMenu.html?method=TransferSubscriberDetails&historylocId="+$("#locationname").val()+
									 "&historyliblocId="+$("#liblocation").val()+"&historysubType="+$("input[name='requested_by']:checked").val()+
									 "&historyAcyearid="+$("#Acyearid").val()+"&historyclassId="+$("#classname").val()+"&historysectionid="+$("#sectionid").val()+"&historysearchValue="+$("#searchValue").val()
									 +"&historydepId="+$("#department").val()+"&historydesigId="+$("#designation").val();
								}
							}, 3000);
				     }else{
				    	 $(".selectall").prop("checked",false);
							$(".select").prop('checked',false);
				     }
				}
			});
		 selectChange();
	}


function selectChange(){
	$(".selectall").change(function(){
	    $(".select").prop('checked', $(this).prop("checked"));
     });

$(".select").change(function(){
	if($(".select").length==$(".select:checked").length){
		$(".selectall").prop("checked",true);
	}
	else{
		$(".selectall").prop("checked",false);
	}
});
}


function getLibraryLocation(location,id){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=LibraryLocation",
		data:{"schoolLocation":location},
		async:false,
		success : function(response){
			var result = $.parseJSON(response);
			id.empty();
			id.append("<option value>ALL</option>");
			for(var i=0;i<result.librarylocationsDetails.length;i++){
			id.append("<option value='"+result.librarylocationsDetails[i].librarylocid+"'>"+result.librarylocationsDetails[i].libraryLocations+"</option>");	
			}
		}
		
	});
}


function getLibraryLocationTransfer(location,id){
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=LibraryLocation",
		data:{"schoolLocation":location},
		async:false,
		success : function(response){
			var result = $.parseJSON(response);
			id.empty();
			id.append("<option value>-----Select-----</option>");
			for(var i=0;i<result.librarylocationsDetails.length;i++){
			id.append("<option value='"+result.librarylocationsDetails[i].librarylocid+"'>"+result.librarylocationsDetails[i].libraryLocations+"</option>");	
			}
		}
		
	});
}

function studenthistory(){
	$("#Acyearid").change(function(){
		 getTranferStudentListDetails();
  });
	
	$("#locationname").change(function(){
		getLibraryLocation($(this).val(),$("#liblocation"));
		if($("#locationname").val()=="all" || $("#locationname").val()=="")
		 {
			 $("#classname").html("");
			 $("#classname").append("<option value='all'>ALL</option>"); 
			 $("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
		 }
		else{
			var classname=$("#classname").val();
			getClassList();
			getSectionList(classname);
			$("#sectionid").html("");
			 $("#sectionid").append("<option value='all'>ALL</option>");
		}
		getTranferStudentListDetails();
	});
	
	 $("#liblocation").change(function(){
	  	   getTranferStudentListDetails();
      });
	
	$("#classname").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		getSectionList(classname);
		TransferSubscriberbySearch();
	});
	
	$("#sectionid").change(function(){
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		TransferSubscriberbySearch();
	});
	 
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();

		        TransferSubscriberbySearch();
		 }	
	 });
	 
	 $("#search").click(function(){
		 var searchname = $("#searchValue").val().trim();
		        TransferSubscriberbySearch();
	 });
}

function staffhistory(){
	$("#Acyearid").change(function(){
		getTransferStaffListDetails();
	});
	$("#locationname").change(function(){
		getTransferStaffListDetails();
		
		});
	$("#liblocation").change(function(){   
		TransferSubscriberbyStaffSearch();
	   }); 
	$("#department").change(function(){
		TransferSubscriberbyStaffSearch();
	});
	
      $("#designation").change(function(){
    	  TransferSubscriberbyStaffSearch();
	});
	
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		        TransferSubscriberbyStaffSearch();
 		 }	
 	 });
	 
	 $("#search").click(function(e){
		 var searchname = $("#searchValue").val().trim();
		 TransferSubscriberbyStaffSearch();
 	 });
}

function othershistory(){
	$("#Acyearid").change(function(){
		getTransferOthersListDetails();
	});
	
	$("#locationname").change(function(){
		 getTransferOthersListDetails();	
	});
	 $("#liblocation").change(function(){
		   
		 getTransferOthersListDetails();
	   }); 
	 
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		    if(e.keyCode == 13){
		        e.preventDefault();
		       
		        TransferSubscriberbyotherSearch();
 		 }	
 	 });
	 
	 $("#search").click(function(e){
		 var searchname = $("#searchValue").val().trim();
		 TransferSubscriberbyotherSearch();
 	 });
}

function HideError(pointer){
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

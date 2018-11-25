$(document).ready(function() {
	getTableByFilteration($("#haccyear").val());
	$("#iconsimg").click(function(){
	if($("#location").val()=="all"){
		 $(".errormessagediv").show();
			$(".validateTips").text("Field Required School Name");
			document.getElementById("location").style.border = "1px solid #AF2C2C";
			document.getElementById("location").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				document.getElementById("location").style.border = "1px solid #ccc";
				document.getElementById("location").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false
	}
	});
	
	$("#departmentid").change(function(){
		getTableByFilteration($("#accyear").val());
	});
	$("#accyear").val($("#haccyear").val());
	getClassList();
	getTableByFilteration($("#accyear").val());
	$("#location").change(function(){
		getClassList();
		getTableByFilteration($("#accyear").val());
		$("#section").empty();
		 
		$("#section").append('<option value="all">' + "ALL"	+ '</option>');
	});
	$("#accyear").change(function(){
		getTableByFilteration($("#accyear").val());
		
	});
	$("#class").change(function(){
		getSection();
		getTableByFilteration($("#accyear").val());
	
	});
	$("#section").change(function(){
		getTableByFilteration($("#accyear").val());
	});
	
	
	$("#add").click(function(){
		window.location.href="teachmap.html?method=AddClassTeacherMapping";
	});
	
	
	 $("#editId").click(function(){
			if($("input[name='getempid']:checked").length != 1 ){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Select atleast one record");
				 return false;
			}
			else{
				
				var checked = $("input[name='getempid']:checked");
				
				var classid = checked.attr("class").split(" ")[0];
				var sectionid = checked.attr("class").split(" ")[1];
				var teacherid = checked.attr("class").split(" ")[2];
				var locationid=checked.attr("class").split(" ")[3];
				var accYear=checked.attr("class").split(" ")[4];
				var className=checked.closest('tr').find('.clsName').text();
				var secName=checked.closest('tr').find('.secName').text();
				getTeacherList(locationid);
				$("#hclassid").val(classid);
	 			$("#hsectionid").val(sectionid);
		 		$("#hteacherid").val(teacherid);
				$("#teachernameid").val(teacherid);
				$("#classname").val(className);
				$("#sectionname").val(secName);
				$("#dlocation").val(locationid);
				 $("#accyearp").val(accYear);
				 $("#dialog1").dialog("open");
				// window.location.href = "classteachermapping.html?method=editClassTeacherAction&classid="+classid+"&sectionid="+sectionid+"&teacherid="+teacherid+"&locationid="+locationid;
			}
	 });
	
	
	if($("#hteacherid").val()!="-"||$("#hteacherid").val()!=undefined){
		$("#classname").attr("readonly",true);
		$("#sectionname").attr("readonly",true);
	}
	
					if($("#allstudent tbody tr").length ==0){
						$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
						$(".banners").hide();
					}
					
				var teachername = $('#hteacherid').val();	
					
				
					
					$("#teachernameid option[value=" + teachername + "]").attr('selected', 'true');			
					
				 
					$("#search").click(function(){
						var searchTerm=$("#searchterm").val().trim();
							window.location.href="menuslist.html?method=getclassandteacherList&searchTerm="+searchTerm;	
					});
				 
					$('#xls').click(function() {
						var classId=$("#class").val();
						var accyear=$("#accyear").val()
						var locationId=$("#location").val();
						var sectionid=$("#section").val();
						var searchVal=$("#searchName").val();
						var dep=$("#departmentid").val();
								window.location.href = "classteachermapping.html?method=classTeacherMappingXLSReport&location="+locationId+"&accyear="+accyear+"&classId="+classId+"&sectionid="+sectionid+"&searchVal="+searchVal+"&dep="+dep;;
							});

					$("#pdfDownload").click(function(){
						var classId=$("#class").val();
						var accyear=$("#accyear").val()
						var locationId=$("#location").val();
						var sectionid=$("#section").val();
						var searchVal=$("#searchName").val();
						var dep=$("#departmentid").val();
						window.location.href = "classteachermapping.html?method=classTeacherMappingPDFReport&location="+locationId+"&accyear="+accyear+"&classId="+classId+"&sectionid="+sectionid+"&searchVal="+searchVal+"&dep="+dep;
					});	
					 $("#dialog1").dialog({
					 	autoOpen  : false,
					 	resizable : false,
					    modal     : true,
					    width	:400,
					    title     : "Class Teacher Mapping",
					    buttons   : {
					
					    	"Save":function() {
					    		var classid = $("#hclassid").val();
					 			var sectionid = $("#hsectionid").val();
					 			var teacherid = $("#teachernameid").val();
					 			var hteacherid = $("#hteacherid").val();
					 			var accYear =  $("#accyearp").val();
					 			
					 			 
					 			if(teacherid=='all'){
					 				 $(".errormessagediv").show();
										$(".validateTips").text("Please select Teacher Name");
										 document.getElementById("teachernameid").style.border = "1px solid #AF2C2C";
											document.getElementById("teachernameid").style.backgroundColor = "#FFF7F7";
											setTimeout(function() {
												$('.errormessagediv').fadeOut();
											}, 3000);
					 			}
					 			else{
					 				saveMapping(classid,sectionid,teacherid,hteacherid,accYear);	
					 			}
					    		            
					    	},
					    	'Close' : function() {
					    		$("#teachernameid").val("");
								$("#classname").val("");
								$("#sectionname").val("");
					    		$(this).dialog('close');
					    	}
					 }
				});	 
					 $("#accyearp").val($("#haccyear").val()); 
				 
				});


function validateclassTeacher(){
	
	var classteacher_validate=0;
	
	
	   var classid = $("#hclassid").val();
		var sectionid = $("#hsectionid").val();
		var teacherid = $("#teachernameid").val();
		var htid=$("#hteacherid").val();
		
	var datalist = {
					"classid" : classid,"sectionid" : sectionid, 
					"teacherid" :teacherid,
					"htid":htid
				};
		 $
			.ajax({
				
				type : 'GET',
				url : "classteachermapping.html?method=viewValidateClassTeacherAction",
				data : datalist,
				async : false,
				success : function(
						data) {
					
					var result = $.parseJSON(data);
					
					
					
					if (result.status=="true") {
						
						classteacher_validate = 1;

					}
					else 
						
					{
						classteacher_validate = 0;
					}
					
					
				}
			});
		 return classteacher_validate;
	
}


function HideError() 
{
	
document.getElementById("teachernameid").style.border = "1px solid #ccc";
document.getElementById("teachernameid").style.backgroundColor = "#fff";

}


function  saveMapping(classid,sectionid,teacherid,hteacherid,accYear){
			 if(teacherid=="" || teacherid==null){
				 $(".errormessagediv").show();
				 $(".validateTips").text("Select Teacher Name");
				 document.getElementById("teachernameid").style.border = "1px solid #AF2C2C";
					document.getElementById("teachernameid").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
				 return false;
			 }
			 if(validateclassTeacher() == 1 ) {
                 $(".errormessagediv").show();
					$(".validateTips").text("Teacher already Mapped for Same Class&Section");
					 document.getElementById("teachernameid").style.border = "1px solid #AF2C2C";
						document.getElementById("teachernameid").style.backgroundColor = "#FFF7F7";
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					return false;
				}
			 else{
			 datalist = {
						"accYear":accYear,"classid" : classid,"sectionid" : sectionid, "teacherid" :teacherid, "teacherid1":hteacherid
					};

			 $.ajax({
					type : 'POST',
					url : "classteachermapping.html?method=saveClassTeachetAction",
					data : datalist,
					async : false,
					success : function(data) {
						var result = $.parseJSON(data);
						if(result.jsonResponse=="Class Teacher Mapped Successfully"){
							 $("#dialog1").dialog('close');
							$(".errormessagediv").hide();
							$(".successmessagediv").show();
							 $(".successmessage").text("Class Teacher Mapped Successfully");
							 setTimeout(function(){
								 getTableByFilteration($("#accyear").val());
								 /*window.location.href = "menuslist.html?method=getclassandteacherList";*/
							 },3000);
						}
						if(result.jsonResponse=="Class Teacher Mapped Failed"){
							 $("#dialog1").dialog('close');
							$(".errormessagediv").show();
							$(".successmessagediv").hide();
							 $(".validateTips").text("Class Teacher Mapped Failed");
							 setTimeout(function(){
								 getTableByFilteration($("#accyear").val());
								 /*window.location.href = "menuslist.html?method=getclassandteacherList";*/
							 },3000);
						}
					}
				});
			 }
}

function getClassList(){
	var locationid=$("#location").val();
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

			$('#class').html("");

			$('#class').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#class').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSection(){
	
		var classId=$("#class").val();
		var accyear=$("#accyear").val();
		var locationId=$("#location").val();
		
		$.ajax({
			type : "GET",
			url : "reportaction.html?method=getSectionByClass",
			data : {"classId":classId,
					"location":locationId},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);

				$("#section").html("");
				$("#section").append('<option value="all">' + "ALL"	+ '</option>');

				for (var j = 0; j < result.SectionList.length; j++) {

					$("#section").append(
							'<option value="'
							+ result.SectionList[j].sectionId
							+ '">'
							+ result.SectionList[j].sectionname
							+ '</option>');
				}
			}
		});
			
}

function getTableByFilteration(accyear){
	var classId=$("#class").val();
	var locationId=$("#location").val();
	var sectionid=$("#section").val();
	var searchVal=$("#searchName").val();
	var dep=$("#departmentid").val();
	datalist = {
			"location" :locationId,
			"accyear" :accyear,
			"classId" :classId,
			"sectionid" :sectionid,
			"searchVal" :searchVal,
			"dep" :dep,
	}, $.ajax({
		type : 'POST',
		url : "classteachermapping.html?method=getFilterdClassTeacherMappingList",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.teacherList.length>0){
				$("#iconsimg").show();
				for(var i=0;i<result.teacherList.length;i++){
					$("#allstudent tbody").append('<tr>'
					+'<td><input type="checkbox" name="getempid"  value="Get Salary Details"  class="'+result.teacherList[i].classId+' '+result.teacherList[i].sectionId+' '+result.teacherList[i].teacherId+' '+result.teacherList[i].locationId+' '+result.teacherList[i].accYear+'"> </td>'
					+'<td style="text-align: left;" class="clsName">'+result.teacherList[i].className+'</td>'
					+'<td style="text-align: left;" class="secName">'+result.teacherList[i].sectionName+'</td>'
					+'<td style="text-align: left;">'+result.teacherList[i].teacherName+'</td>'
					+'</tr>');
				}
				$(".banners").show();
				pagination(50);
				$("#show_per_page").change(function(){
					pagination($(this).val());
				});	
			}
			else{
				$("#allstudent").append("<tr><td colspan='4'>No Records Found</td></tr>");
				$("#iconsimg").hide();
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.teacherList.length);
			
		}
	});
}

function getTeacherList(locationid){
	$.ajax({
		type : "GET",
		url : "classteachermapping.html?method=getTeacherList",
		data : {
				"locationid":locationid,},
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);

			$("#teachernameid").html("");
			$("#teachernameid").append('<option value="all">' + "----------Select----------"	+ '</option>');
			for (var j = 0; j < result.teacherlist.length; j++) {

				$("#teachernameid").append(
						'<option value="'
						+ result.teacherlist[j].teacherId
						+ '">'
						+ result.teacherlist[j].tfastname
						+ '</option>');
			}
		}
	});
}



$(document).ready(function(){
	getSubjectClassList();
	accyearId = $("#hiddenaccyear").val();
	locationId = $("#hiddenloc").val();
	var periodicmarks = $("#hiddenperiodicmark").val();
	if(periodicmarks != ""){
		$("#periodicexam option[value='"+periodicmarks+"']").attr("selected",true);
		
		$("#allstudent tbody tr").each(function(){
			$(this).closest("tr").find(".periodicmarks").val(Number(periodicmarks));
		});
		$(".allstudent tbody tr").each(function(){
			$(this).closest("tr").find(".periodicmarks").val(Number(periodicmarks));
		});
	}
	var notemarks = $("#hiddennotebookmark").val();
	if(notemarks != ""){
		$("#notebook option[value='"+notemarks+"']").attr("selected",true);
	}
	
	var subenrichmarks = $("#hiddensubenrichmark").val();
	if(subenrichmarks != ""){
		$("#subenrichment option[value='"+subenrichmarks+"']").attr("selected",true);
	}
	
	$("#back1").click(function(){
		$('#backValue').val(accyearId+","+locationId);
		$('#backform').submit();
	});
	
	$("#periodicexam").change(function(){
		var periodicmark=$(this).val();
		$("#allstudent tbody tr").each(function(){
			$(this).closest("tr").find(".periodicmarks").val(Number(periodicmark));
		});
		$(".allstudent tbody tr").each(function(){
			$(this).closest("tr").find(".periodicmarks").val(Number(periodicmark));
		});
	});
	
	if($("#hiddenmarkid").val().trim() != ""){
		$("#save").text("Update");
	}
	
	$("#save").click(function(){
		var subjectId=[],labId=[];
		var halflytheoryarray=[];
		var yearlytheoryarray=[];
		var yearlypracticalarray=[];
		
		var periodicexam=$("#periodicexam").val();
		var notebook=$("#notebook").val();
		var subenrichment=$("#subenrichment").val();
		
		$(".allstudent tbody tr").each(function(){
			if($(this).closest("tr").find(".yearlytheory").val() !=""){
				subjectId.push($(this).closest("tr").find(".subjectid").attr("id"));
				labId.push($(this).closest("tr").find(".labid").attr("id"));
				yearlytheoryarray.push($(this).closest("tr").find(".yearlytheory").val());
				yearlypracticalarray.push($(this).closest("tr").find(".yearlypractical").val());
			}
		});
		
		$(".allstudent1 tbody tr").each(function(){
			subjectId.push($(this).closest("tr").find(".subjectid").attr("id"));
			yearlytheoryarray.push($("#maxlimit").val());
			yearlypracticalarray.push(0);
			labId.push(" ");
		});
		
		if(periodicexam == "" || periodicexam == undefined){
			$(".errormessagediv").show();
			document.getElementById("periodicexam").style.border = "1px solid #AF2C2C";
			document.getElementById("periodicexam").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Field Required Periodic Max. Marks.");
			setTimeout(function() {
				document.getElementById("periodicexam").style.border = "1px solid #ccc";
				document.getElementById("periodicexam").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(notebook == "" || notebook == undefined){
			$(".errormessagediv").show();
			document.getElementById("notebook").style.border = "1px solid #AF2C2C";
			document.getElementById("notebook").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Field Required Notebook Max. Marks.");
			setTimeout(function() {
				document.getElementById("notebook").style.border = "1px solid #ccc";
				document.getElementById("notebook").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else if(subenrichment == "" || subenrichment == undefined){
			$(".errormessagediv").show();
			document.getElementById("subenrichment").style.border = "1px solid #AF2C2C";
			document.getElementById("subenrichment").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Field Required Periodic Max. Marks.");
			setTimeout(function() {
				document.getElementById("subenrichment").style.border = "1px solid #ccc";
				document.getElementById("subenrichment").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else{
			datalist = {
					"subjectId" : subjectId.toString(),
					"labId" : labId.toString(),
					"yearlytheory" : yearlytheoryarray.toString(),
					"yearlypractical" : yearlypracticalarray.toString(),
					"academiyear" : $("#hiddenaccyear").val(),
					"location" : $("#hiddenloc").val(),
					"classid" : $("#hiddenclassid").val(),
					"maxlimit" : $("#maxlimit").val(),
					"periodicexam" : periodicexam,
					"notebook" : notebook,
					"subenrichment" : subenrichment,
					"markId" : $("#hiddenmarkid").val(),
			},
			$.ajax({
				type : "POST",
				url :"examCreationPath.html?method=insertMaximumExammarkDetails",
				data : datalist,
				async : false,
				success: function(data) {
					var result = $.parseJSON(data);
					if(result.status == "success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Adding Record in Progress...");
						setTimeout(function() {
							$('.successmessagediv').fadeOut();
							$('#backValue').val(accyearId+","+locationId);
							$('#backform').submit();
						}, 3000);
					}else if(result.status == "update"){
						$(".successmessagediv").show();
						$(".validateTips").text("Updating Record in Progress...");
						setTimeout(function() {
							$('.successmessagediv').fadeOut();
							$('#backValue').val(accyearId+","+locationId);
							$('#backform').submit();
						}, 3000);
					}else{
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						$(".validateTips").text("Fail To Add");
						setTimeout(function() {
							$('.errormessagediv').fadeOut();
							$('#backValue').val(accyearId+","+locationId);
							$('#backform').submit();
						}, 3000);
					}
				}
			});
		}
	});
});

function getSubjectClassList(){
	$.ajax({
		type : "POST",
		url : "examCreationPath.html?method=getSubjectClassList",
		data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val(),"classId":$("#hiddenclassid").val(),"markId":$("#hiddenmarkid").val()},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);

			$("#nonpracticalsubject").empty();
			/*$("#nonpracticalsubject").append("<table class='table allstudent1' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th rowspan='2' style='vertical-align: middle;width:  7%;'>Sl.No</th>" +
						"<th rowspan='2' style='vertical-align: middle;width:  13%;'>Subject Code</th>" +
						"<th rowspan='2' style='vertical-align: middle;'>Subject Name</th>"+
						"<th colspan='3' style='text-align:center;'>Exam Type</th>"+
					"</tr>" +
					"<tr>" +
						"<th style='text-align:center;width: 13%;'>Periodic Exam</th>" +
						"<th style='text-align:center;width: 17%;'>Half Yearly Exam(T)</th>" +
						"<th style='text-align:center;width: 17%;'>Yearly Exam(T)</th>" +
					"</tr>" +
					"</thead><tbody></tbody>" +
			"</table>");*/

			$("#practicalsubject").empty();
			if(result.subjectList.length > 0){
				for(i=0;i<result.subjectList.length;i++){
					if(result.subjectList[i].specilazationCount>0){
						
						$("#nonpracticalsubject").append("<table class='table allstudent1"+i+"' id='allstudent' width='100%'>"+"<thead>"+
							"<tr><th colspan='6' style='text-align:left;font-weight: bold; font-size: 16px;'>"+result.subjectList[i].specialization+" :</th></tr>"+
								"<tr><th rowspan='2' style='vertical-align: middle;width:  7%;'>Sl.No</th>" +
								"<th rowspan='2' style='vertical-align: middle;width:  13%;'>Subject Code</th>" +
								"<th rowspan='2' style='vertical-align: middle;'>Subject Name</th>"+
								"<th colspan='3' style='text-align:center;'>Exam Type</th>"+
							"</tr>" +
							"<tr>" +
								"<th style='text-align:center;width: 13%;'>Periodic Exam</th>" +
								"<th style='text-align:center;width: 17%;'>Half Yearly Exam(T)</th>" +
								"<th style='text-align:center;width: 17%;'>Yearly Exam(T)</th>" +
							"</tr>" +
							"</thead><tbody></tbody>" +
					"</table>");
						
						if(result.subjectList[i].examlist.length>0){
							for(j=0; j< result.subjectList[i].examlist.length ;j++){
								$("#nonpracticalsubject .allstudent1"+i).append(
										"<tr>"+
											"<td>"+(j+1)+"</td>"+
											"<td class='subjectid' id='"+result.subjectList[i].subId+"'>"+result.subjectList[i].examlist[j].subCode+"</td>"+
											"<td>"+result.subjectList[i].examlist[j].subjectName+"</td>"+
											"<td><input type='number' readonly style='width: 100px;' name='periodicmarks' class='periodicmarks'/></td>"+
											"<td><input type='number' readonly style='width: 131px;' name='halflytheory' class='setmark'/></td>"+
											"<td><input type='number' readonly style='width: 131px;' name='yearlytheory' class='setmark'/></td>"+
										"</tr>"
									);
							}
						}else{
							$("#nonpracticalsubject .allstudent1"+i).append(
									"<tr>"+
										"<td colspan='6'>No Subjects Found</td>"+
									"</tr>"
								);
						}
						
						
						
					}else{
						$("#nonpracticalsubject .allstudent1").append(
								"<tr>"+
									"<td>"+result.subjectList[i].sno1+"</td>"+
									"<td class='subjectid' id='"+result.subjectList[i].subId+"'>"+result.subjectList[i].subCode+"</td>"+
									"<td>"+result.subjectList[i].subjectName+"</td>"+
									"<td><input type='number' readonly style='width: 100px;' name='periodicmarks' class='periodicmarks'/></td>"+
									"<td><input type='number' readonly style='width: 131px;' name='halflytheory' class='setmark'/></td>"+
									"<td><input type='number' readonly style='width: 131px;' name='yearlytheory' class='setmark'/></td>"+
								"</tr>"
							);
					}
				
				}
			}else{
				$(".allstudent1 tbody").append("<tr><td colspan='6'>No Record Found</td></tr>");
			}
			if(result.subjectListWithPractical.length > 0){
				for(i=0;i<result.subjectListWithPractical.length;i++){
				if(result.subjectListWithPractical[i].specilazationCount > 0){
					$("#practicalsubject").append("<table class='table allstudent"+i+"' id='allstudent' width='100%'>"+"<thead>"+
							"<tr><th colspan='7' style='text-align:left;font-weight: bold; font-size: 16px;'>"+result.subjectListWithPractical[i].specialization+" :</th></tr>"+
							"<tr><th rowspan='3' style='vertical-align: middle;width:  7%;'>Sl.No</th>" +
							"<th rowspan='3' style='vertical-align: middle;width:  13%;'>Subject Code</th>" +
							"<th rowspan='3' style='vertical-align: middle;'>Subject Name</th>"+
							"<th colspan='5' style='text-align:center;'>Exam Type</th>"+
						"</tr>" +
						"<tr>" +
							"<th rowspan='2' style='text-align:center;width: 13%;vertical-align: middle;'>Periodic Exam</th>" +
							"<th rowspan='2' style='text-align:center;width: 8%;vertical-align: middle;'>Half Yearly Exam(T)</th>" +
							"<th colspan='2' style='text-align:center;width: 15%;'>Yearly Exam</th>" +
						"</tr>" +
						"<tr>" +
							"<th style='text-align:center;width: 10px;'>Theory</th>" +
							"<th style='text-align:center;width: 10px;'>Practical</th>" +
						"</tr>" +
						"</thead><tbody></tbody>" +
				"</table>");
					
					if(result.subjectListWithPractical[i].examlist.length>0){
						for(j=0; j< result.subjectListWithPractical[i].examlist.length ;j++){
							$("#practicalsubject .allstudent"+i).append(
									"<tr>"+
										"<td >"+(j+1)+"</td>"+
										"<td class='subjectid' id='"+result.subjectListWithPractical[i].examlist[j].subId+"'>"+result.subjectListWithPractical[i].examlist[j].subCode+"</td>"+
										"<td>"+result.subjectListWithPractical[i].examlist[j].subjectName+"</td>"+
										"<td><input type='number' readonly style='width: 100px;' name='periodicmarks' class='periodicmarks'/></td>"+
										"<td class='marktheory' id='"+result.subjectListWithPractical[i].examlist[j].theoryMarks+"'><select style='width: 90px;' class='halflytheory' name='halflytheory'><option value=''>Select</option><option value='70'>70</option><option value='80'>80</option><option value='90'>90</option></select></td>"+
										"<td class='markpractical' id='"+result.subjectListWithPractical[i].examlist[j].practicalMarks+"'><select style='width: 80px;' class='yearlytheory' name='yearlytheory'><option value=''>Select</option><option value='70'>70</option><option value='80'>80</option><option value='90'>90</option></select></td>"+
										"<td class='labid' id='"+result.subjectListWithPractical[i].examlist[j].labid+"'><input type='number' readonly style='width: 75px;' name='yearlypractical' class='yearlypractical'/></td>"+
									"</tr>"
								);
						}
					}else{
						$("#practicalsubject .allstudent"+i).append(
								"<tr>"+
									"<td colspan='7'>No Practicals Founds</td>"+
								"</tr>"
							);
					}
				}else{
					for(i=0;i<result.subjectListWithPractical.length;i++){
						$("#practicalsubject").append("<table class='table allstudent' id='allstudent' width='100%'>"+"<thead><tr>"+
								"<th rowspan='3' style='vertical-align: middle;width:  7%;'>Sl.No</th>" +
								"<th rowspan='3' style='vertical-align: middle;width:  13%;'>Subject Code</th>" +
								"<th rowspan='3' style='vertical-align: middle;'>Subject Name</th>"+
								"<th colspan='5' style='text-align:center;'>Exam Type</th>"+
							"</tr>" +
							"<tr>" +
								"<th rowspan='2' style='text-align:center;width: 13%;vertical-align: middle;'>Periodic Exam</th>" +
								"<th rowspan='2' style='text-align:center;width: 8%;vertical-align: middle;'>Half Yearly Exam(T)</th>" +
								"<th colspan='2' style='text-align:center;width: 15%;'>Yearly Exam</th>" +
							"</tr>" +
							"<tr>" +
								"<th style='text-align:center;width: 10px;'>Theory</th>" +
								"<th style='text-align:center;width: 10px;'>Practical</th>" +
							"</tr>" +
							"</thead><tbody></tbody>" +
						"</table>");
						
						
						$("#practicalsubject .allstudent").append(
							"<tr>"+
								"<td >"+result.subjectListWithPractical[i].sno1+"</td>"+
								"<td class='subjectid' id='"+result.subjectListWithPractical[i].subId+"'>"+result.subjectListWithPractical[i].subCode+"</td>"+
								"<td>"+result.subjectListWithPractical[i].subjectName+"</td>"+
								"<td><input type='number' readonly style='width: 100px;' name='periodicmarks' class='periodicmarks'/></td>"+
								"<td class='marktheory' id='"+result.subjectListWithPractical[i].theoryMarks+"'><select style='width: 90px;' class='halflytheory' name='halflytheory'><option value=''>Select</option><option value='70'>70</option><option value='80'>80</option><option value='90'>90</option></select></td>"+
								"<td class='markpractical' id='"+result.subjectListWithPractical[i].practicalMarks+"'><select style='width: 80px;' class='yearlytheory' name='yearlytheory'><option value=''>Select</option><option value='70'>70</option><option value='80'>80</option><option value='90'>90</option></select></td>"+
								"<td class='labid' id='"+result.subjectListWithPractical[i].labid+"'><input type='number' readonly style='width: 75px;' name='yearlypractical' class='yearlypractical'/></td>"+
							"</tr>"
						);
					}
				}
				}
			}else{
				$(".allstudent tbody").append("<tr><td colspan='7'>No Record Found</td></tr>");
			}
			$(".allstudent1 tbody").find(".setmark").val($("#maxlimit").val());
			
			$(".allstudent tbody tr").each(function(){
				var marktheory=$(this).closest("tr").find(".marktheory").attr("id");
				if(marktheory != ""){
					$(this).closest("tr").find(".halflytheory option[value='"+Number(marktheory)+"']").prop('selected',true);
					$(this).closest("tr").find(".yearlytheory option[value='"+Number(marktheory)+"']").prop('selected',true);
				}
				getPracticalMark(this);
			});
			
			$(".yearlytheory").change(function(){
				var yearlymark=$(this).val();
				$(this).closest("tr").find(".halflytheory option[value='"+yearlymark+"']").prop('selected',true);
				getPracticalMark(this);
			});
			
			$(".halflytheory").change(function(){
				var yearlymark=$(this).val();
				$(this).closest("tr").find(".yearlytheory option[value='"+yearlymark+"']").prop('selected',true);
				getPracticalMark(this);
			});
		}
	});
}

function getPracticalMark(e){
	var maxlimit=$("#maxlimit").val();
	var yearlytheory=$(e).closest("tr").find(".yearlytheory option:selected").val();
	if(yearlytheory !=""){
		var total=Number(maxlimit)-Number(yearlytheory);
		$(e).closest("tr").find(".yearlypractical").val(total);
	}else{
		$(e).closest("tr").find(".yearlypractical").val(0);
	}
}
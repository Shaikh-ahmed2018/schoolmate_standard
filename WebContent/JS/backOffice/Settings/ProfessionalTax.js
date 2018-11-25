function addMorePTSeetingsRow(){
	$("#addPTSettings tbody").append("<tr>" +
			"<td><input type='text' class='form-control monthlysalary' placeholder='Min. Salary' value='' /></td>" +
			"<td><input type='text' class='form-control maxMonthlysalary' placeholder='Max. Salary' value='' /></td>" +
			"<td><input type='text' class='form-control ptlevied' value='0' /></td>" +
			"<td align='center'><a href='javascript:void(0);' class='glyphicon glyphicon-trash'></a></td>"+
			"</tr>");
	removeRow();
	validation();
}
$(document).ready(function(){
	removeRow();
	validation();
	$("#stateId").change(function(){
		
		
		$.ajax({
			type:'POST',
			url:'menuslist.html?method=getProfessionalTaxSettings',
			data:{
				"countryId":$("#countryId").val(),
				"stateId":$(this).val(),
			},
			async:false,
			success:function(response){
				var result=$.parseJSON(response);
				$("#addPTSettings tbody").empty();
				
				if(result.ptList.length>0){
					$("#addPTSettings tbody").append("<tr>" +
							"<td><input type='text' class='form-control upto' value='UPTO' readonly='readonly' /></td>" +
							"<td><input type='text' class='form-control maxMonthlysalary' placeholder='Max. Salary' value='"+(result.ptList[0].monthly_salary-1)+"' /></td>" +
							"<td><input type='text' class='form-control ' value='NIL' /></td>" +
							"<td align='center'></td>"+
							"</tr>");
					for(var i=0;i<result.ptList.length;i++){
						var maxSalary="";
						var j=(i+1);
						if(j==result.ptList.length){
							maxSalary="OR ABOVE"
						}
						else{
							maxSalary=Number(result.ptList[j].monthly_salary-1)
									
						}
						$("#addPTSettings tbody").append("<tr>" +
								"<td><input type='text' class='form-control monthlysalary' value='"+result.ptList[i].monthly_salary+"' /></td>" +
								"<td><input type='text' class='form-control maxMonthlysalary' placeholder='Max. Salary' value='"+maxSalary+"' /></td>" +
								"<td><input type='text' class='form-control ptlevied' value='"+result.ptList[i].pt_levied+"' /></td>" +
								"<td align='center'><a href='javascript:void(0);' class='glyphicon glyphicon-trash'></a></td>"+
								"</tr>");
					}
					
					validation();	
				}
				else{
					$("#addPTSettings tbody").append("<tr>" +
							"<td><input type='text' class='form-control upto' value='UPTO' readonly='readonly' /></td>" +
							"<td><input type='text' class='form-control maxMonthlysalary' placeholder='Max. Salary' value='' /></td>" +
							"<td><input type='text' class='form-control ' value='NILL' /></td>" +
							"<td align='center'></td>"+
							"</tr>");
					
				}
				
				
			}
		});
		
	});
	
	$("#saveid").click(function(){
		
		var stateId=$("#stateId").val();
		if(stateId==""){
			$(".errormessagediv").show();
			$(".errormessagediv .validateTips").text("Select State");
		}
		else{
			var monthly_salary=[];
			var pt_levied=[];
			var flag=true;
			var error="";
			$("#addPTSettings tbody tr").each(function(){
				if($(this).find(".upto").val()=="UPTO"){
					
					
				}
				else{
					if($(this).find(".monthlysalary").val()>0 && $(this).find(".ptlevied").val()>0){
						monthly_salary.push($(this).find(".monthlysalary").val());
						pt_levied.push($(this).find(".ptlevied").val());
					}
					else{
						error="Monthly Salary Should be greater than '0' ";
						flag=false;
						return false;
					}
				}
				
					
					
			});
			
			if(flag){
				$.ajax({
					type:'POST',
					url:'menuslist.html?method=professionalTaxSettingsSave',
					data:{
						"monthly_salary":monthly_salary.toString(),
						"pt_levied":pt_levied.toString(),
						"stateId":stateId,
					},
					async:false,
					success:function(response){
						var result=$.parseJSON(response);
						if(result.status=="true"){
							$(".successmessagediv").show();
							$(".successmessagediv .validateTips").text("Adding Records...");
							setTimeout(function(){
								location.reload();
							},3000);
							
						}
						else{
							$(".errormessagediv").show();
							$(".errormessagediv .validateTips").text("Record Not Added");
						}
						
					}
				});
			}
			else{
				$(".errormessagediv").show();
				$(".errormessagediv .validateTips").text("Fill All Fields Correctly");
			}	
		
			
		}
		
	});
	
	
});
function removeRow(){
	$(".glyphicon-trash").click(function(){
		$(this).closest("tr").remove();
	});
}
function validation(){
	$("input.monthlysalary").keyup(function(){
		if(isNaN($(this).val())){
			$(this).val("");
			$(".errormessagediv").show();
			$(".errormessagediv .validateTips").text("Fill minimum salary Amount");
		}
		else{
			$(".errormessagediv").hide();
		}
	});
	
	$(".monthlysalary").change(function(){
		
		var pointer=$(this);
		$(".monthlysalary").each(function(){
			
			if(Number($(this).not(pointer).val())==Number(pointer.val())){
				$(".errormessagediv").show();
				$(".errormessagediv .validateTips").text("Monthy Salary duplicate");
				pointer.val("0");
				return false;
			}
		});
	});
	$(".ptlevied").change(function(){
		var pointer=$(this);
		$(".ptlevied").each(function(){
			if(Number($(this).not(pointer).val())==Number(pointer.val())){
				$(".errormessagediv").show();
				$(".errormessagediv .validateTips").text("Professional Tax Levied (P.M)  duplicate");
				pointer.val("0");
			}
		});
	});
}
$(document).ready(function(){
	
	checkboxselect();
	
	$("#add").click(function(){
		window.location.href="LibraryMenu.html?method=addLibraryItem&historystatus="+$("#status").val();
	});
	
	$("#edit").click(function(){
		var count=$(".select:checked").length;
		if(count== 0 || count > 1){
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
		}
		else{
			window.location.href="LibraryMenu.html?method=modifyLibraryItem&id="+$(".select:checked").val()+
			"&historystatus="+$("#status").val();
		}
		
	});
	$("#status").change(function(){
		getLibraryItemByJS($(this).val());
	});
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
	}
	
	if($("#historystatus").val()!=""){
		$("#status").val($("#historystatus").val());
		
		getLibraryItemByJS($("#status").val());
	}
	pagination(100);
});

function checkboxselect(){
	$("#selectAll").change(function(){
		$(".select").prop("checked",$(this).prop("checked"));
	});
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectAll").prop("checked",true);
		}
		else{
			$("#selectAll").prop("checked",false);
		}
	});
}

function getLibraryItemByJS(data){
	$.ajax({
		type:'POST',
		url:'LibraryMenu.html?method=getLibraryItemByJS',
		data:{"status":data},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$("#allstudent tbody").empty();
			if(result.itemListByJs.length>0){
				for(var i=0;i<result.itemListByJs.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' name='checkbox_id' class='select' style='text-align:center' value='"+result.itemListByJs[i].id+"'></td>" +
							"<td>"+result.itemListByJs[i].id+"</td>" +
							"<td>"+result.itemListByJs[i].name+"</td>" +
							"<td>"+result.itemListByJs[i].description+"</td>" +
							"<td>"+result.itemListByJs[i].status+"</td>" +
							"</tr>");
				}
				
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='5'> No Record Found</td></tr>")
			}
			checkboxselect();
			$(".numberOfItem").text("No.of Records "+result.itemListByJs.length+" .");
			pagination(100);
		}
		
	});
	
}
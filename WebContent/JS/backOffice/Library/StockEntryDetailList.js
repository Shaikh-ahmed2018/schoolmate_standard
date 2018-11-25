$(document).ready(function(){
	
	
	$("#upload").change(function(){
		window.location.href="LibraryMenu.html?method=stockEntryExcelUpload";
	});
	
	getStockEntryBookList();

	$("#selectall").change(function(){
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	getLibraryLocation();
	
	$("#locationname").change(function(){
		getLibraryLocation();
		getStockEntryBookList();
	});
	
	$("#liblocId").change(function(){
		getStockEntryBookList();
	});
	
	$(".select").change(function(){
		if($(".select").length==$(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
		
	});
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='10'>No Records Found</td></tr>");
	}
	
	
	$("#editId").click(function(){
			$(".successmessagediv1").hide();
			var cnt = 0;
			
			getData = $(".select:checked").attr("value");
	        var len =$(".select:checked").length;

		 if (len==0|| len > 1) {
				$(".errormessagediv").show();
				$(".validateTips").text("Select any one record");
				return false;
			} 
			else
			{
				window.location.href = "LibraryMenu.html?method=editStockEntryDetail&stockid="+getData;
			}
		});
	pagination(100);
	
});

function getLibraryLocation(){
	var location=$("#locationname").val();
	
	$.ajax({
		type:"POST",
		url:"LibraryMenu.html?method=LibraryLocation",
		data:{"schoolLocation":location},
		async:false,
		success : function(response){
			var result = $.parseJSON(response);
			$("#liblocId").empty();
			$("#liblocId").append("<option value='all'>ALL</option>");
			for(var i=0;i<result.librarylocationsDetails.length;i++){
				$("#liblocId").append("<option value='"+result.librarylocationsDetails[i].librarylocid+"'>"+result.librarylocationsDetails[i].libraryLocations+"</option>");	
			}
		}
		
	});
}

function getStockEntryBookList(){
		datalist={
			"locId"	:$("#locationname").val(),
			"liblocId"	:$("#liblocId").val(),
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=getStockEntryBookList",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
			
				if(result.StockList.length > 0){
				for(var i=0;i<result.StockList.length;i++){
					$("#allstudent tbody").append("<tr>" +
							"<td><input type='checkbox' name='select' class='select'  value='"+result.StockList[i].enteryid+"'/></td>"+
							"<td>"+result.StockList[i].accessionNo+"</td>"+
							"<td>"+result.StockList[i].itemType+"</td>"+
							"<td>"+result.StockList[i].regdate+"</td>"+
							"<td>"+result.StockList[i].bookTitle+"</td>"+
							"<td>"+result.StockList[i].author+"</td>"+
							"<td>"+result.StockList[i].ddc+"</td>"+
							"<td>"+result.StockList[i].no_of_Copies+"</td>"+
							"<td>"+result.StockList[i].availableCopies+"</td>"+
							"<td>"+result.StockList[i].location +"</td>"+
							"<td>"+result.StockList[i].currentStatus+"</td>"+
							"</tr>"
					);
				    }
				}
				else{
					$("#allstudent tbody").append("<tr><td ColSpan='10'>No Records Found</td></tr>");
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.StockList.length);
				pagination(100);
			}
		});
			
	}

	
	

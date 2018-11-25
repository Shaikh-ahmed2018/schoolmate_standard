$(document).ready(function(){
	getSupplierSettingList();
	
	$("#selectall").change(function(){
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
	
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
	}
	
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
		  $("input:checkbox").prop('checked', false); 
		    if(e.keyCode == 13){
		        e.preventDefault();
		        SupplierDetailsSearch();
 		     }	
 	 });
	 $("#search").click(function(){
		 SupplierDetailsSearch();
	 });
	 
	
	 $("#add").click(function(){
		 window.location.href="LibraryMenu.html?method=supplierSettings&historysearch="+$("#searchValue").val();
	 });
	
	$("#editId").click(function(){
		var cnt = $(".select:checked").length;
		if(cnt > 1 ||  cnt==0)
		{
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
			return false;
		}else{
			getData = $(".select:checked").val();
			window.location.href="LibraryMenu.html?method=editSupplierSetting&supid="+getData+
			"&historysearch="+$("#searchValue").val();
		}
	});
	
	
	 deleteid=[];
		$('#delete').click(function() {

					var count =0;
					
					$(".select:checked").each(function(){

						var list=$(this).val();
						deleteid.push(list);
						count ++;
					});	
					
					if(count == 0)	{
				 		$('.errormessagediv').show();
						$('.validateTips').text("Select any record");
						$('.errormessagediv').delay(3000).slideUp();
				 	}
				 	else {
						  $("#dialog").dialog("open");
						  $("#dialog").empty();
						  $("#dialog").append("<p>Are you sure to delete?</p>");
					}
					
				});

		$("#dialog").dialog({
	 		
	 		 autoOpen: false,
		     modal: true,					    
		     title:'Supplier Details',
		     buttons : {
		    	 "Yes" : function() {
						$.ajax({
							type : "GET",
							url : "LibraryMenu.html?method=deleteSupplierSetting",
							data :{
								"deleteid" :deleteid.toString()},

							success : function(response) {
								var result = $.parseJSON(response);
								$('.errormessagediv').hide();
								
								if(result.supsetting=="deletesuccuss"){
									
									$(".successmessagediv").show();
									$(".validateTips").text("Unmapped Record Delete Progressing...");
								}
								
								else{
									$(".errormessagediv").show();

									$(".validateTips").text("Selected Supplier Name is Mapped Cannot Delete");
									$('.errormessagediv').delay(3000).slideUp();
								}

								setTimeout(function(){
									   
									 window.location.href="LibraryMenu.html?method=ListsupplierSettings";
								 
								 },2000);
							}
						});
				 		 $(this).dialog("close");
		          },
	 		
		          "No" : function() {
			            $(this).dialog("close");
			          }
	 		
		     }
	 	});
	pagination(100);
	
	if($("#historysearch").val()!=""){
		$("#searchValue").val($("#historysearch").val());
		SupplierDetailsSearch();
	}
	
});

function getSupplierSettingList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getSupplierSettingList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		
			for(var i=0;i<result.suplist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select'  value='"+result.suplist[i].entry_id+"'/></td>"+
						"<td>"+result.suplist[i].suppliedBy+"</td>"+
						"<td>"+"<b>E-mail:</b><span class='adjust'>"+result.suplist[i].email+"</span><br/>"+
						"<b>Fax:</b><span class='adjust'>"+result.suplist[i].fax+"</span><br/>"+
						"<b>Tel-Phone:</b><span class='adjust'>"+result.suplist[i].telephone+"</span><br/>"+
						"<b>Mobile-No:</b><span class='adjust'>"+result.suplist[i].mobilenum+"</span><br/>"+"</td>"+
						"<td>"+result.suplist[i].supplieraddress+"</td>"+
						"</tr>"
				);
				
				  
				
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.suplist.length);
		}
	});
		
}



function  SupplierDetailsSearch(){

	datalist={
			"supplier" : 	$("#Supplier").val(),
			"searchid"  :$("#searchValue").val()
		},
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=SupplierDetailsSearch",
			data : datalist,
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
			  if(result.suplist.length>0){
				  
				  for(var i=0;i<result.suplist.length;i++){
						$("#allstudent tbody").append("<tr>" +
								"<td><input type='checkbox' name='select' class='select'  value='"+result.suplist[i].entry_id+"'/></td>"+
								"<td>"+result.suplist[i].suppliedBy+"</td>"+
								"<td>"+" <b>E-mail:</b><span class='adjust'>"+result.suplist[i].email+"</span><br/>"+
								"<b>Fax:</b><span class='adjust'>"+result.suplist[i].fax+"</span><br/>"+
								"<b>Tel-Phone:</b><span class='adjust'>"+result.suplist[i].telephone+"</span><br/>"+
								"<b>Mobile-No:</b><span class='adjust'>"+result.suplist[i].mobilenum+"</span><br/>"+"</td>"+
								"<td>"+result.suplist[i].supplieraddress+"</td>"+
								"</tr>"
						
						);
						
				}
					
				}
			  else{
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
				
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.suplist.length);
				
				
				if(result.suplist.length >= 1){
					$(".select").change(function(){
						if($(".select").length==$(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
				}


			}
		});
	
}







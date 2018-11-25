$(document).ready(function(){
	
	getPublisherSettingList();

	
	 checkboxs();
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='4'>No Records Found</td></tr>");
	}
	
	 $("#searchValue").keypress(function(e){
		 var searchname = $("#searchValue").val().trim();
	      $("input:checkbox").prop('checked', false); 
		    if(e.keyCode == 13){
		        e.preventDefault();      
		        publisherDetailsSearch();
 		 }	
 	 });
	 
	 $("#search").click(function(){
		  publisherDetailsSearch();
	 });
	 $("#add").click(function(){
		 window.location.href="LibraryMenu.html?method=publisherSettings&historysearch="+$("#searchValue").val();
	 });
	
	$("#editId").click(function(){
		var count =$(".select:checked").length;
		if (count == 0 || count > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
			return false;
		} else {
			getData = $(".select:checked").val();
			 window.location.href = "LibraryMenu.html?method=editpublisherSetting&pubid="+getData+
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
	     title:'Publisher Details',
	     buttons : {
	    	 "Yes" : function() {
	    		 
					$.ajax({
						type : "GET",
						url : "LibraryMenu.html?method=deletepublisherSetting",
						data :{
							"deleteid" :deleteid.toString()},

						success : function(response) {
							var result = $.parseJSON(response);
							$('.errormessagediv').hide();
							
							if(result.pubsetting=="DeleteSuccuss"){
								
								$(".successmessagediv").show();
								$(".validateTips").text("Unmapped Record Delete Progressing...");
							}
							
							else{
								$(".errormessagediv").show();

								$(".validateTips").text("Selected Publisher Details is Mapped Cannot be Deleted");
								$('.errormessagediv').delay(3000).slideUp();
							}

							setTimeout(function(){
								 window.location.href="LibraryMenu.html?method=ListpublisherSettings";
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

	
 
	if($("#historysearch").val()!=""){
		$("#searchValue").val($("#historysearch").val());
		publisherDetailsSearch();
	}
	pagination(100);
	
	
});
function getPublisherSettingList(){
	datalist={
			
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getPublisherSettingList",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		  if(result.pubList.length>0){
			for(var i=0;i<result.pubList.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.pubList[i].entry_id+"'/></td>"+
						"<td>"+result.pubList[i].publisher+"</td>"+
					    "<td>"+"<b>E-mail</b>: <span class='adjust'>"+result.pubList[i].email+"</span><br />"+
						"<b>Fax:</b><span class='adjust'>"+result.pubList[i].fax+"</span><br />"+
						"<b>Tel-Phone:</b> <span class='adjust'>"+result.pubList[i].telephone+"</span><br />"+
						"<b>Mobile-No:</b> <span class='adjust'>"+result.pubList[i].mobilenum+"</span><br />"+"</td>"+
						"<td>"+result.pubList[i].address+"</td>"+
						"</tr>"
				
				);
				
			}
		}
	  else{
			$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
		}
	    pagination(100);
	    $(".numberOfItem").empty();
	    $(".numberOfItem").text("No.of Records "+result.pubList.length+".");
	    
		}
	});
		
}

function publisherDetailsSearch(){
datalist={
		"publisher" : 	$("#Publisher").val(),
		"searchid"  :$("#searchValue").val()
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=publisherDetailsSearch",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		  if(result.publist.length>0){
			for(var i=0;i<result.publist.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select' value='"+result.publist[i].entry_id+"'/></td>"+
						"<td>"+result.publist[i].publisher+"</td>"+
					    "<td>"+"<b>E-mail:</b> <span class='adjust'>"+result.publist[i].email+"</span><br />"+
						"<b>Fax:</b> <span class='adjust'>"+result.publist[i].fax+"</span><br />"+
						"<b>Tel-Phone: </b><span class='adjust'>"+result.publist[i].telephone+"</span><br />"+
						"<b>Mobile-No:</b><span class='adjust'> "+result.publist[i].mobilenum+"</span><br />"+"</td>"+
						"<td>"+result.publist[i].address+"</td>"+
						"</tr>"
				
				);
			}
				
			}
		  else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}
		    checkboxs();
		    $(".numberOfItem").empty();
		    $(".numberOfItem").text("No.of Records "+result.publist.length+" .");
		     pagination(100);	
		 
		     if(result.publist.length >= 1){
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

function checkboxs(){

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
}
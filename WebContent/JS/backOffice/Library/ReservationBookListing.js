$(document).ready(function(){
	
	 getReservationListDetails();
	
     getLibraryLocation();
	
	$("#locationname").change(function(){
		getLibraryLocation();
		getReservationListDetails();
	});
	
	$("#liblocId").change(function(){
		getReservationListDetails();
	});
	
	
	$("#selectall").change(function() {

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
	
	$("#edit").click(function(){
		$(".successmessagediv").hide();
		var cnt = 0;

		$('input[type="checkbox"]:checked').map(function() {
		getData = $(this).val();
			cnt++;
		});
		
		if (cnt == 0 || cnt < 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			return false;
		} 
		else 
		{
			var reserveid=getData;
			window.location.href = "LibraryMenu.html?method=editReservationBook&reserveid="+reserveid+
			"&historylocId="+$("#locationname").val()+"&historyliblocId="+$("#liblocId").val();
		
		}
	});
	
	$("#delete").click(function(){
		
		var count = 0;
		libIdlist = [];
		$(".select:checked").each(function() {
			var list = $(this).val();
			libIdlist.push(list);
			count++;
		});

		if (count == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text(
			"Select record to Delete");
			return false;

		} else {
			$("#dialog").dialog("open");
			$("#dialog").empty();
			$("#dialog").append("<p>Are you sure to delete?</p>");
		}

	});

$("#dialog").dialog(
	{
		autoOpen : false,
		modal : true,
		title : 'Book Reservation Details',
		buttons : {
			"Yes" : function() {

		$.ajax({
			type:"Post",
			url:"LibraryMenu.html?method=deleteReservedBook",
			data:{"librarylocid" : libIdlist},
			async:false,
			success:function(response){
				var result = $.parseJSON(response);
			
				if(result.status == "deletesuccess"){
					$(".successmessagediv").show();
					$(".validateTips").text("Deleting Record Progrssing...");
					setTimeout(function() {
						window.location.href="LibraryMenu.html?method=BookReservationDetailslist";
					},3000);
				
			}else if(result.status == "deletefail"){
				$(".errormessagediv").show();
				$(".validateTips").text("Deleting Record failed...");
				setTimeout(function() {
					window.location.href="LibraryMenu.html?method=BookReservationDetailslist";
			},3000);
		}
			  
		}
		});
		$(this).dialog("close");
	},
	"No" : function() {
		$(this).dialog("close");
	}
		}
	});
	
	
	
	
	
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

function getReservationListDetails(){
	datalist={
			"locId":$("#locationname").val(),
			"liblocId":$("#liblocId").val()
	},
	$.ajax({
		type : 'POST',
		url : "LibraryMenu.html?method=getReservationListDetails",
		data : datalist,
		async : false,
		success : function(response) {

			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
		
			if(result.reservationList.length > 0){
			for(var i=0;i<result.reservationList.length;i++){
				$("#allstudent tbody").append("<tr>" +
						"<td><input type='checkbox' name='select' class='select'  value='"+result.reservationList[i].reservationId+"'/></td>"+
						"<td>"+result.reservationList[i].accessionNo+"</td>"+
						"<td>"+result.reservationList[i].bookTitle+"</td>"+
						"<td>"+result.reservationList[i].author+"</td>"+
						"<td>"+result.reservationList[i].location+"</td>"+
						"<td class='"+result.reservationList[i].subscriber_id+" "+result.reservationList[i].name+" "+result.reservationList[i].userType+"'>"+result.reservationList[i].subscriberName+"</td>"+
						"<td>"+result.reservationList[i].from_date+"</td>"+
						"<td>"+result.reservationList[i].to_date+"</td>"+
						"<td>"+result.reservationList[i].userType+"</td>"+
						"</tr>"
				);
			  }
			}else{
				$("#allstudent tbody").append("<tr><td ColSpan='9'>No Records Found</td></tr>");
			}
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.reservationList.length);
			pagination(100);
		}
	
	});
		
}
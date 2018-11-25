function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function myFunction() {
	document.getElementById("myForm").submit();   
}

$(document).ready(function() {
	
	
	
	$("#status").val($("#hiddenstatus").val());
		
	SchoolList();
	setTimeout("removeMessage()", 2000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 2000);
	
});				


function SchoolList(){
	
	$.ajax({
		type : 'POST',
		url : "locationDetails.html?method=SchoolList",
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			var i=0;
			var len=result.LocationList.length;
			
			if(len > 0){
				
				 $("#add").hide();
				for(i=0;i<len;i++){
				$("#allstudent tbody").append("<tr id='"+result.LocationList[i].location_id+"'>"
						+"<td> "+result.LocationList[i].locationname+" </td>"
						+"<td>"+"<span>Email Id:- <span class='tablecontents'>"+result.LocationList[i].emailId+"</span></span><br />"
						+"<span>Web Site:- <span class='tablecontents'>"+result.LocationList[i].website+"</span></span><br />"
						+"<span>Address:- <span class='tablecontents'>"+result.LocationList[i].address+"</span></span><br />"
						+"<td><img src='"+result.LocationList[i].schoollogo+"' width='40' height='40' /></td>"
						+"</tr>");
				 }	
				if($("#isvalid").val() == "modify"){
					$('#allstudent tbody tr').attr('title', 'Click here to view/modify the school details');
				}
				$("#allstudent tbody tr").click(function(){
					getData = $(this).attr("id");
					window.location.href ="locationDetails.html?method=editSchool&locid="+getData;
				});
			}
				else{
					 $("#add").show();
					$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
				}
			 
			$("#loder").hide();
		}
	});
}  

function HideError(pointer){
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


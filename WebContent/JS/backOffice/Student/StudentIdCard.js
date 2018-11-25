$(document).ready(function(){
	
	$("#accyear").val($("#hacademicyaer").val());
	
	getIdCardStreamList();
	getStream($("#locationname"));
$("#locationname").change(function(){
	getStream($(this));
	getSearchIdCardStreamList();
});
$("#streamId").change(function(){
	getSearchIdCardStreamList();
});
$("#accyear").change(function(){
	getSearchIdCardStreamList();
});

 
if($('#allstudent tbody td').length==0){
	$(".pagebanner").hide(); 
	$(".pagination").hide();
}

	if($("#historyschoolId").val()!="" || $("#historyaccyear").val()!=""){
		$("#locationname").val($("#historyschoolId").val());
		getStream($("#locationname"));
		$("#streamId").val($("#historystreamId").val());
		$("#accyear").val($("#historyaccyear").val());
		getSearchIdCardStreamList();
	}

});
function getStream(pointer){
	$.ajax({
		url : "classPath.html?method=getStreamDetailAction",
		async : false,
		data:{'school':pointer.val().trim()},
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#streamId').empty();
			$('#streamId').append('<option value="">-------Select-------</option>');
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#streamId').append('<option value="'+ result.streamList[j].streamId+ '">'
						+ result.streamList[j].streamName+ '</option>');

			}

		}
	});
}
function getIdCardStreamList(){
	var dataList={"academicYear":$("#globalAcademicYear").val(),
					"locationId":$("#locationname").val(),
					"streamId":"all",
	};
	$.ajax({
		url : "menuslist.html?method=NewstudentIdCardDesignList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			if(result.streamList.length > 0){	
			for ( var j = 0; j < result.streamList.length; j++) {
				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].academicYearCode+" "+ result.streamList[j].locationId+" "+ result.streamList[j].streamId+"'>" +
						"<td>"+ result.streamList[j].sno+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].streamName+ "</td>" +
						"</tr>");
			  }
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}
			$(".pagebanner").show(); 
			$(".pagination").show();
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			rowClickable();
		}
	});
	
}
function getSearchIdCardStreamList(){
	
	var academicYear=$("#accyear").val();
	if($("#accyear").val()==""){
		academicYear="all";
	}
	var locationId=$("#locationname").val();
	if($("#locationname").val()==""){
		locationId="all";
	}
	var streamId=$("#streamId").val();
	if($("#streamId").val()==""){
		streamId="all";
	}
	
	
	var dataList={"academicYear":academicYear,
					"locationId":locationId,
					"streamId":streamId,
					
	};
	$.ajax({
		url : "menuslist.html?method=NewstudentIdCardDesignList",
		data:dataList,
		async : false,
		success : function(data) {
			
			var result = $.parseJSON(data);
			$('#allstudent tbody').empty();
			if(result.streamList.length > 0){
			for ( var j = 0; j < result.streamList.length; j++) {

				$('#allstudent tbody').append("<tr class='"+ result.streamList[j].academicYearCode+" "+ result.streamList[j].locationId+" "+ result.streamList[j].streamId+"'>" +
						"<td>"+ result.streamList[j].sno+ "</td>" +
						"<td>"+ result.streamList[j].academicYear+ "</td>" +
						"<td>"+ result.streamList[j].locationName+ "</td>" +
						"<td>"+ result.streamList[j].streamName+ "</td>" +
						"</tr>");
			   }
			}
			else{
				$("#allstudent tbody").append("<tr><td ColSpan='4'>No Records Found</td></tr>");
			}
			$(".pagebanner").show(); 
			$(".pagination").show();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.streamList.length);
			rowClickable();
		}
	});
	
}
function rowClickable(){
	$("#allstudent tbody tr").click(function(){
		
		window.location.href="menuslist.html?method=NewstudentIdCardPrint&accyear="+$(this).attr("class").split(" ")[0]
		+"&schoolId="+$(this).attr("class").split(" ")[1]+"&streamId="+$(this).attr("class").split(" ")[2]
		+"&historystreamId="+$("#streamId").val();

	});
}
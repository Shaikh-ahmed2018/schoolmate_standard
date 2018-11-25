$(document).ready(function() {
	if(!$("#successid").val()=="")
	{
		$("#txtstyle, #txtstyle").hide();
		$("#allstudent").show();
		$(".selecteditems").show();
	}
	else
	{
		$("#allstudent").hide();
		$(".selecteditems").hide();
		
	}
	$("#category").change(function(){
		var category=$("#category").val();
		var location=$("#location").val(); 
		datalist={
				"category" : category,
				"location":location
		},
		$.ajax({
			type : 'POST',
			url : "phonedirectory.html?method=getPersonNameListAction",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
				$('#selectname').html("");
		$('#selectname').append(
				'<option value="'
						+ "all" + '">'
						+ "ALL"
						+ '</option>');
		for ( var j = 0; j < result.phonedirectorylist.length; j++) {
			$('#selectname')
					.append(
							'<option value="'
									+ result.phonedirectorylist[j].id
									+ '">'
									+ result.phonedirectorylist[j].name
									+ '</option>');
		}
				
			}
			
		});
	});
	$("#search").click(function(){
		 var category=$("#category").val();
	     var selectname=$("#selectname").val();
	     var location=$("#location").val();
	     if(category=="" && selectname==""){
				$("#txtstyle, #txtstyle").slideToggle();
			}
	 	
	 	if(selectname==""){
	 		$('.errormessagediv').show();
	 		$('.validateTips').text("Select Branch Name");
	 		return false;
	 	}
	 	if(category==""){
	 		$('.errormessagediv').show();
	 		$('.validateTips').text("Select Category");
	 		return false;
	 	}
	 	else{
	 		$.ajax({
				type : "GET",
				url : "phonedirectory.html?method=getPhoneDirectoryList",
				data : {"category":category,
					 "location":location},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					$("#allstudent").empty("");
					for (var j = 0; j < result.phonedirectorylist.length; j++) {

						$("#allstudent").append(
								"<tr>"+
								"<td>"+result.phonedirectorylist[i].count+"</td>"+
								"<td>"+result.phonedirectorylist[i].name+"</td>"+
								"<td>"+result.phonedirectorylist[i].category+"</td>"+
								"<td>"+result.phonedirectorylist[i].phone+"</td>"+
								"<td>"+result.phonedirectorylist[i].email+"</td>"+
								"<td>"+result.phonedirectorylist[i].address+"</td>"
								+"</tr>");

					}
				}
			});
	 	}
	});
	$("#excelDownload").click(function(){
		  var category=$("#hcategory").val();
		  var selectname=$("#hname").val();
		  var location=$("#plocation").val(); 
			window.location.href = 'phonedirectory.html?method=phonedirectoryExcelReport&category='
				+category
				+ ' &selectname='
				+selectname
				+ ' &location='
				+location +"&locationName="+$("#Location option:selected").text()
				 ;
	});
	
$("#pdfDownload").click(function(){
		  var category=$("#hcategory").val();
		  var selectname=$("#hname").val();
		  var location=$("#plocation").val(); 
		  //alert($("#Location option:selected"));
			window.location.href = 'phonedirectory.html?method=phonedirectoryPdfReport&category='
				+category
				+ ' &selectname='
				+selectname
				+ ' &location='
				+location +"&locationName="+$("#Location option:selected").text();
	});
});
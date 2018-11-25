$(document).ready(function()
{
		
	$("#locationname").val($("#hiddenlocId").val());
	
	
	$("#Acyearid").val($("#globalAcademicYear").val());
	getClassList();
	$("#Acyearid").change(function(){
	 
		var classname=$("#classname").val();
		getSectionList(classname);
		
	});
	$("#locationname").change(function(){
		getClassList();
		var classname=$("#classname").val();
		getSectionList(classname);
		$("#allstudent thead").empty();
		$("#allstudent tbody").empty();
		
	});
	
	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		$("#allstudent thead").empty();
		$("#allstudent tbody").empty();
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		if($("#sectionid").val() == ""){
			 sectionid="%%";
		}
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
	
	 $("#print").click(function(){
			 A4();
	 });
	 $("#locationname").val($("#hiddenlocId").val());
	
});


function A4(){
	 var a=$("#printing-css-a4").val();
		var b = document.getElementById("studentpreview").innerHTML;

	    var abd='<style>' + a +'</style>' + b;
	
	   
	    
    var frame1 = $('<iframe />');
    frame1[0].name = "frame1";
    frame1.css({ "position": "absolute", "top": "-1000000px" });
    $("body").append(frame1);
    var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
    frameDoc.document.open();
    //Create a new HTML document.
    frameDoc.document.write('<html><head><title>DIV Contents</title>');
    //Append the external CSS file.
    frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
    frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/newUI/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">');
    frameDoc.document.write('<link href="CSS/newUI/custome.css" rel="stylesheet">');
    frameDoc.document.write('<link href="CSS/IdCard/IdCard.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('<link href="CSS/IdCard/'+$("#templatename").val()+'.css" rel="stylesheet" type="text/css" />');
    frameDoc.document.write('</head><body>');
  
    
    //Append the DIV contents.
    frameDoc.document.write(abd);
    frameDoc.document.write('</body></html>');
    frameDoc.document.close();
    setTimeout(function () {
        window.frames["frame1"].focus();
        window.frames["frame1"].print();
        frame1.remove();
    }, 100);

}

function getClassList(){
	var locationid=$("#locationname").val();
	datalist={
			"locationid" : locationid
	},

	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassByLocation",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#classname').html("");
			$('#classname').append('<option value="all">' +"-----Select-----"+ '</option>');
			for ( var j = 0; j < result.ClassList.length; j++) {
				$('#classname').append('<option value="'
						+ result.ClassList[j].classcode + '">'
						+ result.ClassList[j].classname
						+ '</option>');
			}
		}
	});
}


function getSectionList(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname").val()
	},
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#sectionid').html("");
			$('#sectionid').append('<option value="">' + "-----Select-----"	+ '</option>');
			for ( var j = 0; j < result.sectionList.length; j++) {
				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}




function getStudentListBySection(locationid,accyear,classname,sectionid){
	datalist = {
			"locationId" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionId" :sectionid,
		}, 
		$.ajax({
			type : 'POST',
			url : "menuslist.html?method=studentPhotosheetAccList",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				var result = $.parseJSON(response);
					$("#allstudent  thead").empty();
					$("#allstudent  tbody").empty();
					
					
				
			
			if(result.studentSearchList.length > 0){
						$("#allstudent thead").empty();
						$("#allstudent thead").append("<tr>"
								+"<th class='"+result.studentSearchList.studentId+"' hidden></td>" 
								+"<th colspan='4' style='text-align:center'>"+""+result.studentSearchList[0].location+"  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+result.studentSearchList[0].classname+" - "+result.studentSearchList[0].sectionnaem+"</th>"
								+"</tr>");
					
					
						var row = "<tr style='margin-top:5px'>";
				
						for(var i=0;i<result.studentSearchList.length;i++){
							if(i%4 == 0){
								   row += "</tr><tr style='margin-top:5px'>"
							        }
					    row += "<td class='"+result.studentSearchList[i].studentId+"' hidden></td>" 
							+"<td> <img src='"+result.studentSearchList[i].studentPhoto+"' width='180px' height='200px' />"+"<br><b>Admission No:</b>"+result.studentSearchList[i].studentAdmissionNo+"<br><b>Name:</b>"+result.studentSearchList[i].studentFullName+" </td>";
					}
						row += "</tr>"; 
						 $("#allstudent tbody").append(row);
			       }
					else{
						 $("#allstudent tbody").append("<tr><td colspan='5'>No Records Found</td></tr>");
					}
			      $("#loder").hide();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.studentSearchList.length);
			}
		});
	}

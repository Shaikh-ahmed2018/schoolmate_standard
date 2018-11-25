$(document).ready(function(){
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
	}
	
	
	$("#Acyearid").val($("#hacademicyaer").val());
	

	$("#locationname").change(function(){
		getClassList();
		
	});
	if($("#locationname").val()!=""){
		getClassList();
	}
	$("#Acyearid").change(function(){
		
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#classname").change(function(){
		getSectionList($(this).val());
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#sectionid").change(function(){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#search").click(function(){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
	});
	$("#searchvalue").keypress(function(e){
		if(e.keyCode=="13"){
		locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()=="" || $("#classname").val()==undefined){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
		}
	});
	
	$("#resetbtn").click(function(){
        $("#locationname").val("all");
        $("#classname").val("all");
        $("#sectionid").val("all");
        $("#searchvalue").val("");
        $("#Acyearid").val($("#hacademicyaer").val());
        
        locationId=$("#locationname").val();
		if($("#locationname").val()==""){
			locationId="all";
		}
		academicYear=$("#Acyearid").val();
		if($("#Acyearid").val()==""){
			academicYear="all";
		}
		classId=$("#classname").val();
		if($("#classname").val()==""){
			classId="all";
		}
		divisionId=$("#sectionid").val();
		if($("#sectionid").val()==""){
			divisionId="all";
		}
		searchTerm=$("#searchvalue").val().trim();
		if($("#searchvalue").val()==""){
			searchTerm="all";
		}
		getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm);
   });
	
	$("#allstudent tbody tr").click(function(){
		var studentIds=$(this).attr("class").split(" ");
		var studentId=studentIds[0];
		var academicYear=studentIds[1];
		var location=studentIds[2];
		
		
		window.location.href = "feecollection.html?method=feeCollectionStudentWise&student="+studentId+
		"&loc_id="+location+"&academicYear="+academicYear+"&historylocId="+$("#locationname").val()+
		"&historyacademicId="+$("#Acyearid").val()+"&historyclassname="+$("#classname").val()+
		"&historysectionid="+$("#sectionid").val()+"&historysearch="+$("#searchvalue").val();
	});
	pagination(100);
	
	if($("#historylocId").val()!="" || $("#historyacademicId").val()!="" || $("#historysearch").val()!="")
	{
		if($("#historyacademicId").val()!=""){
			$("#Acyearid").val($("#historyacademicId").val());
		}
		$("#locationname").val($("#historylocId").val());
		getClassList();
		$("#classname").val($("#historyclassname").val());
		getSectionList($("#classname").val());
		$("#sectionid").val($("#historysectionId").val());
		
		$("#searchvalue").val($("#historysearch").val());
		
	 getStudentListByJs($("#locationname").val(),$("#Acyearid").val(),$("#classname").val(),$("#sectionid").val(),$("#searchvalue").val());
	
	}
	
});


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

			$('#classname').append('<option value="all">' + "----------Select----------"+ '</option>');

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
			
			$('#sectionid').append('<option value="all">' + "----------Select----------"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function handle(event){
	if(event.keyCode==13){
		var stuId=$("#searchvalue").val().trim();
		window.location.href="menuslist.html?method=feeCollection&stuId="+stuId;
	}
}

function pagination(list) {
	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr').length;
   
    var number_of_pages = Math.ceil(number_of_items / show_per_page);
    
    $('.pagination').empty();
    $('.pagination').append('<div class=controls></div><input id=current_page type=hidden><input id=show_per_page type=hidden>');
    $('#current_page').val(0);
    $('#show_per_page').val(show_per_page);

    var navigation_html = '<a class="prev" onclick="previous()">Prev</a>';
    var current_link = 0;
    while (number_of_pages > current_link) {
        navigation_html += '<a class="page" onclick="go_to_page(' + current_link + ')" longdesc="' + current_link + '">' + (current_link + 1) + '</a>';
    	 current_link++;
    }
    navigation_html += '&nbsp;&nbsp;&nbsp;<a class="next" onclick="next()">Next</a>';

    $('.controls').html(navigation_html);
    $('.controls .page:first').addClass('active');
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();
   
    $('#allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');
}

function go_to_page(page_num) {
    var show_per_page = parseInt($('#show_per_page').val(), 0);

    start_from = page_num * show_per_page;

    end_on = start_from + show_per_page;
    $(".controls").find(".page").hide();
    $(".controls").find(".active").show();
    $(".controls").find(".active").prev().prev().show();	
    $(".controls").find(".active").prev().show();	
    $(".controls").find(".active").next().show();	
    $(".controls").find(".active").next().next().show();	
    $('#allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

    $('.page[longdesc=' + page_num + ']').addClass('active').siblings('.active').removeClass('active');

    $('#current_page').val(page_num);
}

function previous() {

    new_page = parseInt($('#current_page').val(), 0) - 1;
    //if there is an item before the current active link run the function
    if ($('.active').prev('.page').length == true) {
        go_to_page(new_page);
    }
}
function next() {
    new_page = parseInt($('#current_page').val(), 0) + 1;
    //if there is an item after the current active link run the function
    if ($('.active').next('.page').length == true) {
        go_to_page(new_page);
    }
}
function getStudentListByJs(locationId,academicYear,classId,divisionId,searchTerm){
	datalist={
			"locationId" : locationId,
			"academicYear" : academicYear,
			"classId" : classId,
			"divisionId" : divisionId,
			"searchTerm" : searchTerm
	},
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=studentListByJs",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			$('#allstudent tbody').empty();
			var i;
			var leng=result.studentdetailslist.length;
			if(leng>0){
				for( i=0;i<leng;i++){
					var rel=result.studentdetailslist[i];
					$('#allstudent tbody').append("<tr class='"+rel.studentId+" "+rel.academicYearId+" "+rel.locationId+"'>" +
							"<td>"+(i+1)+"</td>" +
							"<td>"+rel.academicYear+"</td>" +
							"<td>"+rel.studentAdmissionNo+"</td>" +
							"<td>"+rel.studentFullName+"</td>" +
							"<td>"+rel.classSectionId+"</td>" +
							"<td><img src='"+rel.image+"' width='40' height='40' /></td>" +
							"<td>"+rel.status+"</td>" +
							"</tr>");
				}
			}
			else{
				$('#allstudent tbody').append("<tr><td colspan='7'>NO Records Found</td></tr>");
			}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.studentdetailslist.length);
				pagination(100);
				$("#ShowPerPage").change(function(){
					pagination($(this).val());	
				});
			
			$("#allstudent tbody tr").click(function(){
				var studentIds=$(this).attr("class").split(" ");
				var studentId=studentIds[0];
				var academicYear=studentIds[1];
				var location=studentIds[2];
				window.location.href = "feecollection.html?method=feeCollectionStudentWise&student="+studentId+
				"&loc_id="+location+"&academicYear="+academicYear+"&historylocId="+$("#locationname").val()+
				"&historyacademicId="+$("#Acyearid").val()+"&historyclassname="+$("#classname").val()+
				"&historysectionid="+$("#sectionid").val()+"&historysearch="+$("#searchvalue").val();
			});
		}
	});
}

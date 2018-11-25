$(document).ready(function(){	
	
	$("#location").val($("#hiddenlocId").val());

	$(".class").click(function(){
		$("#classOne").slideToggle();
	});
	
	var year =$("#hiddenlocationid").val();
	$("#location").val(year);
	
	var hacademicyear=$('#hiddenAcademicYear').val();
	$("#accyear").val(hacademicyear);
	
	$("#status").change(function(){
		searchList();
	});
	
	getStudentsDetails($("#location"),$("#accyear"));
	getclass();
	$("#Reset").click(function(){
		$("#searchtext").val("");
		$("#classid").val("all");
		$("#section").val("all")
		$("#location").val("%%");
		$("#accyear").val($('#hiddenAcademicYear').val());
		$("#status").val("active"); 
		$("#findcerti").val("");
		
		getStudentsDetails($("#location"),$("#accyear"));
	});
	$("#location").change(function(){
		getclass();
		classid=$("#classid").val();
		
		getSectionList(classid);
		getStudentsDetails($("#location"),$("#accyear"));
	});
	
	$("#accyear").change(function(){
		getclass();
		classid=$("#classid").val();
		if(classid == "all"){
			classid ="%%";
		}
		getSectionList(classid);
		getStudentsDetails($("#location"),$("#accyear"));
	});
	
	$("#classid").change(function(){
		classid=$("#classid").val();
		if(classid == "all"){
			classid ="%%";
		}
		getSectionList(classid);
		getStudentsByClass($("#location"),$("#accyear"),classid);
	});
	
	$("#section").change(function(){
		$("#searchvalue").val("");
		locationid=$("#location").val();
		accyear=$("#accyear").val();
		classname=$("#classid").val();
		sectionid=$("#section").val();
		if(classid == "all"){
			classid ="%%";
		}
		if(sectionid == "all"){
			sectionid ="%%";
		}
		getStudentListBySection(locationid,accyear,classname,sectionid);
	});
	
	
	$("#search").click(function(){
		
		var searchtext = $("#searchtext").val();
		if(searchtext == "" || searchtext == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Search");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else{
			searchList();
		}
	});
	
	$("#searchtext").keypress(function(e) {
	    if(e.which == 13) {
	    	var searchtext = $("#searchtext").val();
			if(searchtext == "" || searchtext == undefined){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Search ");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
			}
			else{
				searchList();
			}
	    }
	});
	
	$("#searchtext").keypress(function(e) {
		var key = e.which;
		if(key==13){
			 searchList();
		}
		
	});
	
	$("#findcertificate").click(function(){
		
		var accyear=$("#accyear").val();   
		var seletion = $('#findcerti option:selected').val();
		var stuid = $('input[name=stuid]:checked').val();
		
		if(locationid == "%%"){
			locationid = "%%";
		}
		
		if(stuid =="" || stuid == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(seletion == "" || seletion == undefined ){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Certificate Type");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		else if(seletion == "age"){
			var classid = $('input[name=stuid]:checked').closest("tr").find(".classid").attr("class").split(" ")[0];
			var sectionid = $('input[name=stuid]:checked').closest("tr").find(".sectionid").attr("class").split(" ")[0];
			var locationid =  $('input[name=stuid]:checked').closest("tr").find(".locid").attr("class").split(" ")[0];
			
			window.location.href ="studentcertificate.html?method=ageCertificate&locationid="+locationid
			+"&accyear="+accyear+"&classid="+classid+"&sectionid="+sectionid+"&stuid="+stuid+
			"&historylocation="+$("#location").val()+"&historyclassid="+$("#classid").val()+"&historysection="+$("#section").val()
			+"&historysearchtext="+$("#searchtext").val()+"&historystatus="+$("#status").val()+"&historyfindcerti="+$("#findcerti").val();
		}
		else if(seletion == "bonafide"){
			var classid = $('input[name=stuid]:checked').closest("tr").find(".classid").attr("class").split(" ")[0];
			var sectionid = $('input[name=stuid]:checked').closest("tr").find(".sectionid").attr("class").split(" ")[0];
			var locationid =  $('input[name=stuid]:checked').closest("tr").find(".locid").attr("class").split(" ")[0]
			window.location.href ="studentcertificate.html?method=bonafideCertificate&locationid="+locationid
			+"&accyear="+accyear+"&classid="+classid+"&sectionid="+sectionid+"&stuid="+stuid+
			"&historylocation="+$("#location").val()+"&historyclassid="+$("#classid").val()+"&historysection="+$("#section").val()
			+"&historysearchtext="+$("#searchtext").val()+"&historystatus="+$("#status").val()+"&historyfindcerti="+$("#findcerti").val();
		}
		else if(seletion == "conduct"){
			var classid = $('input[name=stuid]:checked').closest("tr").find(".classid").attr("class").split(" ")[0];
			var sectionid = $('input[name=stuid]:checked').closest("tr").find(".sectionid").attr("class").split(" ")[0];
			var locationid =  $('input[name=stuid]:checked').closest("tr").find(".locid").attr("class").split(" ")[0]
			window.location.href ="studentcertificate.html?method=conductCertificate&locationid="+locationid
			+"&accyear="+accyear+"&classid="+classid+"&sectionid="+sectionid+"&stuid="+stuid+
			"&historylocation="+$("#location").val()+"&historyclassid="+$("#classid").val()+"&historysection="+$("#section").val()
			+"&historysearchtext="+$("#searchtext").val()+"&historystatus="+$("#status").val()+"&historyfindcerti="+$("#findcerti").val();
		}
		else if(seletion == "course"){
			var classid = $('input[name=stuid]:checked').closest("tr").find(".classid").attr("class").split(" ")[0];
			var sectionid = $('input[name=stuid]:checked').closest("tr").find(".sectionid").attr("class").split(" ")[0];
			var locationid =  $('input[name=stuid]:checked').closest("tr").find(".locid").attr("class").split(" ")[0]
			window.location.href ="studentcertificate.html?method=course_conductCertificate&locationid="+locationid
			+"&accyear="+accyear+"&classid="+classid+"&sectionid="+sectionid+"&stuid="+stuid+
			"&historylocation="+$("#location").val()+"&historyclassid="+$("#classid").val()+"&historysection="+$("#section").val()
			+"&historysearchtext="+$("#searchtext").val()+"&historystatus="+$("#status").val()+"&historyfindcerti="+$("#findcerti").val();
		}
		else if(seletion == "visa"){
			var classid = $('input[name=stuid]:checked').closest("tr").find(".classid").attr("class").split(" ")[0];
			var sectionid = $('input[name=stuid]:checked').closest("tr").find(".sectionid").attr("class").split(" ")[0];
			var locationid =  $('input[name=stuid]:checked').closest("tr").find(".locid").attr("class").split(" ")[0]
			window.location.href ="studentcertificate.html?method=studentVisaCertificate&locationid="+locationid
			+"&accyear="+accyear+"&classid="+classid+"&sectionid="+sectionid+"&stuid="+stuid+
			"&historylocation="+$("#location").val()+"&historyclassid="+$("#classid").val()+"&historysection="+$("#section").val()
			+"&historysearchtext="+$("#searchtext").val()+"&historystatus="+$("#status").val()+"&historyfindcerti="+$("#findcerti").val();
		}
	})
	
	if($("#historyaccyear").val()!=""){
		$("#accyear").val($("#historyaccyear").val());
	}
	$("#location").val($("#historylocation").val());
	getclass();
	$("#classid").val($("#historyclassid").val());
	getSectionList($("#classid").val());
	$("#section").val($("#historysection").val());
	$("#searchtext").val($("#historysearchtext").val());
	$("#status").val($("#historystatus").val());
	$("#findcerti").val($("#historyfindcerti").val());
	searchList();
	$("#location").val($("#hiddenlocId").val());
	
	pagination(100);
	
});

function getStudentsDetails(pointer1,pointer2){
    var status=$("#status").val();
	
	$.ajax({
			type : "POST",
			url : "studentcertificate.html?method=getStudentsDetails",
			data : {"location":pointer1.val(),"acyear":pointer2.val(),"status":status},
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(data){
				var result = $.parseJSON(data);
				
				$("#studenttable").empty();
				$("#studenttable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th>Sl.No</th>" +
						"<th>Select</th>" +
						"<th>Admission No</th>" +
						"<th>Student Name</th>" +
						"<th>Roll No</th>"+
						"<th>Class</th>"+
						"<th>Division</th>"+
						"</tr></thead>" +
						"<tbody></tbody>"+
						"</table>"
						);
				
				
				if(result.stuList.length>0){
					
				for(var i=0;i<result.stuList.length;i++){
					
					$("#studenttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.stuList[i].slno+"</td>"+
							"<td class='select'>"+"<input type='radio' name='stuid' value='"+result.stuList[i].stuid+"'/>"+"</td>"+
							"<td class='admissionno'>"+result.stuList[i].admissionno+"</td>"+
							"<td class='"+result.stuList[i].locid+" locid'>"+result.stuList[i].stuname+"</td>"+
							"<td>"+result.stuList[i].rollno+"</td>"+
							"<td class='"+result.stuList[i].classid+" classid'>"+result.stuList[i].classname+"</td>"+
							"<td class='"+result.stuList[i].sectionid+" sectionid'>"+result.stuList[i].sectionname+"</td>"
							+"</tr>"
					);
				}
				
				}
				else{
					$("#studenttable #allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				}
				$("#loder").hide();
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.stuList.length);
				/*$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem'></span></div><div class='pagination pagelinks'></div>");*/
				
				pagination(100);
				/*$("#show_per_page").change(function(){
					pagination($(this).val());
				});*/
				
			}
		});
}

function getStudentsByClass(pointer1,pointer2,classid){
	var status=$("#status").val();
	$.ajax({
			type : "POST",
			url : "studentcertificate.html?method=getStudentsDetailsByClass",
			data : {"location":pointer1.val(),"acyear":pointer2.val(),"classid":classid,"status":status},
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(data){
				var result = $.parseJSON(data);
				
				$("#studenttable").empty();
				$("#studenttable").append("<table class='table' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th>Sl.No</th>" +
						"<th>Select</th>" +
						"<th>Admission No</th>" +
						"<th>Student Name</th>" +
						"<th>Roll No</th>"+
						"<th>Class</th>"+
						"<th>Division</th>"+
						"</tr></thead>" +
						"<tbody></tbody>" +
						"</table>"
						);
				if(result.getClassWiseList.length>0){
				for(var i=0;i<result.getClassWiseList.length;i++){
					
					$("#studenttable #allstudent tbody").append(
							"<tr>"+
							"<td>"+result.getClassWiseList[i].count+"</td>"+
							"<td class='select'>"+"<input type='radio' name='stuid' value='"+result.getClassWiseList[i].studentId+"'/>"+"</td>"+
							"<td class='admissionno'>"+result.getClassWiseList[i].studentAdmissionNo+"</td>"+
							"<td class='"+result.getClassWiseList[i].locationId+" locid'>"+result.getClassWiseList[i].studentnamelabel+"</td>"+
							"<td>"+result.getClassWiseList[i].studentrollno+"</td>"+
							"<td class='"+classid+" classid'>"+result.getClassWiseList[i].classname+"</td>"+
							"<td class='"+result.getClassWiseList[i].sectioncode+" sectionid'>"+result.getClassWiseList[i].sectionnaem+"</td>"
							+"</tr>"
					);
				}
				}
				else{
					$("#studenttable #allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				}
				$("#loder").hide();
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.getClassWiseList.length);
				/*$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem'></span></div><div class='pagination pagelinks'></div>");*/
				pagination(100);
				/*$("#show_per_page").change(function(){
					pagination($(this).val());
				});*/
			}
		});
}

function getclass(){
	var loc=$("#location").val();
	$.ajax({
		
		type : "POST",
		url : "studentcertificate.html?method=getClass",
		data : {"location":loc},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$('#classid').html("");

			$('#classid').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.classList.length; j++) {

				$('#classid').append('<option value="'
						+ result.classList[j].classId + '">'
						+ result.classList[j].classname
						+ '</option>');
			}
		}
	});
}

function getSectionList(classname){
	datalist={
		"classidVal" : classname,
		"locationId":$("#location").val()
},

$.ajax({
	
	type : 'POST',
	url : "studentRegistration.html?method=getClassSection",
	data : datalist,
	async : false,
	success : function(response) {
		
		var result = $.parseJSON(response);
		
		$('#section').html("");
		
		$('#section').append('<option value="">' + "ALL"	+ '</option>');
		
		for ( var j = 0; j < result.sectionList.length; j++) {

			$('#section').append('<option value="' + result.sectionList[j].sectioncode
					+ '">' + result.sectionList[j].sectionnaem
					+ '</option>');
		}
	}
});
}

function getStudentListBySection(locationid,accyear,classname,sectionid){
	var status=$("#status").val();
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"status":status
		}, $.ajax({
			type : 'POST',
			url : "studentcertificate.html?method=getStudentListBySection",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#studenttable").empty();
					$("#studenttable").append("<table class='table' name='stuid' id='allstudent' width='100%'>"+"<thead><tr>"+
							"<th>Sl.No</th>" +
							"<th>Select</th>" +
							"<th>Admission No</th>" +
							"<th>Student Name</th>" +
							"<th>Roll No</th>"+
							"<th>Class</th>"+
							"<th>Division</th>"+
							"</tr></thead>" +
							"<tbody></tbody>"+
							"</table>"
							);
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getSectionWiseList[i].locationId+"'>"+result.getSectionWiseList[i].count+"</td>" 
							+"<td class='select'>"+"<input type='radio' name='stuid' value='"+result.getSectionWiseList[i].studentId+"'/>"+"</td>"
							+"<td>"+result.getSectionWiseList[i].studentAdmissionNo+"</td>"
							+"<td class='"+result.getSectionWiseList[i].locationId+" locid'>"+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td>"+result.getSectionWiseList[i].studentrollno+"</td>"
							+"<td class='"+result.getSectionWiseList[i].classDetailId+" classid'>"+result.getSectionWiseList[i].classname+"</td>"
							+"<td class='"+result.getSectionWiseList[i].sectioncode+" sectionid'>"+result.getSectionWiseList[i].sectionnaem+"</td>"
							+"</tr>");
					}	
		      	}
		
		       else{
		          	$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
	               	}
					$("#loder").hide();
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.getSectionWiseList.length);
					/*$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span class='numberOfItem'></span></div><div class='pagination pagelinks'></div>");*/
					pagination(100);
				/*	$("#show_per_page").change(function(){
						pagination($(this).val());
					});
					*/
			}
		});
	}

function searchList(){
     var status=$("#status").val();
	 searchname = $("#searchtext").val().trim();
	 locationid=$("#location").val();
	 accyear=$("#accyear").val();
	 classname=$("#classid").val();
	 sectionid=$("#section").val();
	
datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
			"status":status,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentSearchByList",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
				$("#studenttable").empty();
				$("#studenttable").append("<table class='table' name='stuid' id='allstudent' width='100%'>"+"<thead><tr>"+
						"<th>Sl.No</th>" +
						"<th>Select</th>" +
						"<th>Admission No</th>" +
						"<th>Student Name</th>" +
						"<th>Roll No</th>"+
						"<th>Class</th>"+
						"<th>Division</th>"+
						"</tr></thead>" +
						"<tbody></tbody>"+
						"</table>"
						);
				if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
							+"<td class='select'>"+"<input type='radio' name='stuid' value='"+result.SearchList[i].studentId+"'/>"+"</td>"
							+"<td> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td class='"+result.SearchList[i].locationId+" locid'> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td class='"+result.SearchList[i].classDetailId+" classid'>"+result.SearchList[i].classname+"</td>"
							+"<td class='"+result.SearchList[i].sectioncode+" sectionid'>"+result.SearchList[i].sectionnaem+"</td>"
							+"</tr>");
					}
				 }
				else{
					$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
				}
				
				
					/*$("#allstudent tbody tr").click(function(){
						
						var student_Id = $( this ).find(".studentid").attr("class").split(" ");
						window.location.href="menuslist.html?method=AddStudentConfidentialReport&studentId="+student_Id[0]+"&accyear="+student_Id[1]+"&locationId="+student_Id[2];
				});*/
				$("#loder").hide();
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.SearchList.length);
					/*$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select><span calss='numberOfItem'></span></div><div class='pagination pagelinks'></div>");*/
					pagination(100);
				/*	$("#show_per_page").change(function(){
						pagination($(this).val());
					});*/
			}
		});
}


function pagination(list) {

	var show_per_page = list;
    var number_of_items = $('#allstudent tbody tr,.allstudent tbody tr').length;
   
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
   
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none');
    $('#allstudent tbody tr,.allstudent tbody tr').slice(0, show_per_page).css('display', 'table-row');
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
    $('#allstudent tbody tr,.allstudent tbody tr').css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

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


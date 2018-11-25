$(document).ready(function(){	
	
	$("#allstudent tbody tr").click(function(e) {
		e.preventDefault();
		$('html, body').animate({
			scrollTop : 0
		}, 800);
		return false;
	});
	
	getissuedList();     
	
	$("#back1").click(function(){
		window.location.href ="studentcertificate.html?method=studentCertificates&historyaccyear="+$("#historyaccyear").val()
		+"&historylocation="+$("#historylocation").val()+"&historyclassid="+$("#historyclassid").val()+"&historysection="+$("#historysection").val()
		+"&historysearchtext="+$("#historysearchtext").val()+"&historystatus="+$("#historystatus").val()+"&historyfindcerti="+$("#historyfindcerti").val();
	});
	
	$("#rst").hide();
	$("#rst").click(function(){
		window.location.href ="studentcertificate.html?method=bonafideCertificate&locationid="+ $("#hiddenlocationid").val()
		+"&accyear="+$("#hiddenaccyearid").val()+"&classid="+$("#hiddenclassid").val()+"&sectionid="+$("#hiddensectionid").val()+"&stuid="+$("#hiddenstuid").val();
	});
		 $("#print").click(function () {
			 singleprint();
	     });
		 
		 $("#allstudent tbody tr").click(function(){
			
			 agecetiid = $(this).find(".ageid").attr("id");
				displaytabledata(agecetiid);
			});
		 

	$("#print").hide();
	$("#save").click(function(){

		datalist ={
				"accyear" :$("#hiddenaccyearid").val(),
				"locid" : $("#hiddenlocationid").val(),
				"classid" :$("#hiddenclassid").val(),
				"sectionid" :$("#hiddensectionid").val(),
				"stuname":$("#hiddenstuid").val(),
				"admissionno" :$("#admissionno").val(),
				"fathername" :$("#fathername").val(),
				"dob" : $("#dob").val(),
				"purpose":$("#purpose").val(),
				"otherinfo":$("#otherinfo").val(),
				"studentstatus":$("#studentstatus").val(),
				"motherName":$("#motherName").val(),
				"dobwords" :$("#dobwords").val()
		};
		
		if($("#purpose").val().trim() == "" || $("#purpose").val() == undefined){
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required - Purpose*");
			document.getElementById("purpose").style.border = "1px solid #AF2C2C";
			document.getElementById("purpose").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
		}
		else{
			 $("#dialog").dialog("open");
		}
	});
		$("#dialog").dialog({
			autoOpen  : false,
		    modal     : true,
		    title     : "Bonafied Certificate Details",
		    buttons   : {
		    	"Save": {
		    		text: 'Yes',
		    		 click: function() {
		    			 	$("#save").hide();
		    				$("#print").show();
		    				$('input[type=text]').attr('readonly','true');
		    				document.getElementById("purpose").style.backgroundColor = "#FFF";
		    				document.getElementById("otherinfo").style.backgroundColor = "#FFF";
		    			 datalist ={
		    						"accyear" :$("#hiddenaccyearid").val(),
		    						"locid" : $("#hiddenlocationid").val(),
		    						"classid" :$("#hiddenclassid").val(),
		    						"sectionid" :$("#hiddensectionid").val(),
		    						"stuname":$("#hiddenstuid").val(),
		    						"admissionno" :$("#admissionno").val(),
		    						"fathername" :$("#fathername").val(),
		    						"dob" : $("#dob").val(),
		    						"purpose":$("#purpose").val(),
		    						"otherinfo":$("#otherinfo").val(),
		    						"studentstatus":$("#studentstatus").val(),
		    						"motherName":$("#motherName").val(),
		    						"dobwords" :$("#dobwords").val()
		    				};
		    			 $.ajax({
		    					type : "POST",
		    					url : "studentcertificate.html?method=saveBonafiedCertificateData",
		    					data : datalist,
		    					async : false,
		    					success : function(data){
		    						var result = $.parseJSON(data);
		    						if(result.status == "true"){
		    							$(".successmessagediv").show();
		    							$(".validateTips").text("Record Saved Successfully...");
		    							setTimeout(function() {
		    								$('.successmessagediv').fadeOut();
		    								$.ajax({
		    									type : "POST",
		    									url : "studentcertificate.html?method=getbonafiedissueddetails",
		    									data : datalist,
		    									async : false,
		    									success : function(data){
		    										var result = $.parseJSON(data);
		    										$("#issued").show();
		    										$("#heading").show();
		    										$("#certificatetable").empty();
		    										$("#certificatetable").append("<table class='table' id='allstudent' width='100%'" +">"
		    												+"<center><tr><th>SI No</th>"+
		    												"<th>Entry Date</th>" +
		    												"<th>Purpose</th>" +
		    												"</center></tr>" +
		    												"</table>"
		    										);
		    										for(var i=0;i<result.stuList.length;i++){
		    											$("#allstudent tbody").append("<tr>"
		    													+"<td class='ageid' id='"+result.stuList[i].agecertid+"'>"+result.stuList[i].slno+"</td>" 
		    													+"<td>"+result.stuList[i].entryDate+"</td>"
		    													+"<td>"+result.stuList[i].purpose+"</td>"
		    													+"</tr>");
		    											}	
		    										/*pagination(50);
		    										$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
		    										$("#show_per_page").change(function(){
		    											pagination($(this).val());
		    										});*/
		    									}
		    								});
		    								 $("#allstudent tbody tr").click(function(){
		    									  agecetiid = $(this).find(".ageid").attr("id");
		    										displaytabledata(agecetiid)
		    									});
		    								 $("#allstudent tbody tr").click(function(e) {
		    										e.preventDefault();
		    										$('html, body').animate({
		    											scrollTop : 0
		    										}, 800);
		    										return false;
		    									});
		    							}, 2500);
		    						}
		    					}
		    				});
		    			 $(this).dialog('close');
		    		 }
		    	},
		    	'No' : function() {
	                $(this).dialog('close');
		    	}
		    	}
		    });
});

function getissuedList(){
	
	datalist ={
			"accyear" :$("#hiddenaccyearid").val(),
			"locid" : $("#hiddenlocationid").val(),
			"classid" :$("#hiddenclassid").val(),
			"sectionid" :$("#hiddensectionid").val(),
			"stuname":$("#hiddenstuid").val()
	}
	$.ajax({
		type : "POST",
		url : "studentcertificate.html?method=getbonafiedissueddetails",
		data : datalist,
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			$("#certificatetable").empty();
			
			if(result.stuList.length == 0){
				$("#issued").hide();
				$("#heading").hide();
			}
			else{
				
			$("#certificatetable").append("<table class='table' id='allstudent' width='100%'" +">"
					+"<center><tr><th>SI No</th>"+
					"<th>Entry Date</th>" +
					"<th>Purpose</th>" +
					"</center></tr>" +
					"</table>"
			);
			
			for(var i=0;i<result.stuList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class = 'ageid' id='"+result.stuList[i].agecertid+"'>"+result.stuList[i].slno+"</td>" 
						+"<td>"+result.stuList[i].entryDate+"</td>"
						+"<td>"+result.stuList[i].purpose+"</td>"
						+"</tr>");
				}	
			$("#allstudent tbody tr").click(function(e) {
				e.preventDefault();
				$('html, body').animate({
					scrollTop : 0
				}, 800);
				return false;
			});
			/*pagination(50);
			$("#allstudent").after("<div class='pagebanner'><select id='show_per_page'><option value='50'>50</option><option value='100'>100</option><option value='200'>200</option><option value='300'>300</option><option value='400'>400</option><option value='500'>500</option></select></div><div class='pagination pagelinks'></div>")
			$("#show_per_page").change(function(){
				pagination($(this).val());
			});*/
			
		}
		}
	});
}

function displaytabledata(agecetiid){

	var stuid = $("#hiddenstuid").val();
	var selection = "bonafied";
	$("#save").hide();
	$("#print").show();
	$("#rst").show();
	datalist = {
			"stuid" : $("#hiddenstuid").val(),
			"agecetiid" : agecetiid,
			"selection":selection
	};
	$('input[type=text]').attr('readonly','true');
	document.getElementById("purpose").style.backgroundColor = "#FFF";
	document.getElementById("otherinfo").style.backgroundColor = "#FFF";
	$.ajax({
		
			type : "POST",
			url : "studentcertificate.html?method=displayageissueddetails",
			data : datalist,
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
					
				
				for(i=0;i<result.stuList.length;i++){
					$("#admissionno").val(result.stuList[i].admissionno);
					$("#accyearname").val(result.stuList[i].accyear);
					$("#schoolName").val(result.stuList[i].locname);
					$("#stuname").val(result.stuList[i].stuname);
					$("#purpose").val(result.stuList[i].purpose);
					$("#classname").val(result.stuList[i].classname);
					$("#fathername").val(result.stuList[i].fathername);
					$("#dob").val(result.stuList[i].dob);
					$("#otherinfo").val(result.stuList[i].otherinfo);
					$("#studentstatus").val(result.stuList[i].studentstatus);
					$("#sectionname").val(result.stuList[i].sectionname);		
					$("#motherName").val(result.stuList[i].motherName);		
					$("#dobwords").val();		
				}
				$("#allstudent tbody tr").click(function(e) {
					e.preventDefault();
					$('html, body').animate({
						scrollTop : 0
					}, 800);
					return false;
				});
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

function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function singleprint(){			
	
	var stuname = $("#stuname").val();
	var fathername = $("#fathername").val();
	var mothername = $("#motherName").val();
	var accyear = $("#accyearname").val();
	
	var addmission = $("#admissionno").val();
	var classname= $("#classname").val()+" - "+$("#sectionname").val();
	var affilno=$("#hiddencustSchoolaffilno").val();
	var schlogo=$("#hiddencustSchoollogo").val();
	var emailId=$("#hiddencustSchoolEmail").val();
	var schnam=$("#schoolName").val();
	var adress=$("#hiddencustSchoolAddres").val();
	var schcode=$("#hiddencustSchoolno").val();
	var boardlogo=$("#custSchoolboardlogo").val();
	var website=$("#hiddencustSchoolwebsit").val();
	var add=$("#hiddenaddress").val();
	var currentdate=$("#hiddencurrentdate").val();
	var dobwords=$("#dob").val();
	var schoolLogo= $("#schoolLogo").val();
	
	var schooName = $("#schName").val();
	var pincode=$("#pincode").val();
	var fatherName=$("#fatherName").val();
	
var gender= $("#hiddenstugender").val();
	
	if(gender == "Male"){
		heading = "Kumar";
		rel="son"
	}else{
		heading = "Kumari";
		rel="daughter"
	}
	
	
var frame1 = $('<iframe />');
frame1[0].name = "frame1";
frame1.css({ "position": "absolute", "top": "-1000000px" });
$("body").append(frame1);
var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
frameDoc.document.open();
//Create a new HTML document.
frameDoc.document.write('<html><head><title>...</title>');
//Append the external CSS file.
frameDoc.document.write('<link rel="stylesheet" href="JQUERY/development-bundle/themes/base/jquery.ui.all.css" />');
frameDoc.document.write('<link href="CSS/newUI/bootstrap.min.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/newUI/modern-business.css" rel="stylesheet">');
frameDoc.document.write('<link href="CSS/Certificates/ageCertificate.css" rel="stylesheet">');
frameDoc.document.write('</head><body>');
//Append the DIV contents.
frameDoc.document.write('<div class="container"><div style="margin-left: 10px;margin-right: 10px;margin-top: 10px;margin-bottom: 10px;"><div class="row"><div col-md-12><div class="col-md-3"></div><div col-md-6><div style="margin:auto;">'+
		'<table class="table"  id="bonafied"style="border:none;">' +
		'<tr><td><img src="'+schoolLogo+'" width="100" height="60"></img></td><td><p style="font-size: 29px;font-weight:bold;text-align:center;">'+schooName+'</p><p style="font-size: 20px;font-weight: bold;text-align:center;"><i>'+adress+'</i></p></td></tr></table><p style="font-size: 20px;font-weight: bold;text-align:center;margin-top: -5%;"></p>'+'<hr style="margin-top: 0px; margin-bottom: 0px; border-color: black"><p style="text-align: center;font-size:25px;font-weight: bold;margin-bottom: -5px "><i>BONAFIED CERTIFICATE</i></p>'+
		'<hr style="margin-top: 0px; margin-bottom: 0px; border-color: black;"><div style="margin-left: 11%; margin-top: 10px;">&nbsp;&nbsp;&nbsp;'+
		'<div margin-left:-50px; margin-right:-40px;><table width="100%" id="Certificate" style="font-size:10px;"><tr><td colspan="2"> <p style="font-size:25px!important;">This is to certify that&nbsp<i>&nbsp;&nbsp;'+stuname+'</i>&nbsp&nbsp&nbsp<div style="width:65%;border-bottom: 1px dashed;display: block; margin-left:35%; margin-top: -10px;"></div></p></td></tr><tr><td colspan="2"><p style="margin-left:-50px;font-size:25px!important;">Son/Daughter of&nbsp&nbsp&nbsp<i>&nbsp;&nbsp;&nbsp;&nbsp;'+fathername+'</i><div style="width:78%;border-bottom: 1px dashed;display: block; margin-left:22%; margin-top: -10px;"></div></p></td></tr><tr><td colspan="2"></td></tr><tr><td colspan="2"><p style="margin-left:-50px; font-size:25px!important;">residing at &nbsp<i>&nbsp;'+add+'</i><div style="width:90%;border-bottom: 1px dashed;display: block; margin-left:12%; margin-top: -10px;"></div></p></td></tr>'+
		'<tr><td colspan="2"><p style="margin-left:-50px;font-size:25px!important;">Is bonafide student of our school.</p></td></tr>'+
		'<tr><td colspan="2"><p style="margin-left:-50px;font-size:25px!important;">HE/SHE is /was very obedient, Sincere and Hardworking.</p></td></tr>'+
		'<tr><td><p style="margin-left:-50px;font-size:25px!important;">HE/SHE bears a good moral character. HIS/HER Date of Birth is &nbsp<i>&nbsp;&nbsp;'+dobwords+'</i><div style="width:26%;border-bottom: 1px dashed;display: block; margin-left:4%; margin-top: -10px;"></div></p></td></tr>'+
		'<table width="100%" id="spacesize"><tr><th></th></tr></br><tr><th></th></tr></br><tr><th></th></tr></br><tr><th></th></tr></br><tr><th style="width: 50%;font-size:25px!important;"><p style="margin-left:-50px;">Place :</p></th><th style="width: 60%;text-align:center;font-size:25px!important;">Principal</th></tr></table>'+
		'</div></div></div><div class="col-md-3"></div></div></div></div>');
frameDoc.document.write('</body></html>');
frameDoc.document.close();
setTimeout(function () {
  window.frames["frame1"].focus();
  window.frames["frame1"].print();
  frame1.remove();
}, 100);
}
$(document).ready(function(){
	var hacademicyear=$('#hiddenAcademicYear').val();
	$("#Acyearid option[value="+hacademicyear+"]").attr('selected', 'true');
	$("#Acyearid1 option[value="+hacademicyear+"]").attr('selected', 'true');
	

	$("#locationname").val($("#hiddenlocId").val());
	$("#locationname1").val($("#hiddenlocId").val());
	
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var sortingby=$("#sortingby").val();
	var orderby=$("input[name='sorting']:checked").val();
	getClassList();
	appendpagination($("#stupagination"))

	//getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	
	$("#status").change(function(){
		$("#selectall").prop("checked",false);
		$(".select").prop("checked",false);
		
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid1").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
});
	
	$('#Generated').click(function(){
		
			 $("#generatetc").hide();
			 $("#download").show();
			 $("#TCCancel").show();
		
			$("#selectall").prop("checked",false);
			$(".select").prop("checked",false);
			
			var locationid=$("#locationname1").val();
			var accyear=$("#Acyearid1").val();
			var classname=$("#classname1").val();
			var sectionid=$("#sectionid1").val();
			var sortingby=$("#sortingby1").val();
			var orderby=$("input[name='sorting2']:checked").val();
			
			if(sortingby == "Gender"){
				orderby=$("input[name='sorting3']:checked").val();
			}
			 $('#notGeneratedDiv').removeClass("active");
				$('#GeneratedDiv').addClass("active");
				$('#GeneratedDiv').css({
					'display':'block !important'
				});
			getClassList1();
			$("#allstudent tbody").empty();
			$("#stupagination").empty();
			appendtable();
			studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
	$("#download").click(function(){
	
		var location = $("#locationname1").val();
		list1=[];
		list2=[];
		list3=[];
		list4=[];
		var cnt = 0;
		var len =$(".select1:checked").length;
		$('input[type="checkbox"]:checked').map(function() {
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			var adm =$(this).attr("class").split(" ")[3];
			var cls =$(this).attr("class").split(" ")[4];
			
			list1.push(acy);
			list2.push(stuid);
			list3.push(adm);
			list4.push(cls);
			cnt++;
		});
		if($("#locationname1").val()=="all"){
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Branch Name");
			  document.getElementById("locationname1").style.border = "1px solid #AF2C2C";
				document.getElementById("locationname1").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		}
		else if (len == 0 || len < 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record for TC Generation");
			return false;
		} else{
			 
			window.location.href='menuslist.html?method=TransferCertificateDownload&locationId='+location+'&accyear='+list1.toString()
			+'&studentid='+list2.toString()+'&admid='+list3.toString()+'&classid='+list4.toString()+'&locName='+$("#locationname1 option:selected").text();
		
			/*window.location.href='menuslist.html?method=TransferCertificateDownload&locationId='+list.toString()+'&accyear='+list1.toString()
			+'&studentid='+list2.toString()+'&admid='+list3.toString()+'&classid='+list4.toString()+'&locName='+$("#locationname option:selected").text();*/
			/*	datalist = {			
				"locationId" :list.toString(),
				"accyear" :list1.toString(),
				"studentid" :list2.toString(),
				"admid" :list3.toString(),
				"classid" :list4.toString(),
			}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=TransferCertificateDownload",
			data :  datalist,
			async : false,
			
			success : function(response) {
				var result = $.parseJSON(response);
				$("#dialog").dialog("close");
				$('.errormessagediv').hide();
				$(".successmessagediv").show();
				$(".successmessage").text("TC Downloaded Successfully"+"  "+"And Downloaded In 'D:EcampusPro_TC_Dir' Drive");
				
				window.location.href="menuslist.html?method=tcgeneration";
			}
		});*/
			
		}
	});

	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});
	
	$("#selectall1").change(function() {
		$(".select1").prop('checked', $(this).prop("checked"));
	});
	
	$('#download').click(function(){
	
		if($("#locationname1").val()=="all"){
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Branch Name");
			  document.getElementById("locationname1").style.border = "1px solid #AF2C2C";
				document.getElementById("locationname1").style.backgroundColor = "#FFF7F7";
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		}
		
		 if($('input[type=checkbox]:checked').length == 0) {
			  $(".errormessagediv").show();
			  $(".validateTips").text("Select Any One Record");
			  setTimeout(function() {$('.errormessagediv').fadeOut();}, 3000);
			 return false;
		 }
	});
	
	$(".select").change(function() {
		
		if($(".select").length == $(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
	
$(".select1").change(function() {
	if($(".select1").length == $(".select1:checked").length){
		$("#selectall1").prop("checked",true);
	}
	else{
		$("#selectall1").prop("checked",false);
	}
});


	$("#generatetc").click(function(){
		list=[];
		list1=[];
		list2=[];
		list3=[];
		list4=[];
		var cnt = 0;
		
		$('input[name="select"]:checked').map(function() {
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			var adm =$(this).attr("class").split(" ")[3];
			var cls =$(this).attr("class").split(" ")[4];	
			list.push(schnm);
			list1.push(acy);
			list2.push(stuid);
			list3.push(adm);
			list4.push(cls);
			cnt++;
		});
		
		if($("#locationname").val()=="all"||$("#locationname").val()==undefined){
			 $(".errormessagediv").show();
			  $(".validateTips").text("Select Branch Name");
			  document.getElementById("locationname").style.border = "1px solid #AF2C2C";
			  document.getElementById("locationname").style.backgroundColor = "#FFF7F7";
			  setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 3000);
				return false;
		}
		else if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			return false;
		} else{
			
			$("#dialog").dialog("open");
			
			$("#dialog").empty();
			$("#dialog").append('<label for="">School/Board Annual Examination<font color="red">*</font>:</label>');
			
			$("#dialog").append("<input type='text' class='form-control examdetails' id='examdetails' style='float:right;width:37.5%' onkeypress='HideError(this)' required readonly/><br>");
			
			$("#dialog").append('<label for="">Result<font color="red">*</font>:</label>');
			$("#dialog").append("<select id='result' name='result' class='form-control' required style='float:right;width:37.5%'  required>" +
					"<option value='pass'>Pass</option>" +
					"<option value='fail'>Fail</option>" +
							"</select><br>");

			
			$("#dialog").append('<label for="">Reason for leaving<font color="red">*</font>:</label>');
			$("#dialog").append("<input type='text' class='form-control reason' id='reason' style='float:right;width:37.5%' onkeypress='HideError(this)'/ ><br>");
			
			$("#dialog").append('<label for="">Date of Pupil last attendance in the school<font color="red">*</font>:</label>');
			$("#dialog").append("<input type='text' class='form-control meetingdate' id='meetingdate' style='float:right;width:37.5%' onkeypress='HideError(this)'/><br>");
			
			$("#dialog").append('<label for="">Number of school meetings upto date :</label>');
			$("#dialog").append("<input type='text' class='form-control noofmeeting' id='noofmeeting' style='float:right;width:37.5%' onkeypress='HideError(this)'/><br>");
			
			$("#dialog").append('<label for="">Number of school meetings pupil attended :</label>');
			$("#dialog").append("<input type='text' class='form-control meetattain' id='meetattain' style='float:right;width:37.5%' onkeypress='HideError(this)'/><br>");
			
			$("#dialog").append('<label for="">General Conduct:</label>');
			$("#dialog").append("<input type='text' class='form-control gencond' id='gencond' style='float:right;width:37.5%'/><br>");
			
			$("#dialog").append('<label for="">Number of times the pupil has failed in the present class :</label>');
			$("#dialog").append("<input type='text' class='form-control nooffail' id='nooffail' style='float:right;width:37.5%' onkeypress='HideError(this)'/><br>");
			
			$("#dialog").append('<label for="">Any other Remarks :</label>');
			$("#dialog").append("<input type='text' class='form-control remarks' id='remarks' style='float:right;width:37.5%'/><br>");
			
			$("#meetingdate").datepicker({
				dateFormat : "dd-mm-yy",
				maxDate : 0,
				changeMonth : true,
				changeYear : true,
			});
			
			$("#nooffail").keypress(function(e){
				if(e.which != 8 && e.which != 0 && (e.which < 45 || e.which > 57))
				{
					$(".errormessagediv").show();
					$(".validateTips").text("Enter Only Number.");
					document.getElementById("nooffail").style.border = "1px solid #AF2C2C";
					document.getElementById("nooffail").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			});
			
			
			$("#meetattain").keypress(function(e){
				if(e.which != 8 && e.which != 0 && (e.which < 45 || e.which > 57))
				{
					$(".errormessagediv").show();
					$(".validateTips").text("Enter Only Number.");
					document.getElementById("meetattain").style.border = "1px solid #AF2C2C";
					document.getElementById("meetattain").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			});
			
			$("#noofmeeting").keypress(function(e){
				if(e.which != 8 && e.which != 0 && (e.which < 45 || e.which > 57))
				{
					$(".errormessagediv").show();
					$(".validateTips").text("Enter Only Number.");
					document.getElementById("noofmeeting").style.border = "1px solid #AF2C2C";
					document.getElementById("noofmeeting").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
			});
			
		}
		$("#examdetails").val($("#hiddenboardname").val());
	});
	
	$("#dialog").dialog({
		autoOpen  : false,
		modal     : true,
	    width     : 650,
	    height    : 450,
		title:'Details for TC Generation',
		buttons : {
			"YES" : function(){
				
			
				var examdetails = $("#examdetails").val();
				var reason = $("#reason").val();
				var remarks = $("#remarks").val();
				var result = $("#result").val();
				var meetingdate=$("#meetingdate").val();
				var noofmeeting =$("#noofmeeting").val();
				var meetattain =$("#meetattain").val();
				var gencond = $("#gencond").val();
				var nooffail = $("#nooffail").val();
				if(examdetails == null || examdetails == undefined || examdetails.trim() ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Please Enter The School/Board Annual Examination*");
					document.getElementById("examdetails").style.border = "1px solid #AF2C2C";
					document.getElementById("examdetails").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if(reason == null || reason == undefined || reason.trim() ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Please Enter The Reason for leaving*");
					document.getElementById("reason").style.border = "1px solid #AF2C2C";
					document.getElementById("reason").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				else if(meetingdate == null || meetingdate == undefined || meetingdate.trim() ==""){
					$(".errormessagediv").show();
					$(".validateTips").text("Date of Pupil last attendance in the school*");
					document.getElementById("meetingdate").style.border = "1px solid #AF2C2C";
					document.getElementById("meetingdate").style.backgroundColor = "#FFF7F7";
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}
				
				else{
					generateTranferCertificate(list,list1,list2,list3,list4,examdetails,reason,remarks,result,meetingdate,noofmeeting,meetattain,gencond,nooffail);
				}
			},
			"No" : function(){
				$(this).dialog("close");
			}
			
		}
	});

	
	$("#notGenerated").click(function(){
		 $("#download").hide();
		 $("#generatetc").show();
		 $("#TCCancel").hide();
		 $("#locationname").val($("#hiddenlocId").val());
		 
		 
		    var locationid=$("#locationname").val();
			var accyear=$("#Acyearid").val();
			var classname=$("#classname").val();
			var sectionid=$("#sectionid").val();
			var sortingby=$("#sortingby").val();
			var orderby=$("input[name='sorting']:checked").val();
			$("#demotedTable").empty();
			$("#generateStu").empty();
			appendpagination($("#stupagination"));
	});
	
	
	$("#TCCancel").click(function(){
		
		var list=[];
		var list1=[];
		var list2=[];
		var cnt = 0;
		$('#allstudentd tbody input[type="checkbox"]:checked').map(function() {
		
			var schnm =$(this).attr("class").split(" ")[0];
			var acy=$(this).attr("class").split(" ")[1];
			var stuid =$(this).attr("class").split(" ")[2];
			
			
			list.push(schnm);
			list1.push(acy);
			list2.push(stuid);
	
			cnt++;
		});
		if (cnt == 0) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select any one record for TC Cancellation");
			return false;
		} else{
		
			cancelTranferCertificate(list,list1,list2);
		}
	});
	
	$("#resetbtn").on("click", function (e) {
		 
		$("#locationname,#classname,#sectionid").val("all");
		$("#Acyearid").val($('#hiddenAcademicYear').val());
		$("#sortingby").val("Alphabetical");
		
		orderby=$("input[name='sorting1']:checked").val();
		$("#Male").hide();	
		$("p3").hide();
        $("#Female").hide();
        $("p4").hide();
        
		 $("#ASC").show();
		 $("p1").show();
		 $("#DESC").show();
		 $("p2").show();  
		 $("input[id='ASC']").prop("checked","checked");
		
		$("#searchvalue").val("");
		getClassList();
		$("#sectionid").html("");
		$("#sectionid").append("<option value='all'>----------Select----------</option");
		$("#allstudent tbody").empty()
		$("#selectall").prop("checked",false);
	});
	
	$("#resetbtn1").on("click", function (e) {
		$("#locationname1,#classname1,#sectionid1").val("all");
		$("#Acyearid1").val($('#hiddenAcademicYear').val());
		$("#sortingby1").val("Alphabetical");
		
		orderby=$("input[name='sorting3']:checked").val();
		$("#Male").hide();	
		$("p3").hide();
        $("#Female").hide();
        $("p4").hide();
        
		 $("#ASC").show();
		 $("p1").show();
		 $("#DESC").show();
		 $("p2").show();  
		 
		 $("input[id='ASC1']").prop("checked","checked");
		 $("#status").val("active");
		
		$("#searchvalue1").val("");
		getClassList1();
		$("#sectionid1").html("");
		$("#sectionid1").append("<option value='all'>ALL</option");
		
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid1").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		//studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	
	$("#search").click(function(){
		searchList();
		$("#selectall").prop("checked",false);
	});	
	
	$("#search1").click(function(){
		/*studentDemotedListSearch();*/
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid1").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});	
	
	$("#Acyearid").change(function(){
		var classname=$("#classname").val();
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		if(classname!="all"){
			getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		}
		$("#selectall").prop("checked",false);
	});
	
	
	$("#locationname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		getClassList();
		
		$("#sectionid").html("");
		$("#sectionid").append("<option value='all'>----------Select----------</option");
		
		$("#selectall").prop("checked",false);
	});
	
	
	$("#locationname1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid1").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		getClassList1();
		$("#sectionid1").html("");
		$("#sectionid1").append("<option value='all'>ALL</option");
			
		$("#selectall").prop("checked",false);
	});
	
	$("#Acyearid1").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid1").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		if(classname!="all")
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	
	$("#classname").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($(this).val()!="all"){
			getSectionList(classname);
			getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		}else{
			$('#sectionid').html("");
			$('#sectionid').append('<option value="all">----------Select----------</option>');
			$("#allstudent tbody").empty();
		}
		
		$("#selectall").prop("checked",false);
	});
	
	$("#classname1").change(function(){
		 
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		if($(this).val()!="all"){
			getSectionList1(classname);
			studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		}else{
			$('#sectionid1').html("");
			$('#sectionid1').append('<option value="all">ALL</option>');
		}
		$("#selectall").prop("checked",false);
	});
	
	$("#sectionid").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	
	$("#sectionid1").change(function(){
		 
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if(sortingby == "Gender"){
			orderby=$("input[name='sorting3']:checked").val();
		}
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	
	$("#sortingby").change(function(){
		$("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
			 $("#ASC").hide();
			 $("p1").hide();
			 $("#DESC").hide();
			 $("p2").hide();
			 
			 	$("#Female").show();
		        $("p3").show();
		        $("#Male").show();
		        $("p4").show();
			}else{	
				orderby=$("input[name='sorting1']:checked").val();
				$("#Male").hide();	
				$("p3").hide();
		        $("#Female").hide();
		        $("p4").hide();
		        
				 $("#ASC").show();
				 $("p1").show();
				 $("#DESC").show();
				 $("p2").show();   
		}
		if(classname!="all")
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	
	$("#sortingby1").change(function(){
		 
		var locationid=$("#locationname1").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname1").val();
		var sectionid=$("#sectionid1").val();
		var sortingby=$("#sortingby1").val();
		var orderby=$("input[name='sorting2']:checked").val();
		
		if($("#sortingby1").val()=="Alphabetical"){
			orderby=$("input[name='sorting3']:checked").val();
			$("#Male1").hide();	
			$("p7").hide();
	        $("#Female1").hide();
	        $("p8").hide();
	        
	        $("#ASC1").show();
		    $("p5").show();
		    $("#DESC1").show();
		    $("p6").show();   
			
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
			    $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		
		if($("#sortingby1").val()=="Admission"){
			orderby=$("input[name='sorting3']:checked").val();
			$("#Male1").hide();	
			$("p7").hide();
	        $("#Female1").hide();
	        $("p8").hide();
	        
	        $("#ASC1").show();
		    $("p5").show();
		    $("#DESC1").show();
		    $("p6").show();   
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
		        $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		
		
		if($("#sortingby1").val()=="Gender"){
			orderby=$("input[name='sorting3']:checked").val();
			 $("#ASC1").hide();
			 $("p5").hide();
			 $("#DESC1").hide();
			 $("p6").hide();
			 
			 	$("#Female1").show();
		        $("p7").show();
		        $("#Male1").show();
		        $("p8").show();
			}else{	
				orderby=$("input[name='sorting3']:checked").val();
				$("#Male1").hide();	
				$("p7").hide();
		        $("#Female1").hide();
		        $("p8").hide();
		        
			    $("#ASC1").show();
			    $("p5").show();
			    $("#DESC1").show();
			    $("p6").show();   
		} 
		if(classname !="all")
		studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby);
		$("#selectall").prop("checked",false);
	});
	

$("input[name=sorting2]:radio,input[name=sorting3]:radio").click(function (){
	
	
	$("#searchvalue1").val("");
	$("#selectall").prop("checked",false);
	var accyear=$("#Acyearid").val();

	var locationid1=$("#locationname1").val();
	var classname1=$("#classname1").val();
	var sectionid1=$("#sectionid1").val();
	var sortingby1=$("#sortingby1").val();
	
	orderby1=$("input[name='sorting3']:checked").val();
	
	if($("#sortingby1").val()=="Gender"){
		
		orderby1=$("input[name='sorting3']:checked").val();
		if(classname1 !="all")
		studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
	}
	else if($("#sortingby1").val()=="Alphabetical" || $("#sortingby1").val()=="Admission"){
		
		var orderby1=$("input[name='sorting2']:checked").val();
		if(classname1 !="all")
		studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
	}
	
});

	
$("input[name=sorting]:radio,input[name=sorting1]:radio").change(function(){
	
	$("#selectall").prop("checked",false);
	    $("#searchvalue").val("");
		var locationid=$("#locationname").val();
		var accyear=$("#Acyearid").val();
		var classname=$("#classname").val();
		var sectionid=$("#sectionid").val();
		var sortingby=$("#sortingby").val();
		var orderby=$("input[name='sorting']:checked").val();
		
		if($("#sortingby").val()=="Gender"){
			orderby=$("input[name='sorting1']:checked").val();
		}
		
		var locationid1=$("#locationname1").val();
		var classname1=$("#classname1").val();
		var sectionid1=$("#sectionid1").val();
		var sortingby1=$("#sortingby1").val();
		
		
		/*if($("#sortingby1").val()=="Gender"){
			orderby1=$("input[name='sorting3']:checked").val();
			if(classname!="all")
			studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
		}else{
			var orderby1=$("input[name='sorting3']:checked").val();
			if(classname!="all")
			studentDemotedListFilter(locationid1,accyear,classname1,sectionid1,sortingby1,orderby1);
		}*/
		if(classname!="all")
		getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby);
	});
	
});



function changeAccYear(){
	
	
	var locationId = $("#locationname").val();
	var accyear = $("#Acyearid").val();
	
	$.ajax({
		type : 'POST',
		url : "studentRegistration.html?method=searchAllAcademicYearDetails",
		data : {
			"locationId" :locationId,
			"accyear" :accyear,				
		},
		beforeSend: function(){
			$("#loder").show();
		},
		
		success : function(response) {
			 
			var result = $.parseJSON(response);
				$("#allstudent tbody").empty();
				if(result.SearchList.length>0){
				for(var i=0;i<result.SearchList.length;i++){
				$("#allstudent tbody").append("<tr>"
						+"<td class='"+result.SearchList[i].studentId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].locationId+" "+"studentid"+"'>"+result.SearchList[i].sno+"</td>" 
						+"<td style='text-align: center;'> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classname+" </td>"
						+"<td> "+result.SearchList[i].sectionnaem+" </td>"
						+"</tr>");
				}
				}
				else{
					$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
				}
				$("#loder").hide();
				$(".select").change(function() {
					
					if($(".select").length == $(".select:checked").length){
						$("#selectall").prop("checked",true);
					}
					else{
						$("#selectall").prop("checked",false);
					}
				});
				pagination(100);
				$(".numberOfItem").empty();
				$(".numberOfItem").append(" No. of Records  "+result.SearchList.length);
				
			
		}
	});
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

			$('#classname').append('<option value="all">----------Select----------</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getClassList1(){
	var locationid=$("#locationname1").val();
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

			$('#classname1').html("");

			$('#classname1').append('<option value="all">ALL</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname1').append('<option value="'

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
			
			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getSectionList1(classname){
	datalist={
			"classidVal" : classname,
			"locationId":$("#locationname1").val()
	},
	
	$.ajax({
		
		type : 'POST',
		url : "studentRegistration.html?method=getClassSection",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('#sectionid1').html("");
			
			$('#sectionid1').append('<option value="all">' + "ALL"	+ '</option>');
			
			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid1').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getStudentList(locationid,accyear,classname){
	
	datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentDetailsLByFilter",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getClassWiseList.length>0){
					for(var i=0;i<result.getClassWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td class='"+result.getClassWiseList[i].studentId+" "+result.getClassWiseList[i].academicYearId+" "+result.getClassWiseList[i].locationId+" "+"studentid"+"'>"+result.getClassWiseList[i].count+"</td>" 
							+"<td style='text-align: center;'> "+result.getClassWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getClassWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getClassWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getClassWiseList[i].classname+" </td>"
							+"<td> "+result.getClassWiseList[i].sectionnaem+" </td>"
							+"</tr>");
					}
					}
					else{
						$("#allstudent tbody").append("<tr><td colspan='5'>NO Records Found</td></tr>");
					}
					$("#loder").hide();
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
					pagination(100);
					$(".numberOfItem").empty();
					$(".numberOfItem").append(" No. of Records  "+result.getClassWiseList.length);
				
			}
		});
	}

function cancelTranferCertificate(list,list1,list2){

	datalist = {			
		"location" :list.toString(),
		"accyear"  :list1.toString(),
		"studentid":list2.toString(),
		
		
	}, $.ajax({
		type : 'POST',
		url : "menuslist.html?method=NewCancelStudentTC",
		data :  datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		
		success : function(response) { 
			var result = $.parseJSON(response);
			if(result.status=="TC Cancelled Successfully...")
			  {
				$("#loder").hide();
				$('.errormessagediv').hide();
				$(".successmessages").show();
				$(".successmessage").text("TC Cancelled Successfully");
				}
			else{
				$("#loder").hide();
				$(".successmessages").hide();
				$('.errormessagediv').show();
				$('.validateTips').text(result.status);
			}
			setTimeout( function(){
				window.location.href="menuslist.html?method=tcgeneration";
			},2000);
				
		}
	});
}

function getStudentListBySection(locationid,accyear,classname,sectionid,sortingby,orderby){
	 
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=getStudentListByTC",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.getSectionWiseList.length>0){
					for(var i=0;i<result.getSectionWiseList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox'  name='select' class='"+result.getSectionWiseList[i].locationId+" "+result.getSectionWiseList[i].academicYearId+" "+result.getSectionWiseList[i].studentId+" "+result.getSectionWiseList[i].studentAdmissionNo+" "+result.getSectionWiseList[i].classDetailId+" select'/></td>"  
							+"<td style='text-align: center;'> "+result.getSectionWiseList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.getSectionWiseList[i].studentnamelabel+"</td>"
							+"<td> "+result.getSectionWiseList[i].studentrollno+" </td>"
							+"<td> "+result.getSectionWiseList[i].classsection+" </td>"
							+"</tr>");
					};
					}else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$("#loder").hide();
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
					appendpagination($("#stupagination"))
					$(".numberOfItem").empty();
					$(".numberOfItem").append(" No. of Records  "+result.getSectionWiseList.length);
					
			}
		});
	}

function searchList(){

	var searchname = $("#searchvalue").val().trim();
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	 
datalist = {
			
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		}, $.ajax({
			type : 'POST',
			url : "studentRegistration.html?method=getStudentSearchByTC",
			data : datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			
			success : function(response) {
				 
				var result = $.parseJSON(response);
					$("#allstudent tbody").empty();
					if(result.SearchList.length>0){
					for(var i=0;i<result.SearchList.length;i++){
					$("#allstudent tbody").append("<tr>"
							+"<td><input type='checkbox' name='select' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select'/></td>"  
							+"<td style='text-align: center;'> "+result.SearchList[i].studentAdmissionNo+" </td>"
							+"<td> "+result.SearchList[i].studentFullName+"</td>"
							+"<td> "+result.SearchList[i].studentrollno+" </td>"
							+"<td> "+result.SearchList[i].classsection+" </td>"
							+"</tr>");
					}	
					}
					else{
						$("#allstudent tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
					}
					$("#loder").hide();
					
					$(".select").change(function() {
						
						if($(".select").length == $(".select:checked").length){
							$("#selectall").prop("checked",true);
						}
						else{
							$("#selectall").prop("checked",false);
						}
					});
					appendpagination($("#stupagination"));
					$(".numberOfItem").empty();
					$(".numberOfItem").append(" No. of Records  "+result.SearchList.length);
					
					
					
			}
		});
}

function generateTranferCertificate(list,list1,list2,list3,list4,examdetails,reason,remarks,result,meetingdate,noofmeeting,meetattain,gencond,nooffail)
{
		datalist = {			
			"location" :list.toString(),
			"accyear" :list1.toString(),
			"studentid" :list2.toString(),
			"admid" :list3.toString(),
			"classid" :list4.toString(),
			"examdetails" :examdetails,
			"reason" :reason,
			"remarks" :remarks,
			"result" :result,
			"meetingdate":meetingdate,
			"noofmeeting":noofmeeting,
			"meetattain":meetattain,
			"gencond":gencond,
			"nooffail":nooffail,
		}, $.ajax({
			type : 'POST',
			url : "menuslist.html?method=newTcGeneration",
			data :  datalist,
			beforeSend: function(){
				$("#loder").show();
			},
			success : function(response) {
				var result = $.parseJSON(response);
				if(result.status="success"){
					$("#loder").hide();
				$('.successmessagediv').show();
				$(".successmessage").text("TC Genrated Successfully..." );
				$("#dialog").dialog("close");
				
				setTimeout(function(){
					searchList();
			   },3000);
			}else{
				$("#loder").hide();
			}
				
			}
		});
		}

function studentDemotedList(){

	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th style='text-align:center;'>Admission No</th>" +
			"<th style='width: 200px;style='text-align:left;''>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	
	$.ajax({
		type : "POST",
		url : "menuslist.html?method=notGenTClist",
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.SearchList.length>0){
			for (var i = 0; i < result.SearchList.length; i++) {
				$("#allstudentd").append("<tr>"
						+"<td><input type='checkbox' id='select1' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td style='text-align:center;'> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");
			}	
			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
			$(".select1").change(function() {
				if($(".select").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});	
			}
	      else{
				$("#allstudentd tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			$("#loder").hide();
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records  "+result.SearchList.length);
		}
		
	});
}


function studentDemotedListFilter(locationid,accyear,classname,sectionid,sortingby,orderby){
	  
	$("#demotedTable").empty(); 
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th style='text-align:center;'>Admission No</th>" +
			"<th style='width: 200px;text-align:left;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	 
	var searchvalue=$("#searchvalue1").val();
	var status=$("#status").val();
	 
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"sortingby" :sortingby,
			"orderby" :orderby,
			"searchvalue":searchvalue,
			"status":status
		},
	
	$.ajax({
		
		type : "POST",
		url : "menuslist.html?method=GenTCListFilter",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		
		success : function(response) {
			var result = $.parseJSON(response);
			$("#allstudent tbody").empty();
			
			if(result.SearchList.length>0){
			for (var i = 0; i < result.SearchList.length; i++) {
				$("#allstudentd tbody").append("<tr>"
						+"<td><input type='checkbox' id='select1' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td style='text-align:center;'> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");
			  }
			}
			else{
				$("#allstudentd tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			$("#loder").hide();
			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
						
			$(".select1").change(function() {
				if($(".select1").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records  "+result.SearchList.length);
		}
		
	});
}

function studentDemotedListSearch(){
	
	$("#demotedTable").empty();
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th style='text-align:center;'>Admission No</th>" +
			"<th style='width: 200px;text-align:left;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	
	var searchname = $("#searchvalue1").val().trim();
	var locationid=$("#locationname1").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname1").val();
	var sectionid=$("#sectionid1").val();
	
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"searchname" :searchname,
		},
	$.ajax({
		type : "POST",
		url : "menuslist.html?method=studentDemotedListSearch",
		data : datalist,
		beforeSend: function(){
			$("#loder").show();
		},
		success : function(response) {
			var result = $.parseJSON(response);
			if(result.SearchList.length>0){
			for (var i = 0; i < result.SearchList.length; i++) {

				$("#allstudentd tbody").append("<tr>"
						+"<td><input type='checkbox' id='select1' class='"+result.SearchList[i].locationId+" "+result.SearchList[i].academicYearId+" "+result.SearchList[i].studentId+" "+result.SearchList[i].studentAdmissionNo+" "+result.SearchList[i].classDetailId+" select1'/></td>"  
						+"<td style='text-align:center;'> "+result.SearchList[i].studentAdmissionNo+" </td>"
						+"<td> "+result.SearchList[i].studentFullName+"</td>"
						+"<td> "+result.SearchList[i].studentrollno+" </td>"
						+"<td> "+result.SearchList[i].classsection+" </td>"
						+"</tr>");
			}	
     	}
			else{
				$("#allstudentd tbody").append("<tr>"+"<td colspan='6'>No Records Founds</td>" +"</tr>");
			}
			$("#loder").hide();

			$("#selectall1").change(function() {
				$(".select1").prop('checked', $(this).prop("checked"));
			});
						
			$(".select1").change(function() {
				
				if($(".select").length == $(".select1:checked").length){
					$("#selectall1").prop("checked",true);
				}
				else{
					$("#selectall1").prop("checked",false);
				}
			});
			$(".numberOfItem").empty();
			$(".numberOfItem").append(" No. of Records  "+result.SearchList.length);
		}
		
	});
}

function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #cccccc";
	document.getElementById(pointer.id).style.backgroundColor = "#ffffff";
}

function appendtable(){
	$("#demotedTable").empty(); 
	$("#demotedTable").append("<table class='table allstudent' id='allstudentd' width='100%'" +">"
			+"<tr><th style='width: 70px;'><input type='checkbox' id='selectall1' class='selectall1'/></th>"+
			"<th style='text-align:center;'>Admission No</th>" +
			"<th style='width: 200px;text-align:left;'>Student Name</th>" +
			"<th>Roll No</th>" +
			"<th>Class and Division</th>" +
			"</tr>" +
			"</table>"
	);
	
	appendpagination($("#generateStu"));
}

function appendpagination(divid){

$(divid).empty();
$(divid).append("<div class='pagebanner'>" +
				"<select id='show_per_page'><option value='50'>50</option>" +
				"<option value='100'>100</option>" +
				"<option value='200'>200</option>" +
				"<option value='300'>300</option>" +
				"<option value='400'>400</option>" +
				"<option value='500'>500</option>" +
				"</select>" +
				"<span class='numberOfItem'> No. of Records 0.</span>" +
				"</div>" +
				"<div class='pagination pagelinks'  style='margin-bottom: 10px;'></div>");
pagination(100);
}


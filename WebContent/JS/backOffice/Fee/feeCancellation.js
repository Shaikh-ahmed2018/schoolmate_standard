$(document).ready(function(){
	$("#Acyearid").val($("#hacademicyaer").val());
	$("#acyearid").val($("#hacademicyaer").val());
	$(".pagebanner").hide();
	$(".pagelinks").hide();
	$("#acyearid").change(function(){
		getFeeCancelledStudentList();
	});
	getClassList();
	getFeeCancelledStudentList();
	$("#locationname").change(function(){
		getClassList();
		$('#sectionid').html("");
		$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');
		getFeeCancelledStudentList();
	});
	$("#classname").change(function(){
		getSectionList();
		getFeeCancelledStudentList();
	})
	$("#sectionid").change(function(){
		getFeeCancelledStudentList();
	})
	$("#searchvalue").keypress(function(e){
		var key = e.which;
		if(key==13){
			if($("#searchvalue")!="" && $("#searchvalue")!=null){
				getFeeCancelledStudentList();
			}
		}
	}); 
	
	   $("#search").click(function(){
			if($("#searchvalue")!="" && $("#searchvalue")!=null){
				getFeeCancelledStudentList();
			 }
	});
	
	$("#resetbtn").click(function(){
		$("#locationname").val("all");
		$("#acyearid").val($("#hacademicyaer").val());
		$("#classname").val("all");
		$("#sectionid").val("all");
		$("#searchvalue").val("");
		$(".pagebanner").hide();
		$(".pagelinks").hide();
		$(".maintable tbody").empty();
		$(".maintable tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
		getFeeCancelledStudentList();
	});
	
	$("#add").click(function(){
		$("#studentName").val("");
		$("#dialog").dialog("open");
		$(".selectall").change(function() {
			$(".select").prop('checked', $(this).prop("checked"));
		});
			
		$(".select").change(function() {
			if($(".select").length == $(".select:checked").length){
				$(".selectall").prop("checked",true);
			}
			else{
				$(".selectall").prop("checked",false);
			}
			});
	});
	$("#dialog").dialog({
		autoOpen  : false,
		minWidth :750,
		maxHeight :300,
		modal     : true,
		title     : "New Fee Cancellation",
		buttons   : {
			'Cancel Fee' : function() {
				
				var feecode=[];
				if($(".select:checked").length==0){
					$(".errormessagediv").show();
					 $(".validateTips").text("No Student Selected");
					 setTimeout(function(){
						 $(".successmessagediv").hide();
					 },3000);
					 return false;
				}
				$(".select:checked").each(function(){
					feecode.push($(this).val());
				});
				
				$.ajax({
					type : 'POST',
					url : "feecollection.html?method=cancelFee",
					data : {
						"feecode":feecode.toString(),
						"accYear":$("#Acyearid").val(),
						},
					success : function(response) {
						var result = $
						.parseJSON(response);
						$('.errormessagediv').hide();
						if (result.status == "true") {
							$(".successmessagediv").show();
							$(".validateTips").text("Cancel Fee Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							setTimeout(function(){
								$("#dialog").dialog('close');
								getFeeCancelledStudentList();
							},3000);
						} 
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Fee is Not Canceled");
							setTimeout(function(){
								$("#dialog").dialog('close');
								$('.errormessagediv').hide();
							},3000);
						}
					}
				});  
				
				feecode=[];
			},
			'Back' : function() {
				$(this).dialog('close');
				$("#studentName").val("");
				$("#Acyearid").val($("#hacademicyaer").val());
				$(".dialogtable tbody").empty();
				$(".dialogtable").hide();
			}
		}
	});
	
	$("#dialogind").dialog({
		autoOpen  : false,
		minWidth :750,
		maxHeight :300,
		modal     : true,
		title     : "Fee Cancellation Details",
		buttons   : {
			
		'Print':function(){
			         printDialog();
					},
			'Cancel' : function() {
				$(this).dialog('close');
				$("#dialogind").empty();
			}
		}
	});

	$("#Acyearid").val($("#hacademicyaer").val());
	
	$("select#Acyearid").change(function(){
		paidTermListForStudent($("#studentIdIntId").val());
	});
	
	if($("#allstudent tbody tr").length==0){
			$("#allstudent tbody").append("<tr><td colspan='7'>No Records Found</td></tr>");
	    	$(".numberOfItem").empty();
		    $(".numberOfItem").append("No. of Records 0 ");
		   pagination(100);
	}
	$("#studentName").autocomplete({
		source : function(request, response) {

			$.ajax({
				url : "studentRegistration.html?method=feeCollectedStudents",
				data : {
					searchTerm : request.term,
					accyear : $("#Acyearid").val()
				},
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					response($.map(	result.jsonResponse,function(item) {
						return {
							label : item.studentnamelabel,
							value : item.studentidlabel,
						}
					}));
				}
			});
		},
		select : function(event, ui) {

			var searchTerm = ui.item.value;

			studentDetails = {
					'searchTerm' : searchTerm
			};
		
			paidTermListForStudent(searchTerm);
			
			$("#studentName").val(ui.item.label);
			$("#studentIdIntId").val(searchTerm);

			return false;
		}
	});
	
	
	/*$("#dialog").dialog({
		autoOpen: false,
	    maxWidth  :300,
	    maxHeight :250,
	    width     :250,
	    height    :175,
		modal: true,
		title:'Cancel Fee',
		buttons : {
			"Yes" : function() {

				$.ajax({
					type : 'POST',
					url : "feecollection.html?method=cancelFee",
					data : {"feecode":$("#feecode").val()},
					success : function(
							response) {
						var result = $
						.parseJSON(response);

						$('.errormessagediv').hide();

						if (result.status == "true") {

							$(".successmessagediv").show();
							
							$(".validateTips").text("Cancel Fee Progressing...");
							$('.successmessagediv').delay(3000).slideUp();
							setTimeout(function(){
								paidTermListForStudent($("#studentIdIntId").val());
							},3000);

						} 
						else{
							$(".errormessagediv").show();
							$(".validateTips").text("Selected Fee is Not Canceled");
							$('.errormessagediv').delay(3000).slideUp();
						}
						

					}

				});  

				$(this).dialog("close");

			},

			"No" : function() {
				$(this).dialog("close");
			}

		}
	});*/
	
	});

function paidTermListForStudent(studentId){
	var accYear=$("select#Acyearid").val();
	
	$.ajax({
		type:'POST',
		url:'feecollection.html?method=feeCancellationList',
		data:{'studentId':studentId,
				'accYear':accYear,
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			$(".dialogtable tbody").empty();
			var len=result.data.length;
			if(len>0){
				$(".dialogtable").show();
		for(var i=0;i<len;i++){
			$(".dialogtable tbody").append("<tr><td><input type='checkbox' value='"+result.data[i].id+"' class='select'></td>" +
					"<td>"+result.data[i].admissionNo+"</td>" +
					"<td>"+result.data[i].studentName+"</td>" +
					"<td>"+result.data[i].className+"</td>" +
					"<td>"+result.data[i].termName+"</td>" +
					"<td>"+result.data[i].paidAmt+"</td></tr>");
					//"<td><input class='buttons cancel' type='button' value='cancel' id='"+result.data[i].id+"' /></td></tr>");
		}
		$(".selectall").change(function() {
			$(".select").prop('checked', $(this).prop("checked"));
		});
			
		$(".select").change(function() {
			if($(".select").length == $(".select:checked").length){
				$(".selectall").prop("checked",true);
			}
			else{
				$(".selectall").prop("checked",false);
			}
			});
		}
			else{
				$(".dialogtable").hide();
				//$("#dialogtable tbody").append("<tr><td colspan='7'>No Records Found</td></tr>");
			}
			
		/*	if($("#hiddendelete").val().trim()!="true"){
        		$(".cancel").hide();
        	}else{
        		$(".cancel").show();
        	}*/
			
			/*$(".numberOfItem").empty();
			 $(".numberOfItem").append("No. of Records  "+result.data.length);
			 pagination(100);*/
		$(".cancel").click(function(){
			//$("#dialog").dialog("open");
			$("#feecode").val($(this).attr("id"));
			$(".successmessagediv").show();
			 $(".validateTips").text("Fee Cancelled Successfully");
			 setTimeout(function(){
				 $(".successmessagediv").hide();
			 },3000);
		});
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

			$('#classname').append('<option value="all">' +"ALL"+ '</option>');

			for ( var j = 0; j < result.ClassList.length; j++) {

				$('#classname').append('<option value="'

						+ result.ClassList[j].classcode + '">'

						+ result.ClassList[j].classname

						+ '</option>');
			}
		}
	});
}

function getSectionList(){
	datalist={
			"classidVal" : $("#classname").val(),
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
function getFeeCancelledStudentList(){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	var classname=$("#classname").val();
	var sectionid=$("#sectionid").val();
	var search=$("#searchvalue").val();
	datalist = {
			"location" :locationid,
			"accyear" :accyear,
			"classId" :classname,
			"sectionid" :sectionid,
			"search":search,
		},
		$.ajax({
			type : 'POST',
			url : "feecollection.html?method=getFeeCancelledStudentList",
			data : datalist,
			async : false,
			success : function(response) {
				var result = $.parseJSON(response);
					$(".maintable tbody").empty();
					if(result.cancelledList.length>0){
						$(".pagebanner").show();
						$(".pagelinks").show();
						
					for(var i=0;i<result.cancelledList.length;i++){
					$(".maintable tbody").append("<tr id='"+result.cancelledList[i].studentId+"'>"
							+"<td>"+result.cancelledList[i].count+"</td>" 
							+"<td>"+result.cancelledList[i].studentAdmissionNo+" </td>"
							+"<td class='studentname'>"+result.cancelledList[i].studentnamelabel+"</td>"
							+"<td class='studentclass'>"+result.cancelledList[i].classname+"</td>"
							+"<td class='studentSection'>"+result.cancelledList[i].sectionnaem+"</td>"
							//+"<td> "+result.cancelledList[i].searchTerm+"</td>"
							//+"<td> "+result.cancelledList[i].paidupto+"</td>"
							+"</tr>");
					};
					
					$(".maintable tbody tr").click(function(){
						var flag=$(this);
						var stuId=$(this).attr('id');
						var stuName=$(this).find('.studentname').text();
						var stuClassSection=$(this).find('.studentclass').text()+"-"+$(this).find('.studentSection').text();
						getIndividualCancelledStudentList(stuId,stuName,stuClassSection,flag);
					});
					}else{
						$(".pagebanner").hide();
						$(".pagelinks").hide();
						$(".maintable tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
					}
					$(".numberOfItem").empty();
					$(".numberOfItem").append("No. of Records  "+result.cancelledList.length);
					pagination(100);
			}
		});
	}

function getIndividualCancelledStudentList(stuId,stuName,stuClassSection,flag){
	var locationid=$("#locationname").val();
	var accyear=$("#Acyearid").val();
	datalist={
			"stuid":stuId,
			"locationid":locationid,
			"accyear":accyear,
	}
	$.ajax({
		type : 'POST',
		url : "feecollection.html?method=getIndividualFeeCancelledStudent",
		data : datalist,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
				if(result.indcancelledList.length>0){
					$("#dialogind").dialog("open");
					$("#dialogind").append('<div class="row" style="padding-top: 14px;padding-bottom: 14px;"><div class="col-md-6"><div class="form-group clearfix">'+
							'<label for="inputPassword" class="control-label col-xs-6"'+
							'style="text-align: right; line-height: 35px;font-size: 14px;">Student Name :</label>'+
							''+
							'<label for="inputPassword" class="control-label col-xs-6"'+
							'style="text-align: left; line-height: 35px;font-size: 14px;font-weight: normal;padding-left: 0;" class="dialogStuName">'+stuName+'</label>'+
							'</div></div><div class="col-md-6">'+
							'<div class="form-group clearfix">'+
							'<label for="inputPassword" class="control-label col-xs-6"'+
							'style="text-align: right;line-height:35px;font-size: 14px;">Class-Division :</label>'+
							''+
							'<label for="inputPassword" class="control-label col-xs-6"'+
							'style="text-align: left; line-height: 35px;font-size: 14px;font-weight: normal;padding-left: 0;" class="dialogStuClassSection">'+stuClassSection+'</label>'+
							'</div></div></div><div><table class="inddetail" id="allstudent" style="width:100%;margin-bottom: 2%;"><thead>'+
							'<tr><th>S.No</th>'+
							'<th>Term Name</th>'+
							'<th>Total Amount</th>'+
							'<th>Paid Amount</th>'+
							'<th>Balance Amount</th>'+
							'<th>Advance</th>'+
							'<th>Fine</th>'+
							'<th>Paid Date</th>'+
							'</tr></thead><tbody></tbody></table></div></div></div>');	
					for(var i=0;i<result.indcancelledList.length;i++){
				$(".inddetail tbody").append("<tr>"
						+"<td>"+(i+1)+"</td>" 
						+"<td class='termname'> "+result.indcancelledList[i].term+" </td>"
						+"<td class='actualamt'> "+result.indcancelledList[i].tot_actual_amt+"</td>"
						+"<td class='paidamt'> "+result.indcancelledList[i].tot_paid_amt+"</td>"
						+"<td> "+result.indcancelledList[i].opening_balance+"</td>"
						+"<td> "+result.indcancelledList[i].advanceCarry+"</td>"
						+"<td> "+result.indcancelledList[i].fineAmount+"</td>"
						+"<td class='date'> "+result.indcancelledList[i].dd_cheque_date+"</td>"
						+"</tr>");
				};
				
				$("#printdiv").empty();
				$("#printdiv").append('<table id="allstudent" class="printdiv"><tr><td colspan="4" style="text-align:center;border:0px;"></td></tr>'+
						'<tr><td colspan="4" style="text-align:center;border:0px;font-size: 20px;">'+result.locationname+'</td></tr>'+		
						'<tr><td colspan="4" style="text-align:center;border:0px;">'+result.adress1+'</td></tr>'+		
						'<tr><td colspan="4" style="text-align:center;border:0;border-bottom:1px solid #8e8c8c;">'+result.adress2+'</td></tr>'+	
						'<tr><td colspan="2" style="border:0px;"></td><td colspan="2" style="border:0px;">Academic Year : '+$("#acyearid option:selected").text()+'</td></tr>'+
						'<tr><td colspan="2" style="border:0px;">Name : '+stuName+'</td><td colspan="2" style="border:0px;">Class-Division : '+stuClassSection+'</td></tr>'+
						'<tr><td colspan="4" style="text-align:center;border:0px;border-bottom:1px solid #8e8c8c;"></td></tr>'+
						'<tr><td colspan="4" style="text-align:center;border:0px;border-bottom:1px solid #8e8c8c;">FEE CANCELLED RECIEPT</td></tr>'+
						'<tr><td colspan="4" style="text-align:center;border:0px;"></td></tr>'+
						'<tr><td colspan="2" style="text-align: center;">Term</td><td>Total Amount</td><td>Paid Amount</td></tr>'+
						/*'<tr><td colspan="2">Term1</td><td>2000</td><td>1000</td></tr>'+
						'<tr><td colspan="2" style="border-top:1px solid #000;">Total</td><td style="border-top:1px solid #000;">2000</td><td style="border-top:1px solid #000;">1000</td></tr>'+*/
						'</table>');
				var total=0;
				var totalpaid=0;
				for(var i=0;i<result.indcancelledList.length;i++){
					total=total+result.indcancelledList[i].tot_actual_amt;
					totalpaid=totalpaid+result.indcancelledList[i].tot_paid_amt;
				$("#printdiv .printdiv").append('<tr style="height: 30px;"><td colspan="2" style="text-align: center;">'+result.indcancelledList[i].term+'</td><td>'+result.indcancelledList[i].tot_actual_amt+'</td><td>'+result.indcancelledList[i].tot_paid_amt+'</td></tr>');
				}
				$("#printdiv .printdiv").append('<tr><td colspan="2" style="border-top: 1px solid #8e8c8c;text-align: center;">Total</td><td style="border-top: 1px solid #8e8c8c;">'+total+'</td><td style="border-top: 1px solid #8e8c8c;">'+totalpaid+'</td></tr>');
				}else{
					$(".pagebanner").hide();
					$(".pagelinks").hide();
					$(".inddetail tbody").append("<tr>"+"<td colspan='7'>No Records Founds</td>" +"</tr>");
				}
				$(".numberOfItem").empty();
				$(".numberOfItem").append("No. of Records  "+result.indcancelledList.length);
				pagination(100);
		}
	});
}
function printDialog(){
	
		var a=$("#printing-css").val();
		var b= document.getElementById("printdiv").innerHTML;
		var abd='<style>' + a +'</style>' + b;
		var frame1 = $('<iframe />');
	      frame1[0].name = "frame1";
	      frame1.css({ "position": "absolute", "top": "-1000000px" });
	      $("body").append(frame1);
	      var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
	      frameDoc.document.open();
	      //Create a new HTML document.
	      frameDoc.document.write('<html><head><title>...</title>');
	      //Append the external CSS file.
	      frameDoc.document.write('</head><body>');
	      //Append the DIV contents.
	      frameDoc.document.write('<p>Student Copy</p>');
	      frameDoc.document.write(abd);
	      frameDoc.document.write('<p>School Copy</p>');
	      frameDoc.document.write(abd);
	      frameDoc.document.write('</body></html>');
	      setTimeout(function () {
	          window.frames["frame1"].focus();
	          window.frames["frame1"].print();
	          frame1.remove();
	      }, 100);
	      
	      
}
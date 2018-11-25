$(document).ready(function() {

	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
	}

	$("#Acyearid").val($("#hiddenAcademicYear").val());
	$("#locationname").val($("#hiddenlocId").val());


	$("#add").click(function() {
		window.location.href ="menuslist.html?method=addHomeWork&historylocId="+$("#locationname").val()
		+"&historyacademicId="+$("#Acyearid").val()+"&historyclassname="+$("#classname").val()+
		"&historysectionid="+$("#sectionid").val()+"&historystartdate="+$("#startdate").val()+
		"&historyenddate="+$("#enddate").val();
	});

	$("#selectall").change(function() {
		$(".select").prop('checked', $(this).prop("checked"));
	});

	$(".select").change(function() {
		if($(".select").length == $(".select:checked").length){
			$("#selectall").prop("checked",true);
		}
		else{
			$("#selectall").prop("checked",false);
		}
	});
	getClassList();
	$("#locationname").change(function(){
		getClassList();
		searchHomeWorkList();

	});

	$("#classname").change(function(){
		var classname=$("#classname").val();
		getSectionList(classname);
		searchHomeWorkList();
	});
	$("#sectionid").change(function(){
		searchHomeWorkList();
	});

	$("#Acyearid").change(function(){
		getAcademicYearStartDate();
		searchHomeWorkList();
		academicyearchange();


	});

	academicyearchange();

	if($("#div1 .panel-body").text().trim()=="Nothing found to display."){
		$("#div1 .panel-body").empty();
		$("#div1 .panel-body").append('<table id="allstudent" class="table">' +
				'<thead><tr>' +
				'<th class="sortable"><input type="checkbox" name="selectall" id="selectall" onclick="selectAll()"></a></th>'+
				'<th class="sortable">date<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">classname<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">subjectname<img src="images/sort1.png" style="float: right"></a></th>'+
				'<th class="sortable">description<img src="images/sort1.png" style="float: right"></a></th></tr></thead>'+
		'<tbody><tr><td colspan="5">No Records Found</td></tr></tbody></table>');

	}

	if($("#historyacademicId").val()!="" || $("#historylocId").val()!="" || $("#historystartdate").val()!="" || $("#historyenddate").val()!=""){

		if($("#historyacademicId").val()!=""){
			$("#Acyearid").val($("#historyacademicId").val());
		}

		$("#locationname").val($("#historylocId").val());
		getClassList();
		$("#classname").val($("#historyclassname").val());
		getSectionList($("#classname").val());
		$("#sectionid").val($("#historysectionid").val());
		$("#startdate").val($("#historystartdate").val());
		$("#enddate").val($("#historyenddate").val());

		searchHomeWorkList();
	}else{}

	$("#startdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var min = $(this).datepicker('getDate'); // Get selected date
			$("#enddate").datepicker('option', 'minDate', min);
			searchHomeWorkList();
		}
	});

	$("#enddate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate:fEDate,
		minDate:fSDate,
		changeMonth : "true",
		changeYear : "true",
		buttonImage : "images/calendar.GIF",
		buttonImageOnly : true,
		onSelect:function(){
			var max = $(this).datepicker('getDate'); // Get selected date
			$('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M');
			searchHomeWorkList();
		}

	});

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

			$('#classname').append('<option value="All">' + "ALL"	+ '</option>');

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

			$('#sectionid').append('<option value="all">' + "ALL"	+ '</option>');

			for ( var j = 0; j < result.sectionList.length; j++) {

				$('#sectionid').append('<option value="' + result.sectionList[j].sectioncode
						+ '">' + result.sectionList[j].sectionnaem
						+ '</option>');
			}
		}
	});
}

function getvaldetails(value){
	var s1 =value.id;
	var assgnmentid = s1;
	$("#hhomeworkid").val(assgnmentid);
}

function searchHomeWorkList(){

	dataList={

			"locid":$("#locationname").val(),
			"clasid":$("#classname").val(),
			"secid":$("#sectionid").val(),
			"accid":$("#Acyearid").val(),
			"startdate":$("#startdate").val(),
			"enddate":$("#enddate").val(),

	};

	$.ajax({
		type:'POST',
		url:'menuslist.html?method=seachHomeWorkList',
		data:dataList,
		success: function(data){
			var result=$.parseJSON(data);
			$("#allstudent tbody").empty();
			if(result.homeworklist.length>0){

				for(var i=0;i<result.homeworklist.length;i++){

					$("#allstudent tbody").append("<tr>" +
							"<td>"+result.homeworklist[i].slNo+"</td>" +
							"<td>"+result.homeworklist[i].currentdate+"</td>" +
							"<td>"+result.homeworklist[i].date+"</td>" +
							"<td>"+result.homeworklist[i].classname+" - "+result.homeworklist[i].sectionname+"</td>" +
							"<td>"+result.homeworklist[i].subjectname+"</td>" +
							"<td>"+result.homeworklist[i].smsStatus+"</td>" +

					"</tr>");
				}
			}
			else{
				$("#allstudent tbody").append("<tr><td colspan='9'>No Records Found</td></tr>");
			}

			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.homeworklist.length);
			pagination(100);
			$("#loder").hide();
		}
	});

}

function getAcademicYearStartDate(){

	var accyear=$('#Acyearid').val();
	$.ajax({
		type:"POST", 
		url:"menuslist.html?method=getAcademicYearStartDate",
		data:{
			"accyear":accyear,
		},
		async:false,
		success:function(response){
			var  result=$.parseJSON(response);
			$("#hiddenstartdate").val(result.startdate);
			$("#hiddenenddate").val(result.enddate);
		}

	});



}

function academicyearchange(){

	$('#startdate').datepicker('destroy');
	$('#enddate').datepicker('destroy');
	fSDate = new Date($("#hiddenstartdate").val());
	fEDate = new Date($("#hiddenenddate").val());

}

function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();
	
}

$(document).ready(function() {
	
	var taxablemonth=$("#htaxablemonth").val();
	if(taxablemonth !=undefined){
		
		  var itemId = taxablemonth.substring(1, taxablemonth.length-1);
		
		for(var k=0;k<itemId.split(",").length;k++){
			$("#tax-payable-month tbody tr input[name='"+itemId.split(",")[k].trim()+"']").prop("checked",true);
		}	
	}
	$(".centretext span").text(getCalculationMonth());
	$("#tax-payable-month tbody tr input[type='checkbox']").change(function(){
		addYearlyAmount();
	});
	
	if($("#allstudent tbody tr").length ==0){
		$("#allstudent tbody").append("<tr><td colspan='7'>NO Records Found</td></tr>");
	}
	
	getdepartmentlist();
	
	staffListByLocIdAndDeptId();
	
	$("#locId").change(function(){
		staffListByLocIdAndDeptId();
	});
	
	$("#department").change(function(){
		staffListByLocIdAndDeptId();
	});
	
	$('#basictotal').val(($('#basic').val()*(getCalculationMonth())).toFixed(2));
	$('#datotal').val(($('#da').val()*(getCalculationMonth())).toFixed(2));
	$('#hratotal').val(($('#hra').val()*(getCalculationMonth())).toFixed(2));
	$('#allowancetotal').val(($('#allowance').val()*(getCalculationMonth())).toFixed(2));
	$('#childedutotal').val(($('#childedu').val()*(getCalculationMonth())).toFixed(2));
	$('#specialtotal').val(($('#special').val()*(getCalculationMonth())).toFixed(2));
	$('#arrearstotal').val(($('#arrears').val()*(getCalculationMonth())).toFixed(2));
	$('#performacetotal').val(($('#performace').val()*(getCalculationMonth())).toFixed(2));
	$('#medicaltotal').val(($('#medical').val()*(getCalculationMonth())).toFixed(2));
	$('#leavetotal').val(($('#leave').val()*(getCalculationMonth())).toFixed(2));
	$('#foodtotal').val(($('#food').val()*(getCalculationMonth())).toFixed(2));
	$('#reimbursetotal').val(($('#reimburse').val()*(getCalculationMonth())).toFixed(2));
	$('#internettotal').val(($('#internet').val()*(getCalculationMonth())).toFixed(2));
	$('#drivertotal').val(($('#driver').val()*(getCalculationMonth())).toFixed(2));
	$('#othertotal').val(($('#other').val()*(getCalculationMonth())).toFixed(2));
	$('#yearlytotalpfamount').val(($('#pfamount').val()*(getCalculationMonth())).toFixed(2));
	$('#yearlytotalesiamount').val(($('#esiamount').val()*(getCalculationMonth())).toFixed(2));
	$('#standarddedu').val('250000.00');
	
	
	
	addYearlyAmount();
	addMonthlyAmount();
	$('#basic,#da,#hra,#allowance,#childedu,#special,#arrears,#performace,#medical,#leave,#food,#reimburse,#internet,#driver,#other,#pfemployerbasic,#esiemployertotal,#staffadvance,#otherdeduction').keypress(function(event) {
	    if (event.which != 46 && (event.which < 47 || event.which > 59))
	    {
	        event.preventDefault();
	        if ((event.which == 46) && ($(this).indexOf('.') != -1)) {
	            event.preventDefault();
	        }
	    }
	});
	
	$('#basic').blur(function(){
		$('#basictotal').val(($('#basic').val()*(getCalculationMonth())).toFixed(2));
		pfCalculator();
		addMonthlyAmount();
		addYearlyAmount();
		getHraPercentage();
		
	});
	$('#da').blur(function(){
		pfCalculator();
		$('#datotal').val(($('#da').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
		getHraPercentage();
	});
	$('#hra').blur(function(){
		$('#hratotal').val(($('#hra').val()*(getCalculationMonth())).toFixed(2));
		addYearlyAmount();
		addMonthlyAmount();
	});
	$('#allowance').blur(function(){
		$('#allowancetotal').val(($('#allowance').val()*(getCalculationMonth())).toFixed(2));
		addYearlyAmount();
		addMonthlyAmount();
	});
	$('#childedu').blur(function(){
		$('#childedutotal').val(($('#childedu').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#special').blur(function(){
		$('#specialtotal').val(($('#special').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#arrears').blur(function(){
		$('#arrearstotal').val(($('#arrears').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#performace').blur(function(){
		$('#performacetotal').val(($('#performace').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#medical').blur(function(){
		$('#medicaltotal').val(($('#medical').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#leave').blur(function(){
		$('#leavetotal').val(($('#leave').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#food').blur(function(){
		$('#foodtotal').val(($('#food').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#reimburse').blur(function(){
		$('#reimbursetotal').val(($('#reimburse').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#internet').blur(function(){
		$('#internettotal').val(($('#internet').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#driver').blur(function(){
		$('#drivertotal').val(($('#driver').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	$('#other').blur(function(){
		$('#othertotal').val(($('#other').val()*(getCalculationMonth())).toFixed(2));
		addMonthlyAmount();
		addYearlyAmount();
	});
	//pfYearlyCalculation();
	
	$('#pfemployerbasic').change(function(){
		var basic=$('#basic').val();
		var da=$('#da').val();
		total=Number(basic)+Number(da);
		if(total >= $('#pfemployerbasic').val()){
			var totalpfamount=$('#pfemployerbasic').val();
			pfamount = ( (totalpfamount/100) * getCalculationMonth() );
			$('#pfamount').val(pfamount.toFixed(2));
			$('#yearlytotalpfamount').val(($('#pfamount').val()*(getCalculationMonth())).toFixed(2));
		}else{
			$(".errormessagediv").show();
			$(".validateTips").text("PF Employer Amount Should be more than "+total+" and less than Basic+DA Amount.");
			$("#pfemployerbasic").focus();
			document.getElementById("pfemployerbasic").style.border = "1px solid #AF2C2C";
			document.getElementById("pfemployerbasic").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("pfemployerbasic").style.border = "1px solid #ccc";
				document.getElementById("pfemployerbasic").style.backgroundColor = "#fff";
			}, 5000);

			pfCalculator();
		}
	});
	
	pfCalculator();
	
	$("#employerpfpercentage").hide();
	$("#contributionId").hide();
	$("#employeepfamount").hide();
	
	setTimeout("removeMessage()", 3000);
	setInterval(function() {
		$(".errormessagediv").hide();
	}, 3000);

	var hpaymenttype = $("#hpaymenttype").val();

	if (hpaymenttype != "" && hpaymenttype != undefined) {

		$("#paymenttype option[value="+ hpaymenttype.trim() + "]").attr('selected', 'true');
	}

	var hbankname = $("#hbankname").val();

	if (hbankname != "" && hbankname != undefined) {

		$("#bankid option[value=" + hbankname.trim() + "]").attr('selected', 'true');
		
		getBranchName(hbankname.trim());
		$("#branchname").val($("#hiddenbranchname").val());
	}

	$("#searchname").keypress(function(e) {
		
		var key = e.which;
		if(key==13){
			staffListByLocIdAndDeptId();
		}
		
	});
	$("#searchbtn").click(function(e) {
			staffListByLocIdAndDeptId();
	});
	
	$("#resetbtn").click(function(e) {
		$("#searchname").val("");
		$("#department").val("all");
		staffListByLocIdAndDeptId();
	});
	
	$('#salaryback').click(function() {
		window.location.href = "menuslist.html?method=staffEmployementList&historylocId="+$("#historylocId").val()+
		"&historydepartment="+$("#historydepartment").val()+"&historysearchname="+$("#historysearchname").val()+
		"&salaryupdate="+$("#salaryupdate").val();
	});
	
	$('#editTeacher').click(function() {
		var count = 0;
		var vehicle_id;
		$('input:radio:checked').map(function() {
			vehicle_id= $(this).attr("id");
			count++;
		});
		if (count == 0 || count > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Atleast One Record");
			return false;
		} else {
			window.location.href = "staffemployement.html?method=getStaffEmployementEntry&teachercode="+vehicle_id+
			"&historylocId="+$("#locId").val()+"&historydepartment="+$("#department").val()+
			"&historysearchname="+$("#searchname").val();
		}
	});

	$('#itdeclare').click(function() {

		var count = 0;
		var teacherId;
		var empctc=0.0;
		$('input:radio:checked').map(function() {
			teacherId= $(this).attr("id");
			empctc=$(this).closest("tr").find(".ctc").text();
			count++;
		});
		if (count == 0 || count > 1) {
			$(".errormessagediv").show();
			$(".validateTips").text("Select Any One Record");
			return false;
		} else {
			if(empctc != 0.0){
				window.location.href = "menuslist.html?method=TDSComputationDetails&teachercode="+teacherId
				+"&historylocId="+$("#locId").val()+"&historydepartment="+$("#department").val()+
				"&historysearchname="+$("#searchname").val();
			}else{
				$(".errormessagediv").show();
				$(".validateTips").text("To Apply IT Declaration,Please Update Salary Details");
				return false;
			}
		}
	});
	
	$('#basic,#da').change(function() {

		var da = $("#da").val();
		var basic = $("#basic").val();

		var total=da+basic;

		if(total>=15000.0||total>=15000)
		{
			$("#employerpfpercentage").show();
			$("#contributionId").show();
			$("#employeepfamount").hide();
		}

		else
		{
			$("#employerpfpercentage").hide();
			$("#contributionId").hide();
			$("#employeepfamount").hide();

		}
	});


	$("input[name='employee_contribution']").change(function(){
		var employee_contribution = $('.radio-inline[name="employee_contribution"]:checked').val();

		if(employee_contribution=='Y')
			$("#employeepfamount").show();
		else
			$("#employeepfamount").hide(); 

	});

	$("#submit").click(function() {
		$.ajax({
			type : "POST",
			url : "staffemployement.html?method=saveStaffSalaryDetails",
			data : $("#teacherform").serialize(),
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);
				if(result.status == "true"){
					$('.errormessagediv').hide();
					$(".successmessagediv").show();
					$(".validateTips").text("Salary Details Saved Successfully...");
					setTimeout(function() {
						window.location.href = "menuslist.html?method=staffEmployementList&historylocId="+$("#historylocId").val()+
						"&historydepartment="+$("#historydepartment").val()+"&historysearchname="+$("#historysearchname").val()+
						"&salaryupdate="+$("#salaryupdate").val();
					}, 3000);
				}else{
					$('.errormessagediv').show();
					$(".successmessagediv").hide();
					$(".validateTips").text("Salary Details Not Saved Successfully,Try later...");
					setTimeout(function() {
						window.location.href = "menuslist.html?method=staffEmployementList&historylocId="+$("#historylocId").val()+
						"&historydepartment="+$("#historydepartment").val()+"&historysearchname="+$("#historysearchname").val()+
						"&salaryupdate="+$("#salaryupdate").val();
					}, 3000);
				}
				
			}
		});
		$("#submit").prop("disabled", true);
	});

	$('#excelDownload').click(function() {

		window.location.href = 'staffemployement.html?method=downloadStaffEmploymentDetailsXLS';

	});
	
	$("#pdfDownload").click(function() {

		window.location.href = "staffemployement.html?method=downloadStaffEmploymentDetailsPDF";

	});
	
	$('#hrapercentage').change(function(){
		var basic=$('#basic').val();
		var da=$('#da').val();
		total=Number(basic)+Number(da);
		var hrapercentage=$('#hrapercentage').val();
		if(basic != 0.0){
			monthlyhraamount = ( (total/100) * hrapercentage );
			$('#hra').val(monthlyhraamount.toFixed(2));
			$('#hratotal').val(($('#hra').val()*(getCalculationMonth())).toFixed(2));
		}else{
			$("#hrapercentage option[value='0']").prop('selected', 'true');
			$(".errormessagediv").show();
			$(".validateTips").text("Field Required Basic.");
			$("#basic").focus();
			document.getElementById("hrapercentage").style.border = "1px solid #AF2C2C";
			document.getElementById("hrapercentage").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("hrapercentage").style.border = "1px solid #ccc";
				document.getElementById("hrapercentage").style.backgroundColor = "#fff";
			}, 5000);
			
		}
		
		addMonthlyAmount();
		addYearlyAmount();
	});
	
	var hiddenhrapercentage=$('#hiddenhrapercentage').val();
	if(hiddenhrapercentage !="0"){
		$("#hrapercentage option[value='"+ $('#hiddenhrapercentage').val()+"']").attr('selected', 'true');
		//$("#monthlyhraamount").val($('#hiddenmonthlyhraamount').val());
		//$("#yearlyhraamount").val(($('#hiddenmonthlyhraamount').val()*(getCalculationMonth())).toFixed(2));
	}
	totCalculation();
	$("#prevtdsctc").keyup(function(){
		totCalculation();
	});
	$("#prevtdsexmption").keyup(function(){
		totCalculation();
	});
	$("#prevtdspt").keyup(function(){
		totCalculation();
	});
	$("#prevtdsundervia").keyup(function(){
		totCalculation();
	});
	$("#prevtaxableincomefromothers").keyup(function(){
		totCalculation();
	});
	$("#prevstandarddedu").keyup(function(){
		totCalculation();
	});
	$("#prevtaxableincome").keyup(function(){
		totCalculation();
	});
	$("#prevtaxpayble").keyup(function(){
		totCalculation();
	});
	
	if($("#salaryupdate").val()=="salaryupdate" || $("#Itdeclaration").val()=="Itdeclaration"){
		 $("#locId").val($("#historylocId").val());
		 $("#department").val($("#historydepartment").val());
		 $("#searchname").val($("#historysearchname").val());
		 staffListByLocIdAndDeptId();
	}
	$("#bankid").change(function(){
		getBranchName($(this).val());
		$("#IFSCCode").val("");
	});
	$("#branchname").change(function(){
		getIFSCCode($(this).val());
	});
	
 
	$("#prevtdsctc").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#tdsctc").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#prevtdsexmption").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#prevtdspt").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#prevtdsundervia").keypress(function (e) {
		commonNumbervalidation(e);
    });
	$("#prevtaxableincomefromothers").keypress(function (e) {
		commonNumbervalidation(e);
    });
	$("#prevstandarddedu").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#prevtaxableincome").keypress(function (e) {
		commonNumbervalidation(e);
    });
	$("#prevtaxpayble").keypress(function (e) {
		commonNumbervalidation(e);
    });
	
	$("#bankaccnumber").keypress(function (e) {
		 var regex = new RegExp(/^[a-zA-Z0-9\s]*$/);
	        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
	        if (regex.test(str)) {
	        	 HideError(this);
	            return true;
	        }
	        e.preventDefault();
	        $(".errormessagediv").show();
			$(".validateTips").text("Allows only alphanumerics");
			setTimeout(function() {
				$('#errormessagediv').fadeOut();
			    },3000);
	        return false;
    });
 
	
});

function commonNumbervalidation(e){
	var regex = new RegExp(/^[0-9.]*$/);
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
    	 HideError(this);
        return true;
    }
    e.preventDefault();
   /* $(".errormessagediv").show();
	$(".validateTips").text("Allows only Numeric values");
	setTimeout(function() {
		$('#errormessagediv').fadeOut();
	    },3000);*/
    return false;
}
 

function getCalculationMonth(){
	var employeecurrenttaxablemonth=0;
	$.ajax({
		type:'POST',
		url:'staffemployement.html?method=getCalculationMonth',
		data:{
			"emloyeeid":$("#hemloyeeid").val(),
			"academicyaer":$("#hacademicyaer").val(),
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			employeecurrenttaxablemonth=result.employeecurrenttaxablemonth;
			if(Number(result.employeecurrenttaxablemonth)>12){
				employeecurrenttaxablemonth=12;
			}
		}
	});
	return employeecurrenttaxablemonth
}
function totCalculation(){
	var tottdsctc=Number($("#prevtdsctc").val())+Number($("#tdsctc").val());
	$("#tottdsctc").val(tottdsctc.toFixed(2));
	
	var tottdsexmption=Number($("#prevtdsexmption").val())+Number($("#tdsexmption").val());
	$("#tottdsexmption").val(tottdsexmption.toFixed(2));
	
	var tottdspt=Number($("#prevtdspt").val())+Number($("#tdspt").val());
	$("#tottdspt").val(tottdspt.toFixed(2));
	
	var tottdsundervia=Number($("#prevtdsundervia").val())+Number($("#tdsundervia").val());
	$("#tottdsundervia").val(tottdsundervia.toFixed(2));
	
	var tottaxableincomefromothers=Number($("#prevtaxableincomefromothers").val())+Number($("#taxableincomefromothers").val());
	$("#tottaxableincomefromothers").val(tottaxableincomefromothers.toFixed(2));
	
	$("#totstandarddedu").val($("#prevstandarddedu").val());
	
	var tottaxableincome=Number($("#prevtaxableincome").val())+Number($("#taxableincome").val());
	$("#tottaxableincome").val(tottaxableincome.toFixed(2));
	
	var tottaxpayble=Number($("#prevtaxpayble").val())+Number($("#taxpayble").val());
	$("#tottaxpayble").val(tottaxpayble.toFixed(2));
}

function addMonthlyAmount()
{
	var monthlyamount=Number($('#basic').val())+Number($('#da').val())+Number($('#hra').val())+Number($('#allowance').val())+Number($('#childedu').val())+Number($('#special').val())+Number($('#arrears').val())+Number($('#performace').val())+Number($('#medical').val())+Number($('#leave').val())+Number($('#food').val())+Number($('#reimburse').val())+Number($('#internet').val())+Number($('#driver').val())+Number($('#other').val());
	
	
	$('#totalsalary').val(monthlyamount.toFixed(2));


	
		$('#pt').val(ptdetails().toFixed(2));
	
	esiCalculator();
}
function ptdetails(){
	var monthlyamount=Number($('#basic').val())+Number($('#da').val())+Number($('#hra').val())+Number($('#allowance').val())+Number($('#childedu').val())+Number($('#special').val())+Number($('#arrears').val())+Number($('#performace').val())+Number($('#medical').val())+Number($('#leave').val())+Number($('#food').val())+Number($('#reimburse').val())+Number($('#internet').val())+Number($('#driver').val())+Number($('#other').val());
	var ptt=0.0;
	if(!(isNaN(monthlyamount))){
	$.ajax({
		type:'POST',
		url:'staffemployement.html?method=getPTdetails',
		data:{
			"locationid":$("#hlocationId").val(),
			"monthlyamount":monthlyamount,
		},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			ptt=result.profeesionaltax;
		}
	});
	}
	return ptt;
}
function addYearlyAmount(){
	var yearlyamount=Number($('#basictotal').val())+Number($('#datotal').val())+Number($('#hratotal').val())+Number($('#allowancetotal').val())+Number($('#childedutotal').val())+Number($('#specialtotal').val())+Number($('#arrearstotal').val())+Number($('#performacetotal').val())+Number($('#medicaltotal').val())+Number($('#leavetotal').val())+Number($('#foodtotal').val())+Number($('#reimbursetotal').val())+Number($('#internettotal').val())+Number($('#drivertotal').val())+Number($('#othertotal').val());
	$('#yearlytotalamount').val(yearlyamount.toFixed(2));	
	$('#tdsctc').val(yearlyamount.toFixed(2));	
	if(Number($('#tdsctc').val())>Number($('#standarddedu').val())){
		$('#tdspt').val((ptdetails()*(getCalculationMonth())).toFixed(2));
		$('#tdsexmption').val();
		
		if($('#htdsundervia').val() != 0){
			$('#tdsundervia').val($('#htdsundervia').val());
		}else{
			$('#tdsundervia').val(($('#demployeepf').val()*(getCalculationMonth())).toFixed(2));
		}
		$('#taxableincome').val((Number($('#tdsctc').val())-Number($('#standarddedu').val())-Number($('#tdspt').val())-Number($('#tdsexmption').val())-Number($('#tdsundervia').val())+Number($('#taxableincomefromothers').val())).toFixed(2));
		var taxableincome=$('#taxableincome').val();
		if(taxableincome <= 250000){
			tax_payble = Math.round((taxableincome * 5)/100);
		}else if(taxableincome >= 250001 && taxableincome <= 500000 ){
			tax_payble = Math.round((taxableincome * 5)/100);
		
		}else if(taxableincome >= 500001 &&  taxableincome <= 1000000){
		
			var amount= taxableincome- 500000.0;
		
			var  one = Math.round((500000.0 * 5)/100);
			var  two = Math.round((amount * 20)/100);
			
			tax_payble = one + two;
			
		}else{
			
			var amount= taxableincome- 500000.0;
			
			var  one = Math.round((500000.0 * 5)/100);
			var  two = Math.round((amount * 30)/100);
			
			tax_payble = one + two;;
		}
		
		  var itemId=$("#tax-payable-month tbody tr input[type='checkbox']:checked").length;
		$('#taxpayble').val(tax_payble.toFixed(2));
		$('#incometax').val(Math.round(($('#taxpayble').val()/itemId)).toFixed(2));
	}else{
		$('#tdspt').val('0.0');
		$('#tdsexmption').val('0.0');
		$('#taxbleincomestatury').val('0.0');
	}
	totCalculation();
}

function getdepartmentlist(){
	$.ajax({
	type : 'POST',
	url : 'teacherregistration.html?method=getDepartment',
	async : false,
	success : function(response) {
		var data = $.parseJSON(response);
		$("#department").empty();
		$("#department")
		.append('<option value="all">ALL</option>');
		var j=0;
		var len=data.DEPARTMENTLIST.length;
		for (j = 0; j < len; j++) {
			$("#department")
			.append(
					'<option value="'
					+ data.DEPARTMENTLIST[j].depId
					+ '">'
					+ data.DEPARTMENTLIST[j].depName
					+ '</option>');
		}
	}
  });
}

function staffListByLocIdAndDeptId(){
	var locId = $("#locId").val();
	var department = $("#department").val();
	var searchname = $("#searchname").val();
	
	$.ajax({
		type : 'POST',
		url  : "menuslist.html?method=staffListByLocIdAndDeptId",
		data : {
			"locId"      :locId,
			"department" :department,
			"searchname" :searchname
		},
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$(".allstaff tbody").empty();
			var i=0;
			var len=result.teacherList.length;
			if(len > 0){
				for(i=0;i<len;i++){
				 
				$(".allstaff tbody").append("<tr>"
						+"<td class='"+result.teacherList[i].teacherId+"'><input type='radio' name='select' class='select' id='"+result.teacherList[i].teacherId+"'/></td>" 
						+"<td> "+result.teacherList[i].registartionNo+" </td>"
						+"<td> "+result.teacherList[i].teacherName+"</td>"
						+"<td> "+result.teacherList[i].department+"</td>"
						+"<td> "+result.teacherList[i].bankaccountNo+" </td>"
						+"<td> "+result.teacherList[i].pfnumber+" </td>"
						+"<td class='ctc'> "+result.teacherList[i].ctc+" </td>"
						+"</tr>");
				}	
			}
			else{
				$(".allstaff tbody").append("<tr><td ColSpan='7'>No Records Found</td></tr>");
			}
			pagination(100);
			$(".numberOfItem").empty();
			$(".numberOfItem").append("No. of Records  "+result.teacherList.length);
		}
	});
}


function pfCalculator(){
	var basic=$('#basic').val();
	var da=$('#da').val();
	total=Number(basic)+Number(da);
	if(total>=15000){
		$('#pfemployerbasic').val('15000.00');
		var totalpfamount=$('#pfemployerbasic').val();
		pfamount = ( (totalpfamount/100) * getCalculationMonth() );
		$('#pfamount').val(pfamount.toFixed(2));
		$('#demployerpf').val(pfamount.toFixed(2));
		$('#demployeepf').val(pfamount.toFixed(2));
		$('#yearlytotalpfamount').val(($('#pfamount').val()*(getCalculationMonth())).toFixed(2));
	}else{
		$('#pfemployerbasic').val(total.toFixed(2));
		var totalpfamount=$('#pfemployerbasic').val();
		pfamount = ( (totalpfamount/100) * getCalculationMonth() );
		$('#pfamount').val(pfamount.toFixed(2));
		$('#demployerpf').val(pfamount.toFixed(2));
		$('#demployeepf').val(pfamount.toFixed(2));
		$('#yearlytotalpfamount').val(($('#pfamount').val()*(getCalculationMonth())).toFixed(2));
	}
}

function esiCalculator(){
	if($('#totalsalary').val() <= 21000){
		$('#esiemployertotal').val($('#totalsalary').val());
		var totalesiamount=$('#esiemployertotal').val();
		esiamount = ( (totalesiamount/100) * 4.75 );
		employeesiamount = ( (totalesiamount/100) * 1.75 );
		$('#esiamount').val(Math.ceil(esiamount).toFixed(2));
		$('#demployeeESI').val(Math.ceil(employeesiamount).toFixed(2));
		$('#dedmployerESI').val(Math.ceil(esiamount).toFixed(2));
		$('#yearlytotalesiamount').val(($('#esiamount').val()*(getCalculationMonth())).toFixed(2));
	}else{
		$('#esiemployertotal').val('0.00');
		$('#esiamount').val('0.00');
		$('#dedmployerESI').val('0.00');
		$('#demployeeESI').val('0.00');
		$('#yearlytotalesiamount').val('0.00');
	}
}

function getHraPercentage(){
	var basic=$('#basic').val();
	var da=$('#da').val();
	total=Number(basic)+Number(da);
	var hrapercentage=$('#hrapercentage').val();
	if(total != "0.0"){
		monthlyhraamount = ( (total/100) * hrapercentage );
		$('#hra').val(monthlyhraamount.toFixed(2));
		$('#hratotal').val(($('#hra').val()*(getCalculationMonth())).toFixed(2));
	}else{
		$('#hrapercentage option[value="0"]').prop('selected', true);
		$('#hra').val("0.00");
		$('#hratotal').val("0.00");
	}
}
function getBranchName(bankId){
	 
	$.ajax({
		type:'POST',
		url:'staffemployement.html?method=getBranchName',
		data:{'bankId':bankId},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			
				$("#branchname").empty();
				$("#branchname").append('<option value="">----------Select----------</option>');
				for(var i=0;i<result.BranchList.length;i++){
					$("#branchname").append("<option value='"+result.BranchList[i].id+"'>"+result.BranchList[i].branchName+"</option>");
				}
			
			
		}
	});
}
function getIFSCCode(branchId){
	$.ajax({
		type:'POST',
		url:'staffemployement.html?method=getIFSCCode',
		data:{'branchId':branchId},
		async:false,
		success:function(response){
			var result=$.parseJSON(response);
			if(result.IfscCode.length>0){
				$("#IFSCCode").val(result.IfscCode);
			}else{
				$("#IFSCCode").val("");
			}
			
		}
	});
}

function HideError(pointer) 
{
	$(".errormessagediv").hide();
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}


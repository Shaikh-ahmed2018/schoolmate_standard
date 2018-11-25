$(document).ready(function(){
	getPeriodicExamNameList();
	getHalflyExamNameList();
	getYearlyExamNameList();
	
	accyearId = $("#hiddenaccyear").val();
	locationId = $("#hiddenloc").val();
	
	$("#back1").click(function(){
		$('#backValue').val(accyearId+","+locationId);
		$('#backform').submit();
	});
	
	$("#reporttype").change(function(){
		if($(this).val() == "termbased"){
			$(".termbased").show();
			$(".academicyearbased").hide();
		}else if($(this).val() == "academic"){
			$(".academicyearbased").show();
			$(".termbased").hide();
		}else{
			$(".academicyearbased").hide();
			$(".termbased").hide();
		}
	});
	
	if($("#setupId").val().trim() != ""){
		$("#save").text("Update");
		$("#reporttype option[value="+$("#hreporttype").val().trim()+"]").prop("selected",true);
		$("#maxlimit").val($("#hmaxlimit").val());
		if($("#hreporttype").val().trim() == "termbased"){
			$(".termbased").show();
			$(".academicyearbased").hide();
		}
		if($("#hreporttype").val().trim() == "academic"){
			$(".academicyearbased").show();
			$(".termbased").hide();
		}
		getReportSetupDetails();
	}

	maxlimit=$("#maxlimit").val();
	/*Term I*/
	$('#periodicexam').change(function(){
		var periodicexam1=$('#periodicexam1').val();
		var periodicexam=$(this).val();
		if(periodicexam1 == periodicexam){
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Term I Periodic Exam Should Not Be Equal to Term II Periodic Exam");
    		$("#periodicexam").focus();
    		document.getElementById("periodicexam").style.border = "1px solid #AF2C2C";
    		document.getElementById("periodicexam").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("periodicexam").style.border = "1px solid #ccc";
				document.getElementById("periodicexam").style.backgroundColor = "#fff";
    		}, 1000);
			$(this).val("");
		}else{
			$("#periodicexam1  option[value='"+periodicexam1+"' ]").attr('selected', 'true');
		}
	});
	
	$("#periodictest").change(function(){
		var periodictest=$(this).val();
		var notebook=$("#notebook").val();
		var subenrichment=$("#subenrichment").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(periodictest) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term I Total Mark Should Be Equal To Max.Limit");
	    		$("#periodictest").focus();
	    		document.getElementById("periodictest").style.border = "1px solid #AF2C2C";
	    		document.getElementById("periodictest").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("periodictest").style.border = "1px solid #ccc";
					document.getElementById("periodictest").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#periodictest").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(notebook)-Number(subenrichment));
			}else{
				$("#halfyearly").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Periodic Test Mark Should Not Be 0");
    		$("#periodictest").focus();
    		document.getElementById("periodictest").style.border = "1px solid #AF2C2C";
    		document.getElementById("periodictest").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("periodictest").style.border = "1px solid #ccc";
				document.getElementById("periodictest").style.backgroundColor = "#fff";
    		}, 2000);
			$("#periodictest").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(notebook)-Number(subenrichment));
		}
		
	});
	
	$("#notebook").change(function(){
		var notebook=$(this).val();
		var periodictest=$("#periodictest").val();
		var subenrichment=$("#subenrichment").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(notebook) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term I Total Mark Should Be Equal To Max.Limit");
	    		$("#notebook").focus();
	    		document.getElementById("notebook").style.border = "1px solid #AF2C2C";
	    		document.getElementById("notebook").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("notebook").style.border = "1px solid #ccc";
					document.getElementById("notebook").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#notebook").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(periodictest)-Number(subenrichment));
			}else{
				$("#halfyearly").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Note Book Mark Should Not Be 0");
    		$("#notebook").focus();
    		document.getElementById("notebook").style.border = "1px solid #AF2C2C";
    		document.getElementById("notebook").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("notebook").style.border = "1px solid #ccc";
				document.getElementById("notebook").style.backgroundColor = "#fff";
    		}, 2000);
    		$("#notebook").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(periodictest)-Number(subenrichment));
		}
	});
	
	$("#subenrichment").change(function(){
		var subenrichment=$(this).val();
		var periodictest=$("#periodictest").val();
		var notebook=$("#notebook").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(subenrichment) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term I Total Mark Should Be Equal To Max.Limit");
	    		$("#subenrichment").focus();
	    		document.getElementById("subenrichment").style.border = "1px solid #AF2C2C";
	    		document.getElementById("subenrichment").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("subenrichment").style.border = "1px solid #ccc";
					document.getElementById("subenrichment").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#subenrichment").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(periodictest)-Number(notebook));
			}else{
				$("#halfyearly").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Sub Enrichment Mark Should Not Be 0");
    		$("#subenrichment").focus();
    		document.getElementById("subenrichment").style.border = "1px solid #AF2C2C";
    		document.getElementById("subenrichment").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("subenrichment").style.border = "1px solid #ccc";
				document.getElementById("subenrichment").style.backgroundColor = "#fff";
    		}, 2000);
    		$("#subenrichment").val(Number(maxlimit)-Number($("#halfyearly").val())-Number(periodictest)-Number(notebook));
		}
	});
	
	/*Term II*/
	$('#periodicexam1').change(function(){
		var periodicexam=$('#periodicexam').val();
		var periodicexam1=$(this).val();
		if(periodicexam == periodicexam1){
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Term II Periodic Exam Should Not Be Equal to Term I Periodic Exam");
    		$("#periodicexam1").focus();
    		document.getElementById("periodicexam1").style.border = "1px solid #AF2C2C";
    		document.getElementById("periodicexam1").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("periodicexam1").style.border = "1px solid #ccc";
				document.getElementById("periodicexam1").style.backgroundColor = "#fff";
    		}, 1000);
			$(this).val("");
		}else{
			$("#periodicexam  option[value='"+periodicexam+"' ]").attr('selected', 'true');
		}
	});
	
	$("#periodictest1").change(function(){
		var periodictest=$(this).val();
		var notebook=$("#notebook1").val();
		var subenrichment=$("#subenrichment1").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(periodictest) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term II Total Mark Should Be Equal To Max.Limit");
	    		$("#periodictest1").focus();
	    		document.getElementById("periodictest1").style.border = "1px solid #AF2C2C";
	    		document.getElementById("periodictest1").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("periodictest1").style.border = "1px solid #ccc";
					document.getElementById("periodictest1").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#periodictest1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(notebook)-Number(subenrichment));
			}else{
				$("#halfyearly1").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Periodic Test Mark Should Not Be 0");
    		$("#periodictest1").focus();
    		document.getElementById("periodictest1").style.border = "1px solid #AF2C2C";
    		document.getElementById("periodictest1").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("periodictest1").style.border = "1px solid #ccc";
				document.getElementById("periodictest1").style.backgroundColor = "#fff";
    		}, 2000);
    		$("#periodictest1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(notebook)-Number(subenrichment));
		}
	});
	
	$("#notebook1").change(function(){
		var notebook=$(this).val();
		var periodictest=$("#periodictest1").val();
		var subenrichment=$("#subenrichment1").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(notebook) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term II Total Mark Should Be Equal To Max.Limit");
	    		$("#notebook1").focus();
	    		document.getElementById("notebook1").style.border = "1px solid #AF2C2C";
	    		document.getElementById("notebook1").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("notebook1").style.border = "1px solid #ccc";
					document.getElementById("notebook1").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#notebook1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(periodictest)-Number(subenrichment));
			}else{
				$("#halfyearly1").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Note Book Mark Should Not Be 0");
    		$("#notebook1").focus();
    		document.getElementById("notebook1").style.border = "1px solid #AF2C2C";
    		document.getElementById("notebook1").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("notebook1").style.border = "1px solid #ccc";
				document.getElementById("notebook1").style.backgroundColor = "#fff";
    		}, 2000);
    		$("#notebook1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(periodictest)-Number(subenrichment));
		}
	});
	
	$("#subenrichment1").change(function(){
		var subenrichment=$(this).val();
		var periodictest=$("#periodictest1").val();
		var notebook=$("#notebook1").val();
		var total=Number(periodictest)+Number(notebook)+Number(subenrichment);
		if(Number(subenrichment) > 0){
			if(total > maxlimit){
				$(".errormessagediv").show();
	        	$(".validateTips").css({
	        		'width':'300px'
	        	});
	    		$(".validateTips").text("Term II Total Mark Should Be Equal To Max.Limit");
	    		$("#subenrichment1").focus();
	    		document.getElementById("subenrichment1").style.border = "1px solid #AF2C2C";
	    		document.getElementById("subenrichment1").style.backgroundColor = "#FFF7F7";
	    		setTimeout(function() {
	    			$('#errorhover').fadeOut();
	    			document.getElementById("subenrichment1").style.border = "1px solid #ccc";
					document.getElementById("subenrichment1").style.backgroundColor = "#fff";
	    		}, 2000);
				$("#subenrichment1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(periodictest)-Number(notebook));
			}else{
				$("#halfyearly1").val(Number(maxlimit)-total);
			}
		}else{
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Sub Enrichment Mark Should Not Be 0");
    		$("#subenrichment1").focus();
    		document.getElementById("subenrichment1").style.border = "1px solid #AF2C2C";
    		document.getElementById("subenrichment1").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("subenrichment1").style.border = "1px solid #ccc";
				document.getElementById("subenrichment1").style.backgroundColor = "#fff";
    		}, 2000);
    		$("#subenrichment1").val(Number(maxlimit)-Number($("#halfyearly1").val())-Number(periodictest)-Number(notebook));
		}
	});
	/*Academic Year*/
	$('#aperiodicexam').change(function(){
		var periodicexam1=$('#aperiodicexam1').val();
		var periodicexam=$(this).val();
		if(periodicexam == periodicexam1){
			$(".errormessagediv").show();
        	$(".validateTips").css({
        		'width':'300px'
        	});
    		$(".validateTips").text("Periodic Exam I Should Not Be Equal to Periodic Exam II");
    		$("#aperiodicexam").focus();
    		document.getElementById("aperiodicexam").style.border = "1px solid #AF2C2C";
    		document.getElementById("aperiodicexam").style.backgroundColor = "#FFF7F7";
    		setTimeout(function() {
    			$('#errorhover').fadeOut();
    			document.getElementById("aperiodicexam").style.border = "1px solid #ccc";
				document.getElementById("aperiodicexam").style.backgroundColor = "#fff";
    		}, 1000);
			$(this).val("");
		}else{
			$("#aperiodicexam1 option[value='"+periodicexam1+"' ]").attr('selected', 'true');
		}
	});
	$('#aperiodicexam1').change(function(){
		var periodicexam=$('#aperiodicexam').val();
		var periodicexam1=$(this).val();
		if(periodicexam == periodicexam1){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Periodic Exam II Should Not Be Equal to Periodic Exam I");
			$("#aperiodicexam1").focus();
			document.getElementById("aperiodicexam1").style.border = "1px solid #AF2C2C";
			document.getElementById("aperiodicexam1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("aperiodicexam1").style.border = "1px solid #ccc";
				document.getElementById("aperiodicexam1").style.backgroundColor = "#fff";
			}, 1000);
			$(this).val("");
		}else{
			$("#aperiodicexam  option[value='"+periodicexam+"' ]").attr('selected', 'true');
		}
	});
	
	$("#aperiodictest").change(function(){
		var periodictest=$(this).val();
		var periodictest1=$("#aperiodictest1").val();
		var halfyearlymark=$("#halfyearlymark").val();
		var yearlytheorymarks=$("#yearlytheorymarks").val();
		var practicalmark=$("#practicalmark").val();
		var total=Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark);
		if(total > maxlimit){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit");
			$("#aperiodictest").focus();
			document.getElementById("aperiodictest").style.border = "1px solid #AF2C2C";
			document.getElementById("aperiodictest").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("aperiodictest").style.border = "1px solid #ccc";
				document.getElementById("aperiodictest").style.backgroundColor = "#fff";
			}, 2000);
			$("#aperiodictest").val(Number(maxlimit)-(Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark)));
		}
	});
	
	$("#aperiodictest1").change(function(){
		var periodictest=$("#aperiodictest").val();
		var periodictest1=$(this).val();
		var halfyearlymark=$("#halfyearlymark").val();
		var yearlytheorymarks=$("#yearlytheorymarks").val();
		var practicalmark=$("#practicalmark").val();
		var total=Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark);
		if(total > maxlimit){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit");
			$("#aperiodictest1").focus();
			document.getElementById("aperiodictest1").style.border = "1px solid #AF2C2C";
			document.getElementById("aperiodictest1").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("aperiodictest1").style.border = "1px solid #ccc";
				document.getElementById("aperiodictest1").style.backgroundColor = "#fff";
			}, 2000);
			$("#aperiodictest1").val(Number(maxlimit)-(Number(periodictest)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark)));
		}
	});
	
	$("#halfyearlymark").change(function(){
		var periodictest=$("#aperiodictest").val();
		var periodictest1=$("#aperiodictest1").val();
		var halfyearlymark=$(this).val();
		var yearlytheorymarks=$("#yearlytheorymarks").val();
		var practicalmark=$("#practicalmark").val();
		var total=Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark);
		if(total > maxlimit){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit");
			$("#halfyearlymark").focus();
			document.getElementById("halfyearlymark").style.border = "1px solid #AF2C2C";
			document.getElementById("halfyearlymark").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("halfyearlymark").style.border = "1px solid #ccc";
				document.getElementById("halfyearlymark").style.backgroundColor = "#fff";
			}, 2000);
			$("#halfyearlymark").val(Number(maxlimit)-(Number(periodictest)+Number(periodictest1)+Number(yearlytheorymarks)+Number(practicalmark)));
		}
	});
	
	$("#yearlytheorymarks").change(function(){
		var periodictest=$("#aperiodictest").val();
		var periodictest1=$("#aperiodictest1").val();
		var halfyearlymark=$("#halfyearlymark").val();
		var yearlytheorymarks=$(this).val();
		var practicalmark=$("#practicalmark").val();
		var total=Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark);
		if(total > maxlimit){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit");
			$("#yearlytheorymarks").focus();
			document.getElementById("yearlytheorymarks").style.border = "1px solid #AF2C2C";
			document.getElementById("yearlytheorymarks").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("yearlytheorymarks").style.border = "1px solid #ccc";
				document.getElementById("yearlytheorymarks").style.backgroundColor = "#fff";
			}, 2000);
			$("#yearlytheorymarks").val(Number(maxlimit)-(Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(practicalmark)));
		}
	});
	
	$("#practicalmark").change(function(){
		var periodictest=$("#aperiodictest").val();
		var periodictest1=$("#aperiodictest1").val();
		var halfyearlymark=$("#halfyearlymark").val();
		var yearlytheorymarks=$("#yearlytheorymarks").val();
		var practicalmark=$(this).val();
		var total=Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)+Number(practicalmark);
		if(total > maxlimit){
			$(".errormessagediv").show();
			$(".validateTips").css({
				'width':'300px'
			});
			$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit");
			$("#practicalmark").focus();
			document.getElementById("practicalmark").style.border = "1px solid #AF2C2C";
			document.getElementById("practicalmark").style.backgroundColor = "#FFF7F7";
			setTimeout(function() {
				$('#errorhover').fadeOut();
				document.getElementById("practicalmark").style.border = "1px solid #ccc";
				document.getElementById("practicalmark").style.backgroundColor = "#fff";
			}, 2000);
			$("#practicalmark").val(Number(maxlimit)-(Number(periodictest)+Number(periodictest1)+Number(halfyearlymark)+Number(yearlytheorymarks)));
		}
	});
	
	$("#save").click(function(){
		var termname = [];
		var periodictestmark = [],periodictestmark1 = [];
		var notebookmark = [];
		var subenrichmentmark = [];
		var yearlymark = [], halfyearlyexam=[],halfyearlymark=[];
		var periodicexam = [],periodicexam1 = [];
		var finalexam = [], finalpractical = [];
		var reporttype=$("#reporttype").val();
		var flag=true;
		var nonpracticalmarks=[];
		
		if(reporttype == "termbased"){
			$(".termbased").each(function(){
				termname.push($(this).find(".termname").val());
				periodictestmark.push($(this).find(".periodictest").val());
				periodictestmark1.push($(this).find(".periodictest1").val());
				notebookmark.push($(this).find(".notebook").val());
				subenrichmentmark.push($(this).find(".subenrichment").val());
				yearlymark.push($(this).find(".halfyearly").val());
				finalpractical.push($(this).find(".practicalmark").val());
				halfyearlymark.push($(this).find(".halfyearlymark").val());
				
				if($(this).find(".halfyearlyexam").val() != ""){
					halfyearlyexam.push($(this).find(".halfyearlyexam").val());
				}else{
					halfyearlyexam.push(" ");
				}
				
				if($(this).find(".periodicexam1").val() != ""){
					periodicexam1.push($(this).find(".periodicexam1").val());
				}else{
					periodicexam1.push(" ");
				}
				
				if($(this).find(".examname").val() != ""){
					periodicexam.push($(this).find(".examname").val());
				}else{
					periodicexam.push(" ");
				}
				
				if($(this).find(".commonexam").val() != ""){
					finalexam.push($(this).find(".commonexam").val());
				}else{
					finalexam.push(" ");
				}
			});
		}else if(reporttype == "academic"){
			var pt=$("#aperiodictest").val();
			var pt1=$("#aperiodictest1").val();
			var hym=$("#halfyearlymark").val();
			var ytm=$("#yearlytheorymarks").val();
			var ypm=$("#practicalmark").val();
			var npm=$("#nonpractialmark").val();
			
			var totalmarks=Number(pt)+Number(pt1)+Number(hym)+Number(ytm)+Number(ypm);
			var totalnonpracticalmarks=Number(pt)+Number(pt1)+Number(hym)+Number(npm);
			$(".academicyearbased").each(function(){
				termname.push($(this).find(".termname").val());
				if(Number($(this).find(".periodictest").val()) == 0){
					$(".errormessagediv").show();
					document.getElementById("aperiodictest").style.border = "1px solid #AF2C2C";
					document.getElementById("aperiodictest").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Periodic Test I Mark Should Not Be 0");
					setTimeout(function() {
						document.getElementById("aperiodictest").style.border = "1px solid #ccc";
						document.getElementById("aperiodictest").style.backgroundColor = "#fff";
						$('.errormessagediv').fadeOut();
					}, 3000);
					flag=false;
					return false;
				}else{
					periodictestmark.push($(this).find(".periodictest").val());
				}
				
				if(Number($(this).find(".periodictest1").val()) == 0){
					$(".errormessagediv").show();
					document.getElementById("aperiodictest1").style.border = "1px solid #AF2C2C";
					document.getElementById("aperiodictest1").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Periodic Test II Mark Should Not Be 0");
					setTimeout(function() {
						document.getElementById("aperiodictest1").style.border = "1px solid #ccc";
						document.getElementById("aperiodictest1").style.backgroundColor = "#fff";
						$('.errormessagediv').fadeOut();
					}, 3000);
					flag=false;
					return false;
				}else{
					periodictestmark1.push($(this).find(".periodictest1").val());
				}
				
				
				
				if(Number($(this).find(".halfyearly").val()) == 0){
					$(".errormessagediv").show();
					document.getElementById("yearlytheorymarks").style.border = "1px solid #AF2C2C";
					document.getElementById("yearlytheorymarks").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Yearly Exam Theory Mark Should Not Be 0");
					setTimeout(function() {
						document.getElementById("yearlytheorymarks").style.border = "1px solid #ccc";
						document.getElementById("yearlytheorymarks").style.backgroundColor = "#fff";
						$('.errormessagediv').fadeOut();
					}, 3000);
					flag=false;
					return false;
				}else{
					yearlymark.push($(this).find(".halfyearly").val());
				}
				
				if(Number($(this).find(".halfyearlymark").val()) == 0){
					$(".errormessagediv").show();
					document.getElementById("halfyearlymark").style.border = "1px solid #AF2C2C";
					document.getElementById("halfyearlymark").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Yearly Exam Theory Mark Should Not Be 0");
					setTimeout(function() {
						document.getElementById("halfyearlymark").style.border = "1px solid #ccc";
						document.getElementById("halfyearlymark").style.backgroundColor = "#fff";
						$('.errormessagediv').fadeOut();
					}, 3000);
					flag=false;
					return false;
				}else{
					halfyearlymark.push($(this).find(".halfyearlymark").val());
				}
				
				if(Number($(this).find(".nonpractialmark").val()) == 0){
					$(".errormessagediv").show();
					document.getElementById("nonpractialmark").style.border = "1px solid #AF2C2C";
					document.getElementById("nonpractialmark").style.backgroundColor = "#FFF7F7";
					$(".validateTips").text("Non Practical Subject Mark Should Not Be 0");
					setTimeout(function() {
						document.getElementById("nonpractialmark").style.border = "1px solid #ccc";
						document.getElementById("nonpractialmark").style.backgroundColor = "#fff";
						$('.errormessagediv').fadeOut();
					}, 3000);
					flag=false;
					return false;
				}else{
					nonpracticalmarks.push($(this).find(".nonpractialmark").val());
				}
				
				notebookmark.push(0);
				subenrichmentmark.push(0);
				finalpractical.push($(this).find(".practicalmark").val());
				
				
				if($(this).find(".halfyearlyexam").val() != ""){
					halfyearlyexam.push($(this).find(".halfyearlyexam").val());
				}else{
					halfyearlyexam.push(" ");
				}
				
				if($(this).find(".periodicexam1").val() != ""){
					periodicexam1.push($(this).find(".periodicexam1").val());
				}else{
					periodicexam1.push(" ");
				}
				
				if($(this).find(".periodicexam").val() != ""){
					periodicexam.push($(this).find(".periodicexam").val());
				}else{
					periodicexam.push(" ");
				}
				
				if($(this).find(".commonexam").val() != ""){
					finalexam.push($(this).find(".commonexam").val());
				}else{
					finalexam.push(" ");
				}
			});
		}
		
		if(reporttype == "" || reporttype == undefined){
			$(".errormessagediv").show();
			document.getElementById("reporttype").style.border = "1px solid #AF2C2C";
			document.getElementById("reporttype").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Field Required Report Type.");
			setTimeout(function() {
				document.getElementById("reporttype").style.border = "1px solid #ccc";
				document.getElementById("reporttype").style.backgroundColor = "#fff";
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}else{
			if(flag){
				if(Number(maxlimit) != Number(totalmarks) && reporttype == "academic"){
					$(".errormessagediv").show();
					$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit For Practical Subject.");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}else if(Number(maxlimit) != Number(totalnonpracticalmarks) && reporttype == "academic"){
					$(".errormessagediv").show();
					$(".validateTips").text("Academic Year Total Mark Should Be Equal To Max.Limit For Non-Practical Subject.");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					return false;
				}else{
					datalist = {
							"termname" : termname.toString(),
							"periodictestmark" : periodictestmark.toString(),
							"periodictestmark1" : periodictestmark1.toString(),
							"notebookmark" : notebookmark.toString(),
							"subenrichmentmark" : subenrichmentmark.toString(),
							"yearlytheorymork" : yearlymark.toString(),
							"yearlypractical" : finalpractical.toString(),
							"periodicexam" : periodicexam.toString(),
							"periodicexam1" : periodicexam1.toString(),
							"finalexam" : finalexam.toString(),
							"halfyearlyexam" : halfyearlyexam.toString(),
							"halfyearlymark" : halfyearlymark.toString(),
							"nonpracticalmark" : nonpracticalmarks.toString(),
							"academiyear" : $("#hiddenaccyear").val(),
							"location" : $("#hiddenloc").val(),
							"classid" : $("#hiddenclassid").val(),
							"maxlimit" : $("#maxlimit").val(),
							"reporttype" : reporttype,
							"setupId" : $("#setupId").val(),
					},
					$.ajax({
						type : "POST",
						url :"examCreationPath.html?method=insertReportSetupDetails",
						data : datalist,
						async : false,
						success: function(data) {
							var result = $.parseJSON(data);
							if(result.status == "success"){
								$(".successmessagediv").show();
								$(".validateTips").text("Adding Record in Progress...");
								setTimeout(function() {
									$('.successmessagediv').fadeOut();
									$('#backValue').val(accyearId+","+locationId);
									$('#backform').submit();
								}, 3000);
							}else if(result.status == "update"){
								$(".successmessagediv").show();
								$(".validateTips").text("Updating Record in Progress...");
								setTimeout(function() {
									$('.successmessagediv').fadeOut();
									$('#backValue').val(accyearId+","+locationId);
									$('#backform').submit();
								}, 3000);
							}else{
								$(".successmessagediv").hide();
								$(".errormessagediv").show();
								$(".validateTips").text("Fail To Add");
								setTimeout(function() {
									$('.errormessagediv').fadeOut();
									$('#backValue').val(accyearId+","+locationId);
									$('#backform').submit();
								}, 3000);
							}
						}
					});
				}
			}
		}
	});
});

function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}

function getPeriodicExamNameList(){
	var locationid=$("#hiddenloc").val();
	var classid=$("#hiddenclassid").val();
	var accyear=$("#hiddenaccyear").val();
	datalist={
			"locationid" : locationid,
			"classid" : classid,
			"accyear" : accyear,
	},
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getExamNameListByPeriodicExam",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('.examname').html("");
			
			$('.examname').append('<option value="">' +"----------Select----------"+ '</option>');
			
			for ( var j = 0; j < result.ExamNameList.length; j++) {
				
				$('.examname').append('<option value="'+ result.ExamNameList[j].examid + '">'
						
						+ result.ExamNameList[j].examName + '</option>');
			}
		}
	});
}

function getHalflyExamNameList(){
	var locationid=$("#hiddenloc").val();
	var classid=$("#hiddenclassid").val();
	var accyear=$("#hiddenaccyear").val();
	datalist={
			"locationid" : locationid,
			"classid" : classid,
			"accyear" : accyear,
	},
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getHalflyExamNameList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('.halflyexam').html("");
			
			$('.halflyexam').append('<option value="">' +"----------Select----------"+ '</option>');
			
			for ( var j = 0; j < result.ExamNameList.length; j++) {
				
				$('.halflyexam').append('<option value="'+ result.ExamNameList[j].examid +'">'
						
						+ result.ExamNameList[j].examName + '</option>');
			}
		}
	});
}

function getYearlyExamNameList(){
	var locationid=$("#hiddenloc").val();
	var classid=$("#hiddenclassid").val();
	var accyear=$("#hiddenaccyear").val();
	datalist={
			"locationid" : locationid,
			"classid" : classid,
			"accyear" : accyear,
	},
	$.ajax({
		type : 'POST',
		url : "examTimetablePath.html?method=getYearlyExamNameList",
		data : datalist,
		async : false,
		success : function(response) {
			
			var result = $.parseJSON(response);
			
			$('.yearlyexam').html("");
			
			$('.yearlyexam').append('<option value="">' +"----------Select----------"+ '</option>');
			
			for ( var j = 0; j < result.ExamNameList.length; j++) {
				
				$('.yearlyexam').append('<option value="'+ result.ExamNameList[j].examid +'">'
						
						+ result.ExamNameList[j].examName + '</option>');
			}
		}
	});
}

function getReportSetupDetails(){
	var setupId=$("#setupId").val();
	var reporttype=$("#hreporttype").val();
	$.ajax({
		type : "POST",
		url : "examCreationPath.html?method=getReportSetupDetails",
		data:{"location":$("#hiddenloc").val(),"accyear":$("#hiddenaccyear").val(),"classId":$("#hiddenclassid").val(),"setupId":setupId,"reporttype":reporttype},
		async : false,
		success : function(data){
			var result = $.parseJSON(data);
			
			if(result.reporttype == "termbased"){
				$("#periodicexam option[value="+result.term1periodicexam+"]").prop("selected",true);
				$("#halflyexam option[value="+result.term1finalexam+"]").prop("selected",true);
				$("#periodictest").val(result.term1periodicmark);
				$("#notebook").val(result.term1notebookmark);
				$("#subenrichment").val(result.term1subenrichmentmark);
				$("#halfyearly").val(result.term1theorymark);

				$("#periodicexam1 option[value="+result.term2periodicexam+"]").prop("selected",true);
				$("#halflyexam1 option[value="+result.term2finalexam+"]").prop("selected",true);
				$("#periodictest1").val(result.term2periodicmark);
				$("#notebook1").val(result.term2notebookmark);
				$("#subenrichment1").val(result.term2subenrichmentmark);
				$("#halfyearly1").val(result.term2theorymark);
			}else if(result.reporttype == "academic"){
				$("#aperiodicexam option[value="+result.academicperiodicexam+"]").prop("selected",true);
				$("#aperiodicexam1 option[value="+result.academicperiodicexam1+"]").prop("selected",true);
				$("#ahalflyexam option[value="+result.academichalfyearlyexam+"]").prop("selected",true);
				$("#ayearlyexam option[value="+result.academicyearlyexam+"]").prop("selected",true);
			
				$("#aperiodictest").val(result.academicperiodicmark);
				$("#aperiodictest1").val(result.academicperiodicmark1);
				$("#halfyearlymark").val(result.academichalfyearlymark);
				$("#nonpractialmark").val(result.academictheorymark);
				$("#practicalmark").val(result.academicpracticalmark);
				$("#yearlytheorymarks").val(result.academicnonpracticalmark);
			}
			
		}
	});
}
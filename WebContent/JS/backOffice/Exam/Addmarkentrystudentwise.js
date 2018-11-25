$(document).ready(function() {
	hiddenspecid=$("#hiddenspecid").val();
	if(hiddenspecid !="-"){
		$("#hiddensectionlabel").text("Specialization Name");
	}
	$(".statusvalue ").change(function(){
		if($(this).val()=='Absent'){
		$(this).closest('tr').find('#notebook').val(0);
		$(this).closest('tr').find('#notebook').attr("readonly","readonly");
		$(this).closest('tr').find('#subjectenrichment').val(0);
		$(this).closest('tr').find('#subjectenrichment').attr("readonly","readonly");
		$(this).closest('tr').find('#subjectenrichment').val(0);
		$(this).closest('tr').find('#subjectenrichment').attr("readonly","readonly");
		$(this).closest('tr').find('#scoredhalfyearly').val(0);
		$(this).closest('tr').find('#scoredhalfyearly').attr("readonly","readonly");
		$(this).closest('tr').find('#scoredperiodic').val(0);
		$(this).closest('tr').find('#scoredperiodic').attr("readonly","readonly");
		
		}else{
			$(this).closest('tr').find('#notebook').removeAttr("readonly");
			$(this).closest('tr').find('#scoredhalfyearly').removeAttr("readonly");
			$(this).closest('tr').find('#subjectenrichment').removeAttr("readonly");
			$(this).closest('tr').find('#notebook').removeAttr("readonly");
		}
	});
	
	
	var hexamtype=$("#hiddenexamtypeprefix").val();
	var classidhidden=$("#classidhidden").val();
	
	/*if(classidhidden == "CCD12" || classidhidden == "CCD13"){
		
	}else{
		$(".selectgrade option[value='D']").remove();
		$(".selectgrade option[value='E']").remove();
	}*/
	if(hexamtype == "prdxm"){
		$(".dperiodictest").css({
			"display":"table-cell"
		});
		$(".work").hide();
		$(".art").hide();
		$(".discipline").hide();
		$(".health").hide();
	}else if(hexamtype == "hlfym"){
		if(classidhidden == "CCD14" || classidhidden == "CCD15"){
			$(".dhalfyearly,.maxhalfyearly,.scoredhalfyearly").css({
				"width" : "200px",
				"text-align" : "center"
			});
		}else{
			$(".dnotebook").css({
				"display":"table-cell"
			});
			$(".dsubjectenrichment").css({
				"display":"table-cell"
			});
		}
		$(".dhalfyearly").css({
			"display":"table-cell"
		});
		
		$(".work").show();
		$(".art").show();
		$(".discipline").show();
		$(".health").show();
	}else if(hexamtype == "yrlym"){
		$(".chgname").text("Yearly Marks");
		if(classidhidden == "CCD14" || classidhidden == "CCD15"){
			$(".dhalfyearly,.maxhalfyearly,.scoredhalfyearly").css({
				"width" : "200px",
				"text-align" : "center"
			});
		}else{
			$(".dnotebook").css({
				"display":"table-cell"
			});
			$(".dsubjectenrichment").css({
				"display":"table-cell"
			});
		}
		$(".dhalfyearly").css({
			"display":"table-cell"
		});
		$(".work").show();
		$(".art").show();
		$(".discipline").show();
		$(".health").show();
	}
	
	if($("#hiddenwork").val() != ""){
		$("#workedu option[value="+$("#hiddenwork").val()+"]").attr('selected', 'true');
	}
	if($("#hiddenart").val() != ""){
		$("#artedu option[value="+$("#hiddenart").val()+"]").attr('selected', 'true');
	}
	if($("#hiddenhealth").val() != ""){
		$("#healthedu option[value="+$("#hiddenhealth").val()+"]").attr('selected', 'true');
	}
	if($("#hiddendiscipline").val() != ""){
		$("#disciplineedu option[value="+$("#hiddendiscipline").val()+"]").attr('selected', 'true');
	}
	
	$(".statusvalue").change(function(){
		if(this.value=="Absent"){
			$(this).closest("tr").find(".scoredmarks").val(0);
			$(this).closest("tr").find(".scoredmarks").attr("readonly",true);
		}
		else{
			$(this).closest("tr").find(".scoredmarks").val("");
			$(this).closest("tr").find(".scoredmarks").attr("readonly",false);
		}

	});

	$(".scoredperiodic").keyup(function(){
		$(this).css({'border-color':'#FFF'});
		var maxmarks = $(this).closest("tr").find(".maxperiodic").val(); 
		marks = parseInt(maxmarks);
		var getData = $(this).val();
		data = parseInt(getData);
		if($(this).val() < 0)
		{
			$(".errormessagediv").show();
			$(".scoredperiodic").val("");
			document.getElementsByClassName("scoredperiodic").style.border = "1px solid #AF2C2C";
			document.getElementsByClassName("scoredperiodic").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Scored Marks Should be Greater than or Equal to 0");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}

		else if(data > marks){
			$(this).val("");
			$(this).css({'border-color':'red'});
			$(".errormessagediv").show();
			$(".validateTips").text("Scored Marks Should be Less Than or Equal to Max.Marks");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$(".subjectenrichment").keyup(function(){
		$(this).css({'border-color':'#FFF'});
		var maxmarks = $(this).closest("tr").find(".maxsubjectenrichment").val(); 
		marks = parseInt(maxmarks);
		var getData = $(this).val();
		data = parseInt(getData);
		if($(this).val() < 0)
		{
			$(".errormessagediv").show();
			$(".subjectenrichment").val("");
			document.getElementsByClassName("subjectenrichment").style.border = "1px solid #AF2C2C";
			document.getElementsByClassName("subjectenrichment").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Scored Marks Should be Greater than or Equal to 0");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if(data > marks){
			$(this).val("");
			$(this).css({'border-color':'red'});
			$(".errormessagediv").show();
			$(".validateTips").text("Scored Marks Should be Less Than or Equal to Max.Marks");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	$(".notebook").keyup(function(){
		$(this).css({'border-color':'#FFF'});
		var maxmarks = $(this).closest("tr").find(".maxnotebook").val(); 
		marks = parseInt(maxmarks);
		var getData = $(this).val();
		data = parseInt(getData);
		if($(this).val() < 0)
		{
			$(".errormessagediv").show();
			$(".notebook").val("");
			document.getElementsByClassName("notebook").style.border = "1px solid #AF2C2C";
			document.getElementsByClassName("notebook").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Scored Marks Should be Greater than or Equal to 0");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if(data > marks){
			$(this).val("");
			$(this).css({'border-color':'red'});
			$(".errormessagediv").show();
			$(".validateTips").text("Scored Marks Should be Less Than or Equal to Max.Marks");
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});
	
	$(".scoredhalfyearly").keyup(function(){
		$(this).css({'border-color':'#FFF'});
		var maxmarks = $(this).closest("tr").find(".maxhalfyearly").val(); 
		marks = parseInt(maxmarks);
		var getData = $(this).val();
		data = parseInt(getData);
		if($(this).val() < 0)
		{
			$(".errormessagediv").show();
			$(".scoredhalfyearly").val("");
			document.getElementsByClassName("scoredhalfyearly").style.border = "1px solid #AF2C2C";
			document.getElementsByClassName("scoredhalfyearly").style.backgroundColor = "#FFF7F7";
			$(".validateTips").text("Scored Marks Should be Greater than or Equal to 0");
			setTimeout(function() {
				$(".scoredhalfyearly").css({'border-color':'#FFF'});
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
		
		else if(data > marks){
			$(this).val("");
			$(this).css({'border-color':'red'});
			$(".errormessagediv").show();
			$(".validateTips").text("Scored Marks Should be Less Than or Equal to Max.Marks");
			setTimeout(function() {
				$(this).css({'border-color':'#FFF'});
				$('.errormessagediv').fadeOut();
			}, 3000);
			return false;
		}
	});

	$("#save").click(function(){
		
		var notebookmarks=[];
		var maxnotebookmarks=[];
		
		var subjectenrichmarks=[];
		var maxsubjectenrichmarks=[];
		
		var periodicscoredmarks=[];
		var maxperiodicmarks=[];
		
		var halflyscoredmarks=[];
		var maxhalflymarks=[];
		
		var statusvalues=[];
		var subids=[];
		var primaryidstu=[];
		
		
		
		var saveFlag=true;
		var errorflag=false;
		var classId =$("#classidhidden").val();
		var sectionId =$("#hiddensectionid").val();
		var examid = $("#hiddenexamid").val();
		var accyear = $("#hiddenaccyid").val();
		var studentid = $("#hiddenstudentid").val();
		var hschoolLocation = $("#hiddenlocation").val();
		var workedu = $("#workedu").val();
		var artedu = $("#artedu").val();
		var disciplineedu = $("#disciplineedu").val();
		var healthedu = $("#healthedu").val();
		var examtypeprfix = $("#hiddenexamtypeprefix").val();

		$('#allstudent tbody tr').each(function(){
			
			if(examtypeprfix=="prdxm"){
				if($(this).find(".maxperiodic").val()==""||$(this).find(".maxperiodic").val()=="0"){
					$(".errormessagediv").show();
					$(".validateTips").text("Maximum Marks Should Not Be Empty / '0' ");
					$(this).find(".maxperiodic").css({"border":"1px solid #AF2C2C"});
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
						$(this).find(".maxperiodic").css({"border":""});
					}, 3000);
					errorflag=true;
					return false;
				}
			}
			else if(examtypeprfix=="hlfym" || examtypeprfix=="yrlym"){
				if(classId == "CCD14" || classId =="CCD15"){
					if($(this).find(".maxhalfyearly").val()=="" || $(this).find(".maxhalfyearly").val()=="0"){
						$(".errormessagediv").show();
						$(".validateTips").text("Maximum Marks Should Not Be Empty / '0'");
						$(this).find(".maxhalfyearly").css({"border":"1px solid #AF2C2C"});
						setTimeout(function() {
							$(this).find(".maxperiodic").css({"border":""});
							$('.errormessagediv').fadeOut();
						}, 3000);
						errorflag=true;
						return false;
					}
				}else{
					if($(this).find(".maxnotebook").val()==""||$(this).find(".maxnotebook").val()=="0"){
						$(".errormessagediv").show();
						$(".validateTips").text("Maximum Marks Should Not Be Empty / '0'");
						$(this).find(".maxnotebook").css({"border":"1px solid #AF2C2C"});
						setTimeout(function() {
							$(this).find(".maxperiodic").css({"border":""});
							$('.errormessagediv').fadeOut();
						}, 3000);
						errorflag=true;
						return false;
					}
					if($(this).find(".maxsubjectenrichment").val()==""||$(this).find(".maxsubjectenrichment").val()=="0"){
						$(".errormessagediv").show();
						$(".validateTips").text("Maximum Marks Should Not Be Empty / '0'");
						$(this).find(".maxsubjectenrichment").css({"border":"1px solid #AF2C2C"});
						setTimeout(function() {
							$(this).find(".maxperiodic").css({"border":""});
							$('.errormessagediv').fadeOut();
						}, 3000);
						errorflag=true;
						return false;
					}
					if($(this).find(".maxhalfyearly").val()==""||$(this).find(".maxhalfyearly").val()=="0"){
						$(".errormessagediv").show();
						$(".validateTips").text("Maximum Marks Should Not Be Empty / '0'");
						$(this).find(".maxhalfyearly").css({"border":"1px solid #AF2C2C"});
						setTimeout(function() {
							$(this).find(".maxperiodic").css({"border":""});
							$('.errormessagediv').fadeOut();
						}, 3000);
						errorflag=true;
						return false;
					}
				}
			}
			
			/*pertest = $(this).find(".pertest").val();*/
			notebook = $(this).find(".notebook").val();
			maxnotebook = $(this).find(".maxnotebook").val();
			subjectenrichment = $(this).find(".subjectenrichment").val();
			maxsubjectenrichment = $(this).find(".maxsubjectenrichment").val();
			perioicscoredmark = $(this).find(".scoredperiodic").val();
			maxperiodicmark = $(this).find(".maxperiodic").val();
			scoredhalfyearly = $(this).find(".scoredhalfyearly").val();
			maxhalfyearly = $(this).find(".maxhalfyearly").val();
			
			attendance = $(this).find(".statusvalue").val();
			subprimid = $(this).find(".subprimid").val();
			subid = $(this).find(".subid").val();

			if(maxnotebook == "" || maxnotebook == undefined ){
				maxnotebook=0;
				maxnotebookmarks.push(maxnotebook);
			}else{
				maxnotebookmarks.push(maxnotebook);
			}
			if(notebook == "" || notebook == undefined ){
				notebook=0;
				notebookmarks.push(notebook);
			}else{
				notebookmarks.push(notebook);
			}

			if(maxsubjectenrichment == "" || maxsubjectenrichment == undefined ){
				maxsubjectenrichment=0;
				maxsubjectenrichmarks.push(maxsubjectenrichment);
			}else{
				maxsubjectenrichmarks.push(maxsubjectenrichment);
			}
			if(subjectenrichment == "" || subjectenrichment == undefined ){
				subjectenrichment=0;
				subjectenrichmarks.push(subjectenrichment);
			}else{
				subjectenrichmarks.push(subjectenrichment);
			}

			if(maxperiodicmark == "" || maxperiodicmark == undefined ){
				maxperiodicmark=0;
				maxperiodicmarks.push(maxperiodicmark);
			}else{
				maxperiodicmarks.push(maxperiodicmark);
			}
			if(perioicscoredmark == "" || perioicscoredmark == undefined ){
				perioicscoredmark=0;
				periodicscoredmarks.push(perioicscoredmark);
			}else{
				periodicscoredmarks.push(perioicscoredmark);
			}
			
			if(maxhalfyearly == "" || maxhalfyearly == undefined ){
				maxhalfyearly=0;
				maxhalflymarks.push(maxhalfyearly);
			}else{
				maxhalflymarks.push(maxhalfyearly);
			}
			if(scoredhalfyearly == "" || scoredhalfyearly == undefined ){
				scoredhalfyearly=0;
				halflyscoredmarks.push(scoredhalfyearly);
			}else{
				halflyscoredmarks.push(scoredhalfyearly);
			}

			statusvalues.push(attendance);
			subids.push(subid);
			primaryidstu.push(subprimid);
		});
		if(errorflag){
			return false;
		}
		var datalist = {
				"classId":classId,
				"sectionId":sectionId,
				"examid":examid,
				"accyear":accyear,
				"statusvalues":statusvalues,
				"subids":subids,
				"studentid":studentid,
				"hschoolLocation":hschoolLocation,
				"primaryidstu":primaryidstu,
				"notebookmarks":notebookmarks,
				"maxnotebookmarks":maxnotebookmarks,
				"subjectenrichment":subjectenrichmarks,
				"maxsubjectenrichmarks":maxsubjectenrichmarks,
				"periodicscoredmarks":periodicscoredmarks,
				"maxperiodicmarks":maxperiodicmarks,
				"scoredmarks":halflyscoredmarks,
				"maxscoredmarks":maxhalflymarks,
				"workedu":workedu,
				"artedu":artedu,
				"disciplineedu":disciplineedu,
				"healthedu":healthedu,
				"examtypeprfix":examtypeprfix,
		};
		if(saveFlag){
			$("#loader").show();
			$.ajax({
				type : "POST",
				url : "examTimetablePath.html?method=SaveMarkEntryStudentWise",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status =="inserted" ) {
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$("#loader").hide();
						$(".validateTips").text("Adding Marks in Progress...");
						var classId =$("#classidhidden").val();
						var sectionId =$("#hiddensectionid").val();
						var examid = $("#hiddenexamid").val();
						var accyear = $("#hiddenaccyid").val();
						var hschoolLocation = $("#hiddenlocation").val();
						hiddenspecid=$("#hiddenspecid").val();
						setTimeout(function(){
							/*window.location.href="examTimetablePath.html?method=setMarkEntryStudentwise&classId="+classId+
							"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation+
							"&specId="+hiddenspecid+"&historyacayearId="+$("#historyacayearId").val()+"&historylocId="+$("#historylocId").val();*/ 
							$("#myValue").val(accyear+","+hschoolLocation+","+classId+","+sectionId+","+examid+","+hiddenspecid);
					  		$("#myform").submit();
						},3000);
					}
				}
			});
		}
	});

	$("#back1").click(function(){
		var examid =$("#hiddenexamid").val();
		var classId=$("#classidhidden").val();
		var sectionId=$("#hiddensectionid").val();
		var accyear=$("#hiddenaccyid").val();
		hschoolLocation=$("#hiddenlocation").val();
		hiddenspecid=$("#hiddenspecid").val();
		
		/*window.location.href="examTimetablePath.html?method=setMarkEntryStudentwise&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation+
		"&specId="+hiddenspecid+"&historyacayearId="+$("#historyacayearId").val()+"&historylocId="+$("#historylocId").val();*/ 
		
		$("#myValue").val(accyear+","+hschoolLocation+","+classId+","+sectionId+","+examid+","+hiddenspecid);
  		$("#myform").submit();
	});


	$('#allstudent tbody tr[id]').each(function(){

		var status=$(this).find('select.statusvalue').attr('class');

		if(status.split(" ")[1]!=""){
			status1 = status.split(" ")[1];

			var rowid=$(this).find('.statusvalue').attr("id");

			$('#'+rowid).find('option').remove();

			var statusList=[];
			statusList.push("Present");
			statusList.push("Absent");

			for (var j = 0; j < statusList.length; j++) {


				$("#"+rowid).append(
						'<option value="'
						+ statusList[j]
						+ '">'
						+  statusList[j]
						+ '</option>');
			}
			
			if(status1 != undefined)
				$("#"+rowid+" option[value="+status1+"]").attr('selected', 'true');
			
			if(status1=="Absent"){
				$(this).closest("tr").find(".scoredmarks").val(0);
				$(this).closest("tr").find(".scoredmarks").attr("readonly",true);
			}
		}
	});
	
	$('#allstudent tbody tr').each(function(){
		if($(this).find(".statusvalue ").val()=='Absent'){
			$(this).closest('tr').find('#notebook').val(0);
			$(this).closest('tr').find('#notebook').attr("readonly","readonly");
			$(this).closest('tr').find('#subjectenrichment').val(0);
			$(this).closest('tr').find('#subjectenrichment').attr("readonly","readonly");
			$(this).closest('tr').find('#subjectenrichment').val(0);
			$(this).closest('tr').find('#subjectenrichment').attr("readonly","readonly");
			$(this).closest('tr').find('#scoredhalfyearly').val(0);
			$(this).closest('tr').find('#scoredhalfyearly').attr("readonly","readonly");
			$(this).closest('tr').find('#scoredperiodic').val(0);
			$(this).closest('tr').find('#scoredperiodic').attr("readonly","readonly");

		}
	});
	
});
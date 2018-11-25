$(document).ready(function(){
	$(".statusvalue ").change(function(){
		if($(this).val()=="Absent"){
			$(this).closest('tr').find(".notebook").val(0);
			$(this).closest('tr').find(".subjectenrichment").val(0);
			$(this).closest('tr').find(".scoredperiodic").val(0);
			$(this).closest('tr').find(".scoredhalfyearly").val(0);
			$(this).closest('tr').find(".notebook").attr("readonly","readonly");
			$(this).closest('tr').find(".subjectenrichment").attr("readonly","readonly");
			$(this).closest('tr').find(".scoredperiodic").attr("readonly","readonly");
			$(this).closest('tr').find(".scoredhalfyearly").attr("readonly","readonly");
		}
		else{
			$(this).closest('tr').find(".notebook").removeAttr("readonly");
			$(this).closest('tr').find(".subjectenrichment").removeAttr("readonly");
			$(this).closest('tr').find(".scoredperiodic").removeAttr("readonly");
			$(this).closest('tr').find(".scoredhalfyearly").removeAttr("readonly");
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


	var hexamtype=$("#hiddenexamtypeprefix").val();
	if(hexamtype=="prdxm"){
		$('.dhalfyearly').hide();
		$('.dnotebook').hide();
		$('.dsubjectenrichment').hide();
		$('.dperiodictest').show();
	}else{
		$('.dperiodictest').hide();
		$('.dhalfyearly').show();
		$('.dnotebook').show();
		$('.dsubjectenrichment').show();
	}

	var saveFlag=true;
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
	if(hexamtype="prdxm"){
		if($("#tablesize").val() == "nodata"){
			$("#allstudent tbody").append("<tr><td colspan='10'>No Records Found</td></tr>");
		}
	}else{
		if($("#tablesize").val() == "nodata"){
			$("#allstudent tbody").append("<tr><td colspan='6'>No Records Found</td></tr>");
		}
	}

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

	/*
	$(".scoredmarks").blur(function(){

		var maxmarks = $(this).closest("tr").find(".totalmarks").text(); 
			marks = parseInt(maxmarks);
		var getData = $(this).val();
			data = parseInt(getData);
			 if(data < 0)
		        {
				 $(".errormessagediv").show();
					$(".scoredmarks").val("");
					document.getElementsByClassName("scoredmarks").style.border = "1px solid #AF2C2C";
					document.getElementsByClassName("scoredmarks").style.backgroundColor = "#FFF7F7";
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
					 $(".validateTips").text("Scored Marks Should be Less Than Maximum Marks");
					 setTimeout(function() {
							$('.errormessagediv').fadeOut();
						}, 3000);
					 return false;
			}
	});*/
	$(".maxperiodic").keyup(function(){
		$(".maxperiodic").val($(".maxperiodic").val());
	});
	$(".maxnotebook").keyup(function(){
		$(".maxnotebook").val($(".maxnotebook").val());
	});
	$(".maxsubjectenrichment").keyup(function(){
		$(".maxsubjectenrichment").val($(".maxsubjectenrichment").val());
	});
	$(".maxhalfyearly").keyup(function(){
		$(".maxhalfyearly").val($(".maxhalfyearly").val());
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
		//var subids=[];
		var primaryidstu=[];
		var studentId=[];


		var saveFlag=true;
		var accyear = $("#hiddenaccyid").val();
		var hschoolLocation = $("#hiddenlocid").val();
		var subid = $("#hiddensubid").val();
		var classId =$("#classhiddenid").val();
		var examid = $("#hiddenexamid").val();
		var sectionId =$("#hiddensectionid").val();

		//	var studentid = $("#hiddenstudentid").val();
		//	var workedu = $("#workedu").val();
		//	var artedu = $("#artedu").val();
		//	var disciplineedu = $("#disciplineedu").val();
		//	var healthedu = $("#healthedu").val();
		var examtypeprfix = $("#hiddenexamtypeprefix").val();

		var errorflag=false;
		$('#allstudent tbody tr').each(function(){

			if(examtypeprfix=="prdxm"){
				if($(".maxperiodic").val()==""||$(".maxperiodic").val()=="0"){
					$(".errormessagediv").show();
					$(".validateTips").text("Maximum Marks Should Not Be Empty");
					$(this).find(".maxperiodic").css({"border":"1px solid #AF2C2C"});
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					errorflag=true;
					return false;
				}
			}
			else if(examtypeprfix=="hlfym"||examtypeprfix=="yrlym"){
				if($(".maxnotebook").val()==""||$(".maxnotebook").val()=="0"){
					$(".errormessagediv").show();
					$(".validateTips").text("Maximum Marks Should Not Be Empty");
					$(this).find(".maxnotebook").css({"border":"1px solid #AF2C2C"});
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					errorflag=true;
					return false;
				}
				if($(".maxsubjectenrichment").val()==""||$(".maxsubjectenrichment").val()=="0"){
					$(".errormessagediv").show();
					$(".validateTips").text("Maximum Marks Should Not Be Empty");
					$(this).find(".maxsubjectenrichment").css({"border":"1px solid #AF2C2C"});
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					errorflag=true;
					return false;
				}
				if($(".maxhalfyearly").val()==""||$(".maxhalfyearly").val()=="0"){
					$(".errormessagediv").show();
					$(".validateTips").text("Maximum Marks Should Not Be Empty");
					$(this).find(".maxhalfyearly").css({"border":"1px solid #AF2C2C"});
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 3000);
					errorflag=true;
					return false;
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
			//subid = $(this).find(".subid").val();

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
			//subids.push(subid);
			primaryidstu.push($(this).find('.stuid ').attr("class").split(" ")[2]);
			studentId.push($(this).find('.stuid ').attr("class").split(" ")[1]);
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
				"subids":subid,
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
				//"workedu":workedu,
				//"artedu":artedu,
				//	"disciplineedu":disciplineedu,
				//	"healthedu":healthedu,
				"examtypeprfix":examtypeprfix,
				"studentId":studentId,
		};
		if(saveFlag){
			$("#loader").show();
			$.ajax({
				type : "POST",
				url : "examTimetablePath.html?method=saveMarkEntrySubjectWise",
				data : datalist,
				async : false,
				success : function(data) {
					var result = $.parseJSON(data);
					if(result.status =="inserted" ) {
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$("#loader").hide();
						$(".validateTips").text("Adding Marks Progressing...");
						var examid =$("#hiddenexamid").val();
						var classId=$("#classhiddenid").val();
						var sectionId=$("#hiddensectionid").val();
						var accyear=$("#hiddenaccyid").val();
						var hschoolLocation=$("#hiddenlocid").val();
						var specid=$("#hiddenspecialization").val()
						setTimeout(function(){  
							window.location.href="examCreationPath.html?method=classWiseSubject&classId="+classId+
							"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+
							"&hschoolLocation="+hschoolLocation+"&specId="+specid+
							"&historyacayear="+$("#historyacayear").val()+"&historylocation="+$("#historylocation").val();},3000);
					}else{
						$(".errormessagediv").show();
						$(".successmessagediv").hide();
						$("#loader").hide();
						$(".validateTips").text("Adding Marks Faild Try Again...");
						var examid =$("#hiddenexamid").val();
						var classId=$("#classhiddenid").val();
						var sectionId=$("#hiddensectionid").val();
						var accyear=$("#hiddenaccyid").val();
						var hschoolLocation=$("#hiddenlocid").val();
						var specid=$("#hiddenspecialization").val()
						setTimeout(function(){  
							window.location.href="examCreationPath.html?method=classWiseSubject&classId="+classId+
							"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+
							"&hschoolLocation="+hschoolLocation+"&specId="+specid+
							"&historyacayear="+$("#historyacayear").val()+"&historylocation="+$("#historylocation").val();},3000);

					}
				}
			});
		}
	});

	$("#back1").click(function(){
		var examid =$("#hiddenexamid").val();
		var classId=$("#classhiddenid").val();
		var sectionId=$("#hiddensectionid").val();
		var accyear=$("#hiddenaccyid").val();
		hschoolLocation=$("#hiddenlocid").val();
		var specid=$("#hiddenspecialization").val()
		window.location.href="examCreationPath.html?method=classWiseSubject&classId="+classId+
		"&sectionId="+sectionId+"&examid="+examid+"&accyear="+accyear+"&hschoolLocation="+hschoolLocation+
		"&specId="+specid+"&historyacayear="+$("#historyacayear").val()+"&historylocation="+$("#historylocation").val(); 
	});

	$('#allstudent tbody tr td').each(function(){
		var status=$(this).find('.statusvalue').attr('class');
		if(status!=undefined){
			status1 = status.split(" ")[1];

			var rowid=$(this).find('.statusvalue').attr("id");

			$('#'+rowid).find('option').remove();

			var statusList=[];
			statusList.push("Present");
			statusList.push("Absent");

			for (var j = 0; j < statusList.length; j++) {


				$("#"+rowid).append('<option value="' + statusList[j] + '">' +  statusList[j]  + '</option>');
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
		if($(this).find('.statusvalue').val()=="Absent"){
			$(this).find(".notebook").val(0);
			$(this).find(".subjectenrichment").val(0);
			$(this).find(".scoredperiodic").val(0);
			$(this).find(".scoredhalfyearly").val(0);
			$(this).find(".notebook").attr("readonly","readonly");
			$(this).find(".subjectenrichment").attr("readonly","readonly");
			$(this).find(".scoredperiodic").attr("readonly","readonly");
			$(this).find(".scoredhalfyearly").attr("readonly","readonly");
		}
	});
});
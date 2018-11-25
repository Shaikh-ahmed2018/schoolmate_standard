$(document).ready(function() {
	              getClassName($("#locationname").val());
					$("#locationname").change(function(){
						getClassName($("#locationname").val());
					});
					$("#subjtname").change(function(){
						checkSubjectduplication();
					});	
				
					$("#subjectCode").on('blur keyup paste change',function(e){
						minlength=4;
						maxlength=8;
						this.value=this.value.toUpperCase();
						if(this.value.length > maxlength){
						this.value = this.value.substr(0,maxlength);
						
						}
					});
					$("#classname").change(function(){
						getClassSpecilization($(this).val(),$("#locationname").val());
							checkSubjectduplication();
						 $("#clsname").val($("#classname option:selected").text());
						if($(this).val()=="CCD14" || $(this).val()=="CCD15"){
							$(".notmandatory").show();
						} else{
							$(".notmandatory").hide();
						}
						 
					});	
					
					
					  /*$('input[type=file]').change(function () {
 						 var val = $(this).val().toLowerCase();
 						 var regex = new RegExp("(.*?)\.(docx|doc|pdf|ppt|xls|jpg|jpeg|txt|png|xlsx)$");
 						  if(!(regex.test(val))) {
 						 $(this).val('');
 						 $(
 							".errormessagediv")
 							.show();
 					     $(".validateTips")
 							.text(
 									"Select Correct file format ");
 						 } }); */
				
				var pageUrl=window.location.href.substring(window.location.href.lastIndexOf("/")+1);
				if(pageUrl=="subject.html?method=addSubject"){
					$(".successmessagediv").show();
					setTimeout(function(){
						window.location.href="menuslist.html?method=subjectdetails";
					},2500);
				}
					
					var hiddenclass=$("#hiddenclassid").val().trim(); 
 
				  
					/*$("#classname option[value="+ hiddenclass + "]").attr('selected', 'true');*/
				    var hiddensubject=$("#hiddensubject").val();
 
				   
 
					var hiddendescription=$("#hiddendescription").val();
					var hiddenfile=$("#hiddenfile").val();
					
					$("#subjtname").val(hiddensubject);
					$("#description").val(hiddendescription);
					
					
					
				    setTimeout("removeMessage()" ,5000);
					setInterval(function() {
						$(".errormessagediv").hide();
					}, 5000);
					
					
	 $('#save').click(function() 
	   {
			var reg = /^\d+$/;
	 		var tm=$("#totalMarks").val();
	 		var pm=$("#passMarks").val();
             var status=$("#statusid").val();
            var isLanguage=$("#isLangauge").val();
                                
			$(".errormessagediv").show();
			
			var  classname = $("#classname").val();
			var specialization = $("#specialization").val();
			var subjtname = $("#subjtname").val().trim(); 
			var subjectCode = $("#subjectCode").val().trim(); 
			var isLangauge = $("#isLangauge").val(); 
			var isLab = $("#isLab").val();  
			var subtype = $("#subtype").val();
			var description = $("#description").val();
			var locationId=$("#locationname").val();
			
		    var docreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.doc|.docx|.DOC|.DOCX)$/;
			var pdfreg = /^(([a-zA-Z]:)|(\\{2}\w+)\$?)(\\(\w[\w].*))+(.pdf|.PDF)$/;
			
			if(locationId=="")
			{
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required -School Name");
				showError("#locationname");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			}
			else if(classname==""){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Class");
				showError("#classname");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			}
			else if(subjtname=="")
			{
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Subject Name");
				showError("#subjtname");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			}
			 else if(subjtname.length < 2)
			{
				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name Should Contain min 2 Characters");
				return false;
			}
			 
			 else if($("#subjectCode").val().trim()==""){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Subject Code");
				showError("#subjectCode");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			}
			 else if(checkDuplicateSubCodeCount()){
				 $(".errormessagediv").show();
					$(".validateTips").text("Duplicate Subject Code");
					showError("#subjectCode");
					setTimeout(function() {
						$('.errormessagediv').fadeOut();
					}, 5000);
					return false;
			}
			
			else if($("#isLangauge").val()==""){
			 $(".errormessagediv").show();
				$(".validateTips").text("Field Required - Language");
			  showError("#isLangauge");
			    setTimeout(function() {
				$('.errormessagediv').fadeOut();
			}, 5000);
			return false;
			}
			
			 /*else if(tm==""){
				
				$(".errormessagediv").show();
				$(".validateTips").text("Total Marks Should Not Be Empty");
				showError("#totalMarks");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			}
			 
			 else if(!tm.match(/[0-9]+/)){
				 $(".errormessagediv").show();
				$(".validateTips").text("Total Marks Should Be In Numbers");
				document.getElementById("totalMarks").style.border = "1px solid #AF2C2C";
			 	document.getElementById("totalMarks").style.backgroundColor = "#FFF7F7";
				return false;
			 }*/
			     
			   /*  else if(pm==""){
				 $(".errormessagediv").show();
				$(".validateTips").text("Pass Marks Should Not Be Empty.");
				document.getElementById("totalMarks").style.border = "1px solid #AF2C2C";
			 	document.getElementById("totalMarks").style.backgroundColor = "#FFF7F7";
				return false;
			 }
			 else if(!pm.match(/[0-9]+/)){
				 $(".errormessagediv").show();
				$(".validateTips").text("Pass Marks Should Be In Numbers");
				document.getElementById("passMarks").style.border = "1px solid #AF2C2C";
			 	document.getElementById("passMarks").style.backgroundColor = "#FFF7F7";
				return false;
				
			 }
			 
			 else if(parseFloat($("#totalMarks").val()) < parseFloat($("#passMarks").val())){
				$(".errormessagediv").show();
				$(".validateTips").text("Total Marks Should Be Greater Than Pass Marks.");
				return false;
			 }*/
			 
			 else if($("#isLab").val()==""){
				$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Lab Available");
				showError("#isLab");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			 }
			 else if($("#subtype").val()==""){
			 	$(".errormessagediv").show();
				$(".validateTips").text("Field Required - Subject Type");
				showError("#subtype");
				setTimeout(function() {
					$('.errormessagediv').fadeOut();
				}, 5000);
				return false;
			  }
			
			 else {
				 $(".errormessagediv").hide();

				 var datalist = {
						 "classname"    : classname,
						 "subjtname"    : subjtname,
						 "subjectCode"  : subjectCode,
						 "isLangauge"   : isLangauge, 
						 "isLab"        : isLab,
						 "subtype"      : subtype,
						 "description"  : description,
						 "specialization" : specialization,
						 "locationId"    :locationId,
						 "hiddensubId":$("#hiddensubjectid").val()
				 };

	    $.ajax({
		    type : 'POST',
		    url : "subject.html?method=addSubject",
		    data : datalist,
		    success : function(response) {
			 var result = $.parseJSON(response);
             
			 if (result.status == "true") {
				 $(".successmessagediv").show();
			     $(".validateTips").text("Adding Record progressing...");
				 setTimeout(function() {
					 window.location.href="menuslist.html?method=subjectdetails";
				 }, 5000);
			 }
			 else if (result.status=="false") 
			 {
				 $( ".errormessagediv").show();
				 $( ".validateTips").text("Faile to add the subject");
				 setTimeout(function() {
					 window.location.href="menuslist.html?method=subjectdetails";
				  }, 5000);
			 }
			 else if (result.status == "update") {
				 $(".errormessagediv").hide();
				 $(".successmessagediv").show();
				 $(".validateTips").text("Updating Record progressing...");
				 setTimeout(function() {
					 window.location.href="menuslist.html?method=subjectdetails";
				  }, 5000);
			 }
			 else if (result.status=="alreadyexist") 
			 {
				 $( ".errormessagediv").show();
				 $( ".validateTips").text("This Subject Already Exist !! Make it Active");
				 setTimeout(function() {
					 window.location.href="menuslist.html?method=subjectdetails";
				  }, 5000);
			 }
			 else if (result.status=="updatefail") 
			 {
				 $( ".errormessagediv").show();
				 $( ".validateTips").text("Failed to Update Subject details");
				 setTimeout(function() {
					 window.location.href="menuslist.html?method=subjectdetails";
				  }, 5000);
			 }
		 }
	 });
   }

		
 });
						
					
				});


function getLanguage(){
	$.ajax({
	url : "sectionPath.html?method=",
	})
	
	
	
	
}

function getClassName(val) {
	
	
	
	$.ajax({
	url : "sectionPath.html?method=getCampusClassDetailsIDandClassDetailsNameAction",
	data:{"locationId":val},
	async:false,

	success : function(data) {

		

		var result = $.parseJSON(data);
		$(classname).html("");
		
	    $(classname).append('<option value="">----------Select----------</option>');

		for (var j = 0; j < result.classDetailsIDandClassDetailsNameList.length; j++) {

			$(classname).append(
					'<option value="' + result.classDetailsIDandClassDetailsNameList[j].secDetailsId + '">'
							+ result.classDetailsIDandClassDetailsNameList[j].secDetailsName + '</option>');
		}

	
	
	
	
	}
});}

function clearFields() {


	document.getElementById("classname").value = "";
	document.getElementById("subjtname").value = "";
	document.getElementById("file").value = "";
	document.getElementById("description").value = "";
}

function removeMessage() {
	

	
	$(".successmessagediv").hide();


}

function checkSubjectduplication() {
	
	var classId = $("#classname").val();
	var subject = $("#subjtname").val(); 
	var locationId=$("#locationname").val();
	var specialization=$("#specialization").val();
	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"specialization":specialization,
	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=validateSubNameUpdate",
		data : checkSubjectName,
		async : false,
		success : function(data) {


			var result = $.parseJSON(data);


			if(result.des_available =="inactive" ) {

				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name '"+$("#subjtname").val()+"' Already Exists With this Class !! Make it Active");
				$("#subjtname").val("");
				$("#subjtname").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);

			}
			
			if(result.des_available =="true" ) {
				$(".errormessagediv").show();
				$(".validateTips").text("Subject Name '"+$("#subjtname").val()+"' Already Exists With this Class");
				$("#subjtname").val("");
				$("#subjtname").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);

			}

		}
	});
}

function checkDuplicateSubCodeCount(){
	
	var flag =false;
	var classId = $("#classname").val();
	var subject = $("#subjtname").val(); 
	var locationId=$("#locationname").val();
	var specialization=$("#specialization").val();
	var subjectcode=$("#subjectCode").val();
	var checkSubjectName = {
			"classId" : classId,
			"subject" : subject,
			"locationId":locationId,
			"specialization":specialization,
             "subjectcode":subjectcode,
	};

	$.ajax({
		type : "POST",
		url : "subject.html?method=checkDuplicateSubCodeCount",
		data : checkSubjectName,
		async : false,
		success : function(data) {
			var result = $.parseJSON(data);
      
			if(result.des_available =="inactive" ) {
				$(".errormessagediv").show();
				$(".validateTips").text("Subject Code  Already Exists With this Class !! Make it Active");
				$("#subjectCode").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);
				flag=true;
				return false;
			}
			else if(result.des_available =="true" ) {
				$(".errormessagediv").show();
				$(".validateTips").text("Subject Code  Already Exists.");
				$("#subjectCode").css({'border-color':'#f00'});

				setTimeout(function(){
					$(".errormessagediv").hide();
				},5000);
				flag=true;
				return false;
			}

		}
	});
	return flag;
}

function HideError() 
{
	
document.getElementById("classname").style.border = "1px solid #ccc";
document.getElementById("classname").style.backgroundColor = "#fff";

document.getElementById("subjtname").style.border = "1px solid #ccc";
document.getElementById("subjtname").style.backgroundColor = "#fff";

}
function getClassSpecilization(classId,location){
	var data = {
			"classId" : classId,
			"locationId":location,
	};
	$.ajax({
		type : 'POST',
		url : "specialization.html?method=getSpecializationOnClassBased",
		data: data,
		async : false,
		success : function(response) {
			var result = $.parseJSON(response);
			$('#specialization').empty();
			$('#specialization').append('<option value="-">'+ "NIL" + '</option>');

			for ( var j = 0; j < result.jsonResponse.length; j++) {
				$('#specialization').append(
						'<option value="'
						+ result.jsonResponse[j].spec_Id
						+ '">'
						+ result.jsonResponse[j].spec_Name
						+ '</option>');
			}


		}
	});
}
function showError(id){
	
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	
}	

$(document).ready(function(){
	
	$("#back1").click(function(){
		window.location.href="menuslist.html?method=bankEntry&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
	});

	var hbankId=$("#hbankId").val();
	
	$("#savechanges").click(function(){
		var names=$("#bankname").val().trim();
		var shortnames=$("#bankshortname").val().trim();
		var hbankId = $("#hbankId").val();

		if(names == "" || names == undefined){
			$('.errormessagediv').show();
			$(".validateTips").text("Field required - Bank Name.");
			$("#bankname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#bankname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
		else if(validateBankName()){
			$("#bankname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#bankname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);
			return false;
		}
		
	/*	else if (!shortnames.match(/^[a-zA-Z0-9]*$/i)) {
			$(".errormessagediv").show();
			$(".validateTips").text("Enter Only Number And Alphabets.");
			$("#bankshortname").css({'backgroundColor' : '#FFF7F7','border-color':'#B70606'});
			setTimeout(function() {
				$('.errormessagediv').fadeOut();
				$("#bankshortname").css({'backgroundColor' : '#fff','border':'1px solid #ccc'});
			}, 3000);

			return false;
		}*/
		else{
			$.ajax({
				type : 'post',
				url : 'bankMaster.html?method=saveBank',
				data : {
					"name" : names ,
					"shortname" : shortnames,
					"hbankId" :hbankId,
				},
				async : false,
				success : function(data) {
					var data1 = $.parseJSON(data);
					if(data1.result == "success")
					{
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text("Adding record progressing...");
						setTimeout(function() {
							window.location.href="menuslist.html?method=bankEntry&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						},3000);
					}else if(data1.result == "update"){
						$('.errormessagediv').hide();
						$(".successmessagediv").show();
						$(".successmessage").text("Updating record progressing...");
						setTimeout(function() {
							window.location.href="menuslist.html?method=bankEntry&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						}, 3000);
					}else{
						$('.errormessagediv').show();
						$("#sucesshover").hide();
						$(".validateTips").text("Failed to add Bank,Try Again...");
						setTimeout(function() {
							window.location.href="menuslist.html?method=bankEntry&historysearchname="+$("#historysearchname").val()+"&historystatus="+$("#historystatus").val();
						}, 3000);
					}
				}

			});
		}
	});
});

function validateBankName(){

	flag = false;
	var names=$("#bankname").val().trim();
	var hbankName=$("#hbankName").val();
	var hbankId=$("#hbankId").val();
	
	if(names != hbankName){
		$.ajax({
			type : 'post',
			url : 'bankMaster.html?method=validateBankName',
			data : {
				"name" : names ,
			},
			async : false,
			success : function(data) {
				var result = $.parseJSON(data);				
				if(result.result == "inactive")
				{
					$(".successmessagediv").hide();
					$(".errormessagediv").show();
					$(".validateTips").text("Bank Name already exist !! Make it Active");
					flag = true;
				}
				 else if (result.result=="true") {
						$(".errormessagediv").show();
						$(".validateTips").text("Bank Name already exist.");
						flag = true;

					} 
				else{
					flag = false;
				}
			}

		});
	}
	
	else{
		flag = false;
	}
	
	return flag;
}




$(document).ready(function(){

	var  ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+" +
	"@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)" +
	"+[a-zA-Z]{2,}))$";

	var filter = /^[0-9]{3,5}[-][0-9]{5,8}$/;
	var phone = /^([6-9][0-9]{9})$/;

	$("input,select,textarea").on({
		keypress: function(){
			if(this.value.length>0){
				hideError("#"+$(this).attr("id"));
				$(".errormessagediv").hide();
			}
		},
		change: function(){
			if(this.value.length>0 ){
				hideError("#"+$(this).attr("id"));

				$(".errormessagediv").hide();
			}
		},
	});

	$("#pubdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});

	$("#back1").click(function(){
		window.location.href="LibraryMenu.html?method=ListpublisherSettings&historysearch="+$("#historysearch").val();
	});

	$("#save").click(function(){

		if($("#Publisher").val().trim() == ""){
			$(".errormessagediv").show();
			showError("#Publisher","Field Required - Publisher Name");
			return false;

		}

		else if($("#pubdate").val().trim() == ""){
			$(".errormessagediv").show();
			showError("#pubdate","Field Required - Published Date");
			return false;

		}
		else if($("#Email").val().trim() == "" || !($("#Email").val()).match(ePattern)){
			$(".errormessagediv").show();
			showError("#Email","Field Required - Valid Email Id");
			return false;
		}	

		else if($("#telephone").val().trim() == ""){
			$(".errormessagediv").show();
			showError("#telephone","Field Required - Telephone Number");
			return false;

		}
		else if(!filter.test($("#telephone").val().trim())){
			$(".errormessagediv").show();
			showError("#telephone","Enter Valide Telephone Number");
			return false;
		}
		else if($("#Address").val().trim() == ""){
			$(".errormessagediv").show();
			showError("#Address","Field Required - Publisher Address");
			return false;

		}
		
		else if($("#mobilenum").val() == ""){
			$(".errormessagediv").show();
			showError("#mobilenum","Field Required - Mobile Number");
			return false;
		}
		else if(!phone.test($("#mobilenum").val().trim())){
			$(".errormessagediv").show();
			showError("#mobilenum","Enter Valide Mobile Number");
			return false;
		}
		else if(validationpubsettings()){
			$('.successmessagediv1').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Publisher Details Already Exists.");
			$(".errormessagediv").delay(3000).fadeOut();
			return false;
		}
		else {

			datalist = {
					"publisher" : 	$("#Publisher").val(),
					"address" : $("#Address").val(),
					"email" : $("#Email").val(),
					"pubdate" : $("#pubdate").val(),
					"telphone" : $("#telephone").val(),
					"mobilenum" : $("#mobilenum").val(),
					"fax" : $("#Fax").val(),
					"hiddenid":$("#hiddenid").val()
			};

			$.ajax({
				type : "POST",
				url : "LibraryMenu.html?method=addPublisherSettings",
				data : datalist,
				success:function(data){
					var result = $.parseJSON(data);
					
					if(result.status == "addsuccess" ){

						$('.successmessagediv').show();
						$(".validateTips").text("Adding Record Progressing");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings&historysearch="+$("#historysearch").val();
						}, 3000);
					}
					if(result.status =="success" ){
						$('.successmessagediv').show();
						$(".validateTips").text("Updating Record Progressing");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings&historysearch="+$("#historysearch").val();
						}, 3000);
					}
					/*else (result.status == "fail" ){
					 $('.successmessagediv').show();
					 $(".validateTips").text("Adding Record Failed");
					 setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListpublisherSettings";
						}, 3000);
				 }
					 */
				}
			});


		}


	});


});

function validationpubsettings(){
	flag = false;


	if($("#hiddenid").val() !="" && ($("#hiddenpubname").val().toLowerCase() !=$("#Publisher").val().toLowerCase()) || $(".hiddenpubadd").val()!=$(".address").val()){


		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=validationpubsettings",
			data : {
				"publisher" : 	$("#Publisher").val(),
				"address" : $("#Address").val(),
				"email" : $("#Email").val(),
				"telphone" : $("#telephone").val(),
				"mobilenum" : $("#mobilenum").val(),

			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				if(result.result=="true"){
					flag = true;
				}else{
					flag =false;
				}
			}
		});  
	}    

	else if($("#hiddenid").val()==""){

		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=validationpubsettings",
			data : {
				"publisher" : 	$("#Publisher").val(),
				"address" : $("#Address").val(),
				"email" : $("#Email").val(),
				"telphone" : $("#telephone").val(),
				"mobilenum" : $("#mobilenum").val(),

			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				if(result.result=="true"){
					flag = true;
				}else{
					flag =false;
				}
			}
		});
	}
	else{
		flag = false;
	}
	return flag;
}


function showError(id,errorMessage){
	$(id).focus();
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$('.successmessagediv').hide();
	$(".errormessagediv").show();
	$(".validateTips").text(errorMessage);
	$(".errormessagediv").delay(3000).fadeOut();
}
function hideError(id){
	$(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
}

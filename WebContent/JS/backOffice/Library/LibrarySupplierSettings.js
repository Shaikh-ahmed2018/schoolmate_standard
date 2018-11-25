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
	$("#supdate").datepicker({
		dateFormat : "dd-mm-yy",
		maxDate : 0,
		changeMonth : true,
		changeYear : true,
	});

	$("#back1").click(function(){
		window.location.href="LibraryMenu.html?method=ListsupplierSettings&historysearch="+$("#historysearch").val();
	});

	$("#save").click(function(){

		if($("#Supplier").val() == "" || $("#Supplier").val()==null){
			$(".errormessagediv").show();
			showError("#Supplier","Field Required - Supplier Name");
			return false;

		}

		else if($("#supdate").val() == "" || $("#supdate").val() == null){
			$(".errormessagediv").show();
			showError("#supdate","Select Supply Date");
			return false;

		}

		else if($("#emailid").val() == "" || $("#emailid").val() == null){
			$(".errormessagediv").show();
			showError("#emailid","Field Required - Email Id");
			return false;

		}
		else if(!($("#emailid").val()).match(ePattern)){
			$(".errormessagediv").show();
			showError("#emailid"," Enter Valid Email Id");
			return false;

		}

		else if($("#telephone").val() == "" || $("#telephone").val() == null){
			$(".errormessagediv").show();
			showError("#telephone","Field Required - Telephone Number");
			return false;
		}

		else if(!filter.test($("#telephone").val())){
		 $(".errormessagediv").show();
		 showError("#telephone","Enter Valid Telephone Number");
			return false;
	 }

		else if($("#Supplieraddress").val() == "" ||$("#Supplieraddress").val() == null){
			$(".errormessagediv").show();
			showError("#Supplieraddress","Field Required - Supplier Address");
			return false;
		}

		else if($("#supmobnum").val() == "" || $("#supmobnum").val() == null){
			$(".errormessagediv").show();
			showError("#supmobnum","Field Required - Mobile Number");
			return false;
		}

		else if(!phone.test($("#supmobnum").val())){
			$(".errormessagediv").show();
			showError("#supmobnum","Enter Valid Mobile Number");
			return false;
		}

		else if(validationsubsettings() == 1){
			$('.successmessagediv').hide();
			$(".errormessagediv").show();
			$(".validateTips").text("Supplier Details Already Exists");
			$(".errormessagediv").delay(3000).fadeOut();
		}


		else {
			datalist = {
					"supplier" : 	$("#Supplier").val(),
					"supplieradd" : $("#Supplieraddress").val(),
					"emailid" : $("#emailid").val(),
					"supdate" :$("#supdate").val(),
					"telephone":$("#telephone").val(),
					"supmobnum":$("#supmobnum").val(),
					"fax":$("#Fax").val(),
					"hiddenid":$("#hiddenid").val(),

			};
			$.ajax({
				type : "POST",
				url : "LibraryMenu.html?method=addSupplierSettings",
				data : datalist,
				success:function(data){
					var result = $.parseJSON(data);

					if(result.status == "addsuccess" ){
						$('.successmessagediv').show();
						$(".validateTips").text("Adding Record Progressing...");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListsupplierSettings&historysearch="+$("#historysearch").val();
						}, 3000);
					}

					else if(result.status == "success" ){
						$('.successmessagediv').show();
						$(".validateTips").text("Updating Record Progressing...");
						setTimeout(function() {
							window.location.href="LibraryMenu.html?method=ListsupplierSettings&historysearch="+$("#historysearch").val();
						}, 3000);
					}

				}
			});
		}
	});
});

function validationsubsettings(){

	flag = 0;

	if($("#hiddenid").val()!="" && ($("#hiddensubnm").val().toLowerCase()!=$("#Supplier").val().toLowerCase() ||$("#hiddensubadd").val().toLowerCase()!=$("#Supplieraddress").val().toLowerCase())){
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=validationsubsettings",
			data : {
				"supplier" : $("#Supplier").val(),
				"supplieradd" : $("#Supplieraddress").val(),
				"emailid" : $("#emailid").val(),
				"telephone":$("#telephone").val(),
				"supmobnum":$("#supmobnum").val(),

			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
				if(result.result=="true"){
					flag = 1;
				}else{
					flag =0;
				}
			}
		});
	}


	else if($("#hiddenid").val()=="" ){
		
		$.ajax({
			type : 'POST',
			url : "LibraryMenu.html?method=validationsubsettings",
			data : {
				"supplier" : $("#Supplier").val(),
				"supplieradd" : $("#Supplieraddress").val(),
				"emailid" : $("#emailid").val(),
				"telephone":$("#telephone").val(),
				"supmobnum":$("#supmobnum").val(),

			},
			async : false,
			success : function(response) {

				var result = $.parseJSON(response);
			
				if(result.result=="true"){
					flag = 1;
				}else{
					flag =0;
				}
			}
		});


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

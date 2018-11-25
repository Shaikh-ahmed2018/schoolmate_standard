$(document).ready(function(){
	var flag="new";
	$("#status").val($("#hiddenstatus").val());

	if($("#hiddenid").val()==""){
		flag="new";
	}
	else{
		flag="modify";
	}

	$("#back1").click(function(){
		//window.location.href="LibraryMenu.html?method=libraryItem&historystatus="+$("#historystatus").val();
		window.location.href="LibraryMenu.html?method=libraryItem";
	});

	$('#name').keypress(function (e) {
		var regex = new RegExp(/^[a-zA-Z0-9-.(\][)\s]*$/);
		var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
		if (regex.test(str)) {
			HideError(this);
			return true;
		}
		e.preventDefault();
		$(".errormessagediv").show();
		$(".validateTips").text("Allows only alphanumerics and -, (, ), [, ], &");
		setTimeout(function() {
			$('#errormessagediv').fadeOut();
		},3000);
		return false;

	});


	$("#save").click(function(){
		var id=$("#hiddenid").val();
		var name=$("#name").val();
		var description=$("#description").val();
		var status=$("#status").val();
		
		if(validation()){
			var jSonObject={
					"id":id,
					"name":name,
					"description":description,
					"status":status,
					"flag":flag,
			};
			$.ajax({
				type:"POST",
				url:"LibraryMenu.html?method=InsertLibraryItem",
				data:jSonObject,
				async:false,
				success:function(response){
					var result=$.parseJSON(response);
					if(result.status=="Insert"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".successmessagediv .validateTips").text("Adding Record Processing...");
						setTimeout(function(){
							//window.location.href="LibraryMenu.html?method=libraryItem&historystatus="+$("#historystatus").val();
							window.location.href="LibraryMenu.html?method=libraryItem";
						},3000);
					}
					else if(result.status=="Update"){
						$(".errormessagediv").hide();
						$(".successmessagediv").show();
						$(".successmessagediv .validateTips").text("Update Record processing...");
						setTimeout(function(){
							window.location.href="LibraryMenu.html?method=libraryItem";
						},3000);
					}
					else{
						$("#name").css({
							"border-color":"#f00"
						});
						$(".successmessagediv").hide();
						$(".errormessagediv").show();
						$(".errormessagediv .validateTips").text("Item Name Already Exists...");
					}
				}
			});
		}
	});
	$("#name").keyup(function(){
		if($(this).val().length>1){
			$(this).css({
				"border-color":"#ccc"
			});
			$(".errormessagediv").hide();
		}
	});
});
function validation(){
	var flag=false;
	var name=$("#name").val();
	if(name.trim()==""){
		$(".errormessagediv").show();
		$(".validateTips").text("Field-Required Item Name");
		$("#name").css({
			"border-color":"#f00"
		});
		return false;
		flag=false;
	}
	else{
		$("#name").css({
			"border-color":"#ccc"
		});
		flag=true;
	}
	return flag;
}
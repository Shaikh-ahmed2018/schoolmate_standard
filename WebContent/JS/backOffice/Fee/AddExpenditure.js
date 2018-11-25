function myFunction()

{
	
	var id = $('#expId').val();
	var name = $('#expenditureTitle').val();
	var date=$('#date').val();
	var amount=$('#amount').val();

	if (name == "" || name == null)

	{

		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter Expenditure Name");
		
		document.getElementById("expenditureTitle").style.border = "1px solid #AF2C2C";
		document.getElementById("expenditureTitle").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}

	if (!name.match(/[A-Za-z]+$/i))

	{

		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Expenditure Title");
		document.getElementById("expenditureTitle").style.border = "1px solid #AF2C2C";
		document.getElementById("expenditureTitle").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}

	if (date == "" || date == null)

	{

		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Choose the date");
	
		

		return false;

	}
	
	if (amount == "" || amount == null)

	{

		$(".successmessagediv").hide();

		$(".errormessagediv1").show();

		$(".validateTips1").text("Enter the Amount.");
	
		

		return false;

	}
	
	
	if (!amount.match(/[0-9]+$/i))

	{

		$(".successmessagediv").hide();
		$(".errormessagediv1").show();
		$(".validateTips1").text("Enter Valid Expenditure Amount");
		document.getElementById("expenditureTitle").style.border = "1px solid #AF2C2C";
		document.getElementById("expenditureTitle").style.backgroundColor = "#FFF7F7";
		setTimeout(function() {
			$('.errormessagediv').fadeOut();
		}, 3000);
		

		return false;

	}
	
	/*

	var status = false;

	datalist = {
		"name" : name,
		"id" : id
	},

	$.ajax({

		type : "POST",

		url : "addfee.html?method=getnamecount",

		data : datalist,
		async : false,

		success : function(data)

		{

			var result = $.parseJSON(data);

			if (result.message)

			{

				$('#feename').val("");

				$(".successmessagediv").hide();

				$(".errormessagediv1").show();

				$(".validateTips1").text("Name Already Exists");

				status = false;

			}

			else

			{

				status = true;

			}

		}

	});*/

	return true;

}

function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

$(document)
		.ready(
				function()

				{
					$("#date").datepicker({
						dateFormat : "dd-mm-yy",
						defaultDate : "+1w",
						changeMonth : true,
						changeYear : true,
						numberOfMonths : 1,
						minDate : 0,
					});	
					
					
					
					
					
					$('#description').text($('#hdescription').val());
					$('#locationid').val($('#hdnlocation').val());
				
					
					setTimeout("removeMessage()", 3000);
					setInterval(function() {
						$(".errormessagediv1").hide();
					}, 3000);

				

					

					$("#save").click(function(event){
						var id = $('#expId').val();

						var name = $('#expenditureTitle').val();
						
						var amount= $('#amount').val();

						var description = $('#description').val();

						var date=$('#date').val();
						var schoolname=$("#locationid").val();
						
					
						
						/*var urlmethod=(window.location.href.substr(window.location.href.lastIndexOf("/")+1)).split("=");
						var urledit=(urlmethod[1].split("&")[0]);*/

						if (myFunction())

						{

							datalist = {
									"schoolname":schoolname,
									"expenditureTitle" : name,
									"amount" : amount,
									"description" : description,
									"expId" : id,
									"date": date

							},

							$
							.ajax({

								type : "POST",

								url : "addExpenditure.html?method=insertExpenditure",

								data : datalist,

								success : function(
										data)

										{

									var result = $
									.parseJSON(data);
									// //alert("response"+result.jsonResponse);

									if (result.jsonResponse == "Expenditure Details Added Successfully") {
										$(
										".errormessagediv")
										.hide();
										$(
										".successmessagediv")
										.show();
										$(
										".validateTips")
										.text(
										"Expenditure Details Added Successfully");

										setTimeout(
												function() {

													window.location.href = "menuslist.html?method=expenditureDetailsList";

												},
												3000);

									}

									if (result.jsonResponse == "Expenditure Details not Created Successfully") {

										$(
										".errormessagediv")
										.hide();
										$(
										".successmessagediv")
										.show();
										$(
										".validateTips")
										.text(
										"Expenditure Details not Created Successfully");

										setTimeout(
												function() {

													window.location.href = "menuslist.html?method=expenditureDetailsList";

												},
												3000);

									}
									if (result.jsonResponse == "Expenditure Details Updated Successfully") {
										$(
										".errormessagediv")
										.hide();
										$(
										".successmessagediv")
										.show();
										$(
										".validateTips")
										.text(
										"Expenditure Details Updated Successfully");

										setTimeout(
												function() {

													window.location.href = "menuslist.html?method=expenditureDetailsList";

												},
												3000);

									}

									if (result.jsonResponse == "Expenditure Details not Updated Successfully") {

										$(
										".errormessagediv")
										.hide();
										$(
										".successmessagediv")
										.show();
										$(
										".validateTips")
										.text(
										"Expenditure Details not Updated Successfully");

										setTimeout(
												function() {

													window.location.href = "menuslist.html?method=expenditureDetailsList";

												},
												3000);

									}



										}

							});

						}

					});

					$("#view")
							.click(
									function(event)

									{

										window.location.href = "menuslist.html?method=expenditureDetailsList";

									});

				});

function HideError() 
{
	
document.getElementById("expenditureTitle").style.border = "1px solid #ccc";
document.getElementById("date").style.backgroundColor = "#fff";


}
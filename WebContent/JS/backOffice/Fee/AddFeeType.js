$(document).ready(function(){
	
	$("#feetypeid").change(function(){
		checkfeeId();
	});
	
	$("#feetypename").change(function(){
		checkduplicatefeeTypeName();
	});
	
	$("#save").click(function(){
		
		feetypeid = $("#feetypeid").val();
		feetypename= $("#feetypename").val();
		hiddentypeid = $("#hiddentypeid").val();
		
		if(hiddentypeid == "" || hiddentypeid == null){
			url = "addfee.html?method=saveFeeType";
		}else{
			url = "addfee.html?method=updateFeeType";
		}
		
		if(feetypeid.trim() == "" || feetypeid == undefined){
			showerror($("#feetypeid"),"Field Required - Fee Type Id");
			return false;
		}
		else if(feetypename.trim() == "" || feetypename == undefined){
			showerror($("#feetypename"),"Field Required - Fee Type Name");
			return false;
		}			
		else{
			
			$.ajax({
				type  : "POST",
				url   : url,
				data  : {"feetypeid" :feetypeid,"feetypename": feetypename,"hiddentypeid":hiddentypeid},
				async : false,
				success : function(data){
					var result = $.parseJSON(data);
					if(result.status == "success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Adding record progressing...");
						setTimeout(function(){
							$(".successmessagediv").hide();
							window.location.href= "menuslist.html?method=feeTypeList";
						},2000);
					}
					else if(result.results=="success"){
						$(".successmessagediv").show();
						$(".validateTips").text("Updating record progressing...");
						setTimeout(function(){
							$(".successmessagediv").hide();
							window.location.href= "menuslist.html?method=feeTypeList";
						},2000);
					}
					
					else{
						$(".errormessagediv1").show();
						$(".validateTips1").text("Failed to add the record, try again...");
						setTimeout(function(){
							$(".errormessagediv1").hide();
						},2000);
					}
				}
			});
		}
		
	});
});

function showerror(id,msg){
	$(id).css({
		"border":"1px solid #AF2C2C",
		"background-color":"#FFF7F7"
	});
	$(".form-control").not(id).css({
		"border":"1px solid #ccc",
		"background-color":"#fff"
	});
	$(".errormessagediv1").show();
	$(".validateTips1").text(msg);
	setTimeout(function(){
		$(".errormessagediv1").hide();
	},2000);
}

function HideError(pointer) 
{
	document.getElementById(pointer.id).style.border = "1px solid #ccc";
	document.getElementById(pointer.id).style.backgroundColor = "#fff";
}

function checkfeeId(){
	if($("#hiddenfeeid").val() != $("#feetypeid").val()){
		$.ajax({
			type  : "POST",
			url   : "addfee.html?method=checkduplicatefeeId",
			data  : {"feetypeid" :$("#feetypeid").val()},
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "true"){
					showerror($("#feetypeid"),"Fee Type Id already exists...")
					$("#feetypeid").val("");
				}
			}
		});
	}
}

function checkduplicatefeeTypeName(){
	
	if($("#feetypename").val() != $("#hiddenfeename").val()){
		$.ajax({
			type  : "POST",
			url   : "addfee.html?method=checkduplicatefeeTypeName",
			data  : {"feetypename" :$("#feetypename").val()},
			async : false,
			success : function(data){
				var result = $.parseJSON(data);
				if(result.status == "true"){
					showerror($("#feetypename"),"Fee Name already exists...")
					$("#feetypename").val("");
				}
			}
		});
	}
}

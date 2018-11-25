function removeMessage() {

	$(".successmessagediv").hide();
	$(".successmessagediv").hide();

}

function ajaxCall() {
    this.send = function(data, url, method, success, type) {
      type = type||'json';
      var successRes = function(data) {
          success(data);
      };

      var errorRes = function(e) {
          console.log(e);
         // //alert("Error found \nError Code: "+e.status+" \nError Message: "+e.statusText);
      };
        $.ajax({
            url: url,
            type: method,
            data: data,
            success: successRes,
            error: errorRes,
            dataType: type
        });

      }

    }

function locationInfo() {
	var rootUrl = "locationDetails.html";
	var call = new ajaxCall();

	this.getCities = function(id) {
	    $(".cities option:gt(0)").remove();
	    var url = rootUrl+'?method=getCities&stateId=' + id;
	    var method = "post";
	    var data = {};
	    $('.cities').find("option:eq(0)").html("Please wait..");
	    call.send(data, url, method, function(data) {
	        $('.cities').find("option:eq(0)").html("Select City");
	       
	            /*$.each(data['list'], function(i, list) {
	                var option = $('<option />');
	                option.attr('value', data.list[i].cityId).text(data.list[i].cityName);
	                $('.cities').append(option);
	            });*/
	            
	            var i=0;
	            var len=data.list.length;
	            for(i=0;i<len;i++){
	            	var option = $('<option />');
	                option.attr('value', data.list[i].cityId).text(data.list[i].cityName);
	                $('.cities').append(option);
	            }
	            
	            $(".cities").prop("disabled",false);
	            $(".cities").val($("#hiddencity").val());
	    });
	};

	this.getStates = function(id) {
	    $(".states option:gt(0)").remove(); 
	    $(".cities option:gt(0)").remove(); 
	    var url = rootUrl+'?method=getStates&countryId=' + id;
	    var method = "post";
	    var data = {};
	    $('.states').find("option:eq(0)").html("Please wait..");
	    call.send(data, url, method, function(data) {
	        $('.states').find("option:eq(0)").html("Select State");
	        
	            /*$.each(data['list'], function(i, list) {
	                var option = $('<option />');
	                option.attr('value', data.list[i].stateId).text(data.list[i].stateName);
	                $('.states').append(option);
	            });*/
	            
	            var i=0;
	            var len=data.list.length;
	            for(i=0;i<len;i++){
	            	var option = $('<option />');
	                option.attr('value', data.list[i].stateId).text(data.list[i].stateName);
	                $('.states').append(option);
	            }
	            
	            $(".states").prop("disabled",false);
	            $(".states").val($("#hiddenstate").val());
	    }); 
	};

	this.getCountries = function() {
	    var url = rootUrl+'?method=getCountries';
	    var method = "post";
	    var data = {};
	    $('.countries').find("option:eq(0)").html("Please wait..");
	    call.send(data, url, method, function(data) {
	        $('.countries').find("option:eq(0)").html("Select Country");
	        console.log(data);
	         
	           /* $.each(data['list'],function(i,list) {
	                var option = $('<option/>');
	                option.attr('value', data.list[i].countryId).text(data.list[i].countryName);
	                $('.countries').append(option);
	            });*/
	            
	            var i=0;
	            var len=data.list.length;
	            for(i=0;i<len;i++){
	            	 var option = $('<option/>');
		                option.attr('value', data.list[i].countryId).text(data.list[i].countryName);
		                $('.countries').append(option);
	            }
	            
	            $(".countries").prop("disabled",false);
	            $(".countries").val($("#hiddencountry").val());
	         
	    }); 
	};

}

$(function() {
var loc = new locationInfo();
loc.getCountries();

if($("#hiddencountry").val()!="" || $("#hiddencountry").val()!=undefined){
	loc.getStates($("#hiddencountry").val());
	loc.getCities($("#hiddenstate").val());
}

$(".countries").on("change", function(ev) {
    var countryId = $(this).val();
    if(countryId != ''){
    loc.getStates(countryId);
    }
    else{
        $(".states option:gt(0)").remove();
        $(".cities option:gt(0)").remove();
    }
});
$(".states").on("change", function(ev) {
    var stateId = $(this).val();
    if(stateId != ''){
    loc.getCities(stateId);
    }
    else{
        $(".cities option:gt(0)").remove();
    }
});
});

 
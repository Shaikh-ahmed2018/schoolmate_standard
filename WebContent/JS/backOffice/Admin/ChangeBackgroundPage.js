$(document).ready(function(){
	
	$("#cancelcp").click(function(){
		$("#oldpassword").val("");
		$("#newPassword").val("");
		$("#confirmPassword").val("");
	});
	
	var prevHeaderbackgroundcolor=$("#main-header").css("background-color");
	headerbackgroundcolor="";
	$(".header-color").spectrum({
	    color: "#ECC",
	    showInput: true,
	    className: "full-spectrum",
	    showInitial: true,
	    showPalette: true,
	    showSelectionPalette: true,
	    maxSelectionSize: 10,
	    preferredFormat: "hex",
	    localStorageKey: "spectrum.demo",
	    move: function (color) {
	        
	    },
	    show: function () {
	    
	    },
	    beforeShow: function () {
	    
	    },
	    hide: function () {
	    
	    },
	    change: function(backgroundColor) {
	    	$(".buttons").show();
	    	$("#main-header,.buttons,.primarythemebackgound,.modal-header").css({"background-color": backgroundColor.toHexString()});
	    	$("#reset").css({"background-color":prevHeaderbackgroundcolor});
	    	
	    	$(".leftNav").css({"background-color": backgroundColor.toHexString()});
	    	$("#pageHeading,.glyphicon").css("color",backgroundColor.toHexString());
	    	
	    	headerbackgroundcolor=backgroundColor.toHexString();
	    	setContrast(backgroundColor);
	    
	       	    },
	    palette: [
	        ["rgb(0, 0, 0)", "rgb(67, 67, 67)", "rgb(102, 102, 102)",
	        "rgb(204, 204, 204)", "rgb(217, 217, 217)","rgb(255, 255, 255)"],
	        ["rgb(152, 0, 0)", "rgb(255, 0, 0)", "rgb(255, 153, 0)", "rgb(255, 255, 0)", "rgb(0, 255, 0)",
	        "rgb(0, 255, 255)", "rgb(74, 134, 232)", "rgb(0, 0, 255)", "rgb(153, 0, 255)", "rgb(255, 0, 255)"], 
	        ["rgb(230, 184, 175)", "rgb(244, 204, 204)", "rgb(252, 229, 205)", "rgb(255, 242, 204)", "rgb(217, 234, 211)", 
	        "rgb(208, 224, 227)", "rgb(201, 218, 248)", "rgb(207, 226, 243)", "rgb(217, 210, 233)", "rgb(234, 209, 220)", 
	        "rgb(221, 126, 107)", "rgb(234, 153, 153)", "rgb(249, 203, 156)", "rgb(255, 229, 153)", "rgb(182, 215, 168)", 
	        "rgb(162, 196, 201)", "rgb(164, 194, 244)", "rgb(159, 197, 232)", "rgb(180, 167, 214)", "rgb(213, 166, 189)", 
	        "rgb(204, 65, 37)", "rgb(224, 102, 102)", "rgb(246, 178, 107)", "rgb(255, 217, 102)", "rgb(147, 196, 125)", 
	        "rgb(118, 165, 175)", "rgb(109, 158, 235)", "rgb(111, 168, 220)", "rgb(142, 124, 195)", "rgb(194, 123, 160)",
	        "rgb(166, 28, 0)", "rgb(204, 0, 0)", "rgb(230, 145, 56)", "rgb(241, 194, 50)", "rgb(106, 168, 79)",
	        "rgb(69, 129, 142)", "rgb(60, 120, 216)", "rgb(61, 133, 198)", "rgb(103, 78, 167)", "rgb(166, 77, 121)",
	        "rgb(91, 15, 0)", "rgb(102, 0, 0)", "rgb(120, 63, 4)", "rgb(127, 96, 0)", "rgb(39, 78, 19)", 
	        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)","rgba(255, 255, 255,0.1)"]
	    ]
	});
	$("#reset").click(function(){
		$(".successmessagediv").show();
		$(".successmessagediv .validateTips").text("Resetting Theme ...");
		setTimeout(function(){
			location.reload();
		},4000);
	});
	
	
	$("#headerbackgroundsave").click(function(){
		$.ajax({
			type:'POST',
			url:'menuslist.html?method=themeDesign',
			data:{"background":headerbackgroundcolor,
					"forecolor":fore,
					"hoverColor":hoverColor,
					"lighthoverColor":lighthoverColor,
					"lightThemeColor":lightThemeColor,
			},
			success:function(response){
				var result=$.parseJSON(response);
				$(".successmessagediv1").show();
				$("#sucessmessage").text(result.status);
				setTimeout(function(){
					location.reload(true);
				},2000);
			}		
		});
	});
});

function setContrast(rgb) {
	  // randomly update
	rgbg=convertHex("'"+rgb+"'");
	var thirdClor=(Number(rgbg[1])-20);
	if((Number(rgbg[1])-20)<0){
		thirdClor=(Number(rgbg[1])-10)*(-5);
	}
	hoverColor="rgb("+rgbg[0]+","+thirdClor+","+rgbg[2]+")";
	$("#"+$('#highlightName').val()).css("background-color", hoverColor);
	lighthoverColor="rgba("+rgbg[0]+","+thirdClor+","+rgbg[2]+",0.2)";
	
	lightThemeColor="rgba("+rgbg[0]+","+rgbg[1]+","+rgbg[2]+",0.1)";
	  // http://www.w3.org/TR/AERT#color-contrast
	  var o = Math.round(((parseInt(rgbg[0]) * 299) +
	                      (parseInt(rgbg[1]) * 587) +
	                      (parseInt(rgbg[2]) * 114)) / 1000);
	  
	  
	if(o>125){
		fore='#000';
	}
	else{
		fore='#fff';
	}
	  $('.navbar-inverse .navbar-nav>li>a,.headerNavBarB,.headerNavBarS,.leftNav h3.panel-title,.buttons,.headerLogoutIcon,.sideMenuIcon,.primarythemecolor,.modal-header').css('color', fore); 
	}

function convertHex(hex){
	result=[];
    hex = hex.replace('#','').substring(1,hex.length);
    r = parseInt(hex.substring(0,2), 16);
    g = parseInt(hex.substring(2,4), 16);
    b = parseInt(hex.substring(4,6), 16);
    
    result.push(r);
    result.push(g);
    result.push(b);
    return result;
}

function changeColorSave(fore,hoverColor,lighthoverColor,lightThemeColor){
	
	$.ajax({
		type:'POST',
		url:'menuslist.html?method=themeDesign',
		data:{"background":headerbackgroundcolor,
				"forecolor":fore,
				"hoverColor":hoverColor,
				"lighthoverColor":lighthoverColor,
				"lightThemeColor":lightThemeColor,
		},
		success:function(response){
			var result=$.parseJSON(response);
			$(".successmessagediv").show();
			$(".successmessagediv .validateTips").text(result.status);
			setTimeout(function(){
				location.reload(true);
			},2000);
		}		
	});
}


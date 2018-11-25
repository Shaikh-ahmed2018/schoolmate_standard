
$(document).ready(function() {
	

	
	
	sessionStorage=0;
	$("section.content").css({
		"margin-top":$("nav.navbar.navbar-inverse.navbar-fixed-top").height()-1
	});
	var windowSize=$(window).width();
	
	
	rgbg=convertHex("'"+rgb2hex($("#main-header").css("background-color"))+"'");
	var thirdClor=(Number(rgbg[1])-20);
	if((Number(rgbg[1])-20)<0){
		thirdClor=(Number(rgbg[1])-10)*(-5);
	}
	
	$("#"+$('#highlightName').val()).css("background-color", "rgb("+rgbg[0]+","+thirdClor+","+rgbg[2]+")");
	$("#"+$('#subModuleHighlightName').val()).css("background-color", "#eee");
	$("#"+$('#subModuleHighlightName').val()).css("color", "#000");
	
if($('#subModuleHighlightName').val()!=undefined){
	$(".main_menus ul").css({"display":"none"});
	$("#"+$('#subModuleHighlightName').val()).closest(".main_menus ul").css({"display":"block"});
}
	
	$("div[id^='menucollapse'] li a").click(function(){
		 scrollPosition=$(this).closest("div[id^='menucollapse']").scrollTop();
		if (typeof(Storage) !== "undefined") {
		    // Store
		    sessionStorage.setItem("scrollPosition", scrollPosition);
		    // Retrieve
		  
		} 
		
	});
	if(sessionStorage.getItem("scrollPosition")!=null){
		$("#"+$('#subModuleHighlightName').val()).closest("div[id^='menucollapse']").scrollTop(sessionStorage.getItem("scrollPosition"));
	}

	$(window).resize(function(){
		var windowSize=$(this).width();
		
		$("section.content").css({
			"margin-top":$("nav.navbar.navbar-inverse.navbar-fixed-top").height()
		});
	});
	
	  

	
});

function rgb2hex(orig) {
	  var a, isPercent,
	    rgb = orig.replace(/\s/g, '').match(/^rgba?\((\d+),(\d+),(\d+),?([^,\s)]+)?/i),
	    alpha = (rgb && rgb[4] || "").trim(),
	    hex = rgb ? "#" +
	    (rgb[1] | 1 << 8).toString(16).slice(1) +
	    (rgb[2] | 1 << 8).toString(16).slice(1) +
	    (rgb[3] | 1 << 8).toString(16).slice(1) : orig;
	  if (alpha !== "") {
	    isPercent = alpha.indexOf("%") > -1;
	    a = parseFloat(alpha);
	    if (!isPercent && a >= 0 && a <= 1) {
	      a = Math.round(255 * a);
	    } else if (isPercent && a >= 0 && a <= 100) {
	      a = Math.round(255 * a / 100)
	    } else {
	      a = "";
	    }
	  }
	  if (a) {
	    hex += (a | 1 << 8).toString(16).slice(1);
	  }
	  return hex;
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
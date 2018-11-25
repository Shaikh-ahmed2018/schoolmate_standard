$(document).ready(function () {

	boderFlag=false;
	$("#boxCorner0").click(function(){
		bordercss="#"+divId+"{border-radius:0px}";
		boderFlag=true;
		
	});
	$("#boxCorner2").click(function(){
		bordercss="#"+divId+"{border-radius:2px}";
		boderFlag=true;
	});
	$("#boxCorner4").click(function(){
		bordercss="#"+divId+"{border-radius:4px}";
		boderFlag=true;
	});
	$("#boxCorner6").click(function(){
		bordercss="#"+divId+"{border-radius:6px}";
		boderFlag=true;
	});
	$("#boxCorner8").click(function(){
		 bordercss="#"+divId+"{border-radius:8px}";
		 boderFlag=true;
	});
	
	$("#hfontval").append("<option value='0px'>-</option>" +
			"<option value='10px'>10</option>" +
			"<option value='12px'>12</option>" +
			"<option value='14px'>14</option>" +
			"<option value='16px'>16</option>" +
			"<option value='18px'>18</option>" +
			"<option value='20px'>20</option>" +
			"<option value='22px'>22</option>" +
			"<option value='24px'>24</option>" +
			"<option value='26px'>26</option>" +
			"<option value='28px'>28</option>" +
			"<option value='30px'>30</option>");
	$("#lfontval").append("<option value='0px'>-</option>" +
			"<option value='7px'>7</option>" +
			"<option value='8px'>8</option>" +
			"<option value='9px'>9</option>" +
			"<option value='10px'>10</option>" +
			"<option value='11px'>11</option>" +
			"<option value='12px'>12</option>" +
			"<option value='13px'>13</option>" +
			"<option value='14px'>14</option>" +
			"<option value='15px'>15</option>" +
			"<option value='16px'>16</option>" +
			"<option value='17px'>17</option>" +
			"<option value='18px'>18</option>");
	
	
	$(".main-div").resizable();
	$(".main-div div[id]").draggable().resizable();
	$(".bus-id-main-div div[id]").draggable();
	$("#route").click(function(){
		colorChange($(this));
	});
	$("#schoolName").dblclick(function(){
		colorChange($(this));
	});
	
		$("#print").click(function(){
		var a=$("#printing-css").val();
		var b = document.getElementById("div").innerHTML;
		window.frames["print_frame"].document.title = document.title;
	    window.frames["print_frame"].document.body.innerHTML = '<style>' + a + '</style>' + b;
	    window.frames["print_frame"].window.focus();
	    window.frames["print_frame"].window.print();
		});
		$("#printA3").click(function(){
			var a=$("#printing-css-a3").val();
			var b = document.getElementById("div").innerHTML;
			window.frames["print_frame"].document.title = document.title;
		    window.frames["print_frame"].document.body.innerHTML = '<style>' + a + '</style>' + b;
		    window.frames["print_frame"].window.focus();
		    window.frames["print_frame"].window.print();
			});
		$("#saveChanges").click(function(){
			
			var cssgroup=[];
			$(".section div[id]").each(function(){
			
			if($(this).attr("style[width]")!=undefined){
				var css="."+$(this).attr("class").split(" ")[0]+"{"
				+$(this).attr("style")+"}";
			cssgroup.push(css);
			
			var css1="#"+$(this).attr("id")+"{background-color:"+BackgroundColor+"}";
			var css2="#"+$(this).attr("id")+"{border-color:"+BoderColor+"}";
			cssgroup.push(css1);
			cssgroup.push(css2);
			
			}
			else{
			var carryCss="."+$(this).attr("class").split(" ")[0]+"{"
			+"left:"+$("."+$(this).attr("class").split(" ")[0]).css("left")+";top:"
			+$("."+$(this).attr("class").split(" ")[0]).css("top")+";" +
			"width:"+$("."+$(this).attr("class").split(" ")[0]).css("width")+";" +
			"height:"+$("."+$(this).attr("class").split(" ")[0]).css("height")+";" +
					"}";	
			cssgroup.push(carryCss);
			var carryCss1="#"+$(this).attr("id")+"{background-color:"+rgb2hex($(this).css('background-color'))+"}";
			cssgroup.push(carryCss1);
			var carryCss2="#"+$(this).attr("id")+"{border-color:"+rgb2hex($("#"+$(this).attr("id")).css('border-color'))+"}";
			cssgroup.push(carryCss2);
			}
			
			if(boderFlag){
				cssgroup.push(bordercss);
				var borderCarryCss="#"+$(this).not("#"+divId).attr("id")+"{border-radius:"+$("#"+$(this).attr("id")).css('border-radius')+"}";
				cssgroup.push(borderCarryCss);
			}
			else{
				var borderCarryCss="#"+$(this).attr("id")+"{border-radius:"+$("#"+$(this).attr("id")).css('border-radius')+"}";
				cssgroup.push(borderCarryCss);
			}
			});
			$(".section span[id]").each(function(){
				
				
				if($(this).attr("style[color]")!=undefined){
					var css="#"+$(this).attr("id")+"{color:"+routeColor+"}";
					var css1=".label{background-color:"+labelbackgroundColor+"}";
					var css2=".addresss{background-color:"+labelbackgroundColor+"}";
					var css3=".phones{background-color:"+labelbackgroundColor+"}";
					
				cssgroup.push(css);
				cssgroup.push(css1);
				cssgroup.push(css2);
				cssgroup.push(css3);
			
				}
				else{
				var carryCss="#"+$(this).attr("id")+"{color:"+rgb2hex($(this).css('color'))+"}";
				var carryCss1=".label{background-color:"+rgb2hex($(".label").css('background-color'))+"}";
				var carryCss2=".addresss{background-color:"+rgb2hex($(".label").css('background-color'))+"}";
				var carryCss3=".phones{background-color:"+rgb2hex($(".label").css('background-color'))+"}";
				
				cssgroup.push(carryCss);
				cssgroup.push(carryCss1);
				cssgroup.push(carryCss2);
				cssgroup.push(carryCss3);
			
				}
				
				
				});
			
			if($('#lfontval').val()!="0px"){
			
				var css5="#nametext{font-size:"+$('#lfontval').val()+"}#classlebel{font-size:"+$('#lfontval').val()+"}#pointr{font-size:"+$('#lfontval').val()+"}#classtext{font-size:"+$('#lfontval').val()+"}";
				
				cssgroup.push(css5);
			}
			else{
				var carryCss5="#nametext{font-size:"+$('#nametext').css('font-size')+"}#classlebel{font-size:"+$('#nametext').css('font-size')+"}#pointr{font-size:"+$('#nametext').css('font-size')+"}#classtext{font-size:"+$('#nametext').css('font-size')+"}";
				cssgroup.push(carryCss5);
			}
			if($('#hfontval').val()!="0px"){
				
				var css4="#schoolNameChange{font-size:"+$('#hfontval').val()+"}";
				cssgroup.push(css4);
			}
			else{
				var carryCss4="#schoolNameChange{font-size:"+$("#schoolNameChange").css('font-size')+"}";
				cssgroup.push(carryCss4);
			}
		
			var mainCss=cssgroup.toString().replace(/,/g,"");
			 $.ajax({
				 type: 'POST', 
				  url: "IDCardMenu.html?method=IdDesign",
		          data:{"mainCss":mainCss,"layout":$("#layoutDetails").val()},
		         
				  success: function(response) {
					  
					  var result = $.parseJSON(response);
						
				  }
				
			});
			
			$(".successmessagediv").show();
			$(".successmessagediv span").text("Save Position progressing...");
			setTimeout(function(){
			location.reload();
			},2000);
			});
		
		
		
		$("#busSaveChanges").click(function(){
			
			var cssgroup=[];
			if($('#route').attr("style")!=undefined){
			route=".route{color:"+routeColor+"}";
			}
			
			else{
				route=".route{color:"+rgb2hex($('#route').css('color'))+"}";
			}
			$(".sectionb div[id]").each(function(){
				
			
			if($(this).attr("style")!=undefined){
				var css="."+$(this).attr("class").split(" ")[0]+"{"
				+$(this).attr("style")+"}";
			cssgroup.push(css);
			}
			else{
			var carryCss="."+$(this).attr("class").split(" ")[0]+"{"
			+"left:"+$("."+$(this).attr("class").split(" ")[0]).css("left")+";top:"
			+$("."+$(this).attr("class").split(" ")[0]).css("top")+";}";	
			cssgroup.push(carryCss);
			}
			});
			cssgroup.push(route);
		
			var mainCss=cssgroup.toString().replace(/,/g,"");
			 $.ajax({
				 type: 'POST', 
				  url: "menuslist.html?method=busIdDesign",
		          data:{"mainCss":mainCss},
		         
				  success: function(response) {
					  
					  var result = $.parseJSON(response);
						
				  }
				
			});
			
			$(".successmessagediv").show();
			$(".successmessagediv span").text("Save Position progressing...");
			setTimeout(function(){
			location.reload();
			},2000);
			});
		$(".bus-id-head").click(function(){
			$(".sectionb").slideToggle();
		});
		$(".id-head").click(function(){
			$(".section").slideToggle();
		});
		
		
		
		$("#main-div span").bind("contextmenu",function(e){
			  e.preventDefault();
			  thispointer=$(this);
			  thisId=thispointer.attr("id");
			  $(".cntr").css("right",0);
			  $(".cntr").css("top",0);
			 // $("#cntnr").hide(100);        
			  $(".cntr").fadeIn(200,startFocusOut());      
			});

			function startFocusOut(){
			  $(document).on("click",function(){
			  $(".cntr").hide();        
			  $(document).off("click");
			  });
			}
			
			$("#items > .changeColor").spectrum({
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
				    change: function(color) {
				    	 $("#basic-log").text("change called: " + color.toHexString());
				    	 document.getElementById(thisId).style.color = color.toHexString(); 
				         routeColor=color.toHexString();
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
				        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
				    ]
				});
			
			
			$("#items > .labelBackgroundColor").spectrum({
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
			    change: function(color) {
			    	 $("#basic-log").text("change called: " + color.toHexString());
			    	$(".label").css({'background-color':color.toHexString()});
			    	 labelbackgroundColor=color.toHexString();
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
			        "rgb(12, 52, 61)", "rgb(28, 69, 135)", "rgb(7, 55, 99)", "rgb(32, 18, 77)", "rgb(76, 17, 48)"]
			    ]
			});
			$("#items > .changeBorderColor").backspectrum({
				backgroundColor: "#ECC",
			    showInput: true,
			    className: "full-spectrum",
			    showInitial: true,
			    showPalette: true,
			    showSelectionPalette: true,
			    maxSelectionSize: 10,
			    preferredFormat: "hex",
			    localStorageKey: "backspectrum.demo",
			    move: function (backgroundColor) {
			        
			    },
			    show: function () {
			    
			    },
			    beforeShow: function () {
			    
			    },
			    hide: function () {
			    
			    },
			    change: function(backgroundColor) {
			    	 $("#basic-log").text("change called: " + backgroundColor.toHexString());
			    	 document.getElementById(divId).style.borderColor = backgroundColor; 
			    	 BorderColor=backgroundColor.toHex8String();
			    	 if(BorderColor=="#1affffff"){
			    		 BorderColor="#00ffffff";
			    		
			    	 }
			    	 
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
			
			$("#main-div div[id]").bind("contextmenu",function(e){
				  e.preventDefault();
				  divpointer=$(this);
				  divId=divpointer.attr("id");
				  $(".cntr").css("right",0);
				  $(".cntr").css("top",0);
				 // $("#cntnr").hide(100);        
				  $(".cntr").fadeIn(200,startFocusOut());      
				});

				function startFocusOut(){
				  $(document).on("click",function(){
				  $(".cntr").hide();        
				  $(document).off("click");
				  });
				}
				
				$("#items > .changeBackgroundColor").backspectrum({
					backgroundColor: "#ECC",
				    showInput: true,
				    className: "full-spectrum",
				    showInitial: true,
				    showPalette: true,
				    showSelectionPalette: true,
				    maxSelectionSize: 10,
				    preferredFormat: "hex",
				    localStorageKey: "backspectrum.demo",
				    move: function (backgroundColor) {
				        
				    },
				    show: function () {
				    
				    },
				    beforeShow: function () {
				    
				    },
				    hide: function () {
				    
				    },
				    change: function(backgroundColor) {
				    	 $("#basic-log").text("change called: " + backgroundColor.toHexString());
				    	 document.getElementById(divId).style.backgroundColor = backgroundColor; 
				    	 BackgroundColor=backgroundColor.toHex8String();
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
				$("#changeBackgroundImage").click(function(){
					$("#backgroundfile").click();
				});
				$("#backgroundfile").change(function(){
					readURL(this);
					var formdata;
					formdata = new FormData();
					var file=$("#backgroundfile")[0].files[0];
				

						formdata.append("inputfile",file); 
						formdata.append("layoutDetails",$("#layoutDetails").val()); 

						$.ajax({
							url:'IDCardMenu.html?method=saveLayoutImage',
							cache:false,
							type: 'POST',
							data:formdata,
							contentType: false,
							processData: false,

							success:function(data){
								
							} 
						});
					
				});
				
				$("#main-div").bind("contextmenu",function(e){
					  e.preventDefault();
					 
					  $(".cntr").css("right",0);
					  $(".cntr").css("top",0);
					 // $("#cntnr").hide(100);        
					  $(".cntr").fadeIn(200,startFocusOut());      
					});

					function startFocusOut(){
					  $(document).on("click",function(){
					  $(".cntr").hide();        
					  $(document).off("click");
					  });
					}
					
					$(".labelfontSize").click(function(){
						$("#lfontval").show();
						
					});
					$("#lfontval").blur(function(){
						$("#lfontval").hide();
					});
				$(".headerfontSize").click(function(){
					$("#hfontval").show();
					
				});
				$("#hfontval").blur(function(){
					$("#hfontval").hide();
				});
				});		

function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#layoutImage').attr('src', e.target.result);
		};

		reader.readAsDataURL(input.files[0]);
		
	}

}

/*function rgb2hex(rgb){
	 rgb = rgb.match(/^rgba?[\s+]?\([\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?,[\s+]?(\d+)[\s+]?/i);
	 return (rgb && rgb.length === 4) ? "#" +
	  ("0" + parseInt(rgb[1],10).toString(16)).slice(-2) +
	  ("0" + parseInt(rgb[2],10).toString(16)).slice(-2) +
	  ("0" + parseInt(rgb[3],10).toString(16)).slice(-2) : '';
	}*/
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

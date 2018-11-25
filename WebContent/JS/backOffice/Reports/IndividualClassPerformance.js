$(document).ready(function(){
	
	$("#back").click(function(){
		window.location.href="reportaction.html?method=classPerformance";
	});
	
	var accYear=$("#accYear").val();
	var locationId = $("#location").val();
	var classId = $("#classId").val();
	var SectionId = $("#SectionId").val();
	var ExamCode = $("#ExamCode").val();
	var studentId=$("#studentId").val();
	$.ajax({
		type : 'POST',
		url : "reportaction.html?method=getChartDetail",
		data : {
			"accYear":accYear,
			"locationId" :locationId,
			"classId" :classId,	
			"SectionId":SectionId,
			"ExamCode":ExamCode,
			"studentId":studentId
		},
		async : false,
		
		success : function(response) {
			var result = $.parseJSON(response);
		             getChart(result);
		            return false;
		}
	});
	
	printChart();
});
function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}
function getChart(result){
	var options;
	for(var i=0; i<result.detail.length; i++){
		
	var color1=getRandomColor();
	 options = {
				title: {
					text: "Class Performance",
				//	color:"white"
				},
			            animationEnabled: true,
                       axisY: {
            	            title: "Students--->",
            	            /*valueFormatString: " ",*/
       			         	tickLength:0
            	        	
			            },
			            axisX:{
			            	title: "Student Percentage---->",
			            		stripLines: [{
			            			 color:"black",
			            	         value: result.detail[i].mainCount,
			            	         showOnTop: true,
			            	         valueFormatString: " ",
			    			         tickLength: 0
			            	       }]
			            	
			            },
			            legend:{
			            	//dockInsidePlotArea: true,
			                horizontalAlign: "right", 
			                verticalAlign: "center",
			                fontSize: 20,
			                fontFamily: "tamoha",
			                fontColor: "black"      
			              },
				data: [
				{
					 color:color1,
					 showInLegend: "true",
					 legendMarkerColor: "black",
					 legendText: result.detail[i].mainName,
					 type: "column",
					 dataPoints: [
						{ x: 10, y: result.detail[i].count },
						{ x: 20, y: result.detail[i].count1 },
						{ x: 30, y: result.detail[i].count2 },
						{ x: 40, y: result.detail[i].count3 },
						{ x: 50, y: result.detail[i].count4 },
						{ x: 60, y: result.detail[i].count5},
						{ x: 70, y: result.detail[i].count6 },
						{ x: 80, y: result.detail[i].count7 },
						{ x: 90, y: result.detail[i].count8 },
						{ x: 100, y: result.detail[i].count9 },
					]
				
				},{
					color:color1,
					 showInLegend: "true",
					 legendMarkerColor: "black",
					 legendText:result.detail[i].rank,
					 type: "column",
					 dataPoints: [
									{ x: 10, y: result.detail[i].count },
									{ x: 20, y: result.detail[i].count1 },
									{ x: 30, y: result.detail[i].count2 },
									{ x: 40, y: result.detail[i].count3 },
									{ x: 50, y: result.detail[i].count4 },
									{ x: 60, y: result.detail[i].count5},
									{ x: 70, y: result.detail[i].count6 },
									{ x: 80, y: result.detail[i].count7 },
									{ x: 90, y: result.detail[i].count8 },
									{ x: 100, y: result.detail[i].count9 },
								]
				}
				]
			};
	}
			$("#chartContainer").CanvasJSChart(options);
			/*$("#print").click(function(){
				$("#chartContainer").printElement()
				  ;
		        var divContents = $("#chartContainer").html();
		        var printWindow = window.open('', '', 'height=400,width=800');
		        printWindow.document.write('<html><head><title>DIV Contents</title>');
		        printWindow.document.write('</head><body >');
		        printWindow.document.write(divContents);
		        printWindow.document.write('</body></html>');
		        printWindow.document.close();
		        printWindow.print();
		    });*/
		}
	function printChart(){
		$("#print").click(function () {
			$(".leftmenu").hide();
			$(".panel-list").css({"border-color":"white"});
			$("#div-main").css({"border-bottom":""});
			$("#div-main").css({"border-left":""});
			$("#div-main").css({"border-right":""});
				//$("#div-main").css({ "padding-top":"90px"});
				//$("#div-main").css({ "margin-left":"10%"});
				$("#collapseOne").css({ "margin-top":"12%"});
				$("body").css({ "height":"90%"});
				$("lable").css({ "margin-right": "10px;"});
			$(".panel-heading").hide();
			$("p").hide();
			$("#forPrint").show();
			window.print();
			location.reload(true); 
		});
	}
